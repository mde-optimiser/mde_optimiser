package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang.RuleNameGenerator

class ChangeEdgeRuleCommand implements IRuleGenerationCommand {

	MetamodelWrapper metamodelWrapper;
	EClass node;
	EReference edge;
	
	RuleSpec ruleSpec;
	RepairSpec repairSpec;
	
	new(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec) {
		this.metamodelWrapper = metamodelWrapper;
		this.ruleSpec = ruleSpec;
		this.repairSpec = repairSpec;
	}
	
	new(EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec){
		this.metamodelWrapper = metamodelWrapper;
		this.node = node; 
		this.repairSpec = repairSpec;
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
			this.edge = repairSpec.edge
		}
		
		return this.edge;
	}
	
	override generate() {
		
		//Create module
		val module = HenshinFactory.eINSTANCE.createModule();
		
		//Set module name
		var ruleName = RuleNameGenerator.getRuleName(ruleSpec, repairSpec, this.metamodelWrapper.ruleType)
				
		//Set module name
		module.setName(ruleName)
		module.setDescription("Changes " + this.getEdge.name + " edge from " + this.getNode.name + " to " + this.getEdge.getEType.name);
		
		//Set module metamodels
		module.getImports().add(metamodelWrapper.getMetamodel)
		
		//TODO Test this case with a metamodel variant that has more than one container for the same classifier
		val classifierInfo = metamodelWrapper.metamodelAnalyser.getAllParentContext(this.getNode, true);
		
		for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {
			
			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));
			
			//Create a new rule in the module for each context container of the refined multiplicity node	
			for(var contextId = 0; contextId < context.size; contextId++){
				
				
				var Rule rule = null;
				
				if(this.getEdge.EOpposite === null){
					rule = Common.createBasicRule(module, this.getEdge, this.getNode, this.getEdge.EType, null, null, OperationType.CHANGE_REFERENCE);
				} else {
					rule = Common.createBasicRule(module, this.getEdge.EOpposite, this.getEdge.EType, this.getNode, null, null, OperationType.CHANGE_REFERENCE);
				}
				
				applyRuleNacConditions(rule);

				//Add rule to module for this context classifier
				module.getUnits().add(rule);
			}
		}
		
		return module;
	}
	
	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
	
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
}
