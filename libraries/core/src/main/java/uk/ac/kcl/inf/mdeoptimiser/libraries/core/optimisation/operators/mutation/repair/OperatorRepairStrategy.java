package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair;

import java.util.LinkedList;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface OperatorRepairStrategy {

  boolean applyRepair(
      Unit operator,
      EGraphImpl graph,
      Solution candidateSolution,
      LinkedList<Unit> stepTransformations);
}
