package uk.ac.kcl.inf.mdeoptimiser.languages.validation;

import org.eclipse.xtext.validation.Check;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.TerminationConditionSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParametersConfiguration;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
public class MoptValidator extends AbstractMoptValidator {

  /**
   * Check that there are no duplicate parameter in the algorithm specification
   */
  @Check
  public void checkAlgorithmSpecHasNoDuplicateParameters(AlgorithmSpec algorithm) {

    var dupParameters = getDupplicateParameters(algorithm.getParameters());

    dupParameters.forEach ( (p1, p2) ->
                    error(
                            String.format(
                                    "Duplicate parameter found. Expecting '%s' to be specified only once. Specified %s times instead",
                                    p1, p2.size()), algorithm, null, MoptValidatorIssues.DUPLICATE_PARAMETER_ENCOUNTERED));

  }

  //TODO Check that all the required hyperparameter spec parameters are specified

  //TODO Check that there are no duplicate hyperparameter spec parameters specified

  //TODO Check that there are no unexpected hyperparameter spec parameters specified
  @Check
  public void checkTerminationSpecHasNoDuplicateParameters(TerminationConditionSpec terminationCondition) {

    var dupParameters = getDupplicateParameters(terminationCondition.getParameters());

    dupParameters.forEach((p1, p2) ->
            error(
                    String.format(
                            "Duplicate parameter found. Expecting '%s' to be specified only once. Specified '%s' times instead",
                            p1, p2.size()), terminationCondition, null, MoptValidatorIssues.DUPLICATE_PARAMETER_ENCOUNTERED));
  }

  /**
   * Check that all the configured required algorithm parameters are specified.
   */
  @Check
  public void checkAlgorithmHasAllRequiredParameters(AlgorithmSpec algorithm) {

    var requiredParameters = AlgorithmParametersConfiguration.getInstance().get(algorithm).stream().filter ( parameter ->
            parameter.isRequired());

    requiredParameters.forEach ( requiredParameter -> {
    if (!algorithm.getParameters()
            .stream()
            .anyMatch(algorithmParameter -> algorithmParameter.getName().equals(requiredParameter.getName()))) {
      error(String.format("Required parameter '%s' must be specified.", requiredParameter.getName()), null,
              MoptValidatorIssues.REQUIRED_PARAMETER_NOT_FOUND);
    }
  });
  }

  /**
   * Check that no unexpected algorithm parameters are specified.
   */
  @Check
  public void checkAlgorithmHasNoRedundantParameters(AlgorithmSpec algorithm) {

    var expectedParameters = AlgorithmParametersConfiguration.getInstance().get(algorithm);

    algorithm.getParameters().forEach ( algoParameter -> {

      if (!expectedParameters.stream().anyMatch(algorithmParameter -> algorithmParameter.getName().equals(algoParameter.getName()))) {
        warning(String.format("Unexpected parameter '%s' will be ignored.", algoParameter.getName()), null,
                MoptValidatorIssues.UNEXPECTED_PARAMETER_FOUND);
      }
    });
  }

  private Map<String, List<Parameter>> getDupplicateParameters(List<Parameter> parameters) {
    return parameters.stream()
            .collect(Collectors.groupingBy(x -> x.getName())).entrySet()
            .stream()
            .filter(x -> x.getValue().size() > 1)
            .collect(Collectors.toMap(x-> x.getKey(), x->x.getValue()));
  }

}
