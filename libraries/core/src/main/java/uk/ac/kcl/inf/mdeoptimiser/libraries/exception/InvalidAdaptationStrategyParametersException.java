package uk.ac.kcl.inf.mdeoptimiser.libraries.exception;

public class InvalidAdaptationStrategyParametersException extends Exception {

  public InvalidAdaptationStrategyParametersException(String strategyParameters) {
    super(
        String.format(
            "Invalid adaptation strategy parameters. Parameters given: %s", strategyParameters));
  }
}
