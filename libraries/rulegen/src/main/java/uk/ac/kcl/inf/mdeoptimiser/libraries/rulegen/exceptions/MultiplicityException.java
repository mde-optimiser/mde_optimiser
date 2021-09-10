package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions;

import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;

public class MultiplicityException extends RuntimeException {

  public MultiplicityException(String message) {
    super(message);
  }

  public MultiplicityException(Multiplicity multiplicity) {
    this(multiplicity.lower, multiplicity.upper);
  }

  public MultiplicityException(Integer lower, Integer upper) {
    super(
        "Invalid multiplicity values given. "
            + "Lower multiplicity given: "
            + lower
            + "Upper multiplicity given: "
            + upper);
  }

  public MultiplicityException(String message, Integer lower, Integer upper) {
    super(message + "Lower multiplicity given: " + lower + "Upper multiplicity given: " + upper);
  }

  public MultiplicityException(
      String message, Integer lower, Integer upper, Integer originalLower, Integer originalUpper) {
    super(
        String.format(
            "%s Lower multiplicity given: %s. Upper multiplicity given: %s Original multiplicities lower: %s upper %s",
            message, lower, upper, originalLower, originalUpper));
  }

  public MultiplicityException(Throwable cause) {
    super(cause);
  }

  public MultiplicityException(String message, Throwable cause) {
    super(message, cause);
  }
}
