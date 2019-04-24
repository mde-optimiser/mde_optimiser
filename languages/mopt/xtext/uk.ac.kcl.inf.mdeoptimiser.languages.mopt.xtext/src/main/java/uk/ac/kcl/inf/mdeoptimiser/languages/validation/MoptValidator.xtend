package uk.ac.kcl.inf.mdeoptimiser.languages.validation

import org.eclipse.xtext.validation.Check
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.TerminationConditionSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParametersConfiguration

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class MoptValidator extends AbstractMoptValidator {

	/**
	 * Check that there are no duplicate parameter in the algorithm specification
	 */
    @Check
    def void checkAlgorithmSpecHasNoDuplicateParameters(AlgorithmSpec algorithm) {
    	
    	val dupParameters = algorithm.parameters.groupBy[parameter  | parameter.name].filter[key, value | value.length > 1];
    	
    	dupParameters.forEach[p1, p2| 
    		error(String.format("Duplicate parameter found. Expecting '%s' to be specified only once. Specified %s times instead", p1, p2.length), algorithm, null,
				MoptValidatorIssues.DUPLICATE_PARAMETER_ENCOUNTERED);
    	]
    }
	
	/**
	 * Check that there are no duplicate parameters in the termination condition.
	 */
	@Check
    def void checkTerminationSpecHasNoDuplicateParameters(TerminationConditionSpec terminationCondition) {
    	
    	val dupParameters = terminationCondition.parameters.groupBy[parameter  | parameter.name].filter[key, value | value.length > 1];
    	
    	dupParameters.forEach[p1, p2| 
    		error(String.format("Duplicate parameter found. Expecting '%s' to be specified only once. Specified '%s' times instead", p1, p2.length), 
    			terminationCondition, null, MoptValidatorIssues.DUPLICATE_PARAMETER_ENCOUNTERED);
    	]
    }
    
    /**
     * Check that all the configured required algorithm parameters are specified.
     */
    @Check
    def void checkAlgorithmHasAllRequiredParameters(AlgorithmSpec algorithm){
    	
    	val requiredParameters = AlgorithmParametersConfiguration.getInstance().get(algorithm).filter[parameter | parameter.required == true]
    	
    	requiredParameters.forEach[ requiredParameter |
    		
    		if(algorithm.parameters.filter[ algorithmParameter | algorithmParameter.name.equals(requiredParameter.name)].empty){
    			error(String.format("Required parameter '%s' must be specified.", requiredParameter.name),
    				null, MoptValidatorIssues.REQUIRED_PARAMETER_NOT_FOUND)
    		}	
    	]
    }
    
    /**
     * Check that no unexpected algorithm parameters are specified.
     */
    @Check
    def void checkAlgorithmHasNoRedundantParameters(AlgorithmSpec algorithm) {
    	
    	val expectedParameters = AlgorithmParametersConfiguration.getInstance().get(algorithm);
    	
    	expectedParameters.forEach[ expectedParameter |
    		
    		if(algorithm.parameters.filter[ algorithmParameter | algorithmParameter.name.equals(expectedParameter)].empty){
    			warning(String.format("Unexpected parameter '%s' will be ignored.", expectedParameter.name),
    				null, MoptValidatorIssues.UNEXPECTED_PARAMETER_FOUND)
    		}
    	]	
    }
    
    //TODO Check that all the required hyperparameter spec parameters are specified
    
    //TODO Check that there are no duplicate hyperparameter spec parameters specified
    
    //TODO Check that there are no unexpected hyperparameter spec parameters specified

}
