package models.refactoring.fitness


import uk.ac.kcl.interpreter.guidance.Solution
import uk.ac.kcl.interpreter.IGuidanceFunction

class MinimiseTransformations implements IGuidanceFunction {

	override double computeFitness(Solution model) {
		//val cohesion = calculateCohesionRatio(model);

		//println("Matches found: " + model.transformationsChain.length)
		return model.transformationsChain.length
	}
	
	override getName() {
		return "Minimise Transformations"
	}
	
}