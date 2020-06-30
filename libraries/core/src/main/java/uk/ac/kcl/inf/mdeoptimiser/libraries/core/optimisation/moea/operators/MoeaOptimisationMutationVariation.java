package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators;

import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class MoeaOptimisationMutationVariation implements Variation {

  SolutionGenerator solutionGenerator;

  public MoeaOptimisationMutationVariation(SolutionGenerator solutionGenerator) {
    this.solutionGenerator = solutionGenerator;
  }

  // USed to evolve a solution using the henshin evolvers
  @Override
  public Solution[] evolve(Solution[] parents) {

    var parentSolution = (MoeaOptimisationSolution) parents[0];
    var newSolution = parentSolution.copy();

    newSolution.setModel(solutionGenerator.mutate(newSolution.getModel()));
    // System.out.println("Evolved solution " + newSolution.evolutionsCounter)
    return new Solution[] {newSolution};
  }

  public int getArity() {
    return 1;
  }
}
