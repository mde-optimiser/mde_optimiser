package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

interface MutationStrategy {
		
	def Solution mutate(Solution model)
}
