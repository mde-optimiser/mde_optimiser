package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider
import java.util.Map
import org.deeplearning4j.arbiter.optimize.api.data.DataSource
import java.util.Properties
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation

class MoeaScoreFunction implements ScoreFunction {
	
	new(MoeaOptimisation optimisation) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getSupportedDataTypes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getSupportedModelTypes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override minimize() {
		return false
	}
	
	override score(Object arg0, DataProvider arg1, Map<String, Object> arg2) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override score(Object arg0, Class<? extends DataSource> arg1, Properties arg2) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
}