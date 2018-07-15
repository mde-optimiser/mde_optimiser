package uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.henshin.model.HenshinFactory
import org.eclipse.emf.henshin.model.Rule
import org.sidiff.common.henshin.HenshinConditionUtil
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx
import org.sidiff.common.henshin.HenshinRuleAnalysisUtilEx.FormulaCombineOperator
import org.sidiff.serge.configuration.GlobalConstants
import org.sidiff.serge.generators.conditions.LowerBoundCheckGenerator
import org.sidiff.serge.generators.conditions.UpperBoundCheckGenerator
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.mdeoptimiser.rulegen.lang.RuleNameGenerator
import java.util.AbstractMap
import org.eclipse.emf.henshin.model.Formula

class AddEdgeRuleCommand implements IRuleGenerationCommand {

	MetamodelWrapper metamodelWrapper;
	EClass node;
	EReference edge;

	RuleSpec ruleSpec;
	RepairSpec repairSpec;

	new(MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpec) {
		this.metamodelWrapper = metamodelWrapper;
		this.ruleSpec = ruleSpec;
		this.repairSpec = repairSpec;
	}

	new(EClass node, MetamodelWrapper metamodelWrapper, RuleSpec ruleSpec, RepairSpec repairSpecs) {
		this.metamodelWrapper = metamodelWrapper;
		this.node = node;
		this.repairSpec = repairSpecs;
		this.ruleSpec = ruleSpec;
	}

	def EClass getNode() {

		if (this.node === null) {
			this.node = metamodelWrapper.getNode(ruleSpec.getNode)
		}

		return this.node;
	}

	def EReference getEdge() {

		if (this.edge === null) {
			this.edge = repairSpec.edge
		}

		return this.edge;
	}

	override generate() {

		// Create module
		val module = HenshinFactory.eINSTANCE.createModule();

		// Set module name
		var ruleName = RuleNameGenerator.getRuleName(ruleSpec, this.repairSpec, this.metamodelWrapper.ruleType)

		// Set module name
		module.setName(ruleName)
		module.setDescription("Adds to " + this.getNode.name + " edge of type " + this.getEdge.name)

		// Set module metamodels
		module.getImports().add(metamodelWrapper.getMetamodel)

		// TODO Test this case with a metamodel variant that has more than one container for the same classifier
		val classifierInfo = metamodelWrapper.metamodelAnalyser.getAllParentContext(this.getNode, true);

		for (var contextReferenceId = 0; contextReferenceId < classifierInfo.keySet.size; contextReferenceId++) {

			val context = classifierInfo.get(classifierInfo.keySet.get(contextReferenceId));

			// Create a new rule in the module for each context container of the refined multiplicity node	
			for (var contextId = 0; contextId < context.size; contextId++) {

				val rule = createRule(this.getNode, this.getEdge, this.getEdge.getEType as EClass)

				// applyRuleNacConditions(rule)
				// Add rule to module for this context classifier
				module.getUnits().add(rule);
			}
		}

		return module;
	}

	// Taken in part from the SERGe codebase
	private def Rule createRule(EClass source, EReference outReference, EClass target) {

		var rule = HenshinFactory.eINSTANCE.createRule();
		rule.setActivated(true);
		rule.setName("addTo" + source.getName() + "_" + outReference.getName() + "_" + target.getName());
		rule.setDescription("Adds to " + source.getName() + "'s reference " + outReference.getName() + " the target " +
			target.getName());

		// Create preserved node for eClass
		var selectedNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.SEL, source);
		var newNodePair = HenshinRuleAnalysisUtilEx.createPreservedNode(rule, GlobalConstants.NEWTGT, target);

		// Create <<create>> edge for new target for EReference and it's
		// EOpposite, if any
		HenshinRuleAnalysisUtilEx.createCreateEdge(selectedNodePair.getRhsNode(), newNodePair.getRhsNode(),
			outReference);

		applyRuleNacConditions(rule)

		var nestedCond = HenshinFactory.eINSTANCE.createNestedCondition();
		var graph = HenshinFactory.eINSTANCE.createGraph();
		graph.name = "edgeub"
		var sourceNode = HenshinFactory.eINSTANCE.createNode(graph, selectedNodePair.lhsNode.type, "");
		var targetNode = HenshinFactory.eINSTANCE.createNode(graph, newNodePair.lhsNode.type, "");

		HenshinFactory.eINSTANCE.createEdge(sourceNode, targetNode, outReference)

		nestedCond.setConclusion(graph);

		nestedCond.getMappings().add(HenshinFactory.eINSTANCE.createMapping(selectedNodePair.getLhsNode(), sourceNode));
		nestedCond.getMappings().add(HenshinFactory.eINSTANCE.createMapping(newNodePair.getLhsNode(), targetNode));

		var formula = HenshinFactory.eINSTANCE.createNot();
		formula.setChild(nestedCond);

		HenshinConditionUtil.addFormula(new AbstractMap.SimpleEntry<String, Formula>("edgeub",formula), rule.getLhs(), FormulaCombineOperator.AND);

		return rule;
	}

	// Apply the NACs
	private def void applyRuleNacConditions(Rule rule) {

		new LowerBoundCheckGenerator(rule).generate();
		new UpperBoundCheckGenerator(rule).generate();
	}
}
