package uk.ac.kcl.mdeoptimise.rulegen.tests

import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.Optimisation
import static org.mockito.Mockito.*;
import uk.ac.kcl.mdeoptimise.BasepathSpec
import java.util.List
import uk.ac.kcl.mdeoptimise.rulegen.RulesGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.MetaModelSpec
import java.util.LinkedList
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.tests.utils.MetamodelGenerator

@RunWith(XtextRunner)
class RulegenTests {
	
	
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
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities)
    }
	
	@Test
	def void assertThatCase1RulesAreGenerated() {
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	
	@Test
	def void assertThatCase2RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase3RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	
	@Test
	def void assertThatCase4RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase5RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase6RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase7RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase8RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 0, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase9RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase10RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase11RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase12RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, 1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase13RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase14RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 0, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}
	
	@Test
	def void assertThatCase15RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, 1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}

	@Test
	def void assertThatCase16RulesAreGenerated(){
		
		var metamodel = fakeOptimisationModel()
		
		var multiplicityA = new Multiplicity("Class", "encapsulates", 1, -1);
		var multiplicityB = new Multiplicity("Feature", "isEncapsulatedBy", 1, -1);
		var multiplicities = new LinkedList<Multiplicity>();
		multiplicities.add(multiplicityA);
		multiplicities.add(multiplicityB)
		
		var refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, multiplicities);
		
		var rulesGenerator = new RulesGenerator(refinedMetamodelWrapper.refinedMetamodel, 
												multiplicities)
		rulesGenerator.applyRefinements
	}

}