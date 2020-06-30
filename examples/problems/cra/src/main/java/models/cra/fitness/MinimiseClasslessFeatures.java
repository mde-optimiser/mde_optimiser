package models.cra.fitness;

import models.cra.fitness.architectureCRA.ClassModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseClasslessFeatures implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var classModel = (ClassModel) solution.getModel();

    return classModel.getFeatures().stream()
        .filter(feature -> feature.getIsEncapsulatedBy() == null)
        .count();
  }

  @Override
  public String getName() {
    return "Mimise classless features";
  }
}
