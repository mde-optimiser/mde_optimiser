package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem

import org.moeaframework.core.Solution
import java.util.LinkedHashMap
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator

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
		this(solutionGenerator.optimisationModel.goal.objectives.size(), 
			solutionGenerator.optimisationModel.goal.constraints.size()
		)
		this.solutionGenerator = solutionGenerator;
		setModel(solutionGenerator.mutate(solutionGenerator.initialSolutions.head))
	}
	
	override MoeaOptimisationSolution copy(){
		new MoeaOptimisationSolution(this);
	}
	
	def uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution getModel(){
		(getVariable(0) as MoeaOptimisationVariable).model
	}
	
	def void setModel(uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution model) {
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
		
		solutionGenerator.optimisationModel.goal.objectives.forEach[objective , index | 
			objectives.put(objective.objectiveName, this.objectives.get(index))
		]
		
		return objectives
	}
	
	def LinkedHashMap<String, Double> getFormattedConstraints(){
		val constraints = new LinkedHashMap<String, Double>()
		
		solutionGenerator.optimisationModel.goal.constraints.forEach[constraint , index | 
			constraints.put(constraint.constraintName, this.constraints.get(index))
		]
		
		return constraints
	}
	
	override toString(){
		
		val sb = new StringBuilder();
		
		val objectives = getObjectives()		
		sb.append("[")
		
		objectives.forEach[value,index|
			sb.append(value)
			
			if(index < objectives.size - 1){
				sb.append(",")
			}
		]
		sb.append("]")
		
		val constraints = getConstraints();
		
		if(constraints.size > 0){
			sb.append("[")
			
			constraints.forEach[value, index|
				sb.append(value)
				
				if(index < constraints.size - 1){
					sb.append(",")
				}
			]
		
			sb.append("]")
		}

		
		return sb.toString
	}
}