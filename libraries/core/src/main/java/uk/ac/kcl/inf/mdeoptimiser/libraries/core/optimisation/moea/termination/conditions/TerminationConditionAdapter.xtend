package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions

import org.moeaframework.core.TerminationCondition
import java.security.InvalidParameterException
import java.util.ArrayList
import org.moeaframework.core.termination.CompoundTerminationCondition
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec

class TerminationConditionAdapter {

	TerminationCondition terminationCondition;
	SolverSpec solverSpec

	new(SolverSpec solverSpec){
		this.solverSpec = solverSpec
	}
	
	def TerminationCondition getCondition(){
		
		if(this.terminationCondition === null){

			if(solverSpec.terminationCondition.parameters.empty ){
				throw new InvalidParameterException("No termination condition parameters found.")
			}

			val terminationConditions = new ArrayList<TerminationCondition>();

			this.solverSpec.terminationCondition.parameters.forEach[parameter |
				
				val terminationCondition = TerminationConditionFactory.getTerminationCondition(parameter, solverSpec)
				
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
