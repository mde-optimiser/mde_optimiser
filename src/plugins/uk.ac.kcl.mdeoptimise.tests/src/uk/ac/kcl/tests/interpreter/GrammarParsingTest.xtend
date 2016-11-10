package uk.ac.kcl.tests.interpreter

import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.tests.FullTestInjector

import static org.junit.Assert.*
import org.eclipse.xtext.junit4.validation.ValidationTestHelper

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class GrammarParsingTest {

	@Inject
	ParseHelper<Optimisation> parser
	@Inject extension ValidationTestHelper
	
	Optimisation model
	
	@Before
	def void bootstrapParser() {
		 model = parser.parse('''
			basepath <model/basepath>
			metamodel <ABC>
			objective name minimise java { "src/models/fitness/Fitness.java" }
			objective name maximise ocl { "Valid.OclString()" }
			evolve using <ABC> unit "XYZ"
			evolve using <CDE> unit "LMN"
			optimisation provider ecj algorithm nsga-II evolutions 100 population 100
		''')
	}
	
	@Test
	def void assertThatThereAreNoParsingIssues() {
		model.assertNoIssues
	}
	
	@Test
	def void assertParsedOptimisationModelIsNotNull() {
		assertNotNull(model)
	}
	
	
	@Test
	def void assertBasePathIsParsed() {
		assertEquals("model/basepath", model.getBasepath.location)
	}
	
	@Test
	def void assertMetamodelPathIsParsed() {
		assertEquals("ABC", model.getMetamodel.location)
	}
	
	@Test
	def void assertJavaObjectiveSignatureAndSpecIsParsed() {
		
		//First objective JAVA
		assertEquals("Could not get java objective type.", "java", model.objectives.get(0).getObjectiveType())
		assertEquals("Could not get java objective name.", "name", model.objectives.get(0).getObjectiveName())
		assertEquals("Could not get expected java objective path.", "src/models/fitness/Fitness.java", model.objectives.get(0).getObjectiveSpec())
		assertEquals("Could not get java objective tendency.", "minimise", model.objectives.get(0).getObjectiveTendency())
		
	}
	
	@Test
	def void assertOclObjectiveSignatureAndSpecIsParsed() {
		//Second objective OCL
		assertEquals("Could not get ocl objective type.", "ocl", model.objectives.get(1).getObjectiveType())
		assertEquals("Could not get ocl objective name.", "name", model.objectives.get(1).getObjectiveName())
		assertEquals("Could not get expected ocl objective query.", "Valid.OclString()", model.objectives.get(1).getObjectiveSpec())
		assertEquals("Could not get ocl objective tendency.", "maximise", model.objectives.get(1).getObjectiveTendency())
	}
	
	@Test
	def void assertOptimisationProvidersAreParsed() {
		assertEquals("Could not get optimisation algorithm provider framework", "ecj", model.optimisation.algorithmFactory)
		assertEquals("Could not get optimisation algorithm name", "nsga-II", model.optimisation.algorithmName)
		assertEquals("Could not get optimisation population size", 100, model.optimisation.algorithmPopulation)
		assertEquals("Could not get optimisation evolutions number", 100, model.optimisation.algorithmEvolutions)
	}
}