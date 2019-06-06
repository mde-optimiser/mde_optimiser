package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea

import org.moeaframework.Executor
import org.moeaframework.Instrumenter
import org.moeaframework.algorithm.PeriodicAction
import org.moeaframework.core.spi.AlgorithmFactory
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IOptimisationExecutor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms.MoeaOptimisationAlgorithmProvider
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation.PopulationCollector
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategyFactory

class MoeaOptimisation implements IOptimisationExecutor {

	/*
	 * Returns an instrumenter object containing step level information about the current algorithm run.
	 * 
	 * @return instrumenter containing 
	 */
	override SearchResult execute(MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration){

		val algorithmFactory = getAlgorithmFactory()
		var algorithmStepInstrumenter = getAlgorithmStepInstrumenter(moeaFrameworkAlgorithmConfiguration.solutionGenerator)

		var stepSizeStrategy = new MutationStepSizeStrategyFactory(
			moeaFrameworkAlgorithmConfiguration.solverSpec.algorithm, algorithmStepInstrumenter).strategy

		moeaFrameworkAlgorithmConfiguration.solutionGenerator.setMutationStepSizeStrategy(stepSizeStrategy)

		new Executor()
			.usingAlgorithmFactory(algorithmFactory)
			.withAlgorithm(moeaFrameworkAlgorithmConfiguration.solverSpec.algorithm.name)// Initialize problem with our solution generator
			.withProblemClass(MoeaOptimisationProblem, moeaFrameworkAlgorithmConfiguration.solutionGenerator)
			.withProperties(moeaFrameworkAlgorithmConfiguration.properties)
			.withInstrumenter(algorithmStepInstrumenter)// .distributeOnAllCores() //Leave this on for now. Should perhaps be configurable
		    .withTerminationCondition(moeaFrameworkAlgorithmConfiguration.getTerminationCondition)
		    .run();
		
		return new SearchResult(moeaFrameworkAlgorithmConfiguration, algorithmStepInstrumenter)
	}
	
	/**
	 * Initialise the algorithm factory and register the MDEO algorithm provider in MOEAFramework.
	 * 
	 * @return algorithmFactory object containing the MDEO algorithm provider
	 */
	def AlgorithmFactory getAlgorithmFactory() {
		
		val algorithmFactory = new AlgorithmFactory();
		algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider)
		return algorithmFactory;


	}

	/**
	 * Initialise an algorithm instrumenter with step frequency to log each algorithm operation.
	 * 
	 * @return instrumenter instance used to collect run information
	 */
	def Instrumenter getAlgorithmStepInstrumenter(SolutionGenerator solutionGenerator) {
		return new Instrumenter()
			.withProblemClass(MoeaOptimisationProblem, solutionGenerator)
			.attachApproximationSetCollector()
			.attachElapsedTimeCollector()
			.attachPopulationSizeCollector
			.attach(new PopulationCollector())
			.withFrequency(1)
			.addExcludedPackage("jdk")
			.addExcludedPackage("org.eclipse")
			//TODO Leaving this out throws a concurrent modification error.
			.addExcludedPackage("org.eclipse.emf.common.util")
			.withFrequencyType(PeriodicAction.FrequencyType.STEPS)
	}	
}
