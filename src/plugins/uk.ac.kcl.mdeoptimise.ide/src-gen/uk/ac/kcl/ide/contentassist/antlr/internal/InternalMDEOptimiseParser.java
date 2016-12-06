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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_URL", "RULE_ENTITY_NAME", "RULE_OBJECTIVE_TENDENCY", "RULE_ENTITY_TYPE", "RULE_STRING", "RULE_ALGORITHM_FACTORY", "RULE_ALGORITHM_NAME", "RULE_INT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'basepath'", "'metamodel'", "'objective'", "'{'", "'}'", "'constraint'", "'evolve'", "'using'", "'unit'", "'optimisation'", "'provider'", "'algorithm'", "'evolutions'", "'population'"
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


    // $ANTLR start "entryRuleObjectiveInterpreterSpec"
    // InternalMDEOptimise.g:128:1: entryRuleObjectiveInterpreterSpec : ruleObjectiveInterpreterSpec EOF ;
    public final void entryRuleObjectiveInterpreterSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:129:1: ( ruleObjectiveInterpreterSpec EOF )
            // InternalMDEOptimise.g:130:1: ruleObjectiveInterpreterSpec EOF
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
    // InternalMDEOptimise.g:137:1: ruleObjectiveInterpreterSpec : ( ( rule__ObjectiveInterpreterSpec__Group__0 ) ) ;
    public final void ruleObjectiveInterpreterSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:141:2: ( ( ( rule__ObjectiveInterpreterSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:142:2: ( ( rule__ObjectiveInterpreterSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:142:2: ( ( rule__ObjectiveInterpreterSpec__Group__0 ) )
            // InternalMDEOptimise.g:143:3: ( rule__ObjectiveInterpreterSpec__Group__0 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:144:3: ( rule__ObjectiveInterpreterSpec__Group__0 )
            // InternalMDEOptimise.g:144:4: rule__ObjectiveInterpreterSpec__Group__0
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
    // InternalMDEOptimise.g:153:1: entryRuleConstraintInterpreterSpec : ruleConstraintInterpreterSpec EOF ;
    public final void entryRuleConstraintInterpreterSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:154:1: ( ruleConstraintInterpreterSpec EOF )
            // InternalMDEOptimise.g:155:1: ruleConstraintInterpreterSpec EOF
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
    // InternalMDEOptimise.g:162:1: ruleConstraintInterpreterSpec : ( ( rule__ConstraintInterpreterSpec__Group__0 ) ) ;
    public final void ruleConstraintInterpreterSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:166:2: ( ( ( rule__ConstraintInterpreterSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:167:2: ( ( rule__ConstraintInterpreterSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:167:2: ( ( rule__ConstraintInterpreterSpec__Group__0 ) )
            // InternalMDEOptimise.g:168:3: ( rule__ConstraintInterpreterSpec__Group__0 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:169:3: ( rule__ConstraintInterpreterSpec__Group__0 )
            // InternalMDEOptimise.g:169:4: rule__ConstraintInterpreterSpec__Group__0
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
    // InternalMDEOptimise.g:178:1: entryRuleEvolverSpec : ruleEvolverSpec EOF ;
    public final void entryRuleEvolverSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:179:1: ( ruleEvolverSpec EOF )
            // InternalMDEOptimise.g:180:1: ruleEvolverSpec EOF
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
    // InternalMDEOptimise.g:187:1: ruleEvolverSpec : ( ( rule__EvolverSpec__Group__0 ) ) ;
    public final void ruleEvolverSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:191:2: ( ( ( rule__EvolverSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:192:2: ( ( rule__EvolverSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:192:2: ( ( rule__EvolverSpec__Group__0 ) )
            // InternalMDEOptimise.g:193:3: ( rule__EvolverSpec__Group__0 )
            {
             before(grammarAccess.getEvolverSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:194:3: ( rule__EvolverSpec__Group__0 )
            // InternalMDEOptimise.g:194:4: rule__EvolverSpec__Group__0
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
    // InternalMDEOptimise.g:203:1: entryRuleOptimisationSpec : ruleOptimisationSpec EOF ;
    public final void entryRuleOptimisationSpec() throws RecognitionException {
        try {
            // InternalMDEOptimise.g:204:1: ( ruleOptimisationSpec EOF )
            // InternalMDEOptimise.g:205:1: ruleOptimisationSpec EOF
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
    // InternalMDEOptimise.g:212:1: ruleOptimisationSpec : ( ( rule__OptimisationSpec__Group__0 ) ) ;
    public final void ruleOptimisationSpec() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:216:2: ( ( ( rule__OptimisationSpec__Group__0 ) ) )
            // InternalMDEOptimise.g:217:2: ( ( rule__OptimisationSpec__Group__0 ) )
            {
            // InternalMDEOptimise.g:217:2: ( ( rule__OptimisationSpec__Group__0 ) )
            // InternalMDEOptimise.g:218:3: ( rule__OptimisationSpec__Group__0 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getGroup()); 
            // InternalMDEOptimise.g:219:3: ( rule__OptimisationSpec__Group__0 )
            // InternalMDEOptimise.g:219:4: rule__OptimisationSpec__Group__0
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
    // InternalMDEOptimise.g:227:1: rule__Optimisation__Group__0 : rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1 ;
    public final void rule__Optimisation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:231:1: ( rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1 )
            // InternalMDEOptimise.g:232:2: rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1
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
    // InternalMDEOptimise.g:239:1: rule__Optimisation__Group__0__Impl : ( ( rule__Optimisation__BasepathAssignment_0 ) ) ;
    public final void rule__Optimisation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:243:1: ( ( ( rule__Optimisation__BasepathAssignment_0 ) ) )
            // InternalMDEOptimise.g:244:1: ( ( rule__Optimisation__BasepathAssignment_0 ) )
            {
            // InternalMDEOptimise.g:244:1: ( ( rule__Optimisation__BasepathAssignment_0 ) )
            // InternalMDEOptimise.g:245:2: ( rule__Optimisation__BasepathAssignment_0 )
            {
             before(grammarAccess.getOptimisationAccess().getBasepathAssignment_0()); 
            // InternalMDEOptimise.g:246:2: ( rule__Optimisation__BasepathAssignment_0 )
            // InternalMDEOptimise.g:246:3: rule__Optimisation__BasepathAssignment_0
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
    // InternalMDEOptimise.g:254:1: rule__Optimisation__Group__1 : rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2 ;
    public final void rule__Optimisation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:258:1: ( rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2 )
            // InternalMDEOptimise.g:259:2: rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2
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
    // InternalMDEOptimise.g:266:1: rule__Optimisation__Group__1__Impl : ( ( rule__Optimisation__MetamodelAssignment_1 ) ) ;
    public final void rule__Optimisation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:270:1: ( ( ( rule__Optimisation__MetamodelAssignment_1 ) ) )
            // InternalMDEOptimise.g:271:1: ( ( rule__Optimisation__MetamodelAssignment_1 ) )
            {
            // InternalMDEOptimise.g:271:1: ( ( rule__Optimisation__MetamodelAssignment_1 ) )
            // InternalMDEOptimise.g:272:2: ( rule__Optimisation__MetamodelAssignment_1 )
            {
             before(grammarAccess.getOptimisationAccess().getMetamodelAssignment_1()); 
            // InternalMDEOptimise.g:273:2: ( rule__Optimisation__MetamodelAssignment_1 )
            // InternalMDEOptimise.g:273:3: rule__Optimisation__MetamodelAssignment_1
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
    // InternalMDEOptimise.g:281:1: rule__Optimisation__Group__2 : rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3 ;
    public final void rule__Optimisation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:285:1: ( rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3 )
            // InternalMDEOptimise.g:286:2: rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3
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
    // InternalMDEOptimise.g:293:1: rule__Optimisation__Group__2__Impl : ( ( ( rule__Optimisation__ObjectivesAssignment_2 ) ) ( ( rule__Optimisation__ObjectivesAssignment_2 )* ) ) ;
    public final void rule__Optimisation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:297:1: ( ( ( ( rule__Optimisation__ObjectivesAssignment_2 ) ) ( ( rule__Optimisation__ObjectivesAssignment_2 )* ) ) )
            // InternalMDEOptimise.g:298:1: ( ( ( rule__Optimisation__ObjectivesAssignment_2 ) ) ( ( rule__Optimisation__ObjectivesAssignment_2 )* ) )
            {
            // InternalMDEOptimise.g:298:1: ( ( ( rule__Optimisation__ObjectivesAssignment_2 ) ) ( ( rule__Optimisation__ObjectivesAssignment_2 )* ) )
            // InternalMDEOptimise.g:299:2: ( ( rule__Optimisation__ObjectivesAssignment_2 ) ) ( ( rule__Optimisation__ObjectivesAssignment_2 )* )
            {
            // InternalMDEOptimise.g:299:2: ( ( rule__Optimisation__ObjectivesAssignment_2 ) )
            // InternalMDEOptimise.g:300:3: ( rule__Optimisation__ObjectivesAssignment_2 )
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesAssignment_2()); 
            // InternalMDEOptimise.g:301:3: ( rule__Optimisation__ObjectivesAssignment_2 )
            // InternalMDEOptimise.g:301:4: rule__Optimisation__ObjectivesAssignment_2
            {
            pushFollow(FOLLOW_6);
            rule__Optimisation__ObjectivesAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getObjectivesAssignment_2()); 

            }

            // InternalMDEOptimise.g:304:2: ( ( rule__Optimisation__ObjectivesAssignment_2 )* )
            // InternalMDEOptimise.g:305:3: ( rule__Optimisation__ObjectivesAssignment_2 )*
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesAssignment_2()); 
            // InternalMDEOptimise.g:306:3: ( rule__Optimisation__ObjectivesAssignment_2 )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==19) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMDEOptimise.g:306:4: rule__Optimisation__ObjectivesAssignment_2
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Optimisation__ObjectivesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getOptimisationAccess().getObjectivesAssignment_2()); 

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
    // $ANTLR end "rule__Optimisation__Group__2__Impl"


    // $ANTLR start "rule__Optimisation__Group__3"
    // InternalMDEOptimise.g:315:1: rule__Optimisation__Group__3 : rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4 ;
    public final void rule__Optimisation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:319:1: ( rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4 )
            // InternalMDEOptimise.g:320:2: rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4
            {
            pushFollow(FOLLOW_5);
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
    // InternalMDEOptimise.g:327:1: rule__Optimisation__Group__3__Impl : ( ( rule__Optimisation__ConstraintsAssignment_3 )* ) ;
    public final void rule__Optimisation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:331:1: ( ( ( rule__Optimisation__ConstraintsAssignment_3 )* ) )
            // InternalMDEOptimise.g:332:1: ( ( rule__Optimisation__ConstraintsAssignment_3 )* )
            {
            // InternalMDEOptimise.g:332:1: ( ( rule__Optimisation__ConstraintsAssignment_3 )* )
            // InternalMDEOptimise.g:333:2: ( rule__Optimisation__ConstraintsAssignment_3 )*
            {
             before(grammarAccess.getOptimisationAccess().getConstraintsAssignment_3()); 
            // InternalMDEOptimise.g:334:2: ( rule__Optimisation__ConstraintsAssignment_3 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==22) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMDEOptimise.g:334:3: rule__Optimisation__ConstraintsAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Optimisation__ConstraintsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getOptimisationAccess().getConstraintsAssignment_3()); 

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
    // InternalMDEOptimise.g:342:1: rule__Optimisation__Group__4 : rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5 ;
    public final void rule__Optimisation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:346:1: ( rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5 )
            // InternalMDEOptimise.g:347:2: rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5
            {
            pushFollow(FOLLOW_8);
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
    // InternalMDEOptimise.g:354:1: rule__Optimisation__Group__4__Impl : ( ( ( rule__Optimisation__EvolversAssignment_4 ) ) ( ( rule__Optimisation__EvolversAssignment_4 )* ) ) ;
    public final void rule__Optimisation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:358:1: ( ( ( ( rule__Optimisation__EvolversAssignment_4 ) ) ( ( rule__Optimisation__EvolversAssignment_4 )* ) ) )
            // InternalMDEOptimise.g:359:1: ( ( ( rule__Optimisation__EvolversAssignment_4 ) ) ( ( rule__Optimisation__EvolversAssignment_4 )* ) )
            {
            // InternalMDEOptimise.g:359:1: ( ( ( rule__Optimisation__EvolversAssignment_4 ) ) ( ( rule__Optimisation__EvolversAssignment_4 )* ) )
            // InternalMDEOptimise.g:360:2: ( ( rule__Optimisation__EvolversAssignment_4 ) ) ( ( rule__Optimisation__EvolversAssignment_4 )* )
            {
            // InternalMDEOptimise.g:360:2: ( ( rule__Optimisation__EvolversAssignment_4 ) )
            // InternalMDEOptimise.g:361:3: ( rule__Optimisation__EvolversAssignment_4 )
            {
             before(grammarAccess.getOptimisationAccess().getEvolversAssignment_4()); 
            // InternalMDEOptimise.g:362:3: ( rule__Optimisation__EvolversAssignment_4 )
            // InternalMDEOptimise.g:362:4: rule__Optimisation__EvolversAssignment_4
            {
            pushFollow(FOLLOW_9);
            rule__Optimisation__EvolversAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getEvolversAssignment_4()); 

            }

            // InternalMDEOptimise.g:365:2: ( ( rule__Optimisation__EvolversAssignment_4 )* )
            // InternalMDEOptimise.g:366:3: ( rule__Optimisation__EvolversAssignment_4 )*
            {
             before(grammarAccess.getOptimisationAccess().getEvolversAssignment_4()); 
            // InternalMDEOptimise.g:367:3: ( rule__Optimisation__EvolversAssignment_4 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==23) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMDEOptimise.g:367:4: rule__Optimisation__EvolversAssignment_4
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__Optimisation__EvolversAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getOptimisationAccess().getEvolversAssignment_4()); 

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
    // $ANTLR end "rule__Optimisation__Group__4__Impl"


    // $ANTLR start "rule__Optimisation__Group__5"
    // InternalMDEOptimise.g:376:1: rule__Optimisation__Group__5 : rule__Optimisation__Group__5__Impl ;
    public final void rule__Optimisation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:380:1: ( rule__Optimisation__Group__5__Impl )
            // InternalMDEOptimise.g:381:2: rule__Optimisation__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__Group__5__Impl();

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
    // InternalMDEOptimise.g:387:1: rule__Optimisation__Group__5__Impl : ( ( rule__Optimisation__OptimisationAssignment_5 ) ) ;
    public final void rule__Optimisation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:391:1: ( ( ( rule__Optimisation__OptimisationAssignment_5 ) ) )
            // InternalMDEOptimise.g:392:1: ( ( rule__Optimisation__OptimisationAssignment_5 ) )
            {
            // InternalMDEOptimise.g:392:1: ( ( rule__Optimisation__OptimisationAssignment_5 ) )
            // InternalMDEOptimise.g:393:2: ( rule__Optimisation__OptimisationAssignment_5 )
            {
             before(grammarAccess.getOptimisationAccess().getOptimisationAssignment_5()); 
            // InternalMDEOptimise.g:394:2: ( rule__Optimisation__OptimisationAssignment_5 )
            // InternalMDEOptimise.g:394:3: rule__Optimisation__OptimisationAssignment_5
            {
            pushFollow(FOLLOW_2);
            rule__Optimisation__OptimisationAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getOptimisationAssignment_5()); 

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


    // $ANTLR start "rule__BasepathSpec__Group__0"
    // InternalMDEOptimise.g:403:1: rule__BasepathSpec__Group__0 : rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1 ;
    public final void rule__BasepathSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:407:1: ( rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1 )
            // InternalMDEOptimise.g:408:2: rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1
            {
            pushFollow(FOLLOW_10);
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
    // InternalMDEOptimise.g:415:1: rule__BasepathSpec__Group__0__Impl : ( 'basepath' ) ;
    public final void rule__BasepathSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:419:1: ( ( 'basepath' ) )
            // InternalMDEOptimise.g:420:1: ( 'basepath' )
            {
            // InternalMDEOptimise.g:420:1: ( 'basepath' )
            // InternalMDEOptimise.g:421:2: 'basepath'
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
    // InternalMDEOptimise.g:430:1: rule__BasepathSpec__Group__1 : rule__BasepathSpec__Group__1__Impl ;
    public final void rule__BasepathSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:434:1: ( rule__BasepathSpec__Group__1__Impl )
            // InternalMDEOptimise.g:435:2: rule__BasepathSpec__Group__1__Impl
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
    // InternalMDEOptimise.g:441:1: rule__BasepathSpec__Group__1__Impl : ( ( rule__BasepathSpec__LocationAssignment_1 ) ) ;
    public final void rule__BasepathSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:445:1: ( ( ( rule__BasepathSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:446:1: ( ( rule__BasepathSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:446:1: ( ( rule__BasepathSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:447:2: ( rule__BasepathSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getBasepathSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:448:2: ( rule__BasepathSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:448:3: rule__BasepathSpec__LocationAssignment_1
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
    // InternalMDEOptimise.g:457:1: rule__MetaModelSpec__Group__0 : rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1 ;
    public final void rule__MetaModelSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:461:1: ( rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1 )
            // InternalMDEOptimise.g:462:2: rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1
            {
            pushFollow(FOLLOW_10);
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
    // InternalMDEOptimise.g:469:1: rule__MetaModelSpec__Group__0__Impl : ( 'metamodel' ) ;
    public final void rule__MetaModelSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:473:1: ( ( 'metamodel' ) )
            // InternalMDEOptimise.g:474:1: ( 'metamodel' )
            {
            // InternalMDEOptimise.g:474:1: ( 'metamodel' )
            // InternalMDEOptimise.g:475:2: 'metamodel'
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
    // InternalMDEOptimise.g:484:1: rule__MetaModelSpec__Group__1 : rule__MetaModelSpec__Group__1__Impl ;
    public final void rule__MetaModelSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:488:1: ( rule__MetaModelSpec__Group__1__Impl )
            // InternalMDEOptimise.g:489:2: rule__MetaModelSpec__Group__1__Impl
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
    // InternalMDEOptimise.g:495:1: rule__MetaModelSpec__Group__1__Impl : ( ( rule__MetaModelSpec__LocationAssignment_1 ) ) ;
    public final void rule__MetaModelSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:499:1: ( ( ( rule__MetaModelSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:500:1: ( ( rule__MetaModelSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:500:1: ( ( rule__MetaModelSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:501:2: ( rule__MetaModelSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getMetaModelSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:502:2: ( rule__MetaModelSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:502:3: rule__MetaModelSpec__LocationAssignment_1
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


    // $ANTLR start "rule__ObjectiveInterpreterSpec__Group__0"
    // InternalMDEOptimise.g:511:1: rule__ObjectiveInterpreterSpec__Group__0 : rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1 ;
    public final void rule__ObjectiveInterpreterSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:515:1: ( rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1 )
            // InternalMDEOptimise.g:516:2: rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1
            {
            pushFollow(FOLLOW_11);
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
    // InternalMDEOptimise.g:523:1: rule__ObjectiveInterpreterSpec__Group__0__Impl : ( 'objective' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:527:1: ( ( 'objective' ) )
            // InternalMDEOptimise.g:528:1: ( 'objective' )
            {
            // InternalMDEOptimise.g:528:1: ( 'objective' )
            // InternalMDEOptimise.g:529:2: 'objective'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveKeyword_0()); 
            match(input,19,FOLLOW_2); 
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
    // InternalMDEOptimise.g:538:1: rule__ObjectiveInterpreterSpec__Group__1 : rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2 ;
    public final void rule__ObjectiveInterpreterSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:542:1: ( rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2 )
            // InternalMDEOptimise.g:543:2: rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2
            {
            pushFollow(FOLLOW_12);
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
    // InternalMDEOptimise.g:550:1: rule__ObjectiveInterpreterSpec__Group__1__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:554:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) ) )
            // InternalMDEOptimise.g:555:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) )
            {
            // InternalMDEOptimise.g:555:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) )
            // InternalMDEOptimise.g:556:2: ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameAssignment_1()); 
            // InternalMDEOptimise.g:557:2: ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 )
            // InternalMDEOptimise.g:557:3: rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1
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
    // InternalMDEOptimise.g:565:1: rule__ObjectiveInterpreterSpec__Group__2 : rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3 ;
    public final void rule__ObjectiveInterpreterSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:569:1: ( rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3 )
            // InternalMDEOptimise.g:570:2: rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3
            {
            pushFollow(FOLLOW_13);
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
    // InternalMDEOptimise.g:577:1: rule__ObjectiveInterpreterSpec__Group__2__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:581:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) ) )
            // InternalMDEOptimise.g:582:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) )
            {
            // InternalMDEOptimise.g:582:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) )
            // InternalMDEOptimise.g:583:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyAssignment_2()); 
            // InternalMDEOptimise.g:584:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 )
            // InternalMDEOptimise.g:584:3: rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2
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
    // InternalMDEOptimise.g:592:1: rule__ObjectiveInterpreterSpec__Group__3 : rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4 ;
    public final void rule__ObjectiveInterpreterSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:596:1: ( rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4 )
            // InternalMDEOptimise.g:597:2: rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4
            {
            pushFollow(FOLLOW_14);
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
    // InternalMDEOptimise.g:604:1: rule__ObjectiveInterpreterSpec__Group__3__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:608:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) ) )
            // InternalMDEOptimise.g:609:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) )
            {
            // InternalMDEOptimise.g:609:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) )
            // InternalMDEOptimise.g:610:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeAssignment_3()); 
            // InternalMDEOptimise.g:611:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 )
            // InternalMDEOptimise.g:611:3: rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3
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
    // InternalMDEOptimise.g:619:1: rule__ObjectiveInterpreterSpec__Group__4 : rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5 ;
    public final void rule__ObjectiveInterpreterSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:623:1: ( rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5 )
            // InternalMDEOptimise.g:624:2: rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5
            {
            pushFollow(FOLLOW_15);
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
    // InternalMDEOptimise.g:631:1: rule__ObjectiveInterpreterSpec__Group__4__Impl : ( '{' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:635:1: ( ( '{' ) )
            // InternalMDEOptimise.g:636:1: ( '{' )
            {
            // InternalMDEOptimise.g:636:1: ( '{' )
            // InternalMDEOptimise.g:637:2: '{'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,20,FOLLOW_2); 
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
    // InternalMDEOptimise.g:646:1: rule__ObjectiveInterpreterSpec__Group__5 : rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6 ;
    public final void rule__ObjectiveInterpreterSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:650:1: ( rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6 )
            // InternalMDEOptimise.g:651:2: rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6
            {
            pushFollow(FOLLOW_16);
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
    // InternalMDEOptimise.g:658:1: rule__ObjectiveInterpreterSpec__Group__5__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:662:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) ) )
            // InternalMDEOptimise.g:663:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) )
            {
            // InternalMDEOptimise.g:663:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) )
            // InternalMDEOptimise.g:664:2: ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecAssignment_5()); 
            // InternalMDEOptimise.g:665:2: ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 )
            // InternalMDEOptimise.g:665:3: rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5
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
    // InternalMDEOptimise.g:673:1: rule__ObjectiveInterpreterSpec__Group__6 : rule__ObjectiveInterpreterSpec__Group__6__Impl ;
    public final void rule__ObjectiveInterpreterSpec__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:677:1: ( rule__ObjectiveInterpreterSpec__Group__6__Impl )
            // InternalMDEOptimise.g:678:2: rule__ObjectiveInterpreterSpec__Group__6__Impl
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
    // InternalMDEOptimise.g:684:1: rule__ObjectiveInterpreterSpec__Group__6__Impl : ( '}' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:688:1: ( ( '}' ) )
            // InternalMDEOptimise.g:689:1: ( '}' )
            {
            // InternalMDEOptimise.g:689:1: ( '}' )
            // InternalMDEOptimise.g:690:2: '}'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getRightCurlyBracketKeyword_6()); 
            match(input,21,FOLLOW_2); 
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
    // InternalMDEOptimise.g:700:1: rule__ConstraintInterpreterSpec__Group__0 : rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1 ;
    public final void rule__ConstraintInterpreterSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:704:1: ( rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1 )
            // InternalMDEOptimise.g:705:2: rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1
            {
            pushFollow(FOLLOW_11);
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
    // InternalMDEOptimise.g:712:1: rule__ConstraintInterpreterSpec__Group__0__Impl : ( 'constraint' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:716:1: ( ( 'constraint' ) )
            // InternalMDEOptimise.g:717:1: ( 'constraint' )
            {
            // InternalMDEOptimise.g:717:1: ( 'constraint' )
            // InternalMDEOptimise.g:718:2: 'constraint'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintKeyword_0()); 
            match(input,22,FOLLOW_2); 
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
    // InternalMDEOptimise.g:727:1: rule__ConstraintInterpreterSpec__Group__1 : rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2 ;
    public final void rule__ConstraintInterpreterSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:731:1: ( rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2 )
            // InternalMDEOptimise.g:732:2: rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2
            {
            pushFollow(FOLLOW_13);
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
    // InternalMDEOptimise.g:739:1: rule__ConstraintInterpreterSpec__Group__1__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:743:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) ) )
            // InternalMDEOptimise.g:744:1: ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) )
            {
            // InternalMDEOptimise.g:744:1: ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) )
            // InternalMDEOptimise.g:745:2: ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameAssignment_1()); 
            // InternalMDEOptimise.g:746:2: ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 )
            // InternalMDEOptimise.g:746:3: rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1
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
    // InternalMDEOptimise.g:754:1: rule__ConstraintInterpreterSpec__Group__2 : rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3 ;
    public final void rule__ConstraintInterpreterSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:758:1: ( rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3 )
            // InternalMDEOptimise.g:759:2: rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3
            {
            pushFollow(FOLLOW_14);
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
    // InternalMDEOptimise.g:766:1: rule__ConstraintInterpreterSpec__Group__2__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:770:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) ) )
            // InternalMDEOptimise.g:771:1: ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) )
            {
            // InternalMDEOptimise.g:771:1: ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) )
            // InternalMDEOptimise.g:772:2: ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeAssignment_2()); 
            // InternalMDEOptimise.g:773:2: ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 )
            // InternalMDEOptimise.g:773:3: rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2
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
    // InternalMDEOptimise.g:781:1: rule__ConstraintInterpreterSpec__Group__3 : rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4 ;
    public final void rule__ConstraintInterpreterSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:785:1: ( rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4 )
            // InternalMDEOptimise.g:786:2: rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4
            {
            pushFollow(FOLLOW_15);
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
    // InternalMDEOptimise.g:793:1: rule__ConstraintInterpreterSpec__Group__3__Impl : ( '{' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:797:1: ( ( '{' ) )
            // InternalMDEOptimise.g:798:1: ( '{' )
            {
            // InternalMDEOptimise.g:798:1: ( '{' )
            // InternalMDEOptimise.g:799:2: '{'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,20,FOLLOW_2); 
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
    // InternalMDEOptimise.g:808:1: rule__ConstraintInterpreterSpec__Group__4 : rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5 ;
    public final void rule__ConstraintInterpreterSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:812:1: ( rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5 )
            // InternalMDEOptimise.g:813:2: rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5
            {
            pushFollow(FOLLOW_16);
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
    // InternalMDEOptimise.g:820:1: rule__ConstraintInterpreterSpec__Group__4__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:824:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) ) )
            // InternalMDEOptimise.g:825:1: ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) )
            {
            // InternalMDEOptimise.g:825:1: ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) )
            // InternalMDEOptimise.g:826:2: ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecAssignment_4()); 
            // InternalMDEOptimise.g:827:2: ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 )
            // InternalMDEOptimise.g:827:3: rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4
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
    // InternalMDEOptimise.g:835:1: rule__ConstraintInterpreterSpec__Group__5 : rule__ConstraintInterpreterSpec__Group__5__Impl ;
    public final void rule__ConstraintInterpreterSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:839:1: ( rule__ConstraintInterpreterSpec__Group__5__Impl )
            // InternalMDEOptimise.g:840:2: rule__ConstraintInterpreterSpec__Group__5__Impl
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
    // InternalMDEOptimise.g:846:1: rule__ConstraintInterpreterSpec__Group__5__Impl : ( '}' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:850:1: ( ( '}' ) )
            // InternalMDEOptimise.g:851:1: ( '}' )
            {
            // InternalMDEOptimise.g:851:1: ( '}' )
            // InternalMDEOptimise.g:852:2: '}'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getRightCurlyBracketKeyword_5()); 
            match(input,21,FOLLOW_2); 
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
    // InternalMDEOptimise.g:862:1: rule__EvolverSpec__Group__0 : rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1 ;
    public final void rule__EvolverSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:866:1: ( rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1 )
            // InternalMDEOptimise.g:867:2: rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1
            {
            pushFollow(FOLLOW_17);
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
    // InternalMDEOptimise.g:874:1: rule__EvolverSpec__Group__0__Impl : ( 'evolve' ) ;
    public final void rule__EvolverSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:878:1: ( ( 'evolve' ) )
            // InternalMDEOptimise.g:879:1: ( 'evolve' )
            {
            // InternalMDEOptimise.g:879:1: ( 'evolve' )
            // InternalMDEOptimise.g:880:2: 'evolve'
            {
             before(grammarAccess.getEvolverSpecAccess().getEvolveKeyword_0()); 
            match(input,23,FOLLOW_2); 
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
    // InternalMDEOptimise.g:889:1: rule__EvolverSpec__Group__1 : rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2 ;
    public final void rule__EvolverSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:893:1: ( rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2 )
            // InternalMDEOptimise.g:894:2: rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2
            {
            pushFollow(FOLLOW_10);
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
    // InternalMDEOptimise.g:901:1: rule__EvolverSpec__Group__1__Impl : ( 'using' ) ;
    public final void rule__EvolverSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:905:1: ( ( 'using' ) )
            // InternalMDEOptimise.g:906:1: ( 'using' )
            {
            // InternalMDEOptimise.g:906:1: ( 'using' )
            // InternalMDEOptimise.g:907:2: 'using'
            {
             before(grammarAccess.getEvolverSpecAccess().getUsingKeyword_1()); 
            match(input,24,FOLLOW_2); 
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
    // InternalMDEOptimise.g:916:1: rule__EvolverSpec__Group__2 : rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3 ;
    public final void rule__EvolverSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:920:1: ( rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3 )
            // InternalMDEOptimise.g:921:2: rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3
            {
            pushFollow(FOLLOW_18);
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
    // InternalMDEOptimise.g:928:1: rule__EvolverSpec__Group__2__Impl : ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) ) ;
    public final void rule__EvolverSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:932:1: ( ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) ) )
            // InternalMDEOptimise.g:933:1: ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) )
            {
            // InternalMDEOptimise.g:933:1: ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) )
            // InternalMDEOptimise.g:934:2: ( rule__EvolverSpec__Rule_locationAssignment_2 )
            {
             before(grammarAccess.getEvolverSpecAccess().getRule_locationAssignment_2()); 
            // InternalMDEOptimise.g:935:2: ( rule__EvolverSpec__Rule_locationAssignment_2 )
            // InternalMDEOptimise.g:935:3: rule__EvolverSpec__Rule_locationAssignment_2
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
    // InternalMDEOptimise.g:943:1: rule__EvolverSpec__Group__3 : rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4 ;
    public final void rule__EvolverSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:947:1: ( rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4 )
            // InternalMDEOptimise.g:948:2: rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4
            {
            pushFollow(FOLLOW_15);
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
    // InternalMDEOptimise.g:955:1: rule__EvolverSpec__Group__3__Impl : ( 'unit' ) ;
    public final void rule__EvolverSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:959:1: ( ( 'unit' ) )
            // InternalMDEOptimise.g:960:1: ( 'unit' )
            {
            // InternalMDEOptimise.g:960:1: ( 'unit' )
            // InternalMDEOptimise.g:961:2: 'unit'
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitKeyword_3()); 
            match(input,25,FOLLOW_2); 
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
    // InternalMDEOptimise.g:970:1: rule__EvolverSpec__Group__4 : rule__EvolverSpec__Group__4__Impl ;
    public final void rule__EvolverSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:974:1: ( rule__EvolverSpec__Group__4__Impl )
            // InternalMDEOptimise.g:975:2: rule__EvolverSpec__Group__4__Impl
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
    // InternalMDEOptimise.g:981:1: rule__EvolverSpec__Group__4__Impl : ( ( rule__EvolverSpec__UnitAssignment_4 ) ) ;
    public final void rule__EvolverSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:985:1: ( ( ( rule__EvolverSpec__UnitAssignment_4 ) ) )
            // InternalMDEOptimise.g:986:1: ( ( rule__EvolverSpec__UnitAssignment_4 ) )
            {
            // InternalMDEOptimise.g:986:1: ( ( rule__EvolverSpec__UnitAssignment_4 ) )
            // InternalMDEOptimise.g:987:2: ( rule__EvolverSpec__UnitAssignment_4 )
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitAssignment_4()); 
            // InternalMDEOptimise.g:988:2: ( rule__EvolverSpec__UnitAssignment_4 )
            // InternalMDEOptimise.g:988:3: rule__EvolverSpec__UnitAssignment_4
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
    // InternalMDEOptimise.g:997:1: rule__OptimisationSpec__Group__0 : rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1 ;
    public final void rule__OptimisationSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1001:1: ( rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1 )
            // InternalMDEOptimise.g:1002:2: rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1
            {
            pushFollow(FOLLOW_19);
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
    // InternalMDEOptimise.g:1009:1: rule__OptimisationSpec__Group__0__Impl : ( 'optimisation' ) ;
    public final void rule__OptimisationSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1013:1: ( ( 'optimisation' ) )
            // InternalMDEOptimise.g:1014:1: ( 'optimisation' )
            {
            // InternalMDEOptimise.g:1014:1: ( 'optimisation' )
            // InternalMDEOptimise.g:1015:2: 'optimisation'
            {
             before(grammarAccess.getOptimisationSpecAccess().getOptimisationKeyword_0()); 
            match(input,26,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1024:1: rule__OptimisationSpec__Group__1 : rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2 ;
    public final void rule__OptimisationSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1028:1: ( rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2 )
            // InternalMDEOptimise.g:1029:2: rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2
            {
            pushFollow(FOLLOW_20);
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
    // InternalMDEOptimise.g:1036:1: rule__OptimisationSpec__Group__1__Impl : ( 'provider' ) ;
    public final void rule__OptimisationSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1040:1: ( ( 'provider' ) )
            // InternalMDEOptimise.g:1041:1: ( 'provider' )
            {
            // InternalMDEOptimise.g:1041:1: ( 'provider' )
            // InternalMDEOptimise.g:1042:2: 'provider'
            {
             before(grammarAccess.getOptimisationSpecAccess().getProviderKeyword_1()); 
            match(input,27,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1051:1: rule__OptimisationSpec__Group__2 : rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3 ;
    public final void rule__OptimisationSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1055:1: ( rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3 )
            // InternalMDEOptimise.g:1056:2: rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3
            {
            pushFollow(FOLLOW_21);
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
    // InternalMDEOptimise.g:1063:1: rule__OptimisationSpec__Group__2__Impl : ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) ) ;
    public final void rule__OptimisationSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1067:1: ( ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) ) )
            // InternalMDEOptimise.g:1068:1: ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) )
            {
            // InternalMDEOptimise.g:1068:1: ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) )
            // InternalMDEOptimise.g:1069:2: ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryAssignment_2()); 
            // InternalMDEOptimise.g:1070:2: ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 )
            // InternalMDEOptimise.g:1070:3: rule__OptimisationSpec__AlgorithmFactoryAssignment_2
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
    // InternalMDEOptimise.g:1078:1: rule__OptimisationSpec__Group__3 : rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4 ;
    public final void rule__OptimisationSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1082:1: ( rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4 )
            // InternalMDEOptimise.g:1083:2: rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4
            {
            pushFollow(FOLLOW_22);
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
    // InternalMDEOptimise.g:1090:1: rule__OptimisationSpec__Group__3__Impl : ( 'algorithm' ) ;
    public final void rule__OptimisationSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1094:1: ( ( 'algorithm' ) )
            // InternalMDEOptimise.g:1095:1: ( 'algorithm' )
            {
            // InternalMDEOptimise.g:1095:1: ( 'algorithm' )
            // InternalMDEOptimise.g:1096:2: 'algorithm'
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmKeyword_3()); 
            match(input,28,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1105:1: rule__OptimisationSpec__Group__4 : rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5 ;
    public final void rule__OptimisationSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1109:1: ( rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5 )
            // InternalMDEOptimise.g:1110:2: rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5
            {
            pushFollow(FOLLOW_23);
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
    // InternalMDEOptimise.g:1117:1: rule__OptimisationSpec__Group__4__Impl : ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) ) ;
    public final void rule__OptimisationSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1121:1: ( ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) ) )
            // InternalMDEOptimise.g:1122:1: ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) )
            {
            // InternalMDEOptimise.g:1122:1: ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) )
            // InternalMDEOptimise.g:1123:2: ( rule__OptimisationSpec__AlgorithmNameAssignment_4 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmNameAssignment_4()); 
            // InternalMDEOptimise.g:1124:2: ( rule__OptimisationSpec__AlgorithmNameAssignment_4 )
            // InternalMDEOptimise.g:1124:3: rule__OptimisationSpec__AlgorithmNameAssignment_4
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
    // InternalMDEOptimise.g:1132:1: rule__OptimisationSpec__Group__5 : rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6 ;
    public final void rule__OptimisationSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1136:1: ( rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6 )
            // InternalMDEOptimise.g:1137:2: rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6
            {
            pushFollow(FOLLOW_24);
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
    // InternalMDEOptimise.g:1144:1: rule__OptimisationSpec__Group__5__Impl : ( 'evolutions' ) ;
    public final void rule__OptimisationSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1148:1: ( ( 'evolutions' ) )
            // InternalMDEOptimise.g:1149:1: ( 'evolutions' )
            {
            // InternalMDEOptimise.g:1149:1: ( 'evolutions' )
            // InternalMDEOptimise.g:1150:2: 'evolutions'
            {
             before(grammarAccess.getOptimisationSpecAccess().getEvolutionsKeyword_5()); 
            match(input,29,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1159:1: rule__OptimisationSpec__Group__6 : rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7 ;
    public final void rule__OptimisationSpec__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1163:1: ( rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7 )
            // InternalMDEOptimise.g:1164:2: rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7
            {
            pushFollow(FOLLOW_25);
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
    // InternalMDEOptimise.g:1171:1: rule__OptimisationSpec__Group__6__Impl : ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) ) ;
    public final void rule__OptimisationSpec__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1175:1: ( ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) ) )
            // InternalMDEOptimise.g:1176:1: ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) )
            {
            // InternalMDEOptimise.g:1176:1: ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 ) )
            // InternalMDEOptimise.g:1177:2: ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsAssignment_6()); 
            // InternalMDEOptimise.g:1178:2: ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 )
            // InternalMDEOptimise.g:1178:3: rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6
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
    // InternalMDEOptimise.g:1186:1: rule__OptimisationSpec__Group__7 : rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8 ;
    public final void rule__OptimisationSpec__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1190:1: ( rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8 )
            // InternalMDEOptimise.g:1191:2: rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8
            {
            pushFollow(FOLLOW_24);
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
    // InternalMDEOptimise.g:1198:1: rule__OptimisationSpec__Group__7__Impl : ( 'population' ) ;
    public final void rule__OptimisationSpec__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1202:1: ( ( 'population' ) )
            // InternalMDEOptimise.g:1203:1: ( 'population' )
            {
            // InternalMDEOptimise.g:1203:1: ( 'population' )
            // InternalMDEOptimise.g:1204:2: 'population'
            {
             before(grammarAccess.getOptimisationSpecAccess().getPopulationKeyword_7()); 
            match(input,30,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1213:1: rule__OptimisationSpec__Group__8 : rule__OptimisationSpec__Group__8__Impl ;
    public final void rule__OptimisationSpec__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1217:1: ( rule__OptimisationSpec__Group__8__Impl )
            // InternalMDEOptimise.g:1218:2: rule__OptimisationSpec__Group__8__Impl
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
    // InternalMDEOptimise.g:1224:1: rule__OptimisationSpec__Group__8__Impl : ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) ) ;
    public final void rule__OptimisationSpec__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1228:1: ( ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) ) )
            // InternalMDEOptimise.g:1229:1: ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) )
            {
            // InternalMDEOptimise.g:1229:1: ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 ) )
            // InternalMDEOptimise.g:1230:2: ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationAssignment_8()); 
            // InternalMDEOptimise.g:1231:2: ( rule__OptimisationSpec__AlgorithmPopulationAssignment_8 )
            // InternalMDEOptimise.g:1231:3: rule__OptimisationSpec__AlgorithmPopulationAssignment_8
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
    // InternalMDEOptimise.g:1240:1: rule__Optimisation__BasepathAssignment_0 : ( ruleBasepathSpec ) ;
    public final void rule__Optimisation__BasepathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1244:1: ( ( ruleBasepathSpec ) )
            // InternalMDEOptimise.g:1245:2: ( ruleBasepathSpec )
            {
            // InternalMDEOptimise.g:1245:2: ( ruleBasepathSpec )
            // InternalMDEOptimise.g:1246:3: ruleBasepathSpec
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
    // InternalMDEOptimise.g:1255:1: rule__Optimisation__MetamodelAssignment_1 : ( ruleMetaModelSpec ) ;
    public final void rule__Optimisation__MetamodelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1259:1: ( ( ruleMetaModelSpec ) )
            // InternalMDEOptimise.g:1260:2: ( ruleMetaModelSpec )
            {
            // InternalMDEOptimise.g:1260:2: ( ruleMetaModelSpec )
            // InternalMDEOptimise.g:1261:3: ruleMetaModelSpec
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


    // $ANTLR start "rule__Optimisation__ObjectivesAssignment_2"
    // InternalMDEOptimise.g:1270:1: rule__Optimisation__ObjectivesAssignment_2 : ( ruleObjectiveInterpreterSpec ) ;
    public final void rule__Optimisation__ObjectivesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1274:1: ( ( ruleObjectiveInterpreterSpec ) )
            // InternalMDEOptimise.g:1275:2: ( ruleObjectiveInterpreterSpec )
            {
            // InternalMDEOptimise.g:1275:2: ( ruleObjectiveInterpreterSpec )
            // InternalMDEOptimise.g:1276:3: ruleObjectiveInterpreterSpec
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesObjectiveInterpreterSpecParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleObjectiveInterpreterSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getObjectivesObjectiveInterpreterSpecParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Optimisation__ObjectivesAssignment_2"


    // $ANTLR start "rule__Optimisation__ConstraintsAssignment_3"
    // InternalMDEOptimise.g:1285:1: rule__Optimisation__ConstraintsAssignment_3 : ( ruleConstraintInterpreterSpec ) ;
    public final void rule__Optimisation__ConstraintsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1289:1: ( ( ruleConstraintInterpreterSpec ) )
            // InternalMDEOptimise.g:1290:2: ( ruleConstraintInterpreterSpec )
            {
            // InternalMDEOptimise.g:1290:2: ( ruleConstraintInterpreterSpec )
            // InternalMDEOptimise.g:1291:3: ruleConstraintInterpreterSpec
            {
             before(grammarAccess.getOptimisationAccess().getConstraintsConstraintInterpreterSpecParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleConstraintInterpreterSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getConstraintsConstraintInterpreterSpecParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Optimisation__ConstraintsAssignment_3"


    // $ANTLR start "rule__Optimisation__EvolversAssignment_4"
    // InternalMDEOptimise.g:1300:1: rule__Optimisation__EvolversAssignment_4 : ( ruleEvolverSpec ) ;
    public final void rule__Optimisation__EvolversAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1304:1: ( ( ruleEvolverSpec ) )
            // InternalMDEOptimise.g:1305:2: ( ruleEvolverSpec )
            {
            // InternalMDEOptimise.g:1305:2: ( ruleEvolverSpec )
            // InternalMDEOptimise.g:1306:3: ruleEvolverSpec
            {
             before(grammarAccess.getOptimisationAccess().getEvolversEvolverSpecParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleEvolverSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getEvolversEvolverSpecParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Optimisation__EvolversAssignment_4"


    // $ANTLR start "rule__Optimisation__OptimisationAssignment_5"
    // InternalMDEOptimise.g:1315:1: rule__Optimisation__OptimisationAssignment_5 : ( ruleOptimisationSpec ) ;
    public final void rule__Optimisation__OptimisationAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1319:1: ( ( ruleOptimisationSpec ) )
            // InternalMDEOptimise.g:1320:2: ( ruleOptimisationSpec )
            {
            // InternalMDEOptimise.g:1320:2: ( ruleOptimisationSpec )
            // InternalMDEOptimise.g:1321:3: ruleOptimisationSpec
            {
             before(grammarAccess.getOptimisationAccess().getOptimisationOptimisationSpecParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleOptimisationSpec();

            state._fsp--;

             after(grammarAccess.getOptimisationAccess().getOptimisationOptimisationSpecParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__Optimisation__OptimisationAssignment_5"


    // $ANTLR start "rule__BasepathSpec__LocationAssignment_1"
    // InternalMDEOptimise.g:1330:1: rule__BasepathSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__BasepathSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1334:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1335:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1335:2: ( RULE_URL )
            // InternalMDEOptimise.g:1336:3: RULE_URL
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
    // InternalMDEOptimise.g:1345:1: rule__MetaModelSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__MetaModelSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1349:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1350:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1350:2: ( RULE_URL )
            // InternalMDEOptimise.g:1351:3: RULE_URL
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


    // $ANTLR start "rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1"
    // InternalMDEOptimise.g:1360:1: rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 : ( RULE_ENTITY_NAME ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1364:1: ( ( RULE_ENTITY_NAME ) )
            // InternalMDEOptimise.g:1365:2: ( RULE_ENTITY_NAME )
            {
            // InternalMDEOptimise.g:1365:2: ( RULE_ENTITY_NAME )
            // InternalMDEOptimise.g:1366:3: RULE_ENTITY_NAME
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
    // InternalMDEOptimise.g:1375:1: rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 : ( RULE_OBJECTIVE_TENDENCY ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1379:1: ( ( RULE_OBJECTIVE_TENDENCY ) )
            // InternalMDEOptimise.g:1380:2: ( RULE_OBJECTIVE_TENDENCY )
            {
            // InternalMDEOptimise.g:1380:2: ( RULE_OBJECTIVE_TENDENCY )
            // InternalMDEOptimise.g:1381:3: RULE_OBJECTIVE_TENDENCY
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
    // InternalMDEOptimise.g:1390:1: rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 : ( RULE_ENTITY_TYPE ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1394:1: ( ( RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:1395:2: ( RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:1395:2: ( RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:1396:3: RULE_ENTITY_TYPE
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
    // InternalMDEOptimise.g:1405:1: rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 : ( RULE_STRING ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1409:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1410:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1410:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1411:3: RULE_STRING
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
    // InternalMDEOptimise.g:1420:1: rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 : ( RULE_ENTITY_NAME ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1424:1: ( ( RULE_ENTITY_NAME ) )
            // InternalMDEOptimise.g:1425:2: ( RULE_ENTITY_NAME )
            {
            // InternalMDEOptimise.g:1425:2: ( RULE_ENTITY_NAME )
            // InternalMDEOptimise.g:1426:3: RULE_ENTITY_NAME
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
    // InternalMDEOptimise.g:1435:1: rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 : ( RULE_ENTITY_TYPE ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1439:1: ( ( RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:1440:2: ( RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:1440:2: ( RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:1441:3: RULE_ENTITY_TYPE
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
    // InternalMDEOptimise.g:1450:1: rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 : ( RULE_STRING ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1454:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1455:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1455:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1456:3: RULE_STRING
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
    // InternalMDEOptimise.g:1465:1: rule__EvolverSpec__Rule_locationAssignment_2 : ( RULE_URL ) ;
    public final void rule__EvolverSpec__Rule_locationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1469:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1470:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1470:2: ( RULE_URL )
            // InternalMDEOptimise.g:1471:3: RULE_URL
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
    // InternalMDEOptimise.g:1480:1: rule__EvolverSpec__UnitAssignment_4 : ( RULE_STRING ) ;
    public final void rule__EvolverSpec__UnitAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1484:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1485:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1485:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1486:3: RULE_STRING
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
    // InternalMDEOptimise.g:1495:1: rule__OptimisationSpec__AlgorithmFactoryAssignment_2 : ( RULE_ALGORITHM_FACTORY ) ;
    public final void rule__OptimisationSpec__AlgorithmFactoryAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1499:1: ( ( RULE_ALGORITHM_FACTORY ) )
            // InternalMDEOptimise.g:1500:2: ( RULE_ALGORITHM_FACTORY )
            {
            // InternalMDEOptimise.g:1500:2: ( RULE_ALGORITHM_FACTORY )
            // InternalMDEOptimise.g:1501:3: RULE_ALGORITHM_FACTORY
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
    // InternalMDEOptimise.g:1510:1: rule__OptimisationSpec__AlgorithmNameAssignment_4 : ( RULE_ALGORITHM_NAME ) ;
    public final void rule__OptimisationSpec__AlgorithmNameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1514:1: ( ( RULE_ALGORITHM_NAME ) )
            // InternalMDEOptimise.g:1515:2: ( RULE_ALGORITHM_NAME )
            {
            // InternalMDEOptimise.g:1515:2: ( RULE_ALGORITHM_NAME )
            // InternalMDEOptimise.g:1516:3: RULE_ALGORITHM_NAME
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
    // InternalMDEOptimise.g:1525:1: rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6 : ( RULE_INT ) ;
    public final void rule__OptimisationSpec__AlgorithmEvolutionsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1529:1: ( ( RULE_INT ) )
            // InternalMDEOptimise.g:1530:2: ( RULE_INT )
            {
            // InternalMDEOptimise.g:1530:2: ( RULE_INT )
            // InternalMDEOptimise.g:1531:3: RULE_INT
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
    // InternalMDEOptimise.g:1540:1: rule__OptimisationSpec__AlgorithmPopulationAssignment_8 : ( RULE_INT ) ;
    public final void rule__OptimisationSpec__AlgorithmPopulationAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1544:1: ( ( RULE_INT ) )
            // InternalMDEOptimise.g:1545:2: ( RULE_INT )
            {
            // InternalMDEOptimise.g:1545:2: ( RULE_INT )
            // InternalMDEOptimise.g:1546:3: RULE_INT
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
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000C00000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000C00002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000040000000L});

}