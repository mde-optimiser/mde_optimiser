package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import java.nio.file.Path;
import java.nio.file.Paths;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.ResourceSetWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class MoptSpecSerialiser implements ResultsDescriptor {

  ResourceSetWrapper resourceSet;
  Path moptFile;

  public MoptSpecSerialiser(Path moptFile) {
    this.resourceSet = new ResourceSetWrapper();
    this.moptFile = moptFile;
  }

  /** Serialise the MOPT specs to each of the experiment batch output folder. */
  @Override
  public void generateDescription(
      Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    var moptSpec = batch.searchResult.getOptimisationSpec();

    var moptSpecOutputPath =
        Paths.get(batchPath.toAbsolutePath().toString(), String.format("%s", getMoptFileName()));

    resourceSet.writeModel(moptSpec, moptSpecOutputPath.toAbsolutePath().toString());
  }

  /**
   * Get the file name to save serialise the mopt spec to.
   *
   * @return string
   */
  public String getMoptFileName() {

    String moptSpecFileName = "";

    if (this.moptFile != null) {
      moptSpecFileName = this.moptFile.getFileName().toString();

      // Not sure if we can have this in production
      if (!moptSpecFileName.endsWith(".mopt")) {
        moptSpecFileName = moptSpecFileName + ".mopt";
      }

    } else {
      moptSpecFileName = "optimisation.mopt";
    }

    return moptSpecFileName;
  }
}
