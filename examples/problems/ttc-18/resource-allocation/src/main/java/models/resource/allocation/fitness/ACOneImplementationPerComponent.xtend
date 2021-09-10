package models.resource.allocation.fitness

import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.interpreter.SolutionInterpreter
import org.xtext.example.mydsl.myDsl.Model

class ACOneImplementationPerComponent implements IGuidanceFunction {
	
	override computeFitness(EObject solution) {
		
		var model = (solution as Model)
		
		try {

			val constraint = SolutionInterpreter.checkComponentsOnlyMappedOnce(model.solution)
		
		println(String.format("%s: %s", getName, constraint))
		
		return constraint
			
			
			
		} catch(Exception e) {
					return Integer.MAX_VALUE
			
		}
	}
	
	override getName() {
		return "Architectural constraint: Check that there is only one implementation per component"
	}
}