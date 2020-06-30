package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import org.eclipse.emf.henshin.model.Unit;
import org.moeaframework.Instrumenter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;

public interface OperatorSelectionStrategy {

  /**
   * Invoked when the search starts.
   *
   * @param algorithmSpec configured algorithm spec
   * @param algorithmStepInstrumenter algorithm step size instrumenter
   */
  void initialize(AlgorithmSpec algorithmSpec, Instrumenter algorithmStepInstrumenter);

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
  Unit getNextOperator();
}
