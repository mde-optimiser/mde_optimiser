package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators

import org.moeaframework.core.Solution
import org.moeaframework.core.Variation
import java.util.ArrayList
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution

class MoeaOptimisationCrossoverVariation implements Variation {
	
	SolutionGenerator solutionGenerator;
	
	new(SolutionGenerator solutionGenerator){
		this.solutionGenerator = solutionGenerator;
	}
	
	//USed to evolve a solution using the henshin evolvers
	override evolve(Solution[] parents) {
	
		val solutions = new ArrayList<MoeaOptimisationSolution>();	
		val parentModels = new ArrayList<uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution>();
		
		for(Solution parent : parents){
			val solution = (parent as MoeaOptimisationSolution).copy 
			solutions.add(solution)
			parentModels.add(solution.model)
		}
		
		val children = solutionGenerator.crossover(parentModels)
	
		for(var i = 0; i < 2; i++){
			solutions.get(i).model = children.get(i);
		}
		
		//Some XTend WTF?
		#[solutions.get(0), solutions.get(1)]
	}
	
	/**
	 * The number of solutions to be supplied to the evolve method.
	 * For the crossover operator we require a number of two parents.
	 */
	override getArity() {
		return 2;
	}
	
}