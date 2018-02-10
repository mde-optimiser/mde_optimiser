package models.scrum

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.common.util.EList
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation

class MaximiseAverageSprintEffort implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		
		
		
		var fitness = (model.getFeature("sprints") as EList<EObject>).map[ sprint | 
			
			new Double((sprint.getFeature("committedItem") as EList<EObject>).fold(0d)[ result, item |
				
				result + item.getFeature("Effort") as Integer
				
			])
			
		].toList
		
		var effortStandardDeviation = new StandardDeviation().evaluate(fitness)
		
		println("Sprint effort distribution: " + fitness)
		println("Sprint effort standard deviation: " + effortStandardDeviation)
		
		return effortStandardDeviation * -1
	}
	
	override getName() {
		return "Maximise average sprint effort"
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
