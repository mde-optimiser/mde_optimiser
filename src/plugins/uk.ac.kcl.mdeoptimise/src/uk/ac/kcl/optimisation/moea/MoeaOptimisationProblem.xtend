package uk.ac.kcl.optimisation.moea

import java.util.List
import org.moeaframework.core.Solution
import org.moeaframework.problem.AbstractProblem
import uk.ac.kcl.interpreter.IFitnessFunction
import uk.ac.kcl.interpreter.objectives.ObjectivesFactory
import uk.ac.kcl.optimisation.SolutionGenerator

class MoeaOptimisationProblem extends AbstractProblem {
	
	//variables - number of models generated for each iteration. Should normally be 1
	// objectives - number of fitness functions for each model as defined in the spec
	private SolutionGenerator solutionGenerator
	
	private List<IFitnessFunction> fitnessFunctions;
	
		
	new(int numberOfVariables, int numberOfObjectives) {
		super(numberOfVariables, numberOfObjectives)
	}
	
	new(SolutionGenerator solutionGenerator, int numberOfVariables){
		//One variable - aka 1 model in this solution, fitness functions objectives
		this(numberOfVariables, solutionGenerator.optimisationModel.objectives.size());
		
		this.solutionGenerator = solutionGenerator
	}
	
	new(SolutionGenerator solutionGenerator){
		this(solutionGenerator, 1)
	}
	
	def SolutionGenerator getSolutionGenerator(){
		this.solutionGenerator
	}
	
	def getFitnessFunctions(){
		if (this.fitnessFunctions == null) {
			setFitnessFunctions()
		}
		
		this.fitnessFunctions
	}
	
	def void setFitnessFunctions(){
		if (fitnessFunctions == null) {			
			this.fitnessFunctions = solutionGenerator.optimisationModel.objectives
				.map[ objective | new ObjectivesFactory().loadObjective(objective)]
		}
	}
	
	override evaluate(Solution solution) {

		val moeaSolution = solution as MoeaOptimisationSolution;
		
		getFitnessFunctions
			.forEach[ fitnessFunction, objectiveId | 
						(solution as MoeaOptimisationSolution)
							.setObjective(objectiveId, fitnessFunction.computeFitness(moeaSolution.model))]
	}
	
	override newSolution() {
		new MoeaOptimisationSolution(solutionGenerator)
	}

}