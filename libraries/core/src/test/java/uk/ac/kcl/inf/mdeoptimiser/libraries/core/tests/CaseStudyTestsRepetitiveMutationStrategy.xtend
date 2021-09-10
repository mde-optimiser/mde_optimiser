//package uk.ac.kcl.inf.mdeoptimiser.libraries.core.tests
//
//import com.google.inject.Injector
//import com.google.inject.Key
//import java.nio.file.Paths
//import java.util.Date
//import org.eclipse.xtext.testing.util.ParseHelper
//import org.eclipse.xtext.testing.validation.ValidationTestHelper
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestInfo
//import uk.ac.kcl.inf.mdeoptimiser.languages.MoptStandaloneSetup
//import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
//import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.OptimisationInterpreter
//import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
//import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput
//import org.junit.jupiter.api.Disabled
//
//class CaseStudyTestsRepetitiveMutationStrategy {
//
//	Injector injector = new MoptStandaloneSetup().createInjectorAndDoEMFRegistration
//
//	ParseHelper<Optimisation> parseHelper
//	ValidationTestHelper validationHelper
//	String pathPrefix;
//
//	@BeforeEach
//	def void initialiseParserHelper() {
//		parseHelper = injector.getInstance(new Key<ParseHelper<Optimisation>>() {
//		})
//		validationHelper = new ValidationTestHelper()
//		pathPrefix = "gen/"
//	}
//
//	@Test
//	@Disabled
//	def void craManualRulesConstrained(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_A.xmi>
//			}
//			goal {
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//				constraint MinimiseEmptyClasses java { "models.moea.MinimiseEmptyClasses" }
//			}
//			search { 
//				mutate using <craEvolvers.henshin> unit "createClass"
//				mutate using <craEvolvers.henshin> unit "assignFeature"
//				mutate using <craEvolvers.henshin> unit "moveFeature"
//				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//				}
//				termination {
//					evolutions: 100
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//
//
//
//	//MODEL A
//	
//	@Test
//	def void craManualMutationStepSizeFixedStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_A.xmi>
//			}
//			goal {
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate using <craEvolvers.henshin> unit "createClass"
//				mutate using <craEvolvers.henshin> unit "assignFeature"
//				mutate using <craEvolvers.henshin> unit "moveFeature"
//				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeFixedStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_A.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeIntervalStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_A.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: interval(1, 5)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//
//	@Test
//	def void craRulegenMutationStepSizeAdaptiveStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_A.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive 
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	//MODEL B
//	
//	@Test
//	def void craManualMutationStepSizeFixedStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_B.xmi>
//			}
//			goal {
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate using <craEvolvers.henshin> unit "createClass"
//				mutate using <craEvolvers.henshin> unit "assignFeature"
//				mutate using <craEvolvers.henshin> unit "moveFeature"
//				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeFixedStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_B.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//		
//
//	@Test
//	def void craRulegenMutationStepSizeAdaptiveStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_B.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	//MODEL C
//
//	@Test
//	def void craManualMutationStepSizeFixedStrategyC(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_C.xmi>
//			}
//			goal {
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate using <craEvolvers.henshin> unit "createClass"
//				mutate using <craEvolvers.henshin> unit "assignFeature"
//				mutate using <craEvolvers.henshin> unit "moveFeature"
//				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeFixedStrategyC(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_C.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeIntervalStrategyC(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_C.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: interval(1, 5)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//
//	@Test
//	def void craRulegenMutationStepSizeAdaptiveStrategyC(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_C.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	//MODEL D
//	
//	@Test
//	def void craManualMutationStepSizeFixedStrategyD(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_D.xmi>
//			}
//			goal {
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate using <craEvolvers.henshin> unit "createClass"
//				mutate using <craEvolvers.henshin> unit "assignFeature"
//				mutate using <craEvolvers.henshin> unit "moveFeature"
//				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeFixedStrategyD(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_D.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeIntervalStrategyD(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_D.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: interval(1, 5)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//		
//
//	@Test
//	def void craRulegenMutationStepSizeAdaptiveStrategyD(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_D.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	//MODEL E
//	
//	@Test
//	def void craManualMutationStepSizeFixedStrategyE(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_E.xmi>
//			}
//			goal {
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate using <craEvolvers.henshin> unit "createClass"
//				mutate using <craEvolvers.henshin> unit "assignFeature"
//				mutate using <craEvolvers.henshin> unit "moveFeature"
//				mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeFixedStrategyE(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_E.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void craRulegenMutationStepSizeIntervalStrategyE(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_E.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: interval(1, 5)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//		
//
//	@Test
//	def void craRulegenMutationStepSizeAdaptiveStrategyE(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/cra/>
//				metamodel <architectureCRA.ecore>
//				model <TTC_InputRDG_E.xmi>
//			}
//			goal {
//				refine metamodel {"Feature", "isEncapsulatedBy", 1, 1}
//				objective CRA maximise java { "models.moea.MaximiseCRA" }
//				constraint MinimiseClasslessFeatures java { "models.moea.MinimiseClasslessFeatures" }
//			}
//			search { 
//				mutate {"Class"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	
//	/////////////////////////////////
//	////////////////////////////////
//	//NRP
//	///////////////////////////////
//	//////////////////////////////
//	
//	
//	
//	
//	//NRP MODEL A
//	
//	@Test
//	def void nrpManualMutationStepSizeFixedStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-5-cus-25-req-63-sa.xmi>
//			}
//			goal {
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate using <mutation.henshin> unit "modifySelectionWithHierarchy"
//				mutate using <mutation.henshin> unit "modifySingleSelection"
//				mutate using <mutation.henshin> unit "selectHighestRealisation"
//				mutate using <mutation.henshin> unit "fixDependencies"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void nrpRulegenMutationStepSizeFixedStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-5-cus-25-req-63-sa.xmi>
//			}
//			goal {
//				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate {"Solution","selectedArtifacts"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void nrpRulegenMutationStepSizeIntervalStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-5-cus-25-req-63-sa.xmi>
//			}
//			goal {
//				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate {"Solution","selectedArtifacts"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: interval(1, 5)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//		
//
//	@Test
//	def void nrpRulegenMutationStepSizeAdaptiveStrategyA(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-5-cus-25-req-63-sa.xmi>
//			}
//			goal {
//				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate {"Solution","selectedArtifacts"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	
//	
//	//NRP MODEL B
//	
//	@Test
//	def void nrpManualMutationStepSizeFixedStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-25-cus-50-req-203-sa.xmi>
//			}
//			goal {
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate using <mutation.henshin> unit "modifySelectionWithHierarchy"
//				mutate using <mutation.henshin> unit "modifySingleSelection"
//				mutate using <mutation.henshin> unit "selectHighestRealisation"
//				mutate using <mutation.henshin> unit "fixDependencies"
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void nrpRulegenMutationStepSizeFixedStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-25-cus-50-req-203-sa.xmi>
//			}
//			goal {
//				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate {"Solution","selectedArtifacts"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: 5
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	
//	@Test
//	def void nrpRulegenMutationStepSizeIntervalStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-25-cus-50-req-203-sa.xmi>
//			}
//			goal {
//				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate {"Solution","selectedArtifacts"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: interval(1, 5)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//		
//
//	@Test
//	def void nrpRulegenMutationStepSizeAdaptiveStrategyB(TestInfo testInfo) {
//
//		val model = parseHelper.parse('''
//			problem {
//				basepath <src/test/resources/models/nrp/>
//				metamodel <nextReleaseProblem.ecore>
//				model <nrp-model-25-cus-50-req-203-sa.xmi>
//			}
//			goal {
//				refine metamodel {"Solution", "selectedArtifacts", 1, -1}
//				objective MinimiseCost minimise java { "models.nrp.fitness.MinimiseCost" }
//				objective MaximiseSatisfaction maximise java { "models.nrp.fitness.MaximiseSatisfaction" }
//			}
//			search {
//				mutate {"Solution","selectedArtifacts"}
//			}
//			solver {
//				optimisation provider moea algorithm NSGAII {
//					population: 40
//					variation: mutation
//					mutation.step: adaptive(500)
//					mutation.strategy: repetitive
//				}
//				termination {
//					evolutions: 500
//				}
//				batches 1
//			}
//		''')
//
//		runTestSearch(model, testInfo)
//	}
//	
//	/**
//	 * Helper method to run the MOPT configurations
//	 * 
//	 * @param model instance of a parsed MOPT file
//	 * @param testInfo instance of the running test function
//	 */
//	private def void runTestSearch(Optimisation model, TestInfo testInfo) {
//				Assertions.assertNotNull(model)
//		validationHelper.assertNoErrors(model)
//
//		if (model !== null) {
//
//			val mdeoResultsOutput = new MDEOResultsOutput(
//				new Date(),
//				Paths.get(pathPrefix),
//				Paths.get(testInfo.testMethod.get.name),
//				model
//			);
//
//			var experimentId = 0;
//			do {
//
//				val startTime = System.nanoTime;
//				val optimisationInterpreter = new OptimisationInterpreter("", model);
//				val optimisationOutcome = optimisationInterpreter.start
//				val endTime = System.nanoTime;
//
//				val experimentDuration = (endTime - startTime) / 1000000
//
//				mdeoResultsOutput.logBatch(
//					new MDEOBatch(experimentId, experimentDuration, optimisationOutcome,
//						optimisationInterpreter.rulegenOperators))
//
//				experimentId++
//			} while (experimentId < model.solver.algorithmBatches);
//
//			mdeoResultsOutput.saveOutcome();
//		}
//	}
//}
