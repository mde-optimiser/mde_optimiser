package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms;

import java.util.Properties;
import org.moeaframework.algorithm.*;
import org.moeaframework.core.*;
import org.moeaframework.core.fitness.HypervolumeFitnessEvaluator;
import org.moeaframework.core.operator.GAVariation;
import org.moeaframework.core.operator.RandomInitialization;
import org.moeaframework.core.spi.AlgorithmProvider;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm.UnexpectedAlgorithmException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators.MoeaOptimisationCrossoverVariation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators.MoeaOptimisationMutationVariation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.operators.MoeaProbabilisticVariation;

public class MoeaOptimisationAlgorithmProvider extends AlgorithmProvider {

  public Algorithm algorithm;

  public Algorithm getAlgorithm(String algorithm, Properties properties, Problem problem) {
    switch (algorithm) {
      case "SPEA2":
        this.algorithm = createSPEA2(problem, properties);
        break;
      case "SMSMOEA":
        this.algorithm = createSMSMOEA(problem, properties);
      case "IBEA":
        this.algorithm = createIBEA(problem, properties);
        break;
      case "NSGAII":
        this.algorithm = createNSGAII(problem, properties);
        break;
      case "VEGA":
        this.algorithm = createVEGA(problem, properties);
      case "PESA2":
        this.algorithm = createPESA2(problem, properties);
      case "PAES":
        this.algorithm = createPAES(problem, properties);
      case "RANDOM":
        this.algorithm = createRandom(problem, properties);
      default:
        throw new UnexpectedAlgorithmException(algorithm);
    }

    return this.algorithm;
  }

  private Initialization getRandomInitialization(Problem problem, Properties properties) {
    return new RandomInitialization(problem, (Integer) properties.get("populationSize"));
  }

  public Variation getVariation(Properties properties) {

    var algorithmVariation = new AlgorithmVariation((Parameter) properties.get("variationType"));

    // Check if we have weighted genetic variation
    // TODO: This needs to be refactored and fixed
    if (algorithmVariation.isProbabilisticVariation()) {
      var crossoverVariation =
          new MoeaOptimisationCrossoverVariation(
              (SolutionGenerator) properties.get("solutionGenerator"));
      var mutationVariation =
          new MoeaOptimisationMutationVariation(
              (SolutionGenerator) properties.get("solutionGenerator"));

      return new MoeaProbabilisticVariation(
          crossoverVariation,
          mutationVariation,
          algorithmVariation.getCrossoverRate(),
          algorithmVariation.getMutationRate());
    }

    // Check variation type is crossover with mutation
    if (algorithmVariation.isGeneticVariation()) {
      var crossoverVariation =
          new MoeaOptimisationCrossoverVariation(
              (SolutionGenerator) properties.get("solutionGenerator"));
      var mutationVariation =
          new MoeaOptimisationMutationVariation(
              (SolutionGenerator) properties.get("solutionGenerator"));

      return new GAVariation(crossoverVariation, mutationVariation);
    }

    // Check variation type is mutation
    if (algorithmVariation.isMutationVariation()) {
      return new MoeaOptimisationMutationVariation(
          (SolutionGenerator) properties.get("solutionGenerator"));
    }

    // Must be crossover only then
    return new MoeaOptimisationCrossoverVariation(
        (SolutionGenerator) properties.get("solutionGenerator"));
  }

  public Algorithm createNSGAII(Problem problem, Properties properties) {
    // Create an initial random population of population size
    var initialization =
        new RandomInitialization(problem, (Integer) properties.get("populationSize"));

    return new NSGAII(
        problem,
        new NondominatedSortingPopulation(),
        null, // no archive
        null, // default selection the the one built in
        getVariation(properties),
        getRandomInitialization(problem, properties));
  }

  // http://moeaframework.org/javadoc/org/moeaframework/algorithm/VEGA.html
  public Algorithm createVEGA(Problem problem, Properties properties) {
    // Create an initial random population of population size
    var initialization =
        new RandomInitialization(problem, (Integer) properties.get("populationSize"));

    return new VEGA(
        problem,
        new NondominatedSortingPopulation(),
        null, // no archive
        getRandomInitialization(problem, properties),
        getVariation(properties));
  }

  // http://moeaframework.org/javadoc/org/moeaframework/algorithm/SPEA2.html
  public Algorithm createSPEA2(Problem problem, Properties properties) {

    var initialization =
        new RandomInitialization(problem, (Integer) properties.get("populationSize"));

    //
    //      Parameters:
    //      problem - the problem
    //      initialization - the initialization procedure
    //      variation - the variation operator
    //      numberOfOffspring - the number of offspring generated each iteration
    //      k - niching parameter specifying that crowding is computed using the k-th nearest
    // neighbor, recommend k=1

    return new SPEA2(
        problem,
        getRandomInitialization(problem, properties),
        getVariation(properties),
        (Integer) properties.get("populationSize"),
        1);
  }

  // http://moeaframework.org/javadoc/org/moeaframework/algorithm/SMSEMOA.html
  //  Parameters:
  //  problem - the problem
  //  initialization - the initialization operator
  //  variation - the variation operator
  //  fitnessEvaluator - the fitness evaluator

  public Algorithm createSMSMOEA(Problem problem, Properties properties) {

    var initialization =
        new RandomInitialization(problem, (Integer) properties.get("populationSize"));

    return new SMSEMOA(
        problem,
        getRandomInitialization(problem, properties),
        getVariation(properties),
        new HypervolumeFitnessEvaluator(problem));
  }

  public Algorithm createIBEA(Problem problem, Properties properties) {

    return new IBEA(
        problem,
        null,
        getRandomInitialization(problem, properties),
        getVariation(properties),
        new HypervolumeFitnessEvaluator(problem));
  }

  public Algorithm createPESA2(Problem problem, Properties properties) {

    var bisections = 0;
    var archiveSize = 0;

    return new PESA2(
        problem,
        getVariation(properties),
        getRandomInitialization(problem, properties),
        bisections,
        archiveSize);
  }

  public Algorithm createPAES(Problem problem, Properties properties) {

    var bisections = (Integer) properties.get("bisections");
    var archiveSize = (Integer) properties.get("archive.size");

    return new PESA2(
        problem,
        getVariation(properties),
        getRandomInitialization(problem, properties),
        bisections,
        archiveSize);
  }

  public Algorithm createRandom(Problem problem, Properties properties) {

    return new RandomSearch(
        problem, getRandomInitialization(problem, properties), new NondominatedPopulation());
  }
}
