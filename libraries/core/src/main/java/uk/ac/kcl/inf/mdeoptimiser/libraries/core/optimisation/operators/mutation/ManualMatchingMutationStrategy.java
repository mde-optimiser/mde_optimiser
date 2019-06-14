package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation;

import com.google.common.collect.Streams;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import org.apache.commons.math3.util.Pair;
import org.eclipse.emf.henshin.interpreter.Match;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl;
import org.eclipse.emf.henshin.model.Rule;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy;

public class ManualMatchingMutationStrategy implements MutationStrategy {

  HenshinExecutor henshinExecutor;

  MutationStepSizeStrategy mutationStepSizeStrategy;

  public ManualMatchingMutationStrategy(
      HenshinExecutor henshinExecutor, MutationStepSizeStrategy mutationStepSizeStrategy) {
    this.henshinExecutor = henshinExecutor;
    this.mutationStepSizeStrategy = mutationStepSizeStrategy;
  }

  // TODO Implement the step size strategy
  /**
   * Produce a new solution from the given one using one of the evolvers defined in the optimisation
   * model. This will try evolvers until one of them can be applied or all evolvers have been tried.
   * If no evolver was applicable, returns <code>null</code>, otherwise returns the result of the
   * first randomly picked evolver that was applicable.
   *
   * @param model solution candidate to be evolved
   */
  public Solution mutate(Solution model) {

    var candidateSolution = new Solution(model);

    // Get all matches
    var graph = new EGraphImpl(candidateSolution.getModel());

    var matchesView =
        henshinExecutor.getMutationOperators().stream()
            .map(
                evolver -> {
                  return Streams.stream(
                          henshinExecutor.getEngine().findMatches((Rule) evolver, graph, null))
                      .map(m -> new Pair<Rule, Match>((Rule) evolver, m))
                      .collect(Collectors.toList());
                })
            .collect(Collectors.flatMapping(o -> o.stream(), Collectors.toList()));

    var matches = new ArrayList<>(matchesView);

    if (!matches.isEmpty()) {
      // Randomly pick one match
      var matchToUse = matches.get(new Random().nextInt(matches.size()));

      // Apply the match
      var runner = new RuleApplicationImpl(henshinExecutor.getEngine());
      runner.setEGraph(graph);
      runner.setUnit(matchToUse.getKey());
      runner.setPartialMatch(matchToUse.getValue());

      if (runner.execute(null)) {

        candidateSolution.updateModel(graph.getRoots().get(0), null);
        return candidateSolution;
      }
    }
    return model;
  }
}
