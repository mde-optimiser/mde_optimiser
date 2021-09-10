package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.moeaframework.analysis.plot.Plot;
import org.moeaframework.core.Population;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class ParetoChartOutputDescriptor implements ResultsDescriptor {

  @Override
  public void generateDescription(
      Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    // If there is only one objectove, don't show the chart
    if (!batch.getSolutions().isEmpty() && batch.getSolutions().get(0).getObjectives().length < 2) {
      return;
    }

    var objectivesOutputPath =
        Paths.get(
            batchPath.toAbsolutePath().toString(),
            String.format("batch-%s-pareto-front.jpeg", batch.id));

    batchOverallOutput.append(
        "Serialized objectives saved to: " + objectivesOutputPath.toAbsolutePath().toString());
    batchOverallOutput.append(System.getProperty("line.separator"));

    var outputFile = objectivesOutputPath.toAbsolutePath().toFile();

    var population = new Population();
    population.addAll(batch.getSolutions());

    // TODO Check the iterator here
    try {
      new Plot()
          .add(String.format("Pareto front for batch %s", batch.id), population)
          .setXLabel(
              batch.getSolutions().get(0).getFormattedObjectives().keySet().iterator().next())
          .setYLabel(
              batch.getSolutions().get(0).getFormattedObjectives().keySet().iterator().next())
          .save(outputFile);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not write pareto chart to file: " + outputFile);
    }
  }
}
