package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step;

import java.util.Random;
import org.apache.commons.math3.util.Pair;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

/** Configured in the DSL using the interfal(min, high) format. */
public class IntervalMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

  Integer lowerBound;
  Integer upperBound;
  Random randomIntervalGenerator;

  public IntervalMutationStepSizeStrategy(SearchOperatorConfiguration searchOperatorConfiguration) {
    super(searchOperatorConfiguration);
    this.randomIntervalGenerator = new Random();
  }

  /**
   * Generate a random step size between the specified interval values.
   *
   * @return int holding the mutation step size value
   */
  public int getNextStepSize(Solution solution) {
    var range = this.getUpperBound() - this.getLowerBound();

    var step = randomIntervalGenerator.nextInt(range) + this.getLowerBound();
    solution.setMutationStep(new Pair<>((double) step, 0d));

    return step;
  }

  /**
   * Extract the lower bound step size value from the specified interval.
   *
   * @return lowerBound limit of the step size interval
   */
  public int getLowerBound() {
    if (this.lowerBound == null) {
      this.lowerBound = Integer.parseInt(this.getStrategyParameter().getValueArguments().get(0));
    }

    return this.lowerBound;
  }

  /**
   * Extract the upper bound step size value from the specified interval.
   *
   * @return upperBound limit of the step size interval
   */
  public int getUpperBound() {

    if (this.upperBound == null) {
      this.upperBound = Integer.parseInt(this.getStrategyParameter().getValueArguments().get(1));
    }

    return this.upperBound;
  }
}
