package uk.ac.kcl.interpreter

import java.util.ArrayList
import java.util.HashMap
import java.util.Iterator
import java.util.LinkedList
import java.util.List
import java.util.Random
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.interpreter.objectives.ocl.OclFitnessFunction

/**
 * An interpreter for optimisation specifications. This class provides the basic functionality
 * for interpreting optimisation scripts, but does not actually contain a concrete optimisation
 * algorithm. Such algorithms are provided separately through a strategy object.
 */
// TODO: Add a combine method to support cross-over like steps for multi-objective optimisers. Use something like EMF Diff/Merge for implementation (see {@see http://wiki.eclipse.org/EMF_DiffMerge/Programmatic_Usage}).
class OptimisationInterpreter {

	/**
	 * The model describing the optimisation problem and evolution operators
	 */
	private Optimisation optimisationModel


    /**
     * Strategy object for optimisation algorithms.
     */
    // TODO Eventually want to be able to inject this from a launch configuration or similar
    private IOptimisationAlgorithm optimisationStrategy

    /**
     * Strategy for generating initial models
     */
    // TODO Eventually want to be able to inject this from a launch configuration or similar
    private IModelProvider initalModelProvider

    private HenshinResourceSet henshinResourceSet = null

    private EPackage theMetamodel = null

    /**
     * The list of Henshin rules used as evolvers
     */
    private List<Unit> henshinEvolvers = null

    /**
     * Cache for the fitness function object
     */
    private List<IFitnessFunction> fitnessFunctions = null

    new(Optimisation model, IOptimisationAlgorithm algorithm, IModelProvider initalModelProvider) {
        this.optimisationModel = model
        optimisationStrategy = algorithm
        this.initalModelProvider = initalModelProvider
    }

    public def execute() {
        optimisationStrategy.execute(this)
    }

    /**
     * This will produce a lazy iteration of possible initial solutions
     */
    def Iterator<EObject> getInitialSolutions() {
        initalModelProvider.initialModels(metamodel)
    }

	/**
	 * This will compute the fitness for the given candidate solution
	 */
	def List<Double> fitness(EObject candidateSolution) {
		
		if (candidateSolution == null) {
			throw new NullPointerException();
		}

		if (fitnessFunctions == null) {
			
			fitnessFunctions = new LinkedList();
			
//			//Instantiate functions using defined paths
//			if(!optimisationModel.fitness.empty){
//				fitnessFunctions.addAll(optimisationModel.fitness.map [ f |
//					val Class<? extends IFitnessFunction> fitnessClass = Class.forName(
//						f.fitnessClass) as Class<? extends IFitnessFunction>
//					fitnessClass.newInstance
//				])
//			}
//			
//			//Create OCL interpreter fitness functions
//			if(!optimisationModel.objectives.empty){
//				fitnessFunctions.addAll(optimisationModel.objectives.map [ o |
//					new OclFitnessFunction(o)])
//			}

		}

		fitnessFunctions.map[f|f.computeFitness(candidateSolution)]
	}


    /**
     * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
     * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
     * otherwise returns the result of the first randomly picked evolver that was applicable.
     */
    def EObject evolve(EObject object) {
        // Extract Henshin evolvers if necessary
        if (henshinEvolvers == null) {
            val hrs = resourceSet
            henshinEvolvers = optimisationModel.evolvers.map [ e |
                hrs.getModule(URI.createURI(e.rule_location), false).getUnit(e.unit)
            ]
        }

        // Make a copy of the evolvers list so that we can keep track of which evolvers we have already tried.
        val evolversToTry = new ArrayList(henshinEvolvers.toList)

        do {
            // 1. Pick an evolver
            val evolver = evolversToTry.remove(new Random().nextInt(evolversToTry.size))

            // 2. Make a copy of the current candidate solution
            // Doing this here just in case unsuccessfully applying an evolver has already made changes
            val candidateSolution = EcoreUtil.copy(object)

            // 3. Apply the transformation
            // TODO: Some of these objects we may actually be able to reuse across evolver calls.
            // Waiting to define a test with multiple evolvers before doing this so that I can safely assess whether it will break anything.
            val graph = new EGraphImpl(candidateSolution)
            val engine = new EngineImpl
            val runner = new UnitApplicationImpl(engine)
            runner.EGraph = graph

            runner.unit = evolver
            if (runner.execute(null)) {
                // 4. Return the transformed solution
                return graph.roots.head
            }
        } while (!evolversToTry.empty)

        // We didn't find any applicable evolvers...
        null
    }

    def getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(optimisationModel.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = resourceSet.registerDynamicEPackages(optimisationModel.metamodel.location).head
        }

        theMetamodel
    }

    /**
     * Pareto-rank the provided solutions and return at most targetPopulationSize many starting with the top ranked ones.
     */
    def Pair<List<EObject>, List<List<Double>>> getTopRanked(int targetPopulationSize, List<EObject> solutions, List<List<Double>> fitnesses) {
        // TODO Not a very efficient implementation. Eventually to be shifted to some other framework

        // Build up the domination order
        val dominators = new HashMap<EObject, List<EObject>>()

        solutions.forEach[s, idx|
            dominators.put (s, new LinkedList<EObject>())
            solutions.forEach[s2, idx2 |
                if (fitnesses.get(idx2).dominates (fitnesses.get(idx))) {
                    dominators.get(s).add (s2)
                }
            ]
        ]

        // Iterate through the ranks until we've found enough elements
        val tentativeResult = new Pair<List<EObject>, List<List<Double>>>(new LinkedList<EObject>(),new LinkedList<List<Double>>())
        val adjustedTgtSize = Math.min (targetPopulationSize, solutions.size)
        while (tentativeResult.key.size < adjustedTgtSize) {
            // Find top ranked solutions
            val undominatedSolutions = dominators.keySet.filter[cs | dominators.get(cs).size == 0].toList

            // Remove them from domination matrix
            dominators.values.forEach[lDominators | lDominators.removeAll(undominatedSolutions)]

            // Add them to solution population
            tentativeResult.key.addAll(undominatedSolutions.take(adjustedTgtSize - tentativeResult.key.size))
            tentativeResult.value.addAll(undominatedSolutions.map[cs | fitnesses.get(solutions.indexOf(cs))].take (adjustedTgtSize - tentativeResult.value.size))
        }

        tentativeResult
    }

    /**
     * Check domination between two fitness vectors. Returns true if fitness1 dominates fitness2
     */
    def boolean dominates(List<Double> fitness1, List<Double> fitness2) {
        (0..<fitness1.size).forall[i | fitness1.get(i) >= fitness2.get(i)] &&
                (0..<fitness1.size).exists[i | fitness1.get(i) > fitness2.get(i)]
    }
}