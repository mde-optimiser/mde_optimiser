package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators;

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters.ParametersLoader;

public class SearchOperatorParameterInterpreter {

  private final AlgorithmSpec algorithmSpec;
  private final ParametersLoader parametersLoader;

  public SearchOperatorParameterInterpreter(AlgorithmSpec algorithmSpec) {

    this.algorithmSpec = algorithmSpec;
    this.parametersLoader = new ParametersLoader(algorithmSpec.getParameters());
  }
}
