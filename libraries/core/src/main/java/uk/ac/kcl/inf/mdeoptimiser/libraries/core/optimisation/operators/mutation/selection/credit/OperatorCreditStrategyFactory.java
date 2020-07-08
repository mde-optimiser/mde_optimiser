package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class OperatorCreditStrategyFactory {

  SearchOperatorConfiguration searchOperatorConfiguration;
  private OperatorCreditStrategy operatorCreditStrategy;

  public OperatorCreditStrategyFactory(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  /**
   * Factory for the credit assignment strategies.
   *
   * @return instance of the configured operator credit strategy.
   */
  public OperatorCreditStrategy getStrategy() {

    var strategyTypeParameter =
        this.searchOperatorConfiguration
            .getSearchOperatorParameterInterpreter()
            .get("mutation.credit");

    // TODO In some cases this is not needed
    if (strategyTypeParameter.getValue().equals("random")) {
      return null;
    }

    return this.operatorCreditStrategy;
  }
}
