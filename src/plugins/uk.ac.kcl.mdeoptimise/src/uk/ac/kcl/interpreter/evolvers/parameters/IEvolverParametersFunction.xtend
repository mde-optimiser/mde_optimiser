package uk.ac.kcl.interpreter.evolvers.parameters

import java.util.List
import org.eclipse.emf.ecore.EObject

interface IEvolverParametersFunction {

	def Object sample(List<EObject> model);
}