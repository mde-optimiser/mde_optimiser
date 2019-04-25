package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

interface IModelInitialiser {
	
	/**
	 * Take an instance of an EObject and apply a custom initialisation strategy
	 * to change the structure of the population in the initial search.
	 */
	def Solution initialise(EObject model);
}
