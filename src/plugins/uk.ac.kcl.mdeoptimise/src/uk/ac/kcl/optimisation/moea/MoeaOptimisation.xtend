package uk.ac.kcl.optimisation.moea

import java.util.Iterator
import java.util.Properties
import org.moeaframework.Executor
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.spi.AlgorithmFactory
import uk.ac.kcl.interpreter.IOptimisation
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.mdeoptimise.dashboard.api.sender.Sender
import uk.ac.kcl.json.JsonEncoder

class MoeaOptimisation implements IOptimisation {
	
	SolutionGenerator solutionGenerator
	Sender sender
	
	//This will have to 
	override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {
		this.sender = new Sender()
		this.solutionGenerator = solutionGenerator
		
		var optimisationProperties = getOptimisationProperties(optimisationSpec)
		
		
		//Run the optimisation executor
		val population = runOptimisation(optimisationSpec.algorithmName, optimisationProperties)
		
		var Iterator<MoeaOptimisationSolution> solutions = getOptimisationOutcomeObjects(population)
		var message = JsonEncoder.generateFinalSolutionText(solutionGenerator.getOptimisationModel(), solutions, solutionGenerator.getExperimentId())
		sender.sendMessage(message);
		System.out.println("[MDEO] final solution message sent: " + message);
		
		//Extract optimisation models from the problem solutions
		return solutions;
	}
	
	def Iterator<MoeaOptimisationSolution> getOptimisationOutcomeObjects(NondominatedPopulation population){
		return population.iterator.map[ p | (p as MoeaOptimisationSolution)]
	}
	
	/**
	 * This can be passed in the algorithm factory.
	 * Properties can be extracted through a decorator based on the algorithm name/type?
	 */
	def Properties getOptimisationProperties(OptimisationSpec optimisationSpec) {
		
		var properties = new Properties()
		
		properties.put("populationSize", optimisationSpec.algorithmPopulation)
		properties.put("maxEvolutions", optimisationSpec.algorithmPopulation * optimisationSpec.algorithmEvolutions)
		properties.put("solutionGenerator", solutionGenerator)
		//Crossover and mutation or mutation only
		properties.put("variationType", optimisationSpec.algorithmVariation)
		
		return properties
	}
	
	def NondominatedPopulation runOptimisation(String algorithmName, Properties optimisationProperties) {
		
		//OperatorFactory.getInstance().addProvider(new MoeaOptimisationVariationsProvider());
		// TODO (tamara): One option for getting intermediate solutions is to change this 
		// implementation to not using the executor.
		val algorithmFactory = new AlgorithmFactory();
		algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider)
		
		new Executor()
		   .usingAlgorithmFactory(algorithmFactory)
	       .withAlgorithm(algorithmName)
	       //Initialize problem with our solution generator
	       .withProblemClass(MoeaOptimisationProblem, solutionGenerator)
	       .withProperties(optimisationProperties)
	       .withMaxEvaluations(optimisationProperties.get("maxEvolutions") as Integer)
	       //.distributeOnAllCores() //Leave this on for now. Should perhaps be configurable
	       .run()
	}
}
