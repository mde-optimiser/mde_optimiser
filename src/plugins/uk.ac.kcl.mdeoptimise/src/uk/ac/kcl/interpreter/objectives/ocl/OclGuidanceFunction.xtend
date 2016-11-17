package uk.ac.kcl.interpreter.objectives.ocl

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.OCL
import org.eclipse.ocl.ParserException
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.ocl.expressions.OCLExpression
import org.eclipse.ocl.helper.OCLHelper
import uk.ac.kcl.interpreter.IGuidanceFunction
import uk.ac.kcl.interpreter.objectives.GuidanceFunctionAdapter

class OclGuidanceFunction implements IGuidanceFunction {
	
	private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl
   	private OCLHelper<EClassifier, ?, ?, Constraint> oclHelper
   	private GuidanceFunctionAdapter guidanceFunctionAdapter
	private String objectiveName;
	private OCLExpression<EClassifier> oclQueryExpression;
	
	new(OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl, 
		OCLHelper<EClassifier, ?, ?, Constraint> oclHelper,
		GuidanceFunctionAdapter guidanceFunctionAdapter
	){
		this.ocl = ocl;
		this.oclHelper = oclHelper;
		this.guidanceFunctionAdapter = guidanceFunctionAdapter
		this.objectiveName = guidanceFunctionAdapter.functionName
	}
	
	
	override computeFitness(EObject model) {
		
		var fitness = 0.0
		
        try {
        	
			oclHelper.setContext(model.eClass)
        	if(oclQueryExpression == null){
        		oclQueryExpression = oclHelper.createQuery(guidanceFunctionAdapter.functionSpec)
        	}
        	//val oclQueryExpression = ;
        	
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