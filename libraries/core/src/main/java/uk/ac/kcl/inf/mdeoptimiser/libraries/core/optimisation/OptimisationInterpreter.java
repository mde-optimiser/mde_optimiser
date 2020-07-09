package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation;

import org.eclipse.core.runtime.Path;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor.SolutionGenerator;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaFrameworkAlgorithmConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.SearchResult;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification.ISearchSpecification;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.specification.SearchSpecification;

public class OptimisationInterpreter {

  ISearchSpecification searchSpecification;
  SolutionGenerator solutionGenerator;

  public OptimisationInterpreter(String projectPath, Optimisation model) {
    this.searchSpecification = new SearchSpecification(new Path(projectPath), model);
  }

  public SearchResult start() {

    // This model provider loads the model given by the user in the DSL
    var algorithmConfiguration =
        new MoeaFrameworkAlgorithmConfiguration(
            searchSpecification.getOptimisationModel().getSolver(), this.getSolutionGenerator());

    return this.start(algorithmConfiguration);
  }

  public SearchResult start(MoeaFrameworkAlgorithmConfiguration algorithmConfiguration) {
    var moeaOptimisation = new MoeaOptimisation();
    return moeaOptimisation.execute(algorithmConfiguration);
  }

  public SolutionGenerator getSolutionGenerator() {

    if (this.solutionGenerator == null) {
      this.solutionGenerator = new SolutionGenerator(this.searchSpecification);
    }

    return this.solutionGenerator;
  }

  public ISearchSpecification getSearchSpecification() {
    return this.searchSpecification;
  }
}
