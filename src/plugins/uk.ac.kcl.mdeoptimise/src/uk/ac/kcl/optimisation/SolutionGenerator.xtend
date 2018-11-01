package uk.ac.kcl.optimisation

import java.util.ArrayList
import java.util.Iterator
import java.util.List
import java.util.Random
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
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
import org.eclipse.emf.henshin.model.HenshinPackage
import org.eclipse.emf.henshin.interpreter.EGraph
import java.util.Arrays
import uk.ac.kcl.interpreter.evolvers.parameters.EvolverParametersFactory
import org.eclipse.emf.henshin.model.ParameterKind
import org.eclipse.emf.henshin.interpreter.Match
import uk.ac.kcl.optimisation.henshin.MdeoRuleApplicationImpl
import uk.ac.kcl.optimisation.henshin.MdeoUnitApplicationImpl
import java.util.LinkedList
import uk.ac.kcl.interpreter.guidance.Solution

class SolutionGenerator {

    EPackage theMetamodel = null
	List<Unit> breedingOperators
	List<Unit> mutationOperators
	
	Optimisation optimisationModel
	IEvolverParametersFactory evolverParametersFactory
	boolean enableManualRandomMatching = false;
	
	IModelProvider initialModelProvider

	public Engine engine;
	
	/**
	 * Using only the UnitApplicationImpl class to run both Units
	 * and Rules as this class implements functionality to run a single Rule.
	 */
	public UnitApplicationImpl unitRunner
	public RuleApplicationImpl ruleRunner
	
	new(Optimisation model, List<Unit> breedingOperators, List<Unit> mutationOperators, IModelProvider modelProvider, EPackage metamodel){
		this.optimisationModel = model
		this.breedingOperators = breedingOperators
		this.mutationOperators = mutationOperators
		this.initialModelProvider = modelProvider
		this.theMetamodel = metamodel;
		this.engine = new EngineImpl
		
		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		
		this.unitRunner = new MdeoUnitApplicationImpl(engine)
		this.ruleRunner = new MdeoRuleApplicationImpl(engine)
		this.evolverParametersFactory = new EvolverParametersFactory(model.search.evolvers)
		
		//Disable henshin warnings
		ChangeImpl.PRINT_WARNINGS = false;
	}

    /**
     * This will produce a lazy iteration of possible initial solutions
     */
    def Iterator<Solution> getInitialSolutions() {
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
	def List<Solution> crossover(List<Solution> parents) {
				
		val crossoverParents = new ArrayList<Solution>;
		
		parents.forEach[parent | crossoverParents.add(new Solution(parent))]
		
		//TODO This seems a bit off, but perhaps we get rid of crossover altogether anyway
		val graph = new EGraphImpl(crossoverParents.fold(new LinkedList<EObject>())[ parentsList, parent |
			
			parentsList.add(parent.getModel)
			return parentsList;
		])
		
		
		val triedOperators = new ArrayList<Unit>()
		
		// Randomly pick one unit 
		//TODO Cache random
		var operator = breedingOperators.get(new Random().nextInt(breedingOperators.size()))

		while(triedOperators.length < breedingOperators.length) {

			if(operator.eClass().getClassifierID() == HenshinPackage.RULE){
				//Run the selected Henshin Rule
				if(runRuleOperator(operator, graph, parents)){
					//println("Could run mutation" + matchToUse.name)
					return graph.roots.fold(new LinkedList<Solution>())[ parentsList, root |
			
						parentsList.add(new Solution(root, new LinkedList<String>))
						return parentsList
						
					]
				}
			} else {
				if(runUnitOperator(operator, graph, parents)){
					//println("Could run mutation" + matchToUse.name)
					return graph.roots.fold(new LinkedList<Solution>())[ parentsList, root |
			
						parentsList.add(new Solution(root, new LinkedList<String>()))
						return parentsList
						
					]
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


	def boolean runRuleOperator(Unit operator, EGraph graph, List<Solution> object){
	
		ruleRunner.EGraph = graph
		ruleRunner.unit = operator
		
		if(operator.parameters !== null){
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
		}
		
		//Run the selected Henshin Rule
		return ruleRunner.execute(null)	
		
	}
	
	def boolean runUnitOperator(Unit operator, EGraph graph, List<Solution> object){
	
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
	 * Simple flag to allow us to decide what type of rule matching we want to use.
	 * Henshin vs manually finding all the matches and randomly selecting one.
	 */
	def Solution evolveModel(Solution object){
		
		if(this.enableManualRandomMatching){
			return evolveModelManual(object);
		}
		
		return evolveModelHenshin(object);
	}


    /**
     * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
     * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
     * otherwise returns the result of the first randomly picked evolver that was applicable.
     */
    def Solution evolveModelManual(Solution object) {
    	
		// Extract Henshin evolvers if necessary

	
		ChangeImpl.PRINT_WARNINGS = false;
		
		val candidateSolution = new Solution(object)

		// Get all matches
		val graph = new EGraphImpl(candidateSolution.getModel)
		val matchesView = mutationOperators.map [ evolver |
			engine.findMatches(evolver as Rule, graph, null).map[m | new Pair<Rule, Match>(evolver as Rule, m)]
		].flatten

		val matches = new ArrayList<Pair<Rule, Match>>(matchesView.toList)

		if (!matches.empty) {
			// Randomly pick one match
			val matchToUse = matches.get(new Random().nextInt(matches.size))
			
			//println("Using rule: " + matchToUse.key.name)

			// Apply the match
			val runner = new RuleApplicationImpl(engine)
			runner.EGraph = graph
			runner.unit = matchToUse.key
			runner.partialMatch = matchToUse.value

			if (runner.execute(null)) {
				
				candidateSolution.updateModel(graph.roots.head, matchToUse.key.name)
				return candidateSolution
			}
		} 
		
		return object

    }

    /**
     * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
     * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
     * otherwise returns the result of the first randomly picked evolver that was applicable.
     */
    def Solution evolveModelHenshin(Solution object) {
    			
    	
		val candidateSolution = new Solution(object)

		// Get all matches
		val graph = new EGraphImpl(candidateSolution.getModel)

		// Randomly pick one search operator
		val triedOperators = new ArrayList<Unit>()
		var operator = mutationOperators.get(new Random().nextInt(mutationOperators.size()))
					
		while(triedOperators.length < mutationOperators.length){
			
			if(operator.eClass().getClassifierID() == HenshinPackage.RULE){
				//Run the selected Henshin Rule
				if(runRuleOperator(operator, graph, Arrays.asList(candidateSolution))){
			
					//println("Running operator: " + operator.name)
					//println("Could run mutation" + matchToUse.name)
					candidateSolution.updateModel(graph.roots.head, operator.name)
					return candidateSolution
				}
			} else {
				if(runUnitOperator(operator, graph, Arrays.asList(candidateSolution))){
					//println("Could run mutation" + matchToUse.name)
					candidateSolution.updateModel(graph.roots.head, operator.name)
					return candidateSolution
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
	
	def setEnableManualRandomMatching(boolean randonMatching) {
		this.enableManualRandomMatching = randonMatching;
	}
	
}