package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.moeaframework.Analyzer;
import org.moeaframework.analysis.collector.Accumulator;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.spi.ProblemFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOSearchOutcome;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.reporting.CustomProblemProvider;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.reporting.GuidanceVectorsHelper;

public class HypervolumeScoreFunction extends BaseScoreFunction {

  /**
   * The hypervolume value calculated is normalized and must be maximised to find the best
   * solutions.
   *
   * @return boolean indicating minimisation of maximisation
   */
  @Override
  public boolean minimize() {
    return false;
  }

  @Override
  public List<Class<?>> getSupportedModelTypes() {
    return null;
  }

  @Override
  public List<Class<?>> getSupportedDataTypes() {
    return null;
  }

  @Override
  public double calculateScore(MDEOSearchOutcome model) {
    return this.calculateMultiObjectiveScore(model.getSearchResults());
  }

  /** Calculate the overall hypervolume for the current configuration. */
  private double calculateMultiObjectiveScore(Map<Integer, SearchResult> searchResults) {

    // Calculate the guidance vectors
    var problem = getProblem(searchResults);
    var accumulators = getAccumulators(searchResults);
    var guidanceVectorsHelper = calculateGuidanceVectors(problem, accumulators);

    // Calculate reference set
    var referenceSet = getReferenceSet(accumulators);
    ProblemFactory.getInstance().addProvider(new CustomProblemProvider(problem, referenceSet));

    // Calculate hypervolume
    var batchesHypervolume = calculateHypervolume(problem, guidanceVectorsHelper, accumulators);
    // Get hypervolume mean

    return getMedian(batchesHypervolume);
  }

  private List<Double> calculateHypervolume(
      Problem problem,
      GuidanceVectorsHelper guidanceVectorsHelper,
      Map<Integer, Accumulator> accumulators) {
    var analyzer =
        new Analyzer()
            .withProblem(problem.getName())
            // .withIdealPoint(guidanceVectorsHelper.getIdealSolution().getObjectives())
            .withReferencePoint(guidanceVectorsHelper.getReferenceSolution().getObjectives())
            .includeHypervolume();

    accumulators.forEach(
        (key, accumulator) -> {
          var referenceSet = getReferenceSet(accumulator);

          if (referenceSet.size() > 1) {
            analyzer.add(String.format("BATCH_%s", key), referenceSet);
          }
        });

    var result = analyzer.getAnalysis();
    var batchesHypervolume = new ArrayList<Double>();

    accumulators
        .keySet()
        .forEach(
            key -> {
              var batchResult = result.get(String.format("BATCH_%s", key));

              if (batchResult != null) {
                batchesHypervolume.add(batchResult.get("Hypervolume").getValues()[0]);
              } else {
                batchesHypervolume.add(0d);
              }
            });

    return batchesHypervolume;
  }

  private NondominatedPopulation getReferenceSet(Map<Integer, Accumulator> accumulators) {

    var referenceSet = new NondominatedPopulation();

    accumulators
        .keySet()
        .forEach(
            key -> {
              var rs = getReferenceSet(accumulators.get(key));
              referenceSet.addAll(rs);
            });

    return referenceSet;
  }

  private NondominatedPopulation getReferenceSet(Accumulator accumulator) {

    var approximationSet = new NondominatedPopulation();
    var approximationSetKey = "Approximation Set";

    var solutions =
        (List<Solution>)
            accumulator.get(approximationSetKey, accumulator.size("Approximation Set") - 1);

    approximationSet.addAll(solutions);

    return approximationSet;
  }

  private Problem getProblem(Map<Integer, SearchResult> searchResults) {
    return searchResults.entrySet().iterator().next().getValue().getProblem();
  }

  private Map<Integer, Accumulator> getAccumulators(Map<Integer, SearchResult> searchResults) {

    return searchResults.keySet().stream()
        .collect(Collectors.toMap(x -> x, x -> searchResults.get(x).getAccumulator()));
  }

  /**
   * Calculate the guidance vectors(ideal and nadir point) for the current set of accumulators.
   *
   * @return an instance of GuidanceVectorsHelper for the current set of results.
   */
  private GuidanceVectorsHelper calculateGuidanceVectors(
      Problem problem, Map<Integer, Accumulator> accumulators) {

    var guidanceVectorsHelper = new GuidanceVectorsHelper(problem);
    var approximationSetKey = "Approximation Set";

    accumulators.forEach(
        (key, accumulator) -> {
          for (var i = 0; i < accumulator.size(approximationSetKey); i++) {
            guidanceVectorsHelper.considerAll(
                (List<Solution>) accumulator.get(approximationSetKey, i));
          }
        });

    return guidanceVectorsHelper;
  }

  private double getMedian(List<Double> results) {

    var stats = new DescriptiveStatistics();
    results.forEach(result -> stats.addValue(result));

    return stats.getPercentile(50);
  }
}
