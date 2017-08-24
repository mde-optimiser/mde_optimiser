/*
 * generated by Xtext 2.10.0
 */
package uk.ac.kcl.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.common.services.TerminalsGrammarAccess;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;

@Singleton
public class MDEOptimiseGrammarAccess extends AbstractGrammarElementFinder {
	
	public class OptimisationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.Optimisation");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cBasepathAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cBasepathBasepathSpecParserRuleCall_0_0 = (RuleCall)cBasepathAssignment_0.eContents().get(0);
		private final Assignment cMetamodelAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cMetamodelMetaModelSpecParserRuleCall_1_0 = (RuleCall)cMetamodelAssignment_1.eContents().get(0);
		private final Assignment cModelAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cModelModelPathSpecParserRuleCall_2_0 = (RuleCall)cModelAssignment_2.eContents().get(0);
		private final Assignment cObjectivesAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cObjectivesObjectiveInterpreterSpecParserRuleCall_3_0 = (RuleCall)cObjectivesAssignment_3.eContents().get(0);
		private final Assignment cConstraintsAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cConstraintsConstraintInterpreterSpecParserRuleCall_4_0 = (RuleCall)cConstraintsAssignment_4.eContents().get(0);
		private final Assignment cEvolversAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cEvolversEvolverSpecParserRuleCall_5_0 = (RuleCall)cEvolversAssignment_5.eContents().get(0);
		private final Assignment cOptimisationAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cOptimisationOptimisationSpecParserRuleCall_6_0 = (RuleCall)cOptimisationAssignment_6.eContents().get(0);
		
		//Optimisation:
		//	basepath=BasepathSpec
		//	metamodel=MetaModelSpec
		//	model=ModelPathSpec
		//	objectives+=ObjectiveInterpreterSpec+
		//	constraints+=ConstraintInterpreterSpec*
		//	evolvers+=EvolverSpec+
		//	optimisation=OptimisationSpec;
		@Override public ParserRule getRule() { return rule; }
		
		//basepath=BasepathSpec metamodel=MetaModelSpec model=ModelPathSpec objectives+=ObjectiveInterpreterSpec+
		//constraints+=ConstraintInterpreterSpec* evolvers+=EvolverSpec+ optimisation=OptimisationSpec
		public Group getGroup() { return cGroup; }
		
		//basepath=BasepathSpec
		public Assignment getBasepathAssignment_0() { return cBasepathAssignment_0; }
		
		//BasepathSpec
		public RuleCall getBasepathBasepathSpecParserRuleCall_0_0() { return cBasepathBasepathSpecParserRuleCall_0_0; }
		
		//metamodel=MetaModelSpec
		public Assignment getMetamodelAssignment_1() { return cMetamodelAssignment_1; }
		
		//MetaModelSpec
		public RuleCall getMetamodelMetaModelSpecParserRuleCall_1_0() { return cMetamodelMetaModelSpecParserRuleCall_1_0; }
		
		//model=ModelPathSpec
		public Assignment getModelAssignment_2() { return cModelAssignment_2; }
		
		//ModelPathSpec
		public RuleCall getModelModelPathSpecParserRuleCall_2_0() { return cModelModelPathSpecParserRuleCall_2_0; }
		
		//objectives+=ObjectiveInterpreterSpec+
		public Assignment getObjectivesAssignment_3() { return cObjectivesAssignment_3; }
		
		//ObjectiveInterpreterSpec
		public RuleCall getObjectivesObjectiveInterpreterSpecParserRuleCall_3_0() { return cObjectivesObjectiveInterpreterSpecParserRuleCall_3_0; }
		
		//constraints+=ConstraintInterpreterSpec*
		public Assignment getConstraintsAssignment_4() { return cConstraintsAssignment_4; }
		
		//ConstraintInterpreterSpec
		public RuleCall getConstraintsConstraintInterpreterSpecParserRuleCall_4_0() { return cConstraintsConstraintInterpreterSpecParserRuleCall_4_0; }
		
		//evolvers+=EvolverSpec+
		public Assignment getEvolversAssignment_5() { return cEvolversAssignment_5; }
		
