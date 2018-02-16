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
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.RemoveEdgeRuleCommand

@RunWith(XtextRunner)
class RemoveEdgeRuleCommandTests {
	
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
	def void assertThatCase1RemoveEdgeRulesAreGenerated() {
		
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
		
		var createNodeRuleCommand = new RemoveEdgeRuleCommand(multiplicityA, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser);
		
		val module = createNodeRuleCommand.generate();
		
		writeModel(module, "src/resources/case1/", "case_1_removeEdge.henshin", refinedMetamodelWrapper.refinedMetamodel, refinedMetamodelWrapper)
		
		assertEquals("REMOVE_EdgeRules", module.name);
		assertEquals(1, module.allRules.size);
	}
}