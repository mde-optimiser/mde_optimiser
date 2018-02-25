package uk.ac.kcl.mdeoptimise.rulegen.generator.commands

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import java.io.InvalidObjectException
import org.sidiff.common.henshin.HenshinModuleAnalysis
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.serge.configuration.GlobalConstants
import org.eclipse.emf.ecore.EClass

class DeleteNodeLBRepairManyRuleCommand implements IRuleGenerationCommand {
	
	Multiplicity multiplicity;
	EPackage refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	
	new(Multiplicity multiplicity, EPackage refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.multiplicity = multiplicity;
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
	}
	
	override generate() {
		
		//If this is a unidirectional multiplicity refinement
		if(multiplicity.EReference.EOpposite == null){
		//	throw new InvalidObjectException("Unexpected eopposite reference value in DeleteNodeLBRepairRuleCommand");
		}
		
		//Create the Delete rule as an invesrse of the Create rule
		//TODO This assumes that there is only one rule in the Module
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicity, refinedMetamodelWrapper, metamodelAnalyser);
		var deleteModule = createNodeRuleCommand.generate();
		
		//Create an inverse of the delete module
		var module = Common.createInverse(deleteModule, OperationType.CREATE);
		
		var deleteRule = HenshinModuleAnalysis.getAllRules(module).get(0);
		
		module.setName(module.getName()+"_lb_repair_many");
		
		//Apply the repair operations for this rule
		//applyRepairOperations(deleteRule)
		
		module.units.forEach[
			rule | applyRuleNacConditions(rule as Rule)
		]
		
		return module
	}


	//Delete node A and move the B node connected through an edge of type f to 
	//another node of type A with a NAC forbidding more than one nodes of type B connected to it through and edge of type f;
	private def void applyRepairOperations(Rule rule){

		rule.name = rule.name + "_lb_repair"

		//Get the deleted node from the rule graphs
		var deletedNode = HenshinRuleAnalysisUtilEx.getLHSMinusRHSNodes(rule).get(0);
		
		//Add the refinement operation here
		val existingTargetNode = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.NEWTGT, multiplicity.targetNode as EClass);
	
		//Create existing node A
		val existingsourceNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
		val existingSourceNode = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, existingsourceNodeName, multiplicity.sourceNode as EClass);
		
		//Create an edge between existing node A and an existing node B
		HenshinRuleAnalysisUtilEx.createCreateEdge(existingSourceNode.rhsNode, existingTargetNode.rhsNode, multiplicity.EReference)
		
		//Delete an edge between deleted node A and existing node B
		HenshinRuleAnalysisUtilEx.createDeleteEdge(deletedNode, existingTargetNode.lhsNode, multiplicity.EReference, rule)
	}

	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}

}