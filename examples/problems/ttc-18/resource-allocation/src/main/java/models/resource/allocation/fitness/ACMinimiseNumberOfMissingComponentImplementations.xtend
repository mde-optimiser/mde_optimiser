package models.resource.allocation.fitness

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.xtext.example.mydsl.interpreter.SolutionInterpreter
import org.xtext.example.mydsl.myDsl.Model

class ACMinimiseNumberOfMissingComponentImplementations implements IGuidanceFunction {
	
	override computeFitness(EObject solution) {
		
		var model = (solution as Model)
		
		try {

			val constraint = SolutionInterpreter.checkAllImplementationRequirementsAreMet(model.solution, true, false, false)
			println(String.format("%s: %s", getName, constraint))
		
			return constraint

		} catch(Exception e) {
			return Integer.MAX_VALUE
		}
	}
	
	override getName() {
		return "Architecture constraint: Minimise number of missing component implementations"
	}
}