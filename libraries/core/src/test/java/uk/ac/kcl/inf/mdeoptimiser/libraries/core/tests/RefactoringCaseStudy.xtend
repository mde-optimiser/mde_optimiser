package uk.ac.kcl.inf.mdeoptimiser.libraries.core.tests

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
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput
import org.junit.jupiter.api.Disabled

class RefactoringCaseStudy {

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
	def void refactoringModelA(TestInfo testInfo) {

		val model = parseHelper.parse('''
				problem {
				basepath <src/test/resources/models/refactoring/>
				metamodel <at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringPackage>
				model <SeveralRefactorings.xmi>
				}
				goal {
					objective ContentSize maximise java { "models.refactoring.MinimiseContentSize" }
					//objective Transformations minimise java { "models.refactoring.MinimiseTransformations" }
				}
				search {
					mutate using <Refactoring.henshin> unit "createRootClass"
					mutate using <Refactoring.henshin> unit "extractSuperClass"
					mutate using <Refactoring.henshin> unit "pullUpAttribute"
				}
				solver {
					engine momot
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
	
	// MODEL B
	@Test
	def void refactoringModelB(TestInfo testInfo) {

		val model = parseHelper.parse('''
				problem {
				basepath <src/test/resources/models/refactoring/>
				metamodel <at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringPackage>
				model <GenModel.xmi>
				}
				goal {
					objective ContentSize maximise java { "models.refactoring.MinimiseContentSize" }
					//objective Transformations minimise java { "models.refactoring.MinimiseTransformations" }
				}
				search {
					mutate using <Refactoring.henshin> unit "createRootClass"
					mutate using <Refactoring.henshin> unit "extractSuperClass"
					mutate using <Refactoring.henshin> unit "pullUpAttribute"
				}
				solver {
					engine momot
					optimisation provider moea algorithm NSGAII {
						population: 40
						variation: mutation
						mutation.step: 1
						mutation.strategy: random
					}
					termination {
						time: 800
					}
					batches 1
				}
		''')

		runTestSearch(model, testInfo)
	}

	// MODEL A
	@Test
	def void refactoringModelAMO(TestInfo testInfo) {

		val model = parseHelper.parse('''
				problem {
				basepath <src/test/resources/models/refactoring/>
				metamodel <at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringPackage>
				model <SeveralRefactorings.xmi>
				}
				goal {
					objective ContentSize maximise java { "models.refactoring.MinimiseContentSize" }
					objective Transformations minimise java { "models.refactoring.MinimiseTransformations" }
				}
				search {
					mutate using <Refactoring.henshin> unit "createRootClass"
					mutate using <Refactoring.henshin> unit "extractSuperClass"
					mutate using <Refactoring.henshin> unit "pullUpAttribute"
				}
				solver {
					engine momot
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: mutation
						mutation.step: 1
						mutation.strategy: repetitive
					}
					termination {
						time: 60
					}
					batches 10
				}
		''')

		runTestSearch(model, testInfo)
	}
	
	// MODEL B
	@Test
	@Disabled
	def void refactoringModelBMO(TestInfo testInfo) {

		val model = parseHelper.parse('''
				problem {
				basepath <src/test/resources/models/refactoring/>
				metamodel <at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringPackage>
				model <GenModel.xmi>
				}
				goal {
					objective ContentSize maximise java { "models.refactoring.MinimiseContentSize" }
					objective Transformations minimise java { "models.refactoring.MinimiseTransformations" }
				}
				search {
					mutate using <Refactoring.henshin> unit "createRootClass"
					mutate using <Refactoring.henshin> unit "extractSuperClass"
					mutate using <Refactoring.henshin> unit "pullUpAttribute"
				}
				solver {
					engine momot
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: mutation
						mutation.step: 1
						mutation.strategy: random
					}
					termination {
						time: 800
					}
					batches 10
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
