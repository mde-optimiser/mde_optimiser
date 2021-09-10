package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;

public class DefaultParameterSpecs {

  private Map<String, ParameterSpec> defaultParameterSpecs;

  public DefaultParameterSpecs() {
    this.defaultParameterSpecs = new HashMap<String, ParameterSpec>();
    this.defaultParameterSpecs.put(
        "mutation.application", this.defaultMutationApplicationParameter());
    this.defaultParameterSpecs.put("mutation.selection", this.defaultMutationSelectionParameter());
    this.defaultParameterSpecs.put("mutation.credit", this.defaultMutationCreditParameter());
    this.defaultParameterSpecs.put("mutation.step", this.defaultMutationStepParameter());
    this.defaultParameterSpecs.put("mutation.repair", this.defaultMutationRepairParameter());
  }

  /**
   * Load the unspecified parameters using default values from this default repository.
   *
   * @param parameterKey
   * @return a parameter spec that contains predefined default values
   */
  public ParameterSpec getDefaultParameterSpec(String parameterKey) {

    if (this.defaultParameterSpecs.containsKey(parameterKey)) {
      return this.defaultParameterSpecs.get(parameterKey);
    }

    throw new UnexpectedAlgorithmParameterException(parameterKey);
  }

  /**
   * Default mutation.application is random
   *
   * @return a parameter spec containing the default settings
   */
  private ParameterSpec defaultMutationRepairParameter() {
    return new ParameterSpec("mutation.repair", "default", new LinkedList<>());
  }

  /**
   * Default mutation.application is random
   *
   * @return a parameter spec containing the default settings
   */
  private ParameterSpec defaultMutationApplicationParameter() {
    return new ParameterSpec("mutation.application", "random", new LinkedList<>());
  }

  /**
   * Default mutation.selection is random
   *
   * @return a parameter spec containing the default settings
   */
  private ParameterSpec defaultMutationSelectionParameter() {
    return new ParameterSpec("mutation.selection", "random", new LinkedList<>());
  }

  /**
   * Default mutation.credit parameter TODO
   *
   * @return a parameter spec containing the default settings
   */
  private ParameterSpec defaultMutationCreditParameter() {
    return new ParameterSpec("mutation.credit", "random", new LinkedList<>());
  }

  /**
   * Default mutation.step is fixed with a size of 1
   *
   * @return a parameter spec containing the default settings
   */
  private ParameterSpec defaultMutationStepParameter() {
    return new ParameterSpec("mutation.step", "fixed", new LinkedList<>(List.of("1")));
  }
}
