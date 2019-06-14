package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.moeaframework.core.PopulationIO;
import org.moeaframework.core.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class MOEAObjectivesOutputDescriptor implements ResultsDescriptor {

  @Override
  public void generateDescription(
      Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    var objectivesOutputPath =
        Paths.get(
            batchPath.toAbsolutePath().toString(),
            String.format("batch-%s-serialized-objectives.pf", batch.id));

    batchOverallOutput.append(
        "Serialized objectives saved to: " + objectivesOutputPath.toAbsolutePath().toString());
    batchOverallOutput.append(System.getProperty("line.separator"));

    var outputFile = new File(objectivesOutputPath.toAbsolutePath().toString());

    var solutions = new ArrayList<Solution>();

    batch.getSolutions().forEach(solution -> solutions.add((Solution) solution));

    try {
      PopulationIO.writeObjectives(outputFile, solutions);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(
          "Could not write objectives result file to disk: " + outputFile.getAbsolutePath());
    }
  }
}
