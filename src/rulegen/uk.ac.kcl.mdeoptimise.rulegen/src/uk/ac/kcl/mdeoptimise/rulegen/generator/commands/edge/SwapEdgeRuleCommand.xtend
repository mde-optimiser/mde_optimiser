package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.henshin.model.Module
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.serge.configuration.GlobalConstants
import org.sidiff.serge.core.Common
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import uk.ac.kcl.mdeoptimiser.rulegen.lang.NamingConstants

class SwapEdgeRuleCommand implements IRuleGenerationCommand {
	
	Multiplicity multiplicity;
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	EClass node;
	EReference edge;

	new(EClass node, EReference edge, RefinedMetamodelWrapper refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.node = node;
		this.edge = edge;
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
	}
	
	override generate() {
		
		var moduleName = NamingConstants.SWAP_prefix + multiplicity.getEReference.name + "_edge_rules_in_all_contexts";
		//Create module
		val module = HenshinFactory.eINSTANCE.createModule();
		
		//Set module name
		module.setName(moduleName)
		module.setDescription("Changes " + multiplicity.getEReference.name + " edge from " + multiplicity.sourceNode.name + " to " + multiplicity.targetNode.name);
		
		//Set module metamodels
		module.getImports().add(refinedMetamodelWrapper.refinedMetamodel)
		
		//TODO Test this case with a metamodel variant that has more than one container for the same classifier
		val classifierInfo = metamodelAnalyser.getAllParentContext(multiplicity.sourceNode, true);
		
		for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {
			
			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));
			
			//Create a new rule in the module for each context container of the refined multiplicity node	
			for(var contextId = 0; contextId < context.size; contextId++){
				
				var rule = generateBasicRule(module, multiplicity.getEReference, multiplicity.sourceNode as EClass, multiplicity.targetNode as EClass);
				
				//Add rule to module for this context classifier
				module.getUnits().add(rule);
			}
		}
		
		return module;
	}
	
	
	private def Rule generateBasicRule(Module module, EReference reference, EClass sourceNode, EClass targetNode){
			
			var rule = HenshinFactory.eINSTANCE.createRule();
			rule.setActivated(true);
			rule.setName("swap" + sourceNode.getName() + "_" + reference.getName() + targetNode.getName());
			rule.setDescription("Swap the EReference " + reference.getName());
			module.getUnits().add(rule);

			// Node A
			var selectedNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.SEL, sourceNode);
			
			//Node A*
			val secondSourceNodeName = Common.getFreeNodeName(GlobalConstants.SEL, rule);
			var secondSourceNodePair  = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, secondSourceNodeName, sourceNode)
			
			//Node B
			val oldNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
			var oldNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, oldNodeName, targetNode);
			
			//Node B*
			val newNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
			var newNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, newNodeName, targetNode);

			//Remove edge between node A and B
			HenshinRuleAnalysisUtilEx.createDeleteEdge(selectedNodePair.getLhsNode(), oldNodePair.getLhsNode(), reference, rule);
			
			//Create an edge between A and B*
			HenshinRuleAnalysisUtilEx.createCreateEdge(selectedNodePair.getRhsNode(), newNodePair.getRhsNode(), reference);
			
			//Remove edge between node A* and node B*
			HenshinRuleAnalysisUtilEx.createDeleteEdge(secondSourceNodePair.getLhsNode(), newNodePair.getLhsNode(), reference, rule);
			
			//Crete edge between nonde A* and node B
			HenshinRuleAnalysisUtilEx.createCreateEdge(secondSourceNodePair.getRhsNode(), oldNodePair.getRhsNode(), reference);
			
			return rule
	}
}
					