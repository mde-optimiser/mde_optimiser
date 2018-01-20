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

class DeleteNodeRuleCommand implements IRuleGenerationCommand {
	
	Multiplicity multiplicity;
	EPackage refinedMetamodelWrapper;
	EClassifierInfoManagement metamodelAnalyser;
	
	new(Multiplicity multiplicity, EPackage refinedMetamodelWrapper, EClassifierInfoManagement metamodelAnalyser){
		this.multiplicity = multiplicity;
		this.refinedMetamodelWrapper = refinedMetamodelWrapper;
		this.metamodelAnalyser = metamodelAnalyser;
	}
	
	override generate() {
		
		//Create the Delete rule as an invesrse of the Create rule
		//TODO This assumes that there is only one rule in the Module
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicity, refinedMetamodelWrapper, metamodelAnalyser);
		var deleteModule = createNodeRuleCommand.generate();
		
		//Create an inverse of the delete module
		var module = Common.createInverse(deleteModule, OperationType.CREATE);
	
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