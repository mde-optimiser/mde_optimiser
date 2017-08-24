package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

class MinimiseClasslessMethods extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(EObject model) {
		var fitness = -1.0 * (model.getFeature("features") as EList<EObject>).filter[feature | feature.getFeature("isEncapsulatedBy") == null].size;
		fitness;
	}
	
	override getName() {
		return "Minimise classless methods"
	}
	
}