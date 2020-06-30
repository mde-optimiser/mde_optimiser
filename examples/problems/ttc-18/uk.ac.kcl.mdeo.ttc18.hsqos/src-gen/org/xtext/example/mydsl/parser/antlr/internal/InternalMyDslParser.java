package org.xtext.example.mydsl.parser.antlr.internal;

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
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_UNIT", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'container'", "'resource'", "'type'", "'{'", "'using'", "'property'", "'}'", "':'", "'='", "'meta'", "'component'", "'contract'", "'requires component'", "'of'", "'requires resource'", "'with'", "'request'", "'for'", "'minimize'", "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'^'", "'.'", "'solution'", "'->'", "'-->'", "'requiring'", "'providing'", "'<'", "'<='", "'!='", "'>='", "'>'", "'sum'", "'max'"
    };
    public static final int T__50=50;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=7;
    public static final int RULE_UNIT=5;
    public static final int RULE_SL_COMMENT=9;
    public static final int T__37=37;
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
    public static final int RULE_WS=10;
    public static final int RULE_ANY_OTHER=11;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalMyDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMyDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMyDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMyDsl.g"; }



     	private MyDslGrammarAccess grammarAccess;

        public InternalMyDslParser(TokenStream input, MyDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected MyDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalMyDsl.g:65:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalMyDsl.g:65:46: (iv_ruleModel= ruleModel EOF )
            // InternalMyDsl.g:66:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
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
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMyDsl.g:72:1: ruleModel returns [EObject current=null] : ( ( (lv_hardwareModel_0_0= ruleHardwareModel ) ) ( (lv_softwareModel_1_0= ruleSoftwareModel ) ) ( (lv_requests_2_0= ruleRequest ) )+ ( (lv_objective_3_0= ruleObjective ) ) ( (lv_solution_4_0= ruleSolution ) )? ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject lv_hardwareModel_0_0 = null;

        EObject lv_softwareModel_1_0 = null;

        EObject lv_requests_2_0 = null;

        EObject lv_objective_3_0 = null;

        EObject lv_solution_4_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:78:2: ( ( ( (lv_hardwareModel_0_0= ruleHardwareModel ) ) ( (lv_softwareModel_1_0= ruleSoftwareModel ) ) ( (lv_requests_2_0= ruleRequest ) )+ ( (lv_objective_3_0= ruleObjective ) ) ( (lv_solution_4_0= ruleSolution ) )? ) )
            // InternalMyDsl.g:79:2: ( ( (lv_hardwareModel_0_0= ruleHardwareModel ) ) ( (lv_softwareModel_1_0= ruleSoftwareModel ) ) ( (lv_requests_2_0= ruleRequest ) )+ ( (lv_objective_3_0= ruleObjective ) ) ( (lv_solution_4_0= ruleSolution ) )? )
            {
            // InternalMyDsl.g:79:2: ( ( (lv_hardwareModel_0_0= ruleHardwareModel ) ) ( (lv_softwareModel_1_0= ruleSoftwareModel ) ) ( (lv_requests_2_0= ruleRequest ) )+ ( (lv_objective_3_0= ruleObjective ) ) ( (lv_solution_4_0= ruleSolution ) )? )
            // InternalMyDsl.g:80:3: ( (lv_hardwareModel_0_0= ruleHardwareModel ) ) ( (lv_softwareModel_1_0= ruleSoftwareModel ) ) ( (lv_requests_2_0= ruleRequest ) )+ ( (lv_objective_3_0= ruleObjective ) ) ( (lv_solution_4_0= ruleSolution ) )?
            {
            // InternalMyDsl.g:80:3: ( (lv_hardwareModel_0_0= ruleHardwareModel ) )
            // InternalMyDsl.g:81:4: (lv_hardwareModel_0_0= ruleHardwareModel )
            {
            // InternalMyDsl.g:81:4: (lv_hardwareModel_0_0= ruleHardwareModel )
            // InternalMyDsl.g:82:5: lv_hardwareModel_0_0= ruleHardwareModel
            {

            					newCompositeNode(grammarAccess.getModelAccess().getHardwareModelHardwareModelParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_3);
            lv_hardwareModel_0_0=ruleHardwareModel();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModelRule());
            					}
            					set(
            						current,
            						"hardwareModel",
            						lv_hardwareModel_0_0,
            						"org.xtext.example.mydsl.MyDsl.HardwareModel");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:99:3: ( (lv_softwareModel_1_0= ruleSoftwareModel ) )
            // InternalMyDsl.g:100:4: (lv_softwareModel_1_0= ruleSoftwareModel )
            {
            // InternalMyDsl.g:100:4: (lv_softwareModel_1_0= ruleSoftwareModel )
            // InternalMyDsl.g:101:5: lv_softwareModel_1_0= ruleSoftwareModel
            {

            					newCompositeNode(grammarAccess.getModelAccess().getSoftwareModelSoftwareModelParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_4);
            lv_softwareModel_1_0=ruleSoftwareModel();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModelRule());
            					}
            					set(
            						current,
            						"softwareModel",
            						lv_softwareModel_1_0,
            						"org.xtext.example.mydsl.MyDsl.SoftwareModel");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:118:3: ( (lv_requests_2_0= ruleRequest ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==28) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:119:4: (lv_requests_2_0= ruleRequest )
            	    {
            	    // InternalMyDsl.g:119:4: (lv_requests_2_0= ruleRequest )
            	    // InternalMyDsl.g:120:5: lv_requests_2_0= ruleRequest
            	    {

            	    					newCompositeNode(grammarAccess.getModelAccess().getRequestsRequestParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_requests_2_0=ruleRequest();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"requests",
            	    						lv_requests_2_0,
            	    						"org.xtext.example.mydsl.MyDsl.Request");
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

            // InternalMyDsl.g:137:3: ( (lv_objective_3_0= ruleObjective ) )
            // InternalMyDsl.g:138:4: (lv_objective_3_0= ruleObjective )
            {
            // InternalMyDsl.g:138:4: (lv_objective_3_0= ruleObjective )
            // InternalMyDsl.g:139:5: lv_objective_3_0= ruleObjective
            {

            					newCompositeNode(grammarAccess.getModelAccess().getObjectiveObjectiveParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_6);
            lv_objective_3_0=ruleObjective();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getModelRule());
            					}
            					set(
            						current,
            						"objective",
            						lv_objective_3_0,
            						"org.xtext.example.mydsl.MyDsl.Objective");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:156:3: ( (lv_solution_4_0= ruleSolution ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==39) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalMyDsl.g:157:4: (lv_solution_4_0= ruleSolution )
                    {
                    // InternalMyDsl.g:157:4: (lv_solution_4_0= ruleSolution )
                    // InternalMyDsl.g:158:5: lv_solution_4_0= ruleSolution
                    {

                    					newCompositeNode(grammarAccess.getModelAccess().getSolutionSolutionParserRuleCall_4_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_solution_4_0=ruleSolution();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getModelRule());
                    					}
                    					set(
                    						current,
                    						"solution",
                    						lv_solution_4_0,
                    						"org.xtext.example.mydsl.MyDsl.Solution");
                    					afterParserOrEnumRuleCall();
                    				

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
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleHardwareModel"
    // InternalMyDsl.g:179:1: entryRuleHardwareModel returns [EObject current=null] : iv_ruleHardwareModel= ruleHardwareModel EOF ;
    public final EObject entryRuleHardwareModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHardwareModel = null;


        try {
            // InternalMyDsl.g:179:54: (iv_ruleHardwareModel= ruleHardwareModel EOF )
            // InternalMyDsl.g:180:2: iv_ruleHardwareModel= ruleHardwareModel EOF
            {
             newCompositeNode(grammarAccess.getHardwareModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleHardwareModel=ruleHardwareModel();

            state._fsp--;

             current =iv_ruleHardwareModel; 
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
    // $ANTLR end "entryRuleHardwareModel"


    // $ANTLR start "ruleHardwareModel"
    // InternalMyDsl.g:186:1: ruleHardwareModel returns [EObject current=null] : ( ( (lv_properties_0_0= ruleProperty ) )* ( (lv_resourceTypes_1_0= ruleResourceType ) )+ ( (lv_resources_2_0= ruleResource ) )+ ) ;
    public final EObject ruleHardwareModel() throws RecognitionException {
        EObject current = null;

        EObject lv_properties_0_0 = null;

        EObject lv_resourceTypes_1_0 = null;

        EObject lv_resources_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:192:2: ( ( ( (lv_properties_0_0= ruleProperty ) )* ( (lv_resourceTypes_1_0= ruleResourceType ) )+ ( (lv_resources_2_0= ruleResource ) )+ ) )
            // InternalMyDsl.g:193:2: ( ( (lv_properties_0_0= ruleProperty ) )* ( (lv_resourceTypes_1_0= ruleResourceType ) )+ ( (lv_resources_2_0= ruleResource ) )+ )
            {
            // InternalMyDsl.g:193:2: ( ( (lv_properties_0_0= ruleProperty ) )* ( (lv_resourceTypes_1_0= ruleResourceType ) )+ ( (lv_resources_2_0= ruleResource ) )+ )
            // InternalMyDsl.g:194:3: ( (lv_properties_0_0= ruleProperty ) )* ( (lv_resourceTypes_1_0= ruleResourceType ) )+ ( (lv_resources_2_0= ruleResource ) )+
            {
            // InternalMyDsl.g:194:3: ( (lv_properties_0_0= ruleProperty ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==17) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMyDsl.g:195:4: (lv_properties_0_0= ruleProperty )
            	    {
            	    // InternalMyDsl.g:195:4: (lv_properties_0_0= ruleProperty )
            	    // InternalMyDsl.g:196:5: lv_properties_0_0= ruleProperty
            	    {

            	    					newCompositeNode(grammarAccess.getHardwareModelAccess().getPropertiesPropertyParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_properties_0_0=ruleProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getHardwareModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_0_0,
            	    						"org.xtext.example.mydsl.MyDsl.Property");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalMyDsl.g:213:3: ( (lv_resourceTypes_1_0= ruleResourceType ) )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    int LA4_1 = input.LA(2);

                    if ( (LA4_1==14) ) {
                        alt4=1;
                    }


                }
                else if ( (LA4_0==12) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalMyDsl.g:214:4: (lv_resourceTypes_1_0= ruleResourceType )
            	    {
            	    // InternalMyDsl.g:214:4: (lv_resourceTypes_1_0= ruleResourceType )
            	    // InternalMyDsl.g:215:5: lv_resourceTypes_1_0= ruleResourceType
            	    {

            	    					newCompositeNode(grammarAccess.getHardwareModelAccess().getResourceTypesResourceTypeParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_resourceTypes_1_0=ruleResourceType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getHardwareModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resourceTypes",
            	    						lv_resourceTypes_1_0,
            	    						"org.xtext.example.mydsl.MyDsl.ResourceType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // InternalMyDsl.g:232:3: ( (lv_resources_2_0= ruleResource ) )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==13) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalMyDsl.g:233:4: (lv_resources_2_0= ruleResource )
            	    {
            	    // InternalMyDsl.g:233:4: (lv_resources_2_0= ruleResource )
            	    // InternalMyDsl.g:234:5: lv_resources_2_0= ruleResource
            	    {

            	    					newCompositeNode(grammarAccess.getHardwareModelAccess().getResourcesResourceParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_resources_2_0=ruleResource();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getHardwareModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resources",
            	    						lv_resources_2_0,
            	    						"org.xtext.example.mydsl.MyDsl.Resource");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


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
    // $ANTLR end "ruleHardwareModel"


    // $ANTLR start "entryRuleResourceType"
    // InternalMyDsl.g:255:1: entryRuleResourceType returns [EObject current=null] : iv_ruleResourceType= ruleResourceType EOF ;
    public final EObject entryRuleResourceType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceType = null;


        try {
            // InternalMyDsl.g:255:53: (iv_ruleResourceType= ruleResourceType EOF )
            // InternalMyDsl.g:256:2: iv_ruleResourceType= ruleResourceType EOF
            {
             newCompositeNode(grammarAccess.getResourceTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResourceType=ruleResourceType();

            state._fsp--;

             current =iv_ruleResourceType; 
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
    // $ANTLR end "entryRuleResourceType"


    // $ANTLR start "ruleResourceType"
    // InternalMyDsl.g:262:1: ruleResourceType returns [EObject current=null] : ( ( (lv_container_0_0= 'container' ) )? otherlv_1= 'resource' otherlv_2= 'type' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_resourceTypes_5_0= ruleResourceType ) )* ( (lv_properties_6_0= ruleProperty ) )* (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' ) ;
    public final EObject ruleResourceType() throws RecognitionException {
        EObject current = null;

        Token lv_container_0_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_name_3_0=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        EObject lv_resourceTypes_5_0 = null;

        EObject lv_properties_6_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:268:2: ( ( ( (lv_container_0_0= 'container' ) )? otherlv_1= 'resource' otherlv_2= 'type' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_resourceTypes_5_0= ruleResourceType ) )* ( (lv_properties_6_0= ruleProperty ) )* (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' ) )
            // InternalMyDsl.g:269:2: ( ( (lv_container_0_0= 'container' ) )? otherlv_1= 'resource' otherlv_2= 'type' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_resourceTypes_5_0= ruleResourceType ) )* ( (lv_properties_6_0= ruleProperty ) )* (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' )
            {
            // InternalMyDsl.g:269:2: ( ( (lv_container_0_0= 'container' ) )? otherlv_1= 'resource' otherlv_2= 'type' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_resourceTypes_5_0= ruleResourceType ) )* ( (lv_properties_6_0= ruleProperty ) )* (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' )
            // InternalMyDsl.g:270:3: ( (lv_container_0_0= 'container' ) )? otherlv_1= 'resource' otherlv_2= 'type' ( (lv_name_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_resourceTypes_5_0= ruleResourceType ) )* ( (lv_properties_6_0= ruleProperty ) )* (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}'
            {
            // InternalMyDsl.g:270:3: ( (lv_container_0_0= 'container' ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==12) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalMyDsl.g:271:4: (lv_container_0_0= 'container' )
                    {
                    // InternalMyDsl.g:271:4: (lv_container_0_0= 'container' )
                    // InternalMyDsl.g:272:5: lv_container_0_0= 'container'
                    {
                    lv_container_0_0=(Token)match(input,12,FOLLOW_10); 

                    					newLeafNode(lv_container_0_0, grammarAccess.getResourceTypeAccess().getContainerContainerKeyword_0_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getResourceTypeRule());
                    					}
                    					setWithLastConsumed(current, "container", true, "container");
                    				

                    }


                    }
                    break;

            }

            otherlv_1=(Token)match(input,13,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getResourceTypeAccess().getResourceKeyword_1());
            		
            otherlv_2=(Token)match(input,14,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getResourceTypeAccess().getTypeKeyword_2());
            		
            // InternalMyDsl.g:292:3: ( (lv_name_3_0= RULE_ID ) )
            // InternalMyDsl.g:293:4: (lv_name_3_0= RULE_ID )
            {
            // InternalMyDsl.g:293:4: (lv_name_3_0= RULE_ID )
            // InternalMyDsl.g:294:5: lv_name_3_0= RULE_ID
            {
            lv_name_3_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_3_0, grammarAccess.getResourceTypeAccess().getNameIDTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceTypeRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_3_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_14); 

            			newLeafNode(otherlv_4, grammarAccess.getResourceTypeAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMyDsl.g:314:3: ( (lv_resourceTypes_5_0= ruleResourceType ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=12 && LA7_0<=13)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalMyDsl.g:315:4: (lv_resourceTypes_5_0= ruleResourceType )
            	    {
            	    // InternalMyDsl.g:315:4: (lv_resourceTypes_5_0= ruleResourceType )
            	    // InternalMyDsl.g:316:5: lv_resourceTypes_5_0= ruleResourceType
            	    {

            	    					newCompositeNode(grammarAccess.getResourceTypeAccess().getResourceTypesResourceTypeParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_14);
            	    lv_resourceTypes_5_0=ruleResourceType();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getResourceTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resourceTypes",
            	    						lv_resourceTypes_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.ResourceType");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalMyDsl.g:333:3: ( (lv_properties_6_0= ruleProperty ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==17) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalMyDsl.g:334:4: (lv_properties_6_0= ruleProperty )
            	    {
            	    // InternalMyDsl.g:334:4: (lv_properties_6_0= ruleProperty )
            	    // InternalMyDsl.g:335:5: lv_properties_6_0= ruleProperty
            	    {

            	    					newCompositeNode(grammarAccess.getResourceTypeAccess().getPropertiesPropertyParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_15);
            	    lv_properties_6_0=ruleProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getResourceTypeRule());
            	    					}
            	    					add(
            	    						current,
            	    						"properties",
            	    						lv_properties_6_0,
            	    						"org.xtext.example.mydsl.MyDsl.Property");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalMyDsl.g:352:3: (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==16) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalMyDsl.g:353:4: otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) )
            	    {
            	    otherlv_7=(Token)match(input,16,FOLLOW_16); 

            	    				newLeafNode(otherlv_7, grammarAccess.getResourceTypeAccess().getUsingKeyword_7_0());
            	    			
            	    otherlv_8=(Token)match(input,17,FOLLOW_12); 

            	    				newLeafNode(otherlv_8, grammarAccess.getResourceTypeAccess().getPropertyKeyword_7_1());
            	    			
            	    // InternalMyDsl.g:361:4: ( (otherlv_9= RULE_ID ) )
            	    // InternalMyDsl.g:362:5: (otherlv_9= RULE_ID )
            	    {
            	    // InternalMyDsl.g:362:5: (otherlv_9= RULE_ID )
            	    // InternalMyDsl.g:363:6: otherlv_9= RULE_ID
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getResourceTypeRule());
            	    						}
            	    					
            	    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_17); 

            	    						newLeafNode(otherlv_9, grammarAccess.getResourceTypeAccess().getPropertyReferencesPropertyCrossReference_7_2_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_10=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getResourceTypeAccess().getRightCurlyBracketKeyword_8());
            		

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
    // $ANTLR end "ruleResourceType"


    // $ANTLR start "entryRuleResource"
    // InternalMyDsl.g:383:1: entryRuleResource returns [EObject current=null] : iv_ruleResource= ruleResource EOF ;
    public final EObject entryRuleResource() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResource = null;


        try {
            // InternalMyDsl.g:383:49: (iv_ruleResource= ruleResource EOF )
            // InternalMyDsl.g:384:2: iv_ruleResource= ruleResource EOF
            {
             newCompositeNode(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResource=ruleResource();

            state._fsp--;

             current =iv_ruleResource; 
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
    // $ANTLR end "entryRuleResource"


    // $ANTLR start "ruleResource"
    // InternalMyDsl.g:390:1: ruleResource returns [EObject current=null] : (otherlv_0= 'resource' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_resources_5_0= ruleResource ) )* ( (lv_values_6_0= ruleCurrentResourceValue ) )* otherlv_7= '}' ) ;
    public final EObject ruleResource() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        EObject lv_resources_5_0 = null;

        EObject lv_values_6_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:396:2: ( (otherlv_0= 'resource' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_resources_5_0= ruleResource ) )* ( (lv_values_6_0= ruleCurrentResourceValue ) )* otherlv_7= '}' ) )
            // InternalMyDsl.g:397:2: (otherlv_0= 'resource' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_resources_5_0= ruleResource ) )* ( (lv_values_6_0= ruleCurrentResourceValue ) )* otherlv_7= '}' )
            {
            // InternalMyDsl.g:397:2: (otherlv_0= 'resource' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_resources_5_0= ruleResource ) )* ( (lv_values_6_0= ruleCurrentResourceValue ) )* otherlv_7= '}' )
            // InternalMyDsl.g:398:3: otherlv_0= 'resource' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= ':' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_resources_5_0= ruleResource ) )* ( (lv_values_6_0= ruleCurrentResourceValue ) )* otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,13,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getResourceAccess().getResourceKeyword_0());
            		
            // InternalMyDsl.g:402:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:403:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:403:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:404:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_18); 

            					newLeafNode(lv_name_1_0, grammarAccess.getResourceAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,19,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getResourceAccess().getColonKeyword_2());
            		
            // InternalMyDsl.g:424:3: ( (otherlv_3= RULE_ID ) )
            // InternalMyDsl.g:425:4: (otherlv_3= RULE_ID )
            {
            // InternalMyDsl.g:425:4: (otherlv_3= RULE_ID )
            // InternalMyDsl.g:426:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(otherlv_3, grammarAccess.getResourceAccess().getTypeResourceTypeCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_19); 

            			newLeafNode(otherlv_4, grammarAccess.getResourceAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMyDsl.g:441:3: ( (lv_resources_5_0= ruleResource ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==13) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalMyDsl.g:442:4: (lv_resources_5_0= ruleResource )
            	    {
            	    // InternalMyDsl.g:442:4: (lv_resources_5_0= ruleResource )
            	    // InternalMyDsl.g:443:5: lv_resources_5_0= ruleResource
            	    {

            	    					newCompositeNode(grammarAccess.getResourceAccess().getResourcesResourceParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_19);
            	    lv_resources_5_0=ruleResource();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getResourceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resources",
            	    						lv_resources_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.Resource");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalMyDsl.g:460:3: ( (lv_values_6_0= ruleCurrentResourceValue ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_ID) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalMyDsl.g:461:4: (lv_values_6_0= ruleCurrentResourceValue )
            	    {
            	    // InternalMyDsl.g:461:4: (lv_values_6_0= ruleCurrentResourceValue )
            	    // InternalMyDsl.g:462:5: lv_values_6_0= ruleCurrentResourceValue
            	    {

            	    					newCompositeNode(grammarAccess.getResourceAccess().getValuesCurrentResourceValueParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_20);
            	    lv_values_6_0=ruleCurrentResourceValue();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getResourceRule());
            	    					}
            	    					add(
            	    						current,
            	    						"values",
            	    						lv_values_6_0,
            	    						"org.xtext.example.mydsl.MyDsl.CurrentResourceValue");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            otherlv_7=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getResourceAccess().getRightCurlyBracketKeyword_7());
            		

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
    // $ANTLR end "ruleResource"


    // $ANTLR start "entryRuleCurrentResourceValue"
    // InternalMyDsl.g:487:1: entryRuleCurrentResourceValue returns [EObject current=null] : iv_ruleCurrentResourceValue= ruleCurrentResourceValue EOF ;
    public final EObject entryRuleCurrentResourceValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCurrentResourceValue = null;


        try {
            // InternalMyDsl.g:487:61: (iv_ruleCurrentResourceValue= ruleCurrentResourceValue EOF )
            // InternalMyDsl.g:488:2: iv_ruleCurrentResourceValue= ruleCurrentResourceValue EOF
            {
             newCompositeNode(grammarAccess.getCurrentResourceValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCurrentResourceValue=ruleCurrentResourceValue();

            state._fsp--;

             current =iv_ruleCurrentResourceValue; 
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
    // $ANTLR end "entryRuleCurrentResourceValue"


    // $ANTLR start "ruleCurrentResourceValue"
    // InternalMyDsl.g:494:1: ruleCurrentResourceValue returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleLiteralExpression ) ) ) ;
    public final EObject ruleCurrentResourceValue() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_value_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:500:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleLiteralExpression ) ) ) )
            // InternalMyDsl.g:501:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleLiteralExpression ) ) )
            {
            // InternalMyDsl.g:501:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleLiteralExpression ) ) )
            // InternalMyDsl.g:502:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '=' ( (lv_value_2_0= ruleLiteralExpression ) )
            {
            // InternalMyDsl.g:502:3: ( (otherlv_0= RULE_ID ) )
            // InternalMyDsl.g:503:4: (otherlv_0= RULE_ID )
            {
            // InternalMyDsl.g:503:4: (otherlv_0= RULE_ID )
            // InternalMyDsl.g:504:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCurrentResourceValueRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(otherlv_0, grammarAccess.getCurrentResourceValueAccess().getPropertyPropertyCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,20,FOLLOW_22); 

            			newLeafNode(otherlv_1, grammarAccess.getCurrentResourceValueAccess().getEqualsSignKeyword_1());
            		
            // InternalMyDsl.g:519:3: ( (lv_value_2_0= ruleLiteralExpression ) )
            // InternalMyDsl.g:520:4: (lv_value_2_0= ruleLiteralExpression )
            {
            // InternalMyDsl.g:520:4: (lv_value_2_0= ruleLiteralExpression )
            // InternalMyDsl.g:521:5: lv_value_2_0= ruleLiteralExpression
            {

            					newCompositeNode(grammarAccess.getCurrentResourceValueAccess().getValueLiteralExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_2_0=ruleLiteralExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCurrentResourceValueRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_2_0,
            						"org.xtext.example.mydsl.MyDsl.LiteralExpression");
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
    // $ANTLR end "ruleCurrentResourceValue"


    // $ANTLR start "entryRuleSoftwareModel"
    // InternalMyDsl.g:542:1: entryRuleSoftwareModel returns [EObject current=null] : iv_ruleSoftwareModel= ruleSoftwareModel EOF ;
    public final EObject entryRuleSoftwareModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSoftwareModel = null;


        try {
            // InternalMyDsl.g:542:54: (iv_ruleSoftwareModel= ruleSoftwareModel EOF )
            // InternalMyDsl.g:543:2: iv_ruleSoftwareModel= ruleSoftwareModel EOF
            {
             newCompositeNode(grammarAccess.getSoftwareModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSoftwareModel=ruleSoftwareModel();

            state._fsp--;

             current =iv_ruleSoftwareModel; 
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
    // $ANTLR end "entryRuleSoftwareModel"


    // $ANTLR start "ruleSoftwareModel"
    // InternalMyDsl.g:549:1: ruleSoftwareModel returns [EObject current=null] : ( ( ( (lv_properties_0_0= ruleProperty ) ) | ( (lv_parameters_1_0= ruleMetaParameter ) ) )* ( (lv_components_2_0= ruleComponent ) )+ ) ;
    public final EObject ruleSoftwareModel() throws RecognitionException {
        EObject current = null;

        EObject lv_properties_0_0 = null;

        EObject lv_parameters_1_0 = null;

        EObject lv_components_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:555:2: ( ( ( ( (lv_properties_0_0= ruleProperty ) ) | ( (lv_parameters_1_0= ruleMetaParameter ) ) )* ( (lv_components_2_0= ruleComponent ) )+ ) )
            // InternalMyDsl.g:556:2: ( ( ( (lv_properties_0_0= ruleProperty ) ) | ( (lv_parameters_1_0= ruleMetaParameter ) ) )* ( (lv_components_2_0= ruleComponent ) )+ )
            {
            // InternalMyDsl.g:556:2: ( ( ( (lv_properties_0_0= ruleProperty ) ) | ( (lv_parameters_1_0= ruleMetaParameter ) ) )* ( (lv_components_2_0= ruleComponent ) )+ )
            // InternalMyDsl.g:557:3: ( ( (lv_properties_0_0= ruleProperty ) ) | ( (lv_parameters_1_0= ruleMetaParameter ) ) )* ( (lv_components_2_0= ruleComponent ) )+
            {
            // InternalMyDsl.g:557:3: ( ( (lv_properties_0_0= ruleProperty ) ) | ( (lv_parameters_1_0= ruleMetaParameter ) ) )*
            loop12:
            do {
                int alt12=3;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==17) ) {
                    alt12=1;
                }
                else if ( (LA12_0==21) ) {
                    alt12=2;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMyDsl.g:558:4: ( (lv_properties_0_0= ruleProperty ) )
            	    {
            	    // InternalMyDsl.g:558:4: ( (lv_properties_0_0= ruleProperty ) )
            	    // InternalMyDsl.g:559:5: (lv_properties_0_0= ruleProperty )
            	    {
            	    // InternalMyDsl.g:559:5: (lv_properties_0_0= ruleProperty )
            	    // InternalMyDsl.g:560:6: lv_properties_0_0= ruleProperty
            	    {

            	    						newCompositeNode(grammarAccess.getSoftwareModelAccess().getPropertiesPropertyParserRuleCall_0_0_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_properties_0_0=ruleProperty();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getSoftwareModelRule());
            	    						}
            	    						add(
            	    							current,
            	    							"properties",
            	    							lv_properties_0_0,
            	    							"org.xtext.example.mydsl.MyDsl.Property");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;
            	case 2 :
            	    // InternalMyDsl.g:578:4: ( (lv_parameters_1_0= ruleMetaParameter ) )
            	    {
            	    // InternalMyDsl.g:578:4: ( (lv_parameters_1_0= ruleMetaParameter ) )
            	    // InternalMyDsl.g:579:5: (lv_parameters_1_0= ruleMetaParameter )
            	    {
            	    // InternalMyDsl.g:579:5: (lv_parameters_1_0= ruleMetaParameter )
            	    // InternalMyDsl.g:580:6: lv_parameters_1_0= ruleMetaParameter
            	    {

            	    						newCompositeNode(grammarAccess.getSoftwareModelAccess().getParametersMetaParameterParserRuleCall_0_1_0());
            	    					
            	    pushFollow(FOLLOW_3);
            	    lv_parameters_1_0=ruleMetaParameter();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getSoftwareModelRule());
            	    						}
            	    						add(
            	    							current,
            	    							"parameters",
            	    							lv_parameters_1_0,
            	    							"org.xtext.example.mydsl.MyDsl.MetaParameter");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // InternalMyDsl.g:598:3: ( (lv_components_2_0= ruleComponent ) )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==22) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalMyDsl.g:599:4: (lv_components_2_0= ruleComponent )
            	    {
            	    // InternalMyDsl.g:599:4: (lv_components_2_0= ruleComponent )
            	    // InternalMyDsl.g:600:5: lv_components_2_0= ruleComponent
            	    {

            	    					newCompositeNode(grammarAccess.getSoftwareModelAccess().getComponentsComponentParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_23);
            	    lv_components_2_0=ruleComponent();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSoftwareModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"components",
            	    						lv_components_2_0,
            	    						"org.xtext.example.mydsl.MyDsl.Component");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


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
    // $ANTLR end "ruleSoftwareModel"


    // $ANTLR start "entryRuleMetaParameter"
    // InternalMyDsl.g:621:1: entryRuleMetaParameter returns [EObject current=null] : iv_ruleMetaParameter= ruleMetaParameter EOF ;
    public final EObject entryRuleMetaParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaParameter = null;


        try {
            // InternalMyDsl.g:621:54: (iv_ruleMetaParameter= ruleMetaParameter EOF )
            // InternalMyDsl.g:622:2: iv_ruleMetaParameter= ruleMetaParameter EOF
            {
             newCompositeNode(grammarAccess.getMetaParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMetaParameter=ruleMetaParameter();

            state._fsp--;

             current =iv_ruleMetaParameter; 
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
    // $ANTLR end "entryRuleMetaParameter"


    // $ANTLR start "ruleMetaParameter"
    // InternalMyDsl.g:628:1: ruleMetaParameter returns [EObject current=null] : (otherlv_0= 'meta' ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleMetaParameter() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:634:2: ( (otherlv_0= 'meta' ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalMyDsl.g:635:2: (otherlv_0= 'meta' ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalMyDsl.g:635:2: (otherlv_0= 'meta' ( (lv_name_1_0= RULE_ID ) ) )
            // InternalMyDsl.g:636:3: otherlv_0= 'meta' ( (lv_name_1_0= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getMetaParameterAccess().getMetaKeyword_0());
            		
            // InternalMyDsl.g:640:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:641:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:641:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:642:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getMetaParameterAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMetaParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

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
    // $ANTLR end "ruleMetaParameter"


    // $ANTLR start "entryRuleProperty"
    // InternalMyDsl.g:662:1: entryRuleProperty returns [EObject current=null] : iv_ruleProperty= ruleProperty EOF ;
    public final EObject entryRuleProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProperty = null;


        try {
            // InternalMyDsl.g:662:49: (iv_ruleProperty= ruleProperty EOF )
            // InternalMyDsl.g:663:2: iv_ruleProperty= ruleProperty EOF
            {
             newCompositeNode(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleProperty=ruleProperty();

            state._fsp--;

             current =iv_ruleProperty; 
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
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalMyDsl.g:669:1: ruleProperty returns [EObject current=null] : (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) ( (lv_unit_2_0= RULE_UNIT ) ) ) ;
    public final EObject ruleProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_unit_2_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:675:2: ( (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) ( (lv_unit_2_0= RULE_UNIT ) ) ) )
            // InternalMyDsl.g:676:2: (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) ( (lv_unit_2_0= RULE_UNIT ) ) )
            {
            // InternalMyDsl.g:676:2: (otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) ( (lv_unit_2_0= RULE_UNIT ) ) )
            // InternalMyDsl.g:677:3: otherlv_0= 'property' ( (lv_name_1_0= RULE_ID ) ) ( (lv_unit_2_0= RULE_UNIT ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getPropertyAccess().getPropertyKeyword_0());
            		
            // InternalMyDsl.g:681:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:682:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:682:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:683:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_24); 

            					newLeafNode(lv_name_1_0, grammarAccess.getPropertyAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            // InternalMyDsl.g:699:3: ( (lv_unit_2_0= RULE_UNIT ) )
            // InternalMyDsl.g:700:4: (lv_unit_2_0= RULE_UNIT )
            {
            // InternalMyDsl.g:700:4: (lv_unit_2_0= RULE_UNIT )
            // InternalMyDsl.g:701:5: lv_unit_2_0= RULE_UNIT
            {
            lv_unit_2_0=(Token)match(input,RULE_UNIT,FOLLOW_2); 

            					newLeafNode(lv_unit_2_0, grammarAccess.getPropertyAccess().getUnitUNITTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"unit",
            						lv_unit_2_0,
            						"org.xtext.example.mydsl.MyDsl.UNIT");
            				

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
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRuleComponent"
    // InternalMyDsl.g:721:1: entryRuleComponent returns [EObject current=null] : iv_ruleComponent= ruleComponent EOF ;
    public final EObject entryRuleComponent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent = null;


        try {
            // InternalMyDsl.g:721:50: (iv_ruleComponent= ruleComponent EOF )
            // InternalMyDsl.g:722:2: iv_ruleComponent= ruleComponent EOF
            {
             newCompositeNode(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent=ruleComponent();

            state._fsp--;

             current =iv_ruleComponent; 
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
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalMyDsl.g:728:1: ruleComponent returns [EObject current=null] : (otherlv_0= 'component' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) ) )* ( (lv_implementations_6_0= ruleImplementation ) )+ (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' ) ;
    public final EObject ruleComponent() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        EObject lv_implementations_6_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:734:2: ( (otherlv_0= 'component' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) ) )* ( (lv_implementations_6_0= ruleImplementation ) )+ (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' ) )
            // InternalMyDsl.g:735:2: (otherlv_0= 'component' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) ) )* ( (lv_implementations_6_0= ruleImplementation ) )+ (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' )
            {
            // InternalMyDsl.g:735:2: (otherlv_0= 'component' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) ) )* ( (lv_implementations_6_0= ruleImplementation ) )+ (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}' )
            // InternalMyDsl.g:736:3: otherlv_0= 'component' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' (otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) ) )* ( (lv_implementations_6_0= ruleImplementation ) )+ (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )* otherlv_10= '}'
            {
            otherlv_0=(Token)match(input,22,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getComponentAccess().getComponentKeyword_0());
            		
            // InternalMyDsl.g:740:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:741:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:741:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:742:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_1_0, grammarAccess.getComponentAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_25); 

            			newLeafNode(otherlv_2, grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalMyDsl.g:762:3: (otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==16) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalMyDsl.g:763:4: otherlv_3= 'using' otherlv_4= 'property' ( (otherlv_5= RULE_ID ) )
            	    {
            	    otherlv_3=(Token)match(input,16,FOLLOW_16); 

            	    				newLeafNode(otherlv_3, grammarAccess.getComponentAccess().getUsingKeyword_3_0());
            	    			
            	    otherlv_4=(Token)match(input,17,FOLLOW_12); 

            	    				newLeafNode(otherlv_4, grammarAccess.getComponentAccess().getPropertyKeyword_3_1());
            	    			
            	    // InternalMyDsl.g:771:4: ( (otherlv_5= RULE_ID ) )
            	    // InternalMyDsl.g:772:5: (otherlv_5= RULE_ID )
            	    {
            	    // InternalMyDsl.g:772:5: (otherlv_5= RULE_ID )
            	    // InternalMyDsl.g:773:6: otherlv_5= RULE_ID
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getComponentRule());
            	    						}
            	    					
            	    otherlv_5=(Token)match(input,RULE_ID,FOLLOW_25); 

            	    						newLeafNode(otherlv_5, grammarAccess.getComponentAccess().getUsedPropertiesPropertyCrossReference_3_2_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            // InternalMyDsl.g:785:3: ( (lv_implementations_6_0= ruleImplementation ) )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==23) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalMyDsl.g:786:4: (lv_implementations_6_0= ruleImplementation )
            	    {
            	    // InternalMyDsl.g:786:4: (lv_implementations_6_0= ruleImplementation )
            	    // InternalMyDsl.g:787:5: lv_implementations_6_0= ruleImplementation
            	    {

            	    					newCompositeNode(grammarAccess.getComponentAccess().getImplementationsImplementationParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_26);
            	    lv_implementations_6_0=ruleImplementation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getComponentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"implementations",
            	    						lv_implementations_6_0,
            	    						"org.xtext.example.mydsl.MyDsl.Implementation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);

            // InternalMyDsl.g:804:3: (otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==16) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalMyDsl.g:805:4: otherlv_7= 'using' otherlv_8= 'property' ( (otherlv_9= RULE_ID ) )
            	    {
            	    otherlv_7=(Token)match(input,16,FOLLOW_16); 

            	    				newLeafNode(otherlv_7, grammarAccess.getComponentAccess().getUsingKeyword_5_0());
            	    			
            	    otherlv_8=(Token)match(input,17,FOLLOW_12); 

            	    				newLeafNode(otherlv_8, grammarAccess.getComponentAccess().getPropertyKeyword_5_1());
            	    			
            	    // InternalMyDsl.g:813:4: ( (otherlv_9= RULE_ID ) )
            	    // InternalMyDsl.g:814:5: (otherlv_9= RULE_ID )
            	    {
            	    // InternalMyDsl.g:814:5: (otherlv_9= RULE_ID )
            	    // InternalMyDsl.g:815:6: otherlv_9= RULE_ID
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getComponentRule());
            	    						}
            	    					
            	    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_17); 

            	    						newLeafNode(otherlv_9, grammarAccess.getComponentAccess().getUsedPropertiesPropertyCrossReference_5_2_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            otherlv_10=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_10, grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_6());
            		

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
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleImplementation"
    // InternalMyDsl.g:835:1: entryRuleImplementation returns [EObject current=null] : iv_ruleImplementation= ruleImplementation EOF ;
    public final EObject entryRuleImplementation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImplementation = null;


        try {
            // InternalMyDsl.g:835:55: (iv_ruleImplementation= ruleImplementation EOF )
            // InternalMyDsl.g:836:2: iv_ruleImplementation= ruleImplementation EOF
            {
             newCompositeNode(grammarAccess.getImplementationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImplementation=ruleImplementation();

            state._fsp--;

             current =iv_ruleImplementation; 
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
    // $ANTLR end "entryRuleImplementation"


    // $ANTLR start "ruleImplementation"
    // InternalMyDsl.g:842:1: ruleImplementation returns [EObject current=null] : (otherlv_0= 'contract' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_componentRequirements_3_0= ruleComponentRequirement ) )* ( (lv_resourceRequirements_4_0= ruleResourceRequirement ) )+ ( (lv_clauses_5_0= ruleClause ) )+ otherlv_6= '}' ) ;
    public final EObject ruleImplementation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_6=null;
        EObject lv_componentRequirements_3_0 = null;

        EObject lv_resourceRequirements_4_0 = null;

        EObject lv_clauses_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:848:2: ( (otherlv_0= 'contract' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_componentRequirements_3_0= ruleComponentRequirement ) )* ( (lv_resourceRequirements_4_0= ruleResourceRequirement ) )+ ( (lv_clauses_5_0= ruleClause ) )+ otherlv_6= '}' ) )
            // InternalMyDsl.g:849:2: (otherlv_0= 'contract' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_componentRequirements_3_0= ruleComponentRequirement ) )* ( (lv_resourceRequirements_4_0= ruleResourceRequirement ) )+ ( (lv_clauses_5_0= ruleClause ) )+ otherlv_6= '}' )
            {
            // InternalMyDsl.g:849:2: (otherlv_0= 'contract' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_componentRequirements_3_0= ruleComponentRequirement ) )* ( (lv_resourceRequirements_4_0= ruleResourceRequirement ) )+ ( (lv_clauses_5_0= ruleClause ) )+ otherlv_6= '}' )
            // InternalMyDsl.g:850:3: otherlv_0= 'contract' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_componentRequirements_3_0= ruleComponentRequirement ) )* ( (lv_resourceRequirements_4_0= ruleResourceRequirement ) )+ ( (lv_clauses_5_0= ruleClause ) )+ otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getImplementationAccess().getContractKeyword_0());
            		
            // InternalMyDsl.g:854:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:855:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:855:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:856:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_1_0, grammarAccess.getImplementationAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getImplementationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_27); 

            			newLeafNode(otherlv_2, grammarAccess.getImplementationAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalMyDsl.g:876:3: ( (lv_componentRequirements_3_0= ruleComponentRequirement ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==24) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalMyDsl.g:877:4: (lv_componentRequirements_3_0= ruleComponentRequirement )
            	    {
            	    // InternalMyDsl.g:877:4: (lv_componentRequirements_3_0= ruleComponentRequirement )
            	    // InternalMyDsl.g:878:5: lv_componentRequirements_3_0= ruleComponentRequirement
            	    {

            	    					newCompositeNode(grammarAccess.getImplementationAccess().getComponentRequirementsComponentRequirementParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_27);
            	    lv_componentRequirements_3_0=ruleComponentRequirement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImplementationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"componentRequirements",
            	    						lv_componentRequirements_3_0,
            	    						"org.xtext.example.mydsl.MyDsl.ComponentRequirement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

            // InternalMyDsl.g:895:3: ( (lv_resourceRequirements_4_0= ruleResourceRequirement ) )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_ID||LA18_0==26) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalMyDsl.g:896:4: (lv_resourceRequirements_4_0= ruleResourceRequirement )
            	    {
            	    // InternalMyDsl.g:896:4: (lv_resourceRequirements_4_0= ruleResourceRequirement )
            	    // InternalMyDsl.g:897:5: lv_resourceRequirements_4_0= ruleResourceRequirement
            	    {

            	    					newCompositeNode(grammarAccess.getImplementationAccess().getResourceRequirementsResourceRequirementParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_28);
            	    lv_resourceRequirements_4_0=ruleResourceRequirement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImplementationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resourceRequirements",
            	    						lv_resourceRequirements_4_0,
            	    						"org.xtext.example.mydsl.MyDsl.ResourceRequirement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);

            // InternalMyDsl.g:914:3: ( (lv_clauses_5_0= ruleClause ) )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( ((LA19_0>=42 && LA19_0<=43)) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalMyDsl.g:915:4: (lv_clauses_5_0= ruleClause )
            	    {
            	    // InternalMyDsl.g:915:4: (lv_clauses_5_0= ruleClause )
            	    // InternalMyDsl.g:916:5: lv_clauses_5_0= ruleClause
            	    {

            	    					newCompositeNode(grammarAccess.getImplementationAccess().getClausesClauseParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_clauses_5_0=ruleClause();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImplementationRule());
            	    					}
            	    					add(
            	    						current,
            	    						"clauses",
            	    						lv_clauses_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.Clause");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);

            otherlv_6=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getImplementationAccess().getRightCurlyBracketKeyword_6());
            		

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
    // $ANTLR end "ruleImplementation"


    // $ANTLR start "entryRuleInstance"
    // InternalMyDsl.g:941:1: entryRuleInstance returns [EObject current=null] : iv_ruleInstance= ruleInstance EOF ;
    public final EObject entryRuleInstance() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInstance = null;


        try {
            // InternalMyDsl.g:941:49: (iv_ruleInstance= ruleInstance EOF )
            // InternalMyDsl.g:942:2: iv_ruleInstance= ruleInstance EOF
            {
             newCompositeNode(grammarAccess.getInstanceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInstance=ruleInstance();

            state._fsp--;

             current =iv_ruleInstance; 
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
    // $ANTLR end "entryRuleInstance"


    // $ANTLR start "ruleInstance"
    // InternalMyDsl.g:948:1: ruleInstance returns [EObject current=null] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final EObject ruleInstance() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:954:2: ( ( (lv_name_0_0= RULE_ID ) ) )
            // InternalMyDsl.g:955:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // InternalMyDsl.g:955:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalMyDsl.g:956:3: (lv_name_0_0= RULE_ID )
            {
            // InternalMyDsl.g:956:3: (lv_name_0_0= RULE_ID )
            // InternalMyDsl.g:957:4: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getInstanceAccess().getNameIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getInstanceRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"org.eclipse.xtext.common.Terminals.ID");
            			

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
    // $ANTLR end "ruleInstance"


    // $ANTLR start "entryRuleComponentRequirement"
    // InternalMyDsl.g:976:1: entryRuleComponentRequirement returns [EObject current=null] : iv_ruleComponentRequirement= ruleComponentRequirement EOF ;
    public final EObject entryRuleComponentRequirement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentRequirement = null;


        try {
            // InternalMyDsl.g:976:61: (iv_ruleComponentRequirement= ruleComponentRequirement EOF )
            // InternalMyDsl.g:977:2: iv_ruleComponentRequirement= ruleComponentRequirement EOF
            {
             newCompositeNode(grammarAccess.getComponentRequirementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponentRequirement=ruleComponentRequirement();

            state._fsp--;

             current =iv_ruleComponentRequirement; 
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
    // $ANTLR end "entryRuleComponentRequirement"


    // $ANTLR start "ruleComponentRequirement"
    // InternalMyDsl.g:983:1: ruleComponentRequirement returns [EObject current=null] : (otherlv_0= 'requires component' ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) ) ;
    public final EObject ruleComponentRequirement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_instance_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:989:2: ( (otherlv_0= 'requires component' ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) ) )
            // InternalMyDsl.g:990:2: (otherlv_0= 'requires component' ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) )
            {
            // InternalMyDsl.g:990:2: (otherlv_0= 'requires component' ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) )
            // InternalMyDsl.g:991:3: otherlv_0= 'requires component' ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,24,FOLLOW_27); 

            			newLeafNode(otherlv_0, grammarAccess.getComponentRequirementAccess().getRequiresComponentKeyword_0());
            		
            // InternalMyDsl.g:995:3: ( (lv_instance_1_0= ruleInstance ) )
            // InternalMyDsl.g:996:4: (lv_instance_1_0= ruleInstance )
            {
            // InternalMyDsl.g:996:4: (lv_instance_1_0= ruleInstance )
            // InternalMyDsl.g:997:5: lv_instance_1_0= ruleInstance
            {

            					newCompositeNode(grammarAccess.getComponentRequirementAccess().getInstanceInstanceParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_30);
            lv_instance_1_0=ruleInstance();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComponentRequirementRule());
            					}
            					set(
            						current,
            						"instance",
            						lv_instance_1_0,
            						"org.xtext.example.mydsl.MyDsl.Instance");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,25,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getComponentRequirementAccess().getOfKeyword_2());
            		
            otherlv_3=(Token)match(input,14,FOLLOW_12); 

            			newLeafNode(otherlv_3, grammarAccess.getComponentRequirementAccess().getTypeKeyword_3());
            		
            // InternalMyDsl.g:1022:3: ( (otherlv_4= RULE_ID ) )
            // InternalMyDsl.g:1023:4: (otherlv_4= RULE_ID )
            {
            // InternalMyDsl.g:1023:4: (otherlv_4= RULE_ID )
            // InternalMyDsl.g:1024:5: otherlv_4= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponentRequirementRule());
            					}
            				
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_4, grammarAccess.getComponentRequirementAccess().getTypeComponentCrossReference_4_0());
            				

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
    // $ANTLR end "ruleComponentRequirement"


    // $ANTLR start "entryRuleResourceRequirement"
    // InternalMyDsl.g:1039:1: entryRuleResourceRequirement returns [EObject current=null] : iv_ruleResourceRequirement= ruleResourceRequirement EOF ;
    public final EObject entryRuleResourceRequirement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceRequirement = null;


        try {
            // InternalMyDsl.g:1039:60: (iv_ruleResourceRequirement= ruleResourceRequirement EOF )
            // InternalMyDsl.g:1040:2: iv_ruleResourceRequirement= ruleResourceRequirement EOF
            {
             newCompositeNode(grammarAccess.getResourceRequirementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResourceRequirement=ruleResourceRequirement();

            state._fsp--;

             current =iv_ruleResourceRequirement; 
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
    // $ANTLR end "entryRuleResourceRequirement"


    // $ANTLR start "ruleResourceRequirement"
    // InternalMyDsl.g:1046:1: ruleResourceRequirement returns [EObject current=null] : ( (otherlv_0= 'requires resource' )? ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) (otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}' )? ) ;
    public final EObject ruleResourceRequirement() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        EObject lv_instance_1_0 = null;

        EObject lv_resourceRequirements_7_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1052:2: ( ( (otherlv_0= 'requires resource' )? ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) (otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}' )? ) )
            // InternalMyDsl.g:1053:2: ( (otherlv_0= 'requires resource' )? ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) (otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}' )? )
            {
            // InternalMyDsl.g:1053:2: ( (otherlv_0= 'requires resource' )? ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) (otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}' )? )
            // InternalMyDsl.g:1054:3: (otherlv_0= 'requires resource' )? ( (lv_instance_1_0= ruleInstance ) ) otherlv_2= 'of' otherlv_3= 'type' ( (otherlv_4= RULE_ID ) ) (otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}' )?
            {
            // InternalMyDsl.g:1054:3: (otherlv_0= 'requires resource' )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==26) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // InternalMyDsl.g:1055:4: otherlv_0= 'requires resource'
                    {
                    otherlv_0=(Token)match(input,26,FOLLOW_27); 

                    				newLeafNode(otherlv_0, grammarAccess.getResourceRequirementAccess().getRequiresResourceKeyword_0());
                    			

                    }
                    break;

            }

            // InternalMyDsl.g:1060:3: ( (lv_instance_1_0= ruleInstance ) )
            // InternalMyDsl.g:1061:4: (lv_instance_1_0= ruleInstance )
            {
            // InternalMyDsl.g:1061:4: (lv_instance_1_0= ruleInstance )
            // InternalMyDsl.g:1062:5: lv_instance_1_0= ruleInstance
            {

            					newCompositeNode(grammarAccess.getResourceRequirementAccess().getInstanceInstanceParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_30);
            lv_instance_1_0=ruleInstance();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getResourceRequirementRule());
            					}
            					set(
            						current,
            						"instance",
            						lv_instance_1_0,
            						"org.xtext.example.mydsl.MyDsl.Instance");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,25,FOLLOW_11); 

            			newLeafNode(otherlv_2, grammarAccess.getResourceRequirementAccess().getOfKeyword_2());
            		
            otherlv_3=(Token)match(input,14,FOLLOW_12); 

            			newLeafNode(otherlv_3, grammarAccess.getResourceRequirementAccess().getTypeKeyword_3());
            		
            // InternalMyDsl.g:1087:3: ( (otherlv_4= RULE_ID ) )
            // InternalMyDsl.g:1088:4: (otherlv_4= RULE_ID )
            {
            // InternalMyDsl.g:1088:4: (otherlv_4= RULE_ID )
            // InternalMyDsl.g:1089:5: otherlv_4= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceRequirementRule());
            					}
            				
            otherlv_4=(Token)match(input,RULE_ID,FOLLOW_31); 

            					newLeafNode(otherlv_4, grammarAccess.getResourceRequirementAccess().getTypeResourceTypeCrossReference_4_0());
            				

            }


            }

            // InternalMyDsl.g:1100:3: (otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}' )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==27) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // InternalMyDsl.g:1101:4: otherlv_5= 'with' otherlv_6= '{' ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+ otherlv_8= '}'
                    {
                    otherlv_5=(Token)match(input,27,FOLLOW_13); 

                    				newLeafNode(otherlv_5, grammarAccess.getResourceRequirementAccess().getWithKeyword_5_0());
                    			
                    otherlv_6=(Token)match(input,15,FOLLOW_27); 

                    				newLeafNode(otherlv_6, grammarAccess.getResourceRequirementAccess().getLeftCurlyBracketKeyword_5_1());
                    			
                    // InternalMyDsl.g:1109:4: ( (lv_resourceRequirements_7_0= ruleResourceRequirement ) )+
                    int cnt21=0;
                    loop21:
                    do {
                        int alt21=2;
                        int LA21_0 = input.LA(1);

                        if ( (LA21_0==RULE_ID||LA21_0==26) ) {
                            alt21=1;
                        }


                        switch (alt21) {
                    	case 1 :
                    	    // InternalMyDsl.g:1110:5: (lv_resourceRequirements_7_0= ruleResourceRequirement )
                    	    {
                    	    // InternalMyDsl.g:1110:5: (lv_resourceRequirements_7_0= ruleResourceRequirement )
                    	    // InternalMyDsl.g:1111:6: lv_resourceRequirements_7_0= ruleResourceRequirement
                    	    {

                    	    						newCompositeNode(grammarAccess.getResourceRequirementAccess().getResourceRequirementsResourceRequirementParserRuleCall_5_2_0());
                    	    					
                    	    pushFollow(FOLLOW_32);
                    	    lv_resourceRequirements_7_0=ruleResourceRequirement();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getResourceRequirementRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"resourceRequirements",
                    	    							lv_resourceRequirements_7_0,
                    	    							"org.xtext.example.mydsl.MyDsl.ResourceRequirement");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt21 >= 1 ) break loop21;
                                EarlyExitException eee =
                                    new EarlyExitException(21, input);
                                throw eee;
                        }
                        cnt21++;
                    } while (true);

                    otherlv_8=(Token)match(input,18,FOLLOW_2); 

                    				newLeafNode(otherlv_8, grammarAccess.getResourceRequirementAccess().getRightCurlyBracketKeyword_5_3());
                    			

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
    // $ANTLR end "ruleResourceRequirement"


    // $ANTLR start "entryRuleClause"
    // InternalMyDsl.g:1137:1: entryRuleClause returns [EObject current=null] : iv_ruleClause= ruleClause EOF ;
    public final EObject entryRuleClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClause = null;


        try {
            // InternalMyDsl.g:1137:47: (iv_ruleClause= ruleClause EOF )
            // InternalMyDsl.g:1138:2: iv_ruleClause= ruleClause EOF
            {
             newCompositeNode(grammarAccess.getClauseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleClause=ruleClause();

            state._fsp--;

             current =iv_ruleClause; 
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
    // $ANTLR end "entryRuleClause"


    // $ANTLR start "ruleClause"
    // InternalMyDsl.g:1144:1: ruleClause returns [EObject current=null] : ( ( (lv_type_0_0= ruleClauseType ) ) ( (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance ) ) ( (lv_comp_2_0= ruleClauseComparator ) ) ( (lv_RHS_3_0= ruleExpression ) ) ) ;
    public final EObject ruleClause() throws RecognitionException {
        EObject current = null;

        Enumerator lv_type_0_0 = null;

        EObject lv_LHS_1_0 = null;

        Enumerator lv_comp_2_0 = null;

        EObject lv_RHS_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1150:2: ( ( ( (lv_type_0_0= ruleClauseType ) ) ( (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance ) ) ( (lv_comp_2_0= ruleClauseComparator ) ) ( (lv_RHS_3_0= ruleExpression ) ) ) )
            // InternalMyDsl.g:1151:2: ( ( (lv_type_0_0= ruleClauseType ) ) ( (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance ) ) ( (lv_comp_2_0= ruleClauseComparator ) ) ( (lv_RHS_3_0= ruleExpression ) ) )
            {
            // InternalMyDsl.g:1151:2: ( ( (lv_type_0_0= ruleClauseType ) ) ( (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance ) ) ( (lv_comp_2_0= ruleClauseComparator ) ) ( (lv_RHS_3_0= ruleExpression ) ) )
            // InternalMyDsl.g:1152:3: ( (lv_type_0_0= ruleClauseType ) ) ( (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance ) ) ( (lv_comp_2_0= ruleClauseComparator ) ) ( (lv_RHS_3_0= ruleExpression ) )
            {
            // InternalMyDsl.g:1152:3: ( (lv_type_0_0= ruleClauseType ) )
            // InternalMyDsl.g:1153:4: (lv_type_0_0= ruleClauseType )
            {
            // InternalMyDsl.g:1153:4: (lv_type_0_0= ruleClauseType )
            // InternalMyDsl.g:1154:5: lv_type_0_0= ruleClauseType
            {

            					newCompositeNode(grammarAccess.getClauseAccess().getTypeClauseTypeEnumRuleCall_0_0());
            				
            pushFollow(FOLLOW_12);
            lv_type_0_0=ruleClauseType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClauseRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"org.xtext.example.mydsl.MyDsl.ClauseType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:1171:3: ( (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance ) )
            // InternalMyDsl.g:1172:4: (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance )
            {
            // InternalMyDsl.g:1172:4: (lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance )
            // InternalMyDsl.g:1173:5: lv_LHS_1_0= rulePropertyDesignatorWithOptionalInstance
            {

            					newCompositeNode(grammarAccess.getClauseAccess().getLHSPropertyDesignatorWithOptionalInstanceParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_33);
            lv_LHS_1_0=rulePropertyDesignatorWithOptionalInstance();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClauseRule());
            					}
            					set(
            						current,
            						"LHS",
            						lv_LHS_1_0,
            						"org.xtext.example.mydsl.MyDsl.PropertyDesignatorWithOptionalInstance");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:1190:3: ( (lv_comp_2_0= ruleClauseComparator ) )
            // InternalMyDsl.g:1191:4: (lv_comp_2_0= ruleClauseComparator )
            {
            // InternalMyDsl.g:1191:4: (lv_comp_2_0= ruleClauseComparator )
            // InternalMyDsl.g:1192:5: lv_comp_2_0= ruleClauseComparator
            {

            					newCompositeNode(grammarAccess.getClauseAccess().getCompClauseComparatorEnumRuleCall_2_0());
            				
            pushFollow(FOLLOW_34);
            lv_comp_2_0=ruleClauseComparator();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClauseRule());
            					}
            					set(
            						current,
            						"comp",
            						lv_comp_2_0,
            						"org.xtext.example.mydsl.MyDsl.ClauseComparator");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:1209:3: ( (lv_RHS_3_0= ruleExpression ) )
            // InternalMyDsl.g:1210:4: (lv_RHS_3_0= ruleExpression )
            {
            // InternalMyDsl.g:1210:4: (lv_RHS_3_0= ruleExpression )
            // InternalMyDsl.g:1211:5: lv_RHS_3_0= ruleExpression
            {

            					newCompositeNode(grammarAccess.getClauseAccess().getRHSExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_RHS_3_0=ruleExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getClauseRule());
            					}
            					set(
            						current,
            						"RHS",
            						lv_RHS_3_0,
            						"org.xtext.example.mydsl.MyDsl.Expression");
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
    // $ANTLR end "ruleClause"


    // $ANTLR start "entryRuleRequest"
    // InternalMyDsl.g:1232:1: entryRuleRequest returns [EObject current=null] : iv_ruleRequest= ruleRequest EOF ;
    public final EObject entryRuleRequest() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRequest = null;


        try {
            // InternalMyDsl.g:1232:48: (iv_ruleRequest= ruleRequest EOF )
            // InternalMyDsl.g:1233:2: iv_ruleRequest= ruleRequest EOF
            {
             newCompositeNode(grammarAccess.getRequestRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRequest=ruleRequest();

            state._fsp--;

             current =iv_ruleRequest; 
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
    // $ANTLR end "entryRuleRequest"


    // $ANTLR start "ruleRequest"
    // InternalMyDsl.g:1239:1: ruleRequest returns [EObject current=null] : (otherlv_0= 'request' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'for' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_parameterAssignments_5_0= ruleMetaParameterAssignment ) )* ( (lv_requirements_6_0= ruleClause ) )+ otherlv_7= '}' ) ;
    public final EObject ruleRequest() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        EObject lv_parameterAssignments_5_0 = null;

        EObject lv_requirements_6_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1245:2: ( (otherlv_0= 'request' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'for' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_parameterAssignments_5_0= ruleMetaParameterAssignment ) )* ( (lv_requirements_6_0= ruleClause ) )+ otherlv_7= '}' ) )
            // InternalMyDsl.g:1246:2: (otherlv_0= 'request' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'for' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_parameterAssignments_5_0= ruleMetaParameterAssignment ) )* ( (lv_requirements_6_0= ruleClause ) )+ otherlv_7= '}' )
            {
            // InternalMyDsl.g:1246:2: (otherlv_0= 'request' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'for' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_parameterAssignments_5_0= ruleMetaParameterAssignment ) )* ( (lv_requirements_6_0= ruleClause ) )+ otherlv_7= '}' )
            // InternalMyDsl.g:1247:3: otherlv_0= 'request' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= 'for' ( (otherlv_3= RULE_ID ) ) otherlv_4= '{' ( (lv_parameterAssignments_5_0= ruleMetaParameterAssignment ) )* ( (lv_requirements_6_0= ruleClause ) )+ otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,28,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getRequestAccess().getRequestKeyword_0());
            		
            // InternalMyDsl.g:1251:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:1252:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:1252:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:1253:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_35); 

            					newLeafNode(lv_name_1_0, grammarAccess.getRequestAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRequestRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,29,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getRequestAccess().getForKeyword_2());
            		
            // InternalMyDsl.g:1273:3: ( (otherlv_3= RULE_ID ) )
            // InternalMyDsl.g:1274:4: (otherlv_3= RULE_ID )
            {
            // InternalMyDsl.g:1274:4: (otherlv_3= RULE_ID )
            // InternalMyDsl.g:1275:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRequestRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(otherlv_3, grammarAccess.getRequestAccess().getComponentComponentCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,15,FOLLOW_36); 

            			newLeafNode(otherlv_4, grammarAccess.getRequestAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMyDsl.g:1290:3: ( (lv_parameterAssignments_5_0= ruleMetaParameterAssignment ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==21) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalMyDsl.g:1291:4: (lv_parameterAssignments_5_0= ruleMetaParameterAssignment )
            	    {
            	    // InternalMyDsl.g:1291:4: (lv_parameterAssignments_5_0= ruleMetaParameterAssignment )
            	    // InternalMyDsl.g:1292:5: lv_parameterAssignments_5_0= ruleMetaParameterAssignment
            	    {

            	    					newCompositeNode(grammarAccess.getRequestAccess().getParameterAssignmentsMetaParameterAssignmentParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_36);
            	    lv_parameterAssignments_5_0=ruleMetaParameterAssignment();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRequestRule());
            	    					}
            	    					add(
            	    						current,
            	    						"parameterAssignments",
            	    						lv_parameterAssignments_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.MetaParameterAssignment");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

            // InternalMyDsl.g:1309:3: ( (lv_requirements_6_0= ruleClause ) )+
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>=42 && LA24_0<=43)) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalMyDsl.g:1310:4: (lv_requirements_6_0= ruleClause )
            	    {
            	    // InternalMyDsl.g:1310:4: (lv_requirements_6_0= ruleClause )
            	    // InternalMyDsl.g:1311:5: lv_requirements_6_0= ruleClause
            	    {

            	    					newCompositeNode(grammarAccess.getRequestAccess().getRequirementsClauseParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_29);
            	    lv_requirements_6_0=ruleClause();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getRequestRule());
            	    					}
            	    					add(
            	    						current,
            	    						"requirements",
            	    						lv_requirements_6_0,
            	    						"org.xtext.example.mydsl.MyDsl.Clause");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
            } while (true);

            otherlv_7=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getRequestAccess().getRightCurlyBracketKeyword_7());
            		

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
    // $ANTLR end "ruleRequest"


    // $ANTLR start "entryRuleObjective"
    // InternalMyDsl.g:1336:1: entryRuleObjective returns [EObject current=null] : iv_ruleObjective= ruleObjective EOF ;
    public final EObject entryRuleObjective() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjective = null;


        try {
            // InternalMyDsl.g:1336:50: (iv_ruleObjective= ruleObjective EOF )
            // InternalMyDsl.g:1337:2: iv_ruleObjective= ruleObjective EOF
            {
             newCompositeNode(grammarAccess.getObjectiveRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjective=ruleObjective();

            state._fsp--;

             current =iv_ruleObjective; 
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
    // $ANTLR end "entryRuleObjective"


    // $ANTLR start "ruleObjective"
    // InternalMyDsl.g:1343:1: ruleObjective returns [EObject current=null] : (otherlv_0= 'minimize' ( (lv_agg_1_0= rulePropertyAggregation ) ) otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) ;
    public final EObject ruleObjective() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Enumerator lv_agg_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1349:2: ( (otherlv_0= 'minimize' ( (lv_agg_1_0= rulePropertyAggregation ) ) otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' ) )
            // InternalMyDsl.g:1350:2: (otherlv_0= 'minimize' ( (lv_agg_1_0= rulePropertyAggregation ) ) otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            {
            // InternalMyDsl.g:1350:2: (otherlv_0= 'minimize' ( (lv_agg_1_0= rulePropertyAggregation ) ) otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')' )
            // InternalMyDsl.g:1351:3: otherlv_0= 'minimize' ( (lv_agg_1_0= rulePropertyAggregation ) ) otherlv_2= '(' ( (otherlv_3= RULE_ID ) ) otherlv_4= ')'
            {
            otherlv_0=(Token)match(input,30,FOLLOW_37); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectiveAccess().getMinimizeKeyword_0());
            		
            // InternalMyDsl.g:1355:3: ( (lv_agg_1_0= rulePropertyAggregation ) )
            // InternalMyDsl.g:1356:4: (lv_agg_1_0= rulePropertyAggregation )
            {
            // InternalMyDsl.g:1356:4: (lv_agg_1_0= rulePropertyAggregation )
            // InternalMyDsl.g:1357:5: lv_agg_1_0= rulePropertyAggregation
            {

            					newCompositeNode(grammarAccess.getObjectiveAccess().getAggPropertyAggregationEnumRuleCall_1_0());
            				
            pushFollow(FOLLOW_38);
            lv_agg_1_0=rulePropertyAggregation();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getObjectiveRule());
            					}
            					set(
            						current,
            						"agg",
            						lv_agg_1_0,
            						"org.xtext.example.mydsl.MyDsl.PropertyAggregation");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,31,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getObjectiveAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:1378:3: ( (otherlv_3= RULE_ID ) )
            // InternalMyDsl.g:1379:4: (otherlv_3= RULE_ID )
            {
            // InternalMyDsl.g:1379:4: (otherlv_3= RULE_ID )
            // InternalMyDsl.g:1380:5: otherlv_3= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectiveRule());
            					}
            				
            otherlv_3=(Token)match(input,RULE_ID,FOLLOW_39); 

            					newLeafNode(otherlv_3, grammarAccess.getObjectiveAccess().getPropertyPropertyCrossReference_3_0());
            				

            }


            }

            otherlv_4=(Token)match(input,32,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getObjectiveAccess().getRightParenthesisKeyword_4());
            		

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
    // $ANTLR end "ruleObjective"


    // $ANTLR start "entryRuleExpression"
    // InternalMyDsl.g:1399:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalMyDsl.g:1399:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalMyDsl.g:1400:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
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
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalMyDsl.g:1406:1: ruleExpression returns [EObject current=null] : this_Addition_0= ruleAddition ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Addition_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1412:2: (this_Addition_0= ruleAddition )
            // InternalMyDsl.g:1413:2: this_Addition_0= ruleAddition
            {

            		newCompositeNode(grammarAccess.getExpressionAccess().getAdditionParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_Addition_0=ruleAddition();

            state._fsp--;


            		current = this_Addition_0;
            		afterParserOrEnumRuleCall();
            	

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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleAddition"
    // InternalMyDsl.g:1424:1: entryRuleAddition returns [EObject current=null] : iv_ruleAddition= ruleAddition EOF ;
    public final EObject entryRuleAddition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAddition = null;


        try {
            // InternalMyDsl.g:1424:49: (iv_ruleAddition= ruleAddition EOF )
            // InternalMyDsl.g:1425:2: iv_ruleAddition= ruleAddition EOF
            {
             newCompositeNode(grammarAccess.getAdditionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAddition=ruleAddition();

            state._fsp--;

             current =iv_ruleAddition; 
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
    // $ANTLR end "entryRuleAddition"


    // $ANTLR start "ruleAddition"
    // InternalMyDsl.g:1431:1: ruleAddition returns [EObject current=null] : (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) ) )? ) ;
    public final EObject ruleAddition() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Multiplication_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1437:2: ( (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) ) )? ) )
            // InternalMyDsl.g:1438:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) ) )? )
            {
            // InternalMyDsl.g:1438:2: (this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) ) )? )
            // InternalMyDsl.g:1439:3: this_Multiplication_0= ruleMultiplication ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) ) )?
            {

            			newCompositeNode(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0());
            		
            pushFollow(FOLLOW_40);
            this_Multiplication_0=ruleMultiplication();

            state._fsp--;


            			current = this_Multiplication_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMyDsl.g:1447:3: ( ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0>=33 && LA26_0<=34)) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // InternalMyDsl.g:1448:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) ) ( (lv_right_5_0= ruleAddition ) )
                    {
                    // InternalMyDsl.g:1448:4: ( ( () otherlv_2= '+' ) | ( () otherlv_4= '-' ) )
                    int alt25=2;
                    int LA25_0 = input.LA(1);

                    if ( (LA25_0==33) ) {
                        alt25=1;
                    }
                    else if ( (LA25_0==34) ) {
                        alt25=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 25, 0, input);

                        throw nvae;
                    }
                    switch (alt25) {
                        case 1 :
                            // InternalMyDsl.g:1449:5: ( () otherlv_2= '+' )
                            {
                            // InternalMyDsl.g:1449:5: ( () otherlv_2= '+' )
                            // InternalMyDsl.g:1450:6: () otherlv_2= '+'
                            {
                            // InternalMyDsl.g:1450:6: ()
                            // InternalMyDsl.g:1451:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getAdditionAccess().getAddExpressionLeftAction_1_0_0_0(),
                            								current);
                            						

                            }

                            otherlv_2=(Token)match(input,33,FOLLOW_34); 

                            						newLeafNode(otherlv_2, grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalMyDsl.g:1463:5: ( () otherlv_4= '-' )
                            {
                            // InternalMyDsl.g:1463:5: ( () otherlv_4= '-' )
                            // InternalMyDsl.g:1464:6: () otherlv_4= '-'
                            {
                            // InternalMyDsl.g:1464:6: ()
                            // InternalMyDsl.g:1465:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getAdditionAccess().getSubExpressionLeftAction_1_0_1_0(),
                            								current);
                            						

                            }

                            otherlv_4=(Token)match(input,34,FOLLOW_34); 

                            						newLeafNode(otherlv_4, grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1());
                            					

                            }


                            }
                            break;

                    }

                    // InternalMyDsl.g:1477:4: ( (lv_right_5_0= ruleAddition ) )
                    // InternalMyDsl.g:1478:5: (lv_right_5_0= ruleAddition )
                    {
                    // InternalMyDsl.g:1478:5: (lv_right_5_0= ruleAddition )
                    // InternalMyDsl.g:1479:6: lv_right_5_0= ruleAddition
                    {

                    						newCompositeNode(grammarAccess.getAdditionAccess().getRightAdditionParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_right_5_0=ruleAddition();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getAdditionRule());
                    						}
                    						set(
                    							current,
                    							"right",
                    							lv_right_5_0,
                    							"org.xtext.example.mydsl.MyDsl.Addition");
                    						afterParserOrEnumRuleCall();
                    					

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
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalMyDsl.g:1501:1: entryRuleMultiplication returns [EObject current=null] : iv_ruleMultiplication= ruleMultiplication EOF ;
    public final EObject entryRuleMultiplication() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMultiplication = null;


        try {
            // InternalMyDsl.g:1501:55: (iv_ruleMultiplication= ruleMultiplication EOF )
            // InternalMyDsl.g:1502:2: iv_ruleMultiplication= ruleMultiplication EOF
            {
             newCompositeNode(grammarAccess.getMultiplicationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMultiplication=ruleMultiplication();

            state._fsp--;

             current =iv_ruleMultiplication; 
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
    // $ANTLR end "entryRuleMultiplication"


    // $ANTLR start "ruleMultiplication"
    // InternalMyDsl.g:1508:1: ruleMultiplication returns [EObject current=null] : (this_Power_0= rulePower ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )? ) ;
    public final EObject ruleMultiplication() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject this_Power_0 = null;

        EObject lv_right_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1514:2: ( (this_Power_0= rulePower ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )? ) )
            // InternalMyDsl.g:1515:2: (this_Power_0= rulePower ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )? )
            {
            // InternalMyDsl.g:1515:2: (this_Power_0= rulePower ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )? )
            // InternalMyDsl.g:1516:3: this_Power_0= rulePower ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )?
            {

            			newCompositeNode(grammarAccess.getMultiplicationAccess().getPowerParserRuleCall_0());
            		
            pushFollow(FOLLOW_41);
            this_Power_0=rulePower();

            state._fsp--;


            			current = this_Power_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMyDsl.g:1524:3: ( ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( ((LA28_0>=35 && LA28_0<=36)) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalMyDsl.g:1525:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) ) ( (lv_right_5_0= ruleMultiplication ) )
                    {
                    // InternalMyDsl.g:1525:4: ( ( () otherlv_2= '*' ) | ( () otherlv_4= '/' ) )
                    int alt27=2;
                    int LA27_0 = input.LA(1);

                    if ( (LA27_0==35) ) {
                        alt27=1;
                    }
                    else if ( (LA27_0==36) ) {
                        alt27=2;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 27, 0, input);

                        throw nvae;
                    }
                    switch (alt27) {
                        case 1 :
                            // InternalMyDsl.g:1526:5: ( () otherlv_2= '*' )
                            {
                            // InternalMyDsl.g:1526:5: ( () otherlv_2= '*' )
                            // InternalMyDsl.g:1527:6: () otherlv_2= '*'
                            {
                            // InternalMyDsl.g:1527:6: ()
                            // InternalMyDsl.g:1528:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getMultiplicationAccess().getMultExpressionLeftAction_1_0_0_0(),
                            								current);
                            						

                            }

                            otherlv_2=(Token)match(input,35,FOLLOW_34); 

                            						newLeafNode(otherlv_2, grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1());
                            					

                            }


                            }
                            break;
                        case 2 :
                            // InternalMyDsl.g:1540:5: ( () otherlv_4= '/' )
                            {
                            // InternalMyDsl.g:1540:5: ( () otherlv_4= '/' )
                            // InternalMyDsl.g:1541:6: () otherlv_4= '/'
                            {
                            // InternalMyDsl.g:1541:6: ()
                            // InternalMyDsl.g:1542:7: 
                            {

                            							current = forceCreateModelElementAndSet(
                            								grammarAccess.getMultiplicationAccess().getDivExpressionLeftAction_1_0_1_0(),
                            								current);
                            						

                            }

                            otherlv_4=(Token)match(input,36,FOLLOW_34); 

                            						newLeafNode(otherlv_4, grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1());
                            					

                            }


                            }
                            break;

                    }

                    // InternalMyDsl.g:1554:4: ( (lv_right_5_0= ruleMultiplication ) )
                    // InternalMyDsl.g:1555:5: (lv_right_5_0= ruleMultiplication )
                    {
                    // InternalMyDsl.g:1555:5: (lv_right_5_0= ruleMultiplication )
                    // InternalMyDsl.g:1556:6: lv_right_5_0= ruleMultiplication
                    {

                    						newCompositeNode(grammarAccess.getMultiplicationAccess().getRightMultiplicationParserRuleCall_1_1_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_right_5_0=ruleMultiplication();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getMultiplicationRule());
                    						}
                    						set(
                    							current,
                    							"right",
                    							lv_right_5_0,
                    							"org.xtext.example.mydsl.MyDsl.Multiplication");
                    						afterParserOrEnumRuleCall();
                    					

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
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRulePower"
    // InternalMyDsl.g:1578:1: entryRulePower returns [EObject current=null] : iv_rulePower= rulePower EOF ;
    public final EObject entryRulePower() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePower = null;


        try {
            // InternalMyDsl.g:1578:46: (iv_rulePower= rulePower EOF )
            // InternalMyDsl.g:1579:2: iv_rulePower= rulePower EOF
            {
             newCompositeNode(grammarAccess.getPowerRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePower=rulePower();

            state._fsp--;

             current =iv_rulePower; 
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
    // $ANTLR end "entryRulePower"


    // $ANTLR start "rulePower"
    // InternalMyDsl.g:1585:1: rulePower returns [EObject current=null] : (this_PrimaryExpression_0= rulePrimaryExpression ( () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) ) )? ) ;
    public final EObject rulePower() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        EObject this_PrimaryExpression_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1591:2: ( (this_PrimaryExpression_0= rulePrimaryExpression ( () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) ) )? ) )
            // InternalMyDsl.g:1592:2: (this_PrimaryExpression_0= rulePrimaryExpression ( () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) ) )? )
            {
            // InternalMyDsl.g:1592:2: (this_PrimaryExpression_0= rulePrimaryExpression ( () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) ) )? )
            // InternalMyDsl.g:1593:3: this_PrimaryExpression_0= rulePrimaryExpression ( () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) ) )?
            {

            			newCompositeNode(grammarAccess.getPowerAccess().getPrimaryExpressionParserRuleCall_0());
            		
            pushFollow(FOLLOW_42);
            this_PrimaryExpression_0=rulePrimaryExpression();

            state._fsp--;


            			current = this_PrimaryExpression_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMyDsl.g:1601:3: ( () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==37) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalMyDsl.g:1602:4: () otherlv_2= '^' ( (lv_right_3_0= rulePrimaryExpression ) )
                    {
                    // InternalMyDsl.g:1602:4: ()
                    // InternalMyDsl.g:1603:5: 
                    {

                    					current = forceCreateModelElementAndSet(
                    						grammarAccess.getPowerAccess().getPowExpressionLeftAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_2=(Token)match(input,37,FOLLOW_34); 

                    				newLeafNode(otherlv_2, grammarAccess.getPowerAccess().getCircumflexAccentKeyword_1_1());
                    			
                    // InternalMyDsl.g:1613:4: ( (lv_right_3_0= rulePrimaryExpression ) )
                    // InternalMyDsl.g:1614:5: (lv_right_3_0= rulePrimaryExpression )
                    {
                    // InternalMyDsl.g:1614:5: (lv_right_3_0= rulePrimaryExpression )
                    // InternalMyDsl.g:1615:6: lv_right_3_0= rulePrimaryExpression
                    {

                    						newCompositeNode(grammarAccess.getPowerAccess().getRightPrimaryExpressionParserRuleCall_1_2_0());
                    					
                    pushFollow(FOLLOW_2);
                    lv_right_3_0=rulePrimaryExpression();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getPowerRule());
                    						}
                    						set(
                    							current,
                    							"right",
                    							lv_right_3_0,
                    							"org.xtext.example.mydsl.MyDsl.PrimaryExpression");
                    						afterParserOrEnumRuleCall();
                    					

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
    // $ANTLR end "rulePower"


    // $ANTLR start "entryRulePrimaryExpression"
    // InternalMyDsl.g:1637:1: entryRulePrimaryExpression returns [EObject current=null] : iv_rulePrimaryExpression= rulePrimaryExpression EOF ;
    public final EObject entryRulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePrimaryExpression = null;


        try {
            // InternalMyDsl.g:1637:58: (iv_rulePrimaryExpression= rulePrimaryExpression EOF )
            // InternalMyDsl.g:1638:2: iv_rulePrimaryExpression= rulePrimaryExpression EOF
            {
             newCompositeNode(grammarAccess.getPrimaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePrimaryExpression=rulePrimaryExpression();

            state._fsp--;

             current =iv_rulePrimaryExpression; 
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
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // InternalMyDsl.g:1644:1: rulePrimaryExpression returns [EObject current=null] : ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | this_Designator_3= ruleDesignator | this_LiteralExpression_4= ruleLiteralExpression ) ;
    public final EObject rulePrimaryExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        EObject this_Expression_1 = null;

        EObject this_Designator_3 = null;

        EObject this_LiteralExpression_4 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1650:2: ( ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | this_Designator_3= ruleDesignator | this_LiteralExpression_4= ruleLiteralExpression ) )
            // InternalMyDsl.g:1651:2: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | this_Designator_3= ruleDesignator | this_LiteralExpression_4= ruleLiteralExpression )
            {
            // InternalMyDsl.g:1651:2: ( (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' ) | this_Designator_3= ruleDesignator | this_LiteralExpression_4= ruleLiteralExpression )
            int alt30=3;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt30=1;
                }
                break;
            case RULE_ID:
                {
                alt30=2;
                }
                break;
            case RULE_INT:
                {
                alt30=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }

            switch (alt30) {
                case 1 :
                    // InternalMyDsl.g:1652:3: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    {
                    // InternalMyDsl.g:1652:3: (otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')' )
                    // InternalMyDsl.g:1653:4: otherlv_0= '(' this_Expression_1= ruleExpression otherlv_2= ')'
                    {
                    otherlv_0=(Token)match(input,31,FOLLOW_34); 

                    				newLeafNode(otherlv_0, grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_0_0());
                    			

                    				newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getExpressionParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_39);
                    this_Expression_1=ruleExpression();

                    state._fsp--;


                    				current = this_Expression_1;
                    				afterParserOrEnumRuleCall();
                    			
                    otherlv_2=(Token)match(input,32,FOLLOW_2); 

                    				newLeafNode(otherlv_2, grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_0_2());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1671:3: this_Designator_3= ruleDesignator
                    {

                    			newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getDesignatorParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Designator_3=ruleDesignator();

                    state._fsp--;


                    			current = this_Designator_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:1680:3: this_LiteralExpression_4= ruleLiteralExpression
                    {

                    			newCompositeNode(grammarAccess.getPrimaryExpressionAccess().getLiteralExpressionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_LiteralExpression_4=ruleLiteralExpression();

                    state._fsp--;


                    			current = this_LiteralExpression_4;
                    			afterParserOrEnumRuleCall();
                    		

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
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleDesignator"
    // InternalMyDsl.g:1692:1: entryRuleDesignator returns [EObject current=null] : iv_ruleDesignator= ruleDesignator EOF ;
    public final EObject entryRuleDesignator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDesignator = null;


        try {
            // InternalMyDsl.g:1692:51: (iv_ruleDesignator= ruleDesignator EOF )
            // InternalMyDsl.g:1693:2: iv_ruleDesignator= ruleDesignator EOF
            {
             newCompositeNode(grammarAccess.getDesignatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDesignator=ruleDesignator();

            state._fsp--;

             current =iv_ruleDesignator; 
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
    // $ANTLR end "entryRuleDesignator"


    // $ANTLR start "ruleDesignator"
    // InternalMyDsl.g:1699:1: ruleDesignator returns [EObject current=null] : (this_PropertyDesignator_0= rulePropertyDesignator | this_MetaParameterDesignator_1= ruleMetaParameterDesignator ) ;
    public final EObject ruleDesignator() throws RecognitionException {
        EObject current = null;

        EObject this_PropertyDesignator_0 = null;

        EObject this_MetaParameterDesignator_1 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1705:2: ( (this_PropertyDesignator_0= rulePropertyDesignator | this_MetaParameterDesignator_1= ruleMetaParameterDesignator ) )
            // InternalMyDsl.g:1706:2: (this_PropertyDesignator_0= rulePropertyDesignator | this_MetaParameterDesignator_1= ruleMetaParameterDesignator )
            {
            // InternalMyDsl.g:1706:2: (this_PropertyDesignator_0= rulePropertyDesignator | this_MetaParameterDesignator_1= ruleMetaParameterDesignator )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_ID) ) {
                int LA31_1 = input.LA(2);

                if ( (LA31_1==EOF||LA31_1==18||(LA31_1>=32 && LA31_1<=37)||(LA31_1>=42 && LA31_1<=43)) ) {
                    alt31=2;
                }
                else if ( (LA31_1==38) ) {
                    alt31=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 31, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;
            }
            switch (alt31) {
                case 1 :
                    // InternalMyDsl.g:1707:3: this_PropertyDesignator_0= rulePropertyDesignator
                    {

                    			newCompositeNode(grammarAccess.getDesignatorAccess().getPropertyDesignatorParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_PropertyDesignator_0=rulePropertyDesignator();

                    state._fsp--;


                    			current = this_PropertyDesignator_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1716:3: this_MetaParameterDesignator_1= ruleMetaParameterDesignator
                    {

                    			newCompositeNode(grammarAccess.getDesignatorAccess().getMetaParameterDesignatorParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_MetaParameterDesignator_1=ruleMetaParameterDesignator();

                    state._fsp--;


                    			current = this_MetaParameterDesignator_1;
                    			afterParserOrEnumRuleCall();
                    		

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
    // $ANTLR end "ruleDesignator"


    // $ANTLR start "entryRulePropertyDesignator"
    // InternalMyDsl.g:1728:1: entryRulePropertyDesignator returns [EObject current=null] : iv_rulePropertyDesignator= rulePropertyDesignator EOF ;
    public final EObject entryRulePropertyDesignator() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyDesignator = null;


        try {
            // InternalMyDsl.g:1728:59: (iv_rulePropertyDesignator= rulePropertyDesignator EOF )
            // InternalMyDsl.g:1729:2: iv_rulePropertyDesignator= rulePropertyDesignator EOF
            {
             newCompositeNode(grammarAccess.getPropertyDesignatorRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertyDesignator=rulePropertyDesignator();

            state._fsp--;

             current =iv_rulePropertyDesignator; 
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
    // $ANTLR end "entryRulePropertyDesignator"


    // $ANTLR start "rulePropertyDesignator"
    // InternalMyDsl.g:1735:1: rulePropertyDesignator returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject rulePropertyDesignator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalMyDsl.g:1741:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (otherlv_2= RULE_ID ) ) ) )
            // InternalMyDsl.g:1742:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalMyDsl.g:1742:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (otherlv_2= RULE_ID ) ) )
            // InternalMyDsl.g:1743:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' ( (otherlv_2= RULE_ID ) )
            {
            // InternalMyDsl.g:1743:3: ( (otherlv_0= RULE_ID ) )
            // InternalMyDsl.g:1744:4: (otherlv_0= RULE_ID )
            {
            // InternalMyDsl.g:1744:4: (otherlv_0= RULE_ID )
            // InternalMyDsl.g:1745:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPropertyDesignatorRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_43); 

            					newLeafNode(otherlv_0, grammarAccess.getPropertyDesignatorAccess().getInstanceInstanceCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,38,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getPropertyDesignatorAccess().getFullStopKeyword_1());
            		
            // InternalMyDsl.g:1760:3: ( (otherlv_2= RULE_ID ) )
            // InternalMyDsl.g:1761:4: (otherlv_2= RULE_ID )
            {
            // InternalMyDsl.g:1761:4: (otherlv_2= RULE_ID )
            // InternalMyDsl.g:1762:5: otherlv_2= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPropertyDesignatorRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_2, grammarAccess.getPropertyDesignatorAccess().getPropertyPropertyCrossReference_2_0());
            				

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
    // $ANTLR end "rulePropertyDesignator"


    // $ANTLR start "entryRulePropertyDesignatorWithOptionalInstance"
    // InternalMyDsl.g:1777:1: entryRulePropertyDesignatorWithOptionalInstance returns [EObject current=null] : iv_rulePropertyDesignatorWithOptionalInstance= rulePropertyDesignatorWithOptionalInstance EOF ;
    public final EObject entryRulePropertyDesignatorWithOptionalInstance() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePropertyDesignatorWithOptionalInstance = null;


        try {
            // InternalMyDsl.g:1777:79: (iv_rulePropertyDesignatorWithOptionalInstance= rulePropertyDesignatorWithOptionalInstance EOF )
            // InternalMyDsl.g:1778:2: iv_rulePropertyDesignatorWithOptionalInstance= rulePropertyDesignatorWithOptionalInstance EOF
            {
             newCompositeNode(grammarAccess.getPropertyDesignatorWithOptionalInstanceRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePropertyDesignatorWithOptionalInstance=rulePropertyDesignatorWithOptionalInstance();

            state._fsp--;

             current =iv_rulePropertyDesignatorWithOptionalInstance; 
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
    // $ANTLR end "entryRulePropertyDesignatorWithOptionalInstance"


    // $ANTLR start "rulePropertyDesignatorWithOptionalInstance"
    // InternalMyDsl.g:1784:1: rulePropertyDesignatorWithOptionalInstance returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' )? ( (otherlv_2= RULE_ID ) ) ) ;
    public final EObject rulePropertyDesignatorWithOptionalInstance() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalMyDsl.g:1790:2: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' )? ( (otherlv_2= RULE_ID ) ) ) )
            // InternalMyDsl.g:1791:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' )? ( (otherlv_2= RULE_ID ) ) )
            {
            // InternalMyDsl.g:1791:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' )? ( (otherlv_2= RULE_ID ) ) )
            // InternalMyDsl.g:1792:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' )? ( (otherlv_2= RULE_ID ) )
            {
            // InternalMyDsl.g:1792:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '.' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==RULE_ID) ) {
                int LA32_1 = input.LA(2);

                if ( (LA32_1==38) ) {
                    alt32=1;
                }
            }
            switch (alt32) {
                case 1 :
                    // InternalMyDsl.g:1793:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '.'
                    {
                    // InternalMyDsl.g:1793:4: ( (otherlv_0= RULE_ID ) )
                    // InternalMyDsl.g:1794:5: (otherlv_0= RULE_ID )
                    {
                    // InternalMyDsl.g:1794:5: (otherlv_0= RULE_ID )
                    // InternalMyDsl.g:1795:6: otherlv_0= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getPropertyDesignatorWithOptionalInstanceRule());
                    						}
                    					
                    otherlv_0=(Token)match(input,RULE_ID,FOLLOW_43); 

                    						newLeafNode(otherlv_0, grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceInstanceCrossReference_0_0_0());
                    					

                    }


                    }

                    otherlv_1=(Token)match(input,38,FOLLOW_12); 

                    				newLeafNode(otherlv_1, grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getFullStopKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalMyDsl.g:1811:3: ( (otherlv_2= RULE_ID ) )
            // InternalMyDsl.g:1812:4: (otherlv_2= RULE_ID )
            {
            // InternalMyDsl.g:1812:4: (otherlv_2= RULE_ID )
            // InternalMyDsl.g:1813:5: otherlv_2= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getPropertyDesignatorWithOptionalInstanceRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(otherlv_2, grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyPropertyCrossReference_1_0());
            				

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
    // $ANTLR end "rulePropertyDesignatorWithOptionalInstance"


    // $ANTLR start "entryRuleMetaParameterDesignator"
    // InternalMyDsl.g:1828:1: entryRuleMetaParameterDesignator returns [EObject current=null] : iv_ruleMetaParameterDesignator= ruleMetaParameterDesignator EOF ;
    public final EObject entryRuleMetaParameterDesignator() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaParameterDesignator = null;


        try {
            // InternalMyDsl.g:1828:64: (iv_ruleMetaParameterDesignator= ruleMetaParameterDesignator EOF )
            // InternalMyDsl.g:1829:2: iv_ruleMetaParameterDesignator= ruleMetaParameterDesignator EOF
            {
             newCompositeNode(grammarAccess.getMetaParameterDesignatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMetaParameterDesignator=ruleMetaParameterDesignator();

            state._fsp--;

             current =iv_ruleMetaParameterDesignator; 
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
    // $ANTLR end "entryRuleMetaParameterDesignator"


    // $ANTLR start "ruleMetaParameterDesignator"
    // InternalMyDsl.g:1835:1: ruleMetaParameterDesignator returns [EObject current=null] : ( (otherlv_0= RULE_ID ) ) ;
    public final EObject ruleMetaParameterDesignator() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:1841:2: ( ( (otherlv_0= RULE_ID ) ) )
            // InternalMyDsl.g:1842:2: ( (otherlv_0= RULE_ID ) )
            {
            // InternalMyDsl.g:1842:2: ( (otherlv_0= RULE_ID ) )
            // InternalMyDsl.g:1843:3: (otherlv_0= RULE_ID )
            {
            // InternalMyDsl.g:1843:3: (otherlv_0= RULE_ID )
            // InternalMyDsl.g:1844:4: otherlv_0= RULE_ID
            {

            				if (current==null) {
            					current = createModelElement(grammarAccess.getMetaParameterDesignatorRule());
            				}
            			
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(otherlv_0, grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterMetaParameterCrossReference_0());
            			

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
    // $ANTLR end "ruleMetaParameterDesignator"


    // $ANTLR start "entryRuleLiteralExpression"
    // InternalMyDsl.g:1858:1: entryRuleLiteralExpression returns [EObject current=null] : iv_ruleLiteralExpression= ruleLiteralExpression EOF ;
    public final EObject entryRuleLiteralExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLiteralExpression = null;


        try {
            // InternalMyDsl.g:1858:58: (iv_ruleLiteralExpression= ruleLiteralExpression EOF )
            // InternalMyDsl.g:1859:2: iv_ruleLiteralExpression= ruleLiteralExpression EOF
            {
             newCompositeNode(grammarAccess.getLiteralExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLiteralExpression=ruleLiteralExpression();

            state._fsp--;

             current =iv_ruleLiteralExpression; 
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
    // $ANTLR end "entryRuleLiteralExpression"


    // $ANTLR start "ruleLiteralExpression"
    // InternalMyDsl.g:1865:1: ruleLiteralExpression returns [EObject current=null] : ( (lv_value_0_0= ruleDouble ) ) ;
    public final EObject ruleLiteralExpression() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1871:2: ( ( (lv_value_0_0= ruleDouble ) ) )
            // InternalMyDsl.g:1872:2: ( (lv_value_0_0= ruleDouble ) )
            {
            // InternalMyDsl.g:1872:2: ( (lv_value_0_0= ruleDouble ) )
            // InternalMyDsl.g:1873:3: (lv_value_0_0= ruleDouble )
            {
            // InternalMyDsl.g:1873:3: (lv_value_0_0= ruleDouble )
            // InternalMyDsl.g:1874:4: lv_value_0_0= ruleDouble
            {

            				newCompositeNode(grammarAccess.getLiteralExpressionAccess().getValueDoubleParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_value_0_0=ruleDouble();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getLiteralExpressionRule());
            				}
            				set(
            					current,
            					"value",
            					lv_value_0_0,
            					"org.xtext.example.mydsl.MyDsl.Double");
            				afterParserOrEnumRuleCall();
            			

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
    // $ANTLR end "ruleLiteralExpression"


    // $ANTLR start "entryRuleMetaParameterAssignment"
    // InternalMyDsl.g:1894:1: entryRuleMetaParameterAssignment returns [EObject current=null] : iv_ruleMetaParameterAssignment= ruleMetaParameterAssignment EOF ;
    public final EObject entryRuleMetaParameterAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaParameterAssignment = null;


        try {
            // InternalMyDsl.g:1894:64: (iv_ruleMetaParameterAssignment= ruleMetaParameterAssignment EOF )
            // InternalMyDsl.g:1895:2: iv_ruleMetaParameterAssignment= ruleMetaParameterAssignment EOF
            {
             newCompositeNode(grammarAccess.getMetaParameterAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMetaParameterAssignment=ruleMetaParameterAssignment();

            state._fsp--;

             current =iv_ruleMetaParameterAssignment; 
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
    // $ANTLR end "entryRuleMetaParameterAssignment"


    // $ANTLR start "ruleMetaParameterAssignment"
    // InternalMyDsl.g:1901:1: ruleMetaParameterAssignment returns [EObject current=null] : (otherlv_0= 'meta' ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= ruleLiteralExpression ) ) ) ;
    public final EObject ruleMetaParameterAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        EObject lv_value_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1907:2: ( (otherlv_0= 'meta' ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= ruleLiteralExpression ) ) ) )
            // InternalMyDsl.g:1908:2: (otherlv_0= 'meta' ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= ruleLiteralExpression ) ) )
            {
            // InternalMyDsl.g:1908:2: (otherlv_0= 'meta' ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= ruleLiteralExpression ) ) )
            // InternalMyDsl.g:1909:3: otherlv_0= 'meta' ( (otherlv_1= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= ruleLiteralExpression ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getMetaParameterAssignmentAccess().getMetaKeyword_0());
            		
            // InternalMyDsl.g:1913:3: ( (otherlv_1= RULE_ID ) )
            // InternalMyDsl.g:1914:4: (otherlv_1= RULE_ID )
            {
            // InternalMyDsl.g:1914:4: (otherlv_1= RULE_ID )
            // InternalMyDsl.g:1915:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMetaParameterAssignmentRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_21); 

            					newLeafNode(otherlv_1, grammarAccess.getMetaParameterAssignmentAccess().getParameterMetaParameterCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,20,FOLLOW_22); 

            			newLeafNode(otherlv_2, grammarAccess.getMetaParameterAssignmentAccess().getEqualsSignKeyword_2());
            		
            // InternalMyDsl.g:1930:3: ( (lv_value_3_0= ruleLiteralExpression ) )
            // InternalMyDsl.g:1931:4: (lv_value_3_0= ruleLiteralExpression )
            {
            // InternalMyDsl.g:1931:4: (lv_value_3_0= ruleLiteralExpression )
            // InternalMyDsl.g:1932:5: lv_value_3_0= ruleLiteralExpression
            {

            					newCompositeNode(grammarAccess.getMetaParameterAssignmentAccess().getValueLiteralExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_2);
            lv_value_3_0=ruleLiteralExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getMetaParameterAssignmentRule());
            					}
            					set(
            						current,
            						"value",
            						lv_value_3_0,
            						"org.xtext.example.mydsl.MyDsl.LiteralExpression");
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
    // $ANTLR end "ruleMetaParameterAssignment"


    // $ANTLR start "entryRuleSolution"
    // InternalMyDsl.g:1953:1: entryRuleSolution returns [EObject current=null] : iv_ruleSolution= ruleSolution EOF ;
    public final EObject entryRuleSolution() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSolution = null;


        try {
            // InternalMyDsl.g:1953:49: (iv_ruleSolution= ruleSolution EOF )
            // InternalMyDsl.g:1954:2: iv_ruleSolution= ruleSolution EOF
            {
             newCompositeNode(grammarAccess.getSolutionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSolution=ruleSolution();

            state._fsp--;

             current =iv_ruleSolution; 
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
    // $ANTLR end "entryRuleSolution"


    // $ANTLR start "ruleSolution"
    // InternalMyDsl.g:1960:1: ruleSolution returns [EObject current=null] : (otherlv_0= 'solution' otherlv_1= '{' ( (lv_assignments_2_0= ruleAssignment ) )+ otherlv_3= '}' ) ;
    public final EObject ruleSolution() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_assignments_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1966:2: ( (otherlv_0= 'solution' otherlv_1= '{' ( (lv_assignments_2_0= ruleAssignment ) )+ otherlv_3= '}' ) )
            // InternalMyDsl.g:1967:2: (otherlv_0= 'solution' otherlv_1= '{' ( (lv_assignments_2_0= ruleAssignment ) )+ otherlv_3= '}' )
            {
            // InternalMyDsl.g:1967:2: (otherlv_0= 'solution' otherlv_1= '{' ( (lv_assignments_2_0= ruleAssignment ) )+ otherlv_3= '}' )
            // InternalMyDsl.g:1968:3: otherlv_0= 'solution' otherlv_1= '{' ( (lv_assignments_2_0= ruleAssignment ) )+ otherlv_3= '}'
            {
            otherlv_0=(Token)match(input,39,FOLLOW_13); 

            			newLeafNode(otherlv_0, grammarAccess.getSolutionAccess().getSolutionKeyword_0());
            		
            otherlv_1=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getSolutionAccess().getLeftCurlyBracketKeyword_1());
            		
            // InternalMyDsl.g:1976:3: ( (lv_assignments_2_0= ruleAssignment ) )+
            int cnt33=0;
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==RULE_ID) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // InternalMyDsl.g:1977:4: (lv_assignments_2_0= ruleAssignment )
            	    {
            	    // InternalMyDsl.g:1977:4: (lv_assignments_2_0= ruleAssignment )
            	    // InternalMyDsl.g:1978:5: lv_assignments_2_0= ruleAssignment
            	    {

            	    					newCompositeNode(grammarAccess.getSolutionAccess().getAssignmentsAssignmentParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_20);
            	    lv_assignments_2_0=ruleAssignment();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getSolutionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"assignments",
            	    						lv_assignments_2_0,
            	    						"org.xtext.example.mydsl.MyDsl.Assignment");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt33 >= 1 ) break loop33;
                        EarlyExitException eee =
                            new EarlyExitException(33, input);
                        throw eee;
                }
                cnt33++;
            } while (true);

            otherlv_3=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getSolutionAccess().getRightCurlyBracketKeyword_3());
            		

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
    // $ANTLR end "ruleSolution"


    // $ANTLR start "entryRuleAssignment"
    // InternalMyDsl.g:2003:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalMyDsl.g:2003:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalMyDsl.g:2004:2: iv_ruleAssignment= ruleAssignment EOF
            {
             newCompositeNode(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;

             current =iv_ruleAssignment; 
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
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalMyDsl.g:2010:1: ruleAssignment returns [EObject current=null] : ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' )? ( (otherlv_2= RULE_ID ) ) otherlv_3= '{' ( (lv_resourceMappings_4_0= ruleResourceMapping ) )+ ( (lv_componentMappings_5_0= ruleComponentMapping ) )* otherlv_6= '}' ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_6=null;
        EObject lv_resourceMappings_4_0 = null;

        EObject lv_componentMappings_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:2016:2: ( ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' )? ( (otherlv_2= RULE_ID ) ) otherlv_3= '{' ( (lv_resourceMappings_4_0= ruleResourceMapping ) )+ ( (lv_componentMappings_5_0= ruleComponentMapping ) )* otherlv_6= '}' ) )
            // InternalMyDsl.g:2017:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' )? ( (otherlv_2= RULE_ID ) ) otherlv_3= '{' ( (lv_resourceMappings_4_0= ruleResourceMapping ) )+ ( (lv_componentMappings_5_0= ruleComponentMapping ) )* otherlv_6= '}' )
            {
            // InternalMyDsl.g:2017:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' )? ( (otherlv_2= RULE_ID ) ) otherlv_3= '{' ( (lv_resourceMappings_4_0= ruleResourceMapping ) )+ ( (lv_componentMappings_5_0= ruleComponentMapping ) )* otherlv_6= '}' )
            // InternalMyDsl.g:2018:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' )? ( (otherlv_2= RULE_ID ) ) otherlv_3= '{' ( (lv_resourceMappings_4_0= ruleResourceMapping ) )+ ( (lv_componentMappings_5_0= ruleComponentMapping ) )* otherlv_6= '}'
            {
            // InternalMyDsl.g:2018:3: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==RULE_ID) ) {
                int LA34_1 = input.LA(2);

                if ( (LA34_1==40) ) {
                    alt34=1;
                }
            }
            switch (alt34) {
                case 1 :
                    // InternalMyDsl.g:2019:4: ( (otherlv_0= RULE_ID ) ) otherlv_1= '->'
                    {
                    // InternalMyDsl.g:2019:4: ( (otherlv_0= RULE_ID ) )
                    // InternalMyDsl.g:2020:5: (otherlv_0= RULE_ID )
                    {
                    // InternalMyDsl.g:2020:5: (otherlv_0= RULE_ID )
                    // InternalMyDsl.g:2021:6: otherlv_0= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAssignmentRule());
                    						}
                    					
                    otherlv_0=(Token)match(input,RULE_ID,FOLLOW_44); 

                    						newLeafNode(otherlv_0, grammarAccess.getAssignmentAccess().getRequestRequestCrossReference_0_0_0());
                    					

                    }


                    }

                    otherlv_1=(Token)match(input,40,FOLLOW_12); 

                    				newLeafNode(otherlv_1, grammarAccess.getAssignmentAccess().getHyphenMinusGreaterThanSignKeyword_0_1());
                    			

                    }
                    break;

            }

            // InternalMyDsl.g:2037:3: ( (otherlv_2= RULE_ID ) )
            // InternalMyDsl.g:2038:4: (otherlv_2= RULE_ID )
            {
            // InternalMyDsl.g:2038:4: (otherlv_2= RULE_ID )
            // InternalMyDsl.g:2039:5: otherlv_2= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAssignmentRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(otherlv_2, grammarAccess.getAssignmentAccess().getImplementationImplementationCrossReference_1_0());
            				

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_12); 

            			newLeafNode(otherlv_3, grammarAccess.getAssignmentAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalMyDsl.g:2054:3: ( (lv_resourceMappings_4_0= ruleResourceMapping ) )+
            int cnt35=0;
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( (LA35_0==RULE_ID) ) {
                    int LA35_1 = input.LA(2);

                    if ( (LA35_1==40) ) {
                        alt35=1;
                    }


                }


                switch (alt35) {
            	case 1 :
            	    // InternalMyDsl.g:2055:4: (lv_resourceMappings_4_0= ruleResourceMapping )
            	    {
            	    // InternalMyDsl.g:2055:4: (lv_resourceMappings_4_0= ruleResourceMapping )
            	    // InternalMyDsl.g:2056:5: lv_resourceMappings_4_0= ruleResourceMapping
            	    {

            	    					newCompositeNode(grammarAccess.getAssignmentAccess().getResourceMappingsResourceMappingParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_20);
            	    lv_resourceMappings_4_0=ruleResourceMapping();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resourceMappings",
            	    						lv_resourceMappings_4_0,
            	    						"org.xtext.example.mydsl.MyDsl.ResourceMapping");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt35 >= 1 ) break loop35;
                        EarlyExitException eee =
                            new EarlyExitException(35, input);
                        throw eee;
                }
                cnt35++;
            } while (true);

            // InternalMyDsl.g:2073:3: ( (lv_componentMappings_5_0= ruleComponentMapping ) )*
            loop36:
            do {
                int alt36=2;
                int LA36_0 = input.LA(1);

                if ( (LA36_0==RULE_ID) ) {
                    alt36=1;
                }


                switch (alt36) {
            	case 1 :
            	    // InternalMyDsl.g:2074:4: (lv_componentMappings_5_0= ruleComponentMapping )
            	    {
            	    // InternalMyDsl.g:2074:4: (lv_componentMappings_5_0= ruleComponentMapping )
            	    // InternalMyDsl.g:2075:5: lv_componentMappings_5_0= ruleComponentMapping
            	    {

            	    					newCompositeNode(grammarAccess.getAssignmentAccess().getComponentMappingsComponentMappingParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_20);
            	    lv_componentMappings_5_0=ruleComponentMapping();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"componentMappings",
            	    						lv_componentMappings_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.ComponentMapping");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop36;
                }
            } while (true);

            otherlv_6=(Token)match(input,18,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getAssignmentAccess().getRightCurlyBracketKeyword_5());
            		

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
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleResourceMapping"
    // InternalMyDsl.g:2100:1: entryRuleResourceMapping returns [EObject current=null] : iv_ruleResourceMapping= ruleResourceMapping EOF ;
    public final EObject entryRuleResourceMapping() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceMapping = null;


        try {
            // InternalMyDsl.g:2100:56: (iv_ruleResourceMapping= ruleResourceMapping EOF )
            // InternalMyDsl.g:2101:2: iv_ruleResourceMapping= ruleResourceMapping EOF
            {
             newCompositeNode(grammarAccess.getResourceMappingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResourceMapping=ruleResourceMapping();

            state._fsp--;

             current =iv_ruleResourceMapping; 
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
    // $ANTLR end "entryRuleResourceMapping"


    // $ANTLR start "ruleResourceMapping"
    // InternalMyDsl.g:2107:1: ruleResourceMapping returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) (otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}' )? ) ;
    public final EObject ruleResourceMapping() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_subMappings_4_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:2113:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) (otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}' )? ) )
            // InternalMyDsl.g:2114:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) (otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}' )? )
            {
            // InternalMyDsl.g:2114:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) (otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}' )? )
            // InternalMyDsl.g:2115:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '->' ( (otherlv_2= RULE_ID ) ) (otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}' )?
            {
            // InternalMyDsl.g:2115:3: ( (otherlv_0= RULE_ID ) )
            // InternalMyDsl.g:2116:4: (otherlv_0= RULE_ID )
            {
            // InternalMyDsl.g:2116:4: (otherlv_0= RULE_ID )
            // InternalMyDsl.g:2117:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceMappingRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_44); 

            					newLeafNode(otherlv_0, grammarAccess.getResourceMappingAccess().getRequiredResourceInstanceCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,40,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getResourceMappingAccess().getHyphenMinusGreaterThanSignKeyword_1());
            		
            // InternalMyDsl.g:2132:3: ( (otherlv_2= RULE_ID ) )
            // InternalMyDsl.g:2133:4: (otherlv_2= RULE_ID )
            {
            // InternalMyDsl.g:2133:4: (otherlv_2= RULE_ID )
            // InternalMyDsl.g:2134:5: otherlv_2= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceMappingRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ID,FOLLOW_45); 

            					newLeafNode(otherlv_2, grammarAccess.getResourceMappingAccess().getUsedResourceResourceCrossReference_2_0());
            				

            }


            }

            // InternalMyDsl.g:2145:3: (otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}' )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==15) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // InternalMyDsl.g:2146:4: otherlv_3= '{' ( (lv_subMappings_4_0= ruleResourceMapping ) )+ otherlv_5= '}'
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_12); 

                    				newLeafNode(otherlv_3, grammarAccess.getResourceMappingAccess().getLeftCurlyBracketKeyword_3_0());
                    			
                    // InternalMyDsl.g:2150:4: ( (lv_subMappings_4_0= ruleResourceMapping ) )+
                    int cnt37=0;
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( (LA37_0==RULE_ID) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // InternalMyDsl.g:2151:5: (lv_subMappings_4_0= ruleResourceMapping )
                    	    {
                    	    // InternalMyDsl.g:2151:5: (lv_subMappings_4_0= ruleResourceMapping )
                    	    // InternalMyDsl.g:2152:6: lv_subMappings_4_0= ruleResourceMapping
                    	    {

                    	    						newCompositeNode(grammarAccess.getResourceMappingAccess().getSubMappingsResourceMappingParserRuleCall_3_1_0());
                    	    					
                    	    pushFollow(FOLLOW_20);
                    	    lv_subMappings_4_0=ruleResourceMapping();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getResourceMappingRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"subMappings",
                    	    							lv_subMappings_4_0,
                    	    							"org.xtext.example.mydsl.MyDsl.ResourceMapping");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt37 >= 1 ) break loop37;
                                EarlyExitException eee =
                                    new EarlyExitException(37, input);
                                throw eee;
                        }
                        cnt37++;
                    } while (true);

                    otherlv_5=(Token)match(input,18,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getResourceMappingAccess().getRightCurlyBracketKeyword_3_2());
                    			

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
    // $ANTLR end "ruleResourceMapping"


    // $ANTLR start "entryRuleComponentMapping"
    // InternalMyDsl.g:2178:1: entryRuleComponentMapping returns [EObject current=null] : iv_ruleComponentMapping= ruleComponentMapping EOF ;
    public final EObject entryRuleComponentMapping() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponentMapping = null;


        try {
            // InternalMyDsl.g:2178:57: (iv_ruleComponentMapping= ruleComponentMapping EOF )
            // InternalMyDsl.g:2179:2: iv_ruleComponentMapping= ruleComponentMapping EOF
            {
             newCompositeNode(grammarAccess.getComponentMappingRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponentMapping=ruleComponentMapping();

            state._fsp--;

             current =iv_ruleComponentMapping; 
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
    // $ANTLR end "entryRuleComponentMapping"


    // $ANTLR start "ruleComponentMapping"
    // InternalMyDsl.g:2185:1: ruleComponentMapping returns [EObject current=null] : ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '-->' ( (lv_assignment_2_0= ruleAssignment ) ) ) ;
    public final EObject ruleComponentMapping() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_assignment_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:2191:2: ( ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '-->' ( (lv_assignment_2_0= ruleAssignment ) ) ) )
            // InternalMyDsl.g:2192:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '-->' ( (lv_assignment_2_0= ruleAssignment ) ) )
            {
            // InternalMyDsl.g:2192:2: ( ( (otherlv_0= RULE_ID ) ) otherlv_1= '-->' ( (lv_assignment_2_0= ruleAssignment ) ) )
            // InternalMyDsl.g:2193:3: ( (otherlv_0= RULE_ID ) ) otherlv_1= '-->' ( (lv_assignment_2_0= ruleAssignment ) )
            {
            // InternalMyDsl.g:2193:3: ( (otherlv_0= RULE_ID ) )
            // InternalMyDsl.g:2194:4: (otherlv_0= RULE_ID )
            {
            // InternalMyDsl.g:2194:4: (otherlv_0= RULE_ID )
            // InternalMyDsl.g:2195:5: otherlv_0= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponentMappingRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ID,FOLLOW_46); 

            					newLeafNode(otherlv_0, grammarAccess.getComponentMappingAccess().getComponentInstanceCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,41,FOLLOW_12); 

            			newLeafNode(otherlv_1, grammarAccess.getComponentMappingAccess().getHyphenMinusHyphenMinusGreaterThanSignKeyword_1());
            		
            // InternalMyDsl.g:2210:3: ( (lv_assignment_2_0= ruleAssignment ) )
            // InternalMyDsl.g:2211:4: (lv_assignment_2_0= ruleAssignment )
            {
            // InternalMyDsl.g:2211:4: (lv_assignment_2_0= ruleAssignment )
            // InternalMyDsl.g:2212:5: lv_assignment_2_0= ruleAssignment
            {

            					newCompositeNode(grammarAccess.getComponentMappingAccess().getAssignmentAssignmentParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_assignment_2_0=ruleAssignment();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getComponentMappingRule());
            					}
            					set(
            						current,
            						"assignment",
            						lv_assignment_2_0,
            						"org.xtext.example.mydsl.MyDsl.Assignment");
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
    // $ANTLR end "ruleComponentMapping"


    // $ANTLR start "entryRuleDouble"
    // InternalMyDsl.g:2233:1: entryRuleDouble returns [String current=null] : iv_ruleDouble= ruleDouble EOF ;
    public final String entryRuleDouble() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleDouble = null;


        try {
            // InternalMyDsl.g:2233:46: (iv_ruleDouble= ruleDouble EOF )
            // InternalMyDsl.g:2234:2: iv_ruleDouble= ruleDouble EOF
            {
             newCompositeNode(grammarAccess.getDoubleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDouble=ruleDouble();

            state._fsp--;

             current =iv_ruleDouble.getText(); 
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
    // $ANTLR end "entryRuleDouble"


    // $ANTLR start "ruleDouble"
    // InternalMyDsl.g:2240:1: ruleDouble returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleDouble() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalMyDsl.g:2246:2: ( (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT ) )
            // InternalMyDsl.g:2247:2: (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT )
            {
            // InternalMyDsl.g:2247:2: (this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT )
            // InternalMyDsl.g:2248:3: this_INT_0= RULE_INT kw= '.' this_INT_2= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_43); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getDoubleAccess().getINTTerminalRuleCall_0());
            		
            kw=(Token)match(input,38,FOLLOW_22); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getDoubleAccess().getFullStopKeyword_1());
            		
            this_INT_2=(Token)match(input,RULE_INT,FOLLOW_2); 

            			current.merge(this_INT_2);
            		

            			newLeafNode(this_INT_2, grammarAccess.getDoubleAccess().getINTTerminalRuleCall_2());
            		

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
    // $ANTLR end "ruleDouble"


    // $ANTLR start "ruleClauseType"
    // InternalMyDsl.g:2271:1: ruleClauseType returns [Enumerator current=null] : ( (enumLiteral_0= 'requiring' ) | (enumLiteral_1= 'providing' ) ) ;
    public final Enumerator ruleClauseType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalMyDsl.g:2277:2: ( ( (enumLiteral_0= 'requiring' ) | (enumLiteral_1= 'providing' ) ) )
            // InternalMyDsl.g:2278:2: ( (enumLiteral_0= 'requiring' ) | (enumLiteral_1= 'providing' ) )
            {
            // InternalMyDsl.g:2278:2: ( (enumLiteral_0= 'requiring' ) | (enumLiteral_1= 'providing' ) )
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==42) ) {
                alt39=1;
            }
            else if ( (LA39_0==43) ) {
                alt39=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;
            }
            switch (alt39) {
                case 1 :
                    // InternalMyDsl.g:2279:3: (enumLiteral_0= 'requiring' )
                    {
                    // InternalMyDsl.g:2279:3: (enumLiteral_0= 'requiring' )
                    // InternalMyDsl.g:2280:4: enumLiteral_0= 'requiring'
                    {
                    enumLiteral_0=(Token)match(input,42,FOLLOW_2); 

                    				current = grammarAccess.getClauseTypeAccess().getRequiringEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getClauseTypeAccess().getRequiringEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:2287:3: (enumLiteral_1= 'providing' )
                    {
                    // InternalMyDsl.g:2287:3: (enumLiteral_1= 'providing' )
                    // InternalMyDsl.g:2288:4: enumLiteral_1= 'providing'
                    {
                    enumLiteral_1=(Token)match(input,43,FOLLOW_2); 

                    				current = grammarAccess.getClauseTypeAccess().getProvidingEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getClauseTypeAccess().getProvidingEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "ruleClauseType"


    // $ANTLR start "ruleClauseComparator"
    // InternalMyDsl.g:2298:1: ruleClauseComparator returns [Enumerator current=null] : ( (enumLiteral_0= '<' ) | (enumLiteral_1= '<=' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '>' ) ) ;
    public final Enumerator ruleClauseComparator() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;


        	enterRule();

        try {
            // InternalMyDsl.g:2304:2: ( ( (enumLiteral_0= '<' ) | (enumLiteral_1= '<=' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '>' ) ) )
            // InternalMyDsl.g:2305:2: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '<=' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '>' ) )
            {
            // InternalMyDsl.g:2305:2: ( (enumLiteral_0= '<' ) | (enumLiteral_1= '<=' ) | (enumLiteral_2= '=' ) | (enumLiteral_3= '!=' ) | (enumLiteral_4= '>=' ) | (enumLiteral_5= '>' ) )
            int alt40=6;
            switch ( input.LA(1) ) {
            case 44:
                {
                alt40=1;
                }
                break;
            case 45:
                {
                alt40=2;
                }
                break;
            case 20:
                {
                alt40=3;
                }
                break;
            case 46:
                {
                alt40=4;
                }
                break;
            case 47:
                {
                alt40=5;
                }
                break;
            case 48:
                {
                alt40=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;
            }

            switch (alt40) {
                case 1 :
                    // InternalMyDsl.g:2306:3: (enumLiteral_0= '<' )
                    {
                    // InternalMyDsl.g:2306:3: (enumLiteral_0= '<' )
                    // InternalMyDsl.g:2307:4: enumLiteral_0= '<'
                    {
                    enumLiteral_0=(Token)match(input,44,FOLLOW_2); 

                    				current = grammarAccess.getClauseComparatorAccess().getLTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getClauseComparatorAccess().getLTEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:2314:3: (enumLiteral_1= '<=' )
                    {
                    // InternalMyDsl.g:2314:3: (enumLiteral_1= '<=' )
                    // InternalMyDsl.g:2315:4: enumLiteral_1= '<='
                    {
                    enumLiteral_1=(Token)match(input,45,FOLLOW_2); 

                    				current = grammarAccess.getClauseComparatorAccess().getLEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getClauseComparatorAccess().getLEEnumLiteralDeclaration_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:2322:3: (enumLiteral_2= '=' )
                    {
                    // InternalMyDsl.g:2322:3: (enumLiteral_2= '=' )
                    // InternalMyDsl.g:2323:4: enumLiteral_2= '='
                    {
                    enumLiteral_2=(Token)match(input,20,FOLLOW_2); 

                    				current = grammarAccess.getClauseComparatorAccess().getEQEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_2, grammarAccess.getClauseComparatorAccess().getEQEnumLiteralDeclaration_2());
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:2330:3: (enumLiteral_3= '!=' )
                    {
                    // InternalMyDsl.g:2330:3: (enumLiteral_3= '!=' )
                    // InternalMyDsl.g:2331:4: enumLiteral_3= '!='
                    {
                    enumLiteral_3=(Token)match(input,46,FOLLOW_2); 

                    				current = grammarAccess.getClauseComparatorAccess().getNEEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_3, grammarAccess.getClauseComparatorAccess().getNEEnumLiteralDeclaration_3());
                    			

                    }


                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:2338:3: (enumLiteral_4= '>=' )
                    {
                    // InternalMyDsl.g:2338:3: (enumLiteral_4= '>=' )
                    // InternalMyDsl.g:2339:4: enumLiteral_4= '>='
                    {
                    enumLiteral_4=(Token)match(input,47,FOLLOW_2); 

                    				current = grammarAccess.getClauseComparatorAccess().getGEEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_4, grammarAccess.getClauseComparatorAccess().getGEEnumLiteralDeclaration_4());
                    			

                    }


                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:2346:3: (enumLiteral_5= '>' )
                    {
                    // InternalMyDsl.g:2346:3: (enumLiteral_5= '>' )
                    // InternalMyDsl.g:2347:4: enumLiteral_5= '>'
                    {
                    enumLiteral_5=(Token)match(input,48,FOLLOW_2); 

                    				current = grammarAccess.getClauseComparatorAccess().getGTEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_5, grammarAccess.getClauseComparatorAccess().getGTEnumLiteralDeclaration_5());
                    			

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
    // $ANTLR end "ruleClauseComparator"


    // $ANTLR start "rulePropertyAggregation"
    // InternalMyDsl.g:2357:1: rulePropertyAggregation returns [Enumerator current=null] : ( (enumLiteral_0= 'sum' ) | (enumLiteral_1= 'max' ) ) ;
    public final Enumerator rulePropertyAggregation() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;


        	enterRule();

        try {
            // InternalMyDsl.g:2363:2: ( ( (enumLiteral_0= 'sum' ) | (enumLiteral_1= 'max' ) ) )
            // InternalMyDsl.g:2364:2: ( (enumLiteral_0= 'sum' ) | (enumLiteral_1= 'max' ) )
            {
            // InternalMyDsl.g:2364:2: ( (enumLiteral_0= 'sum' ) | (enumLiteral_1= 'max' ) )
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==49) ) {
                alt41=1;
            }
            else if ( (LA41_0==50) ) {
                alt41=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;
            }
            switch (alt41) {
                case 1 :
                    // InternalMyDsl.g:2365:3: (enumLiteral_0= 'sum' )
                    {
                    // InternalMyDsl.g:2365:3: (enumLiteral_0= 'sum' )
                    // InternalMyDsl.g:2366:4: enumLiteral_0= 'sum'
                    {
                    enumLiteral_0=(Token)match(input,49,FOLLOW_2); 

                    				current = grammarAccess.getPropertyAggregationAccess().getSumEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_0, grammarAccess.getPropertyAggregationAccess().getSumEnumLiteralDeclaration_0());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:2373:3: (enumLiteral_1= 'max' )
                    {
                    // InternalMyDsl.g:2373:3: (enumLiteral_1= 'max' )
                    // InternalMyDsl.g:2374:4: enumLiteral_1= 'max'
                    {
                    enumLiteral_1=(Token)match(input,50,FOLLOW_2); 

                    				current = grammarAccess.getPropertyAggregationAccess().getMaxEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                    				newLeafNode(enumLiteral_1, grammarAccess.getPropertyAggregationAccess().getMaxEnumLiteralDeclaration_1());
                    			

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
    // $ANTLR end "rulePropertyAggregation"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000620000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000050000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000023000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000073000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000070000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000050000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000042010L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000040010L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000620002L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000810000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000850000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000005000010L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x00000C0005000010L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x00000C0000040000L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000005040010L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0001F00000100000L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000080000050L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x00000C0000200000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0006000000000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000000600000002L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000001800000002L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000002000000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000020000000000L});

}