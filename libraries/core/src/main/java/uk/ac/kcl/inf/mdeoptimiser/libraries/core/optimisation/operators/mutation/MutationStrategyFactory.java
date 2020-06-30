package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation;

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelector;

public class MutationStrategyFactory {

  HenshinExecutor henshinExecutor;
  MutationStrategy mutationStrategy;
  MutationStepSizeStrategy mutationStepSizeStrategy;
  Parameter mutationStrategyParameter;

  public MutationStrategyFactory(
      HenshinExecutor henshinExecutor,
      MutationStepSizeStrategy mutationStepSizeStrategy,
      AlgorithmSpec algorithmSpec) {
    this.henshinExecutor = henshinExecutor;
    this.mutationStepSizeStrategy = mutationStepSizeStrategy;
    this.mutationStrategyParameter = getMutationStrategyParameter(algorithmSpec);
  }

  /**
   * Get the 'mutation.strategy' parameter configuration from the specified algorithm parameters
   *
   * @return Parameter containing the 'mutation.step' parameter
   */
  public Parameter getMutationStrategyParameter(AlgorithmSpec algorithmSpec) {
    return algorithmSpec.getParameters().stream()
        .filter(parameter -> parameter.getName().equals("mutation.strategy"))
        .findFirst()
        .get();
  }

  public MutationStrategy getStrategy() {

    if (this.mutationStrategy == null) {

      // TODO This should be an enum perhaps
      var strategyType = getMutationStrategySpecification(this.mutationStrategyParameter);

      switch (strategyType) {
        case "random":
          {
            return new RandomOperatorMutationStrategy(
                this.henshinExecutor,
                this.mutationStepSizeStrategy,
                new RandomOperatorSelector(this.henshinExecutor));
          }

        case "repetitive":
          {
            return new RepetitiveOperatorMutationStrategy(
                this.henshinExecutor,
                this.mutationStepSizeStrategy,
                new RandomOperatorSelector(this.henshinExecutor));
          }

          // Manual Henshin Matching Strategy
        case "manual":
          {
            return new ManualMatchingMutationStrategy(
                this.henshinExecutor, this.mutationStepSizeStrategy);
          }
      }
    }

    return this.mutationStrategy;
  }

  // TODO WIP
  public String getMutationStrategySpecification(Parameter parameter) {

    if (parameter == null) {
      return "random";
    }

    if (parameter.getValue().getNumeric() != null) {
      return "random";
    }

    // Do we have a function name to use as a stategy without other parameters
    // eg: random then we return the name of the strategy to initialize it from the factory (this is
    // only allowed to be random or repetitive
    // currently)
    if (parameter.getValue().getFunctional() != null) {
      return parameter.getValue().getFunctional();
    }

    return null;
  }
}
