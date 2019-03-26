package models.cra.parameters

import java.util.List
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class BooleanEvolverParameterFalse implements IEvolverParametersFunction {
	
	override sample(List<Solution> arg0) {
		return false;
	}
	
}