package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.henshin.model.Unit;
import org.moeaframework.Instrumenter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;

public abstract class AbstractOperatorSelector implements OperatorSelectionStrategy {

  protected HenshinExecutor henshinExecutor;
  protected List<Unit> triedOperators;

  protected AlgorithmSpec algorithmSpec;
  protected Instrumenter algorithmStepInstrumenter;

  public AbstractOperatorSelector(HenshinExecutor henshinExecutor) {
    this.henshinExecutor = henshinExecutor;
    this.triedOperators = new ArrayList<Unit>();
  }

  /**
   * Invoked when the search starts.
   *
   * @param algorithmSpec configured algorithm spec
   * @param algorithmStepInstrumenter algorithm step size instrumenter
   */
  @Override
  public void initialize(AlgorithmSpec algorithmSpec, Instrumenter algorithmStepInstrumenter) {
    this.algorithmSpec = algorithmSpec;
    this.algorithmStepInstrumenter = algorithmStepInstrumenter;
  }

  /** Register a tried operator in the current step. */
  @Override
  public void addTriedOperator(Unit operator) {
    triedOperators.add(operator);
  }

  /**
   * Check if there are any operators that have not yet been tried in this step.
   *
   * @return boolean
   */
  @Override
  public boolean hasUntriedOperators() {
    return triedOperators.size() < this.henshinExecutor.getMutationOperators().size();
  }

  /** Flush the list of tried operators to start a new step. */
  @Override
  public void flushTriedOperators() {
    this.triedOperators.clear();
  }
}
