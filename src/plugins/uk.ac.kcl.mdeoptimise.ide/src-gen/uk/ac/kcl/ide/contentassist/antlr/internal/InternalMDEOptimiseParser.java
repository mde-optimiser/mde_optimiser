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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_URL", "RULE_ENTITY_NAME", "RULE_OBJECTIVE_TENDENCY", "RULE_ENTITY_TYPE", "RULE_STRING", "RULE_ALGORITHM_FACTORY", "RULE_ALGORITHM_NAME", "RULE_ALGORITHM_VARIATION", "RULE_INT", "RULE_ID", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'mutate'", "'breed'", "'basepath'", "'metamodel'", "'model'", "'objective'", "'{'", "'}'", "'constraint'", "'using'", "'unit'", "'optimisation'", "'provider'", "'algorithm'", "'variation'", "'evolutions'", "'population'"
    };
    public static final int RULE_ENTITY_NAME=5;
    public static final int RULE_OBJECTIVE_TENDENCY=6;
    public static final int RULE_STRING=8;
    public static final int RULE_SL_COMMENT=15;
    public static final int T__19=19;
    public static final int RULE_ALGORITHM_FACTORY=9;
    public static final int T__18=18;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ALGORITHM_VARIATION=11;
    public static final int RULE_ID=13;
    public static final int RULE_WS=16;
    public static final int RULE_ANY_OTHER=17;
    public static final int RULE_ALGORITHM_NAME=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=12;
    public static final int T__29=29;
    public static final int RULE_URL=4;
    public static final int RULE_ENTITY_TYPE=7;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=14;
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


    // $ANTLR start "ruleEvolverType"
    // InternalMDEOptimise.g:253:1: ruleEvolverType : ( ( rule__EvolverType__Alternatives ) ) ;
    public final void ruleEvolverType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:257:1: ( ( ( rule__EvolverType__Alternatives ) ) )
            // InternalMDEOptimise.g:258:2: ( ( rule__EvolverType__Alternatives ) )
            {
            // InternalMDEOptimise.g:258:2: ( ( rule__EvolverType__Alternatives ) )
            // InternalMDEOptimise.g:259:3: ( rule__EvolverType__Alternatives )
            {
             before(grammarAccess.getEvolverTypeAccess().getAlternatives()); 
            // InternalMDEOptimise.g:260:3: ( rule__EvolverType__Alternatives )
            // InternalMDEOptimise.g:260:4: rule__EvolverType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__EvolverType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getEvolverTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleEvolverType"


    // $ANTLR start "rule__EvolverType__Alternatives"
    // InternalMDEOptimise.g:268:1: rule__EvolverType__Alternatives : ( ( ( 'mutate' ) ) | ( ( 'breed' ) ) );
    public final void rule__EvolverType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:272:1: ( ( ( 'mutate' ) ) | ( ( 'breed' ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==18) ) {
                alt1=1;
            }
            else if ( (LA1_0==19) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalMDEOptimise.g:273:2: ( ( 'mutate' ) )
                    {
                    // InternalMDEOptimise.g:273:2: ( ( 'mutate' ) )
                    // InternalMDEOptimise.g:274:3: ( 'mutate' )
                    {
                     before(grammarAccess.getEvolverTypeAccess().getMUTATEEnumLiteralDeclaration_0()); 
                    // InternalMDEOptimise.g:275:3: ( 'mutate' )
                    // InternalMDEOptimise.g:275:4: 'mutate'
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getEvolverTypeAccess().getMUTATEEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:279:2: ( ( 'breed' ) )
                    {
                    // InternalMDEOptimise.g:279:2: ( ( 'breed' ) )
                    // InternalMDEOptimise.g:280:3: ( 'breed' )
                    {
                     before(grammarAccess.getEvolverTypeAccess().getBREEDEnumLiteralDeclaration_1()); 
                    // InternalMDEOptimise.g:281:3: ( 'breed' )
                    // InternalMDEOptimise.g:281:4: 'breed'
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getEvolverTypeAccess().getBREEDEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;

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
    // $ANTLR end "rule__EvolverType__Alternatives"


    // $ANTLR start "rule__Optimisation__Group__0"
    // InternalMDEOptimise.g:289:1: rule__Optimisation__Group__0 : rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1 ;
    public final void rule__Optimisation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:293:1: ( rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1 )
            // InternalMDEOptimise.g:294:2: rule__Optimisation__Group__0__Impl rule__Optimisation__Group__1
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
    // InternalMDEOptimise.g:301:1: rule__Optimisation__Group__0__Impl : ( ( rule__Optimisation__BasepathAssignment_0 ) ) ;
    public final void rule__Optimisation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:305:1: ( ( ( rule__Optimisation__BasepathAssignment_0 ) ) )
            // InternalMDEOptimise.g:306:1: ( ( rule__Optimisation__BasepathAssignment_0 ) )
            {
            // InternalMDEOptimise.g:306:1: ( ( rule__Optimisation__BasepathAssignment_0 ) )
            // InternalMDEOptimise.g:307:2: ( rule__Optimisation__BasepathAssignment_0 )
            {
             before(grammarAccess.getOptimisationAccess().getBasepathAssignment_0()); 
            // InternalMDEOptimise.g:308:2: ( rule__Optimisation__BasepathAssignment_0 )
            // InternalMDEOptimise.g:308:3: rule__Optimisation__BasepathAssignment_0
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
    // InternalMDEOptimise.g:316:1: rule__Optimisation__Group__1 : rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2 ;
    public final void rule__Optimisation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:320:1: ( rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2 )
            // InternalMDEOptimise.g:321:2: rule__Optimisation__Group__1__Impl rule__Optimisation__Group__2
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
    // InternalMDEOptimise.g:328:1: rule__Optimisation__Group__1__Impl : ( ( rule__Optimisation__MetamodelAssignment_1 ) ) ;
    public final void rule__Optimisation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:332:1: ( ( ( rule__Optimisation__MetamodelAssignment_1 ) ) )
            // InternalMDEOptimise.g:333:1: ( ( rule__Optimisation__MetamodelAssignment_1 ) )
            {
            // InternalMDEOptimise.g:333:1: ( ( rule__Optimisation__MetamodelAssignment_1 ) )
            // InternalMDEOptimise.g:334:2: ( rule__Optimisation__MetamodelAssignment_1 )
            {
             before(grammarAccess.getOptimisationAccess().getMetamodelAssignment_1()); 
            // InternalMDEOptimise.g:335:2: ( rule__Optimisation__MetamodelAssignment_1 )
            // InternalMDEOptimise.g:335:3: rule__Optimisation__MetamodelAssignment_1
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
    // InternalMDEOptimise.g:343:1: rule__Optimisation__Group__2 : rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3 ;
    public final void rule__Optimisation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:347:1: ( rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3 )
            // InternalMDEOptimise.g:348:2: rule__Optimisation__Group__2__Impl rule__Optimisation__Group__3
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
    // InternalMDEOptimise.g:355:1: rule__Optimisation__Group__2__Impl : ( ( rule__Optimisation__ModelAssignment_2 ) ) ;
    public final void rule__Optimisation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:359:1: ( ( ( rule__Optimisation__ModelAssignment_2 ) ) )
            // InternalMDEOptimise.g:360:1: ( ( rule__Optimisation__ModelAssignment_2 ) )
            {
            // InternalMDEOptimise.g:360:1: ( ( rule__Optimisation__ModelAssignment_2 ) )
            // InternalMDEOptimise.g:361:2: ( rule__Optimisation__ModelAssignment_2 )
            {
             before(grammarAccess.getOptimisationAccess().getModelAssignment_2()); 
            // InternalMDEOptimise.g:362:2: ( rule__Optimisation__ModelAssignment_2 )
            // InternalMDEOptimise.g:362:3: rule__Optimisation__ModelAssignment_2
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
    // InternalMDEOptimise.g:370:1: rule__Optimisation__Group__3 : rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4 ;
    public final void rule__Optimisation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:374:1: ( rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4 )
            // InternalMDEOptimise.g:375:2: rule__Optimisation__Group__3__Impl rule__Optimisation__Group__4
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
    // InternalMDEOptimise.g:382:1: rule__Optimisation__Group__3__Impl : ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) ) ;
    public final void rule__Optimisation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:386:1: ( ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) ) )
            // InternalMDEOptimise.g:387:1: ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) )
            {
            // InternalMDEOptimise.g:387:1: ( ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* ) )
            // InternalMDEOptimise.g:388:2: ( ( rule__Optimisation__ObjectivesAssignment_3 ) ) ( ( rule__Optimisation__ObjectivesAssignment_3 )* )
            {
            // InternalMDEOptimise.g:388:2: ( ( rule__Optimisation__ObjectivesAssignment_3 ) )
            // InternalMDEOptimise.g:389:3: ( rule__Optimisation__ObjectivesAssignment_3 )
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 
            // InternalMDEOptimise.g:390:3: ( rule__Optimisation__ObjectivesAssignment_3 )
            // InternalMDEOptimise.g:390:4: rule__Optimisation__ObjectivesAssignment_3
            {
            pushFollow(FOLLOW_7);
            rule__Optimisation__ObjectivesAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 

            }

            // InternalMDEOptimise.g:393:2: ( ( rule__Optimisation__ObjectivesAssignment_3 )* )
            // InternalMDEOptimise.g:394:3: ( rule__Optimisation__ObjectivesAssignment_3 )*
            {
             before(grammarAccess.getOptimisationAccess().getObjectivesAssignment_3()); 
            // InternalMDEOptimise.g:395:3: ( rule__Optimisation__ObjectivesAssignment_3 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==23) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMDEOptimise.g:395:4: rule__Optimisation__ObjectivesAssignment_3
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Optimisation__ObjectivesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
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
    // InternalMDEOptimise.g:404:1: rule__Optimisation__Group__4 : rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5 ;
    public final void rule__Optimisation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:408:1: ( rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5 )
            // InternalMDEOptimise.g:409:2: rule__Optimisation__Group__4__Impl rule__Optimisation__Group__5
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
    // InternalMDEOptimise.g:416:1: rule__Optimisation__Group__4__Impl : ( ( rule__Optimisation__ConstraintsAssignment_4 )* ) ;
    public final void rule__Optimisation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:420:1: ( ( ( rule__Optimisation__ConstraintsAssignment_4 )* ) )
            // InternalMDEOptimise.g:421:1: ( ( rule__Optimisation__ConstraintsAssignment_4 )* )
            {
            // InternalMDEOptimise.g:421:1: ( ( rule__Optimisation__ConstraintsAssignment_4 )* )
            // InternalMDEOptimise.g:422:2: ( rule__Optimisation__ConstraintsAssignment_4 )*
            {
             before(grammarAccess.getOptimisationAccess().getConstraintsAssignment_4()); 
            // InternalMDEOptimise.g:423:2: ( rule__Optimisation__ConstraintsAssignment_4 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==26) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMDEOptimise.g:423:3: rule__Optimisation__ConstraintsAssignment_4
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Optimisation__ConstraintsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
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
    // InternalMDEOptimise.g:431:1: rule__Optimisation__Group__5 : rule__Optimisation__Group__5__Impl rule__Optimisation__Group__6 ;
    public final void rule__Optimisation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:435:1: ( rule__Optimisation__Group__5__Impl rule__Optimisation__Group__6 )
            // InternalMDEOptimise.g:436:2: rule__Optimisation__Group__5__Impl rule__Optimisation__Group__6
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
    // InternalMDEOptimise.g:443:1: rule__Optimisation__Group__5__Impl : ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) ) ;
    public final void rule__Optimisation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:447:1: ( ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) ) )
            // InternalMDEOptimise.g:448:1: ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) )
            {
            // InternalMDEOptimise.g:448:1: ( ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* ) )
            // InternalMDEOptimise.g:449:2: ( ( rule__Optimisation__EvolversAssignment_5 ) ) ( ( rule__Optimisation__EvolversAssignment_5 )* )
            {
            // InternalMDEOptimise.g:449:2: ( ( rule__Optimisation__EvolversAssignment_5 ) )
            // InternalMDEOptimise.g:450:3: ( rule__Optimisation__EvolversAssignment_5 )
            {
             before(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 
            // InternalMDEOptimise.g:451:3: ( rule__Optimisation__EvolversAssignment_5 )
            // InternalMDEOptimise.g:451:4: rule__Optimisation__EvolversAssignment_5
            {
            pushFollow(FOLLOW_10);
            rule__Optimisation__EvolversAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 

            }

            // InternalMDEOptimise.g:454:2: ( ( rule__Optimisation__EvolversAssignment_5 )* )
            // InternalMDEOptimise.g:455:3: ( rule__Optimisation__EvolversAssignment_5 )*
            {
             before(grammarAccess.getOptimisationAccess().getEvolversAssignment_5()); 
            // InternalMDEOptimise.g:456:3: ( rule__Optimisation__EvolversAssignment_5 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=18 && LA4_0<=19)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalMDEOptimise.g:456:4: rule__Optimisation__EvolversAssignment_5
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__Optimisation__EvolversAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
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
    // InternalMDEOptimise.g:465:1: rule__Optimisation__Group__6 : rule__Optimisation__Group__6__Impl ;
    public final void rule__Optimisation__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:469:1: ( rule__Optimisation__Group__6__Impl )
            // InternalMDEOptimise.g:470:2: rule__Optimisation__Group__6__Impl
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
    // InternalMDEOptimise.g:476:1: rule__Optimisation__Group__6__Impl : ( ( rule__Optimisation__OptimisationAssignment_6 ) ) ;
    public final void rule__Optimisation__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:480:1: ( ( ( rule__Optimisation__OptimisationAssignment_6 ) ) )
            // InternalMDEOptimise.g:481:1: ( ( rule__Optimisation__OptimisationAssignment_6 ) )
            {
            // InternalMDEOptimise.g:481:1: ( ( rule__Optimisation__OptimisationAssignment_6 ) )
            // InternalMDEOptimise.g:482:2: ( rule__Optimisation__OptimisationAssignment_6 )
            {
             before(grammarAccess.getOptimisationAccess().getOptimisationAssignment_6()); 
            // InternalMDEOptimise.g:483:2: ( rule__Optimisation__OptimisationAssignment_6 )
            // InternalMDEOptimise.g:483:3: rule__Optimisation__OptimisationAssignment_6
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
    // InternalMDEOptimise.g:492:1: rule__BasepathSpec__Group__0 : rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1 ;
    public final void rule__BasepathSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:496:1: ( rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1 )
            // InternalMDEOptimise.g:497:2: rule__BasepathSpec__Group__0__Impl rule__BasepathSpec__Group__1
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
    // InternalMDEOptimise.g:504:1: rule__BasepathSpec__Group__0__Impl : ( 'basepath' ) ;
    public final void rule__BasepathSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:508:1: ( ( 'basepath' ) )
            // InternalMDEOptimise.g:509:1: ( 'basepath' )
            {
            // InternalMDEOptimise.g:509:1: ( 'basepath' )
            // InternalMDEOptimise.g:510:2: 'basepath'
            {
             before(grammarAccess.getBasepathSpecAccess().getBasepathKeyword_0()); 
            match(input,20,FOLLOW_2); 
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
    // InternalMDEOptimise.g:519:1: rule__BasepathSpec__Group__1 : rule__BasepathSpec__Group__1__Impl ;
    public final void rule__BasepathSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:523:1: ( rule__BasepathSpec__Group__1__Impl )
            // InternalMDEOptimise.g:524:2: rule__BasepathSpec__Group__1__Impl
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
    // InternalMDEOptimise.g:530:1: rule__BasepathSpec__Group__1__Impl : ( ( rule__BasepathSpec__LocationAssignment_1 ) ) ;
    public final void rule__BasepathSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:534:1: ( ( ( rule__BasepathSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:535:1: ( ( rule__BasepathSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:535:1: ( ( rule__BasepathSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:536:2: ( rule__BasepathSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getBasepathSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:537:2: ( rule__BasepathSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:537:3: rule__BasepathSpec__LocationAssignment_1
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
    // InternalMDEOptimise.g:546:1: rule__MetaModelSpec__Group__0 : rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1 ;
    public final void rule__MetaModelSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:550:1: ( rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1 )
            // InternalMDEOptimise.g:551:2: rule__MetaModelSpec__Group__0__Impl rule__MetaModelSpec__Group__1
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
    // InternalMDEOptimise.g:558:1: rule__MetaModelSpec__Group__0__Impl : ( 'metamodel' ) ;
    public final void rule__MetaModelSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:562:1: ( ( 'metamodel' ) )
            // InternalMDEOptimise.g:563:1: ( 'metamodel' )
            {
            // InternalMDEOptimise.g:563:1: ( 'metamodel' )
            // InternalMDEOptimise.g:564:2: 'metamodel'
            {
             before(grammarAccess.getMetaModelSpecAccess().getMetamodelKeyword_0()); 
            match(input,21,FOLLOW_2); 
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
    // InternalMDEOptimise.g:573:1: rule__MetaModelSpec__Group__1 : rule__MetaModelSpec__Group__1__Impl ;
    public final void rule__MetaModelSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:577:1: ( rule__MetaModelSpec__Group__1__Impl )
            // InternalMDEOptimise.g:578:2: rule__MetaModelSpec__Group__1__Impl
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
    // InternalMDEOptimise.g:584:1: rule__MetaModelSpec__Group__1__Impl : ( ( rule__MetaModelSpec__LocationAssignment_1 ) ) ;
    public final void rule__MetaModelSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:588:1: ( ( ( rule__MetaModelSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:589:1: ( ( rule__MetaModelSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:589:1: ( ( rule__MetaModelSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:590:2: ( rule__MetaModelSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getMetaModelSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:591:2: ( rule__MetaModelSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:591:3: rule__MetaModelSpec__LocationAssignment_1
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
    // InternalMDEOptimise.g:600:1: rule__ModelPathSpec__Group__0 : rule__ModelPathSpec__Group__0__Impl rule__ModelPathSpec__Group__1 ;
    public final void rule__ModelPathSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:604:1: ( rule__ModelPathSpec__Group__0__Impl rule__ModelPathSpec__Group__1 )
            // InternalMDEOptimise.g:605:2: rule__ModelPathSpec__Group__0__Impl rule__ModelPathSpec__Group__1
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
    // InternalMDEOptimise.g:612:1: rule__ModelPathSpec__Group__0__Impl : ( 'model' ) ;
    public final void rule__ModelPathSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:616:1: ( ( 'model' ) )
            // InternalMDEOptimise.g:617:1: ( 'model' )
            {
            // InternalMDEOptimise.g:617:1: ( 'model' )
            // InternalMDEOptimise.g:618:2: 'model'
            {
             before(grammarAccess.getModelPathSpecAccess().getModelKeyword_0()); 
            match(input,22,FOLLOW_2); 
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
    // InternalMDEOptimise.g:627:1: rule__ModelPathSpec__Group__1 : rule__ModelPathSpec__Group__1__Impl ;
    public final void rule__ModelPathSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:631:1: ( rule__ModelPathSpec__Group__1__Impl )
            // InternalMDEOptimise.g:632:2: rule__ModelPathSpec__Group__1__Impl
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
    // InternalMDEOptimise.g:638:1: rule__ModelPathSpec__Group__1__Impl : ( ( rule__ModelPathSpec__LocationAssignment_1 ) ) ;
    public final void rule__ModelPathSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:642:1: ( ( ( rule__ModelPathSpec__LocationAssignment_1 ) ) )
            // InternalMDEOptimise.g:643:1: ( ( rule__ModelPathSpec__LocationAssignment_1 ) )
            {
            // InternalMDEOptimise.g:643:1: ( ( rule__ModelPathSpec__LocationAssignment_1 ) )
            // InternalMDEOptimise.g:644:2: ( rule__ModelPathSpec__LocationAssignment_1 )
            {
             before(grammarAccess.getModelPathSpecAccess().getLocationAssignment_1()); 
            // InternalMDEOptimise.g:645:2: ( rule__ModelPathSpec__LocationAssignment_1 )
            // InternalMDEOptimise.g:645:3: rule__ModelPathSpec__LocationAssignment_1
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
    // InternalMDEOptimise.g:654:1: rule__ObjectiveInterpreterSpec__Group__0 : rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1 ;
    public final void rule__ObjectiveInterpreterSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:658:1: ( rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1 )
            // InternalMDEOptimise.g:659:2: rule__ObjectiveInterpreterSpec__Group__0__Impl rule__ObjectiveInterpreterSpec__Group__1
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
    // InternalMDEOptimise.g:666:1: rule__ObjectiveInterpreterSpec__Group__0__Impl : ( 'objective' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:670:1: ( ( 'objective' ) )
            // InternalMDEOptimise.g:671:1: ( 'objective' )
            {
            // InternalMDEOptimise.g:671:1: ( 'objective' )
            // InternalMDEOptimise.g:672:2: 'objective'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveKeyword_0()); 
            match(input,23,FOLLOW_2); 
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
    // InternalMDEOptimise.g:681:1: rule__ObjectiveInterpreterSpec__Group__1 : rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2 ;
    public final void rule__ObjectiveInterpreterSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:685:1: ( rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2 )
            // InternalMDEOptimise.g:686:2: rule__ObjectiveInterpreterSpec__Group__1__Impl rule__ObjectiveInterpreterSpec__Group__2
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
    // InternalMDEOptimise.g:693:1: rule__ObjectiveInterpreterSpec__Group__1__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:697:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) ) )
            // InternalMDEOptimise.g:698:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) )
            {
            // InternalMDEOptimise.g:698:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 ) )
            // InternalMDEOptimise.g:699:2: ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveNameAssignment_1()); 
            // InternalMDEOptimise.g:700:2: ( rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 )
            // InternalMDEOptimise.g:700:3: rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1
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
    // InternalMDEOptimise.g:708:1: rule__ObjectiveInterpreterSpec__Group__2 : rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3 ;
    public final void rule__ObjectiveInterpreterSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:712:1: ( rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3 )
            // InternalMDEOptimise.g:713:2: rule__ObjectiveInterpreterSpec__Group__2__Impl rule__ObjectiveInterpreterSpec__Group__3
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
    // InternalMDEOptimise.g:720:1: rule__ObjectiveInterpreterSpec__Group__2__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:724:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) ) )
            // InternalMDEOptimise.g:725:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) )
            {
            // InternalMDEOptimise.g:725:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 ) )
            // InternalMDEOptimise.g:726:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTendencyAssignment_2()); 
            // InternalMDEOptimise.g:727:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 )
            // InternalMDEOptimise.g:727:3: rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2
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
    // InternalMDEOptimise.g:735:1: rule__ObjectiveInterpreterSpec__Group__3 : rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4 ;
    public final void rule__ObjectiveInterpreterSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:739:1: ( rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4 )
            // InternalMDEOptimise.g:740:2: rule__ObjectiveInterpreterSpec__Group__3__Impl rule__ObjectiveInterpreterSpec__Group__4
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
    // InternalMDEOptimise.g:747:1: rule__ObjectiveInterpreterSpec__Group__3__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:751:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) ) )
            // InternalMDEOptimise.g:752:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) )
            {
            // InternalMDEOptimise.g:752:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 ) )
            // InternalMDEOptimise.g:753:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveTypeAssignment_3()); 
            // InternalMDEOptimise.g:754:2: ( rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 )
            // InternalMDEOptimise.g:754:3: rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3
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
    // InternalMDEOptimise.g:762:1: rule__ObjectiveInterpreterSpec__Group__4 : rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5 ;
    public final void rule__ObjectiveInterpreterSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:766:1: ( rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5 )
            // InternalMDEOptimise.g:767:2: rule__ObjectiveInterpreterSpec__Group__4__Impl rule__ObjectiveInterpreterSpec__Group__5
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
    // InternalMDEOptimise.g:774:1: rule__ObjectiveInterpreterSpec__Group__4__Impl : ( '{' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:778:1: ( ( '{' ) )
            // InternalMDEOptimise.g:779:1: ( '{' )
            {
            // InternalMDEOptimise.g:779:1: ( '{' )
            // InternalMDEOptimise.g:780:2: '{'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,24,FOLLOW_2); 
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
    // InternalMDEOptimise.g:789:1: rule__ObjectiveInterpreterSpec__Group__5 : rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6 ;
    public final void rule__ObjectiveInterpreterSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:793:1: ( rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6 )
            // InternalMDEOptimise.g:794:2: rule__ObjectiveInterpreterSpec__Group__5__Impl rule__ObjectiveInterpreterSpec__Group__6
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
    // InternalMDEOptimise.g:801:1: rule__ObjectiveInterpreterSpec__Group__5__Impl : ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:805:1: ( ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) ) )
            // InternalMDEOptimise.g:806:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) )
            {
            // InternalMDEOptimise.g:806:1: ( ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 ) )
            // InternalMDEOptimise.g:807:2: ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 )
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getObjectiveSpecAssignment_5()); 
            // InternalMDEOptimise.g:808:2: ( rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 )
            // InternalMDEOptimise.g:808:3: rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5
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
    // InternalMDEOptimise.g:816:1: rule__ObjectiveInterpreterSpec__Group__6 : rule__ObjectiveInterpreterSpec__Group__6__Impl ;
    public final void rule__ObjectiveInterpreterSpec__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:820:1: ( rule__ObjectiveInterpreterSpec__Group__6__Impl )
            // InternalMDEOptimise.g:821:2: rule__ObjectiveInterpreterSpec__Group__6__Impl
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
    // InternalMDEOptimise.g:827:1: rule__ObjectiveInterpreterSpec__Group__6__Impl : ( '}' ) ;
    public final void rule__ObjectiveInterpreterSpec__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:831:1: ( ( '}' ) )
            // InternalMDEOptimise.g:832:1: ( '}' )
            {
            // InternalMDEOptimise.g:832:1: ( '}' )
            // InternalMDEOptimise.g:833:2: '}'
            {
             before(grammarAccess.getObjectiveInterpreterSpecAccess().getRightCurlyBracketKeyword_6()); 
            match(input,25,FOLLOW_2); 
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
    // InternalMDEOptimise.g:843:1: rule__ConstraintInterpreterSpec__Group__0 : rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1 ;
    public final void rule__ConstraintInterpreterSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:847:1: ( rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1 )
            // InternalMDEOptimise.g:848:2: rule__ConstraintInterpreterSpec__Group__0__Impl rule__ConstraintInterpreterSpec__Group__1
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
    // InternalMDEOptimise.g:855:1: rule__ConstraintInterpreterSpec__Group__0__Impl : ( 'constraint' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:859:1: ( ( 'constraint' ) )
            // InternalMDEOptimise.g:860:1: ( 'constraint' )
            {
            // InternalMDEOptimise.g:860:1: ( 'constraint' )
            // InternalMDEOptimise.g:861:2: 'constraint'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintKeyword_0()); 
            match(input,26,FOLLOW_2); 
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
    // InternalMDEOptimise.g:870:1: rule__ConstraintInterpreterSpec__Group__1 : rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2 ;
    public final void rule__ConstraintInterpreterSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:874:1: ( rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2 )
            // InternalMDEOptimise.g:875:2: rule__ConstraintInterpreterSpec__Group__1__Impl rule__ConstraintInterpreterSpec__Group__2
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
    // InternalMDEOptimise.g:882:1: rule__ConstraintInterpreterSpec__Group__1__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:886:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) ) )
            // InternalMDEOptimise.g:887:1: ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) )
            {
            // InternalMDEOptimise.g:887:1: ( ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 ) )
            // InternalMDEOptimise.g:888:2: ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintNameAssignment_1()); 
            // InternalMDEOptimise.g:889:2: ( rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 )
            // InternalMDEOptimise.g:889:3: rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1
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
    // InternalMDEOptimise.g:897:1: rule__ConstraintInterpreterSpec__Group__2 : rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3 ;
    public final void rule__ConstraintInterpreterSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:901:1: ( rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3 )
            // InternalMDEOptimise.g:902:2: rule__ConstraintInterpreterSpec__Group__2__Impl rule__ConstraintInterpreterSpec__Group__3
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
    // InternalMDEOptimise.g:909:1: rule__ConstraintInterpreterSpec__Group__2__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:913:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) ) )
            // InternalMDEOptimise.g:914:1: ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) )
            {
            // InternalMDEOptimise.g:914:1: ( ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 ) )
            // InternalMDEOptimise.g:915:2: ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintTypeAssignment_2()); 
            // InternalMDEOptimise.g:916:2: ( rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 )
            // InternalMDEOptimise.g:916:3: rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2
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
    // InternalMDEOptimise.g:924:1: rule__ConstraintInterpreterSpec__Group__3 : rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4 ;
    public final void rule__ConstraintInterpreterSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:928:1: ( rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4 )
            // InternalMDEOptimise.g:929:2: rule__ConstraintInterpreterSpec__Group__3__Impl rule__ConstraintInterpreterSpec__Group__4
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
    // InternalMDEOptimise.g:936:1: rule__ConstraintInterpreterSpec__Group__3__Impl : ( '{' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:940:1: ( ( '{' ) )
            // InternalMDEOptimise.g:941:1: ( '{' )
            {
            // InternalMDEOptimise.g:941:1: ( '{' )
            // InternalMDEOptimise.g:942:2: '{'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getLeftCurlyBracketKeyword_3()); 
            match(input,24,FOLLOW_2); 
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
    // InternalMDEOptimise.g:951:1: rule__ConstraintInterpreterSpec__Group__4 : rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5 ;
    public final void rule__ConstraintInterpreterSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:955:1: ( rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5 )
            // InternalMDEOptimise.g:956:2: rule__ConstraintInterpreterSpec__Group__4__Impl rule__ConstraintInterpreterSpec__Group__5
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
    // InternalMDEOptimise.g:963:1: rule__ConstraintInterpreterSpec__Group__4__Impl : ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) ) ;
    public final void rule__ConstraintInterpreterSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:967:1: ( ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) ) )
            // InternalMDEOptimise.g:968:1: ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) )
            {
            // InternalMDEOptimise.g:968:1: ( ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 ) )
            // InternalMDEOptimise.g:969:2: ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 )
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getConstraintSpecAssignment_4()); 
            // InternalMDEOptimise.g:970:2: ( rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 )
            // InternalMDEOptimise.g:970:3: rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4
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
    // InternalMDEOptimise.g:978:1: rule__ConstraintInterpreterSpec__Group__5 : rule__ConstraintInterpreterSpec__Group__5__Impl ;
    public final void rule__ConstraintInterpreterSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:982:1: ( rule__ConstraintInterpreterSpec__Group__5__Impl )
            // InternalMDEOptimise.g:983:2: rule__ConstraintInterpreterSpec__Group__5__Impl
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
    // InternalMDEOptimise.g:989:1: rule__ConstraintInterpreterSpec__Group__5__Impl : ( '}' ) ;
    public final void rule__ConstraintInterpreterSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:993:1: ( ( '}' ) )
            // InternalMDEOptimise.g:994:1: ( '}' )
            {
            // InternalMDEOptimise.g:994:1: ( '}' )
            // InternalMDEOptimise.g:995:2: '}'
            {
             before(grammarAccess.getConstraintInterpreterSpecAccess().getRightCurlyBracketKeyword_5()); 
            match(input,25,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1005:1: rule__EvolverSpec__Group__0 : rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1 ;
    public final void rule__EvolverSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1009:1: ( rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1 )
            // InternalMDEOptimise.g:1010:2: rule__EvolverSpec__Group__0__Impl rule__EvolverSpec__Group__1
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
    // InternalMDEOptimise.g:1017:1: rule__EvolverSpec__Group__0__Impl : ( ( rule__EvolverSpec__EvolverTypeAssignment_0 ) ) ;
    public final void rule__EvolverSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1021:1: ( ( ( rule__EvolverSpec__EvolverTypeAssignment_0 ) ) )
            // InternalMDEOptimise.g:1022:1: ( ( rule__EvolverSpec__EvolverTypeAssignment_0 ) )
            {
            // InternalMDEOptimise.g:1022:1: ( ( rule__EvolverSpec__EvolverTypeAssignment_0 ) )
            // InternalMDEOptimise.g:1023:2: ( rule__EvolverSpec__EvolverTypeAssignment_0 )
            {
             before(grammarAccess.getEvolverSpecAccess().getEvolverTypeAssignment_0()); 
            // InternalMDEOptimise.g:1024:2: ( rule__EvolverSpec__EvolverTypeAssignment_0 )
            // InternalMDEOptimise.g:1024:3: rule__EvolverSpec__EvolverTypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__EvolverSpec__EvolverTypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getEvolverSpecAccess().getEvolverTypeAssignment_0()); 

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
    // InternalMDEOptimise.g:1032:1: rule__EvolverSpec__Group__1 : rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2 ;
    public final void rule__EvolverSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1036:1: ( rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2 )
            // InternalMDEOptimise.g:1037:2: rule__EvolverSpec__Group__1__Impl rule__EvolverSpec__Group__2
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
    // InternalMDEOptimise.g:1044:1: rule__EvolverSpec__Group__1__Impl : ( 'using' ) ;
    public final void rule__EvolverSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1048:1: ( ( 'using' ) )
            // InternalMDEOptimise.g:1049:1: ( 'using' )
            {
            // InternalMDEOptimise.g:1049:1: ( 'using' )
            // InternalMDEOptimise.g:1050:2: 'using'
            {
             before(grammarAccess.getEvolverSpecAccess().getUsingKeyword_1()); 
            match(input,27,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1059:1: rule__EvolverSpec__Group__2 : rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3 ;
    public final void rule__EvolverSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1063:1: ( rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3 )
            // InternalMDEOptimise.g:1064:2: rule__EvolverSpec__Group__2__Impl rule__EvolverSpec__Group__3
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
    // InternalMDEOptimise.g:1071:1: rule__EvolverSpec__Group__2__Impl : ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) ) ;
    public final void rule__EvolverSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1075:1: ( ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) ) )
            // InternalMDEOptimise.g:1076:1: ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) )
            {
            // InternalMDEOptimise.g:1076:1: ( ( rule__EvolverSpec__Rule_locationAssignment_2 ) )
            // InternalMDEOptimise.g:1077:2: ( rule__EvolverSpec__Rule_locationAssignment_2 )
            {
             before(grammarAccess.getEvolverSpecAccess().getRule_locationAssignment_2()); 
            // InternalMDEOptimise.g:1078:2: ( rule__EvolverSpec__Rule_locationAssignment_2 )
            // InternalMDEOptimise.g:1078:3: rule__EvolverSpec__Rule_locationAssignment_2
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
    // InternalMDEOptimise.g:1086:1: rule__EvolverSpec__Group__3 : rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4 ;
    public final void rule__EvolverSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1090:1: ( rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4 )
            // InternalMDEOptimise.g:1091:2: rule__EvolverSpec__Group__3__Impl rule__EvolverSpec__Group__4
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
    // InternalMDEOptimise.g:1098:1: rule__EvolverSpec__Group__3__Impl : ( 'unit' ) ;
    public final void rule__EvolverSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1102:1: ( ( 'unit' ) )
            // InternalMDEOptimise.g:1103:1: ( 'unit' )
            {
            // InternalMDEOptimise.g:1103:1: ( 'unit' )
            // InternalMDEOptimise.g:1104:2: 'unit'
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitKeyword_3()); 
            match(input,28,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1113:1: rule__EvolverSpec__Group__4 : rule__EvolverSpec__Group__4__Impl ;
    public final void rule__EvolverSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1117:1: ( rule__EvolverSpec__Group__4__Impl )
            // InternalMDEOptimise.g:1118:2: rule__EvolverSpec__Group__4__Impl
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
    // InternalMDEOptimise.g:1124:1: rule__EvolverSpec__Group__4__Impl : ( ( rule__EvolverSpec__UnitAssignment_4 ) ) ;
    public final void rule__EvolverSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1128:1: ( ( ( rule__EvolverSpec__UnitAssignment_4 ) ) )
            // InternalMDEOptimise.g:1129:1: ( ( rule__EvolverSpec__UnitAssignment_4 ) )
            {
            // InternalMDEOptimise.g:1129:1: ( ( rule__EvolverSpec__UnitAssignment_4 ) )
            // InternalMDEOptimise.g:1130:2: ( rule__EvolverSpec__UnitAssignment_4 )
            {
             before(grammarAccess.getEvolverSpecAccess().getUnitAssignment_4()); 
            // InternalMDEOptimise.g:1131:2: ( rule__EvolverSpec__UnitAssignment_4 )
            // InternalMDEOptimise.g:1131:3: rule__EvolverSpec__UnitAssignment_4
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
    // InternalMDEOptimise.g:1140:1: rule__OptimisationSpec__Group__0 : rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1 ;
    public final void rule__OptimisationSpec__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1144:1: ( rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1 )
            // InternalMDEOptimise.g:1145:2: rule__OptimisationSpec__Group__0__Impl rule__OptimisationSpec__Group__1
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
    // InternalMDEOptimise.g:1152:1: rule__OptimisationSpec__Group__0__Impl : ( 'optimisation' ) ;
    public final void rule__OptimisationSpec__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1156:1: ( ( 'optimisation' ) )
            // InternalMDEOptimise.g:1157:1: ( 'optimisation' )
            {
            // InternalMDEOptimise.g:1157:1: ( 'optimisation' )
            // InternalMDEOptimise.g:1158:2: 'optimisation'
            {
             before(grammarAccess.getOptimisationSpecAccess().getOptimisationKeyword_0()); 
            match(input,29,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1167:1: rule__OptimisationSpec__Group__1 : rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2 ;
    public final void rule__OptimisationSpec__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1171:1: ( rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2 )
            // InternalMDEOptimise.g:1172:2: rule__OptimisationSpec__Group__1__Impl rule__OptimisationSpec__Group__2
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
    // InternalMDEOptimise.g:1179:1: rule__OptimisationSpec__Group__1__Impl : ( 'provider' ) ;
    public final void rule__OptimisationSpec__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1183:1: ( ( 'provider' ) )
            // InternalMDEOptimise.g:1184:1: ( 'provider' )
            {
            // InternalMDEOptimise.g:1184:1: ( 'provider' )
            // InternalMDEOptimise.g:1185:2: 'provider'
            {
             before(grammarAccess.getOptimisationSpecAccess().getProviderKeyword_1()); 
            match(input,30,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1194:1: rule__OptimisationSpec__Group__2 : rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3 ;
    public final void rule__OptimisationSpec__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1198:1: ( rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3 )
            // InternalMDEOptimise.g:1199:2: rule__OptimisationSpec__Group__2__Impl rule__OptimisationSpec__Group__3
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
    // InternalMDEOptimise.g:1206:1: rule__OptimisationSpec__Group__2__Impl : ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) ) ;
    public final void rule__OptimisationSpec__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1210:1: ( ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) ) )
            // InternalMDEOptimise.g:1211:1: ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) )
            {
            // InternalMDEOptimise.g:1211:1: ( ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 ) )
            // InternalMDEOptimise.g:1212:2: ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmFactoryAssignment_2()); 
            // InternalMDEOptimise.g:1213:2: ( rule__OptimisationSpec__AlgorithmFactoryAssignment_2 )
            // InternalMDEOptimise.g:1213:3: rule__OptimisationSpec__AlgorithmFactoryAssignment_2
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
    // InternalMDEOptimise.g:1221:1: rule__OptimisationSpec__Group__3 : rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4 ;
    public final void rule__OptimisationSpec__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1225:1: ( rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4 )
            // InternalMDEOptimise.g:1226:2: rule__OptimisationSpec__Group__3__Impl rule__OptimisationSpec__Group__4
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
    // InternalMDEOptimise.g:1233:1: rule__OptimisationSpec__Group__3__Impl : ( 'algorithm' ) ;
    public final void rule__OptimisationSpec__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1237:1: ( ( 'algorithm' ) )
            // InternalMDEOptimise.g:1238:1: ( 'algorithm' )
            {
            // InternalMDEOptimise.g:1238:1: ( 'algorithm' )
            // InternalMDEOptimise.g:1239:2: 'algorithm'
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmKeyword_3()); 
            match(input,31,FOLLOW_2); 
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
    // InternalMDEOptimise.g:1248:1: rule__OptimisationSpec__Group__4 : rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5 ;
    public final void rule__OptimisationSpec__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1252:1: ( rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5 )
            // InternalMDEOptimise.g:1253:2: rule__OptimisationSpec__Group__4__Impl rule__OptimisationSpec__Group__5
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
    // InternalMDEOptimise.g:1260:1: rule__OptimisationSpec__Group__4__Impl : ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) ) ;
    public final void rule__OptimisationSpec__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1264:1: ( ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) ) )
            // InternalMDEOptimise.g:1265:1: ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) )
            {
            // InternalMDEOptimise.g:1265:1: ( ( rule__OptimisationSpec__AlgorithmNameAssignment_4 ) )
            // InternalMDEOptimise.g:1266:2: ( rule__OptimisationSpec__AlgorithmNameAssignment_4 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmNameAssignment_4()); 
            // InternalMDEOptimise.g:1267:2: ( rule__OptimisationSpec__AlgorithmNameAssignment_4 )
            // InternalMDEOptimise.g:1267:3: rule__OptimisationSpec__AlgorithmNameAssignment_4
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
    // InternalMDEOptimise.g:1275:1: rule__OptimisationSpec__Group__5 : rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6 ;
    public final void rule__OptimisationSpec__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1279:1: ( rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6 )
            // InternalMDEOptimise.g:1280:2: rule__OptimisationSpec__Group__5__Impl rule__OptimisationSpec__Group__6
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
    // InternalMDEOptimise.g:1287:1: rule__OptimisationSpec__Group__5__Impl : ( 'variation' ) ;
    public final void rule__OptimisationSpec__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1291:1: ( ( 'variation' ) )
            // InternalMDEOptimise.g:1292:1: ( 'variation' )
            {
            // InternalMDEOptimise.g:1292:1: ( 'variation' )
            // InternalMDEOptimise.g:1293:2: 'variation'
            {
             before(grammarAccess.getOptimisationSpecAccess().getVariationKeyword_5()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getVariationKeyword_5()); 

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
    // InternalMDEOptimise.g:1302:1: rule__OptimisationSpec__Group__6 : rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7 ;
    public final void rule__OptimisationSpec__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1306:1: ( rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7 )
            // InternalMDEOptimise.g:1307:2: rule__OptimisationSpec__Group__6__Impl rule__OptimisationSpec__Group__7
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
    // InternalMDEOptimise.g:1314:1: rule__OptimisationSpec__Group__6__Impl : ( ( rule__OptimisationSpec__AlgorithmVariationAssignment_6 ) ) ;
    public final void rule__OptimisationSpec__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1318:1: ( ( ( rule__OptimisationSpec__AlgorithmVariationAssignment_6 ) ) )
            // InternalMDEOptimise.g:1319:1: ( ( rule__OptimisationSpec__AlgorithmVariationAssignment_6 ) )
            {
            // InternalMDEOptimise.g:1319:1: ( ( rule__OptimisationSpec__AlgorithmVariationAssignment_6 ) )
            // InternalMDEOptimise.g:1320:2: ( rule__OptimisationSpec__AlgorithmVariationAssignment_6 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmVariationAssignment_6()); 
            // InternalMDEOptimise.g:1321:2: ( rule__OptimisationSpec__AlgorithmVariationAssignment_6 )
            // InternalMDEOptimise.g:1321:3: rule__OptimisationSpec__AlgorithmVariationAssignment_6
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmVariationAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmVariationAssignment_6()); 

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
    // InternalMDEOptimise.g:1329:1: rule__OptimisationSpec__Group__7 : rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8 ;
    public final void rule__OptimisationSpec__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1333:1: ( rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8 )
            // InternalMDEOptimise.g:1334:2: rule__OptimisationSpec__Group__7__Impl rule__OptimisationSpec__Group__8
            {
            pushFollow(FOLLOW_27);
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
    // InternalMDEOptimise.g:1341:1: rule__OptimisationSpec__Group__7__Impl : ( 'evolutions' ) ;
    public final void rule__OptimisationSpec__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1345:1: ( ( 'evolutions' ) )
            // InternalMDEOptimise.g:1346:1: ( 'evolutions' )
            {
            // InternalMDEOptimise.g:1346:1: ( 'evolutions' )
            // InternalMDEOptimise.g:1347:2: 'evolutions'
            {
             before(grammarAccess.getOptimisationSpecAccess().getEvolutionsKeyword_7()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getEvolutionsKeyword_7()); 

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
    // InternalMDEOptimise.g:1356:1: rule__OptimisationSpec__Group__8 : rule__OptimisationSpec__Group__8__Impl rule__OptimisationSpec__Group__9 ;
    public final void rule__OptimisationSpec__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1360:1: ( rule__OptimisationSpec__Group__8__Impl rule__OptimisationSpec__Group__9 )
            // InternalMDEOptimise.g:1361:2: rule__OptimisationSpec__Group__8__Impl rule__OptimisationSpec__Group__9
            {
            pushFollow(FOLLOW_28);
            rule__OptimisationSpec__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__9();

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
    // InternalMDEOptimise.g:1368:1: rule__OptimisationSpec__Group__8__Impl : ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 ) ) ;
    public final void rule__OptimisationSpec__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1372:1: ( ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 ) ) )
            // InternalMDEOptimise.g:1373:1: ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 ) )
            {
            // InternalMDEOptimise.g:1373:1: ( ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 ) )
            // InternalMDEOptimise.g:1374:2: ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsAssignment_8()); 
            // InternalMDEOptimise.g:1375:2: ( rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 )
            // InternalMDEOptimise.g:1375:3: rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsAssignment_8()); 

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


    // $ANTLR start "rule__OptimisationSpec__Group__9"
    // InternalMDEOptimise.g:1383:1: rule__OptimisationSpec__Group__9 : rule__OptimisationSpec__Group__9__Impl rule__OptimisationSpec__Group__10 ;
    public final void rule__OptimisationSpec__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1387:1: ( rule__OptimisationSpec__Group__9__Impl rule__OptimisationSpec__Group__10 )
            // InternalMDEOptimise.g:1388:2: rule__OptimisationSpec__Group__9__Impl rule__OptimisationSpec__Group__10
            {
            pushFollow(FOLLOW_27);
            rule__OptimisationSpec__Group__9__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__10();

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
    // $ANTLR end "rule__OptimisationSpec__Group__9"


    // $ANTLR start "rule__OptimisationSpec__Group__9__Impl"
    // InternalMDEOptimise.g:1395:1: rule__OptimisationSpec__Group__9__Impl : ( 'population' ) ;
    public final void rule__OptimisationSpec__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1399:1: ( ( 'population' ) )
            // InternalMDEOptimise.g:1400:1: ( 'population' )
            {
            // InternalMDEOptimise.g:1400:1: ( 'population' )
            // InternalMDEOptimise.g:1401:2: 'population'
            {
             before(grammarAccess.getOptimisationSpecAccess().getPopulationKeyword_9()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getPopulationKeyword_9()); 

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
    // $ANTLR end "rule__OptimisationSpec__Group__9__Impl"


    // $ANTLR start "rule__OptimisationSpec__Group__10"
    // InternalMDEOptimise.g:1410:1: rule__OptimisationSpec__Group__10 : rule__OptimisationSpec__Group__10__Impl ;
    public final void rule__OptimisationSpec__Group__10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1414:1: ( rule__OptimisationSpec__Group__10__Impl )
            // InternalMDEOptimise.g:1415:2: rule__OptimisationSpec__Group__10__Impl
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__Group__10__Impl();

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
    // $ANTLR end "rule__OptimisationSpec__Group__10"


    // $ANTLR start "rule__OptimisationSpec__Group__10__Impl"
    // InternalMDEOptimise.g:1421:1: rule__OptimisationSpec__Group__10__Impl : ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_10 ) ) ;
    public final void rule__OptimisationSpec__Group__10__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1425:1: ( ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_10 ) ) )
            // InternalMDEOptimise.g:1426:1: ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_10 ) )
            {
            // InternalMDEOptimise.g:1426:1: ( ( rule__OptimisationSpec__AlgorithmPopulationAssignment_10 ) )
            // InternalMDEOptimise.g:1427:2: ( rule__OptimisationSpec__AlgorithmPopulationAssignment_10 )
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationAssignment_10()); 
            // InternalMDEOptimise.g:1428:2: ( rule__OptimisationSpec__AlgorithmPopulationAssignment_10 )
            // InternalMDEOptimise.g:1428:3: rule__OptimisationSpec__AlgorithmPopulationAssignment_10
            {
            pushFollow(FOLLOW_2);
            rule__OptimisationSpec__AlgorithmPopulationAssignment_10();

            state._fsp--;


            }

             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationAssignment_10()); 

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
    // $ANTLR end "rule__OptimisationSpec__Group__10__Impl"


    // $ANTLR start "rule__Optimisation__BasepathAssignment_0"
    // InternalMDEOptimise.g:1437:1: rule__Optimisation__BasepathAssignment_0 : ( ruleBasepathSpec ) ;
    public final void rule__Optimisation__BasepathAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1441:1: ( ( ruleBasepathSpec ) )
            // InternalMDEOptimise.g:1442:2: ( ruleBasepathSpec )
            {
            // InternalMDEOptimise.g:1442:2: ( ruleBasepathSpec )
            // InternalMDEOptimise.g:1443:3: ruleBasepathSpec
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
    // InternalMDEOptimise.g:1452:1: rule__Optimisation__MetamodelAssignment_1 : ( ruleMetaModelSpec ) ;
    public final void rule__Optimisation__MetamodelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1456:1: ( ( ruleMetaModelSpec ) )
            // InternalMDEOptimise.g:1457:2: ( ruleMetaModelSpec )
            {
            // InternalMDEOptimise.g:1457:2: ( ruleMetaModelSpec )
            // InternalMDEOptimise.g:1458:3: ruleMetaModelSpec
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
    // InternalMDEOptimise.g:1467:1: rule__Optimisation__ModelAssignment_2 : ( ruleModelPathSpec ) ;
    public final void rule__Optimisation__ModelAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1471:1: ( ( ruleModelPathSpec ) )
            // InternalMDEOptimise.g:1472:2: ( ruleModelPathSpec )
            {
            // InternalMDEOptimise.g:1472:2: ( ruleModelPathSpec )
            // InternalMDEOptimise.g:1473:3: ruleModelPathSpec
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
    // InternalMDEOptimise.g:1482:1: rule__Optimisation__ObjectivesAssignment_3 : ( ruleObjectiveInterpreterSpec ) ;
    public final void rule__Optimisation__ObjectivesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1486:1: ( ( ruleObjectiveInterpreterSpec ) )
            // InternalMDEOptimise.g:1487:2: ( ruleObjectiveInterpreterSpec )
            {
            // InternalMDEOptimise.g:1487:2: ( ruleObjectiveInterpreterSpec )
            // InternalMDEOptimise.g:1488:3: ruleObjectiveInterpreterSpec
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
    // InternalMDEOptimise.g:1497:1: rule__Optimisation__ConstraintsAssignment_4 : ( ruleConstraintInterpreterSpec ) ;
    public final void rule__Optimisation__ConstraintsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1501:1: ( ( ruleConstraintInterpreterSpec ) )
            // InternalMDEOptimise.g:1502:2: ( ruleConstraintInterpreterSpec )
            {
            // InternalMDEOptimise.g:1502:2: ( ruleConstraintInterpreterSpec )
            // InternalMDEOptimise.g:1503:3: ruleConstraintInterpreterSpec
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
    // InternalMDEOptimise.g:1512:1: rule__Optimisation__EvolversAssignment_5 : ( ruleEvolverSpec ) ;
    public final void rule__Optimisation__EvolversAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1516:1: ( ( ruleEvolverSpec ) )
            // InternalMDEOptimise.g:1517:2: ( ruleEvolverSpec )
            {
            // InternalMDEOptimise.g:1517:2: ( ruleEvolverSpec )
            // InternalMDEOptimise.g:1518:3: ruleEvolverSpec
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
    // InternalMDEOptimise.g:1527:1: rule__Optimisation__OptimisationAssignment_6 : ( ruleOptimisationSpec ) ;
    public final void rule__Optimisation__OptimisationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1531:1: ( ( ruleOptimisationSpec ) )
            // InternalMDEOptimise.g:1532:2: ( ruleOptimisationSpec )
            {
            // InternalMDEOptimise.g:1532:2: ( ruleOptimisationSpec )
            // InternalMDEOptimise.g:1533:3: ruleOptimisationSpec
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
    // InternalMDEOptimise.g:1542:1: rule__BasepathSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__BasepathSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1546:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1547:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1547:2: ( RULE_URL )
            // InternalMDEOptimise.g:1548:3: RULE_URL
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
    // InternalMDEOptimise.g:1557:1: rule__MetaModelSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__MetaModelSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1561:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1562:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1562:2: ( RULE_URL )
            // InternalMDEOptimise.g:1563:3: RULE_URL
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
    // InternalMDEOptimise.g:1572:1: rule__ModelPathSpec__LocationAssignment_1 : ( RULE_URL ) ;
    public final void rule__ModelPathSpec__LocationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1576:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1577:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1577:2: ( RULE_URL )
            // InternalMDEOptimise.g:1578:3: RULE_URL
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
    // InternalMDEOptimise.g:1587:1: rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1 : ( RULE_ENTITY_NAME ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1591:1: ( ( RULE_ENTITY_NAME ) )
            // InternalMDEOptimise.g:1592:2: ( RULE_ENTITY_NAME )
            {
            // InternalMDEOptimise.g:1592:2: ( RULE_ENTITY_NAME )
            // InternalMDEOptimise.g:1593:3: RULE_ENTITY_NAME
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
    // InternalMDEOptimise.g:1602:1: rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2 : ( RULE_OBJECTIVE_TENDENCY ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveTendencyAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1606:1: ( ( RULE_OBJECTIVE_TENDENCY ) )
            // InternalMDEOptimise.g:1607:2: ( RULE_OBJECTIVE_TENDENCY )
            {
            // InternalMDEOptimise.g:1607:2: ( RULE_OBJECTIVE_TENDENCY )
            // InternalMDEOptimise.g:1608:3: RULE_OBJECTIVE_TENDENCY
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
    // InternalMDEOptimise.g:1617:1: rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3 : ( RULE_ENTITY_TYPE ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveTypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1621:1: ( ( RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:1622:2: ( RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:1622:2: ( RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:1623:3: RULE_ENTITY_TYPE
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
    // InternalMDEOptimise.g:1632:1: rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5 : ( RULE_STRING ) ;
    public final void rule__ObjectiveInterpreterSpec__ObjectiveSpecAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1636:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1637:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1637:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1638:3: RULE_STRING
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
    // InternalMDEOptimise.g:1647:1: rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1 : ( RULE_ENTITY_NAME ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintNameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1651:1: ( ( RULE_ENTITY_NAME ) )
            // InternalMDEOptimise.g:1652:2: ( RULE_ENTITY_NAME )
            {
            // InternalMDEOptimise.g:1652:2: ( RULE_ENTITY_NAME )
            // InternalMDEOptimise.g:1653:3: RULE_ENTITY_NAME
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
    // InternalMDEOptimise.g:1662:1: rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2 : ( RULE_ENTITY_TYPE ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintTypeAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1666:1: ( ( RULE_ENTITY_TYPE ) )
            // InternalMDEOptimise.g:1667:2: ( RULE_ENTITY_TYPE )
            {
            // InternalMDEOptimise.g:1667:2: ( RULE_ENTITY_TYPE )
            // InternalMDEOptimise.g:1668:3: RULE_ENTITY_TYPE
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
    // InternalMDEOptimise.g:1677:1: rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4 : ( RULE_STRING ) ;
    public final void rule__ConstraintInterpreterSpec__ConstraintSpecAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1681:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1682:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1682:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1683:3: RULE_STRING
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


    // $ANTLR start "rule__EvolverSpec__EvolverTypeAssignment_0"
    // InternalMDEOptimise.g:1692:1: rule__EvolverSpec__EvolverTypeAssignment_0 : ( ruleEvolverType ) ;
    public final void rule__EvolverSpec__EvolverTypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1696:1: ( ( ruleEvolverType ) )
            // InternalMDEOptimise.g:1697:2: ( ruleEvolverType )
            {
            // InternalMDEOptimise.g:1697:2: ( ruleEvolverType )
            // InternalMDEOptimise.g:1698:3: ruleEvolverType
            {
             before(grammarAccess.getEvolverSpecAccess().getEvolverTypeEvolverTypeEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleEvolverType();

            state._fsp--;

             after(grammarAccess.getEvolverSpecAccess().getEvolverTypeEvolverTypeEnumRuleCall_0_0()); 

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
    // $ANTLR end "rule__EvolverSpec__EvolverTypeAssignment_0"


    // $ANTLR start "rule__EvolverSpec__Rule_locationAssignment_2"
    // InternalMDEOptimise.g:1707:1: rule__EvolverSpec__Rule_locationAssignment_2 : ( RULE_URL ) ;
    public final void rule__EvolverSpec__Rule_locationAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1711:1: ( ( RULE_URL ) )
            // InternalMDEOptimise.g:1712:2: ( RULE_URL )
            {
            // InternalMDEOptimise.g:1712:2: ( RULE_URL )
            // InternalMDEOptimise.g:1713:3: RULE_URL
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
    // InternalMDEOptimise.g:1722:1: rule__EvolverSpec__UnitAssignment_4 : ( RULE_STRING ) ;
    public final void rule__EvolverSpec__UnitAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1726:1: ( ( RULE_STRING ) )
            // InternalMDEOptimise.g:1727:2: ( RULE_STRING )
            {
            // InternalMDEOptimise.g:1727:2: ( RULE_STRING )
            // InternalMDEOptimise.g:1728:3: RULE_STRING
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
    // InternalMDEOptimise.g:1737:1: rule__OptimisationSpec__AlgorithmFactoryAssignment_2 : ( RULE_ALGORITHM_FACTORY ) ;
    public final void rule__OptimisationSpec__AlgorithmFactoryAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1741:1: ( ( RULE_ALGORITHM_FACTORY ) )
            // InternalMDEOptimise.g:1742:2: ( RULE_ALGORITHM_FACTORY )
            {
            // InternalMDEOptimise.g:1742:2: ( RULE_ALGORITHM_FACTORY )
            // InternalMDEOptimise.g:1743:3: RULE_ALGORITHM_FACTORY
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
    // InternalMDEOptimise.g:1752:1: rule__OptimisationSpec__AlgorithmNameAssignment_4 : ( RULE_ALGORITHM_NAME ) ;
    public final void rule__OptimisationSpec__AlgorithmNameAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1756:1: ( ( RULE_ALGORITHM_NAME ) )
            // InternalMDEOptimise.g:1757:2: ( RULE_ALGORITHM_NAME )
            {
            // InternalMDEOptimise.g:1757:2: ( RULE_ALGORITHM_NAME )
            // InternalMDEOptimise.g:1758:3: RULE_ALGORITHM_NAME
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


    // $ANTLR start "rule__OptimisationSpec__AlgorithmVariationAssignment_6"
    // InternalMDEOptimise.g:1767:1: rule__OptimisationSpec__AlgorithmVariationAssignment_6 : ( RULE_ALGORITHM_VARIATION ) ;
    public final void rule__OptimisationSpec__AlgorithmVariationAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1771:1: ( ( RULE_ALGORITHM_VARIATION ) )
            // InternalMDEOptimise.g:1772:2: ( RULE_ALGORITHM_VARIATION )
            {
            // InternalMDEOptimise.g:1772:2: ( RULE_ALGORITHM_VARIATION )
            // InternalMDEOptimise.g:1773:3: RULE_ALGORITHM_VARIATION
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmVariationALGORITHM_VARIATIONTerminalRuleCall_6_0()); 
            match(input,RULE_ALGORITHM_VARIATION,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmVariationALGORITHM_VARIATIONTerminalRuleCall_6_0()); 

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
    // $ANTLR end "rule__OptimisationSpec__AlgorithmVariationAssignment_6"


    // $ANTLR start "rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8"
    // InternalMDEOptimise.g:1782:1: rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8 : ( RULE_INT ) ;
    public final void rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1786:1: ( ( RULE_INT ) )
            // InternalMDEOptimise.g:1787:2: ( RULE_INT )
            {
            // InternalMDEOptimise.g:1787:2: ( RULE_INT )
            // InternalMDEOptimise.g:1788:3: RULE_INT
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsINTTerminalRuleCall_8_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmEvolutionsINTTerminalRuleCall_8_0()); 

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
    // $ANTLR end "rule__OptimisationSpec__AlgorithmEvolutionsAssignment_8"


    // $ANTLR start "rule__OptimisationSpec__AlgorithmPopulationAssignment_10"
    // InternalMDEOptimise.g:1797:1: rule__OptimisationSpec__AlgorithmPopulationAssignment_10 : ( RULE_INT ) ;
    public final void rule__OptimisationSpec__AlgorithmPopulationAssignment_10() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMDEOptimise.g:1801:1: ( ( RULE_INT ) )
            // InternalMDEOptimise.g:1802:2: ( RULE_INT )
            {
            // InternalMDEOptimise.g:1802:2: ( RULE_INT )
            // InternalMDEOptimise.g:1803:3: RULE_INT
            {
             before(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationINTTerminalRuleCall_10_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getOptimisationSpecAccess().getAlgorithmPopulationINTTerminalRuleCall_10_0()); 

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
    // $ANTLR end "rule__OptimisationSpec__AlgorithmPopulationAssignment_10"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000000040C0000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000040C0002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000400000000L});

}