package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.engine;

import at.ac.tuwien.big.momot.search.engine.MomotEngine;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;

public class HenshinEngineFactory {

  SolverSpec solverSpec;

  public HenshinEngineFactory(SolverSpec solverSpec) {
    this.solverSpec = solverSpec;
  }

  /**
   * If a different Henshin Engine is configured, initialise it from the factory. By default run the
   * classic Henshin Engine.
   */
  public Engine create() {

    if (this.solverSpec.getSolverEngine() == null) {
      return new EngineImpl();
    }

    switch (this.solverSpec.getSolverEngine()) {
      case "momot":
        return new MomotEngine();
      default:
        return new EngineImpl();
    }
  }
}
