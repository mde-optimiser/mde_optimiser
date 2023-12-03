package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.repair;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface RepairStrategy {

  /**
   * Repair is assumed to only be performed on already generated offspring and the given solution is expected to be
   * altered in this process.
   * 
   * @param solution solution to repair
   */
  void repair(Solution solution);
}
