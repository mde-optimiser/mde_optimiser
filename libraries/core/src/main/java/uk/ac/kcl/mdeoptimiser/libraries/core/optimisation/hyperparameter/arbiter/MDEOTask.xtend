package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import org.deeplearning4j.arbiter.optimize.api.OptimizationResult
import java.util.concurrent.Callable
import org.deeplearning4j.arbiter.optimize.api.Candidate
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import org.deeplearning4j.arbiter.optimize.api.evaluation.ModelEvaluator
import org.deeplearning4j.arbiter.optimize.runner.listener.StatusListener
import java.util.List
import org.deeplearning4j.arbiter.task.TaskListener
import org.deeplearning4j.arbiter.optimize.runner.IOptimizationRunner
import org.deeplearning4j.arbiter.optimize.api.data.DataSource
import java.util.Properties
import org.deeplearning4j.arbiter.optimize.runner.CandidateStatus
import org.deeplearning4j.arbiter.optimize.runner.CandidateInfo
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaFrameworkAlgorithmConfiguration
import com.fasterxml.jackson.annotation.JsonIgnore
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import java.io.IOException
import org.deeplearning4j.arbiter.optimize.api.saving.ResultSaver
import org.deeplearning4j.arbiter.optimize.api.saving.ResultReference

class MDEOTask implements Callable<OptimizationResult> {

	Candidate candidate

	DataProvider dataProvider

	ScoreFunction scoreFunction

	ModelEvaluator modelEvaluator

	List<StatusListener> listeners

	TaskListener taskListener

	@JsonIgnore
	IOptimizationRunner runner

	Class<? extends DataSource> dataSource

	Properties dataSourceProperties

	long startTime

	SolutionGenerator solutionGenerator

	Optimisation optimisationSpec

	new(Optimisation optimisationSpec, SolutionGenerator solutionGenerator, Candidate candidate,
		DataProvider dataProvider, ScoreFunction scoreFunction, ModelEvaluator modelEvaluator,
		List<StatusListener> listeners, TaskListener taskListener, IOptimizationRunner runner) {
		this.optimisationSpec = optimisationSpec;
		this.solutionGenerator = solutionGenerator;
		this.candidate = candidate;
		this.dataProvider = dataProvider;
		this.scoreFunction = scoreFunction;
		this.modelEvaluator = modelEvaluator;
		this.listeners = listeners;
		this.taskListener = taskListener;
		this.runner = runner;
	}

	new(Optimisation optimisationSpec, SolutionGenerator solutionGenerator, Candidate candidate,
		Class<? extends DataSource> dataSource, Properties dataSourceProperties, ScoreFunction scoreFunction,
		ModelEvaluator modelEvaluator, List<StatusListener> listeners, TaskListener taskListener,
		IOptimizationRunner runner) {
		this.optimisationSpec = optimisationSpec;
		this.solutionGenerator = solutionGenerator;
		this.candidate = candidate;
		this.dataSource = dataSource;
		this.dataSourceProperties = dataSourceProperties;
		this.scoreFunction = scoreFunction;
		this.modelEvaluator = modelEvaluator;
		this.listeners = listeners;
		this.taskListener = taskListener;
		this.runner = runner;
	}

	/**
	 * Initialise a MDEOptimiser search instance with the selected parameters.
	 */
	override call() throws Exception {

		startTime = System.currentTimeMillis()

		var candidateInfo = new CandidateInfo(candidate.getIndex(), CandidateStatus.Running, null, startTime, startTime,
			null, candidate.flatParameters, null);

		var currentConfiguration = candidate.getValue() as MDEOHyperparametersConfiguration

		var algorithmConfiguration = new MoeaFrameworkAlgorithmConfiguration(optimisationSpec.solver,
			this.solutionGenerator)

		algorithmConfiguration.updateParameters(currentConfiguration)

		var moeaOptimisation = new MoeaOptimisation();

		var outcome = moeaOptimisation.execute(algorithmConfiguration)

		// TODO: Here iterate through the data set if provided. Potentially, this could mean a set of models
		// for a given problem?
		var Double score = null;

		if (outcome !== null) {

			if (this.dataSource !== null) {
				score = scoreFunction.score(outcome, dataSource, dataSourceProperties);
			} else {
				score = scoreFunction.score(outcome, dataProvider, candidate.getDataParameters());
			}

			candidateInfo.setScore(score);
		}

		var result = new OptimizationResult(candidate, score, candidate.getIndex(), null, candidateInfo, null);

		var ResultSaver saver = runner.getConfiguration().getResultSaver();
		var ResultReference resultReference = null;

		if (saver !== null) {
			try {
				resultReference = saver.saveModel(result, currentConfiguration);
			} catch (IOException e) {
			}
		}
		result.setResultReference(resultReference);

		return result;
	}

}
