package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem;

import org.moeaframework.core.Variable;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

// This is a representation of the evolved model
public class MoeaOptimisationVariable implements Variable {

  Solution model;
  SolutionGenerator solutionGenerator;

  public MoeaOptimisationVariable(Solution model, SolutionGenerator solutionGenerator) {
    this.model = model;
    this.solutionGenerator = solutionGenerator;
  }

  @Override
  public Variable copy() {
    return new MoeaOptimisationVariable(model, solutionGenerator);
  }

  // Used to create the initial population
  @Override
  public void randomize() {
    // Apply evolvers on the model
    model = solutionGenerator.mutate(this.model);
  }

  public Solution getModel() {
    return model;
  }
}
