package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.guidance.Solution

class MinimiseClasslessFeatures extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(Solution model) {
		var fitness = (model.model.getFeature("features") as EList<EObject>).filter[feature | feature.getFeature("isEncapsulatedBy") == null].size;
		println("Classless features:" + fitness)

		return fitness;
	}
	
	override getName() {
		return "Mimise classless features"
	}
	
}