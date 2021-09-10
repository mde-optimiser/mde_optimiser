package models.project.allocation.fitness;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MaximiseProjectsWithCompleteMarkingTeams implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var completeMarkingTeams =
        model.getProjects().stream()
            .filter(
                project -> {
                  if (project.getSupervisor() != null && project.getSecondMarker() != null) {
                    return true;
                  }
                  return false;
                })
            .count();

    System.out.println("Projects with complete marking teams: " + completeMarkingTeams);

    return -1 * completeMarkingTeams;
  }

  @Override
  public String getName() {
    return "Maximise Projects With Complete Marking Teams";
  }
}
