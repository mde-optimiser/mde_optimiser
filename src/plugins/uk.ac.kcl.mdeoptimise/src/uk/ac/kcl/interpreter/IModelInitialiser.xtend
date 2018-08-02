package uk.ac.kcl.interpreter

import org.eclipse.emf.ecore.EObject

interface IModelInitialiser {
	
	/**
	 * Take an instance of an EObject and apply a custom initialisation strategy
	 * to change the structure of the population in the initial search.
	 */
	def EObject initialise(EObject model);
}