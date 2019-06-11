package models.resource.allocation.fitness

import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.Model
import org.xtext.example.mydsl.interpreter.SolutionInterpreter

class RCRequestNonFuncPropertiesProvidedBySelectedComponents implements IGuidanceFunction {
	
	override computeFitness(EObject solution) {
		
		var model = (solution as Model)
		
		
		try {

			val constraint = SolutionInterpreter.checkAllRequestNonFunctionalRequirementsAreMet(model.solution)
			println(String.format("%s: %s", getName, constraint))
		
			return constraint

		} catch(Exception e) {
					return Integer.MAX_VALUE
			
		}
	}
	
	override getName() {
		return "Request constraint: Ensure that selected components satisfy the request non-functional properties"
	}
}