package models.resource.allocation.fitness

import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.ecore.EObject
import org.xtext.example.mydsl.interpreter.SolutionInterpreter
import org.xtext.example.mydsl.myDsl.Model

class ResourceAllocationObjective implements IGuidanceFunction {
	
	override computeFitness(EObject solution) {
		
		
		var model = solution as Model//ModelGenerator.XMItoXtext(solution)
		
		try {
				val objective = SolutionInterpreter.calculateObjective(model.solution, model.objective)
				
				println(String.format("%s: %s", getName, objective))
				
				return objective
				
		} catch(Exception e) {
			println("Objective exception: " + e.message + e.stackTrace.toString)
			return Integer.MAX_VALUE
			
		}
	}
	
	override getName() {
		return "Resource allocation task objective"
	}
}