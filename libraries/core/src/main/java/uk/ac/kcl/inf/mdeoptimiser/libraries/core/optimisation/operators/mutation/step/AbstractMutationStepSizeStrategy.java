package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.step;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.SearchOperatorConfiguration;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.parameters.AbstractStrategyParameter;

public abstract class AbstractMutationStepSizeStrategy extends AbstractStrategyParameter
    implements MutationStepSizeStrategy {

  public AbstractMutationStepSizeStrategy(SearchOperatorConfiguration searchOperatorConfiguration) {
    super(searchOperatorConfiguration);
  }

  @Override
  public String getStrategyParameterKey() {
    return "mutation.step";
  }
}
