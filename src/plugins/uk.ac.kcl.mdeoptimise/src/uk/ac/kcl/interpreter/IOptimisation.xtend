package uk.ac.kcl.interpreter

import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.Instrumenter

interface IOptimisation {
	
	/**
	 * Returns an optimisation outcome as a set of objects.
	 * Maybe this should also include population information, such as objective values.
	 * TODO Look at MOEA Instrumentation?
	 */
	def Instrumenter execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator)
	
}
