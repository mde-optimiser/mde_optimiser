package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters;

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.EvolverParameter;

public class EvolverParameterAdapter {

  public String name;
  public String function;
  public String parameter;

  public EvolverParameterAdapter(EvolverParameter evolverParameterSpec) {
    name = evolverParameterSpec.getName();

    if (evolverParameterSpec.getFunction() != null) {
      function = evolverParameterSpec.getFunction().getName();
      parameter = evolverParameterSpec.getFunction().getParameter();
    } else {
      function = evolverParameterSpec.getCustomFunction();
    }
  }
}
