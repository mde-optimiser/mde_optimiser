package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application;

import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair.OperatorRepairStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.OperatorSelectionStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step.MutationStepSizeStrategy;

/** Factory class that loads the user configured mutation strategy. */
public class MutationStrategyFactory {

  private final OperatorRepairStrategy operatorRepairStrategy;
  private SearchOperatorConfiguration searchOperatorConfiguration;
  MutationApplicationStrategy mutationStrategy;
  MutationStepSizeStrategy mutationStepSizeStrategy;
  OperatorSelectionStrategy operatorSelectionStrategy;

  public MutationStrategyFactory(
      SearchOperatorConfiguration searchOperatorConfiguration,
      MutationStepSizeStrategy mutationStepSizeStrategy,
      OperatorSelectionStrategy operatorSelectionStrategy,
      OperatorRepairStrategy operatorRepairStrategy) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
    this.mutationStepSizeStrategy = mutationStepSizeStrategy;
    this.operatorSelectionStrategy = operatorSelectionStrategy;
    this.operatorRepairStrategy = operatorRepairStrategy;
  }

  public MutationApplicationStrategy getStrategy() {

    if (this.mutationStrategy == null) {
      var strategyParameter =
          this.searchOperatorConfiguration
              .getSearchOperatorParameterInterpreter()
              .get("mutation.strategy");

      switch (strategyParameter.getValue()) {
        case "random":
          {
            return new RandomOperatorMutationStrategy(
                this.searchOperatorConfiguration,
                this.mutationStepSizeStrategy,
                this.operatorSelectionStrategy,
                this.operatorRepairStrategy);
          }

        case "repetitive":
          {
            return new RepetitiveOperatorMutationStrategy(
                this.searchOperatorConfiguration,
                this.mutationStepSizeStrategy,
                this.operatorSelectionStrategy,
                this.operatorRepairStrategy);
          }

        default:
          throw new UnexpectedAlgorithmParameterException(strategyParameter.getValue());
      }
    }

    return this.mutationStrategy;
  }
}
