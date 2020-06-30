package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters.tests;

import static org.assertj.core.api.Assertions.assertThat;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import uk.ac.kcl.inf.mdeoptimiser.languages.MoptStandaloneSetup;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters.ParametersLoader;

public class ParametersLoaderTests {

  Injector injector = new MoptStandaloneSetup().createInjectorAndDoEMFRegistration();

  ParseHelper<Optimisation> parseHelper;
  ValidationTestHelper validationHelper;
  String pathPrefix;

  @BeforeEach
  public void initialiseParserHelper() {
    parseHelper = injector.getInstance(new Key<ParseHelper<Optimisation>>() {});
    validationHelper = new ValidationTestHelper();
    pathPrefix = "gen/";
  }

  @Test
  public void assertThatFunctionalParameterParsingWorksAsExpected(TestInfo testInfo)
      throws Exception {

    var modelSpec =
        String.join(
            System.getProperty("line.separator"),
            "problem {",
            "basepath <src/test/resources/models/cra/>",
            "metamodel <models.cra.fitness.architectureCRA.ArchitectureCRAPackage>",
            "model <TTC_InputRDG_A.xmi>",
            "}",
            "goal {",
            "refine metamodel {\"Feature\", \"isEncapsulatedBy\", 1, 1}",
            "objective CRA maximise java { \"models.cra.fitness.MaximiseCRA\" }",
            "constraint MinimiseClasslessFeatures java { \"models.cra.fitness.MinimiseClasslessFeatures\" }",
            "}",
            "search {",
            "mutate {\"Class\"}",
            "}",
            "solver {",
            "optimisation provider moea algorithm NSGAII {",
            "population: 100",
            "variation: mutation",
            "mutation.step: 1",
            "mutation.strategy: manual",
            "}",
            "termination {",
            "evolutions: 500",
            "}",
            "batches 1",
            "}");

    var model = parseHelper.parse(modelSpec);
    Assertions.assertNotNull(model);
    validationHelper.assertNoErrors(model);

    var parametersList = model.getSolver().getAlgorithm().getParameters();

    var parametersParser = new ParametersLoader(parametersList);

    var parsedParameter = parametersParser.get("mutation.strategy");

    assertThat(parsedParameter.getValue()).isEqualTo("manual");
    assertThat(parsedParameter.getValue().isEmpty()).isTrue();
  }

  @Test
  public void assertThatParametricParameterParsingWorksAsExpected(TestInfo testInfo)
      throws Exception {

    var modelSpec =
        String.join(
            System.getProperty("line.separator"),
            "problem {",
            "basepath <src/test/resources/models/cra/>",
            "metamodel <models.cra.fitness.architectureCRA.ArchitectureCRAPackage>",
            "model <TTC_InputRDG_A.xmi>",
            "}",
            "goal {",
            "refine metamodel {\"Feature\", \"isEncapsulatedBy\", 1, 1}",
            "objective CRA maximise java { \"models.cra.fitness.MaximiseCRA\" }",
            "constraint MinimiseClasslessFeatures java { \"models.cra.fitness.MinimiseClasslessFeatures\" }",
            "}",
            "search {",
            "mutate {\"Class\"}",
            "}",
            "solver {",
            "optimisation provider moea algorithm NSGAII {",
            "population: 100",
            "variation: mutation",
            "mutation.step: 1",
            "mutation.strategy: function(\"a\", \"b\", 5, 6.0)",
            "}",
            "termination {",
            "evolutions: 500",
            "}",
            "batches 1",
            "}");

    var model = parseHelper.parse(modelSpec);
    Assertions.assertNotNull(model);
    validationHelper.assertNoErrors(model);

    var parametersList = model.getSolver().getAlgorithm().getParameters();

    var parametersParser = new ParametersLoader(parametersList);

    var expectedParameterValues = new LinkedHashMap<String, String>();
    expectedParameterValues.put("parameter1", "a");
    expectedParameterValues.put("parameter2", "b");
    expectedParameterValues.put("parameter3", "5");
    expectedParameterValues.put("parameter4", "6.0");

    var parsedParameter = parametersParser.get("mutation.strategy");

    assertThat(parsedParameter.getValue()).isEqualTo("function");
    assertThat(parsedParameter.getValueArguments(new ArrayList<>(expectedParameterValues.keySet())))
        .isEqualTo(expectedParameterValues);
  }
}
