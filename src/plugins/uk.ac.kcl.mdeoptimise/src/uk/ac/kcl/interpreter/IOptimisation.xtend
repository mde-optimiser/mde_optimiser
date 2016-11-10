package uk.ac.kcl.interpreter

import java.util.List
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator

interface IOptimisation {
	
	def List<EObject> execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator)
	
}