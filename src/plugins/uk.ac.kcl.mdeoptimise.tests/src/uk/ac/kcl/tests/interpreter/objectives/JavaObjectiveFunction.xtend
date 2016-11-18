package uk.ac.kcl.tests.interpreter.objectives

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction

class JavaObjectiveFunction implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		return 5
	}
	
	override getName() {
		return "Java objective name"
	}
	
}