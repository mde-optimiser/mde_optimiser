package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.ocl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class OclGuidanceFunction implements IGuidanceFunction {

  OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
  OCLHelper<EClassifier, ?, ?, Constraint> oclHelper;
  GuidanceFunctionAdapter guidanceFunctionAdapter;
  String objectiveName;
  OCLExpression<EClassifier> oclQueryExpression;

  public OclGuidanceFunction(
      OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl,
      OCLHelper<EClassifier, ?, ?, Constraint> oclHelper,
      GuidanceFunctionAdapter guidanceFunctionAdapter) {
    this.ocl = ocl;
    this.oclHelper = oclHelper;
    this.guidanceFunctionAdapter = guidanceFunctionAdapter;
    this.objectiveName = guidanceFunctionAdapter.functionName;
  }

  public double computeFitness(Solution solution) {

    var fitness = 0.0;

    try {

      if (oclQueryExpression == null) {
        oclHelper.setContext(solution.getModel().eClass());
        oclQueryExpression = oclHelper.createQuery(this.guidanceFunctionAdapter.functionSpec);
      }

      fitness = getNumericFitnessValue(ocl.evaluate(solution.getModel(), oclQueryExpression));

    } catch (ParserException exception) {
      // TODO logger
      exception.printStackTrace();
      throw new RuntimeException(
          "Parser exception encountered when trying to parse OCL guidance function: "
              + this.objectiveName);
    }

    return fitness;
  }

  public double getNumericFitnessValue(Object fitnessValue) {

    if (fitnessValue instanceof Integer) {
      return 1.0d * (Integer) fitnessValue;
    } else if (fitnessValue instanceof Float) {
      return 1.0d * (Float) fitnessValue;
    } else if (fitnessValue instanceof Double) {
      return 1.0d * (Double) fitnessValue;
    } else if (fitnessValue instanceof Long) {
      return 1.0d * (Long) fitnessValue;
    }

    return 0d;
  }

  public String getName() {
    return this.objectiveName;
  }
}
