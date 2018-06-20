package uk.ac.kcl.tests.interpreter.objectives

import com.google.inject.Inject
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.tests.TestModelHelper

import static org.junit.Assert.*
import uk.ac.kcl.tests.FullTestInjector
import org.eclipse.xtext.junit4.InjectWith

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class GuidanceFunctionsAdapterTests {

	@Inject
	TestModelHelper testModelHelper

	@Test
	def void assertThatObjectiveInterpreterSpecIsAdaptedCorrectly() {
	
		val oclObjective = "objective objectiveName maximise ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(oclObjective)
		
		val adapter = new GuidanceFunctionAdapter(model.objectives.get(0))

		assertEquals("objectiveName", adapter.functionName)
		assertEquals("maximise", adapter.functionTendency)
		assertEquals("ocl", adapter.functionType)
		assertEquals("Class.allInstances()->size()", adapter.functionSpec)
	}
	
	@Test
	def void assertThatConstraintInterpreterSpecIsAdapterCorrectly() {
		
		val oclConstraint = "constraint constraintName ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints(oclConstraint)
		
		val adapter = new GuidanceFunctionAdapter(model.constraints.get(0))

		assertEquals("constraintName", adapter.functionName)
		assertEquals(null, adapter.functionTendency)
		assertEquals("ocl", adapter.functionType)
		assertEquals("Class.allInstances()->size()", adapter.functionSpec)
	}
	
	@Test
	def void assertThatObjectiveAdapterNumericalTendencyReturnsCorrectlyForMinimise() {
		
		val oclObjective = "objective objectiveName minimise ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(oclObjective)
		
		val adapter = new GuidanceFunctionAdapter(model.objectives.get(0))

		assertEquals(-1, adapter.getNumericalTendency, 0.0)
	}
		
	@Test
	def void assertThatObjectiveAdapterNumericalTendencyReturnsCorrectlyForMaximise() {
		
		val oclObjective = "objective objectiveName maximise ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(oclObjective)
		
		val adapter = new GuidanceFunctionAdapter(model.objectives.get(0))

		assertEquals(1, adapter.getNumericalTendency, 0.0)
	}
	
	@Test
	def void assertThatIsObjectiveReturnsTrueForObjective() {
		val oclObjective = "objective objectiveName maximise ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(oclObjective)
		
		val adapter = new GuidanceFunctionAdapter(model.objectives.get(0))
		
		assertTrue("This is an objective function",adapter.isObjectiveFunction)
	}
	
	@Test
	def void assertThatIsObjectiveReturnsTrueForConstraint() {
		val oclObjective = "constraint constraintName ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints(oclObjective)
		
		val adapter = new GuidanceFunctionAdapter(model.constraints.get(0))
		
		assertFalse("This is not a constraint function",adapter.isObjectiveFunction)
	}
}