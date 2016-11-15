package uk.ac.kcl.optimisation.moea

import org.moeaframework.core.spi.OperatorProvider
import org.moeaframework.core.Problem
import java.util.Properties

class MoeaOptimisationVariationsProvider extends OperatorProvider {
	
	override getMutationHint(Problem problem) {
		val solution = problem.newSolution();
		
		for (var i = 0; i < problem.getNumberOfVariables(); i++) {
			if (solution.getVariable(i) instanceof MoeaOptimisationVariable) {
				return "evolutionMutation"
			}
		}
		
		null
	}
	
	override getVariation(String name, Properties properties, Problem problem) {
		
		val moeaOptimisationProblem = problem as MoeaOptimisationProblem;
		
		if (name.equalsIgnoreCase("evolutionMutation")) {
			return new MoeaOptimisationVariation(moeaOptimisationProblem.solutionGenerator);
		}
		
		return null;
	}
	
	override getVariationHint(Problem problem) {
		return getMutationHint(problem)
	}
	
}