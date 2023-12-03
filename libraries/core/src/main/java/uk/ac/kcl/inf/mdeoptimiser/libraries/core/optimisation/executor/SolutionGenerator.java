package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor;

import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.moeaframework.Instrumenter;
import org.moeaframework.core.Problem;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IModelProvider;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.EvolverParametersFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover.CrossoverStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover.CrossoverStrategyFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application.MutationApplicationStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application.MutationStrategyFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair.OperatorRepairFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.repair.OperatorRepairStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.OperatorSelectionStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.OperatorSelectionStrategyFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit.OperatorCreditStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit.OperatorCreditStrategyFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step.MutationStepSizeStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step.MutationStepSizeStrategyFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.repair.RepairStrategy;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.repair.RepairStrategyFactory;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification.ISearchSpecification;

public class SolutionGenerator {

  EPackage theMetamodel;

  ISearchSpecification searchSpecification;

  IModelProvider initialModelProvider;

  MutationApplicationStrategy mutationStrategy;
  CrossoverStrategy crossoverStrategy;

  HenshinExecutor henshinExecutor;

  MutationStepSizeStrategy mutationStepSizeStrategy;
  OperatorSelectionStrategy mutationOperatorSelectionStrategy;
  Problem problem;
  Instrumenter algorithmStepInstrumenter;
  private SearchOperatorConfiguration searchOperatorConfiguration;
  private OperatorCreditStrategy mutationOperatorCreditStrategy;
  private OperatorRepairStrategy operatorRepairStrategy;
  private RepairStrategy repairStrategy;

  public SolutionGenerator(ISearchSpecification searchSpecification) {
    this.searchSpecification = searchSpecification;
    this.initialModelProvider = searchSpecification.getModelProvider();
    this.theMetamodel = searchSpecification.getMetamodel();
    this.henshinExecutor =
        new HenshinExecutor(
            new EvolverParametersFactory(
                searchSpecification.getOptimisationModel().getSearch().getEvolvers()),
            searchSpecification.getMutationOperators(),
            searchSpecification.getBreedingOperators(),
            searchSpecification.getRepairOperators(),
            searchSpecification.getOptimisationModel().getSolver());
  }

  /**
   * Set the current problem. Not the best palce for this setter.
   *
   * @param problem
   */
  public void setProblem(Problem problem) {
    this.problem = problem;
  }

  public void setAlgorithmStepInstrumenter(Instrumenter algorithmStepInstrumenter) {
    this.algorithmStepInstrumenter = algorithmStepInstrumenter;
  }

  /**
   * Build an instance of the mutation selection strategy. The mutation selection strategy defines
   * which search operator is selected to be applied next.
   *
   * @return an instance of the operator selection strategy
   */
  private OperatorSelectionStrategy getOperatorSelectionStrategy() {

    if (this.mutationOperatorSelectionStrategy == null) {
      this.mutationOperatorSelectionStrategy =
          new OperatorSelectionStrategyFactory(
                  this.getSearchOperatorConfiguration(), this.getOperatorCreditStrategy())
              .getStrategy();
    }

    return this.mutationOperatorSelectionStrategy;
  }

  /**
   * Build an instance of the configured credit assignment strategy. Thie credit assignment strategy
   * defines what reward each operator receives during the search process.
   *
   * @return an instance of the configured credit assignment strategy
   */
  private OperatorCreditStrategy getOperatorCreditStrategy() {

    if (this.mutationOperatorCreditStrategy == null) {
      this.mutationOperatorCreditStrategy =
          new OperatorCreditStrategyFactory(this.getSearchOperatorConfiguration()).getStrategy();
    }

    return this.mutationOperatorCreditStrategy;
  }

  /**
   * Build an instance of the mutation step size strategy. The step size strategy defines the size
   * of the mutation applied to each selected solution candidate.
   *
   * @return an instance of the mutation step size strategy
   */
  private MutationStepSizeStrategy getMutationStepSizeStrategy() {

    if (this.mutationStepSizeStrategy == null) {
      this.mutationStepSizeStrategy =
          new MutationStepSizeStrategyFactory(this.getSearchOperatorConfiguration()).getStrategy();
    }

    return this.mutationStepSizeStrategy;
  }

