package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import java.util.Map
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult

class MDEOSearchOutcome {
	
	Map<Integer, SearchResult> searchResults
	Optimisation optimisation
	
	new(Optimisation optimisationSpec, Map<Integer, SearchResult> searchResults){
		this.optimisation = optimisationSpec;
		this.searchResults = searchResults;
	}
	
	def Map<Integer, SearchResult> getSearchResults(){
		return this.searchResults;
	}
	
	def Optimisation getOptimisation(){
		return this.optimisation;
	}

	def boolean isMoProblem() {
		return this.optimisation.goal.objectives.size > 1;
	}
}