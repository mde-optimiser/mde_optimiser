package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver

import com.google.common.base.Charsets
import java.io.File
import java.io.IOException
import java.util.Map
import org.apache.commons.io.FileUtils
import org.deeplearning4j.arbiter.optimize.api.Candidate
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult
import org.deeplearning4j.arbiter.optimize.api.saving.ResultReference
import org.deeplearning4j.arbiter.optimize.runner.CandidateInfo
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration

class MDEOResultReference implements ResultReference {
	
	int index;
    String dir;
    File modelFile;
    File scoreFile;
	Candidate<MDEOHyperparametersConfiguration> candidate;
	CandidateInfo candidateInfo;
	Map<Integer, SearchResult> searchResults
	
	new(Integer index, String dir, File modelFile, File scoreFile, Map<Integer, SearchResult> searchResults, 
		Candidate<MDEOHyperparametersConfiguration> candidate, CandidateInfo candidateInfo
	){
		this.index = index;
		this.dir = dir;
		this.modelFile = modelFile;
		this.scoreFile = scoreFile;
		this.searchResults = searchResults;
		this.candidate = candidate;
		this.candidateInfo = candidateInfo;
	}
	
	override getResult() throws IOException {
		var score = loadScore();
		return new OptimizationResult(candidate, score, candidate.index, searchResults, candidateInfo, this)
	}
	
	override getResultModel() throws IOException {
		var model = MDEOModelSerialiser.readModel(modelFile)
		
		return model
	}
	
	def Double loadScore(){
		val score = FileUtils.readFileToString(this.scoreFile, Charsets.UTF_8);
		return Double.parseDouble(score);
	}
}