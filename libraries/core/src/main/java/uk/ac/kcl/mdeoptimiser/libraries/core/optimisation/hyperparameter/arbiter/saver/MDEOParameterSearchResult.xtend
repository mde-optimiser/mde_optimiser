package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver

import org.deeplearning4j.arbiter.optimize.runner.LocalOptimizationRunner

class MDEOParameterSearchResult {
	
//        //Print out some basic stats regarding the optimization procedure
//        var s = "Best score: " + runner.bestScore() + "\n" +
//            "Index of model with best score: " + runner.bestScoreCandidateIndex() + "\n" +
//            "Number of configurations evaluated: " + runner.numCandidatesCompleted() + "\n";
//        System.out.println(s);
//        
//		//Get all results, and print out details of the best result:
//        var indexOfBestResult = runner.bestScoreCandidateIndex();
//        var allResults = runner.getResults();
//
//        var bestResult = allResults.get(indexOfBestResult).getResult();
//        var bestModel =  bestResult.getResultReference().getResultModel();
	
	
	LocalOptimizationRunner parameterSearchRunner
	String parameterSearchOutputPath
	
	new(LocalOptimizationRunner runner, String parameterSearchOutputPath) {
		this.parameterSearchRunner = runner;
		this.parameterSearchOutputPath = parameterSearchOutputPath;
	}
	
	def getNumberOfCandidatesCompleted(){
		return this.parameterSearchRunner.numCandidatesCompleted
	}
	
	def getResultReferences(){
		return parameterSearchRunner.getResults();
	}
	
	def getBestResultReference(){
		var bestResultIndex = this.parameterSearchRunner.bestScoreCandidateIndex
		return this.getResultReferences.get(bestResultIndex);
	}
	
	def String getParameterSearchOutputPath(){
		return this.parameterSearchOutputPath
	}
}