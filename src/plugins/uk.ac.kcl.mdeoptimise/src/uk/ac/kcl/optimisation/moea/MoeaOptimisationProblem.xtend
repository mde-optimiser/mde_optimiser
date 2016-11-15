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
	
	private int evaluationsCounter = 0;
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
							.setObjective(objectiveId, fitnessFunction.computeFitness(moeaSolution.model))	
						]
		this.evaluationsCounter++
		moeaSolution.evaluatedCounter++
//		System.out.println("Evaluations for this solution: " + moeaSolution.evaluatedCounter)
//		System.out.println("Evolutions for this solution: " + moeaSolution.evolutionsCounter)
//		System.out.println("Total evaluations: " + this.evaluationsCounter)
//		System.out.println("Evaluating an initial solution: " + moeaSolution.initialSolution)
		moeaSolution.objectives.forEach[ x, index | System.out.println("Objective " + index + " value is " + x.doubleValue)]

	}
	
	override newSolution() {
		System.out.println("Generated new solution at evaluation " + this.evaluationsCounter);
		
		var a = new MoeaOptimisationSolution(solutionGenerator)
		a.initialSolution = true
		a
	}

}