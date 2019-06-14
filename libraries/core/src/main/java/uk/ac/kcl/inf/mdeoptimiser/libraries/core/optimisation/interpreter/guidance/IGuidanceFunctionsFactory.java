package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance;

import java.io.InvalidObjectException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;

public interface IGuidanceFunctionsFactory {

  /**
   * Given an objective descriptor initialize the correct objective function.
   *
   * @throws InvalidObjectException
   */
  IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter);
}
