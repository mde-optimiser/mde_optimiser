package models.argumentation.fitness

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MaximisePFWithAcceptableTopicsGroundReasoner implements IGuidanceFunction {
	
	override computeFitness(Solution solution) {
		
		var acceptableTopicsCounter = new EcoreScenarioParser(solution.model)
		
		var fitness = acceptableTopicsCounter.countAcceptableTopicsGroundReasoner
		
		return fitness * -1
	}
	
	override getName() {
		return "Maximise Persuadee Frameworks With Acceptable Topics using Ground Reasoner"
	}

	/**
	 * Helper function getting the value of the named feature (if it exists) for the given EObject.
	 */
	def Object getFeature (EObject o, String feature) {
		
		if(o === null){
			println("Null object given")
		}
		
		o.eGet(o.eClass.getEStructuralFeature(feature))
		
	}	
}
