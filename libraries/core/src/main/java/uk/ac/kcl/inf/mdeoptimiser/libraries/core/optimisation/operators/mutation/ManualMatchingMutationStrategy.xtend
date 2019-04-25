package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import org.eclipse.emf.henshin.model.Rule
import org.eclipse.emf.henshin.interpreter.Match
import java.util.ArrayList
import java.util.Random
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.adaptation.MutationStepSizeStrategy

class ManualMatchingMutationStrategy implements MutationStrategy {

	HenshinExecutor henshinExecutor

	MutationStepSizeStrategy mutationStepSizeStrategy

	new(HenshinExecutor henshinExecutor, MutationStepSizeStrategy mutationStepSizeStrategy) {
		this.henshinExecutor = henshinExecutor
		this.mutationStepSizeStrategy = mutationStepSizeStrategy
	}

	// TODO Implement the step size strategy
	/**
	 * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
	 * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
	 * otherwise returns the result of the first randomly picked evolver that was applicable.
	 * 
	 * @param model solution candidate to be evolved
	 */
	override mutate(Solution model) {

		val candidateSolution = new Solution(model)

		// Get all matches
		val graph = new EGraphImpl(candidateSolution.getModel)
		val matchesView = henshinExecutor.mutationOperators.map [ evolver |
			henshinExecutor.engine.findMatches(evolver as Rule, graph, null).map [ m |
				new Pair<Rule, Match>(evolver as Rule, m)
			]
		].flatten

		val matches = new ArrayList<Pair<Rule, Match>>(matchesView.toList)

		if (!matches.empty) {
			// Randomly pick one match
			val matchToUse = matches.get(new Random().nextInt(matches.size))

			// Apply the match
			val runner = new RuleApplicationImpl(henshinExecutor.engine)
			runner.EGraph = graph
			runner.unit = matchToUse.key
			runner.partialMatch = matchToUse.value

			if (runner.execute(null)) {

				candidateSolution.updateModel(graph.roots.head, null)
				return candidateSolution
			}
		}

		return model
	}

}