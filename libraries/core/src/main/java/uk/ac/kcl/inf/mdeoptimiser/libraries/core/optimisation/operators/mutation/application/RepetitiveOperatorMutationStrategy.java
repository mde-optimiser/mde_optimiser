package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application;

import java.util.LinkedList;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair.OperatorRepairStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.OperatorSelectionStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step.MutationStepSizeStrategy;

public class RepetitiveOperatorMutationStrategy extends AbstractMutationStrategy {

  public RepetitiveOperatorMutationStrategy(
      SearchOperatorConfiguration searchOperatorConfiguration,
      MutationStepSizeStrategy mutationStepSizeStrategy,
      OperatorSelectionStrategy operatorSelectionStrategy,
      OperatorRepairStrategy operatorRepairStrategy) {
    super(
        searchOperatorConfiguration,
        mutationStepSizeStrategy,
        operatorSelectionStrategy,
        operatorRepairStrategy);
  }

  /**
   * Apply transformations according to the configured step size.
   *
   * @return a map of the ordered transformations applied in this step
   */
  public LinkedList<Unit> applyOperators(Solution candidateSolution, EGraphImpl egraph) {

    // Run the mutation for the selected number of steps
    var stepSize = this.mutationStepSizeStrategy.getNextStepSize(candidateSolution);
    Unit operator = null;

    var stepTransformations = new LinkedList<Unit>();

    for (var step = 1; step <= stepSize; step++) {

      do {
        if (operator == null) {
          operator = this.operatorSelectionStrategy.getNextOperator(candidateSolution);
        }

        if (this.getSearchOperatorConfiguration()
                .getHenshinExecutor()
                .operatorApplied(operator, egraph, candidateSolution)
            // TODO: Apply the repair. Perhaps would need some kind of rollback here?
            || this.operatorRepairStrategy.applyRepair(
                operator, egraph, candidateSolution, stepTransformations)) {
          stepTransformations.add(operator);
        } else {
          operator = null;
        }

      } while (operator == null && this.operatorSelectionStrategy.hasUntriedOperators());

      this.operatorSelectionStrategy.flushTriedOperators();
    }

    return stepTransformations;
  }
}
