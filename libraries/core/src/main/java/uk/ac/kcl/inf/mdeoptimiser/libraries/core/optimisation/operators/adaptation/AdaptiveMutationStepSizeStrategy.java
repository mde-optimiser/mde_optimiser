package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation;

import org.eclipse.xtext.xbase.XNumberLiteral;

public class AdaptiveMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

  Integer maxGenerationsNumber;
  Integer populationSize;
  Integer currentGenerationCounter;

  public AdaptiveMutationStepSizeStrategy() {
    this.currentGenerationCounter = 1;
  }

  // TODO: Make validation requirement so that this
  // strategy can only be specified when there is a time or evolutions termination condition
  // specified
  public int getNextStepSize() {

    var mutationStepSize = 1 - 0.9 * getCurrentAlgorithmGenerations() / getMaxGenerations();

    var ceiledValue = Math.ceil(mutationStepSize * 10);

    return (int) ceiledValue;
  }

  public int getCurrentAlgorithmGenerations() {

    // extract from the instrumenter
    this.currentGenerationCounter = this.currentGenerationCounter + 1;

    return this.currentGenerationCounter;
  }

  /**
   * Extract the max generation number value from the 'mutation.step' parameter.
   *
   * @return maxGenerationsNumber limit of the adaptive step size
   */
  public int getMaxGenerationsNumber() {

    if (this.maxGenerationsNumber == null) {
      var numberLiteral = (XNumberLiteral) this.parameterValue.getParams().get(0);
      this.maxGenerationsNumber = Integer.parseInt(numberLiteral.getValue());
    }

    return this.maxGenerationsNumber;
  }

  public int getAlgorithmPopulation() {

    if (this.populationSize == null) {
      var populationSize =
          this.algorithmSpec.getParameters().stream()
              .filter(parameter -> parameter.getName().equals("population"))
              .findFirst()
              .get();
      this.populationSize = Integer.parseInt(populationSize.getValue().getNumeric());
    }

    return this.populationSize;
  }

  public int getMaxGenerations() {
    return this.getMaxGenerationsNumber() * this.getAlgorithmPopulation();
  }
}
