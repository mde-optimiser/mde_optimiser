package uk.ac.kcl.optimisation.moea

import org.eclipse.emf.ecore.EObject
import org.moeaframework.core.Solution
import uk.ac.kcl.optimisation.SolutionGenerator

class MoeaOptimisationSolution extends Solution {

	SolutionGenerator solutionGenerator
	
	public int evolutionsCounter = 0;
	public boolean initialSolution = false;
	public int evaluatedCounter = 0;
	new(MoeaOptimisationSolution moeaOptimisationSolution){
		super(1, moeaOptimisationSolution.numberOfObjectives)
		this.model = moeaOptimisationSolution.getModel
		this.solutionGenerator = moeaOptimisationSolution.getSolutionGenerator
		this.evolutionsCounter = moeaOptimisationSolution.evolutionsCounter;
	}
	
	new(int numberOfObjectives) {
		super(1, numberOfObjectives)
	}
	
	new(SolutionGenerator solutionGenerator){
		
		//1 variable - our model, fitness function objectives
		this(solutionGenerator.optimisationModel.objectives.size())
		
		this.solutionGenerator = solutionGenerator;
		setModel(solutionGenerator.evolveModel(solutionGenerator.initialSolutions.head))
		//System.out.println("Generated solution.")
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
	
}