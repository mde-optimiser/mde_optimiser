package uk.ac.kcl.mdeoptimise.rulegen

import org.eclipse.emf.ecore.EPackage
import java.util.Map
import org.eclipse.emf.henshin.model.Module
import java.util.Set
import java.util.List
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper

class RulesGenerator {
	
	Map<String, Set<Module>> modules
	List<Multiplicity> refinedMultiplicities
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	
	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities){
		this.modules = modules;
		this.refinedMultiplicities = refinedMultiplicities;
		this.refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, refinedMultiplicities)
	}
	
	
	def void applyRefinements() {
		
		refinedMultiplicities.forEach[ multiplicity |
			generateCreateRules(multiplicity, refinedMetamodelWrapper)
		]
	}
	
	def void generateCreateRules(Multiplicity multiplicity, RefinedMetamodelWrapper refinedMetamodelWrapper){
		
		var reference = refinedMetamodelWrapper.getEdge(multiplicity);
		
		if(multiplicity.lower == 0 && multiplicity.upper == -1){
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				return;
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1) {
				
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1) {
				
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				
			}
			
		}
		
		if(multiplicity.lower == 0 && multiplicity.upper == 1){
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				return;
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1) {
				
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1) {
				
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper == -1){
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				return;
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1){
				
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper == 1){
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				return;
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1){
				
			}
		}
		
		if(multiplicity.lower > 1 && multiplicity.upper > 1) {
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				return;
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1){
				
			}
		}
	}
	
	def void generateDeleteRules(Multiplicity multiplicity){
		
	}
	
	def void generateAddEdgeRules(Multiplicity multiplicity){
		
	}
	
	def void generateRemoveEdgeRules(Multiplicity multiplicity){
		
	}
}