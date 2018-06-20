package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node

import java.util.ArrayList
import java.util.List
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.AddEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.ChangeEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.RemoveEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.SwapEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpecType
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec

class RuleGenerationCommandFactory {

	
	def List<IRuleGenerationCommand> makeCommand(String repairSpecsType, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs) {
		
		val ruleGenerationCommands = new ArrayList<IRuleGenerationCommand>();
		
		//TODO Refactored this to fix a bug, fix default check, change repairspectype to enum
		switch(repairSpecsType) {
			case "CREATE":
				ruleGenerationCommands.add(new CreateNodeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs))
			case "DELETE":
				ruleGenerationCommands.add(new DeleteNodeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs))
			
			//Must be Add / Remove edge cases
			default: {
				ruleGenerationCommands.addAll(repairSpecs.fold(new ArrayList<IRuleGenerationCommand>, [
					list, repairSpec | list.add(makeEdgeCommand(metamodelWrapper, ruleSpec, repairSpec)) return list
				]))
			}
		}
		
		return ruleGenerationCommands
	}
	
	def IRuleGenerationCommand makeEdgeCommand(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec) {
		
		switch(repairSpec.repairSpecType) {
			case RepairSpecType.ADD:
					return new AddEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec)
			case RepairSpecType.REMOVE:
					return new RemoveEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec)
			case RepairSpecType.CHANGE:
					return new ChangeEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec)
			case RepairSpecType.SWAP:
					return new SwapEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpec)
				default: {
					throw new IllegalArgumentException("Invalid rule repair spec type given")
				}
		}
	}
}