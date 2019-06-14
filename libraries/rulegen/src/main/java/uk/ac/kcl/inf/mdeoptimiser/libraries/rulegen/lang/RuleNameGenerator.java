package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang;

import java.util.List;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;

public class RuleNameGenerator {

  public static String getRuleName(RuleSpec ruleSpec, List<RepairSpec> repairSpecs, String ruleType) {
		return String.format("%s_%s_%s_%s", ruleType, ruleSpec.getActions(), ruleSpec.getNode(), combineRepairSpecNames(repairSpecs));
	}

  public static String getRuleName(RuleSpec ruleSpec, RepairSpec repairSpec, String ruleType) {
		return String.format("%s_%s_%s_%s", ruleType, ruleSpec.getActions(), ruleSpec.getNode(), combineRepairSpecNames(List.of(repairSpec)));
	}
	
	private static String combineRepairSpecNames(List<RepairSpec> repairSpecs) {
		
		var combinedRepairSpecs = new StringBuilder();
		var repairSpecNameFormat = "%s_edge_%s";
		
		repairSpecs.forEach((repairSpec) -> {

      if (repairSpecs.indexOf(repairSpec) > 0 &&  repairSpecs.indexOf(repairSpec) < repairSpecs.size() - 1) {
        combinedRepairSpecs.append("_");
      }

      combinedRepairSpecs.append(String.format(repairSpecNameFormat, repairSpec.getEdge().getName(), repairSpec.getRepairSpecType()));
    });
		
		return combinedRepairSpecs.toString();
	}
}
