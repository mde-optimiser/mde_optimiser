package uk.ac.kcl.optimisation.moea

import java.util.Properties
import org.moeaframework.algorithm.NSGAII
import org.moeaframework.algorithm.SPEA2
import org.moeaframework.core.Algorithm
import org.moeaframework.core.NondominatedSortingPopulation
import org.moeaframework.core.Problem
import org.moeaframework.core.operator.RandomInitialization
import org.moeaframework.core.operator.TournamentSelection
import org.moeaframework.core.spi.AlgorithmProvider
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.algorithm.EpsilonMOEA
import org.moeaframework.core.EpsilonBoxDominanceArchive
import org.moeaframework.core.operator.GAVariation
import org.moeaframework.core.Variation
import uk.ac.kcl.mdeoptimise.AlgorithmVariation
import org.moeaframework.core.EvolutionaryAlgorithm

class MoeaOptimisationAlgorithmProvider extends AlgorithmProvider {
	
	public Algorithm algo; 
	
	override getAlgorithm(String algorithm, Properties properties, Problem problem) {
		switch algorithm {
			case "NSGAII":
				this.algo = createNSGAII(problem, properties)
			case "SPEA2":
				this.algo = createSPEA2(problem, properties)
			case "eMOEA":
				this.algo = createeMOEA(problem, properties)
			default:
				throw new Exception("Invalid algorithm given: " + algorithm)
		}
		
		return this.algo;
	}
	
	def Variation getVariation(Properties properties){
		
		var algorithmVariation = properties.get("variationType") as AlgorithmVariation
		
		//Check if we have weighted genetic variation
		if(algorithmVariation.simpleVariation == null){
			val crossoverVariation = new MoeaOptimisationCrossoverVariation(properties.get("solutionGenerator") as SolutionGenerator)
			val mutationVariation = new MoeaOptimisationMutationVariation(properties.get("solutionGenerator") as SolutionGenerator)
				
			return new MoeaProbabilisticVariation(crossoverVariation, mutationVariation, 
				Double.parseDouble(algorithmVariation.probabilityVariation.crossover_rate), 
				Double.parseDouble(algorithmVariation.probabilityVariation.mutation_rate)
			)	
		}
		
		//Check variation type is crossover with mutation
		if(algorithmVariation.simpleVariation.equals("genetic")){
			val crossoverVariation = new MoeaOptimisationCrossoverVariation(properties.get("solutionGenerator") as SolutionGenerator)
			val mutationVariation = new MoeaOptimisationMutationVariation(properties.get("solutionGenerator") as SolutionGenerator)
			
			return new GAVariation(crossoverVariation, mutationVariation)
		}
		
		//Check variation type is mutation
		if(algorithmVariation.simpleVariation.equals("mutation")){
			return new MoeaOptimisationMutationVariation(properties.get("solutionGenerator") as SolutionGenerator)		
		}
		
		//Must be crossover only then
		return new MoeaOptimisationCrossoverVariation(properties.get("solutionGenerator") as SolutionGenerator)
		
	}
	
	def Algorithm createNSGAII(Problem problem, Properties properties) {
		//Create an initial random population of population size
		var initialization = new RandomInitialization(problem, properties.get("populationSize") as Integer)
		
		var selection = new TournamentSelection(2);
		
		new NSGAII(
				problem,
				new NondominatedSortingPopulation(),
				null, // no archiv
				selection,
				getVariation(properties),
				initialization
			);
	}
	
	def Algorithm createSPEA2(Problem problem, Properties properties) {
		
		var initialization = new RandomInitialization(problem, properties.get("populationSize") as Integer)
		
		new SPEA2(
				problem,
				initialization,
				getVariation(properties),
				properties.get("populationSize") as Integer,
				1
			);
	}
	
	def Algorithm createeMOEA(Problem problem, Properties properties) {
		
		//Create an initial random population of population size
		var initialization = new RandomInitialization(problem, properties.get("populationSize") as Integer)
		
		
		var selection = new TournamentSelection(2);
		
		new EpsilonMOEA(
				problem,
				new NondominatedSortingPopulation(),
				new EpsilonBoxDominanceArchive(0.01),
				selection, 
				getVariation(properties),
				initialization
			);
	}
	
}