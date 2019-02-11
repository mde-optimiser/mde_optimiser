package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem

import java.util.List
import org.moeaframework.core.Solution
import org.moeaframework.problem.AbstractProblem
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionsFactory
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter

class MoeaOptimisationProblem extends AbstractProblem {

	SolutionGenerator solutionGenerator

	List<IGuidanceFunction> fitnessFunctions;
	List<IGuidanceFunction> constraintFunctions;

	new(int numberOfVariables, int numberOfObjectives, int numberOfConstraints) {
		super(numberOfVariables, numberOfObjectives, numberOfConstraints)
	}

	new(SolutionGenerator solutionGenerator) {
		// Number of variables is for now always one.
		this(1, solutionGenerator.optimisationModel.goal.objectives.size(),
			solutionGenerator.optimisationModel.goal.constraints.size())
		this.solutionGenerator = solutionGenerator
	}

	def SolutionGenerator getSolutionGenerator() {
		this.solutionGenerator
	}

	def getConstraintFunctions() {
		if (this.constraintFunctions === null) {
			setConstraintFunctions()
		}

		this.constraintFunctions
	}

	def setConstraintFunctions() {
		if (constraintFunctions === null) {
			this.constraintFunctions = solutionGenerator.optimisationModel.goal.constraints.map [ constraint |
				new GuidanceFunctionsFactory().loadFunction(new GuidanceFunctionAdapter(constraint))
			]
		}
	}

	def getFitnessFunctions() {
		if (this.fitnessFunctions === null) {
			setFitnessFunctions()
		}

		this.fitnessFunctions
	}

	def void setFitnessFunctions() {
		if (fitnessFunctions === null) {
			this.fitnessFunctions = solutionGenerator.optimisationModel.goal.objectives.map [ objective |
				new GuidanceFunctionsFactory().loadFunction(new GuidanceFunctionAdapter(objective))
			]
		}
	}

	override evaluate(Solution solution) {

		// TODO if some constraints are the same as the objectives, they shoyuld be cached for the same model
		val moeaSolution = solution as MoeaOptimisationSolution;

		// Set objectives
		getFitnessFunctions.forEach [ fitnessFunction, objectiveId |
			moeaSolution.setObjective(objectiveId, fitnessFunction.computeFitness(moeaSolution.model))
		]

		// Set Constraints
		getConstraintFunctions.forEach [ constraintFunction, objectiveId |
			moeaSolution.setConstraint(objectiveId, constraintFunction.computeFitness(moeaSolution.model))
		]
		
	}

	override newSolution() {
		new MoeaOptimisationSolution(solutionGenerator)
	}

}
