package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.Module;
import org.moeaframework.core.NondominatedPopulation;
import org.moeaframework.core.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class MDEOBatch {

  List<MoeaOptimisationSolution> solutions;
  public Integer id;
  public long duration;
  public Map<EPackage, List<Module>> generatedRules;
  public SearchResult searchResult;
  public boolean singleBatch;

  public MDEOBatch(
      Integer id,
      long duration,
      SearchResult searchResult,
      Map<EPackage, List<Module>> generatedRules,
      boolean singleBatch) {
    this.id = id;
    this.duration = duration;
    this.searchResult = searchResult;
    this.generatedRules = generatedRules;
    this.singleBatch = singleBatch;
  }

  public MDEOBatch(
      Integer id,
      long duration,
      SearchResult searchResult,
      Map<EPackage, List<Module>> generatedRules) {
    this(id, duration, searchResult, generatedRules, false);
  }

  public List<MoeaOptimisationSolution> getSolutions() {

    if (this.solutions != null) {
      return this.solutions;
    }

    var acumulator = searchResult.getAccumulator();
    var approximationSet =
        (List<Solution>)
            acumulator.get("Approximation Set", acumulator.size("Approximation Set") - 1);
    this.solutions = getOptimisationOutcomeObjects(new NondominatedPopulation(approximationSet));

    return this.solutions;
  }

  public List<MoeaOptimisationSolution> getOptimisationOutcomeObjects(
      NondominatedPopulation population) {

    List<MoeaOptimisationSolution> solutions = new ArrayList<>();
    population
        .iterator()
        .forEachRemaining(solution -> solutions.add((MoeaOptimisationSolution) solution));
    return solutions;
  }
}
