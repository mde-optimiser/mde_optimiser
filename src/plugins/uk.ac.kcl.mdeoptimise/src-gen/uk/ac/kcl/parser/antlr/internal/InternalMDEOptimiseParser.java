package uk.ac.kcl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import uk.ac.kcl.services.MDEOptimiseGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMDEOptimiseParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_URL", "RULE_ID", "RULE_OBJECTIVE_TENDENCY", "RULE_ENTITY_TYPE", "RULE_STRING", "RULE_ALGORITHM_FACTORY", "RULE_ALGORITHM_NAME", "RULE_INT", "RULE_MUTATION_VARIATION", "RULE_CROSSOVER_VARIATION", "RULE_GENETIC_VARIATION", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'basepath'", "'metamodel'", "'model'", "'objective'", "'{'", "'}'", "'constraint'", "'using'", "'unit'", "'parameters'", "','", "'=>'", "'('", "')'", "'optimisation'", "'provider'", "'algorithm'", "'variation'", "'evolutions'", "'population'", "'experiments'", "'mutate'", "'breed'"
    };
    public static final int RULE_MUTATION_VARIATION=12;
    public static final int RULE_OBJECTIVE_TENDENCY=6;
    public static final int RULE_STRING=8;
    public static final int RULE_CROSSOVER_VARIATION=13;
    public static final int RULE_SL_COMMENT=16;
    public static final int T__19=19;
    public static final int T__37=37;
    public static final int RULE_ALGORITHM_FACTORY=9;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=5;
    public static final int RULE_WS=17;
    public static final int RULE_GENETIC_VARIATION=14;
    public static final int RULE_ANY_OTHER=18;
    public static final int RULE_ALGORITHM_NAME=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=11;
    public static final int T__29=29;
    public static final int RULE_URL=4;
    public static final int RULE_ENTITY_TYPE=7;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=15;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalMDEOptimiseParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMDEOptimiseParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMDEOptimiseParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMDEOptimise.g"; }



     	private MDEOptimiseGrammarAccess grammarAccess;

        public InternalMDEOptimiseParser(TokenStream input, MDEOptimiseGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Optimisation";
       	}

       	@Override
       	protected MDEOptimiseGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleOptimisation"
    // InternalMDEOptimise.g:65:1: entryRuleOptimisation returns [EObject current=null] : iv_ruleOptimisation= ruleOptimisation EOF ;
    public final EObject entryRuleOptimisation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptimisation = null;


        try {
            // InternalMDEOptimise.g:65:53: (iv_ruleOptimisation= ruleOptimisation EOF )
            // InternalMDEOptimise.g:66:2: iv_ruleOptimisation= ruleOptimisation EOF
            {
             newCompositeNode(grammarAccess.getOptimisationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptimisation=ruleOptimisation();

            state._fsp--;

             current =iv_ruleOptimisation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptimisation"


    // $ANTLR start "ruleOptimisation"
    // InternalMDEOptimise.g:72:1: ruleOptimisation returns [EObject current=null] : ( ( (lv_basepath_0_0= ruleBasepathSpec ) ) ( (lv_metamodel_1_0= ruleMetaModelSpec ) ) ( (lv_model_2_0= ruleModelPathSpec ) ) ( (lv_objectives_3_0= ruleObjectiveInterpreterSpec ) )+ ( (lv_constraints_4_0= ruleConstraintInterpreterSpec ) )* ( (lv_evolvers_5_0= ruleEvolverSpec ) )+ ( (lv_optimisation_6_0= ruleOptimisationSpec ) ) ) ;
    public final EObject ruleOptimisation() throws RecognitionException {
        EObject current = null;

        EObject lv_basepath_0_0 = null;

        EObject lv_metamodel_1_0 = null;

        EObject lv_model_2_0 = null;

        EObject lv_objectives_3_0 = null;

        EObject lv_constraints_4_0 = null;

        EObject lv_evolvers_5_0 = null;

        EObject lv_optimisation_6_0 = null;



        	enterRule();

        try {
            // InternalMDEOptimise.g:78:2: ( ( ( (lv_basepath_0_0= ruleBasepathSpec ) ) ( (lv_metamodel_1_0= ruleMetaModelSpec ) ) ( (lv_model_2_0= ruleModelPathSpec ) ) ( (lv_objectives_3_0= ruleObjectiveInterpreterSpec ) )+ ( (lv_constraints_4_0= ruleConstraintInterpreterSpec ) )* ( (lv_evolvers_5_0= ruleEvolverSpec ) )+ ( (lv_optimisation_6_0= ruleOptimisationSpec ) ) ) )
            // InternalMDEOptimise.g:79:2: ( ( (lv_basepath_0_0= ruleBasepathSpec ) ) ( (lv_metamodel_1_0= ruleMetaModelSpec ) ) ( (lv_model_2_0= ruleModelPathSpec ) ) ( (lv_objectives_3_0= ruleObjectiveInterpreterSpec ) )+ ( (lv_constraints_4_0= ruleConstraintInterpreterSpec ) )* ( (lv_evolvers_5_0= ruleEvolverSpec ) )+ ( (lv_optimisation_6_0= ruleOptimisationSpec ) ) )
            {
            // InternalMDEOptimise.g:79:2: ( ( (lv_basepath_0_0= ruleBasepathSpec ) ) ( (lv_metamodel_1_0= ruleMetaModelSpec ) ) ( (lv_model_2_0= ruleModelPathSpec ) ) ( (lv_objectives_3_0= ruleObjectiveInterpreterSpec ) )+ ( (lv_constraints_4_0= ruleConstraintInterpreterSpec ) )* ( (lv_evolvers_5_0= ruleEvolverSpec ) )+ ( (lv_optimisation_6_0= ruleOptimisationSpec ) ) )
            // InternalMDEOptimise.g:80:3: ( (lv_basepath_0_0= ruleBasepathSpec ) ) ( (lv_metamodel_1_0= ruleMetaModelSpec ) ) ( (lv_model_2_0= ruleModelPathSpec ) ) ( (lv_objectives_3_0= ruleObjectiveInterpreterSpec ) )+ ( (lv_constraints_4_0= ruleConstraintInterpreterSpec ) )* ( (lv_evolvers_5_0= ruleEvolverSpec ) )+ ( (lv_optimisation_6_0= ruleOptimisationSpec ) )
            {
            // InternalMDEOptimise.g:80:3: ( (lv_basepath_0_0= ruleBasepathSpec ) )
            // InternalMDEOptimise.g:81:4: (lv_basepath_0_0= ruleBasepathSpec )
            {
            // InternalMDEOptimise.g:81:4: (lv_basepath_0_0= ruleBasepathSpec )
            // InternalMDEOptimise.g:82:5: lv_basepath_0_0= ruleBasepathSpec
            {

            					newCompositeNode(grammarAccess.getOptimisationAccess().getBasepathBasepathSpecParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_3);
            lv_basepath_0_0=ruleBasepathSpec();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            					}
            					set(
            						current,
            						"basepath",
            						lv_basepath_0_0,
            						"uk.ac.kcl.MDEOptimise.BasepathSpec");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMDEOptimise.g:99:3: ( (lv_metamodel_1_0= ruleMetaModelSpec ) )
            // InternalMDEOptimise.g:100:4: (lv_metamodel_1_0= ruleMetaModelSpec )
            {
            // InternalMDEOptimise.g:100:4: (lv_metamodel_1_0= ruleMetaModelSpec )
            // InternalMDEOptimise.g:101:5: lv_metamodel_1_0= ruleMetaModelSpec
            {

            					newCompositeNode(grammarAccess.getOptimisationAccess().getMetamodelMetaModelSpecParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_4);
            lv_metamodel_1_0=ruleMetaModelSpec();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            					}
            					set(
            						current,
            						"metamodel",
            						lv_metamodel_1_0,
            						"uk.ac.kcl.MDEOptimise.MetaModelSpec");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMDEOptimise.g:118:3: ( (lv_model_2_0= ruleModelPathSpec ) )
            // InternalMDEOptimise.g:119:4: (lv_model_2_0= ruleModelPathSpec )
            {
            // InternalMDEOptimise.g:119:4: (lv_model_2_0= ruleModelPathSpec )
            // InternalMDEOptimise.g:120:5: lv_model_2_0= ruleModelPathSpec
            {

            					newCompositeNode(grammarAccess.getOptimisationAccess().getModelModelPathSpecParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_5);
            lv_model_2_0=ruleModelPathSpec();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            					}
            					set(
            						current,
            						"model",
            						lv_model_2_0,
            						"uk.ac.kcl.MDEOptimise.ModelPathSpec");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMDEOptimise.g:137:3: ( (lv_objectives_3_0= ruleObjectiveInterpreterSpec ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==22) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMDEOptimise.g:138:4: (lv_objectives_3_0= ruleObjectiveInterpreterSpec )
            	    {
            	    // InternalMDEOptimise.g:138:4: (lv_objectives_3_0= ruleObjectiveInterpreterSpec )
            	    // InternalMDEOptimise.g:139:5: lv_objectives_3_0= ruleObjectiveInterpreterSpec
            	    {

            	    					newCompositeNode(grammarAccess.getOptimisationAccess().getObjectivesObjectiveInterpreterSpecParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_objectives_3_0=ruleObjectiveInterpreterSpec();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"objectives",
            	    						lv_objectives_3_0,
            	    						"uk.ac.kcl.MDEOptimise.ObjectiveInterpreterSpec");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // InternalMDEOptimise.g:156:3: ( (lv_constraints_4_0= ruleConstraintInterpreterSpec ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==25) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMDEOptimise.g:157:4: (lv_constraints_4_0= ruleConstraintInterpreterSpec )
            	    {
            	    // InternalMDEOptimise.g:157:4: (lv_constraints_4_0= ruleConstraintInterpreterSpec )
            	    // InternalMDEOptimise.g:158:5: lv_constraints_4_0= ruleConstraintInterpreterSpec
            	    {

            	    					newCompositeNode(grammarAccess.getOptimisationAccess().getConstraintsConstraintInterpreterSpecParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_constraints_4_0=ruleConstraintInterpreterSpec();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"constraints",
            	    						lv_constraints_4_0,
            	    						"uk.ac.kcl.MDEOptimise.ConstraintInterpreterSpec");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalMDEOptimise.g:175:3: ( (lv_evolvers_5_0= ruleEvolverSpec ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=40 && LA3_0<=41)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMDEOptimise.g:176:4: (lv_evolvers_5_0= ruleEvolverSpec )
            	    {
            	    // InternalMDEOptimise.g:176:4: (lv_evolvers_5_0= ruleEvolverSpec )
            	    // InternalMDEOptimise.g:177:5: lv_evolvers_5_0= ruleEvolverSpec
            	    {

            	    					newCompositeNode(grammarAccess.getOptimisationAccess().getEvolversEvolverSpecParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_evolvers_5_0=ruleEvolverSpec();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"evolvers",
            	    						lv_evolvers_5_0,
            	    						"uk.ac.kcl.MDEOptimise.EvolverSpec");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // InternalMDEOptimise.g:194:3: ( (lv_optimisation_6_0= ruleOptimisationSpec ) )
            // InternalMDEOptimise.g:195:4: (lv_optimisation_6_0= ruleOptimisationSpec )
            {
            // InternalMDEOptimise.g:195:4: (lv_optimisation_6_0= ruleOptimisationSpec )
            // InternalMDEOptimise.g:196:5: lv_optimisation_6_0= ruleOptimisationSpec
            {

            					newCompositeNode(grammarAccess.getOptimisationAccess().getOptimisationOptimisationSpecParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_2);
            lv_optimisation_6_0=ruleOptimisationSpec();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptimisationRule());
            					}
            					set(
            						current,
            						"optimisation",
            						lv_optimisation_6_0,
            						"uk.ac.kcl.MDEOptimise.OptimisationSpec");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptimisation"


    // $ANTLR start "entryRuleBasepathSpec"
    // InternalMDEOptimise.g:217:1: entryRuleBasepathSpec returns [EObject current=null] : iv_ruleBasepathSpec= ruleBasepathSpec EOF ;
    public final EObject entryRuleBasepathSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBasepathSpec = null;


        try {
            // InternalMDEOptimise.g:217:53: (iv_ruleBasepathSpec= ruleBasepathSpec EOF )
            // InternalMDEOptimise.g:218:2: iv_ruleBasepathSpec= ruleBasepathSpec EOF
            {
             newCompositeNode(grammarAccess.getBasepathSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBasepathSpec=ruleBasepathSpec();

            state._fsp--;

             current =iv_ruleBasepathSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBasepathSpec"


    // $ANTLR start "ruleBasepathSpec"
    // InternalMDEOptimise.g:224:1: ruleBasepathSpec returns [EObject current=null] : (otherlv_0= 'basepath' ( (lv_location_1_0= RULE_URL ) ) ) ;
    public final EObject ruleBasepathSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_location_1_0=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:230:2: ( (otherlv_0= 'basepath' ( (lv_location_1_0= RULE_URL ) ) ) )
            // InternalMDEOptimise.g:231:2: (otherlv_0= 'basepath' ( (lv_location_1_0= RULE_URL ) ) )
            {
            // InternalMDEOptimise.g:231:2: (otherlv_0= 'basepath' ( (lv_location_1_0= RULE_URL ) ) )
            // InternalMDEOptimise.g:232:3: otherlv_0= 'basepath' ( (lv_location_1_0= RULE_URL ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getBasepathSpecAccess().getBasepathKeyword_0());
            		
            // InternalMDEOptimise.g:236:3: ( (lv_location_1_0= RULE_URL ) )
            // InternalMDEOptimise.g:237:4: (lv_location_1_0= RULE_URL )
            {
            // InternalMDEOptimise.g:237:4: (lv_location_1_0= RULE_URL )
            // InternalMDEOptimise.g:238:5: lv_location_1_0= RULE_URL
            {
            lv_location_1_0=(Token)match(input,RULE_URL,FOLLOW_2); 

            					newLeafNode(lv_location_1_0, grammarAccess.getBasepathSpecAccess().getLocationURLTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBasepathSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"location",
            						lv_location_1_0,
            						"uk.ac.kcl.MDEOptimise.URL");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBasepathSpec"


    // $ANTLR start "entryRuleMetaModelSpec"
    // InternalMDEOptimise.g:258:1: entryRuleMetaModelSpec returns [EObject current=null] : iv_ruleMetaModelSpec= ruleMetaModelSpec EOF ;
    public final EObject entryRuleMetaModelSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaModelSpec = null;


        try {
            // InternalMDEOptimise.g:258:54: (iv_ruleMetaModelSpec= ruleMetaModelSpec EOF )
            // InternalMDEOptimise.g:259:2: iv_ruleMetaModelSpec= ruleMetaModelSpec EOF
            {
             newCompositeNode(grammarAccess.getMetaModelSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMetaModelSpec=ruleMetaModelSpec();

            state._fsp--;

             current =iv_ruleMetaModelSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetaModelSpec"


    // $ANTLR start "ruleMetaModelSpec"
    // InternalMDEOptimise.g:265:1: ruleMetaModelSpec returns [EObject current=null] : (otherlv_0= 'metamodel' ( (lv_location_1_0= RULE_URL ) ) ) ;
    public final EObject ruleMetaModelSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_location_1_0=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:271:2: ( (otherlv_0= 'metamodel' ( (lv_location_1_0= RULE_URL ) ) ) )
            // InternalMDEOptimise.g:272:2: (otherlv_0= 'metamodel' ( (lv_location_1_0= RULE_URL ) ) )
            {
            // InternalMDEOptimise.g:272:2: (otherlv_0= 'metamodel' ( (lv_location_1_0= RULE_URL ) ) )
            // InternalMDEOptimise.g:273:3: otherlv_0= 'metamodel' ( (lv_location_1_0= RULE_URL ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getMetaModelSpecAccess().getMetamodelKeyword_0());
            		
            // InternalMDEOptimise.g:277:3: ( (lv_location_1_0= RULE_URL ) )
            // InternalMDEOptimise.g:278:4: (lv_location_1_0= RULE_URL )
            {
            // InternalMDEOptimise.g:278:4: (lv_location_1_0= RULE_URL )
            // InternalMDEOptimise.g:279:5: lv_location_1_0= RULE_URL
            {
            lv_location_1_0=(Token)match(input,RULE_URL,FOLLOW_2); 

            					newLeafNode(lv_location_1_0, grammarAccess.getMetaModelSpecAccess().getLocationURLTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMetaModelSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"location",
            						lv_location_1_0,
            						"uk.ac.kcl.MDEOptimise.URL");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetaModelSpec"


    // $ANTLR start "entryRuleModelPathSpec"
    // InternalMDEOptimise.g:299:1: entryRuleModelPathSpec returns [EObject current=null] : iv_ruleModelPathSpec= ruleModelPathSpec EOF ;
    public final EObject entryRuleModelPathSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModelPathSpec = null;


        try {
            // InternalMDEOptimise.g:299:54: (iv_ruleModelPathSpec= ruleModelPathSpec EOF )
            // InternalMDEOptimise.g:300:2: iv_ruleModelPathSpec= ruleModelPathSpec EOF
            {
             newCompositeNode(grammarAccess.getModelPathSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModelPathSpec=ruleModelPathSpec();

            state._fsp--;

             current =iv_ruleModelPathSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModelPathSpec"


    // $ANTLR start "ruleModelPathSpec"
    // InternalMDEOptimise.g:306:1: ruleModelPathSpec returns [EObject current=null] : (otherlv_0= 'model' ( (lv_location_1_0= RULE_URL ) ) ) ;
    public final EObject ruleModelPathSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_location_1_0=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:312:2: ( (otherlv_0= 'model' ( (lv_location_1_0= RULE_URL ) ) ) )
            // InternalMDEOptimise.g:313:2: (otherlv_0= 'model' ( (lv_location_1_0= RULE_URL ) ) )
            {
            // InternalMDEOptimise.g:313:2: (otherlv_0= 'model' ( (lv_location_1_0= RULE_URL ) ) )
            // InternalMDEOptimise.g:314:3: otherlv_0= 'model' ( (lv_location_1_0= RULE_URL ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getModelPathSpecAccess().getModelKeyword_0());
            		
            // InternalMDEOptimise.g:318:3: ( (lv_location_1_0= RULE_URL ) )
            // InternalMDEOptimise.g:319:4: (lv_location_1_0= RULE_URL )
            {
            // InternalMDEOptimise.g:319:4: (lv_location_1_0= RULE_URL )
            // InternalMDEOptimise.g:320:5: lv_location_1_0= RULE_URL
            {
            lv_location_1_0=(Token)match(input,RULE_URL,FOLLOW_2); 

            					newLeafNode(lv_location_1_0, grammarAccess.getModelPathSpecAccess().getLocationURLTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getModelPathSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"location",
            						lv_location_1_0,
            						"uk.ac.kcl.MDEOptimise.URL");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModelPathSpec"


    // $ANTLR start "entryRuleObjectiveInterpreterSpec"
    // InternalMDEOptimise.g:340:1: entryRuleObjectiveInterpreterSpec returns [EObject current=null] : iv_ruleObjectiveInterpreterSpec= ruleObjectiveInterpreterSpec EOF ;
    public final EObject entryRuleObjectiveInterpreterSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectiveInterpreterSpec = null;


        try {
            // InternalMDEOptimise.g:340:65: (iv_ruleObjectiveInterpreterSpec= ruleObjectiveInterpreterSpec EOF )
            // InternalMDEOptimise.g:341:2: iv_ruleObjectiveInterpreterSpec= ruleObjectiveInterpreterSpec EOF
            {
             newCompositeNode(grammarAccess.getObjectiveInterpreterSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjectiveInterpreterSpec=ruleObjectiveInterpreterSpec();

            state._fsp--;

             current =iv_ruleObjectiveInterpreterSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObjectiveInterpreterSpec"


    // $ANTLR start "ruleObjectiveInterpreterSpec"
    // InternalMDEOptimise.g:347:1: ruleObjectiveInterpreterSpec returns [EObject current=null] : (otherlv_0= 'objective' ( (lv_objectiveName_1_0= RULE_ID ) ) ( (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY ) ) ( (lv_objectiveType_3_0= RULE_ENTITY_TYPE ) ) otherlv_4= '{' ( (lv_objectiveSpec_5_0= RULE_STRING ) ) otherlv_6= '}' ) ;
    public final EObject ruleObjectiveInterpreterSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_objectiveName_1_0=null;
        Token lv_objectiveTendency_2_0=null;
        Token lv_objectiveType_3_0=null;
        Token otherlv_4=null;
        Token lv_objectiveSpec_5_0=null;
        Token otherlv_6=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:353:2: ( (otherlv_0= 'objective' ( (lv_objectiveName_1_0= RULE_ID ) ) ( (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY ) ) ( (lv_objectiveType_3_0= RULE_ENTITY_TYPE ) ) otherlv_4= '{' ( (lv_objectiveSpec_5_0= RULE_STRING ) ) otherlv_6= '}' ) )
            // InternalMDEOptimise.g:354:2: (otherlv_0= 'objective' ( (lv_objectiveName_1_0= RULE_ID ) ) ( (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY ) ) ( (lv_objectiveType_3_0= RULE_ENTITY_TYPE ) ) otherlv_4= '{' ( (lv_objectiveSpec_5_0= RULE_STRING ) ) otherlv_6= '}' )
            {
            // InternalMDEOptimise.g:354:2: (otherlv_0= 'objective' ( (lv_objectiveName_1_0= RULE_ID ) ) ( (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY ) ) ( (lv_objectiveType_3_0= RULE_ENTITY_TYPE ) ) otherlv_4= '{' ( (lv_objectiveSpec_5_0= RULE_STRING ) ) otherlv_6= '}' )
            // InternalMDEOptimise.g:355:3: otherlv_0= 'objective' ( (lv_objectiveName_1_0= RULE_ID ) ) ( (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY ) ) ( (lv_objectiveType_3_0= RULE_ENTITY_TYPE ) ) otherlv_4= '{' ( (lv_objectiveSpec_5_0= RULE_STRING ) ) otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,22,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveKeyword_0());
            		
            // InternalMDEOptimise.g:359:3: ( (lv_objectiveName_1_0= RULE_ID ) )
            // InternalMDEOptimise.g:360:4: (lv_objectiveName_1_0= RULE_ID )
            {
            // InternalMDEOptimise.g:360:4: (lv_objectiveName_1_0= RULE_ID )
            // InternalMDEOptimise.g:361:5: lv_objectiveName_1_0= RULE_ID
            {
            lv_objectiveName_1_0=(Token)match(input,RULE_ID,FOLLOW_11); 

            					newLeafNode(lv_objectiveName_1_0, grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectiveInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"objectiveName",
            						lv_objectiveName_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMDEOptimise.g:377:3: ( (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY ) )
            // InternalMDEOptimise.g:378:4: (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY )
            {
            // InternalMDEOptimise.g:378:4: (lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY )
            // InternalMDEOptimise.g:379:5: lv_objectiveTendency_2_0= RULE_OBJECTIVE_TENDENCY
            {
            lv_objectiveTendency_2_0=(Token)match(input,RULE_OBJECTIVE_TENDENCY,FOLLOW_12); 

            					newLeafNode(lv_objectiveTendency_2_0, grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyOBJECTIVE_TENDENCYTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectiveInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"objectiveTendency",
            						lv_objectiveTendency_2_0,
            						"uk.ac.kcl.MDEOptimise.OBJECTIVE_TENDENCY");
            				

            }


            }

            // InternalMDEOptimise.g:395:3: ( (lv_objectiveType_3_0= RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:396:4: (lv_objectiveType_3_0= RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:396:4: (lv_objectiveType_3_0= RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:397:5: lv_objectiveType_3_0= RULE_ENTITY_TYPE
            {
            lv_objectiveType_3_0=(Token)match(input,RULE_ENTITY_TYPE,FOLLOW_13); 

            					newLeafNode(lv_objectiveType_3_0, grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeENTITY_TYPETerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectiveInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"objectiveType",
            						lv_objectiveType_3_0,
            						"uk.ac.kcl.MDEOptimise.ENTITY_TYPE");
            				

            }


            }

            otherlv_4=(Token)match(input,23,FOLLOW_14); 

            			newLeafNode(otherlv_4, grammarAccess.getObjectiveInterpreterSpecAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMDEOptimise.g:417:3: ( (lv_objectiveSpec_5_0= RULE_STRING ) )
            // InternalMDEOptimise.g:418:4: (lv_objectiveSpec_5_0= RULE_STRING )
            {
            // InternalMDEOptimise.g:418:4: (lv_objectiveSpec_5_0= RULE_STRING )
            // InternalMDEOptimise.g:419:5: lv_objectiveSpec_5_0= RULE_STRING
            {
            lv_objectiveSpec_5_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            					newLeafNode(lv_objectiveSpec_5_0, grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecSTRINGTerminalRuleCall_5_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectiveInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"objectiveSpec",
            						lv_objectiveSpec_5_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_6=(Token)match(input,24,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getObjectiveInterpreterSpecAccess().getRightCurlyBracketKeyword_6());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObjectiveInterpreterSpec"


    // $ANTLR start "entryRuleConstraintInterpreterSpec"
    // InternalMDEOptimise.g:443:1: entryRuleConstraintInterpreterSpec returns [EObject current=null] : iv_ruleConstraintInterpreterSpec= ruleConstraintInterpreterSpec EOF ;
    public final EObject entryRuleConstraintInterpreterSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConstraintInterpreterSpec = null;


        try {
            // InternalMDEOptimise.g:443:66: (iv_ruleConstraintInterpreterSpec= ruleConstraintInterpreterSpec EOF )
            // InternalMDEOptimise.g:444:2: iv_ruleConstraintInterpreterSpec= ruleConstraintInterpreterSpec EOF
            {
             newCompositeNode(grammarAccess.getConstraintInterpreterSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleConstraintInterpreterSpec=ruleConstraintInterpreterSpec();

            state._fsp--;

             current =iv_ruleConstraintInterpreterSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleConstraintInterpreterSpec"


    // $ANTLR start "ruleConstraintInterpreterSpec"
    // InternalMDEOptimise.g:450:1: ruleConstraintInterpreterSpec returns [EObject current=null] : (otherlv_0= 'constraint' ( (lv_constraintName_1_0= RULE_ID ) ) ( (lv_constraintType_2_0= RULE_ENTITY_TYPE ) ) otherlv_3= '{' ( (lv_constraintSpec_4_0= RULE_STRING ) ) otherlv_5= '}' ) ;
    public final EObject ruleConstraintInterpreterSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_constraintName_1_0=null;
        Token lv_constraintType_2_0=null;
        Token otherlv_3=null;
        Token lv_constraintSpec_4_0=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:456:2: ( (otherlv_0= 'constraint' ( (lv_constraintName_1_0= RULE_ID ) ) ( (lv_constraintType_2_0= RULE_ENTITY_TYPE ) ) otherlv_3= '{' ( (lv_constraintSpec_4_0= RULE_STRING ) ) otherlv_5= '}' ) )
            // InternalMDEOptimise.g:457:2: (otherlv_0= 'constraint' ( (lv_constraintName_1_0= RULE_ID ) ) ( (lv_constraintType_2_0= RULE_ENTITY_TYPE ) ) otherlv_3= '{' ( (lv_constraintSpec_4_0= RULE_STRING ) ) otherlv_5= '}' )
            {
            // InternalMDEOptimise.g:457:2: (otherlv_0= 'constraint' ( (lv_constraintName_1_0= RULE_ID ) ) ( (lv_constraintType_2_0= RULE_ENTITY_TYPE ) ) otherlv_3= '{' ( (lv_constraintSpec_4_0= RULE_STRING ) ) otherlv_5= '}' )
            // InternalMDEOptimise.g:458:3: otherlv_0= 'constraint' ( (lv_constraintName_1_0= RULE_ID ) ) ( (lv_constraintType_2_0= RULE_ENTITY_TYPE ) ) otherlv_3= '{' ( (lv_constraintSpec_4_0= RULE_STRING ) ) otherlv_5= '}'
            {
            otherlv_0=(Token)match(input,25,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getConstraintInterpreterSpecAccess().getConstraintKeyword_0());
            		
            // InternalMDEOptimise.g:462:3: ( (lv_constraintName_1_0= RULE_ID ) )
            // InternalMDEOptimise.g:463:4: (lv_constraintName_1_0= RULE_ID )
            {
            // InternalMDEOptimise.g:463:4: (lv_constraintName_1_0= RULE_ID )
            // InternalMDEOptimise.g:464:5: lv_constraintName_1_0= RULE_ID
            {
            lv_constraintName_1_0=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(lv_constraintName_1_0, grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstraintInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"constraintName",
            						lv_constraintName_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMDEOptimise.g:480:3: ( (lv_constraintType_2_0= RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:481:4: (lv_constraintType_2_0= RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:481:4: (lv_constraintType_2_0= RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:482:5: lv_constraintType_2_0= RULE_ENTITY_TYPE
            {
            lv_constraintType_2_0=(Token)match(input,RULE_ENTITY_TYPE,FOLLOW_13); 

            					newLeafNode(lv_constraintType_2_0, grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeENTITY_TYPETerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstraintInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"constraintType",
            						lv_constraintType_2_0,
            						"uk.ac.kcl.MDEOptimise.ENTITY_TYPE");
            				

            }


            }

            otherlv_3=(Token)match(input,23,FOLLOW_14); 

            			newLeafNode(otherlv_3, grammarAccess.getConstraintInterpreterSpecAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalMDEOptimise.g:502:3: ( (lv_constraintSpec_4_0= RULE_STRING ) )
            // InternalMDEOptimise.g:503:4: (lv_constraintSpec_4_0= RULE_STRING )
            {
            // InternalMDEOptimise.g:503:4: (lv_constraintSpec_4_0= RULE_STRING )
            // InternalMDEOptimise.g:504:5: lv_constraintSpec_4_0= RULE_STRING
            {
            lv_constraintSpec_4_0=(Token)match(input,RULE_STRING,FOLLOW_15); 

            					newLeafNode(lv_constraintSpec_4_0, grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getConstraintInterpreterSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"constraintSpec",
            						lv_constraintSpec_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_5=(Token)match(input,24,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getConstraintInterpreterSpecAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleConstraintInterpreterSpec"


    // $ANTLR start "entryRuleEvolverSpec"
    // InternalMDEOptimise.g:528:1: entryRuleEvolverSpec returns [EObject current=null] : iv_ruleEvolverSpec= ruleEvolverSpec EOF ;
    public final EObject entryRuleEvolverSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEvolverSpec = null;


        try {
            // InternalMDEOptimise.g:528:52: (iv_ruleEvolverSpec= ruleEvolverSpec EOF )
            // InternalMDEOptimise.g:529:2: iv_ruleEvolverSpec= ruleEvolverSpec EOF
            {
             newCompositeNode(grammarAccess.getEvolverSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEvolverSpec=ruleEvolverSpec();

            state._fsp--;

             current =iv_ruleEvolverSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEvolverSpec"


    // $ANTLR start "ruleEvolverSpec"
    // InternalMDEOptimise.g:535:1: ruleEvolverSpec returns [EObject current=null] : ( ( (lv_evolverType_0_0= ruleEvolverType ) ) otherlv_1= 'using' ( (lv_rule_location_2_0= RULE_URL ) ) otherlv_3= 'unit' ( (lv_unit_4_0= RULE_STRING ) ) (otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}' )? ) ;
    public final EObject ruleEvolverSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_rule_location_2_0=null;
        Token otherlv_3=null;
        Token lv_unit_4_0=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Enumerator lv_evolverType_0_0 = null;

        EObject lv_parameters_7_0 = null;

        EObject lv_parameters_9_0 = null;



        	enterRule();

        try {
            // InternalMDEOptimise.g:541:2: ( ( ( (lv_evolverType_0_0= ruleEvolverType ) ) otherlv_1= 'using' ( (lv_rule_location_2_0= RULE_URL ) ) otherlv_3= 'unit' ( (lv_unit_4_0= RULE_STRING ) ) (otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}' )? ) )
            // InternalMDEOptimise.g:542:2: ( ( (lv_evolverType_0_0= ruleEvolverType ) ) otherlv_1= 'using' ( (lv_rule_location_2_0= RULE_URL ) ) otherlv_3= 'unit' ( (lv_unit_4_0= RULE_STRING ) ) (otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}' )? )
            {
            // InternalMDEOptimise.g:542:2: ( ( (lv_evolverType_0_0= ruleEvolverType ) ) otherlv_1= 'using' ( (lv_rule_location_2_0= RULE_URL ) ) otherlv_3= 'unit' ( (lv_unit_4_0= RULE_STRING ) ) (otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}' )? )
            // InternalMDEOptimise.g:543:3: ( (lv_evolverType_0_0= ruleEvolverType ) ) otherlv_1= 'using' ( (lv_rule_location_2_0= RULE_URL ) ) otherlv_3= 'unit' ( (lv_unit_4_0= RULE_STRING ) ) (otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}' )?
            {
            // InternalMDEOptimise.g:543:3: ( (lv_evolverType_0_0= ruleEvolverType ) )
            // InternalMDEOptimise.g:544:4: (lv_evolverType_0_0= ruleEvolverType )
            {
            // InternalMDEOptimise.g:544:4: (lv_evolverType_0_0= ruleEvolverType )
            // InternalMDEOptimise.g:545:5: lv_evolverType_0_0= ruleEvolverType
            {

            					newCompositeNode(grammarAccess.getEvolverSpecAccess().getEvolverTypeEvolverTypeEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_16);
            lv_evolverType_0_0=ruleEvolverType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEvolverSpecRule());
            					}
            					set(
            						current,
            						"evolverType",
            						lv_evolverType_0_0,
            						"uk.ac.kcl.MDEOptimise.EvolverType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,26,FOLLOW_9); 

            			newLeafNode(otherlv_1, grammarAccess.getEvolverSpecAccess().getUsingKeyword_1());
            		
            // InternalMDEOptimise.g:566:3: ( (lv_rule_location_2_0= RULE_URL ) )
            // InternalMDEOptimise.g:567:4: (lv_rule_location_2_0= RULE_URL )
            {
            // InternalMDEOptimise.g:567:4: (lv_rule_location_2_0= RULE_URL )
            // InternalMDEOptimise.g:568:5: lv_rule_location_2_0= RULE_URL
            {
            lv_rule_location_2_0=(Token)match(input,RULE_URL,FOLLOW_17); 

            					newLeafNode(lv_rule_location_2_0, grammarAccess.getEvolverSpecAccess().getRule_locationURLTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEvolverSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"rule_location",
            						lv_rule_location_2_0,
            						"uk.ac.kcl.MDEOptimise.URL");
            				

            }


            }

            otherlv_3=(Token)match(input,27,FOLLOW_14); 

            			newLeafNode(otherlv_3, grammarAccess.getEvolverSpecAccess().getUnitKeyword_3());
            		
            // InternalMDEOptimise.g:588:3: ( (lv_unit_4_0= RULE_STRING ) )
            // InternalMDEOptimise.g:589:4: (lv_unit_4_0= RULE_STRING )
            {
            // InternalMDEOptimise.g:589:4: (lv_unit_4_0= RULE_STRING )
            // InternalMDEOptimise.g:590:5: lv_unit_4_0= RULE_STRING
            {
            lv_unit_4_0=(Token)match(input,RULE_STRING,FOLLOW_18); 

            					newLeafNode(lv_unit_4_0, grammarAccess.getEvolverSpecAccess().getUnitSTRINGTerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEvolverSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"unit",
            						lv_unit_4_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            // InternalMDEOptimise.g:606:3: (otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}' )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==28) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalMDEOptimise.g:607:4: otherlv_5= 'parameters' otherlv_6= '{' ( (lv_parameters_7_0= ruleEvolverParameter ) ) (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )* otherlv_10= '}'
                    {
                    otherlv_5=(Token)match(input,28,FOLLOW_13); 

                    				newLeafNode(otherlv_5, grammarAccess.getEvolverSpecAccess().getParametersKeyword_5_0());
                    			
                    otherlv_6=(Token)match(input,23,FOLLOW_10); 

                    				newLeafNode(otherlv_6, grammarAccess.getEvolverSpecAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalMDEOptimise.g:615:4: ( (lv_parameters_7_0= ruleEvolverParameter ) )
                    // InternalMDEOptimise.g:616:5: (lv_parameters_7_0= ruleEvolverParameter )
                    {
                    // InternalMDEOptimise.g:616:5: (lv_parameters_7_0= ruleEvolverParameter )
                    // InternalMDEOptimise.g:617:6: lv_parameters_7_0= ruleEvolverParameter
                    {

                    						newCompositeNode(grammarAccess.getEvolverSpecAccess().getParametersEvolverParameterParserRuleCall_5_2_0());
                    					
                    pushFollow(FOLLOW_19);
                    lv_parameters_7_0=ruleEvolverParameter();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getEvolverSpecRule());
                    						}
                    						add(
                    							current,
                    							"parameters",
                    							lv_parameters_7_0,
                    							"uk.ac.kcl.MDEOptimise.EvolverParameter");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMDEOptimise.g:634:4: (otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==29) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalMDEOptimise.g:635:5: otherlv_8= ',' ( (lv_parameters_9_0= ruleEvolverParameter ) )
                    	    {
                    	    otherlv_8=(Token)match(input,29,FOLLOW_10); 

                    	    					newLeafNode(otherlv_8, grammarAccess.getEvolverSpecAccess().getCommaKeyword_5_3_0());
                    	    				
                    	    // InternalMDEOptimise.g:639:5: ( (lv_parameters_9_0= ruleEvolverParameter ) )
                    	    // InternalMDEOptimise.g:640:6: (lv_parameters_9_0= ruleEvolverParameter )
                    	    {
                    	    // InternalMDEOptimise.g:640:6: (lv_parameters_9_0= ruleEvolverParameter )
                    	    // InternalMDEOptimise.g:641:7: lv_parameters_9_0= ruleEvolverParameter
                    	    {

                    	    							newCompositeNode(grammarAccess.getEvolverSpecAccess().getParametersEvolverParameterParserRuleCall_5_3_1_0());
                    	    						
                    	    pushFollow(FOLLOW_19);
                    	    lv_parameters_9_0=ruleEvolverParameter();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getEvolverSpecRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"parameters",
                    	    								lv_parameters_9_0,
                    	    								"uk.ac.kcl.MDEOptimise.EvolverParameter");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    otherlv_10=(Token)match(input,24,FOLLOW_2); 

                    				newLeafNode(otherlv_10, grammarAccess.getEvolverSpecAccess().getRightCurlyBracketKeyword_5_4());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEvolverSpec"


    // $ANTLR start "entryRuleEvolverParameter"
    // InternalMDEOptimise.g:668:1: entryRuleEvolverParameter returns [EObject current=null] : iv_ruleEvolverParameter= ruleEvolverParameter EOF ;
    public final EObject entryRuleEvolverParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEvolverParameter = null;


        try {
            // InternalMDEOptimise.g:668:57: (iv_ruleEvolverParameter= ruleEvolverParameter EOF )
            // InternalMDEOptimise.g:669:2: iv_ruleEvolverParameter= ruleEvolverParameter EOF
            {
             newCompositeNode(grammarAccess.getEvolverParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEvolverParameter=ruleEvolverParameter();

            state._fsp--;

             current =iv_ruleEvolverParameter; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEvolverParameter"


    // $ANTLR start "ruleEvolverParameter"
    // InternalMDEOptimise.g:675:1: ruleEvolverParameter returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) ) ) ) ;
    public final EObject ruleEvolverParameter() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_customFunction_3_0=null;
        EObject lv_function_2_0 = null;



        	enterRule();

        try {
            // InternalMDEOptimise.g:681:2: ( ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) ) ) ) )
            // InternalMDEOptimise.g:682:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) ) ) )
            {
            // InternalMDEOptimise.g:682:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) ) ) )
            // InternalMDEOptimise.g:683:3: ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) ) )
            {
            // InternalMDEOptimise.g:683:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalMDEOptimise.g:684:4: (lv_name_0_0= RULE_ID )
            {
            // InternalMDEOptimise.g:684:4: (lv_name_0_0= RULE_ID )
            // InternalMDEOptimise.g:685:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_20); 

            					newLeafNode(lv_name_0_0, grammarAccess.getEvolverParameterAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getEvolverParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMDEOptimise.g:701:3: (otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) ) )
            // InternalMDEOptimise.g:702:4: otherlv_1= '=>' ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) )
            {
            otherlv_1=(Token)match(input,30,FOLLOW_21); 

            				newLeafNode(otherlv_1, grammarAccess.getEvolverParameterAccess().getEqualsSignGreaterThanSignKeyword_1_0());
            			
            // InternalMDEOptimise.g:706:4: ( ( (lv_function_2_0= ruleParameterFunction ) ) | ( (lv_customFunction_3_0= RULE_STRING ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_ID) ) {
                alt6=1;
            }
            else if ( (LA6_0==RULE_STRING) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalMDEOptimise.g:707:5: ( (lv_function_2_0= ruleParameterFunction ) )
                    {
                    // InternalMDEOptimise.g:707:5: ( (lv_function_2_0= ruleParameterFunction ) )
                    // InternalMDEOptimise.g:708:6: (lv_function_2_0= ruleParameterFunction )
                    {
                    // InternalMDEOptimise.g:708:6: (lv_function_2_0= ruleParameterFunction )
                    // InternalMDEOptimise.g:709:7: lv_function_2_0= ruleParameterFunction
                    {

                    							newCompositeNode(grammarAccess.getEvolverParameterAccess().getFunctionParameterFunctionParserRuleCall_1_1_0_0());
                    						
                    pushFollow(FOLLOW_2);
                    lv_function_2_0=ruleParameterFunction();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getEvolverParameterRule());
                    							}
                    							set(
                    								current,
                    								"function",
                    								lv_function_2_0,
                    								"uk.ac.kcl.MDEOptimise.ParameterFunction");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:727:5: ( (lv_customFunction_3_0= RULE_STRING ) )
                    {
                    // InternalMDEOptimise.g:727:5: ( (lv_customFunction_3_0= RULE_STRING ) )
                    // InternalMDEOptimise.g:728:6: (lv_customFunction_3_0= RULE_STRING )
                    {
                    // InternalMDEOptimise.g:728:6: (lv_customFunction_3_0= RULE_STRING )
                    // InternalMDEOptimise.g:729:7: lv_customFunction_3_0= RULE_STRING
                    {
                    lv_customFunction_3_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    							newLeafNode(lv_customFunction_3_0, grammarAccess.getEvolverParameterAccess().getCustomFunctionSTRINGTerminalRuleCall_1_1_1_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getEvolverParameterRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"customFunction",
                    								lv_customFunction_3_0,
                    								"org.eclipse.xtext.common.Terminals.STRING");
                    						

                    }


                    }


                    }
                    break;

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEvolverParameter"


    // $ANTLR start "entryRuleParameterFunction"
    // InternalMDEOptimise.g:751:1: entryRuleParameterFunction returns [EObject current=null] : iv_ruleParameterFunction= ruleParameterFunction EOF ;
    public final EObject entryRuleParameterFunction() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParameterFunction = null;


        try {
            // InternalMDEOptimise.g:751:58: (iv_ruleParameterFunction= ruleParameterFunction EOF )
            // InternalMDEOptimise.g:752:2: iv_ruleParameterFunction= ruleParameterFunction EOF
            {
             newCompositeNode(grammarAccess.getParameterFunctionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleParameterFunction=ruleParameterFunction();

            state._fsp--;

             current =iv_ruleParameterFunction; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleParameterFunction"


    // $ANTLR start "ruleParameterFunction"
    // InternalMDEOptimise.g:758:1: ruleParameterFunction returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')' )? ) ;
    public final EObject ruleParameterFunction() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        Token lv_parameter_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:764:2: ( ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')' )? ) )
            // InternalMDEOptimise.g:765:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')' )? )
            {
            // InternalMDEOptimise.g:765:2: ( ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')' )? )
            // InternalMDEOptimise.g:766:3: ( (lv_name_0_0= RULE_ID ) ) (otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')' )?
            {
            // InternalMDEOptimise.g:766:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalMDEOptimise.g:767:4: (lv_name_0_0= RULE_ID )
            {
            // InternalMDEOptimise.g:767:4: (lv_name_0_0= RULE_ID )
            // InternalMDEOptimise.g:768:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_22); 

            					newLeafNode(lv_name_0_0, grammarAccess.getParameterFunctionAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getParameterFunctionRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMDEOptimise.g:784:3: (otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==31) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalMDEOptimise.g:785:4: otherlv_1= '(' ( (lv_parameter_2_0= RULE_STRING ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,31,FOLLOW_14); 

                    				newLeafNode(otherlv_1, grammarAccess.getParameterFunctionAccess().getLeftParenthesisKeyword_1_0());
                    			
                    // InternalMDEOptimise.g:789:4: ( (lv_parameter_2_0= RULE_STRING ) )
                    // InternalMDEOptimise.g:790:5: (lv_parameter_2_0= RULE_STRING )
                    {
                    // InternalMDEOptimise.g:790:5: (lv_parameter_2_0= RULE_STRING )
                    // InternalMDEOptimise.g:791:6: lv_parameter_2_0= RULE_STRING
                    {
                    lv_parameter_2_0=(Token)match(input,RULE_STRING,FOLLOW_23); 

                    						newLeafNode(lv_parameter_2_0, grammarAccess.getParameterFunctionAccess().getParameterSTRINGTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getParameterFunctionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"parameter",
                    							lv_parameter_2_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }

                    otherlv_3=(Token)match(input,32,FOLLOW_2); 

                    				newLeafNode(otherlv_3, grammarAccess.getParameterFunctionAccess().getRightParenthesisKeyword_1_2());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleParameterFunction"


    // $ANTLR start "entryRuleOptimisationSpec"
    // InternalMDEOptimise.g:816:1: entryRuleOptimisationSpec returns [EObject current=null] : iv_ruleOptimisationSpec= ruleOptimisationSpec EOF ;
    public final EObject entryRuleOptimisationSpec() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOptimisationSpec = null;


        try {
            // InternalMDEOptimise.g:816:57: (iv_ruleOptimisationSpec= ruleOptimisationSpec EOF )
            // InternalMDEOptimise.g:817:2: iv_ruleOptimisationSpec= ruleOptimisationSpec EOF
            {
             newCompositeNode(grammarAccess.getOptimisationSpecRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptimisationSpec=ruleOptimisationSpec();

            state._fsp--;

             current =iv_ruleOptimisationSpec; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOptimisationSpec"


    // $ANTLR start "ruleOptimisationSpec"
    // InternalMDEOptimise.g:823:1: ruleOptimisationSpec returns [EObject current=null] : (otherlv_0= 'optimisation' otherlv_1= 'provider' ( (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY ) ) otherlv_3= 'algorithm' ( (lv_algorithmName_4_0= RULE_ALGORITHM_NAME ) ) otherlv_5= 'variation' ( (lv_algorithmVariation_6_0= ruleAlgorithmVariation ) ) otherlv_7= 'evolutions' ( (lv_algorithmEvolutions_8_0= RULE_INT ) ) otherlv_9= 'population' ( (lv_algorithmPopulation_10_0= RULE_INT ) ) (otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) ) )? ) ;
    public final EObject ruleOptimisationSpec() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_algorithmFactory_2_0=null;
        Token otherlv_3=null;
        Token lv_algorithmName_4_0=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token lv_algorithmEvolutions_8_0=null;
        Token otherlv_9=null;
        Token lv_algorithmPopulation_10_0=null;
        Token otherlv_11=null;
        Token lv_algorithmExperiments_12_0=null;
        EObject lv_algorithmVariation_6_0 = null;



        	enterRule();

        try {
            // InternalMDEOptimise.g:829:2: ( (otherlv_0= 'optimisation' otherlv_1= 'provider' ( (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY ) ) otherlv_3= 'algorithm' ( (lv_algorithmName_4_0= RULE_ALGORITHM_NAME ) ) otherlv_5= 'variation' ( (lv_algorithmVariation_6_0= ruleAlgorithmVariation ) ) otherlv_7= 'evolutions' ( (lv_algorithmEvolutions_8_0= RULE_INT ) ) otherlv_9= 'population' ( (lv_algorithmPopulation_10_0= RULE_INT ) ) (otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) ) )? ) )
            // InternalMDEOptimise.g:830:2: (otherlv_0= 'optimisation' otherlv_1= 'provider' ( (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY ) ) otherlv_3= 'algorithm' ( (lv_algorithmName_4_0= RULE_ALGORITHM_NAME ) ) otherlv_5= 'variation' ( (lv_algorithmVariation_6_0= ruleAlgorithmVariation ) ) otherlv_7= 'evolutions' ( (lv_algorithmEvolutions_8_0= RULE_INT ) ) otherlv_9= 'population' ( (lv_algorithmPopulation_10_0= RULE_INT ) ) (otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) ) )? )
            {
            // InternalMDEOptimise.g:830:2: (otherlv_0= 'optimisation' otherlv_1= 'provider' ( (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY ) ) otherlv_3= 'algorithm' ( (lv_algorithmName_4_0= RULE_ALGORITHM_NAME ) ) otherlv_5= 'variation' ( (lv_algorithmVariation_6_0= ruleAlgorithmVariation ) ) otherlv_7= 'evolutions' ( (lv_algorithmEvolutions_8_0= RULE_INT ) ) otherlv_9= 'population' ( (lv_algorithmPopulation_10_0= RULE_INT ) ) (otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) ) )? )
            // InternalMDEOptimise.g:831:3: otherlv_0= 'optimisation' otherlv_1= 'provider' ( (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY ) ) otherlv_3= 'algorithm' ( (lv_algorithmName_4_0= RULE_ALGORITHM_NAME ) ) otherlv_5= 'variation' ( (lv_algorithmVariation_6_0= ruleAlgorithmVariation ) ) otherlv_7= 'evolutions' ( (lv_algorithmEvolutions_8_0= RULE_INT ) ) otherlv_9= 'population' ( (lv_algorithmPopulation_10_0= RULE_INT ) ) (otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) ) )?
            {
            otherlv_0=(Token)match(input,33,FOLLOW_24); 

            			newLeafNode(otherlv_0, grammarAccess.getOptimisationSpecAccess().getOptimisationKeyword_0());
            		
            otherlv_1=(Token)match(input,34,FOLLOW_25); 

            			newLeafNode(otherlv_1, grammarAccess.getOptimisationSpecAccess().getProviderKeyword_1());
            		
            // InternalMDEOptimise.g:839:3: ( (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY ) )
            // InternalMDEOptimise.g:840:4: (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY )
            {
            // InternalMDEOptimise.g:840:4: (lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY )
            // InternalMDEOptimise.g:841:5: lv_algorithmFactory_2_0= RULE_ALGORITHM_FACTORY
            {
            lv_algorithmFactory_2_0=(Token)match(input,RULE_ALGORITHM_FACTORY,FOLLOW_26); 

            					newLeafNode(lv_algorithmFactory_2_0, grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryALGORITHM_FACTORYTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOptimisationSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"algorithmFactory",
            						lv_algorithmFactory_2_0,
            						"uk.ac.kcl.MDEOptimise.ALGORITHM_FACTORY");
            				

            }


            }

            otherlv_3=(Token)match(input,35,FOLLOW_27); 

            			newLeafNode(otherlv_3, grammarAccess.getOptimisationSpecAccess().getAlgorithmKeyword_3());
            		
            // InternalMDEOptimise.g:861:3: ( (lv_algorithmName_4_0= RULE_ALGORITHM_NAME ) )
            // InternalMDEOptimise.g:862:4: (lv_algorithmName_4_0= RULE_ALGORITHM_NAME )
            {
            // InternalMDEOptimise.g:862:4: (lv_algorithmName_4_0= RULE_ALGORITHM_NAME )
            // InternalMDEOptimise.g:863:5: lv_algorithmName_4_0= RULE_ALGORITHM_NAME
            {
            lv_algorithmName_4_0=(Token)match(input,RULE_ALGORITHM_NAME,FOLLOW_28); 

            					newLeafNode(lv_algorithmName_4_0, grammarAccess.getOptimisationSpecAccess().getAlgorithmNameALGORITHM_NAMETerminalRuleCall_4_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOptimisationSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"algorithmName",
            						lv_algorithmName_4_0,
            						"uk.ac.kcl.MDEOptimise.ALGORITHM_NAME");
            				

            }


            }

            otherlv_5=(Token)match(input,36,FOLLOW_29); 

            			newLeafNode(otherlv_5, grammarAccess.getOptimisationSpecAccess().getVariationKeyword_5());
            		
            // InternalMDEOptimise.g:883:3: ( (lv_algorithmVariation_6_0= ruleAlgorithmVariation ) )
            // InternalMDEOptimise.g:884:4: (lv_algorithmVariation_6_0= ruleAlgorithmVariation )
            {
            // InternalMDEOptimise.g:884:4: (lv_algorithmVariation_6_0= ruleAlgorithmVariation )
            // InternalMDEOptimise.g:885:5: lv_algorithmVariation_6_0= ruleAlgorithmVariation
            {

            					newCompositeNode(grammarAccess.getOptimisationSpecAccess().getAlgorithmVariationAlgorithmVariationParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_30);
            lv_algorithmVariation_6_0=ruleAlgorithmVariation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getOptimisationSpecRule());
            					}
            					set(
            						current,
            						"algorithmVariation",
            						lv_algorithmVariation_6_0,
            						"uk.ac.kcl.MDEOptimise.AlgorithmVariation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,37,FOLLOW_31); 

            			newLeafNode(otherlv_7, grammarAccess.getOptimisationSpecAccess().getEvolutionsKeyword_7());
            		
            // InternalMDEOptimise.g:906:3: ( (lv_algorithmEvolutions_8_0= RULE_INT ) )
            // InternalMDEOptimise.g:907:4: (lv_algorithmEvolutions_8_0= RULE_INT )
            {
            // InternalMDEOptimise.g:907:4: (lv_algorithmEvolutions_8_0= RULE_INT )
            // InternalMDEOptimise.g:908:5: lv_algorithmEvolutions_8_0= RULE_INT
            {
            lv_algorithmEvolutions_8_0=(Token)match(input,RULE_INT,FOLLOW_32); 

            					newLeafNode(lv_algorithmEvolutions_8_0, grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsINTTerminalRuleCall_8_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOptimisationSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"algorithmEvolutions",
            						lv_algorithmEvolutions_8_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_9=(Token)match(input,38,FOLLOW_31); 

            			newLeafNode(otherlv_9, grammarAccess.getOptimisationSpecAccess().getPopulationKeyword_9());
            		
            // InternalMDEOptimise.g:928:3: ( (lv_algorithmPopulation_10_0= RULE_INT ) )
            // InternalMDEOptimise.g:929:4: (lv_algorithmPopulation_10_0= RULE_INT )
            {
            // InternalMDEOptimise.g:929:4: (lv_algorithmPopulation_10_0= RULE_INT )
            // InternalMDEOptimise.g:930:5: lv_algorithmPopulation_10_0= RULE_INT
            {
            lv_algorithmPopulation_10_0=(Token)match(input,RULE_INT,FOLLOW_33); 

            					newLeafNode(lv_algorithmPopulation_10_0, grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationINTTerminalRuleCall_10_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getOptimisationSpecRule());
            					}
            					setWithLastConsumed(
            						current,
            						"algorithmPopulation",
            						lv_algorithmPopulation_10_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            // InternalMDEOptimise.g:946:3: (otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==39) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalMDEOptimise.g:947:4: otherlv_11= 'experiments' ( (lv_algorithmExperiments_12_0= RULE_INT ) )
                    {
                    otherlv_11=(Token)match(input,39,FOLLOW_31); 

                    				newLeafNode(otherlv_11, grammarAccess.getOptimisationSpecAccess().getExperimentsKeyword_11_0());
                    			
                    // InternalMDEOptimise.g:951:4: ( (lv_algorithmExperiments_12_0= RULE_INT ) )
                    // InternalMDEOptimise.g:952:5: (lv_algorithmExperiments_12_0= RULE_INT )
                    {
                    // InternalMDEOptimise.g:952:5: (lv_algorithmExperiments_12_0= RULE_INT )
                    // InternalMDEOptimise.g:953:6: lv_algorithmExperiments_12_0= RULE_INT
                    {
                    lv_algorithmExperiments_12_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_algorithmExperiments_12_0, grammarAccess.getOptimisationSpecAccess().getAlgorithmExperimentsINTTerminalRuleCall_11_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getOptimisationSpecRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"algorithmExperiments",
                    							lv_algorithmExperiments_12_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOptimisationSpec"


    // $ANTLR start "entryRuleAlgorithmVariation"
    // InternalMDEOptimise.g:974:1: entryRuleAlgorithmVariation returns [EObject current=null] : iv_ruleAlgorithmVariation= ruleAlgorithmVariation EOF ;
    public final EObject entryRuleAlgorithmVariation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAlgorithmVariation = null;


        try {
            // InternalMDEOptimise.g:974:59: (iv_ruleAlgorithmVariation= ruleAlgorithmVariation EOF )
            // InternalMDEOptimise.g:975:2: iv_ruleAlgorithmVariation= ruleAlgorithmVariation EOF
            {
             newCompositeNode(grammarAccess.getAlgorithmVariationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAlgorithmVariation=ruleAlgorithmVariation();

            state._fsp--;

             current =iv_ruleAlgorithmVariation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAlgorithmVariation"


    // $ANTLR start "ruleAlgorithmVariation"
    // InternalMDEOptimise.g:981:1: ruleAlgorithmVariation returns [EObject current=null] : ( ( (lv_probabilityVariation_0_0= ruleProbabilityVariation ) ) | ( ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) ) ) ) ;
    public final EObject ruleAlgorithmVariation() throws RecognitionException {
        EObject current = null;

        Token lv_simpleVariation_1_1=null;
        Token lv_simpleVariation_1_2=null;
        EObject lv_probabilityVariation_0_0 = null;



        	enterRule();

        try {
            // InternalMDEOptimise.g:987:2: ( ( ( (lv_probabilityVariation_0_0= ruleProbabilityVariation ) ) | ( ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) ) ) ) )
            // InternalMDEOptimise.g:988:2: ( ( (lv_probabilityVariation_0_0= ruleProbabilityVariation ) ) | ( ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) ) ) )
            {
            // InternalMDEOptimise.g:988:2: ( ( (lv_probabilityVariation_0_0= ruleProbabilityVariation ) ) | ( ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) ) ) )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_GENETIC_VARIATION) ) {
                alt10=1;
            }
            else if ( ((LA10_0>=RULE_MUTATION_VARIATION && LA10_0<=RULE_CROSSOVER_VARIATION)) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalMDEOptimise.g:989:3: ( (lv_probabilityVariation_0_0= ruleProbabilityVariation ) )
                    {
                    // InternalMDEOptimise.g:989:3: ( (lv_probabilityVariation_0_0= ruleProbabilityVariation ) )
                    // InternalMDEOptimise.g:990:4: (lv_probabilityVariation_0_0= ruleProbabilityVariation )
                    {
                    // InternalMDEOptimise.g:990:4: (lv_probabilityVariation_0_0= ruleProbabilityVariation )
                    // InternalMDEOptimise.g:991:5: lv_probabilityVariation_0_0= ruleProbabilityVariation
                    {

                    					newCompositeNode(grammarAccess.getAlgorithmVariationAccess().getProbabilityVariationProbabilityVariationParserRuleCall_0_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_probabilityVariation_0_0=ruleProbabilityVariation();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getAlgorithmVariationRule());
                    					}
                    					set(
                    						current,
                    						"probabilityVariation",
                    						lv_probabilityVariation_0_0,
                    						"uk.ac.kcl.MDEOptimise.ProbabilityVariation");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1009:3: ( ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) ) )
                    {
                    // InternalMDEOptimise.g:1009:3: ( ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) ) )
                    // InternalMDEOptimise.g:1010:4: ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) )
                    {
                    // InternalMDEOptimise.g:1010:4: ( (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION ) )
                    // InternalMDEOptimise.g:1011:5: (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION )
                    {
                    // InternalMDEOptimise.g:1011:5: (lv_simpleVariation_1_1= RULE_MUTATION_VARIATION | lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION )
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==RULE_MUTATION_VARIATION) ) {
                        alt9=1;
                    }
                    else if ( (LA9_0==RULE_CROSSOVER_VARIATION) ) {
                        alt9=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 9, 0, input);

                        throw nvae;
                    }
                    switch (alt9) {
                        case 1 :
                            // InternalMDEOptimise.g:1012:6: lv_simpleVariation_1_1= RULE_MUTATION_VARIATION
                            {
                            lv_simpleVariation_1_1=(Token)match(input,RULE_MUTATION_VARIATION,FOLLOW_2); 

                            						newLeafNode(lv_simpleVariation_1_1, grammarAccess.getAlgorithmVariationAccess().getSimpleVariationMUTATION_VARIATIONTerminalRuleCall_1_0_0());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getAlgorithmVariationRule());
                            						}
                            						setWithLastConsumed(
                            							current,
                            							"simpleVariation",
                            							lv_simpleVariation_1_1,
                            							"uk.ac.kcl.MDEOptimise.MUTATION_VARIATION");
                            					

                            }
                            break;
                        case 2 :
                            // InternalMDEOptimise.g:1027:6: lv_simpleVariation_1_2= RULE_CROSSOVER_VARIATION
                            {
                            lv_simpleVariation_1_2=(Token)match(input,RULE_CROSSOVER_VARIATION,FOLLOW_2); 

                            						newLeafNode(lv_simpleVariation_1_2, grammarAccess.getAlgorithmVariationAccess().getSimpleVariationCROSSOVER_VARIATIONTerminalRuleCall_1_0_1());
                            					

                            						if (current==null) {
                            							current = createModelElement(grammarAccess.getAlgorithmVariationRule());
                            						}
                            						setWithLastConsumed(
                            							current,
                            							"simpleVariation",
                            							lv_simpleVariation_1_2,
                            							"uk.ac.kcl.MDEOptimise.CROSSOVER_VARIATION");
                            					

                            }
                            break;

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAlgorithmVariation"


    // $ANTLR start "entryRuleProbabilityVariation"
    // InternalMDEOptimise.g:1048:1: entryRuleProbabilityVariation returns [EObject current=null] : iv_ruleProbabilityVariation= ruleProbabilityVariation EOF ;
    public final EObject entryRuleProbabilityVariation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProbabilityVariation = null;


        try {
            // InternalMDEOptimise.g:1048:61: (iv_ruleProbabilityVariation= ruleProbabilityVariation EOF )
            // InternalMDEOptimise.g:1049:2: iv_ruleProbabilityVariation= ruleProbabilityVariation EOF
            {
             newCompositeNode(grammarAccess.getProbabilityVariationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProbabilityVariation=ruleProbabilityVariation();

            state._fsp--;

             current =iv_ruleProbabilityVariation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProbabilityVariation"


    // $ANTLR start "ruleProbabilityVariation"
    // InternalMDEOptimise.g:1055:1: ruleProbabilityVariation returns [EObject current=null] : ( ( (lv_type_0_0= RULE_GENETIC_VARIATION ) ) (otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')' )? ) ;
    public final EObject ruleProbabilityVariation() throws RecognitionException {
        EObject current = null;

        Token lv_type_0_0=null;
        Token otherlv_1=null;
        Token lv_crossover_rate_2_0=null;
        Token otherlv_3=null;
        Token lv_mutation_rate_4_0=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:1061:2: ( ( ( (lv_type_0_0= RULE_GENETIC_VARIATION ) ) (otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')' )? ) )
            // InternalMDEOptimise.g:1062:2: ( ( (lv_type_0_0= RULE_GENETIC_VARIATION ) ) (otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')' )? )
            {
            // InternalMDEOptimise.g:1062:2: ( ( (lv_type_0_0= RULE_GENETIC_VARIATION ) ) (otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')' )? )
            // InternalMDEOptimise.g:1063:3: ( (lv_type_0_0= RULE_GENETIC_VARIATION ) ) (otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')' )?
            {
            // InternalMDEOptimise.g:1063:3: ( (lv_type_0_0= RULE_GENETIC_VARIATION ) )
            // InternalMDEOptimise.g:1064:4: (lv_type_0_0= RULE_GENETIC_VARIATION )
            {
            // InternalMDEOptimise.g:1064:4: (lv_type_0_0= RULE_GENETIC_VARIATION )
            // InternalMDEOptimise.g:1065:5: lv_type_0_0= RULE_GENETIC_VARIATION
            {
            lv_type_0_0=(Token)match(input,RULE_GENETIC_VARIATION,FOLLOW_22); 

            					newLeafNode(lv_type_0_0, grammarAccess.getProbabilityVariationAccess().getTypeGENETIC_VARIATIONTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getProbabilityVariationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"type",
            						lv_type_0_0,
            						"uk.ac.kcl.MDEOptimise.GENETIC_VARIATION");
            				

            }


            }

            // InternalMDEOptimise.g:1081:3: (otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==31) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalMDEOptimise.g:1082:4: otherlv_1= '(' ( (lv_crossover_rate_2_0= RULE_INT ) ) otherlv_3= ',' ( (lv_mutation_rate_4_0= RULE_INT ) ) otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,31,FOLLOW_31); 

                    				newLeafNode(otherlv_1, grammarAccess.getProbabilityVariationAccess().getLeftParenthesisKeyword_1_0());
                    			
                    // InternalMDEOptimise.g:1086:4: ( (lv_crossover_rate_2_0= RULE_INT ) )
                    // InternalMDEOptimise.g:1087:5: (lv_crossover_rate_2_0= RULE_INT )
                    {
                    // InternalMDEOptimise.g:1087:5: (lv_crossover_rate_2_0= RULE_INT )
                    // InternalMDEOptimise.g:1088:6: lv_crossover_rate_2_0= RULE_INT
                    {
                    lv_crossover_rate_2_0=(Token)match(input,RULE_INT,FOLLOW_34); 

                    						newLeafNode(lv_crossover_rate_2_0, grammarAccess.getProbabilityVariationAccess().getCrossover_rateINTTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProbabilityVariationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"crossover_rate",
                    							lv_crossover_rate_2_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_3=(Token)match(input,29,FOLLOW_31); 

                    				newLeafNode(otherlv_3, grammarAccess.getProbabilityVariationAccess().getCommaKeyword_1_2());
                    			
                    // InternalMDEOptimise.g:1108:4: ( (lv_mutation_rate_4_0= RULE_INT ) )
                    // InternalMDEOptimise.g:1109:5: (lv_mutation_rate_4_0= RULE_INT )
                    {
                    // InternalMDEOptimise.g:1109:5: (lv_mutation_rate_4_0= RULE_INT )
                    // InternalMDEOptimise.g:1110:6: lv_mutation_rate_4_0= RULE_INT
                    {
                    lv_mutation_rate_4_0=(Token)match(input,RULE_INT,FOLLOW_23); 

                    						newLeafNode(lv_mutation_rate_4_0, grammarAccess.getProbabilityVariationAccess().getMutation_rateINTTerminalRuleCall_1_3_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getProbabilityVariationRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"mutation_rate",
                    							lv_mutation_rate_4_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }

                    otherlv_5=(Token)match(input,32,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getProbabilityVariationAccess().getRightParenthesisKeyword_1_4());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProbabilityVariation"


    // $ANTLR start "ruleEvolverType"
    // InternalMDEOptimise.g:1135:1: ruleEvolverType returns [Enumerator current=null] : ( (enumLiteral_0= 'mutate' ) | (enumLiteral_1= 'breed' ) ) ;
    public final Enumerator ruleEvolverType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalMDEOptimise.g:1141:2: ( ( (enumLiteral_0= 'mutate' ) | (enumLiteral_1= 'breed' ) ) )
            // InternalMDEOptimise.g:1142:2: ( (enumLiteral_0= 'mutate' ) | (enumLiteral_1= 'breed' ) )
            {
            // InternalMDEOptimise.g:1142:2: ( (enumLiteral_0= 'mutate' ) | (enumLiteral_1= 'breed' ) )
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==40) ) {
                alt12=1;
            }
            else if ( (LA12_0==41) ) {
                alt12=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }
            switch (alt12) {
                case 1 :
                    // InternalMDEOptimise.g:1143:3: (enumLiteral_0= 'mutate' )
                    {
                    // InternalMDEOptimise.g:1143:3: (enumLiteral_0= 'mutate' )
                    // InternalMDEOptimise.g:1144:4: enumLiteral_0= 'mutate'
                    {
                    enumLiteral_0=(Token)match(input,40,FOLLOW_2); 

                    				current = grammarAccess.getEvolverTypeAccess().getMUTATEEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getEvolverTypeAccess().getMUTATEEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1151:3: (enumLiteral_1= 'breed' )
                    {
                    // InternalMDEOptimise.g:1151:3: (enumLiteral_1= 'breed' )
                    // InternalMDEOptimise.g:1152:4: enumLiteral_1= 'breed'
                    {
                    enumLiteral_1=(Token)match(input,41,FOLLOW_2); 

                    				current = grammarAccess.getEvolverTypeAccess().getBREEDEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getEvolverTypeAccess().getBREEDEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEvolverType"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000030002400000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000030002000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000030202000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000010000002L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000021000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000120L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000000007000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000020000000L});

}