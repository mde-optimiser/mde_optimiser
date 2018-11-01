package uk.ac.kcl.optimisation.moea.termination.conditions

import uk.ac.kcl.mdeoptimise.Parameter
import java.util.List
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import org.moeaframework.core.termination.MaxFunctionEvaluations
import org.moeaframework.core.termination.MaxElapsedTime

class TerminationConditionFactory {
	
	def static getTerminationCondition(Parameter currentParameter, OptimisationSpec optimisationSpec) {
		
		switch currentParameter.name {
			
			case 'time': {
				return new MaxElapsedTime(Integer.parseInt(currentParameter.value) * 1000)	
			}
			
			case 'evolutions': {
				
				val populationSize = getPopulationSize(optimisationSpec)
				
				if(populationSize > 0) {
					return new MaxFunctionEvaluations(Integer.parseInt(currentParameter.value) * populationSize) 
				} else {
					return new MaxFunctionEvaluations(Integer.parseInt(currentParameter.value)
					) 
				}
			}
			
			//This parameter has an optional dependency on iterations, alternatively it falls back to 3
			case 'delta': {
		
				return new DeltaTerminationCondition(currentParameter,
					getTerminationConditionParameter("iterations", optimisationSpec.terminationCondition.parameters)
				);
			}
			
			default: return null
		}
		
	}
	
	
	private def static Parameter getTerminationConditionParameter(String parameterKey, List<Parameter> allParameters) {
		
		var parameter = allParameters.filter[p | p.name.equals(parameterKey) ]
		
		if(parameter.length === 1){
			return parameter.head
		}
		
		return null
	}
	
	private	def static getPopulationSize(OptimisationSpec optimisation){
			var population = optimisation.algorithmParameters.parameters.filter[parameter | parameter.name.equals("population")]
			
			if(!population.empty) {
				return Integer.parseInt(population.head.value)
			}
			
		return 0
	}
}