package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Rule;
import org.sidiff.serge.configuration.Configuration.OperationType;
import org.sidiff.serge.core.Common;
import org.sidiff.serge.exceptions.OperationTypeNotImplementedException;
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator;
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang.RuleNameGenerator;
import org.eclipse.emf.henshin.model.Module;

public class ChangeEdgeRuleCommand implements IRuleGenerationCommand {

  MetamodelWrapper metamodelWrapper;
  EClass node;
  EReference edge;

  RuleSpec ruleSpec;
  RepairSpec repairSpec;

  public ChangeEdgeRuleCommand(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec) {
    this.metamodelWrapper = metamodelWrapper;
    this.ruleSpec = ruleSpec;
    this.repairSpec = repairSpec;
  }

  public ChangeEdgeRuleCommand(EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec){
    this.metamodelWrapper = metamodelWrapper;
    this.node = node;
    this.repairSpec = repairSpec;
    this.ruleSpec = ruleSpec;
  }

  public EClass getNode(){

    if(this.node == null){
      this.node = metamodelWrapper.getNode(ruleSpec.getNode());

    }

    return this.node;
  }

  public EReference getEdge(){

    if(this.edge == null){
      this.edge = repairSpec.getEdge();
    }

    return this.edge;
  }

  @Override
  public Module generate() {

    //Create module
    var module = HenshinFactory.eINSTANCE.createModule();

    //Set module name
    var ruleName = RuleNameGenerator.getRuleName(ruleSpec, repairSpec, this.metamodelWrapper.ruleType());

    //Set module name
    module.setName(ruleName);
    module.setDescription("Changes " + this.getEdge().getName() + " edge from " + this.getNode().getName() + " to " + this.getEdge().getEType().getName());

    //Set module metamodels
    module.getImports().add(metamodelWrapper.getMetamodel());

    //TODO Test this case with a metamodel variant that has more than one container for the same classifier
    var classifierInfo = metamodelWrapper.getMetamodelAnalyser().getAllParentContext(this.getNode(), true);

    for(var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet().size(); contextReferenceId++) {

      var context = classifierInfo.get(classifierInfo.keySet().toArray()[contextReferenceId]);

      //Create a new rule in the module for each context container of the refined multiplicity node
      for(var contextId = 0; contextId < context.size(); contextId++){


        Rule rule = null;

        if(this.getEdge().getEOpposite() == null){
          try {
            rule = Common.createBasicRule(module, this.getEdge(), this.getNode(),
                    this.getEdge().getEType(), null, null, OperationType.CHANGE_REFERENCE);
          } catch (OperationTypeNotImplementedException e) {
            e.printStackTrace();
          }
        } else {
          try {
            rule = Common.createBasicRule(module, this.getEdge().getEOpposite(),
                    this.getEdge().getEType(), this.getNode(), null, null, OperationType.CHANGE_REFERENCE);
          } catch (OperationTypeNotImplementedException e) {
            e.printStackTrace();
          }
        }

        applyRuleNacConditions(rule);

        //Add rule to module for this context classifier
        module.getUnits().add(rule);
      }
    }

    return module;
  }

  //Apply the NACs
  private void applyRuleNacConditions(Rule rule){

    new LowerBoundCheckGenerator(rule).generate();
    new UpperBoundCheckGenerator(rule).generate();
  }
}
