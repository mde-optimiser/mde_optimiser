package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import java.util.Collections;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public class RandomOperatorSelection extends AbstractOperatorSelector {

  public RandomOperatorSelection(SearchOperatorConfiguration searchOperatorConfiguration) {
    super(searchOperatorConfiguration, null);
  }

  @Override
  public Unit getNextOperator(Solution solution) {

    if (this.hasUntriedOperators()) {
      var nextOperator =
          this.getSearchOperatorConfiguration().getHenshinExecutor().getMutationOperators().stream()
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
