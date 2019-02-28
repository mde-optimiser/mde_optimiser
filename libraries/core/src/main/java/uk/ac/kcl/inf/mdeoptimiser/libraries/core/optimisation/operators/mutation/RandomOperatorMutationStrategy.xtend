package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation

import java.util.HashMap
import java.util.Random
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy

class RandomOperatorMutationStrategy implements MutationStrategy {

	HenshinExecutor henshinExecutor
	
	MutationStepSizeStrategy mutationStepSizeStrategy

	new(HenshinExecutor henshinExecutor, MutationStepSizeStrategy mutationStepSizeStrategy) {
		this.henshinExecutor = henshinExecutor
		this.mutationStepSizeStrategy = mutationStepSizeStrategy
	}

	override mutate(Solution model) {
		val candidateSolution = new Solution(model)

		val graph = new EGraphImpl(candidateSolution.getModel)

		val stepSize = this.mutationStepSizeStrategy.nextStepSize

		var operator = henshinExecutor.mutationOperators.get(new Random().nextInt(henshinExecutor.mutationOperators.size()))

		var stepTransformations = new HashMap<Integer, String>();
		
		// Run the mutation for the selected number of steps
		for (var step = 1; step <= stepSize; step++) {
			
			var operatorApplied = false;
			
			do {
				
				operator = henshinExecutor.mutationOperators.get(new Random().nextInt(henshinExecutor.mutationOperators.size()))
				
				if(henshinExecutor.operatorApplied(operator, graph, candidateSolution)) {
					stepTransformations.put(step, operator.name)
					operatorApplied = true;
				}
				
			} while(!operatorApplied)	
		}
		
		candidateSolution.updateModel(graph.roots.head, stepTransformations);
		return candidateSolution;
	}
}