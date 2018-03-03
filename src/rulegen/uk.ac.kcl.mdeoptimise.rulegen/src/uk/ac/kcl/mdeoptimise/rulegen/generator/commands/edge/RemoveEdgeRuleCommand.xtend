package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper

class RemoveEdgeRuleCommand implements IRuleGenerationCommand {
	
	Multiplicity multiplicity;
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	EClass node;
	EReference edge;
	
	new(EClass node, EReference edge, RefinedMetamodelWrapper refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.node = node;
		this.edge = edge;
		this.multiplicity = multiplicity;
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
	}
	
	override generate() {
		
		var createEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
		
		var module = Common.createInverse(createEdgeRuleCommand.generate(), OperationType.ADD);
		
		module.units.forEach[
			rule | applyRuleNacConditions(rule as Rule)
		]
		
		return module
	}

	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
	
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
}
