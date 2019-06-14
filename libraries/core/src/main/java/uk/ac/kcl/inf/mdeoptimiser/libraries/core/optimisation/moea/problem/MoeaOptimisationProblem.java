package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem;

import java.util.List;
import java.util.stream.Collectors;
import org.moeaframework.core.Solution;
import org.moeaframework.problem.AbstractProblem;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionsFactory;

public class MoeaOptimisationProblem extends AbstractProblem {

  SolutionGenerator solutionGenerator;

  List<IGuidanceFunction> fitnessFunctions;
  List<IGuidanceFunction> constraintFunctions;

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

  public SolutionGenerator getSolutionGenerator() {
    return this.solutionGenerator;
  }

  public List<IGuidanceFunction> getConstraintFunctions() {
    if (this.constraintFunctions == null) {
      setConstraintFunctions();
    }

    return this.constraintFunctions;
  }

  public void setConstraintFunctions() {
    if (constraintFunctions == null) {
      this.constraintFunctions =
          solutionGenerator.getOptimisationModel().getGoal().getConstraints().stream()
              .map(
                  constraint ->
                      new GuidanceFunctionsFactory()
                          .loadFunction(new GuidanceFunctionAdapter(constraint)))
              .collect(Collectors.toList());
    }
  }

  public List<IGuidanceFunction> getFitnessFunctions() {
    if (this.fitnessFunctions == null) {
      setFitnessFunctions();
    }

    return this.fitnessFunctions;
  }

  public void setFitnessFunctions() {
    if (fitnessFunctions == null) {
      this.fitnessFunctions =
          solutionGenerator.getOptimisationModel().getGoal().getObjectives().stream()
              .map(
                  objective ->
                      new GuidanceFunctionsFactory()
                          .loadFunction(new GuidanceFunctionAdapter(objective)))
              .collect(Collectors.toList());
    }
  }

  public void evaluate(Solution solution) {

    var moeaSolution = (MoeaOptimisationSolution) solution;

    // Set objectives
    getFitnessFunctions()
        .forEach(
            (fitnessFunction) ->
                moeaSolution.setObjective(
                    getFitnessFunctions().indexOf(fitnessFunction),
                    fitnessFunction.computeFitness(moeaSolution.getModel())));

    // Set Constraints
    getConstraintFunctions()
        .forEach(
            constraintFunction ->
                moeaSolution.setConstraint(
                    getConstraintFunctions().indexOf(constraintFunction),
                    constraintFunction.computeFitness(moeaSolution.getModel())));
  }

  @Override
  public MoeaOptimisationSolution newSolution() {

    return new MoeaOptimisationSolution(solutionGenerator);
  }
}
