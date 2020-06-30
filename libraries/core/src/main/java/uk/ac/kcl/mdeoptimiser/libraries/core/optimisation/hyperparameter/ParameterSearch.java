package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import org.deeplearning4j.arbiter.optimize.api.CandidateGenerator;
import org.deeplearning4j.arbiter.optimize.api.saving.ResultSaver;
import org.deeplearning4j.arbiter.optimize.api.score.ScoreFunction;
import org.deeplearning4j.arbiter.optimize.api.termination.TerminationCondition;
import org.deeplearning4j.arbiter.optimize.config.OptimizationConfiguration;
import org.deeplearning4j.arbiter.optimize.runner.LocalOptimizationRunner;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEODataSource;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOTaskCreator;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.generator.CandidateGeneratorFactory;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver.MDEOParameterSearchResult;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver.MDEOResultSaver;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.score.ScoreFunctionFactory;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.termination.condition.TerminationConditionFactory;

public class ParameterSearch {

  String parameterSearchOutputPath;

  public CandidateGenerator getCandidateGenerator(Optimisation optimisationSpec) {
    return CandidateGeneratorFactory.getINSTANCE()
        .get(optimisationSpec.getSolver().getParameterSearch());
  }

  public List<TerminationCondition> getTerminationCondition(Optimisation optimisationSpec) {
    return TerminationConditionFactory.getINSTANCE()
        .get(optimisationSpec.getSolver().getParameterSearch());
  }

  public ScoreFunction getScoreFunction(Optimisation optimisationSpec) {
    return ScoreFunctionFactory.getINSTANCE().get(optimisationSpec);
  }

  public ResultSaver getResultSaver(String moptProjectPath) {
    return new MDEOResultSaver(this.getParameterSearchOutputPath(moptProjectPath));
  }

  public String getParameterSearchOutputPath(String moptProjectPath) {

    if (this.parameterSearchOutputPath == null) {
      var experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(new Date());
      // TODO Make this path a parameter from the cli project
      var outputPath =
          Paths.get(
              moptProjectPath, String.format("mdeo-results/parameter-search-%s/", experimentDate));

      try {
        var outputDirectory = Files.createDirectories(outputPath);
        this.parameterSearchOutputPath = outputDirectory.toFile().getAbsolutePath();

      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException(
            "Could not make parameter search output directory: " + outputPath.toAbsolutePath());
      }
    }

    return this.parameterSearchOutputPath;
  }

  public int getMaximumNumberOfConcurrentTasks() {

    var cores = Runtime.getRuntime().availableProcessors();

    // Use all but 4 cores on the system
    var availableCores = cores - 4;
    if (availableCores > 1) {
      return availableCores;
    }

    // If running on a machine with a low number of cores, default to 1 task
    return 1;
  }

  public OptimizationConfiguration getOptimisationConfiguration(
      String moptProjectPath, Optimisation optimisationSpec) {

    var candidateGenerator = getCandidateGenerator(optimisationSpec);
    var resultSaver = getResultSaver(moptProjectPath);
    var scoreFunction = getScoreFunction(optimisationSpec);
    var terminationConditions = getTerminationCondition(optimisationSpec);

    // Given these configuration options, let's put them all together:
    return new OptimizationConfiguration.Builder()
        .candidateGenerator(candidateGenerator)
        .scoreFunction(scoreFunction)
        .dataSource(MDEODataSource.class, new Properties())
        .modelSaver(resultSaver)
        .terminationConditions(terminationConditions)
        .build();
  }

  public MDEOParameterSearchResult search(String moptProjectPath, Optimisation optimisationSpec) {

    var configuration = getOptimisationConfiguration(moptProjectPath, optimisationSpec);
    var concurrentTasks = getMaximumNumberOfConcurrentTasks();
    var runner =
        new LocalOptimizationRunner(
            concurrentTasks, configuration, new MDEOTaskCreator(optimisationSpec, moptProjectPath));

    // Start the hyperparameter optimization
    runner.execute();

    return new MDEOParameterSearchResult(runner, this.parameterSearchOutputPath);
  }
}
