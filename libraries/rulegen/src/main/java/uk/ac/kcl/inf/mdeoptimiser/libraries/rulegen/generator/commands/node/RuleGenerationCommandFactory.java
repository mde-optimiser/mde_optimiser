package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge.AddEdgeRuleCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge.ChangeEdgeRuleCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge.RemoveEdgeRuleCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge.SwapEdgeRuleCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class RuleGenerationCommandFactory {

  public List<IRuleGenerationCommand> makeCommand(
      String repairSpecsType,
      MetamodelWrapper metamodelWrapper,
      RuleSpec ruleSpec,
      List<RepairSpec> repairSpecs) {

    var ruleGenerationCommands = new ArrayList<IRuleGenerationCommand>();

    // TODO Refactored this to fix a bug, fix default check, change repairspectype to enum
    switch (repairSpecsType) {
      case "CREATE":
        ruleGenerationCommands.add(
            new CreateNodeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs));
        break;
      case "DELETE":
        ruleGenerationCommands.add(
            new DeleteNodeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs));
        break;
        // Must be Add / Remove edge cases
      default:
        {
          ruleGenerationCommands.addAll(
              repairSpecs.stream()
                  .map(repairSpec -> makeEdgeCommand(metamodelWrapper, ruleSpec, repairSpec))
                  .collect(Collectors.toList()));
        }
    }

    return ruleGenerationCommands;
  }

  public IRuleGenerationCommand makeEdgeCommand(
      MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec) {

    switch (repairSpec.getRepairSpecType()) {
      case ADD:
        return new AddEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec);
      case REMOVE:
        return new RemoveEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec);
      case CHANGE:
        return new ChangeEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec);
      case SWAP:
        return new SwapEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec);
      default:
        {
          throw new IllegalArgumentException("Invalid rule repair spec type given");
        }
    }
  }
}
