package models.knapsack.fitness;

import models.knapsack.ecore.Item;
import models.knapsack.ecore.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseWeight implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var plan = (Plan) solution.getModel();
    var weight =
        plan.getKnapsacks().get(0).getContains().stream().mapToDouble(Item::getWeight).sum();

    return weight;
  }

  @Override
  public String getName() {
    return "Minimise Weight";
  }
}
