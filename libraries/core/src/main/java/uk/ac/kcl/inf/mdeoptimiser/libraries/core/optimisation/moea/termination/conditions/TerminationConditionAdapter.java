package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import org.moeaframework.core.TerminationCondition;
import org.moeaframework.core.termination.CompoundTerminationCondition;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec;
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration;

public class TerminationConditionAdapter {

  TerminationCondition terminationCondition;
  SolverSpec solverSpec;

  MDEOHyperparametersConfiguration hyperparameterConfiguration;

  public TerminationConditionAdapter(SolverSpec solverSpec) {
    this.solverSpec = solverSpec;
  }

  public TerminationConditionAdapter(MDEOHyperparametersConfiguration hyperparameterConfiguration) {
    this.hyperparameterConfiguration = hyperparameterConfiguration;
  }

  public TerminationCondition getCondition() {

    if (this.terminationCondition == null) {

      var terminationConditions = new ArrayList<TerminationCondition>();

      if (this.hyperparameterConfiguration == null) {

        if (solverSpec.getTerminationCondition().getParameters().isEmpty()) {
          throw new InvalidParameterException("No termination condition parameters found.");
        }

        this.solverSpec
            .getTerminationCondition()
            .getParameters()
            .forEach(
                parameter -> {
                  var terminationCondition =
                      TerminationConditionFactory.getTerminationCondition(parameter, solverSpec);

                  if (terminationCondition != null) terminationConditions.add(terminationCondition);
                });

        if (terminationConditions.isEmpty()) {
          throw new InvalidParameterException("No known termination condition parameters found.");
        }
      } else {

        // Return the termination condition found by Arbiter
        terminationConditions.add(hyperparameterConfiguration.getEvolutionsTerminationCondition());
      }

      this.terminationCondition =
          new CompoundTerminationCondition(
              terminationConditions.toArray(
                  new TerminationCondition[terminationConditions.size()]));
    }

    return this.terminationCondition;
  }
}
