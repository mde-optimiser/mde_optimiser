package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin;

import org.eclipse.emf.henshin.interpreter.ApplicationMonitor;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.InterpreterFactory;
import org.eclipse.emf.henshin.interpreter.impl.AssignmentImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;

public class MdeoUnitApplicationImpl extends UnitApplicationImpl {

  public MdeoUnitApplicationImpl(Engine engine) {
    super(engine);
  }

  @Override
  public boolean execute(ApplicationMonitor monitor) {

    ApplicationMonitor applicationMonitor;

    if (monitor == null) {
      applicationMonitor = InterpreterFactory.INSTANCE.createApplicationMonitor();
    } else {
      applicationMonitor = monitor;
    }

    appliedRules.clear();
    undoneRules.clear();

    if (assignment != null) {
      assignment = new AssignmentImpl(assignment, true);
    } else {
      assignment = new AssignmentImpl(unit, true);
    }

    return doExecute(applicationMonitor);
  }
}
