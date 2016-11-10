package uk.ac.kcl.optimisation

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import java.util.List
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.mdeoptimise.Optimisation
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.common.util.URI
import java.util.Random
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import java.util.ArrayList
import java.util.Iterator
import uk.ac.kcl.interpreter.IModelProvider

class SolutionGenerator {
	
	private HenshinResourceSet henshinResourceSet = null

    private EPackage theMetamodel = null

    /**
     * The list of Henshin rules used as evolvers
     */
    private List<Unit> henshinEvolvers = null
	
	public Optimisation optimisationModel
	
	IModelProvider initialModelProvider

	new(Optimisation optimisationModel, List<Unit> henshinEvolvers, HenshinResourceSet henshinResourceSet, IModelProvider modelProvider, EPackage metamodel){
		this.optimisationModel = optimisationModel
		this.henshinEvolvers = henshinEvolvers
		this.henshinResourceSet = henshinResourceSet
		this.initialModelProvider = modelProvider
		this.theMetamodel = metamodel;
	}

    /**
     * This will produce a lazy iteration of possible initial solutions
     */
    def Iterator<EObject> getInitialSolutions() {
        initialModelProvider.initialModels(theMetamodel)
    }


    /**
     * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
     * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
     * otherwise returns the result of the first randomly picked evolver that was applicable.
     */
    def EObject evolveModel(EObject object) {
    	
    	var currentObject = object;
    

        // Extract Henshin evolvers if necessary
        if (henshinEvolvers == null) {
            val hrs = henshinResourceSet
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
            val candidateSolution = EcoreUtil.copy(currentObject)

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
        //null
        //Start from scratch if cannot apply evolvers to this model?
        System.out.println("Very bad model with no evolvers applicable.....")
        //initialModelProvider.initialModels(theMetamodel).head
    	object
    
    }
	
}