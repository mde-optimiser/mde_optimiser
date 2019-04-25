package uk.ac.kcl.inf.mdeoptimiser.languages.tests

import com.google.inject.Inject
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.extensions.InjectionExtension
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.^extension.ExtendWith
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.MoptValidatorIssues
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MoptPackage

@ExtendWith(InjectionExtension)
@InjectWith(MoptInjectorProvider)
class MoptParsingTest {
	
	@Inject
	ParseHelper<Optimisation> parseHelper
	
	@Inject extension
	ValidationTestHelper
	
	@Test
	def void assertThatDuplicateAlgorithmParameterValidationReturnsError() {
		val result = parseHelper.parse('''
				problem {
					basepath <src/main/resources/models/cra/>
					metamodel <architectureCRA.ecore>
					model <TTC_InputRDG_C.xmi>
				}
				goal {
					objective CRA maximise java { "models.cra.fitness.MaximiseCRA" }
					constraint MinimiseClasslessFeatures java { "models.cra.fitness.MinimiseClasslessFeatures" }
					constraint MinimiseEmptyClasses java { "models.cra.fitness.MinimiseEmptyClasses" }
				}
				search { 
					mutate using <craEvolvers.henshin> unit "createClass"
					mutate using <craEvolvers.henshin> unit "assignFeature"
					mutate using <craEvolvers.henshin> unit "moveFeature"
					mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				}
				solver {
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: 11
						variation: 12
					}
					termination {
						delta: 5
						iterations: 100
						iterations: 1003
						time: 100
						other: 4000
					}
					batches 10
				}
		''')
		Assertions.assertNotNull(result)
		result.assertError(MoptPackage.Literals.ALGORITHM_SPEC, MoptValidatorIssues::DUPLICATE_PARAMETER_ENCOUNTERED);
	}
	
	@Test
	def void assertThatValidAlgorithmParameterValidationReturnsNoError() {
		val result = parseHelper.parse('''
				problem {
					basepath <src/main/resources/models/cra/>
					metamodel <architectureCRA.ecore>
					model <TTC_InputRDG_C.xmi>
				}
				goal {
					objective CRA maximise java { "models.cra.fitness.MaximiseCRA" }
					constraint MinimiseClasslessFeatures java { "models.cra.fitness.MinimiseClasslessFeatures" }
					constraint MinimiseEmptyClasses java { "models.cra.fitness.MinimiseEmptyClasses" }
				}
				search { 
					mutate using <craEvolvers.henshin> unit "createClass"
					mutate using <craEvolvers.henshin> unit "assignFeature"
					mutate using <craEvolvers.henshin> unit "moveFeature"
					mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				}
				solver {
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: 11
					}
					termination {
						delta: 5
						iterations: 100
						time: 100
						other: 4000
					}
					batches 10
				}
		''')
		Assertions.assertNotNull(result)
		result.assertNoErrors() 
	}
	
	@Test
	def void assertThatDuplicateTerminationConditionParameterValidationReturnsError() {
		val result = parseHelper.parse('''
				problem {
					basepath <src/main/resources/models/cra/>
					metamodel <architectureCRA.ecore>
					model <TTC_InputRDG_C.xmi>
				}
				goal {
					objective CRA maximise java { "models.cra.fitness.MaximiseCRA" }
					constraint MinimiseClasslessFeatures java { "models.cra.fitness.MinimiseClasslessFeatures" }
					constraint MinimiseEmptyClasses java { "models.cra.fitness.MinimiseEmptyClasses" }
				}
				search { 
					mutate using <craEvolvers.henshin> unit "createClass"
					mutate using <craEvolvers.henshin> unit "assignFeature"
					mutate using <craEvolvers.henshin> unit "moveFeature"
					mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				}
				solver {
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: 11
						parameter: name
						parameter: secondary(1)
					}
					termination {
						delta: 5
						delta: 5
						iterations: 100
						iterations: 1003
						time: 100
						other: 4000
					}
					batches 10
				}
		''')
		Assertions.assertNotNull(result)
		result.assertError(MoptPackage.Literals.ALGORITHM_SPEC, MoptValidatorIssues::DUPLICATE_PARAMETER_ENCOUNTERED);
	}
	
	@Test
	def void assertThatValidTerminationConditionParameterValidationReturnsNoError() {
		val result = parseHelper.parse('''
				problem {
					basepath <src/main/resources/models/cra/>
					metamodel <architectureCRA.ecore>
					model <TTC_InputRDG_C.xmi>
				}
				goal {
					objective CRA maximise java { "models.cra.fitness.MaximiseCRA" }
					constraint MinimiseClasslessFeatures java { "models.cra.fitness.MinimiseClasslessFeatures" }
					constraint MinimiseEmptyClasses java { "models.cra.fitness.MinimiseEmptyClasses" }
				}
				search { 
					mutate using <craEvolvers.henshin> unit "createClass"
					mutate using <craEvolvers.henshin> unit "assignFeature"
					mutate using <craEvolvers.henshin> unit "moveFeature"
					mutate using <craEvolvers.henshin> unit "deleteEmptyClass"
				}
				solver {
					optimisation provider moea algorithm NSGAII {
						population: 100
						variation: mutation
					}
					termination {
						delta: 5
						iterations: 100
						time: 100
						other: 4000
					}
					batches 10
				}
		''')
		Assertions.assertNotNull(result)
		result.assertNoErrors() 
		result.assertNoIssues
	}
	
}
