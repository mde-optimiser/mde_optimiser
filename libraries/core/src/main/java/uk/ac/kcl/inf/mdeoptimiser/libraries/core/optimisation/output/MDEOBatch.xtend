package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output

import java.util.Iterator
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Module
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution

class MDEOBatch {
	
	List<MoeaOptimisationSolution> solutions;
	public Integer id;
	public long duration;
	public Map<EPackage, List<Module>> generatedRules;
	public SearchResult searchResult;
	public boolean singleBatch;
	
	new(Integer id, long duration, SearchResult searchResult, Map<EPackage, List<Module>> generatedRules, boolean singleBatch){
		this.id = id;
		this.duration = duration;
		this.searchResult = searchResult;
		this.generatedRules = generatedRules;
		this.singleBatch = singleBatch
	}
	
	new(Integer id, long duration, SearchResult searchResult, Map<EPackage, List<Module>> generatedRules){
		this(id, duration, searchResult, generatedRules, false)
	}
	
	def List<MoeaOptimisationSolution> getSolutions(){
		
		if(this.solutions !== null){
			return this.solutions;
		}
		
		val acumulator = searchResult.accumulator
		val approximationSet = (acumulator.get("Approximation Set", acumulator.size("Approximation Set") - 1) as List<Solution>) 
		this.solutions = getOptimisationOutcomeObjects(new NondominatedPopulation(approximationSet)).toList
		
		return this.solutions
	}
	
	def Iterator<MoeaOptimisationSolution> getOptimisationOutcomeObjects(NondominatedPopulation population){
		return population.iterator.map[ p | (p as MoeaOptimisationSolution)]
	}	
}