		//EvolverSpec
		public RuleCall getEvolversEvolverSpecParserRuleCall_5_0() { return cEvolversEvolverSpecParserRuleCall_5_0; }
		
		//optimisation=OptimisationSpec
		public Assignment getOptimisationAssignment_6() { return cOptimisationAssignment_6; }
		
		//OptimisationSpec
		public RuleCall getOptimisationOptimisationSpecParserRuleCall_6_0() { return cOptimisationOptimisationSpecParserRuleCall_6_0; }
	}
	public class BasepathSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.BasepathSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cBasepathKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cLocationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLocationURLTerminalRuleCall_1_0 = (RuleCall)cLocationAssignment_1.eContents().get(0);
		
		/// **
		// * Use this to identify a basepath to be set in the HenshinResourceSet so that Henshin 
		// * transformations referencing file-based meta-models through relative paths will work 
		// * correctly. This will happen mainly when working with dynamic instances rather than 
		// * fully generated and registered metamodels. All further URLs should be either absolute 
		// * or relative to this basepath.
		// * / BasepathSpec:
		//	"basepath" location=URL;
		@Override public ParserRule getRule() { return rule; }
		
		//"basepath" location=URL
		public Group getGroup() { return cGroup; }
		
		//"basepath"
		public Keyword getBasepathKeyword_0() { return cBasepathKeyword_0; }
		
		//location=URL
		public Assignment getLocationAssignment_1() { return cLocationAssignment_1; }
		
		//URL
		public RuleCall getLocationURLTerminalRuleCall_1_0() { return cLocationURLTerminalRuleCall_1_0; }
	}
	public class MetaModelSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.MetaModelSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cMetamodelKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cLocationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLocationURLTerminalRuleCall_1_0 = (RuleCall)cLocationAssignment_1.eContents().get(0);
		
		//MetaModelSpec:
		//	"metamodel" location=URL;
		@Override public ParserRule getRule() { return rule; }
		
		//"metamodel" location=URL
		public Group getGroup() { return cGroup; }
		
		//"metamodel"
		public Keyword getMetamodelKeyword_0() { return cMetamodelKeyword_0; }
		
		//location=URL
		public Assignment getLocationAssignment_1() { return cLocationAssignment_1; }
		
		//URL
		public RuleCall getLocationURLTerminalRuleCall_1_0() { return cLocationURLTerminalRuleCall_1_0; }
	}
	public class ModelPathSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ModelPathSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cModelKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cLocationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cLocationURLTerminalRuleCall_1_0 = (RuleCall)cLocationAssignment_1.eContents().get(0);
		
		//ModelPathSpec:
		//	"model" location=URL;
		@Override public ParserRule getRule() { return rule; }
		
		//"model" location=URL
		public Group getGroup() { return cGroup; }
		
		//"model"
		public Keyword getModelKeyword_0() { return cModelKeyword_0; }
		
		//location=URL
		public Assignment getLocationAssignment_1() { return cLocationAssignment_1; }
		
		//URL
		public RuleCall getLocationURLTerminalRuleCall_1_0() { return cLocationURLTerminalRuleCall_1_0; }
	}
	public class ObjectiveInterpreterSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ObjectiveInterpreterSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cObjectiveKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cObjectiveNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cObjectiveNameENTITY_NAMETerminalRuleCall_1_0 = (RuleCall)cObjectiveNameAssignment_1.eContents().get(0);
		private final Assignment cObjectiveTendencyAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cObjectiveTendencyOBJECTIVE_TENDENCYTerminalRuleCall_2_0 = (RuleCall)cObjectiveTendencyAssignment_2.eContents().get(0);
		private final Assignment cObjectiveTypeAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cObjectiveTypeENTITY_TYPETerminalRuleCall_3_0 = (RuleCall)cObjectiveTypeAssignment_3.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Assignment cObjectiveSpecAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cObjectiveSpecSTRINGTerminalRuleCall_5_0 = (RuleCall)cObjectiveSpecAssignment_5.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_6 = (Keyword)cGroup.eContents().get(6);
		
		//ObjectiveInterpreterSpec:
		//	"objective" objectiveName=ENTITY_NAME objectiveTendency=OBJECTIVE_TENDENCY objectiveType=ENTITY_TYPE "{"
		//	objectiveSpec=STRING "}";
		@Override public ParserRule getRule() { return rule; }
		
		//"objective" objectiveName=ENTITY_NAME objectiveTendency=OBJECTIVE_TENDENCY objectiveType=ENTITY_TYPE "{"
		//objectiveSpec=STRING "}"
		public Group getGroup() { return cGroup; }
		
		//"objective"
		public Keyword getObjectiveKeyword_0() { return cObjectiveKeyword_0; }
		
		//objectiveName=ENTITY_NAME
		public Assignment getObjectiveNameAssignment_1() { return cObjectiveNameAssignment_1; }
		
		//ENTITY_NAME
		public RuleCall getObjectiveNameENTITY_NAMETerminalRuleCall_1_0() { return cObjectiveNameENTITY_NAMETerminalRuleCall_1_0; }
		
		//objectiveTendency=OBJECTIVE_TENDENCY
		public Assignment getObjectiveTendencyAssignment_2() { return cObjectiveTendencyAssignment_2; }
		
		//OBJECTIVE_TENDENCY
		public RuleCall getObjectiveTendencyOBJECTIVE_TENDENCYTerminalRuleCall_2_0() { return cObjectiveTendencyOBJECTIVE_TENDENCYTerminalRuleCall_2_0; }
		
		//objectiveType=ENTITY_TYPE
		public Assignment getObjectiveTypeAssignment_3() { return cObjectiveTypeAssignment_3; }
		
		//ENTITY_TYPE
		public RuleCall getObjectiveTypeENTITY_TYPETerminalRuleCall_3_0() { return cObjectiveTypeENTITY_TYPETerminalRuleCall_3_0; }
		
		//"{"
		public Keyword getLeftCurlyBracketKeyword_4() { return cLeftCurlyBracketKeyword_4; }
		
		//objectiveSpec=STRING
		public Assignment getObjectiveSpecAssignment_5() { return cObjectiveSpecAssignment_5; }
		
		//STRING
		public RuleCall getObjectiveSpecSTRINGTerminalRuleCall_5_0() { return cObjectiveSpecSTRINGTerminalRuleCall_5_0; }
		
		//"}"
		public Keyword getRightCurlyBracketKeyword_6() { return cRightCurlyBracketKeyword_6; }
	}
	public class ConstraintInterpreterSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ConstraintInterpreterSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cConstraintKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cConstraintNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cConstraintNameENTITY_NAMETerminalRuleCall_1_0 = (RuleCall)cConstraintNameAssignment_1.eContents().get(0);
		private final Assignment cConstraintTypeAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cConstraintTypeENTITY_TYPETerminalRuleCall_2_0 = (RuleCall)cConstraintTypeAssignment_2.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cConstraintSpecAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cConstraintSpecSTRINGTerminalRuleCall_4_0 = (RuleCall)cConstraintSpecAssignment_4.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_5 = (Keyword)cGroup.eContents().get(5);
		
		//ConstraintInterpreterSpec:
		//	"constraint" constraintName=ENTITY_NAME constraintType=ENTITY_TYPE "{" constraintSpec=STRING "}";
		@Override public ParserRule getRule() { return rule; }
		
		//"constraint" constraintName=ENTITY_NAME constraintType=ENTITY_TYPE "{" constraintSpec=STRING "}"
		public Group getGroup() { return cGroup; }
		
		//"constraint"
		public Keyword getConstraintKeyword_0() { return cConstraintKeyword_0; }
		
		//constraintName=ENTITY_NAME
		public Assignment getConstraintNameAssignment_1() { return cConstraintNameAssignment_1; }
		
		//ENTITY_NAME
		public RuleCall getConstraintNameENTITY_NAMETerminalRuleCall_1_0() { return cConstraintNameENTITY_NAMETerminalRuleCall_1_0; }
		
		//constraintType=ENTITY_TYPE
		public Assignment getConstraintTypeAssignment_2() { return cConstraintTypeAssignment_2; }
		
		//ENTITY_TYPE
		public RuleCall getConstraintTypeENTITY_TYPETerminalRuleCall_2_0() { return cConstraintTypeENTITY_TYPETerminalRuleCall_2_0; }
		
		//"{"
		public Keyword getLeftCurlyBracketKeyword_3() { return cLeftCurlyBracketKeyword_3; }
		
		//constraintSpec=STRING
		public Assignment getConstraintSpecAssignment_4() { return cConstraintSpecAssignment_4; }
		
		//STRING
		public RuleCall getConstraintSpecSTRINGTerminalRuleCall_4_0() { return cConstraintSpecSTRINGTerminalRuleCall_4_0; }
		
		//"}"
		public Keyword getRightCurlyBracketKeyword_5() { return cRightCurlyBracketKeyword_5; }
	}
	public class EvolverSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.EvolverSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cEvolveKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cUsingKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cRule_locationAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cRule_locationURLTerminalRuleCall_2_0 = (RuleCall)cRule_locationAssignment_2.eContents().get(0);
		private final Keyword cUnitKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cUnitAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cUnitSTRINGTerminalRuleCall_4_0 = (RuleCall)cUnitAssignment_4.eContents().get(0);
		private final Keyword cTypeKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cTypeAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cTypeSTRINGTerminalRuleCall_6_0 = (RuleCall)cTypeAssignment_6.eContents().get(0);
		
		//EvolverSpec: // This expects to be passed in a Henshin model
		//// TODO We may also want to allow the specification of parameters, but not for now. 
		//	"evolve" "using" rule_location=URL "unit" unit=STRING "type" type=STRING;
		@Override public ParserRule getRule() { return rule; }
		
		//// This expects to be passed in a Henshin model
		//// TODO We may also want to allow the specification of parameters, but not for now. 
		//"evolve" "using" rule_location=URL "unit" unit=STRING "type" type=STRING
		public Group getGroup() { return cGroup; }
		
		//// This expects to be passed in a Henshin model
		//// TODO We may also want to allow the specification of parameters, but not for now. 
		//"evolve"
		public Keyword getEvolveKeyword_0() { return cEvolveKeyword_0; }
		
		//"using"
		public Keyword getUsingKeyword_1() { return cUsingKeyword_1; }
		
		//rule_location=URL
		public Assignment getRule_locationAssignment_2() { return cRule_locationAssignment_2; }
		
		//URL
		public RuleCall getRule_locationURLTerminalRuleCall_2_0() { return cRule_locationURLTerminalRuleCall_2_0; }
		
		//"unit"
		public Keyword getUnitKeyword_3() { return cUnitKeyword_3; }
		
		//unit=STRING
		public Assignment getUnitAssignment_4() { return cUnitAssignment_4; }
		
		//STRING
		public RuleCall getUnitSTRINGTerminalRuleCall_4_0() { return cUnitSTRINGTerminalRuleCall_4_0; }
		
		//"type"
		public Keyword getTypeKeyword_5() { return cTypeKeyword_5; }
		
		//type=STRING
		public Assignment getTypeAssignment_6() { return cTypeAssignment_6; }
		
		//STRING
		public RuleCall getTypeSTRINGTerminalRuleCall_6_0() { return cTypeSTRINGTerminalRuleCall_6_0; }
	}
	public class OptimisationSpecElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.OptimisationSpec");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cOptimisationKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cProviderKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cAlgorithmFactoryAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cAlgorithmFactoryALGORITHM_FACTORYTerminalRuleCall_2_0 = (RuleCall)cAlgorithmFactoryAssignment_2.eContents().get(0);
		private final Keyword cAlgorithmKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Assignment cAlgorithmNameAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cAlgorithmNameALGORITHM_NAMETerminalRuleCall_4_0 = (RuleCall)cAlgorithmNameAssignment_4.eContents().get(0);
		private final Keyword cVariationKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Assignment cAlgorithmVariationAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cAlgorithmVariationALGORITHM_VARIATIONTerminalRuleCall_6_0 = (RuleCall)cAlgorithmVariationAssignment_6.eContents().get(0);
		private final Keyword cEvolutionsKeyword_7 = (Keyword)cGroup.eContents().get(7);
		private final Assignment cAlgorithmEvolutionsAssignment_8 = (Assignment)cGroup.eContents().get(8);
		private final RuleCall cAlgorithmEvolutionsINTTerminalRuleCall_8_0 = (RuleCall)cAlgorithmEvolutionsAssignment_8.eContents().get(0);
		private final Keyword cPopulationKeyword_9 = (Keyword)cGroup.eContents().get(9);
		private final Assignment cAlgorithmPopulationAssignment_10 = (Assignment)cGroup.eContents().get(10);
		private final RuleCall cAlgorithmPopulationINTTerminalRuleCall_10_0 = (RuleCall)cAlgorithmPopulationAssignment_10.eContents().get(0);
		
		//OptimisationSpec:
		//	"optimisation" "provider" algorithmFactory=ALGORITHM_FACTORY
		//	"algorithm" algorithmName=ALGORITHM_NAME
		//	"variation" algorithmVariation=ALGORITHM_VARIATION
		//	"evolutions" algorithmEvolutions=INT
		//	"population" algorithmPopulation=INT;
		@Override public ParserRule getRule() { return rule; }
		
		//"optimisation" "provider" algorithmFactory=ALGORITHM_FACTORY "algorithm" algorithmName=ALGORITHM_NAME "variation"
		//algorithmVariation=ALGORITHM_VARIATION "evolutions" algorithmEvolutions=INT "population" algorithmPopulation=INT
		public Group getGroup() { return cGroup; }
		
		//"optimisation"
		public Keyword getOptimisationKeyword_0() { return cOptimisationKeyword_0; }
		
		//"provider"
		public Keyword getProviderKeyword_1() { return cProviderKeyword_1; }
		
		//algorithmFactory=ALGORITHM_FACTORY
		public Assignment getAlgorithmFactoryAssignment_2() { return cAlgorithmFactoryAssignment_2; }
		
		//ALGORITHM_FACTORY
		public RuleCall getAlgorithmFactoryALGORITHM_FACTORYTerminalRuleCall_2_0() { return cAlgorithmFactoryALGORITHM_FACTORYTerminalRuleCall_2_0; }
		
		//"algorithm"
		public Keyword getAlgorithmKeyword_3() { return cAlgorithmKeyword_3; }
		
		//algorithmName=ALGORITHM_NAME
		public Assignment getAlgorithmNameAssignment_4() { return cAlgorithmNameAssignment_4; }
		
		//ALGORITHM_NAME
		public RuleCall getAlgorithmNameALGORITHM_NAMETerminalRuleCall_4_0() { return cAlgorithmNameALGORITHM_NAMETerminalRuleCall_4_0; }
		
		//"variation"
		public Keyword getVariationKeyword_5() { return cVariationKeyword_5; }
		
		//algorithmVariation=ALGORITHM_VARIATION
		public Assignment getAlgorithmVariationAssignment_6() { return cAlgorithmVariationAssignment_6; }
		
		//ALGORITHM_VARIATION
		public RuleCall getAlgorithmVariationALGORITHM_VARIATIONTerminalRuleCall_6_0() { return cAlgorithmVariationALGORITHM_VARIATIONTerminalRuleCall_6_0; }
		
		//"evolutions"
		public Keyword getEvolutionsKeyword_7() { return cEvolutionsKeyword_7; }
		
		//algorithmEvolutions=INT
		public Assignment getAlgorithmEvolutionsAssignment_8() { return cAlgorithmEvolutionsAssignment_8; }
		
		//INT
		public RuleCall getAlgorithmEvolutionsINTTerminalRuleCall_8_0() { return cAlgorithmEvolutionsINTTerminalRuleCall_8_0; }
		
		//"population"
		public Keyword getPopulationKeyword_9() { return cPopulationKeyword_9; }
		
		//algorithmPopulation=INT
		public Assignment getAlgorithmPopulationAssignment_10() { return cAlgorithmPopulationAssignment_10; }
		
		//INT
		public RuleCall getAlgorithmPopulationINTTerminalRuleCall_10_0() { return cAlgorithmPopulationINTTerminalRuleCall_10_0; }
	}
	
	
	private final OptimisationElements pOptimisation;
	private final BasepathSpecElements pBasepathSpec;
	private final MetaModelSpecElements pMetaModelSpec;
	private final ModelPathSpecElements pModelPathSpec;
	private final ObjectiveInterpreterSpecElements pObjectiveInterpreterSpec;
	private final ConstraintInterpreterSpecElements pConstraintInterpreterSpec;
	private final EvolverSpecElements pEvolverSpec;
	private final OptimisationSpecElements pOptimisationSpec;
	private final TerminalRule tEVOLVER_TYPE;
	private final TerminalRule tALGORITHM_VARIATION;
	private final TerminalRule tALGORITHM_FACTORY;
	private final TerminalRule tALGORITHM_NAME;
	private final TerminalRule tURL;
	private final TerminalRule tOBJECTIVE_TENDENCY;
	private final TerminalRule tENTITY_TYPE;
	private final TerminalRule tENTITY_NAME;
	
	private final Grammar grammar;
	
	private final TerminalsGrammarAccess gaTerminals;

	@Inject
	public MDEOptimiseGrammarAccess(GrammarProvider grammarProvider,
			TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
		this.pOptimisation = new OptimisationElements();
		this.pBasepathSpec = new BasepathSpecElements();
		this.pMetaModelSpec = new MetaModelSpecElements();
		this.pModelPathSpec = new ModelPathSpecElements();
		this.pObjectiveInterpreterSpec = new ObjectiveInterpreterSpecElements();
		this.pConstraintInterpreterSpec = new ConstraintInterpreterSpecElements();
		this.pEvolverSpec = new EvolverSpecElements();
		this.pOptimisationSpec = new OptimisationSpecElements();
		this.tEVOLVER_TYPE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.EVOLVER_TYPE");
		this.tALGORITHM_VARIATION = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ALGORITHM_VARIATION");
		this.tALGORITHM_FACTORY = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ALGORITHM_FACTORY");
		this.tALGORITHM_NAME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ALGORITHM_NAME");
		this.tURL = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.URL");
		this.tOBJECTIVE_TENDENCY = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.OBJECTIVE_TENDENCY");
		this.tENTITY_TYPE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ENTITY_TYPE");
		this.tENTITY_NAME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "uk.ac.kcl.MDEOptimise.ENTITY_NAME");
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("uk.ac.kcl.MDEOptimise".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//Optimisation:
	//	basepath=BasepathSpec
	//	metamodel=MetaModelSpec
	//	model=ModelPathSpec
	//	objectives+=ObjectiveInterpreterSpec+
	//	constraints+=ConstraintInterpreterSpec*
	//	evolvers+=EvolverSpec+
	//	optimisation=OptimisationSpec;
	public OptimisationElements getOptimisationAccess() {
		return pOptimisation;
	}
	
	public ParserRule getOptimisationRule() {
		return getOptimisationAccess().getRule();
	}
	
	/// **
	// * Use this to identify a basepath to be set in the HenshinResourceSet so that Henshin 
	// * transformations referencing file-based meta-models through relative paths will work 
	// * correctly. This will happen mainly when working with dynamic instances rather than 
	// * fully generated and registered metamodels. All further URLs should be either absolute 
	// * or relative to this basepath.
	// * / BasepathSpec:
	//	"basepath" location=URL;
	public BasepathSpecElements getBasepathSpecAccess() {
		return pBasepathSpec;
	}
	
	public ParserRule getBasepathSpecRule() {
		return getBasepathSpecAccess().getRule();
	}
	
	//MetaModelSpec:
	//	"metamodel" location=URL;
	public MetaModelSpecElements getMetaModelSpecAccess() {
		return pMetaModelSpec;
	}
	
	public ParserRule getMetaModelSpecRule() {
		return getMetaModelSpecAccess().getRule();
	}
	
	//ModelPathSpec:
	//	"model" location=URL;
	public ModelPathSpecElements getModelPathSpecAccess() {
		return pModelPathSpec;
	}
	
	public ParserRule getModelPathSpecRule() {
		return getModelPathSpecAccess().getRule();
	}
	
	//ObjectiveInterpreterSpec:
	//	"objective" objectiveName=ENTITY_NAME objectiveTendency=OBJECTIVE_TENDENCY objectiveType=ENTITY_TYPE "{"
	//	objectiveSpec=STRING "}";
	public ObjectiveInterpreterSpecElements getObjectiveInterpreterSpecAccess() {
		return pObjectiveInterpreterSpec;
	}
	
	public ParserRule getObjectiveInterpreterSpecRule() {
		return getObjectiveInterpreterSpecAccess().getRule();
	}
	
	//ConstraintInterpreterSpec:
	//	"constraint" constraintName=ENTITY_NAME constraintType=ENTITY_TYPE "{" constraintSpec=STRING "}";
	public ConstraintInterpreterSpecElements getConstraintInterpreterSpecAccess() {
		return pConstraintInterpreterSpec;
	}
	
	public ParserRule getConstraintInterpreterSpecRule() {
		return getConstraintInterpreterSpecAccess().getRule();
	}
	
	//EvolverSpec: // This expects to be passed in a Henshin model
	//// TODO We may also want to allow the specification of parameters, but not for now. 
	//	"evolve" "using" rule_location=URL "unit" unit=STRING "type" type=STRING;
	public EvolverSpecElements getEvolverSpecAccess() {
		return pEvolverSpec;
	}
	
	public ParserRule getEvolverSpecRule() {
		return getEvolverSpecAccess().getRule();
	}
	
	//OptimisationSpec:
	//	"optimisation" "provider" algorithmFactory=ALGORITHM_FACTORY
	//	"algorithm" algorithmName=ALGORITHM_NAME
	//	"variation" algorithmVariation=ALGORITHM_VARIATION
	//	"evolutions" algorithmEvolutions=INT
	//	"population" algorithmPopulation=INT;
	public OptimisationSpecElements getOptimisationSpecAccess() {
		return pOptimisationSpec;
	}
	
	public ParserRule getOptimisationSpecRule() {
		return getOptimisationSpecAccess().getRule();
	}
	
	//terminal EVOLVER_TYPE:
	//	'm' | 'c';
	public TerminalRule getEVOLVER_TYPERule() {
		return tEVOLVER_TYPE;
	}
	
	//terminal ALGORITHM_VARIATION:
	//	'genetic' | 'crossover' | 'mutation';
	public TerminalRule getALGORITHM_VARIATIONRule() {
		return tALGORITHM_VARIATION;
	}
	
	//terminal ALGORITHM_FACTORY:
	//	'moea' | 'ecj';
	public TerminalRule getALGORITHM_FACTORYRule() {
		return tALGORITHM_FACTORY;
	}
	
	//terminal ALGORITHM_NAME:
	//	'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2';
	public TerminalRule getALGORITHM_NAMERule() {
		return tALGORITHM_NAME;
	}
	
	//terminal URL:
	//	'<'->'>';
	public TerminalRule getURLRule() {
		return tURL;
	}
	
	//terminal OBJECTIVE_TENDENCY:
	//	'minimise' | 'maximise';
	public TerminalRule getOBJECTIVE_TENDENCYRule() {
		return tOBJECTIVE_TENDENCY;
	}
	
	//terminal ENTITY_TYPE:
	//	'java' | 'ocl';
	public TerminalRule getENTITY_TYPERule() {
		return tENTITY_TYPE;
	}
	
	//terminal ENTITY_NAME:
	//	'a'..'z' | 'A'..'Z'+;
	public TerminalRule getENTITY_NAMERule() {
		return tENTITY_NAME;
	}
	
	//terminal ID:
	//	'^'? ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	}
	
	//terminal INT returns ecore::EInt:
	//	'0'..'9'+;
	public TerminalRule getINTRule() {
		return gaTerminals.getINTRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' . | !('\\' | '"'))* '"' |
	//	"'" ('\\' . | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	}
	
	//terminal ML_COMMENT:
	//	'/ *'->'* /';
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	}
	
	//terminal SL_COMMENT:
	//	'//' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	}
	
	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	}
}
