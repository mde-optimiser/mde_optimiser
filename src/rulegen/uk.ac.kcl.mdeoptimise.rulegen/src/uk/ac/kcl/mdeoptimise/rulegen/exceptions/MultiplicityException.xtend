package uk.ac.kcl.mdeoptimise.rulegen.exceptions

import java.lang.Exception
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity

class MultiplicityException extends Exception {
	
	String defaultMessage = "Invalid multiplicity values given. "; 
	
	new(String message) {
        super(message);
    }
    
    new(Multiplicity multiplicity){
    	this(multiplicity.lower, multiplicity.upper);
    }
    
    new(Integer lower, Integer upper) {
    	super("Invalid multiplicity values given. " +
    		"Lower multiplicity given: " + lower + "Upper multiplicity given: " + upper
    	)
    }
    
    new(String message, Integer lower, Integer upper) {
    	super(message + "Lower multiplicity given: " + lower + "Upper multiplicity given: " + upper)
    }

    new(String message, Integer lower, Integer upper, Integer originalLower, Integer originalUpper) {
    	super(message + "Lower multiplicity given: " 
    		+ lower + ". Upper multiplicity given: " + upper
    		+ " Original multiplicities lower: " + originalLower
    		+ " upper: " + originalUpper 
    	)
    }

    new(Throwable cause) {
        super(cause);
    }

    new(String message, Throwable cause) {
        super(message, cause);
    }
    
    def String getInvalidMultiplicityValuesString(Integer lower, Integer upper){
    	
    }
}