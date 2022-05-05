package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IProblemPartSpecifier;

public class EmptyProblemPartSpecifier implements IProblemPartSpecifier {

  @Override
  public Set<EObject> getProblemPart(EPackage metamodel) {
    return new HashSet<>();
  }
}
