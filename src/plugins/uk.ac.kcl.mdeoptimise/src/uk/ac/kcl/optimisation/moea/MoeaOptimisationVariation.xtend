package uk.ac.kcl.optimisation.moea

import org.moeaframework.core.Solution
import org.moeaframework.core.Variation
import uk.ac.kcl.optimisation.SolutionGenerator

class MoeaOptimisationVariation implements Variation {
	
	private SolutionGenerator solutionGenerator;
	
	new(SolutionGenerator solutionGenerator){
		this.solutionGenerator = solutionGenerator;
	}
	
	//USed to evolve a solution using the henshin evolvers
	override evolve(Solution[] parents) {

		var parentSolution = parents.head as MoeaOptimisationSolution;
		var newSolution = parentSolution.copy
			
		newSolution.setModel(solutionGenerator.evolveModel(parentSolution.model));
		newSolution.evolutionsCounter++
		//System.out.println("Evolved solution " + newSolution.evolutionsCounter)
		#[newSolution]

	}
	
	override getArity() {
		return 1;
	}
	
}