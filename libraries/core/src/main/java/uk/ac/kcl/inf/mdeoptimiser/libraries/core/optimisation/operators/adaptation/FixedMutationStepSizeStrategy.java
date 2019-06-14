package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation;

public class FixedMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

  /**
   * Gets the next step size based on previous performance.
   *
   * @return stepSize indicating the number of mutations to be applied in the next algorithm step
   */
  public int getNextStepSize() {
    return Integer.parseInt(this.parameterValue.getNumeric());
  }
}
