package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover;

import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class CrossoverStrategyFactory {

  private CrossoverStrategy crossoverStrategy;
  private SearchOperatorConfiguration searchOperatorConfiguration;

  public CrossoverStrategyFactory(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  public CrossoverStrategy getStrategy() {
    if (this.crossoverStrategy == null) {
      var strategyParameter =
          this.searchOperatorConfiguration
              .getSearchOperatorParameterInterpreter()
              .get("crossover.strategy");

      switch (strategyParameter.getValue()) {
        case "problempart":
          var searchSpec = searchOperatorConfiguration.searchSpecification();
          var problemPartSpecifier = searchSpec.getProblemPartSpecifier();
          var problemParts = problemPartSpecifier.getProblemPart(searchSpec.getMetamodel());
          var engine = searchOperatorConfiguration.getHenshinExecutor().getEngine();
          var crossoverImpl = new crossover.ProblemPartCrossover(problemParts, engine);
          crossoverStrategy = new ProblemPartCrossover(crossoverImpl);

        default:
          throw new UnexpectedAlgorithmParameterException(strategyParameter.getValue());
      }
    }

    return this.crossoverStrategy;
  }
}
