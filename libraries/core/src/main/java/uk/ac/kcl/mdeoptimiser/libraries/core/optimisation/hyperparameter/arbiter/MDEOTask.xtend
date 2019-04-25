package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import java.io.IOException
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Properties
import java.util.concurrent.Callable
import org.deeplearning4j.arbiter.optimize.api.Candidate
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider
import org.deeplearning4j.arbiter.optimize.api.data.DataSource
import org.deeplearning4j.arbiter.optimize.api.evaluation.ModelEvaluator
import org.deeplearning4j.arbiter.optimize.api.saving.ResultReference
import org.deeplearning4j.arbiter.optimize.api.saving.ResultSaver
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import org.deeplearning4j.arbiter.optimize.runner.CandidateInfo
import org.deeplearning4j.arbiter.optimize.runner.CandidateStatus
import org.deeplearning4j.arbiter.optimize.runner.IOptimizationRunner
import org.deeplearning4j.arbiter.optimize.runner.listener.StatusListener
import org.eclipse.xtext.EcoreUtil2
import org.moeaframework.analysis.collector.Accumulator
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.OptimisationInterpreter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaFrameworkAlgorithmConfiguration
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult

class MDEOTask implements Callable<OptimizationResult> {

	Candidate candidate

	DataProvider dataProvider

	ScoreFunction scoreFunction

	ModelEvaluator modelEvaluator

	List<StatusListener> listeners

	//TaskListener - not sure if needed
	Object taskListener

	IOptimizationRunner runner

	Class<? extends DataSource> dataSource

	Properties dataSourceProperties

	long startTime

	String moptProjectPath

	Optimisation optimisationSpec

	new(Optimisation optimisationSpec, String moptProjectPath, Candidate candidate, DataProvider dataProvider,
		ScoreFunction scoreFunction, ModelEvaluator modelEvaluator, List<StatusListener> listeners,
		Object taskListener, IOptimizationRunner runner) {
		this.optimisationSpec = optimisationSpec;
		this.moptProjectPath = moptProjectPath;
		this.candidate = candidate;
		this.dataProvider = dataProvider;
		this.scoreFunction = scoreFunction;
		this.modelEvaluator = modelEvaluator;
		this.listeners = listeners;
		this.taskListener = taskListener;
		this.runner = runner;
	}

	new(Optimisation optimisationSpec, String moptProjectPath, Candidate candidate,
		Class<? extends DataSource> dataSource, Properties dataSourceProperties, ScoreFunction scoreFunction,
		ModelEvaluator modelEvaluator, List<StatusListener> listeners, Object taskListener,
		IOptimizationRunner runner) {
		this.optimisationSpec = optimisationSpec;
		this.moptProjectPath = moptProjectPath;
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

		var searchResults = new HashMap<Integer, SearchResult>();

		for (var batch = 1; batch < 6; batch++) {
			// This should be one instance per task.
			searchResults.put(batch,runSingleBatch(moptProjectPath, optimisationSpec, currentConfiguration))
		}

		// TODO: Here iterate through the data set if provided. Potentially, this could mean a set of models
		// for a given problem?
		var Double score = null;

		// TODO I think we can to be able to configure this from the DSL and fall back to a default value of 5
		if (searchResults.size === 5) {

			var outcome = new MDEOSearchOutcome(optimisationSpec, searchResults);

			if (this.dataSource !== null) {
				score = scoreFunction.score(outcome, dataSource, dataSourceProperties);
			} else {
				score = scoreFunction.score(outcome, dataProvider, candidate.getDataParameters());
			}

			candidateInfo.setScore(score);
			candidateInfo.setEndTime(System.currentTimeMillis())
		}

		var result = new OptimizationResult(candidate, score, candidate.getIndex(), searchResults, candidateInfo, null);

		var resultSaver = runner.getConfiguration().getResultSaver();
		var ResultReference resultReference = null;

		if (resultSaver !== null) {
			try {
				resultReference = resultSaver.saveModel(result, currentConfiguration);
			} catch (IOException e) {
				println(
					String.format("Parameter search model saver encountered an exception: %s %s", e.message,
						e.stackTrace))
						throw e
			}
		}
		result.setResultReference(resultReference);

		return result;
	}

	/**
	 * Extract accumulators from the MDEO search outputs
	 * 
	 * @return list of accumulators
	 */
	def Map<Integer, Accumulator> getBatchesAccumulators(Map<Integer, SearchResult> results){
		
		val accumulators = new HashMap<Integer, Accumulator>();
		
		results.keySet.forEach[key | 
			accumulators.put(key, results.get(key).accumulator)
		]
		
		return accumulators;
	}

	/**
	 * Run a single MDEOptimiser run with the current configuration.
	 * @return an instrumenter reference containing the run results.
	 */
	 //TODO Return a ModelSearchResult class containing a reference to the instrumenter, algorithm, problem instance and solution generator
	def SearchResult runSingleBatch(String moptProjectPath, Optimisation optimisationSpec, MDEOHyperparametersConfiguration currentConfiguration) {
		var optimisationModel = EcoreUtil2.copy(optimisationSpec);
		var optimisationInterpreter = new OptimisationInterpreter(moptProjectPath, optimisationModel);
		var algorithmConfiguration = new MoeaFrameworkAlgorithmConfiguration(optimisationModel.solver,
			optimisationInterpreter.solutionGenerator)
		algorithmConfiguration.updateParameters(currentConfiguration)
		var moeaOptimisation = new MoeaOptimisation();
		return moeaOptimisation.execute(algorithmConfiguration)
	}
}
