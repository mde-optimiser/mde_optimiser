package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver

import java.io.File
import java.io.IOException
import java.nio.file.Path
import java.util.ArrayList
import java.util.Collections
import java.util.Map
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult
import org.deeplearning4j.arbiter.optimize.api.saving.ResultSaver
import org.moeaframework.analysis.collector.Accumulator
import org.nd4j.shade.jackson.annotation.JsonCreator
import org.nd4j.shade.jackson.annotation.JsonProperty
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult
import java.util.HashMap

class MDEOResultSaver implements ResultSaver {

	@JsonProperty
	String path;

	File resultsOutputLocation

	new(){
		
	}

	@JsonCreator
	new(String path) {
		this(new File(path))
	}

	new(File file) {
		this.setOutputPath(file)
	}

	def void createOutputPath() {

		if (!this.resultsOutputLocation.exists) {
			this.resultsOutputLocation.mkdirs
		}

		if (!this.resultsOutputLocation.isDirectory) {
			throw new IllegalArgumentException(String.format("Invalid path: results output path is not a valid directory %s",
				this.resultsOutputLocation.path))
		}
	}

	override getSupportedCandidateTypes() {
		return Collections.<Class<?>>singletonList(Object);
	}

	override getSupportedModelTypes() {
		return #[MDEOHyperparametersConfiguration]
	}

	/**
	 * Serialise the outcome of a parameter optimisation run.
	 * 
	 * @param result contains information about the current optimisation candidate
	 * @param modelResult contains the current parameter configuration object (population and evolutions)
	 */
	override saveModel(OptimizationResult result, Object modelResult) throws IOException {
		
		var outputDirectory = getOutputDirectory(result)
		var modelFile = saveModel(outputDirectory, modelResult as MDEOHyperparametersConfiguration)
		var scoreFile = saveScore(outputDirectory, result.score);
		var searchResults = result.modelSpecificResults as HashMap<Integer, SearchResult>
		saveAccumulators(outputDirectory, searchResults)
		//TODO Save Mopt file maybe?
		
		return new MDEOResultReference(result.index, outputDirectory.absolutePath,
			modelFile, scoreFile, searchResults, result.candidate, result.candidateInfo
		);
	
	}
	
	def saveAccumulators(File outputDirectory, Map<Integer, SearchResult> searchResults) {
		
		val accumulatorFiles = new ArrayList<File>();
		
		searchResults.keySet.forEach[key | 
			
			var accumulatorOutputFile = Path.of(outputDirectory.absolutePath, String.format("accumulators/accumulator-batch-%s.csv", key)).toFile
			
			if(!accumulatorOutputFile.parentFile.exists){
				accumulatorOutputFile.parentFile.mkdirs();
			}
			
			var accumulator = searchResults.get(key).accumulator
			accumulator.saveCSV(accumulatorOutputFile)

			accumulatorFiles.add(accumulatorOutputFile)
		]
		
		return accumulatorFiles
	}
	
	def File getOutputDirectory(OptimizationResult result){
		
		var outputDirectory = new File(path, result.index + "/")
		outputDirectory.mkdir
		
		return outputDirectory
	}
	
	def File saveScore(File outputDirectory, Double score){
		
		//Save the score function
		var scoreFile = Path.of(outputDirectory.absolutePath, "score.txt").toFile
		
		MDEOModelSerialiser.writeScore(score, scoreFile);
		
		return scoreFile;
	}
	
	def File saveModel(File outputDirectory, MDEOHyperparametersConfiguration modelResult) {
		//Save the model
		var modelFile = Path.of(outputDirectory.absolutePath, "modelFile.json").toFile
		
		MDEOModelSerialiser.writeModel(modelResult, modelFile);

		return modelFile		
	}
	
	def void setOutputPath(File file){
		this.resultsOutputLocation = file;
		this.path = file.absolutePath
	}

    override toString() {
        return String.format("MDEOResultSaver(path=%s)", this.resultsOutputLocation.path);
	}
}
