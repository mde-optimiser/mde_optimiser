package uk.ac.kcl.interpreter.evolvers.parameters

import org.eclipse.emf.ecore.EObject
import java.util.List

interface IEvolverParametersFunction {

	def Object sample(List<EObject> model);
}