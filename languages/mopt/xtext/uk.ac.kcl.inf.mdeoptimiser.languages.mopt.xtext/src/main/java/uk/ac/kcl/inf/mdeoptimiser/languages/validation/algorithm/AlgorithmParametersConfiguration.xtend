package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm

import java.util.List
import java.util.ArrayList
import java.util.Map
import java.util.HashMap
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec

/**
 * This class configures the parameters required by each algorithm.
 * The required and optional parameter are also configured here.
 */
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

		this.cachedAlgorithmParameters.put(algorithm.name, parameters)

		return parameters;
	}

	/**
	 * Evolutionary search parameters required by all the evolutionary algorithms.
	 * The configuration of the algorithm parameters is as follows:
	 * 
	 * variation: the type of solution variation, namely mutation only or 
	 * genetic, consisting of crossover followed by mutation.
	 * 
	 * population: the population size of the algorithm
	 * 
	 * mutation: the mutation prefix, identifies parameters that are specific only to 
	 * the mutation parameters
	 * 
	 * 	mutation.step: defines the mutation step size strategy (fixed, numeric, strategy based)
	 * 	mutation.strategy: defines the strategy for applying mutations in a single step (randomly, repetitively)
	 * 
	 * The default parameter values are set here, for usage in cases where they are not specified in the DSL.
	 */
	private def List<AlgorithmParameter> getEvolutionaryParameters() {

		var parameters = new ArrayList<AlgorithmParameter>()

		parameters.add(new AlgorithmParameter("variation"))
		parameters.add(new AlgorithmParameter("population"))
		parameters.add(new AlgorithmParameter("mutation.step", "fixed"))
		parameters.add(new AlgorithmParameter("mutation.strategy", "random"))
		
		parameters.add(new AlgorithmParameter("mutation.selection", ""))
		return parameters
	}
}
