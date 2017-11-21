package models.stack

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation

class MinimiseStandardDeviation  implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		
		var fitness = (model.getFeature("stacks") as EList<EObject>).map[ e | new Double(e.getFeature("load") as Integer)]
		var sD = new StandardDeviation().evaluate(fitness)
		println("Found deviation: " + sD)
		return sD;
	}
	
	override getName() {
		return "Minimise Standard Deviation"
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