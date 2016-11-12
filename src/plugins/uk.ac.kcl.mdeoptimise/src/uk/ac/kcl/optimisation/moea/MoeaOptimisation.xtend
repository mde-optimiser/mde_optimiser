package uk.ac.kcl.optimisation.moea

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.moeaframework.algorithm.EpsilonMOEA
import org.moeaframework.analysis.plot.Plot
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.Solution
import org.moeaframework.core.operator.RandomInitialization
import org.moeaframework.core.operator.TournamentSelection
import uk.ac.kcl.interpreter.IOptimisation
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.core.NondominatedSortingPopulation
import org.moeaframework.core.EpsilonBoxDominanceArchive
import java.util.Properties
import org.moeaframework.Executor
import java.util.Iterator
import org.moeaframework.core.spi.AlgorithmFactory

class MoeaOptimisation implements IOptimisation {
	
	SolutionGenerator solutionGenerator
	
	//This will have to 
	override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {
		
		this.solutionGenerator = solutionGenerator;
		
		var optimisationProperties = getOptimisationProperties(optimisationSpec)
		
		//Run the optimisation executor
		val population = runOptimisation(optimisationSpec.algorithmName, optimisationProperties);
		
		//Extract optimisation models from the problem solutions
		return getOptimisationOutcomeObjects(population);
	}
	
	def Iterator<EObject> getOptimisationOutcomeObjects(NondominatedPopulation population){
		return population.iterator.map[ p | (p as MoeaOptimisationSolution).model]
	}
	
	/**
	 * This can be passed in the algorithm factory.
	 * Properties can be extracted through a decorator based on the algorithm name/type?
	 */
	def Properties getOptimisationProperties(OptimisationSpec optimisationSpec) {
		
		var properties = new Properties()
		
		properties.put("populationSize", optimisationSpec.algorithmPopulation)
		//This does not seem to be picked up from here in the executor. Needs manual setting
		properties.put("maxEvaluations", optimisationSpec.algorithmEvolutions)
		properties.put("solutionGenerator", solutionGenerator)
		return properties
	}
	
	def NondominatedPopulation runOptimisation(String algorithmName, Properties optimisationProperties) {
		
		val algorithmFactory = new AlgorithmFactory();
		algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider)
		
		new Executor()
		   .usingAlgorithmFactory(algorithmFactory)
	       .withAlgorithm(algorithmName)
	       //Initialize problem with our solution generator
	       .withProblemClass(MoeaOptimisationProblem, solutionGenerator)
	       .withProperties(optimisationProperties)
	       .withMaxEvaluations(optimisationProperties.get("maxEvaluations") as Integer)
	       //.distributeOnAllCores() //Leave this on for now. Should perhaps be configurable.
	       //Todo look at distribution service available
	       .run()
	}
	
	//TODO remove once the correct implementation is proven to work
	def List<EObject> bootstrapOptimisation(OptimisationSpec optimisationSpec){		
		
		//OperatorFactory.getInstance().addProvider(new MoeaOptimmisationVariationsProvider());
		
		//Define the problem with variables and objectives
		var moeaOptimisationProblem = new MoeaOptimisationProblem(solutionGenerator, 1)
	
		//Create an initial random population of population size
		var initialization = new RandomInitialization(moeaOptimisationProblem, optimisationSpec.algorithmPopulation)
		//println(initialization.initialize.length)
		
		//Define the selection operator with the tournament size and dominance comparator
		//
		var selection = new TournamentSelection()//(1, new ParetoDominanceComparator());
		
		//Define the crossover / mutation operator
		var variation = new MoeaOptimisationVariation(solutionGenerator)	
	
//		var algorithm = new NSGAIII(
//				moeaOptimisationProblem,
//				new NondominatedSortingPopulation(),
//				null, // no archive
//				selection,
//				variation,
//				initialization
//			);

//		var algorithm = new SPEA2(
//				moeaOptimisationProblem,
//				initialization,
//				variation,
//				optimisationSpec.algorithmPopulation,
//				1
//			);
		
		var algorithm = new EpsilonMOEA(
				moeaOptimisationProblem,
				new NondominatedSortingPopulation(),
				new EpsilonBoxDominanceArchive(1000),
				selection, 
				variation,
				initialization
			);
		
		var step = 0;
		
		while (step < optimisationSpec.algorithmEvolutions) {
			algorithm.step()
			System.out.println("Running step " + step++)
		}
		
		var result = algorithm.getResult();
	
	    println(result.length)
	
		//Show the results
		var plot = new Plot()
			.add("NSGAII", result)
			.show();
			
		//Show the graph for a bit
		Thread.sleep(6000)
		System.out.println()
	
		var results = new ArrayList<EObject>()
		
		for(Solution object : result.toList) {			
			var solution = object as MoeaOptimisationSolution;
			results.add(solution.model)
		}
	
		results
	}
}
