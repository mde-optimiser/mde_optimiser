package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm;

public class UnexpectedAlgorithmException extends RuntimeException {

  public UnexpectedAlgorithmException(String algorithmName) {
    super(String.format("Unexpected algorithm name: %s", algorithmName));
  }
}
