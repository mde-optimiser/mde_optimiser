package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.deeplearning4j.arbiter.optimize.api.Candidate;
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult;
import org.deeplearning4j.arbiter.optimize.api.saving.ResultReference;
import org.deeplearning4j.arbiter.optimize.runner.CandidateInfo;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;

public class MDEOResultReference implements ResultReference {

  int index;
  String dir;
  File modelFile;
  File scoreFile;
  Candidate<MDEOHyperparametersConfiguration> candidate;
  CandidateInfo candidateInfo;
  Map<Integer, SearchResult> searchResults;

  public MDEOResultReference(
      Integer index,
      String dir,
      File modelFile,
      File scoreFile,
      Map<Integer, SearchResult> searchResults,
      Candidate<MDEOHyperparametersConfiguration> candidate,
      CandidateInfo candidateInfo) {
    this.index = index;
    this.dir = dir;
    this.modelFile = modelFile;
    this.scoreFile = scoreFile;
    this.searchResults = searchResults;
    this.candidate = candidate;
    this.candidateInfo = candidateInfo;
  }

  @Override
  public OptimizationResult getResult() {
    var score = loadScore();
    return new OptimizationResult(
        candidate, score, candidate.getIndex(), searchResults, candidateInfo, this);
  }

  @Override
  public Object getResultModel() {
    var model = MDEOModelSerialiser.readModel(modelFile);

    return model;
  }

  public Double loadScore() {
    String score = null;
    try {
      score = FileUtils.readFileToString(this.scoreFile, Charsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not read score file: " + this.scoreFile.getAbsolutePath());
    }
    return Double.parseDouble(score);
  }
}
