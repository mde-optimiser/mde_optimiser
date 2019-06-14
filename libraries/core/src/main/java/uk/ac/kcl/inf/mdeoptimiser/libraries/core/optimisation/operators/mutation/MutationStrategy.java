package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface MutationStrategy {

  Solution mutate(Solution model);
}
