package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation

import org.moeaframework.Instrumenter
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter

/**
 * Interface used to implement mutation step size adaptation strategies.
 */

interface MutationStepSizeStrategy {
	
	/**
	 * Invoked when the search starts.
	 * 
	 * @param algorithmStepInstrumenter algorith step size instrumenter
	 */
	def void initialize(AlgorithmSpec algorithmSpec, Instrumenter algorithmStepInstrumenter, Parameter parameter);
	
	/**
	 * Invoked during each step evaluation, when a new mutation needs to be applied and 
	 * returns a mutation step size.
	 */
	def int getNextStepSize();
}
