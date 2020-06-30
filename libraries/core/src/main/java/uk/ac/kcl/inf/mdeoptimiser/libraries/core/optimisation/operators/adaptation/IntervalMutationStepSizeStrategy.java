package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation;

import java.util.Random;
import org.eclipse.xtext.xbase.XNumberLiteral;

public class IntervalMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

  Integer lowerBound;
  Integer upperBound;
  Random randomIntervalGenerator;

  public IntervalMutationStepSizeStrategy() {
    this.randomIntervalGenerator = new Random();
  }

  /**
   * Generate a random step size between the specified interval values.
   *
   * @return int holding the mutation step size value
   */
  public int getNextStepSize() {
    var range = this.getUpperBound() - this.getLowerBound();
    return randomIntervalGenerator.nextInt(range) + this.getLowerBound();
  }

  /**
   * Extract the lower bound step size value from the specified interval.
   *
   * @return lowerBound limit of the step size interval
   */
  public int getLowerBound() {

    if (this.lowerBound == null) {
      var numberLiteral = (XNumberLiteral) this.parameterValue.getParams().get(0);
      this.lowerBound = Integer.parseInt(numberLiteral.getValue());
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
      var numberLiteral = (XNumberLiteral) this.parameterValue.getParams().get(1);
      this.upperBound = Integer.parseInt(numberLiteral.getValue());
    }

    return this.getUpperBound();
  }
}
