package uk.ac.kcl.optimisation.moea

import org.moeaframework.core.Solution
import org.moeaframework.core.Variation
import uk.ac.kcl.optimisation.SolutionGenerator
import org.eclipse.emf.ecore.EObject
import java.util.ArrayList

class MoeaOptimisationCrossoverVariation implements Variation {
	
	private SolutionGenerator solutionGenerator;
	
	new(SolutionGenerator solutionGenerator){
		this.solutionGenerator = solutionGenerator;
	}
	
	//USed to evolve a solution using the henshin evolvers
	override evolve(Solution[] parents) {

//		var parentSolution = parents.head as MoeaOptimisationSolution;
//		var newSolution = parentSolution.copy
	
		val solutions = new ArrayList<MoeaOptimisationSolution>();	
		val parentModels = new ArrayList<EObject>();
		
		for(Solution parent : parents){
			val solution = (parent as MoeaOptimisationSolution).copy 
			solutions.add(solution)
			parentModels.add(solution.model)
		}
		
		val children = solutionGenerator.crossover(parentModels)
	
		
		//println("Children:" + children.size)
		//println("Parents:" + parents.size)
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