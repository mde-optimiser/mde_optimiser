package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import java.util.List
import java.util.Map
import java.util.Properties
import org.deeplearning4j.arbiter.optimize.api.data.DataProvider
import org.deeplearning4j.arbiter.optimize.api.data.DataSource
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction
import org.moeaframework.Instrumenter
import org.moeaframework.core.NondominatedPopulation
import org.moeaframework.core.Solution
import org.nd4j.shade.jackson.annotation.JsonIgnore
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation

class MDEOScoreFunction implements ScoreFunction {

	@JsonIgnore
	Optimisation optimisationSpec

	new(Optimisation optimisation) {
		this.optimisationSpec = optimisation;
	}

	override getSupportedDataTypes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override getSupportedModelTypes() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}

	override minimize() {
		
		//For mo problems we want to calculate the HV which needs to be maximised
		if(isMoProblem){
			return false;
		}
		
		//For so problems we minimise the single objective
		return true
	}
	
	def boolean isMoProblem(){
		return optimisationSpec.goal.objectives.size > 1;
	}

	override score(Object searchOutcome, DataProvider arg1, Map<String, Object> arg2) {
		var instrumenter = searchOutcome as Instrumenter
		return calculateScore(instrumenter);
	}

	override score(Object searchOutcome, Class<? extends DataSource> arg1, Properties arg2) {
		var instrumenter = searchOutcome as Instrumenter

		return calculateScore(instrumenter);
	}

	def calculateScore(Instrumenter instrumenter) {

		if (isMoProblem) {
			calculateMOProblemScore(instrumenter)
		}

		return calculateSOProblemScore(instrumenter)
	}

	def calculateMOProblemScore(Instrumenter instrumenter) {
		return 0d
	}

	def calculateSOProblemScore(Instrumenter instrumenter) {

		var accumulator = instrumenter.lastAccumulator;
		
		val referenceSet = new NondominatedPopulation();
		    var approximationSetKey = "Approximation Set";

	    for (var i = 0; i < accumulator.size(approximationSetKey); i++) {
	      referenceSet.addAll( accumulator.get(approximationSetKey, i) as List<Solution>);
	    }

		return referenceSet.head.objectives.head
	}

}
