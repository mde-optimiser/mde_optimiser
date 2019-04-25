package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation

import org.eclipse.xtext.xbase.XNumberLiteral

class AdaptiveMutationStepSizeStrategy extends AbstractMutationStepSizeStrategy {

	Integer maxGenerationsNumber
	Integer populationSize
	Integer currentGenerationCounter
	
	new(){
		this.currentGenerationCounter = 1;
	}

	// TODO: Make validation requirement so that this
	// strategy can only be specified when there is a time or evolutions termination condition specified
	override getNextStepSize() {

		var mutationStepSize = 1 - 0.9 * getCurrentAlgorithmGenerations / getMaxGenerations

		var ceiledValue = Math.ceil(mutationStepSize * 10)
		
		return ceiledValue as int
	}

	def getCurrentAlgorithmGenerations() {

		// extract from the instrumenter
		this.currentGenerationCounter = this.currentGenerationCounter + 1
		
		return this.currentGenerationCounter
	}

	/**
	 * Extract the max generation number value from the 'mutation.step' parameter.
	 * 
	 * @return maxGenerationsNumber limit of the adaptive step size
	 */
	def int getMaxGenerationsNumber() {

		if (this.maxGenerationsNumber === null) {
			var numberLiteral = this.parameterValue.params.get(0) as XNumberLiteral
			this.maxGenerationsNumber = Integer.parseInt(numberLiteral.value);
		}

		return this.maxGenerationsNumber
	}

	def int getAlgorithmPopulation() {

		if (this.populationSize === null) {
			var populationSize = this.algorithmSpec.parameters.filter[parameter|parameter.name.equals("population")].head
			this.populationSize = Integer.parseInt(populationSize.value.numeric)
		}
		
		return this.populationSize
	}
	
	def int getMaxGenerations(){
		this.getMaxGenerationsNumber * this.algorithmPopulation
	}
	
}
