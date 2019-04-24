package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import org.deeplearning4j.arbiter.optimize.api.TaskCreator
import org.deeplearning4j.arbiter.optimize.api.Candidate
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import java.util.List
import org.deeplearning4j.arbiter.optimize.runner.listener.StatusListener
import org.deeplearning4j.arbiter.optimize.runner.IOptimizationRunner
import org.deeplearning4j.arbiter.optimize.api.data.DataSource
import java.util.Properties
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator

class MDEOTaskCreator implements TaskCreator {

	Optimisation optimisationSpec
	String moptProjectPath 
	
	new(Optimisation optimisationSpec, String moptProjectPath) {
		this.optimisationSpec = optimisationSpec;
		this.moptProjectPath = moptProjectPath;
	}

	override create(Candidate candidate, DataProvider dataProvider, ScoreFunction scoreFunction,
		List<StatusListener> statusListeners, IOptimizationRunner optimisationRunner) {
		return new MDEOTask(this.optimisationSpec, this.moptProjectPath, candidate, dataProvider, scoreFunction, null, statusListeners, null, optimisationRunner)
	}

	override create(Candidate candidate, Class<? extends DataSource> dataSource, Properties properties, ScoreFunction scoreFunction,
		List<StatusListener> statusListeners, IOptimizationRunner optimisationRunner) {
		return new MDEOTask(this.optimisationSpec, this.moptProjectPath, candidate, dataSource, properties, scoreFunction, null, statusListeners, null, optimisationRunner)
	}

}
