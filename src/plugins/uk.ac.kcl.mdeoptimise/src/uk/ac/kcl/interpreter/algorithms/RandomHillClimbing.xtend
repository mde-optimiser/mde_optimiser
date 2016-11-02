package uk.ac.kcl.interpreter.algorithms

import uk.ac.kcl.interpreter.OptimisationAlgorithm
import uk.ac.kcl.interpreter.OptimisationInterpreter

/**
 * A strategy implementing random hill climbing over models.
 * This is single objective only, using only the objective function named first in the specification.
 */
class RandomHillClimbing implements OptimisationAlgorithm {

    /**
     * Maximum number of iterations to run for
     */
    private int maxIterations

    public new(int maxIterations) {
        this.maxIterations = maxIterations
    }

    override execute(OptimisationInterpreter interpreter) {
        // The current best solution found
        var currentSolution = interpreter.initialSolutions.head
        // The fitness of the current best solution found
        var currentFitness = interpreter.fitness(currentSolution).head

        for (i : 0 ..< maxIterations) {
            val candidateSolution = interpreter.evolve(currentSolution)
            val candidateFitness = interpreter.fitness(candidateSolution).head
            if (candidateFitness >= currentFitness) {
                currentSolution = candidateSolution
                currentFitness = candidateFitness
            }
        }

        #{currentSolution}
    }
}