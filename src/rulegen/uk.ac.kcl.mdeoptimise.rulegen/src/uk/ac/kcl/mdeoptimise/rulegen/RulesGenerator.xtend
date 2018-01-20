package uk.ac.kcl.mdeoptimise.rulegen

import org.eclipse.emf.ecore.EPackage
import java.util.Map
import org.eclipse.emf.henshin.model.Module
import java.util.Set
import java.util.List
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import java.util.ArrayList
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.CreateNodeRuleCommand
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import java.util.Stack
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.DeleteNodeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.AddEdgeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.RemoveEdgeRuleCommand

class RulesGenerator {
	
	Map<String, Set<Module>> modules
	List<Multiplicity> refinedMultiplicities
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	EPackage metamodel;
	private List<IRuleGenerationCommand> rulesGenerationList;
	private EClassifierInfoManagement metamodelAnalyser;
	
	//TODO The metamodel should ideally be a list as there are some cases where this is requested
	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities){
		this.modules = modules;
		this.refinedMultiplicities = refinedMultiplicities;
		this.metamodel = metamodel;
		this.refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, refinedMultiplicities)
		this.rulesGenerationList = new ArrayList<IRuleGenerationCommand>();
	}
	
	def void analyseMetamodel(){
		val metamodels = new Stack<EPackage>();
		metamodels.add(this.metamodel)
		
		//The boolean does not do anything here
		//TODO Not really needing this as a singleton
		metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels);
	}
	
	def void applyRefinements() {
		
		refinedMultiplicities.forEach[ multiplicity |
			generateRules(multiplicity, refinedMetamodelWrapper, metamodelAnalyser)
		]
	}
	
	//TODO Perhaps not the best name?
	def void generate(IRuleGenerationCommand command){
		rulesGenerationList.add(command);
	}
	
	//TODO also perhaps not the best name
	def void generateRules(Multiplicity multiplicity, 
		RefinedMetamodelWrapper refinedMetamodelWrapper, 
		EClassifierInfoManagement metamodelAnalyser
	){
		
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
				
				//TODO: Add a logger
				println("Case 1")
				
				//Create node A and connect it to its mandatory neighbours (containment edges)
				this.generate(new CreateNodeRuleCommand(multiplicity, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser))
				
				//Delete node A and delete its edges from all its neighbours
				this.generate(new DeleteNodeRuleCommand(multiplicity, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser))
				
				//Create edge of type e from node A to node B 
				this.generate(new AddEdgeRuleCommand(multiplicity, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser))
				
				//Create edge of type e from node A to node B by deleting existing edge
				this.generate(new RemoveEdgeRuleCommand(multiplicity, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser))
				
				//TODO
				//this.generate(new ChangeEdgeRuleCommand(multiplicity, refinedMetamodelWrapper.refinedMetamodel, metamodelAnalyser)
		
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