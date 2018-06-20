package models.nrp

import org.eclipse.emf.ecore.EObject
import uk.ac.kcl.interpreter.IGuidanceFunction
import org.eclipse.emf.common.util.BasicEList

class HasValidSolutionBudget  implements IGuidanceFunction {
	
	override computeFitness(EObject model) {
		
		val selectedArtifactsCost =  model.getReferenceFeature("solutions").head
				.getReferenceFeature("selectedArtifacts").fold(0d)[
					result, artifact | 
					
					//This assumes money for now only
					result + artifact.getReferenceFeature("costs").head.getFeature("amount") as Double
				]
		
		println("Calculated selectedArtifacts cost: " + selectedArtifactsCost)
		
		var budget = 2000;
		
		//If within budget
		if(budget - selectedArtifactsCost > 0){
			return 0
		}
		
		return budget - selectedArtifactsCost
		
	}
	
	override getName() {
		return "Minimise Next Release Cost"
	}

	/**
	 * Helper function getting the value of the named feature (if it exists) for the given EObject.
	 */
	def Object getFeature (EObject o, String feature) {
		
		if(o === null){
			println("Null object given")
		}
		
		o.eGet(o.eClass.getEStructuralFeature(feature))
		
	}
	
	def Iterable<EObject> getReferenceFeature(EObject o, String feature) {
		
		val object = (o.getFeature(feature))
		var features = new BasicEList<EObject>();
		
		if(object instanceof EObject) {
					features.add(object)
		} else {
			features = object as BasicEList<EObject>;
		}		
		return features
	}	
}