package models.knapsack.fitness;

import models.knapsack.ecore.Item;
import models.knapsack.ecore.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class WeightConstraint implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var plan = (Plan) solution.getModel();
    var capacity = plan.getKnapsacks().get(0).getCapacity();
    var weight =
        plan.getKnapsacks().get(0).getContains().stream().mapToDouble(Item::getWeight).sum();

    return (weight > capacity) ? capacity - weight : 0;
  }

  @Override
  public String getName() {
    return "Weight Constraint";
  }
}
