package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation

class FixedMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {
	
	/**
	 * Gets the next step size based on previous performance.
	 * 
	 * @return stepSize indicating the number of mutations to be applied in the next
	 * algorithm step
	 */
	override getNextStepSize() {
		return Integer.parseInt(this.parameterValue.numeric)
	}
}
