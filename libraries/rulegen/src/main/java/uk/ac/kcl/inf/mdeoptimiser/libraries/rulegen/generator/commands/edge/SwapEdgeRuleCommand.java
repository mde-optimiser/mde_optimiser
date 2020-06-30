package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx;
import org.sidiff.serge.configuration.GlobalConstants;
import org.sidiff.serge.core.Common;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang.RuleNameGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class SwapEdgeRuleCommand implements IRuleGenerationCommand {

  MetamodelWrapper metamodelWrapper;
  EClass node;
  EReference edge;

  RuleSpec ruleSpec;
  RepairSpec repairSpecs;

  public SwapEdgeRuleCommand(
      MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs) {
    this.metamodelWrapper = metamodelWrapper;
    this.ruleSpec = ruleSpec;
    this.repairSpecs = repairSpecs;
  }

  public SwapEdgeRuleCommand(
      EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs) {
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

  public EReference getEdge() {

    if (this.edge == null) {
      this.edge = repairSpecs.getEdge();
    }

    return this.edge;
  }

  @Override
  public Module generate() {

    // Create module
    var module = HenshinFactory.eINSTANCE.createModule();

    // Set module name
    var ruleName =
        RuleNameGenerator.getRuleName(ruleSpec, repairSpecs, this.metamodelWrapper.ruleType());

    // Set module name
    module.setName(ruleName);
    module.setDescription(
        "Changes "
            + this.getEdge().getName()
            + " edge from "
            + this.getNode().getName()
            + " to "
            + this.getEdge().getEType().getName());

    // Set module metamodels
    module.getImports().add(metamodelWrapper.getMetamodel());

    // TODO Test this case with a metamodel variant that has more than one container for the same
    // classifier
    var classifierInfo = metamodelWrapper.getMetamodelAnalyser().getAllParentContext(node, true);

    for (var contextReferenceId = 0;
        contextReferenceId < classifierInfo.keySet().size();
        contextReferenceId++) {

      var context = classifierInfo.get(classifierInfo.keySet().toArray()[contextReferenceId]);

      // Create a new rule in the module for each context container of the refined multiplicity node
      for (var contextId = 0; contextId < context.size(); contextId++) {

        var rule =
            generateBasicRule(
                module, this.getEdge(), this.getNode(), (EClass) this.getEdge().getEType());

        // Add rule to module for this context classifier
        module.getUnits().add(rule);
      }
    }

    return module;
  }

  private Rule generateBasicRule(
      Module module, EReference reference, EClass sourceNode, EClass targetNode) {

    var rule = HenshinFactory.eINSTANCE.createRule();
    rule.setActivated(true);
    rule.setName("swap" + sourceNode.getName() + "_" + reference.getName() + targetNode.getName());
    rule.setDescription("Swap the EReference " + reference.getName());
    module.getUnits().add(rule);

    // Node A
    var selectedNodePair =
        HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.SEL, sourceNode);

    // Node A*
    var secondSourceNodeName = Common.getFreeNodeName(GlobalConstants.SEL, rule);
    var secondSourceNodePair =
        HenshinRuleAnalysisUtilEx.createPreservedNode(rule, secondSourceNodeName, sourceNode);

    // Node B
    var oldNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
    var oldNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, oldNodeName, targetNode);

    // Node B*
    var newNodeName = Common.getFreeNodeName(GlobalConstants.NEWTGT, rule);
    var newNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, newNodeName, targetNode);

    // Remove edge between node A and B
    HenshinRuleAnalysisUtilEx.createDeleteEdge(
        selectedNodePair.getLhsNode(), oldNodePair.getLhsNode(), reference, rule);

    // Create an edge between A and B*
    HenshinRuleAnalysisUtilEx.createCreateEdge(
        selectedNodePair.getRhsNode(), newNodePair.getRhsNode(), reference);

    // Remove edge between node A* and node B*
    HenshinRuleAnalysisUtilEx.createDeleteEdge(
        secondSourceNodePair.getLhsNode(), newNodePair.getLhsNode(), reference, rule);

    // Crete edge between nonde A* and node B
    HenshinRuleAnalysisUtilEx.createCreateEdge(
        secondSourceNodePair.getRhsNode(), oldNodePair.getRhsNode(), reference);

    return rule;
  }
}
