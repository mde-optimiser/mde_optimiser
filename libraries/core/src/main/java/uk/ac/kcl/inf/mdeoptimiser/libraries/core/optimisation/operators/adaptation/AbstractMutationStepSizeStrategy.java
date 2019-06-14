package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation;

import org.moeaframework.Instrumenter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterValue;

public abstract class AbstractMutationStepSizeStrategy implements MutationStepSizeStrategy {

  protected Instrumenter instrumenter;
  protected AlgorithmSpec algorithmSpec;
  protected ParameterValue parameterValue;

  /**
   * Initializes the current strategy with a reference to the algorithm step size instrumenter.
   *
   * @param algorithmSpec containing the current MOPT solver settings
   * @param algorithmStepInstrumenter algorithm step size instrumenter
   * @param parameter 'mutation.step' parameter and values
   */
  public void initialize(
      AlgorithmSpec algorithmSpec, Instrumenter algorithmStepInstrumenter, Parameter parameter) {
    this.algorithmSpec = algorithmSpec;
    this.instrumenter = algorithmStepInstrumenter;
    this.parameterValue = parameter.getValue();
  }
}
