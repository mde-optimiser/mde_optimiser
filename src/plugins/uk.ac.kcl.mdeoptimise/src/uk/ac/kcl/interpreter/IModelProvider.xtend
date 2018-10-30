package uk.ac.kcl.interpreter

import java.util.Iterator
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.interpreter.guidance.Solution

/**
 * Strategy for generating initial models.
 */
interface IModelProvider {
    /**
     * Lazily generate initial models given a meta-model. This has to make sure that all models returned are instances of the given meta-model (in particular for dynamic model instances!).
     */
    def Iterator<Solution> initialModels(EPackage metamodel)
}