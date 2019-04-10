package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea

import java.util.Properties
import org.moeaframework.core.TerminationCondition
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions.TerminationConditionAdapter
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration

//TODO This should become a template for all the types of parameters supported
class MoeaFrameworkAlgorithmConfiguration {
	
	int populationSize;
	
	Properties properties;
	
	SolverSpec solverSpec;
	
	SolutionGenerator solutionGenerator
	
	TerminationCondition terminationCondition;
	
	MDEOHyperparametersConfiguration hyperparameters
	
	new(SolverSpec solverSpec, SolutionGenerator solutionGenerator) {
		this.solverSpec = solverSpec;
		this.solutionGenerator = solutionGenerator;
	}
	
	def Properties getProperties(){
		if(this.properties === null) {
			this.properties = loadProperties();
		}
		
		return this.properties;
	}
	
	def SolverSpec getSolverSpec(){
		return this.solverSpec;
	}
	
	def SolutionGenerator getSolutionGenerator(){
		return this.solutionGenerator;
	}
	
	def loadProperties() {
		
		var properties = new Properties()
		if(this.hyperparameters !== null) {
			properties.put("populationSize", this.hyperparameters.populationSize)			
		} else {
			properties.put("populationSize", Integer.parseInt(solverSpec.algorithm.parameters.filter[p| p.name.equals("population")].head.value.numeric))
		}
		//properties.put("maxEvolutions", optimisationSpec.algorithmPopulation * optimisationSpec.algorithmEvolutions)
		properties.put("solutionGenerator", solutionGenerator)
		//Crossover and mutation or mutation only
		properties.put("variationType", solverSpec.algorithm.parameters.filter[p| p.name.equals("variation")].head)
		//properties.put("terminationCondition", new TerminationConditionAdapter(solverSpec).condition)
		
		return properties
	}
	
	
	def TerminationCondition getTerminationCondition() {
		
		if(this.terminationCondition === null) {
			if(this.hyperparameters === null ) {
				this.terminationCondition = new TerminationConditionAdapter(solverSpec).condition
			}	 else {
				this.terminationCondition = new TerminationConditionAdapter(hyperparameters).condition
			}
			
		}
		
		return this.terminationCondition;
	}
	
	def updateParameters(MDEOHyperparametersConfiguration configuration) {
		
		this.hyperparameters = configuration;
	}

}
