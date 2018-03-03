package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node

import java.util.List
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.AddEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.ChangeEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.SwapEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpecType
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.RemoveEdgeRuleCommand

class RuleGenerationCommandFactory {
	
	def IRuleGenerationCommand makeCommand(String repairSpecsType, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs) {
		
		switch(repairSpecsType) {
			case "CREATE":
				return new CreateNodeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs)
			case "DELETE":
				return new DeleteNodeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs)
			case "ADD":
					return getEdgeRepairSpecType(metamodelWrapper, ruleSpec, repairSpecs)
			case "REMOVE":
					return getEdgeRepairSpecType(metamodelWrapper, ruleSpec, repairSpecs)
						
					
		}
	}
	
	
	def IRuleGenerationCommand getEdgeRepairSpecType(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs){
		
		switch(repairSpecs.head.repairSpecType) {
			case RepairSpecType.ADD:
					return new AddEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs)
			case RepairSpecType.REMOVE:
					return new RemoveEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs)
			case RepairSpecType.CHANGE:
				return new ChangeEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs)
			case RepairSpecType.SWAP:
				return new SwapEdgeRuleCommand(metamodelWrapper, ruleSpec, repairSpecs)
		}
		
	}
	
}