package models.nrp.fitness;

import models.nrp.nextReleaseProblem.EcorePackage;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class ModelLoadHelper {

  public static Solution loadModel(String filename) {
    HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/resources");

    var metamodel = EcorePackage.eINSTANCE;

    var model = resourceSet.getResource(filename).getContents().get(0);

    return new Solution(model);
  }
}
