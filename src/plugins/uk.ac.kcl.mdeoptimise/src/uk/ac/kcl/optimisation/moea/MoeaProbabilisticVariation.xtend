package uk.ac.kcl.optimisation.moea

import org.moeaframework.core.Solution
import org.moeaframework.core.Variation
import uk.ac.kcl.optimisation.SolutionGenerator
import org.eclipse.emf.ecore.EObject
import java.util.ArrayList
import java.util.Arrays
import java.util.Random
import java.io.InvalidObjectException
import java.util.LinkedList

class MoeaProbabilisticVariation implements Variation {
	
	private SolutionGenerator solutionGenerator;
	private Variation crossoverOperator;
	private Variation mutationOperator;
	private double crossoverRate;
	private double mutationRate;
	
	
	new(Variation crossover, Variation mutation, double crossoverRate, double mutationRate){
		this.solutionGenerator = solutionGenerator;
		this.crossoverOperator = crossover;
		this.mutationOperator = mutation;
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
	}
	
	//USed to evolve a solution using the henshin evolvers
	override evolve(Solution[] parents) {
		var result = Arrays.copyOf(parents, parents.length);
		var random = new Random();
		
		//Apply crossover with probability specified by the user
		val crossoverProbability = random.nextDouble
		if(crossoverProbability <= crossoverRate){
			println("Running crossover with probability: " + crossoverProbability)
			if(result.length == crossoverOperator.arity){
				result = crossoverOperator.evolve(result)
			} else {
				throw new InvalidObjectException("Invalid number of parents for crossover operator.");
			}
		} else {
			println("Not running crossover this run")
		}
		
		var solutions = new LinkedList<Solution>();
		
		for(var i = 0; i < result.length; i++){
			
			var mutationProbability = random.nextDouble
			if(mutationProbability <= mutationRate){
				println("Running mutation with probability: " + mutationProbability)
				solutions.addAll(mutationOperator.evolve(#[result.get(i)]))
			} else {
				println("Not running mutation this run")
			}
		}
		
		return solutions;	

	}
	
	/**
	 * The number of solutions to be supplied to the evolve method.
	 * For the crossover operator we require a number of two parents.
	 */
	override getArity() {
		return 2;
	}
	
}