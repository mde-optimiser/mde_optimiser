package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.reflection.LanguageClassLoader;

public class BatchReportsDescriptor implements ResultsDescriptor {

  List<IGuidanceFunction> reportFunctions;

  public BatchReportsDescriptor(Optimisation moptOptimisation) {
    this.loadReportFunctions(moptOptimisation);
  }

  @Override
  public void generateDescription(
      Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    if (this.reportFunctions.isEmpty()) {
      return;
    }

    var objectivesOutputPath =
        Paths.get(
            batchOutputPath.toString(), String.format("batch-%s-serialized-reports.txt", batch.id));

    batchOverallOutput.append(
        "Serialized batch reports saved to: " + objectivesOutputPath.toAbsolutePath().toString());
    batchOverallOutput.append(System.getProperty("line.separator"));

    writeReportInfo(new File(objectivesOutputPath.toAbsolutePath().toString()), batch);
  }

  public void loadReportFunctions(Optimisation moptOptimisation) {

    this.reportFunctions = new ArrayList<>();

    moptOptimisation
        .getSearch()
        .getReports()
        .forEach(function -> this.reportFunctions.add(LanguageClassLoader.load(function)));
  }

  public void writeReportInfo(File outputFile, MDEOBatch batch) {

    BufferedWriter writer = null;
    try {
      writer = new BufferedWriter(new FileWriter(outputFile));

      try {

        for (var i = 0; i < batch.getSolutions().size(); i++) {

          var solution = (MoeaOptimisationSolution) batch.getSolutions().get(i);

          for (var j = 0; j < reportFunctions.size(); j++) {

            writer.write(
                Double.toString(reportFunctions.get(j).computeFitness(solution.getModel())));

            if (j < reportFunctions.size()) writer.write(" ");
          }

          writer.newLine();
        }
      } finally {
        if (writer != null) {
          writer.close();
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(String.format("Unable to write to file: %s", outputFile));
    }
  }
}
