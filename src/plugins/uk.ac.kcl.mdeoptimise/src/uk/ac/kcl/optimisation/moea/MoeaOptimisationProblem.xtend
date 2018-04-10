package uk.ac.kcl.optimisation.moea

import java.util.List
import org.moeaframework.core.Solution
import org.moeaframework.problem.AbstractProblem
import uk.ac.kcl.interpreter.IGuidanceFunction
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.interpreter.guidance.GuidanceFunctionsFactory
import uk.ac.kcl.optimisation.SolutionGenerator

class MoeaOptimisationProblem extends AbstractProblem {

	private SolutionGenerator solutionGenerator

	private List<IGuidanceFunction> fitnessFunctions;
	private List<IGuidanceFunction> constraintFunctions;
	private IntermediateSolutionsCollector solutionsCollector;

	new(int numberOfVariables, int numberOfObjectives, int numberOfConstraints) {
		super(numberOfVariables, numberOfObjectives, numberOfConstraints)
	}

	new(SolutionGenerator solutionGenerator) {
		// Number of variables is for now always one.
		this(1, solutionGenerator.optimisationModel.objectives.size(),
			solutionGenerator.optimisationModel.constraints.size())
		this.solutionGenerator = solutionGenerator
		this.solutionsCollector = new IntermediateSolutionsCollector(
			solutionGenerator.optimisationModel /* optimisation model */,
			solutionGenerator.experimentId /* experiment ID */);
	}

	def SolutionGenerator getSolutionGenerator() {
		this.solutionGenerator
	}

	def getConstraintFunctions() {
		if (this.constraintFunctions == null) {
			setConstraintFunctions()
		}

		this.constraintFunctions
	}

	def setConstraintFunctions() {
		if (constraintFunctions == null) {
			this.constraintFunctions = solutionGenerator.optimisationModel.constraints.map [ constraint |
				new GuidanceFunctionsFactory().loadFunction(new GuidanceFunctionAdapter(constraint))
			]
		}
	}

	def getFitnessFunctions() {
		if (this.fitnessFunctions == null) {
			setFitnessFunctions()
		}

		this.fitnessFunctions
	}

	def void setFitnessFunctions() {
		if (fitnessFunctions == null) {
			this.fitnessFunctions = solutionGenerator.optimisationModel.objectives.map [ objective |
				new GuidanceFunctionsFactory().loadFunction(new GuidanceFunctionAdapter(objective))
			]
		}
	}

	override evaluate(Solution solution) {

		// TODO if some constraints are the same as the objectives, they should be cached for the same model
		val moeaSolution = solution as MoeaOptimisationSolution;

		// Set objectives
		getFitnessFunctions.forEach [ fitnessFunction, objectiveId |
			moeaSolution.setObjective(objectiveId, fitnessFunction.computeFitness(moeaSolution.model))
		]

		// Set Constraints
		getConstraintFunctions.forEach [ constraintFunction, objectiveId |
			moeaSolution.setConstraint(objectiveId, constraintFunction.computeFitness(moeaSolution.model))
		]
		
		// Collect the current intermediate solution
		solutionsCollector.addIntermediateSolution(moeaSolution)
	}

	override newSolution() {
		new MoeaOptimisationSolution(solutionGenerator)
	}

}
