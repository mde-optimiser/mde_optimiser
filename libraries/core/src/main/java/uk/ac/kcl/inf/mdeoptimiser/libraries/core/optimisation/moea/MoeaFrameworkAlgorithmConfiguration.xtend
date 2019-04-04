package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec
import org.moeaframework.core.TerminationCondition
import java.util.Properties
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions.TerminationConditionAdapter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator

//TODO This should become a template for all the types of parameters supported
class MoeaFrameworkAlgorithmConfiguration {
	
	int populationSize;
	
	Properties properties;
	
	SolverSpec solverSpec;
	
	SolutionGenerator solutionGenerator
	
	TerminationCondition terminationCondition;
	
	new(SolverSpec solverSpec, SolutionGenerator solutionGenerator) {
		this.solverSpec = solverSpec;
		this.solutionGenerator = solutionGenerator;
	}
	
	
	def Properties getProperties(){
		if(this.properties === null) {
			loadProperties();
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
		
		properties.put("populationSize", Integer.parseInt(solverSpec.algorithm.parameters.filter[p| p.name.equals("population")].head.value.numeric))
		//properties.put("maxEvolutions", optimisationSpec.algorithmPopulation * optimisationSpec.algorithmEvolutions)
		properties.put("solutionGenerator", solutionGenerator)
		//Crossover and mutation or mutation only
		properties.put("variationType", solverSpec.algorithm.parameters.filter[p| p.name.equals("variation")].head)
		properties.put("terminationCondition", new TerminationConditionAdapter(solverSpec).condition)
		
		return properties
	}
	
	
	def TerminationCondition getTerminationCondition() {
		
		if(this.terminationCondition === null) {
			
			this.terminationCondition = new TerminationConditionAdapter(solverSpec).condition
		}
		
		return this.terminationCondition;
	}
	
}
