package uk.ac.kcl.interpreter.objectives.ocl

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.OCL
import org.eclipse.ocl.ParserException
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.ocl.helper.OCLHelper
import uk.ac.kcl.interpreter.IFitnessFunction
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec

class OclFitnessFunction implements IFitnessFunction {
	
	private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl
   	private OCLHelper<EClassifier, ?, ?, Constraint> oclHelper
   	private ObjectiveInterpreterSpec objectiveInterpreterSpec
	private String objectiveName;
	
	new(OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl, 
		OCLHelper<EClassifier, ?, ?, Constraint> oclHelper,
		ObjectiveInterpreterSpec objectiveInterpreterSpec
	){
		this.ocl = ocl;
		this.oclHelper = oclHelper;
		this.objectiveInterpreterSpec = objectiveInterpreterSpec
		this.objectiveName = objectiveInterpreterSpec.objectiveName
	}
	
	
	override computeFitness(EObject model) {
		
		var fitness = 0.0
        
        try {
        	
			oclHelper.setContext(model.eClass)
        	
        	val oclQueryExpression = oclHelper.createQuery(objectiveInterpreterSpec.getObjectiveSpec);
        	
        	fitness = getNumericFitnessValue(ocl.evaluate(model, oclQueryExpression))
        	        
        } catch(ParserException exception){
        	
        	//Poor man's logger
        	//exception.printStackTrace
        	
        	//TODO logger
        	throw exception
        }
        
      return fitness;
      
	}
	
	def double getNumericFitnessValue(Object fitnessValue){
		
		if(fitnessValue instanceof Integer){
			return 1.0d * fitnessValue as Integer
		} else if (fitnessValue instanceof Float) {
			return 1.0d * fitnessValue as Float
		} else if (fitnessValue instanceof Double) {
			return 1.0d * fitnessValue as Double
		} else if (fitnessValue instanceof Long) {
			return 1.0d * fitnessValue as Long
		}
	}
	
	override getName() {
		this.objectiveName
	}
	
}