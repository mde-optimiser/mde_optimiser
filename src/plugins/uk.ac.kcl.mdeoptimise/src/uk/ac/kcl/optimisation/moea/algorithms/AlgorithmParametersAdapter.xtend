package uk.ac.kcl.optimisation.moea.algorithms

import uk.ac.kcl.mdeoptimise.AlgorithmParameters
import java.util.Map
import java.util.HashMap

class AlgorithmParametersAdapter {
	
	private Map<Object, Object> parameters 
	private String algorithmName;
	
	new(String algorithmName, AlgorithmParameters algorithmParameters){
		this.algorithmName = algorithmName
	}
	
	def Map<Object, Object> algorithmParametersFactory(String algorithmName, AlgorithmParameters algorithParameters){
		
		this.parameters = new HashMap<Object, Object>();
				
		var expectedParameters = #["population"]
		
		this.parameters.put("population", algorithParameters.parameters.filter[p | p.name.equals("population")].head)
		
		return this.parameters;
		
	}
	
}