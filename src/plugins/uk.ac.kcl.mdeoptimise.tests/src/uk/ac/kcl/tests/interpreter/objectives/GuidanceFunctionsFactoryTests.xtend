package uk.ac.kcl.tests.interpreter.objectives

import java.io.InvalidObjectException
import javax.inject.Inject
import models.java.JavaObjectiveFunction
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionsFactory
import uk.ac.kcl.interpreter.guidance.ocl.OclGuidanceFunction
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.tests.TestModelHelper

import static org.hamcrest.CoreMatchers.instanceOf
import static org.junit.Assert.*
import static org.mockito.Mockito.*

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class GuidanceFunctionsFactoryTests {

	@Inject TestModelHelper testModelHelper
	
	// Objectives
	@Test
	def void assertThatGuidanceFunctionsFactoryReturnsTheCorrectObjectiveTypeGivenAJavaObjective() {
		val javaObjective = "objective name maximise java { \"models.java.JavaObjectiveFunction\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(javaObjective)
		
		val objectivesFactory = new GuidanceFunctionsFactory();
		
		val javaObjectiveFunction = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		
		assertThat("Produced guidance function for a Java objective spec has type of the Java fitness class.", 
			javaObjectiveFunction, instanceOf(models.java.JavaObjectiveFunction)
		)
	}
	
	@Test
	def void assertThatGuidanceFunctionsFactoryReturnsTheCorrectObjectiveTypeGivenAnOclObjective() {
		
		val oclObjective = "objective name maximise ocl { \"Class.allInstances()->size()\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(oclObjective)
		
		val objectivesFactory = new GuidanceFunctionsFactory();
		
		val oclObjectiveFunction = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		
		assertThat("Produced objectives factory for ocl objective type is an instance of the OCL fitness class.", 
			oclObjectiveFunction, instanceOf(uk.ac.kcl.interpreter.guidance.ocl.OclGuidanceFunction)
		)
	}
	
	@Test
	def void assertThatReturnedJavaFunctionReturnsExpectedFitnessValue() {
		
		val javaObjective = "objective name maximise java { \"models.java.JavaObjectiveFunction\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(javaObjective)
		
		val objectivesFactory = new GuidanceFunctionsFactory();
		
		val javaObjectiveFunction = objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		
		val mockedEObject = mock(EObject)
		
		assertEquals(5.0, javaObjectiveFunction.computeFitness(mockedEObject), 0.0)
	}
	
	@Test
	def void assertThatInvalidJavaObejectivePathThrowsAnException() {
		
		try {
			val invalidJavaObjective = "objective brokenObjective minimise java { \"invalid-path\" }"
			
			val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(invalidJavaObjective)
			
			val objectivesFactory = new GuidanceFunctionsFactory();
			
			objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		} catch(ClassNotFoundException e) {
			assertEquals("Invalid objective class path: invalid-path", e.getMessage())
		}
	}
	
	@Test
	def void assertThatForAnUnknownObjectiveTypeAnExceptionIsThrown() {
		
		try {
			
			val invalidJavaObjective = "objective brokenObjective minimise java { \"models.java.JavaObjectiveFunction\" }"
			
			val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(invalidJavaObjective)

			val objectivesFactory = new GuidanceFunctionsFactory();
	
			objectivesFactory.loadFunction(new GuidanceFunctionAdapter(model.getObjectives().get(0)))
		} catch(InvalidObjectException e) {
			assertEquals("Invalid objective type: random", e.getMessage())
		}
		
	}
	
	//Constraints
	
	@Test
	def void assertThatGuidanceFunctionsFactoryReturnsTheCorrectConstraintTypeGivenAJavaConstraint() {
		
		val javaConstraint = "constraint name maximise java { \"models.java.JavaObjectiveFunction\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints(javaConstraint)
		
		val constraintsFactory = new GuidanceFunctionsFactory();
		
		val javaConstraintFunction = constraintsFactory.loadFunction(new GuidanceFunctionAdapter(model.getConstraints().get(0)))
		
		assertThat("Produced guidance function for a Java constraint spec has type of the Java fitness class",
			javaConstraintFunction, instanceOf(models.java.JavaObjectiveFunction)
		)
	}
	
	@Test
	def void assertThatGuidanceFunctionsFactoryReturnsTheCorrectConstraintTypeGivenAOclConstraint() {
		
		val oclConstraint = "constraint name ocl { \"Class.allInstances()->size()\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints(oclConstraint)
		
		val constraintsFactory = new GuidanceFunctionsFactory();
		
		val oclConstraintFunction = constraintsFactory.loadFunction(new GuidanceFunctionAdapter(model.getConstraints().get(0)))
		
		assertThat("Produced guidance function for an Ocl constraint spec has type of the Ocl constraint class",
			oclConstraintFunction, instanceOf(uk.ac.kcl.interpreter.guidance.ocl.OclGuidanceFunction)
		)
	}	
}