package uk.ac.kcl.interpreter.objectives.java

import java.io.File
import java.util.HashMap
import uk.ac.kcl.interpreter.IFitnessFunction
import uk.ac.kcl.interpreter.IObjectiveEngine

class JavaObjectiveEngine implements IObjectiveEngine {
	
	HashMap<String, IFitnessFunction> functions
	
	new(){
		functions = new HashMap<String, IFitnessFunction>();
	}
	
	override loadObjective(String type, String name, String objectiveString) {
		
		if(functions.containsKey(objectiveString)){
			functions.get(objectiveString)
		}
		
		val javaObjectiveFunction = new File(objectiveString)
		
		if(javaObjectiveFunction.exists() && javaObjectiveFunction.isFile()){
			
			var function = Class.forName(objectiveString).newInstance() as IFitnessFunction
		
			functions.put(objectiveString, function)
			
			return function
		}		
	}
	
}