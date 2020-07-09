package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance;

import java.util.LinkedList;
import org.apache.commons.math3.util.Pair;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.model.Unit;

public class Solution {

  private EObject model;
  private LinkedList<LinkedList<Unit>> transformationsChain;
  private LinkedList<Pair<Double, Double>> parentMutationSteps;
  private Pair<Double, Double> mutationStep;

  public Solution(EObject model) {
    this(model, new LinkedList<>());
  }

  public Solution(EObject model, LinkedList<LinkedList<Unit>> transformationsChain) {
    this.model = model;
    this.transformationsChain = transformationsChain;
    this.parentMutationSteps = new LinkedList<>();
  }

  public Solution(Solution solution) {
    this(solution, false);
  }

  /**
   * Create a copy of this solution without the EObject instance
   *
   * @param solution
   * @param lightCopy
   */
  public Solution(Solution solution, boolean lightCopy) {

    if (!lightCopy) {
      this.model = EcoreUtil.copy(solution.getModel());
    }

    this.transformationsChain = new LinkedList<>(solution.getTransformationsChain());

    if (solution.getParentMutationSteps() != null) {
      this.parentMutationSteps = new LinkedList<>(solution.getParentMutationSteps());
    } else {
      this.parentMutationSteps = new LinkedList<>();
    }

    if (solution.mutationStep != null) {
      this.parentMutationSteps.add(solution.mutationStep);
    }
  }

  public EObject getModel() {
    return model;
  }

  public void updateModel(EObject model, LinkedList<Unit> transformations) {

    if (this.model == null) {
      throw new RuntimeException("Trying to update the model on a solution with a null model.");
    }

    this.model = model;
    this.transformationsChain.add(transformations);
  }

  public LinkedList<LinkedList<Unit>> getTransformationsChain() {
    return this.transformationsChain;
  }

  public LinkedList<Pair<Double, Double>> getParentMutationSteps() {

    if (this.parentMutationSteps != null) {
      return this.parentMutationSteps;
    }

    return new LinkedList<>();
  }

  public Pair<Double, Double> getParentMutationStep() {

    if (this.parentMutationSteps != null && this.parentMutationSteps.size() > 0) {
      return this.parentMutationSteps.getLast();
    }

    return null;
  }

  public Pair<Double, Double> getMutationStep() {
    return this.mutationStep;
  }

  public void setMutationStep(Pair<Double, Double> mutationStep) {
    this.mutationStep = mutationStep;
  }
}
