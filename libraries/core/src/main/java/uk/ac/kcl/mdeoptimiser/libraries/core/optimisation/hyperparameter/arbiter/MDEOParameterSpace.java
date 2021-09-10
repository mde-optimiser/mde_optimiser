package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter;

import java.util.*;
import java.util.stream.Collectors;
import org.deeplearning4j.arbiter.optimize.api.ParameterSpace;

public class MDEOParameterSpace implements ParameterSpace {

  Map<String, ParameterSpace> leaves = new HashMap<String, ParameterSpace>();

  /**
   * Returns a list of all the parameter spaces to be optimised.
   *
   * @return list of configured parameterSpaces
   */
  public List<ParameterSpace> collectLeaves() {

    return this.leaves.entrySet().stream()
        .map(value -> value.getValue())
        .collect(Collectors.toList());
  }

  /** This should return a list of nested parameter spaces. Not something we use in MDEO */
  @Override
  public Map<String, ParameterSpace> getNestedSpaces() {
    return Collections.EMPTY_MAP;
  }

  public MDEOHyperparametersConfiguration getValue(double[] parameters) {

    this.leaves.get("populationSize").setIndices(new int[] {0});
    this.leaves.get("evolutions").setIndices(new int[] {0});

    var population = (Integer) leaves.get("populationSize").getValue(parameters);
    var evolutions = (Integer) leaves.get("evolutions").getValue(parameters);

    // Add setters for algorithm parameters
    return new MDEOHyperparametersConfiguration(population, evolutions);
  }

  @Override
  public int numParameters() {
    return this.leaves.entrySet().size();
  }

  @Override
  public boolean isLeaf() {
    return false;
  }

  /** The parameter order index for the generation function */
  @Override
  public void setIndices(int... indices) {}

  // TODO: Order is important
  public void setPopulationSize(ParameterSpace parameterSpace) {

    this.leaves.put("populationSize", parameterSpace);
  }

  public void setEvolutions(ParameterSpace parameterSpace) {

    this.leaves.put("evolutions", parameterSpace);
  }
}
