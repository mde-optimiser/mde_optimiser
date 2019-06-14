package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators;

import java.util.ArrayList;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class MoeaOptimisationCrossoverVariation implements Variation {

  SolutionGenerator solutionGenerator;

  public MoeaOptimisationCrossoverVariation(SolutionGenerator solutionGenerator) {
    this.solutionGenerator = solutionGenerator;
  }

  // USed to evolve a solution using the henshin evolvers
  @Override
  public Solution[] evolve(Solution[] parents) {

    var solutions = new ArrayList<MoeaOptimisationSolution>();
    var parentModels =
        new ArrayList<
            uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution>();

    for (Solution parent : parents) {
      var solution = ((MoeaOptimisationSolution) parent).copy();
      solutions.add(solution);
      parentModels.add(solution.getModel());
    }

    var children = solutionGenerator.breed(parentModels);

    for (var i = 0; i < 2; i++) {
      solutions.get(i).setModel(children.get(i));
    }

    return new Solution[] {solutions.get(0), solutions.get(1)};
  }

  /**
   * The number of solutions to be supplied to the evolve method. For the crossover operator we
   * require a number of two parents.
   */
  public int getArity() {
    return 2;
  }
}
