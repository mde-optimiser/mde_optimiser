package uk.ac.kcl.interpreter

import java.util.Set
import org.eclipse.emf.ecore.EObject

/**
 * A specific optimisation algorithm
 */
interface OptimisationAlgorithm {

    /**
     * Run the optimisation algorithm and return its results (this may be a single solution or a
     * Pareto set).
     */
    def Set<EObject> execute(OptimisationInterpreter interpreter)
}