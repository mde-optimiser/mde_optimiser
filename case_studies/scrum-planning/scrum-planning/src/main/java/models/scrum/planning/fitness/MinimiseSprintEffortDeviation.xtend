package models.scrum.planning.fitness

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseSprintEffortDeviation implements IGuidanceFunction {
	
	override computeFitness(Solution solution) {
				
		var fitness = (solution.model.getFeature("sprints") as EList<EObject>).map[ sprint | 
			
			new Double((sprint.getFeature("committedItem") as EList<EObject>).fold(0d)[ result, item |
				
				result + item.getFeature("Effort") as Integer
				
			])
			
		].toList
		
		var effortStandardDeviation = new StandardDeviation().evaluate(fitness)
		
		println("Sprint effort distribution: " + fitness)
		println("Sprint effort standard deviation: " + effortStandardDeviation)
		
		return effortStandardDeviation
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
