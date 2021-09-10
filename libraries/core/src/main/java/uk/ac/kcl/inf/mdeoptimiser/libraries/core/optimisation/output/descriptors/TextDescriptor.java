package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors;

import com.google.common.io.Files;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.model.NamedElement;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.ResourceSetWrapper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch;

public class TextDescriptor implements ResultsDescriptor {

  ResourceSetWrapper resourceSet;

  public TextDescriptor() {
    this.resourceSet = new ResourceSetWrapper();
  }

  @Override
  public void generateDescription(
      Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

    var batchInfoPath =
        Paths.get(
            batchOutputPath.toAbsolutePath().toString(),
            String.format("batch-%s-outcome.txt", batch.id));

    var outputFile = batchInfoPath.toAbsolutePath().toFile();
    try {
      Files.createParentDirs(outputFile);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    PrintWriter batchWriter;
    try {
      batchWriter = new PrintWriter(outputFile);

      var batchDuration = new Date(batch.duration);

      var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
      formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

      batchWriter.println();
      batchWriter.println("============================================");
      batchWriter.println();
      batchWriter.println(
          String.format(
              "Batch %s duration: %s", batch.id, formatter.format(batchDuration.getTime())));
      batchWriter.println();

      batchWriter.println("--------------------------------------------");

      for (var i = 0; i < batch.getSolutions().size(); i++) {

        var solution = batch.getSolutions().get(i);
        var modelPath =
            batchOutputPath + String.format("%08X", solution.getModel().hashCode()) + ".xmi";

        resourceSet.writeModel(solution.getModel().getModel(), modelPath);
        storeSolutionData(batchWriter, modelPath, solution);
      }

      batchWriter.println();
      batchWriter.println("============================================");
      batchWriter.println();
      batchWriter.close();

      batchOverallOutput.append(Files.toString(outputFile, Charset.defaultCharset()));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void storeSolutionData(
      PrintWriter infoWriter, String modelPath, MoeaOptimisationSolution solution) {

    infoWriter.println("Evaluation data for solution: " + modelPath);
    infoWriter.println();
    infoWriter.println("Objective values:");

    // Pretty print the objectives
    var objectives = solution.getFormattedObjectives();
    objectives.forEach((key, value) -> infoWriter.println(String.format("%s: %s", key, value)));

    infoWriter.println("");

    // Pretty print the constraints
    var constraints = solution.getFormattedConstraints();
    if (constraints.size() > 0) {

      infoWriter.println("Constraint values:");
      constraints.forEach((key, value) -> infoWriter.println(String.format("%s: %s", key, value)));
    }

    infoWriter.println("");
    infoWriter.println("Parent mutation steps:");

    var mutationSteps = solution.getModel().getParentMutationSteps();
    infoWriter.println(
        String.format(
            "Steps: %s",
            mutationSteps.stream()
                .map(s -> String.format("[x=%s, sigma=%s]", s.getFirst(), s.getSecond()))
                .collect(Collectors.joining(" -> "))));

    infoWriter.println("");

    var transformations = solution.getModel().getTransformationsChain();
    if (transformations.size() > 0) {
      infoWriter.println("Transformations chain:");
      infoWriter.println(String.format("Length: %s", transformations.size()));
      infoWriter.println(
          String.format(
              "Sequence: %s",
              transformations.stream()
                  .map(
                      step ->
                          step.stream()
                              .map(NamedElement::getName)
                              .collect(Collectors.toList())
                              .toString())
                  .collect(Collectors.joining(" -> "))));
    }
  }
}
