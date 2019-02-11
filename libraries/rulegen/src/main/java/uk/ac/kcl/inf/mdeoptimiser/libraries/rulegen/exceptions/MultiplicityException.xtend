package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions

import java.lang.Exception
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity

class MultiplicityException extends Exception {
	
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
    	super(String.format("%s Lower multiplicity given: %s. Upper multiplicity given: %s Original multiplicities lower: %s upper %s", 
    		message, lower, upper, originalLower, originalUpper))
    }

    new(Throwable cause) {
        super(cause);
    }

    new(String message, Throwable cause) {
        super(message, cause);
    }
}
