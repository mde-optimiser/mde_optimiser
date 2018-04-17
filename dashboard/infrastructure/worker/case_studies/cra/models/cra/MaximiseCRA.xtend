package models.cra

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.EList

class MaximiseCRA extends AbstractModelQueryFitnessFunction {

	override double computeFitness(EObject model) {
		val cohesion = calculateCohesionRatio(model);
		val coupling = calculateCouplingRatio(model);

		println("Calculated CRA : " + (cohesion - coupling))

		return (cohesion - coupling) * -1
	}

	def double calculateCohesionRatio(EObject classModel) {
		
		var cohesionRatio = 0.0;
		
		for(EObject classObject : classModel.getFeature("classes") as EList<EObject>) {
			
			val classObjectMethods = classObject.getClassFeatures("Method")
			val classObjectAttributes = classObject.getClassFeatures("Attribute")
			
			if(classObjectMethods.size == 0) {
				
				cohesionRatio += 0.0
				
			} else if (classObjectMethods.size == 1) {
				if(classObjectAttributes.size == 0){
					cohesionRatio += 0.0
				} else {
					val mai = mai(classObject, classObject)
					cohesionRatio += mai / (classObjectMethods.size * classObjectAttributes.size) as double
				}
			} else {
				if(classObjectAttributes.size == 0) {
					val mmi = mmi(classObject, classObject)
					cohesionRatio += mmi / (classObjectMethods.size * (classObjectMethods.size - 1)) as double
				} else {
					val mai = mai(classObject, classObject)
					val mmi = mmi(classObject, classObject)
					val maCoupling = (classObjectMethods.size * classObjectAttributes.size) as double
					val mmCoupling = (classObjectMethods.size * (classObjectMethods.size - 1)) as double
					
					cohesionRatio += mai / maCoupling + mmi / mmCoupling
				}
			}
		}
		
		cohesionRatio
	}

	def double calculateCouplingRatio(EObject classModel) {
		
		var couplingRatio = 0.0
		
		val classModelClasses = classModel.getFeature("classes") as EList<EObject>;
		
		for(EObject classSource : classModelClasses){
			couplingRatio += calculateCouplingRatio(classSource, classModel)
		}
		
		couplingRatio
	}

	def double calculateCouplingRatio(EObject classSource, EObject classModel) {
		
		var couplingRatio = 0.0
		val sourceClassMethods = classSource.getClassFeatures("Method")
		
		for(EObject classTarget : classModel.getFeature("classes") as EList<EObject>) {
			
			if(classSource != classTarget) {		
				
				val targetClassMethods = classTarget.getClassFeatures("Method")
				val targetClassAttributes = classTarget.getClassFeatures("Attribute")
				
				if(sourceClassMethods.size == 0) {
					couplingRatio += 0.0
				} else {
					
					if(targetClassMethods.size <= 1) {
						
						if(targetClassAttributes.size == 0) {
							couplingRatio += 0.0
						} else {
							couplingRatio += mai(classSource, classTarget) / (sourceClassMethods.size * targetClassAttributes.size) as double
						}
						
					} else {
						
						if(targetClassAttributes.size == 0){
							couplingRatio += mmi(classSource, classTarget) / (sourceClassMethods.size * (targetClassMethods.size -1)) as double
							
						} else {
							//XTend slows down considerably if this formula is in line
							val mai = mai(classSource, classTarget)
							val mmi = mmi(classSource, classTarget)
							
							val maCoupling = (sourceClassMethods.size * targetClassAttributes.size) as double
							val mmCoupling = (sourceClassMethods.size * (targetClassMethods.size -1)) as double
							
							if(maCoupling > 0 && mmCoupling > 0) {
								couplingRatio += mai / maCoupling + mmi / mmCoupling
							}
						}
					}
				}
			}
			
		}
		couplingRatio 
	}

	def mai(EObject classSource, EObject classTarget) {
	
		var mai = 0
	
		for(EObject method : classSource.getClassFeatures("Method")){
			for(EObject attributeTarget : classTarget.getClassFeatures("Attribute")){
				
				val dataDependencies = (method.getFeature("dataDependency") as EList<EObject>)
				
				if(dataDependencies.contains(attributeTarget)){
					mai++
				}
			}
		}
	
		mai
	}
	
	def mmi(EObject classSource, EObject classTarget) {
	
		var mmi = 0
		
		for(EObject methodSource : classSource.getClassFeatures("Method")){
			for(EObject methodTarget : classTarget.getClassFeatures("Method")){
				
				val functionalDependencies = (methodSource.getFeature("functionalDependency") as EList<EObject>);
				
				if(functionalDependencies.contains(methodTarget)){
					mmi++
				}
			}
		}
		
		mmi
	}
	
	override getName() {
		return "Maximise CRA"
	}
	
}
				