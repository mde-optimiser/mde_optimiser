package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms;

import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XNumberLiteral;

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
    var value = parameter.getValue();
    boolean isProbabilistic = value.getParametric() != null
		        && value.getParametric().equals("probabilistic");
    if (isProbabilistic 
        && (value.getParams().size() != 2 
          || !(value.getParams().get(0) instanceof XNumberLiteral)
          || !(value.getParams().get(1) instanceof XNumberLiteral))) {
      throw new UnexpectedAlgorithmParameterException("Wrong arguments for probabilistic variation.");
    }

    return isProbabilistic;
  }

  public Double getCrossoverRate() {
    Double crossRate = 0.0d;
    if (this.isProbabilisticVariation()) {
      XNumberLiteral number = (XNumberLiteral) parameter.getValue().getParams().get(0);
      crossRate = Double.parseDouble(number.getValue());
    }
	  return crossRate;
  }

  public Double getMutationRate() {
    Double mutationRate = 0.0d;
    if (this.isProbabilisticVariation()) {
      XNumberLiteral number = (XNumberLiteral) parameter.getValue().getParams().get(1);
      mutationRate = Double.parseDouble(number.getValue());
    }
    return mutationRate;
  }
}
