package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.functions

import java.util.List
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import java.io.InvalidObjectException
import com.mifmif.common.regex.Generex
import org.apache.commons.lang3.math.NumberUtils
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class RandomEvolverParameter implements IEvolverParametersFunction {
	
	Generex randomParameterGenerator
	
	new(String parameter){
		
		if(checkRegexParameter(parameter)) {
			randomParameterGenerator = new Generex(parameter);	
		}
	}
	
	override sample(List<Solution> model) {
	
		var randomParameter = randomParameterGenerator.random();	

		if(NumberUtils.isNumber(randomParameter)){
			return NumberUtils.createNumber(randomParameter)	
		}
		
		return randomParameter
	}
	
	def boolean checkRegexParameter(String parameter) {
		
		try {
			
			Pattern.compile(parameter)
			return true
			
		} catch (PatternSyntaxException exception) {
			throw new InvalidObjectException("Invalid Random function regex parameter: " + parameter)
        }
  	}	
}
