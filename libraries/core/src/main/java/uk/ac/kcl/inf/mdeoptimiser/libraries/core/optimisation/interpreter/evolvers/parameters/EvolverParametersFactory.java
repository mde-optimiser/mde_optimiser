package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.EvolverSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.functions.RandomEvolverParameter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.reflection.LanguageClassLoader;

public class EvolverParametersFactory implements IEvolverParametersFactory {

  List<EvolverSpec> evolvers;
  // Triple <UnitName, <ParameterName, Function>>
  Map<String, Map<String, IEvolverParametersFunction>> evolverParameterFunctions;

  /** Cache parameter functions */
  HashMap<String, IEvolverParametersFunction> cachedFunctions =
      new HashMap<String, IEvolverParametersFunction>();

  // TODO Add an evolver spec adapter
  public EvolverParametersFactory(List<EvolverSpec> evolvers) {
    this.evolvers = evolvers;

    // Load the parameter functions for each of the evolvers specified in the DSL
    loadParameters();
  }

  public void loadParameters() {

    evolverParameterFunctions = new HashMap<String, Map<String, IEvolverParametersFunction>>();

    for (var evolverId = 0; evolverId < evolvers.size(); evolverId++) {

      var evolver = evolvers.get(evolverId);

      var parameters = new HashMap<String, IEvolverParametersFunction>();

      evolver.getParameters().stream()
          .forEach(
              parameter ->
                  parameters.put(
                      parameter.getName(), getParameter(new EvolverParameterAdapter(parameter))));

      evolverParameterFunctions.put(evolver.getUnit(), parameters);
    }
  }

  public IEvolverParametersFunction getParameter(EvolverParameterAdapter parameter) {
    switch (parameter.function) {
      case "Random":
        return new RandomEvolverParameter(parameter.parameter);
      default:
        return loadCustomFunction(parameter);
    }
  }

  public IEvolverParametersFunction loadCustomFunction(EvolverParameterAdapter parameter) {

    // Is there is a key for the given function path cached, return it
    if (cachedFunctions.containsKey(parameter.function)) {
      return cachedFunctions.get(parameter.function);
    }

    var function = LanguageClassLoader.load(parameter);
    cachedFunctions.put(parameter.function, function);

    return function;
  }

  @Override
  public Object getParameterValue(Unit unit, Parameter parameter, List<Solution> solution) {

    var parameterFunction = evolverParameterFunctions.get(unit.getName()).get(parameter.getName());

    return parameterFunction.sample(solution);
  }
}
