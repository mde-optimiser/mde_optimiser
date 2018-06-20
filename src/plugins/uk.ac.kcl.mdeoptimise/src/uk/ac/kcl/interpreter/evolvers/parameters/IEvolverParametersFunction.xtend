package uk.ac.kcl.interpreter.evolvers.parameters

import org.eclipse.emf.ecore.EObject
import java.util.List
import java.lang.reflect.Type

interface IEvolverParametersFunction {

	def Object sample(List<EObject> model);
}