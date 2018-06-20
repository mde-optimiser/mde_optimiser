package uk.ac.kcl.tests.interpreter.objectives.ocl

import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionsFactory
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.tests.TestModelHelper
import uk.ac.kcl.tests.TestModelLoader

import static org.junit.Assert.*
import static org.mockito.Mockito.*

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class OclInterpreterGrammarTests {

	@Inject
	TestModelHelper testModelHelper
	
	@Inject extension ValidationTestHelper
	
	def objectiveGrammarBootstrap(String objective) {
		return '''
			basepath <src/models/ocl/>
			metamodel <architectureCRA.ecore>
			model <model.xmi>
			''' + objective + '''
			mutate using <ABC> unit "XYZ"
			mutate using <CDE> unit "LMN"
			optimisation provider ecj algorithm NSGAII variation mutation evolutions 100 population 100
		'''
	}
	
	@Test
	def void assertThatEmptyOclStringIsInvalid() {
		
		try {
			
			val objectiveSpec = "objective name maximise ocl { \"Class.allInstances()->size()\" }"
			
			val testModel = testModelHelper.getParsedFullValidModel(objectiveGrammarBootstrap(objectiveSpec))
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			val oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(testModel.getObjectives().get(0)))

			oclObjective.computeFitness(mock(EObject))	
			
			testModel.assertNoIssues
			
		} catch(Exception e) {
			assertEquals("The 'no null' constraint is violated", e.getMessage())
		}
	}
	
	@Test
	def void assertThatNonEmptyCorrectOclStringIsValidAndReturnsExpectedFitnessValue() {
			
			val objectiveSpec = "objective name maximise ocl { \"Class.allInstances()->size()\" }"
			
			val testModel = testModelHelper.getParsedFullValidModel(objectiveGrammarBootstrap(objectiveSpec))
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			val oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(testModel.getObjectives().get(0)))

			val testModelLoader = new TestModelLoader(testModel);

			val initialModelObject = new OclModelProvider().initialModels(testModelLoader.getMetamodel()).head
			
			testModel.assertNoIssues
			assertEquals(2.0d, oclObjective.computeFitness(initialModelObject), 0.0)
	}
	
	@Test 
	def void assertThatASemanticExceptionIsThrownForInvalidOcl(){
		
		try {

			val objectiveSpec = "objective name maximise ocl { \"Class.allInstances().size()\" }"
			val testModel = testModelHelper.getParsedFullValidModel(objectiveGrammarBootstrap(objectiveSpec))
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			val oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(testModel.getObjectives().get(0)))

			val testModelLoader = new TestModelLoader(testModel);

			var initialModelObject = new OclModelProvider().initialModels(testModelLoader.getMetamodel()).head
			
			oclObjective.computeFitness(initialModelObject)
			
			testModel.assertNoIssues
			
		} catch(Exception exception){
			assertEquals("Cannot find operation (size()) for the type (Class)", exception.getMessage)
		}
	}
	
	@Test
	def void assertThatOclQueriesWorkWithLetExpressions(){
			
			val objectiveSpec = "objective name maximise ocl { \"let att : String = 'A2' in "+
				"Feature.allInstances()->select(f : Feature | f.name = att)->size()\"}"
			
			val testModel = testModelHelper.getParsedFullValidModel(objectiveGrammarBootstrap(objectiveSpec))
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			val oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(testModel.getObjectives().get(0)))

			val initialModelObject = new OclModelProvider().initialModels(new TestModelLoader(testModel).getMetamodel()).head
			
			testModel.assertNoIssues
			assertEquals(1.0d, oclObjective.computeFitness(initialModelObject), 0.0)
	}
	
}