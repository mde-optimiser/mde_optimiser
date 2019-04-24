package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation

import java.util.HashMap
import java.util.Map
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelector
import org.eclipse.emf.henshin.model.Unit

class RandomOperatorMutationStrategy implements MutationStrategy {

	HenshinExecutor henshinExecutor

	MutationStepSizeStrategy mutationStepSizeStrategy
	RandomOperatorSelector operatorSelectionDecorator;

	new(HenshinExecutor henshinExecutor, MutationStepSizeStrategy mutationStepSizeStrategy,
		RandomOperatorSelector randomOperatorSelector) {
		this.henshinExecutor = henshinExecutor
		this.mutationStepSizeStrategy = mutationStepSizeStrategy
		this.operatorSelectionDecorator = randomOperatorSelector
	}

	override mutate(Solution model) {
		val candidateSolution = new Solution(model)

		val graph = new EGraphImpl(candidateSolution.getModel)

		var stepTransformations = applyOperators(candidateSolution, graph);

		candidateSolution.updateModel(graph.roots.head, stepTransformations);
		return candidateSolution;
	}

	/**
	 * Apply transformations according to the configured step size.
	 * @return a map of the ordered transformations applied in this step
	 */
	def Map<Integer, String> applyOperators(Solution candidateSolution, EGraphImpl egraph) {

		val stepSize = this.mutationStepSizeStrategy.nextStepSize

		var stepTransformations = new HashMap<Integer, String>();

		// Run the mutation for the selected number of steps
		for (var step = 1; step <= stepSize; step++) {

			var Unit operator = null;
			var operatorApplied = false;

			do {
				operator = this.operatorSelectionDecorator.nextOperator

				if (operator !== null) {
					if (henshinExecutor.operatorApplied(operator, egraph, candidateSolution)) {
						stepTransformations.put(step, operator.name)
						operatorApplied = true;
						operator = null;
					}
				}

			} while (!operatorApplied && operatorSelectionDecorator.hasUntriedOperators())

			operatorSelectionDecorator.flushTriedOperators
		}

		return stepTransformations;
	}

}
