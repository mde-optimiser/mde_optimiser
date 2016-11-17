package uk.ac.kcl.interpreter.guidance

import uk.ac.kcl.interpreter.IGuidanceFunction

interface IGuidanceFunctionsFactory {
	
	/**
	 * Given an objective descriptor initialize the correct objective function.
	 */
	def IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter);
}
