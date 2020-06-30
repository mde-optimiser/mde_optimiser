package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.termination.condition;

import java.util.List;
import org.deeplearning4j.arbiter.optimize.api.termination.MaxCandidatesCondition;
import org.deeplearning4j.arbiter.optimize.api.termination.TerminationCondition;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ParameterSearchSpec;

public class TerminationConditionFactory {

  static TerminationConditionFactory factoryInstance;

  private TerminationConditionFactory() {}

  public static synchronized TerminationConditionFactory getINSTANCE() {

    if (factoryInstance == null) {
      factoryInstance = new TerminationConditionFactory();
    }

    return factoryInstance;
  }

  // TODO Make this a proper factory. What other termination conditions can we support
  // Not sure about the time limit one?
  public List<TerminationCondition> get(ParameterSearchSpec parameterSearchSpec) {

    var terminationConditionLimit = getCandidatesTerminationCondition(parameterSearchSpec);
    return List.of(new MaxCandidatesCondition(terminationConditionLimit));
  }

  /**
   * Get the configured algorithm algorithm name to use for generating parameter values.
   *
   * @return algorithm name
   */
  private int getCandidatesTerminationCondition(ParameterSearchSpec spec) {
    var algorithmParameter = getParameter(spec, "candidates");
    // These are just strategy names: random (should add grid, smac etc)
    return Integer.parseInt(algorithmParameter.getValue().getNumeric());
  }

  private Parameter getParameter(ParameterSearchSpec parameterSearchSpec, String keyName) {
    return parameterSearchSpec.getParameters().stream()
        .filter(parameter -> parameter.getName().equals(keyName))
        .findFirst()
        .get();
  }
}
