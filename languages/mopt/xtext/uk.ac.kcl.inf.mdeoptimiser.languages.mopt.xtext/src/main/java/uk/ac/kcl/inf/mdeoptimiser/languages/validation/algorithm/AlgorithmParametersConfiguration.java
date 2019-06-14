package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;

/**
 * This class configures the parameters required by each algorithm.
 * The required and optional parameter are also configured here.
 */
public class AlgorithmParametersConfiguration {

	Map<String, List<AlgorithmParameter>> cachedAlgorithmParameters;

	static AlgorithmParametersConfiguration algorithmParametersProvider = null;

	private AlgorithmParametersConfiguration(){
		this.cachedAlgorithmParameters = new HashMap<String, List<AlgorithmParameter>>();
	}

	public static AlgorithmParametersConfiguration getInstance() {
		
		if(algorithmParametersProvider == null) {
			algorithmParametersProvider = new AlgorithmParametersConfiguration();
		}
		
		return algorithmParametersProvider;
	}

	public List<AlgorithmParameter> get(AlgorithmSpec algorithm) {

		switch (algorithm.getName()) {
			case "NSGAII":
				  return getNSGAIIParameters(algorithm);
			default:
				throw new UnexpectedAlgorithmException(algorithm.getName());
		}
	}

	private List<AlgorithmParameter> getNSGAIIParameters(AlgorithmSpec algorithm) {

		if (this.cachedAlgorithmParameters.containsKey(algorithm.getName())) {
			return this.cachedAlgorithmParameters.get(algorithm.getName());
		}

		var parameters = getEvolutionaryParameters();

		this.cachedAlgorithmParameters.put(algorithm.getName(), parameters);

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
	private List<AlgorithmParameter> getEvolutionaryParameters() {

		var parameters = new ArrayList<AlgorithmParameter>();

		parameters.add(new AlgorithmParameter("variation"));
		parameters.add(new AlgorithmParameter("population"));
		parameters.add(new AlgorithmParameter("mutation.step", "fixed"));
		parameters.add(new AlgorithmParameter("mutation.strategy", "random"));
		
		parameters.add(new AlgorithmParameter("mutation.selection", ""));
		return parameters;
	}
}
