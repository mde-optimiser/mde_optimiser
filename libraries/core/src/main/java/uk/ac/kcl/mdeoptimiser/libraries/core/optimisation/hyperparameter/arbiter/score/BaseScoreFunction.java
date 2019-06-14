package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score;

import java.util.Map;
import java.util.Properties;
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider;
import org.deeplearning4j.arbiter.optimize.api.data.DataSource;
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOSearchOutcome;

abstract class BaseScoreFunction implements ScoreFunction {

  /**
   * Score function to calculate the overall performance of the current run.
   *
   * @param model is an instance of MDEOSearchOutcome
   * @param dataProvider is a data source provider to further test the obtained solution. Not used,
   *     can be null
   * @param data parameters. Not used, can be null
   */
  @Override
  public double score(Object model, DataProvider dataProvider, Map<String, Object> dataParameters) {
    var outcomeModel = (MDEOSearchOutcome) model;
    return calculateScore(outcomeModel);
  }

  /**
   * Score function to calculate the overall performance of the current run.
   *
   * @param model is an instance of MDEOSearchOutcome
   * @param dataProvider is a data source provider to further test the obtained solution. Not used,
   *     can be null
   * @param data parameters. Not used, can be null
   */
  @Override
  public double score(
      Object model, Class<? extends DataSource> dataSource, Properties dataSourceProperties) {
    var outcomeModel = (MDEOSearchOutcome) model;
    return calculateScore(outcomeModel);
  }

  /**
   * Score function used to calculate the score by the concrete implementations
   *
   * @return double the score value
   */
  public abstract double calculateScore(MDEOSearchOutcome model);
}
