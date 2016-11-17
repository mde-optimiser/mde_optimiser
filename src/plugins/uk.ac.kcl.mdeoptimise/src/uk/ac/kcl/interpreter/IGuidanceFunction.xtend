package uk.ac.kcl.interpreter

import org.eclipse.emf.ecore.EObject

/**
 * This interface defines the contract for fitness functions.
 */
interface IGuidanceFunction {
    /**
     * Return the fitness of the given model. Higher fitness values are better.
     */
    def double computeFitness (EObject model)
    def String getName()
}