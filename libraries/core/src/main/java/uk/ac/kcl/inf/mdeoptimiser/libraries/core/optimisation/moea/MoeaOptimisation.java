package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea;

import org.moeaframework.Executor;
import org.moeaframework.Instrumenter;
import org.moeaframework.algorithm.PeriodicAction;
import org.moeaframework.core.spi.AlgorithmFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IOptimisationExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms.MoeaOptimisationAlgorithmProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation.PopulationCollector;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategyFactory;

public class MoeaOptimisation implements IOptimisationExecutor {

  /*
   * Returns an instrumenter object containing step level information about the current algorithm run.
   *
   * @return instrumenter containing
   */
  public SearchResult execute(
      MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration) {

    var algorithmFactory = getAlgorithmFactory();
    var algorithmStepInstrumenter =
        getAlgorithmStepInstrumenter(moeaFrameworkAlgorithmConfiguration.getSolutionGenerator());

    var stepSizeStrategy =
        new MutationStepSizeStrategyFactory(
                moeaFrameworkAlgorithmConfiguration.getSolverSpec().getAlgorithm(),
                algorithmStepInstrumenter)
            .getStrategy();

    moeaFrameworkAlgorithmConfiguration
        .getSolutionGenerator()
        .setMutationStepSizeStrategy(stepSizeStrategy);

    new Executor()
        .usingAlgorithmFactory(algorithmFactory)
        .withAlgorithm(
            moeaFrameworkAlgorithmConfiguration
                .getSolverSpec()
                .getAlgorithm()
                .getName()) // Initialize problem with our solution generator
        .withProblemClass(
            MoeaOptimisationProblem.class,
            moeaFrameworkAlgorithmConfiguration.getSolutionGenerator())
        .withProperties(moeaFrameworkAlgorithmConfiguration.getProperties())
        .withInstrumenter(
            algorithmStepInstrumenter) // .distributeOnAllCores() //Leave this on for now. Should
                                       // perhaps be configurable
        .withTerminationCondition(moeaFrameworkAlgorithmConfiguration.getTerminationCondition())
        .run();

    return new SearchResult(moeaFrameworkAlgorithmConfiguration, algorithmStepInstrumenter);
  }

  /**
   * Initialise the algorithm factory and register the MDEO algorithm provider in MOEAFramework.
   *
   * @return algorithmFactory object containing the MDEO algorithm provider
   */
  public AlgorithmFactory getAlgorithmFactory() {

    var algorithmFactory = new AlgorithmFactory();
    algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider());
    return algorithmFactory;
  }

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
