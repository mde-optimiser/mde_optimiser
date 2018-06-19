package uk.ac.kcl.mdeoptimise.rulegen.metamodel.tests

import java.util.LinkedList
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.testing.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.BasePathSpec
import uk.ac.kcl.mdeoptimise.MetaModelSpec
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelGenerator

import static org.junit.Assert.*
import static org.mockito.Mockito.*

@RunWith(XtextRunner)
class MultiplicityTests {
	
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
    
    //TODO This should be moved to an utils class that can be shared across tests perhaps?
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

	/**
	 * The orchestration metamodel does not have any refinements that we need to make
	 */    
    def EPackage fakeServiceCompositionOptimisationModel(){
    	this.model = mock(Optimisation)
		var basepath = mock(BasePathSpec);
		var metamodel = mock(MetaModelSpec);
		when(basepath.location).thenReturn("src/resources/");
		when(metamodel.location).thenReturn("OrchestrationMM.ecore")
		
		when(model.basepath).thenReturn(basepath)
		when(model.metamodel).thenReturn(metamodel)
		
		var multiplicities = new LinkedList<Multiplicity>();
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities)
    }
    
    @Test
	def void assertThatMultiplicityParserReturnsTheCorrectNodesAndEdges() {
		
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		
		assertEquals("Unexpected source node name", "Class", multiplicityA.sourceNode.name);
		assertEquals("Unexpected target node name", "Feature", multiplicityA.targetNode.name);
		assertEquals("Unexpected outgoing reference name", "encapsulates", multiplicityA.EReference.name);
		assertEquals("Unexpected opposite reference name", "isEncapsulatedBy", multiplicityA.EReference.EOpposite.name);
	}
	
	@Test
	def void assertThatMultiplicityRangeValidityReturnsTrueForValidLb(){
		
		assertTrue("Unexpected multiplicity range lb == 0 && ub > lb", Multiplicity.checkMultiplicityRangeValidity(0, 1))
		assertTrue("Unexpected multiplicity range lb == 0 && ub >= lb", Multiplicity.checkMultiplicityRangeValidity(1, 2))
		assertTrue("Unexpected multiplicity range lb > 0 && ub >= lb", Multiplicity.checkMultiplicityRangeValidity(1, 1))
		assertTrue("Unexpected multiplicity range lb = ub = -1", Multiplicity.checkMultiplicityRangeValidity(1, -1))
	}
	
	@Test
	def void assertThatUnidirectionalReferenceReturnsNullEOpposite(){
		
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Method", "dataDependency", 1, 1, getMetamodel);
		
		assertEquals("EOpposite reference for unidirectional reference is null", null, multiplicityA.EReference.EOpposite)
	}
	
	@Test
	def void assertThatBidirectionalReferenceReturnsCorrectReference(){
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		
		assertNotNull("EOpposite reference for bidirectional reference is not null", multiplicityA.EReference.EOpposite)
		assertEquals("EOpposite reference for bidirectional reference has correct name", 
			"isEncapsulatedBy", multiplicityA.EReference.EOpposite.getName)
	}
	
	@Test
	def void assertThatTheCorrectMultiplicityEdgeIsReturned(){
		
		fakeServiceCompositionOptimisationModel()
		
		var multiplicityA = new Multiplicity("Orchestrator", "concreteServices", 1, -1, getMetamodel);
		
		assertNotNull("EReference is not null", multiplicityA.EReference)
		assertEquals("EReference has the correct container", 
			"Orchestrator", multiplicityA.EReference.EContainingClass.name)
	}
}