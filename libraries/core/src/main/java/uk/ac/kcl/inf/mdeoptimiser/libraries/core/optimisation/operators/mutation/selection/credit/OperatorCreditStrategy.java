package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection.credit;

import java.util.Map;
import org.eclipse.emf.henshin.model.Unit;

public interface OperatorCreditStrategy {

  Map<Unit, Double> calculateOperatorCredits();
}
