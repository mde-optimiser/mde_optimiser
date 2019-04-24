package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score

import java.util.HashMap
import java.util.Map
import org.moeaframework.analysis.collector.Accumulator
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOSearchOutcome
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.reporting.GuidanceVectorsHelper
import org.moeaframework.core.Problem
import java.util.List
import org.moeaframework.core.Solution
import org.moeaframework.Analyzer
import org.moeaframework.core.NondominatedPopulation
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import java.util.ArrayList
import org.moeaframework.core.spi.ProblemFactory
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.reporting.CustomProblemProvider

class HypervolumeScoreFunction extends BaseScoreFunction {
	
	/**
	 * The hypervolume value calculated is normalized and must be maximised to
	 * find the best solutions.
	 * 
	 * @return boolean indicating minimisation of maximisation
	 */
	override minimize() {
		return false
	}
	
	override calculateScore(MDEOSearchOutcome model) {
		return this.calculateMultiObjectiveScore(model.searchResults)
	}
	
	/**
	 * Calculate the overall hypervolume for the current configuration.
	 * 
	 */
	private def calculateMultiObjectiveScore(Map<Integer, SearchResult> searchResults) {
		
		//Calculate the guidance vectors
		var problem = getProblem(searchResults)
		var accumulators = getAccumulators(searchResults)
		var guidanceVectorsHelper = calculateGuidanceVectors(problem, accumulators)
		
		//Calculate reference set
		var referenceSet = getReferenceSet(accumulators);
		ProblemFactory.getInstance().addProvider(new CustomProblemProvider(problem, referenceSet));
        
                  
		//Calculate hypervolume
		var batchesHypervolume = calculateHypervolume(problem, guidanceVectorsHelper, accumulators);
		//Get hypervolume mean
		
		return getMedian(batchesHypervolume)
	}
	
	private def calculateHypervolume(Problem problem, GuidanceVectorsHelper guidanceVectorsHelper, Map<Integer, Accumulator> accumulators) {
		val analyzer = new Analyzer()
                      .withProblem(problem.getName())
                      //.withIdealPoint(guidanceVectorsHelper.getIdealSolution().getObjectives())
                      .withReferencePoint(guidanceVectorsHelper.getReferenceSolution().getObjectives())
                      .includeHypervolume();
                      
       accumulators.forEach[key, accumulator |
       	
       		var referenceSet = getReferenceSet(accumulator);
       		
       		if(referenceSet.size > 1) {
				     analyzer.add(String.format("BATCH_%s", key), referenceSet);       			
       		}
       	
       ]
       
       val result = analyzer.getAnalysis();
       val batchesHypervolume = new ArrayList<Double>();
       
       accumulators.keySet.forEach[key | 

			var batchResult = result.get(String.format("BATCH_%s", key))
			
			if(batchResult !== null){
				batchesHypervolume.add(batchResult.get("Hypervolume").values.get(0))
			} else {
				batchesHypervolume.add(0d)
			}
       		
       ]
       
       return batchesHypervolume;
       
	}
	
	
	private def NondominatedPopulation getReferenceSet(Map<Integer, Accumulator> accumulators) {
		
		val referenceSet = new NondominatedPopulation();
		
		accumulators.keySet.forEach[key | 
			var rs = getReferenceSet(accumulators.get(key))
			referenceSet.addAll(rs)
		]
		
		return referenceSet;
	}
	
	private def NondominatedPopulation getReferenceSet(Accumulator accumulator) {
		
		var approximationSet = new NondominatedPopulation();
		val approximationSetKey = "Approximation Set";
		
		var solutions = accumulator.get(approximationSetKey, accumulator.size("Approximation Set") - 1) as List<Solution>;
		
		approximationSet.addAll(solutions)
		
		return approximationSet;
		
	}
	
	private def getProblem(Map<Integer, SearchResult> searchResults){

		return searchResults.get(searchResults.keySet.head).problem
		
	}
	
	private def getAccumulators(Map<Integer, SearchResult> searchResults) {
		
		return searchResults.keySet.fold(new HashMap<Integer, Accumulator>())[seed, key | 
			seed.put(key, searchResults.get(key).accumulator)	
			return seed
		]
	}
	
	/**
	 * Calculate the guidance vectors(ideal and nadir point) for the current set of accumulators.
	 * 
	 * @return an instance of GuidanceVectorsHelper for the current set of results.
	 */
	private def GuidanceVectorsHelper calculateGuidanceVectors(Problem problem, Map<Integer, Accumulator> accumulators) {
		
		val guidanceVectorsHelper = new GuidanceVectorsHelper(problem);
		val approximationSetKey = "Approximation Set";
		
		accumulators.forEach[key, accumulator |
			for(var i = 0; i < accumulator.size(approximationSetKey); i++) {
				guidanceVectorsHelper.considerAll(accumulator.get(approximationSetKey, i) as List<Solution>)
			}
		]
		
		return guidanceVectorsHelper
	}
	
	private def double getMedian(List<Double> results){
		
		val stats = new DescriptiveStatistics();
		results.forEach[result | stats.addValue(result)];
		
		return stats.getPercentile(50);
	}
	
}