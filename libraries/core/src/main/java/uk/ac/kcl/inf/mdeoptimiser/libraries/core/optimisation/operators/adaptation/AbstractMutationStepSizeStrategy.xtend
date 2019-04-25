package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec
import org.moeaframework.Instrumenter
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterValue
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter

abstract class AbstractMutationStepSizeStrategy implements MutationStepSizeStrategy {

	protected Instrumenter instrumenter;
	protected AlgorithmSpec algorithmSpec;
	protected ParameterValue parameterValue;

	/**
	 * Initializes the current strategy with a reference to the algorithm step size
	 * instrumenter.
	 * 
	 * @param solverSpec containing the current MOPT solver settings
	 * @param algorithmStepInstrumenter algorithm step size instrumenter
	 * @param 'mutation.step' specified 'mutation.step' parameter and values
	 */
	override initialize(AlgorithmSpec algorithmSpec, Instrumenter algorithmStepInstrumenter, Parameter parameter) {
		this.algorithmSpec = algorithmSpec
		this.instrumenter = algorithmStepInstrumenter
		this.parameterValue = parameter.value
	}

}
