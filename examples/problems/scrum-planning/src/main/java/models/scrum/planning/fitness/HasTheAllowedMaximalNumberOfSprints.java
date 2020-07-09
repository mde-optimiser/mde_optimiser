package models.scrum.planning.fitness;

import models.scrum.planning.SprintPlanning.Plan;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class HasTheAllowedMaximalNumberOfSprints implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var plan = (Plan) solution.getModel();

    var totalEffort =
        plan.getBacklog().getWorkitems().stream()
            .mapToDouble(workItem -> workItem.getEffort())
            .sum();

    var maximumVelocity = 25;

    var desiredSprints = totalEffort / maximumVelocity;

    if (desiredSprints - (int) desiredSprints > 0.5d) {
      desiredSprints = Math.ceil(desiredSprints);
    } else {
      desiredSprints = Math.floor(desiredSprints);
    }

    var nonEmptySprints =
        plan.getSprints().stream().filter(sprint -> sprint.getCommittedItem().size() > 0).count();

    System.out.println("Counted sprints: " + nonEmptySprints);
    System.out.println("Counted maximal desired sprints: " + desiredSprints);

    // If we have less than the minimum number of desired sprints
    if (nonEmptySprints > desiredSprints) {
      return desiredSprints - nonEmptySprints;
    }

    return 0;
  }

  @Override
  public String getName() {
    return "Has the allowed maximal number of sprints";
  }
}
