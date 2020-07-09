package models.scrum.planning.fitness;

import models.scrum.planning.SprintPlanning.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class HasNoUnassignedWorkItems implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    long unassignedWorkItems =
        ((Plan) solution.getModel())
            .getBacklog().getWorkitems().stream()
                .filter(wi -> wi.getIsPlannedFor() == null)
                .count();

    System.out.println("Unassigned backlog work items: " + unassignedWorkItems);

    return unassignedWorkItems;
  }

  @Override
  public String getName() {
    return "Mimise unassigned work items";
  }
}
