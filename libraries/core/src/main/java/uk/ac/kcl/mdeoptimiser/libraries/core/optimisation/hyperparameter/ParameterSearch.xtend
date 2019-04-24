package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter

import java.nio.file.Files
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.Date
import java.util.List
import java.util.Properties
import org.deeplearning4j.arbiter.optimize.api.CandidateGenerator
import org.deeplearning4j.arbiter.optimize.api.saving.ResultSaver
import org.deeplearning4j.arbiter.optimize.api.termination.TerminationCondition
import org.deeplearning4j.arbiter.optimize.config.OptimizationConfiguration
import org.deeplearning4j.arbiter.optimize.runner.LocalOptimizationRunner
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEODataSource
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOTaskCreator
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.generator.CandidateGeneratorFactory
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver.MDEOParameterSearchResult
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver.MDEOResultSaver
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.termination.condition.TerminationConditionFactory
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score.ScoreFunctionFactory

class ParameterSearch {

	String parameterSearchOutputPath;

	def CandidateGenerator getCandidateGenerator(Optimisation optimisationSpec) {
		return CandidateGeneratorFactory.INSTANCE.get(optimisationSpec.solver.parameterSearch)
	}

	def List<TerminationCondition> getTerminationCondition(Optimisation optimisationSpec) {
		return TerminationConditionFactory.INSTANCE.get(optimisationSpec.solver.parameterSearch);
	}

	def ScoreFunction getScoreFunction(Optimisation optimisationSpec) {
		return ScoreFunctionFactory.INSTANCE.get(optimisationSpec);
	}

	def ResultSaver getResultSaver(String moptProjectPath) {
		return new MDEOResultSaver(this.getParameterSearchOutputPath(moptProjectPath));
	}
	
	def String getParameterSearchOutputPath(String moptProjectPath) {

		if (this.parameterSearchOutputPath === null) {
			val experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(new Date());
			val outputPath = Paths.get(moptProjectPath,
				String.format("mdeo-results/parameter-search-%s/", experimentDate));

			var outputDirectory = Files.createDirectories(outputPath)

			this.parameterSearchOutputPath = outputDirectory.toFile.absolutePath
		}

		return this.parameterSearchOutputPath
	}

	def OptimizationConfiguration getOptimisationConfiguration(String moptProjectPath, Optimisation optimisationSpec) {

		var candidateGenerator = getCandidateGenerator(optimisationSpec)
		var resultSaver = getResultSaver(moptProjectPath)
		var scoreFunction = getScoreFunction(optimisationSpec);
		var terminationConditions = getTerminationCondition(optimisationSpec)
		
		// Given these configuration options, let's put them all together:
		return new OptimizationConfiguration.Builder().candidateGenerator(candidateGenerator).scoreFunction(
			scoreFunction).dataSource(MDEODataSource, new Properties()).modelSaver(resultSaver).terminationConditions(
			terminationConditions).build();
	}

	def MDEOParameterSearchResult search(String moptProjectPath, Optimisation optimisationSpec) {

		var configuration = getOptimisationConfiguration(moptProjectPath, optimisationSpec);
		var runner = new LocalOptimizationRunner(configuration, new MDEOTaskCreator(optimisationSpec, moptProjectPath));

		// Start the hyperparameter optimization
		runner.execute();

		return new MDEOParameterSearchResult(runner, this.parameterSearchOutputPath);
	}
}
