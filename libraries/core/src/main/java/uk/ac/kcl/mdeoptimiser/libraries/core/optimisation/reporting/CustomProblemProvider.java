package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.reporting;

import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.core.spi.ProblemProvider;

public class CustomProblemProvider extends ProblemProvider {

  private final Problem problem;
  private NondominatedPopulation referenceSet;

  public CustomProblemProvider(Problem problem, NondominatedPopulation referenceSet) {
    this.problem = problem;
    this.referenceSet = referenceSet;
  }

  @Override
  public Problem getProblem(String s) {
    if (problem.getName().equals(s)) {
      return this.problem;
    }

    return null;
  }

  @Override
  public NondominatedPopulation getReferenceSet(String s) {

    if (this.problem.getName().equals(s)) {
      return this.referenceSet;
    }

    return null;
  }
}
