package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step;

import org.apache.commons.math3.util.Pair;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class FixedMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

  Double step;

  public FixedMutationStepSizeStrategy(SearchOperatorConfiguration searchOperatorConfiguration) {
    super(searchOperatorConfiguration);
    this.step = Double.parseDouble(this.getStrategyParameter().getValueArguments().get(0));
  }

  public FixedMutationStepSizeStrategy(
      SearchOperatorConfiguration searchOperatorConfiguration, boolean noKeyword) {
    super(searchOperatorConfiguration);
    if (noKeyword) {
      this.step = Double.parseDouble(this.getStrategyParameter().getValue());
    } else {
      this.step = Double.parseDouble(this.getStrategyParameter().getValueArguments().get(0));
    }
  }

  /**
   * Gets the next step size based on previous performance.
   *
   * @return stepSize indicating the number of mutations to be applied in the next algorithm step
   */
  public int getNextStepSize(Solution solution) {

    solution.setMutationStep(new Pair<>(this.step, 0d));

    return this.step.intValue();
  }
}
