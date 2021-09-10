package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import java.nio.file.Path;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public interface ResultsDescriptor {
  public void generateDescription(
      Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput);
}
