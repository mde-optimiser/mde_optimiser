package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.listeners;

import org.moeaframework.util.progress.ProgressEvent;
import org.moeaframework.util.progress.ProgressListener;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class RuntimeProgressListener implements ProgressListener {
  @Override
  public void progressUpdate(ProgressEvent progressEvent) {

    var nfes = progressEvent.getCurrentNFE();
    var elapsedTime = progressEvent.getElapsedTime();

    if (progressEvent.getCurrentSeed() == progressEvent.getTotalSeeds()) {

      var currentApproximationSet = progressEvent.getCurrentAlgorithm().getResult();

      StringBuilder outputString = new StringBuilder();

      outputString.append(String.format("Executed NFE: %s\n", nfes));
      outputString.append(String.format("Elapsed Time: %ss\n", elapsedTime));
      outputString.append("Approximation Set: ");

      for (var solution : currentApproximationSet) {
        outputString.append("{");
        outputString.append(String.format("Fitness: %s ", solution.toString()));
        outputString.append(
            String.format(
                "Step: %s ", ((MoeaOptimisationSolution) solution).getModel().getMutationStep()));
        outputString.append("}");
      }

      outputString.append("\n=========\n");
      System.out.println(outputString);
    } else {
      System.out.println("Search finished.");
    }
  }
}
