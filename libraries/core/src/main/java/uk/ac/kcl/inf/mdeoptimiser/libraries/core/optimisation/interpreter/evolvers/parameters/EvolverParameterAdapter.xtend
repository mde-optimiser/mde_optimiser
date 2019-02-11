package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.EvolverParameter

class EvolverParameterAdapter {

	public String name;
	public String function;
	public String parameter;

	new(EvolverParameter evolverParameterSpec) {
		name = evolverParameterSpec.name;
		
		if(evolverParameterSpec.function !== null){
			function = evolverParameterSpec.function.name;
			parameter = evolverParameterSpec.function.parameter;	
		} else {
			function = evolverParameterSpec.customFunction;
		}
	}
}
