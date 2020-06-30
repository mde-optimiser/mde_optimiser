package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea;

import org.moeaframework.Instrumenter;
import org.moeaframework.analysis.collector.Accumulator;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem;

public class SearchResult {

  Instrumenter instrumenter;

  MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration;

  MoeaOptimisationProblem moeaProblem;

  public SearchResult(
      MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration,
      Instrumenter instrumenter) {
    this.moeaFrameworkAlgorithmConfiguration = moeaFrameworkAlgorithmConfiguration;
    this.instrumenter = instrumenter;
  }

  /**
   * Get an instance of the current results problem.
   *
   * @return problem instance for these results.
   */
  public MoeaOptimisationProblem getProblem() {
    if (this.moeaProblem == null) {
      this.moeaProblem =
          new MoeaOptimisationProblem(
              this.moeaFrameworkAlgorithmConfiguration.getSolutionGenerator());
    }

    return this.moeaProblem;
  }

  /**
   * Get an instance of the problem accumulator containing all the collected search information.
   *
   * @return accumulator instance for the current algorithm rum
   */
  public Accumulator getAccumulator() {
    return this.instrumenter.getLastAccumulator();
  }

  /**
   * Get an instance of the Optimisation instance used to run the current batch.
   *
   * @return optimisation instance
   */
  public Optimisation getOptimisationSpec() {
    return this.moeaFrameworkAlgorithmConfiguration.getSolutionGenerator().getOptimisationModel();
  }
}
