package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation

import java.util.HashMap
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.model.Unit
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.RandomOperatorSelector
import java.util.Map

class RepetitiveOperatorMutationStrategy implements MutationStrategy {

	HenshinExecutor henshinExecutor
	MutationStepSizeStrategy mutationStepSizeStrategy
	RandomOperatorSelector operatorSelectionDecorator

	new(
		HenshinExecutor henshinExecutor,
		MutationStepSizeStrategy mutationStepSizeStrategy,
		RandomOperatorSelector operatorSelectionDectorator
	) {
		this.henshinExecutor = henshinExecutor
		this.mutationStepSizeStrategy = mutationStepSizeStrategy
		this.operatorSelectionDecorator = operatorSelectionDectorator
	}

	override mutate(Solution model) {
		val candidateSolution = new Solution(model)

		// TODO This will come from a repository object in the future
		val graph = new EGraphImpl(candidateSolution.model);

		val stepTransformations = applyOperators(candidateSolution, graph);

		candidateSolution.updateModel(graph.roots.head, stepTransformations);
		return candidateSolution;
	}

	/**
	 * Apply transformations according to the configured step size.
	 * @return a map of the ordered transformations applied in this step
	 */
	def Map<Integer, String> applyOperators(Solution candidateSolution, EGraphImpl egraph) {

		// Run the mutation for the selected number of steps
		val stepSize = this.mutationStepSizeStrategy.nextStepSize;
		var Unit operator = null;

		var stepTransformations = new HashMap<Integer, String>();

		for (var step = 1; step <= stepSize; step++) {

			do {

				if (operator === null) {
					operator = operatorSelectionDecorator.nextOperator
				}

				if (henshinExecutor.operatorApplied(operator, egraph, candidateSolution)) {
					stepTransformations.put(step, operator.name)
				} else {

					operator = null
				}

			} while (operator === null && operatorSelectionDecorator.hasUntriedOperators())

			operatorSelectionDecorator.flushTriedOperators
		}

		return stepTransformations;
	}
}
