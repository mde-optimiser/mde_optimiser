package uk.ac.kcl.tests.interpreter.objectives.ocl

import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector

import static org.junit.Assert.*
import static org.mockito.Mockito.*
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionsFactory
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class OclInterpreterGrammarTests {

	@Inject
	ParseHelper<Optimisation> parser
	
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	OclModelProvider oclModelProvider
	
	private HenshinResourceSet henshinResourceSet

    private EPackage theMetamodel
	
	
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
	
	@Test
	def void assertThatEmptyOclStringIsInvalid() {
		
		try {
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			model = parser.parse('''
				basepath <src/models/ocl/>
				metamodel <architectureCRA.ecore>
				objective name maximise ocl { "Class.allInstances()->size()" }
				evolve using <ABC> unit "XYZ"
				evolve using <CDE> unit "LMN"
				optimisation provider ecj algorithm nsga-II evolutions 100 population 100
			''')
			
			var oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
			
			var mockedEObject = mock(EObject)

			oclObjective.computeFitness(mockedEObject)	
			
			model.assertNoIssues
			
		} catch(Exception e) {
			assertEquals("The 'no null' constraint is violated", e.getMessage())
		}
	}
	
	@Test
	def void assertThatNonEmptyCorrectOclStringIsValidAndReturnsExpectedFitnessValue() {
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			oclModelProvider = new OclModelProvider

			model = parser.parse('''
				basepath <src/models/ocl/>
				metamodel <architectureCRA.ecore>
				objective name maximise ocl { "Class.allInstances()->size()" }
				evolve using <ABC> unit "XYZ"
				evolve using <CDE> unit "LMN"
				optimisation provider ecj algorithm NSGAII evolutions 100 population 100
			''')
			
			var oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
			
			var initialModelObject = oclModelProvider.initialModels(getMetamodel()).head
			
			model.assertNoIssues
			assertEquals(2.0d, oclObjective.computeFitness(initialModelObject), 0.0)
	}
	
	@Test 
	def void assertThatASemanticExceptionIsThrownForInvalidOcl(){
		
		try {
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			oclModelProvider = new OclModelProvider

			model = parser.parse('''
				basepath <src/models/ocl/>
				metamodel <architectureCRA.ecore>
				objective name maximise ocl { "Class.allInstances().size()" }
				evolve using <ABC> unit "XYZ"
				evolve using <CDE> unit "LMN"
				optimisation provider ecj algorithm NSGAII evolutions 100 population 100
			''')
			
			var oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
			
			var initialModelObject = oclModelProvider.initialModels(getMetamodel()).head
			
			oclObjective.computeFitness(initialModelObject)
			
			model.assertNoIssues
			
		} catch(Exception exception){
			assertEquals("Cannot find operation (size()) for the type (Class)", exception.getMessage)
		}
	}
	
	@Test
	def void assertThatOclQueriesWorkWithLetExpressions(){
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			oclModelProvider = new OclModelProvider

			model = parser.parse('''
				basepath <src/models/ocl/>
				metamodel <architectureCRA.ecore>
				objective name maximise ocl { 
					"let att : String = 'A2' in Feature.allInstances()->select(f : Feature | f.name = att)->size()"
				}
				evolve using <ABC> unit "XYZ"
				evolve using <CDE> unit "LMN"
				optimisation provider ecj algorithm NSGAII evolutions 100 population 100
			''')
			
			var oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
			
			var initialModelObject = oclModelProvider.initialModels(getMetamodel()).head
			
			model.assertNoIssues
			assertEquals(1.0d, oclObjective.computeFitness(initialModelObject), 0.0)
	}
	
}