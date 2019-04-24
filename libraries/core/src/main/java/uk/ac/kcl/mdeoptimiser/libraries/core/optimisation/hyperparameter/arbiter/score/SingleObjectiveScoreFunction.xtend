package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score

import java.util.ArrayList
import java.util.List
import java.util.Map
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.Solution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOSearchOutcome

class SingleObjectiveScoreFunction extends BaseScoreFunction {
	
	/**
	 * All problems in MDEOptimiser are minimisation problems. In the case of single objective
	 * problems we seek to minimise the objective value so the score function with the smallest
	 * score is the best found.
	 * 
	 * @return score value tendency
	 */
	override minimize() {
		return true
	}
	
	override calculateScore(MDEOSearchOutcome model) {
		return this.calculateSingleObjectiveScore(model.searchResults)
	}
	
	def double calculateSingleObjectiveScore(Map<Integer, SearchResult> searchResults) {
		
		var scores = searchResults.keySet.fold(new ArrayList<Double>())[seed, key | 
			seed.add(calculateSingleObjectiveBatchScore(searchResults.get(key)))
			return seed
		]
		
		return getMedian(scores)
	}

	def double getMedian(List<Double> results){
		
		val stats = new DescriptiveStatistics();
		results.forEach[result | stats.addValue(result)];
		
		return stats.getPercentile(50);
	}

	def double calculateSingleObjectiveBatchScore(SearchResult searchResult) {
		
		val referenceSet = new NondominatedPopulation();
		var approximationSetKey = "Approximation Set";

		for (var i = 0; i < searchResult.accumulator.size(approximationSetKey); i++) {
			referenceSet.addAll(searchResult.accumulator.get(approximationSetKey, i) as List<Solution>);
		}

		return referenceSet.head.objectives.head
	}
}