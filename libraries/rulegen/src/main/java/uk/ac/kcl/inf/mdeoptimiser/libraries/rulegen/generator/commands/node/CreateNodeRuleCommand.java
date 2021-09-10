package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.node;

import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx;
import org.sidiff.serge.configuration.Configuration.OperationType;
import org.sidiff.serge.configuration.GlobalConstants;
import org.sidiff.serge.core.Common;
import org.sidiff.serge.exceptions.OperationTypeNotImplementedException;
import org.sidiff.serge.filter.ElementFilter;
import org.sidiff.serge.generators.actions.RuleParameterGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.LowerBoundManyRepairCheckGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.UpperBoundManyRepairCheckGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpecType;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang.RuleNameGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class CreateNodeRuleCommand implements IRuleGenerationCommand {

  MetamodelWrapper metamodelWrapper;
  EClass node;

  RuleSpec ruleSpec;
  List<RepairSpec> repairSpecs;

  public CreateNodeRuleCommand(
      MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, List<RepairSpec> repairSpecs) {
    this.metamodelWrapper = metamodelWrapper;
    this.ruleSpec = ruleSpec;
    this.repairSpecs = repairSpecs;
  }

  public CreateNodeRuleCommand(
      EClass node,
      MetamodelWrapper metamodelWrapper,
      RuleSpec ruleSpec,
      List<RepairSpec> repairSpecs) {
    this.metamodelWrapper = metamodelWrapper;
    this.node = node;
    this.repairSpecs = repairSpecs;
    this.ruleSpec = ruleSpec;
  }

  public EClass getNode() {

    if (this.node == null) {
      this.node = metamodelWrapper.getNode(ruleSpec.getNode());
    }

    return this.node;
  }

  @Override
  public Module generate() {

    // Create module
    var module = HenshinFactory.eINSTANCE.createModule();

    var ruleName =
        RuleNameGenerator.getRuleName(ruleSpec, repairSpecs, this.metamodelWrapper.ruleType());

    // Set module name
    module.setName(ruleName);
    module.setDescription("Creates a node in all allowed metamodel contexts");

    // Set module metamodels
    // TODO fix this to use original metamodel
    module.getImports().add(metamodelWrapper.getMetamodel());

    // classifier
    var classifierInfo =
        metamodelWrapper.getMetamodelAnalyser().getAllParentContext(this.getNode(), true);

    for (var contextReferenceId = 0;
        contextReferenceId < classifierInfo.keySet().size();
        contextReferenceId++) {

      var context = classifierInfo.get(classifierInfo.keySet().toArray()[contextReferenceId]);

      // Create a new rule in the module for each context container of the refined multiplicity node
      for (var contextId = 0; contextId < context.size(); contextId++) {

        Rule rule = null;
        try {
          rule =
              Common.createBasicRule(
                  module,
                  (EReference) classifierInfo.keySet().toArray()[contextReferenceId],
                  this.getNode(),
                  context.get(contextId),
                  null,
                  null,
                  OperationType.CREATE);
        } catch (OperationTypeNotImplementedException e) {
          e.printStackTrace();
        }

        var newNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);

        // Add mandatory children for the new node created

        // TODO Perhaps should be moved somewhere else to make only one call
        // This loads the info about mandatories in the respective classes
        var childInfo = metamodelWrapper.getMetamodelAnalyser().getEClassifierInfo(this.getNode());
        childInfo.getMandatoryNeighbours();
        childInfo.getMandatoryChildren();

        var elementFilter = new ElementFilter(metamodelWrapper.getMetamodelAnalyser());

        // create mandatories if any
        if (childInfo.hasMandatories()) {
          try {
            Common.createMandatoryChildren(
                rule,
                childInfo,
                newNode,
                OperationType.CREATE,
                true,
                metamodelWrapper.getMetamodelAnalyser(),
                elementFilter);
          } catch (OperationTypeNotImplementedException e) {
            e.printStackTrace();
          }
          try {
            Common.createMandatoryNeighbours(
                rule, childInfo, newNode, OperationType.CREATE, true, elementFilter);
          } catch (OperationTypeNotImplementedException e) {
            e.printStackTrace();
          }
        }

        applyRepairOperations(rule, this.repairSpecs);

        applyRuleNacConditions(rule);
        applyRuleParameters(rule);

        // Add rule to module for this context classifier
        module.getUnits().add(rule);
      }
    }

    return module;
  }

  // Fix the LB requirement for the created node by taking one node required for the LB from lb
  // nodes of the same type
  private void applyRepairOperations(Rule rule, List<RepairSpec> repairSpecs) {

    // Get the repair spec from a factory
    // TODO Move these to their own factory. This list will increase in the future.
    applyRepairOperationsLbSingle(rule, repairSpecs);
    applyRepairOperationsLbMany(rule, repairSpecs);
  }

  private void applyRepairOperationsLbMany(Rule rule, List<RepairSpec> repairSpecs) {

    // Get the created node from the rule graphs
    var createdNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);

    repairSpecs.stream()
        .filter(
            repairSpec ->
                repairSpec.getRepairSpecType() == RepairSpecType.CREATE_LB_REPAIR_MANY
                    || repairSpec.getRepairSpecType() == RepairSpecType.DELETE_LB_REPAIR_MANY)
        .forEach(
            repairSpec -> {

              // TODO Move this to the delete node class
              if (repairSpec.getRepairSpecType() == RepairSpecType.DELETE_LB_REPAIR_MANY) {

                if (repairSpec.getEdge().getEOpposite().getLowerBound()
                        == repairSpec.getEdge().getEOpposite().getUpperBound()
                    && repairSpec.getEdge().getEOpposite().getLowerBound() > 1
                    && repairSpec.getEdge().getLowerBound() == 0) {

                  var childInfo =
                      metamodelWrapper.getMetamodelAnalyser().getEClassifierInfo(this.getNode());
                  childInfo.getMandatoryNeighbours();
                  childInfo.getMandatoryChildren();

                  var elementFilter = new ElementFilter(metamodelWrapper.getMetamodelAnalyser());

                  try {
                    Common.createRepairOppositeMandatoryNeighbours(
                        rule,
                        childInfo,
                        createdNode,
                        OperationType.CREATE,
                        true,
                        metamodelWrapper.getMetamodelAnalyser(),
                        elementFilter);
                  } catch (OperationTypeNotImplementedException e) {
                    e.printStackTrace();
                  }
                }
              }

              createdNode
                  .getOutgoing(repairSpec.getEdge())
                  .forEach(
                      outgoingEdge -> {

                        // Find existing target node
                        var existingTargetNode =
                            HenshinRuleAnalysisUtilEx.findCorrespondingNodeInLHS(
                                outgoingEdge.getTarget());

                        // Create existing node A from which to take the existing target node
                        var existingsourceNodeName =
                            Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
                        var existingSourceNode =
                            HenshinRuleAnalysisUtilEx.createPreservedNode(
                                rule, existingsourceNodeName, this.getNode());

                        // Create a delete edge between existing node A and an existing node B
                        HenshinRuleAnalysisUtilEx.createDeleteEdge(
                            existingSourceNode.getLhsNode(),
                            existingTargetNode,
                            repairSpec.getEdge(),
                            rule);
                      });
            });
  }

  private void applyRepairOperationsLbSingle(Rule rule, List<RepairSpec> repairSpecs) {

    // Get the created node from the rule graphs
    var createdNode = HenshinRuleAnalysisUtilEx.getRHSMinusLHSNodes(rule).get(0);

    repairSpecs.stream()
        .filter(
            repairSpec ->
                repairSpec.getRepairSpecType() == RepairSpecType.DELETE_LB_REPAIR
                    || repairSpec.getRepairSpecType() == RepairSpecType.CREATE_LB_REPAIR)
        .forEach(
            repairSpec -> {

              // TODO Move this to the delete node class
              if (repairSpec.getRepairSpecType() == RepairSpecType.DELETE_LB_REPAIR) {

                if (repairSpec.getEdge().getEOpposite().getLowerBound()
                        == repairSpec.getEdge().getEOpposite().getUpperBound()
                    && repairSpec.getEdge().getEOpposite().getLowerBound() > 0
                    && repairSpec.getEdge().getLowerBound() == 0) {

                  var childInfo =
                      metamodelWrapper.getMetamodelAnalyser().getEClassifierInfo(this.getNode());
                  childInfo.getMandatoryNeighbours();
                  childInfo.getMandatoryChildren();

                  var elementFilter = new ElementFilter(metamodelWrapper.getMetamodelAnalyser());

                  try {
                    Common.createRepairOppositeMandatoryNeighbours(
                        rule,
                        childInfo,
                        createdNode,
                        OperationType.CREATE,
                        true,
                        metamodelWrapper.getMetamodelAnalyser(),
                        elementFilter);
                  } catch (OperationTypeNotImplementedException e) {
                    e.printStackTrace();
                  }
                }
              }

              // Create existing node A from which to take the existing target node
              var existingsourceNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
              var existingSourceNode =
                  HenshinRuleAnalysisUtilEx.createPreservedNode(
                      rule, existingsourceNodeName, this.getNode());

              createdNode
                  .getOutgoing(repairSpec.getEdge())
                  .forEach(
                      outgoingEdge -> {

                        // Find existing target node
                        var existingTargetNode =
                            HenshinRuleAnalysisUtilEx.findCorrespondingNodeInLHS(
                                outgoingEdge.getTarget());

                        // Create a delete edge between existing node A and an existing node B
                        HenshinRuleAnalysisUtilEx.createDeleteEdge(
                            existingSourceNode.getLhsNode(),
                            existingTargetNode,
                            repairSpec.getEdge(),
                            rule);
                      });
            });
  }

  // Apply the NACs
  private void applyRuleNacConditions(Rule rule) {
    new LowerBoundManyRepairCheckGenerator(rule).generate();
    new UpperBoundManyRepairCheckGenerator(rule).generate();
  }

  // Create the rule parameters
  private void applyRuleParameters(Rule rule) {
    new RuleParameterGenerator(rule).generate();
  }
}
