package uk.ac.kcl.mdeoptimise.rulegen.tests

import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import java.util.Set
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.testing.XtextRunner
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpecType
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.SpecsGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelLoader

import static org.junit.Assert.*
import static org.junit.Assert.assertThat
import org.junit.Ignore
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper

@RunWith(XtextRunner)
class SpecGeneratorTests {
	
	
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

	def List<RepairSpec> getRepairSpecsFromSet(Set<List<RepairSpec>> combinations) {
		val generatedSpecs = new ArrayList<RepairSpec>();
		
		combinations.forEach[ combination |
			generatedSpecs.addAll(combination)	
		]
		
		return generatedSpecs
	}

	///////////////////////////////////////////////////////////////////////////
	//
	//
	// Create Node Rule tests
	//
	//
	///////////////////////////////////////////////////////////////////////////
	
	//
	//
	// 0 .. 1
	//
	//
	//

		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([0..1] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([0..1] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([0..1] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Create node multiplicity pattern ([0..1] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([0..1] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Create node multiplicity pattern ([0..1] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Create node multiplicity pattern ([0..1] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Create node multiplicity pattern ([0..1] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Create node multiplicity pattern ([0..1] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_01_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));			
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}

		
	
	//
	//
	// 0 .. n
	//
	//
	//
	
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([0..n] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([0..n] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([0..n] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Create node multiplicity pattern ([0..n] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([0..n] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Create node multiplicity pattern ([0..n] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Create node multiplicity pattern ([0..n] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Create node multiplicity pattern ([0..n] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Create node multiplicity pattern ([0..n] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0n_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));			
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	//
	//
	// 0 .. *
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([0..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
		
			/*
			 * Create node multiplicity pattern ([0..*] to [0..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([0..*] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Create node multiplicity pattern ([0..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
			/*
			 * Create node multiplicity pattern ([0..*] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
			/*
			 * Create node multiplicity pattern ([0..*] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 2 .. 2
			/*
			 * Create node multiplicity pattern ([0..*] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 2 .. l
			/*
			 * Create node multiplicity pattern ([0..*] to [2..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_2l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 2 .. *
			/*
			 * Create node multiplicity pattern ([0..*] to [2..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_0Star_2Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	//
	//
	// 1 .. 1
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([1..1] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([1..1] to [0..5])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([1..1] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Create node multiplicity pattern ([1..1] to [1..1])
			 */
			@Test
			@Ignore
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([1..1] to [1..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Create node multiplicity pattern ([1..1] to [1..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Create node multiplicity pattern ([1..1] to [k..k])
			 */
			@Test
			@Ignore
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		// k .. l
				
			/*
			 * Create node multiplicity pattern ([1..1] to [k..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR))
				]
				
				println(String.format("Generated rules: %s", combinations))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Create node multiplicity pattern ([1..1] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_11_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	
	//
	//
	// 1 .. n
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([1..n] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
		
			/*
			 * Create node multiplicity pattern ([1..n] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
		
			/*
			 * Create node multiplicity pattern ([1..n] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Create node multiplicity pattern ([1..n] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([1..n] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Create node multiplicity pattern ([1..n] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
		
			/*
			 * Create node multiplicity pattern ([1..n] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Create node multiplicity pattern ([1..n] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		
		// k .. *
	
			/*
			 * Create node multiplicity pattern ([1..n] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1n_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	
	
	//
	//
	// 1 .. *
	//
	//
	//
	
		// 0 .. 1
		
			/*
			 * Create node multiplicity pattern ([1..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
		
			/*
			 * Create node multiplicity pattern ([1..*] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
		
			/*
			 * Create node multiplicity pattern ([1..*] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Create node multiplicity pattern ([1..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([1..*] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Create node multiplicity pattern ([1..*] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// k .. k
	
			/*
			 * Create node multiplicity pattern ([1..*] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. l
	
			/*
			 * Create node multiplicity pattern ([1..*] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Create node multiplicity pattern ([1..*] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_1Star_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	
	//
	//
	// n .. n
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([n..n] to [0..1]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([n..n] to [0..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([n..n] to [0..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Create node multiplicity pattern ([n..n] to [1..1]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
			/*
			 * Create node multiplicity pattern ([n..n] to [1..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
			/*
			 * Create node multiplicity pattern ([n..n] to [1..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
		
			/*
			 * Create node multiplicity pattern ([n..n] to [n..n]
			 */
			@Test
			@Ignore
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_nn_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
			
			/*
			 * Create node multiplicity pattern ([n..n] to [k..k]
			 */
			@Test
			@Ignore
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 3, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		// k .. l
			/*
			 * Create node multiplicity pattern ([n..n] to [k..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
			/*
			 * Create node multiplicity pattern ([n..n] to [k..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nn_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
	//
	//
	// n .. m
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([n..m] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([n..m] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([n..m] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Create node multiplicity pattern ([n..m] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([n..m] to [1..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(3, combinations.length)
			}
		
		
		// 1 .. *
				
			/*
			 * Create node multiplicity pattern ([n..m] to [1..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// k .. k
		
			/*
			 * Create node multiplicity pattern ([n..m] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
			
		// k .. l
		
			/*
			 * Create node multiplicity pattern ([n..m] to [k..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(3, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Create node multiplicity pattern ([n..m] to [k..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nm_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	//
	//
	// n .. *
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([n..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([n..*] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([n..*] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "CREATE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Create node multiplicity pattern ([n..*] to [1..1]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		
		// 1 .. l
		
			/*
			 * Create node multiplicity pattern ([n..*] to [1..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(3, combinations.length)
			}
		
		
		// 1 .. *
		
			/*
			 * Create node multiplicity pattern ([n..*] to [1..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Create node multiplicity pattern ([n..*] to [k..k]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Create node multiplicity pattern ([n..*] to [k..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(3, combinations.length)
			}
		
		// k .. *
		
			/*
			 * Create node multiplicity pattern ([n..*] to [k..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_nStar_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}











	
	//Multiple edges
	//TODO add tests for multiple edges and iterative repairs
	@Test
	@Ignore
	def void assertThatTheCorrectSetOfRulesIsGeneratedWhenCreatingANodeWith_multi_Multiplicity(){
		
		//Original metamodel with 0..* 0..* multiplicities
		this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
		this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
		this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		
		var ruleSpec = new RuleSpec("B", "ALL");
		
		var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
		
		var specGenerator = new SpecsGenerator();
			
		var generatedSpecs = specGenerator.generateNodeCreateRules(ruleSpec, metamodelWrapper);
		
		var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
		
		val node = metamodelWrapper.getNode(ruleSpec.getNode);
		
		val expectedSpecs = new ArrayList<RepairSpec>();
		
		node.EReferences.forEach[edge |
			expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CREATE));
			
		]
		
		println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))

		assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
		assertEquals(1, combinations.length)
	}

	///////////////////////////////////////////////////////////////////////////
	//
	//
	// Delete Node Rule tests
	//
	//
	///////////////////////////////////////////////////////////////////////////
	
	
	
	//
	//
	// 0 .. 1
	//
	//
	//

		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([0..1] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Delete node multiplicity pattern ([0..1] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Delete node multiplicity pattern ([0..1] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Delete node multiplicity pattern ([0..1] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([0..1] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([0..1] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Delete node multiplicity pattern ([0..1] to [k..k])
			 */
			 //TODO This I think is a bug as you can't do single repair, when ub of the opposite is > ub of the target node for repair
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Delete node multiplicity pattern ([0..1] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Delete node multiplicity pattern ([0..1] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_01_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));			
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}

		
	
	//
	//
	// 0 .. n
	//
	//
	//
	
	
		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([0..n] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Delete node multiplicity pattern ([0..n] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Delete node multiplicity pattern ([0..n] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Delete node multiplicity pattern ([0..n] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([0..n] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([0..n] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Delete node multiplicity pattern ([0..n] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Delete node multiplicity pattern ([0..n] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Delete node multiplicity pattern ([0..n] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0n_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));			
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	//
	//
	// 0 .. *
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([0..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
		
			/*
			 * Delete node multiplicity pattern ([0..*] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Delete node multiplicity pattern ([0..*] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Delete node multiplicity pattern ([0..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
			/*
			 * Delete node multiplicity pattern ([0..*] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
			/*
			 * Delete node multiplicity pattern ([0..*] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 2 .. 2
			/*
			 * Delete node multiplicity pattern ([0..*] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// 2 .. l
			/*
			 * Delete node multiplicity pattern ([0..*] to [2..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_2l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 2 .. *
			/*
			 * Delete node multiplicity pattern ([0..*] to [2..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_0Star_2Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 0, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	//
	//
	// 1 .. 1
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([1..1] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Delete node multiplicity pattern ([1..1] to [0..5])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Delete node multiplicity pattern ([1..1] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Delete node multiplicity pattern ([1..1] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([1..1] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([1..1] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Delete node multiplicity pattern ([1..1] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Delete node multiplicity pattern ([1..1] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Delete node multiplicity pattern ([1..1] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_11_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));			
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	
	//
	//
	// 1 .. n
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([1..n] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));	
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Delete node multiplicity pattern ([1..n] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// k .. *
	
			/*
			 * Delete node multiplicity pattern ([1..n] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1n_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));		
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	
	
	//
	//
	// 1 .. *
	//
	//
	//
	
		// 0 .. 1
		
			/*
			 * Delete node multiplicity pattern ([1..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
		
			/*
			 * Delete node multiplicity pattern ([1..*] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
		
			/*
			 * Delete node multiplicity pattern ([1..*] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Delete node multiplicity pattern ([1..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([1..*] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([1..*] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// k .. k
	
			/*
			 * Delete node multiplicity pattern ([1..*] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. l
	
			/*
			 * Delete node multiplicity pattern ([1..*] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Delete node multiplicity pattern ([1..*] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_1Star_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
					
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	
	//
	//
	// n .. n
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Create node multiplicity pattern ([n..n] to [0..1]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Create node multiplicity pattern ([n..n] to [0..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Create node multiplicity pattern ([n..n] to [0..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
			/*
			 * Create node multiplicity pattern ([n..n] to [1..1]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		// 1 .. l
			/*
			 * Create node multiplicity pattern ([n..n] to [1..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. *
			/*
			 * Create node multiplicity pattern ([n..n] to [1..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Create node multiplicity pattern ([n..n] to [k..k]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_nn_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
			
			/*
			 * Create node multiplicity pattern ([n..n] to [k..k]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 3, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(0, combinations.length)
			}
		
		
		// k .. l
			/*
			 * Create node multiplicity pattern ([n..n] to [k..l]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
			/*
			 * Create node multiplicity pattern ([n..n] to [k..*]
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nn_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 2, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ALL");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);
				
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.repairSpecsFromSet))
		
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
	//
	//
	// n .. m
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([n..m] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Delete node multiplicity pattern ([n..m] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Delete node multiplicity pattern ([n..m] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Delete node multiplicity pattern ([n..m] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([n..m] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([n..m] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// k .. k
		
			/*
			 * Delete node multiplicity pattern ([n..m] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
			
		// k .. l
		
			/*
			 * Delete node multiplicity pattern ([n..m] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					//expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
	
			/*
			 * Delete node multiplicity pattern ([n..m] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nm_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, 5, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
	
	
	//
	//
	// n .. *
	//
	//
	//
	
		// 0 .. 1
			/*
			 * Delete node multiplicity pattern ([n..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. l
			/*
			 * Delete node multiplicity pattern ([n..*] to [0..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_0l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 0 .. *
			/*
			 * Delete node multiplicity pattern ([n..*] to [0..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// 1 .. 1
		
			/*
			 * Delete node multiplicity pattern ([n..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// 1 .. l
		
			/*
			 * Delete node multiplicity pattern ([n..*] to [1..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_1l_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		
		// 1 .. *
		
			/*
			 * Delete node multiplicity pattern ([n..*] to [1..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_1Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. k
			/*
			 * Delete node multiplicity pattern ([n..*] to [k..k])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_kk_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR));
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE_LB_REPAIR_MANY));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(2, combinations.length)
			}
		
		// k .. l
		
			/*
			 * Delete node multiplicity pattern ([n..*] to [k..l])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_kl_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, 5, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
		
		// k .. *
		
			/*
			 * Delete node multiplicity pattern ([n..*] to [k..*])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenDeletingANodeWith_nStar_kStar_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 2, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 2, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "DELETE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateNodeDeleteRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.DELETE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
			
			
	///////////////////////////////////////////////////////////////////////////
	//
	//
	// Add Edge Rule tests
	//
	//
	///////////////////////////////////////////////////////////////////////////
	
			/*
			 * Add edge multiplicity pattern ([1..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ADD");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
			
			/*
			 * Add edge multiplicity pattern ([1..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ADD");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
			
			/*
			 * Add edge multiplicity pattern ([1..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenAddingAnEdgeWith_1Star_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ADD");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateEdgeAddRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.ADD));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}


	///////////////////////////////////////////////////////////////////////////
	//
	//
	// Remove Edge Rule tests
	//
	//
	///////////////////////////////////////////////////////////////////////////
	
			/*
			 * Remove edge multiplicity pattern ([1..*] to [0..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_01_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "REMOVE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
			
			/*
			 * Remove edge multiplicity pattern ([1..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_11_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 1, 1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "ADD");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.CHANGE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				assertEquals(1, combinations.length)
			}
			
			/*
			 * Remove edge multiplicity pattern ([1..*] to [1..1])
			 */
			@Test
			def void assertThatTheCorrectSetOfRulesIsGeneratedWhenRemovingAnEdgeWith_1Star_0Star_Multiplicity(){
				
				//Original metamodel with 0..* 0..* multiplicities
				this.multiplicities.add(new Multiplicity("A", "edgeAB", 2, 2, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBA", 2, 3, this.metamodel));
				this.multiplicities.add(new Multiplicity("B", "edgeBC", 0, -1, this.metamodel));
				this.multiplicities.add(new Multiplicity("C", "edgeCB", 1, -1, this.metamodel));
				
				//Refined metamodel wrapper containing the metamodel with the refined multiplicities
				
				var ruleSpec = new RuleSpec("C", "REMOVE");
				
				var metamodelWrapper = new MetamodelWrapper(metamodel, this.multiplicities)
				
				var specGenerator = new SpecsGenerator();
					
				var generatedSpecs = specGenerator.generateEdgeRemoveRules(ruleSpec, metamodelWrapper);		
				var combinations = specGenerator.generateNodeRepairCombinations(generatedSpecs);
				val node = metamodelWrapper.getNode(ruleSpec.getNode);
				val expectedSpecs = new ArrayList<RepairSpec>();
				
				node.EReferences.forEach[edge |
					expectedSpecs.add(new RepairSpec(node, edge, RepairSpecType.REMOVE));
				]
				
				println(String.format("Generated rules: %s", combinations.head))
				
				assertThat(combinations.repairSpecsFromSet, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedSpecs.toArray))
				//assertEquals(1, combinations.length)
			}
			

}