package uk.ac.kcl.mdeoptimise.rulegen

import java.util.ArrayList
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Module
import uk.ac.kcl.mdeoptimise.rulegen.generator.IRuleGenerationCommand
import uk.ac.kcl.mdeoptimise.rulegen.generator.commands.node.RuleGenerationCommandFactory
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.RepairSpec
import uk.ac.kcl.mdeoptimise.rulegen.generator.specs.SpecsGenerator
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.MetamodelWrapper
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.Multiplicity
import uk.ac.kcl.mdeoptimise.rulegen.metamodel.RuleSpec
import org.sidiff.serge.util.RuleSemanticsChecker
import org.sidiff.common.henshin.HenshinModuleAnalysis
import org.eclipse.emf.henshin.model.Rule

class RulesGenerator {
	
	Map<String, Set<Module>> modules
	List<Multiplicity> refinedMultiplicities

	EPackage metamodel;
	List<RuleSpec> ruleSpecs;
	
	private SpecsGenerator specsGenerator;
	private RuleGenerationCommandFactory ruleGenerationCommandFactory;
	
	
	//TODO The metamodel should ideally be a list as there are some cases where this is requested
	new(EPackage metamodel, List<Multiplicity> refinedMultiplicities, List<RuleSpec> rulegenSpecs){
		this.refinedMultiplicities = refinedMultiplicities;
		this.metamodel = metamodel;
		this.ruleSpecs = rulegenSpecs;
		this.specsGenerator = new SpecsGenerator();
	}
	
	def RuleGenerationCommandFactory getRuleGenerationCommandFactory(){
		
		if(this.ruleGenerationCommandFactory === null){
			this.ruleGenerationCommandFactory = new RuleGenerationCommandFactory()
		}
		
		return this.ruleGenerationCommandFactory;
	}
	
	def Map<EPackage, List<Module>> generateRules(){
		
		val generatedRules = new HashMap<EPackage, List<Module>>();
		
		val generatedRulesListProblem = new ArrayList<Module>();
		
		val generatedRulesListSolution = new ArrayList<Module>();
		
		val problemMetamodelWrapper = new MetamodelWrapper(this.metamodel)
		
		//Get each of the rule generation specs specs
		ruleSpecs.forEach[ruleSpec |
			
			//Generate the set of repair spec combinations
			var repairSpecs = this.specsGenerator.getRepairsForRuleSpec(ruleSpec, problemMetamodelWrapper)
			
			//Generate the rules for those combinations
			generatedRulesListProblem.addAll(this.generateSpecRules(ruleSpec, repairSpecs, problemMetamodelWrapper))
		]	
		
		if(this.refinedMultiplicities.length > 0) {
			val solutionMetamodelWrapper = new MetamodelWrapper(this.metamodel, this.refinedMultiplicities)
			
			//Get each of the rule generation specs specs
			ruleSpecs.forEach[ruleSpec |
				
				//Generate the set of repair spec combinations
				var repairSpecs = this.specsGenerator.getRepairsForRuleSpec(ruleSpec, solutionMetamodelWrapper)
				
				//Generate the rules for those combinations
				generatedRulesListSolution.addAll(this.generateSpecRules(ruleSpec, repairSpecs, solutionMetamodelWrapper))
			]
		}
		
		//TODO reverse metamodel multiplicities to original one
		
		generatedRules.put(this.metamodel, removeDupplicateRules(generatedRulesListProblem, generatedRulesListSolution));		
		
		return generatedRules;
	}
	
	private def List<Module> removeDupplicateRules(List<Module> problemRules, List<Module> solutionRules){
		
		val uniqueRules = new ArrayList<Module>();
		
		solutionRules.forEach[module | 
			
			if(!isRuleInCollection(module, uniqueRules)){
				uniqueRules.add(module)
			}
		]	
		
		problemRules.forEach[module |
			
			if(!isRuleInCollection(module, uniqueRules)){
				uniqueRules.add(module)
			}
		]
		
		return uniqueRules
	}
	
	
	private def boolean isRuleInCollection(Module rule, List<Module> uniqueRules) {
		
		return uniqueRules.filter[module | 
				var leftRule = HenshinModuleAnalysis.getAllRules(rule).get(0);
				var rightRule = HenshinModuleAnalysis.getAllRules(module).get(0);
			
				var checker = new RuleSemanticsChecker(leftRule, rightRule);
				
				return checker.isEqual()
		].empty == false
	}
	
	private def List<Module> runRuleGenerationCommands(List<IRuleGenerationCommand> rulesGenerationList){
		
		val generatedRules = new ArrayList<Module>();
		
		rulesGenerationList.forEach[command |
			generatedRules.add(command.generate)
		];
		
		return generatedRules;
	}
	
	private def List<Module> generateSpecRules(RuleSpec ruleSpec, Map<String, Set<List<RepairSpec>>> mappedRepairSpecs, MetamodelWrapper metamodelWrapper) {

		val rulesGenerationList = new ArrayList<IRuleGenerationCommand>();

		mappedRepairSpecs.forEach[repairType, repairSpecsSet |
			
			//For each repair spec type, generate rules for all the combinations
			repairSpecsSet.forEach[repairSpecs |
				
				//for each repair spec, pick a command from the factory and add it to the command executor
				rulesGenerationList.addAll(this.getRuleGenerationCommandFactory().makeCommand(repairType, metamodelWrapper, ruleSpec, repairSpecs))
			]
		]
			
		//Execute the rule generation commands
		return this.runRuleGenerationCommands(rulesGenerationList);
	}
}