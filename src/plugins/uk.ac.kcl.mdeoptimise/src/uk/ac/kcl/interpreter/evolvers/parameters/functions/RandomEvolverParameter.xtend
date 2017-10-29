package uk.ac.kcl.interpreter.evolvers.parameters.functions

import uk.ac.kcl.interpreter.evolvers.parameters.IEvolverParametersFunction
import org.eclipse.emf.ecore.EObject
import java.util.List
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import java.io.InvalidObjectException
import com.mifmif.common.regex.Generex

class RandomEvolverParameter implements IEvolverParametersFunction {
	
	private Generex randomParameterGenerator
	
	new(String parameter){
		
		if(checkRegexParameter(parameter)) {
			randomParameterGenerator = new Generex(parameter);	
		}
	}
	
	override sample(List<EObject> model) {
	
		var randomParameter = randomParameterGenerator.random();	
		println("Returning random parameter value: " + randomParameter)
		
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