package uk.ac.kcl.inf.mdeoptimiser.languages.tests;

import com.google.inject.Inject;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.MoptValidatorIssues;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MoptPackage;

@ExtendWith(InjectionExtension.class)
@InjectWith(MoptInjectorProvider.class)
public class MoptParsingTest {
	
	@Inject
	ParseHelper<Optimisation> parseHelper;

	@Inject
  ValidationTestHelper validation;

	@Test
	public void assertThatDuplicateAlgorithmParameterValidationReturnsError() throws Exception {

    var modelSpec =
            String.join(
                    System.getProperty("line.separator"),
                    "problem {",
                    "basepath <src/test/resources/models/cra/>",
                    "metamodel <architectureCRA.ecore>",
                    "model <TTC_InputRDG_A.xmi>",
                    "}",
                    "goal {",
                    "objective CRA maximise java { \"models.moea.MaximiseCRA\" }",
                    "constraint MinimiseClasslessFeatures java { \"models.moea.MinimiseClasslessFeatures\" }",
                    "constraint MinimiseEmptyClasses java { \"models.moea.MinimiseEmptyClasses\" }",
                    "}",
                    "search {",
                    "mutate using <craEvolvers.henshin> unit \"createClass\"",
                    "mutate using <craEvolvers.henshin> unit \"assignFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"moveFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"deleteEmptyClass\"",
                    "}",
                    "solver {",
                    "optimisation provider moea algorithm NSGAII {",
                    "population: 40",
                    "variation: mutation",
                    "variation: mutation", //This should trigger a validation error
                    "mutation.step: 1",
                    "mutation.strategy: manual",
                    "}",
                    "termination {",
                    "evolutions: 100",
                    "}",
                    "batches 1",
                    "}");

		var result = parseHelper.parse(modelSpec);
		Assertions.assertNotNull(result);
    validation.assertError(result, MoptPackage.Literals.ALGORITHM_SPEC, MoptValidatorIssues.DUPLICATE_PARAMETER_ENCOUNTERED);
	}
	
	@Test
  public void assertThatValidAlgorithmParameterValidationReturnsNoError() throws Exception {


    var modelSpec =
            String.join(
                    System.getProperty("line.separator"),
                    "problem {",
                    "basepath <src/test/resources/models/cra/>",
                    "metamodel <architectureCRA.ecore>",
                    "model <TTC_InputRDG_A.xmi>",
                    "}",
                    "goal {",
                    "objective CRA maximise java { \"models.moea.MaximiseCRA\" }",
                    "constraint MinimiseClasslessFeatures java { \"models.moea.MinimiseClasslessFeatures\" }",
                    "constraint MinimiseEmptyClasses java { \"models.moea.MinimiseEmptyClasses\" }",
                    "}",
                    "search {",
                    "mutate using <craEvolvers.henshin> unit \"createClass\"",
                    "mutate using <craEvolvers.henshin> unit \"assignFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"moveFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"deleteEmptyClass\"",
                    "}",
                    "solver {",
                    "optimisation provider moea algorithm NSGAII {",
                    "population: 40",
                    "variation: mutation",
                    "mutation.step: 1",
                    "mutation.strategy: manual",
                    "}",
                    "termination {",
                    "evolutions: 100",
                    "iterations: 100",
                    "time: 100",
                    "other: 5000", //This should trigger a validation error
                    "}",
                    "batches 1",
                    "}");

		var result = parseHelper.parse(modelSpec);
		Assertions.assertNotNull(result);
		validation.assertNoErrors(result);
	}
	
	@Test
  public void assertThatDuplicateTerminationConditionParameterValidationReturnsError() throws Exception {


    var modelSpec =
            String.join(
                    System.getProperty("line.separator"),
                    "problem {",
                    "basepath <src/test/resources/models/cra/>",
                    "metamodel <architectureCRA.ecore>",
                    "model <TTC_InputRDG_A.xmi>",
                    "}",
                    "goal {",
                    "objective CRA maximise java { \"models.moea.MaximiseCRA\" }",
                    "constraint MinimiseClasslessFeatures java { \"models.moea.MinimiseClasslessFeatures\" }",
                    "constraint MinimiseEmptyClasses java { \"models.moea.MinimiseEmptyClasses\" }",
                    "}",
                    "search {",
                    "mutate using <craEvolvers.henshin> unit \"createClass\"",
                    "mutate using <craEvolvers.henshin> unit \"assignFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"moveFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"deleteEmptyClass\"",
                    "}",
                    "solver {",
                    "optimisation provider moea algorithm NSGAII {",
                    "population: 40",
                    "variation: mutation",
                    "mutation.step: 1",
                    "mutation.strategy: manual",
                    "}",
                    "termination {",
                    "delta: 5",
                    "delta: 5", //This should trigger a validation error
                    "}",
                    "batches 1",
                    "}");

    var result = parseHelper.parse(modelSpec);

    Assertions.assertNotNull(result);
    validation.assertError(result, MoptPackage.Literals.TERMINATION_CONDITION_SPEC, MoptValidatorIssues.DUPLICATE_PARAMETER_ENCOUNTERED);
	}
	
	@Test
	public void assertThatValidTerminationConditionParameterValidationReturnsNoError() throws Exception {
    var modelSpec =
            String.join(
                    System.getProperty("line.separator"),
                    "problem {",
                    "basepath <src/test/resources/models/cra/>",
                    "metamodel <architectureCRA.ecore>",
                    "model <TTC_InputRDG_A.xmi>",
                    "}",
                    "goal {",
                    "objective CRA maximise java { \"models.moea.MaximiseCRA\" }",
                    "constraint MinimiseClasslessFeatures java { \"models.moea.MinimiseClasslessFeatures\" }",
                    "constraint MinimiseEmptyClasses java { \"models.moea.MinimiseEmptyClasses\" }",
                    "}",
                    "search {",
                    "mutate using <craEvolvers.henshin> unit \"createClass\"",
                    "mutate using <craEvolvers.henshin> unit \"assignFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"moveFeature\"",
                    "mutate using <craEvolvers.henshin> unit \"deleteEmptyClass\"",
                    "}",
                    "solver {",
                    "optimisation provider moea algorithm NSGAII {",
                    "population: 40",
                    "variation: mutation",
                    "mutation.step: 1",
                    "mutation.strategy: manual",
                    "}",
                    "termination {",
                    "delta: 5",
                    "}",
                    "batches 1",
                    "}");

    var result = parseHelper.parse(modelSpec);
		Assertions.assertNotNull(result);
		validation.assertNoErrors(result) ;
		validation.assertNoIssues(result);
	}
	
}
