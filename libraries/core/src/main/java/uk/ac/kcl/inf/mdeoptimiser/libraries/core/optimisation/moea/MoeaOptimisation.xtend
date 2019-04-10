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
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec

class MoeaOptimisation implements IOptimisationExecutor {

	/*
	 * Returns an instrumenter object containing step level information about the current algorithm run.
	 * 
	 * @return instrumenter containing 
	 */
	override Instrumenter execute(MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration){

		val algorithmFactory = new AlgorithmFactory();
		algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider)

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
		    .run()

		return algorithmStepInstrumenter;

	}

	def Instrumenter getAlgorithmStepInstrumenter(SolutionGenerator solutionGenerator) {
		return new Instrumenter()
			.withProblemClass(MoeaOptimisationProblem, solutionGenerator)
			.attachApproximationSetCollector()
			.attachElapsedTimeCollector()
			.attachPopulationSizeCollector
			.attach(new PopulationCollector())
			.withFrequency(1)
			.addExcludedPackage("jdk")
			.withFrequencyType(PeriodicAction.FrequencyType.STEPS)
	}	
}
