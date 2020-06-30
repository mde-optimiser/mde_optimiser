package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import com.google.common.collect.Streams;
import java.util.*;
import java.util.stream.Collectors;
import org.apache.commons.math3.util.Pair;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

/**
 * This strategy distributes the probability of an operator being selected based on the number of
 * matches that can be found for that operator in the solution candidate model.
 */
public class WeightedMatchOperatorSelection extends AbstractOperatorSelector {

  public WeightedMatchOperatorSelection(SearchOperatorConfiguration searchOperatorConfiguration) {
    super(searchOperatorConfiguration);
  }

  /**
   * Selects a mutation operator based on the number of matches this operator has in the model. This
   * is the "classic" matching strategy operator selector originally developed in MDEO.
   *
   * @param solution
   * @return mutation operator to use next
   */
  @Override
  public Unit getNextOperator(Solution solution) {

    // Get all matches
    var graph = new EGraphImpl(solution.getModel());

    var henshinExecutor = this.getSearchOperatorConfiguration().getHenshinExecutor();

    var matchesView =
        henshinExecutor.getMutationOperators().stream()
            .map(
                evolver -> {
                  return Streams.stream(
                          henshinExecutor.getEngine().findMatches((Rule) evolver, graph, null))
                      .map(m -> new Pair<Rule, Match>((Rule) evolver, m))
                      .collect(Collectors.toList());
                })
            .collect(Collectors.flatMapping(Collection::stream, Collectors.toList()));

    var matches = new ArrayList<>(matchesView);

    if (!matches.isEmpty()) {
      // Randomly pick one match
      var matchToUse = matches.get(new Random().nextInt(matches.size()));

      return matchToUse.getKey();
    }

    // No mathces found. Perhaps throw exception here? If this happens, your operators are bad or
    // your model is broken.
    return null;
  }
}
