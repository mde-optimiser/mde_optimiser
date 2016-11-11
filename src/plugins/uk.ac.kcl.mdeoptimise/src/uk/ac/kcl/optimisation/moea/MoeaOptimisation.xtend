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
import org.moeaframework.core.spi.OperatorFactory
import org.moeaframework.algorithm.SPEA2

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
			
			
		//OperatorFactory.getInstance().addProvider(new MoeaOptimmisationVariationsProvider());
		
		//Define the problem with variables and objectives
		var moeaOptimisationProblem = new MoeaOptimisationProblem(solutionGenerator, 1)
	
		//Create an initial random population of population size
		var initialization = new RandomInitialization(moeaOptimisationProblem, optimisationSpec.algorithmPopulation)
		//println(initialization.initialize.length)
		
		//Define the selection operator with the tournament size and dominance comparator
		//
		var selection = new TournamentSelection()//(1, new ParetoDominanceComparator());
		
		//Define the crossover / mutation operator
		var variation = new MoeaOptimisationVariation(solutionGenerator)	
	
//		var algorithm = new NSGAIII(
//				moeaOptimisationProblem,
//				new NondominatedSortingPopulation(),
//				null, // no archive
//				selection,
//				variation,
//				initialization
//			);

		var algorithm = new SPEA2(
				moeaOptimisationProblem,
				initialization,
				variation,
				optimisationSpec.algorithmPopulation,
				1
			);
		
		var step = 0;
		
		while (step < optimisationSpec.algorithmEvolutions) {
			algorithm.step()
			System.out.println("Running step " + step++)
		}
		
		var result = algorithm.getResult();
	
	    println(result.length)
	
		//Show the results
		var plot = new Plot()
			.add("NSGAII", result)
			.show();
		
			Thread.sleep(6000)
			System.out.println()
		
		var results = new ArrayList<EObject>()
		
		for(Solution object : result.toList) {			
			var solution = object as MoeaOptimisationSolution;
			results.add(solution.model)
		}
	
		results
	}
}
