package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.ParameterKind;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.engine.HenshinEngineFactory;

public class HenshinExecutor {

  Engine engine;

  /**
   * Using only the UnitApplicationImpl class to run both Units and Rules as this class implements
   * functionality to run a single Rule.
   */
  UnitApplicationImpl unitRunner;

  RuleApplicationImpl ruleRunner;

  IEvolverParametersFactory evolverParametersFactory;

  List<Unit> mutationOperators;
  List<Unit> crossoverOperators;

  public HenshinExecutor(
      IEvolverParametersFactory evolverParametersFactory,
      List<Unit> mutationOperators,
      List<Unit> crossoverOperators,
      SolverSpec solverSpec) {

    this.engine = new HenshinEngineFactory(solverSpec).create();

    engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);

    this.unitRunner = new MdeoUnitApplicationImpl(engine);
    this.ruleRunner = new MdeoRuleApplicationImpl(engine);

    this.mutationOperators = mutationOperators;
    this.crossoverOperators = crossoverOperators;
  }

  public boolean runRuleOperator(Unit operator, EGraph graph, List<Solution> object) {

    ruleRunner.setEGraph(graph);
    ruleRunner.setUnit(operator);

    if (operator.getParameters() != null) {
      // TODO Not sure about this filter. Check what kind of parameter we would expect people
      // to pass in
      var inParameters =
          operator.getParameters().stream()
              .filter(parameter -> parameter.getKind().equals(ParameterKind.IN))
              .collect(Collectors.toList());

      if (!inParameters.isEmpty()) {
        inParameters.forEach(
            parameter ->
                ruleRunner.setParameterValue(
                    parameter.getName(),
                    evolverParametersFactory.getParameterValue(operator, parameter, object)));
      }
    }

    // Run the selected Henshin Rule
    return ruleRunner.execute(null);
  }

  public boolean runUnitOperator(Unit operator, EGraph graph, List<Solution> object) {

    unitRunner.setEGraph(graph);
    unitRunner.setUnit(operator);

    var inParameters =
        operator.getParameters().stream()
            .filter(parameter -> parameter.getKind().equals(ParameterKind.IN))
            .collect(Collectors.toList());

    if (!inParameters.isEmpty()) {
      inParameters.forEach(
          parameter ->
              unitRunner.setParameterValue(
                  parameter.getName(),
                  evolverParametersFactory.getParameterValue(operator, parameter, object)));
    }

    // Run the selected Henshin Unit
    return unitRunner.execute(null);
  }

  public boolean operatorApplied(Unit operator, EGraphImpl graph, Solution candidateSolution) {

    if (operator.eClass().getClassifierID() == HenshinPackage.RULE) {
      return this.runRuleOperator(operator, graph, Arrays.asList(candidateSolution));
    }

    return this.runUnitOperator(operator, graph, Arrays.asList(candidateSolution));
  }

  public List<Unit> getMutationOperators() {
    return this.mutationOperators;
  }

  public List<Unit> getCrossoverOperators() {

    return this.crossoverOperators;
  }

  public Engine getEngine() {

    return this.engine;
  }
}
