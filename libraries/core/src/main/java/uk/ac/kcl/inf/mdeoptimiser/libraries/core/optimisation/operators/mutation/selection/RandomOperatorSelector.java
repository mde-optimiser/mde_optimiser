package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import java.util.Collections;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;

public class RandomOperatorSelector extends AbstractOperatorSelector {

  public RandomOperatorSelector(HenshinExecutor henshinExecutor) {
    super(henshinExecutor);
  }

  @Override
  public Unit getNextOperator() {

    if (this.hasUntriedOperators()) {
      var nextOperator =
          henshinExecutor.getMutationOperators().stream()
              .filter(operator -> !this.triedOperators.contains(operator))
              .collect(
                  Collectors.collectingAndThen(
                      Collectors.toList(),
                      remainingOperators -> {
                        Collections.shuffle(remainingOperators);
                        return remainingOperators.stream();
                      }))
              .limit(1)
              .collect(Collectors.toList())
              .get(0);

      this.addTriedOperator(nextOperator);

      return nextOperator;
    }

    // Used all the available operators. Time to stop the step size loop
    return null;
  }
}
