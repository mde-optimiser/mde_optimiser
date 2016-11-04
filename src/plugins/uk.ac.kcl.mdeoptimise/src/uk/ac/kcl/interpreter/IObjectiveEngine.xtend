package uk.ac.kcl.interpreter

interface IObjectiveEngine {
	
	/**
	 * Given a set of objective descriptors, initialize the correct objective function.
	 * Eventually this will have an objective interpreter spec passed in.
	 */
	def IFitnessFunction loadObjective(String type, String name, String objectiveString);
	
}