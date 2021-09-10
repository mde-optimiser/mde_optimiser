package models.scrum.planning.fitness;

import java.util.stream.DoubleStream;
import models.scrum.planning.SprintPlanning.Plan;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseCustomerSatisfactionIndex implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var plan = (Plan) solution.getModel();

    var sprints = plan.getSprints();
    var standardDeviationCalculator = new StandardDeviation();

    var stakeholderImportanceSprintDeviation =
        plan.getStakeholders().stream()
            .map(
                stakeholder -> {
                  var effortAccrossSprints =
                      sprints.stream()
                          .map(
                              sprint ->
                                  sprint.getCommittedItem().stream()
                                      .filter(item -> item.getStakeholder().equals(stakeholder))
                                      .flatMapToDouble(
                                          item -> DoubleStream.of(item.getImportance()))
                                      .sum())
                          .mapToDouble(Double::doubleValue)
                          .toArray();

                  return standardDeviationCalculator.evaluate(effortAccrossSprints);
                })
            .mapToDouble(Double::doubleValue)
            .toArray();

    var importanceStandardDeviation =
        standardDeviationCalculator.evaluate(stakeholderImportanceSprintDeviation);

    System.out.println(
        "Sprint stakeholder importance distribution: " + stakeholderImportanceSprintDeviation);
    System.out.println("Sprint Customer Satisfaction Index: " + importanceStandardDeviation);

    return importanceStandardDeviation;
  }

  @Override
  public String getName() {

    return "Minimise Customer Satisfaction Index";
  }
}
