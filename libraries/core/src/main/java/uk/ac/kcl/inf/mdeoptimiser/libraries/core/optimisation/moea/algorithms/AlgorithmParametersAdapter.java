package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterValue;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParameterException;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.AlgorithmParametersConfiguration;

public class AlgorithmParametersAdapter {

  Map<Object, Object> parameters;

  public Map<Object, Object> algorithmParametersFactory(AlgorithmSpec algorithm) {

    this.parameters = new HashMap<>();

    var algorithmParameters = AlgorithmParametersConfiguration.getInstance().get(algorithm);

    algorithmParameters.forEach(
        algorithmParameter -> {
          if (algorithmParameter.isRequired()) {
            this.parameters.put(
                algorithmParameter,
                getAlgorithmParameterValue(algorithm.getParameters(), algorithmParameter));
          }
        });

    return this.parameters;
  }

  /**
   * Read the specified algorithm parameters based on the expected parameters specified in the
   * configuration.
   *
   * @param specifiedParameters of user specified parameters in the DSL
   * @param configuredParameter parameter to extract the value for from the specified list
   */
  private ParameterValue getAlgorithmParameterValue(
      List<Parameter> specifiedParameters, AlgorithmParameter configuredParameter) {

    if (configuredParameter.isRequired()) {

      var parameters =
          specifiedParameters.stream()
              .filter(
                  specifiedParameter ->
                      specifiedParameter.getName().equals(configuredParameter.getName()))
              .collect(Collectors.toList());

      // This parameter is missing or it appears multiple times
      if (parameters.isEmpty() || parameters.size() > 1) {
        throw new AlgorithmParameterException(configuredParameter.getName());
      }

      return parameters.get(0).getValue();
    }

    var parameters =
        specifiedParameters.stream()
            .filter(
                specifiedParameter ->
                    specifiedParameter.getName().equals(configuredParameter.getName()))
            .collect(Collectors.toList());

    // This optional parameter is specified
    if (!parameters.isEmpty()) {
      return parameters.get(0).getValue();
    }

    return null;
  }
}
