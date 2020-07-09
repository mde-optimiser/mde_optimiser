package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.moeaframework.Instrumenter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;

public class InstrumenterAnalyser {

  private Instrumenter instrumenter;

  private int currentAlgorithmStep;

  public InstrumenterAnalyser(Instrumenter instrumenter) {
    this.setInstrumenter(instrumenter);
  }

  private void setInstrumenter(Instrumenter instrumenter) {
    this.instrumenter = instrumenter;
  }

  public Instrumenter getInstrumenter() {
    return this.instrumenter;
  }

  /**
   * Get the algorithm population from the last step.
   *
   * @return the population or an empty collection if this is the first step
   */
  public List<MoeaOptimisationSolution> getLastPopulation() {
    var lastAccumulator = this.getInstrumenter().getLastAccumulator();

    if (lastAccumulator != null
        && lastAccumulator.keySet().contains("Population")
        && lastAccumulator.size("Population") > 0) {
      return (ArrayList<MoeaOptimisationSolution>)
          lastAccumulator.get("Population", lastAccumulator.size("Population") - 1);
    }

    return Collections.emptyList();
  }

  public List<MoeaOptimisationSolution> getLastApproximationSet() {

    var lastAccumulator = this.getInstrumenter().getLastAccumulator();

    if (lastAccumulator != null
        && lastAccumulator.keySet().contains("Approximation Set")
        && lastAccumulator.size("Approximation Set") > 0) {
      return (ArrayList<MoeaOptimisationSolution>)
          lastAccumulator.get("Approximation Set", lastAccumulator.size("Approximation Set") - 1);
    }

    return Collections.emptyList();
  }

  /**
   * Get the algorithm approximation set from the previous step.
   *
   * @return the approximation set population or an empty collection if this is the first step
   */
  public List<MoeaOptimisationSolution> getPreviousApproximationSet() {

    var lastAccumulator = this.getInstrumenter().getLastAccumulator();

    if (lastAccumulator != null && lastAccumulator.size("Approximation Set") > 2) {
      return (ArrayList<MoeaOptimisationSolution>)
          lastAccumulator.get("Approximation Set", lastAccumulator.size("Approximation Set") - 2);
    }

    return Collections.emptyList();
  }

  /**
   * Get the current algorithm step by counting the number of keys in the last accumulator
   *
   * @return 0 if the first step, step number otherwise
   */
  public Integer getAlgorithmStep() {

    if (this.getInstrumenter().getLastAccumulator() == null
        || this.getInstrumenter().getLastAccumulator().keySet().isEmpty()) {
      return 0;
    }

    return this.getInstrumenter().getLastAccumulator().size("NFE");
  }
}
