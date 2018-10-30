package uk.ac.kcl.interpreter.guidance

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import java.util.LinkedList
import java.util.List

class Solution {
	
	EObject model;
	LinkedList<String> transformationsChain;
	
	new(EObject model) {
		this(model, new LinkedList<String>)
	}
	
	new(EObject model, List<String> transformations){
		this.model = model;
		this.transformationsChain = new LinkedList<String>()
	}
	
	new(Solution solution) {
		this.model = EcoreUtil.copy(solution.getModel)
		this.transformationsChain = new LinkedList<String>(solution.getTransformationsChain);
	}
	
	def getModel() {
		return model;
	}
	
	def setModel(EObject model, List<String> transformations){
		this.model = model;
	}
	
	def updateModel(EObject model, String transformation){
		
		if(this.model === null){
			throw new Exception("Trying to update the model on a solution with a null model.")
		}
		
		this.model = model;
		this.transformationsChain.add(transformation)
	}
	
	def addTransformation(String match){
		this.transformationsChain.add(match)
	}
	
	def getTransformationsChain(){
		return this.transformationsChain;
	}
}