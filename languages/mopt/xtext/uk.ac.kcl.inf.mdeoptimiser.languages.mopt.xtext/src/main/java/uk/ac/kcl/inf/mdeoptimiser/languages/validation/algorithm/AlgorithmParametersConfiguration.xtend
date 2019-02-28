package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm

import java.util.List
import java.util.ArrayList
import java.util.Map
import java.util.HashMap
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec

class AlgorithmParametersConfiguration {

	Map<String, List<AlgorithmParameter>> cachedAlgorithmParameters 

	static AlgorithmParametersConfiguration algorithmParametersProvider = null;

	private new(){
		this.cachedAlgorithmParameters = new HashMap<String, List<AlgorithmParameter>>()
	}

	def static getInstance() {
		
		if(algorithmParametersProvider === null) {
			algorithmParametersProvider = new AlgorithmParametersConfiguration();
		}
		
		return algorithmParametersProvider
	}

	def List<AlgorithmParameter> get(AlgorithmSpec algorithm) {

		switch algorithm.name {
			case "NSGAII":
				getNSGAIIParameters(algorithm)
			default:
				throw new UnexpectedAlgorithmException(algorithm.name)
		}

	}

	private def List<AlgorithmParameter> getNSGAIIParameters(AlgorithmSpec algorithm) {

		if (this.cachedAlgorithmParameters.containsKey(algorithm.name)) {
			return this.cachedAlgorithmParameters.get(algorithm.name)
		}

		var parameters = getEvolutionaryParameters()
		parameters.add(new AlgorithmParameter("selection", ""))

		this.cachedAlgorithmParameters.put(algorithm.name, parameters)

		return parameters;
	}

	/**
	 * Evolutionary search parameters required by all the evolutionary algorithms.
	 */
	private def List<AlgorithmParameter> getEvolutionaryParameters() {

		var parameters = new ArrayList<AlgorithmParameter>()

		parameters.add(new AlgorithmParameter("variation"))
		parameters.add(new AlgorithmParameter("population"))
		parameters.add(new AlgorithmParameter("mutation.step", "fixed"))
		parameters.add(new AlgorithmParameter("mutation.strategy", "random"))
		return parameters
	}
}
