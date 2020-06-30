package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation;

import java.util.Iterator;
import org.eclipse.emf.ecore.EPackage;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

/** Strategy for generating initial models. */
public interface IModelProvider {
  /**
   * Lazily generate initial models given a meta-model. This has to make sure that all models
   * returned are instances of the given meta-model (in particular for dynamic model instances!).
   */
  Iterator<Solution> initialModels(EPackage metamodel);
}
