package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

/** This interface defines the contract for fitness functions. */
public interface IGuidanceFunction {
  /** Return the fitness of the given model. Higher fitness values are better. */
  public double computeFitness(Solution model);

  public String getName();
}
