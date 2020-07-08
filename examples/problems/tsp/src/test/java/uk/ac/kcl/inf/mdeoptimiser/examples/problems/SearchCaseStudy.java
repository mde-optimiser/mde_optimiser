package uk.ac.kcl.inf.mdeoptimiser.examples.problems;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.nio.file.Paths;
import java.util.Date;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
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
  public void TSPModelA(TestInfo testInfo) throws Exception {

    var modelSpec =
        String.join(
            System.getProperty("line.separator"),
            "problem {\n"
                + "\tbasepath <src/main/resources/models/tsp/>\n"
                + "\tmetamodel <models.tsp.fitness.TSP.TSPPackage>\n"
                + "\tmodel <br17.atsp.xmi>\n"
                + "}\n"
                + "goal {\n"
                + "\trefine metamodel {\"City\", \"next\", 1, 1}",
            "\tobjective MinimiseDistanceTravelled minimise java { \"models.tsp.fitness.MinimiseDistanceTravelled\" }\n"
                + "\tconstraint MinimiseUnvisitedCities java { \"models.tsp.fitness.MinimiseUnvisitedCities\" }\n"
                + "}\n"
                + "search { \n"
                + "\tmutate {\"City\", \"next\"}\n"
                + "}\n"
                + "solver {\n"
                + "\toptimisation provider moea algorithm NSGAII {\n"
                + "\t\tpopulation: 100\n"
                + "\t\tvariation: mutation\n"
                + "\t\tmutation.step: annealing(1500, 10)\n"
                + "\t\tmutation.strategy: random\n"
                + "\t}\n"
                + "\ttermination {\n"
                + "\t\tevolutions: 1500\n"
                + "\t}\n"
                + "\tbatches 1\n"
                + "}");

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
