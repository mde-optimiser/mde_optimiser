package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.ResourceSetWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class GeneratedMutationOperatorsDescriptor implements ResultsDescriptor {

  ResourceSetWrapper resourceSet;

  @Override
  public void generateDescription(
      Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    // Do nothing if there are no generated mutation operators
    if (batch.generatedRules.isEmpty()) {
      return;
    }

    var mutationGenerationpath =
        Paths.get(batchOutputPath.toAbsolutePath().toString(), "mutation-operators/");
    this.resourceSet = new ResourceSetWrapper(mutationGenerationpath);

    var outputFile = new File(mutationGenerationpath.toAbsolutePath().toString());
    try {
      Files.createParentDirs(outputFile);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(
          String.format(
              "Unable to create parent directories for file: %s", outputFile.getAbsolutePath()));
    }

    var metamodel = batch.generatedRules.keySet().iterator().next();
    var rules = batch.generatedRules.get(metamodel);

    for (var i = 0; i < rules.size(); i++) {

      var rule = rules.get(i);
      var modelPath = rule.getName() + ".henshin";

      // This may require a henshin resource set and a metamodel copied in the directory.
      // Serialize the henshin file
      resourceSet.writeModel(rule, modelPath);
    }

    resourceSet.writeModel(metamodel, metamodel.getName() + ".ecore");
  }
}
