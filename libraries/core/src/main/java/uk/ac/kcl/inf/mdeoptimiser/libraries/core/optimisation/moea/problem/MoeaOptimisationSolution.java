package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem;

import java.util.LinkedHashMap;
import org.moeaframework.core.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;

public class MoeaOptimisationSolution extends Solution {

  SolutionGenerator solutionGenerator;

  public MoeaOptimisationSolution(MoeaOptimisationSolution moeaOptimisationSolution) {
    super(moeaOptimisationSolution);
    this.setModel(moeaOptimisationSolution.getModel());
    this.setSolutionGenerator(moeaOptimisationSolution.getSolutionGenerator());
  }

  public MoeaOptimisationSolution(int numberOfObjectives, int numberOfConstraints) {
    super(1, numberOfObjectives, numberOfConstraints);
  }

  public MoeaOptimisationSolution(SolutionGenerator solutionGenerator) {
    this(
        solutionGenerator.getOptimisationModel().getGoal().getObjectives().size(),
        solutionGenerator.getOptimisationModel().getGoal().getConstraints().size());
    this.solutionGenerator = solutionGenerator;
    setModel(solutionGenerator.mutate(solutionGenerator.getInitialSolutions().next()));
  }

  public MoeaOptimisationSolution copy() {
    return new MoeaOptimisationSolution(this);
  }

  public uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
      getModel() {
    return ((MoeaOptimisationVariable) getVariable(0)).getModel();
  }

  public void setModel(
      uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution model) {
    setVariable(0, new MoeaOptimisationVariable(model, solutionGenerator));
  }

  public SolutionGenerator getSolutionGenerator() {
    return this.solutionGenerator;
  }

  public void setSolutionGenerator(SolutionGenerator solutionGenerator) {
    this.solutionGenerator = solutionGenerator;
  }

  public LinkedHashMap<String, Double> getFormattedObjectives() {

    var formattedObjectives = new LinkedHashMap<String, Double>();

    var moptObjectives = solutionGenerator.getOptimisationModel().getGoal().getObjectives();

    for (var i = 0; i < moptObjectives.size(); i++) {
      formattedObjectives.put(moptObjectives.get(i).getObjectiveName(), this.getObjectives()[i]);
    }

    return formattedObjectives;
  }

  public LinkedHashMap<String, Double> getFormattedConstraints() {
    var formattedConstraints = new LinkedHashMap<String, Double>();

    var moptConstraints = solutionGenerator.getOptimisationModel().getGoal().getConstraints();

    for (var i = 0; i < moptConstraints.size(); i++) {
      formattedConstraints.put(
          moptConstraints.get(i).getConstraintName(), this.getConstraints()[i]);
    }

    return formattedConstraints;
  }

  public String toString() {

    var sb = new StringBuilder();

    fitnessLoop(sb, getObjectives());
    fitnessLoop(sb, getConstraints());

    return sb.toString();
  }

  private StringBuilder fitnessLoop(StringBuilder sb, double[] vector) {

    if (vector.length > 0) {

      sb.append("[");

      for (var i = 0; i < vector.length; i++) {
        sb.append(vector[i]);

        if (i < vector.length - 1) {
          sb.append(",");
        }
      }

      sb.append("]");
    }
    return sb;
  }
}
