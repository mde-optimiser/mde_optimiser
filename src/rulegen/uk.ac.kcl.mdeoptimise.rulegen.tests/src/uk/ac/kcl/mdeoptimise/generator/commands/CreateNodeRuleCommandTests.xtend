package uk.ac.kcl.mdeoptimise.generator.commands

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.Optimisation
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import uk.ac.kcl.mdeoptimise.BasepathSpec
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
		var basepath = mock(BasepathSpec);
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
	def void assertThatCase1CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_1_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase2CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_2_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	
	@Test
	def void assertThatCase3CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_3_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase4CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_4_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		@Test
	def void assertThatCase5CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_5_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase6CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_6_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase7CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_7_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCase8CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_8_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase9CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_9_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase10CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_10_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase11CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_11_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase12CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_12_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCase13CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_13_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCase14CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_14_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
			
	@Test
	def void assertThatCase15CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_15_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase16CreateNodeRulesAreGenerated() {
		
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
		
		writeModel(module, "src/resources/cases/", "case_16_rule_1_create_node_a.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("CREATE_Node_Class_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
}