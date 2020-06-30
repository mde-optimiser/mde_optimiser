package models.project.allocation.fitness;

import java.util.stream.Collectors;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseLargeMarkingTeams extends AbstractFitnessFunction
    implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var teams = this.getTeams(model);

    var largeTeams =
        teams.stream()
            .filter(team -> team.size() > 5)
            .map(team -> team.size())
            .collect(Collectors.toList());

    System.out.println("Number of teams too large: " + largeTeams.size());
    System.out.println("Number of teams too large: " + largeTeams);

    return largeTeams.stream().mapToInt(a -> a.intValue()).sum();
  }

  @Override
  public String getName() {
    return "Minimise number of teams larger than 5";
  }
}
