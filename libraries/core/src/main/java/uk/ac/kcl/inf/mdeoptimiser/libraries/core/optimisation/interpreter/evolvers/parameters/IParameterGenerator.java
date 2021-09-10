package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;

/**
 * This is a function that needs to be implemented in order to get custom parameters for Henshin
 * rules. TODO: Refactor this interface to a package that is going to be used for external
 * implementation
 */
public interface IParameterGenerator {
  Map<String, Object> get(EObject object);
}
