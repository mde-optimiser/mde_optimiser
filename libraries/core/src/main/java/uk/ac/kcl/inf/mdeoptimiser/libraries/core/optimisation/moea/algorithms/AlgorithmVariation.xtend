package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmParameterException

class AlgorithmVariation {

	Parameter parameter;

	new(Parameter parameter) {

		if (!parameter.name.equals("variation")) {
			throw new UnexpectedAlgorithmParameterException(parameter.name);
		}

		this.parameter = parameter;
	}

	/**
	 * Checks to see if te configured variation strategy is 'mutation' consisting of a typical 
	 * mutation only strategy where each population member is evolved using a single mutation only.
	 * 
	 * @returns true if the specified mutation strategy is 'mutation' only
	 */
	def boolean isMutationVariation() {

		if (parameter.value.functional !== null && parameter.value.functional.equals("mutation")) {
			return true
		}
		return false
	}

	/**
	 * Checks to see if the configured variation strategy is 'genetic' consisting of a crossover
	 * step, in which two parents are combined to obtain an offspring, followed by a mutation applied
	 * to the offspring.
	 * 
	 * @returns 
	 */
	def boolean isGeneticVariation() {

		if (parameter.value.functional !== null && parameter.value.functional.equals("genetic")) {
			return true
		}
		return false
	}

	/**
	 * As discussed, this does not really make sense if both of the probabilities are less 
	 * than 100%, because we lose one step.
	 * 
	 * @returns true if the specified mutation strategy is probabilistic
	 */
	def boolean isProbabilisticVariation() {

		if (parameter.value.parametric !== null && parameter.value.parametric.equals("probabilistic")) {
			return true
		}

		return false
	}

	def Double getCrossoverRate() {
		if (this.isProbabilisticVariation) {
		}

		return 0.0d
	}

	def Double getMutationRate() {

		if (this.isProbabilisticVariation) {
		}

		return 0.0d
	}
}
