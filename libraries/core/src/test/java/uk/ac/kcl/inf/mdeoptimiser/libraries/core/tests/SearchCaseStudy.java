package uk.ac.kcl.inf.mdeoptimiser.libraries.core.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.nio.file.Paths;
import java.util.Date;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.junit.jupiter.api.*;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import org.moeaframework.core.comparator.ParetoDominanceComparator;
import org.moeaframework.core.indicator.Hypervolume;
import org.moeaframework.core.indicator.R2Indicator;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.problem.AbstractProblem;
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
  public void refactoringModelA(TestInfo testInfo) throws Exception {

    var modelSpec =
        String.join(
            System.getProperty("line.separator"),
            "problem {",
            "basepath <src/test/resources/models/cra/>",
            "metamodel <models.cra.fitness.architectureCRA.ArchitectureCRAPackage>",
            "model <TTC_InputRDG_C.xmi>",
            "}",
            "goal {",
            "refine metamodel {\"Feature\", \"isEncapsulatedBy\", 1, 1}",
            "objective CRA maximise java { \"models.cra.fitness.MaximiseCRA\" }",
            "constraint MinimiseClasslessFeatures java { \"models.cra.fitness.MinimiseClasslessFeatures\" }",
            "constraint MinimiseEmptyClasses java { \"models.cra.fitness.MinimiseEmptyClasses\" }",
            "}",
            "search {",
            "mutate {\"Class\"}",
            "}",
            "solver {",
            "optimisation provider moea algorithm SPEA2 {",
            "population: 100",
            "variation: mutation",
            "mutation.step: fixed(1)",
            "mutation.strategy: random",
            // "mutation.selection: random",
//            "mutation.selection: ap(0.05, 0.1, 0.8)",
//            "mutation.credit: opd",
            // "mutation.repair: cpa",
            "}",
            "termination {",
            "evolutions: 1500",
            "}",
            "batches 1",
            "}");

    var model = parseHelper.parse(modelSpec);
    runTestSearch(model, testInfo);
  }

  @Test
  public void testDominance() {
    NondominatedPopulation referenceSet = new NondominatedPopulation();
    referenceSet.add(new Solution(new double[] {0.0, Double.MAX_VALUE}));
    referenceSet.add(new Solution(new double[] {Double.MAX_VALUE, 0.0}));

    R2Indicator indicator = new R2Indicator(new MockRealProblem(1, 2, 2), 2, referenceSet);

    Hypervolume hvIndicator = new Hypervolume(new MockRealProblem(2), referenceSet);

    NondominatedPopulation population1 = new NondominatedPopulation();
    var solutionA = new Solution(1, 2, 2);
    solutionA.setObjectives(new double[] {0.4, 0});
    solutionA.setConstraints(new double[] {-4, 3});
    population1.add(solutionA);
    //    population1.add(new Solution(new double[] {0.25, 0}));

    NondominatedPopulation population2 = new NondominatedPopulation();
    var solutionB = new Solution(1, 2, 2);
    solutionB.setObjectives(new double[] {-15, 0});
    solutionA.setConstraints(new double[] {-4, 1});
    population2.add(solutionB);
    //    population2.add(new Solution(new double[] {-4.0, 0}));
    //    population2.add(new Solution(new double[] {-2.0, 0}));

    System.out.println(indicator.evaluate(population1));
    System.out.println(indicator.evaluate(population2));

    System.out.println("HV");
    System.out.println(hvIndicator.evaluate(population1));
    System.out.println(hvIndicator.evaluate(population2));

    assertTrue(indicator.evaluate(population1) < indicator.evaluate(population2));
  }

  @Test
  public void testDominanceSO() {

    var solutionA = new Solution(1, 1, 1);
    solutionA.setObjectives(new double[] {0.1});
    solutionA.setConstraints(new double[] {-1});

    var solutionB = new Solution(1, 1, 1);
    solutionB.setObjectives(new double[] {0.2});
    solutionB.setConstraints(new double[] {-1.1});

    var dominanceComparator = new ParetoDominanceComparator();
    var comparisonOutcome = dominanceComparator.compare(solutionA, solutionB);
    assertTrue(comparisonOutcome < 0);
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

class MockRealProblem extends AbstractProblem {

  public MockRealProblem() {
    this(1);
  }

  public MockRealProblem(int variables, int objectives, int constraints) {
    super(variables, objectives, constraints);
  }

  public MockRealProblem(int numberOfObjectives) {
    super(1, numberOfObjectives);
  }

  @Override
  public void evaluate(Solution solution) {
    for (int i = 0; i < getNumberOfObjectives(); i++) {
      solution.setObjective(i, 5.0);
    }
  }

  @Override
  public Solution newSolution() {
    Solution solution = new Solution(1, getNumberOfObjectives());
    solution.setVariable(0, new RealVariable(0.0, 1.0));
    return solution;
  }
}
