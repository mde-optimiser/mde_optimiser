package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions;

import com.google.common.collect.Streams;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import org.apache.commons.math3.ml.distance.EuclideanDistance;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.Population;
import org.moeaframework.core.TerminationCondition;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;

public class DeltaTerminationCondition implements TerminationCondition {

  double delta = 0d;
  double deltaSteps = 0d;
  Algorithm algorithm;

  Population lastPopulation;
  double totalDistanceSinceStart = 0;
  int unchangedDeltaSteps;

  public DeltaTerminationCondition(Parameter deltaParameter, Parameter deltaStepsParameter) {
    this.delta = Double.parseDouble(deltaParameter.getValue().getNumeric());
    setDeltaSteps(deltaStepsParameter);
  }

  public void setDeltaSteps(Parameter iterationsParameter) {

    if (iterationsParameter == null) {
      // Default to three iterations
      this.deltaSteps = 3;
    } else {
      this.deltaSteps = Double.parseDouble(iterationsParameter.getValue().getNumeric());
    }
  }

  @Override
  public void initialize(Algorithm algorithm) {
    this.algorithm = algorithm;
  }

  @Override
  public boolean shouldTerminate(Algorithm algorithm) {
    boolean terminate = false;
    if (lastPopulation == null) {
      lastPopulation = algorithm.getResult();
      unchangedDeltaSteps = 0;
    } else {
      if (!isDeltaThresholdChange(algorithm.getResult(), lastPopulation)) {
        this.unchangedDeltaSteps++;
        terminate = this.unchangedDeltaSteps > deltaSteps;
      } else {
        lastPopulation = algorithm.getResult();
        this.unchangedDeltaSteps = 0;
      }
    }
    return terminate;
  }

  /**
   * Checks if the difference between the current and last population exceeds at least a defined
   * percentage (delta) of the last meaningful difference recorded for the current optimization run.
   * This difference is defined by the sum of the distances of each element of the current
   * population from the last population. The distance of each such element, in turn, is defined by
   * the minimum of the euclidean distances to all elements of the last population. A difference is
   * considered meaningful if this check return true. In that case it will be used as the reference
   * for the delta calculation of follow-up calls to this method.
   *
   * @param currentPopulation
   * @param lastDeltaExceedingPopulation
   * @return
   */
  private boolean isDeltaThresholdChange(
      Population currentPopulation, Population lastDeltaExceedingPopulation) {

    var distanceMeasure = new EuclideanDistance();

    if (currentPopulation.isEmpty() || lastDeltaExceedingPopulation.isEmpty()) {
      return true;
    }

    double distanceToLastExeecdingPopulation =
        Streams.stream(currentPopulation.iterator())
            .map(
                currentSolution -> {
                  return Streams.stream(lastDeltaExceedingPopulation.iterator())
                      .map(
                          lastSolution -> {
                            double[] lastSolutionVector = lastSolution.getObjectives();
                            double[] currentSolutionVector = currentSolution.getObjectives();

                            if (this.algorithm.getProblem().getNumberOfConstraints() > 0) {

                              lastSolutionVector =
                                  DoubleStream.concat(
                                          Arrays.stream(lastSolutionVector),
                                          Arrays.stream(lastSolution.getConstraints()))
                                      .toArray();
                              currentSolutionVector =
                                  DoubleStream.concat(
                                          Arrays.stream(currentSolutionVector),
                                          Arrays.stream(currentSolution.getConstraints()))
                                      .toArray();
                            }

                            double solutionDistance =
                                distanceMeasure.compute(lastSolutionVector, currentSolutionVector);
                            return solutionDistance;
                          })
                      .mapToDouble(d -> d)
                      .min()
                      .orElseThrow(); // There should be a min as both populations are not empty.
                })
            .collect(Collectors.summingDouble(Double::doubleValue));

    if (distanceToLastExeecdingPopulation == 0d) {
      return false;
    }

    if (!(this.totalDistanceSinceStart == 0)) {
      var delta = distanceToLastExeecdingPopulation / this.totalDistanceSinceStart * 100;
      if (delta < this.delta) {
        return false;
      }
    }

    totalDistanceSinceStart += distanceToLastExeecdingPopulation;

    return true;
  }
}
