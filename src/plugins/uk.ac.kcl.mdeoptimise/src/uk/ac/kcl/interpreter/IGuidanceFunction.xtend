package uk.ac.kcl.interpreter

import uk.ac.kcl.interpreter.guidance.Solution

/**
 * This interface defines the contract for fitness functions.
 */
interface IGuidanceFunction {
    /**
     * Return the fitness of the given model. Higher fitness values are better.
     */
    def double computeFitness (Solution model)
    def String getName()
}