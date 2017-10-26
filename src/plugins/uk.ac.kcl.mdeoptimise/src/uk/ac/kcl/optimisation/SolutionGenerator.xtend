package uk.ac.kcl.optimisation

import java.util.ArrayList
import java.util.Iterator
import java.util.List
import java.util.Random
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.henshin.interpreter.Engine
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl
import org.eclipse.emf.henshin.model.Unit
import uk.ac.kcl.interpreter.IModelProvider
import uk.ac.kcl.mdeoptimise.Optimisation

import static org.eclipse.emf.henshin.interpreter.impl.ChangeImpl.*
import org.eclipse.emf.henshin.model.Rule
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import uk.ac.kcl.interpreter.evolvers.parameters.IEvolverParametersFactory

class SolutionGenerator {

    private EPackage theMetamodel = null
	private List<Unit> breedingOperators
	private List<Unit> mutationOperators
	
	private Optimisation optimisationModel
	private IEvolverParametersFactory evolverParametersFactory
	
	IModelProvider initialModelProvider

	public Engine engine;
	
	/**
	 * Using only the UnitApplicationImpl class to run both Units
	 * and Rules as this class implements functionality to run a single Rule.
	 */
	public UnitApplicationImpl unitRunner
	
	new(Optimisation model, List<Unit> breedingOperators, List<Unit> mutationOperators, IModelProvider modelProvider, EPackage metamodel){
		this.optimisationModel = model
		this.breedingOperators = breedingOperators
		this.mutationOperators = mutationOperators
		this.initialModelProvider = modelProvider
		this.theMetamodel = metamodel;
		this.engine = new EngineImpl
		
		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		
		this.unitRunner = new UnitApplicationImpl(engine)
		
		//Disable henshin warnings
		ChangeImpl.PRINT_WARNINGS = false;
	}

    /**
     * This will produce a lazy iteration of possible initial solutions
     */
    def Iterator<EObject> getInitialSolutions() {
        initialModelProvider.initialModels(theMetamodel)
    }
    
    /**
     * Returns the optimisation model to use inside the moea problem/solution
     */
    def Optimisation getOptimisationModel() {
    	return optimisationModel
    }
	
	/**
	 * Produces two offsprint from the two parents provided in the parameter.
	 * @param parents a list of two parent models
	 * @returns a list of results offsprint
	 */
	def List<EObject> crossover(List<EObject> parents) {
				
		var crossoverParents = EcoreUtil.copyAll(parents)
		
		val graph = new EGraphImpl(crossoverParents)
		val triedOperators = new ArrayList<Unit>()
		
		// Randomly pick one unit
		var operator = breedingOperators.get(new Random().nextInt(breedingOperators.size()))

		while(triedOperators.length < breedingOperators.length) {

			// Apply the match
			unitRunner.EGraph = graph
			unitRunner.unit = operator
			unitRunner.setParameterValue("number", new Random().nextInt(5))
			
			//Run the selected Henshin Unit
			if(unitRunner.execute(null)) {
				if(graph.roots.head != null)
					return graph.roots	
			} else {
				triedOperators.add(operator)
				var remainingRules = breedingOperators.filter[ o  | !triedOperators.contains(o)];
				
				if(remainingRules.size == 0) {
					return parents
				}
				
				operator = remainingRules.get(new Random().nextInt(remainingRules.size()))
				
			}				
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
    			
		val candidateSolution = EcoreUtil.copy(object)

		// Get all matches
		val graph = new EGraphImpl(candidateSolution)

		// Randomly pick one match
		val triedOperators = new ArrayList<Unit>()
		var operator = mutationOperators.get(new Random().nextInt(mutationOperators.size()))
					
		while(triedOperators.length < mutationOperators.length){
			
			unitRunner.EGraph = graph
			unitRunner.unit = operator
			
			if(!operator.parameters.empty){
				operator.parameters.forEach[ parameter | unitRunner.setParameterValue(parameter.name, evolverParametersFactory.getParameterValue(unit, parameter.name))]	
			}
			
			//Run the selected Henshin Rule
			if(unitRunner.execute(null)){
				//println("Could run mutation" + matchToUse.name)
				if(graph.roots.head != null)
					return graph.roots.head	
			} else {
				
				
				triedOperators.add(operator);
				var remainingRules = mutationOperators.filter[ o  | !triedOperators.contains(o)];
				
				if(remainingRules.size == 0) {
					return object
				}
				
				operator = remainingRules.get(new Random().nextInt(remainingRules.size()))
				//println("Could not run mutation for rule " + matchToUse.name)
			}	
		}	
		
        // We didn't find any applicable evolvers...
        println("Model with no mutation evolvers applicable.....")
        object
    }
}