package uk.ac.kcl.interpreter.evolvers.parameters.functions

import uk.ac.kcl.interpreter.evolvers.parameters.IEvolverParametersFunction
import org.eclipse.emf.ecore.EObject
import java.util.Random

class RandomEvolverParameter implements IEvolverParametersFunction {
	
	override sample(EObject model) {
		return new Random().nextInt(1000);
	}
}