package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover;

import java.util.List;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface CrossoverStrategy {

  List<Solution> breed(List<Solution> parents);
}
