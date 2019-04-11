package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.guidance.Solution

class MinimiseEmptyClasses extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(Solution model) {
		
		var fitness = (model.model.getFeature("classes") as EList<EObject>).filter[class | (class.getFeature("encapsulates") as EList<Object>).empty].size;
		println("Empty classes:" + fitness)
		return fitness;
	}
	
	override getName() {
		return "Minimise Empty Classes"
	}
	
}