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
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import uk.ac.kcl.interpreter.evolvers.parameters.IEvolverParametersFactory
import org.eclipse.emf.henshin.model.HenshinPackage
import org.eclipse.emf.henshin.interpreter.EGraph
import java.util.Arrays
import uk.ac.kcl.interpreter.evolvers.parameters.EvolverParametersFactory
import org.eclipse.emf.henshin.model.ParameterKind
import uk.ac.kcl.mdeoptimise.dashboard.api.sender.Sender
import uk.ac.kcl.json.JsonEncoder

class SolutionGenerator {

    private EPackage theMetamodel = null
	private List<Unit> breedingOperators
	private List<Unit> mutationOperators
	private String experimentId
	
	private Optimisation optimisationModel
	private IEvolverParametersFactory evolverParametersFactory
	
	IModelProvider initialModelProvider

	public Engine engine;
	
	/**
	 * Using only the UnitApplicationImpl class to run both Units
	 * and Rules as this class implements functionality to run a single Rule.
	 */
	public UnitApplicationImpl unitRunner
	public RuleApplicationImpl ruleRunner
	
	private Sender sender
	
	new(Optimisation model, List<Unit> breedingOperators, List<Unit> mutationOperators, IModelProvider modelProvider, EPackage metamodel, String experimentId){
		this.optimisationModel = model
		this.breedingOperators = breedingOperators
		this.mutationOperators = mutationOperators
		this.initialModelProvider = modelProvider
		this.theMetamodel = metamodel
		this.engine = new EngineImpl
		this.experimentId = experimentId
		
		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		
		this.unitRunner = new UnitApplicationImpl(engine)
		this.ruleRunner = new RuleApplicationImpl(engine)
		this.evolverParametersFactory = new EvolverParametersFactory(model.evolvers)
		
		//Disable henshin warnings
		ChangeImpl.PRINT_WARNINGS = false;
		this.sender = new Sender()
		var message = JsonEncoder.generateWorkerRegistrationText(model, experimentId)
		sender.sendMessage(message)
		System.out.println("[MDEO] worker registration message sent: " + message);
	}
	
	def String getExperimentId() {
		return this.experimentId;
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
	 * Produces two offspring from the two parents provided in the parameter.
	 * @param parents a list of two parent models
	 * @returns a list of results offspring
	 */
	def List<EObject> crossover(List<EObject> parents) {
				
		var crossoverParents = EcoreUtil.copyAll(parents)
		
		val graph = new EGraphImpl(crossoverParents)
		val triedOperators = new ArrayList<Unit>()
		
		// Randomly pick one unit
		var operator = breedingOperators.get(new Random().nextInt(breedingOperators.size()))

		while(triedOperators.length < breedingOperators.length) {

			if(operator.eClass().getClassifierID() == HenshinPackage.RULE){
				//Run the selected Henshin Rule
				if(runRuleOperator(operator, graph, parents)){
					//println("Could run mutation" + matchToUse.name)
					return graph.roots	
				}
			} else {
				if(runUnitOperator(operator, graph, parents)){
					//println("Could run mutation" + matchToUse.name)
					return graph.roots
				}
			}
			
			triedOperators.add(operator)
			var remainingRules = breedingOperators.filter[ o  | !triedOperators.contains(o)];
			
			if(remainingRules.size == 0) {
				return parents
			}
			
			operator = remainingRules.get(new Random().nextInt(remainingRules.size()))
				
		}
		
        // We didn't find any applicable evolvers...
        System.out.println("Model with no crossover evolvers applicable.....")
		return parents
	}


	def boolean runRuleOperator(Unit operator, EGraph graph, List<EObject> object){
	
		ruleRunner.EGraph = graph
		ruleRunner.unit = operator
		
		//TODO Not sure about this filter. Check what kind of parameter we would expect people
		//to pass in
		var inParameters = operator.parameters.filter[parameter | parameter.kind.equals(ParameterKind.IN)]
		
		if(!inParameters.empty){
			inParameters.forEach[  
				parameter | ruleRunner.setParameterValue(
					parameter.name, 
					evolverParametersFactory.getParameterValue(operator, parameter, object)
				)
			]
		}
		
		//Run the selected Henshin Rule
		return ruleRunner.execute(null)
	}
	
	def boolean runUnitOperator(Unit operator, EGraph graph, List<EObject> object){
	
		unitRunner.EGraph = graph
		unitRunner.unit = operator
		
		var inParameters = operator.parameters.filter[parameter | parameter.kind.equals(ParameterKind.IN)]
		
		if(!inParameters.empty){
			inParameters.forEach[ 
				parameter | unitRunner.setParameterValue(
					parameter.name, 
					evolverParametersFactory.getParameterValue(operator, parameter, object)
				)
			]	
		}
		
		//Run the selected Henshin Unit
		return unitRunner.execute(null)
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
			
			if(operator.eClass().getClassifierID() == HenshinPackage.RULE){
				//Run the selected Henshin Rule
				if(runRuleOperator(operator, graph, Arrays.asList(object))){
					//println("Could run mutation" + matchToUse.name)
					return graph.roots.head	
				}
			} else {
				if(runUnitOperator(operator, graph, Arrays.asList(object))){
					//println("Could run mutation" + matchToUse.name)
					return graph.roots.head	
				}
			}
			
			triedOperators.add(operator);
			var remainingRules = mutationOperators.filter[ o  | !triedOperators.contains(o)];
			
			if(remainingRules.size == 0) {
				return object
			}
			
			operator = remainingRules.get(new Random().nextInt(remainingRules.size()))
			//println("Could not run mutation for rule " + matchToUse.name)
		
		}	
		
        // We didn't find any applicable evolvers...
        println("Model with no mutation evolvers applicable.....")
        object
    }
}