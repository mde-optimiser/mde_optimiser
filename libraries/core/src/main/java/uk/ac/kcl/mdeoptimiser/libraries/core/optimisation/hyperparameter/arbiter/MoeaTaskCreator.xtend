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

class MoeaTaskCreator implements TaskCreator {
	
	override create(Candidate arg0, DataProvider arg1, ScoreFunction arg2, List<StatusListener> arg3, IOptimizationRunner arg4) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override create(Candidate arg0, Class<? extends DataSource> arg1, Properties arg2, ScoreFunction arg3, List<StatusListener> arg4, IOptimizationRunner arg5) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}