package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.tests.optimisation.moea.MoeaModelProvider

class MinimiseClasslessFeatures extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(EObject model) {
		var fitness = (model.getFeature("features") as EList<EObject>).filter[feature | feature.getFeature("isEncapsulatedBy") == null].size;
		println("Classless features:" + fitness)
		//if(fitness > 1 ) return fitness;
		
		//var a = new MoeaModelProvider();
		
		//var cra = new MaximiseCRA();
		
		//a.storeModel(model, (model.getFeature("classes") as EList<EObject>).length, cra.computeFitness(model), fitness, "crossover-models/")
		
		return fitness;
	}
	
	override getName() {
		return "Mimise classless features"
	}
	
}