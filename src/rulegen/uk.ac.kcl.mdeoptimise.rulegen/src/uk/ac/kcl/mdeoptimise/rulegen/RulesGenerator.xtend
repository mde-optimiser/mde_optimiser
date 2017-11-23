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
			generateRules(multiplicity, refinedMetamodelWrapper)
		]
	}
	
	def void generateRules(Multiplicity multiplicity, RefinedMetamodelWrapper refinedMetamodelWrapper){
		
		var reference = refinedMetamodelWrapper.getEdge(multiplicity);
		
		if(multiplicity.lower == 0 && multiplicity.upper == -1){
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case 6")
				println("A 0..*")
				println("B 0..*")
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case 5")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case 8")	
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case 7")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("0..* m..n case")
			}
			
		}
		
		if(multiplicity.lower == 0 && multiplicity.upper == 1){
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case 2")
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case 1")
				println("A node name: " + reference.EContainingClass.name)
				println("B node name: " + reference.EOpposite.EContainingClass.name)
				println("A 0..1 B 0..1 case")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1) {
				println("Case 4")
				println("A 0..1 B 1..* case")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1) {
				println("Case 3")
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("0..1 m..n case")
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper == -1){
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case 14")
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case 13")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case 16")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case 15")
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1){
				println("0..1 m..n case")
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper == 1){
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case 10")
			}
			
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case 9")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case 12")
			}
			
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case 11")
			}
			
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1){
				println("1..1 m..n case")
			}
		}
	}
}