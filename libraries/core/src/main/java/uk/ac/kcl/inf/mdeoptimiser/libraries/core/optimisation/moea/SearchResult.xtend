package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea

import org.moeaframework.Instrumenter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationProblem

class SearchResult {
	
	Instrumenter instrumenter
		
	MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration
	
	MoeaOptimisationProblem moeaProblem
	
	new(MoeaFrameworkAlgorithmConfiguration moeaFrameworkAlgorithmConfiguration, Instrumenter instrumenter){
		this.moeaFrameworkAlgorithmConfiguration = moeaFrameworkAlgorithmConfiguration;
		this.instrumenter = instrumenter;
	}
	
	/**
	 * Get an instance of the current results problem.
	 * 
	 * @return problem instance for these results.
	 */
	def getProblem(){
		if(this.moeaProblem === null) {
			this.moeaProblem = new MoeaOptimisationProblem(this.moeaFrameworkAlgorithmConfiguration.solutionGenerator);
		}		
		
		return this.moeaProblem;
	}
	
	/**
	 * Get an instance of the problem accumulator containing all the collected search information.
	 * 
	 * @return accumulator instance for the current algorithm rum
	 */
	def getAccumulator(){
		return this.instrumenter.lastAccumulator
	}
	
	/**
	 * Get an instance of the Optimisation instance used to run the current batch.
	 * 
	 * @return optimisation instance
	 */
	def getOptimisationSpec(){
		return this.moeaFrameworkAlgorithmConfiguration.solutionGenerator.optimisationModel;
	}
}