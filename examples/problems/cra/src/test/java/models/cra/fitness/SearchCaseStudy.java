package models.cra.fitness;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.nio.file.Paths;
import java.util.Date;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.jupiter.api.*;
import uk.ac.kcl.inf.mdeoptimiser.languages.MoptStandaloneSetup;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.OptimisationInterpreter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOResultsOutput;

public class SearchCaseStudy {

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

  // MODEL A
  @Test
  @Disabled
  public void craModelA(TestInfo testInfo) throws Exception {

    var modelSpec =
        String.join(
            System.getProperty("line.separator"),
            "problem {",
            "basepath <src/main/resources/models/cra/>",
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
            "mutation.strategy: random",
            "}",
            "termination {",
            "evolutions: 500",
            "}",
            "batches 1",
            "}");

    var model = parseHelper.parse(modelSpec);

    runTestSearch(model, testInfo);
  }

  /**
   * Helper method to run the MOPT configurations
   *
   * @param model instance of a parsed MOPT file
   * @param testInfo instance of the running test function
   */
  private void runTestSearch(Optimisation model, TestInfo testInfo) {
    Assertions.assertNotNull(model);
    validationHelper.assertNoErrors(model);

    if (model != null) {

      var mdeoResultsOutput =
          new MDEOResultsOutput(
              new Date(),
              Paths.get(pathPrefix),
              Paths.get(testInfo.getTestMethod().get().getName()),
              model);

      var experimentId = 0;
      do {

        var startTime = System.nanoTime();
        var optimisationInterpreter = new OptimisationInterpreter("", model);
        var optimisationOutcome = optimisationInterpreter.start();
        var endTime = System.nanoTime();

        var experimentDuration = (endTime - startTime) / 1000000;

        mdeoResultsOutput.logBatch(
            new MDEOBatch(
                experimentId,
                experimentDuration,
                optimisationOutcome,
                optimisationInterpreter.getSearchSpecification().getRulegenOperators()));

        experimentId++;
      } while (experimentId < model.getSolver().getAlgorithmBatches());

      mdeoResultsOutput.saveOutcome();
    }
  }
}
