package models.moea

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject

class ClassesCounter extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(EObject model) {
		
		/*
		val modelClass = model.eClass
		val classes = modelClass.getEStructuralFeature("classes")
		val classesClass = classes.EType as EClass
		val encapsulates = classesClass.getEStructuralFeature("encapsulates");
		
		var fitness = -1.0 * (model.eGet(classes) as EList<EObject>).filter[class | (class.eGet(encapsulates) as EList<EObject>).empty].size;
		*/
		var fitness = -1.0 * (model.getFeature("classes") as EList<EObject>).filter[class | (class.getFeature("encapsulates") as EList<EObject>).empty].size;
		fitness;
	}
	
	override getName() {
		return "Classes counter"
	}
	
}
		