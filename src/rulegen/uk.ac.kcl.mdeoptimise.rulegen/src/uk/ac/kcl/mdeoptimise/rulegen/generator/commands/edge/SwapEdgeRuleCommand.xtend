package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.henshin.model.Module
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.serge.configuration.GlobalConstants
import org.sidiff.serge.core.Common
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.mdeoptimiser.rulegen.lang.RuleNameGenerator

class SwapEdgeRuleCommand implements IRuleGenerationCommand {
	
	MetamodelWrapper metamodelWrapper;
	EClass node;
	EReference edge;
	
	RuleSpec ruleSpec;
	RepairSpec repairSpecs;
	
	new(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs) {
		this.metamodelWrapper = metamodelWrapper;
		this.ruleSpec = ruleSpec;
		this.repairSpecs = repairSpecs;
	}
	
	new(EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs){
		this.metamodelWrapper = metamodelWrapper;
		this.node = node; 
		this.repairSpecs = repairSpecs;
		this.ruleSpec = ruleSpec;
	}
	
	def EClass getNode(){
		
		if(this.node === null){
			this.node = metamodelWrapper.getNode(ruleSpec.getNode)	
		}
		
		return this.node;
	}
	
	def EReference getEdge(){
		
		if(this.edge === null){
			this.edge = repairSpecs.edge
		}
		
		return this.edge;
	}
	
	override generate() {
		
		//Create module
		val module = HenshinFactory.eINSTANCE.createModule();
		
		//Set module name
		var ruleName = RuleNameGenerator.getRuleName(ruleSpec, repairSpecs, this.metamodelWrapper.ruleType)
				
		//Set module name
		module.setName(ruleName)
		module.setDescription("Changes " + this.getEdge.name + " edge from " + this.getNode.name + " to " + this.getEdge.EType.name);
		
		//Set module metamodels
		module.getImports().add(metamodelWrapper.getMetamodel)
		
		//TODO Test this case with a metamodel variant that has more than one container for the same classifier
		val classifierInfo = metamodelWrapper.metamodelAnalyser.getAllParentContext(node, true);
		
		for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {
			
			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));
			
			//Create a new rule in the module for each context container of the refined multiplicity node	
			for(var contextId = 0; contextId < context.size; contextId++){
				
				var rule = generateBasicRule(module, this.getEdge, this.getNode, this.getEdge.EType as EClass);
				
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
					