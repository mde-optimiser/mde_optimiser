package uk.ac.kcl.optimisation.moea

import org.eclipse.emf.ecore.EObject
import org.moeaframework.core.Variable
import uk.ac.kcl.optimisation.SolutionGenerator
import java.util.Random

//This is a representation of the evolved model
class MoeaOptimisationVariable implements Variable {

	private EObject model
	private SolutionGenerator solutionGenerator;

	new(EObject model, SolutionGenerator solutionGenerator){
		this.model = model;
		this.solutionGenerator = solutionGenerator;
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