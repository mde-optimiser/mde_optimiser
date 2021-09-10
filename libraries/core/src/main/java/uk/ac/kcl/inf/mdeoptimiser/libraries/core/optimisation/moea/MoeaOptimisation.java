package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea;

import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.algorithm.PeriodicAction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IOptimisationExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation.PopulationCollector;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.listeners.RuntimeProgressListener;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem;

public class MoeaOptimisation implements IOptimisationExecutor {

  /*
   * Returns an instrumenter object containing step level information about the current algorithm run.
   *
   * @return instrumenter containing algorithm individual level search metrics.
   */
  public SearchResult execute(
      MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration) {

    var algorithmStepInstrumenter =
        getAlgorithmStepInstrumenter(moeaFrameworkAlgorithmConfiguration.getSolutionGenerator());

    // Pass reference to the instrumenter instance
    moeaFrameworkAlgorithmConfiguration
        .getSolutionGenerator()
        .setAlgorithmStepInstrumenter(algorithmStepInstrumenter);

    try {
      new Executor()
          .usingAlgorithmFactory(moeaFrameworkAlgorithmConfiguration.getAlgorithmFactory())
          .withAlgorithm(
              moeaFrameworkAlgorithmConfiguration
                  .getSolverSpec()
                  .getAlgorithm()
                  .getName()) // Initialize problem with our solution generator
          //
          .withProblem(moeaFrameworkAlgorithmConfiguration.getProblem())
          //               Instantiate the problem class here as we need a reference to compute
          //               indicators outside of the search eg: operator selection, step size etc.
          //              .withProblemClass(
          //                      MoeaOptimisationProblem.class,
          //                      moeaFrameworkAlgorithmConfiguration.getSolutionGenerator())
          .withProperties(moeaFrameworkAlgorithmConfiguration.getProperties())
          .withInstrumenter(
              algorithmStepInstrumenter) // .distributeOnAllCores() //Leave this on for now. Should
          // perhaps be configurable
          .withTerminationCondition(moeaFrameworkAlgorithmConfiguration.getTerminationCondition())
          .withProgressListener(new RuntimeProgressListener())
          .run();

    } catch (Exception e) {

      String exceptionText =
          String.format("Unable to run search. Encountered exception: %s", e.getMessage());
      System.out.println(exceptionText);
      e.printStackTrace();
      throw new RuntimeException(exceptionText);
    }

    return new SearchResult(moeaFrameworkAlgorithmConfiguration, algorithmStepInstrumenter);
  }

  // TODO Perhaps move this from here to the MOEAFA class as well
  /**
   * Initialise an algorithm instrumenter with step frequency to log each algorithm operation.
   *
   * @return instrumenter instance used to collect run information
   */
  public Instrumenter getAlgorithmStepInstrumenter(SolutionGenerator solutionGenerator) {
    return new Instrumenter()
        .withProblemClass(MoeaOptimisationProblem.class, solutionGenerator)
        .attachApproximationSetCollector()
        .attachElapsedTimeCollector()
        .attachPopulationSizeCollector()
        .attach(new PopulationCollector())
        .withFrequency(1)
        .addExcludedPackage("jdk")
        .addExcludedPackage("org.eclipse")
        // TODO Leaving this out throws a concurrent modification error.
        .addExcludedPackage("org.eclipse.emf.common.util")
        .withFrequencyType(PeriodicAction.FrequencyType.STEPS);
  }
}
