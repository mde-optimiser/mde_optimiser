package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor

class CrossoverStrategyFactory {
	
	HenshinExecutor henshinExecutor;
	
	new(HenshinExecutor henshinExecutor) {
		this.henshinExecutor = henshinExecutor;
	}
	
	def CrossoverStrategy getStrategy(String strategy) {
		
		return null
	}
}