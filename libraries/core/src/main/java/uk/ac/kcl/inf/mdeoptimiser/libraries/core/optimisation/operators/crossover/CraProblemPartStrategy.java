package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.crossover;

import java.util.List;
import java.util.Set;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.henshin.interpreter.Engine;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class CraProblemPartStrategy implements CrossoverStrategy {

  public CraProblemPartStrategy(Set<EReference> problemEdgeTypes, Engine engine) {}

  @Override
  public List<Solution> breed(List<Solution> parents) {
    //		var offspring = new ProblemPartCrossover().evolve(parents.get(0).getModel(),
    // parents.get(1).getModel());
    //		return offspring.stream().map(Solution::new).collect(Collectors.toList());
    return null;
  }
}
