package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms;

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException;

public class AlgorithmVariation {

  Parameter parameter;

  public AlgorithmVariation(Parameter parameter) {

    if (!parameter.getName().equals("variation")) {
      throw new UnexpectedAlgorithmParameterException(parameter.getName());
    }

    this.parameter = parameter;
  }

  /**
   * Checks to see if te configured variation strategy is 'mutation' consisting of a typical
   * mutation only strategy where each population member is evolved using a single mutation only.
   *
   * @returns true if the specified mutation strategy is 'mutation' only
   */
  public boolean isMutationVariation() {

    return (parameter.getValue().getFunctional() != null
        && parameter.getValue().getFunctional().equals("mutation"));
  }

  /**
   * Checks to see if the configured variation strategy is 'genetic' consisting of a crossover step,
   * in which two parents are combined to obtain an offspring, followed by a mutation applied to the
   * offspring.
   *
   * @returns
   */
  public boolean isGeneticVariation() {

    return parameter.getValue().getFunctional() != null
        && parameter.getValue().getFunctional().equals("genetic");
  }

  /**
   * As discussed, this does not really make sense if both of the probabilities are less than 100%,
   * because we lose one step.
   *
   * @returns true if the specified mutation strategy is probabilistic
   */
  public boolean isProbabilisticVariation() {

    return parameter.getValue().getParametric() != null
        && parameter.getValue().getParametric().equals("probabilistic");
  }

  // TODO What is this?
  public Double getCrossoverRate() {
    if (this.isProbabilisticVariation()) {}

    return 0.0d;
  }

  // TODO What is this?
  public Double getMutationRate() {

    if (this.isProbabilisticVariation()) {}

    return 0.0d;
  }
}
