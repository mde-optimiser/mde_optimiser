package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea

import java.util.Properties
import org.moeaframework.Executor
import org.moeaframework.core.spi.AlgorithmFactory
import org.moeaframework.core.TerminationCondition
import org.moeaframework.Instrumenter
import org.moeaframework.algorithm.PeriodicAction

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions.TerminationConditionAdapter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms.MoeaOptimisationAlgorithmProvider
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation.PopulationCollector
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IOptimisationExecutor
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategyFactory

class MoeaOptimisation implements IOptimisationExecutor {
	
	SolutionGenerator solutionGenerator
	Instrumenter algorithmStepSizeInstrumenter;
	
	override execute(SolverSpec solverSpec, SolutionGenerator solutionGenerator) {
		
		this.solutionGenerator = solutionGenerator;
		
		var optimisationProperties = getOptimisationProperties(solverSpec)
		
		//Extract optimisation models from the problem solutions
		return runOptimisation(solverSpec, optimisationProperties);
	}
	
	/**
	 * This can be passed in the algorithm factory.
	 * Properties can be extracted through a decorator based on the algorithm name/type?
	 */
	def Properties getOptimisationProperties(SolverSpec solverSpec) {
		
		var properties = new Properties()
		
		properties.put("populationSize", Integer.parseInt(solverSpec.algorithm.parameters.filter[p| p.name.equals("population")].head.value.numeric))
		//properties.put("maxEvolutions", optimisationSpec.algorithmPopulation * optimisationSpec.algorithmEvolutions)
		properties.put("solutionGenerator", solutionGenerator)
		//Crossover and mutation or mutation only
		properties.put("variationType", solverSpec.algorithm.parameters.filter[p| p.name.equals("variation")].head)
		properties.put("terminationCondition", new TerminationConditionAdapter(solverSpec).condition)
		
		return properties
	}
	
	/*
	 * TODO This should actually return an object containing all the information about 
	 * the run, not just the solution
	 */
	def Instrumenter runOptimisation(SolverSpec solverSpec, Properties optimisationProperties) {
		
		
		val algorithmFactory = new AlgorithmFactory();
		algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider)
		
		this.algorithmStepSizeInstrumenter = new Instrumenter()
				.withProblemClass(MoeaOptimisationProblem, solutionGenerator)
				.attachApproximationSetCollector()
				.attachElapsedTimeCollector()
				.attachPopulationSizeCollector
				.attach(new PopulationCollector())
				.withFrequency(1)
				.withFrequencyType(PeriodicAction.FrequencyType.STEPS)
		
		var stepSizeStrategy =  new MutationStepSizeStrategyFactory(solverSpec.algorithm, algorithmStepSizeInstrumenter).strategy
		
		this.solutionGenerator.setMutationStepSizeStrategy(stepSizeStrategy)
		
		new Executor()
		   .usingAlgorithmFactory(algorithmFactory)
	       .withAlgorithm(solverSpec.algorithm.name)
	       //Initialize problem with our solution generator
	       .withProblemClass(MoeaOptimisationProblem, solutionGenerator)
	       .withProperties(optimisationProperties)
	       .withInstrumenter(algorithmStepSizeInstrumenter)
	       //.distributeOnAllCores() //Leave this on for now. Should perhaps be configurable
	       .withTerminationCondition(optimisationProperties.get("terminationCondition") as TerminationCondition)
	       .run()

		return this.algorithmStepSizeInstrumenter;
	}
}
