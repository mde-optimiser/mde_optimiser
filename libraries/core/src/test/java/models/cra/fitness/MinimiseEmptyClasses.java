package models.cra.fitness;

import models.cra.fitness.architectureCRA.ClassModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseEmptyClasses implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    ClassModel classModel = (ClassModel) solution.getModel();
    return classModel.getClasses().stream()
        .filter(modelClass -> modelClass.getEncapsulates().isEmpty())
        .count();
  }

  @Override
  public String getName() {

    return "Minimise Empty Classes";
  }
}
