package uk.ac.kcl.interpreter.objectives.ocl

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.OCL
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory
import org.eclipse.ocl.helper.OCLHelper
import uk.ac.kcl.interpreter.objectives.IObjectiveEngine
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec

class OclObjectiveEngine implements IObjectiveEngine {
	
	private final static OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  oclInterpreter = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE)
   	private final static OCLHelper<EClassifier, ?, ?, Constraint> oclHelper = oclInterpreter.createOCLHelper();
	
	/**
	 * Type - minimise / maximise
	 * name - objective name
	 * objectiveString - string to be converted to fitness function
	 * 
	 */
	override loadObjective(ObjectiveInterpreterSpec objectiveInterpreter) {
		return new OclFitnessFunction(oclInterpreter, oclHelper, objectiveInterpreter)
	}
}
