package uk.ac.kcl.tests.interpreter

import javax.inject.Inject
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import uk.ac.kcl.tests.FullTestInjector
import uk.ac.kcl.tests.TestModelHelper

import static org.junit.Assert.*
import org.junit.Ignore

@RunWith(XtextRunner)
@InjectWith(FullTestInjector)
class GrammarParsingTests {

	@Inject extension ValidationTestHelper
	@Inject TestModelHelper testModelHelper
	
	@Test
	@Ignore
	def void assertThatThereAreNoParsingIssues() {	
		val model = testModelHelper.parsedFullValidModel
		model.assertNoIssues
	}
	
	@Test
	@Ignore
	def void assertParsedOptimisationModelIsNotNull() {
		var model = testModelHelper.parsedFullValidModel
		assertNotNull(model)
	}
	
	@Test
	@Ignore
	def void assertBasePathIsParsed() {
		val model = testModelHelper.parsedFullValidModel
		assertEquals("src/models/cra/", model.getBasepath.location)
	}
	
	@Test
	@Ignore
	def void assertMetamodelPathIsParsed() {
		
		val model = testModelHelper.parsedFullValidModel
		
		assertEquals("architectureCRA.ecore", model.getMetamodel.location)
	}
	
	@Test
	@Ignore
	def void assertJavaObjectiveSignatureAndSpecIsParsed() {
		
		val javaObjective = "objective name minimise java { \"models.fitness.Fitness.java\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(javaObjective)
		
		//First objective JAVA
		assertEquals("Could not get java objective type.", "java", model.objectives.get(0).getObjectiveType())
		assertEquals("Could not get java objective name.", "name", model.objectives.get(0).getObjectiveName())
		assertEquals("Could not get expected java objective path.", "models.fitness.Fitness.java", model.objectives.get(0).getObjectiveSpec())
		assertEquals("Could not get java objective tendency.", "minimise", model.objectives.get(0).getObjectiveTendency())
		
	}
	
	@Test
	@Ignore
	def void assertOclObjectiveSignatureAndSpecIsParsed() {
		
		val oclObjective = "objective name maximise ocl { \"Class.allInstances()->size()\""
		
		val model = testModelHelper.getParsedFullValidModelWithCustomObjectives(oclObjective)
		
		//Second objective OCL
		assertEquals("Could not get ocl objective type.", "ocl", model.objectives.get(0).getObjectiveType())
		assertEquals("Could not get ocl objective name.", "name", model.objectives.get(0).getObjectiveName())
		assertEquals("Could not get expected ocl objective query.", "Class.allInstances()->size()", model.objectives.get(0).getObjectiveSpec())
		assertEquals("Could not get ocl objective tendency.", "maximise", model.objectives.get(0).getObjectiveTendency())
	}
	
	@Test
	@Ignore
	def void assertOptimisationProvidersAreParsed() {
		
		val customOptimisation = "optimisation provider moea algorithm NSGAII variation genetic evolutions 100000 population 100"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomOptimisation(customOptimisation)
		
		assertEquals("Could not get optimisation algorithm provider framework", "moea", model.optimisation.algorithmFactory)
		assertEquals("Could not get optimisation algorithm name", "NSGAII", model.optimisation.algorithmName)
		assertEquals("Could not get optimisation population size", 100, model.optimisation.algorithmPopulation)
		assertEquals("Could not get optimisation evolutions number", 100000, model.optimisation.algorithmEvolutions)
	}
	
	@Test
	@Ignore
	def void assertOclConstraintProvidersAreParsed() {
		
		val oclConstraint = "constraint constraintName ocl { \"Class.allInstances->size()\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints(oclConstraint)
		
		assertEquals("Could not get ocl constraint name", "constraintName", model.constraints.get(0).constraintName)
		assertEquals("Could not get ocl constraint type", "ocl", model.constraints.get(0).constraintType)
		assertEquals("Could not get ocl constraint spec", "Class.allInstances->size()", model.constraints.get(0).constraintSpec)
	}
	
	@Test
	@Ignore
	def void assertJavaConstraintProvidersAreParsed() {

		val javaConstraint = "constraint constraintName java { \"models.constraints.Constraint.java\" }"
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints(javaConstraint)
		
		assertEquals("Could not get java constraint name", "constraintName", model.constraints.get(0).constraintName)
		assertEquals("Could not get java constraint type", "java", model.constraints.get(0).constraintType)
		assertEquals("Could not get java constraint spec", "models.constraints.Constraint.java", model.constraints.get(0).constraintSpec)
	}
	
	@Test
	@Ignore
	def void assertThatConstraintsAreOptional() {
		
		val model = testModelHelper.getParsedFullValidModelWithCustomConstraints("")
		
		assertEquals("There are no parsed constraints when no constraint specified", 
			0, model.getConstraints().size()
		)
	}
}