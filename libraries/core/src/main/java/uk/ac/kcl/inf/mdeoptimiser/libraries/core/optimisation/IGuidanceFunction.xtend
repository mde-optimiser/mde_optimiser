package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

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
