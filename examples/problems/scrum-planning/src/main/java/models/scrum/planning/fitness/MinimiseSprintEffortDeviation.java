package models.scrum.planning.fitness;

import java.util.stream.DoubleStream;
import models.scrum.planning.SprintPlanning.Plan;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseSprintEffortDeviation implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var plan = (Plan) solution.getModel();

    var fitness =
        plan.getSprints().stream()
            .map(
                sprint ->
                    sprint.getCommittedItem().stream()
                        .flatMapToDouble(x -> DoubleStream.of(x.getEffort()))
                        .sum())
            .mapToDouble(Double::doubleValue)
            .toArray();

    var effortStandardDeviation = new StandardDeviation().evaluate(fitness);

    System.out.println("Sprint effort distribution: " + fitness);
    System.out.println("Sprint effort standard deviation: " + effortStandardDeviation);

    return effortStandardDeviation;
  }

  @Override
  public String getName() {
    return "Maximise average sprint effort";
  }
}
