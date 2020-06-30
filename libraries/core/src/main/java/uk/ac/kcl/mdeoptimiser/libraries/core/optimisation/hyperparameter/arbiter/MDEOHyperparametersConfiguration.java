package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter;

import java.io.Serializable;
import org.moeaframework.core.TerminationCondition;
import org.moeaframework.core.termination.MaxFunctionEvaluations;

public class MDEOHyperparametersConfiguration implements Serializable {
  public Integer populationSize;
  public Integer evolutions;

  public MDEOHyperparametersConfiguration(Integer populationSize, Integer evolutions) {
    this.populationSize = populationSize;
    this.evolutions = evolutions;
  }

  public TerminationCondition getEvolutionsTerminationCondition() {
    return new MaxFunctionEvaluations(populationSize * evolutions);
  }
}
