package models.resource.allocation.fitness

import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.myDsl.Model
import org.xtext.example.mydsl.interpreter.SolutionInterpreter

class ACOneImplementationPerResource implements IGuidanceFunction {
	
	override computeFitness(EObject solution) {
		
		var model = (solution as Model)
		
		try {

			val constraint = SolutionInterpreter.checkEachResourceHasAtMostOneImplementationMapped(model.solution)
			println(String.format("%s: %s", getName, constraint))
		
		return constraint
			
			
			
		} catch(Exception e) {
					return Integer.MAX_VALUE
			
		}
	}
	
	override getName() {
		return "Architectural constraint: Check that each implementation is deployed on one resource"
	}
}