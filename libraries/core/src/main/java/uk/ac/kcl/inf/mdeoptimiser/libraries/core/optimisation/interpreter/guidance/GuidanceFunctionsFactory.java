package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.java.JavaGuidanceFunctionsFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.ocl.OclGuidanceFunctionsFactory;

public class GuidanceFunctionsFactory implements IGuidanceFunctionsFactory {

  IGuidanceFunctionsFactory javaObjectiveEngine;
  IGuidanceFunctionsFactory oclObjectiveEngine;

  public GuidanceFunctionsFactory() {
    javaObjectiveEngine = new JavaGuidanceFunctionsFactory();
    oclObjectiveEngine = new OclGuidanceFunctionsFactory();
  }

  public IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter) {
    switch (guidanceFunctionAdapter.functionType) {
      case "java":
        return javaObjectiveEngine.loadFunction(guidanceFunctionAdapter);
      case "ocl":
        return oclObjectiveEngine.loadFunction(guidanceFunctionAdapter);
      default:
        // TODO perhaps this is not the best exception to use here. Implement our own?
        throw new RuntimeException(
            "Invalid objective type: " + guidanceFunctionAdapter.functionType);
    }
  }
}
