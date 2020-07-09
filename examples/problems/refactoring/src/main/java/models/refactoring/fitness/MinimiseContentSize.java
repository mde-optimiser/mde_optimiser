package models.refactoring.fitness;

import at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseContentSize implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var refactoring = (RefactoringModel) solution.getModel();
    var fitness = refactoring.getProperties().size() + refactoring.getEntities().size() * 1.1;

    System.out.println(String.format("%s: %s", this.getName(), fitness));

    return fitness;
  }

  @Override
  public String getName() {
    return "Minimise Content Size";
  }
}
