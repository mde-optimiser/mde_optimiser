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
  double smallestDeltaExceedingDistance = Double.POSITIVE_INFINITY;
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

      if (!isDeltaThresholdChange(algorithm.getResult(), lastPopulation)) {
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
      return true;
    }

    double totalDistance =
        Streams.stream(currentPopulation.iterator())
            .map(
                currentSolution -> {
                  return Streams.stream(lastPopulation.iterator())
                      .map(
                          lastSolution -> {
                            double[] lastSolutionVector = lastSolution.getObjectives();
                            double[] currentSolutionVector = currentSolution.getObjectives();                 

                            if (this.algorithm.getProblem().getNumberOfConstraints() > 0) {
                            
                              lastSolutionVector = DoubleStream.concat(
                            		  Arrays.stream(lastSolutionVector), 
                            		  Arrays.stream(lastSolution.getConstraints())).toArray();
                              currentSolutionVector = DoubleStream.concat(
                            		  Arrays.stream(currentSolutionVector), 
                            		  Arrays.stream(currentSolution.getConstraints())).toArray();
                            }     

                            double currentDistance = distanceMeasure.compute(
                                    lastSolutionVector,
                                    currentSolutionVector);
                            return currentDistance;
                          })
                      .mapToDouble(d -> d)
                      .min().orElseThrow(); //There should be a min as both populations are not empty.
                })
            .collect(Collectors.summingDouble(Double::doubleValue));

    if (totalDistance == 0d) {
      return false;
    }

    if (!(this.smallestDeltaExceedingDistance == Double.POSITIVE_INFINITY)) {
    	var delta = totalDistance / this.smallestDeltaExceedingDistance * 100;
    	if (delta < this.delta) {
    		return false;
    	}
    }

//    if (smallestDeltaExceedingDistance > totalDistance) {
  	this.smallestDeltaExceedingDistance = totalDistance;
//    }
    return true;
  }
}
