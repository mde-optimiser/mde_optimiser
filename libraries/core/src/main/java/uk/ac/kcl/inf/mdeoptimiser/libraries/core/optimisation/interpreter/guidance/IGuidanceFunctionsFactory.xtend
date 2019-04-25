package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction

interface IGuidanceFunctionsFactory {
	
	/**
	 * Given an objective descriptor initialize the correct objective function.
	 */
	def IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter);
}
