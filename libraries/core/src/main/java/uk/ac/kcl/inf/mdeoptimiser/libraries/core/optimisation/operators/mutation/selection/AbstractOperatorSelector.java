package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit.OperatorCreditStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters.AbstractStrategyParameter;

public abstract class AbstractOperatorSelector extends AbstractStrategyParameter
    implements OperatorSelectionStrategy {

  protected List<Unit> triedOperators;
  protected OperatorCreditStrategy operatorCreditStrategy;

  public AbstractOperatorSelector(SearchOperatorConfiguration searchOperatorConfiguration) {
    this(searchOperatorConfiguration, null);
  }

  public AbstractOperatorSelector(
      SearchOperatorConfiguration searchOperatorConfiguration,
      OperatorCreditStrategy operatorCreditStrategy) {
    super(searchOperatorConfiguration);
    this.operatorCreditStrategy = operatorCreditStrategy;
    this.triedOperators = new ArrayList<Unit>();
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
    return triedOperators.size() < this.getOperators().size();
  }

  /** Flush the list of tried operators to start a new step. */
  @Override
  public void flushTriedOperators() {
    this.triedOperators.clear();
  }

  /**
   * Get the list of configured operators.
   *
   * @return a list of individual operators as configured in the DSL
   */
  protected List<Unit> getOperators() {
    return this.getSearchOperatorConfiguration().getHenshinExecutor().getMutationOperators();
  }

  /**
   * Return a list of operators for which the application failed.
   *
   * @return list of tried operators
   */
  protected List<Unit> getTriedOperators() {
    return this.triedOperators;
  }

  /**
   * Specify the DSL key for this mutation strategy.
   *
   * @return
   */
  protected String getStrategyParameterKey() {
    return "mutation.selection";
  }
}
