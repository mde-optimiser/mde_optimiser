package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions;

import java.util.List;
import java.util.stream.Collectors;
import org.moeaframework.core.TerminationCondition;
import org.moeaframework.core.termination.MaxElapsedTime;
import org.moeaframework.core.termination.MaxFunctionEvaluations;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;

public class TerminationConditionFactory {

  public static TerminationCondition getTerminationCondition(
      Parameter currentParameter, SolverSpec optimisationSpec) {

    switch (currentParameter.getName()) {
      case "time":
        {
          return new MaxElapsedTime(
              Integer.parseInt(currentParameter.getValue().getNumeric()) * 1000);
        }

      case "evolutions":
        {
          var populationSize = getPopulationSize(optimisationSpec);

          if (populationSize > 0) {
            return new MaxFunctionEvaluations(
                Integer.parseInt(currentParameter.getValue().getNumeric()) * populationSize);
          } else {
            return new MaxFunctionEvaluations(
                Integer.parseInt(currentParameter.getValue().getNumeric()));
          }
        }

        // This parameter has an optional dependency on iterations, alternatively it falls back to 3
      case "delta":
        {
          return new DeltaTerminationCondition(
              currentParameter,
              getTerminationConditionParameter(
                  "iterations", optimisationSpec.getTerminationCondition().getParameters()));
        }

      default:
        return null;
    }
  }

  private static Parameter getTerminationConditionParameter(
      String parameterKey, List<Parameter> allParameters) {

    var parameter =
        allParameters.stream()
            .filter(p -> p.getName().equals(parameterKey))
            .collect(Collectors.toList());

    if (parameter.size() == 1) {
      return parameter.get(0);
    }

    return null;
  }

  private static int getPopulationSize(SolverSpec optimisation) {
    var population =
        optimisation.getAlgorithm().getParameters().stream()
            .filter(parameter -> parameter.getName().equals("population"))
            .collect(Collectors.toList());

    if (!population.isEmpty()) {
      return Integer.parseInt(population.get(0).getValue().getNumeric());
    }

    return 0;
  }
}
