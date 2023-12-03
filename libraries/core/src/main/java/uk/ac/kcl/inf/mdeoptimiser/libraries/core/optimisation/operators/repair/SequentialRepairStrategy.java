package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.repair;

import java.util.LinkedList;

import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.Unit;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;

/**
 * Tries to apply all repair operators in the order of their definition. Each operator is applied as often as
 * possible. If an operator is not applicable the repair continues with the next operator.
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
      int count = 0;
      while (executor.operatorApplied(unit, graph, solution)) {
        appliedOps.add(unit);
        count++;
      }
      System.out.println("SequentialRepairStrategy: applied '" + unit.getName() + "' " + count + " times.");
    }
    solution.getTransformationsChain().add(appliedOps);
  }
}
