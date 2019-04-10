package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.hyperparameter.tests

import com.google.inject.Injector
import com.google.inject.Key
import java.nio.file.Paths
import java.util.Date
import org.eclipse.xtext.testing.util.ParseHelper
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo
import uk.ac.kcl.inf.mdeoptimiser.languages.MoptStandaloneSetup
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.OptimisationInterpreter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.ParameterSearch
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaFrameworkAlgorithmConfiguration

class ParameterSearchTests {

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

	@Test
	def void assertThatHyperparameterSearchingWorks(TestInfo testInfo) {

		val model = parseHelper.parse('''
			problem {
				basepath <src/test/resources/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_A.xmi>
			}
			goal {
				objective CRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
				constraint MinimiseEmptyClasses java { "models.moea.MinimiseEmptyClasses" }
			}
			search { 
				mutate using <craEvolvers.henshin> unit "createClass"
				mutate using <craEvolvers.henshin> unit "assignFeature"
				mutate using <craEvolvers.henshin> unit "moveFeature"
				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
			}
			solver {
				optimisation provider moea algorithm NSGAII {
					population: 40
					variation: mutation
					mutation.step: 3
					mutation.strategy: random
				}
				termination {
					evolutions: 500
				}
				batches 1
			}
		''')

		Assertions.assertNotNull(model)
		validationHelper.assertNoErrors(model)

		if (model !== null) {

			val mdeoResultsOutput = new MDEOResultsOutput(
				new Date(),
				Paths.get(pathPrefix),
				Paths.get(testInfo.testMethod.get.name),
				model
			);

			var OptimisationInterpreter optimisationInterpreter = new OptimisationInterpreter("", model);

			var algorithmConfiguration = new MoeaFrameworkAlgorithmConfiguration(model.solver, optimisationInterpreter.getSolutionGenerator())
			var moeaOptimisationConfiguration = new MoeaOptimisation();

			var parameterSearch = new ParameterSearch();

			parameterSearch.search(model, optimisationInterpreter.solutionGenerator)

			val optimisationOutcome = optimisationInterpreter.start

			mdeoResultsOutput.saveOutcome();
		}
	}
}
