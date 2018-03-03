package uk.ac.kcl.mdeoptimise.rulegen.tests

import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.rulegen.RulesGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.ExpectedRulesHelper
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelLoader

import static org.junit.Assert.*

@RunWith(XtextRunner)
class RulegenTests {
	
	
	EPackage metamodel;
	List<Multiplicity> multiplicities;

	@Before
	def void rulegenInitialise(){
		this.metamodel = new MetamodelLoader().fakeGenericMetamodel
		this.multiplicities = new LinkedList<Multiplicity>();
	}
    
    def writeModel(EObject model, String modelPathPrefix, String modelFilename) {
    	
    	var reg = Resource.Factory.Registry.INSTANCE;
		var m = reg.getExtensionToFactoryMap();
		m.put("henshin", new HenshinResourceFactory());
    	
		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		
		var resourceSet = new HenshinResourceSet(modelPathPrefix);
		
		val resource = resourceSet.createResource(URI.createFileURI(modelFilename))
		
		if (resource.loaded) {
			resource.contents.clear
		}
		
		resource.contents.add(model)
		resource.save(options)
	}
	
	@Test
	def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWithTwoMultiplicitySets(){
		
		
		//Original metamodel with 0..* 0..* multiplicities
		
		this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 3, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 5, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
		
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		
		var List<RuleSpec> ruleSpecs = new ArrayList<RuleSpec>();
		
		var ruleSpec = new RuleSpec("B", "ALL");
		
		ruleSpecs.add(ruleSpec);
		
		var rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		var createdRules = rulesGenerator.generateRules
		
		
		createdRules.get(this.metamodel).forEach[ module, index |
			
			ExpectedRulesHelper.writeModel(module, "src/resources/expected/new/", module.name + ".henshin");
		]
		
		assertEquals(9, createdRules.get(this.metamodel).length)
	}




	@Test
	def void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWithTwoMultiplicitySets(){
		
		
		//Original metamodel with 0..* 0..* multiplicities
		
		this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 3, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 5, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
		
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		
		var List<RuleSpec> ruleSpecs = new ArrayList<RuleSpec>();
		
		var ruleSpec = new RuleSpec("C", "edgeCB", "ADD");
		
		ruleSpecs.add(ruleSpec);
		
		var rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		var createdRules = rulesGenerator.generateRules
		
		
		createdRules.get(this.metamodel).forEach[ module, index |
			
			ExpectedRulesHelper.writeModel(module, "src/resources/expected/new/", module.name + ".henshin");
		]
		
		//ExpectedRulesHelper.writeModel(module, "src/resources/expected/create/", module.name + "_a_ub_1_b_ub_1.henshin");
		
		//Check the generated rule
		
		//var comparator = new ComparatorHelper(expectedRule, module);
		
		//var equal = comparator.compare();
		
		//assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
		//	module.name, comparator.diferences), equal)
		assertEquals(9, createdRules.get(this.metamodel).length)
	}
	
	@Test
	def void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWithTwoMultiplicitySets(){
		
		
		//Original metamodel with 0..* 0..* multiplicities
		
		this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 3, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 5, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
		
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		
		var List<RuleSpec> ruleSpecs = new ArrayList<RuleSpec>();
		
		var ruleSpec = new RuleSpec("C", "edgeCB", "REMOVE");
		
		ruleSpecs.add(ruleSpec);
		
		var rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		var createdRules = rulesGenerator.generateRules
		
		
		createdRules.get(this.metamodel).forEach[ module, index |
			
			ExpectedRulesHelper.writeModel(module, "src/resources/expected/new/", module.name + ".henshin");
		]
		
		//ExpectedRulesHelper.writeModel(module, "src/resources/expected/create/", module.name + "_a_ub_1_b_ub_1.henshin");
		
		//Check the generated rule
		
		//var comparator = new ComparatorHelper(expectedRule, module);
		
		//var equal = comparator.compare();
		
		//assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
		//	module.name, comparator.diferences), equal)
		assertEquals(9, createdRules.get(this.metamodel).length)
	}
	
	@Test
	def void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAndRemovingAnEdgeWithTwoMultiplicitySets(){
		
		
		//Original metamodel with 0..* 0..* multiplicities
		
		this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 3, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 5, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
		
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		
		var List<RuleSpec> ruleSpecs = new ArrayList<RuleSpec>();
		
		var ruleSpec = new RuleSpec("C", "ALL");
		
		ruleSpecs.add(ruleSpec);
		
		var rulesGenerator = new RulesGenerator(this.metamodel,  this.multiplicities, ruleSpecs)
		
		var createdRules = rulesGenerator.generateRules
		
		
		createdRules.get(this.metamodel).forEach[ module, index |
			
			ExpectedRulesHelper.writeModel(module, "src/resources/expected/new/", module.name + ".henshin");
		]
		
		//ExpectedRulesHelper.writeModel(module, "src/resources/expected/create/", module.name + "_a_ub_1_b_ub_1.henshin");
		
		//Check the generated rule
		
		//var comparator = new ComparatorHelper(expectedRule, module);
		
		//var equal = comparator.compare();
		
		//assertTrue(String.format("Assert that %s rule is as expected. Differences found: %s",
		//	module.name, comparator.diferences), equal)
		assertEquals(9, createdRules.get(this.metamodel).length)
	}
}