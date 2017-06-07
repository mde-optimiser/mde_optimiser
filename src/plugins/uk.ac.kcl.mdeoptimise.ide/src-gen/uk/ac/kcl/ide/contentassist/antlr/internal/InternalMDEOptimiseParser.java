package uk.ac.kcl.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import uk.ac.kcl.services.MDEOptimiseGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMDEOptimiseParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_URL", "RULE_ENTITY_NAME", "RULE_OBJECTIVE_TENDENCY", "RULE_ENTITY_TYPE", "RULE_STRING", "RULE_ALGORITHM_FACTORY", "RULE_ALGORITHM_NAME", "RULE_INT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'basepath'", "'metamodel'", "'model'", "'objective'", "'{'", "'}'", "'constraint'", "'evolve'", "'using'", "'unit'", "'optimisation'", "'provider'", "'algorithm'", "'evolutions'", "'population'"
    };
    public static final int RULE_ENTITY_NAME=5;
    public static final int RULE_OBJECTIVE_TENDENCY=6;
    public static final int RULE_STRING=8;
    public static final int RULE_SL_COMMENT=14;
    public static final int T__19=19;
    public static final int RULE_ALGORITHM_FACTORY=9;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_ID=12;
    public static final int RULE_WS=15;
    public static final int RULE_ANY_OTHER=16;
    public static final int RULE_ALGORITHM_NAME=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=11;
    public static final int T__29=29;
    public static final int RULE_URL=4;
    public static final int RULE_ENTITY_TYPE=7;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=13;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
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

    	public void setGrammarAccess(MDEOptimiseGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleOptimisation"
    // InternalMDEOptimise.g:53:1: entryRuleOptimisation : ruleOptimisation EOF ;
    public final void entryRuleOptimisation() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:54:1: ( ruleOptimisation EOF )
            // InternalMDEOptimise.g:55:1: ruleOptimisation EOF
            {
             before(grammarAccess.getOptimisationRule()); 
            pushFollow(FOLLOW_1);
            ruleOptimisation();

            state._fsp--;

             after(grammarAccess.getOptimisationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOptimisation"


    // $ANTLR start "ruleOptimisation"
    // InternalMDEOptimise.g:62:1: ruleOptimisation : ( ( rule__Optimisation__Group__0 ) ) ;
    public final void ruleOptimisation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:66:2: ( ( ( rule__Optimisation__Group__0 ) ) )
            // InternalMDEOptimise.g:67:2: ( ( rule__Optimisation__Group__0 ) )
            {
            // InternalMDEOptimise.g:67:2: ( ( rule__Optimisation__Group__0 ) )
            // InternalMDEOptimise.g:68:3: ( rule__Optimisation__Group__0 )
            {
             before(grammarAccess.getOptimisationAccess().getGroup()); 
            // InternalMDEOptimise.g:69:3: ( rule__Optimisation__Group__0 )
            // InternalMDEOptimise.g:69:4: rule__Optimisation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptimisation"


    // $ANTLR start "entryRuleBasepathSpec"
    // InternalMDEOptimise.g:78:1: entryRuleBasepathSpec : ruleBasepathSpec EOF ;
    public final void entryRuleBasepathSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:79:1: ( ruleBasepathSpec EOF )
            // InternalMDEOptimise.g:80:1: ruleBasepathSpec EOF
            {
             before(grammarAccess.getBasepathSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleBasepathSpec();

            state._fsp--;

             after(grammarAccess.getBasepathSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleBasepathSpec"


    // $ANTLR start "ruleBasepathSpec"
    // InternalMDEOptimise.g:87:1: ruleBasepathSpec : ( ( rule__BasepathSpec__Group__0 ) ) ;
    public final void ruleBasepathSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:91:2: ( ( ( rule__BasepathSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:92:2: ( ( rule__BasepathSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:92:2: ( ( rule__BasepathSpec__Group__0 ) )
            // InternalMDEOptimise.g:93:3: ( rule__BasepathSpec__Group__0 )
            {
             before(grammarAccess.getBasepathSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:94:3: ( rule__BasepathSpec__Group__0 )
            // InternalMDEOptimise.g:94:4: rule__BasepathSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__BasepathSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getBasepathSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleBasepathSpec"


    // $ANTLR start "entryRuleMetaModelSpec"
    // InternalMDEOptimise.g:103:1: entryRuleMetaModelSpec : ruleMetaModelSpec EOF ;
    public final void entryRuleMetaModelSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:104:1: ( ruleMetaModelSpec EOF )
            // InternalMDEOptimise.g:105:1: ruleMetaModelSpec EOF
            {
             before(grammarAccess.getMetaModelSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleMetaModelSpec();

            state._fsp--;

             after(grammarAccess.getMetaModelSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMetaModelSpec"


    // $ANTLR start "ruleMetaModelSpec"
    // InternalMDEOptimise.g:112:1: ruleMetaModelSpec : ( ( rule__MetaModelSpec__Group__0 ) ) ;
    public final void ruleMetaModelSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:116:2: ( ( ( rule__MetaModelSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:117:2: ( ( rule__MetaModelSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:117:2: ( ( rule__MetaModelSpec__Group__0 ) )
            // InternalMDEOptimise.g:118:3: ( rule__MetaModelSpec__Group__0 )
            {
             before(grammarAccess.getMetaModelSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:119:3: ( rule__MetaModelSpec__Group__0 )
            // InternalMDEOptimise.g:119:4: rule__MetaModelSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MetaModelSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMetaModelSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMetaModelSpec"


    // $ANTLR start "entryRuleModelPathSpec"
    // InternalMDEOptimise.g:128:1: entryRuleModelPathSpec : ruleModelPathSpec EOF ;
    public final void entryRuleModelPathSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:129:1: ( ruleModelPathSpec EOF )
            // InternalMDEOptimise.g:130:1: ruleModelPathSpec EOF
            {
             before(grammarAccess.getModelPathSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleModelPathSpec();

            state._fsp--;

             after(grammarAccess.getModelPathSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModelPathSpec"


    // $ANTLR start "ruleModelPathSpec"
    // InternalMDEOptimise.g:137:1: ruleModelPathSpec : ( ( rule__ModelPathSpec__Group__0 ) ) ;
    public final void ruleModelPathSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:141:2: ( ( ( rule__ModelPathSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:142:2: ( ( rule__ModelPathSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:142:2: ( ( rule__ModelPathSpec__Group__0 ) )
            // InternalMDEOptimise.g:143:3: ( rule__ModelPathSpec__Group__0 )
            {
             before(grammarAccess.getModelPathSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:144:3: ( rule__ModelPathSpec__Group__0 )
            // InternalMDEOptimise.g:144:4: rule__ModelPathSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ModelPathSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelPathSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModelPathSpec"


    // $ANTLR start "entryRuleObjectiveInterpreterSpec"
    // InternalMDEOptimise.g:153:1: entryRuleObjectiveInterpreterSpec : ruleObjectiveInterpreterSpec EOF ;
    public final void entryRuleObjectiveInterpreterSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:154:1: ( ruleObjectiveInterpreterSpec EOF )
            // InternalMDEOptimise.g:155:1: ruleObjectiveInterpreterSpec EOF
            {
             before(grammarAccess.getObjectiveInterpreterSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleObjectiveInterpreterSpec();

            state._fsp--;

             after(grammarAccess.getObjectiveInterpreterSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleObjectiveInterpreterSpec"


    // $ANTLR start "ruleObjectiveInterpreterSpec"
    // InternalMDEOptimise.g:162:1: ruleObjectiveInterpreterSpec : ( ( rule__ObjectiveInterpreterSpec__Group__0 ) ) ;
    public final void ruleObjectiveInterpreterSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:166:2: ( ( ( rule__ObjectiveInterpreterSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:167:2: ( ( rule__ObjectiveInterpreterSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:167:2: ( ( rule__ObjectiveInterpreterSpec__Group__0 ) )
            // InternalMDEOptimise.g:168:3: ( rule__ObjectiveInterpreterSpec__Group__0 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:169:3: ( rule__ObjectiveInterpreterSpec__Group__0 )
            // InternalMDEOptimise.g:169:4: rule__ObjectiveInterpreterSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveInterpreterSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleObjectiveInterpreterSpec"


    // $ANTLR start "entryRuleConstraintInterpreterSpec"
    // InternalMDEOptimise.g:178:1: entryRuleConstraintInterpreterSpec : ruleConstraintInterpreterSpec EOF ;
    public final void entryRuleConstraintInterpreterSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:179:1: ( ruleConstraintInterpreterSpec EOF )
            // InternalMDEOptimise.g:180:1: ruleConstraintInterpreterSpec EOF
            {
             before(grammarAccess.getConstraintInterpreterSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleConstraintInterpreterSpec();

            state._fsp--;

             after(grammarAccess.getConstraintInterpreterSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleConstraintInterpreterSpec"


    // $ANTLR start "ruleConstraintInterpreterSpec"
    // InternalMDEOptimise.g:187:1: ruleConstraintInterpreterSpec : ( ( rule__ConstraintInterpreterSpec__Group__0 ) ) ;
    public final void ruleConstraintInterpreterSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:191:2: ( ( ( rule__ConstraintInterpreterSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:192:2: ( ( rule__ConstraintInterpreterSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:192:2: ( ( rule__ConstraintInterpreterSpec__Group__0 ) )
            // InternalMDEOptimise.g:193:3: ( rule__ConstraintInterpreterSpec__Group__0 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:194:3: ( rule__ConstraintInterpreterSpec__Group__0 )
            // InternalMDEOptimise.g:194:4: rule__ConstraintInterpreterSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getConstraintInterpreterSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleConstraintInterpreterSpec"


    // $ANTLR start "entryRuleEvolverSpec"
    // InternalMDEOptimise.g:203:1: entryRuleEvolverSpec : ruleEvolverSpec EOF ;
    public final void entryRuleEvolverSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:204:1: ( ruleEvolverSpec EOF )
            // InternalMDEOptimise.g:205:1: ruleEvolverSpec EOF
            {
             before(grammarAccess.getEvolverSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleEvolverSpec();

            state._fsp--;

             after(grammarAccess.getEvolverSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleEvolverSpec"


    // $ANTLR start "ruleEvolverSpec"
    // InternalMDEOptimise.g:212:1: ruleEvolverSpec : ( ( rule__EvolverSpec__Group__0 ) ) ;
    public final void ruleEvolverSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:216:2: ( ( ( rule__EvolverSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:217:2: ( ( rule__EvolverSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:217:2: ( ( rule__EvolverSpec__Group__0 ) )
            // InternalMDEOptimise.g:218:3: ( rule__EvolverSpec__Group__0 )
            {
             before(grammarAccess.getEvolverSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:219:3: ( rule__EvolverSpec__Group__0 )
            // InternalMDEOptimise.g:219:4: rule__EvolverSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getEvolverSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleEvolverSpec"


    // $ANTLR start "entryRuleOptimisationSpec"
    // InternalMDEOptimise.g:228:1: entryRuleOptimisationSpec : ruleOptimisationSpec EOF ;
    public final void entryRuleOptimisationSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:229:1: ( ruleOptimisationSpec EOF )
            // InternalMDEOptimise.g:230:1: ruleOptimisationSpec EOF
            {
             before(grammarAccess.getOptimisationSpecRule()); 
            pushFollow(FOLLOW_1);
            ruleOptimisationSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationSpecRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleOptimisationSpec"


    // $ANTLR start "ruleOptimisationSpec"
    // InternalMDEOptimise.g:237:1: ruleOptimisationSpec : ( ( rule__OptimisationSpec__Group__0 ) ) ;
    public final void ruleOptimisationSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:241:2: ( ( ( rule__OptimisationSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:242:2: ( ( rule__OptimisationSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:242:2: ( ( rule__OptimisationSpec__Group__0 ) )
            // InternalMDEOptimise.g:243:3: ( rule__OptimisationSpec__Group__0 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:244:3: ( rule__OptimisationSpec__Group__0 )
            // InternalMDEOptimise.g:244:4: rule__OptimisationSpec__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleOptimisationSpec"


    // $ANTLR start "rule__Optimisation__Group__0"
    // InternalMDEOptimise.g:252:1: rule__Optimisation__Group__0 : rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1 ;
    public final void rule__Optimisation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:256:1: ( rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1 )
            // InternalMDEOptimise.g:257:2: rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Optimisation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__0"


    // $ANTLR start "rule__Optimisation__Group__0__Impl"
    // InternalMDEOptimise.g:264:1: rule__Optimisation__Group__0__Impl : ( ( rule__Optimisation__BasepathAssignment_0 ) ) ;
    public final void rule__Optimisation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:268:1: ( ( ( rule__Optimisation__BasepathAssignment_0 ) ) )
            // InternalMDEOptimise.g:269:1: ( ( rule__Optimisation__BasepathAssignment_0 ) )
            {
            // InternalMDEOptimise.g:269:1: ( ( rule__Optimisation__BasepathAssignment_0 ) )
            // InternalMDEOptimise.g:270:2: ( rule__Optimisation__BasepathAssignment_0 )
            {
             before(grammarAccess.getOptimisationAccess().getBasepathAssignment_0()); 
            // InternalMDEOptimise.g:271:2: ( rule__Optimisation__BasepathAssignment_0 )
            // InternalMDEOptimise.g:271:3: rule__Optimisation__BasepathAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__BasepathAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getBasepathAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__0__Impl"


    // $ANTLR start "rule__Optimisation__Group__1"
    // InternalMDEOptimise.g:279:1: rule__Optimisation__Group__1 : rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2 ;
    public final void rule__Optimisation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:283:1: ( rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2 )
            // InternalMDEOptimise.g:284:2: rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Optimisation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__1"


    // $ANTLR start "rule__Optimisation__Group__1__Impl"
    // InternalMDEOptimise.g:291:1: rule__Optimisation__Group__1__Impl : ( ( rule__Optimisation__MetamodelAssignment_1 ) ) ;
    public final void rule__Optimisation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:295:1: ( ( ( rule__Optimisation__MetamodelAssignment_1 ) ) )
            // InternalMDEOptimise.g:296:1: ( ( rule__Optimisation__MetamodelAssignment_1 ) )
            {
            // InternalMDEOptimise.g:296:1: ( ( rule__Optimisation__MetamodelAssignment_1 ) )
            // InternalMDEOptimise.g:297:2: ( rule__Optimisation__MetamodelAssignment_1 )
            {
             before(grammarAccess.getOptimisationAccess().getMetamodelAssignment_1()); 
            // InternalMDEOptimise.g:298:2: ( rule__Optimisation__MetamodelAssignment_1 )
            // InternalMDEOptimise.g:298:3: rule__Optimisation__MetamodelAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__MetamodelAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getMetamodelAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__1__Impl"


    // $ANTLR start "rule__Optimisation__Group__2"
    // InternalMDEOptimise.g:306:1: rule__Optimisation__Group__2 : rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3 ;
    public final void rule__Optimisation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:310:1: ( rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3 )
            // InternalMDEOptimise.g:311:2: rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Optimisation__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__2"


    // $ANTLR start "rule__Optimisation__Group__2__Impl"
    // InternalMDEOptimise.g:318:1: rule__Optimisation__Group__2__Impl : ( ( rule__Optimisation__ModelAssignment_2 ) ) ;
    public final void rule__Optimisation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:322:1: ( ( ( rule__Optimisation__ModelAssignment_2 ) ) )
            // InternalMDEOptimise.g:323:1: ( ( rule__Optimisation__ModelAssignment_2 ) )
            {
            // InternalMDEOptimise.g:323:1: ( ( rule__Optimisation__ModelAssignment_2 ) )
            // InternalMDEOptimise.g:324:2: ( rule__Optimisation__ModelAssignment_2 )
            {
             before(grammarAccess.getOptimisationAccess().getModelAssignment_2()); 
            // InternalMDEOptimise.g:325:2: ( rule__Optimisation__ModelAssignment_2 )
            // InternalMDEOptimise.g:325:3: rule__Optimisation__ModelAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__ModelAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getModelAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__2__Impl"


    // $ANTLR start "rule__Optimisation__Group__3"
    // InternalMDEOptimise.g:333:1: rule__Optimisation__Group__3 : rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4 ;
    public final void rule__Optimisation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:337:1: ( rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4 )
            // InternalMDEOptimise.g:338:2: rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Optimisation__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__3"


    // $ANTLR start "rule__Optimisation__Group__3__Impl"
    // InternalMDEOptimise.g:345:1: rule__Optimisation__Group__3__Impl : ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) ) ;
    public final void rule__Optimisation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:349:1: ( ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) ) )
            // InternalMDEOptimise.g:350:1: ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) )
            {
            // InternalMDEOptimise.g:350:1: ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) )
            // InternalMDEOptimise.g:351:2: ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* )
            {
            // InternalMDEOptimise.g:351:2: ( ( rule__Optimisation__ObjectivesAssignment_3 ) )
            // InternalMDEOptimise.g:352:3: ( rule__Optimisation__ObjectivesAssignment_3 )
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 
            // InternalMDEOptimise.g:353:3: ( rule__Optimisation__ObjectivesAssignment_3 )
            // InternalMDEOptimise.g:353:4: rule__Optimisation__ObjectivesAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__Optimisation__ObjectivesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 

            }

            // InternalMDEOptimise.g:356:2: ( ( rule__Optimisation__ObjectivesAssignment_3 )* )
            // InternalMDEOptimise.g:357:3: ( rule__Optimisation__ObjectivesAssignment_3 )*
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 
            // InternalMDEOptimise.g:358:3: ( rule__Optimisation__ObjectivesAssignment_3 )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==20) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMDEOptimise.g:358:4: rule__Optimisation__ObjectivesAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Optimisation__ObjectivesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__3__Impl"


    // $ANTLR start "rule__Optimisation__Group__4"
    // InternalMDEOptimise.g:367:1: rule__Optimisation__Group__4 : rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5 ;
    public final void rule__Optimisation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:371:1: ( rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5 )
            // InternalMDEOptimise.g:372:2: rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__Optimisation__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__4"


    // $ANTLR start "rule__Optimisation__Group__4__Impl"
    // InternalMDEOptimise.g:379:1: rule__Optimisation__Group__4__Impl : ( ( rule__Optimisation__ConstraintsAssignment_4 )* ) ;
    public final void rule__Optimisation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:383:1: ( ( ( rule__Optimisation__ConstraintsAssignment_4 )* ) )
            // InternalMDEOptimise.g:384:1: ( ( rule__Optimisation__ConstraintsAssignment_4 )* )
            {
            // InternalMDEOptimise.g:384:1: ( ( rule__Optimisation__ConstraintsAssignment_4 )* )
            // InternalMDEOptimise.g:385:2: ( rule__Optimisation__ConstraintsAssignment_4 )*
            {
             before(grammarAccess.getOptimisationAccess().getConstraintsAssignment_4()); 
            // InternalMDEOptimise.g:386:2: ( rule__Optimisation__ConstraintsAssignment_4 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==23) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMDEOptimise.g:386:3: rule__Optimisation__ConstraintsAssignment_4
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Optimisation__ConstraintsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getOptimisationAccess().getConstraintsAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__4__Impl"


    // $ANTLR start "rule__Optimisation__Group__5"
    // InternalMDEOptimise.g:394:1: rule__Optimisation__Group__5 : rule__Optimisation__Group__5__Impl rule__Optimisation__Group__6 ;
    public final void rule__Optimisation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:398:1: ( rule__Optimisation__Group__5__Impl rule__Optimisation__Group__6 )
            // InternalMDEOptimise.g:399:2: rule__Optimisation__Group__5__Impl rule__Optimisation__Group__6
            {
            pushFollow(FOLLOW_9);
            rule__Optimisation__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__5"


    // $ANTLR start "rule__Optimisation__Group__5__Impl"
    // InternalMDEOptimise.g:406:1: rule__Optimisation__Group__5__Impl : ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) ) ;
    public final void rule__Optimisation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:410:1: ( ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) ) )
            // InternalMDEOptimise.g:411:1: ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) )
            {
            // InternalMDEOptimise.g:411:1: ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) )
            // InternalMDEOptimise.g:412:2: ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* )
            {
            // InternalMDEOptimise.g:412:2: ( ( rule__Optimisation__EvolversAssignment_5 ) )
            // InternalMDEOptimise.g:413:3: ( rule__Optimisation__EvolversAssignment_5 )
            {
             before(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 
            // InternalMDEOptimise.g:414:3: ( rule__Optimisation__EvolversAssignment_5 )
            // InternalMDEOptimise.g:414:4: rule__Optimisation__EvolversAssignment_5
            {
            pushFollow(FOLLOW_10);
            rule__Optimisation__EvolversAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 

            }

            // InternalMDEOptimise.g:417:2: ( ( rule__Optimisation__EvolversAssignment_5 )* )
            // InternalMDEOptimise.g:418:3: ( rule__Optimisation__EvolversAssignment_5 )*
            {
             before(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 
            // InternalMDEOptimise.g:419:3: ( rule__Optimisation__EvolversAssignment_5 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==24) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMDEOptimise.g:419:4: rule__Optimisation__EvolversAssignment_5
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__Optimisation__EvolversAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__5__Impl"


    // $ANTLR start "rule__Optimisation__Group__6"
    // InternalMDEOptimise.g:428:1: rule__Optimisation__Group__6 : rule__Optimisation__Group__6__Impl ;
    public final void rule__Optimisation__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:432:1: ( rule__Optimisation__Group__6__Impl )
            // InternalMDEOptimise.g:433:2: rule__Optimisation__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__6"


    // $ANTLR start "rule__Optimisation__Group__6__Impl"
    // InternalMDEOptimise.g:439:1: rule__Optimisation__Group__6__Impl : ( ( rule__Optimisation__OptimisationAssignment_6 ) ) ;
    public final void rule__Optimisation__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:443:1: ( ( ( rule__Optimisation__OptimisationAssignment_6 ) ) )
            // InternalMDEOptimise.g:444:1: ( ( rule__Optimisation__OptimisationAssignment_6 ) )
            {
            // InternalMDEOptimise.g:444:1: ( ( rule__Optimisation__OptimisationAssignment_6 ) )
            // InternalMDEOptimise.g:445:2: ( rule__Optimisation__OptimisationAssignment_6 )
            {
             before(grammarAccess.getOptimisationAccess().getOptimisationAssignment_6()); 
            // InternalMDEOptimise.g:446:2: ( rule__Optimisation__OptimisationAssignment_6 )
            // InternalMDEOptimise.g:446:3: rule__Optimisation__OptimisationAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__OptimisationAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getOptimisationAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__Group__6__Impl"


    // $ANTLR start "rule__BasepathSpec__Group__0"
    // InternalMDEOptimise.g:455:1: rule__BasepathSpec__Group__0 : rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1 ;
    public final void rule__BasepathSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:459:1: ( rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1 )
            // InternalMDEOptimise.g:460:2: rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__BasepathSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__BasepathSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BasepathSpec__Group__0"


    // $ANTLR start "rule__BasepathSpec__Group__0__Impl"
    // InternalMDEOptimise.g:467:1: rule__BasepathSpec__Group__0__Impl : ( 'basepath' ) ;
    public final void rule__BasepathSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:471:1: ( ( 'basepath' ) )
            // InternalMDEOptimise.g:472:1: ( 'basepath' )
            {
            // InternalMDEOptimise.g:472:1: ( 'basepath' )
            // InternalMDEOptimise.g:473:2: 'basepath'
            {
             before(grammarAccess.getBasepathSpecAccess().getBasepathKeyword_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getBasepathSpecAccess().getBasepathKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BasepathSpec__Group__0__Impl"


    // $ANTLR start "rule__BasepathSpec__Group__1"
    // InternalMDEOptimise.g:482:1: rule__BasepathSpec__Group__1 : rule__BasepathSpec__Group__1__Impl ;
    public final void rule__BasepathSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:486:1: ( rule__BasepathSpec__Group__1__Impl )
            // InternalMDEOptimise.g:487:2: rule__BasepathSpec__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__BasepathSpec__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BasepathSpec__Group__1"


    // $ANTLR start "rule__BasepathSpec__Group__1__Impl"
    // InternalMDEOptimise.g:493:1: rule__BasepathSpec__Group__1__Impl : ( ( rule__BasepathSpec__LocationAssignment_1 ) ) ;
    public final void rule__BasepathSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:497:1: ( ( ( rule__BasepathSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:498:1: ( ( rule__BasepathSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:498:1: ( ( rule__BasepathSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:499:2: ( rule__BasepathSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getBasepathSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:500:2: ( rule__BasepathSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:500:3: rule__BasepathSpec__LocationAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__BasepathSpec__LocationAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getBasepathSpecAccess().getLocationAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BasepathSpec__Group__1__Impl"


    // $ANTLR start "rule__MetaModelSpec__Group__0"
    // InternalMDEOptimise.g:509:1: rule__MetaModelSpec__Group__0 : rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1 ;
    public final void rule__MetaModelSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:513:1: ( rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1 )
            // InternalMDEOptimise.g:514:2: rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__MetaModelSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaModelSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MetaModelSpec__Group__0"


    // $ANTLR start "rule__MetaModelSpec__Group__0__Impl"
    // InternalMDEOptimise.g:521:1: rule__MetaModelSpec__Group__0__Impl : ( 'metamodel' ) ;
    public final void rule__MetaModelSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:525:1: ( ( 'metamodel' ) )
            // InternalMDEOptimise.g:526:1: ( 'metamodel' )
            {
            // InternalMDEOptimise.g:526:1: ( 'metamodel' )
            // InternalMDEOptimise.g:527:2: 'metamodel'
            {
             before(grammarAccess.getMetaModelSpecAccess().getMetamodelKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getMetaModelSpecAccess().getMetamodelKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MetaModelSpec__Group__0__Impl"


    // $ANTLR start "rule__MetaModelSpec__Group__1"
    // InternalMDEOptimise.g:536:1: rule__MetaModelSpec__Group__1 : rule__MetaModelSpec__Group__1__Impl ;
    public final void rule__MetaModelSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:540:1: ( rule__MetaModelSpec__Group__1__Impl )
            // InternalMDEOptimise.g:541:2: rule__MetaModelSpec__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MetaModelSpec__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MetaModelSpec__Group__1"


    // $ANTLR start "rule__MetaModelSpec__Group__1__Impl"
    // InternalMDEOptimise.g:547:1: rule__MetaModelSpec__Group__1__Impl : ( ( rule__MetaModelSpec__LocationAssignment_1 ) ) ;
    public final void rule__MetaModelSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:551:1: ( ( ( rule__MetaModelSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:552:1: ( ( rule__MetaModelSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:552:1: ( ( rule__MetaModelSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:553:2: ( rule__MetaModelSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getMetaModelSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:554:2: ( rule__MetaModelSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:554:3: rule__MetaModelSpec__LocationAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__MetaModelSpec__LocationAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMetaModelSpecAccess().getLocationAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MetaModelSpec__Group__1__Impl"


    // $ANTLR start "rule__ModelPathSpec__Group__0"
    // InternalMDEOptimise.g:563:1: rule__ModelPathSpec__Group__0 : rule__ModelPathSpec__Group__0__Impl rule__ModelPathSpec__Group__1 ;
    public final void rule__ModelPathSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:567:1: ( rule__ModelPathSpec__Group__0__Impl rule__ModelPathSpec__Group__1 )
            // InternalMDEOptimise.g:568:2: rule__ModelPathSpec__Group__0__Impl rule__ModelPathSpec__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__ModelPathSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ModelPathSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ModelPathSpec__Group__0"


    // $ANTLR start "rule__ModelPathSpec__Group__0__Impl"
    // InternalMDEOptimise.g:575:1: rule__ModelPathSpec__Group__0__Impl : ( 'model' ) ;
    public final void rule__ModelPathSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:579:1: ( ( 'model' ) )
            // InternalMDEOptimise.g:580:1: ( 'model' )
            {
            // InternalMDEOptimise.g:580:1: ( 'model' )
            // InternalMDEOptimise.g:581:2: 'model'
            {
             before(grammarAccess.getModelPathSpecAccess().getModelKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getModelPathSpecAccess().getModelKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ModelPathSpec__Group__0__Impl"


    // $ANTLR start "rule__ModelPathSpec__Group__1"
    // InternalMDEOptimise.g:590:1: rule__ModelPathSpec__Group__1 : rule__ModelPathSpec__Group__1__Impl ;
    public final void rule__ModelPathSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:594:1: ( rule__ModelPathSpec__Group__1__Impl )
            // InternalMDEOptimise.g:595:2: rule__ModelPathSpec__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ModelPathSpec__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ModelPathSpec__Group__1"


    // $ANTLR start "rule__ModelPathSpec__Group__1__Impl"
    // InternalMDEOptimise.g:601:1: rule__ModelPathSpec__Group__1__Impl : ( ( rule__ModelPathSpec__LocationAssignment_1 ) ) ;
    public final void rule__ModelPathSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:605:1: ( ( ( rule__ModelPathSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:606:1: ( ( rule__ModelPathSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:606:1: ( ( rule__ModelPathSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:607:2: ( rule__ModelPathSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getModelPathSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:608:2: ( rule__ModelPathSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:608:3: rule__ModelPathSpec__LocationAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ModelPathSpec__LocationAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getModelPathSpecAccess().getLocationAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ModelPathSpec__Group__1__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__0"
    // InternalMDEOptimise.g:617:1: rule__ObjectiveInterpreterSpec__Group__0 : rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1 ;
    public final void rule__ObjectiveInterpreterSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:621:1: ( rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1 )
            // InternalMDEOptimise.g:622:2: rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__ObjectiveInterpreterSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__0"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__0__Impl"
    // InternalMDEOptimise.g:629:1: rule__ObjectiveInterpreterSpec__Group__0__Impl : ( 'objective' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:633:1: ( ( 'objective' ) )
            // InternalMDEOptimise.g:634:1: ( 'objective' )
            {
            // InternalMDEOptimise.g:634:1: ( 'objective' )
            // InternalMDEOptimise.g:635:2: 'objective'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveKeyword_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__0__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__1"
    // InternalMDEOptimise.g:644:1: rule__ObjectiveInterpreterSpec__Group__1 : rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2 ;
    public final void rule__ObjectiveInterpreterSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:648:1: ( rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2 )
            // InternalMDEOptimise.g:649:2: rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__ObjectiveInterpreterSpec__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__1"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__1__Impl"
    // InternalMDEOptimise.g:656:1: rule__ObjectiveInterpreterSpec__Group__1__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:660:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) ) )
            // InternalMDEOptimise.g:661:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) )
            {
            // InternalMDEOptimise.g:661:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) )
            // InternalMDEOptimise.g:662:2: ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameAssignment_1()); 
            // InternalMDEOptimise.g:663:2: ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 )
            // InternalMDEOptimise.g:663:3: rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__1__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__2"
    // InternalMDEOptimise.g:671:1: rule__ObjectiveInterpreterSpec__Group__2 : rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3 ;
    public final void rule__ObjectiveInterpreterSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:675:1: ( rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3 )
            // InternalMDEOptimise.g:676:2: rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__ObjectiveInterpreterSpec__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__2"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__2__Impl"
    // InternalMDEOptimise.g:683:1: rule__ObjectiveInterpreterSpec__Group__2__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:687:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) ) )
            // InternalMDEOptimise.g:688:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) )
            {
            // InternalMDEOptimise.g:688:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) )
            // InternalMDEOptimise.g:689:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyAssignment_2()); 
            // InternalMDEOptimise.g:690:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 )
            // InternalMDEOptimise.g:690:3: rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__2__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__3"
    // InternalMDEOptimise.g:698:1: rule__ObjectiveInterpreterSpec__Group__3 : rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4 ;
    public final void rule__ObjectiveInterpreterSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:702:1: ( rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4 )
            // InternalMDEOptimise.g:703:2: rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__ObjectiveInterpreterSpec__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__3"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__3__Impl"
    // InternalMDEOptimise.g:710:1: rule__ObjectiveInterpreterSpec__Group__3__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:714:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) ) )
            // InternalMDEOptimise.g:715:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) )
            {
            // InternalMDEOptimise.g:715:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) )
            // InternalMDEOptimise.g:716:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeAssignment_3()); 
            // InternalMDEOptimise.g:717:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 )
            // InternalMDEOptimise.g:717:3: rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__3__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__4"
    // InternalMDEOptimise.g:725:1: rule__ObjectiveInterpreterSpec__Group__4 : rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5 ;
    public final void rule__ObjectiveInterpreterSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:729:1: ( rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5 )
            // InternalMDEOptimise.g:730:2: rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5
            {
            pushFollow(FOLLOW_16);
            rule__ObjectiveInterpreterSpec__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__4"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__4__Impl"
    // InternalMDEOptimise.g:737:1: rule__ObjectiveInterpreterSpec__Group__4__Impl : ( '{' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:741:1: ( ( '{' ) )
            // InternalMDEOptimise.g:742:1: ( '{' )
            {
            // InternalMDEOptimise.g:742:1: ( '{' )
            // InternalMDEOptimise.g:743:2: '{'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getLeftCurlyBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__4__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__5"
    // InternalMDEOptimise.g:752:1: rule__ObjectiveInterpreterSpec__Group__5 : rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6 ;
    public final void rule__ObjectiveInterpreterSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:756:1: ( rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6 )
            // InternalMDEOptimise.g:757:2: rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6
            {
            pushFollow(FOLLOW_17);
            rule__ObjectiveInterpreterSpec__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__5"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__5__Impl"
    // InternalMDEOptimise.g:764:1: rule__ObjectiveInterpreterSpec__Group__5__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:768:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) ) )
            // InternalMDEOptimise.g:769:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) )
            {
            // InternalMDEOptimise.g:769:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) )
            // InternalMDEOptimise.g:770:2: ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecAssignment_5()); 
            // InternalMDEOptimise.g:771:2: ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 )
            // InternalMDEOptimise.g:771:3: rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__5__Impl"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__6"
    // InternalMDEOptimise.g:779:1: rule__ObjectiveInterpreterSpec__Group__6 : rule__ObjectiveInterpreterSpec__Group__6__Impl ;
    public final void rule__ObjectiveInterpreterSpec__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:783:1: ( rule__ObjectiveInterpreterSpec__Group__6__Impl )
            // InternalMDEOptimise.g:784:2: rule__ObjectiveInterpreterSpec__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ObjectiveInterpreterSpec__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__6"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__6__Impl"
    // InternalMDEOptimise.g:790:1: rule__ObjectiveInterpreterSpec__Group__6__Impl : ( '}' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:794:1: ( ( '}' ) )
            // InternalMDEOptimise.g:795:1: ( '}' )
            {
            // InternalMDEOptimise.g:795:1: ( '}' )
            // InternalMDEOptimise.g:796:2: '}'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getRightCurlyBracketKeyword_6()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getRightCurlyBracketKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__Group__6__Impl"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__0"
    // InternalMDEOptimise.g:806:1: rule__ConstraintInterpreterSpec__Group__0 : rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1 ;
    public final void rule__ConstraintInterpreterSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:810:1: ( rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1 )
            // InternalMDEOptimise.g:811:2: rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__ConstraintInterpreterSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__0"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__0__Impl"
    // InternalMDEOptimise.g:818:1: rule__ConstraintInterpreterSpec__Group__0__Impl : ( 'constraint' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:822:1: ( ( 'constraint' ) )
            // InternalMDEOptimise.g:823:1: ( 'constraint' )
            {
            // InternalMDEOptimise.g:823:1: ( 'constraint' )
            // InternalMDEOptimise.g:824:2: 'constraint'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintKeyword_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__0__Impl"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__1"
    // InternalMDEOptimise.g:833:1: rule__ConstraintInterpreterSpec__Group__1 : rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2 ;
    public final void rule__ConstraintInterpreterSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:837:1: ( rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2 )
            // InternalMDEOptimise.g:838:2: rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__ConstraintInterpreterSpec__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__1"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__1__Impl"
    // InternalMDEOptimise.g:845:1: rule__ConstraintInterpreterSpec__Group__1__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:849:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) ) )
            // InternalMDEOptimise.g:850:1: ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) )
            {
            // InternalMDEOptimise.g:850:1: ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) )
            // InternalMDEOptimise.g:851:2: ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameAssignment_1()); 
            // InternalMDEOptimise.g:852:2: ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 )
            // InternalMDEOptimise.g:852:3: rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__1__Impl"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__2"
    // InternalMDEOptimise.g:860:1: rule__ConstraintInterpreterSpec__Group__2 : rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3 ;
    public final void rule__ConstraintInterpreterSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:864:1: ( rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3 )
            // InternalMDEOptimise.g:865:2: rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__ConstraintInterpreterSpec__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__2"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__2__Impl"
    // InternalMDEOptimise.g:872:1: rule__ConstraintInterpreterSpec__Group__2__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:876:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) ) )
            // InternalMDEOptimise.g:877:1: ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) )
            {
            // InternalMDEOptimise.g:877:1: ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) )
            // InternalMDEOptimise.g:878:2: ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeAssignment_2()); 
            // InternalMDEOptimise.g:879:2: ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 )
            // InternalMDEOptimise.g:879:3: rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__2__Impl"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__3"
    // InternalMDEOptimise.g:887:1: rule__ConstraintInterpreterSpec__Group__3 : rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4 ;
    public final void rule__ConstraintInterpreterSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:891:1: ( rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4 )
            // InternalMDEOptimise.g:892:2: rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4
            {
            pushFollow(FOLLOW_16);
            rule__ConstraintInterpreterSpec__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__3"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__3__Impl"
    // InternalMDEOptimise.g:899:1: rule__ConstraintInterpreterSpec__Group__3__Impl : ( '{' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:903:1: ( ( '{' ) )
            // InternalMDEOptimise.g:904:1: ( '{' )
            {
            // InternalMDEOptimise.g:904:1: ( '{' )
            // InternalMDEOptimise.g:905:2: '{'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getConstraintInterpreterSpecAccess().getLeftCurlyBracketKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__3__Impl"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__4"
    // InternalMDEOptimise.g:914:1: rule__ConstraintInterpreterSpec__Group__4 : rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5 ;
    public final void rule__ConstraintInterpreterSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:918:1: ( rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5 )
            // InternalMDEOptimise.g:919:2: rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5
            {
            pushFollow(FOLLOW_17);
            rule__ConstraintInterpreterSpec__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__4"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__4__Impl"
    // InternalMDEOptimise.g:926:1: rule__ConstraintInterpreterSpec__Group__4__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:930:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) ) )
            // InternalMDEOptimise.g:931:1: ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) )
            {
            // InternalMDEOptimise.g:931:1: ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) )
            // InternalMDEOptimise.g:932:2: ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecAssignment_4()); 
            // InternalMDEOptimise.g:933:2: ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 )
            // InternalMDEOptimise.g:933:3: rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__4__Impl"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__5"
    // InternalMDEOptimise.g:941:1: rule__ConstraintInterpreterSpec__Group__5 : rule__ConstraintInterpreterSpec__Group__5__Impl ;
    public final void rule__ConstraintInterpreterSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:945:1: ( rule__ConstraintInterpreterSpec__Group__5__Impl )
            // InternalMDEOptimise.g:946:2: rule__ConstraintInterpreterSpec__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ConstraintInterpreterSpec__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__5"


    // $ANTLR start "rule__ConstraintInterpreterSpec__Group__5__Impl"
    // InternalMDEOptimise.g:952:1: rule__ConstraintInterpreterSpec__Group__5__Impl : ( '}' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:956:1: ( ( '}' ) )
            // InternalMDEOptimise.g:957:1: ( '}' )
            {
            // InternalMDEOptimise.g:957:1: ( '}' )
            // InternalMDEOptimise.g:958:2: '}'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getRightCurlyBracketKeyword_5()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getConstraintInterpreterSpecAccess().getRightCurlyBracketKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__Group__5__Impl"


    // $ANTLR start "rule__EvolverSpec__Group__0"
    // InternalMDEOptimise.g:968:1: rule__EvolverSpec__Group__0 : rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1 ;
    public final void rule__EvolverSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:972:1: ( rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1 )
            // InternalMDEOptimise.g:973:2: rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1
            {
            pushFollow(FOLLOW_18);
            rule__EvolverSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__0"


    // $ANTLR start "rule__EvolverSpec__Group__0__Impl"
    // InternalMDEOptimise.g:980:1: rule__EvolverSpec__Group__0__Impl : ( 'evolve' ) ;
    public final void rule__EvolverSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:984:1: ( ( 'evolve' ) )
            // InternalMDEOptimise.g:985:1: ( 'evolve' )
            {
            // InternalMDEOptimise.g:985:1: ( 'evolve' )
            // InternalMDEOptimise.g:986:2: 'evolve'
            {
             before(grammarAccess.getEvolverSpecAccess().getEvolveKeyword_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getEvolverSpecAccess().getEvolveKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__0__Impl"


    // $ANTLR start "rule__EvolverSpec__Group__1"
    // InternalMDEOptimise.g:995:1: rule__EvolverSpec__Group__1 : rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2 ;
    public final void rule__EvolverSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:999:1: ( rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2 )
            // InternalMDEOptimise.g:1000:2: rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__EvolverSpec__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__1"


    // $ANTLR start "rule__EvolverSpec__Group__1__Impl"
    // InternalMDEOptimise.g:1007:1: rule__EvolverSpec__Group__1__Impl : ( 'using' ) ;
    public final void rule__EvolverSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1011:1: ( ( 'using' ) )
            // InternalMDEOptimise.g:1012:1: ( 'using' )
            {
            // InternalMDEOptimise.g:1012:1: ( 'using' )
            // InternalMDEOptimise.g:1013:2: 'using'
            {
             before(grammarAccess.getEvolverSpecAccess().getUsingKeyword_1()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getEvolverSpecAccess().getUsingKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__1__Impl"


    // $ANTLR start "rule__EvolverSpec__Group__2"
    // InternalMDEOptimise.g:1022:1: rule__EvolverSpec__Group__2 : rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3 ;
    public final void rule__EvolverSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1026:1: ( rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3 )
            // InternalMDEOptimise.g:1027:2: rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3
            {
            pushFollow(FOLLOW_19);
            rule__EvolverSpec__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__2"


    // $ANTLR start "rule__EvolverSpec__Group__2__Impl"
    // InternalMDEOptimise.g:1034:1: rule__EvolverSpec__Group__2__Impl : ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) ) ;
    public final void rule__EvolverSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1038:1: ( ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) ) )
            // InternalMDEOptimise.g:1039:1: ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) )
            {
            // InternalMDEOptimise.g:1039:1: ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) )
            // InternalMDEOptimise.g:1040:2: ( rule__EvolverSpec__Rule_locationAssignment_2 )
            {
             before(grammarAccess.getEvolverSpecAccess().getRule_locationAssignment_2()); 
            // InternalMDEOptimise.g:1041:2: ( rule__EvolverSpec__Rule_locationAssignment_2 )
            // InternalMDEOptimise.g:1041:3: rule__EvolverSpec__Rule_locationAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Rule_locationAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getEvolverSpecAccess().getRule_locationAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__2__Impl"


    // $ANTLR start "rule__EvolverSpec__Group__3"
    // InternalMDEOptimise.g:1049:1: rule__EvolverSpec__Group__3 : rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4 ;
    public final void rule__EvolverSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1053:1: ( rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4 )
            // InternalMDEOptimise.g:1054:2: rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4
            {
            pushFollow(FOLLOW_16);
            rule__EvolverSpec__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__3"


    // $ANTLR start "rule__EvolverSpec__Group__3__Impl"
    // InternalMDEOptimise.g:1061:1: rule__EvolverSpec__Group__3__Impl : ( 'unit' ) ;
    public final void rule__EvolverSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1065:1: ( ( 'unit' ) )
            // InternalMDEOptimise.g:1066:1: ( 'unit' )
            {
            // InternalMDEOptimise.g:1066:1: ( 'unit' )
            // InternalMDEOptimise.g:1067:2: 'unit'
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitKeyword_3()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getEvolverSpecAccess().getUnitKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__3__Impl"


    // $ANTLR start "rule__EvolverSpec__Group__4"
    // InternalMDEOptimise.g:1076:1: rule__EvolverSpec__Group__4 : rule__EvolverSpec__Group__4__Impl ;
    public final void rule__EvolverSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1080:1: ( rule__EvolverSpec__Group__4__Impl )
            // InternalMDEOptimise.g:1081:2: rule__EvolverSpec__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__EvolverSpec__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__4"


    // $ANTLR start "rule__EvolverSpec__Group__4__Impl"
    // InternalMDEOptimise.g:1087:1: rule__EvolverSpec__Group__4__Impl : ( ( rule__EvolverSpec__UnitAssignment_4 ) ) ;
    public final void rule__EvolverSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1091:1: ( ( ( rule__EvolverSpec__UnitAssignment_4 ) ) )
            // InternalMDEOptimise.g:1092:1: ( ( rule__EvolverSpec__UnitAssignment_4 ) )
            {
            // InternalMDEOptimise.g:1092:1: ( ( rule__EvolverSpec__UnitAssignment_4 ) )
            // InternalMDEOptimise.g:1093:2: ( rule__EvolverSpec__UnitAssignment_4 )
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitAssignment_4()); 
            // InternalMDEOptimise.g:1094:2: ( rule__EvolverSpec__UnitAssignment_4 )
            // InternalMDEOptimise.g:1094:3: rule__EvolverSpec__UnitAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__EvolverSpec__UnitAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getEvolverSpecAccess().getUnitAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Group__4__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__0"
    // InternalMDEOptimise.g:1103:1: rule__OptimisationSpec__Group__0 : rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1 ;
    public final void rule__OptimisationSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1107:1: ( rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1 )
            // InternalMDEOptimise.g:1108:2: rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1
            {
            pushFollow(FOLLOW_20);
            rule__OptimisationSpec__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__0"


    // $ANTLR start "rule__OptimisationSpec__Group__0__Impl"
    // InternalMDEOptimise.g:1115:1: rule__OptimisationSpec__Group__0__Impl : ( 'optimisation' ) ;
    public final void rule__OptimisationSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1119:1: ( ( 'optimisation' ) )
            // InternalMDEOptimise.g:1120:1: ( 'optimisation' )
            {
            // InternalMDEOptimise.g:1120:1: ( 'optimisation' )
            // InternalMDEOptimise.g:1121:2: 'optimisation'
            {
             before(grammarAccess.getOptimisationSpecAccess().getOptimisationKeyword_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getOptimisationKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__0__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__1"
    // InternalMDEOptimise.g:1130:1: rule__OptimisationSpec__Group__1 : rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2 ;
    public final void rule__OptimisationSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1134:1: ( rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2 )
            // InternalMDEOptimise.g:1135:2: rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2
            {
            pushFollow(FOLLOW_21);
            rule__OptimisationSpec__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__1"


    // $ANTLR start "rule__OptimisationSpec__Group__1__Impl"
    // InternalMDEOptimise.g:1142:1: rule__OptimisationSpec__Group__1__Impl : ( 'provider' ) ;
    public final void rule__OptimisationSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1146:1: ( ( 'provider' ) )
            // InternalMDEOptimise.g:1147:1: ( 'provider' )
            {
            // InternalMDEOptimise.g:1147:1: ( 'provider' )
            // InternalMDEOptimise.g:1148:2: 'provider'
            {
             before(grammarAccess.getOptimisationSpecAccess().getProviderKeyword_1()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getProviderKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__1__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__2"
    // InternalMDEOptimise.g:1157:1: rule__OptimisationSpec__Group__2 : rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3 ;
    public final void rule__OptimisationSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1161:1: ( rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3 )
            // InternalMDEOptimise.g:1162:2: rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3
            {
            pushFollow(FOLLOW_22);
            rule__OptimisationSpec__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__2"


    // $ANTLR start "rule__OptimisationSpec__Group__2__Impl"
    // InternalMDEOptimise.g:1169:1: rule__OptimisationSpec__Group__2__Impl : ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) ) ;
    public final void rule__OptimisationSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1173:1: ( ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) ) )
            // InternalMDEOptimise.g:1174:1: ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) )
            {
            // InternalMDEOptimise.g:1174:1: ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) )
            // InternalMDEOptimise.g:1175:2: ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryAssignment_2()); 
            // InternalMDEOptimise.g:1176:2: ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 )
            // InternalMDEOptimise.g:1176:3: rule__OptimisationSpec__AlgorithmFactoryAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmFactoryAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__2__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__3"
    // InternalMDEOptimise.g:1184:1: rule__OptimisationSpec__Group__3 : rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4 ;
    public final void rule__OptimisationSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1188:1: ( rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4 )
            // InternalMDEOptimise.g:1189:2: rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4
            {
            pushFollow(FOLLOW_23);
            rule__OptimisationSpec__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__3"


    // $ANTLR start "rule__OptimisationSpec__Group__3__Impl"
    // InternalMDEOptimise.g:1196:1: rule__OptimisationSpec__Group__3__Impl : ( 'algorithm' ) ;
    public final void rule__OptimisationSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1200:1: ( ( 'algorithm' ) )
            // InternalMDEOptimise.g:1201:1: ( 'algorithm' )
            {
            // InternalMDEOptimise.g:1201:1: ( 'algorithm' )
            // InternalMDEOptimise.g:1202:2: 'algorithm'
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmKeyword_3()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__3__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__4"
    // InternalMDEOptimise.g:1211:1: rule__OptimisationSpec__Group__4 : rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5 ;
    public final void rule__OptimisationSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1215:1: ( rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5 )
            // InternalMDEOptimise.g:1216:2: rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5
            {
            pushFollow(FOLLOW_24);
            rule__OptimisationSpec__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__4"


    // $ANTLR start "rule__OptimisationSpec__Group__4__Impl"
    // InternalMDEOptimise.g:1223:1: rule__OptimisationSpec__Group__4__Impl : ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) ) ;
    public final void rule__OptimisationSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1227:1: ( ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) ) )
            // InternalMDEOptimise.g:1228:1: ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) )
            {
            // InternalMDEOptimise.g:1228:1: ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) )
            // InternalMDEOptimise.g:1229:2: ( rule__OptimisationSpec__AlgorithmNameAssignment_4 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmNameAssignment_4()); 
            // InternalMDEOptimise.g:1230:2: ( rule__OptimisationSpec__AlgorithmNameAssignment_4 )
            // InternalMDEOptimise.g:1230:3: rule__OptimisationSpec__AlgorithmNameAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmNameAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmNameAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__4__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__5"
    // InternalMDEOptimise.g:1238:1: rule__OptimisationSpec__Group__5 : rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6 ;
    public final void rule__OptimisationSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1242:1: ( rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6 )
            // InternalMDEOptimise.g:1243:2: rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6
            {
            pushFollow(FOLLOW_25);
            rule__OptimisationSpec__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__5"


    // $ANTLR start "rule__OptimisationSpec__Group__5__Impl"
    // InternalMDEOptimise.g:1250:1: rule__OptimisationSpec__Group__5__Impl : ( 'evolutions' ) ;
    public final void rule__OptimisationSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1254:1: ( ( 'evolutions' ) )
            // InternalMDEOptimise.g:1255:1: ( 'evolutions' )
            {
            // InternalMDEOptimise.g:1255:1: ( 'evolutions' )
            // InternalMDEOptimise.g:1256:2: 'evolutions'
            {
             before(grammarAccess.getOptimisationSpecAccess().getEvolutionsKeyword_5()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getEvolutionsKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__5__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__6"
    // InternalMDEOptimise.g:1265:1: rule__OptimisationSpec__Group__6 : rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7 ;
    public final void rule__OptimisationSpec__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1269:1: ( rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7 )
            // InternalMDEOptimise.g:1270:2: rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7
            {
            pushFollow(FOLLOW_26);
            rule__OptimisationSpec__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__6"


    // $ANTLR start "rule__OptimisationSpec__Group__6__Impl"
    // InternalMDEOptimise.g:1277:1: rule__OptimisationSpec__Group__6__Impl : ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) ) ;
    public final void rule__OptimisationSpec__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1281:1: ( ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) ) )
            // InternalMDEOptimise.g:1282:1: ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) )
            {
            // InternalMDEOptimise.g:1282:1: ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) )
            // InternalMDEOptimise.g:1283:2: ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsAssignment_6()); 
            // InternalMDEOptimise.g:1284:2: ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 )
            // InternalMDEOptimise.g:1284:3: rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__6__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__7"
    // InternalMDEOptimise.g:1292:1: rule__OptimisationSpec__Group__7 : rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8 ;
    public final void rule__OptimisationSpec__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1296:1: ( rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8 )
            // InternalMDEOptimise.g:1297:2: rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8
            {
            pushFollow(FOLLOW_25);
            rule__OptimisationSpec__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__7"


    // $ANTLR start "rule__OptimisationSpec__Group__7__Impl"
    // InternalMDEOptimise.g:1304:1: rule__OptimisationSpec__Group__7__Impl : ( 'population' ) ;
    public final void rule__OptimisationSpec__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1308:1: ( ( 'population' ) )
            // InternalMDEOptimise.g:1309:1: ( 'population' )
            {
            // InternalMDEOptimise.g:1309:1: ( 'population' )
            // InternalMDEOptimise.g:1310:2: 'population'
            {
             before(grammarAccess.getOptimisationSpecAccess().getPopulationKeyword_7()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getPopulationKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__7__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__8"
    // InternalMDEOptimise.g:1319:1: rule__OptimisationSpec__Group__8 : rule__OptimisationSpec__Group__8__Impl ;
    public final void rule__OptimisationSpec__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1323:1: ( rule__OptimisationSpec__Group__8__Impl )
            // InternalMDEOptimise.g:1324:2: rule__OptimisationSpec__Group__8__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__8__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__8"


    // $ANTLR start "rule__OptimisationSpec__Group__8__Impl"
    // InternalMDEOptimise.g:1330:1: rule__OptimisationSpec__Group__8__Impl : ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) ) ;
    public final void rule__OptimisationSpec__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1334:1: ( ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) ) )
            // InternalMDEOptimise.g:1335:1: ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) )
            {
            // InternalMDEOptimise.g:1335:1: ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) )
            // InternalMDEOptimise.g:1336:2: ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationAssignment_8()); 
            // InternalMDEOptimise.g:1337:2: ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 )
            // InternalMDEOptimise.g:1337:3: rule__OptimisationSpec__AlgorithmPopulationAssignment_8
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmPopulationAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationAssignment_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__Group__8__Impl"


    // $ANTLR start "rule__Optimisation__BasepathAssignment_0"
    // InternalMDEOptimise.g:1346:1: rule__Optimisation__BasepathAssignment_0 : ( ruleBasepathSpec ) ;
    public final void rule__Optimisation__BasepathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1350:1: ( ( ruleBasepathSpec ) )
            // InternalMDEOptimise.g:1351:2: ( ruleBasepathSpec )
            {
            // InternalMDEOptimise.g:1351:2: ( ruleBasepathSpec )
            // InternalMDEOptimise.g:1352:3: ruleBasepathSpec
            {
             before(grammarAccess.getOptimisationAccess().getBasepathBasepathSpecParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleBasepathSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getBasepathBasepathSpecParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__BasepathAssignment_0"


    // $ANTLR start "rule__Optimisation__MetamodelAssignment_1"
    // InternalMDEOptimise.g:1361:1: rule__Optimisation__MetamodelAssignment_1 : ( ruleMetaModelSpec ) ;
    public final void rule__Optimisation__MetamodelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1365:1: ( ( ruleMetaModelSpec ) )
            // InternalMDEOptimise.g:1366:2: ( ruleMetaModelSpec )
            {
            // InternalMDEOptimise.g:1366:2: ( ruleMetaModelSpec )
            // InternalMDEOptimise.g:1367:3: ruleMetaModelSpec
            {
             before(grammarAccess.getOptimisationAccess().getMetamodelMetaModelSpecParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMetaModelSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getMetamodelMetaModelSpecParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__MetamodelAssignment_1"


    // $ANTLR start "rule__Optimisation__ModelAssignment_2"
    // InternalMDEOptimise.g:1376:1: rule__Optimisation__ModelAssignment_2 : ( ruleModelPathSpec ) ;
    public final void rule__Optimisation__ModelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1380:1: ( ( ruleModelPathSpec ) )
            // InternalMDEOptimise.g:1381:2: ( ruleModelPathSpec )
            {
            // InternalMDEOptimise.g:1381:2: ( ruleModelPathSpec )
            // InternalMDEOptimise.g:1382:3: ruleModelPathSpec
            {
             before(grammarAccess.getOptimisationAccess().getModelModelPathSpecParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleModelPathSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getModelModelPathSpecParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__ModelAssignment_2"


    // $ANTLR start "rule__Optimisation__ObjectivesAssignment_3"
    // InternalMDEOptimise.g:1391:1: rule__Optimisation__ObjectivesAssignment_3 : ( ruleObjectiveInterpreterSpec ) ;
    public final void rule__Optimisation__ObjectivesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1395:1: ( ( ruleObjectiveInterpreterSpec ) )
            // InternalMDEOptimise.g:1396:2: ( ruleObjectiveInterpreterSpec )
            {
            // InternalMDEOptimise.g:1396:2: ( ruleObjectiveInterpreterSpec )
            // InternalMDEOptimise.g:1397:3: ruleObjectiveInterpreterSpec
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesObjectiveInterpreterSpecParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleObjectiveInterpreterSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getObjectivesObjectiveInterpreterSpecParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__ObjectivesAssignment_3"


    // $ANTLR start "rule__Optimisation__ConstraintsAssignment_4"
    // InternalMDEOptimise.g:1406:1: rule__Optimisation__ConstraintsAssignment_4 : ( ruleConstraintInterpreterSpec ) ;
    public final void rule__Optimisation__ConstraintsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1410:1: ( ( ruleConstraintInterpreterSpec ) )
            // InternalMDEOptimise.g:1411:2: ( ruleConstraintInterpreterSpec )
            {
            // InternalMDEOptimise.g:1411:2: ( ruleConstraintInterpreterSpec )
            // InternalMDEOptimise.g:1412:3: ruleConstraintInterpreterSpec
            {
             before(grammarAccess.getOptimisationAccess().getConstraintsConstraintInterpreterSpecParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleConstraintInterpreterSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getConstraintsConstraintInterpreterSpecParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__ConstraintsAssignment_4"


    // $ANTLR start "rule__Optimisation__EvolversAssignment_5"
    // InternalMDEOptimise.g:1421:1: rule__Optimisation__EvolversAssignment_5 : ( ruleEvolverSpec ) ;
    public final void rule__Optimisation__EvolversAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1425:1: ( ( ruleEvolverSpec ) )
            // InternalMDEOptimise.g:1426:2: ( ruleEvolverSpec )
            {
            // InternalMDEOptimise.g:1426:2: ( ruleEvolverSpec )
            // InternalMDEOptimise.g:1427:3: ruleEvolverSpec
            {
             before(grammarAccess.getOptimisationAccess().getEvolversEvolverSpecParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleEvolverSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getEvolversEvolverSpecParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__EvolversAssignment_5"


    // $ANTLR start "rule__Optimisation__OptimisationAssignment_6"
    // InternalMDEOptimise.g:1436:1: rule__Optimisation__OptimisationAssignment_6 : ( ruleOptimisationSpec ) ;
    public final void rule__Optimisation__OptimisationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1440:1: ( ( ruleOptimisationSpec ) )
            // InternalMDEOptimise.g:1441:2: ( ruleOptimisationSpec )
            {
            // InternalMDEOptimise.g:1441:2: ( ruleOptimisationSpec )
            // InternalMDEOptimise.g:1442:3: ruleOptimisationSpec
            {
             before(grammarAccess.getOptimisationAccess().getOptimisationOptimisationSpecParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleOptimisationSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getOptimisationOptimisationSpecParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Optimisation__OptimisationAssignment_6"


    // $ANTLR start "rule__BasepathSpec__LocationAssignment_1"
    // InternalMDEOptimise.g:1451:1: rule__BasepathSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__BasepathSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1455:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1456:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1456:2: ( RULE_URL )
            // InternalMDEOptimise.g:1457:3: RULE_URL
            {
             before(grammarAccess.getBasepathSpecAccess().getLocationURLTerminalRuleCall_1_0()); 
            match(input,RULE_URL,FOLLOW_2); 
             after(grammarAccess.getBasepathSpecAccess().getLocationURLTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__BasepathSpec__LocationAssignment_1"


    // $ANTLR start "rule__MetaModelSpec__LocationAssignment_1"
    // InternalMDEOptimise.g:1466:1: rule__MetaModelSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__MetaModelSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1470:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1471:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1471:2: ( RULE_URL )
            // InternalMDEOptimise.g:1472:3: RULE_URL
            {
             before(grammarAccess.getMetaModelSpecAccess().getLocationURLTerminalRuleCall_1_0()); 
            match(input,RULE_URL,FOLLOW_2); 
             after(grammarAccess.getMetaModelSpecAccess().getLocationURLTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MetaModelSpec__LocationAssignment_1"


    // $ANTLR start "rule__ModelPathSpec__LocationAssignment_1"
    // InternalMDEOptimise.g:1481:1: rule__ModelPathSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__ModelPathSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1485:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1486:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1486:2: ( RULE_URL )
            // InternalMDEOptimise.g:1487:3: RULE_URL
            {
             before(grammarAccess.getModelPathSpecAccess().getLocationURLTerminalRuleCall_1_0()); 
            match(input,RULE_URL,FOLLOW_2); 
             after(grammarAccess.getModelPathSpecAccess().getLocationURLTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ModelPathSpec__LocationAssignment_1"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1"
    // InternalMDEOptimise.g:1496:1: rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 : ( RULE_ENTITY_NAME ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1500:1: ( ( RULE_ENTITY_NAME ) )
            // InternalMDEOptimise.g:1501:2: ( RULE_ENTITY_NAME )
            {
            // InternalMDEOptimise.g:1501:2: ( RULE_ENTITY_NAME )
            // InternalMDEOptimise.g:1502:3: RULE_ENTITY_NAME
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameENTITY_NAMETerminalRuleCall_1_0()); 
            match(input,RULE_ENTITY_NAME,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameENTITY_NAMETerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2"
    // InternalMDEOptimise.g:1511:1: rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 : ( RULE_OBJECTIVE_TENDENCY ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1515:1: ( ( RULE_OBJECTIVE_TENDENCY ) )
            // InternalMDEOptimise.g:1516:2: ( RULE_OBJECTIVE_TENDENCY )
            {
            // InternalMDEOptimise.g:1516:2: ( RULE_OBJECTIVE_TENDENCY )
            // InternalMDEOptimise.g:1517:3: RULE_OBJECTIVE_TENDENCY
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyOBJECTIVE_TENDENCYTerminalRuleCall_2_0()); 
            match(input,RULE_OBJECTIVE_TENDENCY,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyOBJECTIVE_TENDENCYTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3"
    // InternalMDEOptimise.g:1526:1: rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 : ( RULE_ENTITY_TYPE ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1530:1: ( ( RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:1531:2: ( RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:1531:2: ( RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:1532:3: RULE_ENTITY_TYPE
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeENTITY_TYPETerminalRuleCall_3_0()); 
            match(input,RULE_ENTITY_TYPE,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeENTITY_TYPETerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3"


    // $ANTLR start "rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5"
    // InternalMDEOptimise.g:1541:1: rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 : ( RULE_STRING ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1545:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1546:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1546:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1547:3: RULE_STRING
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecSTRINGTerminalRuleCall_5_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecSTRINGTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5"


    // $ANTLR start "rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1"
    // InternalMDEOptimise.g:1556:1: rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 : ( RULE_ENTITY_NAME ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1560:1: ( ( RULE_ENTITY_NAME ) )
            // InternalMDEOptimise.g:1561:2: ( RULE_ENTITY_NAME )
            {
            // InternalMDEOptimise.g:1561:2: ( RULE_ENTITY_NAME )
            // InternalMDEOptimise.g:1562:3: RULE_ENTITY_NAME
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameENTITY_NAMETerminalRuleCall_1_0()); 
            match(input,RULE_ENTITY_NAME,FOLLOW_2); 
             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameENTITY_NAMETerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1"


    // $ANTLR start "rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2"
    // InternalMDEOptimise.g:1571:1: rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 : ( RULE_ENTITY_TYPE ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1575:1: ( ( RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:1576:2: ( RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:1576:2: ( RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:1577:3: RULE_ENTITY_TYPE
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeENTITY_TYPETerminalRuleCall_2_0()); 
            match(input,RULE_ENTITY_TYPE,FOLLOW_2); 
             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeENTITY_TYPETerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2"


    // $ANTLR start "rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4"
    // InternalMDEOptimise.g:1586:1: rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 : ( RULE_STRING ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1590:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1591:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1591:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1592:3: RULE_STRING
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecSTRINGTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4"


    // $ANTLR start "rule__EvolverSpec__Rule_locationAssignment_2"
    // InternalMDEOptimise.g:1601:1: rule__EvolverSpec__Rule_locationAssignment_2 : ( RULE_URL ) ;
    public final void rule__EvolverSpec__Rule_locationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1605:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1606:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1606:2: ( RULE_URL )
            // InternalMDEOptimise.g:1607:3: RULE_URL
            {
             before(grammarAccess.getEvolverSpecAccess().getRule_locationURLTerminalRuleCall_2_0()); 
            match(input,RULE_URL,FOLLOW_2); 
             after(grammarAccess.getEvolverSpecAccess().getRule_locationURLTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__Rule_locationAssignment_2"


    // $ANTLR start "rule__EvolverSpec__UnitAssignment_4"
    // InternalMDEOptimise.g:1616:1: rule__EvolverSpec__UnitAssignment_4 : ( RULE_STRING ) ;
    public final void rule__EvolverSpec__UnitAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1620:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1621:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1621:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1622:3: RULE_STRING
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitSTRINGTerminalRuleCall_4_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getEvolverSpecAccess().getUnitSTRINGTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__EvolverSpec__UnitAssignment_4"


    // $ANTLR start "rule__OptimisationSpec__AlgorithmFactoryAssignment_2"
    // InternalMDEOptimise.g:1631:1: rule__OptimisationSpec__AlgorithmFactoryAssignment_2 : ( RULE_ALGORITHM_FACTORY ) ;
    public final void rule__OptimisationSpec__AlgorithmFactoryAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1635:1: ( ( RULE_ALGORITHM_FACTORY ) )
            // InternalMDEOptimise.g:1636:2: ( RULE_ALGORITHM_FACTORY )
            {
            // InternalMDEOptimise.g:1636:2: ( RULE_ALGORITHM_FACTORY )
            // InternalMDEOptimise.g:1637:3: RULE_ALGORITHM_FACTORY
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryALGORITHM_FACTORYTerminalRuleCall_2_0()); 
            match(input,RULE_ALGORITHM_FACTORY,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryALGORITHM_FACTORYTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__AlgorithmFactoryAssignment_2"


    // $ANTLR start "rule__OptimisationSpec__AlgorithmNameAssignment_4"
    // InternalMDEOptimise.g:1646:1: rule__OptimisationSpec__AlgorithmNameAssignment_4 : ( RULE_ALGORITHM_NAME ) ;
    public final void rule__OptimisationSpec__AlgorithmNameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1650:1: ( ( RULE_ALGORITHM_NAME ) )
            // InternalMDEOptimise.g:1651:2: ( RULE_ALGORITHM_NAME )
            {
            // InternalMDEOptimise.g:1651:2: ( RULE_ALGORITHM_NAME )
            // InternalMDEOptimise.g:1652:3: RULE_ALGORITHM_NAME
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmNameALGORITHM_NAMETerminalRuleCall_4_0()); 
            match(input,RULE_ALGORITHM_NAME,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmNameALGORITHM_NAMETerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__AlgorithmNameAssignment_4"


    // $ANTLR start "rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6"
    // InternalMDEOptimise.g:1661:1: rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 : ( RULE_INT ) ;
    public final void rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1665:1: ( ( RULE_INT ) )
            // InternalMDEOptimise.g:1666:2: ( RULE_INT )
            {
            // InternalMDEOptimise.g:1666:2: ( RULE_INT )
            // InternalMDEOptimise.g:1667:3: RULE_INT
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsINTTerminalRuleCall_6_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsINTTerminalRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6"


    // $ANTLR start "rule__OptimisationSpec__AlgorithmPopulationAssignment_8"
    // InternalMDEOptimise.g:1676:1: rule__OptimisationSpec__AlgorithmPopulationAssignment_8 : ( RULE_INT ) ;
    public final void rule__OptimisationSpec__AlgorithmPopulationAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1680:1: ( ( RULE_INT ) )
            // InternalMDEOptimise.g:1681:2: ( RULE_INT )
            {
            // InternalMDEOptimise.g:1681:2: ( RULE_INT )
            // InternalMDEOptimise.g:1682:3: RULE_INT
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationINTTerminalRuleCall_8_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationINTTerminalRuleCall_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__OptimisationSpec__AlgorithmPopulationAssignment_8"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000001800000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000001800002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000080000000L});

}