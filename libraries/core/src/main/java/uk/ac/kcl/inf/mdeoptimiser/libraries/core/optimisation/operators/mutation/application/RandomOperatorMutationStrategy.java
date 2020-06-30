package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application;

import java.util.LinkedList;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair.OperatorRepairStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.OperatorSelectionStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step.MutationStepSizeStrategy;

public class RandomOperatorMutationStrategy extends AbstractMutationStrategy {

  public RandomOperatorMutationStrategy(
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

    var stepSize = this.mutationStepSizeStrategy.getNextStepSize(candidateSolution);

    var stepTransformations = new LinkedList<Unit>();

    // Run the mutation for the selected number of steps
    for (var step = 1; step <= stepSize; step++) {

      Unit operator = null;
      var operatorApplied = false;

      do {
        operator = this.operatorSelectionStrategy.getNextOperator(candidateSolution);

        if (operator != null) {
          if (this.getSearchOperatorConfiguration()
                  .getHenshinExecutor()
                  .operatorApplied(operator, egraph, candidateSolution)
              // TODO: Apply the repair. Perhaps would need some kind of rollback here?
              || this.operatorRepairStrategy.applyRepair(
                  operator, egraph, candidateSolution, stepTransformations)) {
            stepTransformations.add(operator);
            operatorApplied = true;
            operator = null;
          }
        }

      } while (!operatorApplied && operatorSelectionStrategy.hasUntriedOperators());

      operatorSelectionStrategy.flushTriedOperators();
    }

    return stepTransformations;
  }
}
