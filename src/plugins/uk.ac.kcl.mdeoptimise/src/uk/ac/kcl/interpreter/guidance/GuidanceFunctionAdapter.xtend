package uk.ac.kcl.interpreter.guidance

import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec
import uk.ac.kcl.mdeoptimise.ConstraintInterpreterSpec

class GuidanceFunctionAdapter {
	
	 public String functionName
	 public String functionTendency
	 public String functionType
	 public String functionSpec
	
	new(ObjectiveInterpreterSpec objectiveInterpreterSpec){
		functionName = objectiveInterpreterSpec.objectiveName
		functionTendency = objectiveInterpreterSpec.objectiveTendency
		functionType = objectiveInterpreterSpec.objectiveType
		functionSpec = objectiveInterpreterSpec.objectiveSpec
	}
	
	new(ConstraintInterpreterSpec constraintInterpreterSpec){
		functionName = constraintInterpreterSpec.constraintName
		functionType = constraintInterpreterSpec.constraintType
		functionSpec = constraintInterpreterSpec.constraintSpec
	}
	
	def double getNumericalTendency(){
		
		/**
		 * Moea is designed to minimise objectives. To maximise an objective, it should be negated.
		 * Output maximised objectives will be displayed with their negative values.
		 */
		if (this.functionTendency != null && this.functionTendency.equals("minimise")) -1 else 1
	}
	
	def boolean isObjectiveFunction() {
		return this.functionTendency != null
	}
}