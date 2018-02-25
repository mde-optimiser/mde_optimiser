package uk.ac.kcl.mdeoptimise.generator.commands

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.Optimisation
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import uk.ac.kcl.mdeoptimise.BasePathSpec
import uk.ac.kcl.mdeoptimise.MetaModelSpec
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelGenerator
import java.util.LinkedList
import org.junit.Test
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.CreateNodeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.URI
import java.util.Collections
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
import java.util.HashMap
import org.eclipse.emf.ecore.xmi.XMIResource
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import java.util.Stack
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.DeleteNodeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.DeleteNodeLBRepairRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.DeleteNodeLBRepairManyRuleCommand

@RunWith(XtextRunner)
class DeleteNodeRuleCommandTests {
	
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
	def void assertThatCase1DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_11_b_1l.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase2DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_11_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}

	@Test
	def void assertThatCase3DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_11_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase4DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_11_b_k-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		@Test
	def void assertThatCase5DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_11.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase6DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase7DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_1n.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase8DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase9DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_kl_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase10DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase11DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 5, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1n_b_n-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase12DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase13DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_1l.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase14DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_1l_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCase15DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
		
	@Test
	def void assertThatCase16DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase17DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_kl_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		@Test
	def void assertThatCase18DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_k-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase19DeleteRulesAreGenerated() {
		
		//Original metamodel with 0..* 0..* multiplicities
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1, getMetamodel);
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_1-1_b_k-1_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase20DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase21DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase22DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_1l.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase23DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_1l_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
		
	@Test
	def void assertThatCase24DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase25DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_1-1_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase26DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_nm.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase27DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_nm_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase28DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_n-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	
	@Test
	def void assertThatCase30DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_nm_b_11_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase31DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_1k.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase32DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_1k_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase33DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_1k_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase34DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_1-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase35DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_1-1_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase36DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_1-1_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	@Test
	def void assertThatCase37DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_kl.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase38DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_kl_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase39DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_kl_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	
	
	@Test
	def void assertThatCase40DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_k-1.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase41DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_k-1_lb_repair.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair", module.name);
		assertEquals(1, module.allRules.size);
	}
	
	@Test
	def void assertThatCase42DeleteRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new DeleteNodeLBRepairManyRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/cases/", "delete_node_a_n-1_b_k-1_lb_repair_many.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("DELETE_Node_" + multiplicityA.sourceNode.name + "_Rules_lb_repair_many", module.name);
		assertEquals(1, module.allRules.size);
	}
	
}