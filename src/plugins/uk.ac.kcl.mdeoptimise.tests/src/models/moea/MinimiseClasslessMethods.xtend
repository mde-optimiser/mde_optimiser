package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.guidance.Solution

class MinimiseClasslessMethods extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(Solution model) {
		var fitness = -1.0 * (model.model.getFeature("features") as EList<EObject>).filter[feature | feature.getFeature("isEncapsulatedBy") == null].size;
		fitness;
	}
	
	override getName() {
		return "Minimise classless methods"
	}
	
}