package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.hyperparameter.tests;

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
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.ParameterSearch;

public class ParameterSearchTests {

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
  @Disabled
  public void assertThatCraArtifactsWorkAsExpected(TestInfo testInfo) throws Exception {

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

    runTestSearch(model, testInfo);
  }

  // Transformation rules are for dynemf

  @Test
  @Disabled
  public void assertThatHyperparameterSearchingWorks(TestInfo testInfo) throws Exception {

    var model =
        parseHelper.parse(
            "\t\t\tproblem {\n"
                + "\t\t\t\tbasepath <src/test/resources/models/cra/>\n"
                + "\t\t\t\tmetamodel <models.cra.fitness.architectureCRA.ArchitectureCRAPackage>\n"
                + "\t\t\t\tmodel <TTC_InputRDG_A.xmi>\n"
                + "\t\t\t}\n"
                + "\t\t\tgoal {\n"
                + "refine metamodel {\"Feature\", \"isEncapsulatedBy\", 1, 1}"
                + "\t\t\t\tobjective CRA maximise java { \"models.cra.fitness.MaximiseCRA\" }\n"
                + "\t\t\t\tconstraint MinimiseClasslessFeatures java { \"models.cra.fitness.MinimiseClasslessFeatures\" }\n"
                // + "\t\t\t\tconstraint MinimiseEmptyClasses java {
                // \"models.cra.fitness.MinimiseEmptyClasses\" }\n"
                + "\t\t\t}\n"
                + "search {"
                + "mutate {\"Class\"}"
                + "}"
                + "\t\t\tsolver {\n"
                + "\t\t\t\toptimisation provider moea algorithm NSGAII {\n"
                + "\t\t\t\t\tpopulation: 40\n"
                + "\t\t\t\t\tvariation: mutation\n"
                + "\t\t\t\t\tmutation.step: fixed(1)\n"
                + "\t\t\t\t\tmutation.strategy: random\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\ttermination {\n"
                + "\t\t\t\t\tevolutions: 500\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\tparameter search {\n"
                + "\t\t\t\t\tstrategy: random\n"
                + "\t\t\t\t\tcandidates: 1\n"
                + "\t\t\t\t\tpopulation: range(10,20)\n"
                + "\t\t\t\t\tevolutions: range(100,200)\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\tbatches 10\n"
                + "\t\t\t}");

    runParameterSearch(model, testInfo);
  }

  @Test
  @Disabled
  public void assertThatHyperparameterSearchingWorksRulegen(TestInfo testInfo) throws Exception {

    var model =
        parseHelper.parse(
            "\t\t\tproblem {\n"
                + "\t\t\t\tbasepath <src/test/resources/models/cra/>\n"
                + "\t\t\t\tmetamodel <architectureCRA.ecore>\n"
                + "\t\t\t\tmodel <TTC_InputRDG_A.xmi>\n"
                + "\t\t\t}\n"
                + "\t\t\tgoal {\n"
                + "\t\t\t\trefine metamodel {\"Feature\", \"isEncapsulatedBy\", 1, 1}\n"
                + "\t\t\t\tobjective CRA maximise java { \"models.moea.MaximiseCRA\" }\n"
                + "\t\t\t\tconstraint MinimiseClasslessFeatures java { \"models.moea.MinimiseClasslessFeatures\" }\n"
                + "\t\t\t}\n"
                + "\t\t\tsearch { \n"
                + "\t\t\t\tmutate {\"Class\"}\n"
                + "\t\t\t}\n"
                + "\t\t\tsolver {\n"
                + "\t\t\t\toptimisation provider moea algorithm NSGAII {\n"
                + "\t\t\t\t\tpopulation: 40\n"
                + "\t\t\t\t\tvariation: mutation\n"
                + "\t\t\t\t\tmutation.step: 1\n"
                + "\t\t\t\t\tmutation.strategy: random\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\ttermination {\n"
                + "\t\t\t\t\tevolutions: 500\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\tparameter search {\n"
                + "\t\t\t\t\tstrategy: random\n"
                + "\t\t\t\t\tcandidates: 50\n"
                + "\t\t\t\t\tpopulation: range(10,100)\n"
                + "\t\t\t\t\tevolutions: range(10,1000)\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\tbatches 1\n"
                + "\t\t\t}");

    runParameterSearch(model, testInfo);
  }

  @Test
  @Disabled
  public void nrpRulegenMutationStepSizeFixedStrategyA(TestInfo testInfo) throws Exception {

    var model =
        parseHelper.parse(
            "problem {\n"
                + "\t\t\t\tbasepath <src/test/resources/models/nrp/>\n"
                + "\t\t\t\tmetamodel <nextReleaseProblem.ecore>\n"
                + "\t\t\t\tmodel <nrp-model-5-cus-25-req-63-sa.xmi>\n"
                + "\t\t\t}\n"
                + "\t\t\tgoal {\n"
                + "\t\t\t\trefine metamodel {\"Solution\", \"selectedArtifacts\", 1, -1}\n"
                + "\t\t\t\tobjective MinimiseCost minimise java { \"models.nrp.fitness.MinimiseCost\" }\n"
                + "\t\t\t\tobjective MaximiseSatisfaction maximise java { \"models.nrp.fitness.MaximiseSatisfaction\" }\n"
                + "\t\t\t}\n"
                + "\t\t\tsearch {\n"
                + "\t\t\t\tmutate {\"Solution\",\"selectedArtifacts\"}\n"
                + "\t\t\t}\n"
                + "\t\t\tsolver {\n"
                + "\t\t\t\toptimisation provider moea algorithm NSGAII {\n"
                + "\t\t\t\t\tpopulation: 40\n"
                + "\t\t\t\t\tvariation: mutation\n"
                + "\t\t\t\t\tmutation.step: 5\n"
                + "\t\t\t\t\tmutation.strategy: random\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\ttermination {\n"
                + "\t\t\t\t\tevolutions: 500\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\tparameter search {\n"
                + "\t\t\t\t\tstrategy: random\n"
                + "\t\t\t\t\tcandidates: 2\n"
                + "\t\t\t\t\tpopulation: range(10,100)\n"
                + "\t\t\t\t\tevolutions: range(10,100)\n"
                + "\t\t\t\t}\n"
                + "\t\t\t\tbatches 1\n"
                + "\t\t\t}");

    runParameterSearch(model, testInfo);
  }

  public void runParameterSearch(Optimisation model, TestInfo testInfo) {

    Assertions.assertNotNull(model);
    validationHelper.assertNoErrors(model);

    if (model != null) {

      var mdeoResultsOutput =
          new MDEOResultsOutput(
              new Date(),
              Paths.get(pathPrefix),
              Paths.get(testInfo.getTestMethod().get().getName()),
              model);

      var parameterSearch = new ParameterSearch();
      var parameterSearchResult = parameterSearch.search("", model);

      mdeoResultsOutput.saveParameterSearchOutcome(parameterSearchResult);
    }
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
