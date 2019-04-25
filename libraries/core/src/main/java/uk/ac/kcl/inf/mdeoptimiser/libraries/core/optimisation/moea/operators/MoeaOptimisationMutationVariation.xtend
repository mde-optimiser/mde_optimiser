package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators

import org.moeaframework.core.Solution
import org.moeaframework.core.Variation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution

class MoeaOptimisationMutationVariation implements Variation {
	
	SolutionGenerator solutionGenerator;
	
	new(SolutionGenerator solutionGenerator){
		this.solutionGenerator = solutionGenerator;
	}
	
	//USed to evolve a solution using the henshin evolvers
	override evolve(Solution[] parents) {

		var parentSolution = parents.head as MoeaOptimisationSolution;
		var newSolution = parentSolution.copy
			
		newSolution.setModel(solutionGenerator.mutate(newSolution.model));
		//System.out.println("Evolved solution " + newSolution.evolutionsCounter)
		#[newSolution]

	}
	
	override getArity() {
		return 1;
	}
	
}