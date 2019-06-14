package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelector;

public class RandomOperatorMutationStrategy implements MutationStrategy {

  HenshinExecutor henshinExecutor;

  MutationStepSizeStrategy mutationStepSizeStrategy;
  RandomOperatorSelector operatorSelectionDecorator;

  public RandomOperatorMutationStrategy(
      HenshinExecutor henshinExecutor,
      MutationStepSizeStrategy mutationStepSizeStrategy,
      RandomOperatorSelector randomOperatorSelector) {
    this.henshinExecutor = henshinExecutor;
    this.mutationStepSizeStrategy = mutationStepSizeStrategy;
    this.operatorSelectionDecorator = randomOperatorSelector;
  }

  public Solution mutate(Solution model) {
    var candidateSolution = new Solution(model);

    var graph = new EGraphImpl(candidateSolution.getModel());

    var stepTransformations = applyOperators(candidateSolution, graph);

    candidateSolution.updateModel(graph.getRoots().get(0), stepTransformations);
    return candidateSolution;
  }

  /**
   * Apply transformations according to the configured step size.
   *
   * @return a map of the ordered transformations applied in this step
   */
  public Map<Integer, String> applyOperators(Solution candidateSolution, EGraphImpl egraph) {

    var stepSize = this.mutationStepSizeStrategy.getNextStepSize();

    var stepTransformations = new HashMap<Integer, String>();

    // Run the mutation for the selected number of steps
    for (var step = 1; step <= stepSize; step++) {

      Unit operator = null;
      var operatorApplied = false;

      do {
        operator = this.operatorSelectionDecorator.getNextOperator();

        if (operator != null) {
          if (henshinExecutor.operatorApplied(operator, egraph, candidateSolution)) {
            stepTransformations.put(step, operator.getName());
            operatorApplied = true;
            operator = null;
          }
        }

      } while (!operatorApplied && operatorSelectionDecorator.hasUntriedOperators());

      operatorSelectionDecorator.flushTriedOperators();
    }

    return stepTransformations;
  }
}
