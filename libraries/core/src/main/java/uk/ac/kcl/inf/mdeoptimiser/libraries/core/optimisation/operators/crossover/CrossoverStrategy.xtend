package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import java.util.List

interface CrossoverStrategy {
	
	def List<Solution> breed(List<Solution> parents)
}
