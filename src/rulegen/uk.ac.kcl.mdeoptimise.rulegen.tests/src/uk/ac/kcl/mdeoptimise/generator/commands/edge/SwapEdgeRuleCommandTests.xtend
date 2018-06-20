//package uk.ac.kcl.mdeoptimise.generator.commands.edge
//
//import java.util.HashMap
//import java.util.LinkedList
//import java.util.Stack
//import org.eclipse.emf.common.util.URI
//import org.eclipse.emf.ecore.EObject
//import org.eclipse.emf.ecore.EPackage
//import org.eclipse.emf.ecore.resource.Resource
//import org.eclipse.emf.ecore.xmi.XMIResource
//import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
//import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
//import org.eclipse.xtext.junit4.XtextRunner
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
//import uk.ac.kcl.mdeoptimise.BasePathSpec
//import uk.ac.kcl.mdeoptimise.MetaModelSpec
//import uk.ac.kcl.mdeoptimise.Optimisation
//import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
//import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
//import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelGenerator
//
//import static org.junit.Assert.*
//import static org.mockito.Mockito.*
//import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.SwapEdgeRuleCommand
//
//@RunWith(XtextRunner)
//class SwapEdgeRuleCommandTests {
//	
//	private HenshinResourceSet henshinResourceSet;
//	private EPackage theMetamodel
//	Optimisation model;
//	
//	def getResourceSet() {
//        if (henshinResourceSet == null) {
//            henshinResourceSet = new HenshinResourceSet(model.basepath.location)
//        }
//
//        henshinResourceSet
//    }
//
//    def getMetamodel() {
//        if (theMetamodel == null) {
//            theMetamodel = getResourceSet.registerDynamicEPackages(model.metamodel.location).head
//        }
//
//        theMetamodel
//    }
//    
//    //This should be moved to an utils class that can be shared across projects
//    def EPackage fakeOptimisationModel(){
//    	this.model = mock(Optimisation)
//		var basepath = mock(BasePathSpec);
//		var metamodel = mock(MetaModelSpec);
//		when(basepath.location).thenReturn("src/resources/");
//		when(metamodel.location).thenReturn("architectureCRA.ecore")
//		
//		when(model.basepath).thenReturn(basepath)
//		when(model.metamodel).thenReturn(metamodel)
//		
//		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel);
//		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel);
//		var multiplicities = new LinkedList<Multiplicity>();
//		multiplicities.add(multiplicityA);
//		multiplicities.add(multiplicityB)
//		
//		return new MetamodelGenerator(getMetamodel()).generate(multiplicities)
//    }
//    
//    def writeModel(EObject model, String modelPathPrefix, String modelFilename, EPackage meta, RefinedMetamodelWrapper refinedMetamodel) {
//    	
//    	var reg = Resource.Factory.Registry.INSTANCE;
//		var m = reg.getExtensionToFactoryMap();
//		m.put("henshin", new HenshinResourceFactory());
//    	
//		var options = new HashMap<String, Boolean>();
//		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
//		
//		val resource = refinedMetamodel.resourceSet.createResource(URI.createFileURI(modelPathPrefix + modelFilename))
//		
//		if (resource.loaded) {
//			resource.contents.clear
//		}
//		
//		resource.contents.add(model)
//		resource.save(options)
//	}
//    
//    @Test
//	def void assertThatCase1AddEdgeRulesAreGenerated() {
//		
//		//Original metamodel with 0..* 0..* multiplicities
//		fakeOptimisationModel()
//		
//		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
//		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1, getMetamodel);
//		
//		var multiplicities = new LinkedList<Multiplicity>();
//		multiplicities.add(multiplicityA);
//		multiplicities.add(multiplicityB);
//		
//		//Refined metamodel wrapper containing the metamodel with the refined multiplicities
//		val refinedMetamodelWrapper = new RefinedMetamodelWrapper(getMetamodel, multiplicities)
//		
//		val metamodels = new Stack();
//		metamodels.add(refinedMetamodelWrapper.refinedMetamodel);
//		
//		val metamodelAnalyser = new EClassifierInfoManagement();
//		metamodelAnalyser.gatherInformation(false, metamodels)
//		
//		var node = refinedMetamodelWrapper.getNode("Class");
//		var edge = refinedMetamodelWrapper.getEdge("Class", "encapsulates");
//		
//		var createNodeRuleCommand = new SwapEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser);
//		
//		val module = createNodeRuleCommand.generate();
//		
//		writeModel(module, "src/resources/cases/", "case_1_swapEdge.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
//		
//		assertEquals("Swap_encapsulates_edge_for_Class_rules", module.name);
//		assertEquals(1, module.allRules.size);
//	}
//}