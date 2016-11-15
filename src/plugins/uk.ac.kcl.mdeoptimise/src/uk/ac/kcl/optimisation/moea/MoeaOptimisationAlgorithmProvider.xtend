package uk.ac.kcl.optimisation.moea

import java.util.Properties
import org.moeaframework.core.Problem
import org.moeaframework.core.spi.AlgorithmProvider
import org.moeaframework.core.operator.RandomInitialization
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.core.operator.TournamentSelection
import org.moeaframework.core.Algorithm
import org.moeaframework.algorithm.NSGAII
import org.moeaframework.core.NondominatedSortingPopulation

class MoeaOptimisationAlgorithmProvider extends AlgorithmProvider {
	
	def getAlgorithm(){
		
	}
	
	override getAlgorithm(String algorithm, Properties properties, Problem problem) {
		switch algorithm {
			case "NSGAII":
				return createNSGAII(problem, properties)
			default:
				throw new Exception("Invalid algorithm given: " + algorithm)
		}
	}
	
	def Algorithm createNSGAII(Problem problem, Properties properties) {
		//Create an initial random population of population size
		var initialization = new RandomInitialization(problem, properties.get("populationSize") as Integer)
		
		//Define the selection operator with the tournament size and dominance comparator
		var selection = new TournamentSelection(2)//(1, new ParetoDominanceComparator());
		
		//Define the crossover / mutation operator
		var variation = new MoeaOptimisationVariation(properties.get("solutionGenerator") as SolutionGenerator)	
	
		var algorithm = new NSGAII(
				problem,
				new NondominatedSortingPopulation(),
				null, // no archive
				selection,
				variation,
				initialization
			);
			println(algorithm.numberOfEvaluations)
			algorithm
	}
	
}