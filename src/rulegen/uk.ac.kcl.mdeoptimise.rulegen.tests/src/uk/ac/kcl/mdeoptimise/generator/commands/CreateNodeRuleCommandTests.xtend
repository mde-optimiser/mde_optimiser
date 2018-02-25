package uk.ac.kcl.mdeoptimise.generator.commands

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.Optimisation
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import uk.ac.kcl.mdeoptimise.MetaModelSpec
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelGenerator
import java.util.LinkedList
import org.junit.Test
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.CreateNodeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
import java.util.HashMap
import org.eclipse.emf.ecore.xmi.XMIResource
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import java.util.Stack
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import uk.ac.kcl.mdeoptimise.BasePathSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.CreateNodeLBRepairRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.CreateNodeLBRepairManyRuleCommand

@RunWith(XtextRunner)
class CreateNodeRuleCommandTests {
	
	private HenshinResourceSet henshinResourceSet;
	private EPackage theMetamodel
	Optimisation model;
	
	def getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(model.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.metamodel.location).head
        }

        theMetamodel
    }
    
    //This should be moved to an utils class that can be shared across projects
    def EPackage fakeOptimisationModel(){
    	this.model = mock(Optimisation)
		var basepath = mock(BasePathSpec);
		var metamodel = mock(MetaModelSpec);
		when(basepath.location).thenReturn("src/resources/");
		when(metamodel.location).thenReturn("architectureCRA.ecore")
		
		when(model.basepath).thenReturn(basepath)
		when(model.metamodel).thenReturn(metamodel)
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities)
    }
    
    def writeModel(EObject model, String modelPathPrefix, String modelFilename, EPackage meta, RefinedMetamodelWrapper refinedMetamodel) {
    	
    	var reg = Resource.Factory.Registry.INSTANCE;
		var m = reg.getExtensionToFactoryMap();
		m.put("henshin", new HenshinResourceFactory());
    	
		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		
		val resource = refinedMetamodel.resourceSet.createResource(URI.createFileURI(modelPathPrefix + modelFilename))
		
		if (resource.loaded) {
			resource.contents.clear
		}
		
		resource.contents.add(model)
		resource.save(options)
	}
    
    @Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase1Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_1_rule_1_create_node_a_multiplicity_A01_B01.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase2Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_2_rule_1_create_node_a_multiplicity_A01_B0-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase3Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_3_rule_1_create_node_a_multiplicity_A01_B11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase4Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_4_rule_1_create_node_a_multiplicity_A01_B1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase5Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_5_rule_1_create_node_a_multiplicity_A0-1_B1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase6Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_6_rule_1_create_node_a_multiplicity_A0-1_B0-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase7Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_7_rule_1_create_node_a_multiplicity_A0-1_B11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase8Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_8_rule_1_create_node_a_multiplicity_A0-1_B1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase9Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_9_rule_1_create_node_a_multiplicity_A11_B01.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase10Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_10_rule_1_create_node_a_multiplicity_A11_B01.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase11Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_11_rule_1_create_node_a_multiplicity_A11_B11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase12Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_12_rule_1_create_node_a_multiplicity_A11_B1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase13Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_13_rule_1_create_node_a_multiplicity_A1-1_B01.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase14Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_14_rule_1_create_node_a_multiplicity_A1-1_B0-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
			
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase15Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "case_15_rule_1_create_node_a_multiplicity_A1-1_B11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase16Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_11_b_1l.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase17Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_11_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase18Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_11_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase19Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_11_b_k-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase20Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1n_b_11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase21Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1n_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase22Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1n_b_1l.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase23Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1n_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase24Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1n_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase25Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1n_b_k-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase26Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase27Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase28Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_1l.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase29Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_1l_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase30Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase31Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase32Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_kl_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase33Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 2, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_1-1_b_k-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase34Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 2, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase35Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase36Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_11_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase37Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_1l_lb.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase38Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_1l_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase39Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_1l_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase40Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
			
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase41Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_nm.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase42Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_nm_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase43Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_1l_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase44Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, 5, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_n-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}


	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase45Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
			@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase46Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase47Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_nm_b_11_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase48Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_nm.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
			@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase49Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_nm_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase50Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_nm_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase51Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase52Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_nm.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase53Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_nm_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase54Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, 5, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_nm_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCorrectCreateNodeRulesAreGeneratedForCase55Multiplicity() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 3, -1, getMetamodel);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 3, -1, getMetamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
		
		val metamodels = new Stack();
		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
		
		val metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels)
		
		var createNodeRuleCommand = new CreateNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "create_node_a_n-1_b_n-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	
}