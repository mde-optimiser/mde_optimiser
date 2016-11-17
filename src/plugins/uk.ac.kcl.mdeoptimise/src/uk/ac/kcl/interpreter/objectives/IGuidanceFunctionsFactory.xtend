package uk.ac.kcl.interpreter.objectives

import uk.ac.kcl.interpreter.IGuidanceFunction

interface IGuidanceFunctionsFactory {
	
	/**
	 * Given an objective descriptor initialize the correct objective function.
	 */
	def IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter);
}
