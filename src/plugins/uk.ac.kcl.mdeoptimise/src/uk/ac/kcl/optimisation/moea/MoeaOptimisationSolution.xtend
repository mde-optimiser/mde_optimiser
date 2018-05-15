package uk.ac.kcl.optimisation.moea

import org.eclipse.emf.ecore.EObject
import org.moeaframework.core.Solution
import uk.ac.kcl.optimisation.SolutionGenerator
import java.util.LinkedHashMap

class MoeaOptimisationSolution extends Solution {

	SolutionGenerator solutionGenerator
	
	new(MoeaOptimisationSolution moeaOptimisationSolution){
		super(1, moeaOptimisationSolution.numberOfObjectives, moeaOptimisationSolution.numberOfConstraints)
		this.model = moeaOptimisationSolution.getModel
		this.solutionGenerator = moeaOptimisationSolution.getSolutionGenerator
	}
	
	new(int numberOfObjectives, int numberOfConstraints) {
		super(1, numberOfObjectives, numberOfConstraints)
	}
	
	new(SolutionGenerator solutionGenerator){
		this(solutionGenerator.optimisationModel.objectives.size(), 
			solutionGenerator.optimisationModel.constraints.size()
		)
		this.solutionGenerator = solutionGenerator;
		setModel(solutionGenerator.evolveModel(solutionGenerator.initialSolutions.next))
	}
	
	override MoeaOptimisationSolution copy(){
		new MoeaOptimisationSolution(this);
	}
	
	def EObject getModel(){
		(getVariable(0) as MoeaOptimisationVariable).model
	}
	
	def void setModel(EObject model) {
		setVariable(0, new MoeaOptimisationVariable(model, solutionGenerator))
	}
	
	def SolutionGenerator getSolutionGenerator(){
		return this.solutionGenerator
	}
	
	def void setSolutionGenerator(SolutionGenerator solutionGenerator) {
		this.solutionGenerator = solutionGenerator;
	}
	
	def LinkedHashMap<String, Double> getFormattedObjectives(){
		val objectives = new LinkedHashMap<String, Double>()
		
		solutionGenerator.optimisationModel.objectives.forEach[objective , index | 
			objectives.put(objective.objectiveName, this.objectives.get(index))
		]
		
		return objectives
	}
	
	def LinkedHashMap<String, Double> getFormattedConstraints(){
		val constraints = new LinkedHashMap<String, Double>()
		
		solutionGenerator.optimisationModel.constraints.forEach[constraint , index | 
			constraints.put(constraint.constraintName, this.constraints.get(index))
		]
		
		return constraints
	}
}