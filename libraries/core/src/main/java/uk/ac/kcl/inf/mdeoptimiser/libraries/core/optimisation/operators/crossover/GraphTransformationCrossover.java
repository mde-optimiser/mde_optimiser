package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.Unit;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;

public class GraphTransformationCrossover implements CrossoverStrategy {

  HenshinExecutor henshinExecutor;
  List<Unit> breedingOperators;

  public GraphTransformationCrossover(
      HenshinExecutor henshinExecutor, List<Unit> breedingOperators) {
    this.henshinExecutor = henshinExecutor;
    this.breedingOperators = breedingOperators;
  }

  public List<Solution> breed(List<Solution> parents) {

    var crossoverParents = parents.stream().map(Solution::new).collect(Collectors.toList());

    // TODO This seems a bit off, but perhaps we get rid of crossover altogether anyway
    var graph =
        crossoverParents.stream()
            .map(Solution::getModel)
            .collect(Collectors.toCollection(EGraphImpl::new));

    var triedOperators = new ArrayList<Unit>();

    // Randomly pick one unit
    var operator = breedingOperators.get(new Random().nextInt(breedingOperators.size()));

    while (triedOperators.size() < breedingOperators.size()) {

      if (operator.eClass().getClassifierID() == HenshinPackage.RULE) {
        // Run the selected Henshin Rule
        if (henshinExecutor.runRuleOperator(operator, graph, parents)) {

          return graph.getRoots().stream()
              .map(root -> new Solution(root))
              .collect(Collectors.toList());
        }

      } else {
        if (henshinExecutor.runUnitOperator(operator, graph, parents)) {

          return graph.getRoots().stream()
              .map(root -> new Solution(root))
              .collect(Collectors.toList());
        }
      }

      triedOperators.add(operator);
      var remainingRules =
          breedingOperators.stream()
              .filter(o -> !triedOperators.contains(o))
              .collect(Collectors.toList());

      if (remainingRules.size() == 0) {
        return parents;
      }

      operator = remainingRules.get(new Random().nextInt(remainingRules.size()));
    }

    // We didn't find any applicable evolvers...
    // TODO This should be logged as it should probably never happen
    System.out.println("Model with no crossover evolvers applicable.....");

    return parents;
  }
}
