package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit.OperatorCreditStrategy;

public class OperatorSelectionStrategyFactory {

  private final OperatorCreditStrategy operatorCreditStrategy;
  private final SearchOperatorConfiguration searchOperatorConfiguration;
  private OperatorSelectionStrategy operatorSelectionStrategy;

  public OperatorSelectionStrategyFactory(
      SearchOperatorConfiguration searchOperatorConfiguration,
      OperatorCreditStrategy operatorCreditStrategy) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
    this.operatorCreditStrategy = operatorCreditStrategy;
  }

  public OperatorSelectionStrategy getStrategy() {

    var strategyTypeParameter =
        this.searchOperatorConfiguration
            .getSearchOperatorParameterInterpreter()
            .get("mutation.selection");

    if (this.operatorSelectionStrategy == null) {
      switch (strategyTypeParameter.getValue()) {

          // Random operator selection
        case "random":
          {
            this.operatorSelectionStrategy =
                new RandomOperatorSelection(this.searchOperatorConfiguration);
            break;
          }

        default:
          throw new UnexpectedAlgorithmParameterException(
              strategyTypeParameter.getName(), strategyTypeParameter.getValue());
      }
    }

    return this.operatorSelectionStrategy;
  }
}
