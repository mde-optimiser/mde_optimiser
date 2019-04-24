package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score

import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider
import java.util.Map
import org.deeplearning4j.arbiter.optimize.api.data.DataSource
import java.util.Properties
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOSearchOutcome

abstract class BaseScoreFunction implements ScoreFunction {
	
	override getSupportedDataTypes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getSupportedModelTypes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	/**
	 * Score function to calculate the overall performance of the current run.
	 * 
	 * @param model is an instance of MDEOSearchOutcome
	 * @param dataProvider is a data source provider to further test the obtained solution. Not used, can be null
	 * @param data parameters. Not used, can be null
	 */
	override score(Object model, DataProvider dataProvider, Map<String, Object> dataParameters) {
		var outcomeModel = model as MDEOSearchOutcome
		return calculateScore(outcomeModel);
	}
	
	/**
	 * Score function to calculate the overall performance of the current run.
	 * 
	 * @param model is an instance of MDEOSearchOutcome
	 * @param dataProvider is a data source provider to further test the obtained solution. Not used, can be null
	 * @param data parameters. Not used, can be null
	 */
	override score(Object model, Class<? extends DataSource> dataSource, Properties dataSourceProperties) {
		var outcomeModel = model as MDEOSearchOutcome
		return calculateScore(outcomeModel);
	}
	
	/**
	 * Score function used to calculate the score by the concrete
	 * implementations
	 * 
	 * @return double the score value
	 */
	def abstract double calculateScore(MDEOSearchOutcome model);
}