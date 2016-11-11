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
import uk.ac.kcl.interpreter.objectives.ObjectivesFactory
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.tests.interpreter.objectives.ocl.OclModelProvider

import static org.hamcrest.CoreMatchers.instanceOf
import static org.junit.Assert.*
import static org.mockito.Mockito.*
import uk.ac.kcl.interpreter.objectives.ocl.OclFitnessFunction
import java.io.InvalidObjectException

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class ObjectivesFactoryTests {

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
			optimisation provider moea algorithm nsga-II evolutions 2000 population 100
		''')
	
		oclModelProvider = new OclModelProvider	
	}
	
	@Test
	def void assertThatThereAreNoGrammarIssues(){
		model.assertNoIssues
	}
	
	@Test
	def void assertThatObjectivesFactoryReturnsTheCorrectObjectiveTypeGivenAJavaObjective(){
		
		val objectivesFactory = new ObjectivesFactory();
		
		var javaObjective = objectivesFactory.loadObjective(model.getObjectives().get(0))
		
		assertThat("Produced objectives factory for java objective type is an instance of the Java fitness class.", 
			javaObjective, instanceOf(JavaObjectiveFunction)
		)
	}
	
	@Test
	def void assertThatObjectivesFactoryReturnsTheCorrectObjectiveTypeGivenAnOclObjective(){
		
		val objectivesFactory = new ObjectivesFactory();
		
		var oclObjective = objectivesFactory.loadObjective(model.getObjectives().get(1))
		
		assertThat("Produced objectives factory for ocl objective type is an instance of the OCL fitness class.", 
			oclObjective, instanceOf(OclFitnessFunction)
		)
	}
	
	@Test
	def void assertThatReturnedJavaFunctionReturnsExpectedFitnessValue(){
		
		val objectivesFactory = new ObjectivesFactory();
		
		var javaObjective = objectivesFactory.loadObjective(model.getObjectives().get(0))
		
		var mockedEObject = mock(EObject)
		
		assertEquals(5.0, javaObjective.computeFitness(mockedEObject), 0.0)
	}
	
	@Test
	def void assertThatInvalidJavaObejectivePathThrowsAnException(){
		
		try {
			val objectivesFactory = new ObjectivesFactory();
			
			var objectiveInterpreter = model.getObjectives().get(0);
			objectiveInterpreter.objectiveSpec = "random-class";
	
			objectivesFactory.loadObjective(model.getObjectives().get(0))
		} catch(ClassNotFoundException e) {
			assertEquals("Invalid objective class path: random-class", e.getMessage())
		}
	}
	
	@Test
	def void assertThatForAnUnknownObjectiveTypeAnExceptionIsThrown() {
		
		try {
			val objectivesFactory = new ObjectivesFactory();
			
			var objectiveInterpreter = model.getObjectives().get(0);
			objectiveInterpreter.objectiveType = "random";
	
			objectivesFactory.loadObjective(model.getObjectives().get(0))
		} catch(InvalidObjectException e) {
			assertEquals("Invalid objective type: random", e.getMessage())
		}
		
	}
	
}