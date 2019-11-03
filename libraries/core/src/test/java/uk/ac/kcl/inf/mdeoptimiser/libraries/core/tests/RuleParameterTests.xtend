package uk.ac.kcl.inf.mdeoptimiser.libraries.core.tests

import com.google.inject.Injector
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import org.junit.jupiter.api.BeforeEach
import uk.ac.kcl.inf.mdeoptimiser.languages.MoptStandaloneSetup
import com.google.inject.Key
import org.junit.jupiter.api.TestInfo
import org.junit.jupiter.api.Assertions
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput
import java.util.Date
import java.nio.file.Paths
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.OptimisationInterpreter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import org.junit.jupiter.api.Test
import org.junit.Ignore

class RuleParameterTests {
	Injector injector = new MoptStandaloneSetup().createInjectorAndDoEMFRegistration

	ParseHelper<Optimisation> parseHelper
	ValidationTestHelper validationHelper
	String pathPrefix;

	@BeforeEach
	def void initialiseParserHelper() {
		parseHelper = injector.getInstance(new Key<ParseHelper<Optimisation>>() {
		})
		validationHelper = new ValidationTestHelper()
		pathPrefix = "gen/"
	}
	
		// MODEL A
	@Test
	@Ignore
	def void stackParametersTestModelA(TestInfo testInfo) {

		val model = parseHelper.parse('''
				problem {
					basepath <src/test/resources/models/stack/>
					metamodel <stack.ecore>
					model <model_five_stacks.xmi>
				}
				goal {
					objective ContentSize maximise java { "models.stack.MinimiseStandardDeviation" }
				}
				search {
					mutate using <stack.henshin> unit "shiftLeft" parameters { amount => Random("[1-5]{1}") }
					mutate using <stack.henshin> unit "shiftRight" parameters { amount => Random("[1-5]{1}") }
				}
				solver {
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: mutation
						mutation.step: 3
						mutation.strategy: repetitive
						//Add this in the language
						mutation.selection: random
					}
					termination {
						delta: 5
						iterations: 100
					}
					batches 1
				}
		''')

		runTestSearch(model, testInfo)
	}
	
	
	/**
	 * Helper method to run the MOPT configurations
	 * 
	 * @param model instance of a parsed MOPT file
	 * @param testInfo instance of the running test function
	 */
	private def void runTestSearch(Optimisation model, TestInfo testInfo) {
		Assertions.assertNotNull(model)
		validationHelper.assertNoErrors(model)

		if (model !== null) {

			val mdeoResultsOutput = new MDEOResultsOutput(
				new Date(),
				Paths.get(pathPrefix),
				Paths.get(testInfo.testMethod.get.name),
				model
			);

			var experimentId = 0;
			do {

				val startTime = System.nanoTime;
				val optimisationInterpreter = new OptimisationInterpreter("", model);
				val optimisationOutcome = optimisationInterpreter.start
				val endTime = System.nanoTime;

				val experimentDuration = (endTime - startTime) / 1000000

				mdeoResultsOutput.logBatch(
					new MDEOBatch(experimentId, experimentDuration, optimisationOutcome,
						optimisationInterpreter.rulegenOperators))

				experimentId++
			} while (experimentId < model.solver.algorithmBatches);

			mdeoResultsOutput.saveOutcome();
		}
	}
	
}