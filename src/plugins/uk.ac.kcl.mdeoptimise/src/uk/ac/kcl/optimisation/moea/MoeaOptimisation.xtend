package uk.ac.kcl.optimisation.moea

import java.util.ArrayList
import org.eclipse.emf.ecore.EObject
import org.moeaframework.Executor
import org.moeaframework.algorithm.NSGAII
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.NondominatedSortingPopulation
import org.moeaframework.core.comparator.ParetoDominanceComparator
import org.moeaframework.core.operator.RandomInitialization
import org.moeaframework.core.operator.TournamentSelection
import uk.ac.kcl.interpreter.IOptimisation
import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.analysis.plot.Plot
import java.util.List
import org.moeaframework.core.Solution
import org.moeaframework.core.comparator.ChainedComparator
import org.moeaframework.core.comparator.CrowdingComparator

class MoeaOptimisation implements IOptimisation {
	
	SolutionGenerator solutionGenerator
	NondominatedPopulation results
	
	//This will have to 
	override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {
		
		//Load the optimisation engine
		//bootstrapOptimisation(optimisationSpec)
		this.solutionGenerator = solutionGenerator;
		
		bootstrapOptimisation(optimisationSpec)
	}
	
	def List<EObject> bootstrapOptimisation(OptimisationSpec optimisationSpec){		
			
		//Define the problem with variables and objectives
		var moeaOptimisationProblem = new MoeaOptimisationProblem(solutionGenerator, optimisationSpec.algorithmPopulation)
	
		//Create an initial random population of population size
		var initialization = new RandomInitialization(moeaOptimisationProblem, optimisationSpec.algorithmPopulation)
		
		//Define the selection operator with the tournament size and dominance comparator
		//
		var selection = new TournamentSelection(2, 
				new ChainedComparator(
						new ParetoDominanceComparator(),
						new CrowdingComparator()));
		
		//Define the crossover / mutation operator
		var variation = new MoeaOptimisationVariation(solutionGenerator)	
	
		var algorithm = new NSGAII(
				moeaOptimisationProblem,
				new NondominatedSortingPopulation(),
				null, // no archive
				selection,
				variation,
				initialization
			);
		
		while (algorithm.getNumberOfEvaluations() < optimisationSpec.algorithmEvolutions) {
			algorithm.step()
		}
		
		var result = algorithm.getResult();
	
		//Show the results
		var plot = new Plot()
			.add("NSGAII", result)
			.show();
			
			System.out.println()
		
		var results = new ArrayList<EObject>()
		
		for(Solution object : result.toList) {
			
			var solution = object as MoeaOptimisationSolution;
			for(var i = 0; i < solution.numberOfVariables; i++){
				
				var variable = solution.getVariable(i) as MoeaOptimisationVariable;
				results.add(variable.getModel)
					
			}
		}
	
		results
	}
}
