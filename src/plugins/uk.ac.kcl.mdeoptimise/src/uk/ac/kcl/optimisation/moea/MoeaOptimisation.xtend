package uk.ac.kcl.optimisation.moea

import java.util.Properties
import org.moeaframework.Executor
import org.moeaframework.core.spi.AlgorithmFactory
import uk.ac.kcl.interpreter.IOptimisation
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.core.TerminationCondition
import org.moeaframework.Instrumenter
import org.moeaframework.algorithm.PeriodicAction
import uk.ac.kcl.optimisation.moea.instrumentation.PopulationCollector
import uk.ac.kcl.optimisation.moea.algorithms.MoeaOptimisationAlgorithmProvider
import uk.ac.kcl.optimisation.moea.termination.conditions.TerminationConditionAdapter
import uk.ac.kcl.optimisation.moea.problem.MoeaOptimisationProblem

class MoeaOptimisation implements IOptimisation {
	
	SolutionGenerator solutionGenerator
	
	override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {
		
		this.solutionGenerator = solutionGenerator;
		
		var optimisationProperties = getOptimisationProperties(optimisationSpec)
		
		//Extract optimisation models from the problem solutions
		return runOptimisation(optimisationSpec.algorithmName, optimisationProperties);
	}
	
	/**
	 * This can be passed in the algorithm factory.
	 * Properties can be extracted through a decorator based on the algorithm name/type?
	 */
	def Properties getOptimisationProperties(OptimisationSpec optimisationSpec) {
		
		var properties = new Properties()
		
		properties.put("populationSize", Integer.parseInt(optimisationSpec.algorithmParameters.parameters.filter[p| p.name.equals("population")].head.value))
		//properties.put("maxEvolutions", optimisationSpec.algorithmPopulation * optimisationSpec.algorithmEvolutions)
		properties.put("solutionGenerator", solutionGenerator)
		//Crossover and mutation or mutation only
		properties.put("variationType", optimisationSpec.algorithmVariation)
		properties.put("terminationCondition", new TerminationConditionAdapter(optimisationSpec).condition)
		return properties
	}
	
	
	/*
	 * TODO This should actually return an object containing all the information about 
	 * the run, not just the solution
	 */
	def Instrumenter runOptimisation(String algorithmName, Properties optimisationProperties) {
		
		
		val algorithmFactory = new AlgorithmFactory();
		algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider)
		
		var instrumenter = new Instrumenter()
				.withProblemClass(MoeaOptimisationProblem, solutionGenerator)
				.attachApproximationSetCollector()
				.attachElapsedTimeCollector()
				.attachPopulationSizeCollector
				.attach(new PopulationCollector())
				.withFrequency(1)
				.withFrequencyType(PeriodicAction.FrequencyType.STEPS)
		
		new Executor()
		   .usingAlgorithmFactory(algorithmFactory)
	       .withAlgorithm(algorithmName)
	       //Initialize problem with our solution generator
	       .withProblemClass(MoeaOptimisationProblem, solutionGenerator)
	       .withProperties(optimisationProperties)
	       .withInstrumenter(instrumenter)
	       //.distributeOnAllCores() //Leave this on for now. Should perhaps be configurable
	       .withTerminationCondition(optimisationProperties.get("terminationCondition") as TerminationCondition)
	       .run()

		return instrumenter;
	}
}
