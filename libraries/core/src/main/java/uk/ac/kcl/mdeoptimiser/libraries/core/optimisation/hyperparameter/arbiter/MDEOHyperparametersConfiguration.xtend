package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import java.io.Serializable
import org.moeaframework.core.TerminationCondition
import org.moeaframework.core.termination.MaxFunctionEvaluations

class MDEOHyperparametersConfiguration implements Serializable {
	public Integer populationSize;
	public Integer evolutions;

	new(Integer populationSize, Integer evolutions) {
		this.populationSize = populationSize;
		this.evolutions = evolutions;
	}
	
	def TerminationCondition getEvolutionsTerminationCondition(){
		return new MaxFunctionEvaluations(populationSize * evolutions)
	}
}
