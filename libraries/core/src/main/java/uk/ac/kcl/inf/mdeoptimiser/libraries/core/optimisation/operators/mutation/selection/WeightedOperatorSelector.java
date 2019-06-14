package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class WeightedOperatorSelector extends AbstractOperatorSelector {

  Map<Integer, List<Solution>> stepsReferenceSets;

  public WeightedOperatorSelector(HenshinExecutor henshinExecutor) {
    super(henshinExecutor);
  }

  // What are the new archive contributions.

  // What is the rejection ratio, promote the mutators that contributed to the archive.

  // Count how many candidates have been created by each operator and calculate a percentage for
  // each mutator,
  // then calculate the negative value and multiply by by delta hv,

  // Probability of selection for operators, add a minimal probability to the operators to be
  // picked.

  // if first step, then pick a random operatpr

  // if have a previous step, extract previous archive

  // get operators contributions to archive, sorted by number of contributions out of total
  // population
  // get percentage contribution per operator
  //

  @Override
  public Unit getNextOperator() {

    if (stepsReferenceSets.isEmpty()) {
      // pick random operator
      return null;
    }

    // pick a contribution based operator
    return null;
  }

  public Map<String, Double[]> getOperatorContributions(List<MoeaOptimisationSolution> solutions) {

    var contributions = new HashMap<String, Double[]>();

    for (var solution : solutions) {
      var lastOperator = solution.getModel().getTransformationsChain().getLast();
      var contribution = solution.getObjectives();
    }

    return contributions;
  }

  public List<MoeaOptimisationSolution> getLastApproximationSet() {

    var lastAccumulator = this.algorithmStepInstrumenter.getLastAccumulator();

    if (lastAccumulator != null) {
      return (ArrayList<MoeaOptimisationSolution>)
          lastAccumulator.get("Approximation Set", lastAccumulator.size("Approximation Set") - 1);
    }

    return Collections.emptyList();
  }
}
