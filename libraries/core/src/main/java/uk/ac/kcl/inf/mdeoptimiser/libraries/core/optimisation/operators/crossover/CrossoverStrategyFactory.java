package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;

public class CrossoverStrategyFactory {

  HenshinExecutor henshinExecutor;

  public CrossoverStrategyFactory(HenshinExecutor henshinExecutor) {
    this.henshinExecutor = henshinExecutor;
  }

  public CrossoverStrategy getStrategy(String strategy) {
    return null;
  }
}
