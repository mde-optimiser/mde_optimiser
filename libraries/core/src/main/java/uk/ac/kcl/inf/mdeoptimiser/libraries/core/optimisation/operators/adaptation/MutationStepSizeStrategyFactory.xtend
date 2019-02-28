package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec
import java.security.InvalidAlgorithmParameterException
import org.moeaframework.Instrumenter
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.FixedMutationStepSizeStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.IntervalMutationStepSizeStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.AdaptiveMutationStepSizeStrategy

class MutationStepSizeStrategyFactory {

	MutationStepSizeStrategy mutationStepSizeStrategy;
	AlgorithmSpec algorithmSpec;
	Instrumenter stepSizeAlgorithmInstrumenter;
	Parameter mutationStepParameter;

	new(AlgorithmSpec algorithmSpec, Instrumenter stepSizeAlgorithmInstrumenter) {
		this.algorithmSpec = algorithmSpec;
		this.stepSizeAlgorithmInstrumenter = stepSizeAlgorithmInstrumenter;
		this.mutationStepParameter = getMutationStepParameter(this.algorithmSpec)
	}

	/**
	 * Load and initialize the configured 'mutation.step' strategy.
	 * 
	 * @return MutationStepSizeStrategy configured in the AlgorithmSpec
	 */
	def MutationStepSizeStrategy getStrategy() {

		if (this.mutationStepSizeStrategy === null) {

			val mutationStepParameter = getMutationStepStrategySpecification(this.algorithmSpec)

			switch mutationStepParameter {
				case "fixed":
					this.mutationStepSizeStrategy = new FixedMutationStepSizeStrategy()
				case "interval":
					this.mutationStepSizeStrategy = new IntervalMutationStepSizeStrategy()
				case "adaptive":
					this.mutationStepSizeStrategy = new AdaptiveMutationStepSizeStrategy()
				default:
					throw new InvalidAlgorithmParameterException(mutationStepParameter)
			}

			this.mutationStepSizeStrategy.initialize(this.algorithmSpec, this.stepSizeAlgorithmInstrumenter,
				this.mutationStepParameter)
		}

		return this.mutationStepSizeStrategy;
	}

	/**
	 * Get the 'mutation.step' parameter configuration from the specified algorithm parameters
	 * 
	 * @return Parameter containing the 'mutation.step' parameter
	 */
	def Parameter getMutationStepParameter(AlgorithmSpec algorithmSpec) {
		return algorithmSpec.parameters.filter[parameter|parameter.name.equals("mutation.step")].head
	}

	/**
	 * Extract the 'mutation.step' strategy parameters from the configured 
	 * mutation step size parameter.
	 * 
	 * @param algorithmSpec containing the specified algorithm parameters.
	 * @return String indicating the name of the mutation step size adaptation to be used.
	 */
	def String getMutationStepStrategySpecification(AlgorithmSpec algorithmSpec) {

		// Do we have a number to use as a fixed size for a mutation step
		// eg: 1,2,3,n then we default to the fixed mutation step size strategy
		if (this.mutationStepParameter.value.numeric !== null) {
			return "fixed"
		}

		// Do we have a function name to use as a stategy without other parameters
		// eg: fixed then we return the name of the strategy to initialize it from the factory
		if (this.mutationStepParameter.value.functional !== null) {
			return this.mutationStepParameter.value.functional
		}

		// Do we have a function name with parameters to use as a strategy to which we pass its ordered
		// parameters eg: interval(1, 5, 9) then e return the name of the function and the parameters
		// will be extracted in the strategy implementation class
		return this.mutationStepParameter.value.parametric
	}
}
