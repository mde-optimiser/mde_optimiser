package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters;

import java.util.*;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;

public class ParameterSpec {

  private Parameter parameter;
  private final String parameterName;
  private String parameterValue;
  private final LinkedList<String> parameterValueArguments;
  private Map<String, String> mappedParameterValues;

  public ParameterSpec(
      Parameter parameter,
      String parameterName,
      String parameterValue,
      LinkedList<String> parameterValueArguments) {
    this.parameter = parameter;
    this.parameterName = parameterName;
    this.parameterValue = parameterValue;
    this.parameterValueArguments = parameterValueArguments;
  }

  public ParameterSpec(
      String parameterName, String parameterValue, LinkedList<String> parameterValueArguments) {
    this.parameter = null;
    this.parameterName = parameterName;
    this.parameterValue = parameterValue;
    this.parameterValueArguments = parameterValueArguments;
  }

  /**
   * Get the MOPT instance of the parameter.
   *
   * @return
   */
  public Parameter getParameter() {
    return this.parameter;
  }

  public String getValue() {
    return this.parameterValue;
  }

  /**
   * Get the parameter name.
   *
   * @return the DSL name of this parameter
   */
  public String getName() {
    return this.parameterName;
  }

  /**
   * Get the ordered list of parsed parameter values.
   *
   * @return list of parameter values
   */
  public LinkedList<String> getValueArguments() {
    return this.parameterValueArguments;
  }

  /**
   * Get the parsed parameter values mapped to the given parameter keys.
   *
   * @param parameterValueKeys
   * @return list of mapped parameter values
   */
  public Map<String, String> getValueArguments(List<String> parameterValueKeys) {

    // Overly confident here
    if (this.mappedParameterValues != null) {
      return this.mappedParameterValues;
    }

    if (parameterValueKeys.size() == this.parameterValueArguments.size()) {
      this.mappedParameterValues = new HashMap<String, String>();

      for (int i = 0; i < parameterValueKeys.size(); i++) {
        this.mappedParameterValues.put(
            parameterValueKeys.get(i), this.parameterValueArguments.get(i));
      }

      return this.mappedParameterValues;
    }

    // Mismatched parameter keys and values.
    throw new RuntimeException(
        String.format(
            "The expected number of parameter arguments "
                + "does not match the number of configured argument keys for parameter: %s. Arguments given %s expected %s",
            this.parameterName, parameterValueArguments.size(), parameterValueKeys.size()));
  }
}
