package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.repair;

import java.util.LinkedList;

import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;

/**
 * Tries to applies all repair operators in the order of their definition. Each operator is applied as often as
 * possible. If an operator is not applicable the repair continues with the next operator. applicable but continues with
 * the next one.
 * 
 * @author S. John
 *
 */
public class SequentialRepairStrategy implements RepairStrategy {

  private HenshinExecutor executor;

  public SequentialRepairStrategy(HenshinExecutor executor) {
    this.executor = executor;
  }

  @Override
  public void repair(Solution solution) {
    var graph = new EGraphImpl(solution.getModel());
    var operators = executor.getRepairOperators();

    LinkedList<Unit> appliedOps = new LinkedList<>();
    for (Unit unit : operators) {
      while (executor.operatorApplied(unit, graph, solution)) {
        appliedOps.add(unit);
        System.out.println("SequentialRepairStrategy: repair applied '" + unit.getName() + "'");
      }
    }
    solution.getTransformationsChain().add(appliedOps);
  }
}
