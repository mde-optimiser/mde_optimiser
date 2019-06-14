package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score;

import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;

public class ScoreFunctionFactory {

  static ScoreFunctionFactory factoryInstance;

  private ScoreFunctionFactory() {}

  public static synchronized ScoreFunctionFactory getINSTANCE() {

    if (factoryInstance == null) {
      factoryInstance = new ScoreFunctionFactory();
    }

    return factoryInstance;
  }

  public ScoreFunction get(Optimisation optimisationSpec) {

    var problemType = getProblemType(optimisationSpec);

    switch (problemType) {
      case "mo":
        return new HypervolumeScoreFunction();

      case "so":
        return new SingleObjectiveScoreFunction();

      default:
        throw new RuntimeException(
            String.format("Illegal ScoreFunction factory argument. Given: %s", problemType));
    }
  }

  /**
   * Get the problem type based on the number of objectives.
   *
   * @return problem type indicating single-objective or multi-objective
   */
  public String getProblemType(Optimisation optimisation) {

    if (optimisation.getGoal().getObjectives().size() > 1) {
      return "mo";
    }
    return "so";
  }
}
