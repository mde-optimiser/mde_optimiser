package models.scrum.planning.fitness

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class HasNoUnassignedWorkItems implements IGuidanceFunction {
	
	override computeFitness(Solution solution) {
		
		var workItems = (solution.model.getFeature("backlog") as EObject).getFeature("workitems") as EList<EObject>
		var fitness = 0
		
		if(workItems != null) {
			fitness = workItems.filter[workItem | workItem.getFeature("isPlannedFor") == null].toList.length
		}
		
		println("Unassigned backlog work items: " + fitness)
		
		return fitness
	}
	
	override getName() {
		return "Mimise unassigned work items"
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