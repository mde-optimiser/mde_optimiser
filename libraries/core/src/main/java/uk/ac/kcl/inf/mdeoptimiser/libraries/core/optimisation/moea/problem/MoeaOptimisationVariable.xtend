package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem

import org.moeaframework.core.Variable
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator

//This is a representation of the evolved model
class MoeaOptimisationVariable implements Variable {

	Solution model
	SolutionGenerator solutionGenerator;

	new(Solution model, SolutionGenerator solutionGenerator){
		this.model = model
		this.solutionGenerator = solutionGenerator
	}
	
	override copy() {
		return new MoeaOptimisationVariable(model, solutionGenerator)
	}
	
	//Used to create the initial population
	override randomize() {
		//Apply evolvers on the model
		model = solutionGenerator.evolveModel(this.model)	
	}
	
	def getModel(){
		model;
	}
	
}