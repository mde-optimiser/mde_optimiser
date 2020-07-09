package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea;

import java.util.Properties;
import org.eclipse.emf.common.util.EList;
import org.moeaframework.core.Problem;
import org.moeaframework.core.TerminationCondition;
import org.moeaframework.core.spi.AlgorithmFactory;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms.MoeaOptimisationAlgorithmProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions.TerminationConditionAdapter;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;

// TODO This should become a template for all the types of parameters supported
public class MoeaFrameworkAlgorithmConfiguration {

  private Properties properties;
  private SolverSpec solverSpec;
  private TerminationCondition terminationCondition;
  private MDEOHyperparametersConfiguration hyperparameters;

  private AlgorithmFactory algorithmFactory;
  private Problem problem;

  private SolutionGenerator solutionGenerator;

  public MoeaFrameworkAlgorithmConfiguration(
      SolverSpec solverSpec, SolutionGenerator solutionGenerator) {
    this.solverSpec = solverSpec;
    this.solutionGenerator = solutionGenerator;
  }

  public Properties getProperties() {
    if (this.properties == null) {
      this.properties = loadProperties();
    }

    return this.properties;
  }

  public SolverSpec getSolverSpec() {
    return this.solverSpec;
  }

  public SolutionGenerator getSolutionGenerator() {
    return this.solutionGenerator;
  }

  public Properties loadProperties() {

    var properties = new Properties();
    if (this.hyperparameters != null) {
      properties.put("populationSize", this.hyperparameters.populationSize);
    } else {
      properties.put(
          "populationSize",
          Integer.parseInt(
              solverSpec.getAlgorithm().getParameters().stream()
                  .filter(p -> p.getName().equals("population"))
                  .findFirst()
                  .get()
                  .getValue()
                  .getNumeric()));
    }

    populateNumericParameter(
        properties, this.solverSpec.getAlgorithm().getParameters(), "archive.size");
    populateNumericParameter(
        properties, this.solverSpec.getAlgorithm().getParameters(), "bisections");

    properties.put("solutionGenerator", solutionGenerator);
    // Crossover and mutation or mutation only
    properties.put(
        "variationType",
        solverSpec.getAlgorithm().getParameters().stream()
            .filter(p -> p.getName().equals("variation"))
            .findFirst()
            .get());

    return properties;
  }

  /**
   * Populate additional parameters in the properties using the configured parameter key
   *
   * @param properties
   * @param parameters
   * @param parameterKey
   */
  private void populateNumericParameter(
      Properties properties, EList<Parameter> parameters, String parameterKey) {
    var parameter = parameters.stream().filter(p -> p.getName().equals(parameterKey)).findFirst();
    parameter.ifPresent(p -> properties.put(parameterKey, p.getValue().getNumeric()));
  }

  /**
   * Build an instance of the MOEAOptimisationProblem class using the configured search specificaion
   *
   * @return an instance of the MOEAOptimisationProblem
   */
  public Problem getProblem() {

    if (this.problem == null) {
      this.problem = new MoeaOptimisationProblem(this.solutionGenerator);
      // TODO
      this.solutionGenerator.setProblem(this.problem);
    }

    return this.problem;
  }

  /**
   * Initialise the algorithm factory and register the MDEO algorithm provider in MOEAFramework.
   *
   * @return algorithmFactory object containing the MDEO algorithm provider
   */
  public AlgorithmFactory getAlgorithmFactory() {

    if (this.algorithmFactory == null) {
      var algorithmFactory = new AlgorithmFactory();
      algorithmFactory.addProvider(new MoeaOptimisationAlgorithmProvider());
      this.algorithmFactory = algorithmFactory;
    }

    return this.algorithmFactory;
  }

  public TerminationCondition getTerminationCondition() {

    if (this.terminationCondition == null) {
      if (this.hyperparameters == null) {
        this.terminationCondition = new TerminationConditionAdapter(solverSpec).getCondition();
      } else {
        this.terminationCondition = new TerminationConditionAdapter(hyperparameters).getCondition();
      }
    }

    return this.terminationCondition;
  }

  public void updateParameters(MDEOHyperparametersConfiguration configuration) {
    this.hyperparameters = configuration;
  }
}
