package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.reporting;

import java.security.InvalidParameterException;
import java.util.List;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;

// TODO: Should probably check if there are no valid solutions, to look at constraints only in that
// case
public class GuidanceVectorsHelper {

  private double[] referencePoint;
  private double[] idealPoint;
  private Problem problem;

  public GuidanceVectorsHelper(Problem problem) {
    this.problem = problem;
  }

  /**
   * Consider objective points from a single solution and update the guidance points if any of the
   * solution objectives are better(worse) than the already known values.
   *
   * @param solution
   */
  public void consider(Solution solution) {
    // TODO check if the solution violates the constraints?
    // Do we consider these points in this case or to we swap to constraints only if no solution
    // with
    // valid constraints satisfied is identified?

    if (solution.violatesConstraints()) {
      return;
    }

    if (this.referencePoint == null && this.idealPoint == null) {
      this.setReferencePoint(solution.getObjectives());
      this.setIdealPoint(solution.getObjectives());

      return;
    }

    // All problems are minimisation
    for (int i = 0; i < solution.getObjectives().length; i++) {

      // Worst objectives - greater are better
      if (this.referencePoint[i] < solution.getObjective(i)) {

        // Add 1 to avoid out of range errors in the Normalizer
        this.referencePoint[i] = solution.getObjective(i) + 1;
      }

      // Best objectives - smaller are better
      if (this.idealPoint[i] > solution.getObjective(i)) {
        this.idealPoint[i] = solution.getObjective(i);
      }
    }
  }

  /**
   * Consider objective points from multiple solutions and update the guidance points if any of the
   * solution objectives are better(worse) than the already known values.
   *
   * @param solutions
   */
  public void considerAll(List<Solution> solutions) {
    for (Solution solution : solutions) {
      this.consider(solution);
    }
  }

  /**
   * Return a solution with the best objective values from the considered solutions.
   *
   * @return solution
   */
  public Solution getIdealSolution() {

    if (this.idealPoint == null) {
      throw new InvalidParameterException("No valid solutions considered to identify ideal point.");
    }

    Solution solution = problem.newSolution();

    if (solution == null) {
      throw new InvalidParameterException(
          "Unexpected null solution when seeking guidance vectors.");
    }

    solution.setObjectives(this.idealPoint);

    return solution;
  }

  /**
   * Return a solution with the worst objective values from the considered solutions.
   *
   * @return solution
   */
  public Solution getReferenceSolution() {

    if (this.referencePoint == null) {
      throw new InvalidParameterException(
          "No valid solutions considered to identify reference point.");
    }

    Solution solution = problem.newSolution();
    solution.setObjectives(this.referencePoint);

    return solution;
  }

  private void setReferencePoint(double[] objectives) {

    double[] referencePointBaseCoordinates = new double[objectives.length];

    for (int i = 0; i < objectives.length; i++) {

      referencePointBaseCoordinates[i] = objectives[i] + 1;
    }

    this.referencePoint = referencePointBaseCoordinates;
  }

  private void setIdealPoint(double[] objectives) {
    double[] idealPointBaseCoordinates = new double[objectives.length];

    for (int i = 0; i < objectives.length; i++) {

      idealPointBaseCoordinates[i] = objectives[i];
    }

    this.idealPoint = idealPointBaseCoordinates;
  }
}
