package uk.ac.kcl.mdeoptimise.rulegen.metamodel.tests

import java.util.LinkedList
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.BasepathSpec
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
}