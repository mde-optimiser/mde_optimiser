package models.cra.fitness

import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MinimiseEmptyClasses extends AbstractModelQueryFitnessFunction {
	
	override computeFitness(Solution solution) {
		
		var fitness = (solution.model.getFeature("classes") as EList<EObject>).filter[class | (class.getFeature("encapsulates") as EList<Object>).empty].size;
		return fitness;
	}
	
	override getName() {
		return "Minimise Empty Classes"
	}
	
}