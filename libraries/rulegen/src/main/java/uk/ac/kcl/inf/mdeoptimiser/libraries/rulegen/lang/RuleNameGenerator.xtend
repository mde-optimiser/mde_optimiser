package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang

import java.util.List
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec

class RuleNameGenerator {
	
	def static String getRuleName(RuleSpec ruleSpec, List<RepairSpec> repairSpecs, String ruleType) {
		return String.format("%s_%s_%s_%s", ruleType, ruleSpec.actions, ruleSpec.getNode, combineRepairSpecNames(repairSpecs))
	}
	
	def static String getRuleName(RuleSpec ruleSpec, RepairSpec repairSpec, String ruleType) {
		return String.format("%s_%s_%s_%s", ruleType, ruleSpec.actions, ruleSpec.getNode, combineRepairSpecNames(#[repairSpec]))
	}
	
	private static def String combineRepairSpecNames(List<RepairSpec> repairSpecs) {
		
		val combinedRepairSpecs = new StringBuilder()
		val repairSpecNameFormat = "%s_edge_%s"
		
		repairSpecs.forEach[repairSpec, index | 
			
			if(index > 0 && index < repairSpecs.size){
				combinedRepairSpecs.append("_")
			}
			
			combinedRepairSpecs.append(String.format(repairSpecNameFormat, repairSpec.edge.name, repairSpec.repairSpecType.toString))
		]
		
		return combinedRepairSpecs.toString();
	}
}
