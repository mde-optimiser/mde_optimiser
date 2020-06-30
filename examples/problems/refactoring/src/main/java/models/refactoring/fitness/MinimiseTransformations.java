package models.refactoring.fitness;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseTransformations implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution model) {

    return model.getTransformationsChain().size();
  }

  @Override
  public String getName() {
    return "Minimise Transformations";
  }
}
