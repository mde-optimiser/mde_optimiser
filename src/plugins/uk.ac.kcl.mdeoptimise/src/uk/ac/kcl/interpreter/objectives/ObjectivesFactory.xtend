package uk.ac.kcl.interpreter.objectives

import java.io.InvalidObjectException
import uk.ac.kcl.interpreter.objectives.java.JavaObjectiveEngine
import uk.ac.kcl.interpreter.objectives.ocl.OclObjectiveEngine
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec

class ObjectivesFactory implements IObjectiveEngine {
	
	private IObjectiveEngine javaObjectiveEngine
	private IObjectiveEngine oclObjectiveEngine
	
	new(){
		javaObjectiveEngine = new JavaObjectiveEngine()
		oclObjectiveEngine = new OclObjectiveEngine();
	}
	
	override loadObjective(ObjectiveInterpreterSpec objectiveInterpreter) {
		
		switch(objectiveInterpreter.getObjectiveType) {
			case "java":
				return javaObjectiveEngine.loadObjective(objectiveInterpreter)
			case "ocl":
				return oclObjectiveEngine.loadObjective(objectiveInterpreter)
			default:
			
				//TODO perhaps this is not the best exception to use here. Implement our own?
				throw new InvalidObjectException("Invalid objective type: " + objectiveInterpreter.getObjectiveType)
		}	
	}
}