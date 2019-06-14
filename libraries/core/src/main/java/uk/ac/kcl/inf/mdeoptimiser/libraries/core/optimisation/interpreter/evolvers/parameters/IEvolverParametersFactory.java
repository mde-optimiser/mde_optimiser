package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters;

import java.util.List;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface IEvolverParametersFactory {

  /** Given an objective descriptor initialize the correct objective function. */
  Object getParameterValue(Unit unit, Parameter parameter, List<Solution> model);
}
