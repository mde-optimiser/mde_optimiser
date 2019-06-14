package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult;
import org.deeplearning4j.arbiter.optimize.api.saving.ResultReference;
import org.deeplearning4j.arbiter.optimize.api.saving.ResultSaver;
import org.nd4j.shade.jackson.annotation.JsonCreator;
import org.nd4j.shade.jackson.annotation.JsonProperty;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;

public class MDEOResultSaver implements ResultSaver {

  @JsonProperty String path;

  File resultsOutputLocation;

  public MDEOResultSaver() {}

  @JsonCreator
  public MDEOResultSaver(String path) {
    this(new File(path));
  }

  public MDEOResultSaver(File file) {
    this.setOutputPath(file);
  }

  public void createOutputPath() {

    if (!this.resultsOutputLocation.exists()) {
      this.resultsOutputLocation.mkdirs();
    }

    if (!this.resultsOutputLocation.isDirectory()) {
      throw new IllegalArgumentException(
          String.format(
              "Invalid path: results output path is not a valid directory %s",
              this.resultsOutputLocation.getPath()));
    }
  }

  @Override
  public List<Class<?>> getSupportedCandidateTypes() {
    return Collections.<Class<?>>singletonList(Object.class);
  }

  @Override
  public List<Class<?>> getSupportedModelTypes() {
    return Collections.<Class<?>>singletonList(MDEOHyperparametersConfiguration.class);
  }

  /**
   * Serialise the outcome of a parameter optimisation run.
   *
   * @param result contains information about the current optimisation candidate
   * @param modelResult contains the current parameter configuration object (population and
   *     evolutions)
   */
  @Override
  public ResultReference saveModel(OptimizationResult result, Object modelResult)
      throws IOException {

    var outputDirectory = getOutputDirectory(result);
    var modelFile = saveModel(outputDirectory, (MDEOHyperparametersConfiguration) modelResult);
    var scoreFile = saveScore(outputDirectory, result.getScore());
    var searchResults = (HashMap<Integer, SearchResult>) result.getModelSpecificResults();
    saveAccumulators(outputDirectory, searchResults);
    // TODO Save Mopt file maybe?

    return new MDEOResultReference(
        result.getIndex(),
        outputDirectory.getAbsolutePath(),
        modelFile,
        scoreFile,
        searchResults,
        result.getCandidate(),
        result.getCandidateInfo());
  }

  public List<File> saveAccumulators(
      File outputDirectory, Map<Integer, SearchResult> searchResults) {

    var accumulatorFiles = new ArrayList<File>();

    searchResults
        .keySet()
        .forEach(
            key -> {
              var accumulatorOutputFile =
                  Path.of(
                          outputDirectory.getAbsolutePath(),
                          String.format("accumulators/accumulator-batch-%s.csv", key))
                      .toFile();

              if (!accumulatorOutputFile.getParentFile().exists()) {
                accumulatorOutputFile.getParentFile().mkdirs();
              }

              var accumulator = searchResults.get(key).getAccumulator();
              try {
                accumulator.saveCSV(accumulatorOutputFile);
              } catch (IOException e) {
                e.printStackTrace();
              }

              accumulatorFiles.add(accumulatorOutputFile);
            });

    return accumulatorFiles;
  }

  public File getOutputDirectory(OptimizationResult result) {

    var outputDirectory = new File(path, result.getIndex() + "/");
    outputDirectory.mkdir();

    return outputDirectory;
  }

  public File saveScore(File outputDirectory, Double score) {

    // Save the score function
    var scoreFile = Path.of(outputDirectory.getAbsolutePath(), "score.txt").toFile();

    MDEOModelSerialiser.writeScore(score, scoreFile);

    return scoreFile;
  }

  public File saveModel(File outputDirectory, MDEOHyperparametersConfiguration modelResult) {
    // Save the model
    var modelFile = Path.of(outputDirectory.getAbsolutePath(), "modelFile.json").toFile();

    MDEOModelSerialiser.writeModel(modelResult, modelFile);

    return modelFile;
  }

  public void setOutputPath(File file) {
    this.resultsOutputLocation = file;
    this.path = file.getAbsolutePath();
  }

  @Override
  public String toString() {

    return String.format("MDEOResultSaver(path=%s)", this.resultsOutputLocation.getPath());
  }
}
