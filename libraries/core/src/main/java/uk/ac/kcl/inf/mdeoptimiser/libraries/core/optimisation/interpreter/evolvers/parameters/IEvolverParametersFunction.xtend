package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters

import java.util.List
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

interface IEvolverParametersFunction {

	def Object sample(List<Solution> model);
}
