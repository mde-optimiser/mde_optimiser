package uk.ac.kcl.interpreter

import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EClassifier
import org.eclipse.emf.ecore.EObject
import org.eclipse.ocl.OCL
import org.eclipse.ocl.ParserException
import org.eclipse.ocl.ecore.Constraint
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory
import org.eclipse.ocl.helper.OCLHelper
import uk.ac.kcl.mdeoptimise.OclInterpreterSpec

class OclFitnessFunction implements FitnessFunction {
	
	private String objectiveName;
	private String oclQuery
	private String objectiveType
	
	private OCL<?, EClassifier, ?, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject>  ocl
   	private OCLHelper<EClassifier, ?, ?, Constraint> oclHelper
	
	
	new(OclInterpreterSpec objective){
		
		this.objectiveName = objective.objectiveName
		this.oclQuery = objective.oclQuery
		this.objectiveType = objective.getObjectiveType
				ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE)
		oclHelper = ocl.createOCLHelper();
	}
	
	/**
	 * Instantiate a function using the given DSL ocl identifiers.
	 */
	new (String objectiveName, String oclQuery, String objectiveType){
		this.objectiveName = objectiveName
		this.oclQuery = oclQuery
		this.objectiveType = objectiveType
		ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE)
		oclHelper = ocl.createOCLHelper();
	}
	
	override computeFitness(EObject model) {
		
		oclHelper.setContext(model.eClass);
		var fitness = 0.0
        try {
        	fitness = 1.0d * ocl.evaluate(model, oclHelper.createQuery(oclQuery)) as Integer;
        } catch(ParserException exception){
        	exception.printStackTrace();
        }
         System.out.println(fitness)
      return fitness;
	}
	
}