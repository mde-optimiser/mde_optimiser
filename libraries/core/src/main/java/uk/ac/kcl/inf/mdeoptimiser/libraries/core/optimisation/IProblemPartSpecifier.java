package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation;

import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public interface IProblemPartSpecifier {

  Set<EObject> getProblemPart(EPackage metamodel);
}
