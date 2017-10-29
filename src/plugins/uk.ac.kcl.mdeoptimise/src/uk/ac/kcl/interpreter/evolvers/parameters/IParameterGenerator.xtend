package uk.ac.kcl.interpreter.evolvers.parameters

import java.util.Map
import org.eclipse.emf.ecore.EObject

/**
 * This is a function that needs to be implemented in order to get custom 
 * parameters for Henshin rules.
 */
interface IParameterGenerator {
	def Map<String, Object> get(EObject object)
}