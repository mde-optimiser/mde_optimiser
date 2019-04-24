package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.generator

import org.deeplearning4j.arbiter.optimize.api.CandidateGenerator
import org.deeplearning4j.arbiter.optimize.generator.RandomSearchGenerator
import org.deeplearning4j.arbiter.optimize.parameter.integer.IntegerParameterSpace
import org.eclipse.xtext.xbase.XExpression
import org.eclipse.xtext.xbase.XNumberLiteral
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterSearchSpec
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOParameterSpace

class CandidateGeneratorFactory {

	static CandidateGeneratorFactory factoryInstance

	private new() {
	}

	def static synchronized CandidateGeneratorFactory getINSTANCE() {

		if (factoryInstance === null) {
			factoryInstance = new CandidateGeneratorFactory()
		}

		return factoryInstance;
	}

	def CandidateGenerator get(ParameterSearchSpec parameterSearchSpec) {

		var parameterSpace = getParameterSpace(parameterSearchSpec);
		var parameterSearchAlgorithm = getParameterSearchAlgorithmName(parameterSearchSpec);

		switch parameterSearchAlgorithm {
			case "random": {
				return new RandomSearchGenerator(parameterSpace, null)
			}
			// TODO Would be interesting to add Grid and SMAC
			default:
				throw new IllegalArgumentException(
					String.format("Unexpected parameter search candidate generator algorithm name: %s",
						parameterSearchAlgorithm))
		}
	}

	/**
	 * Get the configured algorithm algorithm name to use for generating parameter values.
	 * 
	 * @return algorithm name
	 */
	def getParameterSearchAlgorithmName(ParameterSearchSpec spec) {
		var algorithmParameter = getParameter(spec, "strategy")
		// These are just strategy names: random (should add grid, smac etc)
		return algorithmParameter.value.functional
	}

	def getParameterSpace(ParameterSearchSpec parameterSearchSpec) {
		var parameterSpace = new MDEOParameterSpace();

		parameterSpace.evolutions = getParameterSpace(parameterSearchSpec, "evolutions")
		parameterSpace.populationSize = getParameterSpace(parameterSearchSpec, "population")

		return parameterSpace
	}

	def getParameterSpace(ParameterSearchSpec spec, String key) {

		var populationParameter = getParameter(spec, key)

		var populationStart = getParameterValue(populationParameter.value.params.get(0))
		var populationEnd = getParameterValue(populationParameter.value.params.get(1))

		return new IntegerParameterSpace(populationStart, populationEnd);
	}

	// TODO This is repeated several times in other parts. Need to add a parameter interpreter class.	
	def Integer getParameterValue(XExpression expression) {

		var numberLiteral = expression as XNumberLiteral
		return Integer.parseInt(numberLiteral.value)
	}

	def getParameter(ParameterSearchSpec parameterSearchSpec, String keyName) {
		return parameterSearchSpec.parameters.filter[parameter|parameter.name.equals(keyName)].head
	}
}
