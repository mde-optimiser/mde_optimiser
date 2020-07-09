package models.project.allocation.fitness;

import java.util.stream.Collectors;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseNumberOfMarkingTeams extends AbstractFitnessFunction
    implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var teams = this.getTeams(model);

    System.out.println("Number of marking teams: " + teams.size());
    System.out.println(
        "Number of marking teams: "
            + teams.stream().map(t -> t.size()).collect(Collectors.toList()));

    return teams.size();
  }

  @Override
  public String getName() {
    return "Minimise Number Of Marking Teams";
  }
}
