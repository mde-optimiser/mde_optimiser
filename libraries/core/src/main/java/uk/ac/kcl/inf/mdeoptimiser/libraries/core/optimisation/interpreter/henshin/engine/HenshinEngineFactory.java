package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.engine;

import at.ac.tuwien.big.momot.search.engine.MomotEngine;
import efmconfiguration.engine.ConfigurationEngine;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;

public class HenshinEngineFactory {

  private SolverSpec solverSpec;
  private static Engine MOMOT_ENGINE;
  private static Engine MDEO_ENGINE;
  private static Engine EFMCONFIG_ENGINE;

  static {
    MOMOT_ENGINE = new MomotEngine();
    MDEO_ENGINE = new EngineImpl();
    EFMCONFIG_ENGINE = new ConfigurationEngine();
  }

  public HenshinEngineFactory(SolverSpec solverSpec) {
    this.solverSpec = solverSpec;
  }

  /**
   * If a different Henshin Engine is configured, initialise it from the factory. By default run the
   * classic Henshin Engine.
   */
  public Engine create() {

    String solverEngine = this.solverSpec.getSolverEngine();

    if (solverEngine == null) {
      System.out.println("Using default Henshin engine.");
      solverEngine = "mdeo";
    }

    switch (solverEngine) {
      case "efmconfig":
        return EFMCONFIG_ENGINE;
      case "momot":
        return MOMOT_ENGINE;
      default:
        return MDEO_ENGINE;
    }
  }
}
