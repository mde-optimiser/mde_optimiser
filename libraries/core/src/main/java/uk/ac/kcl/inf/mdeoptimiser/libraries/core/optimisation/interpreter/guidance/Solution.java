package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class Solution {

  private EObject model;
  private LinkedList<Map<Integer, String>> transformationsChain;

  public Solution(EObject model) {
    this(model, new LinkedList<String>());
  }

  public Solution(EObject model, List<String> transformations) {
    this.model = model;
    this.transformationsChain = new LinkedList<Map<Integer, String>>();
  }

  public Solution(Solution solution) {
    this.model = EcoreUtil.copy(solution.getModel());
    this.transformationsChain =
        new LinkedList<Map<Integer, String>>(solution.getTransformationsChain());
  }

  public EObject getModel() {
    return model;
  }

  public void setModel(EObject model, List<String> transformations) {
    this.model = model;
  }

  public void updateModel(EObject model, Map<Integer, String> transformation) {

    if (this.model == null) {
      throw new RuntimeException("Trying to update the model on a solution with a null model.");
    }

    this.model = model;
    this.transformationsChain.add(transformation);
  }

  public LinkedList<Map<Integer, String>> getTransformationsChain() {
    return this.transformationsChain;
  }
}
