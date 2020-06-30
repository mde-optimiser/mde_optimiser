package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators;

import org.moeaframework.Instrumenter;
import org.moeaframework.core.Problem;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation.InstrumenterAnalyser;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters.ParametersLoader;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification.ISearchSpecification;

/**
 * A simple parameter object to simplify the injection of fields in the various parameter selection
 * classes and factories.
 */
public class SearchOperatorConfiguration {

  private final ParametersLoader parametersLoader;
  private HenshinExecutor henshinExecutor;
  private ISearchSpecification searchSpecification;
  private Problem problem;
  private InstrumenterAnalyser instrumenterAnalyser;

  public SearchOperatorConfiguration(
      HenshinExecutor henshinExecutor,
      ISearchSpecification searchSpecification,
      Instrumenter instrumenter,
      Problem problem) {
    this.henshinExecutor = henshinExecutor;
    this.searchSpecification = searchSpecification;
    this.problem = problem;
    this.parametersLoader =
        new ParametersLoader(
            this.searchSpecification
                .getOptimisationModel()
                .getSolver()
                .getAlgorithm()
                .getParameters());
    this.instrumenterAnalyser = new InstrumenterAnalyser(instrumenter);
  }

  public InstrumenterAnalyser getInstrumenterAnalyser() {
    return this.instrumenterAnalyser;
  }

  public HenshinExecutor getHenshinExecutor() {
    return this.henshinExecutor;
  }

  public ISearchSpecification searchSpecification() {
    return this.searchSpecification;
  }

  public Problem getProblem() {
    return this.problem;
  }

  public boolean isMOProblem() {
    return this.problem.getNumberOfObjectives() > 1;
  }

  public ParametersLoader getSearchOperatorParameterInterpreter() {
    return this.parametersLoader;
  }
}
