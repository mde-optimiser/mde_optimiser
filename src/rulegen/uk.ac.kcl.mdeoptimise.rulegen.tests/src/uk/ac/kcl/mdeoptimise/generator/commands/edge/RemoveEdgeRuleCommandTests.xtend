//package uk.ac.kcl.mdeoptimise.generator.commands.edge
//
//import java.util.LinkedList
//import java.util.Stack
//import org.eclipse.xtext.testing.XtextRunner
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
//import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
//import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
//import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.ComparatorHelper
//import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.ExpectedRulesHelper
//import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelLoader
//
//import static org.junit.Assert.*
//import org.junit.Before
//import org.eclipse.emf.ecore.EPackage
//import java.util.List
//import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.RemoveEdgeRuleCommand
//
//@RunWith(XtextRunner)
//class RemoveEdgeRuleCommandTests {
//
//	EPackage metamodel;
//	List<Multiplicity> multiplicities;
//
//	@Before
//	def void rulegenInitialise(){
//		this.metamodel = new MetamodelLoader().fakeGenericMetamodel
//		this.multiplicities = new LinkedList<Multiplicity>();
//	}
//
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForNoLowerOrUpperBoundMultiplicities() {
//		
//		multiplicities.add(new Multiplicity("D", "edgeDC", 0, -1, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_no_lb")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForNoLowerBoundMultiplicitiesaAndUpperBoundOfOne() {
//		
//		multiplicities.add(new Multiplicity("D", "edgeDC", 0, 1, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_no_lb")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForNoLowerBoundMultiplicitiesaAndUpperBoundOfFive() {
//		
//		multiplicities.add(new Multiplicity("D", "edgeDC", 0, 5, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_no_lb")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//	
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForLowerBoundMultiplicityOfOne() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 1, -1, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_lb_1")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//
//
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForLowerBoundMultiplicityOfOneAndUpperBoundOfOne() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 1, 1, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_lb_1_ub_1")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//	
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForLowerBoundMultiplicityOfOneAndUpperBoundFive() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 1, 5, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_lb_1_ub_5")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//	
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForLowerBoundMultiplicityOfFive() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 5, -1, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_lb_5")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//	
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForLowerBoundMultiplicityOfFiveAndUpperBoundOfSix() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 5, 5, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_lb_5_ub_6")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//	
//	@Test
//	def void assertThatRemoveEdgeRuleIsGeneratedAsExpectedForLowerBoundMultiplicityOfFiveAndUpperBoundOfFive() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 5, 6, this.metamodel));
//
//		//Prepare rule generation inputs
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities)
//
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//
//		var node = refinedMetamodelWrapper.getNode("D");
//		var edge = refinedMetamodelWrapper.getEdge("D", "edgeDC");
//
//		//Generate the rule
//		var removeEdgeRuleCommand = new RemoveEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = removeEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadRemoveRule(module.name + "_lb_5_ub_5")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//
//}
