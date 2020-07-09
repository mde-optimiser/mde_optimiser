package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair;

import java.util.LinkedList;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class SkipOperatorRepair implements OperatorRepairStrategy {

  /**
   * Always return false and skip this operator as being impossible to repair
   *
   * @param operator
   * @param graph
   * @param candidateSolution
   * @param stepTransformations
   * @return
   */
  @Override
  public boolean applyRepair(
      Unit operator,
      EGraphImpl graph,
      Solution candidateSolution,
      LinkedList<Unit> stepTransformations) {
    return false;
  }
}
