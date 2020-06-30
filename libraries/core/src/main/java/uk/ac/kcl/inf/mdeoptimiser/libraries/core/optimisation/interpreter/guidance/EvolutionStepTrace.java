package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.emf.henshin.model.Unit;

public class EvolutionStepTrace {

  Integer algorithmStep;
  Integer stepSize;
  Unit appliedOperator;
  String appliedMatch;
  Map<Unit, String> appliedCPARepairs;

  // Want to log:
  // Current Step
  // Applied Operator
  // Applied Match
  // Applied CPA repairs
  // Step size

  public EvolutionStepTrace() {
    appliedCPARepairs = new HashMap<Unit, String>();
  }

  /**
   * Logs the current operator in the transformation chain of this model.
   *
   * @param operator
   */
  public void logOperator(Unit operator) {}
}
