package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms

import java.util.Map
import java.util.HashMap
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParametersConfiguration
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterValue
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParameter
import java.util.List
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParameterException

class AlgorithmParametersAdapter {
	
	Map<Object, Object> parameters
	
	def Map<Object, Object> algorithmParametersFactory(AlgorithmSpec algorithm){
		
		this.parameters = new HashMap<Object, Object>();

		val algorithmParameters = AlgorithmParametersConfiguration.instance.get(algorithm);
		
		algorithmParameters.forEach[ algorithmParameter | 
			
			if(algorithmParameter.required) {
				this.parameters.put(algorithmParameter, algorithm.parameters.getAlgorithmParameterValue(algorithmParameter))
			}	
		]
		
		return this.parameters;
	}

	/**
	 * Read the specified algorithm parameters based on the expected parameters specified in the configuration.
	 * @param list of user specified parameters in the DSL
	 * @param configured parameter to extract the value for from the specified list
	 */	
	private def ParameterValue getAlgorithmParameterValue(List<Parameter> specifiedParameters, AlgorithmParameter configuredParameter) {
		
		if(configuredParameter.required) {
			
			val parameters = specifiedParameters.filter[specifiedParameter | 
				specifiedParameter.name.equals(configuredParameter.name)
			]
			
			//This parameter is missing or it appears multiple times
			if(parameters.empty || parameters.length > 1) {
				throw new AlgorithmParameterException(configuredParameter.name)
			}
			
			return parameters.head.value
		}
		
		val parameters = specifiedParameters.filter[specifiedParameter | 
				specifiedParameter.name.equals(configuredParameter.name)
			]
		
		//This optional parameter is specified
		if(!parameters.empty) {
			return parameters.head.value;
		}
	}
	
}