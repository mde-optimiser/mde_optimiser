package uk.ac.kcl.optimisation.moea

import org.moeaframework.core.spi.ProblemProvider
import org.moeaframework.core.Problem
import org.moeaframework.core.spi.ProviderNotFoundException
import org.moeaframework.core.Settings
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.PopulationIO
import java.io.File
import uk.ac.kcl.optimisation.SolutionGenerator
import org.moeaframework.core.EvolutionaryAlgorithm

class MoeaMdeoProblemProvider extends ProblemProvider {
	
	private SolutionGenerator solutionGenerator;
	private NondominatedPopulation referenceSet;
	private EvolutionaryAlgorithm algo

	new(SolutionGenerator solutionGenerator, MoeaOptimisationAlgorithmProvider referenceSet){
		this.solutionGenerator = solutionGenerator;
		this.algo = (referenceSet.algo as EvolutionaryAlgorithm);
	}

	override getProblem(String name) {
		
		if(name.equals("problemName")){
			try {
				var problem = new MoeaOptimisationProblem(solutionGenerator)
		
				return problem as Problem
			} catch (ClassNotFoundException e) {
				return null;
			} catch (Exception e) {
				throw new ProviderNotFoundException(name, e);
			}
		}
		
	}
	
	override getReferenceSet(String name) {
		return this.algo.result;
	}
	
}