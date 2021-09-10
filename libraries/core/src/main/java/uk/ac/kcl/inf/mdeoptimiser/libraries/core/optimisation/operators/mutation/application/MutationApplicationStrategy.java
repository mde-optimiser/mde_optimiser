package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.application;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public interface MutationApplicationStrategy {

  Solution mutate(Solution model);
}
