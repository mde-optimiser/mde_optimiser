package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node

import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec

class DeleteNodeRuleCommand implements IRuleGenerationCommand {
	
	MetamodelWrapper refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	EClass node;
	RuleSpec ruleSpec;
	List<RepairSpec> repairSpecs;
	
	new(MetamodelWrapper refinedMetamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs) {
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
		this.ruleSpec = ruleSpec;
		this.repairSpecs = repairSpecs;
	}
	
	override generate() {
		
		//Create the Delete rule as an invesrse of the Create rule
		//TODO This assumes that there is only one rule in the Module
		var createNodeRuleCommand = new CreateNodeRuleCommand(node, refinedMetamodelWrapper, ruleSpec, repairSpecs);
		var deleteModule = createNodeRuleCommand.generate();
		
		//Create an inverse of the delete module
		var module = Common.createInverse(deleteModule, OperationType.CREATE);
	
		module.units.forEach[ rule | applyRuleNacConditions(rule as Rule)]
		
		return module
	}

	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
	
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}

}
