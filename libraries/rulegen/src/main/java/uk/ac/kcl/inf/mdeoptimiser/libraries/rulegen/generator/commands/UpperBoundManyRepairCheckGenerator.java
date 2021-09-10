package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands;

import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Rule;
import org.sidiff.common.henshin.HenshinConditionUtil;
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx.FormulaCombineOperator;
import org.sidiff.common.henshin.NodePair;
import org.sidiff.serge.generators.conditions.AbstractBoundCheckGenerator;

public class UpperBoundManyRepairCheckGenerator extends AbstractBoundCheckGenerator {

  public UpperBoundManyRepairCheckGenerator(Rule editRule) {
    super(editRule);
  }

  public void generate() {
    for (NodePair preservedNode : src2Out.keySet()) {
      Set<EReference> outTypes = src2Out.get(preservedNode);
      for (EReference outType : outTypes) {
        int ub = outType.getUpperBound();
        int balance = getBalance(preservedNode, outType);
        int preserved = getPreservedCount(preservedNode, outType);

        if (ub > 0 && balance > 0) {
          assert (ub >= preserved + balance);
          int offset = (ub - (preserved + balance)) - balance * -1;
          if (offset > 0) {
            Map.Entry<String, Formula> formula =
                createBoundCheck(offset, preservedNode.getLhsNode(), outType, true);
            HenshinConditionUtil.addFormula(formula, editRule.getLhs(), FormulaCombineOperator.AND);
          }
        }
      }
    }
  }
}
