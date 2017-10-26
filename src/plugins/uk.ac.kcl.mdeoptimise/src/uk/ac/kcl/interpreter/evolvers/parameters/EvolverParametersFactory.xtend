package uk.ac.kcl.interpreter.evolvers.parameters

import java.util.List
import uk.ac.kcl.mdeoptimise.EvolverSpec


class EvolverParametersFactory implements IEvolverParametersFactory {
	
	private List<EvolverSpec> evolvers;
	
	//TODO Add an evolver spec adapter
	new(List<EvolverSpec> evolvers){
		this.evolvers = evolvers;
	}
	
	def Object getParameterValue(EvolverParameterAdapter guidanceFunctionAdapter);



}