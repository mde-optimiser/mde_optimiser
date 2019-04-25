package models.moea

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseTransformations extends AbstractModelQueryFitnessFunction {

	override double computeFitness(Solution model) {
		//val cohesion = calculateCohesionRatio(model);

		println("Matches found: " + model.transformationsChain.length)
		return model.transformationsChain.length
	}
	
	override getName() {
		return "Maximise CRA"
	}
	
}