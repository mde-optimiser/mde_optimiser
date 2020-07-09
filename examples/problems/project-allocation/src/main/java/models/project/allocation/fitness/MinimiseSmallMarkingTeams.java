package models.project.allocation.fitness;

import java.util.stream.Collectors;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseSmallMarkingTeams extends AbstractFitnessFunction
    implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var teams = this.getTeams(model);

    var smallTeams =
        teams.stream()
            .filter(team -> team.size() < 3)
            .map(team -> team.size())
            .collect(Collectors.toList());

    System.out.println("Number of teams too small: " + smallTeams.size());
    System.out.println("Number of teams too small: " + smallTeams);

    return smallTeams.size(); // stream().mapToInt(a -> a.intValue()).sum();
  }

  @Override
  public String getName() {
    return "Minimise number of teams smaller than 3";
  }
}
