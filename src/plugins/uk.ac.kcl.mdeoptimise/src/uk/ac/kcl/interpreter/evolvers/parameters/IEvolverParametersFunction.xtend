package uk.ac.kcl.interpreter.evolvers.parameters

import java.util.List
import uk.ac.kcl.interpreter.guidance.Solution

interface IEvolverParametersFunction {

	def Object sample(List<Solution> model);
}