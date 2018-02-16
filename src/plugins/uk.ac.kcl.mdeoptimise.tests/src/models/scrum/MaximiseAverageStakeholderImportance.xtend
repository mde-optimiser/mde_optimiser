package models.scrum

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation

class MaximiseAverageStakeholderImportance implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		
		
		
		val sprints = (model.getFeature("sprints") as EList<EObject>)
		val standardDeviationCalculator = new StandardDeviation();
		
		val stakeholderImportanceSprintDeviation = new ArrayList<Double>();
		
		(model.getFeature("stakeholders") as EList<EObject>).forEach[stakeholder |
			
			
			var effortAccrossSprints = sprints.map[
			sprint | new Double((sprint.getFeature("committedItem") as EList<EObject>)
				.filter[ item | item.getFeature("stakeholder").equals(stakeholder)]
				.fold(0d)[result, item | result + item.getFeature("Importance") as Integer])
			]
			
			stakeholderImportanceSprintDeviation.add(standardDeviationCalculator.evaluate(effortAccrossSprints))
		]
		
		var importanceStandardDeviation = standardDeviationCalculator.evaluate(stakeholderImportanceSprintDeviation)
		
		println("Sprint stakeholder importance distribution: " + stakeholderImportanceSprintDeviation)
		println("Sprint stakeholder importance standard deviation: " + importanceStandardDeviation)
		
		return importanceStandardDeviation * -1;
	}
	
	override getName() {
		return "Minimise Next Release Cost"
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
