//package uk.ac.kcl.mdeoptimise.generator.commands.edge
//
//import java.util.LinkedList
//import java.util.Stack
//import org.eclipse.xtext.testing.XtextRunner
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
//import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.AddEdgeRuleCommand
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
//
//@RunWith(XtextRunner)
//class AddEdgeRuleCommandTests {
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
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForNoUpperBoundMultiplicities() {
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_no_ub")
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//	}
//	
//		@Test
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForUpperBoundMultiplicityOfOne(){
//		
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//	
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_ub_1")
//
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//		
//	}
//
//	@Test
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForUpperBoundMultiplicitieOfFive(){
//		
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//	
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_ub_5")
//		
//		//ExpectedRulesHelper.writeModel(module, "src/resources/expected/add/", module.name + "_ub_5.henshin");
//		
//		var comparator = new ComparatorHelper(expectedRule, module);
//		
//		var equal = comparator.compare();
//		
//		assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
//			module.name, comparator.diferences), equal)
//		
//	}
//	
//	@Test
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForNoUpperBoundMultiplicitiesAndLowerBoundOfOne() {
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_no_ub")
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
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForUpperBoundOfTwoAndLowerBoundOfOne() {
//
//		multiplicities.add(new Multiplicity("D", "edgeDC", 1, 2, this.metamodel));
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_lb_1_ub_2")
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
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForUpperBoundOfOneAndLowerBoundOfOne() {
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_lb_1_ub_1")
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
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForNoUpperBoundMultiplicitiesAndLowerBoundOfFive() {
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_no_ub")
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
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForUpperBoundOfSixAndLowerBoundOfFive() {
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_lb_5_ub_6")
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
//	def void assertThatAddEdgeRuleIsGeneratedAsExpectedForUpperBoundOfFivendLowerBoundOfFive() {
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
//		var addEdgeRuleCommand = new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		val module = addEdgeRuleCommand.generate();
//		
//		//Check the generated rule
//		var expectedRule = ExpectedRulesHelper.loadAddRule(module.name + "_lb_5_ub_5")
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
