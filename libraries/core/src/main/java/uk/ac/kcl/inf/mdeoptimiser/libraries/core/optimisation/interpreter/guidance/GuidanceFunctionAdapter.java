package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance;

import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ConstraintInterpreterSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ObjectiveInterpreterSpec;

public class GuidanceFunctionAdapter {

  public String functionName;
  public String functionTendency;
  public String functionType;
  public String functionSpec;

  public GuidanceFunctionAdapter(ObjectiveInterpreterSpec objectiveInterpreterSpec) {
    functionName = objectiveInterpreterSpec.getObjectiveName();
    functionTendency = objectiveInterpreterSpec.getObjectiveTendency();
    functionType = objectiveInterpreterSpec.getObjectiveType();
    functionSpec = objectiveInterpreterSpec.getObjectiveSpec();
  }

  public GuidanceFunctionAdapter(ConstraintInterpreterSpec constraintInterpreterSpec) {
    functionName = constraintInterpreterSpec.getConstraintName();
    functionType = constraintInterpreterSpec.getConstraintType();
    functionSpec = constraintInterpreterSpec.getConstraintSpec();
  }

  // TODO AB: I don't think this is used and not sure if it has much use
  public double getNumericalTendency() {

    /**
     * Moea is designed to minimise objectives. To maximise an objective, it should be negated.
     * Output maximised objectives will be displayed with their negative values.
     */
    return (this.functionTendency != null && this.functionTendency.equals("minimise")) ? -1 : 1;
  }

  public boolean isObjectiveFunction() {
    return this.functionTendency != null;
  }
}
