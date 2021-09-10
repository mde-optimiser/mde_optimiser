package models.cra.fitness;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class CRAFitnessTests {

  Solution model;

  @Test
  public void assertThatAllFitnessFunctionsReturnAsExpectedForValidModel() {

    this.model = ModelLoadHelper.loadModel("cra-3.xmi");

    var cra = new MaximiseCRA().computeFitness(this.model);
    var minimiseClasslessFeatures = new MinimiseClasslessFeatures().computeFitness(this.model);
    var minimiseEmptyClasses = new MinimiseEmptyClasses().computeFitness(this.model);

    assertEquals(-3.0d, cra);
    assertEquals(0, minimiseClasslessFeatures);
    assertEquals(0, minimiseEmptyClasses);
  }
}
