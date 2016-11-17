package uk.ac.kcl.interpreter.guidance

import java.io.InvalidObjectException
import uk.ac.kcl.interpreter.guidance.ocl.OclGuidanceFunctionsFactory
import uk.ac.kcl.interpreter.guidance.java.JavaGuidanceFunctionsFactory

class GuidanceFunctionsFactory implements IGuidanceFunctionsFactory {
	
	private IGuidanceFunctionsFactory javaObjectiveEngine
	private IGuidanceFunctionsFactory oclObjectiveEngine
	
	new(){
		javaObjectiveEngine = new JavaGuidanceFunctionsFactory()
		oclObjectiveEngine = new OclGuidanceFunctionsFactory();
	}
	
	override loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter) {
		switch(guidanceFunctionAdapter.functionType) {
			case "java":
				return javaObjectiveEngine.loadFunction(guidanceFunctionAdapter)
			case "ocl":
				return oclObjectiveEngine.loadFunction(guidanceFunctionAdapter)
			default:		
				//TODO perhaps this is not the best exception to use here. Implement our own?
				throw new InvalidObjectException("Invalid objective type: " + guidanceFunctionAdapter.functionType)
		}	
	}
}