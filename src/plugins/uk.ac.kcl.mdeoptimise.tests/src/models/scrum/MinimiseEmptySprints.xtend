package models.scrum

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.common.util.EList

class MinimiseEmptySprints implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		
		var sprints = (model.getFeature("sprints") as EList<EObject>).filter[ 
			sprint | (sprint.getFeature("committedItem") as EList<EObject>).length == 0].toList
		
		var fitness = 0
		
		if(sprints != null) {
			fitness = sprints.length	
		}
		
		println("Counted empty sprints: " + fitness)
		
		return fitness * -1
	}
	
	override getName() {
		return "Mimise empty sprints"
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
	