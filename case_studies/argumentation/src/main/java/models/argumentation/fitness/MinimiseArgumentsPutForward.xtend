package models.argumentation.fitness

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseArgumentsPutForward implements IGuidanceFunction {
	
	override computeFitness(Solution solution) {
		
		var scenario = new EcoreScenarioParser(solution.model)
		
		var frameworkArguments = scenario.getArgumentationFrameworkArguments()
		
		var argumentsPutForward = scenario.getArgumentsPutForward(frameworkArguments);
		
		return argumentsPutForward.size
	}
	
	override getName() {
		return "Minimise persuader arguments put forward"
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
