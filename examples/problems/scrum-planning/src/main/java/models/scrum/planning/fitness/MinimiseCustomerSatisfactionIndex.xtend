package models.scrum.planning.fitness

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList
import java.util.ArrayList
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseCustomerSatisfactionIndex implements IGuidanceFunction {
	
	override computeFitness(Solution solution) {
		
		val sprints = (solution.model.getFeature("sprints") as EList<EObject>)
		val standardDeviationCalculator = new StandardDeviation();
		
		val stakeholderImportanceSprintDeviation = new ArrayList<Double>();
		
		(solution.model.getFeature("stakeholders") as EList<EObject>).forEach[stakeholder |
			
			var effortAccrossSprints = sprints.map[
			sprint | new Double((sprint.getFeature("committedItem") as EList<EObject>)
				.filter[ item | item.getFeature("stakeholder").equals(stakeholder)]
				.fold(0d)[result, item | result + item.getFeature("Importance") as Integer])
			]
			
			stakeholderImportanceSprintDeviation.add(standardDeviationCalculator.evaluate(effortAccrossSprints))
		]
		
		var importanceStandardDeviation = standardDeviationCalculator.evaluate(stakeholderImportanceSprintDeviation)
		
		println("Sprint stakeholder importance distribution: " + stakeholderImportanceSprintDeviation)
		println("Sprint Customer Satisfaction Index: " + importanceStandardDeviation)
		
		return importanceStandardDeviation;
	}
	
	override getName() {
		return "Minimise Customer Satisfaction Index"
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
