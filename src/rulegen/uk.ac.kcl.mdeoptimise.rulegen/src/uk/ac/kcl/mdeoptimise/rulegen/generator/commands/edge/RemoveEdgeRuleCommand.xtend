package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge

import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.serge.configuration.Configuration.OperationType
import org.sidiff.serge.core.Common
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec

class RemoveEdgeRuleCommand implements IRuleGenerationCommand {
	
	MetamodelWrapper metamodelWrapper;
	EClass node;
	EReference edge;
	
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
	
	def EReference getEdge(){
		
		if(this.edge == null){
			this.edge = metamodelWrapper.getEdge(ruleSpec.getNode, ruleSpec.getEdge)	
		}
		
		return this.edge;
	}
	
	override generate() {
		
		var createEdgeRuleCommand = new AddEdgeRuleCommand(this.getNode, metamodelWrapper, ruleSpec, repairSpecs);
		
		var module = Common.createInverse(createEdgeRuleCommand.generate(), OperationType.ADD);
				
		module.units.forEach[
			rule | applyRuleNacConditions(rule as Rule)
		]
		
		return module
	}

	//Apply the NACs
	private def void applyRuleNacConditions(Rule rule){
		
		// Remove NAC formula left over by inverse
		rule.rhs.formula = null;
	
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
}
