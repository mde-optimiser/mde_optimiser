package models.cra.fitness

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseClasslessFeatures extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(Solution solution) {
		var fitness = (solution.model.getFeature("features") as EList<EObject>).filter[feature | feature.getFeature("isEncapsulatedBy") === null].size;
		println("Classless features: " + fitness)
		return fitness;
	}
	
	override getName() {
		return "Mimise classless features"
	}
	
}
	