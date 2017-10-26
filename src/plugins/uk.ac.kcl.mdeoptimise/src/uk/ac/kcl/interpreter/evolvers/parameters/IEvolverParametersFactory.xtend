package uk.ac.kcl.interpreter.evolvers.parameters

interface IEvolverParametersFactory {
		
	/**
	 * Given an objective descriptor initialize the correct objective function.
	 */
	def IEvolverParametersFunction getParameterValue(EvolverParameterAdapter guidanceFunctionAdapter);
}