package uk.ac.kcl.mdeoptimise.rulegen.generator.commands

import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.eclipse.emf.ecore.EReference
import org.sidiff.serge.configuration.GlobalConstants
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator

class AddEdgeRuleCommand implements IRuleGenerationCommand {
	
	Multiplicity multiplicity;
	EPackage refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	
	new(Multiplicity multiplicity, EPackage refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.multiplicity = multiplicity;
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
	}
	
	override generate() {
		
		//Create module
		val module = HenshinFactory.eINSTANCE.createModule();
		
		//Set module name
		module.setName("ADD_EdgeRules")
		module.setDescription("Adds to " + multiplicity.targetNode.name + " edge of type " + multiplicity.EReference.name)
		
		//Set module metamodels
		module.getImports().add(refinedMetamodelWrapper)
		
		//TODO Test this case with a metamodel variant that has more than one container for the same classifier
		val classifierInfo = metamodelAnalyser.getAllParentContext(multiplicity.targetNode, true);
		
		for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {
			
			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));
			
			//Create a new rule in the module for each context container of the refined multiplicity node	
			for(var contextId = 0; contextId < context.size; contextId++){
				
				val rule = createRule(multiplicity.targetNode as EClass, multiplicity.EReference, multiplicity.sourceNode as EClass)
				
				applyRuleNacConditions(rule)
				//Add rule to module for this context classifier
				module.getUnits().add(rule);
			}
		}
		
		return module;
	}
	
	//Taken in part from the SERGe codebase
	private def Rule createRule(EClass source, EReference outReference, EClass target) {
		
		var rule = HenshinFactory.eINSTANCE.createRule();
		rule.setActivated(true);
		rule.setName("addTo" + source.getName() + "_" + outReference.getName() + "_" + target.getName());
		rule.setDescription("Adds to " + source.getName() + "'s reference " + outReference.getName() + " the target " + target.getName());

		// Create preserved node for eClass
		var selectedNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.SEL, source);
		var newNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.NEWTGT, target);

		// Create <<create>> edge for new target for EReference and it's
		// EOpposite, if any
		HenshinRuleAnalysisUtilEx.createCreateEdge(selectedNodePair.getRhsNode(), newNodePair.getRhsNode(), outReference);
		
		return rule;
	}
	
	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
	
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
}