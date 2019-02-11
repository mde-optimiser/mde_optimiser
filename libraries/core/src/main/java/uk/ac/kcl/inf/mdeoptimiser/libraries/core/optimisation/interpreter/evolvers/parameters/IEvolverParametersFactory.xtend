package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters

import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.henshin.model.Parameter
import java.util.List
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

interface IEvolverParametersFactory {
		
	/**
	 * Given an objective descriptor initialize the correct objective function.
	 */
	def Object getParameterValue(Unit unit, Parameter parameter, List<Solution> model);
}
