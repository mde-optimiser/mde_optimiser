package uk.ac.kcl.interpreter.objectives.ocl

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.OCL
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory
import org.eclipse.ocl.helper.OCLHelper
import uk.ac.kcl.interpreter.objectives.GuidanceFunctionAdapter
import uk.ac.kcl.interpreter.objectives.IGuidanceFunctionsFactory

class OclGuidanceFunctionsFactory implements IGuidanceFunctionsFactory {
	
	private final static OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  oclInterpreter = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE)
   	private final static OCLHelper<EClassifier, ?, ?, Constraint> oclHelper = oclInterpreter.createOCLHelper();
	
	/**
	 * Type - minimise / maximise
	 * name - objective name
	 * objectiveString - string to be converted to fitness function
	 * 
	 */	
	override loadFunction(GuidanceFunctionAdapter guidanceFunctionAdapter) {
		return new OclGuidanceFunction(oclInterpreter, oclHelper, guidanceFunctionAdapter)
	}
	
}
