package uk.ac.kcl.interpreter.objectives

import uk.ac.kcl.interpreter.IFitnessFunction
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec

interface IObjectiveEngine {
	
	/**
	 * Given a set of objective descriptors, initialize the correct objective function.
	 * Eventually this will have an objective interpreter spec passed in.
	 */
	def IFitnessFunction loadObjective(ObjectiveInterpreterSpec objectiveInterpreter);
	
}
