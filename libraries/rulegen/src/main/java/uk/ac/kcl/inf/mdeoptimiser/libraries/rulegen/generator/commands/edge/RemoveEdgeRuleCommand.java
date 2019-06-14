package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge;

import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.Rule;
import org.sidiff.serge.configuration.Configuration;
import org.sidiff.serge.configuration.Configuration.OperationType;
import org.sidiff.serge.core.Common;
import org.sidiff.serge.exceptions.OperationTypeNotImplementedException;
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator;
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import org.eclipse.emf.henshin.model.Module;

public class RemoveEdgeRuleCommand implements IRuleGenerationCommand {
	
	MetamodelWrapper metamodelWrapper;
	EClass node;
	EReference edge;
	
	RuleSpec ruleSpec;
  RepairSpec repairSpec;
	
	public RemoveEdgeRuleCommand(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec,RepairSpec repairSpec) {
		this.metamodelWrapper = metamodelWrapper;
		this.ruleSpec = ruleSpec;
		this.repairSpec = repairSpec;
	}
	
	public RemoveEdgeRuleCommand(EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs){
		this.metamodelWrapper = metamodelWrapper;
		this.node = node;
		this.ruleSpec = ruleSpec;
    this.repairSpec = repairSpecs;
  }
	
	public EClass getNode(){
		
		if(this.node == null){
			this.node = metamodelWrapper.getNode(ruleSpec.getNode());
		}
		
		return this.node;
	}
	
	public EReference getEdge(){
		
		if(this.edge == null){
			this.edge = metamodelWrapper.getEdge(ruleSpec.getNode(), ruleSpec.getEdge());
		}
		
		return this.edge;
	}

	@Override
	public Module generate() {
		
		var createEdgeRuleCommand = new AddEdgeRuleCommand(this.getNode(), metamodelWrapper, ruleSpec, repairSpec);

    Module module = null;

    try {
      module = Common.createInverse(createEdgeRuleCommand.generate(), OperationType.ADD);
    } catch (OperationTypeNotImplementedException e) {
      e.printStackTrace();
    }

    module.getUnits().forEach(rule -> applyRuleNacConditions((Rule)rule));
		
		return module;
	}

	//Apply the NACs
	private void applyRuleNacConditions(Rule rule){
		
		// Remove NAC formula left over by inverse
		rule.getRhs().setFormula(null);
	
		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
}
