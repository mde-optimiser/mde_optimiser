package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.AccumulatorSerialiser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.BatchReportsDescriptor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.ExperimentCSVSerializer;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.GeneratedMutationOperatorsDescriptor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.MOEAObjectivesOutputDescriptor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.MoptSpecSerialiser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.ParetoChartOutputDescriptor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.ResultsDescriptor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.SystemInformationDescriptor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.TextDescriptor;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.saver.MDEOParameterSearchResult;

public class MDEOResultsOutput {

  Date experimentStartTime;
  List<MDEOBatch> batches;
  Path projectRoot;
  Path moptFile;
  Optimisation moptConfiguration;
  List<ResultsDescriptor> resultsDescriptors;
  boolean classicRuleMatchingEnabled;

  public MDEOResultsOutput(
      Date startTime, Path projectRoot, Path moptFile, Optimisation moptConfiguration) {
    experimentStartTime = startTime;
    // Store output of a batch experiment id, solutions set
    batches = new LinkedList<MDEOBatch>();
    this.projectRoot = projectRoot;
    this.moptConfiguration = moptConfiguration;
    this.moptFile = moptFile;
    this.resultsDescriptors = loadDescriptors();
    this.classicRuleMatchingEnabled = classicRuleMatchingEnabled;
  }

  public void logBatch(MDEOBatch batch) {
    batches.add(batch);
  }

  public void saveOutcome() {
    this.saveOutcome(null);
  }

  public void saveOutcome(Integer customBatch) {

    // When running custom batches no date is needed
    var outcomePath =
        Paths.get(projectRoot.toString(), String.format("mdeo-results/experiment-%s/", moptFile));

    if (customBatch == null) {
      var experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(experimentStartTime);
      outcomePath =
          Paths.get(
              projectRoot.toString(),
              String.format(
                  "mdeo-results/experiment-%s-%s/", moptFile.getFileName(), experimentDate));
    }

    // Used to generate the experiments summary
    var batchesOutput = new StringBuilder();

    var outcomePathVal = outcomePath;

    batches.stream()
        .forEach(
            batch -> {
              batchesOutput.append("============================================");
              batchesOutput.append(System.getProperty("line.separator"));

              var batchOutputPath =
                  Paths.get(outcomePathVal.toString(), String.format("batch-%s/", batch.id));

              this.resultsDescriptors.stream()
                  .forEach(
                      descriptor ->
                          descriptor.generateDescription(batchOutputPath, batch, batchesOutput));

              batchesOutput.append("============================================");
              batchesOutput.append(System.getProperty("line.separator"));
            });

    if (customBatch == null) {
      outputExperimentSummary(batches, outcomePath, moptFile, batchesOutput);
    }
  }

  public void outputExperimentSummary(
      List<MDEOBatch> batches, Path outcomePath, Path moptFile, StringBuilder batchesOutput) {

    var averageTime =
        batches.stream()
            .map(batch -> batch.duration)
            .collect(Collectors.averagingDouble(Long::doubleValue));

    var averageObjectiveValues = new HashMap<String, Double>();
    var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

    for (var i = 0; i < batches.size(); i++) {

      var batch = batches.get(i);
      var sumBatchObjectives = new HashMap<String, Double>();

      batch.solutions.forEach(
          solution -> {
            solution
                .getFormattedObjectives()
                .forEach(
                    (p1, p2) -> {
                      if (sumBatchObjectives.containsKey(p1)) {
                        sumBatchObjectives.put(p1, sumBatchObjectives.get(p1) + p2);
                      } else {
                        sumBatchObjectives.put(p1, p2);
                      }
                    });
          });

      sumBatchObjectives.forEach(
          (p1, p2) -> {
            if (averageObjectiveValues.containsKey(p1)) {
              averageObjectiveValues.put(
                  p1, averageObjectiveValues.get(p1) + p2 / batch.getSolutions().size());
            } else {
              averageObjectiveValues.put(p1, p2);
            }
          });
    }

    try {
      var infoWriter = new PrintWriter(new File(outcomePath + "overall-results.txt"));

      infoWriter.println(
          String.format("Average experiment time: %s", formatter.format(averageTime)));
      infoWriter.println();

      infoWriter.println(batchesOutput.toString());

      infoWriter.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not write overall results file.");
    }
  }

  /**
   * Load a set of descriptors that we would like to run on the produced experiments data. They
   * perform tasks such as generating parseable results, serializing models and generated mutation
   * operators.
   */
  private List<ResultsDescriptor> loadDescriptors() {

    var descriptors = new ArrayList<ResultsDescriptor>();

    descriptors.add(new TextDescriptor());
    descriptors.add(new MOEAObjectivesOutputDescriptor());
    descriptors.add(new ParetoChartOutputDescriptor());
    descriptors.add(new BatchReportsDescriptor(this.moptConfiguration));
    descriptors.add(new GeneratedMutationOperatorsDescriptor());
    descriptors.add(new ExperimentCSVSerializer());
    descriptors.add(new AccumulatorSerialiser());
    descriptors.add(new SystemInformationDescriptor());
    descriptors.add(new MoptSpecSerialiser(this.moptFile));
    return descriptors;
  }

  // TODO Split this from the normal run results
  public void saveParameterSearchOutcome(MDEOParameterSearchResult outcome) {

    try {
      var infoWriter =
          new PrintWriter(
              Paths.get(outcome.getParameterSearchOutputPath(), "overall-results.txt").toFile());

      infoWriter.println("Parameter search results");
      infoWriter.println();
      infoWriter.println(
          "Number of configurations evaluated: " + outcome.getNumberOfCandidatesCompleted());

      infoWriter.println("Best candidate");
      infoWriter.println("============================================");
      describeResult(infoWriter, outcome.getBestResultReference().getResult());
      infoWriter.println();

      infoWriter.println("All candidates");
      infoWriter.println("============================================");
      outcome
          .getResultReferences()
          .forEach(
              resultReference -> {
                try {
                  describeResult(infoWriter, resultReference.getResult());
                } catch (IOException e) {
                  e.printStackTrace();
                  throw new RuntimeException("Could not load result reference");
                }
              });

      infoWriter.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not save parameter search outcome.");
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not load parameter search outcome.");
    }
  }

  public void describeResult(PrintWriter printWriter, OptimizationResult result) {

    try {
      var mdeoCandidate =
          (MDEOHyperparametersConfiguration) result.getResultReference().getResultModel();

      var mdeoBatchResults = (Map<Integer, SearchResult>) result.getModelSpecificResults();

      printWriter.println(String.format("Candidate id: %s", result.getIndex()));
      printWriter.println(String.format("Candidate score: %s", result.getScore()));
      printWriter.println(String.format("Evolutions parameter: %s", mdeoCandidate.evolutions));
      printWriter.println(String.format("Populations parameter: %s", mdeoCandidate.populationSize));
      printWriter.println(String.format("Total runtime: %s", getCandidateRuntime(result)));
      printWriter.println(String.format("Total batches: %s", mdeoBatchResults.size()));
      printWriter.println();
      // TODO Add more score statistics best, worst, std-dev

    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Coult not load result model.");
    }
  }

  public String getCandidateRuntime(OptimizationResult result) {

    var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    return formatter.format(
        result.getCandidateInfo().getEndTime() - result.getCandidateInfo().getStartTime());
  }
}
