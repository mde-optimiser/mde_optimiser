package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem;

import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;

public class MoeaOptimisationProblem extends AbstractProblem {

  SolutionGenerator solutionGenerator;

  public MoeaOptimisationProblem(
      int numberOfVariables, int numberOfObjectives, int numberOfConstraints) {
    super(numberOfVariables, numberOfObjectives, numberOfConstraints);
  }

  public MoeaOptimisationProblem(SolutionGenerator solutionGenerator) {
    // Number of variables is for now always one.
    this(
        1,
        solutionGenerator.getOptimisationModel().getGoal().getObjectives().size(),
        solutionGenerator.getOptimisationModel().getGoal().getConstraints().size());
    this.solutionGenerator = solutionGenerator;
  }

  public void evaluate(Solution solution) {

    var moeaSolution = (MoeaOptimisationSolution) solution;

    // Calculate objectives
    solutionGenerator
        .getObjectiveFunctions()
        .forEach(
            fitnessFunction ->
                moeaSolution.setObjective(
                    solutionGenerator.getObjectiveFunctions().indexOf(fitnessFunction),
                    fitnessFunction.computeFitness(moeaSolution.getModel())));

    // Calculate Constraints
    solutionGenerator
        .getConstraintFunctions()
        .forEach(
            constraintFunction ->
                moeaSolution.setConstraint(
                    solutionGenerator.getConstraintFunctions().indexOf(constraintFunction),
                    constraintFunction.computeFitness(moeaSolution.getModel())));
  }

  @Override
  public MoeaOptimisationSolution newSolution() {
    return new MoeaOptimisationSolution(solutionGenerator);
  }
}
