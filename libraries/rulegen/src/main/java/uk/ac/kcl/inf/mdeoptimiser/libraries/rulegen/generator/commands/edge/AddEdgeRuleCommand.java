package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.commands.edge;

import java.util.AbstractMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Rule;
import org.sidiff.common.henshin.HenshinConditionUtil;
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx;
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx.FormulaCombineOperator;
import org.sidiff.serge.configuration.GlobalConstants;
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator;
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.IRuleGenerationCommand;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.generator.specs.RepairSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.lang.RuleNameGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.MetamodelWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.RuleSpec;

public class AddEdgeRuleCommand implements IRuleGenerationCommand {

  MetamodelWrapper metamodelWrapper;
  EClass node;
  EReference edge;

  RuleSpec ruleSpec;
  RepairSpec repairSpec;

  public AddEdgeRuleCommand(
      MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec) {
    this.metamodelWrapper = metamodelWrapper;
    this.ruleSpec = ruleSpec;
    this.repairSpec = repairSpec;
  }

  public AddEdgeRuleCommand(
      EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs) {
    this.metamodelWrapper = metamodelWrapper;
    this.node = node;
    this.repairSpec = repairSpecs;
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
      this.edge = repairSpec.getEdge();
    }

    return this.edge;
  }

  @Override
  public Module generate() {

    // Create module
    var module = HenshinFactory.eINSTANCE.createModule();

    // Set module name
    var ruleName =
        RuleNameGenerator.getRuleName(ruleSpec, this.repairSpec, this.metamodelWrapper.ruleType());

    // Set module name
    module.setName(ruleName);
    module.setDescription(
        "Adds to " + this.getNode().getName() + " edge of type " + this.getEdge().getName());

    // Set module metamodels
    module.getImports().add(metamodelWrapper.getMetamodel());

    var classifierInfo =
        metamodelWrapper.getMetamodelAnalyser().getAllParentContext(this.getNode(), true);

    for (var contextReferenceId = 0;
        contextReferenceId < classifierInfo.keySet().size();
        contextReferenceId++) {

      var context = classifierInfo.get(classifierInfo.keySet().toArray()[contextReferenceId]);

      // Create a new rule in the module for each context container of the refined multiplicity node
      for (var contextId = 0; contextId < context.size(); contextId++) {

        var rule = createRule(this.getNode(), this.getEdge(), (EClass) this.getEdge().getEType());

        // applyRuleNacConditions(rule)
        // Add rule to module for this context classifier
        module.getUnits().add(rule);
      }
    }

    return module;
  }

  // Taken in part from the SERGe codebase
  private Rule createRule(EClass source, EReference outReference, EClass target) {

    var rule = HenshinFactory.eINSTANCE.createRule();
    rule.setActivated(true);
    rule.setName(
        "addTo" + source.getName() + "_" + outReference.getName() + "_" + target.getName());
    rule.setDescription(
        "Adds to "
            + source.getName()
            + "'s reference "
            + outReference.getName()
            + " the target "
            + target.getName());

    // Create preserved node for eClass
    var selectedNodePair =
        HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.SEL, source);
    var newNodePair =
        HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.NEWTGT, target);

    // Create <<create>> edge for new target for EReference and it's
    // EOpposite, if any
    HenshinRuleAnalysisUtilEx.createCreateEdge(
        selectedNodePair.getRhsNode(), newNodePair.getRhsNode(), outReference);

    applyRuleNacConditions(rule);

    var nestedCond = HenshinFactory.eINSTANCE.createNestedCondition();
    var graph = HenshinFactory.eINSTANCE.createGraph();
    graph.setName("edgeub");
    var sourceNode =
        HenshinFactory.eINSTANCE.createNode(graph, selectedNodePair.getLhsNode().getType(), "");
    var targetNode =
        HenshinFactory.eINSTANCE.createNode(graph, newNodePair.getLhsNode().getType(), "");

    HenshinFactory.eINSTANCE.createEdge(sourceNode, targetNode, outReference);

    nestedCond.setConclusion(graph);

    nestedCond
        .getMappings()
        .add(HenshinFactory.eINSTANCE.createMapping(selectedNodePair.getLhsNode(), sourceNode));
    nestedCond
        .getMappings()
        .add(HenshinFactory.eINSTANCE.createMapping(newNodePair.getLhsNode(), targetNode));

    var formula = HenshinFactory.eINSTANCE.createNot();
    formula.setChild(nestedCond);

    HenshinConditionUtil.addFormula(
        new AbstractMap.SimpleEntry<String, Formula>("edgeub", formula),
        rule.getLhs(),
        FormulaCombineOperator.AND);

    return rule;
  }

  // Apply the NACs
  private void applyRuleNacConditions(Rule rule) {

    new LowerBoundCheckGenerator(rule).generate();
    new UpperBoundCheckGenerator(rule).generate();
  }
}
