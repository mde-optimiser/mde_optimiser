package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation;

import org.moeaframework.Instrumenter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;

public class MutationStepSizeStrategyFactory {

  MutationStepSizeStrategy mutationStepSizeStrategy;
  AlgorithmSpec algorithmSpec;
  Instrumenter stepSizeAlgorithmInstrumenter;
  Parameter mutationStepParameter;

  public MutationStepSizeStrategyFactory(
      AlgorithmSpec algorithmSpec, Instrumenter stepSizeAlgorithmInstrumenter) {
    this.algorithmSpec = algorithmSpec;
    this.stepSizeAlgorithmInstrumenter = stepSizeAlgorithmInstrumenter;
    this.mutationStepParameter = getMutationStepParameter(this.algorithmSpec);
  }

  /**
   * Load and initialize the configured 'mutation.step' strategy.
   *
   * @return MutationStepSizeStrategy configured in the AlgorithmSpec
   */
  public MutationStepSizeStrategy getStrategy() {

    if (this.mutationStepSizeStrategy == null) {

      var mutationStepParameter = this.getMutationStepStrategySpecification(this.algorithmSpec);

      switch (mutationStepParameter) {
        case "fixed":
          this.mutationStepSizeStrategy = new FixedMutationStepSizeStrategy();
          break;
        case "interval":
          this.mutationStepSizeStrategy = new IntervalMutationStepSizeStrategy();
          break;
        case "adaptive":
          this.mutationStepSizeStrategy = new AdaptiveMutationStepSizeStrategy();
          break;

          // TODO Better exception handling here? Perhaps specific mutation step size exception?
        default:
          throw new UnexpectedAlgorithmParameterException(mutationStepParameter);
      }

      this.mutationStepSizeStrategy.initialize(
          this.algorithmSpec, this.stepSizeAlgorithmInstrumenter, this.mutationStepParameter);
    }

    return this.mutationStepSizeStrategy;
  }

  /**
   * Get the 'mutation.step' parameter configuration from the specified algorithm parameters
   *
   * @return Parameter containing the 'mutation.step' parameter
   */
  public Parameter getMutationStepParameter(AlgorithmSpec algorithmSpec) {
    return algorithmSpec.getParameters().stream()
        .filter(parameter -> parameter.getName().equals("mutation.step"))
        .findFirst()
        .get();
  }

  /**
   * Extract the 'mutation.step' strategy parameters from the configured mutation step size
   * parameter.
   *
   * @param algorithmSpec containing the specified algorithm parameters.
   * @return String indicating the name of the mutation step size adaptation to be used.
   */
  public String getMutationStepStrategySpecification(AlgorithmSpec algorithmSpec) {

    // Do we have a number to use as a fixed size for a mutation step
    // eg: 1,2,3,n then we default to the fixed mutation step size strategy
    if (this.mutationStepParameter.getValue().getNumeric() != null) {
      return "fixed";
    }

    // Do we have a function name to use as a stategy without other parameters
    // eg: fixed then we return the name of the strategy to initialize it from the factory
    if (this.mutationStepParameter.getValue().getFunctional() != null) {
      return this.mutationStepParameter.getValue().getFunctional();
    }

    // Do we have a function name with parameters to use as a strategy to which we pass its ordered
    // parameters eg: interval(1, 5, 9) then e return the name of the function and the parameters
    // will be extracted in the strategy implementation class
    return this.mutationStepParameter.getValue().getParametric();
  }
}
