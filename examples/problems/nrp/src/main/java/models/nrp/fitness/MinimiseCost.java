package models.nrp.fitness;

import models.nrp.nextReleaseProblem.NRP;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseCost implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var nextRelease = (NRP) solution.getModel();

    var selectedArtifactsCost =
        nextRelease.getSolutions().get(0).getSelectedArtifacts().stream()
            .mapToDouble(sa -> sa.getCosts().get(0).getAmount())
            .sum();

    System.out.println("Calculated selectedArtifacts cost: " + selectedArtifactsCost);

    return selectedArtifactsCost;
  }

  @Override
  public String getName() {
    return "Minimise Next Release Cost";
  }
}
