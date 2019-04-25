package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.ocl

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.OCL
import org.eclipse.ocl.ParserException
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.ocl.expressions.OCLExpression
import org.eclipse.ocl.helper.OCLHelper
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.GuidanceFunctionAdapter
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class OclGuidanceFunction implements IGuidanceFunction {
	
	OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl
   	OCLHelper<EClassifier, ?, ?, Constraint> oclHelper
   	GuidanceFunctionAdapter guidanceFunctionAdapter
	String objectiveName;
	OCLExpression<EClassifier> oclQueryExpression;
	
	new(OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl, 
		OCLHelper<EClassifier, ?, ?, Constraint> oclHelper,
		GuidanceFunctionAdapter guidanceFunctionAdapter
	){
		this.ocl = ocl;
		this.oclHelper = oclHelper;
		this.guidanceFunctionAdapter = guidanceFunctionAdapter
		this.objectiveName = guidanceFunctionAdapter.functionName
	}
	
	override computeFitness(Solution solution) {
		
		var fitness = 0.0
		
		if(guidanceFunctionAdapter.isObjectiveFunction) {
			fitness *= guidanceFunctionAdapter.numericalTendency
		}
		
        try {
 
        	if(oclQueryExpression === null){
        		oclHelper.setContext(solution.model.eClass)
        		oclQueryExpression = oclHelper.createQuery(guidanceFunctionAdapter.functionSpec)
        	}
        	
        	fitness = getNumericFitnessValue(ocl.evaluate(solution.model, oclQueryExpression))
        	        
        } catch(ParserException exception){
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
