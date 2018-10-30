package uk.ac.kcl.interpreter

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.guidance.Solution

interface IModelInitialiser {
	
	/**
	 * Take an instance of an EObject and apply a custom initialisation strategy
	 * to change the structure of the population in the initial search.
	 */
	def Solution initialise(EObject model);
}