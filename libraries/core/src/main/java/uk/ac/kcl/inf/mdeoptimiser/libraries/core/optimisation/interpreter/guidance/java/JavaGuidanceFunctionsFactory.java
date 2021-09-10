package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.java;

import java.util.HashMap;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.IGuidanceFunctionsFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.reflection.LanguageClassLoader;

public class JavaGuidanceFunctionsFactory implements IGuidanceFunctionsFactory {

  /** Cache functions */
  HashMap<String, IGuidanceFunction> functions;

  public JavaGuidanceFunctionsFactory() {

    functions = new HashMap<>();
  }

  /**
   * Loads a function from a given Java class file present on the classpath. If this is not the
   * first invocation of this function then a cached instance of the objective java class will be
   * returned, otherwise a new instance will be created, cached and returned.
   *
   * @return instance of the fitness function described in the objective intereter
   */
  public IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter) {

    // Is there is a key for the given function path cached, return it
    if (functions.containsKey(guidanceFunctionAdapter.functionSpec)) {
      return functions.get(guidanceFunctionAdapter.functionSpec);
    }

    var function = LanguageClassLoader.load(guidanceFunctionAdapter);

    functions.put(guidanceFunctionAdapter.functionSpec, function);

    return function;
  }
}
