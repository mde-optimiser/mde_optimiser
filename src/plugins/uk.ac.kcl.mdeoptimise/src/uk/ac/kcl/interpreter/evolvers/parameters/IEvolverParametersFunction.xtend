package uk.ac.kcl.interpreter.evolvers.parameters

import org.eclipse.emf.ecore.EObject

interface IEvolverParametersFunction {

	def Object sample(EObject model);
}