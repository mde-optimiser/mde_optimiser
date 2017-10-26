package uk.ac.kcl.interpreter.evolvers.parameters

import uk.ac.kcl.mdeoptimise.EvolverParameter
import org.eclipse.xtend.lib.annotations.Accessors

class EvolverParameterAdapter {

	@Accessors String name;
	@Accessors String value;

	new(EvolverParameter evolverParameterSpec) {
		name = evolverParameterSpec.name;
		value = evolverParameterSpec.value;
	}
	
}