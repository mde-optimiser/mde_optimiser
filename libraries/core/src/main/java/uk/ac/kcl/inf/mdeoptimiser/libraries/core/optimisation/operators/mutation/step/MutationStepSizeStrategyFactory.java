package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step;

import org.apache.commons.lang3.StringUtils;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class MutationStepSizeStrategyFactory {

  private final SearchOperatorConfiguration searchOperatorConfiguration;
  MutationStepSizeStrategy mutationStepSizeStrategy;

  public MutationStepSizeStrategyFactory(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  /**
   * Load and initialize the configured 'mutation.step' strategy.
   *
   * @return MutationStepSizeStrategy configured in the AlgorithmSpec
   */
  public MutationStepSizeStrategy getStrategy() {

    if (this.mutationStepSizeStrategy == null) {

      var mutationStepParameter =
          this.searchOperatorConfiguration
              .getSearchOperatorParameterInterpreter()
              .get("mutation.step");

      if (StringUtils.isNumeric(mutationStepParameter.getValue())) {
        this.mutationStepSizeStrategy =
            new FixedMutationStepSizeStrategy(this.searchOperatorConfiguration, true);
      } else {
        switch (mutationStepParameter.getValue()) {
          case "fixed":
            this.mutationStepSizeStrategy =
                new FixedMutationStepSizeStrategy(this.searchOperatorConfiguration);
            break;

          case "interval":
            this.mutationStepSizeStrategy =
                new IntervalMutationStepSizeStrategy(this.searchOperatorConfiguration);
            break;

          default:
            throw new UnexpectedAlgorithmParameterException(
                mutationStepParameter.getName(), mutationStepParameter.getValue());
        }
      }
    }

    return this.mutationStepSizeStrategy;
  }
}
