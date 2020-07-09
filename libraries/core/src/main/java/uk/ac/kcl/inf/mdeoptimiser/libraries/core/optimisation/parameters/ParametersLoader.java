package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters;

import java.util.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.XNumberLiteral;
import org.eclipse.xtext.xbase.XStringLiteral;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;

public class ParametersLoader {

  private List<Parameter> parameters;
  private Map<String, ParameterSpec> parsedParameters = new HashMap<>();
  private DefaultParameterSpecs defaultParameterSpecs;

  public ParametersLoader(List<Parameter> parameters) {
    this.parameters = parameters;
    this.defaultParameterSpecs = new DefaultParameterSpecs();
  }

  /**
   * Parse the parameter stored for the given key. Parse the value un a pair containing the
   * parameter value and any attributes.
   *
   * @param parameterKey
   * @return a parameter spec instance for the given parameter key
   */
  public ParameterSpec get(String parameterKey) {

    // Load the spec from cache
    if (this.parsedParameters.containsKey(parameterKey)) {
      return this.parsedParameters.get(parameterKey);
    }

    // Extract the parameter from the algorithm parameter spec list
    var parameterSpec = this.parse(parameterKey);

    // If no parameter specified, try to get a default, hoping one is specified
    if (parameterSpec == null) {
      parameterSpec = this.defaultParameterSpecs.getDefaultParameterSpec(parameterKey);
    }

    // TODO Probably fail completely here
    if (parameterSpec == null) {
      throw new RuntimeException(
          String.format("Could not find a parameter with the key: %s", parameterKey));
    }

    // Return the parsed parameter value and arguments as a pair.
    return parameterSpec;
  }

  private ParameterSpec parse(String parameterKey) {

    var parameter = this.getParameter(parameterKey);

    if (parameter == null) {
      return null;
    }

    // Get the parameter value (numeric, functional, or parametric)
    var parameterValue = this.getParameterValue(parameter);

    // If the parameter type is parametric, then extract the argument values using the given keys
    var parameterArguments = this.getParameterValueArguments(parameter);

    // Cache
    return new ParameterSpec(parameter, parameterKey, parameterValue, parameterArguments);
  }

  /**
   * Extract the parameter value from the given parameter instance. The parameter value can take any
   * of the three accepted formats: - numeric, encountered for parameters that are just a number.
   * Eg: no of evolutions - functional, encountered for parameters that take the value of a
   * function. Eg: random - parametric, encountered for parameters that take the value of a function
   * with attributes. Eg: random(1, 2, 3)
   *
   * @param parameter
   * @return
   */
  public String getParameterValue(Parameter parameter) {

    // Parses something like: 100
    if (parameter.getValue().getNumeric() != null) {
      return parameter.getValue().getNumeric();
    }

    // Parses something like: value
    if (parameter.getValue().getFunctional() != null) {
      return parameter.getValue().getFunctional();
    }

    // Then it must be parametric
    // Parses something like: value(1, 2, 3)
    // if(parameter.getValue().getParametric() != null){
    return parameter.getValue().getParametric();
    // }
  }

  /**
   * If this parameter is a function that takes parameters. Parse those parameters to into a map
   * using the expected argument keys. The order is important when writing parameter values in the
   * parameter function.
   *
   * @param parameter
   * @return
   */
  private LinkedList<String> getParameterValueArguments(Parameter parameter) {

    var parsedParameterValues = new LinkedList<String>();
    var parameterArguments = parameter.getValue().getParams();

    if (parameterArguments.size() > 0) {
      parameterArguments.forEach(p -> parsedParameterValues.add(getParameterArgumentValue(p)));
    }

    return parsedParameterValues;
  }

  /**
   * Get the parameter argument value as a string.
   *
   * @param parameterArgument
   * @return
   */
  private String getParameterArgumentValue(EObject parameterArgument) {
    // Here we expect two types really, a string or a numeral
    if (parameterArgument instanceof XNumberLiteral) {
      return ((XNumberLiteral) parameterArgument).getValue();
    }
    return ((XStringLiteral) parameterArgument).getValue();
  }

  /**
   * Load the parameter with the given key
   *
   * @param key
   * @return eobject parameter instance
   */
  private Parameter getParameter(String key) {

    var optionalParameter =
        this.parameters.stream().filter(parameter -> parameter.getName().equals(key)).findFirst();

    return optionalParameter.orElse(null);

    // No param configured by the DSL

    //    //TODO Might need to configure default parameter values here
    //    // Throw a runtime exception if the parameter with the given key is not configured
    //    throw new RuntimeException(String.format("Could not find a parameter with the key: %s",
    // key));
  }
}
