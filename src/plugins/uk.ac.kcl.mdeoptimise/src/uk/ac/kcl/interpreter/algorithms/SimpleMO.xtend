package uk.ac.kcl.interpreter.algorithms

import java.util.ArrayList
import uk.ac.kcl.interpreter.OptimisationAlgorithm
import uk.ac.kcl.interpreter.OptimisationInterpreter

/**
 * A simple multi-objective optimisation algorithm.
 */
class SimpleMO implements OptimisationAlgorithm {

    /**
     * Maximum number of iterations to run for
     */
    private int maxIterations

    /**
     * Maximum size of populations to keep around
     */
    private int maxPopulationSize

    public new(int maxIterations, int maxPopulationSize) {
        this.maxIterations = maxIterations
        this.maxPopulationSize = maxPopulationSize
    }

    override execute(OptimisationInterpreter interpreter) {
        // The current ParetoFront
        var currentPareto = interpreter.initialSolutions.toList
        // The fitness values of current Pareto front
        var currentFitnesses = currentPareto.map[cs|interpreter.fitness(cs)]

        for (i : 0 ..< maxIterations) {
            // 1. Create new solutions
            val newSolutions = new ArrayList(currentPareto.map[cs|interpreter.evolve(cs)].filter[cs | cs != null].toList)

            // 2. Merge generations and find the top ranked members to create new generation
            val newFitnesses = new ArrayList(newSolutions.map[cs|interpreter.fitness(cs)])

            val combinedSolutions = new ArrayList()
            combinedSolutions.addAll(currentPareto)
            combinedSolutions.addAll(newSolutions)
            val combinedFitnesses = new ArrayList()
            combinedFitnesses.addAll(currentFitnesses)
            combinedFitnesses.addAll(newFitnesses)

            val newGeneration = interpreter.getTopRanked(maxPopulationSize, combinedSolutions, combinedFitnesses)

            currentPareto = newGeneration.key
            currentFitnesses = newGeneration.value
        }

        currentPareto.toSet
    }
}