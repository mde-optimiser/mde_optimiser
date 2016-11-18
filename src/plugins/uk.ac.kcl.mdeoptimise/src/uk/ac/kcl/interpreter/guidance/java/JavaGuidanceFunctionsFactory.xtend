package uk.ac.kcl.interpreter.guidance.java

import java.util.HashMap
import uk.ac.kcl.interpreter.IGuidanceFunction
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.interpreter.guidance.IGuidanceFunctionsFactory

class JavaGuidanceFunctionsFactory implements IGuidanceFunctionsFactory {
	
	/**
	 * Cache functions
	 */
	HashMap<String, IGuidanceFunction> functions
	
	new(){
		functions = new HashMap<String, IGuidanceFunction>();
	}
	
	/**
	 * Loads a function from a given Java class file present on the classpath.
	 * If this is not the first invocation of this function then a cached instance
	 * of the objective java class will be returned, otherwise a new instance will be
	 * created, cached and returned.
	 * 
	 * @return instance of the fitness function described in the objective intereter
	 */
	override loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter) {
		
		//Is there is a key for the given function path cached, return it
		if(functions.containsKey(guidanceFunctionAdapter.functionSpec)){
			functions.get(guidanceFunctionAdapter.functionSpec)
		}

		try {
			
			var function = Class.forName(guidanceFunctionAdapter.functionSpec).newInstance() as IGuidanceFunction
		
			functions.put(guidanceFunctionAdapter.functionSpec, function)
			
			return function
			
		} catch( ClassNotFoundException exception) {
			//TODO logger
			throw new ClassNotFoundException("Invalid objective class path: " + guidanceFunctionAdapter.functionSpec);
		}	
	}
	
}