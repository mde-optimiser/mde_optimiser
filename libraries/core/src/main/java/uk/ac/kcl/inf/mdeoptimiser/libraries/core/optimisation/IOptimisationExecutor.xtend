package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation

import org.moeaframework.Instrumenter
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.OptimisationSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator

interface IOptimisationExecutor {
	
	/**
	 * Returns an optimisation outcome as a set of objects.
	 * Maybe this should also include population information, such as objective values.
	 * TODO Look at MOEA Instrumentation?
	 */
	def Instrumenter execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator)
	
}
