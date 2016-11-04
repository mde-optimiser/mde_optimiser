package uk.ac.kcl.interpreter.objectives.ocl

import uk.ac.kcl.interpreter.IObjectiveEngine
import org.eclipse.ocl.OCL
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.helper.OCLHelper
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory

class OclObjectiveEngine implements IObjectiveEngine {
	
	private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  oclInterpreter
   	private OCLHelper<EClassifier, ?, ?, Constraint> oclHelper
	
	new(){
		oclInterpreter = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE)
		oclHelper = oclInterpreter.createOCLHelper();
	}
	
	/**
	 * Type - minimise / maximise
	 * name - objective name
	 * objectiveString - string to be converted to fitness function
	 * 
	 */
	override loadObjective(String type, String name, String objectiveString) {
		return new OclFitnessFunction(oclInterpreter, oclHelper)
	}
}
