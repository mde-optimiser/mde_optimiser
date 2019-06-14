package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators;

import java.util.List;
import org.eclipse.emf.henshin.model.Unit;

public interface IOperatorsProvider {

  List<Unit> getMutationOperators();

  List<Unit> getCrossoverOperators();
}
