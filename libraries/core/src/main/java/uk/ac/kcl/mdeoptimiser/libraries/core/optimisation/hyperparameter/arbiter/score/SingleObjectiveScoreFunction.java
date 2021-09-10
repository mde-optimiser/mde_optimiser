package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOSearchOutcome;

public class SingleObjectiveScoreFunction extends BaseScoreFunction {

  /**
   * All problems in MDEOptimiser are minimisation problems. In the case of single objective
   * problems we seek to minimise the objective value so the score function with the smallest score
   * is the best found.
   *
   * @return score value tendency
   */
  @Override
  public boolean minimize() {
    return true;
  }

  @Override
  public double calculateScore(MDEOSearchOutcome model) {
    return this.calculateSingleObjectiveScore(model.getSearchResults());
  }

  public double calculateSingleObjectiveScore(Map<Integer, SearchResult> searchResults) {

    var scores =
        searchResults.keySet().stream()
            .map(
                key -> {
                  return calculateSingleObjectiveBatchScore(searchResults.get(key));
                })
            .collect(Collectors.toList());

    return getMedian(scores);
  }

  public double getMedian(List<Double> results) {

    var stats = new DescriptiveStatistics();

    // Multiply by -1 to reflect minimisation
    results.forEach(result -> stats.addValue(-1 * result));

    return stats.getPercentile(50);
  }

  public double calculateSingleObjectiveBatchScore(SearchResult searchResult) {

    var referenceSet = new NondominatedPopulation();
    var approximationSetKey = "Approximation Set";

    for (var i = 0; i < searchResult.getAccumulator().size(approximationSetKey); i++) {
      referenceSet.addAll(
          (List<Solution>) searchResult.getAccumulator().get(approximationSetKey, i));
    }

    return referenceSet.get(0).getObjectives()[0];
  }

  @Override
  public List<Class<?>> getSupportedModelTypes() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Class<?>> getSupportedDataTypes() {
    // TODO Auto-generated method stub
    return null;
  }
}
