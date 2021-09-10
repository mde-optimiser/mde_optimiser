package models.java

import uk.ac.kcl.interpreter.IGuidanceFunction
import uk.ac.kcl.interpreter.guidance.Solution

class JavaObjectiveFunction implements IGuidanceFunction {
	
	override computeFitness(Solution model) {
		return 5
	}
	
	override getName() {
		return "Java objective name"
	}
	
}