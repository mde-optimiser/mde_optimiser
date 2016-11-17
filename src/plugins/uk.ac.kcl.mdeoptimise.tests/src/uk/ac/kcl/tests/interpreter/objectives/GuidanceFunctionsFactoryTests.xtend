package uk.ac.kcl.tests.interpreter.objectives

import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.tests.interpreter.objectives.ocl.OclModelProvider

import static org.hamcrest.CoreMatchers.instanceOf
import static org.junit.Assert.*
import static org.mockito.Mockito.*
import java.io.InvalidObjectException
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionsFactory
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.interpreter.guidance.ocl.OclGuidanceFunction

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class GuidanceFunctionsFactoryTests {

	@Inject
	ParseHelper<Optimisation> parser
	
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	OclModelProvider oclModelProvider

	@Before
	def void bootstrapParser() {
		model = parser.parse('''
			basepath <model/basepath>
			metamodel <ABC>
			objective name minimise java { "uk.ac.kcl.tests.interpreter.objectives.JavaObjectiveFunction" }
			objective name maximise ocl { "Valid.OclString()" }
			evolve using <ABC> unit "XYZ"
			evolve using <CDE> unit "LMN"
			optimisation provider moea algorithm NSGAII evolutions 2000 population 100
		''')
	
		oclModelProvider = new OclModelProvider	
	}
	
	@Test
	def void assertThatThereAreNoGrammarIssues(){
		model.assertNoIssues
	}
	
	@Test
	def void assertThatGuidanceFunctionsFactoryReturnsTheCorrectObjectiveTypeGivenAJavaObjective(){
		
		val objectivesFactory = new GuidanceFunctionsFactory();
		
		var javaObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		
		assertThat("Produced objectives factory for java objective type is an instance of the Java fitness class.", 
			javaObjective, instanceOf(JavaObjectiveFunction)
		)
	}
	
	@Test
	def void assertThatGuidanceFunctionsFactoryReturnsTheCorrectObjectiveTypeGivenAnOclObjective(){
		
		val objectivesFactory = new GuidanceFunctionsFactory();
		
		var oclObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(1)))
		
		assertThat("Produced objectives factory for ocl objective type is an instance of the OCL fitness class.", 
			oclObjective, instanceOf(OclGuidanceFunction)
		)
	}
	
	@Test
	def void assertThatReturnedJavaFunctionReturnsExpectedFitnessValue(){
		
		val objectivesFactory = new GuidanceFunctionsFactory();
		
		var javaObjective = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		
		var mockedEObject = mock(EObject)
		
		assertEquals(5.0, javaObjective.computeFitness(mockedEObject), 0.0)
	}
	
	@Test
	def void assertThatInvalidJavaObejectivePathThrowsAnException(){
		
		try {
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			var objectiveInterpreter = model.getObjectives().get(0);
			objectiveInterpreter.objectiveSpec = "random-class";
	
			objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		} catch(ClassNotFoundException e) {
			assertEquals("Invalid objective class path: random-class", e.getMessage())
		}
	}
	
	@Test
	def void assertThatForAnUnknownObjectiveTypeAnExceptionIsThrown() {
		
		try {
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			var objectiveInterpreter = model.getObjectives().get(0);
			objectiveInterpreter.objectiveType = "random";
	
			objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		} catch(InvalidObjectException e) {
			assertEquals("Invalid objective type: random", e.getMessage())
		}
		
	}
	
}