package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.engine

import org.eclipse.emf.henshin.interpreter.Engine
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl
import at.ac.tuwien.big.momot.search.engine.MomotEngine

class HenshinEngineFactory {
	
	SolverSpec solverSpec
	
	new(SolverSpec solverSpec){
		this.solverSpec = solverSpec;
	}
	
	/**
	 * If a different Henshin Engine is configured, initialise it from the factory.
	 * By default run the classic Henshin Engine.
	 */
	def Engine create() {
		
		switch this.solverSpec.solverEngine {
			case "momot":
				return new MomotEngine
			default:
				return new EngineImpl
		}
	}
}