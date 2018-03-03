package uk.ac.kcl.mdeoptimise.rulegen

import java.util.ArrayList
import java.util.List
import java.util.Map
import java.util.Set
import java.util.Stack
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Module
import org.sidiff.common.emf.extensions.impl.EClassifierInfoManagement
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RefinedMetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EReference
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node.CreateNodeRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node.CreateNodeIterativeRepairManyRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node.CreateNodeIterativeRepairRuleCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.edge.AddEdgeRuleCommand

class RulesGenerator {
	
	Map<String, Set<Module>> modules
	List<Multiplicity> refinedMultiplicities
	RefinedMetamodelWrapper refinedMetamodelWrapper;
	EPackage metamodel;
	List<RuleSpec> ruleSpecs;
	private List<IRuleGenerationCommand> rulesGenerationList;
	private EClassifierInfoManagement metamodelAnalyser;
	
	//TODO The metamodel should ideally be a list as there are some cases where this is requested
	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities, List<RuleSpec> rulegenSpecs){
		this.modules = modules;
		this.refinedMultiplicities = refinedMultiplicities;
		this.metamodel = metamodel;
		this.ruleSpecs = rulegenSpecs;
		this.refinedMetamodelWrapper = new RefinedMetamodelWrapper(metamodel, refinedMultiplicities)
		this.rulesGenerationList = new ArrayList<IRuleGenerationCommand>();
	}
	
	def void analyseMetamodel(){
		
		val metamodels = new Stack<EPackage>();
		metamodels.add(this.metamodel)
		
		
		metamodelAnalyser = new EClassifierInfoManagement();
		metamodelAnalyser.gatherInformation(false, metamodels);
	}
	
	def List<Module> runRuleGenerationCommands(){
		
		val generatedRules = new ArrayList<Module>();
		
		rulesGenerationList.forEach[command |
			generatedRules.add(command.generate)
		];
		
		return generatedRules;
		
	}
	
	def List<Module> getGeneratedRules(){
		
		val generatedRules = new ArrayList<Module>();
		
		ruleSpecs.forEach[ruleSpec |
			
			
			var node = refinedMetamodelWrapper.getNode(ruleSpec.getNode);
			
			//Generate nodes
			if(ruleSpec.isNode){
				
				generatedRules.addAll(generateNodeRules(node, ruleSpec.actions, this.refinedMetamodelWrapper, this.metamodelAnalyser))
			
			} else {
				var edge = refinedMetamodelWrapper.getEdge(ruleSpec.getNode, ruleSpec.edge)
				generatedRules.addAll(generateEdgeRules(node, edge, ruleSpec.actions, this.refinedMetamodelWrapper, this.metamodelAnalyser))
			}	
		]
		
		return this.runRuleGenerationCommands
	}
	
	//TODO Perhaps not the best name?
	def void generate(IRuleGenerationCommand command){
		rulesGenerationList.add(command);
	}
	
	def List<Module> generateEdgeRules(EClass node, EReference edge, String actions, RefinedMetamodelWrapper refinedMetamodelWrapper, 
		EClassifierInfoManagement metamodelAnalyser){
		
		
		//Generate edge rules for each bidirectional edge from node A
		
		//Generate edge rules for each unidirectional edge from node A
		
		if(actions.equals("ALL")){
			generate(new AddEdgeRuleCommand(node, edge, refinedMetamodelWrapper, metamodelAnalyser))
		}
		
		if(actions.equals("ADD")){
			
		}
		
		if(actions.equals("REMOVE")){
			
		}
	}
	
	def List<Module> generateNodeRules(EClass node, String actions, RefinedMetamodelWrapper refinedMetamodelWrapper, 
		EClassifierInfoManagement metamodelAnalyser){
		
		var references = refinedMetamodelWrapper.getBidirectionalReferences(node);
		
		if(actions.equals("ALL")){
			//Generate create simple
			
			//create basic rule and nac
			generate(new CreateNodeRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser))
			
			if(refinedMetamodelWrapper.hasEdgesForSingleRepair(node)) {
				generate(new CreateNodeIterativeRepairRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser))
				//generate delete
			}
				
			if(refinedMetamodelWrapper.hasEdgesForMultiRepair(node)){
				generate(new CreateNodeIterativeRepairManyRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser))	
				//generate delete
			}
			
			//opposite lb = ub = 1
				//lb repair one
				
			//opposite lb = 1 and ub > 1 but not *
				//lb repair many
			//Generate create lb repair
			//Generate create lb repair many
		}
		
		if(actions.equals("CREATE")){
			
			generate(new CreateNodeRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser))
			
			if(refinedMetamodelWrapper.hasEdgesForSingleRepair(node)) {
				generate(new CreateNodeIterativeRepairRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser))
			}
				
			if(refinedMetamodelWrapper.hasEdgesForMultiRepair(node)){
				generate(new CreateNodeIterativeRepairManyRuleCommand(node, refinedMetamodelWrapper, metamodelAnalyser))	
			}
		}
		
		if(actions.equals("DELETE")){
			//just delete
		}
	}
}