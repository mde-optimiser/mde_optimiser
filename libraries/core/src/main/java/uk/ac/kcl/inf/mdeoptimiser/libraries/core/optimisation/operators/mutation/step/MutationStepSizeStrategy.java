package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

/** Interface used to implement mutation step size adaptation strategies. */
public interface MutationStepSizeStrategy {

  /**
   * Invoked during each step evaluation, when a new mutation needs to be applied and returns a
   * mutation step size.
   *
   * @return the next mutation step size
   */
  int getNextStepSize(Solution solution);
}
