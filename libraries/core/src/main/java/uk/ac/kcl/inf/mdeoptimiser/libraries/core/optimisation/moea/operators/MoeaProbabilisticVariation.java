package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;

public class MoeaProbabilisticVariation implements Variation {

  Variation crossoverOperator;
  Variation mutationOperator;
  double crossoverRate;
  double mutationRate;

  public MoeaProbabilisticVariation(
      Variation crossover, Variation mutation, double crossoverRate, double mutationRate) {
    this.crossoverOperator = crossover;
    this.mutationOperator = mutation;
    this.crossoverRate = crossoverRate;
    this.mutationRate = mutationRate;
  }

  // Used to evolve a solution using the henshin evolvers
  @Override
  public Solution[] evolve(Solution[] parents) {
    var result = Arrays.copyOf(parents, parents.length);
    var random = new Random();

    // Apply crossover with probability specified by the user
    var crossoverProbability = random.nextDouble();

    if (crossoverProbability <= crossoverRate) {

      System.out.println("Running crossover with probability: " + crossoverProbability);

      if (result.length == crossoverOperator.getArity()) {
        result = crossoverOperator.evolve(result);

      } else {
        throw new RuntimeException(
            String.format(
                "Invalid number of parents for crossover operator. Given: %s Expected: %s",
                result.length, this.getArity()));
      }
    } else {
      System.out.println("Not running crossover this run");
    }

    var solutions = new LinkedList<Solution>();

    for (Solution aResult : result) {

      var mutationProbability = random.nextDouble();
      if (mutationProbability <= mutationRate) {
        System.out.println("Running mutation with probability: " + mutationProbability);
        solutions.addAll(Arrays.asList(mutationOperator.evolve(new Solution[] {aResult})));
      } else {
        System.out.println("Not running mutation this run");
      }
    }
    Solution[] solutionArray = new Solution[solutions.size()];
    return solutions.toArray(solutionArray);
  }

  /**
   * The number of solutions to be supplied to the evolve method. For the crossover operator we
   * require a number of two parents.
   */
  @Override
  public int getArity() {
    return 2;
  }
}
