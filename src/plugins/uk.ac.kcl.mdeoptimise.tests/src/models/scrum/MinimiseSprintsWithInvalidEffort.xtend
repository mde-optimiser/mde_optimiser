package models.scrum

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.common.util.EList

class MinimiseSprintsWithInvalidEffort implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		
		var invalidSprints = (model.getFeature("sprints") as EList<EObject>).filter[ sprint | 
			
			(sprint.getFeature("committedItem") as EList<EObject>).fold(0)[ result, item |
				
				result + item.getFeature("Effort") as Integer
				
			] as Integer > 8
			
		].toList
		
		var fitness = 0
		
		if(invalidSprints !== null) {
			fitness = invalidSprints.length	
		}
		
		println("Counted invalid sprints: " + fitness)
		
		return fitness * -1
	}
	
	override getName() {
		return "Minimise sprints with invalid effort"
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