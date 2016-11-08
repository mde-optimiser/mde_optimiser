package uk.ac.kcl.interpreter.objectives.java

import java.io.File
import java.util.HashMap
import uk.ac.kcl.interpreter.IFitnessFunction
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec
import uk.ac.kcl.interpreter.objectives.IObjectiveEngine
import org.eclipse.emf.ecore.resource.ResourceSet

class JavaObjectiveEngine implements IObjectiveEngine {
	
	/**
	 * Cache functions
	 */
	HashMap<String, IFitnessFunction> functions
	
	new(){
		functions = new HashMap<String, IFitnessFunction>();
	}
	
	override loadObjective(ObjectiveInterpreterSpec objectiveInterpreter) {
		
		if(functions.containsKey(objectiveInterpreter.getObjectiveSpec())){
			functions.get(objectiveInterpreter.getObjectiveSpec())
		}

		try {
			
			var function = Class.forName(objectiveInterpreter.getObjectiveSpec).newInstance() as IFitnessFunction
		
			functions.put(objectiveInterpreter.getObjectiveSpec, function)
			
			return function
			
		} catch( ClassNotFoundException exception) {
			//TODO logger
			throw new ClassNotFoundException("Invalid objective class path: " + objectiveInterpreter.getObjectiveSpec);
		}	
	}
	
}