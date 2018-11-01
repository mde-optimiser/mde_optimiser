package uk.ac.kcl.optimisation.moea.termination.conditions

import org.moeaframework.core.TerminationCondition
import java.security.InvalidParameterException
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import java.util.ArrayList
import org.moeaframework.core.termination.CompoundTerminationCondition

class TerminationConditionAdapter {

	TerminationCondition terminationCondition;
	OptimisationSpec optimisationSpec

	new(OptimisationSpec optimisationSpec){
		this.optimisationSpec = optimisationSpec
	}
	
	def TerminationCondition getCondition(){
		
		if(this.terminationCondition === null){

			if(optimisationSpec.terminationCondition.parameters.empty ){
				throw new InvalidParameterException("No termination condition parameters found.")
			}

			val terminationConditions = new ArrayList<TerminationCondition>();

			this.optimisationSpec.terminationCondition.parameters.forEach[parameter |
				
				val terminationCondition = TerminationConditionFactory.getTerminationCondition(parameter, optimisationSpec)
				
				if(terminationCondition !== null)
					terminationConditions.add(terminationCondition)
				
			]

			if(terminationConditions.empty){
				throw new InvalidParameterException("No known termination condition parameters found.")
			}

			this.terminationCondition = new CompoundTerminationCondition(terminationConditions)

		}
		
		return this.terminationCondition
	}
}