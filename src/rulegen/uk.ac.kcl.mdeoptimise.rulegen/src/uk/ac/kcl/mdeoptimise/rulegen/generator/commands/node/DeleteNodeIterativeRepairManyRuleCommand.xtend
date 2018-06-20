package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.generators.actions.RuleParameterGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.LowerBoundManyRepairCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper

class DeleteNodeIterativeRepairManyRuleCommand implements IRuleGenerationCommand {
	
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	EClass node;

	new(EClass node, RefinedMetamodelWrapper refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
		this.node = node; 
	}
	
	override generate() {
		
		var createNodeRuleCommand = new CreateNodeIterativeRepairManyRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser);
		var deleteModule = createNodeRuleCommand.generate();
		
		//Create an inverse of the delete module
		var module = Common.createInverse(deleteModule, OperationType.CREATE);
				
		//Apply the repair operations for this rule	
		module.units.forEach[
			rule | 
			
			applyRuleNacConditions(rule as Rule)
			applyRuleParameters(rule as Rule)
		]
		
		return module
	}
	
	
	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
		
		new LowerBoundManyRepairCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
	
	//Create the rule parameters
	private def void applyRuleParameters(Rule rule) {
		new RuleParameterGenerator(rule).generate();
	}
	
}
			