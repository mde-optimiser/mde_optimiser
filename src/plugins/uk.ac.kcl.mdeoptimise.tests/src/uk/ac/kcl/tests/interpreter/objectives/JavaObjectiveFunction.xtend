package uk.ac.kcl.tests.interpreter.objectives

import uk.ac.kcl.interpreter.IFitnessFunction
import org.eclipse.emf.ecore.EObject

class JavaObjectiveFunction implements IFitnessFunction {
	
	override computeFitness(EObject model) {
		return 5
	}
	
}