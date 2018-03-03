package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node

import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.configuration.GlobalConstants
import org.sidiff.serge.core.Common
import org.sidiff.serge.filter.ElementFilter
import org.sidiff.serge.generators.actions.RuleParameterGenerator
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpecType
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.mdeoptimiser.rulegen.lang.RuleNameGenerator

class CreateNodeRuleCommand implements IRuleGenerationCommand {
	
	MetamodelWrapper metamodelWrapper;
	EClass node;
	
	RuleSpec ruleSpec;
	List<RepairSpec> repairSpecs;
	
	new(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs) {
		this.metamodelWrapper = metamodelWrapper;
		this.ruleSpec = ruleSpec;
		this.repairSpecs = repairSpecs;
	}
	
	new(EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs){
		this.metamodelWrapper = metamodelWrapper;
		this.node = node; 
		this.repairSpecs = repairSpecs;
		this.ruleSpec = ruleSpec;
	}
	
	def EClass getNode(){
		
		if(this.node == null){
			this.node = metamodelWrapper.getNode(ruleSpec.getNode)	
		}
		
		return this.node;
	}
	
	override generate() {
		
		//Create module
		val module = HenshinFactory.eINSTANCE.createModule();
		
		var ruleName = RuleNameGenerator.getRuleName(ruleSpec, repairSpecs, this.metamodelWrapper.ruleType)
		
		//Set module name
		module.setName(ruleName)
		module.setDescription("Creates a node in all allowed metamodel contexts")

		//Set module metamodels
		//TODO fix this to use original metamodel
		module.getImports().add(metamodelWrapper.getMetamodel)
		
		//TODO Test this case with a metamodel variant that has more than one container for the same 
		//classifier
		val classifierInfo = metamodelWrapper.metamodelAnalyser.getAllParentContext(this.getNode(), true);
		
		for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {
			
			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));
			
			//Create a new rule in the module for each context container of the refined multiplicity node	
			for(var contextId = 0; contextId < context.size; contextId++){
				
				val rule = Common.createBasicRule(module, classifierInfo.keySet.get(contextReferenceId),
					this.getNode(), context.get(contextId), null, null, OperationType.CREATE
				);
				
				val newNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);
		
				//Add mandatory children for the new node created
				
				//TODO Perhaps should be moved somewhere else to make only one call
				//This loads the info about mandatories in the respective classes
				val childInfo = metamodelWrapper.metamodelAnalyser.getEClassifierInfo(this.getNode());
				childInfo.mandatoryNeighbours
				childInfo.mandatoryChildren
				
				val elementFilter = new ElementFilter(metamodelWrapper.metamodelAnalyser);
				
				// create mandatories if any
				if(childInfo.hasMandatories()) {
					Common.createMandatoryChildren(rule, childInfo, newNode, OperationType.CREATE, true, metamodelWrapper.metamodelAnalyser, elementFilter);			
					Common.createMandatoryNeighbours(rule, childInfo, newNode, OperationType.CREATE, true, elementFilter);
				}
				
				applyRepairOperations(rule, this.ruleSpec, this.repairSpecs)
				
				applyRuleNacConditions(rule);
				applyRuleParameters(rule);
				
				//Add rule to module for this context classifier
				module.getUnits().add(rule);
			}
		}
		
		return module;
	}
	
	//Fix the LB requirement for the created node by taking one node required for the LB from lb nodes of the same type
	private def void applyRepairOperations(Rule rule, RuleSpec ruleSpec, List<RepairSpec> repairSpecs){
		
		//Get the repair spec from a factory
		//TODO Move these to their own factory. This list will increase in the future.
		applyRepairOperationsLbSingle(rule, repairSpecs)
		applyRepairOperationsLbMany(rule, repairSpecs)	
	}
	
	
	private def void applyRepairOperationsLbMany(Rule rule, List<RepairSpec> repairSpecs){
		
		//Get the created node from the rule graphs
		val createdNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);
		
		repairSpecs.filter[repairSpec | repairSpec.repairSpecType == RepairSpecType.CREATE_LB_REPAIR_MANY
			|| repairSpec.repairSpecType == RepairSpecType.DELETE_LB_REPAIR_MANY
		].forEach[ repairSpec | 
						
			createdNode.getOutgoing(repairSpec.edge).forEach[ outgoingEdge |
				
				//Find existing target node
				var existingTargetNode =  HenshinRuleAnalysisUtilEx.findCorrespondingNodeInLHS(outgoingEdge.getTarget())
			
				//Create existing node A from which to take the existing target node
				val existingsourceNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
				val existingSourceNode = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, existingsourceNodeName, this.getNode());
		
				//Create a delete edge between existing node A and an existing node B
				HenshinRuleAnalysisUtilEx.createDeleteEdge(existingSourceNode.lhsNode, existingTargetNode, repairSpec.edge, rule);
			]
		]
	}
	
	private def void applyRepairOperationsLbSingle(Rule rule, List<RepairSpec> repairSpecs){
		
		
			//Get the created node from the rule graphs
			val createdNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);
			
			repairSpecs.filter[repairSpec | repairSpec.repairSpecType == RepairSpecType.DELETE_LB_REPAIR
				|| repairSpec.repairSpecType == RepairSpecType.CREATE_LB_REPAIR
			].forEach[ repairSpec | 
			
				//Create existing node A from which to take the existing target node
				val existingsourceNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
				val existingSourceNode = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, existingsourceNodeName, this.getNode());
			
				createdNode.getOutgoing(repairSpec.edge).forEach[ outgoingEdge |
					
					//Find existing target node
					var existingTargetNode =  HenshinRuleAnalysisUtilEx.findCorrespondingNodeInLHS(outgoingEdge.getTarget())
					
					//Create a delete edge between existing node A and an existing node B
					HenshinRuleAnalysisUtilEx.createDeleteEdge(existingSourceNode.lhsNode, existingTargetNode, repairSpec.edge, rule);
				]
			]	
	}
	
	
	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
	
		//new LowerBoundManyRepairCheckGenerator(rule).generate();
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
	
	//Create the rule parameters
	private def void applyRuleParameters(Rule rule) {
		new RuleParameterGenerator(rule).generate();
	}
}
	