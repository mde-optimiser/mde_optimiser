package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.engine;

import org.eclipse.emf.henshin.interpreter.Engine;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import at.ac.tuwien.big.momot.search.engine.MomotEngine;

public class HenshinEngineFactory {
	
	private SolverSpec solverSpec;
	private static Engine MOMOT_ENGINE;
	private static Engine MDEO_ENGINE;
	
	static {
		MOMOT_ENGINE = new MomotEngine();
		MDEO_ENGINE = new EngineImpl();
	}

	
	public HenshinEngineFactory(SolverSpec solverSpec){
		this.solverSpec = solverSpec;
	}
	
	/**
	 * If a different Henshin Engine is configured, initialise it from the factory.
	 * By default run the classic Henshin Engine.
	 */
	public Engine create() {
		
		String solverEngine = this.solverSpec.getSolverEngine();
		
		if(solverEngine == null) {
			solverEngine = "mdeo";
		}
		
		switch(solverEngine) {
			case "momot":
				return MOMOT_ENGINE;
			default:
				return MDEO_ENGINE;
		}
	}
}
