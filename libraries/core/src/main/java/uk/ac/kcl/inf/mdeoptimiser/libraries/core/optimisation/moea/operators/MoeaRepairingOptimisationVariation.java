package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators;

import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class MoeaRepairingOptimisationVariation implements Variation {
  
  SolutionGenerator solutionGenerator;
  Variation evolvingVariation;
  

  public MoeaRepairingOptimisationVariation(SolutionGenerator solutionGenerator, Variation delegateVariation) {
    this.solutionGenerator = solutionGenerator;
    this.evolvingVariation = delegateVariation;
  }

  @Override
  public Solution[] evolve(Solution[] parents) {
    Solution[] offspring = evolvingVariation.evolve(parents);
    for (Solution solution : offspring) {
      solutionGenerator.repair(((MoeaOptimisationSolution)solution).getModel());
    }
    return offspring;
  }

  @Override
  public int getArity() {
    return evolvingVariation.getArity();
  }
}
