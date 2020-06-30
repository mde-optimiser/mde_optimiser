package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem;

import org.moeaframework.core.EvolutionaryAlgorithm;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.core.spi.ProblemProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms.MoeaOptimisationAlgorithmProvider;

public class MoeaMdeoProblemProvider extends ProblemProvider {

  SolutionGenerator solutionGenerator;
  EvolutionaryAlgorithm algo;

  // TODO This is buggy
  public MoeaMdeoProblemProvider(
      SolutionGenerator solutionGenerator, MoeaOptimisationAlgorithmProvider algorithmProvider) {
    this.solutionGenerator = solutionGenerator;
    // this.algo = (EvolutionaryAlgorithm) algorithmProvider.getAlgorithm();
  }

  @Override
  public Problem getProblem(String name) {

    if (name.equals("problemName")) {
      return new MoeaOptimisationProblem(solutionGenerator);
    }

    return null;
  }

  @Override
  public NondominatedPopulation getReferenceSet(String name) {
    return this.algo.getResult();
  }
}
