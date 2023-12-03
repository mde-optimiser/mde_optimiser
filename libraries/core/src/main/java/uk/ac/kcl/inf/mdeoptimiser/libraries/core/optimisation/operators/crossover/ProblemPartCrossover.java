package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover;

import java.util.List;
import java.util.stream.Collectors;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class ProblemPartCrossover implements CrossoverStrategy {

  private de.uni_marburg.mdo_over.crossover.ProblemPartCrossover crossover;

  public ProblemPartCrossover(de.uni_marburg.mdo_over.crossover.ProblemPartCrossover crossover) {
    this.crossover = crossover;
  }

  @Override
  public List<Solution> breed(List<Solution> parents) {
    /* TODO crossover invalidate data collected in solution (transformation chain, mutation steps).
     * Therefore, a deep copy of parent solution seems useless. New solutions are created.
     */
    var offspringModels = crossover.evolve(parents.get(0).getModel(), parents.get(1).getModel());
    return offspringModels.stream().map(Solution::new).collect(Collectors.toList());
  }
}
