package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import java.util.List
import static org.mockito.Mockito.*
import java.util.LinkedList
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.BasePathSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MetaModelSpec
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ProblemSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity

class MetamodelLoader {
	
	HenshinResourceSet henshinResourceSet;
	EPackage theMetamodel
	Optimisation model;
	
	def getResourceSet() {
        if (henshinResourceSet === null) {
            henshinResourceSet = new HenshinResourceSet(model.problem.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel === null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.problem.metamodel.location).head
        }

        theMetamodel
    }
    
    def EPackage fakeOptimisationModel(String metamodelName, List<Multiplicity> multiplicities){
    	this.model = mock(Optimisation)
		var basepath = mock(BasePathSpec);
		var metamodel = mock(MetaModelSpec);
		var problem = mock(ProblemSpec);
		
		when(model.problem).thenReturn(problem)
		when(basepath.location).thenReturn("src/test/resources/");
		when(metamodel.location).thenReturn(metamodelName)
		
		when(model.problem.basepath).thenReturn(basepath)
		when(model.problem.metamodel).thenReturn(metamodel)
		
		
		return new MetamodelGenerator(getMetamodel()).generate(multiplicities)
    }
    
    def EPackage fakeCraMetamodel(){
    	
		var multiplicities = new LinkedList<Multiplicity>();

    	return this.fakeOptimisationModel("architectureCRA.ecore", multiplicities);
    }
    
    def EPackage fakeSprintPlaningMetamodel(){
    	
    	var multiplicities = new LinkedList<Multiplicity>();
    	
    	return this.fakeOptimisationModel("SprintPlanning.ecore", multiplicities);
    }
    
    def EPackage fakeOrchestrationMetamodel(){
    	
    	var multiplicities = new LinkedList<Multiplicity>();
		
    	return this.fakeOptimisationModel("OrchestrationMM.ecore", multiplicities);
    }
    
    def EPackage fakeNRPMetamodel(){
    	
    	var multiplicities = new LinkedList<Multiplicity>();
		
    	return this.fakeOptimisationModel("nextReleaseProblem.ecore", multiplicities);
    }
    
    def EPackage fakeGenericMetamodel(){
    	
    	var multiplicities = new LinkedList<Multiplicity>();
		
    	return this.fakeOptimisationModel("rulegen.ecore", multiplicities);
    }
}
