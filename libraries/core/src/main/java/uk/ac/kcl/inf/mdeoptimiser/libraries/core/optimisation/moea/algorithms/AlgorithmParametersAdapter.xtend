package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms

import java.util.Map
import java.util.HashMap
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmParameters

class AlgorithmParametersAdapter {
	
	Map<Object, Object> parameters 
	String algorithmName;
	
	new(String algorithmName, AlgorithmParameters algorithmParameters){
		this.algorithmName = algorithmName
	}
	
	def Map<Object, Object> algorithmParametersFactory(String algorithmName, AlgorithmParameters algorithParameters){
		
		this.parameters = new HashMap<Object, Object>();		
		this.parameters.put("population", algorithParameters.parameters.filter[p | p.name.equals("population")].head)
		
		return this.parameters;
	}
	
}