package uk.ac.kcl.optimisation.moea.termination.conditions

import uk.ac.kcl.mdeoptimise.TerminationConditionParameters
import org.moeaframework.core.TerminationCondition
import java.security.InvalidParameterException
import org.moeaframework.core.termination.CompoundTerminationCondition
import org.moeaframework.core.termination.MaxElapsedTime
import org.moeaframework.core.termination.MaxFunctionEvaluations

class TerminationConditionAdapter {


	private TerminationCondition terminationCondition;
	private TerminationConditionParameters terminantionConditionParameters;

	new(TerminationConditionParameters terminationConditionParameters){
		this.terminantionConditionParameters = terminationConditionParameters;
	}

	
	def TerminationCondition getCondition(){
		
		if(this.terminationCondition === null){

			var evolutions =  getEvolutionsTerminationCondition()
			
			var time = getTimeTerminationCondition()
		
			if(evolutions !== null && time !== null) {
				
				this.terminationCondition = new CompoundTerminationCondition(time, evolutions);
				
			} else if(evolutions !== null){
				
				this.terminationCondition = new CompoundTerminationCondition(evolutions);
			} else if(time !== null){
				
				this.terminationCondition = new CompoundTerminationCondition(time);
			}
			
			if(terminantionConditionParameters.parameters.empty ){
				throw new InvalidParameterException("No termination condition parameters found.")
			}
			
		}
		
		
		return this.terminationCondition
		
	}

	private def TerminationCondition getEvolutionsTerminationCondition(){
		
		var evolutions = this.terminantionConditionParameters.parameters.filter[p | p.name.equals("evolutions") ]
		
		if(!evolutions.empty){
			return new MaxFunctionEvaluations(evolutions.head.value) 
		}
		
		null
	}
	
	
	private def TerminationCondition getTimeTerminationCondition(){
		
		var evolutions = this.terminantionConditionParameters.parameters.filter[p | p.name.equals("time") ]
		
		if(!evolutions.empty){
			return new MaxElapsedTime(evolutions.head.value * 1000)	
		}
		
		null
	}
}