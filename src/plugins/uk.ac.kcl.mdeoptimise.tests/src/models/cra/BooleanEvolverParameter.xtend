package models.cra

import uk.ac.kcl.interpreter.evolvers.parameters.IEvolverParametersFunction
import java.util.List
import uk.ac.kcl.interpreter.guidance.Solution

class BooleanEvolverParameter implements IEvolverParametersFunction {
	
	override sample(List<Solution> model) {
		return false;
	}
	
}