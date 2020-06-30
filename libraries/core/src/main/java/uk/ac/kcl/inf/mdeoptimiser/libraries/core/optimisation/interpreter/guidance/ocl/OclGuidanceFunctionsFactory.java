package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.ocl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.helper.OCLHelper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.IGuidanceFunctionsFactory;

public class OclGuidanceFunctionsFactory implements IGuidanceFunctionsFactory {

  static final OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>
      oclInterpreter = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
  static final OCLHelper<EClassifier, ?, ?, Constraint> oclHelper =
      oclInterpreter.createOCLHelper();

  /**
   * Type - minimise / maximise name - objective name objectiveString - string to be converted to
   * fitness function
   */
  @Override
  public IGuidanceFunction loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter) {
    return new OclGuidanceFunction(oclInterpreter, oclHelper, guidanceFunctionAdapter);
  }
}
