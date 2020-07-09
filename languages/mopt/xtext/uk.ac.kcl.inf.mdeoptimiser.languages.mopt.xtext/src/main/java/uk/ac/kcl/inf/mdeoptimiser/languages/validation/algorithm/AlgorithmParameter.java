package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm;

public class AlgorithmParameter {

  String name;
  String value;
  boolean required;

  public AlgorithmParameter(String name) {
    this.name = name;
    this.value = null;
    this.required = true;
  }

  public AlgorithmParameter(String name, String defaultValue) {
    this.name = name;
    this.value = defaultValue;
    this.required = false;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public boolean isRequired() {
    return this.required;
  }
}
