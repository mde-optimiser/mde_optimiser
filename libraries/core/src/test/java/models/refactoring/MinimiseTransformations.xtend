package models.refactoring

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseTransformations implements IGuidanceFunction {

	override double computeFitness(Solution model) {
		return model.transformationsChain.length
	}
	
	override getName() {
		return "Minimise Transformations"
	}
	
}