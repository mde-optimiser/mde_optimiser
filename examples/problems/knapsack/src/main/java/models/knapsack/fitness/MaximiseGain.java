package models.knapsack.fitness;

import models.knapsack.ecore.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MaximiseGain implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution model) {

    var plan = (Plan) model.getModel();
    var gain =
        plan.getKnapsacks().get(0).getContains().stream().mapToDouble(item -> item.getGain()).sum();

    System.out.println(String.format("%s: %s", this.getName(), gain));

    return -1 * gain;
  }

  @Override
  public String getName() {
    return "Maximise Gain";
  }
}
