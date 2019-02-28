package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation

import org.eclipse.xtext.xbase.XNumberLiteral
import java.util.Random

class IntervalMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

	Integer lowerBound;
	Integer upperBound;
	Random randomIntervalGenerator

	new(){
		this.randomIntervalGenerator = new Random();	
	}

	/**
	 * Generate a random step size between the specified interval values.
	 * 
	 * @return int holding the mutation step size value
	 */
	override getNextStepSize() {
		var range = getUpperBound - getLowerBound;
		return randomIntervalGenerator.nextInt(range) + getLowerBound
	}

	/**
	 * Extract the lower bound step size value from the specified interval.
	 * 
	 * @return lowerBound limit of the step size interval
	 */
	def int getLowerBound() {

		if (this.lowerBound === null) {
			var numberLiteral = this.parameterValue.params.get(0) as XNumberLiteral
			this.lowerBound = Integer.parseInt(numberLiteral.value);
		}

		return this.lowerBound
	}

	/**
	 * Extract the upper bound step size value from the specified interval.
	 * 
	 * @return upperBound limit of the step size interval
	 */
	def int getUpperBound() {
		
		if (this.upperBound === null) {
			var numberLiteral = this.parameterValue.params.get(1) as XNumberLiteral
			this.upperBound = Integer.parseInt(numberLiteral.value);
		}

		return this.upperBound
	}
}
