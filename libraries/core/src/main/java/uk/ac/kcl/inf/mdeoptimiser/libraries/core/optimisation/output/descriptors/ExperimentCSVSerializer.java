package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import com.google.common.base.Joiner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.TimeZone;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class ExperimentCSVSerializer implements ResultsDescriptor {

  String serializedOutputFileName = "experiment-data.csv";

  @Override
  public void generateDescription(
      Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    // If there are no solutions, then don't generate this file.
    if (batch.getSolutions().size() == 0) {
      return;
    }

    var batchRows = new ArrayList<LinkedHashMap<String, String>>();

    batch.getSolutions().stream()
        .forEach(
            solution -> {
              var rowMap = new LinkedHashMap<String, String>();

              rowMap.put("batch-id", batch.id.toString());
              rowMap.put("duration", formattedDuration(batch.duration));

              solution
                  .getFormattedObjectives()
                  .forEach((name, value) -> rowMap.put(name, value.toString()));
              solution
                  .getFormattedObjectives()
                  .forEach((name, value) -> rowMap.put(name, value.toString()));

              rowMap.put("solutions-found", "" + batch.getSolutions().size());
              var modelPath =
                  batchPath + String.format("%08X", solution.getModel().hashCode()) + ".xmi";
              rowMap.put("solution-model", modelPath);
              batchRows.add(rowMap);
            });

    var outputFile = getSerializedOutputFile(batch.getSolutions().get(0), batchPath);

    printSerializedDataRow(outputFile, batchRows);
  }

  public String formattedDuration(long batchDuration) {

    var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

    return formatter.format(new Date(batchDuration).getTime());
  }

  public void printSerializedDataRow(
      File serializedDataFile, ArrayList<LinkedHashMap<String, String>> rows) {
    try {

      var out = new PrintWriter(new BufferedWriter(new FileWriter(serializedDataFile, true)));
      rows.forEach(row -> out.println(Joiner.on(",").join(row.values())));
      out.close();

      // more code
    } catch (IOException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public File createSerializedOutputFile(
      File serializedOutputFile, MoeaOptimisationSolution solution) {
    if (!serializedOutputFile.exists()) {

      try {
        if (serializedOutputFile.createNewFile()) {

          var headers = new ArrayList<String>();
          headers.add("batch-id");
          headers.add("duration");

          solution.getFormattedObjectives().forEach((name, value) -> headers.add("o-" + name));
          solution.getFormattedConstraints().forEach((name, value) -> headers.add("c-" + name));

          headers.add("solutions-found");
          headers.add("solution-model");

          try {

            var out =
                new PrintWriter(new BufferedWriter(new FileWriter(serializedOutputFile, true)));
            out.println(Joiner.on(",").join(headers));
            out.close();

            // more code
          } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException(
            String.format(
                "Unable to write to file from CSVSerialiser: %s",
                serializedOutputFile.getAbsolutePath()));
      }
    }

    return serializedOutputFile;
  }

  public File getSerializedOutputFile(MoeaOptimisationSolution solution, Path batchPath) {

    var batchRootDirectory = new File(batchPath.toAbsolutePath().toString());

    var serializedOutputFile =
        new File(batchRootDirectory.getParent() + "/" + this.serializedOutputFileName);

    if (serializedOutputFile.exists()) {
      return serializedOutputFile;
    }

    return this.createSerializedOutputFile(serializedOutputFile, solution);
  }
}
