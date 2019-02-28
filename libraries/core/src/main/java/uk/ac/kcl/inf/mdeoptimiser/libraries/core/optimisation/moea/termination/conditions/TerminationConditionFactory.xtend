package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions

import java.util.List
import org.moeaframework.core.termination.MaxFunctionEvaluations
import org.moeaframework.core.termination.MaxElapsedTime
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec

class TerminationConditionFactory {
	
	def static getTerminationCondition(Parameter currentParameter, SolverSpec optimisationSpec) {
		
		switch currentParameter.name {
			
			case 'time': {
				return new MaxElapsedTime(Integer.parseInt(currentParameter.value.numeric) * 1000)	
			}
			
			case 'evolutions': {
				
				val populationSize = getPopulationSize(optimisationSpec)
				
				if(populationSize > 0) {
					return new MaxFunctionEvaluations(Integer.parseInt(currentParameter.value.numeric) * populationSize) 
				} else {
					return new MaxFunctionEvaluations(Integer.parseInt(currentParameter.value.numeric)
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
	
	private	def static getPopulationSize(SolverSpec optimisation){
			var population = optimisation.algorithm.parameters.filter[parameter | parameter.name.equals("population")]
			
			if(!population.empty) {
				return Integer.parseInt(population.head.value.numeric)
			}
			
		return 0
	}
}