  /**
   * Build an instance of the mutation repair strategy. The mutation repair strategy defines how the
   * mutation operators are repaired if they cannot be applied
   *
   * @return operatorRepairStrategy instance
   */
  private OperatorRepairStrategy getOperatorRepairStrategy() {

    if (this.operatorRepairStrategy == null) {
      this.operatorRepairStrategy =
          new OperatorRepairFactory(this.getSearchOperatorConfiguration()).getStrategy();
    }
    return this.operatorRepairStrategy;
  }

  /**
   * Build an instance of the mutation strategy. The mutation strategy defines how the mutation
   * operators are applied.
   *
   * @return mutationStrategy instance
   */
  private MutationApplicationStrategy getMutationStrategy() {

    if (this.mutationStrategy == null) {
      this.mutationStrategy =
          new MutationStrategyFactory(
                  this.getSearchOperatorConfiguration(),
                  this.getMutationStepSizeStrategy(),
                  this.getOperatorSelectionStrategy(),
                  this.getOperatorRepairStrategy())
              .getStrategy();
    }

    return this.mutationStrategy;
  }

  /**
   * Get the crossover strategy. This is not really used.
   *
   * @return
   */
  private CrossoverStrategy getCrossoverStrategy() {

    if (this.crossoverStrategy == null) {
      this.crossoverStrategy =
          new CrossoverStrategyFactory(this.getSearchOperatorConfiguration()).getStrategy();
    }

    return this.crossoverStrategy;
  }
  
  private RepairStrategy getRepairStrategy() {
    if (this.repairStrategy == null) {
      this.repairStrategy =
          new RepairStrategyFactory(this.getSearchOperatorConfiguration()).getStrategy();
    }
    return this.repairStrategy;
  }

  /**
   * Parameter object for mutation strategies.
   *
   * @return
   */
  public SearchOperatorConfiguration getSearchOperatorConfiguration() {

    if (this.searchOperatorConfiguration == null) {
      this.searchOperatorConfiguration =
          new SearchOperatorConfiguration(
              this.henshinExecutor,
              this.searchSpecification,
              this.algorithmStepInstrumenter,
              this.problem);
    }

    return this.searchOperatorConfiguration;
  }

  /**
   * This will produce a lazy iteration of possible initial solutions
   *
   * @return list of initial list of models
   */
  public Iterator<Solution> getInitialSolutions() {
    return initialModelProvider.initialModels(theMetamodel);
  }

  /**
   * Gets the instance of the configured mopt model
   *
   * @return
   */
  public Optimisation getOptimisationModel() {
    return this.searchSpecification.getOptimisationModel();
  }

  /**
   * Produces two offspring from the two parents provided in the parameter.
   *
   * @param parents a list of two parent models
   * @returns a list of results offspring
   */
  public List<Solution> breed(List<Solution> parents) {
    return this.getCrossoverStrategy().breed(parents);
  }

  /**
   * Produce a new solution from the given one using one of the evolvers defined in the optimisation
   * model.
   *
   * @param model Solution model
   */
  public Solution mutate(Solution model) {
    return this.getMutationStrategy().mutate(model);
  }
  
  public void repair(Solution model) {
    this.getRepairStrategy().repair(model);
  }

  /**
   * Returns the configured set of objective functions.
   *
   * @return a list containing instances of the configured objective functions
   */
  public List<IGuidanceFunction> getObjectiveFunctions() {
    return this.searchSpecification.getObjectiveFunctions();
  }

  /**
   * Returns the configured set of constraint functions.
   *
   * @return a list containing instances of the configured constraint functions
   */
  public List<IGuidanceFunction> getConstraintFunctions() {
    return this.searchSpecification.getConstraintFunctions();
  }
}
