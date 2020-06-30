package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea;

import java.util.Properties;
import org.moeaframework.core.TerminationCondition;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions.TerminationConditionAdapter;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;

// TODO This should become a template for all the types of parameters supported
public class MoeaFrameworkAlgorithmConfiguration {

  int populationSize;

  private Properties properties;

  private SolverSpec solverSpec;

  private SolutionGenerator solutionGenerator;

  private TerminationCondition terminationCondition;

  private MDEOHyperparametersConfiguration hyperparameters;

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
    // properties.put("maxEvolutions", optimisationSpec.algorithmPopulation *
    // optimisationSpec.algorithmEvolutions)
    properties.put("solutionGenerator", solutionGenerator);
    // Crossover and mutation or mutation only
    properties.put(
        "variationType",
        solverSpec.getAlgorithm().getParameters().stream()
            .filter(p -> p.getName().equals("variation"))
            .findFirst()
            .get());
    // properties.put("terminationCondition", new TerminationConditionAdapter(solverSpec).condition)

    return properties;
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
