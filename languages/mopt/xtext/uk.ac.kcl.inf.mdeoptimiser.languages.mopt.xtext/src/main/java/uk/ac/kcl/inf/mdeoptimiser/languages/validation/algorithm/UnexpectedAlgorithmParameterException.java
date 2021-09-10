package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm;

public class UnexpectedAlgorithmParameterException extends RuntimeException {

  public UnexpectedAlgorithmParameterException(String parameterName) {
    super(String.format("Unexpected algorithm parameter: %s", parameterName));
  }

  public UnexpectedAlgorithmParameterException(String parameterName, String parameterValue) {
    super(
        String.format(
            "Unexpected algorithm parameter %s with value %s", parameterName, parameterValue));
  }
}
