package models.scrum.planning.fitness;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class ScrumPlanningFitnessTests {

  private Solution model;

  @Test
  public void assertThatAllFitnessFunctionsReturnAsExpectedForValidModel() {

    this.model = ModelLoadHelper.loadModel("batch-115A17B14.xmi");

    // Calculate fitness objectives
    var minimiseCustomerSatisfactionIndex = new MinimiseCustomerSatisfactionIndex();
    var minimiseSprintEffortDeviation = new MinimiseSprintEffortDeviation();

    // Calculate constraints
    var hasNoUnassignedWorkItems = new HasNoUnassignedWorkItems();
    var hasTheAllowedMaximalNumberOfSprints = new HasTheAllowedMaximalNumberOfSprints();

    assertEquals(0.551232502197523, minimiseCustomerSatisfactionIndex.computeFitness(this.model));
    assertEquals(1.1785113019775793, minimiseSprintEffortDeviation.computeFitness(this.model));
    assertEquals(0, hasNoUnassignedWorkItems.computeFitness(this.model));
    assertEquals(0, hasTheAllowedMaximalNumberOfSprints.computeFitness(this.model));
  }

  @Test
  public void assertThatHasNoUnassignedWorkItemsRetursAsExpected() {

    this.model = ModelLoadHelper.loadModel("one-wi-unassigned.xmi");

    // Calculate constraints
    var hasNoUnassignedWorkItems = new HasNoUnassignedWorkItems();

    assertEquals(1, hasNoUnassignedWorkItems.computeFitness(this.model));
  }

  @Test
  public void assertThathasAllowedMaximalNumberOfSprintsReturnsAsExpectedForInvalidModel() {

    this.model = ModelLoadHelper.loadModel("too-many-sprints.xmi");

    // Calculate constraints
    var hasTheAllowedMaximalNumberOfSprints = new HasTheAllowedMaximalNumberOfSprints();

    assertEquals(-1, hasTheAllowedMaximalNumberOfSprints.computeFitness(this.model));
  }
}
