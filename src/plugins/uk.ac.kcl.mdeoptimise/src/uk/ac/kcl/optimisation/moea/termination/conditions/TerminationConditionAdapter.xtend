package uk.ac.kcl.optimisation.moea.termination.conditions

import uk.ac.kcl.mdeoptimise.TerminationConditionParameters
import org.moeaframework.core.TerminationCondition
import java.security.InvalidParameterException
import org.moeaframework.core.termination.CompoundTerminationCondition
import org.moeaframework.core.termination.MaxElapsedTime
import org.moeaframework.core.termination.MaxFunctionEvaluations
import uk.ac.kcl.mdeoptimise.OptimisationSpec

class TerminationConditionAdapter {

	TerminationCondition terminationCondition;
	TerminationConditionParameters terminantionConditionParameters;
	int populationSize;

	new(OptimisationSpec optimisation, TerminationConditionParameters terminationConditionParameters){
		this.terminantionConditionParameters = terminationConditionParameters;
		this.populationSize = getPopulationSize(optimisation);
	}

	//TODO this is not in the best place
	def getPopulationSize(OptimisationSpec optimisation){
			var population = optimisation.algorithmParameters.parameters.filter[parameter | parameter.name.equals("population")]
			
			if(!population.empty) {
				return population.head.value
			}
			
		return 0
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
			
			if(this.populationSize > 0) {
				return new MaxFunctionEvaluations(evolutions.head.value * this.populationSize) 
			} else {
				return new MaxFunctionEvaluations(evolutions.head.value) 
			}
		}
		
		null
	}
	
	
	private def TerminationCondition getTimeTerminationCondition(){
		
		var seconds = this.terminantionConditionParameters.parameters.filter[p | p.name.equals("time") ]
		
		if(!seconds.empty){
			return new MaxElapsedTime(seconds.head.value * 1000)	
		}
		
		null
	}
}