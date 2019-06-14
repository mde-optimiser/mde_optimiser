package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.algorithms;

import java.util.Properties;
import org.moeaframework.algorithm.NSGAII;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.NondominatedSortingPopulation;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Variation;
import org.moeaframework.core.operator.GAVariation;
import org.moeaframework.core.operator.RandomInitialization;
import org.moeaframework.core.operator.TournamentSelection;
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
      case "NSGAII":
        this.algorithm = createNSGAII(problem, properties);
        break;
      default:
        throw new UnexpectedAlgorithmException(algorithm);
    }

    return this.algorithm;
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

    var selection = new TournamentSelection(2);

    return new NSGAII(
        problem,
        new NondominatedSortingPopulation(),
        null, // no archive
        selection,
        getVariation(properties),
        initialization);
  }

  //
  //	def Algorithm createSPEA2(Problem problem, Properties properties) {
  //
  //		var initialization = new RandomInitialization(problem, properties.get("populationSize") as
  // Integer)
  //
  //		new SPEA2(
  //				problem,
  //				initialization,
  //				getVariation(properties),
  //				properties.get("populationSize") as Integer,
  //				1
  //			);
  //	}
  //
  //	def Algorithm createeMOEA(Problem problem, Properties properties) {
  //
  //		//Create an initial random population of population size
  //		var initialization = new RandomInitialization(problem, properties.get("populationSize") as
  // Integer)
  //
  //		var selection = new TournamentSelection(2);
  //
  //		new EpsilonMOEA(
  //				problem,
  //				new NondominatedSortingPopulation(),
  //				//TODO This must be a user configurable parameter
  //				new EpsilonBoxDominanceArchive(0.01),
  //				selection,
  //				getVariation(properties),
  //				initialization
  //			);
  //	}

}
