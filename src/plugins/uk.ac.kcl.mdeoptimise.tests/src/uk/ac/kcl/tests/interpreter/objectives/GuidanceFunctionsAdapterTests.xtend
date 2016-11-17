package uk.ac.kcl.tests.interpreter.objectives

import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Before
import org.junit.runner.RunWith
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector

import static org.junit.Assert.*
import org.junit.Test

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class GuidanceFunctionsAdapterTests {

	@Inject
	ParseHelper<Optimisation> parser
	
	Optimisation model
	

	@Before
	def void bootstrapParser() {
		model = parser.parse('''
			basepath <model/basepath>
			metamodel <ABC>
			objective name minimise java { "uk.ac.kcl.tests.interpreter.objectives.JavaObjectiveFunction" }
			objective name maximise ocl { "Valid.OclString()" }
			constraint name java { "models.moea.MinimiseClasslessFeatures" }
			evolve using <ABC> unit "XYZ"
			evolve using <CDE> unit "LMN"
			optimisation provider moea algorithm NSGAII evolutions 2000 population 100
		''')
	
	}
	
	@Test
	def void assertThatObjectiveInterpreterSpecIsAdaptedCorrectly() {
		
		var adapter = new GuidanceFunctionAdapter(model.objectives.get(0))

		assertEquals("name", adapter.functionName)
		assertEquals("minimise", adapter.functionTendency)
		assertEquals("java", adapter.functionType)
		assertEquals("uk.ac.kcl.tests.interpreter.objectives.JavaObjectiveFunction", adapter.functionSpec)
	}
	
	@Test
	def void assertThatConstraintInterpreterSpecIsAdapterCorrectly() {
		
		var adapter = new GuidanceFunctionAdapter(model.constraints.get(0))

		assertEquals("name", adapter.functionName)
		assertEquals(null, adapter.functionTendency)
		assertEquals("java", adapter.functionType)
		assertEquals("models.moea.MinimiseClasslessFeatures", adapter.functionSpec)
	}
	
	@Test
	def void assertThatNumericalTendencyReturnsCorrectlyForMinimise() {
		
		var adapter = new GuidanceFunctionAdapter(model.objectives.get(0))

		assertEquals(-1, adapter.getNumericalTendency, 0.0)
	}
		
	@Test
	def void assertThatNumericalTendencyReturnsCorrectlyForMaximise() {
		
		var adapter = new GuidanceFunctionAdapter(model.objectives.get(1))

		assertEquals(1, adapter.getNumericalTendency, 0.0)
	}
	
}