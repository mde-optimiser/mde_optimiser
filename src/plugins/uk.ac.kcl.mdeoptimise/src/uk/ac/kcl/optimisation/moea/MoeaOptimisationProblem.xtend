package uk.ac.kcl.optimisation.moea

import org.moeaframework.problem.AbstractProblem
import org.moeaframework.core.Solution
import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.interpreter.IFitnessFunction
import java.util.List
import java.util.LinkedList
import uk.ac.kcl.interpreter.objectives.ObjectivesFactory
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl

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
			
			this.fitnessFunctions = new LinkedList();
			
			val objectivesFactory = new ObjectivesFactory()
			
			for (ObjectiveInterpreterSpec objectiveSpec : solutionGenerator.optimisationModel.objectives){
				fitnessFunctions.add(objectivesFactory.loadObjective(objectiveSpec))
			}
		}
	}
	
	override evaluate(Solution solution) {

		val moeaSolution = solution as MoeaOptimisationSolution;
		
		for(var i = 0; i < getFitnessFunctions.size; i++){
			val fitnessValue = getFitnessFunctions.get(i).computeFitness(moeaSolution.getModel);
			System.out.println("Found fitness value " + fitnessValue + " for objective " + getFitnessFunctions.get(i).name)				
			moeaSolution.setObjective(i, fitnessValue)
		}		
		
	}
	
	override newSolution() {
		new MoeaOptimisationSolution(solutionGenerator)
	}

}