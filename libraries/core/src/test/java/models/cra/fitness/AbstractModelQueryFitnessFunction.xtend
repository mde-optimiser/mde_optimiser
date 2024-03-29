//package models.cra.fitness
//
//import org.eclipse.emf.common.util.BasicEList
//import org.eclipse.emf.ecore.EObject
//import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction
//
//abstract class AbstractModelQueryFitnessFunction implements IGuidanceFunction {
//	/**
//	 * Helper function getting the value of the named feature (if it exists) for the given EObject.
//	 */
//	def Object getFeature (EObject o, String feature) {
//		
//		if(o === null){
//			println("Null object given")
//		}
//		
//		o.eGet (o.eClass.getEStructuralFeature(feature))
//		
//	}
//	
//	/**
//	 * Helper method returning true if the given EObject is an instance of the named EClass.
//	 */
//	def boolean isOfClass (EObject o, String className) {
//		o.eClass.name.equals (className)
//	}
//	
//	/**
//	 * Helper method which returns the named encapsulated features for a class.
//	 */
//	def Iterable<EObject> getClassFeatures(EObject classObject, String element){	
//				
//		val object = (classObject.getFeature("encapsulates"))
//		var features = new BasicEList<EObject>();
//			
//		if(object instanceof EObject){
//					features.add(object)
//		} else {
//			features = object as BasicEList<EObject>;
//		}
//		
//		features.filter[ feature | feature.eClass.name.equals (element)] 
//	}
//}
