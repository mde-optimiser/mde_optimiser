package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters;

import java.util.LinkedList;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;

public abstract class AbstractStrategyParameter {

  private SearchOperatorConfiguration searchOperatorConfiguration;
  private Integer populationSize;

  public AbstractStrategyParameter(SearchOperatorConfiguration searchOperatorConfiguration) {
    this.searchOperatorConfiguration = searchOperatorConfiguration;
  }

  protected SearchOperatorConfiguration getSearchOperatorConfiguration() {
    return this.searchOperatorConfiguration;
  }

  /**
   * Get the list of configured parameter attributes. By default there are none. These are the
   * values passed to the parametric functions used by some strategies.
   *
   * @return list of parameter attribute keys
   */
  protected LinkedList<String> getParameterValueKeys() {
    return new LinkedList<>();
  };

  /**
   * Return the configured parameter key for this strategy.
   *
   * @return
   */
  protected abstract String getStrategyParameterKey();

  /**
   * Parse the strategy parameter and any attribute values.
   *
   * @return pair containing the parameter value and any passed attributes.
   */
  protected ParameterSpec getStrategyParameter() {
    return this.searchOperatorConfiguration
        .getSearchOperatorParameterInterpreter()
        .get(this.getStrategyParameterKey());
  }

  /**
   * Load the configured population size from the DSL.
   *
   * @return population size
   */
  protected int getPopulationSize() {

    if (this.populationSize == null) {
      var populationSize =
          this.getSearchOperatorConfiguration().searchSpecification().getOptimisationModel()
              .getSolver().getAlgorithm().getParameters().stream()
              .filter(parameter -> parameter.getName().equals("population"))
              .findFirst()
              .get();
      this.populationSize = Integer.parseInt(populationSize.getValue().getNumeric());
    }

    return this.populationSize;
  }
}
