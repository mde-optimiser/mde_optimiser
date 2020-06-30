package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface OperatorSelectionStrategy {

  /** Register a tried operator in the current step. */
  void addTriedOperator(Unit operator);

  /**
   * Check if there are any operators that have not yet been tried in this step.
   *
   * @return boolean
   */
  boolean hasUntriedOperators();

  /** Empty the list of tried operators to start a new step. */
  void flushTriedOperators();

  /**
   * Try to select from the available operators only once and if none can be applied, then return
   * the candidate solution without any operators applied.
   *
   * @return next untried operator to be applied
   */
  Unit getNextOperator(Solution solution);
}
