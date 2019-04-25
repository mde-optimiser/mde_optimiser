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
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.ParameterSearch
import org.junit.Ignore

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
	@Ignore
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
					mutation.step: 1
					mutation.strategy: random
				}
				termination {
					evolutions: 500
				}
				parameter search {
					strategy: random
					candidates: 10
					population: range(10,20)
					evolutions: range(10,500)
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

			var parameterSearch = new ParameterSearch();
			var parameterSearchResult = parameterSearch.search("", model);

			mdeoResultsOutput.saveParameterSearchOutcome(parameterSearchResult)
		}
	}
	
	@Test
	@Ignore
	def void assertThatHyperparameterSearchingWorksRulegen(TestInfo testInfo) {

		val model = parseHelper.parse('''
			problem {
				basepath <src/test/resources/models/cra/>
				metamodel <architectureCRA.ecore>
				model <TTC_InputRDG_A.xmi>
			}
			goal {
				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
				objective CRA maximise java { "models.moea.MaximiseCRA" }
				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
			}
			search { 
				mutate {"Class"}
			}
			solver {
				optimisation provider moea algorithm NSGAII {
					population: 40
					variation: mutation
					mutation.step: 1
					mutation.strategy: random
				}
				termination {
					evolutions: 500
				}
				parameter search {
					strategy: random
					candidates: 50
					population: range(10,100)
					evolutions: range(10,1000)
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

			var parameterSearch = new ParameterSearch();
			var parameterSearchResult = parameterSearch.search("", model);

			mdeoResultsOutput.saveParameterSearchOutcome(parameterSearchResult)
		}
	}
	
	@Test
	@Ignore
	def void nrpRulegenMutationStepSizeFixedStrategyA(TestInfo testInfo) {

		val model = parseHelper.parse('''
			problem {
				basepath <src/test/resources/models/nrp/>
				metamodel <nextReleaseProblem.ecore>
				model <nrp-model-5-cus-25-req-63-sa.xmi>
			}
			goal {
				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
			}
			search {
				mutate {"Solution","selectedArtifacts"}
			}
			solver {
				optimisation provider moea algorithm NSGAII {
					population: 40
					variation: mutation
					mutation.step: 5
					mutation.strategy: random
				}
				termination {
					evolutions: 500
				}
				parameter search {
					strategy: random
					candidates: 2
					population: range(10,100)
					evolutions: range(10,100)
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

			var parameterSearch = new ParameterSearch();
			var parameterSearchResult = parameterSearch.search("", model);

			mdeoResultsOutput.saveParameterSearchOutcome(parameterSearchResult)
		}
		
	}
}
