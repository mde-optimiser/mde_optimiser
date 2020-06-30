package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter;

import java.util.Map;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;

public class MDEOSearchOutcome {

  Map<Integer, SearchResult> searchResults;
  Optimisation optimisation;

  public MDEOSearchOutcome(
      Optimisation optimisationSpec, Map<Integer, SearchResult> searchResults) {
    this.optimisation = optimisationSpec;
    this.searchResults = searchResults;
  }

  public Map<Integer, SearchResult> getSearchResults() {
    return this.searchResults;
  }

  public Optimisation getOptimisation() {
    return this.optimisation;
  }

  public boolean isMoProblem() {
    return this.optimisation.getGoal().getObjectives().size() > 1;
  }
}
