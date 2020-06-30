package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class AccumulatorSerialiser implements ResultsDescriptor {

  String serializedOutputFileName = "data-steps.csv";

  @Override
  public void generateDescription(
      Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    // If there are no solutions, then don't generate this file.
    if (batch.searchResult == null) {
      return;
    }

    var batchRootDirectory = new File(batchPath.toString());

    var serializedOutputFile =
        new File(
            String.format(
                "%s/batch-%s-%s", batchRootDirectory, batch.id, this.serializedOutputFileName));

    try {
      batch.searchResult.getAccumulator().saveCSV(serializedOutputFile);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(
          String.format(
              "Unable to serialise batch csv file: %s", serializedOutputFile.getAbsolutePath()));
    }
  }
}
