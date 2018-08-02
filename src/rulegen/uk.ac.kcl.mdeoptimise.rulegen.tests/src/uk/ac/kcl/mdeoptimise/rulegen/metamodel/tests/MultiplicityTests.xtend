package uk.ac.kcl.mdeoptimise.rulegen.metamodel.tests

import java.util.LinkedList
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelGenerator
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.mdeoptimise.MetaModelSpec
import uk.ac.kcl.mdeoptimise.BasePathSpec
import org.junit.jupiter.api.^extension.ExtendWith
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import uk.ac.kcl.mdeoptimise.ProblemSpec

@ExtendWith(InjectionExtension)
class MultiplicityTests {
	
	HenshinResourceSet henshinResourceSet;
	private EPackage theMetamodel
	Optimisation model;
	
	def getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(model.problem.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.problem.metamodel.location).head
        }

        theMetamodel
    }
    
    //TODO This should be moved to an utils class that can be shared across tests perhaps?
    def EPackage fakeOptimisationModel(){
    	this.model = mock(Optimisation)
		var basepath = mock(BasePathSpec);
		var metamodel = mock(MetaModelSpec);
		var problem = mock(ProblemSpec);
		
		when(basepath.location).thenReturn("src/resources/");
		when(metamodel.location).thenReturn("architectureCRA.ecore")
		
		when(model.problem).thenReturn(problem)
		when(model.problem.basepath).thenReturn(basepath)
		when(model.problem.metamodel).thenReturn(metamodel)
		
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
		
		var problem = mock(ProblemSpec);
		
		when(basepath.location).thenReturn("src/resources/");
		when(metamodel.location).thenReturn("OrchestrationMM.ecore")
		
		when(model.problem).thenReturn(problem)
		when(model.problem.basepath).thenReturn(basepath)
		when(model.problem.metamodel).thenReturn(metamodel)
		
		var multiplicities = new LinkedList<Multiplicity>();
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities)
    }
    
    @Test
	def void assertThatMultiplicityParserReturnsTheCorrectNodesAndEdges() {
		
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		
		assertEquals("Class", multiplicityA.sourceNode.name, "Unexpected source node name")
		assertEquals("Feature", multiplicityA.targetNode.name, "Unexpected target node name");
		assertEquals("encapsulates", multiplicityA.EReference.name, "Unexpected outgoing reference name");
		assertEquals("isEncapsulatedBy", multiplicityA.EReference.EOpposite.name, "Unexpected opposite reference name");
	}
	
	@Test
	def void assertThatMultiplicityRangeValidityReturnsTrueForValidLb(){
		
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(0, 1), "Unexpected multiplicity range lb == 0 && ub > lb")
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(1, 2), "Unexpected multiplicity range lb == 0 && ub >= lb")
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(1, 1), "Unexpected multiplicity range lb > 0 && ub >= lb")
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(1, -1), "Unexpected multiplicity range lb = ub = -1")
	}
	
	@Test
	def void assertThatUnidirectionalReferenceReturnsNullEOpposite(){
		
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Method", "dataDependency", 1, 1, getMetamodel);
		
		assertEquals(null, multiplicityA.EReference.EOpposite, "EOpposite reference for unidirectional reference is null")
	}
	
	@Test
	def void assertThatBidirectionalReferenceReturnsCorrectReference(){
		fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel);
		
		assertNotNull(multiplicityA.EReference.EOpposite, "EOpposite reference for bidirectional reference is not null")
		assertEquals("isEncapsulatedBy", multiplicityA.EReference.EOpposite.getName, 
		"EOpposite reference for bidirectional reference has correct name")
	}
	
	@Test
	def void assertThatTheCorrectMultiplicityEdgeIsReturned(){
		
		fakeServiceCompositionOptimisationModel()
		
		var multiplicityA = new Multiplicity("Orchestrator", "concreteServices", 1, -1, getMetamodel);
		
		assertNotNull(multiplicityA.EReference, "EReference is not null")
		assertEquals("Orchestrator", multiplicityA.EReference.EContainingClass.name, "EReference has the correct container")
	}
}