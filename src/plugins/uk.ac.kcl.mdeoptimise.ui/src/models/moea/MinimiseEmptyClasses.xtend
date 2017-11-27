package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import java.util.LinkedList

class MinimiseEmptyClasses extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(EObject model) {
		
		var fitness = (model.getFeature("classes") as EList<EObject>).filter[class | (class.getFeature("encapsulates") as EList<Object>).empty].size;
		println("Empty classes:" + fitness)
		return fitness;
	}
	
	override getName() {
		return "Minimise Empty Classes"
	}
	
}