package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.repair;

import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class RepairStrategyFactory {
  
  private RepairStrategy repairStrategy;
  private SearchOperatorConfiguration searchOperatorConfiguration;
  
  public RepairStrategyFactory(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  public RepairStrategy getStrategy() {
    if (this.repairStrategy == null) {
      var strategyParameter =
          this.searchOperatorConfiguration
              .getSearchOperatorParameterInterpreter()
              .get("rep.strategy");

      switch (strategyParameter.getValue()) {
        case "sequential":
          repairStrategy = new SequentialRepairStrategy(searchOperatorConfiguration.getHenshinExecutor());
          break;
        case "none":
          repairStrategy = new SkipRepairStrategy();
          break;

        default:
          throw new UnexpectedAlgorithmParameterException(strategyParameter.getValue());
      }
    }

    return this.repairStrategy;
  }
}
