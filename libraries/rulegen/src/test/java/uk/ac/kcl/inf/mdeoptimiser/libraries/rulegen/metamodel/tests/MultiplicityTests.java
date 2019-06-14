package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.tests;

import java.util.LinkedList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.BasePathSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MetaModelSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ProblemSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils.MetamodelGenerator;

@ExtendWith(InjectionExtension.class)
public class MultiplicityTests {
	
	HenshinResourceSet henshinResourceSet;
	EPackage theMetamodel;
	Optimisation model;
	
	public HenshinResourceSet getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(model.getProblem().getBasepath().getLocation());
        }

        return this.henshinResourceSet;
    }

  public EPackage getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = this.getResourceSet()
                    .registerDynamicEPackages(model.getProblem().getMetamodel().getLocation()).get(0);
        }

        return theMetamodel;
    }
    
    //TODO This should be moved to an utils class that can be shared across tests perhaps?
    public EPackage fakeOptimisationModel(){
    	this.model = mock(Optimisation.class);
		var basepath = mock(BasePathSpec.class);
		var metamodel = mock(MetaModelSpec.class);
		var problem = mock(ProblemSpec.class);
		
		when(basepath.getLocation()).thenReturn("src/test/resources/");
		when(metamodel.getLocation()).thenReturn("architectureCRA.ecore");
		
		when(model.getProblem()).thenReturn(problem);
		when(model.getProblem().getBasepath()).thenReturn(basepath);
		when(model.getProblem().getMetamodel()).thenReturn(metamodel);
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1, getMetamodel());
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1, getMetamodel());
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB);
		
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities);
    }

	/**
	 * The orchestration metamodel does not have any refinements that we need to make
	 */
  public EPackage fakeServiceCompositionOptimisationModel(){
    	this.model = mock(Optimisation.class);
		var basepath = mock(BasePathSpec.class);
		var metamodel = mock(MetaModelSpec.class);
		
		var problem = mock(ProblemSpec.class);
		
		when(basepath.getLocation()).thenReturn("src/test/resources/");
		when(metamodel.getLocation()).thenReturn("OrchestrationMM.ecore");
		
		when(model.getProblem()).thenReturn(problem);
		when(model.getProblem().getBasepath()).thenReturn(basepath);
		when(model.getProblem().getMetamodel()).thenReturn(metamodel);
		
		var multiplicities = new LinkedList<Multiplicity>();
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities);
    }
    
    @Test
    public void assertThatMultiplicityParserReturnsTheCorrectNodesAndEdges() {
		
		fakeOptimisationModel();
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel());
		
		assertEquals("Class", multiplicityA.sourceNode().getName(), "Unexpected source node name");
		assertEquals("Feature", multiplicityA.targetNode().getName(), "Unexpected target node name");
		assertEquals("encapsulates", multiplicityA.getEReference().getName(), "Unexpected outgoing reference name");
		assertEquals("isEncapsulatedBy", multiplicityA.getEReference().getEOpposite().getName(), "Unexpected opposite reference name");
	}
	
	@Test
  public void assertThatMultiplicityRangeValidityReturnsTrueForValidLb(){
		
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(0, 1), "Unexpected multiplicity range lb == 0 && ub > lb");
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(1, 2), "Unexpected multiplicity range lb == 0 && ub >= lb");
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(1, 1), "Unexpected multiplicity range lb > 0 && ub >= lb");
		assertTrue(Multiplicity.checkMultiplicityRangeValidity(1, -1), "Unexpected multiplicity range lb = ub = -1");
	}
	
	@Test
  public void assertThatUnidirectionalReferenceReturnsNullEOpposite(){
		
		fakeOptimisationModel();
		
		var multiplicityA = new Multiplicity("Method", "dataDependency", 1, 1, getMetamodel());
		
		assertEquals(null, multiplicityA.getEReference().getEOpposite(), "EOpposite reference for unidirectional reference is null");
	}
	
	@Test
	public void assertThatBidirectionalReferenceReturnsCorrectReference(){
		fakeOptimisationModel();
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1, getMetamodel());
		
		assertNotNull(multiplicityA.getEReference().getEOpposite(), "EOpposite reference for bidirectional reference is not null");
		assertEquals("isEncapsulatedBy", multiplicityA.getEReference().getEOpposite().getName(),
		"EOpposite reference for bidirectional reference has correct name");
	}
	
	@Test
	public void assertThatTheCorrectMultiplicityEdgeIsReturned(){
		
		fakeServiceCompositionOptimisationModel();
		
		var multiplicityA = new Multiplicity("Orchestrator", "concreteServices", 1, -1, getMetamodel());
		
		assertNotNull(multiplicityA.getEReference(), "EReference is not null");
		assertEquals("Orchestrator", multiplicityA.getEReference().getEContainingClass().getName(), "EReference has the correct container");
	}
}
