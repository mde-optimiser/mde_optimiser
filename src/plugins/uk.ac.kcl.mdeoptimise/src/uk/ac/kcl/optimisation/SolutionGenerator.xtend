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
import org.eclipse.emf.henshin.interpreter.Engine
import org.eclipse.emf.henshin.model.Rule
import org.eclipse.emf.henshin.interpreter.Match
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl
import java.io.File
import java.io.PrintStream
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl

class SolutionGenerator {
	
	private HenshinResourceSet henshinResourceSet = null

    private EPackage theMetamodel = null

    /**
     * The list of Henshin rules used as evolvers
     */
    private List<Unit> henshinEvolvers = null
	
	/**
	 * The list of Henshin crossover rules
	 */
	private List<Unit> henshinCrossoverEvolvers = null
	
	public Optimisation optimisationModel
	
	IModelProvider initialModelProvider

	public Engine engine;
	public RuleApplicationImpl runner;
	
	new(Optimisation optimisationModel, List<Unit> henshinEvolvers, HenshinResourceSet henshinResourceSet, IModelProvider modelProvider, EPackage metamodel){
		this.optimisationModel = optimisationModel
		this.henshinEvolvers = henshinEvolvers
		this.henshinResourceSet = henshinResourceSet
		this.initialModelProvider = modelProvider
		this.theMetamodel = metamodel;
		this.engine = new EngineImpl
		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		this.runner = new RuleApplicationImpl(engine)
		
		//Disable henshin warnings
		ChangeImpl.PRINT_WARNINGS = false;
	}

    /**
     * This will produce a lazy iteration of possible initial solutions
     */
    def Iterator<EObject> getInitialSolutions() {
        initialModelProvider.initialModels(theMetamodel)
    }

	def List<EObject> crossover(List<EObject> parents) {
		
		// Extract Henshin crossover evolvers if necessary
		if (henshinCrossoverEvolvers == null) {
			val hrs = henshinResourceSet
			// Explicitly creating a list here to make sure the map is only invoked once not every time we try and evolve a model
			henshinCrossoverEvolvers = new ArrayList(optimisationModel.evolvers.filter[ e | e.type.equals("crossover")].toList.map [ e |
				hrs.getModule(URI.createURI(e.rule_location), false).getUnit(e.unit)
			])
		}
		
		var crossoverParents = EcoreUtil.copyAll(parents)
		
		val graph = new EGraphImpl(crossoverParents)

		// Randomly pick one unit
		val unitToUse = henshinCrossoverEvolvers.get(new Random().nextInt(henshinCrossoverEvolvers.size()))
			
		
		// Apply the match
		runner.EGraph = graph
		runner.unit = unitToUse
		//runner.partialMatch = matchToUse.value
		
		if(runner.execute(null)) {

		if(graph.roots.head != null)
			return graph.roots	
		}

		
        // We didn't find any applicable evolvers...
        System.out.println("Model with no crossover evolvers applicable.....")
        
		return parents
	}

    /**
     * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
     * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
     * otherwise returns the result of the first randomly picked evolver that was applicable.
     */
    def EObject evolveModel(EObject object) {
    	
		// Extract Henshin evolvers if necessary
		if (henshinEvolvers == null) {
			val hrs = henshinResourceSet
			// Explicitly creating a list here to make sure the map is only invoked once not every time we try and evolve a model
			henshinEvolvers = new ArrayList(optimisationModel.evolvers.filter[ e | e.type.equals("mutation")].toList.map [ e |
				hrs.getModule(URI.createURI(e.rule_location), false).getUnit(e.unit)
			])
		}
		
		val candidateSolution = EcoreUtil.copy(object)

		// Get all matches
		val graph = new EGraphImpl(candidateSolution)

		// Randomly pick one match
		val triedRules = new ArrayList<Unit>()
		var matchToUse = henshinEvolvers.get(new Random().nextInt(henshinEvolvers.size()))
					
		while(triedRules.length < henshinEvolvers.length){
			
			runner.EGraph = graph
			runner.unit = matchToUse
			
			if(runner.execute(null)){
				//println("Could run mutation" + matchToUse.name)
				if(graph.roots.head != null)
					return graph.roots.head	
			} else {
				triedRules.add(matchToUse);
				var remainingRules = henshinEvolvers.filter[ x  | !triedRules.contains(x)];
				matchToUse = remainingRules.get(new Random().nextInt(remainingRules.size()))
				//println("Could not run mutation for rule " + matchToUse.name)
			}	
		}	
		
        // We didn't find any applicable evolvers...
        println("Model with no mutation evolvers applicable.....")
        object
    }
	
}