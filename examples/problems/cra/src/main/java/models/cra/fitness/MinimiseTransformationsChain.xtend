package models.cra.fitness

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseTransformationsChain extends AbstractModelQueryFitnessFunction {
	
	override double computeFitness(Solution model) {
		return model.transformationsChain.length
	}
	
	override getName() {
		return "Minimise Transformations Chain"
	}
	
}
