package uk.ac.kcl.interpreter.evolvers.parameters.functions

import uk.ac.kcl.interpreter.evolvers.parameters.IEvolverParametersFunction
import org.eclipse.emf.ecore.EObject
import java.util.Random
import java.util.List

class RandomEvolverParameter implements IEvolverParametersFunction {
	
	override sample(List<EObject> model) {
	
		var parameter = new Random().nextInt(20);
		
		println("Returning random parameter value: " + parameter)
		
		return parameter
	}
}