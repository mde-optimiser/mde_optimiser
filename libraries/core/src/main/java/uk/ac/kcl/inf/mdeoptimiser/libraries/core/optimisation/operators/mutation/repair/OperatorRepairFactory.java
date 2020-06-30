package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair;

import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class OperatorRepairFactory {

  private final SearchOperatorConfiguration searchOperatorConfiguration;
  private OperatorRepairStrategy operatorRepairStrategy;

  public OperatorRepairFactory(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  public OperatorRepairStrategy getStrategy() {

    if (this.operatorRepairStrategy == null) {
      var strategyTypeParameter =
          this.searchOperatorConfiguration
              .getSearchOperatorParameterInterpreter()
              .get("mutation.repair");

      switch (strategyTypeParameter.getValue()) {
          // By default there is no repair strategy defined. We just fall back to the default to
          // skip the operator
          // that cannot be applied
        case "default":
          this.operatorRepairStrategy = new SkipOperatorRepair();
          break;

        default:
          throw new UnexpectedAlgorithmParameterException(
              strategyTypeParameter.getName(), strategyTypeParameter.getValue());
      }
    }

    return this.operatorRepairStrategy;
  }
}
