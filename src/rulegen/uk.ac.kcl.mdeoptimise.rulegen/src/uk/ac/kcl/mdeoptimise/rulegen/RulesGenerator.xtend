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
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		//TODO MultiplicityEOpposite is null in the case of a unidirectional reference, so this 
		//is not going to work without some extra checks.
		if(multiplicity.lower == 0 && multiplicity.upper == 1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper == -1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper == 1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		if(multiplicity.lower == 0 && multiplicity.upper > 1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		if(multiplicity.lower == 1 && multiplicity.upper > 1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		if(multiplicity.lower > 1 && multiplicity.upper > 1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
		if(multiplicity.lower > 1 && multiplicity.upper == -1){
			
			//Unidirectional references case
			if(reference.EOpposite == null){
				//Add cases where EOpposite is null and we have a unidirectional reference
			}
			
			//0..* case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == -1){	
				println("Case ")
			}
			
			//0..1 case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//0..m case
			if(reference.EOpposite.lowerBound == 0 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//1..* case
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == -1){
				println("Case ")	
			}
			
			//1..1
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound == 1){
				println("Case ")
			}
			
			//1..m
			if(reference.EOpposite.lowerBound == 1 && reference.EOpposite.upperBound > 1){
				println("Case ")
			}
			
			//n..m case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound > 1) {
				println("Case ")
			}
			
			//n..* case
			if(reference.EOpposite.lowerBound > 1 && reference.EOpposite.upperBound == -1) {
				println("Case ")
			}
		}
		
	}
}