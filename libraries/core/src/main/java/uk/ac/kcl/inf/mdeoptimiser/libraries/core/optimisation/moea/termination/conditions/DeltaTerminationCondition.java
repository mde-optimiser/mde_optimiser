package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions;

import com.google.common.collect.Streams;
import java.util.stream.Collectors;
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
  double lastDistance;
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
    if (lastPopulation == null) {

      lastPopulation = algorithm.getResult();
      unchangedDeltaSteps = 0;

      return false;

    } else {

      if (isDeltaThresholdChange(algorithm.getResult(), lastPopulation)) {
        this.unchangedDeltaSteps++;
        return this.unchangedDeltaSteps > deltaSteps;

      } else {
        lastPopulation = algorithm.getResult();
        this.unchangedDeltaSteps = 0;
        return false;
      }
    }
  }

  private boolean isDeltaThresholdChange(Population currentPopulation, Population lastPopulation) {

    var distanceMeasure = new EuclideanDistance();

    if (currentPopulation.isEmpty() || lastPopulation.isEmpty()) {
      return false;
    }

    double totalDistance =
        Streams.stream(currentPopulation.iterator())
            .map(
                currentSolution -> {
                  return Streams.stream(lastPopulation.iterator())
                      .map(
                          lastSolution -> {
                            double currentDistance = 0d;

                            if (this.algorithm.getProblem().getNumberOfConstraints() > 0) {
                              currentDistance +=
                                  distanceMeasure.compute(
                                      lastSolution.getConstraints(),
                                      currentSolution.getConstraints());
                            }

                            currentDistance +=
                                distanceMeasure.compute(
                                    lastSolution.getObjectives(), currentSolution.getObjectives());

                            return currentDistance;
                          })
                      .collect(Collectors.summarizingDouble(x -> x + x))
                      .getSum();
                })
            .collect(Collectors.summarizingDouble(x -> x + x))
            .getSum();

    if (totalDistance == 0d) {
      return true;
    }

    var delta = (this.lastDistance - totalDistance) / totalDistance * 100;

    this.lastDistance = totalDistance;

    if (Math.abs(delta) < this.delta) {
      return true;
    }

    return false;
  }
}
