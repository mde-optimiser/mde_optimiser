package uk.ac.kcl.interpreter

import java.util.Iterator
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution

interface IOptimisation {
	
	/**
	 * Returns an optimisation outcome as a set of objects.
	 * Maybe this should also include population information, such as objective values.
	 * TODO Look at MOEA Instrumentation?
	 */
	def Iterator<MoeaOptimisationSolution> execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator)
	
}
