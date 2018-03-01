package uk.ac.kcl.mdeoptimise.rulegen.generator.commands

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.filter.ElementFilter
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import org.sidiff.serge.configuration.GlobalConstants
import org.eclipse.emf.ecore.EClass
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import java.util.List
import org.eclipse.emf.ecore.EReference
import java.util.ArrayList

class CreateNodeIterativeRepairManyRuleCommand implements IRuleGenerationCommand {
	
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	EClass node;

	new(String node, RefinedMetamodelWrapper refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
		this.node = refinedMetamodelWrapper.getNode(node); 
	}
	
	override generate() {
		
		//Create module
		val module = HenshinFactory.eINSTANCE.createModule();
		
		//Set module name
		module.setName("CREATE_Node_" + node.name + "_Rules_Iterative_Repair")
		module.setDescription("Creates a node and generates an iterative repair for all outgoing edges.")

		//Set module metamodels
		module.getImports().add(refinedMetamodelWrapper.refinedMetamodel)
		
		//TODO Test this case with a metamodel variant that has more than one container for the same 
		//classifier
		val classifierInfo = metamodelAnalyser.getAllParentContext(node, true);
		
		for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {
			
			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));
			
			//Create a new rule in the module for each context container of the refined multiplicity node	
			for(var contextId = 0; contextId < context.size; contextId++){
				
				val rule = Common.createBasicRule(module, classifierInfo.keySet.get(contextReferenceId),
					node, context.get(contextId), null, null, OperationType.CREATE
				);
				
				val newNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);
		
				//Add mandatory children for the new node created
				
				//TODO Perhaps should be moved somewhere else to make only one call
				//This loads the info about mandatories in the respective classes
				val childInfo = this.metamodelAnalyser.getEClassifierInfo(node);
				childInfo.mandatoryNeighbours
				childInfo.mandatoryChildren
				
				val elementFilter = new ElementFilter(metamodelAnalyser);
				
				// create mandatories if any
				if(childInfo.hasMandatories()) {
					Common.createMandatoryChildren(rule, childInfo, newNode, OperationType.CREATE, true, metamodelAnalyser, elementFilter);			
					Common.createMandatoryNeighbours(rule, childInfo, newNode, OperationType.CREATE, true, elementFilter);
				}
				
				conditionalIterativeRepairGenerator(rule)
				
				applyRuleNacConditions(rule);
				//Add rule to module for this context classifier
				module.getUnits().add(rule);
			}
		}
		
		return module;
	}
	
	//loop through the edges and for each apply the required repairs lb single, or multiple
	private def void conditionalIterativeRepairGenerator(Rule rule){
		
		var bidirectionalReferences = refinedMetamodelWrapper.getBidirectionalReferences(node);
		
		val validReferences = new ArrayList<EReference>();
		
		bidirectionalReferences.forEach[reference | 
			
			if(reference.EOpposite.lowerBound > 0 && reference.EOpposite.upperBound > 0){
				validReferences.add(reference);
			}
		]
		
		if(!validReferences.empty){
			applyRepairOperations(rule, validReferences);	
		}
	}
	
	private def void applyRepairOperations(Rule rule, List<EReference> edges){
		
		
		rule.name = rule.name + "_lb_repair"
		
		//Get the created node from the rule graphs
		val createdNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);
			
		edges.forEach[edge | 
			createdNode.getOutgoing(edge).forEach[ outgoingEdge |
				
				//Find existing target node
				var existingTargetNode =  HenshinRuleAnalysisUtilEx.findCorrespondingNodeInLHS(outgoingEdge.getTarget())
			
				//Create existing node A from which to take the existing target node
				val existingsourceNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
				val existingSourceNode = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, existingsourceNodeName, this.node);
		
				//Create a delete edge between existing node A and an existing node B
				HenshinRuleAnalysisUtilEx.createDeleteEdge(existingSourceNode.lhsNode, existingTargetNode, edge, rule);
			]
		]
		
	}
	
	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
		
		new LowerBoundManyRepairCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
	
}