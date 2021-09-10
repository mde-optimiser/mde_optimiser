package org.xtext.example.mydsl.ide.contentassist.antlr.internal;

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
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_ID", "RULE_UNIT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'requiring'", "'providing'", "'<'", "'<='", "'='", "'!='", "'>='", "'>'", "'sum'", "'max'", "'resource'", "'type'", "'{'", "'}'", "'using'", "'property'", "':'", "'meta'", "'component'", "'contract'", "'requires component'", "'of'", "'requires resource'", "'with'", "'request'", "'for'", "'minimize'", "'('", "')'", "'+'", "'-'", "'*'", "'/'", "'^'", "'.'", "'solution'", "'->'", "'-->'", "'container'"
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
    public static final int RULE_ID=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=8;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=7;
    public static final int RULE_UNIT=6;
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

    	public void setGrammarAccess(MyDslGrammarAccess grammarAccess) {
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



    // $ANTLR start "entryRuleModel"
    // InternalMyDsl.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalMyDsl.g:54:1: ( ruleModel EOF )
            // InternalMyDsl.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
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
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalMyDsl.g:62:1: ruleModel : ( ( rule__Model__Group__0 ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:66:2: ( ( ( rule__Model__Group__0 ) ) )
            // InternalMyDsl.g:67:2: ( ( rule__Model__Group__0 ) )
            {
            // InternalMyDsl.g:67:2: ( ( rule__Model__Group__0 ) )
            // InternalMyDsl.g:68:3: ( rule__Model__Group__0 )
            {
             before(grammarAccess.getModelAccess().getGroup()); 
            // InternalMyDsl.g:69:3: ( rule__Model__Group__0 )
            // InternalMyDsl.g:69:4: rule__Model__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getGroup()); 

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
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleHardwareModel"
    // InternalMyDsl.g:78:1: entryRuleHardwareModel : ruleHardwareModel EOF ;
    public final void entryRuleHardwareModel() throws RecognitionException {
        try {
            // InternalMyDsl.g:79:1: ( ruleHardwareModel EOF )
            // InternalMyDsl.g:80:1: ruleHardwareModel EOF
            {
             before(grammarAccess.getHardwareModelRule()); 
            pushFollow(FOLLOW_1);
            ruleHardwareModel();

            state._fsp--;

             after(grammarAccess.getHardwareModelRule()); 
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
    // $ANTLR end "entryRuleHardwareModel"


    // $ANTLR start "ruleHardwareModel"
    // InternalMyDsl.g:87:1: ruleHardwareModel : ( ( rule__HardwareModel__Group__0 ) ) ;
    public final void ruleHardwareModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:91:2: ( ( ( rule__HardwareModel__Group__0 ) ) )
            // InternalMyDsl.g:92:2: ( ( rule__HardwareModel__Group__0 ) )
            {
            // InternalMyDsl.g:92:2: ( ( rule__HardwareModel__Group__0 ) )
            // InternalMyDsl.g:93:3: ( rule__HardwareModel__Group__0 )
            {
             before(grammarAccess.getHardwareModelAccess().getGroup()); 
            // InternalMyDsl.g:94:3: ( rule__HardwareModel__Group__0 )
            // InternalMyDsl.g:94:4: rule__HardwareModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__HardwareModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getHardwareModelAccess().getGroup()); 

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
    // $ANTLR end "ruleHardwareModel"


    // $ANTLR start "entryRuleResourceType"
    // InternalMyDsl.g:103:1: entryRuleResourceType : ruleResourceType EOF ;
    public final void entryRuleResourceType() throws RecognitionException {
        try {
            // InternalMyDsl.g:104:1: ( ruleResourceType EOF )
            // InternalMyDsl.g:105:1: ruleResourceType EOF
            {
             before(grammarAccess.getResourceTypeRule()); 
            pushFollow(FOLLOW_1);
            ruleResourceType();

            state._fsp--;

             after(grammarAccess.getResourceTypeRule()); 
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
    // $ANTLR end "entryRuleResourceType"


    // $ANTLR start "ruleResourceType"
    // InternalMyDsl.g:112:1: ruleResourceType : ( ( rule__ResourceType__Group__0 ) ) ;
    public final void ruleResourceType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:116:2: ( ( ( rule__ResourceType__Group__0 ) ) )
            // InternalMyDsl.g:117:2: ( ( rule__ResourceType__Group__0 ) )
            {
            // InternalMyDsl.g:117:2: ( ( rule__ResourceType__Group__0 ) )
            // InternalMyDsl.g:118:3: ( rule__ResourceType__Group__0 )
            {
             before(grammarAccess.getResourceTypeAccess().getGroup()); 
            // InternalMyDsl.g:119:3: ( rule__ResourceType__Group__0 )
            // InternalMyDsl.g:119:4: rule__ResourceType__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceTypeAccess().getGroup()); 

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
    // $ANTLR end "ruleResourceType"


    // $ANTLR start "entryRuleResource"
    // InternalMyDsl.g:128:1: entryRuleResource : ruleResource EOF ;
    public final void entryRuleResource() throws RecognitionException {
        try {
            // InternalMyDsl.g:129:1: ( ruleResource EOF )
            // InternalMyDsl.g:130:1: ruleResource EOF
            {
             before(grammarAccess.getResourceRule()); 
            pushFollow(FOLLOW_1);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getResourceRule()); 
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
    // $ANTLR end "entryRuleResource"


    // $ANTLR start "ruleResource"
    // InternalMyDsl.g:137:1: ruleResource : ( ( rule__Resource__Group__0 ) ) ;
    public final void ruleResource() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:141:2: ( ( ( rule__Resource__Group__0 ) ) )
            // InternalMyDsl.g:142:2: ( ( rule__Resource__Group__0 ) )
            {
            // InternalMyDsl.g:142:2: ( ( rule__Resource__Group__0 ) )
            // InternalMyDsl.g:143:3: ( rule__Resource__Group__0 )
            {
             before(grammarAccess.getResourceAccess().getGroup()); 
            // InternalMyDsl.g:144:3: ( rule__Resource__Group__0 )
            // InternalMyDsl.g:144:4: rule__Resource__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Resource__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getGroup()); 

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
    // $ANTLR end "ruleResource"


    // $ANTLR start "entryRuleCurrentResourceValue"
    // InternalMyDsl.g:153:1: entryRuleCurrentResourceValue : ruleCurrentResourceValue EOF ;
    public final void entryRuleCurrentResourceValue() throws RecognitionException {
        try {
            // InternalMyDsl.g:154:1: ( ruleCurrentResourceValue EOF )
            // InternalMyDsl.g:155:1: ruleCurrentResourceValue EOF
            {
             before(grammarAccess.getCurrentResourceValueRule()); 
            pushFollow(FOLLOW_1);
            ruleCurrentResourceValue();

            state._fsp--;

             after(grammarAccess.getCurrentResourceValueRule()); 
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
    // $ANTLR end "entryRuleCurrentResourceValue"


    // $ANTLR start "ruleCurrentResourceValue"
    // InternalMyDsl.g:162:1: ruleCurrentResourceValue : ( ( rule__CurrentResourceValue__Group__0 ) ) ;
    public final void ruleCurrentResourceValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:166:2: ( ( ( rule__CurrentResourceValue__Group__0 ) ) )
            // InternalMyDsl.g:167:2: ( ( rule__CurrentResourceValue__Group__0 ) )
            {
            // InternalMyDsl.g:167:2: ( ( rule__CurrentResourceValue__Group__0 ) )
            // InternalMyDsl.g:168:3: ( rule__CurrentResourceValue__Group__0 )
            {
             before(grammarAccess.getCurrentResourceValueAccess().getGroup()); 
            // InternalMyDsl.g:169:3: ( rule__CurrentResourceValue__Group__0 )
            // InternalMyDsl.g:169:4: rule__CurrentResourceValue__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__CurrentResourceValue__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCurrentResourceValueAccess().getGroup()); 

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
    // $ANTLR end "ruleCurrentResourceValue"


    // $ANTLR start "entryRuleSoftwareModel"
    // InternalMyDsl.g:178:1: entryRuleSoftwareModel : ruleSoftwareModel EOF ;
    public final void entryRuleSoftwareModel() throws RecognitionException {
        try {
            // InternalMyDsl.g:179:1: ( ruleSoftwareModel EOF )
            // InternalMyDsl.g:180:1: ruleSoftwareModel EOF
            {
             before(grammarAccess.getSoftwareModelRule()); 
            pushFollow(FOLLOW_1);
            ruleSoftwareModel();

            state._fsp--;

             after(grammarAccess.getSoftwareModelRule()); 
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
    // $ANTLR end "entryRuleSoftwareModel"


    // $ANTLR start "ruleSoftwareModel"
    // InternalMyDsl.g:187:1: ruleSoftwareModel : ( ( rule__SoftwareModel__Group__0 ) ) ;
    public final void ruleSoftwareModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:191:2: ( ( ( rule__SoftwareModel__Group__0 ) ) )
            // InternalMyDsl.g:192:2: ( ( rule__SoftwareModel__Group__0 ) )
            {
            // InternalMyDsl.g:192:2: ( ( rule__SoftwareModel__Group__0 ) )
            // InternalMyDsl.g:193:3: ( rule__SoftwareModel__Group__0 )
            {
             before(grammarAccess.getSoftwareModelAccess().getGroup()); 
            // InternalMyDsl.g:194:3: ( rule__SoftwareModel__Group__0 )
            // InternalMyDsl.g:194:4: rule__SoftwareModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__SoftwareModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSoftwareModelAccess().getGroup()); 

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
    // $ANTLR end "ruleSoftwareModel"


    // $ANTLR start "entryRuleMetaParameter"
    // InternalMyDsl.g:203:1: entryRuleMetaParameter : ruleMetaParameter EOF ;
    public final void entryRuleMetaParameter() throws RecognitionException {
        try {
            // InternalMyDsl.g:204:1: ( ruleMetaParameter EOF )
            // InternalMyDsl.g:205:1: ruleMetaParameter EOF
            {
             before(grammarAccess.getMetaParameterRule()); 
            pushFollow(FOLLOW_1);
            ruleMetaParameter();

            state._fsp--;

             after(grammarAccess.getMetaParameterRule()); 
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
    // $ANTLR end "entryRuleMetaParameter"


    // $ANTLR start "ruleMetaParameter"
    // InternalMyDsl.g:212:1: ruleMetaParameter : ( ( rule__MetaParameter__Group__0 ) ) ;
    public final void ruleMetaParameter() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:216:2: ( ( ( rule__MetaParameter__Group__0 ) ) )
            // InternalMyDsl.g:217:2: ( ( rule__MetaParameter__Group__0 ) )
            {
            // InternalMyDsl.g:217:2: ( ( rule__MetaParameter__Group__0 ) )
            // InternalMyDsl.g:218:3: ( rule__MetaParameter__Group__0 )
            {
             before(grammarAccess.getMetaParameterAccess().getGroup()); 
            // InternalMyDsl.g:219:3: ( rule__MetaParameter__Group__0 )
            // InternalMyDsl.g:219:4: rule__MetaParameter__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameter__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMetaParameterAccess().getGroup()); 

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
    // $ANTLR end "ruleMetaParameter"


    // $ANTLR start "entryRuleProperty"
    // InternalMyDsl.g:228:1: entryRuleProperty : ruleProperty EOF ;
    public final void entryRuleProperty() throws RecognitionException {
        try {
            // InternalMyDsl.g:229:1: ( ruleProperty EOF )
            // InternalMyDsl.g:230:1: ruleProperty EOF
            {
             before(grammarAccess.getPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getPropertyRule()); 
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
    // $ANTLR end "entryRuleProperty"


    // $ANTLR start "ruleProperty"
    // InternalMyDsl.g:237:1: ruleProperty : ( ( rule__Property__Group__0 ) ) ;
    public final void ruleProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:241:2: ( ( ( rule__Property__Group__0 ) ) )
            // InternalMyDsl.g:242:2: ( ( rule__Property__Group__0 ) )
            {
            // InternalMyDsl.g:242:2: ( ( rule__Property__Group__0 ) )
            // InternalMyDsl.g:243:3: ( rule__Property__Group__0 )
            {
             before(grammarAccess.getPropertyAccess().getGroup()); 
            // InternalMyDsl.g:244:3: ( rule__Property__Group__0 )
            // InternalMyDsl.g:244:4: rule__Property__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Property__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getGroup()); 

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
    // $ANTLR end "ruleProperty"


    // $ANTLR start "entryRuleComponent"
    // InternalMyDsl.g:253:1: entryRuleComponent : ruleComponent EOF ;
    public final void entryRuleComponent() throws RecognitionException {
        try {
            // InternalMyDsl.g:254:1: ( ruleComponent EOF )
            // InternalMyDsl.g:255:1: ruleComponent EOF
            {
             before(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getComponentRule()); 
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
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalMyDsl.g:262:1: ruleComponent : ( ( rule__Component__Group__0 ) ) ;
    public final void ruleComponent() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:266:2: ( ( ( rule__Component__Group__0 ) ) )
            // InternalMyDsl.g:267:2: ( ( rule__Component__Group__0 ) )
            {
            // InternalMyDsl.g:267:2: ( ( rule__Component__Group__0 ) )
            // InternalMyDsl.g:268:3: ( rule__Component__Group__0 )
            {
             before(grammarAccess.getComponentAccess().getGroup()); 
            // InternalMyDsl.g:269:3: ( rule__Component__Group__0 )
            // InternalMyDsl.g:269:4: rule__Component__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Component__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getGroup()); 

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
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleImplementation"
    // InternalMyDsl.g:278:1: entryRuleImplementation : ruleImplementation EOF ;
    public final void entryRuleImplementation() throws RecognitionException {
        try {
            // InternalMyDsl.g:279:1: ( ruleImplementation EOF )
            // InternalMyDsl.g:280:1: ruleImplementation EOF
            {
             before(grammarAccess.getImplementationRule()); 
            pushFollow(FOLLOW_1);
            ruleImplementation();

            state._fsp--;

             after(grammarAccess.getImplementationRule()); 
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
    // $ANTLR end "entryRuleImplementation"


    // $ANTLR start "ruleImplementation"
    // InternalMyDsl.g:287:1: ruleImplementation : ( ( rule__Implementation__Group__0 ) ) ;
    public final void ruleImplementation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:291:2: ( ( ( rule__Implementation__Group__0 ) ) )
            // InternalMyDsl.g:292:2: ( ( rule__Implementation__Group__0 ) )
            {
            // InternalMyDsl.g:292:2: ( ( rule__Implementation__Group__0 ) )
            // InternalMyDsl.g:293:3: ( rule__Implementation__Group__0 )
            {
             before(grammarAccess.getImplementationAccess().getGroup()); 
            // InternalMyDsl.g:294:3: ( rule__Implementation__Group__0 )
            // InternalMyDsl.g:294:4: rule__Implementation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Implementation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImplementationAccess().getGroup()); 

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
    // $ANTLR end "ruleImplementation"


    // $ANTLR start "entryRuleInstance"
    // InternalMyDsl.g:303:1: entryRuleInstance : ruleInstance EOF ;
    public final void entryRuleInstance() throws RecognitionException {
        try {
            // InternalMyDsl.g:304:1: ( ruleInstance EOF )
            // InternalMyDsl.g:305:1: ruleInstance EOF
            {
             before(grammarAccess.getInstanceRule()); 
            pushFollow(FOLLOW_1);
            ruleInstance();

            state._fsp--;

             after(grammarAccess.getInstanceRule()); 
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
    // $ANTLR end "entryRuleInstance"


    // $ANTLR start "ruleInstance"
    // InternalMyDsl.g:312:1: ruleInstance : ( ( rule__Instance__NameAssignment ) ) ;
    public final void ruleInstance() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:316:2: ( ( ( rule__Instance__NameAssignment ) ) )
            // InternalMyDsl.g:317:2: ( ( rule__Instance__NameAssignment ) )
            {
            // InternalMyDsl.g:317:2: ( ( rule__Instance__NameAssignment ) )
            // InternalMyDsl.g:318:3: ( rule__Instance__NameAssignment )
            {
             before(grammarAccess.getInstanceAccess().getNameAssignment()); 
            // InternalMyDsl.g:319:3: ( rule__Instance__NameAssignment )
            // InternalMyDsl.g:319:4: rule__Instance__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Instance__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getInstanceAccess().getNameAssignment()); 

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
    // $ANTLR end "ruleInstance"


    // $ANTLR start "entryRuleComponentRequirement"
    // InternalMyDsl.g:328:1: entryRuleComponentRequirement : ruleComponentRequirement EOF ;
    public final void entryRuleComponentRequirement() throws RecognitionException {
        try {
            // InternalMyDsl.g:329:1: ( ruleComponentRequirement EOF )
            // InternalMyDsl.g:330:1: ruleComponentRequirement EOF
            {
             before(grammarAccess.getComponentRequirementRule()); 
            pushFollow(FOLLOW_1);
            ruleComponentRequirement();

            state._fsp--;

             after(grammarAccess.getComponentRequirementRule()); 
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
    // $ANTLR end "entryRuleComponentRequirement"


    // $ANTLR start "ruleComponentRequirement"
    // InternalMyDsl.g:337:1: ruleComponentRequirement : ( ( rule__ComponentRequirement__Group__0 ) ) ;
    public final void ruleComponentRequirement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:341:2: ( ( ( rule__ComponentRequirement__Group__0 ) ) )
            // InternalMyDsl.g:342:2: ( ( rule__ComponentRequirement__Group__0 ) )
            {
            // InternalMyDsl.g:342:2: ( ( rule__ComponentRequirement__Group__0 ) )
            // InternalMyDsl.g:343:3: ( rule__ComponentRequirement__Group__0 )
            {
             before(grammarAccess.getComponentRequirementAccess().getGroup()); 
            // InternalMyDsl.g:344:3: ( rule__ComponentRequirement__Group__0 )
            // InternalMyDsl.g:344:4: rule__ComponentRequirement__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponentRequirementAccess().getGroup()); 

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
    // $ANTLR end "ruleComponentRequirement"


    // $ANTLR start "entryRuleResourceRequirement"
    // InternalMyDsl.g:353:1: entryRuleResourceRequirement : ruleResourceRequirement EOF ;
    public final void entryRuleResourceRequirement() throws RecognitionException {
        try {
            // InternalMyDsl.g:354:1: ( ruleResourceRequirement EOF )
            // InternalMyDsl.g:355:1: ruleResourceRequirement EOF
            {
             before(grammarAccess.getResourceRequirementRule()); 
            pushFollow(FOLLOW_1);
            ruleResourceRequirement();

            state._fsp--;

             after(grammarAccess.getResourceRequirementRule()); 
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
    // $ANTLR end "entryRuleResourceRequirement"


    // $ANTLR start "ruleResourceRequirement"
    // InternalMyDsl.g:362:1: ruleResourceRequirement : ( ( rule__ResourceRequirement__Group__0 ) ) ;
    public final void ruleResourceRequirement() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:366:2: ( ( ( rule__ResourceRequirement__Group__0 ) ) )
            // InternalMyDsl.g:367:2: ( ( rule__ResourceRequirement__Group__0 ) )
            {
            // InternalMyDsl.g:367:2: ( ( rule__ResourceRequirement__Group__0 ) )
            // InternalMyDsl.g:368:3: ( rule__ResourceRequirement__Group__0 )
            {
             before(grammarAccess.getResourceRequirementAccess().getGroup()); 
            // InternalMyDsl.g:369:3: ( rule__ResourceRequirement__Group__0 )
            // InternalMyDsl.g:369:4: rule__ResourceRequirement__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceRequirementAccess().getGroup()); 

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
    // $ANTLR end "ruleResourceRequirement"


    // $ANTLR start "entryRuleClause"
    // InternalMyDsl.g:378:1: entryRuleClause : ruleClause EOF ;
    public final void entryRuleClause() throws RecognitionException {
        try {
            // InternalMyDsl.g:379:1: ( ruleClause EOF )
            // InternalMyDsl.g:380:1: ruleClause EOF
            {
             before(grammarAccess.getClauseRule()); 
            pushFollow(FOLLOW_1);
            ruleClause();

            state._fsp--;

             after(grammarAccess.getClauseRule()); 
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
    // $ANTLR end "entryRuleClause"


    // $ANTLR start "ruleClause"
    // InternalMyDsl.g:387:1: ruleClause : ( ( rule__Clause__Group__0 ) ) ;
    public final void ruleClause() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:391:2: ( ( ( rule__Clause__Group__0 ) ) )
            // InternalMyDsl.g:392:2: ( ( rule__Clause__Group__0 ) )
            {
            // InternalMyDsl.g:392:2: ( ( rule__Clause__Group__0 ) )
            // InternalMyDsl.g:393:3: ( rule__Clause__Group__0 )
            {
             before(grammarAccess.getClauseAccess().getGroup()); 
            // InternalMyDsl.g:394:3: ( rule__Clause__Group__0 )
            // InternalMyDsl.g:394:4: rule__Clause__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Clause__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getClauseAccess().getGroup()); 

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
    // $ANTLR end "ruleClause"


    // $ANTLR start "entryRuleRequest"
    // InternalMyDsl.g:403:1: entryRuleRequest : ruleRequest EOF ;
    public final void entryRuleRequest() throws RecognitionException {
        try {
            // InternalMyDsl.g:404:1: ( ruleRequest EOF )
            // InternalMyDsl.g:405:1: ruleRequest EOF
            {
             before(grammarAccess.getRequestRule()); 
            pushFollow(FOLLOW_1);
            ruleRequest();

            state._fsp--;

             after(grammarAccess.getRequestRule()); 
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
    // $ANTLR end "entryRuleRequest"


    // $ANTLR start "ruleRequest"
    // InternalMyDsl.g:412:1: ruleRequest : ( ( rule__Request__Group__0 ) ) ;
    public final void ruleRequest() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:416:2: ( ( ( rule__Request__Group__0 ) ) )
            // InternalMyDsl.g:417:2: ( ( rule__Request__Group__0 ) )
            {
            // InternalMyDsl.g:417:2: ( ( rule__Request__Group__0 ) )
            // InternalMyDsl.g:418:3: ( rule__Request__Group__0 )
            {
             before(grammarAccess.getRequestAccess().getGroup()); 
            // InternalMyDsl.g:419:3: ( rule__Request__Group__0 )
            // InternalMyDsl.g:419:4: rule__Request__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Request__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRequestAccess().getGroup()); 

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
    // $ANTLR end "ruleRequest"


    // $ANTLR start "entryRuleObjective"
    // InternalMyDsl.g:428:1: entryRuleObjective : ruleObjective EOF ;
    public final void entryRuleObjective() throws RecognitionException {
        try {
            // InternalMyDsl.g:429:1: ( ruleObjective EOF )
            // InternalMyDsl.g:430:1: ruleObjective EOF
            {
             before(grammarAccess.getObjectiveRule()); 
            pushFollow(FOLLOW_1);
            ruleObjective();

            state._fsp--;

             after(grammarAccess.getObjectiveRule()); 
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
    // $ANTLR end "entryRuleObjective"


    // $ANTLR start "ruleObjective"
    // InternalMyDsl.g:437:1: ruleObjective : ( ( rule__Objective__Group__0 ) ) ;
    public final void ruleObjective() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:441:2: ( ( ( rule__Objective__Group__0 ) ) )
            // InternalMyDsl.g:442:2: ( ( rule__Objective__Group__0 ) )
            {
            // InternalMyDsl.g:442:2: ( ( rule__Objective__Group__0 ) )
            // InternalMyDsl.g:443:3: ( rule__Objective__Group__0 )
            {
             before(grammarAccess.getObjectiveAccess().getGroup()); 
            // InternalMyDsl.g:444:3: ( rule__Objective__Group__0 )
            // InternalMyDsl.g:444:4: rule__Objective__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Objective__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveAccess().getGroup()); 

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
    // $ANTLR end "ruleObjective"


    // $ANTLR start "entryRuleExpression"
    // InternalMyDsl.g:453:1: entryRuleExpression : ruleExpression EOF ;
    public final void entryRuleExpression() throws RecognitionException {
        try {
            // InternalMyDsl.g:454:1: ( ruleExpression EOF )
            // InternalMyDsl.g:455:1: ruleExpression EOF
            {
             before(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getExpressionRule()); 
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
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalMyDsl.g:462:1: ruleExpression : ( ruleAddition ) ;
    public final void ruleExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:466:2: ( ( ruleAddition ) )
            // InternalMyDsl.g:467:2: ( ruleAddition )
            {
            // InternalMyDsl.g:467:2: ( ruleAddition )
            // InternalMyDsl.g:468:3: ruleAddition
            {
             before(grammarAccess.getExpressionAccess().getAdditionParserRuleCall()); 
            pushFollow(FOLLOW_2);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getExpressionAccess().getAdditionParserRuleCall()); 

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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleAddition"
    // InternalMyDsl.g:478:1: entryRuleAddition : ruleAddition EOF ;
    public final void entryRuleAddition() throws RecognitionException {
        try {
            // InternalMyDsl.g:479:1: ( ruleAddition EOF )
            // InternalMyDsl.g:480:1: ruleAddition EOF
            {
             before(grammarAccess.getAdditionRule()); 
            pushFollow(FOLLOW_1);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getAdditionRule()); 
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
    // $ANTLR end "entryRuleAddition"


    // $ANTLR start "ruleAddition"
    // InternalMyDsl.g:487:1: ruleAddition : ( ( rule__Addition__Group__0 ) ) ;
    public final void ruleAddition() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:491:2: ( ( ( rule__Addition__Group__0 ) ) )
            // InternalMyDsl.g:492:2: ( ( rule__Addition__Group__0 ) )
            {
            // InternalMyDsl.g:492:2: ( ( rule__Addition__Group__0 ) )
            // InternalMyDsl.g:493:3: ( rule__Addition__Group__0 )
            {
             before(grammarAccess.getAdditionAccess().getGroup()); 
            // InternalMyDsl.g:494:3: ( rule__Addition__Group__0 )
            // InternalMyDsl.g:494:4: rule__Addition__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getGroup()); 

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
    // $ANTLR end "ruleAddition"


    // $ANTLR start "entryRuleMultiplication"
    // InternalMyDsl.g:503:1: entryRuleMultiplication : ruleMultiplication EOF ;
    public final void entryRuleMultiplication() throws RecognitionException {
        try {
            // InternalMyDsl.g:504:1: ( ruleMultiplication EOF )
            // InternalMyDsl.g:505:1: ruleMultiplication EOF
            {
             before(grammarAccess.getMultiplicationRule()); 
            pushFollow(FOLLOW_1);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getMultiplicationRule()); 
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
    // $ANTLR end "entryRuleMultiplication"


    // $ANTLR start "ruleMultiplication"
    // InternalMyDsl.g:512:1: ruleMultiplication : ( ( rule__Multiplication__Group__0 ) ) ;
    public final void ruleMultiplication() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:516:2: ( ( ( rule__Multiplication__Group__0 ) ) )
            // InternalMyDsl.g:517:2: ( ( rule__Multiplication__Group__0 ) )
            {
            // InternalMyDsl.g:517:2: ( ( rule__Multiplication__Group__0 ) )
            // InternalMyDsl.g:518:3: ( rule__Multiplication__Group__0 )
            {
             before(grammarAccess.getMultiplicationAccess().getGroup()); 
            // InternalMyDsl.g:519:3: ( rule__Multiplication__Group__0 )
            // InternalMyDsl.g:519:4: rule__Multiplication__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getGroup()); 

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
    // $ANTLR end "ruleMultiplication"


    // $ANTLR start "entryRulePower"
    // InternalMyDsl.g:528:1: entryRulePower : rulePower EOF ;
    public final void entryRulePower() throws RecognitionException {
        try {
            // InternalMyDsl.g:529:1: ( rulePower EOF )
            // InternalMyDsl.g:530:1: rulePower EOF
            {
             before(grammarAccess.getPowerRule()); 
            pushFollow(FOLLOW_1);
            rulePower();

            state._fsp--;

             after(grammarAccess.getPowerRule()); 
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
    // $ANTLR end "entryRulePower"


    // $ANTLR start "rulePower"
    // InternalMyDsl.g:537:1: rulePower : ( ( rule__Power__Group__0 ) ) ;
    public final void rulePower() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:541:2: ( ( ( rule__Power__Group__0 ) ) )
            // InternalMyDsl.g:542:2: ( ( rule__Power__Group__0 ) )
            {
            // InternalMyDsl.g:542:2: ( ( rule__Power__Group__0 ) )
            // InternalMyDsl.g:543:3: ( rule__Power__Group__0 )
            {
             before(grammarAccess.getPowerAccess().getGroup()); 
            // InternalMyDsl.g:544:3: ( rule__Power__Group__0 )
            // InternalMyDsl.g:544:4: rule__Power__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Power__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPowerAccess().getGroup()); 

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
    // $ANTLR end "rulePower"


    // $ANTLR start "entryRulePrimaryExpression"
    // InternalMyDsl.g:553:1: entryRulePrimaryExpression : rulePrimaryExpression EOF ;
    public final void entryRulePrimaryExpression() throws RecognitionException {
        try {
            // InternalMyDsl.g:554:1: ( rulePrimaryExpression EOF )
            // InternalMyDsl.g:555:1: rulePrimaryExpression EOF
            {
             before(grammarAccess.getPrimaryExpressionRule()); 
            pushFollow(FOLLOW_1);
            rulePrimaryExpression();

            state._fsp--;

             after(grammarAccess.getPrimaryExpressionRule()); 
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
    // $ANTLR end "entryRulePrimaryExpression"


    // $ANTLR start "rulePrimaryExpression"
    // InternalMyDsl.g:562:1: rulePrimaryExpression : ( ( rule__PrimaryExpression__Alternatives ) ) ;
    public final void rulePrimaryExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:566:2: ( ( ( rule__PrimaryExpression__Alternatives ) ) )
            // InternalMyDsl.g:567:2: ( ( rule__PrimaryExpression__Alternatives ) )
            {
            // InternalMyDsl.g:567:2: ( ( rule__PrimaryExpression__Alternatives ) )
            // InternalMyDsl.g:568:3: ( rule__PrimaryExpression__Alternatives )
            {
             before(grammarAccess.getPrimaryExpressionAccess().getAlternatives()); 
            // InternalMyDsl.g:569:3: ( rule__PrimaryExpression__Alternatives )
            // InternalMyDsl.g:569:4: rule__PrimaryExpression__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__PrimaryExpression__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPrimaryExpressionAccess().getAlternatives()); 

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
    // $ANTLR end "rulePrimaryExpression"


    // $ANTLR start "entryRuleDesignator"
    // InternalMyDsl.g:578:1: entryRuleDesignator : ruleDesignator EOF ;
    public final void entryRuleDesignator() throws RecognitionException {
        try {
            // InternalMyDsl.g:579:1: ( ruleDesignator EOF )
            // InternalMyDsl.g:580:1: ruleDesignator EOF
            {
             before(grammarAccess.getDesignatorRule()); 
            pushFollow(FOLLOW_1);
            ruleDesignator();

            state._fsp--;

             after(grammarAccess.getDesignatorRule()); 
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
    // $ANTLR end "entryRuleDesignator"


    // $ANTLR start "ruleDesignator"
    // InternalMyDsl.g:587:1: ruleDesignator : ( ( rule__Designator__Alternatives ) ) ;
    public final void ruleDesignator() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:591:2: ( ( ( rule__Designator__Alternatives ) ) )
            // InternalMyDsl.g:592:2: ( ( rule__Designator__Alternatives ) )
            {
            // InternalMyDsl.g:592:2: ( ( rule__Designator__Alternatives ) )
            // InternalMyDsl.g:593:3: ( rule__Designator__Alternatives )
            {
             before(grammarAccess.getDesignatorAccess().getAlternatives()); 
            // InternalMyDsl.g:594:3: ( rule__Designator__Alternatives )
            // InternalMyDsl.g:594:4: rule__Designator__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Designator__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getDesignatorAccess().getAlternatives()); 

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
    // $ANTLR end "ruleDesignator"


    // $ANTLR start "entryRulePropertyDesignator"
    // InternalMyDsl.g:603:1: entryRulePropertyDesignator : rulePropertyDesignator EOF ;
    public final void entryRulePropertyDesignator() throws RecognitionException {
        try {
            // InternalMyDsl.g:604:1: ( rulePropertyDesignator EOF )
            // InternalMyDsl.g:605:1: rulePropertyDesignator EOF
            {
             before(grammarAccess.getPropertyDesignatorRule()); 
            pushFollow(FOLLOW_1);
            rulePropertyDesignator();

            state._fsp--;

             after(grammarAccess.getPropertyDesignatorRule()); 
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
    // $ANTLR end "entryRulePropertyDesignator"


    // $ANTLR start "rulePropertyDesignator"
    // InternalMyDsl.g:612:1: rulePropertyDesignator : ( ( rule__PropertyDesignator__Group__0 ) ) ;
    public final void rulePropertyDesignator() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:616:2: ( ( ( rule__PropertyDesignator__Group__0 ) ) )
            // InternalMyDsl.g:617:2: ( ( rule__PropertyDesignator__Group__0 ) )
            {
            // InternalMyDsl.g:617:2: ( ( rule__PropertyDesignator__Group__0 ) )
            // InternalMyDsl.g:618:3: ( rule__PropertyDesignator__Group__0 )
            {
             before(grammarAccess.getPropertyDesignatorAccess().getGroup()); 
            // InternalMyDsl.g:619:3: ( rule__PropertyDesignator__Group__0 )
            // InternalMyDsl.g:619:4: rule__PropertyDesignator__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignator__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyDesignatorAccess().getGroup()); 

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
    // $ANTLR end "rulePropertyDesignator"


    // $ANTLR start "entryRulePropertyDesignatorWithOptionalInstance"
    // InternalMyDsl.g:628:1: entryRulePropertyDesignatorWithOptionalInstance : rulePropertyDesignatorWithOptionalInstance EOF ;
    public final void entryRulePropertyDesignatorWithOptionalInstance() throws RecognitionException {
        try {
            // InternalMyDsl.g:629:1: ( rulePropertyDesignatorWithOptionalInstance EOF )
            // InternalMyDsl.g:630:1: rulePropertyDesignatorWithOptionalInstance EOF
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceRule()); 
            pushFollow(FOLLOW_1);
            rulePropertyDesignatorWithOptionalInstance();

            state._fsp--;

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceRule()); 
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
    // $ANTLR end "entryRulePropertyDesignatorWithOptionalInstance"


    // $ANTLR start "rulePropertyDesignatorWithOptionalInstance"
    // InternalMyDsl.g:637:1: rulePropertyDesignatorWithOptionalInstance : ( ( rule__PropertyDesignatorWithOptionalInstance__Group__0 ) ) ;
    public final void rulePropertyDesignatorWithOptionalInstance() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:641:2: ( ( ( rule__PropertyDesignatorWithOptionalInstance__Group__0 ) ) )
            // InternalMyDsl.g:642:2: ( ( rule__PropertyDesignatorWithOptionalInstance__Group__0 ) )
            {
            // InternalMyDsl.g:642:2: ( ( rule__PropertyDesignatorWithOptionalInstance__Group__0 ) )
            // InternalMyDsl.g:643:3: ( rule__PropertyDesignatorWithOptionalInstance__Group__0 )
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getGroup()); 
            // InternalMyDsl.g:644:3: ( rule__PropertyDesignatorWithOptionalInstance__Group__0 )
            // InternalMyDsl.g:644:4: rule__PropertyDesignatorWithOptionalInstance__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getGroup()); 

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
    // $ANTLR end "rulePropertyDesignatorWithOptionalInstance"


    // $ANTLR start "entryRuleMetaParameterDesignator"
    // InternalMyDsl.g:653:1: entryRuleMetaParameterDesignator : ruleMetaParameterDesignator EOF ;
    public final void entryRuleMetaParameterDesignator() throws RecognitionException {
        try {
            // InternalMyDsl.g:654:1: ( ruleMetaParameterDesignator EOF )
            // InternalMyDsl.g:655:1: ruleMetaParameterDesignator EOF
            {
             before(grammarAccess.getMetaParameterDesignatorRule()); 
            pushFollow(FOLLOW_1);
            ruleMetaParameterDesignator();

            state._fsp--;

             after(grammarAccess.getMetaParameterDesignatorRule()); 
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
    // $ANTLR end "entryRuleMetaParameterDesignator"


    // $ANTLR start "ruleMetaParameterDesignator"
    // InternalMyDsl.g:662:1: ruleMetaParameterDesignator : ( ( rule__MetaParameterDesignator__MetaParameterAssignment ) ) ;
    public final void ruleMetaParameterDesignator() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:666:2: ( ( ( rule__MetaParameterDesignator__MetaParameterAssignment ) ) )
            // InternalMyDsl.g:667:2: ( ( rule__MetaParameterDesignator__MetaParameterAssignment ) )
            {
            // InternalMyDsl.g:667:2: ( ( rule__MetaParameterDesignator__MetaParameterAssignment ) )
            // InternalMyDsl.g:668:3: ( rule__MetaParameterDesignator__MetaParameterAssignment )
            {
             before(grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterAssignment()); 
            // InternalMyDsl.g:669:3: ( rule__MetaParameterDesignator__MetaParameterAssignment )
            // InternalMyDsl.g:669:4: rule__MetaParameterDesignator__MetaParameterAssignment
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameterDesignator__MetaParameterAssignment();

            state._fsp--;


            }

             after(grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterAssignment()); 

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
    // $ANTLR end "ruleMetaParameterDesignator"


    // $ANTLR start "entryRuleLiteralExpression"
    // InternalMyDsl.g:678:1: entryRuleLiteralExpression : ruleLiteralExpression EOF ;
    public final void entryRuleLiteralExpression() throws RecognitionException {
        try {
            // InternalMyDsl.g:679:1: ( ruleLiteralExpression EOF )
            // InternalMyDsl.g:680:1: ruleLiteralExpression EOF
            {
             before(grammarAccess.getLiteralExpressionRule()); 
            pushFollow(FOLLOW_1);
            ruleLiteralExpression();

            state._fsp--;

             after(grammarAccess.getLiteralExpressionRule()); 
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
    // $ANTLR end "entryRuleLiteralExpression"


    // $ANTLR start "ruleLiteralExpression"
    // InternalMyDsl.g:687:1: ruleLiteralExpression : ( ( rule__LiteralExpression__ValueAssignment ) ) ;
    public final void ruleLiteralExpression() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:691:2: ( ( ( rule__LiteralExpression__ValueAssignment ) ) )
            // InternalMyDsl.g:692:2: ( ( rule__LiteralExpression__ValueAssignment ) )
            {
            // InternalMyDsl.g:692:2: ( ( rule__LiteralExpression__ValueAssignment ) )
            // InternalMyDsl.g:693:3: ( rule__LiteralExpression__ValueAssignment )
            {
             before(grammarAccess.getLiteralExpressionAccess().getValueAssignment()); 
            // InternalMyDsl.g:694:3: ( rule__LiteralExpression__ValueAssignment )
            // InternalMyDsl.g:694:4: rule__LiteralExpression__ValueAssignment
            {
            pushFollow(FOLLOW_2);
            rule__LiteralExpression__ValueAssignment();

            state._fsp--;


            }

             after(grammarAccess.getLiteralExpressionAccess().getValueAssignment()); 

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
    // $ANTLR end "ruleLiteralExpression"


    // $ANTLR start "entryRuleMetaParameterAssignment"
    // InternalMyDsl.g:703:1: entryRuleMetaParameterAssignment : ruleMetaParameterAssignment EOF ;
    public final void entryRuleMetaParameterAssignment() throws RecognitionException {
        try {
            // InternalMyDsl.g:704:1: ( ruleMetaParameterAssignment EOF )
            // InternalMyDsl.g:705:1: ruleMetaParameterAssignment EOF
            {
             before(grammarAccess.getMetaParameterAssignmentRule()); 
            pushFollow(FOLLOW_1);
            ruleMetaParameterAssignment();

            state._fsp--;

             after(grammarAccess.getMetaParameterAssignmentRule()); 
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
    // $ANTLR end "entryRuleMetaParameterAssignment"


    // $ANTLR start "ruleMetaParameterAssignment"
    // InternalMyDsl.g:712:1: ruleMetaParameterAssignment : ( ( rule__MetaParameterAssignment__Group__0 ) ) ;
    public final void ruleMetaParameterAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:716:2: ( ( ( rule__MetaParameterAssignment__Group__0 ) ) )
            // InternalMyDsl.g:717:2: ( ( rule__MetaParameterAssignment__Group__0 ) )
            {
            // InternalMyDsl.g:717:2: ( ( rule__MetaParameterAssignment__Group__0 ) )
            // InternalMyDsl.g:718:3: ( rule__MetaParameterAssignment__Group__0 )
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getGroup()); 
            // InternalMyDsl.g:719:3: ( rule__MetaParameterAssignment__Group__0 )
            // InternalMyDsl.g:719:4: rule__MetaParameterAssignment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMetaParameterAssignmentAccess().getGroup()); 

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
    // $ANTLR end "ruleMetaParameterAssignment"


    // $ANTLR start "entryRuleSolution"
    // InternalMyDsl.g:728:1: entryRuleSolution : ruleSolution EOF ;
    public final void entryRuleSolution() throws RecognitionException {
        try {
            // InternalMyDsl.g:729:1: ( ruleSolution EOF )
            // InternalMyDsl.g:730:1: ruleSolution EOF
            {
             before(grammarAccess.getSolutionRule()); 
            pushFollow(FOLLOW_1);
            ruleSolution();

            state._fsp--;

             after(grammarAccess.getSolutionRule()); 
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
    // $ANTLR end "entryRuleSolution"


    // $ANTLR start "ruleSolution"
    // InternalMyDsl.g:737:1: ruleSolution : ( ( rule__Solution__Group__0 ) ) ;
    public final void ruleSolution() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:741:2: ( ( ( rule__Solution__Group__0 ) ) )
            // InternalMyDsl.g:742:2: ( ( rule__Solution__Group__0 ) )
            {
            // InternalMyDsl.g:742:2: ( ( rule__Solution__Group__0 ) )
            // InternalMyDsl.g:743:3: ( rule__Solution__Group__0 )
            {
             before(grammarAccess.getSolutionAccess().getGroup()); 
            // InternalMyDsl.g:744:3: ( rule__Solution__Group__0 )
            // InternalMyDsl.g:744:4: rule__Solution__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Solution__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getSolutionAccess().getGroup()); 

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
    // $ANTLR end "ruleSolution"


    // $ANTLR start "entryRuleAssignment"
    // InternalMyDsl.g:753:1: entryRuleAssignment : ruleAssignment EOF ;
    public final void entryRuleAssignment() throws RecognitionException {
        try {
            // InternalMyDsl.g:754:1: ( ruleAssignment EOF )
            // InternalMyDsl.g:755:1: ruleAssignment EOF
            {
             before(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getAssignmentRule()); 
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
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalMyDsl.g:762:1: ruleAssignment : ( ( rule__Assignment__Group__0 ) ) ;
    public final void ruleAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:766:2: ( ( ( rule__Assignment__Group__0 ) ) )
            // InternalMyDsl.g:767:2: ( ( rule__Assignment__Group__0 ) )
            {
            // InternalMyDsl.g:767:2: ( ( rule__Assignment__Group__0 ) )
            // InternalMyDsl.g:768:3: ( rule__Assignment__Group__0 )
            {
             before(grammarAccess.getAssignmentAccess().getGroup()); 
            // InternalMyDsl.g:769:3: ( rule__Assignment__Group__0 )
            // InternalMyDsl.g:769:4: rule__Assignment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getGroup()); 

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
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleResourceMapping"
    // InternalMyDsl.g:778:1: entryRuleResourceMapping : ruleResourceMapping EOF ;
    public final void entryRuleResourceMapping() throws RecognitionException {
        try {
            // InternalMyDsl.g:779:1: ( ruleResourceMapping EOF )
            // InternalMyDsl.g:780:1: ruleResourceMapping EOF
            {
             before(grammarAccess.getResourceMappingRule()); 
            pushFollow(FOLLOW_1);
            ruleResourceMapping();

            state._fsp--;

             after(grammarAccess.getResourceMappingRule()); 
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
    // $ANTLR end "entryRuleResourceMapping"


    // $ANTLR start "ruleResourceMapping"
    // InternalMyDsl.g:787:1: ruleResourceMapping : ( ( rule__ResourceMapping__Group__0 ) ) ;
    public final void ruleResourceMapping() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:791:2: ( ( ( rule__ResourceMapping__Group__0 ) ) )
            // InternalMyDsl.g:792:2: ( ( rule__ResourceMapping__Group__0 ) )
            {
            // InternalMyDsl.g:792:2: ( ( rule__ResourceMapping__Group__0 ) )
            // InternalMyDsl.g:793:3: ( rule__ResourceMapping__Group__0 )
            {
             before(grammarAccess.getResourceMappingAccess().getGroup()); 
            // InternalMyDsl.g:794:3: ( rule__ResourceMapping__Group__0 )
            // InternalMyDsl.g:794:4: rule__ResourceMapping__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceMappingAccess().getGroup()); 

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
    // $ANTLR end "ruleResourceMapping"


    // $ANTLR start "entryRuleComponentMapping"
    // InternalMyDsl.g:803:1: entryRuleComponentMapping : ruleComponentMapping EOF ;
    public final void entryRuleComponentMapping() throws RecognitionException {
        try {
            // InternalMyDsl.g:804:1: ( ruleComponentMapping EOF )
            // InternalMyDsl.g:805:1: ruleComponentMapping EOF
            {
             before(grammarAccess.getComponentMappingRule()); 
            pushFollow(FOLLOW_1);
            ruleComponentMapping();

            state._fsp--;

             after(grammarAccess.getComponentMappingRule()); 
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
    // $ANTLR end "entryRuleComponentMapping"


    // $ANTLR start "ruleComponentMapping"
    // InternalMyDsl.g:812:1: ruleComponentMapping : ( ( rule__ComponentMapping__Group__0 ) ) ;
    public final void ruleComponentMapping() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:816:2: ( ( ( rule__ComponentMapping__Group__0 ) ) )
            // InternalMyDsl.g:817:2: ( ( rule__ComponentMapping__Group__0 ) )
            {
            // InternalMyDsl.g:817:2: ( ( rule__ComponentMapping__Group__0 ) )
            // InternalMyDsl.g:818:3: ( rule__ComponentMapping__Group__0 )
            {
             before(grammarAccess.getComponentMappingAccess().getGroup()); 
            // InternalMyDsl.g:819:3: ( rule__ComponentMapping__Group__0 )
            // InternalMyDsl.g:819:4: rule__ComponentMapping__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ComponentMapping__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponentMappingAccess().getGroup()); 

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
    // $ANTLR end "ruleComponentMapping"


    // $ANTLR start "entryRuleDouble"
    // InternalMyDsl.g:828:1: entryRuleDouble : ruleDouble EOF ;
    public final void entryRuleDouble() throws RecognitionException {
        try {
            // InternalMyDsl.g:829:1: ( ruleDouble EOF )
            // InternalMyDsl.g:830:1: ruleDouble EOF
            {
             before(grammarAccess.getDoubleRule()); 
            pushFollow(FOLLOW_1);
            ruleDouble();

            state._fsp--;

             after(grammarAccess.getDoubleRule()); 
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
    // $ANTLR end "entryRuleDouble"


    // $ANTLR start "ruleDouble"
    // InternalMyDsl.g:837:1: ruleDouble : ( ( rule__Double__Group__0 ) ) ;
    public final void ruleDouble() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:841:2: ( ( ( rule__Double__Group__0 ) ) )
            // InternalMyDsl.g:842:2: ( ( rule__Double__Group__0 ) )
            {
            // InternalMyDsl.g:842:2: ( ( rule__Double__Group__0 ) )
            // InternalMyDsl.g:843:3: ( rule__Double__Group__0 )
            {
             before(grammarAccess.getDoubleAccess().getGroup()); 
            // InternalMyDsl.g:844:3: ( rule__Double__Group__0 )
            // InternalMyDsl.g:844:4: rule__Double__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Double__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDoubleAccess().getGroup()); 

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
    // $ANTLR end "ruleDouble"


    // $ANTLR start "ruleClauseType"
    // InternalMyDsl.g:853:1: ruleClauseType : ( ( rule__ClauseType__Alternatives ) ) ;
    public final void ruleClauseType() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:857:1: ( ( ( rule__ClauseType__Alternatives ) ) )
            // InternalMyDsl.g:858:2: ( ( rule__ClauseType__Alternatives ) )
            {
            // InternalMyDsl.g:858:2: ( ( rule__ClauseType__Alternatives ) )
            // InternalMyDsl.g:859:3: ( rule__ClauseType__Alternatives )
            {
             before(grammarAccess.getClauseTypeAccess().getAlternatives()); 
            // InternalMyDsl.g:860:3: ( rule__ClauseType__Alternatives )
            // InternalMyDsl.g:860:4: rule__ClauseType__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ClauseType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getClauseTypeAccess().getAlternatives()); 

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
    // $ANTLR end "ruleClauseType"


    // $ANTLR start "ruleClauseComparator"
    // InternalMyDsl.g:869:1: ruleClauseComparator : ( ( rule__ClauseComparator__Alternatives ) ) ;
    public final void ruleClauseComparator() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:873:1: ( ( ( rule__ClauseComparator__Alternatives ) ) )
            // InternalMyDsl.g:874:2: ( ( rule__ClauseComparator__Alternatives ) )
            {
            // InternalMyDsl.g:874:2: ( ( rule__ClauseComparator__Alternatives ) )
            // InternalMyDsl.g:875:3: ( rule__ClauseComparator__Alternatives )
            {
             before(grammarAccess.getClauseComparatorAccess().getAlternatives()); 
            // InternalMyDsl.g:876:3: ( rule__ClauseComparator__Alternatives )
            // InternalMyDsl.g:876:4: rule__ClauseComparator__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__ClauseComparator__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getClauseComparatorAccess().getAlternatives()); 

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
    // $ANTLR end "ruleClauseComparator"


    // $ANTLR start "rulePropertyAggregation"
    // InternalMyDsl.g:885:1: rulePropertyAggregation : ( ( rule__PropertyAggregation__Alternatives ) ) ;
    public final void rulePropertyAggregation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:889:1: ( ( ( rule__PropertyAggregation__Alternatives ) ) )
            // InternalMyDsl.g:890:2: ( ( rule__PropertyAggregation__Alternatives ) )
            {
            // InternalMyDsl.g:890:2: ( ( rule__PropertyAggregation__Alternatives ) )
            // InternalMyDsl.g:891:3: ( rule__PropertyAggregation__Alternatives )
            {
             before(grammarAccess.getPropertyAggregationAccess().getAlternatives()); 
            // InternalMyDsl.g:892:3: ( rule__PropertyAggregation__Alternatives )
            // InternalMyDsl.g:892:4: rule__PropertyAggregation__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__PropertyAggregation__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAggregationAccess().getAlternatives()); 

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
    // $ANTLR end "rulePropertyAggregation"


    // $ANTLR start "rule__SoftwareModel__Alternatives_0"
    // InternalMyDsl.g:900:1: rule__SoftwareModel__Alternatives_0 : ( ( ( rule__SoftwareModel__PropertiesAssignment_0_0 ) ) | ( ( rule__SoftwareModel__ParametersAssignment_0_1 ) ) );
    public final void rule__SoftwareModel__Alternatives_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:904:1: ( ( ( rule__SoftwareModel__PropertiesAssignment_0_0 ) ) | ( ( rule__SoftwareModel__ParametersAssignment_0_1 ) ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==27) ) {
                alt1=1;
            }
            else if ( (LA1_0==29) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalMyDsl.g:905:2: ( ( rule__SoftwareModel__PropertiesAssignment_0_0 ) )
                    {
                    // InternalMyDsl.g:905:2: ( ( rule__SoftwareModel__PropertiesAssignment_0_0 ) )
                    // InternalMyDsl.g:906:3: ( rule__SoftwareModel__PropertiesAssignment_0_0 )
                    {
                     before(grammarAccess.getSoftwareModelAccess().getPropertiesAssignment_0_0()); 
                    // InternalMyDsl.g:907:3: ( rule__SoftwareModel__PropertiesAssignment_0_0 )
                    // InternalMyDsl.g:907:4: rule__SoftwareModel__PropertiesAssignment_0_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__SoftwareModel__PropertiesAssignment_0_0();

                    state._fsp--;


                    }

                     after(grammarAccess.getSoftwareModelAccess().getPropertiesAssignment_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:911:2: ( ( rule__SoftwareModel__ParametersAssignment_0_1 ) )
                    {
                    // InternalMyDsl.g:911:2: ( ( rule__SoftwareModel__ParametersAssignment_0_1 ) )
                    // InternalMyDsl.g:912:3: ( rule__SoftwareModel__ParametersAssignment_0_1 )
                    {
                     before(grammarAccess.getSoftwareModelAccess().getParametersAssignment_0_1()); 
                    // InternalMyDsl.g:913:3: ( rule__SoftwareModel__ParametersAssignment_0_1 )
                    // InternalMyDsl.g:913:4: rule__SoftwareModel__ParametersAssignment_0_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__SoftwareModel__ParametersAssignment_0_1();

                    state._fsp--;


                    }

                     after(grammarAccess.getSoftwareModelAccess().getParametersAssignment_0_1()); 

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
    // $ANTLR end "rule__SoftwareModel__Alternatives_0"


    // $ANTLR start "rule__Addition__Alternatives_1_0"
    // InternalMyDsl.g:921:1: rule__Addition__Alternatives_1_0 : ( ( ( rule__Addition__Group_1_0_0__0 ) ) | ( ( rule__Addition__Group_1_0_1__0 ) ) );
    public final void rule__Addition__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:925:1: ( ( ( rule__Addition__Group_1_0_0__0 ) ) | ( ( rule__Addition__Group_1_0_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==41) ) {
                alt2=1;
            }
            else if ( (LA2_0==42) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalMyDsl.g:926:2: ( ( rule__Addition__Group_1_0_0__0 ) )
                    {
                    // InternalMyDsl.g:926:2: ( ( rule__Addition__Group_1_0_0__0 ) )
                    // InternalMyDsl.g:927:3: ( rule__Addition__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getAdditionAccess().getGroup_1_0_0()); 
                    // InternalMyDsl.g:928:3: ( rule__Addition__Group_1_0_0__0 )
                    // InternalMyDsl.g:928:4: rule__Addition__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Addition__Group_1_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAdditionAccess().getGroup_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:932:2: ( ( rule__Addition__Group_1_0_1__0 ) )
                    {
                    // InternalMyDsl.g:932:2: ( ( rule__Addition__Group_1_0_1__0 ) )
                    // InternalMyDsl.g:933:3: ( rule__Addition__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getAdditionAccess().getGroup_1_0_1()); 
                    // InternalMyDsl.g:934:3: ( rule__Addition__Group_1_0_1__0 )
                    // InternalMyDsl.g:934:4: rule__Addition__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Addition__Group_1_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getAdditionAccess().getGroup_1_0_1()); 

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
    // $ANTLR end "rule__Addition__Alternatives_1_0"


    // $ANTLR start "rule__Multiplication__Alternatives_1_0"
    // InternalMyDsl.g:942:1: rule__Multiplication__Alternatives_1_0 : ( ( ( rule__Multiplication__Group_1_0_0__0 ) ) | ( ( rule__Multiplication__Group_1_0_1__0 ) ) );
    public final void rule__Multiplication__Alternatives_1_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:946:1: ( ( ( rule__Multiplication__Group_1_0_0__0 ) ) | ( ( rule__Multiplication__Group_1_0_1__0 ) ) )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==43) ) {
                alt3=1;
            }
            else if ( (LA3_0==44) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalMyDsl.g:947:2: ( ( rule__Multiplication__Group_1_0_0__0 ) )
                    {
                    // InternalMyDsl.g:947:2: ( ( rule__Multiplication__Group_1_0_0__0 ) )
                    // InternalMyDsl.g:948:3: ( rule__Multiplication__Group_1_0_0__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); 
                    // InternalMyDsl.g:949:3: ( rule__Multiplication__Group_1_0_0__0 )
                    // InternalMyDsl.g:949:4: rule__Multiplication__Group_1_0_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Multiplication__Group_1_0_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMultiplicationAccess().getGroup_1_0_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:953:2: ( ( rule__Multiplication__Group_1_0_1__0 ) )
                    {
                    // InternalMyDsl.g:953:2: ( ( rule__Multiplication__Group_1_0_1__0 ) )
                    // InternalMyDsl.g:954:3: ( rule__Multiplication__Group_1_0_1__0 )
                    {
                     before(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); 
                    // InternalMyDsl.g:955:3: ( rule__Multiplication__Group_1_0_1__0 )
                    // InternalMyDsl.g:955:4: rule__Multiplication__Group_1_0_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Multiplication__Group_1_0_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getMultiplicationAccess().getGroup_1_0_1()); 

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
    // $ANTLR end "rule__Multiplication__Alternatives_1_0"


    // $ANTLR start "rule__PrimaryExpression__Alternatives"
    // InternalMyDsl.g:963:1: rule__PrimaryExpression__Alternatives : ( ( ( rule__PrimaryExpression__Group_0__0 ) ) | ( ruleDesignator ) | ( ruleLiteralExpression ) );
    public final void rule__PrimaryExpression__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:967:1: ( ( ( rule__PrimaryExpression__Group_0__0 ) ) | ( ruleDesignator ) | ( ruleLiteralExpression ) )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 39:
                {
                alt4=1;
                }
                break;
            case RULE_ID:
                {
                alt4=2;
                }
                break;
            case RULE_INT:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalMyDsl.g:968:2: ( ( rule__PrimaryExpression__Group_0__0 ) )
                    {
                    // InternalMyDsl.g:968:2: ( ( rule__PrimaryExpression__Group_0__0 ) )
                    // InternalMyDsl.g:969:3: ( rule__PrimaryExpression__Group_0__0 )
                    {
                     before(grammarAccess.getPrimaryExpressionAccess().getGroup_0()); 
                    // InternalMyDsl.g:970:3: ( rule__PrimaryExpression__Group_0__0 )
                    // InternalMyDsl.g:970:4: rule__PrimaryExpression__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__PrimaryExpression__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getPrimaryExpressionAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:974:2: ( ruleDesignator )
                    {
                    // InternalMyDsl.g:974:2: ( ruleDesignator )
                    // InternalMyDsl.g:975:3: ruleDesignator
                    {
                     before(grammarAccess.getPrimaryExpressionAccess().getDesignatorParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleDesignator();

                    state._fsp--;

                     after(grammarAccess.getPrimaryExpressionAccess().getDesignatorParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:980:2: ( ruleLiteralExpression )
                    {
                    // InternalMyDsl.g:980:2: ( ruleLiteralExpression )
                    // InternalMyDsl.g:981:3: ruleLiteralExpression
                    {
                     before(grammarAccess.getPrimaryExpressionAccess().getLiteralExpressionParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleLiteralExpression();

                    state._fsp--;

                     after(grammarAccess.getPrimaryExpressionAccess().getLiteralExpressionParserRuleCall_2()); 

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
    // $ANTLR end "rule__PrimaryExpression__Alternatives"


    // $ANTLR start "rule__Designator__Alternatives"
    // InternalMyDsl.g:990:1: rule__Designator__Alternatives : ( ( rulePropertyDesignator ) | ( ruleMetaParameterDesignator ) );
    public final void rule__Designator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:994:1: ( ( rulePropertyDesignator ) | ( ruleMetaParameterDesignator ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==EOF||(LA5_1>=12 && LA5_1<=13)||LA5_1==25||(LA5_1>=40 && LA5_1<=45)) ) {
                    alt5=2;
                }
                else if ( (LA5_1==46) ) {
                    alt5=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalMyDsl.g:995:2: ( rulePropertyDesignator )
                    {
                    // InternalMyDsl.g:995:2: ( rulePropertyDesignator )
                    // InternalMyDsl.g:996:3: rulePropertyDesignator
                    {
                     before(grammarAccess.getDesignatorAccess().getPropertyDesignatorParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    rulePropertyDesignator();

                    state._fsp--;

                     after(grammarAccess.getDesignatorAccess().getPropertyDesignatorParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1001:2: ( ruleMetaParameterDesignator )
                    {
                    // InternalMyDsl.g:1001:2: ( ruleMetaParameterDesignator )
                    // InternalMyDsl.g:1002:3: ruleMetaParameterDesignator
                    {
                     before(grammarAccess.getDesignatorAccess().getMetaParameterDesignatorParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleMetaParameterDesignator();

                    state._fsp--;

                     after(grammarAccess.getDesignatorAccess().getMetaParameterDesignatorParserRuleCall_1()); 

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
    // $ANTLR end "rule__Designator__Alternatives"


    // $ANTLR start "rule__ClauseType__Alternatives"
    // InternalMyDsl.g:1011:1: rule__ClauseType__Alternatives : ( ( ( 'requiring' ) ) | ( ( 'providing' ) ) );
    public final void rule__ClauseType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1015:1: ( ( ( 'requiring' ) ) | ( ( 'providing' ) ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==12) ) {
                alt6=1;
            }
            else if ( (LA6_0==13) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalMyDsl.g:1016:2: ( ( 'requiring' ) )
                    {
                    // InternalMyDsl.g:1016:2: ( ( 'requiring' ) )
                    // InternalMyDsl.g:1017:3: ( 'requiring' )
                    {
                     before(grammarAccess.getClauseTypeAccess().getRequiringEnumLiteralDeclaration_0()); 
                    // InternalMyDsl.g:1018:3: ( 'requiring' )
                    // InternalMyDsl.g:1018:4: 'requiring'
                    {
                    match(input,12,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseTypeAccess().getRequiringEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1022:2: ( ( 'providing' ) )
                    {
                    // InternalMyDsl.g:1022:2: ( ( 'providing' ) )
                    // InternalMyDsl.g:1023:3: ( 'providing' )
                    {
                     before(grammarAccess.getClauseTypeAccess().getProvidingEnumLiteralDeclaration_1()); 
                    // InternalMyDsl.g:1024:3: ( 'providing' )
                    // InternalMyDsl.g:1024:4: 'providing'
                    {
                    match(input,13,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseTypeAccess().getProvidingEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__ClauseType__Alternatives"


    // $ANTLR start "rule__ClauseComparator__Alternatives"
    // InternalMyDsl.g:1032:1: rule__ClauseComparator__Alternatives : ( ( ( '<' ) ) | ( ( '<=' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( '>=' ) ) | ( ( '>' ) ) );
    public final void rule__ClauseComparator__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1036:1: ( ( ( '<' ) ) | ( ( '<=' ) ) | ( ( '=' ) ) | ( ( '!=' ) ) | ( ( '>=' ) ) | ( ( '>' ) ) )
            int alt7=6;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt7=1;
                }
                break;
            case 15:
                {
                alt7=2;
                }
                break;
            case 16:
                {
                alt7=3;
                }
                break;
            case 17:
                {
                alt7=4;
                }
                break;
            case 18:
                {
                alt7=5;
                }
                break;
            case 19:
                {
                alt7=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // InternalMyDsl.g:1037:2: ( ( '<' ) )
                    {
                    // InternalMyDsl.g:1037:2: ( ( '<' ) )
                    // InternalMyDsl.g:1038:3: ( '<' )
                    {
                     before(grammarAccess.getClauseComparatorAccess().getLTEnumLiteralDeclaration_0()); 
                    // InternalMyDsl.g:1039:3: ( '<' )
                    // InternalMyDsl.g:1039:4: '<'
                    {
                    match(input,14,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseComparatorAccess().getLTEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1043:2: ( ( '<=' ) )
                    {
                    // InternalMyDsl.g:1043:2: ( ( '<=' ) )
                    // InternalMyDsl.g:1044:3: ( '<=' )
                    {
                     before(grammarAccess.getClauseComparatorAccess().getLEEnumLiteralDeclaration_1()); 
                    // InternalMyDsl.g:1045:3: ( '<=' )
                    // InternalMyDsl.g:1045:4: '<='
                    {
                    match(input,15,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseComparatorAccess().getLEEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:1049:2: ( ( '=' ) )
                    {
                    // InternalMyDsl.g:1049:2: ( ( '=' ) )
                    // InternalMyDsl.g:1050:3: ( '=' )
                    {
                     before(grammarAccess.getClauseComparatorAccess().getEQEnumLiteralDeclaration_2()); 
                    // InternalMyDsl.g:1051:3: ( '=' )
                    // InternalMyDsl.g:1051:4: '='
                    {
                    match(input,16,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseComparatorAccess().getEQEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:1055:2: ( ( '!=' ) )
                    {
                    // InternalMyDsl.g:1055:2: ( ( '!=' ) )
                    // InternalMyDsl.g:1056:3: ( '!=' )
                    {
                     before(grammarAccess.getClauseComparatorAccess().getNEEnumLiteralDeclaration_3()); 
                    // InternalMyDsl.g:1057:3: ( '!=' )
                    // InternalMyDsl.g:1057:4: '!='
                    {
                    match(input,17,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseComparatorAccess().getNEEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:1061:2: ( ( '>=' ) )
                    {
                    // InternalMyDsl.g:1061:2: ( ( '>=' ) )
                    // InternalMyDsl.g:1062:3: ( '>=' )
                    {
                     before(grammarAccess.getClauseComparatorAccess().getGEEnumLiteralDeclaration_4()); 
                    // InternalMyDsl.g:1063:3: ( '>=' )
                    // InternalMyDsl.g:1063:4: '>='
                    {
                    match(input,18,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseComparatorAccess().getGEEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:1067:2: ( ( '>' ) )
                    {
                    // InternalMyDsl.g:1067:2: ( ( '>' ) )
                    // InternalMyDsl.g:1068:3: ( '>' )
                    {
                     before(grammarAccess.getClauseComparatorAccess().getGTEnumLiteralDeclaration_5()); 
                    // InternalMyDsl.g:1069:3: ( '>' )
                    // InternalMyDsl.g:1069:4: '>'
                    {
                    match(input,19,FOLLOW_2); 

                    }

                     after(grammarAccess.getClauseComparatorAccess().getGTEnumLiteralDeclaration_5()); 

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
    // $ANTLR end "rule__ClauseComparator__Alternatives"


    // $ANTLR start "rule__PropertyAggregation__Alternatives"
    // InternalMyDsl.g:1077:1: rule__PropertyAggregation__Alternatives : ( ( ( 'sum' ) ) | ( ( 'max' ) ) );
    public final void rule__PropertyAggregation__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1081:1: ( ( ( 'sum' ) ) | ( ( 'max' ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==20) ) {
                alt8=1;
            }
            else if ( (LA8_0==21) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalMyDsl.g:1082:2: ( ( 'sum' ) )
                    {
                    // InternalMyDsl.g:1082:2: ( ( 'sum' ) )
                    // InternalMyDsl.g:1083:3: ( 'sum' )
                    {
                     before(grammarAccess.getPropertyAggregationAccess().getSumEnumLiteralDeclaration_0()); 
                    // InternalMyDsl.g:1084:3: ( 'sum' )
                    // InternalMyDsl.g:1084:4: 'sum'
                    {
                    match(input,20,FOLLOW_2); 

                    }

                     after(grammarAccess.getPropertyAggregationAccess().getSumEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1088:2: ( ( 'max' ) )
                    {
                    // InternalMyDsl.g:1088:2: ( ( 'max' ) )
                    // InternalMyDsl.g:1089:3: ( 'max' )
                    {
                     before(grammarAccess.getPropertyAggregationAccess().getMaxEnumLiteralDeclaration_1()); 
                    // InternalMyDsl.g:1090:3: ( 'max' )
                    // InternalMyDsl.g:1090:4: 'max'
                    {
                    match(input,21,FOLLOW_2); 

                    }

                     after(grammarAccess.getPropertyAggregationAccess().getMaxEnumLiteralDeclaration_1()); 

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
    // $ANTLR end "rule__PropertyAggregation__Alternatives"


    // $ANTLR start "rule__Model__Group__0"
    // InternalMyDsl.g:1098:1: rule__Model__Group__0 : rule__Model__Group__0__Impl rule__Model__Group__1 ;
    public final void rule__Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1102:1: ( rule__Model__Group__0__Impl rule__Model__Group__1 )
            // InternalMyDsl.g:1103:2: rule__Model__Group__0__Impl rule__Model__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__1();

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
    // $ANTLR end "rule__Model__Group__0"


    // $ANTLR start "rule__Model__Group__0__Impl"
    // InternalMyDsl.g:1110:1: rule__Model__Group__0__Impl : ( ( rule__Model__HardwareModelAssignment_0 ) ) ;
    public final void rule__Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1114:1: ( ( ( rule__Model__HardwareModelAssignment_0 ) ) )
            // InternalMyDsl.g:1115:1: ( ( rule__Model__HardwareModelAssignment_0 ) )
            {
            // InternalMyDsl.g:1115:1: ( ( rule__Model__HardwareModelAssignment_0 ) )
            // InternalMyDsl.g:1116:2: ( rule__Model__HardwareModelAssignment_0 )
            {
             before(grammarAccess.getModelAccess().getHardwareModelAssignment_0()); 
            // InternalMyDsl.g:1117:2: ( rule__Model__HardwareModelAssignment_0 )
            // InternalMyDsl.g:1117:3: rule__Model__HardwareModelAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Model__HardwareModelAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getHardwareModelAssignment_0()); 

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
    // $ANTLR end "rule__Model__Group__0__Impl"


    // $ANTLR start "rule__Model__Group__1"
    // InternalMyDsl.g:1125:1: rule__Model__Group__1 : rule__Model__Group__1__Impl rule__Model__Group__2 ;
    public final void rule__Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1129:1: ( rule__Model__Group__1__Impl rule__Model__Group__2 )
            // InternalMyDsl.g:1130:2: rule__Model__Group__1__Impl rule__Model__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Model__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__2();

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
    // $ANTLR end "rule__Model__Group__1"


    // $ANTLR start "rule__Model__Group__1__Impl"
    // InternalMyDsl.g:1137:1: rule__Model__Group__1__Impl : ( ( rule__Model__SoftwareModelAssignment_1 ) ) ;
    public final void rule__Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1141:1: ( ( ( rule__Model__SoftwareModelAssignment_1 ) ) )
            // InternalMyDsl.g:1142:1: ( ( rule__Model__SoftwareModelAssignment_1 ) )
            {
            // InternalMyDsl.g:1142:1: ( ( rule__Model__SoftwareModelAssignment_1 ) )
            // InternalMyDsl.g:1143:2: ( rule__Model__SoftwareModelAssignment_1 )
            {
             before(grammarAccess.getModelAccess().getSoftwareModelAssignment_1()); 
            // InternalMyDsl.g:1144:2: ( rule__Model__SoftwareModelAssignment_1 )
            // InternalMyDsl.g:1144:3: rule__Model__SoftwareModelAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Model__SoftwareModelAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getSoftwareModelAssignment_1()); 

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
    // $ANTLR end "rule__Model__Group__1__Impl"


    // $ANTLR start "rule__Model__Group__2"
    // InternalMyDsl.g:1152:1: rule__Model__Group__2 : rule__Model__Group__2__Impl rule__Model__Group__3 ;
    public final void rule__Model__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1156:1: ( rule__Model__Group__2__Impl rule__Model__Group__3 )
            // InternalMyDsl.g:1157:2: rule__Model__Group__2__Impl rule__Model__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Model__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__3();

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
    // $ANTLR end "rule__Model__Group__2"


    // $ANTLR start "rule__Model__Group__2__Impl"
    // InternalMyDsl.g:1164:1: rule__Model__Group__2__Impl : ( ( ( rule__Model__RequestsAssignment_2 ) ) ( ( rule__Model__RequestsAssignment_2 )* ) ) ;
    public final void rule__Model__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1168:1: ( ( ( ( rule__Model__RequestsAssignment_2 ) ) ( ( rule__Model__RequestsAssignment_2 )* ) ) )
            // InternalMyDsl.g:1169:1: ( ( ( rule__Model__RequestsAssignment_2 ) ) ( ( rule__Model__RequestsAssignment_2 )* ) )
            {
            // InternalMyDsl.g:1169:1: ( ( ( rule__Model__RequestsAssignment_2 ) ) ( ( rule__Model__RequestsAssignment_2 )* ) )
            // InternalMyDsl.g:1170:2: ( ( rule__Model__RequestsAssignment_2 ) ) ( ( rule__Model__RequestsAssignment_2 )* )
            {
            // InternalMyDsl.g:1170:2: ( ( rule__Model__RequestsAssignment_2 ) )
            // InternalMyDsl.g:1171:3: ( rule__Model__RequestsAssignment_2 )
            {
             before(grammarAccess.getModelAccess().getRequestsAssignment_2()); 
            // InternalMyDsl.g:1172:3: ( rule__Model__RequestsAssignment_2 )
            // InternalMyDsl.g:1172:4: rule__Model__RequestsAssignment_2
            {
            pushFollow(FOLLOW_6);
            rule__Model__RequestsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getRequestsAssignment_2()); 

            }

            // InternalMyDsl.g:1175:2: ( ( rule__Model__RequestsAssignment_2 )* )
            // InternalMyDsl.g:1176:3: ( rule__Model__RequestsAssignment_2 )*
            {
             before(grammarAccess.getModelAccess().getRequestsAssignment_2()); 
            // InternalMyDsl.g:1177:3: ( rule__Model__RequestsAssignment_2 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==36) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalMyDsl.g:1177:4: rule__Model__RequestsAssignment_2
            	    {
            	    pushFollow(FOLLOW_6);
            	    rule__Model__RequestsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getModelAccess().getRequestsAssignment_2()); 

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
    // $ANTLR end "rule__Model__Group__2__Impl"


    // $ANTLR start "rule__Model__Group__3"
    // InternalMyDsl.g:1186:1: rule__Model__Group__3 : rule__Model__Group__3__Impl rule__Model__Group__4 ;
    public final void rule__Model__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1190:1: ( rule__Model__Group__3__Impl rule__Model__Group__4 )
            // InternalMyDsl.g:1191:2: rule__Model__Group__3__Impl rule__Model__Group__4
            {
            pushFollow(FOLLOW_7);
            rule__Model__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Model__Group__4();

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
    // $ANTLR end "rule__Model__Group__3"


    // $ANTLR start "rule__Model__Group__3__Impl"
    // InternalMyDsl.g:1198:1: rule__Model__Group__3__Impl : ( ( rule__Model__ObjectiveAssignment_3 ) ) ;
    public final void rule__Model__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1202:1: ( ( ( rule__Model__ObjectiveAssignment_3 ) ) )
            // InternalMyDsl.g:1203:1: ( ( rule__Model__ObjectiveAssignment_3 ) )
            {
            // InternalMyDsl.g:1203:1: ( ( rule__Model__ObjectiveAssignment_3 ) )
            // InternalMyDsl.g:1204:2: ( rule__Model__ObjectiveAssignment_3 )
            {
             before(grammarAccess.getModelAccess().getObjectiveAssignment_3()); 
            // InternalMyDsl.g:1205:2: ( rule__Model__ObjectiveAssignment_3 )
            // InternalMyDsl.g:1205:3: rule__Model__ObjectiveAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Model__ObjectiveAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getObjectiveAssignment_3()); 

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
    // $ANTLR end "rule__Model__Group__3__Impl"


    // $ANTLR start "rule__Model__Group__4"
    // InternalMyDsl.g:1213:1: rule__Model__Group__4 : rule__Model__Group__4__Impl ;
    public final void rule__Model__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1217:1: ( rule__Model__Group__4__Impl )
            // InternalMyDsl.g:1218:2: rule__Model__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Model__Group__4__Impl();

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
    // $ANTLR end "rule__Model__Group__4"


    // $ANTLR start "rule__Model__Group__4__Impl"
    // InternalMyDsl.g:1224:1: rule__Model__Group__4__Impl : ( ( rule__Model__SolutionAssignment_4 )? ) ;
    public final void rule__Model__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1228:1: ( ( ( rule__Model__SolutionAssignment_4 )? ) )
            // InternalMyDsl.g:1229:1: ( ( rule__Model__SolutionAssignment_4 )? )
            {
            // InternalMyDsl.g:1229:1: ( ( rule__Model__SolutionAssignment_4 )? )
            // InternalMyDsl.g:1230:2: ( rule__Model__SolutionAssignment_4 )?
            {
             before(grammarAccess.getModelAccess().getSolutionAssignment_4()); 
            // InternalMyDsl.g:1231:2: ( rule__Model__SolutionAssignment_4 )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==47) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalMyDsl.g:1231:3: rule__Model__SolutionAssignment_4
                    {
                    pushFollow(FOLLOW_2);
                    rule__Model__SolutionAssignment_4();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getModelAccess().getSolutionAssignment_4()); 

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
    // $ANTLR end "rule__Model__Group__4__Impl"


    // $ANTLR start "rule__HardwareModel__Group__0"
    // InternalMyDsl.g:1240:1: rule__HardwareModel__Group__0 : rule__HardwareModel__Group__0__Impl rule__HardwareModel__Group__1 ;
    public final void rule__HardwareModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1244:1: ( rule__HardwareModel__Group__0__Impl rule__HardwareModel__Group__1 )
            // InternalMyDsl.g:1245:2: rule__HardwareModel__Group__0__Impl rule__HardwareModel__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__HardwareModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__HardwareModel__Group__1();

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
    // $ANTLR end "rule__HardwareModel__Group__0"


    // $ANTLR start "rule__HardwareModel__Group__0__Impl"
    // InternalMyDsl.g:1252:1: rule__HardwareModel__Group__0__Impl : ( ( rule__HardwareModel__PropertiesAssignment_0 )* ) ;
    public final void rule__HardwareModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1256:1: ( ( ( rule__HardwareModel__PropertiesAssignment_0 )* ) )
            // InternalMyDsl.g:1257:1: ( ( rule__HardwareModel__PropertiesAssignment_0 )* )
            {
            // InternalMyDsl.g:1257:1: ( ( rule__HardwareModel__PropertiesAssignment_0 )* )
            // InternalMyDsl.g:1258:2: ( rule__HardwareModel__PropertiesAssignment_0 )*
            {
             before(grammarAccess.getHardwareModelAccess().getPropertiesAssignment_0()); 
            // InternalMyDsl.g:1259:2: ( rule__HardwareModel__PropertiesAssignment_0 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==27) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalMyDsl.g:1259:3: rule__HardwareModel__PropertiesAssignment_0
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__HardwareModel__PropertiesAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getHardwareModelAccess().getPropertiesAssignment_0()); 

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
    // $ANTLR end "rule__HardwareModel__Group__0__Impl"


    // $ANTLR start "rule__HardwareModel__Group__1"
    // InternalMyDsl.g:1267:1: rule__HardwareModel__Group__1 : rule__HardwareModel__Group__1__Impl rule__HardwareModel__Group__2 ;
    public final void rule__HardwareModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1271:1: ( rule__HardwareModel__Group__1__Impl rule__HardwareModel__Group__2 )
            // InternalMyDsl.g:1272:2: rule__HardwareModel__Group__1__Impl rule__HardwareModel__Group__2
            {
            pushFollow(FOLLOW_10);
            rule__HardwareModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__HardwareModel__Group__2();

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
    // $ANTLR end "rule__HardwareModel__Group__1"


    // $ANTLR start "rule__HardwareModel__Group__1__Impl"
    // InternalMyDsl.g:1279:1: rule__HardwareModel__Group__1__Impl : ( ( ( rule__HardwareModel__ResourceTypesAssignment_1 ) ) ( ( rule__HardwareModel__ResourceTypesAssignment_1 )* ) ) ;
    public final void rule__HardwareModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1283:1: ( ( ( ( rule__HardwareModel__ResourceTypesAssignment_1 ) ) ( ( rule__HardwareModel__ResourceTypesAssignment_1 )* ) ) )
            // InternalMyDsl.g:1284:1: ( ( ( rule__HardwareModel__ResourceTypesAssignment_1 ) ) ( ( rule__HardwareModel__ResourceTypesAssignment_1 )* ) )
            {
            // InternalMyDsl.g:1284:1: ( ( ( rule__HardwareModel__ResourceTypesAssignment_1 ) ) ( ( rule__HardwareModel__ResourceTypesAssignment_1 )* ) )
            // InternalMyDsl.g:1285:2: ( ( rule__HardwareModel__ResourceTypesAssignment_1 ) ) ( ( rule__HardwareModel__ResourceTypesAssignment_1 )* )
            {
            // InternalMyDsl.g:1285:2: ( ( rule__HardwareModel__ResourceTypesAssignment_1 ) )
            // InternalMyDsl.g:1286:3: ( rule__HardwareModel__ResourceTypesAssignment_1 )
            {
             before(grammarAccess.getHardwareModelAccess().getResourceTypesAssignment_1()); 
            // InternalMyDsl.g:1287:3: ( rule__HardwareModel__ResourceTypesAssignment_1 )
            // InternalMyDsl.g:1287:4: rule__HardwareModel__ResourceTypesAssignment_1
            {
            pushFollow(FOLLOW_11);
            rule__HardwareModel__ResourceTypesAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getHardwareModelAccess().getResourceTypesAssignment_1()); 

            }

            // InternalMyDsl.g:1290:2: ( ( rule__HardwareModel__ResourceTypesAssignment_1 )* )
            // InternalMyDsl.g:1291:3: ( rule__HardwareModel__ResourceTypesAssignment_1 )*
            {
             before(grammarAccess.getHardwareModelAccess().getResourceTypesAssignment_1()); 
            // InternalMyDsl.g:1292:3: ( rule__HardwareModel__ResourceTypesAssignment_1 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==22) ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1==23) ) {
                        alt12=1;
                    }


                }
                else if ( (LA12_0==50) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMyDsl.g:1292:4: rule__HardwareModel__ResourceTypesAssignment_1
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__HardwareModel__ResourceTypesAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getHardwareModelAccess().getResourceTypesAssignment_1()); 

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
    // $ANTLR end "rule__HardwareModel__Group__1__Impl"


    // $ANTLR start "rule__HardwareModel__Group__2"
    // InternalMyDsl.g:1301:1: rule__HardwareModel__Group__2 : rule__HardwareModel__Group__2__Impl ;
    public final void rule__HardwareModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1305:1: ( rule__HardwareModel__Group__2__Impl )
            // InternalMyDsl.g:1306:2: rule__HardwareModel__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__HardwareModel__Group__2__Impl();

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
    // $ANTLR end "rule__HardwareModel__Group__2"


    // $ANTLR start "rule__HardwareModel__Group__2__Impl"
    // InternalMyDsl.g:1312:1: rule__HardwareModel__Group__2__Impl : ( ( ( rule__HardwareModel__ResourcesAssignment_2 ) ) ( ( rule__HardwareModel__ResourcesAssignment_2 )* ) ) ;
    public final void rule__HardwareModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1316:1: ( ( ( ( rule__HardwareModel__ResourcesAssignment_2 ) ) ( ( rule__HardwareModel__ResourcesAssignment_2 )* ) ) )
            // InternalMyDsl.g:1317:1: ( ( ( rule__HardwareModel__ResourcesAssignment_2 ) ) ( ( rule__HardwareModel__ResourcesAssignment_2 )* ) )
            {
            // InternalMyDsl.g:1317:1: ( ( ( rule__HardwareModel__ResourcesAssignment_2 ) ) ( ( rule__HardwareModel__ResourcesAssignment_2 )* ) )
            // InternalMyDsl.g:1318:2: ( ( rule__HardwareModel__ResourcesAssignment_2 ) ) ( ( rule__HardwareModel__ResourcesAssignment_2 )* )
            {
            // InternalMyDsl.g:1318:2: ( ( rule__HardwareModel__ResourcesAssignment_2 ) )
            // InternalMyDsl.g:1319:3: ( rule__HardwareModel__ResourcesAssignment_2 )
            {
             before(grammarAccess.getHardwareModelAccess().getResourcesAssignment_2()); 
            // InternalMyDsl.g:1320:3: ( rule__HardwareModel__ResourcesAssignment_2 )
            // InternalMyDsl.g:1320:4: rule__HardwareModel__ResourcesAssignment_2
            {
            pushFollow(FOLLOW_12);
            rule__HardwareModel__ResourcesAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getHardwareModelAccess().getResourcesAssignment_2()); 

            }

            // InternalMyDsl.g:1323:2: ( ( rule__HardwareModel__ResourcesAssignment_2 )* )
            // InternalMyDsl.g:1324:3: ( rule__HardwareModel__ResourcesAssignment_2 )*
            {
             before(grammarAccess.getHardwareModelAccess().getResourcesAssignment_2()); 
            // InternalMyDsl.g:1325:3: ( rule__HardwareModel__ResourcesAssignment_2 )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==22) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalMyDsl.g:1325:4: rule__HardwareModel__ResourcesAssignment_2
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__HardwareModel__ResourcesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

             after(grammarAccess.getHardwareModelAccess().getResourcesAssignment_2()); 

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
    // $ANTLR end "rule__HardwareModel__Group__2__Impl"


    // $ANTLR start "rule__ResourceType__Group__0"
    // InternalMyDsl.g:1335:1: rule__ResourceType__Group__0 : rule__ResourceType__Group__0__Impl rule__ResourceType__Group__1 ;
    public final void rule__ResourceType__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1339:1: ( rule__ResourceType__Group__0__Impl rule__ResourceType__Group__1 )
            // InternalMyDsl.g:1340:2: rule__ResourceType__Group__0__Impl rule__ResourceType__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__ResourceType__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__1();

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
    // $ANTLR end "rule__ResourceType__Group__0"


    // $ANTLR start "rule__ResourceType__Group__0__Impl"
    // InternalMyDsl.g:1347:1: rule__ResourceType__Group__0__Impl : ( ( rule__ResourceType__ContainerAssignment_0 )? ) ;
    public final void rule__ResourceType__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1351:1: ( ( ( rule__ResourceType__ContainerAssignment_0 )? ) )
            // InternalMyDsl.g:1352:1: ( ( rule__ResourceType__ContainerAssignment_0 )? )
            {
            // InternalMyDsl.g:1352:1: ( ( rule__ResourceType__ContainerAssignment_0 )? )
            // InternalMyDsl.g:1353:2: ( rule__ResourceType__ContainerAssignment_0 )?
            {
             before(grammarAccess.getResourceTypeAccess().getContainerAssignment_0()); 
            // InternalMyDsl.g:1354:2: ( rule__ResourceType__ContainerAssignment_0 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==50) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalMyDsl.g:1354:3: rule__ResourceType__ContainerAssignment_0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResourceType__ContainerAssignment_0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getResourceTypeAccess().getContainerAssignment_0()); 

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
    // $ANTLR end "rule__ResourceType__Group__0__Impl"


    // $ANTLR start "rule__ResourceType__Group__1"
    // InternalMyDsl.g:1362:1: rule__ResourceType__Group__1 : rule__ResourceType__Group__1__Impl rule__ResourceType__Group__2 ;
    public final void rule__ResourceType__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1366:1: ( rule__ResourceType__Group__1__Impl rule__ResourceType__Group__2 )
            // InternalMyDsl.g:1367:2: rule__ResourceType__Group__1__Impl rule__ResourceType__Group__2
            {
            pushFollow(FOLLOW_13);
            rule__ResourceType__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__2();

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
    // $ANTLR end "rule__ResourceType__Group__1"


    // $ANTLR start "rule__ResourceType__Group__1__Impl"
    // InternalMyDsl.g:1374:1: rule__ResourceType__Group__1__Impl : ( 'resource' ) ;
    public final void rule__ResourceType__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1378:1: ( ( 'resource' ) )
            // InternalMyDsl.g:1379:1: ( 'resource' )
            {
            // InternalMyDsl.g:1379:1: ( 'resource' )
            // InternalMyDsl.g:1380:2: 'resource'
            {
             before(grammarAccess.getResourceTypeAccess().getResourceKeyword_1()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getResourceKeyword_1()); 

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
    // $ANTLR end "rule__ResourceType__Group__1__Impl"


    // $ANTLR start "rule__ResourceType__Group__2"
    // InternalMyDsl.g:1389:1: rule__ResourceType__Group__2 : rule__ResourceType__Group__2__Impl rule__ResourceType__Group__3 ;
    public final void rule__ResourceType__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1393:1: ( rule__ResourceType__Group__2__Impl rule__ResourceType__Group__3 )
            // InternalMyDsl.g:1394:2: rule__ResourceType__Group__2__Impl rule__ResourceType__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__ResourceType__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__3();

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
    // $ANTLR end "rule__ResourceType__Group__2"


    // $ANTLR start "rule__ResourceType__Group__2__Impl"
    // InternalMyDsl.g:1401:1: rule__ResourceType__Group__2__Impl : ( 'type' ) ;
    public final void rule__ResourceType__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1405:1: ( ( 'type' ) )
            // InternalMyDsl.g:1406:1: ( 'type' )
            {
            // InternalMyDsl.g:1406:1: ( 'type' )
            // InternalMyDsl.g:1407:2: 'type'
            {
             before(grammarAccess.getResourceTypeAccess().getTypeKeyword_2()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getTypeKeyword_2()); 

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
    // $ANTLR end "rule__ResourceType__Group__2__Impl"


    // $ANTLR start "rule__ResourceType__Group__3"
    // InternalMyDsl.g:1416:1: rule__ResourceType__Group__3 : rule__ResourceType__Group__3__Impl rule__ResourceType__Group__4 ;
    public final void rule__ResourceType__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1420:1: ( rule__ResourceType__Group__3__Impl rule__ResourceType__Group__4 )
            // InternalMyDsl.g:1421:2: rule__ResourceType__Group__3__Impl rule__ResourceType__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__ResourceType__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__4();

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
    // $ANTLR end "rule__ResourceType__Group__3"


    // $ANTLR start "rule__ResourceType__Group__3__Impl"
    // InternalMyDsl.g:1428:1: rule__ResourceType__Group__3__Impl : ( ( rule__ResourceType__NameAssignment_3 ) ) ;
    public final void rule__ResourceType__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1432:1: ( ( ( rule__ResourceType__NameAssignment_3 ) ) )
            // InternalMyDsl.g:1433:1: ( ( rule__ResourceType__NameAssignment_3 ) )
            {
            // InternalMyDsl.g:1433:1: ( ( rule__ResourceType__NameAssignment_3 ) )
            // InternalMyDsl.g:1434:2: ( rule__ResourceType__NameAssignment_3 )
            {
             before(grammarAccess.getResourceTypeAccess().getNameAssignment_3()); 
            // InternalMyDsl.g:1435:2: ( rule__ResourceType__NameAssignment_3 )
            // InternalMyDsl.g:1435:3: rule__ResourceType__NameAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__ResourceType__NameAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getResourceTypeAccess().getNameAssignment_3()); 

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
    // $ANTLR end "rule__ResourceType__Group__3__Impl"


    // $ANTLR start "rule__ResourceType__Group__4"
    // InternalMyDsl.g:1443:1: rule__ResourceType__Group__4 : rule__ResourceType__Group__4__Impl rule__ResourceType__Group__5 ;
    public final void rule__ResourceType__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1447:1: ( rule__ResourceType__Group__4__Impl rule__ResourceType__Group__5 )
            // InternalMyDsl.g:1448:2: rule__ResourceType__Group__4__Impl rule__ResourceType__Group__5
            {
            pushFollow(FOLLOW_16);
            rule__ResourceType__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__5();

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
    // $ANTLR end "rule__ResourceType__Group__4"


    // $ANTLR start "rule__ResourceType__Group__4__Impl"
    // InternalMyDsl.g:1455:1: rule__ResourceType__Group__4__Impl : ( '{' ) ;
    public final void rule__ResourceType__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1459:1: ( ( '{' ) )
            // InternalMyDsl.g:1460:1: ( '{' )
            {
            // InternalMyDsl.g:1460:1: ( '{' )
            // InternalMyDsl.g:1461:2: '{'
            {
             before(grammarAccess.getResourceTypeAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getLeftCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__ResourceType__Group__4__Impl"


    // $ANTLR start "rule__ResourceType__Group__5"
    // InternalMyDsl.g:1470:1: rule__ResourceType__Group__5 : rule__ResourceType__Group__5__Impl rule__ResourceType__Group__6 ;
    public final void rule__ResourceType__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1474:1: ( rule__ResourceType__Group__5__Impl rule__ResourceType__Group__6 )
            // InternalMyDsl.g:1475:2: rule__ResourceType__Group__5__Impl rule__ResourceType__Group__6
            {
            pushFollow(FOLLOW_16);
            rule__ResourceType__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__6();

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
    // $ANTLR end "rule__ResourceType__Group__5"


    // $ANTLR start "rule__ResourceType__Group__5__Impl"
    // InternalMyDsl.g:1482:1: rule__ResourceType__Group__5__Impl : ( ( rule__ResourceType__ResourceTypesAssignment_5 )* ) ;
    public final void rule__ResourceType__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1486:1: ( ( ( rule__ResourceType__ResourceTypesAssignment_5 )* ) )
            // InternalMyDsl.g:1487:1: ( ( rule__ResourceType__ResourceTypesAssignment_5 )* )
            {
            // InternalMyDsl.g:1487:1: ( ( rule__ResourceType__ResourceTypesAssignment_5 )* )
            // InternalMyDsl.g:1488:2: ( rule__ResourceType__ResourceTypesAssignment_5 )*
            {
             before(grammarAccess.getResourceTypeAccess().getResourceTypesAssignment_5()); 
            // InternalMyDsl.g:1489:2: ( rule__ResourceType__ResourceTypesAssignment_5 )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==22||LA15_0==50) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalMyDsl.g:1489:3: rule__ResourceType__ResourceTypesAssignment_5
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__ResourceType__ResourceTypesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

             after(grammarAccess.getResourceTypeAccess().getResourceTypesAssignment_5()); 

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
    // $ANTLR end "rule__ResourceType__Group__5__Impl"


    // $ANTLR start "rule__ResourceType__Group__6"
    // InternalMyDsl.g:1497:1: rule__ResourceType__Group__6 : rule__ResourceType__Group__6__Impl rule__ResourceType__Group__7 ;
    public final void rule__ResourceType__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1501:1: ( rule__ResourceType__Group__6__Impl rule__ResourceType__Group__7 )
            // InternalMyDsl.g:1502:2: rule__ResourceType__Group__6__Impl rule__ResourceType__Group__7
            {
            pushFollow(FOLLOW_16);
            rule__ResourceType__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__7();

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
    // $ANTLR end "rule__ResourceType__Group__6"


    // $ANTLR start "rule__ResourceType__Group__6__Impl"
    // InternalMyDsl.g:1509:1: rule__ResourceType__Group__6__Impl : ( ( rule__ResourceType__PropertiesAssignment_6 )* ) ;
    public final void rule__ResourceType__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1513:1: ( ( ( rule__ResourceType__PropertiesAssignment_6 )* ) )
            // InternalMyDsl.g:1514:1: ( ( rule__ResourceType__PropertiesAssignment_6 )* )
            {
            // InternalMyDsl.g:1514:1: ( ( rule__ResourceType__PropertiesAssignment_6 )* )
            // InternalMyDsl.g:1515:2: ( rule__ResourceType__PropertiesAssignment_6 )*
            {
             before(grammarAccess.getResourceTypeAccess().getPropertiesAssignment_6()); 
            // InternalMyDsl.g:1516:2: ( rule__ResourceType__PropertiesAssignment_6 )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==27) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalMyDsl.g:1516:3: rule__ResourceType__PropertiesAssignment_6
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__ResourceType__PropertiesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

             after(grammarAccess.getResourceTypeAccess().getPropertiesAssignment_6()); 

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
    // $ANTLR end "rule__ResourceType__Group__6__Impl"


    // $ANTLR start "rule__ResourceType__Group__7"
    // InternalMyDsl.g:1524:1: rule__ResourceType__Group__7 : rule__ResourceType__Group__7__Impl rule__ResourceType__Group__8 ;
    public final void rule__ResourceType__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1528:1: ( rule__ResourceType__Group__7__Impl rule__ResourceType__Group__8 )
            // InternalMyDsl.g:1529:2: rule__ResourceType__Group__7__Impl rule__ResourceType__Group__8
            {
            pushFollow(FOLLOW_16);
            rule__ResourceType__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__8();

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
    // $ANTLR end "rule__ResourceType__Group__7"


    // $ANTLR start "rule__ResourceType__Group__7__Impl"
    // InternalMyDsl.g:1536:1: rule__ResourceType__Group__7__Impl : ( ( rule__ResourceType__Group_7__0 )* ) ;
    public final void rule__ResourceType__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1540:1: ( ( ( rule__ResourceType__Group_7__0 )* ) )
            // InternalMyDsl.g:1541:1: ( ( rule__ResourceType__Group_7__0 )* )
            {
            // InternalMyDsl.g:1541:1: ( ( rule__ResourceType__Group_7__0 )* )
            // InternalMyDsl.g:1542:2: ( rule__ResourceType__Group_7__0 )*
            {
             before(grammarAccess.getResourceTypeAccess().getGroup_7()); 
            // InternalMyDsl.g:1543:2: ( rule__ResourceType__Group_7__0 )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==26) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // InternalMyDsl.g:1543:3: rule__ResourceType__Group_7__0
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__ResourceType__Group_7__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);

             after(grammarAccess.getResourceTypeAccess().getGroup_7()); 

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
    // $ANTLR end "rule__ResourceType__Group__7__Impl"


    // $ANTLR start "rule__ResourceType__Group__8"
    // InternalMyDsl.g:1551:1: rule__ResourceType__Group__8 : rule__ResourceType__Group__8__Impl ;
    public final void rule__ResourceType__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1555:1: ( rule__ResourceType__Group__8__Impl )
            // InternalMyDsl.g:1556:2: rule__ResourceType__Group__8__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceType__Group__8__Impl();

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
    // $ANTLR end "rule__ResourceType__Group__8"


    // $ANTLR start "rule__ResourceType__Group__8__Impl"
    // InternalMyDsl.g:1562:1: rule__ResourceType__Group__8__Impl : ( '}' ) ;
    public final void rule__ResourceType__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1566:1: ( ( '}' ) )
            // InternalMyDsl.g:1567:1: ( '}' )
            {
            // InternalMyDsl.g:1567:1: ( '}' )
            // InternalMyDsl.g:1568:2: '}'
            {
             before(grammarAccess.getResourceTypeAccess().getRightCurlyBracketKeyword_8()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getRightCurlyBracketKeyword_8()); 

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
    // $ANTLR end "rule__ResourceType__Group__8__Impl"


    // $ANTLR start "rule__ResourceType__Group_7__0"
    // InternalMyDsl.g:1578:1: rule__ResourceType__Group_7__0 : rule__ResourceType__Group_7__0__Impl rule__ResourceType__Group_7__1 ;
    public final void rule__ResourceType__Group_7__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1582:1: ( rule__ResourceType__Group_7__0__Impl rule__ResourceType__Group_7__1 )
            // InternalMyDsl.g:1583:2: rule__ResourceType__Group_7__0__Impl rule__ResourceType__Group_7__1
            {
            pushFollow(FOLLOW_18);
            rule__ResourceType__Group_7__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group_7__1();

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
    // $ANTLR end "rule__ResourceType__Group_7__0"


    // $ANTLR start "rule__ResourceType__Group_7__0__Impl"
    // InternalMyDsl.g:1590:1: rule__ResourceType__Group_7__0__Impl : ( 'using' ) ;
    public final void rule__ResourceType__Group_7__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1594:1: ( ( 'using' ) )
            // InternalMyDsl.g:1595:1: ( 'using' )
            {
            // InternalMyDsl.g:1595:1: ( 'using' )
            // InternalMyDsl.g:1596:2: 'using'
            {
             before(grammarAccess.getResourceTypeAccess().getUsingKeyword_7_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getUsingKeyword_7_0()); 

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
    // $ANTLR end "rule__ResourceType__Group_7__0__Impl"


    // $ANTLR start "rule__ResourceType__Group_7__1"
    // InternalMyDsl.g:1605:1: rule__ResourceType__Group_7__1 : rule__ResourceType__Group_7__1__Impl rule__ResourceType__Group_7__2 ;
    public final void rule__ResourceType__Group_7__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1609:1: ( rule__ResourceType__Group_7__1__Impl rule__ResourceType__Group_7__2 )
            // InternalMyDsl.g:1610:2: rule__ResourceType__Group_7__1__Impl rule__ResourceType__Group_7__2
            {
            pushFollow(FOLLOW_14);
            rule__ResourceType__Group_7__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceType__Group_7__2();

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
    // $ANTLR end "rule__ResourceType__Group_7__1"


    // $ANTLR start "rule__ResourceType__Group_7__1__Impl"
    // InternalMyDsl.g:1617:1: rule__ResourceType__Group_7__1__Impl : ( 'property' ) ;
    public final void rule__ResourceType__Group_7__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1621:1: ( ( 'property' ) )
            // InternalMyDsl.g:1622:1: ( 'property' )
            {
            // InternalMyDsl.g:1622:1: ( 'property' )
            // InternalMyDsl.g:1623:2: 'property'
            {
             before(grammarAccess.getResourceTypeAccess().getPropertyKeyword_7_1()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getPropertyKeyword_7_1()); 

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
    // $ANTLR end "rule__ResourceType__Group_7__1__Impl"


    // $ANTLR start "rule__ResourceType__Group_7__2"
    // InternalMyDsl.g:1632:1: rule__ResourceType__Group_7__2 : rule__ResourceType__Group_7__2__Impl ;
    public final void rule__ResourceType__Group_7__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1636:1: ( rule__ResourceType__Group_7__2__Impl )
            // InternalMyDsl.g:1637:2: rule__ResourceType__Group_7__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceType__Group_7__2__Impl();

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
    // $ANTLR end "rule__ResourceType__Group_7__2"


    // $ANTLR start "rule__ResourceType__Group_7__2__Impl"
    // InternalMyDsl.g:1643:1: rule__ResourceType__Group_7__2__Impl : ( ( rule__ResourceType__PropertyReferencesAssignment_7_2 ) ) ;
    public final void rule__ResourceType__Group_7__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1647:1: ( ( ( rule__ResourceType__PropertyReferencesAssignment_7_2 ) ) )
            // InternalMyDsl.g:1648:1: ( ( rule__ResourceType__PropertyReferencesAssignment_7_2 ) )
            {
            // InternalMyDsl.g:1648:1: ( ( rule__ResourceType__PropertyReferencesAssignment_7_2 ) )
            // InternalMyDsl.g:1649:2: ( rule__ResourceType__PropertyReferencesAssignment_7_2 )
            {
             before(grammarAccess.getResourceTypeAccess().getPropertyReferencesAssignment_7_2()); 
            // InternalMyDsl.g:1650:2: ( rule__ResourceType__PropertyReferencesAssignment_7_2 )
            // InternalMyDsl.g:1650:3: rule__ResourceType__PropertyReferencesAssignment_7_2
            {
            pushFollow(FOLLOW_2);
            rule__ResourceType__PropertyReferencesAssignment_7_2();

            state._fsp--;


            }

             after(grammarAccess.getResourceTypeAccess().getPropertyReferencesAssignment_7_2()); 

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
    // $ANTLR end "rule__ResourceType__Group_7__2__Impl"


    // $ANTLR start "rule__Resource__Group__0"
    // InternalMyDsl.g:1659:1: rule__Resource__Group__0 : rule__Resource__Group__0__Impl rule__Resource__Group__1 ;
    public final void rule__Resource__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1663:1: ( rule__Resource__Group__0__Impl rule__Resource__Group__1 )
            // InternalMyDsl.g:1664:2: rule__Resource__Group__0__Impl rule__Resource__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Resource__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__1();

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
    // $ANTLR end "rule__Resource__Group__0"


    // $ANTLR start "rule__Resource__Group__0__Impl"
    // InternalMyDsl.g:1671:1: rule__Resource__Group__0__Impl : ( 'resource' ) ;
    public final void rule__Resource__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1675:1: ( ( 'resource' ) )
            // InternalMyDsl.g:1676:1: ( 'resource' )
            {
            // InternalMyDsl.g:1676:1: ( 'resource' )
            // InternalMyDsl.g:1677:2: 'resource'
            {
             before(grammarAccess.getResourceAccess().getResourceKeyword_0()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getResourceAccess().getResourceKeyword_0()); 

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
    // $ANTLR end "rule__Resource__Group__0__Impl"


    // $ANTLR start "rule__Resource__Group__1"
    // InternalMyDsl.g:1686:1: rule__Resource__Group__1 : rule__Resource__Group__1__Impl rule__Resource__Group__2 ;
    public final void rule__Resource__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1690:1: ( rule__Resource__Group__1__Impl rule__Resource__Group__2 )
            // InternalMyDsl.g:1691:2: rule__Resource__Group__1__Impl rule__Resource__Group__2
            {
            pushFollow(FOLLOW_19);
            rule__Resource__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__2();

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
    // $ANTLR end "rule__Resource__Group__1"


    // $ANTLR start "rule__Resource__Group__1__Impl"
    // InternalMyDsl.g:1698:1: rule__Resource__Group__1__Impl : ( ( rule__Resource__NameAssignment_1 ) ) ;
    public final void rule__Resource__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1702:1: ( ( ( rule__Resource__NameAssignment_1 ) ) )
            // InternalMyDsl.g:1703:1: ( ( rule__Resource__NameAssignment_1 ) )
            {
            // InternalMyDsl.g:1703:1: ( ( rule__Resource__NameAssignment_1 ) )
            // InternalMyDsl.g:1704:2: ( rule__Resource__NameAssignment_1 )
            {
             before(grammarAccess.getResourceAccess().getNameAssignment_1()); 
            // InternalMyDsl.g:1705:2: ( rule__Resource__NameAssignment_1 )
            // InternalMyDsl.g:1705:3: rule__Resource__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Resource__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Resource__Group__1__Impl"


    // $ANTLR start "rule__Resource__Group__2"
    // InternalMyDsl.g:1713:1: rule__Resource__Group__2 : rule__Resource__Group__2__Impl rule__Resource__Group__3 ;
    public final void rule__Resource__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1717:1: ( rule__Resource__Group__2__Impl rule__Resource__Group__3 )
            // InternalMyDsl.g:1718:2: rule__Resource__Group__2__Impl rule__Resource__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__Resource__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__3();

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
    // $ANTLR end "rule__Resource__Group__2"


    // $ANTLR start "rule__Resource__Group__2__Impl"
    // InternalMyDsl.g:1725:1: rule__Resource__Group__2__Impl : ( ':' ) ;
    public final void rule__Resource__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1729:1: ( ( ':' ) )
            // InternalMyDsl.g:1730:1: ( ':' )
            {
            // InternalMyDsl.g:1730:1: ( ':' )
            // InternalMyDsl.g:1731:2: ':'
            {
             before(grammarAccess.getResourceAccess().getColonKeyword_2()); 
            match(input,28,FOLLOW_2); 
             after(grammarAccess.getResourceAccess().getColonKeyword_2()); 

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
    // $ANTLR end "rule__Resource__Group__2__Impl"


    // $ANTLR start "rule__Resource__Group__3"
    // InternalMyDsl.g:1740:1: rule__Resource__Group__3 : rule__Resource__Group__3__Impl rule__Resource__Group__4 ;
    public final void rule__Resource__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1744:1: ( rule__Resource__Group__3__Impl rule__Resource__Group__4 )
            // InternalMyDsl.g:1745:2: rule__Resource__Group__3__Impl rule__Resource__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__Resource__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__4();

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
    // $ANTLR end "rule__Resource__Group__3"


    // $ANTLR start "rule__Resource__Group__3__Impl"
    // InternalMyDsl.g:1752:1: rule__Resource__Group__3__Impl : ( ( rule__Resource__TypeAssignment_3 ) ) ;
    public final void rule__Resource__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1756:1: ( ( ( rule__Resource__TypeAssignment_3 ) ) )
            // InternalMyDsl.g:1757:1: ( ( rule__Resource__TypeAssignment_3 ) )
            {
            // InternalMyDsl.g:1757:1: ( ( rule__Resource__TypeAssignment_3 ) )
            // InternalMyDsl.g:1758:2: ( rule__Resource__TypeAssignment_3 )
            {
             before(grammarAccess.getResourceAccess().getTypeAssignment_3()); 
            // InternalMyDsl.g:1759:2: ( rule__Resource__TypeAssignment_3 )
            // InternalMyDsl.g:1759:3: rule__Resource__TypeAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Resource__TypeAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getResourceAccess().getTypeAssignment_3()); 

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
    // $ANTLR end "rule__Resource__Group__3__Impl"


    // $ANTLR start "rule__Resource__Group__4"
    // InternalMyDsl.g:1767:1: rule__Resource__Group__4 : rule__Resource__Group__4__Impl rule__Resource__Group__5 ;
    public final void rule__Resource__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1771:1: ( rule__Resource__Group__4__Impl rule__Resource__Group__5 )
            // InternalMyDsl.g:1772:2: rule__Resource__Group__4__Impl rule__Resource__Group__5
            {
            pushFollow(FOLLOW_20);
            rule__Resource__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__5();

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
    // $ANTLR end "rule__Resource__Group__4"


    // $ANTLR start "rule__Resource__Group__4__Impl"
    // InternalMyDsl.g:1779:1: rule__Resource__Group__4__Impl : ( '{' ) ;
    public final void rule__Resource__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1783:1: ( ( '{' ) )
            // InternalMyDsl.g:1784:1: ( '{' )
            {
            // InternalMyDsl.g:1784:1: ( '{' )
            // InternalMyDsl.g:1785:2: '{'
            {
             before(grammarAccess.getResourceAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getResourceAccess().getLeftCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__Resource__Group__4__Impl"


    // $ANTLR start "rule__Resource__Group__5"
    // InternalMyDsl.g:1794:1: rule__Resource__Group__5 : rule__Resource__Group__5__Impl rule__Resource__Group__6 ;
    public final void rule__Resource__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1798:1: ( rule__Resource__Group__5__Impl rule__Resource__Group__6 )
            // InternalMyDsl.g:1799:2: rule__Resource__Group__5__Impl rule__Resource__Group__6
            {
            pushFollow(FOLLOW_20);
            rule__Resource__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__6();

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
    // $ANTLR end "rule__Resource__Group__5"


    // $ANTLR start "rule__Resource__Group__5__Impl"
    // InternalMyDsl.g:1806:1: rule__Resource__Group__5__Impl : ( ( rule__Resource__ResourcesAssignment_5 )* ) ;
    public final void rule__Resource__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1810:1: ( ( ( rule__Resource__ResourcesAssignment_5 )* ) )
            // InternalMyDsl.g:1811:1: ( ( rule__Resource__ResourcesAssignment_5 )* )
            {
            // InternalMyDsl.g:1811:1: ( ( rule__Resource__ResourcesAssignment_5 )* )
            // InternalMyDsl.g:1812:2: ( rule__Resource__ResourcesAssignment_5 )*
            {
             before(grammarAccess.getResourceAccess().getResourcesAssignment_5()); 
            // InternalMyDsl.g:1813:2: ( rule__Resource__ResourcesAssignment_5 )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==22) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalMyDsl.g:1813:3: rule__Resource__ResourcesAssignment_5
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__Resource__ResourcesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

             after(grammarAccess.getResourceAccess().getResourcesAssignment_5()); 

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
    // $ANTLR end "rule__Resource__Group__5__Impl"


    // $ANTLR start "rule__Resource__Group__6"
    // InternalMyDsl.g:1821:1: rule__Resource__Group__6 : rule__Resource__Group__6__Impl rule__Resource__Group__7 ;
    public final void rule__Resource__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1825:1: ( rule__Resource__Group__6__Impl rule__Resource__Group__7 )
            // InternalMyDsl.g:1826:2: rule__Resource__Group__6__Impl rule__Resource__Group__7
            {
            pushFollow(FOLLOW_20);
            rule__Resource__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Resource__Group__7();

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
    // $ANTLR end "rule__Resource__Group__6"


    // $ANTLR start "rule__Resource__Group__6__Impl"
    // InternalMyDsl.g:1833:1: rule__Resource__Group__6__Impl : ( ( rule__Resource__ValuesAssignment_6 )* ) ;
    public final void rule__Resource__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1837:1: ( ( ( rule__Resource__ValuesAssignment_6 )* ) )
            // InternalMyDsl.g:1838:1: ( ( rule__Resource__ValuesAssignment_6 )* )
            {
            // InternalMyDsl.g:1838:1: ( ( rule__Resource__ValuesAssignment_6 )* )
            // InternalMyDsl.g:1839:2: ( rule__Resource__ValuesAssignment_6 )*
            {
             before(grammarAccess.getResourceAccess().getValuesAssignment_6()); 
            // InternalMyDsl.g:1840:2: ( rule__Resource__ValuesAssignment_6 )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_ID) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalMyDsl.g:1840:3: rule__Resource__ValuesAssignment_6
            	    {
            	    pushFollow(FOLLOW_21);
            	    rule__Resource__ValuesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

             after(grammarAccess.getResourceAccess().getValuesAssignment_6()); 

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
    // $ANTLR end "rule__Resource__Group__6__Impl"


    // $ANTLR start "rule__Resource__Group__7"
    // InternalMyDsl.g:1848:1: rule__Resource__Group__7 : rule__Resource__Group__7__Impl ;
    public final void rule__Resource__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1852:1: ( rule__Resource__Group__7__Impl )
            // InternalMyDsl.g:1853:2: rule__Resource__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Resource__Group__7__Impl();

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
    // $ANTLR end "rule__Resource__Group__7"


    // $ANTLR start "rule__Resource__Group__7__Impl"
    // InternalMyDsl.g:1859:1: rule__Resource__Group__7__Impl : ( '}' ) ;
    public final void rule__Resource__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1863:1: ( ( '}' ) )
            // InternalMyDsl.g:1864:1: ( '}' )
            {
            // InternalMyDsl.g:1864:1: ( '}' )
            // InternalMyDsl.g:1865:2: '}'
            {
             before(grammarAccess.getResourceAccess().getRightCurlyBracketKeyword_7()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getResourceAccess().getRightCurlyBracketKeyword_7()); 

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
    // $ANTLR end "rule__Resource__Group__7__Impl"


    // $ANTLR start "rule__CurrentResourceValue__Group__0"
    // InternalMyDsl.g:1875:1: rule__CurrentResourceValue__Group__0 : rule__CurrentResourceValue__Group__0__Impl rule__CurrentResourceValue__Group__1 ;
    public final void rule__CurrentResourceValue__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1879:1: ( rule__CurrentResourceValue__Group__0__Impl rule__CurrentResourceValue__Group__1 )
            // InternalMyDsl.g:1880:2: rule__CurrentResourceValue__Group__0__Impl rule__CurrentResourceValue__Group__1
            {
            pushFollow(FOLLOW_22);
            rule__CurrentResourceValue__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CurrentResourceValue__Group__1();

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
    // $ANTLR end "rule__CurrentResourceValue__Group__0"


    // $ANTLR start "rule__CurrentResourceValue__Group__0__Impl"
    // InternalMyDsl.g:1887:1: rule__CurrentResourceValue__Group__0__Impl : ( ( rule__CurrentResourceValue__PropertyAssignment_0 ) ) ;
    public final void rule__CurrentResourceValue__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1891:1: ( ( ( rule__CurrentResourceValue__PropertyAssignment_0 ) ) )
            // InternalMyDsl.g:1892:1: ( ( rule__CurrentResourceValue__PropertyAssignment_0 ) )
            {
            // InternalMyDsl.g:1892:1: ( ( rule__CurrentResourceValue__PropertyAssignment_0 ) )
            // InternalMyDsl.g:1893:2: ( rule__CurrentResourceValue__PropertyAssignment_0 )
            {
             before(grammarAccess.getCurrentResourceValueAccess().getPropertyAssignment_0()); 
            // InternalMyDsl.g:1894:2: ( rule__CurrentResourceValue__PropertyAssignment_0 )
            // InternalMyDsl.g:1894:3: rule__CurrentResourceValue__PropertyAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__CurrentResourceValue__PropertyAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getCurrentResourceValueAccess().getPropertyAssignment_0()); 

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
    // $ANTLR end "rule__CurrentResourceValue__Group__0__Impl"


    // $ANTLR start "rule__CurrentResourceValue__Group__1"
    // InternalMyDsl.g:1902:1: rule__CurrentResourceValue__Group__1 : rule__CurrentResourceValue__Group__1__Impl rule__CurrentResourceValue__Group__2 ;
    public final void rule__CurrentResourceValue__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1906:1: ( rule__CurrentResourceValue__Group__1__Impl rule__CurrentResourceValue__Group__2 )
            // InternalMyDsl.g:1907:2: rule__CurrentResourceValue__Group__1__Impl rule__CurrentResourceValue__Group__2
            {
            pushFollow(FOLLOW_23);
            rule__CurrentResourceValue__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__CurrentResourceValue__Group__2();

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
    // $ANTLR end "rule__CurrentResourceValue__Group__1"


    // $ANTLR start "rule__CurrentResourceValue__Group__1__Impl"
    // InternalMyDsl.g:1914:1: rule__CurrentResourceValue__Group__1__Impl : ( '=' ) ;
    public final void rule__CurrentResourceValue__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1918:1: ( ( '=' ) )
            // InternalMyDsl.g:1919:1: ( '=' )
            {
            // InternalMyDsl.g:1919:1: ( '=' )
            // InternalMyDsl.g:1920:2: '='
            {
             before(grammarAccess.getCurrentResourceValueAccess().getEqualsSignKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getCurrentResourceValueAccess().getEqualsSignKeyword_1()); 

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
    // $ANTLR end "rule__CurrentResourceValue__Group__1__Impl"


    // $ANTLR start "rule__CurrentResourceValue__Group__2"
    // InternalMyDsl.g:1929:1: rule__CurrentResourceValue__Group__2 : rule__CurrentResourceValue__Group__2__Impl ;
    public final void rule__CurrentResourceValue__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1933:1: ( rule__CurrentResourceValue__Group__2__Impl )
            // InternalMyDsl.g:1934:2: rule__CurrentResourceValue__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__CurrentResourceValue__Group__2__Impl();

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
    // $ANTLR end "rule__CurrentResourceValue__Group__2"


    // $ANTLR start "rule__CurrentResourceValue__Group__2__Impl"
    // InternalMyDsl.g:1940:1: rule__CurrentResourceValue__Group__2__Impl : ( ( rule__CurrentResourceValue__ValueAssignment_2 ) ) ;
    public final void rule__CurrentResourceValue__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1944:1: ( ( ( rule__CurrentResourceValue__ValueAssignment_2 ) ) )
            // InternalMyDsl.g:1945:1: ( ( rule__CurrentResourceValue__ValueAssignment_2 ) )
            {
            // InternalMyDsl.g:1945:1: ( ( rule__CurrentResourceValue__ValueAssignment_2 ) )
            // InternalMyDsl.g:1946:2: ( rule__CurrentResourceValue__ValueAssignment_2 )
            {
             before(grammarAccess.getCurrentResourceValueAccess().getValueAssignment_2()); 
            // InternalMyDsl.g:1947:2: ( rule__CurrentResourceValue__ValueAssignment_2 )
            // InternalMyDsl.g:1947:3: rule__CurrentResourceValue__ValueAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__CurrentResourceValue__ValueAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getCurrentResourceValueAccess().getValueAssignment_2()); 

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
    // $ANTLR end "rule__CurrentResourceValue__Group__2__Impl"


    // $ANTLR start "rule__SoftwareModel__Group__0"
    // InternalMyDsl.g:1956:1: rule__SoftwareModel__Group__0 : rule__SoftwareModel__Group__0__Impl rule__SoftwareModel__Group__1 ;
    public final void rule__SoftwareModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1960:1: ( rule__SoftwareModel__Group__0__Impl rule__SoftwareModel__Group__1 )
            // InternalMyDsl.g:1961:2: rule__SoftwareModel__Group__0__Impl rule__SoftwareModel__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__SoftwareModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__SoftwareModel__Group__1();

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
    // $ANTLR end "rule__SoftwareModel__Group__0"


    // $ANTLR start "rule__SoftwareModel__Group__0__Impl"
    // InternalMyDsl.g:1968:1: rule__SoftwareModel__Group__0__Impl : ( ( rule__SoftwareModel__Alternatives_0 )* ) ;
    public final void rule__SoftwareModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1972:1: ( ( ( rule__SoftwareModel__Alternatives_0 )* ) )
            // InternalMyDsl.g:1973:1: ( ( rule__SoftwareModel__Alternatives_0 )* )
            {
            // InternalMyDsl.g:1973:1: ( ( rule__SoftwareModel__Alternatives_0 )* )
            // InternalMyDsl.g:1974:2: ( rule__SoftwareModel__Alternatives_0 )*
            {
             before(grammarAccess.getSoftwareModelAccess().getAlternatives_0()); 
            // InternalMyDsl.g:1975:2: ( rule__SoftwareModel__Alternatives_0 )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==27||LA20_0==29) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalMyDsl.g:1975:3: rule__SoftwareModel__Alternatives_0
            	    {
            	    pushFollow(FOLLOW_24);
            	    rule__SoftwareModel__Alternatives_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

             after(grammarAccess.getSoftwareModelAccess().getAlternatives_0()); 

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
    // $ANTLR end "rule__SoftwareModel__Group__0__Impl"


    // $ANTLR start "rule__SoftwareModel__Group__1"
    // InternalMyDsl.g:1983:1: rule__SoftwareModel__Group__1 : rule__SoftwareModel__Group__1__Impl ;
    public final void rule__SoftwareModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1987:1: ( rule__SoftwareModel__Group__1__Impl )
            // InternalMyDsl.g:1988:2: rule__SoftwareModel__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__SoftwareModel__Group__1__Impl();

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
    // $ANTLR end "rule__SoftwareModel__Group__1"


    // $ANTLR start "rule__SoftwareModel__Group__1__Impl"
    // InternalMyDsl.g:1994:1: rule__SoftwareModel__Group__1__Impl : ( ( ( rule__SoftwareModel__ComponentsAssignment_1 ) ) ( ( rule__SoftwareModel__ComponentsAssignment_1 )* ) ) ;
    public final void rule__SoftwareModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1998:1: ( ( ( ( rule__SoftwareModel__ComponentsAssignment_1 ) ) ( ( rule__SoftwareModel__ComponentsAssignment_1 )* ) ) )
            // InternalMyDsl.g:1999:1: ( ( ( rule__SoftwareModel__ComponentsAssignment_1 ) ) ( ( rule__SoftwareModel__ComponentsAssignment_1 )* ) )
            {
            // InternalMyDsl.g:1999:1: ( ( ( rule__SoftwareModel__ComponentsAssignment_1 ) ) ( ( rule__SoftwareModel__ComponentsAssignment_1 )* ) )
            // InternalMyDsl.g:2000:2: ( ( rule__SoftwareModel__ComponentsAssignment_1 ) ) ( ( rule__SoftwareModel__ComponentsAssignment_1 )* )
            {
            // InternalMyDsl.g:2000:2: ( ( rule__SoftwareModel__ComponentsAssignment_1 ) )
            // InternalMyDsl.g:2001:3: ( rule__SoftwareModel__ComponentsAssignment_1 )
            {
             before(grammarAccess.getSoftwareModelAccess().getComponentsAssignment_1()); 
            // InternalMyDsl.g:2002:3: ( rule__SoftwareModel__ComponentsAssignment_1 )
            // InternalMyDsl.g:2002:4: rule__SoftwareModel__ComponentsAssignment_1
            {
            pushFollow(FOLLOW_25);
            rule__SoftwareModel__ComponentsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getSoftwareModelAccess().getComponentsAssignment_1()); 

            }

            // InternalMyDsl.g:2005:2: ( ( rule__SoftwareModel__ComponentsAssignment_1 )* )
            // InternalMyDsl.g:2006:3: ( rule__SoftwareModel__ComponentsAssignment_1 )*
            {
             before(grammarAccess.getSoftwareModelAccess().getComponentsAssignment_1()); 
            // InternalMyDsl.g:2007:3: ( rule__SoftwareModel__ComponentsAssignment_1 )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==30) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalMyDsl.g:2007:4: rule__SoftwareModel__ComponentsAssignment_1
            	    {
            	    pushFollow(FOLLOW_25);
            	    rule__SoftwareModel__ComponentsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

             after(grammarAccess.getSoftwareModelAccess().getComponentsAssignment_1()); 

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
    // $ANTLR end "rule__SoftwareModel__Group__1__Impl"


    // $ANTLR start "rule__MetaParameter__Group__0"
    // InternalMyDsl.g:2017:1: rule__MetaParameter__Group__0 : rule__MetaParameter__Group__0__Impl rule__MetaParameter__Group__1 ;
    public final void rule__MetaParameter__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2021:1: ( rule__MetaParameter__Group__0__Impl rule__MetaParameter__Group__1 )
            // InternalMyDsl.g:2022:2: rule__MetaParameter__Group__0__Impl rule__MetaParameter__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__MetaParameter__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaParameter__Group__1();

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
    // $ANTLR end "rule__MetaParameter__Group__0"


    // $ANTLR start "rule__MetaParameter__Group__0__Impl"
    // InternalMyDsl.g:2029:1: rule__MetaParameter__Group__0__Impl : ( 'meta' ) ;
    public final void rule__MetaParameter__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2033:1: ( ( 'meta' ) )
            // InternalMyDsl.g:2034:1: ( 'meta' )
            {
            // InternalMyDsl.g:2034:1: ( 'meta' )
            // InternalMyDsl.g:2035:2: 'meta'
            {
             before(grammarAccess.getMetaParameterAccess().getMetaKeyword_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getMetaParameterAccess().getMetaKeyword_0()); 

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
    // $ANTLR end "rule__MetaParameter__Group__0__Impl"


    // $ANTLR start "rule__MetaParameter__Group__1"
    // InternalMyDsl.g:2044:1: rule__MetaParameter__Group__1 : rule__MetaParameter__Group__1__Impl ;
    public final void rule__MetaParameter__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2048:1: ( rule__MetaParameter__Group__1__Impl )
            // InternalMyDsl.g:2049:2: rule__MetaParameter__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameter__Group__1__Impl();

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
    // $ANTLR end "rule__MetaParameter__Group__1"


    // $ANTLR start "rule__MetaParameter__Group__1__Impl"
    // InternalMyDsl.g:2055:1: rule__MetaParameter__Group__1__Impl : ( ( rule__MetaParameter__NameAssignment_1 ) ) ;
    public final void rule__MetaParameter__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2059:1: ( ( ( rule__MetaParameter__NameAssignment_1 ) ) )
            // InternalMyDsl.g:2060:1: ( ( rule__MetaParameter__NameAssignment_1 ) )
            {
            // InternalMyDsl.g:2060:1: ( ( rule__MetaParameter__NameAssignment_1 ) )
            // InternalMyDsl.g:2061:2: ( rule__MetaParameter__NameAssignment_1 )
            {
             before(grammarAccess.getMetaParameterAccess().getNameAssignment_1()); 
            // InternalMyDsl.g:2062:2: ( rule__MetaParameter__NameAssignment_1 )
            // InternalMyDsl.g:2062:3: rule__MetaParameter__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameter__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMetaParameterAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__MetaParameter__Group__1__Impl"


    // $ANTLR start "rule__Property__Group__0"
    // InternalMyDsl.g:2071:1: rule__Property__Group__0 : rule__Property__Group__0__Impl rule__Property__Group__1 ;
    public final void rule__Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2075:1: ( rule__Property__Group__0__Impl rule__Property__Group__1 )
            // InternalMyDsl.g:2076:2: rule__Property__Group__0__Impl rule__Property__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Property__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Property__Group__1();

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
    // $ANTLR end "rule__Property__Group__0"


    // $ANTLR start "rule__Property__Group__0__Impl"
    // InternalMyDsl.g:2083:1: rule__Property__Group__0__Impl : ( 'property' ) ;
    public final void rule__Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2087:1: ( ( 'property' ) )
            // InternalMyDsl.g:2088:1: ( 'property' )
            {
            // InternalMyDsl.g:2088:1: ( 'property' )
            // InternalMyDsl.g:2089:2: 'property'
            {
             before(grammarAccess.getPropertyAccess().getPropertyKeyword_0()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getPropertyKeyword_0()); 

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
    // $ANTLR end "rule__Property__Group__0__Impl"


    // $ANTLR start "rule__Property__Group__1"
    // InternalMyDsl.g:2098:1: rule__Property__Group__1 : rule__Property__Group__1__Impl rule__Property__Group__2 ;
    public final void rule__Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2102:1: ( rule__Property__Group__1__Impl rule__Property__Group__2 )
            // InternalMyDsl.g:2103:2: rule__Property__Group__1__Impl rule__Property__Group__2
            {
            pushFollow(FOLLOW_26);
            rule__Property__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Property__Group__2();

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
    // $ANTLR end "rule__Property__Group__1"


    // $ANTLR start "rule__Property__Group__1__Impl"
    // InternalMyDsl.g:2110:1: rule__Property__Group__1__Impl : ( ( rule__Property__NameAssignment_1 ) ) ;
    public final void rule__Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2114:1: ( ( ( rule__Property__NameAssignment_1 ) ) )
            // InternalMyDsl.g:2115:1: ( ( rule__Property__NameAssignment_1 ) )
            {
            // InternalMyDsl.g:2115:1: ( ( rule__Property__NameAssignment_1 ) )
            // InternalMyDsl.g:2116:2: ( rule__Property__NameAssignment_1 )
            {
             before(grammarAccess.getPropertyAccess().getNameAssignment_1()); 
            // InternalMyDsl.g:2117:2: ( rule__Property__NameAssignment_1 )
            // InternalMyDsl.g:2117:3: rule__Property__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Property__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Property__Group__1__Impl"


    // $ANTLR start "rule__Property__Group__2"
    // InternalMyDsl.g:2125:1: rule__Property__Group__2 : rule__Property__Group__2__Impl ;
    public final void rule__Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2129:1: ( rule__Property__Group__2__Impl )
            // InternalMyDsl.g:2130:2: rule__Property__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Property__Group__2__Impl();

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
    // $ANTLR end "rule__Property__Group__2"


    // $ANTLR start "rule__Property__Group__2__Impl"
    // InternalMyDsl.g:2136:1: rule__Property__Group__2__Impl : ( ( rule__Property__UnitAssignment_2 ) ) ;
    public final void rule__Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2140:1: ( ( ( rule__Property__UnitAssignment_2 ) ) )
            // InternalMyDsl.g:2141:1: ( ( rule__Property__UnitAssignment_2 ) )
            {
            // InternalMyDsl.g:2141:1: ( ( rule__Property__UnitAssignment_2 ) )
            // InternalMyDsl.g:2142:2: ( rule__Property__UnitAssignment_2 )
            {
             before(grammarAccess.getPropertyAccess().getUnitAssignment_2()); 
            // InternalMyDsl.g:2143:2: ( rule__Property__UnitAssignment_2 )
            // InternalMyDsl.g:2143:3: rule__Property__UnitAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Property__UnitAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getPropertyAccess().getUnitAssignment_2()); 

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
    // $ANTLR end "rule__Property__Group__2__Impl"


    // $ANTLR start "rule__Component__Group__0"
    // InternalMyDsl.g:2152:1: rule__Component__Group__0 : rule__Component__Group__0__Impl rule__Component__Group__1 ;
    public final void rule__Component__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2156:1: ( rule__Component__Group__0__Impl rule__Component__Group__1 )
            // InternalMyDsl.g:2157:2: rule__Component__Group__0__Impl rule__Component__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Component__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__1();

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
    // $ANTLR end "rule__Component__Group__0"


    // $ANTLR start "rule__Component__Group__0__Impl"
    // InternalMyDsl.g:2164:1: rule__Component__Group__0__Impl : ( 'component' ) ;
    public final void rule__Component__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2168:1: ( ( 'component' ) )
            // InternalMyDsl.g:2169:1: ( 'component' )
            {
            // InternalMyDsl.g:2169:1: ( 'component' )
            // InternalMyDsl.g:2170:2: 'component'
            {
             before(grammarAccess.getComponentAccess().getComponentKeyword_0()); 
            match(input,30,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getComponentKeyword_0()); 

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
    // $ANTLR end "rule__Component__Group__0__Impl"


    // $ANTLR start "rule__Component__Group__1"
    // InternalMyDsl.g:2179:1: rule__Component__Group__1 : rule__Component__Group__1__Impl rule__Component__Group__2 ;
    public final void rule__Component__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2183:1: ( rule__Component__Group__1__Impl rule__Component__Group__2 )
            // InternalMyDsl.g:2184:2: rule__Component__Group__1__Impl rule__Component__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__Component__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__2();

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
    // $ANTLR end "rule__Component__Group__1"


    // $ANTLR start "rule__Component__Group__1__Impl"
    // InternalMyDsl.g:2191:1: rule__Component__Group__1__Impl : ( ( rule__Component__NameAssignment_1 ) ) ;
    public final void rule__Component__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2195:1: ( ( ( rule__Component__NameAssignment_1 ) ) )
            // InternalMyDsl.g:2196:1: ( ( rule__Component__NameAssignment_1 ) )
            {
            // InternalMyDsl.g:2196:1: ( ( rule__Component__NameAssignment_1 ) )
            // InternalMyDsl.g:2197:2: ( rule__Component__NameAssignment_1 )
            {
             before(grammarAccess.getComponentAccess().getNameAssignment_1()); 
            // InternalMyDsl.g:2198:2: ( rule__Component__NameAssignment_1 )
            // InternalMyDsl.g:2198:3: rule__Component__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Component__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Component__Group__1__Impl"


    // $ANTLR start "rule__Component__Group__2"
    // InternalMyDsl.g:2206:1: rule__Component__Group__2 : rule__Component__Group__2__Impl rule__Component__Group__3 ;
    public final void rule__Component__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2210:1: ( rule__Component__Group__2__Impl rule__Component__Group__3 )
            // InternalMyDsl.g:2211:2: rule__Component__Group__2__Impl rule__Component__Group__3
            {
            pushFollow(FOLLOW_27);
            rule__Component__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__3();

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
    // $ANTLR end "rule__Component__Group__2"


    // $ANTLR start "rule__Component__Group__2__Impl"
    // InternalMyDsl.g:2218:1: rule__Component__Group__2__Impl : ( '{' ) ;
    public final void rule__Component__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2222:1: ( ( '{' ) )
            // InternalMyDsl.g:2223:1: ( '{' )
            {
            // InternalMyDsl.g:2223:1: ( '{' )
            // InternalMyDsl.g:2224:2: '{'
            {
             before(grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__Component__Group__2__Impl"


    // $ANTLR start "rule__Component__Group__3"
    // InternalMyDsl.g:2233:1: rule__Component__Group__3 : rule__Component__Group__3__Impl rule__Component__Group__4 ;
    public final void rule__Component__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2237:1: ( rule__Component__Group__3__Impl rule__Component__Group__4 )
            // InternalMyDsl.g:2238:2: rule__Component__Group__3__Impl rule__Component__Group__4
            {
            pushFollow(FOLLOW_27);
            rule__Component__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__4();

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
    // $ANTLR end "rule__Component__Group__3"


    // $ANTLR start "rule__Component__Group__3__Impl"
    // InternalMyDsl.g:2245:1: rule__Component__Group__3__Impl : ( ( rule__Component__Group_3__0 )* ) ;
    public final void rule__Component__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2249:1: ( ( ( rule__Component__Group_3__0 )* ) )
            // InternalMyDsl.g:2250:1: ( ( rule__Component__Group_3__0 )* )
            {
            // InternalMyDsl.g:2250:1: ( ( rule__Component__Group_3__0 )* )
            // InternalMyDsl.g:2251:2: ( rule__Component__Group_3__0 )*
            {
             before(grammarAccess.getComponentAccess().getGroup_3()); 
            // InternalMyDsl.g:2252:2: ( rule__Component__Group_3__0 )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==26) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalMyDsl.g:2252:3: rule__Component__Group_3__0
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__Component__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

             after(grammarAccess.getComponentAccess().getGroup_3()); 

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
    // $ANTLR end "rule__Component__Group__3__Impl"


    // $ANTLR start "rule__Component__Group__4"
    // InternalMyDsl.g:2260:1: rule__Component__Group__4 : rule__Component__Group__4__Impl rule__Component__Group__5 ;
    public final void rule__Component__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2264:1: ( rule__Component__Group__4__Impl rule__Component__Group__5 )
            // InternalMyDsl.g:2265:2: rule__Component__Group__4__Impl rule__Component__Group__5
            {
            pushFollow(FOLLOW_28);
            rule__Component__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__5();

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
    // $ANTLR end "rule__Component__Group__4"


    // $ANTLR start "rule__Component__Group__4__Impl"
    // InternalMyDsl.g:2272:1: rule__Component__Group__4__Impl : ( ( ( rule__Component__ImplementationsAssignment_4 ) ) ( ( rule__Component__ImplementationsAssignment_4 )* ) ) ;
    public final void rule__Component__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2276:1: ( ( ( ( rule__Component__ImplementationsAssignment_4 ) ) ( ( rule__Component__ImplementationsAssignment_4 )* ) ) )
            // InternalMyDsl.g:2277:1: ( ( ( rule__Component__ImplementationsAssignment_4 ) ) ( ( rule__Component__ImplementationsAssignment_4 )* ) )
            {
            // InternalMyDsl.g:2277:1: ( ( ( rule__Component__ImplementationsAssignment_4 ) ) ( ( rule__Component__ImplementationsAssignment_4 )* ) )
            // InternalMyDsl.g:2278:2: ( ( rule__Component__ImplementationsAssignment_4 ) ) ( ( rule__Component__ImplementationsAssignment_4 )* )
            {
            // InternalMyDsl.g:2278:2: ( ( rule__Component__ImplementationsAssignment_4 ) )
            // InternalMyDsl.g:2279:3: ( rule__Component__ImplementationsAssignment_4 )
            {
             before(grammarAccess.getComponentAccess().getImplementationsAssignment_4()); 
            // InternalMyDsl.g:2280:3: ( rule__Component__ImplementationsAssignment_4 )
            // InternalMyDsl.g:2280:4: rule__Component__ImplementationsAssignment_4
            {
            pushFollow(FOLLOW_29);
            rule__Component__ImplementationsAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getImplementationsAssignment_4()); 

            }

            // InternalMyDsl.g:2283:2: ( ( rule__Component__ImplementationsAssignment_4 )* )
            // InternalMyDsl.g:2284:3: ( rule__Component__ImplementationsAssignment_4 )*
            {
             before(grammarAccess.getComponentAccess().getImplementationsAssignment_4()); 
            // InternalMyDsl.g:2285:3: ( rule__Component__ImplementationsAssignment_4 )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==31) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalMyDsl.g:2285:4: rule__Component__ImplementationsAssignment_4
            	    {
            	    pushFollow(FOLLOW_29);
            	    rule__Component__ImplementationsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);

             after(grammarAccess.getComponentAccess().getImplementationsAssignment_4()); 

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
    // $ANTLR end "rule__Component__Group__4__Impl"


    // $ANTLR start "rule__Component__Group__5"
    // InternalMyDsl.g:2294:1: rule__Component__Group__5 : rule__Component__Group__5__Impl rule__Component__Group__6 ;
    public final void rule__Component__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2298:1: ( rule__Component__Group__5__Impl rule__Component__Group__6 )
            // InternalMyDsl.g:2299:2: rule__Component__Group__5__Impl rule__Component__Group__6
            {
            pushFollow(FOLLOW_28);
            rule__Component__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group__6();

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
    // $ANTLR end "rule__Component__Group__5"


    // $ANTLR start "rule__Component__Group__5__Impl"
    // InternalMyDsl.g:2306:1: rule__Component__Group__5__Impl : ( ( rule__Component__Group_5__0 )* ) ;
    public final void rule__Component__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2310:1: ( ( ( rule__Component__Group_5__0 )* ) )
            // InternalMyDsl.g:2311:1: ( ( rule__Component__Group_5__0 )* )
            {
            // InternalMyDsl.g:2311:1: ( ( rule__Component__Group_5__0 )* )
            // InternalMyDsl.g:2312:2: ( rule__Component__Group_5__0 )*
            {
             before(grammarAccess.getComponentAccess().getGroup_5()); 
            // InternalMyDsl.g:2313:2: ( rule__Component__Group_5__0 )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==26) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // InternalMyDsl.g:2313:3: rule__Component__Group_5__0
            	    {
            	    pushFollow(FOLLOW_17);
            	    rule__Component__Group_5__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop24;
                }
            } while (true);

             after(grammarAccess.getComponentAccess().getGroup_5()); 

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
    // $ANTLR end "rule__Component__Group__5__Impl"


    // $ANTLR start "rule__Component__Group__6"
    // InternalMyDsl.g:2321:1: rule__Component__Group__6 : rule__Component__Group__6__Impl ;
    public final void rule__Component__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2325:1: ( rule__Component__Group__6__Impl )
            // InternalMyDsl.g:2326:2: rule__Component__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component__Group__6__Impl();

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
    // $ANTLR end "rule__Component__Group__6"


    // $ANTLR start "rule__Component__Group__6__Impl"
    // InternalMyDsl.g:2332:1: rule__Component__Group__6__Impl : ( '}' ) ;
    public final void rule__Component__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2336:1: ( ( '}' ) )
            // InternalMyDsl.g:2337:1: ( '}' )
            {
            // InternalMyDsl.g:2337:1: ( '}' )
            // InternalMyDsl.g:2338:2: '}'
            {
             before(grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_6()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getRightCurlyBracketKeyword_6()); 

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
    // $ANTLR end "rule__Component__Group__6__Impl"


    // $ANTLR start "rule__Component__Group_3__0"
    // InternalMyDsl.g:2348:1: rule__Component__Group_3__0 : rule__Component__Group_3__0__Impl rule__Component__Group_3__1 ;
    public final void rule__Component__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2352:1: ( rule__Component__Group_3__0__Impl rule__Component__Group_3__1 )
            // InternalMyDsl.g:2353:2: rule__Component__Group_3__0__Impl rule__Component__Group_3__1
            {
            pushFollow(FOLLOW_18);
            rule__Component__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group_3__1();

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
    // $ANTLR end "rule__Component__Group_3__0"


    // $ANTLR start "rule__Component__Group_3__0__Impl"
    // InternalMyDsl.g:2360:1: rule__Component__Group_3__0__Impl : ( 'using' ) ;
    public final void rule__Component__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2364:1: ( ( 'using' ) )
            // InternalMyDsl.g:2365:1: ( 'using' )
            {
            // InternalMyDsl.g:2365:1: ( 'using' )
            // InternalMyDsl.g:2366:2: 'using'
            {
             before(grammarAccess.getComponentAccess().getUsingKeyword_3_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getUsingKeyword_3_0()); 

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
    // $ANTLR end "rule__Component__Group_3__0__Impl"


    // $ANTLR start "rule__Component__Group_3__1"
    // InternalMyDsl.g:2375:1: rule__Component__Group_3__1 : rule__Component__Group_3__1__Impl rule__Component__Group_3__2 ;
    public final void rule__Component__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2379:1: ( rule__Component__Group_3__1__Impl rule__Component__Group_3__2 )
            // InternalMyDsl.g:2380:2: rule__Component__Group_3__1__Impl rule__Component__Group_3__2
            {
            pushFollow(FOLLOW_14);
            rule__Component__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group_3__2();

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
    // $ANTLR end "rule__Component__Group_3__1"


    // $ANTLR start "rule__Component__Group_3__1__Impl"
    // InternalMyDsl.g:2387:1: rule__Component__Group_3__1__Impl : ( 'property' ) ;
    public final void rule__Component__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2391:1: ( ( 'property' ) )
            // InternalMyDsl.g:2392:1: ( 'property' )
            {
            // InternalMyDsl.g:2392:1: ( 'property' )
            // InternalMyDsl.g:2393:2: 'property'
            {
             before(grammarAccess.getComponentAccess().getPropertyKeyword_3_1()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getPropertyKeyword_3_1()); 

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
    // $ANTLR end "rule__Component__Group_3__1__Impl"


    // $ANTLR start "rule__Component__Group_3__2"
    // InternalMyDsl.g:2402:1: rule__Component__Group_3__2 : rule__Component__Group_3__2__Impl ;
    public final void rule__Component__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2406:1: ( rule__Component__Group_3__2__Impl )
            // InternalMyDsl.g:2407:2: rule__Component__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component__Group_3__2__Impl();

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
    // $ANTLR end "rule__Component__Group_3__2"


    // $ANTLR start "rule__Component__Group_3__2__Impl"
    // InternalMyDsl.g:2413:1: rule__Component__Group_3__2__Impl : ( ( rule__Component__UsedPropertiesAssignment_3_2 ) ) ;
    public final void rule__Component__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2417:1: ( ( ( rule__Component__UsedPropertiesAssignment_3_2 ) ) )
            // InternalMyDsl.g:2418:1: ( ( rule__Component__UsedPropertiesAssignment_3_2 ) )
            {
            // InternalMyDsl.g:2418:1: ( ( rule__Component__UsedPropertiesAssignment_3_2 ) )
            // InternalMyDsl.g:2419:2: ( rule__Component__UsedPropertiesAssignment_3_2 )
            {
             before(grammarAccess.getComponentAccess().getUsedPropertiesAssignment_3_2()); 
            // InternalMyDsl.g:2420:2: ( rule__Component__UsedPropertiesAssignment_3_2 )
            // InternalMyDsl.g:2420:3: rule__Component__UsedPropertiesAssignment_3_2
            {
            pushFollow(FOLLOW_2);
            rule__Component__UsedPropertiesAssignment_3_2();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getUsedPropertiesAssignment_3_2()); 

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
    // $ANTLR end "rule__Component__Group_3__2__Impl"


    // $ANTLR start "rule__Component__Group_5__0"
    // InternalMyDsl.g:2429:1: rule__Component__Group_5__0 : rule__Component__Group_5__0__Impl rule__Component__Group_5__1 ;
    public final void rule__Component__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2433:1: ( rule__Component__Group_5__0__Impl rule__Component__Group_5__1 )
            // InternalMyDsl.g:2434:2: rule__Component__Group_5__0__Impl rule__Component__Group_5__1
            {
            pushFollow(FOLLOW_18);
            rule__Component__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group_5__1();

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
    // $ANTLR end "rule__Component__Group_5__0"


    // $ANTLR start "rule__Component__Group_5__0__Impl"
    // InternalMyDsl.g:2441:1: rule__Component__Group_5__0__Impl : ( 'using' ) ;
    public final void rule__Component__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2445:1: ( ( 'using' ) )
            // InternalMyDsl.g:2446:1: ( 'using' )
            {
            // InternalMyDsl.g:2446:1: ( 'using' )
            // InternalMyDsl.g:2447:2: 'using'
            {
             before(grammarAccess.getComponentAccess().getUsingKeyword_5_0()); 
            match(input,26,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getUsingKeyword_5_0()); 

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
    // $ANTLR end "rule__Component__Group_5__0__Impl"


    // $ANTLR start "rule__Component__Group_5__1"
    // InternalMyDsl.g:2456:1: rule__Component__Group_5__1 : rule__Component__Group_5__1__Impl rule__Component__Group_5__2 ;
    public final void rule__Component__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2460:1: ( rule__Component__Group_5__1__Impl rule__Component__Group_5__2 )
            // InternalMyDsl.g:2461:2: rule__Component__Group_5__1__Impl rule__Component__Group_5__2
            {
            pushFollow(FOLLOW_14);
            rule__Component__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component__Group_5__2();

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
    // $ANTLR end "rule__Component__Group_5__1"


    // $ANTLR start "rule__Component__Group_5__1__Impl"
    // InternalMyDsl.g:2468:1: rule__Component__Group_5__1__Impl : ( 'property' ) ;
    public final void rule__Component__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2472:1: ( ( 'property' ) )
            // InternalMyDsl.g:2473:1: ( 'property' )
            {
            // InternalMyDsl.g:2473:1: ( 'property' )
            // InternalMyDsl.g:2474:2: 'property'
            {
             before(grammarAccess.getComponentAccess().getPropertyKeyword_5_1()); 
            match(input,27,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getPropertyKeyword_5_1()); 

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
    // $ANTLR end "rule__Component__Group_5__1__Impl"


    // $ANTLR start "rule__Component__Group_5__2"
    // InternalMyDsl.g:2483:1: rule__Component__Group_5__2 : rule__Component__Group_5__2__Impl ;
    public final void rule__Component__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2487:1: ( rule__Component__Group_5__2__Impl )
            // InternalMyDsl.g:2488:2: rule__Component__Group_5__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component__Group_5__2__Impl();

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
    // $ANTLR end "rule__Component__Group_5__2"


    // $ANTLR start "rule__Component__Group_5__2__Impl"
    // InternalMyDsl.g:2494:1: rule__Component__Group_5__2__Impl : ( ( rule__Component__UsedPropertiesAssignment_5_2 ) ) ;
    public final void rule__Component__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2498:1: ( ( ( rule__Component__UsedPropertiesAssignment_5_2 ) ) )
            // InternalMyDsl.g:2499:1: ( ( rule__Component__UsedPropertiesAssignment_5_2 ) )
            {
            // InternalMyDsl.g:2499:1: ( ( rule__Component__UsedPropertiesAssignment_5_2 ) )
            // InternalMyDsl.g:2500:2: ( rule__Component__UsedPropertiesAssignment_5_2 )
            {
             before(grammarAccess.getComponentAccess().getUsedPropertiesAssignment_5_2()); 
            // InternalMyDsl.g:2501:2: ( rule__Component__UsedPropertiesAssignment_5_2 )
            // InternalMyDsl.g:2501:3: rule__Component__UsedPropertiesAssignment_5_2
            {
            pushFollow(FOLLOW_2);
            rule__Component__UsedPropertiesAssignment_5_2();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getUsedPropertiesAssignment_5_2()); 

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
    // $ANTLR end "rule__Component__Group_5__2__Impl"


    // $ANTLR start "rule__Implementation__Group__0"
    // InternalMyDsl.g:2510:1: rule__Implementation__Group__0 : rule__Implementation__Group__0__Impl rule__Implementation__Group__1 ;
    public final void rule__Implementation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2514:1: ( rule__Implementation__Group__0__Impl rule__Implementation__Group__1 )
            // InternalMyDsl.g:2515:2: rule__Implementation__Group__0__Impl rule__Implementation__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Implementation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Implementation__Group__1();

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
    // $ANTLR end "rule__Implementation__Group__0"


    // $ANTLR start "rule__Implementation__Group__0__Impl"
    // InternalMyDsl.g:2522:1: rule__Implementation__Group__0__Impl : ( 'contract' ) ;
    public final void rule__Implementation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2526:1: ( ( 'contract' ) )
            // InternalMyDsl.g:2527:1: ( 'contract' )
            {
            // InternalMyDsl.g:2527:1: ( 'contract' )
            // InternalMyDsl.g:2528:2: 'contract'
            {
             before(grammarAccess.getImplementationAccess().getContractKeyword_0()); 
            match(input,31,FOLLOW_2); 
             after(grammarAccess.getImplementationAccess().getContractKeyword_0()); 

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
    // $ANTLR end "rule__Implementation__Group__0__Impl"


    // $ANTLR start "rule__Implementation__Group__1"
    // InternalMyDsl.g:2537:1: rule__Implementation__Group__1 : rule__Implementation__Group__1__Impl rule__Implementation__Group__2 ;
    public final void rule__Implementation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2541:1: ( rule__Implementation__Group__1__Impl rule__Implementation__Group__2 )
            // InternalMyDsl.g:2542:2: rule__Implementation__Group__1__Impl rule__Implementation__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__Implementation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Implementation__Group__2();

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
    // $ANTLR end "rule__Implementation__Group__1"


    // $ANTLR start "rule__Implementation__Group__1__Impl"
    // InternalMyDsl.g:2549:1: rule__Implementation__Group__1__Impl : ( ( rule__Implementation__NameAssignment_1 ) ) ;
    public final void rule__Implementation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2553:1: ( ( ( rule__Implementation__NameAssignment_1 ) ) )
            // InternalMyDsl.g:2554:1: ( ( rule__Implementation__NameAssignment_1 ) )
            {
            // InternalMyDsl.g:2554:1: ( ( rule__Implementation__NameAssignment_1 ) )
            // InternalMyDsl.g:2555:2: ( rule__Implementation__NameAssignment_1 )
            {
             before(grammarAccess.getImplementationAccess().getNameAssignment_1()); 
            // InternalMyDsl.g:2556:2: ( rule__Implementation__NameAssignment_1 )
            // InternalMyDsl.g:2556:3: rule__Implementation__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Implementation__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getImplementationAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Implementation__Group__1__Impl"


    // $ANTLR start "rule__Implementation__Group__2"
    // InternalMyDsl.g:2564:1: rule__Implementation__Group__2 : rule__Implementation__Group__2__Impl rule__Implementation__Group__3 ;
    public final void rule__Implementation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2568:1: ( rule__Implementation__Group__2__Impl rule__Implementation__Group__3 )
            // InternalMyDsl.g:2569:2: rule__Implementation__Group__2__Impl rule__Implementation__Group__3
            {
            pushFollow(FOLLOW_30);
            rule__Implementation__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Implementation__Group__3();

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
    // $ANTLR end "rule__Implementation__Group__2"


    // $ANTLR start "rule__Implementation__Group__2__Impl"
    // InternalMyDsl.g:2576:1: rule__Implementation__Group__2__Impl : ( '{' ) ;
    public final void rule__Implementation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2580:1: ( ( '{' ) )
            // InternalMyDsl.g:2581:1: ( '{' )
            {
            // InternalMyDsl.g:2581:1: ( '{' )
            // InternalMyDsl.g:2582:2: '{'
            {
             before(grammarAccess.getImplementationAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getImplementationAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__Implementation__Group__2__Impl"


    // $ANTLR start "rule__Implementation__Group__3"
    // InternalMyDsl.g:2591:1: rule__Implementation__Group__3 : rule__Implementation__Group__3__Impl rule__Implementation__Group__4 ;
    public final void rule__Implementation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2595:1: ( rule__Implementation__Group__3__Impl rule__Implementation__Group__4 )
            // InternalMyDsl.g:2596:2: rule__Implementation__Group__3__Impl rule__Implementation__Group__4
            {
            pushFollow(FOLLOW_30);
            rule__Implementation__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Implementation__Group__4();

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
    // $ANTLR end "rule__Implementation__Group__3"


    // $ANTLR start "rule__Implementation__Group__3__Impl"
    // InternalMyDsl.g:2603:1: rule__Implementation__Group__3__Impl : ( ( rule__Implementation__ComponentRequirementsAssignment_3 )* ) ;
    public final void rule__Implementation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2607:1: ( ( ( rule__Implementation__ComponentRequirementsAssignment_3 )* ) )
            // InternalMyDsl.g:2608:1: ( ( rule__Implementation__ComponentRequirementsAssignment_3 )* )
            {
            // InternalMyDsl.g:2608:1: ( ( rule__Implementation__ComponentRequirementsAssignment_3 )* )
            // InternalMyDsl.g:2609:2: ( rule__Implementation__ComponentRequirementsAssignment_3 )*
            {
             before(grammarAccess.getImplementationAccess().getComponentRequirementsAssignment_3()); 
            // InternalMyDsl.g:2610:2: ( rule__Implementation__ComponentRequirementsAssignment_3 )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==32) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalMyDsl.g:2610:3: rule__Implementation__ComponentRequirementsAssignment_3
            	    {
            	    pushFollow(FOLLOW_31);
            	    rule__Implementation__ComponentRequirementsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);

             after(grammarAccess.getImplementationAccess().getComponentRequirementsAssignment_3()); 

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
    // $ANTLR end "rule__Implementation__Group__3__Impl"


    // $ANTLR start "rule__Implementation__Group__4"
    // InternalMyDsl.g:2618:1: rule__Implementation__Group__4 : rule__Implementation__Group__4__Impl rule__Implementation__Group__5 ;
    public final void rule__Implementation__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2622:1: ( rule__Implementation__Group__4__Impl rule__Implementation__Group__5 )
            // InternalMyDsl.g:2623:2: rule__Implementation__Group__4__Impl rule__Implementation__Group__5
            {
            pushFollow(FOLLOW_32);
            rule__Implementation__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Implementation__Group__5();

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
    // $ANTLR end "rule__Implementation__Group__4"


    // $ANTLR start "rule__Implementation__Group__4__Impl"
    // InternalMyDsl.g:2630:1: rule__Implementation__Group__4__Impl : ( ( ( rule__Implementation__ResourceRequirementsAssignment_4 ) ) ( ( rule__Implementation__ResourceRequirementsAssignment_4 )* ) ) ;
    public final void rule__Implementation__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2634:1: ( ( ( ( rule__Implementation__ResourceRequirementsAssignment_4 ) ) ( ( rule__Implementation__ResourceRequirementsAssignment_4 )* ) ) )
            // InternalMyDsl.g:2635:1: ( ( ( rule__Implementation__ResourceRequirementsAssignment_4 ) ) ( ( rule__Implementation__ResourceRequirementsAssignment_4 )* ) )
            {
            // InternalMyDsl.g:2635:1: ( ( ( rule__Implementation__ResourceRequirementsAssignment_4 ) ) ( ( rule__Implementation__ResourceRequirementsAssignment_4 )* ) )
            // InternalMyDsl.g:2636:2: ( ( rule__Implementation__ResourceRequirementsAssignment_4 ) ) ( ( rule__Implementation__ResourceRequirementsAssignment_4 )* )
            {
            // InternalMyDsl.g:2636:2: ( ( rule__Implementation__ResourceRequirementsAssignment_4 ) )
            // InternalMyDsl.g:2637:3: ( rule__Implementation__ResourceRequirementsAssignment_4 )
            {
             before(grammarAccess.getImplementationAccess().getResourceRequirementsAssignment_4()); 
            // InternalMyDsl.g:2638:3: ( rule__Implementation__ResourceRequirementsAssignment_4 )
            // InternalMyDsl.g:2638:4: rule__Implementation__ResourceRequirementsAssignment_4
            {
            pushFollow(FOLLOW_33);
            rule__Implementation__ResourceRequirementsAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getImplementationAccess().getResourceRequirementsAssignment_4()); 

            }

            // InternalMyDsl.g:2641:2: ( ( rule__Implementation__ResourceRequirementsAssignment_4 )* )
            // InternalMyDsl.g:2642:3: ( rule__Implementation__ResourceRequirementsAssignment_4 )*
            {
             before(grammarAccess.getImplementationAccess().getResourceRequirementsAssignment_4()); 
            // InternalMyDsl.g:2643:3: ( rule__Implementation__ResourceRequirementsAssignment_4 )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==RULE_ID||LA26_0==34) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalMyDsl.g:2643:4: rule__Implementation__ResourceRequirementsAssignment_4
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__Implementation__ResourceRequirementsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);

             after(grammarAccess.getImplementationAccess().getResourceRequirementsAssignment_4()); 

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
    // $ANTLR end "rule__Implementation__Group__4__Impl"


    // $ANTLR start "rule__Implementation__Group__5"
    // InternalMyDsl.g:2652:1: rule__Implementation__Group__5 : rule__Implementation__Group__5__Impl rule__Implementation__Group__6 ;
    public final void rule__Implementation__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2656:1: ( rule__Implementation__Group__5__Impl rule__Implementation__Group__6 )
            // InternalMyDsl.g:2657:2: rule__Implementation__Group__5__Impl rule__Implementation__Group__6
            {
            pushFollow(FOLLOW_34);
            rule__Implementation__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Implementation__Group__6();

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
    // $ANTLR end "rule__Implementation__Group__5"


    // $ANTLR start "rule__Implementation__Group__5__Impl"
    // InternalMyDsl.g:2664:1: rule__Implementation__Group__5__Impl : ( ( ( rule__Implementation__ClausesAssignment_5 ) ) ( ( rule__Implementation__ClausesAssignment_5 )* ) ) ;
    public final void rule__Implementation__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2668:1: ( ( ( ( rule__Implementation__ClausesAssignment_5 ) ) ( ( rule__Implementation__ClausesAssignment_5 )* ) ) )
            // InternalMyDsl.g:2669:1: ( ( ( rule__Implementation__ClausesAssignment_5 ) ) ( ( rule__Implementation__ClausesAssignment_5 )* ) )
            {
            // InternalMyDsl.g:2669:1: ( ( ( rule__Implementation__ClausesAssignment_5 ) ) ( ( rule__Implementation__ClausesAssignment_5 )* ) )
            // InternalMyDsl.g:2670:2: ( ( rule__Implementation__ClausesAssignment_5 ) ) ( ( rule__Implementation__ClausesAssignment_5 )* )
            {
            // InternalMyDsl.g:2670:2: ( ( rule__Implementation__ClausesAssignment_5 ) )
            // InternalMyDsl.g:2671:3: ( rule__Implementation__ClausesAssignment_5 )
            {
             before(grammarAccess.getImplementationAccess().getClausesAssignment_5()); 
            // InternalMyDsl.g:2672:3: ( rule__Implementation__ClausesAssignment_5 )
            // InternalMyDsl.g:2672:4: rule__Implementation__ClausesAssignment_5
            {
            pushFollow(FOLLOW_35);
            rule__Implementation__ClausesAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getImplementationAccess().getClausesAssignment_5()); 

            }

            // InternalMyDsl.g:2675:2: ( ( rule__Implementation__ClausesAssignment_5 )* )
            // InternalMyDsl.g:2676:3: ( rule__Implementation__ClausesAssignment_5 )*
            {
             before(grammarAccess.getImplementationAccess().getClausesAssignment_5()); 
            // InternalMyDsl.g:2677:3: ( rule__Implementation__ClausesAssignment_5 )*
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>=12 && LA27_0<=13)) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalMyDsl.g:2677:4: rule__Implementation__ClausesAssignment_5
            	    {
            	    pushFollow(FOLLOW_35);
            	    rule__Implementation__ClausesAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop27;
                }
            } while (true);

             after(grammarAccess.getImplementationAccess().getClausesAssignment_5()); 

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
    // $ANTLR end "rule__Implementation__Group__5__Impl"


    // $ANTLR start "rule__Implementation__Group__6"
    // InternalMyDsl.g:2686:1: rule__Implementation__Group__6 : rule__Implementation__Group__6__Impl ;
    public final void rule__Implementation__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2690:1: ( rule__Implementation__Group__6__Impl )
            // InternalMyDsl.g:2691:2: rule__Implementation__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Implementation__Group__6__Impl();

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
    // $ANTLR end "rule__Implementation__Group__6"


    // $ANTLR start "rule__Implementation__Group__6__Impl"
    // InternalMyDsl.g:2697:1: rule__Implementation__Group__6__Impl : ( '}' ) ;
    public final void rule__Implementation__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2701:1: ( ( '}' ) )
            // InternalMyDsl.g:2702:1: ( '}' )
            {
            // InternalMyDsl.g:2702:1: ( '}' )
            // InternalMyDsl.g:2703:2: '}'
            {
             before(grammarAccess.getImplementationAccess().getRightCurlyBracketKeyword_6()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getImplementationAccess().getRightCurlyBracketKeyword_6()); 

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
    // $ANTLR end "rule__Implementation__Group__6__Impl"


    // $ANTLR start "rule__ComponentRequirement__Group__0"
    // InternalMyDsl.g:2713:1: rule__ComponentRequirement__Group__0 : rule__ComponentRequirement__Group__0__Impl rule__ComponentRequirement__Group__1 ;
    public final void rule__ComponentRequirement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2717:1: ( rule__ComponentRequirement__Group__0__Impl rule__ComponentRequirement__Group__1 )
            // InternalMyDsl.g:2718:2: rule__ComponentRequirement__Group__0__Impl rule__ComponentRequirement__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__ComponentRequirement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__Group__1();

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
    // $ANTLR end "rule__ComponentRequirement__Group__0"


    // $ANTLR start "rule__ComponentRequirement__Group__0__Impl"
    // InternalMyDsl.g:2725:1: rule__ComponentRequirement__Group__0__Impl : ( 'requires component' ) ;
    public final void rule__ComponentRequirement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2729:1: ( ( 'requires component' ) )
            // InternalMyDsl.g:2730:1: ( 'requires component' )
            {
            // InternalMyDsl.g:2730:1: ( 'requires component' )
            // InternalMyDsl.g:2731:2: 'requires component'
            {
             before(grammarAccess.getComponentRequirementAccess().getRequiresComponentKeyword_0()); 
            match(input,32,FOLLOW_2); 
             after(grammarAccess.getComponentRequirementAccess().getRequiresComponentKeyword_0()); 

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
    // $ANTLR end "rule__ComponentRequirement__Group__0__Impl"


    // $ANTLR start "rule__ComponentRequirement__Group__1"
    // InternalMyDsl.g:2740:1: rule__ComponentRequirement__Group__1 : rule__ComponentRequirement__Group__1__Impl rule__ComponentRequirement__Group__2 ;
    public final void rule__ComponentRequirement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2744:1: ( rule__ComponentRequirement__Group__1__Impl rule__ComponentRequirement__Group__2 )
            // InternalMyDsl.g:2745:2: rule__ComponentRequirement__Group__1__Impl rule__ComponentRequirement__Group__2
            {
            pushFollow(FOLLOW_36);
            rule__ComponentRequirement__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__Group__2();

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
    // $ANTLR end "rule__ComponentRequirement__Group__1"


    // $ANTLR start "rule__ComponentRequirement__Group__1__Impl"
    // InternalMyDsl.g:2752:1: rule__ComponentRequirement__Group__1__Impl : ( ( rule__ComponentRequirement__InstanceAssignment_1 ) ) ;
    public final void rule__ComponentRequirement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2756:1: ( ( ( rule__ComponentRequirement__InstanceAssignment_1 ) ) )
            // InternalMyDsl.g:2757:1: ( ( rule__ComponentRequirement__InstanceAssignment_1 ) )
            {
            // InternalMyDsl.g:2757:1: ( ( rule__ComponentRequirement__InstanceAssignment_1 ) )
            // InternalMyDsl.g:2758:2: ( rule__ComponentRequirement__InstanceAssignment_1 )
            {
             before(grammarAccess.getComponentRequirementAccess().getInstanceAssignment_1()); 
            // InternalMyDsl.g:2759:2: ( rule__ComponentRequirement__InstanceAssignment_1 )
            // InternalMyDsl.g:2759:3: rule__ComponentRequirement__InstanceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__InstanceAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComponentRequirementAccess().getInstanceAssignment_1()); 

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
    // $ANTLR end "rule__ComponentRequirement__Group__1__Impl"


    // $ANTLR start "rule__ComponentRequirement__Group__2"
    // InternalMyDsl.g:2767:1: rule__ComponentRequirement__Group__2 : rule__ComponentRequirement__Group__2__Impl rule__ComponentRequirement__Group__3 ;
    public final void rule__ComponentRequirement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2771:1: ( rule__ComponentRequirement__Group__2__Impl rule__ComponentRequirement__Group__3 )
            // InternalMyDsl.g:2772:2: rule__ComponentRequirement__Group__2__Impl rule__ComponentRequirement__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__ComponentRequirement__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__Group__3();

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
    // $ANTLR end "rule__ComponentRequirement__Group__2"


    // $ANTLR start "rule__ComponentRequirement__Group__2__Impl"
    // InternalMyDsl.g:2779:1: rule__ComponentRequirement__Group__2__Impl : ( 'of' ) ;
    public final void rule__ComponentRequirement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2783:1: ( ( 'of' ) )
            // InternalMyDsl.g:2784:1: ( 'of' )
            {
            // InternalMyDsl.g:2784:1: ( 'of' )
            // InternalMyDsl.g:2785:2: 'of'
            {
             before(grammarAccess.getComponentRequirementAccess().getOfKeyword_2()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getComponentRequirementAccess().getOfKeyword_2()); 

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
    // $ANTLR end "rule__ComponentRequirement__Group__2__Impl"


    // $ANTLR start "rule__ComponentRequirement__Group__3"
    // InternalMyDsl.g:2794:1: rule__ComponentRequirement__Group__3 : rule__ComponentRequirement__Group__3__Impl rule__ComponentRequirement__Group__4 ;
    public final void rule__ComponentRequirement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2798:1: ( rule__ComponentRequirement__Group__3__Impl rule__ComponentRequirement__Group__4 )
            // InternalMyDsl.g:2799:2: rule__ComponentRequirement__Group__3__Impl rule__ComponentRequirement__Group__4
            {
            pushFollow(FOLLOW_14);
            rule__ComponentRequirement__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__Group__4();

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
    // $ANTLR end "rule__ComponentRequirement__Group__3"


    // $ANTLR start "rule__ComponentRequirement__Group__3__Impl"
    // InternalMyDsl.g:2806:1: rule__ComponentRequirement__Group__3__Impl : ( 'type' ) ;
    public final void rule__ComponentRequirement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2810:1: ( ( 'type' ) )
            // InternalMyDsl.g:2811:1: ( 'type' )
            {
            // InternalMyDsl.g:2811:1: ( 'type' )
            // InternalMyDsl.g:2812:2: 'type'
            {
             before(grammarAccess.getComponentRequirementAccess().getTypeKeyword_3()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getComponentRequirementAccess().getTypeKeyword_3()); 

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
    // $ANTLR end "rule__ComponentRequirement__Group__3__Impl"


    // $ANTLR start "rule__ComponentRequirement__Group__4"
    // InternalMyDsl.g:2821:1: rule__ComponentRequirement__Group__4 : rule__ComponentRequirement__Group__4__Impl ;
    public final void rule__ComponentRequirement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2825:1: ( rule__ComponentRequirement__Group__4__Impl )
            // InternalMyDsl.g:2826:2: rule__ComponentRequirement__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__Group__4__Impl();

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
    // $ANTLR end "rule__ComponentRequirement__Group__4"


    // $ANTLR start "rule__ComponentRequirement__Group__4__Impl"
    // InternalMyDsl.g:2832:1: rule__ComponentRequirement__Group__4__Impl : ( ( rule__ComponentRequirement__TypeAssignment_4 ) ) ;
    public final void rule__ComponentRequirement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2836:1: ( ( ( rule__ComponentRequirement__TypeAssignment_4 ) ) )
            // InternalMyDsl.g:2837:1: ( ( rule__ComponentRequirement__TypeAssignment_4 ) )
            {
            // InternalMyDsl.g:2837:1: ( ( rule__ComponentRequirement__TypeAssignment_4 ) )
            // InternalMyDsl.g:2838:2: ( rule__ComponentRequirement__TypeAssignment_4 )
            {
             before(grammarAccess.getComponentRequirementAccess().getTypeAssignment_4()); 
            // InternalMyDsl.g:2839:2: ( rule__ComponentRequirement__TypeAssignment_4 )
            // InternalMyDsl.g:2839:3: rule__ComponentRequirement__TypeAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__ComponentRequirement__TypeAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getComponentRequirementAccess().getTypeAssignment_4()); 

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
    // $ANTLR end "rule__ComponentRequirement__Group__4__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group__0"
    // InternalMyDsl.g:2848:1: rule__ResourceRequirement__Group__0 : rule__ResourceRequirement__Group__0__Impl rule__ResourceRequirement__Group__1 ;
    public final void rule__ResourceRequirement__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2852:1: ( rule__ResourceRequirement__Group__0__Impl rule__ResourceRequirement__Group__1 )
            // InternalMyDsl.g:2853:2: rule__ResourceRequirement__Group__0__Impl rule__ResourceRequirement__Group__1
            {
            pushFollow(FOLLOW_30);
            rule__ResourceRequirement__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__1();

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
    // $ANTLR end "rule__ResourceRequirement__Group__0"


    // $ANTLR start "rule__ResourceRequirement__Group__0__Impl"
    // InternalMyDsl.g:2860:1: rule__ResourceRequirement__Group__0__Impl : ( ( 'requires resource' )? ) ;
    public final void rule__ResourceRequirement__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2864:1: ( ( ( 'requires resource' )? ) )
            // InternalMyDsl.g:2865:1: ( ( 'requires resource' )? )
            {
            // InternalMyDsl.g:2865:1: ( ( 'requires resource' )? )
            // InternalMyDsl.g:2866:2: ( 'requires resource' )?
            {
             before(grammarAccess.getResourceRequirementAccess().getRequiresResourceKeyword_0()); 
            // InternalMyDsl.g:2867:2: ( 'requires resource' )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==34) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // InternalMyDsl.g:2867:3: 'requires resource'
                    {
                    match(input,34,FOLLOW_2); 

                    }
                    break;

            }

             after(grammarAccess.getResourceRequirementAccess().getRequiresResourceKeyword_0()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group__0__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group__1"
    // InternalMyDsl.g:2875:1: rule__ResourceRequirement__Group__1 : rule__ResourceRequirement__Group__1__Impl rule__ResourceRequirement__Group__2 ;
    public final void rule__ResourceRequirement__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2879:1: ( rule__ResourceRequirement__Group__1__Impl rule__ResourceRequirement__Group__2 )
            // InternalMyDsl.g:2880:2: rule__ResourceRequirement__Group__1__Impl rule__ResourceRequirement__Group__2
            {
            pushFollow(FOLLOW_36);
            rule__ResourceRequirement__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__2();

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
    // $ANTLR end "rule__ResourceRequirement__Group__1"


    // $ANTLR start "rule__ResourceRequirement__Group__1__Impl"
    // InternalMyDsl.g:2887:1: rule__ResourceRequirement__Group__1__Impl : ( ( rule__ResourceRequirement__InstanceAssignment_1 ) ) ;
    public final void rule__ResourceRequirement__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2891:1: ( ( ( rule__ResourceRequirement__InstanceAssignment_1 ) ) )
            // InternalMyDsl.g:2892:1: ( ( rule__ResourceRequirement__InstanceAssignment_1 ) )
            {
            // InternalMyDsl.g:2892:1: ( ( rule__ResourceRequirement__InstanceAssignment_1 ) )
            // InternalMyDsl.g:2893:2: ( rule__ResourceRequirement__InstanceAssignment_1 )
            {
             before(grammarAccess.getResourceRequirementAccess().getInstanceAssignment_1()); 
            // InternalMyDsl.g:2894:2: ( rule__ResourceRequirement__InstanceAssignment_1 )
            // InternalMyDsl.g:2894:3: rule__ResourceRequirement__InstanceAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__InstanceAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceRequirementAccess().getInstanceAssignment_1()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group__1__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group__2"
    // InternalMyDsl.g:2902:1: rule__ResourceRequirement__Group__2 : rule__ResourceRequirement__Group__2__Impl rule__ResourceRequirement__Group__3 ;
    public final void rule__ResourceRequirement__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2906:1: ( rule__ResourceRequirement__Group__2__Impl rule__ResourceRequirement__Group__3 )
            // InternalMyDsl.g:2907:2: rule__ResourceRequirement__Group__2__Impl rule__ResourceRequirement__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__ResourceRequirement__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__3();

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
    // $ANTLR end "rule__ResourceRequirement__Group__2"


    // $ANTLR start "rule__ResourceRequirement__Group__2__Impl"
    // InternalMyDsl.g:2914:1: rule__ResourceRequirement__Group__2__Impl : ( 'of' ) ;
    public final void rule__ResourceRequirement__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2918:1: ( ( 'of' ) )
            // InternalMyDsl.g:2919:1: ( 'of' )
            {
            // InternalMyDsl.g:2919:1: ( 'of' )
            // InternalMyDsl.g:2920:2: 'of'
            {
             before(grammarAccess.getResourceRequirementAccess().getOfKeyword_2()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getResourceRequirementAccess().getOfKeyword_2()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group__2__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group__3"
    // InternalMyDsl.g:2929:1: rule__ResourceRequirement__Group__3 : rule__ResourceRequirement__Group__3__Impl rule__ResourceRequirement__Group__4 ;
    public final void rule__ResourceRequirement__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2933:1: ( rule__ResourceRequirement__Group__3__Impl rule__ResourceRequirement__Group__4 )
            // InternalMyDsl.g:2934:2: rule__ResourceRequirement__Group__3__Impl rule__ResourceRequirement__Group__4
            {
            pushFollow(FOLLOW_14);
            rule__ResourceRequirement__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__4();

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
    // $ANTLR end "rule__ResourceRequirement__Group__3"


    // $ANTLR start "rule__ResourceRequirement__Group__3__Impl"
    // InternalMyDsl.g:2941:1: rule__ResourceRequirement__Group__3__Impl : ( 'type' ) ;
    public final void rule__ResourceRequirement__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2945:1: ( ( 'type' ) )
            // InternalMyDsl.g:2946:1: ( 'type' )
            {
            // InternalMyDsl.g:2946:1: ( 'type' )
            // InternalMyDsl.g:2947:2: 'type'
            {
             before(grammarAccess.getResourceRequirementAccess().getTypeKeyword_3()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getResourceRequirementAccess().getTypeKeyword_3()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group__3__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group__4"
    // InternalMyDsl.g:2956:1: rule__ResourceRequirement__Group__4 : rule__ResourceRequirement__Group__4__Impl rule__ResourceRequirement__Group__5 ;
    public final void rule__ResourceRequirement__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2960:1: ( rule__ResourceRequirement__Group__4__Impl rule__ResourceRequirement__Group__5 )
            // InternalMyDsl.g:2961:2: rule__ResourceRequirement__Group__4__Impl rule__ResourceRequirement__Group__5
            {
            pushFollow(FOLLOW_37);
            rule__ResourceRequirement__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__5();

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
    // $ANTLR end "rule__ResourceRequirement__Group__4"


    // $ANTLR start "rule__ResourceRequirement__Group__4__Impl"
    // InternalMyDsl.g:2968:1: rule__ResourceRequirement__Group__4__Impl : ( ( rule__ResourceRequirement__TypeAssignment_4 ) ) ;
    public final void rule__ResourceRequirement__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2972:1: ( ( ( rule__ResourceRequirement__TypeAssignment_4 ) ) )
            // InternalMyDsl.g:2973:1: ( ( rule__ResourceRequirement__TypeAssignment_4 ) )
            {
            // InternalMyDsl.g:2973:1: ( ( rule__ResourceRequirement__TypeAssignment_4 ) )
            // InternalMyDsl.g:2974:2: ( rule__ResourceRequirement__TypeAssignment_4 )
            {
             before(grammarAccess.getResourceRequirementAccess().getTypeAssignment_4()); 
            // InternalMyDsl.g:2975:2: ( rule__ResourceRequirement__TypeAssignment_4 )
            // InternalMyDsl.g:2975:3: rule__ResourceRequirement__TypeAssignment_4
            {
            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__TypeAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getResourceRequirementAccess().getTypeAssignment_4()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group__4__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group__5"
    // InternalMyDsl.g:2983:1: rule__ResourceRequirement__Group__5 : rule__ResourceRequirement__Group__5__Impl ;
    public final void rule__ResourceRequirement__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2987:1: ( rule__ResourceRequirement__Group__5__Impl )
            // InternalMyDsl.g:2988:2: rule__ResourceRequirement__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group__5__Impl();

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
    // $ANTLR end "rule__ResourceRequirement__Group__5"


    // $ANTLR start "rule__ResourceRequirement__Group__5__Impl"
    // InternalMyDsl.g:2994:1: rule__ResourceRequirement__Group__5__Impl : ( ( rule__ResourceRequirement__Group_5__0 )? ) ;
    public final void rule__ResourceRequirement__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:2998:1: ( ( ( rule__ResourceRequirement__Group_5__0 )? ) )
            // InternalMyDsl.g:2999:1: ( ( rule__ResourceRequirement__Group_5__0 )? )
            {
            // InternalMyDsl.g:2999:1: ( ( rule__ResourceRequirement__Group_5__0 )? )
            // InternalMyDsl.g:3000:2: ( rule__ResourceRequirement__Group_5__0 )?
            {
             before(grammarAccess.getResourceRequirementAccess().getGroup_5()); 
            // InternalMyDsl.g:3001:2: ( rule__ResourceRequirement__Group_5__0 )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==35) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalMyDsl.g:3001:3: rule__ResourceRequirement__Group_5__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResourceRequirement__Group_5__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getResourceRequirementAccess().getGroup_5()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group__5__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group_5__0"
    // InternalMyDsl.g:3010:1: rule__ResourceRequirement__Group_5__0 : rule__ResourceRequirement__Group_5__0__Impl rule__ResourceRequirement__Group_5__1 ;
    public final void rule__ResourceRequirement__Group_5__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3014:1: ( rule__ResourceRequirement__Group_5__0__Impl rule__ResourceRequirement__Group_5__1 )
            // InternalMyDsl.g:3015:2: rule__ResourceRequirement__Group_5__0__Impl rule__ResourceRequirement__Group_5__1
            {
            pushFollow(FOLLOW_15);
            rule__ResourceRequirement__Group_5__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group_5__1();

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__0"


    // $ANTLR start "rule__ResourceRequirement__Group_5__0__Impl"
    // InternalMyDsl.g:3022:1: rule__ResourceRequirement__Group_5__0__Impl : ( 'with' ) ;
    public final void rule__ResourceRequirement__Group_5__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3026:1: ( ( 'with' ) )
            // InternalMyDsl.g:3027:1: ( 'with' )
            {
            // InternalMyDsl.g:3027:1: ( 'with' )
            // InternalMyDsl.g:3028:2: 'with'
            {
             before(grammarAccess.getResourceRequirementAccess().getWithKeyword_5_0()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getResourceRequirementAccess().getWithKeyword_5_0()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__0__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group_5__1"
    // InternalMyDsl.g:3037:1: rule__ResourceRequirement__Group_5__1 : rule__ResourceRequirement__Group_5__1__Impl rule__ResourceRequirement__Group_5__2 ;
    public final void rule__ResourceRequirement__Group_5__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3041:1: ( rule__ResourceRequirement__Group_5__1__Impl rule__ResourceRequirement__Group_5__2 )
            // InternalMyDsl.g:3042:2: rule__ResourceRequirement__Group_5__1__Impl rule__ResourceRequirement__Group_5__2
            {
            pushFollow(FOLLOW_30);
            rule__ResourceRequirement__Group_5__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group_5__2();

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__1"


    // $ANTLR start "rule__ResourceRequirement__Group_5__1__Impl"
    // InternalMyDsl.g:3049:1: rule__ResourceRequirement__Group_5__1__Impl : ( '{' ) ;
    public final void rule__ResourceRequirement__Group_5__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3053:1: ( ( '{' ) )
            // InternalMyDsl.g:3054:1: ( '{' )
            {
            // InternalMyDsl.g:3054:1: ( '{' )
            // InternalMyDsl.g:3055:2: '{'
            {
             before(grammarAccess.getResourceRequirementAccess().getLeftCurlyBracketKeyword_5_1()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getResourceRequirementAccess().getLeftCurlyBracketKeyword_5_1()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__1__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group_5__2"
    // InternalMyDsl.g:3064:1: rule__ResourceRequirement__Group_5__2 : rule__ResourceRequirement__Group_5__2__Impl rule__ResourceRequirement__Group_5__3 ;
    public final void rule__ResourceRequirement__Group_5__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3068:1: ( rule__ResourceRequirement__Group_5__2__Impl rule__ResourceRequirement__Group_5__3 )
            // InternalMyDsl.g:3069:2: rule__ResourceRequirement__Group_5__2__Impl rule__ResourceRequirement__Group_5__3
            {
            pushFollow(FOLLOW_34);
            rule__ResourceRequirement__Group_5__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group_5__3();

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__2"


    // $ANTLR start "rule__ResourceRequirement__Group_5__2__Impl"
    // InternalMyDsl.g:3076:1: rule__ResourceRequirement__Group_5__2__Impl : ( ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 ) ) ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )* ) ) ;
    public final void rule__ResourceRequirement__Group_5__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3080:1: ( ( ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 ) ) ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )* ) ) )
            // InternalMyDsl.g:3081:1: ( ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 ) ) ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )* ) )
            {
            // InternalMyDsl.g:3081:1: ( ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 ) ) ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )* ) )
            // InternalMyDsl.g:3082:2: ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 ) ) ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )* )
            {
            // InternalMyDsl.g:3082:2: ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 ) )
            // InternalMyDsl.g:3083:3: ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )
            {
             before(grammarAccess.getResourceRequirementAccess().getResourceRequirementsAssignment_5_2()); 
            // InternalMyDsl.g:3084:3: ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )
            // InternalMyDsl.g:3084:4: rule__ResourceRequirement__ResourceRequirementsAssignment_5_2
            {
            pushFollow(FOLLOW_33);
            rule__ResourceRequirement__ResourceRequirementsAssignment_5_2();

            state._fsp--;


            }

             after(grammarAccess.getResourceRequirementAccess().getResourceRequirementsAssignment_5_2()); 

            }

            // InternalMyDsl.g:3087:2: ( ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )* )
            // InternalMyDsl.g:3088:3: ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )*
            {
             before(grammarAccess.getResourceRequirementAccess().getResourceRequirementsAssignment_5_2()); 
            // InternalMyDsl.g:3089:3: ( rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==RULE_ID||LA30_0==34) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalMyDsl.g:3089:4: rule__ResourceRequirement__ResourceRequirementsAssignment_5_2
            	    {
            	    pushFollow(FOLLOW_33);
            	    rule__ResourceRequirement__ResourceRequirementsAssignment_5_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

             after(grammarAccess.getResourceRequirementAccess().getResourceRequirementsAssignment_5_2()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__2__Impl"


    // $ANTLR start "rule__ResourceRequirement__Group_5__3"
    // InternalMyDsl.g:3098:1: rule__ResourceRequirement__Group_5__3 : rule__ResourceRequirement__Group_5__3__Impl ;
    public final void rule__ResourceRequirement__Group_5__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3102:1: ( rule__ResourceRequirement__Group_5__3__Impl )
            // InternalMyDsl.g:3103:2: rule__ResourceRequirement__Group_5__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceRequirement__Group_5__3__Impl();

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__3"


    // $ANTLR start "rule__ResourceRequirement__Group_5__3__Impl"
    // InternalMyDsl.g:3109:1: rule__ResourceRequirement__Group_5__3__Impl : ( '}' ) ;
    public final void rule__ResourceRequirement__Group_5__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3113:1: ( ( '}' ) )
            // InternalMyDsl.g:3114:1: ( '}' )
            {
            // InternalMyDsl.g:3114:1: ( '}' )
            // InternalMyDsl.g:3115:2: '}'
            {
             before(grammarAccess.getResourceRequirementAccess().getRightCurlyBracketKeyword_5_3()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getResourceRequirementAccess().getRightCurlyBracketKeyword_5_3()); 

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
    // $ANTLR end "rule__ResourceRequirement__Group_5__3__Impl"


    // $ANTLR start "rule__Clause__Group__0"
    // InternalMyDsl.g:3125:1: rule__Clause__Group__0 : rule__Clause__Group__0__Impl rule__Clause__Group__1 ;
    public final void rule__Clause__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3129:1: ( rule__Clause__Group__0__Impl rule__Clause__Group__1 )
            // InternalMyDsl.g:3130:2: rule__Clause__Group__0__Impl rule__Clause__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Clause__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Clause__Group__1();

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
    // $ANTLR end "rule__Clause__Group__0"


    // $ANTLR start "rule__Clause__Group__0__Impl"
    // InternalMyDsl.g:3137:1: rule__Clause__Group__0__Impl : ( ( rule__Clause__TypeAssignment_0 ) ) ;
    public final void rule__Clause__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3141:1: ( ( ( rule__Clause__TypeAssignment_0 ) ) )
            // InternalMyDsl.g:3142:1: ( ( rule__Clause__TypeAssignment_0 ) )
            {
            // InternalMyDsl.g:3142:1: ( ( rule__Clause__TypeAssignment_0 ) )
            // InternalMyDsl.g:3143:2: ( rule__Clause__TypeAssignment_0 )
            {
             before(grammarAccess.getClauseAccess().getTypeAssignment_0()); 
            // InternalMyDsl.g:3144:2: ( rule__Clause__TypeAssignment_0 )
            // InternalMyDsl.g:3144:3: rule__Clause__TypeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Clause__TypeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getClauseAccess().getTypeAssignment_0()); 

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
    // $ANTLR end "rule__Clause__Group__0__Impl"


    // $ANTLR start "rule__Clause__Group__1"
    // InternalMyDsl.g:3152:1: rule__Clause__Group__1 : rule__Clause__Group__1__Impl rule__Clause__Group__2 ;
    public final void rule__Clause__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3156:1: ( rule__Clause__Group__1__Impl rule__Clause__Group__2 )
            // InternalMyDsl.g:3157:2: rule__Clause__Group__1__Impl rule__Clause__Group__2
            {
            pushFollow(FOLLOW_38);
            rule__Clause__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Clause__Group__2();

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
    // $ANTLR end "rule__Clause__Group__1"


    // $ANTLR start "rule__Clause__Group__1__Impl"
    // InternalMyDsl.g:3164:1: rule__Clause__Group__1__Impl : ( ( rule__Clause__LHSAssignment_1 ) ) ;
    public final void rule__Clause__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3168:1: ( ( ( rule__Clause__LHSAssignment_1 ) ) )
            // InternalMyDsl.g:3169:1: ( ( rule__Clause__LHSAssignment_1 ) )
            {
            // InternalMyDsl.g:3169:1: ( ( rule__Clause__LHSAssignment_1 ) )
            // InternalMyDsl.g:3170:2: ( rule__Clause__LHSAssignment_1 )
            {
             before(grammarAccess.getClauseAccess().getLHSAssignment_1()); 
            // InternalMyDsl.g:3171:2: ( rule__Clause__LHSAssignment_1 )
            // InternalMyDsl.g:3171:3: rule__Clause__LHSAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Clause__LHSAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getClauseAccess().getLHSAssignment_1()); 

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
    // $ANTLR end "rule__Clause__Group__1__Impl"


    // $ANTLR start "rule__Clause__Group__2"
    // InternalMyDsl.g:3179:1: rule__Clause__Group__2 : rule__Clause__Group__2__Impl rule__Clause__Group__3 ;
    public final void rule__Clause__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3183:1: ( rule__Clause__Group__2__Impl rule__Clause__Group__3 )
            // InternalMyDsl.g:3184:2: rule__Clause__Group__2__Impl rule__Clause__Group__3
            {
            pushFollow(FOLLOW_39);
            rule__Clause__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Clause__Group__3();

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
    // $ANTLR end "rule__Clause__Group__2"


    // $ANTLR start "rule__Clause__Group__2__Impl"
    // InternalMyDsl.g:3191:1: rule__Clause__Group__2__Impl : ( ( rule__Clause__CompAssignment_2 ) ) ;
    public final void rule__Clause__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3195:1: ( ( ( rule__Clause__CompAssignment_2 ) ) )
            // InternalMyDsl.g:3196:1: ( ( rule__Clause__CompAssignment_2 ) )
            {
            // InternalMyDsl.g:3196:1: ( ( rule__Clause__CompAssignment_2 ) )
            // InternalMyDsl.g:3197:2: ( rule__Clause__CompAssignment_2 )
            {
             before(grammarAccess.getClauseAccess().getCompAssignment_2()); 
            // InternalMyDsl.g:3198:2: ( rule__Clause__CompAssignment_2 )
            // InternalMyDsl.g:3198:3: rule__Clause__CompAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Clause__CompAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getClauseAccess().getCompAssignment_2()); 

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
    // $ANTLR end "rule__Clause__Group__2__Impl"


    // $ANTLR start "rule__Clause__Group__3"
    // InternalMyDsl.g:3206:1: rule__Clause__Group__3 : rule__Clause__Group__3__Impl ;
    public final void rule__Clause__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3210:1: ( rule__Clause__Group__3__Impl )
            // InternalMyDsl.g:3211:2: rule__Clause__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Clause__Group__3__Impl();

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
    // $ANTLR end "rule__Clause__Group__3"


    // $ANTLR start "rule__Clause__Group__3__Impl"
    // InternalMyDsl.g:3217:1: rule__Clause__Group__3__Impl : ( ( rule__Clause__RHSAssignment_3 ) ) ;
    public final void rule__Clause__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3221:1: ( ( ( rule__Clause__RHSAssignment_3 ) ) )
            // InternalMyDsl.g:3222:1: ( ( rule__Clause__RHSAssignment_3 ) )
            {
            // InternalMyDsl.g:3222:1: ( ( rule__Clause__RHSAssignment_3 ) )
            // InternalMyDsl.g:3223:2: ( rule__Clause__RHSAssignment_3 )
            {
             before(grammarAccess.getClauseAccess().getRHSAssignment_3()); 
            // InternalMyDsl.g:3224:2: ( rule__Clause__RHSAssignment_3 )
            // InternalMyDsl.g:3224:3: rule__Clause__RHSAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Clause__RHSAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getClauseAccess().getRHSAssignment_3()); 

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
    // $ANTLR end "rule__Clause__Group__3__Impl"


    // $ANTLR start "rule__Request__Group__0"
    // InternalMyDsl.g:3233:1: rule__Request__Group__0 : rule__Request__Group__0__Impl rule__Request__Group__1 ;
    public final void rule__Request__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3237:1: ( rule__Request__Group__0__Impl rule__Request__Group__1 )
            // InternalMyDsl.g:3238:2: rule__Request__Group__0__Impl rule__Request__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Request__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__1();

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
    // $ANTLR end "rule__Request__Group__0"


    // $ANTLR start "rule__Request__Group__0__Impl"
    // InternalMyDsl.g:3245:1: rule__Request__Group__0__Impl : ( 'request' ) ;
    public final void rule__Request__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3249:1: ( ( 'request' ) )
            // InternalMyDsl.g:3250:1: ( 'request' )
            {
            // InternalMyDsl.g:3250:1: ( 'request' )
            // InternalMyDsl.g:3251:2: 'request'
            {
             before(grammarAccess.getRequestAccess().getRequestKeyword_0()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getRequestAccess().getRequestKeyword_0()); 

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
    // $ANTLR end "rule__Request__Group__0__Impl"


    // $ANTLR start "rule__Request__Group__1"
    // InternalMyDsl.g:3260:1: rule__Request__Group__1 : rule__Request__Group__1__Impl rule__Request__Group__2 ;
    public final void rule__Request__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3264:1: ( rule__Request__Group__1__Impl rule__Request__Group__2 )
            // InternalMyDsl.g:3265:2: rule__Request__Group__1__Impl rule__Request__Group__2
            {
            pushFollow(FOLLOW_40);
            rule__Request__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__2();

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
    // $ANTLR end "rule__Request__Group__1"


    // $ANTLR start "rule__Request__Group__1__Impl"
    // InternalMyDsl.g:3272:1: rule__Request__Group__1__Impl : ( ( rule__Request__NameAssignment_1 ) ) ;
    public final void rule__Request__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3276:1: ( ( ( rule__Request__NameAssignment_1 ) ) )
            // InternalMyDsl.g:3277:1: ( ( rule__Request__NameAssignment_1 ) )
            {
            // InternalMyDsl.g:3277:1: ( ( rule__Request__NameAssignment_1 ) )
            // InternalMyDsl.g:3278:2: ( rule__Request__NameAssignment_1 )
            {
             before(grammarAccess.getRequestAccess().getNameAssignment_1()); 
            // InternalMyDsl.g:3279:2: ( rule__Request__NameAssignment_1 )
            // InternalMyDsl.g:3279:3: rule__Request__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Request__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRequestAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Request__Group__1__Impl"


    // $ANTLR start "rule__Request__Group__2"
    // InternalMyDsl.g:3287:1: rule__Request__Group__2 : rule__Request__Group__2__Impl rule__Request__Group__3 ;
    public final void rule__Request__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3291:1: ( rule__Request__Group__2__Impl rule__Request__Group__3 )
            // InternalMyDsl.g:3292:2: rule__Request__Group__2__Impl rule__Request__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__Request__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__3();

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
    // $ANTLR end "rule__Request__Group__2"


    // $ANTLR start "rule__Request__Group__2__Impl"
    // InternalMyDsl.g:3299:1: rule__Request__Group__2__Impl : ( 'for' ) ;
    public final void rule__Request__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3303:1: ( ( 'for' ) )
            // InternalMyDsl.g:3304:1: ( 'for' )
            {
            // InternalMyDsl.g:3304:1: ( 'for' )
            // InternalMyDsl.g:3305:2: 'for'
            {
             before(grammarAccess.getRequestAccess().getForKeyword_2()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getRequestAccess().getForKeyword_2()); 

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
    // $ANTLR end "rule__Request__Group__2__Impl"


    // $ANTLR start "rule__Request__Group__3"
    // InternalMyDsl.g:3314:1: rule__Request__Group__3 : rule__Request__Group__3__Impl rule__Request__Group__4 ;
    public final void rule__Request__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3318:1: ( rule__Request__Group__3__Impl rule__Request__Group__4 )
            // InternalMyDsl.g:3319:2: rule__Request__Group__3__Impl rule__Request__Group__4
            {
            pushFollow(FOLLOW_15);
            rule__Request__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__4();

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
    // $ANTLR end "rule__Request__Group__3"


    // $ANTLR start "rule__Request__Group__3__Impl"
    // InternalMyDsl.g:3326:1: rule__Request__Group__3__Impl : ( ( rule__Request__ComponentAssignment_3 ) ) ;
    public final void rule__Request__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3330:1: ( ( ( rule__Request__ComponentAssignment_3 ) ) )
            // InternalMyDsl.g:3331:1: ( ( rule__Request__ComponentAssignment_3 ) )
            {
            // InternalMyDsl.g:3331:1: ( ( rule__Request__ComponentAssignment_3 ) )
            // InternalMyDsl.g:3332:2: ( rule__Request__ComponentAssignment_3 )
            {
             before(grammarAccess.getRequestAccess().getComponentAssignment_3()); 
            // InternalMyDsl.g:3333:2: ( rule__Request__ComponentAssignment_3 )
            // InternalMyDsl.g:3333:3: rule__Request__ComponentAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Request__ComponentAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getRequestAccess().getComponentAssignment_3()); 

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
    // $ANTLR end "rule__Request__Group__3__Impl"


    // $ANTLR start "rule__Request__Group__4"
    // InternalMyDsl.g:3341:1: rule__Request__Group__4 : rule__Request__Group__4__Impl rule__Request__Group__5 ;
    public final void rule__Request__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3345:1: ( rule__Request__Group__4__Impl rule__Request__Group__5 )
            // InternalMyDsl.g:3346:2: rule__Request__Group__4__Impl rule__Request__Group__5
            {
            pushFollow(FOLLOW_41);
            rule__Request__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__5();

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
    // $ANTLR end "rule__Request__Group__4"


    // $ANTLR start "rule__Request__Group__4__Impl"
    // InternalMyDsl.g:3353:1: rule__Request__Group__4__Impl : ( '{' ) ;
    public final void rule__Request__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3357:1: ( ( '{' ) )
            // InternalMyDsl.g:3358:1: ( '{' )
            {
            // InternalMyDsl.g:3358:1: ( '{' )
            // InternalMyDsl.g:3359:2: '{'
            {
             before(grammarAccess.getRequestAccess().getLeftCurlyBracketKeyword_4()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getRequestAccess().getLeftCurlyBracketKeyword_4()); 

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
    // $ANTLR end "rule__Request__Group__4__Impl"


    // $ANTLR start "rule__Request__Group__5"
    // InternalMyDsl.g:3368:1: rule__Request__Group__5 : rule__Request__Group__5__Impl rule__Request__Group__6 ;
    public final void rule__Request__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3372:1: ( rule__Request__Group__5__Impl rule__Request__Group__6 )
            // InternalMyDsl.g:3373:2: rule__Request__Group__5__Impl rule__Request__Group__6
            {
            pushFollow(FOLLOW_41);
            rule__Request__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__6();

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
    // $ANTLR end "rule__Request__Group__5"


    // $ANTLR start "rule__Request__Group__5__Impl"
    // InternalMyDsl.g:3380:1: rule__Request__Group__5__Impl : ( ( rule__Request__ParameterAssignmentsAssignment_5 )* ) ;
    public final void rule__Request__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3384:1: ( ( ( rule__Request__ParameterAssignmentsAssignment_5 )* ) )
            // InternalMyDsl.g:3385:1: ( ( rule__Request__ParameterAssignmentsAssignment_5 )* )
            {
            // InternalMyDsl.g:3385:1: ( ( rule__Request__ParameterAssignmentsAssignment_5 )* )
            // InternalMyDsl.g:3386:2: ( rule__Request__ParameterAssignmentsAssignment_5 )*
            {
             before(grammarAccess.getRequestAccess().getParameterAssignmentsAssignment_5()); 
            // InternalMyDsl.g:3387:2: ( rule__Request__ParameterAssignmentsAssignment_5 )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==29) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalMyDsl.g:3387:3: rule__Request__ParameterAssignmentsAssignment_5
            	    {
            	    pushFollow(FOLLOW_42);
            	    rule__Request__ParameterAssignmentsAssignment_5();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

             after(grammarAccess.getRequestAccess().getParameterAssignmentsAssignment_5()); 

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
    // $ANTLR end "rule__Request__Group__5__Impl"


    // $ANTLR start "rule__Request__Group__6"
    // InternalMyDsl.g:3395:1: rule__Request__Group__6 : rule__Request__Group__6__Impl rule__Request__Group__7 ;
    public final void rule__Request__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3399:1: ( rule__Request__Group__6__Impl rule__Request__Group__7 )
            // InternalMyDsl.g:3400:2: rule__Request__Group__6__Impl rule__Request__Group__7
            {
            pushFollow(FOLLOW_34);
            rule__Request__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Request__Group__7();

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
    // $ANTLR end "rule__Request__Group__6"


    // $ANTLR start "rule__Request__Group__6__Impl"
    // InternalMyDsl.g:3407:1: rule__Request__Group__6__Impl : ( ( ( rule__Request__RequirementsAssignment_6 ) ) ( ( rule__Request__RequirementsAssignment_6 )* ) ) ;
    public final void rule__Request__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3411:1: ( ( ( ( rule__Request__RequirementsAssignment_6 ) ) ( ( rule__Request__RequirementsAssignment_6 )* ) ) )
            // InternalMyDsl.g:3412:1: ( ( ( rule__Request__RequirementsAssignment_6 ) ) ( ( rule__Request__RequirementsAssignment_6 )* ) )
            {
            // InternalMyDsl.g:3412:1: ( ( ( rule__Request__RequirementsAssignment_6 ) ) ( ( rule__Request__RequirementsAssignment_6 )* ) )
            // InternalMyDsl.g:3413:2: ( ( rule__Request__RequirementsAssignment_6 ) ) ( ( rule__Request__RequirementsAssignment_6 )* )
            {
            // InternalMyDsl.g:3413:2: ( ( rule__Request__RequirementsAssignment_6 ) )
            // InternalMyDsl.g:3414:3: ( rule__Request__RequirementsAssignment_6 )
            {
             before(grammarAccess.getRequestAccess().getRequirementsAssignment_6()); 
            // InternalMyDsl.g:3415:3: ( rule__Request__RequirementsAssignment_6 )
            // InternalMyDsl.g:3415:4: rule__Request__RequirementsAssignment_6
            {
            pushFollow(FOLLOW_43);
            rule__Request__RequirementsAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getRequestAccess().getRequirementsAssignment_6()); 

            }

            // InternalMyDsl.g:3418:2: ( ( rule__Request__RequirementsAssignment_6 )* )
            // InternalMyDsl.g:3419:3: ( rule__Request__RequirementsAssignment_6 )*
            {
             before(grammarAccess.getRequestAccess().getRequirementsAssignment_6()); 
            // InternalMyDsl.g:3420:3: ( rule__Request__RequirementsAssignment_6 )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( ((LA32_0>=12 && LA32_0<=13)) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // InternalMyDsl.g:3420:4: rule__Request__RequirementsAssignment_6
            	    {
            	    pushFollow(FOLLOW_43);
            	    rule__Request__RequirementsAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

             after(grammarAccess.getRequestAccess().getRequirementsAssignment_6()); 

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
    // $ANTLR end "rule__Request__Group__6__Impl"


    // $ANTLR start "rule__Request__Group__7"
    // InternalMyDsl.g:3429:1: rule__Request__Group__7 : rule__Request__Group__7__Impl ;
    public final void rule__Request__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3433:1: ( rule__Request__Group__7__Impl )
            // InternalMyDsl.g:3434:2: rule__Request__Group__7__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Request__Group__7__Impl();

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
    // $ANTLR end "rule__Request__Group__7"


    // $ANTLR start "rule__Request__Group__7__Impl"
    // InternalMyDsl.g:3440:1: rule__Request__Group__7__Impl : ( '}' ) ;
    public final void rule__Request__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3444:1: ( ( '}' ) )
            // InternalMyDsl.g:3445:1: ( '}' )
            {
            // InternalMyDsl.g:3445:1: ( '}' )
            // InternalMyDsl.g:3446:2: '}'
            {
             before(grammarAccess.getRequestAccess().getRightCurlyBracketKeyword_7()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getRequestAccess().getRightCurlyBracketKeyword_7()); 

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
    // $ANTLR end "rule__Request__Group__7__Impl"


    // $ANTLR start "rule__Objective__Group__0"
    // InternalMyDsl.g:3456:1: rule__Objective__Group__0 : rule__Objective__Group__0__Impl rule__Objective__Group__1 ;
    public final void rule__Objective__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3460:1: ( rule__Objective__Group__0__Impl rule__Objective__Group__1 )
            // InternalMyDsl.g:3461:2: rule__Objective__Group__0__Impl rule__Objective__Group__1
            {
            pushFollow(FOLLOW_44);
            rule__Objective__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Objective__Group__1();

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
    // $ANTLR end "rule__Objective__Group__0"


    // $ANTLR start "rule__Objective__Group__0__Impl"
    // InternalMyDsl.g:3468:1: rule__Objective__Group__0__Impl : ( 'minimize' ) ;
    public final void rule__Objective__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3472:1: ( ( 'minimize' ) )
            // InternalMyDsl.g:3473:1: ( 'minimize' )
            {
            // InternalMyDsl.g:3473:1: ( 'minimize' )
            // InternalMyDsl.g:3474:2: 'minimize'
            {
             before(grammarAccess.getObjectiveAccess().getMinimizeKeyword_0()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getObjectiveAccess().getMinimizeKeyword_0()); 

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
    // $ANTLR end "rule__Objective__Group__0__Impl"


    // $ANTLR start "rule__Objective__Group__1"
    // InternalMyDsl.g:3483:1: rule__Objective__Group__1 : rule__Objective__Group__1__Impl rule__Objective__Group__2 ;
    public final void rule__Objective__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3487:1: ( rule__Objective__Group__1__Impl rule__Objective__Group__2 )
            // InternalMyDsl.g:3488:2: rule__Objective__Group__1__Impl rule__Objective__Group__2
            {
            pushFollow(FOLLOW_45);
            rule__Objective__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Objective__Group__2();

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
    // $ANTLR end "rule__Objective__Group__1"


    // $ANTLR start "rule__Objective__Group__1__Impl"
    // InternalMyDsl.g:3495:1: rule__Objective__Group__1__Impl : ( ( rule__Objective__AggAssignment_1 ) ) ;
    public final void rule__Objective__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3499:1: ( ( ( rule__Objective__AggAssignment_1 ) ) )
            // InternalMyDsl.g:3500:1: ( ( rule__Objective__AggAssignment_1 ) )
            {
            // InternalMyDsl.g:3500:1: ( ( rule__Objective__AggAssignment_1 ) )
            // InternalMyDsl.g:3501:2: ( rule__Objective__AggAssignment_1 )
            {
             before(grammarAccess.getObjectiveAccess().getAggAssignment_1()); 
            // InternalMyDsl.g:3502:2: ( rule__Objective__AggAssignment_1 )
            // InternalMyDsl.g:3502:3: rule__Objective__AggAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Objective__AggAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveAccess().getAggAssignment_1()); 

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
    // $ANTLR end "rule__Objective__Group__1__Impl"


    // $ANTLR start "rule__Objective__Group__2"
    // InternalMyDsl.g:3510:1: rule__Objective__Group__2 : rule__Objective__Group__2__Impl rule__Objective__Group__3 ;
    public final void rule__Objective__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3514:1: ( rule__Objective__Group__2__Impl rule__Objective__Group__3 )
            // InternalMyDsl.g:3515:2: rule__Objective__Group__2__Impl rule__Objective__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__Objective__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Objective__Group__3();

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
    // $ANTLR end "rule__Objective__Group__2"


    // $ANTLR start "rule__Objective__Group__2__Impl"
    // InternalMyDsl.g:3522:1: rule__Objective__Group__2__Impl : ( '(' ) ;
    public final void rule__Objective__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3526:1: ( ( '(' ) )
            // InternalMyDsl.g:3527:1: ( '(' )
            {
            // InternalMyDsl.g:3527:1: ( '(' )
            // InternalMyDsl.g:3528:2: '('
            {
             before(grammarAccess.getObjectiveAccess().getLeftParenthesisKeyword_2()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getObjectiveAccess().getLeftParenthesisKeyword_2()); 

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
    // $ANTLR end "rule__Objective__Group__2__Impl"


    // $ANTLR start "rule__Objective__Group__3"
    // InternalMyDsl.g:3537:1: rule__Objective__Group__3 : rule__Objective__Group__3__Impl rule__Objective__Group__4 ;
    public final void rule__Objective__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3541:1: ( rule__Objective__Group__3__Impl rule__Objective__Group__4 )
            // InternalMyDsl.g:3542:2: rule__Objective__Group__3__Impl rule__Objective__Group__4
            {
            pushFollow(FOLLOW_46);
            rule__Objective__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Objective__Group__4();

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
    // $ANTLR end "rule__Objective__Group__3"


    // $ANTLR start "rule__Objective__Group__3__Impl"
    // InternalMyDsl.g:3549:1: rule__Objective__Group__3__Impl : ( ( rule__Objective__PropertyAssignment_3 ) ) ;
    public final void rule__Objective__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3553:1: ( ( ( rule__Objective__PropertyAssignment_3 ) ) )
            // InternalMyDsl.g:3554:1: ( ( rule__Objective__PropertyAssignment_3 ) )
            {
            // InternalMyDsl.g:3554:1: ( ( rule__Objective__PropertyAssignment_3 ) )
            // InternalMyDsl.g:3555:2: ( rule__Objective__PropertyAssignment_3 )
            {
             before(grammarAccess.getObjectiveAccess().getPropertyAssignment_3()); 
            // InternalMyDsl.g:3556:2: ( rule__Objective__PropertyAssignment_3 )
            // InternalMyDsl.g:3556:3: rule__Objective__PropertyAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__Objective__PropertyAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getObjectiveAccess().getPropertyAssignment_3()); 

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
    // $ANTLR end "rule__Objective__Group__3__Impl"


    // $ANTLR start "rule__Objective__Group__4"
    // InternalMyDsl.g:3564:1: rule__Objective__Group__4 : rule__Objective__Group__4__Impl ;
    public final void rule__Objective__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3568:1: ( rule__Objective__Group__4__Impl )
            // InternalMyDsl.g:3569:2: rule__Objective__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Objective__Group__4__Impl();

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
    // $ANTLR end "rule__Objective__Group__4"


    // $ANTLR start "rule__Objective__Group__4__Impl"
    // InternalMyDsl.g:3575:1: rule__Objective__Group__4__Impl : ( ')' ) ;
    public final void rule__Objective__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3579:1: ( ( ')' ) )
            // InternalMyDsl.g:3580:1: ( ')' )
            {
            // InternalMyDsl.g:3580:1: ( ')' )
            // InternalMyDsl.g:3581:2: ')'
            {
             before(grammarAccess.getObjectiveAccess().getRightParenthesisKeyword_4()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getObjectiveAccess().getRightParenthesisKeyword_4()); 

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
    // $ANTLR end "rule__Objective__Group__4__Impl"


    // $ANTLR start "rule__Addition__Group__0"
    // InternalMyDsl.g:3591:1: rule__Addition__Group__0 : rule__Addition__Group__0__Impl rule__Addition__Group__1 ;
    public final void rule__Addition__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3595:1: ( rule__Addition__Group__0__Impl rule__Addition__Group__1 )
            // InternalMyDsl.g:3596:2: rule__Addition__Group__0__Impl rule__Addition__Group__1
            {
            pushFollow(FOLLOW_47);
            rule__Addition__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group__1();

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
    // $ANTLR end "rule__Addition__Group__0"


    // $ANTLR start "rule__Addition__Group__0__Impl"
    // InternalMyDsl.g:3603:1: rule__Addition__Group__0__Impl : ( ruleMultiplication ) ;
    public final void rule__Addition__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3607:1: ( ( ruleMultiplication ) )
            // InternalMyDsl.g:3608:1: ( ruleMultiplication )
            {
            // InternalMyDsl.g:3608:1: ( ruleMultiplication )
            // InternalMyDsl.g:3609:2: ruleMultiplication
            {
             before(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getAdditionAccess().getMultiplicationParserRuleCall_0()); 

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
    // $ANTLR end "rule__Addition__Group__0__Impl"


    // $ANTLR start "rule__Addition__Group__1"
    // InternalMyDsl.g:3618:1: rule__Addition__Group__1 : rule__Addition__Group__1__Impl ;
    public final void rule__Addition__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3622:1: ( rule__Addition__Group__1__Impl )
            // InternalMyDsl.g:3623:2: rule__Addition__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group__1__Impl();

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
    // $ANTLR end "rule__Addition__Group__1"


    // $ANTLR start "rule__Addition__Group__1__Impl"
    // InternalMyDsl.g:3629:1: rule__Addition__Group__1__Impl : ( ( rule__Addition__Group_1__0 )? ) ;
    public final void rule__Addition__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3633:1: ( ( ( rule__Addition__Group_1__0 )? ) )
            // InternalMyDsl.g:3634:1: ( ( rule__Addition__Group_1__0 )? )
            {
            // InternalMyDsl.g:3634:1: ( ( rule__Addition__Group_1__0 )? )
            // InternalMyDsl.g:3635:2: ( rule__Addition__Group_1__0 )?
            {
             before(grammarAccess.getAdditionAccess().getGroup_1()); 
            // InternalMyDsl.g:3636:2: ( rule__Addition__Group_1__0 )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( ((LA33_0>=41 && LA33_0<=42)) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // InternalMyDsl.g:3636:3: rule__Addition__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Addition__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAdditionAccess().getGroup_1()); 

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
    // $ANTLR end "rule__Addition__Group__1__Impl"


    // $ANTLR start "rule__Addition__Group_1__0"
    // InternalMyDsl.g:3645:1: rule__Addition__Group_1__0 : rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 ;
    public final void rule__Addition__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3649:1: ( rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1 )
            // InternalMyDsl.g:3650:2: rule__Addition__Group_1__0__Impl rule__Addition__Group_1__1
            {
            pushFollow(FOLLOW_39);
            rule__Addition__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__1();

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
    // $ANTLR end "rule__Addition__Group_1__0"


    // $ANTLR start "rule__Addition__Group_1__0__Impl"
    // InternalMyDsl.g:3657:1: rule__Addition__Group_1__0__Impl : ( ( rule__Addition__Alternatives_1_0 ) ) ;
    public final void rule__Addition__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3661:1: ( ( ( rule__Addition__Alternatives_1_0 ) ) )
            // InternalMyDsl.g:3662:1: ( ( rule__Addition__Alternatives_1_0 ) )
            {
            // InternalMyDsl.g:3662:1: ( ( rule__Addition__Alternatives_1_0 ) )
            // InternalMyDsl.g:3663:2: ( rule__Addition__Alternatives_1_0 )
            {
             before(grammarAccess.getAdditionAccess().getAlternatives_1_0()); 
            // InternalMyDsl.g:3664:2: ( rule__Addition__Alternatives_1_0 )
            // InternalMyDsl.g:3664:3: rule__Addition__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Alternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getAlternatives_1_0()); 

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
    // $ANTLR end "rule__Addition__Group_1__0__Impl"


    // $ANTLR start "rule__Addition__Group_1__1"
    // InternalMyDsl.g:3672:1: rule__Addition__Group_1__1 : rule__Addition__Group_1__1__Impl ;
    public final void rule__Addition__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3676:1: ( rule__Addition__Group_1__1__Impl )
            // InternalMyDsl.g:3677:2: rule__Addition__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1__1__Impl();

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
    // $ANTLR end "rule__Addition__Group_1__1"


    // $ANTLR start "rule__Addition__Group_1__1__Impl"
    // InternalMyDsl.g:3683:1: rule__Addition__Group_1__1__Impl : ( ( rule__Addition__RightAssignment_1_1 ) ) ;
    public final void rule__Addition__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3687:1: ( ( ( rule__Addition__RightAssignment_1_1 ) ) )
            // InternalMyDsl.g:3688:1: ( ( rule__Addition__RightAssignment_1_1 ) )
            {
            // InternalMyDsl.g:3688:1: ( ( rule__Addition__RightAssignment_1_1 ) )
            // InternalMyDsl.g:3689:2: ( rule__Addition__RightAssignment_1_1 )
            {
             before(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); 
            // InternalMyDsl.g:3690:2: ( rule__Addition__RightAssignment_1_1 )
            // InternalMyDsl.g:3690:3: rule__Addition__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Addition__RightAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getAdditionAccess().getRightAssignment_1_1()); 

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
    // $ANTLR end "rule__Addition__Group_1__1__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_0__0"
    // InternalMyDsl.g:3699:1: rule__Addition__Group_1_0_0__0 : rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1 ;
    public final void rule__Addition__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3703:1: ( rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1 )
            // InternalMyDsl.g:3704:2: rule__Addition__Group_1_0_0__0__Impl rule__Addition__Group_1_0_0__1
            {
            pushFollow(FOLLOW_48);
            rule__Addition__Group_1_0_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_0__1();

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
    // $ANTLR end "rule__Addition__Group_1_0_0__0"


    // $ANTLR start "rule__Addition__Group_1_0_0__0__Impl"
    // InternalMyDsl.g:3711:1: rule__Addition__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3715:1: ( ( () ) )
            // InternalMyDsl.g:3716:1: ( () )
            {
            // InternalMyDsl.g:3716:1: ( () )
            // InternalMyDsl.g:3717:2: ()
            {
             before(grammarAccess.getAdditionAccess().getAddExpressionLeftAction_1_0_0_0()); 
            // InternalMyDsl.g:3718:2: ()
            // InternalMyDsl.g:3718:3: 
            {
            }

             after(grammarAccess.getAdditionAccess().getAddExpressionLeftAction_1_0_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_0__1"
    // InternalMyDsl.g:3726:1: rule__Addition__Group_1_0_0__1 : rule__Addition__Group_1_0_0__1__Impl ;
    public final void rule__Addition__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3730:1: ( rule__Addition__Group_1_0_0__1__Impl )
            // InternalMyDsl.g:3731:2: rule__Addition__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_0__1__Impl();

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
    // $ANTLR end "rule__Addition__Group_1_0_0__1"


    // $ANTLR start "rule__Addition__Group_1_0_0__1__Impl"
    // InternalMyDsl.g:3737:1: rule__Addition__Group_1_0_0__1__Impl : ( '+' ) ;
    public final void rule__Addition__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3741:1: ( ( '+' ) )
            // InternalMyDsl.g:3742:1: ( '+' )
            {
            // InternalMyDsl.g:3742:1: ( '+' )
            // InternalMyDsl.g:3743:2: '+'
            {
             before(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); 
            match(input,41,FOLLOW_2); 
             after(grammarAccess.getAdditionAccess().getPlusSignKeyword_1_0_0_1()); 

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
    // $ANTLR end "rule__Addition__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_1__0"
    // InternalMyDsl.g:3753:1: rule__Addition__Group_1_0_1__0 : rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1 ;
    public final void rule__Addition__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3757:1: ( rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1 )
            // InternalMyDsl.g:3758:2: rule__Addition__Group_1_0_1__0__Impl rule__Addition__Group_1_0_1__1
            {
            pushFollow(FOLLOW_47);
            rule__Addition__Group_1_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_1__1();

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
    // $ANTLR end "rule__Addition__Group_1_0_1__0"


    // $ANTLR start "rule__Addition__Group_1_0_1__0__Impl"
    // InternalMyDsl.g:3765:1: rule__Addition__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Addition__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3769:1: ( ( () ) )
            // InternalMyDsl.g:3770:1: ( () )
            {
            // InternalMyDsl.g:3770:1: ( () )
            // InternalMyDsl.g:3771:2: ()
            {
             before(grammarAccess.getAdditionAccess().getSubExpressionLeftAction_1_0_1_0()); 
            // InternalMyDsl.g:3772:2: ()
            // InternalMyDsl.g:3772:3: 
            {
            }

             after(grammarAccess.getAdditionAccess().getSubExpressionLeftAction_1_0_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Addition__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Addition__Group_1_0_1__1"
    // InternalMyDsl.g:3780:1: rule__Addition__Group_1_0_1__1 : rule__Addition__Group_1_0_1__1__Impl ;
    public final void rule__Addition__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3784:1: ( rule__Addition__Group_1_0_1__1__Impl )
            // InternalMyDsl.g:3785:2: rule__Addition__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Addition__Group_1_0_1__1__Impl();

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
    // $ANTLR end "rule__Addition__Group_1_0_1__1"


    // $ANTLR start "rule__Addition__Group_1_0_1__1__Impl"
    // InternalMyDsl.g:3791:1: rule__Addition__Group_1_0_1__1__Impl : ( '-' ) ;
    public final void rule__Addition__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3795:1: ( ( '-' ) )
            // InternalMyDsl.g:3796:1: ( '-' )
            {
            // InternalMyDsl.g:3796:1: ( '-' )
            // InternalMyDsl.g:3797:2: '-'
            {
             before(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); 
            match(input,42,FOLLOW_2); 
             after(grammarAccess.getAdditionAccess().getHyphenMinusKeyword_1_0_1_1()); 

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
    // $ANTLR end "rule__Addition__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Multiplication__Group__0"
    // InternalMyDsl.g:3807:1: rule__Multiplication__Group__0 : rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 ;
    public final void rule__Multiplication__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3811:1: ( rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1 )
            // InternalMyDsl.g:3812:2: rule__Multiplication__Group__0__Impl rule__Multiplication__Group__1
            {
            pushFollow(FOLLOW_49);
            rule__Multiplication__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__1();

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
    // $ANTLR end "rule__Multiplication__Group__0"


    // $ANTLR start "rule__Multiplication__Group__0__Impl"
    // InternalMyDsl.g:3819:1: rule__Multiplication__Group__0__Impl : ( rulePower ) ;
    public final void rule__Multiplication__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3823:1: ( ( rulePower ) )
            // InternalMyDsl.g:3824:1: ( rulePower )
            {
            // InternalMyDsl.g:3824:1: ( rulePower )
            // InternalMyDsl.g:3825:2: rulePower
            {
             before(grammarAccess.getMultiplicationAccess().getPowerParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePower();

            state._fsp--;

             after(grammarAccess.getMultiplicationAccess().getPowerParserRuleCall_0()); 

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
    // $ANTLR end "rule__Multiplication__Group__0__Impl"


    // $ANTLR start "rule__Multiplication__Group__1"
    // InternalMyDsl.g:3834:1: rule__Multiplication__Group__1 : rule__Multiplication__Group__1__Impl ;
    public final void rule__Multiplication__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3838:1: ( rule__Multiplication__Group__1__Impl )
            // InternalMyDsl.g:3839:2: rule__Multiplication__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group__1__Impl();

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
    // $ANTLR end "rule__Multiplication__Group__1"


    // $ANTLR start "rule__Multiplication__Group__1__Impl"
    // InternalMyDsl.g:3845:1: rule__Multiplication__Group__1__Impl : ( ( rule__Multiplication__Group_1__0 )? ) ;
    public final void rule__Multiplication__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3849:1: ( ( ( rule__Multiplication__Group_1__0 )? ) )
            // InternalMyDsl.g:3850:1: ( ( rule__Multiplication__Group_1__0 )? )
            {
            // InternalMyDsl.g:3850:1: ( ( rule__Multiplication__Group_1__0 )? )
            // InternalMyDsl.g:3851:2: ( rule__Multiplication__Group_1__0 )?
            {
             before(grammarAccess.getMultiplicationAccess().getGroup_1()); 
            // InternalMyDsl.g:3852:2: ( rule__Multiplication__Group_1__0 )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( ((LA34_0>=43 && LA34_0<=44)) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // InternalMyDsl.g:3852:3: rule__Multiplication__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Multiplication__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getMultiplicationAccess().getGroup_1()); 

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
    // $ANTLR end "rule__Multiplication__Group__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__0"
    // InternalMyDsl.g:3861:1: rule__Multiplication__Group_1__0 : rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 ;
    public final void rule__Multiplication__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3865:1: ( rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1 )
            // InternalMyDsl.g:3866:2: rule__Multiplication__Group_1__0__Impl rule__Multiplication__Group_1__1
            {
            pushFollow(FOLLOW_39);
            rule__Multiplication__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__1();

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
    // $ANTLR end "rule__Multiplication__Group_1__0"


    // $ANTLR start "rule__Multiplication__Group_1__0__Impl"
    // InternalMyDsl.g:3873:1: rule__Multiplication__Group_1__0__Impl : ( ( rule__Multiplication__Alternatives_1_0 ) ) ;
    public final void rule__Multiplication__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3877:1: ( ( ( rule__Multiplication__Alternatives_1_0 ) ) )
            // InternalMyDsl.g:3878:1: ( ( rule__Multiplication__Alternatives_1_0 ) )
            {
            // InternalMyDsl.g:3878:1: ( ( rule__Multiplication__Alternatives_1_0 ) )
            // InternalMyDsl.g:3879:2: ( rule__Multiplication__Alternatives_1_0 )
            {
             before(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); 
            // InternalMyDsl.g:3880:2: ( rule__Multiplication__Alternatives_1_0 )
            // InternalMyDsl.g:3880:3: rule__Multiplication__Alternatives_1_0
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Alternatives_1_0();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getAlternatives_1_0()); 

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
    // $ANTLR end "rule__Multiplication__Group_1__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1__1"
    // InternalMyDsl.g:3888:1: rule__Multiplication__Group_1__1 : rule__Multiplication__Group_1__1__Impl ;
    public final void rule__Multiplication__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3892:1: ( rule__Multiplication__Group_1__1__Impl )
            // InternalMyDsl.g:3893:2: rule__Multiplication__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1__1__Impl();

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
    // $ANTLR end "rule__Multiplication__Group_1__1"


    // $ANTLR start "rule__Multiplication__Group_1__1__Impl"
    // InternalMyDsl.g:3899:1: rule__Multiplication__Group_1__1__Impl : ( ( rule__Multiplication__RightAssignment_1_1 ) ) ;
    public final void rule__Multiplication__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3903:1: ( ( ( rule__Multiplication__RightAssignment_1_1 ) ) )
            // InternalMyDsl.g:3904:1: ( ( rule__Multiplication__RightAssignment_1_1 ) )
            {
            // InternalMyDsl.g:3904:1: ( ( rule__Multiplication__RightAssignment_1_1 ) )
            // InternalMyDsl.g:3905:2: ( rule__Multiplication__RightAssignment_1_1 )
            {
             before(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); 
            // InternalMyDsl.g:3906:2: ( rule__Multiplication__RightAssignment_1_1 )
            // InternalMyDsl.g:3906:3: rule__Multiplication__RightAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__RightAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getMultiplicationAccess().getRightAssignment_1_1()); 

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
    // $ANTLR end "rule__Multiplication__Group_1__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__0"
    // InternalMyDsl.g:3915:1: rule__Multiplication__Group_1_0_0__0 : rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1 ;
    public final void rule__Multiplication__Group_1_0_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3919:1: ( rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1 )
            // InternalMyDsl.g:3920:2: rule__Multiplication__Group_1_0_0__0__Impl rule__Multiplication__Group_1_0_0__1
            {
            pushFollow(FOLLOW_50);
            rule__Multiplication__Group_1_0_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_0__1();

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
    // $ANTLR end "rule__Multiplication__Group_1_0_0__0"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__0__Impl"
    // InternalMyDsl.g:3927:1: rule__Multiplication__Group_1_0_0__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3931:1: ( ( () ) )
            // InternalMyDsl.g:3932:1: ( () )
            {
            // InternalMyDsl.g:3932:1: ( () )
            // InternalMyDsl.g:3933:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getMultExpressionLeftAction_1_0_0_0()); 
            // InternalMyDsl.g:3934:2: ()
            // InternalMyDsl.g:3934:3: 
            {
            }

             after(grammarAccess.getMultiplicationAccess().getMultExpressionLeftAction_1_0_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_0__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__1"
    // InternalMyDsl.g:3942:1: rule__Multiplication__Group_1_0_0__1 : rule__Multiplication__Group_1_0_0__1__Impl ;
    public final void rule__Multiplication__Group_1_0_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3946:1: ( rule__Multiplication__Group_1_0_0__1__Impl )
            // InternalMyDsl.g:3947:2: rule__Multiplication__Group_1_0_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_0__1__Impl();

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
    // $ANTLR end "rule__Multiplication__Group_1_0_0__1"


    // $ANTLR start "rule__Multiplication__Group_1_0_0__1__Impl"
    // InternalMyDsl.g:3953:1: rule__Multiplication__Group_1_0_0__1__Impl : ( '*' ) ;
    public final void rule__Multiplication__Group_1_0_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3957:1: ( ( '*' ) )
            // InternalMyDsl.g:3958:1: ( '*' )
            {
            // InternalMyDsl.g:3958:1: ( '*' )
            // InternalMyDsl.g:3959:2: '*'
            {
             before(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); 
            match(input,43,FOLLOW_2); 
             after(grammarAccess.getMultiplicationAccess().getAsteriskKeyword_1_0_0_1()); 

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
    // $ANTLR end "rule__Multiplication__Group_1_0_0__1__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__0"
    // InternalMyDsl.g:3969:1: rule__Multiplication__Group_1_0_1__0 : rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1 ;
    public final void rule__Multiplication__Group_1_0_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3973:1: ( rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1 )
            // InternalMyDsl.g:3974:2: rule__Multiplication__Group_1_0_1__0__Impl rule__Multiplication__Group_1_0_1__1
            {
            pushFollow(FOLLOW_49);
            rule__Multiplication__Group_1_0_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_1__1();

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
    // $ANTLR end "rule__Multiplication__Group_1_0_1__0"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__0__Impl"
    // InternalMyDsl.g:3981:1: rule__Multiplication__Group_1_0_1__0__Impl : ( () ) ;
    public final void rule__Multiplication__Group_1_0_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:3985:1: ( ( () ) )
            // InternalMyDsl.g:3986:1: ( () )
            {
            // InternalMyDsl.g:3986:1: ( () )
            // InternalMyDsl.g:3987:2: ()
            {
             before(grammarAccess.getMultiplicationAccess().getDivExpressionLeftAction_1_0_1_0()); 
            // InternalMyDsl.g:3988:2: ()
            // InternalMyDsl.g:3988:3: 
            {
            }

             after(grammarAccess.getMultiplicationAccess().getDivExpressionLeftAction_1_0_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Multiplication__Group_1_0_1__0__Impl"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__1"
    // InternalMyDsl.g:3996:1: rule__Multiplication__Group_1_0_1__1 : rule__Multiplication__Group_1_0_1__1__Impl ;
    public final void rule__Multiplication__Group_1_0_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4000:1: ( rule__Multiplication__Group_1_0_1__1__Impl )
            // InternalMyDsl.g:4001:2: rule__Multiplication__Group_1_0_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Multiplication__Group_1_0_1__1__Impl();

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
    // $ANTLR end "rule__Multiplication__Group_1_0_1__1"


    // $ANTLR start "rule__Multiplication__Group_1_0_1__1__Impl"
    // InternalMyDsl.g:4007:1: rule__Multiplication__Group_1_0_1__1__Impl : ( '/' ) ;
    public final void rule__Multiplication__Group_1_0_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4011:1: ( ( '/' ) )
            // InternalMyDsl.g:4012:1: ( '/' )
            {
            // InternalMyDsl.g:4012:1: ( '/' )
            // InternalMyDsl.g:4013:2: '/'
            {
             before(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); 
            match(input,44,FOLLOW_2); 
             after(grammarAccess.getMultiplicationAccess().getSolidusKeyword_1_0_1_1()); 

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
    // $ANTLR end "rule__Multiplication__Group_1_0_1__1__Impl"


    // $ANTLR start "rule__Power__Group__0"
    // InternalMyDsl.g:4023:1: rule__Power__Group__0 : rule__Power__Group__0__Impl rule__Power__Group__1 ;
    public final void rule__Power__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4027:1: ( rule__Power__Group__0__Impl rule__Power__Group__1 )
            // InternalMyDsl.g:4028:2: rule__Power__Group__0__Impl rule__Power__Group__1
            {
            pushFollow(FOLLOW_51);
            rule__Power__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Power__Group__1();

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
    // $ANTLR end "rule__Power__Group__0"


    // $ANTLR start "rule__Power__Group__0__Impl"
    // InternalMyDsl.g:4035:1: rule__Power__Group__0__Impl : ( rulePrimaryExpression ) ;
    public final void rule__Power__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4039:1: ( ( rulePrimaryExpression ) )
            // InternalMyDsl.g:4040:1: ( rulePrimaryExpression )
            {
            // InternalMyDsl.g:4040:1: ( rulePrimaryExpression )
            // InternalMyDsl.g:4041:2: rulePrimaryExpression
            {
             before(grammarAccess.getPowerAccess().getPrimaryExpressionParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePrimaryExpression();

            state._fsp--;

             after(grammarAccess.getPowerAccess().getPrimaryExpressionParserRuleCall_0()); 

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
    // $ANTLR end "rule__Power__Group__0__Impl"


    // $ANTLR start "rule__Power__Group__1"
    // InternalMyDsl.g:4050:1: rule__Power__Group__1 : rule__Power__Group__1__Impl ;
    public final void rule__Power__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4054:1: ( rule__Power__Group__1__Impl )
            // InternalMyDsl.g:4055:2: rule__Power__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Power__Group__1__Impl();

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
    // $ANTLR end "rule__Power__Group__1"


    // $ANTLR start "rule__Power__Group__1__Impl"
    // InternalMyDsl.g:4061:1: rule__Power__Group__1__Impl : ( ( rule__Power__Group_1__0 )? ) ;
    public final void rule__Power__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4065:1: ( ( ( rule__Power__Group_1__0 )? ) )
            // InternalMyDsl.g:4066:1: ( ( rule__Power__Group_1__0 )? )
            {
            // InternalMyDsl.g:4066:1: ( ( rule__Power__Group_1__0 )? )
            // InternalMyDsl.g:4067:2: ( rule__Power__Group_1__0 )?
            {
             before(grammarAccess.getPowerAccess().getGroup_1()); 
            // InternalMyDsl.g:4068:2: ( rule__Power__Group_1__0 )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==45) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // InternalMyDsl.g:4068:3: rule__Power__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Power__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPowerAccess().getGroup_1()); 

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
    // $ANTLR end "rule__Power__Group__1__Impl"


    // $ANTLR start "rule__Power__Group_1__0"
    // InternalMyDsl.g:4077:1: rule__Power__Group_1__0 : rule__Power__Group_1__0__Impl rule__Power__Group_1__1 ;
    public final void rule__Power__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4081:1: ( rule__Power__Group_1__0__Impl rule__Power__Group_1__1 )
            // InternalMyDsl.g:4082:2: rule__Power__Group_1__0__Impl rule__Power__Group_1__1
            {
            pushFollow(FOLLOW_51);
            rule__Power__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Power__Group_1__1();

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
    // $ANTLR end "rule__Power__Group_1__0"


    // $ANTLR start "rule__Power__Group_1__0__Impl"
    // InternalMyDsl.g:4089:1: rule__Power__Group_1__0__Impl : ( () ) ;
    public final void rule__Power__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4093:1: ( ( () ) )
            // InternalMyDsl.g:4094:1: ( () )
            {
            // InternalMyDsl.g:4094:1: ( () )
            // InternalMyDsl.g:4095:2: ()
            {
             before(grammarAccess.getPowerAccess().getPowExpressionLeftAction_1_0()); 
            // InternalMyDsl.g:4096:2: ()
            // InternalMyDsl.g:4096:3: 
            {
            }

             after(grammarAccess.getPowerAccess().getPowExpressionLeftAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Power__Group_1__0__Impl"


    // $ANTLR start "rule__Power__Group_1__1"
    // InternalMyDsl.g:4104:1: rule__Power__Group_1__1 : rule__Power__Group_1__1__Impl rule__Power__Group_1__2 ;
    public final void rule__Power__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4108:1: ( rule__Power__Group_1__1__Impl rule__Power__Group_1__2 )
            // InternalMyDsl.g:4109:2: rule__Power__Group_1__1__Impl rule__Power__Group_1__2
            {
            pushFollow(FOLLOW_39);
            rule__Power__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Power__Group_1__2();

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
    // $ANTLR end "rule__Power__Group_1__1"


    // $ANTLR start "rule__Power__Group_1__1__Impl"
    // InternalMyDsl.g:4116:1: rule__Power__Group_1__1__Impl : ( '^' ) ;
    public final void rule__Power__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4120:1: ( ( '^' ) )
            // InternalMyDsl.g:4121:1: ( '^' )
            {
            // InternalMyDsl.g:4121:1: ( '^' )
            // InternalMyDsl.g:4122:2: '^'
            {
             before(grammarAccess.getPowerAccess().getCircumflexAccentKeyword_1_1()); 
            match(input,45,FOLLOW_2); 
             after(grammarAccess.getPowerAccess().getCircumflexAccentKeyword_1_1()); 

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
    // $ANTLR end "rule__Power__Group_1__1__Impl"


    // $ANTLR start "rule__Power__Group_1__2"
    // InternalMyDsl.g:4131:1: rule__Power__Group_1__2 : rule__Power__Group_1__2__Impl ;
    public final void rule__Power__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4135:1: ( rule__Power__Group_1__2__Impl )
            // InternalMyDsl.g:4136:2: rule__Power__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Power__Group_1__2__Impl();

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
    // $ANTLR end "rule__Power__Group_1__2"


    // $ANTLR start "rule__Power__Group_1__2__Impl"
    // InternalMyDsl.g:4142:1: rule__Power__Group_1__2__Impl : ( ( rule__Power__RightAssignment_1_2 ) ) ;
    public final void rule__Power__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4146:1: ( ( ( rule__Power__RightAssignment_1_2 ) ) )
            // InternalMyDsl.g:4147:1: ( ( rule__Power__RightAssignment_1_2 ) )
            {
            // InternalMyDsl.g:4147:1: ( ( rule__Power__RightAssignment_1_2 ) )
            // InternalMyDsl.g:4148:2: ( rule__Power__RightAssignment_1_2 )
            {
             before(grammarAccess.getPowerAccess().getRightAssignment_1_2()); 
            // InternalMyDsl.g:4149:2: ( rule__Power__RightAssignment_1_2 )
            // InternalMyDsl.g:4149:3: rule__Power__RightAssignment_1_2
            {
            pushFollow(FOLLOW_2);
            rule__Power__RightAssignment_1_2();

            state._fsp--;


            }

             after(grammarAccess.getPowerAccess().getRightAssignment_1_2()); 

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
    // $ANTLR end "rule__Power__Group_1__2__Impl"


    // $ANTLR start "rule__PrimaryExpression__Group_0__0"
    // InternalMyDsl.g:4158:1: rule__PrimaryExpression__Group_0__0 : rule__PrimaryExpression__Group_0__0__Impl rule__PrimaryExpression__Group_0__1 ;
    public final void rule__PrimaryExpression__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4162:1: ( rule__PrimaryExpression__Group_0__0__Impl rule__PrimaryExpression__Group_0__1 )
            // InternalMyDsl.g:4163:2: rule__PrimaryExpression__Group_0__0__Impl rule__PrimaryExpression__Group_0__1
            {
            pushFollow(FOLLOW_39);
            rule__PrimaryExpression__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PrimaryExpression__Group_0__1();

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
    // $ANTLR end "rule__PrimaryExpression__Group_0__0"


    // $ANTLR start "rule__PrimaryExpression__Group_0__0__Impl"
    // InternalMyDsl.g:4170:1: rule__PrimaryExpression__Group_0__0__Impl : ( '(' ) ;
    public final void rule__PrimaryExpression__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4174:1: ( ( '(' ) )
            // InternalMyDsl.g:4175:1: ( '(' )
            {
            // InternalMyDsl.g:4175:1: ( '(' )
            // InternalMyDsl.g:4176:2: '('
            {
             before(grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_0_0()); 
            match(input,39,FOLLOW_2); 
             after(grammarAccess.getPrimaryExpressionAccess().getLeftParenthesisKeyword_0_0()); 

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
    // $ANTLR end "rule__PrimaryExpression__Group_0__0__Impl"


    // $ANTLR start "rule__PrimaryExpression__Group_0__1"
    // InternalMyDsl.g:4185:1: rule__PrimaryExpression__Group_0__1 : rule__PrimaryExpression__Group_0__1__Impl rule__PrimaryExpression__Group_0__2 ;
    public final void rule__PrimaryExpression__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4189:1: ( rule__PrimaryExpression__Group_0__1__Impl rule__PrimaryExpression__Group_0__2 )
            // InternalMyDsl.g:4190:2: rule__PrimaryExpression__Group_0__1__Impl rule__PrimaryExpression__Group_0__2
            {
            pushFollow(FOLLOW_46);
            rule__PrimaryExpression__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PrimaryExpression__Group_0__2();

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
    // $ANTLR end "rule__PrimaryExpression__Group_0__1"


    // $ANTLR start "rule__PrimaryExpression__Group_0__1__Impl"
    // InternalMyDsl.g:4197:1: rule__PrimaryExpression__Group_0__1__Impl : ( ruleExpression ) ;
    public final void rule__PrimaryExpression__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4201:1: ( ( ruleExpression ) )
            // InternalMyDsl.g:4202:1: ( ruleExpression )
            {
            // InternalMyDsl.g:4202:1: ( ruleExpression )
            // InternalMyDsl.g:4203:2: ruleExpression
            {
             before(grammarAccess.getPrimaryExpressionAccess().getExpressionParserRuleCall_0_1()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getPrimaryExpressionAccess().getExpressionParserRuleCall_0_1()); 

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
    // $ANTLR end "rule__PrimaryExpression__Group_0__1__Impl"


    // $ANTLR start "rule__PrimaryExpression__Group_0__2"
    // InternalMyDsl.g:4212:1: rule__PrimaryExpression__Group_0__2 : rule__PrimaryExpression__Group_0__2__Impl ;
    public final void rule__PrimaryExpression__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4216:1: ( rule__PrimaryExpression__Group_0__2__Impl )
            // InternalMyDsl.g:4217:2: rule__PrimaryExpression__Group_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PrimaryExpression__Group_0__2__Impl();

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
    // $ANTLR end "rule__PrimaryExpression__Group_0__2"


    // $ANTLR start "rule__PrimaryExpression__Group_0__2__Impl"
    // InternalMyDsl.g:4223:1: rule__PrimaryExpression__Group_0__2__Impl : ( ')' ) ;
    public final void rule__PrimaryExpression__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4227:1: ( ( ')' ) )
            // InternalMyDsl.g:4228:1: ( ')' )
            {
            // InternalMyDsl.g:4228:1: ( ')' )
            // InternalMyDsl.g:4229:2: ')'
            {
             before(grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_0_2()); 
            match(input,40,FOLLOW_2); 
             after(grammarAccess.getPrimaryExpressionAccess().getRightParenthesisKeyword_0_2()); 

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
    // $ANTLR end "rule__PrimaryExpression__Group_0__2__Impl"


    // $ANTLR start "rule__PropertyDesignator__Group__0"
    // InternalMyDsl.g:4239:1: rule__PropertyDesignator__Group__0 : rule__PropertyDesignator__Group__0__Impl rule__PropertyDesignator__Group__1 ;
    public final void rule__PropertyDesignator__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4243:1: ( rule__PropertyDesignator__Group__0__Impl rule__PropertyDesignator__Group__1 )
            // InternalMyDsl.g:4244:2: rule__PropertyDesignator__Group__0__Impl rule__PropertyDesignator__Group__1
            {
            pushFollow(FOLLOW_52);
            rule__PropertyDesignator__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PropertyDesignator__Group__1();

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
    // $ANTLR end "rule__PropertyDesignator__Group__0"


    // $ANTLR start "rule__PropertyDesignator__Group__0__Impl"
    // InternalMyDsl.g:4251:1: rule__PropertyDesignator__Group__0__Impl : ( ( rule__PropertyDesignator__InstanceAssignment_0 ) ) ;
    public final void rule__PropertyDesignator__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4255:1: ( ( ( rule__PropertyDesignator__InstanceAssignment_0 ) ) )
            // InternalMyDsl.g:4256:1: ( ( rule__PropertyDesignator__InstanceAssignment_0 ) )
            {
            // InternalMyDsl.g:4256:1: ( ( rule__PropertyDesignator__InstanceAssignment_0 ) )
            // InternalMyDsl.g:4257:2: ( rule__PropertyDesignator__InstanceAssignment_0 )
            {
             before(grammarAccess.getPropertyDesignatorAccess().getInstanceAssignment_0()); 
            // InternalMyDsl.g:4258:2: ( rule__PropertyDesignator__InstanceAssignment_0 )
            // InternalMyDsl.g:4258:3: rule__PropertyDesignator__InstanceAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignator__InstanceAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyDesignatorAccess().getInstanceAssignment_0()); 

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
    // $ANTLR end "rule__PropertyDesignator__Group__0__Impl"


    // $ANTLR start "rule__PropertyDesignator__Group__1"
    // InternalMyDsl.g:4266:1: rule__PropertyDesignator__Group__1 : rule__PropertyDesignator__Group__1__Impl rule__PropertyDesignator__Group__2 ;
    public final void rule__PropertyDesignator__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4270:1: ( rule__PropertyDesignator__Group__1__Impl rule__PropertyDesignator__Group__2 )
            // InternalMyDsl.g:4271:2: rule__PropertyDesignator__Group__1__Impl rule__PropertyDesignator__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__PropertyDesignator__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PropertyDesignator__Group__2();

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
    // $ANTLR end "rule__PropertyDesignator__Group__1"


    // $ANTLR start "rule__PropertyDesignator__Group__1__Impl"
    // InternalMyDsl.g:4278:1: rule__PropertyDesignator__Group__1__Impl : ( '.' ) ;
    public final void rule__PropertyDesignator__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4282:1: ( ( '.' ) )
            // InternalMyDsl.g:4283:1: ( '.' )
            {
            // InternalMyDsl.g:4283:1: ( '.' )
            // InternalMyDsl.g:4284:2: '.'
            {
             before(grammarAccess.getPropertyDesignatorAccess().getFullStopKeyword_1()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getPropertyDesignatorAccess().getFullStopKeyword_1()); 

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
    // $ANTLR end "rule__PropertyDesignator__Group__1__Impl"


    // $ANTLR start "rule__PropertyDesignator__Group__2"
    // InternalMyDsl.g:4293:1: rule__PropertyDesignator__Group__2 : rule__PropertyDesignator__Group__2__Impl ;
    public final void rule__PropertyDesignator__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4297:1: ( rule__PropertyDesignator__Group__2__Impl )
            // InternalMyDsl.g:4298:2: rule__PropertyDesignator__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignator__Group__2__Impl();

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
    // $ANTLR end "rule__PropertyDesignator__Group__2"


    // $ANTLR start "rule__PropertyDesignator__Group__2__Impl"
    // InternalMyDsl.g:4304:1: rule__PropertyDesignator__Group__2__Impl : ( ( rule__PropertyDesignator__PropertyAssignment_2 ) ) ;
    public final void rule__PropertyDesignator__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4308:1: ( ( ( rule__PropertyDesignator__PropertyAssignment_2 ) ) )
            // InternalMyDsl.g:4309:1: ( ( rule__PropertyDesignator__PropertyAssignment_2 ) )
            {
            // InternalMyDsl.g:4309:1: ( ( rule__PropertyDesignator__PropertyAssignment_2 ) )
            // InternalMyDsl.g:4310:2: ( rule__PropertyDesignator__PropertyAssignment_2 )
            {
             before(grammarAccess.getPropertyDesignatorAccess().getPropertyAssignment_2()); 
            // InternalMyDsl.g:4311:2: ( rule__PropertyDesignator__PropertyAssignment_2 )
            // InternalMyDsl.g:4311:3: rule__PropertyDesignator__PropertyAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignator__PropertyAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getPropertyDesignatorAccess().getPropertyAssignment_2()); 

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
    // $ANTLR end "rule__PropertyDesignator__Group__2__Impl"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group__0"
    // InternalMyDsl.g:4320:1: rule__PropertyDesignatorWithOptionalInstance__Group__0 : rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl rule__PropertyDesignatorWithOptionalInstance__Group__1 ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4324:1: ( rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl rule__PropertyDesignatorWithOptionalInstance__Group__1 )
            // InternalMyDsl.g:4325:2: rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl rule__PropertyDesignatorWithOptionalInstance__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__Group__1();

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group__0"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl"
    // InternalMyDsl.g:4332:1: rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl : ( ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0 )? ) ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4336:1: ( ( ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0 )? ) )
            // InternalMyDsl.g:4337:1: ( ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0 )? )
            {
            // InternalMyDsl.g:4337:1: ( ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0 )? )
            // InternalMyDsl.g:4338:2: ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0 )?
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getGroup_0()); 
            // InternalMyDsl.g:4339:2: ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0 )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==RULE_ID) ) {
                int LA36_1 = input.LA(2);

                if ( (LA36_1==46) ) {
                    alt36=1;
                }
            }
            switch (alt36) {
                case 1 :
                    // InternalMyDsl.g:4339:3: rule__PropertyDesignatorWithOptionalInstance__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__PropertyDesignatorWithOptionalInstance__Group_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getGroup_0()); 

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group__0__Impl"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group__1"
    // InternalMyDsl.g:4347:1: rule__PropertyDesignatorWithOptionalInstance__Group__1 : rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4351:1: ( rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl )
            // InternalMyDsl.g:4352:2: rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl();

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group__1"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl"
    // InternalMyDsl.g:4358:1: rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl : ( ( rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 ) ) ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4362:1: ( ( ( rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 ) ) )
            // InternalMyDsl.g:4363:1: ( ( rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 ) )
            {
            // InternalMyDsl.g:4363:1: ( ( rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 ) )
            // InternalMyDsl.g:4364:2: ( rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 )
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyAssignment_1()); 
            // InternalMyDsl.g:4365:2: ( rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 )
            // InternalMyDsl.g:4365:3: rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyAssignment_1()); 

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group__1__Impl"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group_0__0"
    // InternalMyDsl.g:4374:1: rule__PropertyDesignatorWithOptionalInstance__Group_0__0 : rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl rule__PropertyDesignatorWithOptionalInstance__Group_0__1 ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4378:1: ( rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl rule__PropertyDesignatorWithOptionalInstance__Group_0__1 )
            // InternalMyDsl.g:4379:2: rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl rule__PropertyDesignatorWithOptionalInstance__Group_0__1
            {
            pushFollow(FOLLOW_52);
            rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__Group_0__1();

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group_0__0"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl"
    // InternalMyDsl.g:4386:1: rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl : ( ( rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 ) ) ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4390:1: ( ( ( rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 ) ) )
            // InternalMyDsl.g:4391:1: ( ( rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 ) )
            {
            // InternalMyDsl.g:4391:1: ( ( rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 ) )
            // InternalMyDsl.g:4392:2: ( rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 )
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceAssignment_0_0()); 
            // InternalMyDsl.g:4393:2: ( rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 )
            // InternalMyDsl.g:4393:3: rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceAssignment_0_0()); 

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group_0__0__Impl"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group_0__1"
    // InternalMyDsl.g:4401:1: rule__PropertyDesignatorWithOptionalInstance__Group_0__1 : rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4405:1: ( rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl )
            // InternalMyDsl.g:4406:2: rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl();

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group_0__1"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl"
    // InternalMyDsl.g:4412:1: rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl : ( '.' ) ;
    public final void rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4416:1: ( ( '.' ) )
            // InternalMyDsl.g:4417:1: ( '.' )
            {
            // InternalMyDsl.g:4417:1: ( '.' )
            // InternalMyDsl.g:4418:2: '.'
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getFullStopKeyword_0_1()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getFullStopKeyword_0_1()); 

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__Group_0__1__Impl"


    // $ANTLR start "rule__MetaParameterAssignment__Group__0"
    // InternalMyDsl.g:4428:1: rule__MetaParameterAssignment__Group__0 : rule__MetaParameterAssignment__Group__0__Impl rule__MetaParameterAssignment__Group__1 ;
    public final void rule__MetaParameterAssignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4432:1: ( rule__MetaParameterAssignment__Group__0__Impl rule__MetaParameterAssignment__Group__1 )
            // InternalMyDsl.g:4433:2: rule__MetaParameterAssignment__Group__0__Impl rule__MetaParameterAssignment__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__MetaParameterAssignment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__Group__1();

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__0"


    // $ANTLR start "rule__MetaParameterAssignment__Group__0__Impl"
    // InternalMyDsl.g:4440:1: rule__MetaParameterAssignment__Group__0__Impl : ( 'meta' ) ;
    public final void rule__MetaParameterAssignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4444:1: ( ( 'meta' ) )
            // InternalMyDsl.g:4445:1: ( 'meta' )
            {
            // InternalMyDsl.g:4445:1: ( 'meta' )
            // InternalMyDsl.g:4446:2: 'meta'
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getMetaKeyword_0()); 
            match(input,29,FOLLOW_2); 
             after(grammarAccess.getMetaParameterAssignmentAccess().getMetaKeyword_0()); 

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__0__Impl"


    // $ANTLR start "rule__MetaParameterAssignment__Group__1"
    // InternalMyDsl.g:4455:1: rule__MetaParameterAssignment__Group__1 : rule__MetaParameterAssignment__Group__1__Impl rule__MetaParameterAssignment__Group__2 ;
    public final void rule__MetaParameterAssignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4459:1: ( rule__MetaParameterAssignment__Group__1__Impl rule__MetaParameterAssignment__Group__2 )
            // InternalMyDsl.g:4460:2: rule__MetaParameterAssignment__Group__1__Impl rule__MetaParameterAssignment__Group__2
            {
            pushFollow(FOLLOW_22);
            rule__MetaParameterAssignment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__Group__2();

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__1"


    // $ANTLR start "rule__MetaParameterAssignment__Group__1__Impl"
    // InternalMyDsl.g:4467:1: rule__MetaParameterAssignment__Group__1__Impl : ( ( rule__MetaParameterAssignment__ParameterAssignment_1 ) ) ;
    public final void rule__MetaParameterAssignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4471:1: ( ( ( rule__MetaParameterAssignment__ParameterAssignment_1 ) ) )
            // InternalMyDsl.g:4472:1: ( ( rule__MetaParameterAssignment__ParameterAssignment_1 ) )
            {
            // InternalMyDsl.g:4472:1: ( ( rule__MetaParameterAssignment__ParameterAssignment_1 ) )
            // InternalMyDsl.g:4473:2: ( rule__MetaParameterAssignment__ParameterAssignment_1 )
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getParameterAssignment_1()); 
            // InternalMyDsl.g:4474:2: ( rule__MetaParameterAssignment__ParameterAssignment_1 )
            // InternalMyDsl.g:4474:3: rule__MetaParameterAssignment__ParameterAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__ParameterAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMetaParameterAssignmentAccess().getParameterAssignment_1()); 

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__1__Impl"


    // $ANTLR start "rule__MetaParameterAssignment__Group__2"
    // InternalMyDsl.g:4482:1: rule__MetaParameterAssignment__Group__2 : rule__MetaParameterAssignment__Group__2__Impl rule__MetaParameterAssignment__Group__3 ;
    public final void rule__MetaParameterAssignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4486:1: ( rule__MetaParameterAssignment__Group__2__Impl rule__MetaParameterAssignment__Group__3 )
            // InternalMyDsl.g:4487:2: rule__MetaParameterAssignment__Group__2__Impl rule__MetaParameterAssignment__Group__3
            {
            pushFollow(FOLLOW_23);
            rule__MetaParameterAssignment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__Group__3();

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__2"


    // $ANTLR start "rule__MetaParameterAssignment__Group__2__Impl"
    // InternalMyDsl.g:4494:1: rule__MetaParameterAssignment__Group__2__Impl : ( '=' ) ;
    public final void rule__MetaParameterAssignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4498:1: ( ( '=' ) )
            // InternalMyDsl.g:4499:1: ( '=' )
            {
            // InternalMyDsl.g:4499:1: ( '=' )
            // InternalMyDsl.g:4500:2: '='
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getEqualsSignKeyword_2()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getMetaParameterAssignmentAccess().getEqualsSignKeyword_2()); 

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__2__Impl"


    // $ANTLR start "rule__MetaParameterAssignment__Group__3"
    // InternalMyDsl.g:4509:1: rule__MetaParameterAssignment__Group__3 : rule__MetaParameterAssignment__Group__3__Impl ;
    public final void rule__MetaParameterAssignment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4513:1: ( rule__MetaParameterAssignment__Group__3__Impl )
            // InternalMyDsl.g:4514:2: rule__MetaParameterAssignment__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__Group__3__Impl();

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__3"


    // $ANTLR start "rule__MetaParameterAssignment__Group__3__Impl"
    // InternalMyDsl.g:4520:1: rule__MetaParameterAssignment__Group__3__Impl : ( ( rule__MetaParameterAssignment__ValueAssignment_3 ) ) ;
    public final void rule__MetaParameterAssignment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4524:1: ( ( ( rule__MetaParameterAssignment__ValueAssignment_3 ) ) )
            // InternalMyDsl.g:4525:1: ( ( rule__MetaParameterAssignment__ValueAssignment_3 ) )
            {
            // InternalMyDsl.g:4525:1: ( ( rule__MetaParameterAssignment__ValueAssignment_3 ) )
            // InternalMyDsl.g:4526:2: ( rule__MetaParameterAssignment__ValueAssignment_3 )
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getValueAssignment_3()); 
            // InternalMyDsl.g:4527:2: ( rule__MetaParameterAssignment__ValueAssignment_3 )
            // InternalMyDsl.g:4527:3: rule__MetaParameterAssignment__ValueAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__MetaParameterAssignment__ValueAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getMetaParameterAssignmentAccess().getValueAssignment_3()); 

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
    // $ANTLR end "rule__MetaParameterAssignment__Group__3__Impl"


    // $ANTLR start "rule__Solution__Group__0"
    // InternalMyDsl.g:4536:1: rule__Solution__Group__0 : rule__Solution__Group__0__Impl rule__Solution__Group__1 ;
    public final void rule__Solution__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4540:1: ( rule__Solution__Group__0__Impl rule__Solution__Group__1 )
            // InternalMyDsl.g:4541:2: rule__Solution__Group__0__Impl rule__Solution__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__Solution__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Solution__Group__1();

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
    // $ANTLR end "rule__Solution__Group__0"


    // $ANTLR start "rule__Solution__Group__0__Impl"
    // InternalMyDsl.g:4548:1: rule__Solution__Group__0__Impl : ( 'solution' ) ;
    public final void rule__Solution__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4552:1: ( ( 'solution' ) )
            // InternalMyDsl.g:4553:1: ( 'solution' )
            {
            // InternalMyDsl.g:4553:1: ( 'solution' )
            // InternalMyDsl.g:4554:2: 'solution'
            {
             before(grammarAccess.getSolutionAccess().getSolutionKeyword_0()); 
            match(input,47,FOLLOW_2); 
             after(grammarAccess.getSolutionAccess().getSolutionKeyword_0()); 

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
    // $ANTLR end "rule__Solution__Group__0__Impl"


    // $ANTLR start "rule__Solution__Group__1"
    // InternalMyDsl.g:4563:1: rule__Solution__Group__1 : rule__Solution__Group__1__Impl rule__Solution__Group__2 ;
    public final void rule__Solution__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4567:1: ( rule__Solution__Group__1__Impl rule__Solution__Group__2 )
            // InternalMyDsl.g:4568:2: rule__Solution__Group__1__Impl rule__Solution__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__Solution__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Solution__Group__2();

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
    // $ANTLR end "rule__Solution__Group__1"


    // $ANTLR start "rule__Solution__Group__1__Impl"
    // InternalMyDsl.g:4575:1: rule__Solution__Group__1__Impl : ( '{' ) ;
    public final void rule__Solution__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4579:1: ( ( '{' ) )
            // InternalMyDsl.g:4580:1: ( '{' )
            {
            // InternalMyDsl.g:4580:1: ( '{' )
            // InternalMyDsl.g:4581:2: '{'
            {
             before(grammarAccess.getSolutionAccess().getLeftCurlyBracketKeyword_1()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getSolutionAccess().getLeftCurlyBracketKeyword_1()); 

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
    // $ANTLR end "rule__Solution__Group__1__Impl"


    // $ANTLR start "rule__Solution__Group__2"
    // InternalMyDsl.g:4590:1: rule__Solution__Group__2 : rule__Solution__Group__2__Impl rule__Solution__Group__3 ;
    public final void rule__Solution__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4594:1: ( rule__Solution__Group__2__Impl rule__Solution__Group__3 )
            // InternalMyDsl.g:4595:2: rule__Solution__Group__2__Impl rule__Solution__Group__3
            {
            pushFollow(FOLLOW_34);
            rule__Solution__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Solution__Group__3();

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
    // $ANTLR end "rule__Solution__Group__2"


    // $ANTLR start "rule__Solution__Group__2__Impl"
    // InternalMyDsl.g:4602:1: rule__Solution__Group__2__Impl : ( ( ( rule__Solution__AssignmentsAssignment_2 ) ) ( ( rule__Solution__AssignmentsAssignment_2 )* ) ) ;
    public final void rule__Solution__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4606:1: ( ( ( ( rule__Solution__AssignmentsAssignment_2 ) ) ( ( rule__Solution__AssignmentsAssignment_2 )* ) ) )
            // InternalMyDsl.g:4607:1: ( ( ( rule__Solution__AssignmentsAssignment_2 ) ) ( ( rule__Solution__AssignmentsAssignment_2 )* ) )
            {
            // InternalMyDsl.g:4607:1: ( ( ( rule__Solution__AssignmentsAssignment_2 ) ) ( ( rule__Solution__AssignmentsAssignment_2 )* ) )
            // InternalMyDsl.g:4608:2: ( ( rule__Solution__AssignmentsAssignment_2 ) ) ( ( rule__Solution__AssignmentsAssignment_2 )* )
            {
            // InternalMyDsl.g:4608:2: ( ( rule__Solution__AssignmentsAssignment_2 ) )
            // InternalMyDsl.g:4609:3: ( rule__Solution__AssignmentsAssignment_2 )
            {
             before(grammarAccess.getSolutionAccess().getAssignmentsAssignment_2()); 
            // InternalMyDsl.g:4610:3: ( rule__Solution__AssignmentsAssignment_2 )
            // InternalMyDsl.g:4610:4: rule__Solution__AssignmentsAssignment_2
            {
            pushFollow(FOLLOW_21);
            rule__Solution__AssignmentsAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getSolutionAccess().getAssignmentsAssignment_2()); 

            }

            // InternalMyDsl.g:4613:2: ( ( rule__Solution__AssignmentsAssignment_2 )* )
            // InternalMyDsl.g:4614:3: ( rule__Solution__AssignmentsAssignment_2 )*
            {
             before(grammarAccess.getSolutionAccess().getAssignmentsAssignment_2()); 
            // InternalMyDsl.g:4615:3: ( rule__Solution__AssignmentsAssignment_2 )*
            loop37:
            do {
                int alt37=2;
                int LA37_0 = input.LA(1);

                if ( (LA37_0==RULE_ID) ) {
                    alt37=1;
                }


                switch (alt37) {
            	case 1 :
            	    // InternalMyDsl.g:4615:4: rule__Solution__AssignmentsAssignment_2
            	    {
            	    pushFollow(FOLLOW_21);
            	    rule__Solution__AssignmentsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop37;
                }
            } while (true);

             after(grammarAccess.getSolutionAccess().getAssignmentsAssignment_2()); 

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
    // $ANTLR end "rule__Solution__Group__2__Impl"


    // $ANTLR start "rule__Solution__Group__3"
    // InternalMyDsl.g:4624:1: rule__Solution__Group__3 : rule__Solution__Group__3__Impl ;
    public final void rule__Solution__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4628:1: ( rule__Solution__Group__3__Impl )
            // InternalMyDsl.g:4629:2: rule__Solution__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Solution__Group__3__Impl();

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
    // $ANTLR end "rule__Solution__Group__3"


    // $ANTLR start "rule__Solution__Group__3__Impl"
    // InternalMyDsl.g:4635:1: rule__Solution__Group__3__Impl : ( '}' ) ;
    public final void rule__Solution__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4639:1: ( ( '}' ) )
            // InternalMyDsl.g:4640:1: ( '}' )
            {
            // InternalMyDsl.g:4640:1: ( '}' )
            // InternalMyDsl.g:4641:2: '}'
            {
             before(grammarAccess.getSolutionAccess().getRightCurlyBracketKeyword_3()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getSolutionAccess().getRightCurlyBracketKeyword_3()); 

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
    // $ANTLR end "rule__Solution__Group__3__Impl"


    // $ANTLR start "rule__Assignment__Group__0"
    // InternalMyDsl.g:4651:1: rule__Assignment__Group__0 : rule__Assignment__Group__0__Impl rule__Assignment__Group__1 ;
    public final void rule__Assignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4655:1: ( rule__Assignment__Group__0__Impl rule__Assignment__Group__1 )
            // InternalMyDsl.g:4656:2: rule__Assignment__Group__0__Impl rule__Assignment__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Assignment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__1();

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
    // $ANTLR end "rule__Assignment__Group__0"


    // $ANTLR start "rule__Assignment__Group__0__Impl"
    // InternalMyDsl.g:4663:1: rule__Assignment__Group__0__Impl : ( ( rule__Assignment__Group_0__0 )? ) ;
    public final void rule__Assignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4667:1: ( ( ( rule__Assignment__Group_0__0 )? ) )
            // InternalMyDsl.g:4668:1: ( ( rule__Assignment__Group_0__0 )? )
            {
            // InternalMyDsl.g:4668:1: ( ( rule__Assignment__Group_0__0 )? )
            // InternalMyDsl.g:4669:2: ( rule__Assignment__Group_0__0 )?
            {
             before(grammarAccess.getAssignmentAccess().getGroup_0()); 
            // InternalMyDsl.g:4670:2: ( rule__Assignment__Group_0__0 )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==RULE_ID) ) {
                int LA38_1 = input.LA(2);

                if ( (LA38_1==48) ) {
                    alt38=1;
                }
            }
            switch (alt38) {
                case 1 :
                    // InternalMyDsl.g:4670:3: rule__Assignment__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Assignment__Group_0__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getAssignmentAccess().getGroup_0()); 

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
    // $ANTLR end "rule__Assignment__Group__0__Impl"


    // $ANTLR start "rule__Assignment__Group__1"
    // InternalMyDsl.g:4678:1: rule__Assignment__Group__1 : rule__Assignment__Group__1__Impl rule__Assignment__Group__2 ;
    public final void rule__Assignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4682:1: ( rule__Assignment__Group__1__Impl rule__Assignment__Group__2 )
            // InternalMyDsl.g:4683:2: rule__Assignment__Group__1__Impl rule__Assignment__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__Assignment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__2();

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
    // $ANTLR end "rule__Assignment__Group__1"


    // $ANTLR start "rule__Assignment__Group__1__Impl"
    // InternalMyDsl.g:4690:1: rule__Assignment__Group__1__Impl : ( ( rule__Assignment__ImplementationAssignment_1 ) ) ;
    public final void rule__Assignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4694:1: ( ( ( rule__Assignment__ImplementationAssignment_1 ) ) )
            // InternalMyDsl.g:4695:1: ( ( rule__Assignment__ImplementationAssignment_1 ) )
            {
            // InternalMyDsl.g:4695:1: ( ( rule__Assignment__ImplementationAssignment_1 ) )
            // InternalMyDsl.g:4696:2: ( rule__Assignment__ImplementationAssignment_1 )
            {
             before(grammarAccess.getAssignmentAccess().getImplementationAssignment_1()); 
            // InternalMyDsl.g:4697:2: ( rule__Assignment__ImplementationAssignment_1 )
            // InternalMyDsl.g:4697:3: rule__Assignment__ImplementationAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__ImplementationAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getImplementationAssignment_1()); 

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
    // $ANTLR end "rule__Assignment__Group__1__Impl"


    // $ANTLR start "rule__Assignment__Group__2"
    // InternalMyDsl.g:4705:1: rule__Assignment__Group__2 : rule__Assignment__Group__2__Impl rule__Assignment__Group__3 ;
    public final void rule__Assignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4709:1: ( rule__Assignment__Group__2__Impl rule__Assignment__Group__3 )
            // InternalMyDsl.g:4710:2: rule__Assignment__Group__2__Impl rule__Assignment__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__Assignment__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__3();

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
    // $ANTLR end "rule__Assignment__Group__2"


    // $ANTLR start "rule__Assignment__Group__2__Impl"
    // InternalMyDsl.g:4717:1: rule__Assignment__Group__2__Impl : ( '{' ) ;
    public final void rule__Assignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4721:1: ( ( '{' ) )
            // InternalMyDsl.g:4722:1: ( '{' )
            {
            // InternalMyDsl.g:4722:1: ( '{' )
            // InternalMyDsl.g:4723:2: '{'
            {
             before(grammarAccess.getAssignmentAccess().getLeftCurlyBracketKeyword_2()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getLeftCurlyBracketKeyword_2()); 

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
    // $ANTLR end "rule__Assignment__Group__2__Impl"


    // $ANTLR start "rule__Assignment__Group__3"
    // InternalMyDsl.g:4732:1: rule__Assignment__Group__3 : rule__Assignment__Group__3__Impl rule__Assignment__Group__4 ;
    public final void rule__Assignment__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4736:1: ( rule__Assignment__Group__3__Impl rule__Assignment__Group__4 )
            // InternalMyDsl.g:4737:2: rule__Assignment__Group__3__Impl rule__Assignment__Group__4
            {
            pushFollow(FOLLOW_53);
            rule__Assignment__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__4();

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
    // $ANTLR end "rule__Assignment__Group__3"


    // $ANTLR start "rule__Assignment__Group__3__Impl"
    // InternalMyDsl.g:4744:1: rule__Assignment__Group__3__Impl : ( ( ( rule__Assignment__ResourceMappingsAssignment_3 ) ) ( ( rule__Assignment__ResourceMappingsAssignment_3 )* ) ) ;
    public final void rule__Assignment__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4748:1: ( ( ( ( rule__Assignment__ResourceMappingsAssignment_3 ) ) ( ( rule__Assignment__ResourceMappingsAssignment_3 )* ) ) )
            // InternalMyDsl.g:4749:1: ( ( ( rule__Assignment__ResourceMappingsAssignment_3 ) ) ( ( rule__Assignment__ResourceMappingsAssignment_3 )* ) )
            {
            // InternalMyDsl.g:4749:1: ( ( ( rule__Assignment__ResourceMappingsAssignment_3 ) ) ( ( rule__Assignment__ResourceMappingsAssignment_3 )* ) )
            // InternalMyDsl.g:4750:2: ( ( rule__Assignment__ResourceMappingsAssignment_3 ) ) ( ( rule__Assignment__ResourceMappingsAssignment_3 )* )
            {
            // InternalMyDsl.g:4750:2: ( ( rule__Assignment__ResourceMappingsAssignment_3 ) )
            // InternalMyDsl.g:4751:3: ( rule__Assignment__ResourceMappingsAssignment_3 )
            {
             before(grammarAccess.getAssignmentAccess().getResourceMappingsAssignment_3()); 
            // InternalMyDsl.g:4752:3: ( rule__Assignment__ResourceMappingsAssignment_3 )
            // InternalMyDsl.g:4752:4: rule__Assignment__ResourceMappingsAssignment_3
            {
            pushFollow(FOLLOW_21);
            rule__Assignment__ResourceMappingsAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getResourceMappingsAssignment_3()); 

            }

            // InternalMyDsl.g:4755:2: ( ( rule__Assignment__ResourceMappingsAssignment_3 )* )
            // InternalMyDsl.g:4756:3: ( rule__Assignment__ResourceMappingsAssignment_3 )*
            {
             before(grammarAccess.getAssignmentAccess().getResourceMappingsAssignment_3()); 
            // InternalMyDsl.g:4757:3: ( rule__Assignment__ResourceMappingsAssignment_3 )*
            loop39:
            do {
                int alt39=2;
                int LA39_0 = input.LA(1);

                if ( (LA39_0==RULE_ID) ) {
                    int LA39_1 = input.LA(2);

                    if ( (LA39_1==48) ) {
                        alt39=1;
                    }


                }


                switch (alt39) {
            	case 1 :
            	    // InternalMyDsl.g:4757:4: rule__Assignment__ResourceMappingsAssignment_3
            	    {
            	    pushFollow(FOLLOW_21);
            	    rule__Assignment__ResourceMappingsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop39;
                }
            } while (true);

             after(grammarAccess.getAssignmentAccess().getResourceMappingsAssignment_3()); 

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
    // $ANTLR end "rule__Assignment__Group__3__Impl"


    // $ANTLR start "rule__Assignment__Group__4"
    // InternalMyDsl.g:4766:1: rule__Assignment__Group__4 : rule__Assignment__Group__4__Impl rule__Assignment__Group__5 ;
    public final void rule__Assignment__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4770:1: ( rule__Assignment__Group__4__Impl rule__Assignment__Group__5 )
            // InternalMyDsl.g:4771:2: rule__Assignment__Group__4__Impl rule__Assignment__Group__5
            {
            pushFollow(FOLLOW_53);
            rule__Assignment__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__5();

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
    // $ANTLR end "rule__Assignment__Group__4"


    // $ANTLR start "rule__Assignment__Group__4__Impl"
    // InternalMyDsl.g:4778:1: rule__Assignment__Group__4__Impl : ( ( rule__Assignment__ComponentMappingsAssignment_4 )* ) ;
    public final void rule__Assignment__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4782:1: ( ( ( rule__Assignment__ComponentMappingsAssignment_4 )* ) )
            // InternalMyDsl.g:4783:1: ( ( rule__Assignment__ComponentMappingsAssignment_4 )* )
            {
            // InternalMyDsl.g:4783:1: ( ( rule__Assignment__ComponentMappingsAssignment_4 )* )
            // InternalMyDsl.g:4784:2: ( rule__Assignment__ComponentMappingsAssignment_4 )*
            {
             before(grammarAccess.getAssignmentAccess().getComponentMappingsAssignment_4()); 
            // InternalMyDsl.g:4785:2: ( rule__Assignment__ComponentMappingsAssignment_4 )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==RULE_ID) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // InternalMyDsl.g:4785:3: rule__Assignment__ComponentMappingsAssignment_4
            	    {
            	    pushFollow(FOLLOW_21);
            	    rule__Assignment__ComponentMappingsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);

             after(grammarAccess.getAssignmentAccess().getComponentMappingsAssignment_4()); 

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
    // $ANTLR end "rule__Assignment__Group__4__Impl"


    // $ANTLR start "rule__Assignment__Group__5"
    // InternalMyDsl.g:4793:1: rule__Assignment__Group__5 : rule__Assignment__Group__5__Impl ;
    public final void rule__Assignment__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4797:1: ( rule__Assignment__Group__5__Impl )
            // InternalMyDsl.g:4798:2: rule__Assignment__Group__5__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__5__Impl();

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
    // $ANTLR end "rule__Assignment__Group__5"


    // $ANTLR start "rule__Assignment__Group__5__Impl"
    // InternalMyDsl.g:4804:1: rule__Assignment__Group__5__Impl : ( '}' ) ;
    public final void rule__Assignment__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4808:1: ( ( '}' ) )
            // InternalMyDsl.g:4809:1: ( '}' )
            {
            // InternalMyDsl.g:4809:1: ( '}' )
            // InternalMyDsl.g:4810:2: '}'
            {
             before(grammarAccess.getAssignmentAccess().getRightCurlyBracketKeyword_5()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getRightCurlyBracketKeyword_5()); 

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
    // $ANTLR end "rule__Assignment__Group__5__Impl"


    // $ANTLR start "rule__Assignment__Group_0__0"
    // InternalMyDsl.g:4820:1: rule__Assignment__Group_0__0 : rule__Assignment__Group_0__0__Impl rule__Assignment__Group_0__1 ;
    public final void rule__Assignment__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4824:1: ( rule__Assignment__Group_0__0__Impl rule__Assignment__Group_0__1 )
            // InternalMyDsl.g:4825:2: rule__Assignment__Group_0__0__Impl rule__Assignment__Group_0__1
            {
            pushFollow(FOLLOW_54);
            rule__Assignment__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group_0__1();

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
    // $ANTLR end "rule__Assignment__Group_0__0"


    // $ANTLR start "rule__Assignment__Group_0__0__Impl"
    // InternalMyDsl.g:4832:1: rule__Assignment__Group_0__0__Impl : ( ( rule__Assignment__RequestAssignment_0_0 ) ) ;
    public final void rule__Assignment__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4836:1: ( ( ( rule__Assignment__RequestAssignment_0_0 ) ) )
            // InternalMyDsl.g:4837:1: ( ( rule__Assignment__RequestAssignment_0_0 ) )
            {
            // InternalMyDsl.g:4837:1: ( ( rule__Assignment__RequestAssignment_0_0 ) )
            // InternalMyDsl.g:4838:2: ( rule__Assignment__RequestAssignment_0_0 )
            {
             before(grammarAccess.getAssignmentAccess().getRequestAssignment_0_0()); 
            // InternalMyDsl.g:4839:2: ( rule__Assignment__RequestAssignment_0_0 )
            // InternalMyDsl.g:4839:3: rule__Assignment__RequestAssignment_0_0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__RequestAssignment_0_0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getRequestAssignment_0_0()); 

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
    // $ANTLR end "rule__Assignment__Group_0__0__Impl"


    // $ANTLR start "rule__Assignment__Group_0__1"
    // InternalMyDsl.g:4847:1: rule__Assignment__Group_0__1 : rule__Assignment__Group_0__1__Impl ;
    public final void rule__Assignment__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4851:1: ( rule__Assignment__Group_0__1__Impl )
            // InternalMyDsl.g:4852:2: rule__Assignment__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group_0__1__Impl();

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
    // $ANTLR end "rule__Assignment__Group_0__1"


    // $ANTLR start "rule__Assignment__Group_0__1__Impl"
    // InternalMyDsl.g:4858:1: rule__Assignment__Group_0__1__Impl : ( '->' ) ;
    public final void rule__Assignment__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4862:1: ( ( '->' ) )
            // InternalMyDsl.g:4863:1: ( '->' )
            {
            // InternalMyDsl.g:4863:1: ( '->' )
            // InternalMyDsl.g:4864:2: '->'
            {
             before(grammarAccess.getAssignmentAccess().getHyphenMinusGreaterThanSignKeyword_0_1()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getHyphenMinusGreaterThanSignKeyword_0_1()); 

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
    // $ANTLR end "rule__Assignment__Group_0__1__Impl"


    // $ANTLR start "rule__ResourceMapping__Group__0"
    // InternalMyDsl.g:4874:1: rule__ResourceMapping__Group__0 : rule__ResourceMapping__Group__0__Impl rule__ResourceMapping__Group__1 ;
    public final void rule__ResourceMapping__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4878:1: ( rule__ResourceMapping__Group__0__Impl rule__ResourceMapping__Group__1 )
            // InternalMyDsl.g:4879:2: rule__ResourceMapping__Group__0__Impl rule__ResourceMapping__Group__1
            {
            pushFollow(FOLLOW_54);
            rule__ResourceMapping__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group__1();

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
    // $ANTLR end "rule__ResourceMapping__Group__0"


    // $ANTLR start "rule__ResourceMapping__Group__0__Impl"
    // InternalMyDsl.g:4886:1: rule__ResourceMapping__Group__0__Impl : ( ( rule__ResourceMapping__RequiredResourceAssignment_0 ) ) ;
    public final void rule__ResourceMapping__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4890:1: ( ( ( rule__ResourceMapping__RequiredResourceAssignment_0 ) ) )
            // InternalMyDsl.g:4891:1: ( ( rule__ResourceMapping__RequiredResourceAssignment_0 ) )
            {
            // InternalMyDsl.g:4891:1: ( ( rule__ResourceMapping__RequiredResourceAssignment_0 ) )
            // InternalMyDsl.g:4892:2: ( rule__ResourceMapping__RequiredResourceAssignment_0 )
            {
             before(grammarAccess.getResourceMappingAccess().getRequiredResourceAssignment_0()); 
            // InternalMyDsl.g:4893:2: ( rule__ResourceMapping__RequiredResourceAssignment_0 )
            // InternalMyDsl.g:4893:3: rule__ResourceMapping__RequiredResourceAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceMapping__RequiredResourceAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getResourceMappingAccess().getRequiredResourceAssignment_0()); 

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
    // $ANTLR end "rule__ResourceMapping__Group__0__Impl"


    // $ANTLR start "rule__ResourceMapping__Group__1"
    // InternalMyDsl.g:4901:1: rule__ResourceMapping__Group__1 : rule__ResourceMapping__Group__1__Impl rule__ResourceMapping__Group__2 ;
    public final void rule__ResourceMapping__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4905:1: ( rule__ResourceMapping__Group__1__Impl rule__ResourceMapping__Group__2 )
            // InternalMyDsl.g:4906:2: rule__ResourceMapping__Group__1__Impl rule__ResourceMapping__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__ResourceMapping__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group__2();

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
    // $ANTLR end "rule__ResourceMapping__Group__1"


    // $ANTLR start "rule__ResourceMapping__Group__1__Impl"
    // InternalMyDsl.g:4913:1: rule__ResourceMapping__Group__1__Impl : ( '->' ) ;
    public final void rule__ResourceMapping__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4917:1: ( ( '->' ) )
            // InternalMyDsl.g:4918:1: ( '->' )
            {
            // InternalMyDsl.g:4918:1: ( '->' )
            // InternalMyDsl.g:4919:2: '->'
            {
             before(grammarAccess.getResourceMappingAccess().getHyphenMinusGreaterThanSignKeyword_1()); 
            match(input,48,FOLLOW_2); 
             after(grammarAccess.getResourceMappingAccess().getHyphenMinusGreaterThanSignKeyword_1()); 

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
    // $ANTLR end "rule__ResourceMapping__Group__1__Impl"


    // $ANTLR start "rule__ResourceMapping__Group__2"
    // InternalMyDsl.g:4928:1: rule__ResourceMapping__Group__2 : rule__ResourceMapping__Group__2__Impl rule__ResourceMapping__Group__3 ;
    public final void rule__ResourceMapping__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4932:1: ( rule__ResourceMapping__Group__2__Impl rule__ResourceMapping__Group__3 )
            // InternalMyDsl.g:4933:2: rule__ResourceMapping__Group__2__Impl rule__ResourceMapping__Group__3
            {
            pushFollow(FOLLOW_15);
            rule__ResourceMapping__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group__3();

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
    // $ANTLR end "rule__ResourceMapping__Group__2"


    // $ANTLR start "rule__ResourceMapping__Group__2__Impl"
    // InternalMyDsl.g:4940:1: rule__ResourceMapping__Group__2__Impl : ( ( rule__ResourceMapping__UsedResourceAssignment_2 ) ) ;
    public final void rule__ResourceMapping__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4944:1: ( ( ( rule__ResourceMapping__UsedResourceAssignment_2 ) ) )
            // InternalMyDsl.g:4945:1: ( ( rule__ResourceMapping__UsedResourceAssignment_2 ) )
            {
            // InternalMyDsl.g:4945:1: ( ( rule__ResourceMapping__UsedResourceAssignment_2 ) )
            // InternalMyDsl.g:4946:2: ( rule__ResourceMapping__UsedResourceAssignment_2 )
            {
             before(grammarAccess.getResourceMappingAccess().getUsedResourceAssignment_2()); 
            // InternalMyDsl.g:4947:2: ( rule__ResourceMapping__UsedResourceAssignment_2 )
            // InternalMyDsl.g:4947:3: rule__ResourceMapping__UsedResourceAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ResourceMapping__UsedResourceAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getResourceMappingAccess().getUsedResourceAssignment_2()); 

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
    // $ANTLR end "rule__ResourceMapping__Group__2__Impl"


    // $ANTLR start "rule__ResourceMapping__Group__3"
    // InternalMyDsl.g:4955:1: rule__ResourceMapping__Group__3 : rule__ResourceMapping__Group__3__Impl ;
    public final void rule__ResourceMapping__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4959:1: ( rule__ResourceMapping__Group__3__Impl )
            // InternalMyDsl.g:4960:2: rule__ResourceMapping__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group__3__Impl();

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
    // $ANTLR end "rule__ResourceMapping__Group__3"


    // $ANTLR start "rule__ResourceMapping__Group__3__Impl"
    // InternalMyDsl.g:4966:1: rule__ResourceMapping__Group__3__Impl : ( ( rule__ResourceMapping__Group_3__0 )? ) ;
    public final void rule__ResourceMapping__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4970:1: ( ( ( rule__ResourceMapping__Group_3__0 )? ) )
            // InternalMyDsl.g:4971:1: ( ( rule__ResourceMapping__Group_3__0 )? )
            {
            // InternalMyDsl.g:4971:1: ( ( rule__ResourceMapping__Group_3__0 )? )
            // InternalMyDsl.g:4972:2: ( rule__ResourceMapping__Group_3__0 )?
            {
             before(grammarAccess.getResourceMappingAccess().getGroup_3()); 
            // InternalMyDsl.g:4973:2: ( rule__ResourceMapping__Group_3__0 )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==24) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // InternalMyDsl.g:4973:3: rule__ResourceMapping__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResourceMapping__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getResourceMappingAccess().getGroup_3()); 

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
    // $ANTLR end "rule__ResourceMapping__Group__3__Impl"


    // $ANTLR start "rule__ResourceMapping__Group_3__0"
    // InternalMyDsl.g:4982:1: rule__ResourceMapping__Group_3__0 : rule__ResourceMapping__Group_3__0__Impl rule__ResourceMapping__Group_3__1 ;
    public final void rule__ResourceMapping__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4986:1: ( rule__ResourceMapping__Group_3__0__Impl rule__ResourceMapping__Group_3__1 )
            // InternalMyDsl.g:4987:2: rule__ResourceMapping__Group_3__0__Impl rule__ResourceMapping__Group_3__1
            {
            pushFollow(FOLLOW_14);
            rule__ResourceMapping__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group_3__1();

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
    // $ANTLR end "rule__ResourceMapping__Group_3__0"


    // $ANTLR start "rule__ResourceMapping__Group_3__0__Impl"
    // InternalMyDsl.g:4994:1: rule__ResourceMapping__Group_3__0__Impl : ( '{' ) ;
    public final void rule__ResourceMapping__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:4998:1: ( ( '{' ) )
            // InternalMyDsl.g:4999:1: ( '{' )
            {
            // InternalMyDsl.g:4999:1: ( '{' )
            // InternalMyDsl.g:5000:2: '{'
            {
             before(grammarAccess.getResourceMappingAccess().getLeftCurlyBracketKeyword_3_0()); 
            match(input,24,FOLLOW_2); 
             after(grammarAccess.getResourceMappingAccess().getLeftCurlyBracketKeyword_3_0()); 

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
    // $ANTLR end "rule__ResourceMapping__Group_3__0__Impl"


    // $ANTLR start "rule__ResourceMapping__Group_3__1"
    // InternalMyDsl.g:5009:1: rule__ResourceMapping__Group_3__1 : rule__ResourceMapping__Group_3__1__Impl rule__ResourceMapping__Group_3__2 ;
    public final void rule__ResourceMapping__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5013:1: ( rule__ResourceMapping__Group_3__1__Impl rule__ResourceMapping__Group_3__2 )
            // InternalMyDsl.g:5014:2: rule__ResourceMapping__Group_3__1__Impl rule__ResourceMapping__Group_3__2
            {
            pushFollow(FOLLOW_34);
            rule__ResourceMapping__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group_3__2();

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
    // $ANTLR end "rule__ResourceMapping__Group_3__1"


    // $ANTLR start "rule__ResourceMapping__Group_3__1__Impl"
    // InternalMyDsl.g:5021:1: rule__ResourceMapping__Group_3__1__Impl : ( ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 ) ) ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 )* ) ) ;
    public final void rule__ResourceMapping__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5025:1: ( ( ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 ) ) ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 )* ) ) )
            // InternalMyDsl.g:5026:1: ( ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 ) ) ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 )* ) )
            {
            // InternalMyDsl.g:5026:1: ( ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 ) ) ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 )* ) )
            // InternalMyDsl.g:5027:2: ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 ) ) ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 )* )
            {
            // InternalMyDsl.g:5027:2: ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 ) )
            // InternalMyDsl.g:5028:3: ( rule__ResourceMapping__SubMappingsAssignment_3_1 )
            {
             before(grammarAccess.getResourceMappingAccess().getSubMappingsAssignment_3_1()); 
            // InternalMyDsl.g:5029:3: ( rule__ResourceMapping__SubMappingsAssignment_3_1 )
            // InternalMyDsl.g:5029:4: rule__ResourceMapping__SubMappingsAssignment_3_1
            {
            pushFollow(FOLLOW_21);
            rule__ResourceMapping__SubMappingsAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceMappingAccess().getSubMappingsAssignment_3_1()); 

            }

            // InternalMyDsl.g:5032:2: ( ( rule__ResourceMapping__SubMappingsAssignment_3_1 )* )
            // InternalMyDsl.g:5033:3: ( rule__ResourceMapping__SubMappingsAssignment_3_1 )*
            {
             before(grammarAccess.getResourceMappingAccess().getSubMappingsAssignment_3_1()); 
            // InternalMyDsl.g:5034:3: ( rule__ResourceMapping__SubMappingsAssignment_3_1 )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==RULE_ID) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // InternalMyDsl.g:5034:4: rule__ResourceMapping__SubMappingsAssignment_3_1
            	    {
            	    pushFollow(FOLLOW_21);
            	    rule__ResourceMapping__SubMappingsAssignment_3_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop42;
                }
            } while (true);

             after(grammarAccess.getResourceMappingAccess().getSubMappingsAssignment_3_1()); 

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
    // $ANTLR end "rule__ResourceMapping__Group_3__1__Impl"


    // $ANTLR start "rule__ResourceMapping__Group_3__2"
    // InternalMyDsl.g:5043:1: rule__ResourceMapping__Group_3__2 : rule__ResourceMapping__Group_3__2__Impl ;
    public final void rule__ResourceMapping__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5047:1: ( rule__ResourceMapping__Group_3__2__Impl )
            // InternalMyDsl.g:5048:2: rule__ResourceMapping__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceMapping__Group_3__2__Impl();

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
    // $ANTLR end "rule__ResourceMapping__Group_3__2"


    // $ANTLR start "rule__ResourceMapping__Group_3__2__Impl"
    // InternalMyDsl.g:5054:1: rule__ResourceMapping__Group_3__2__Impl : ( '}' ) ;
    public final void rule__ResourceMapping__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5058:1: ( ( '}' ) )
            // InternalMyDsl.g:5059:1: ( '}' )
            {
            // InternalMyDsl.g:5059:1: ( '}' )
            // InternalMyDsl.g:5060:2: '}'
            {
             before(grammarAccess.getResourceMappingAccess().getRightCurlyBracketKeyword_3_2()); 
            match(input,25,FOLLOW_2); 
             after(grammarAccess.getResourceMappingAccess().getRightCurlyBracketKeyword_3_2()); 

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
    // $ANTLR end "rule__ResourceMapping__Group_3__2__Impl"


    // $ANTLR start "rule__ComponentMapping__Group__0"
    // InternalMyDsl.g:5070:1: rule__ComponentMapping__Group__0 : rule__ComponentMapping__Group__0__Impl rule__ComponentMapping__Group__1 ;
    public final void rule__ComponentMapping__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5074:1: ( rule__ComponentMapping__Group__0__Impl rule__ComponentMapping__Group__1 )
            // InternalMyDsl.g:5075:2: rule__ComponentMapping__Group__0__Impl rule__ComponentMapping__Group__1
            {
            pushFollow(FOLLOW_55);
            rule__ComponentMapping__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ComponentMapping__Group__1();

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
    // $ANTLR end "rule__ComponentMapping__Group__0"


    // $ANTLR start "rule__ComponentMapping__Group__0__Impl"
    // InternalMyDsl.g:5082:1: rule__ComponentMapping__Group__0__Impl : ( ( rule__ComponentMapping__ComponentAssignment_0 ) ) ;
    public final void rule__ComponentMapping__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5086:1: ( ( ( rule__ComponentMapping__ComponentAssignment_0 ) ) )
            // InternalMyDsl.g:5087:1: ( ( rule__ComponentMapping__ComponentAssignment_0 ) )
            {
            // InternalMyDsl.g:5087:1: ( ( rule__ComponentMapping__ComponentAssignment_0 ) )
            // InternalMyDsl.g:5088:2: ( rule__ComponentMapping__ComponentAssignment_0 )
            {
             before(grammarAccess.getComponentMappingAccess().getComponentAssignment_0()); 
            // InternalMyDsl.g:5089:2: ( rule__ComponentMapping__ComponentAssignment_0 )
            // InternalMyDsl.g:5089:3: rule__ComponentMapping__ComponentAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ComponentMapping__ComponentAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getComponentMappingAccess().getComponentAssignment_0()); 

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
    // $ANTLR end "rule__ComponentMapping__Group__0__Impl"


    // $ANTLR start "rule__ComponentMapping__Group__1"
    // InternalMyDsl.g:5097:1: rule__ComponentMapping__Group__1 : rule__ComponentMapping__Group__1__Impl rule__ComponentMapping__Group__2 ;
    public final void rule__ComponentMapping__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5101:1: ( rule__ComponentMapping__Group__1__Impl rule__ComponentMapping__Group__2 )
            // InternalMyDsl.g:5102:2: rule__ComponentMapping__Group__1__Impl rule__ComponentMapping__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__ComponentMapping__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ComponentMapping__Group__2();

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
    // $ANTLR end "rule__ComponentMapping__Group__1"


    // $ANTLR start "rule__ComponentMapping__Group__1__Impl"
    // InternalMyDsl.g:5109:1: rule__ComponentMapping__Group__1__Impl : ( '-->' ) ;
    public final void rule__ComponentMapping__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5113:1: ( ( '-->' ) )
            // InternalMyDsl.g:5114:1: ( '-->' )
            {
            // InternalMyDsl.g:5114:1: ( '-->' )
            // InternalMyDsl.g:5115:2: '-->'
            {
             before(grammarAccess.getComponentMappingAccess().getHyphenMinusHyphenMinusGreaterThanSignKeyword_1()); 
            match(input,49,FOLLOW_2); 
             after(grammarAccess.getComponentMappingAccess().getHyphenMinusHyphenMinusGreaterThanSignKeyword_1()); 

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
    // $ANTLR end "rule__ComponentMapping__Group__1__Impl"


    // $ANTLR start "rule__ComponentMapping__Group__2"
    // InternalMyDsl.g:5124:1: rule__ComponentMapping__Group__2 : rule__ComponentMapping__Group__2__Impl ;
    public final void rule__ComponentMapping__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5128:1: ( rule__ComponentMapping__Group__2__Impl )
            // InternalMyDsl.g:5129:2: rule__ComponentMapping__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ComponentMapping__Group__2__Impl();

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
    // $ANTLR end "rule__ComponentMapping__Group__2"


    // $ANTLR start "rule__ComponentMapping__Group__2__Impl"
    // InternalMyDsl.g:5135:1: rule__ComponentMapping__Group__2__Impl : ( ( rule__ComponentMapping__AssignmentAssignment_2 ) ) ;
    public final void rule__ComponentMapping__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5139:1: ( ( ( rule__ComponentMapping__AssignmentAssignment_2 ) ) )
            // InternalMyDsl.g:5140:1: ( ( rule__ComponentMapping__AssignmentAssignment_2 ) )
            {
            // InternalMyDsl.g:5140:1: ( ( rule__ComponentMapping__AssignmentAssignment_2 ) )
            // InternalMyDsl.g:5141:2: ( rule__ComponentMapping__AssignmentAssignment_2 )
            {
             before(grammarAccess.getComponentMappingAccess().getAssignmentAssignment_2()); 
            // InternalMyDsl.g:5142:2: ( rule__ComponentMapping__AssignmentAssignment_2 )
            // InternalMyDsl.g:5142:3: rule__ComponentMapping__AssignmentAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ComponentMapping__AssignmentAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getComponentMappingAccess().getAssignmentAssignment_2()); 

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
    // $ANTLR end "rule__ComponentMapping__Group__2__Impl"


    // $ANTLR start "rule__Double__Group__0"
    // InternalMyDsl.g:5151:1: rule__Double__Group__0 : rule__Double__Group__0__Impl rule__Double__Group__1 ;
    public final void rule__Double__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5155:1: ( rule__Double__Group__0__Impl rule__Double__Group__1 )
            // InternalMyDsl.g:5156:2: rule__Double__Group__0__Impl rule__Double__Group__1
            {
            pushFollow(FOLLOW_52);
            rule__Double__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Double__Group__1();

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
    // $ANTLR end "rule__Double__Group__0"


    // $ANTLR start "rule__Double__Group__0__Impl"
    // InternalMyDsl.g:5163:1: rule__Double__Group__0__Impl : ( RULE_INT ) ;
    public final void rule__Double__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5167:1: ( ( RULE_INT ) )
            // InternalMyDsl.g:5168:1: ( RULE_INT )
            {
            // InternalMyDsl.g:5168:1: ( RULE_INT )
            // InternalMyDsl.g:5169:2: RULE_INT
            {
             before(grammarAccess.getDoubleAccess().getINTTerminalRuleCall_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDoubleAccess().getINTTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__Double__Group__0__Impl"


    // $ANTLR start "rule__Double__Group__1"
    // InternalMyDsl.g:5178:1: rule__Double__Group__1 : rule__Double__Group__1__Impl rule__Double__Group__2 ;
    public final void rule__Double__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5182:1: ( rule__Double__Group__1__Impl rule__Double__Group__2 )
            // InternalMyDsl.g:5183:2: rule__Double__Group__1__Impl rule__Double__Group__2
            {
            pushFollow(FOLLOW_23);
            rule__Double__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Double__Group__2();

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
    // $ANTLR end "rule__Double__Group__1"


    // $ANTLR start "rule__Double__Group__1__Impl"
    // InternalMyDsl.g:5190:1: rule__Double__Group__1__Impl : ( '.' ) ;
    public final void rule__Double__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5194:1: ( ( '.' ) )
            // InternalMyDsl.g:5195:1: ( '.' )
            {
            // InternalMyDsl.g:5195:1: ( '.' )
            // InternalMyDsl.g:5196:2: '.'
            {
             before(grammarAccess.getDoubleAccess().getFullStopKeyword_1()); 
            match(input,46,FOLLOW_2); 
             after(grammarAccess.getDoubleAccess().getFullStopKeyword_1()); 

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
    // $ANTLR end "rule__Double__Group__1__Impl"


    // $ANTLR start "rule__Double__Group__2"
    // InternalMyDsl.g:5205:1: rule__Double__Group__2 : rule__Double__Group__2__Impl ;
    public final void rule__Double__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5209:1: ( rule__Double__Group__2__Impl )
            // InternalMyDsl.g:5210:2: rule__Double__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Double__Group__2__Impl();

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
    // $ANTLR end "rule__Double__Group__2"


    // $ANTLR start "rule__Double__Group__2__Impl"
    // InternalMyDsl.g:5216:1: rule__Double__Group__2__Impl : ( RULE_INT ) ;
    public final void rule__Double__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5220:1: ( ( RULE_INT ) )
            // InternalMyDsl.g:5221:1: ( RULE_INT )
            {
            // InternalMyDsl.g:5221:1: ( RULE_INT )
            // InternalMyDsl.g:5222:2: RULE_INT
            {
             before(grammarAccess.getDoubleAccess().getINTTerminalRuleCall_2()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getDoubleAccess().getINTTerminalRuleCall_2()); 

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
    // $ANTLR end "rule__Double__Group__2__Impl"


    // $ANTLR start "rule__Model__HardwareModelAssignment_0"
    // InternalMyDsl.g:5232:1: rule__Model__HardwareModelAssignment_0 : ( ruleHardwareModel ) ;
    public final void rule__Model__HardwareModelAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5236:1: ( ( ruleHardwareModel ) )
            // InternalMyDsl.g:5237:2: ( ruleHardwareModel )
            {
            // InternalMyDsl.g:5237:2: ( ruleHardwareModel )
            // InternalMyDsl.g:5238:3: ruleHardwareModel
            {
             before(grammarAccess.getModelAccess().getHardwareModelHardwareModelParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleHardwareModel();

            state._fsp--;

             after(grammarAccess.getModelAccess().getHardwareModelHardwareModelParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__Model__HardwareModelAssignment_0"


    // $ANTLR start "rule__Model__SoftwareModelAssignment_1"
    // InternalMyDsl.g:5247:1: rule__Model__SoftwareModelAssignment_1 : ( ruleSoftwareModel ) ;
    public final void rule__Model__SoftwareModelAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5251:1: ( ( ruleSoftwareModel ) )
            // InternalMyDsl.g:5252:2: ( ruleSoftwareModel )
            {
            // InternalMyDsl.g:5252:2: ( ruleSoftwareModel )
            // InternalMyDsl.g:5253:3: ruleSoftwareModel
            {
             before(grammarAccess.getModelAccess().getSoftwareModelSoftwareModelParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleSoftwareModel();

            state._fsp--;

             after(grammarAccess.getModelAccess().getSoftwareModelSoftwareModelParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__Model__SoftwareModelAssignment_1"


    // $ANTLR start "rule__Model__RequestsAssignment_2"
    // InternalMyDsl.g:5262:1: rule__Model__RequestsAssignment_2 : ( ruleRequest ) ;
    public final void rule__Model__RequestsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5266:1: ( ( ruleRequest ) )
            // InternalMyDsl.g:5267:2: ( ruleRequest )
            {
            // InternalMyDsl.g:5267:2: ( ruleRequest )
            // InternalMyDsl.g:5268:3: ruleRequest
            {
             before(grammarAccess.getModelAccess().getRequestsRequestParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleRequest();

            state._fsp--;

             after(grammarAccess.getModelAccess().getRequestsRequestParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Model__RequestsAssignment_2"


    // $ANTLR start "rule__Model__ObjectiveAssignment_3"
    // InternalMyDsl.g:5277:1: rule__Model__ObjectiveAssignment_3 : ( ruleObjective ) ;
    public final void rule__Model__ObjectiveAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5281:1: ( ( ruleObjective ) )
            // InternalMyDsl.g:5282:2: ( ruleObjective )
            {
            // InternalMyDsl.g:5282:2: ( ruleObjective )
            // InternalMyDsl.g:5283:3: ruleObjective
            {
             before(grammarAccess.getModelAccess().getObjectiveObjectiveParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleObjective();

            state._fsp--;

             after(grammarAccess.getModelAccess().getObjectiveObjectiveParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Model__ObjectiveAssignment_3"


    // $ANTLR start "rule__Model__SolutionAssignment_4"
    // InternalMyDsl.g:5292:1: rule__Model__SolutionAssignment_4 : ( ruleSolution ) ;
    public final void rule__Model__SolutionAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5296:1: ( ( ruleSolution ) )
            // InternalMyDsl.g:5297:2: ( ruleSolution )
            {
            // InternalMyDsl.g:5297:2: ( ruleSolution )
            // InternalMyDsl.g:5298:3: ruleSolution
            {
             before(grammarAccess.getModelAccess().getSolutionSolutionParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleSolution();

            state._fsp--;

             after(grammarAccess.getModelAccess().getSolutionSolutionParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Model__SolutionAssignment_4"


    // $ANTLR start "rule__HardwareModel__PropertiesAssignment_0"
    // InternalMyDsl.g:5307:1: rule__HardwareModel__PropertiesAssignment_0 : ( ruleProperty ) ;
    public final void rule__HardwareModel__PropertiesAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5311:1: ( ( ruleProperty ) )
            // InternalMyDsl.g:5312:2: ( ruleProperty )
            {
            // InternalMyDsl.g:5312:2: ( ruleProperty )
            // InternalMyDsl.g:5313:3: ruleProperty
            {
             before(grammarAccess.getHardwareModelAccess().getPropertiesPropertyParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getHardwareModelAccess().getPropertiesPropertyParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__HardwareModel__PropertiesAssignment_0"


    // $ANTLR start "rule__HardwareModel__ResourceTypesAssignment_1"
    // InternalMyDsl.g:5322:1: rule__HardwareModel__ResourceTypesAssignment_1 : ( ruleResourceType ) ;
    public final void rule__HardwareModel__ResourceTypesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5326:1: ( ( ruleResourceType ) )
            // InternalMyDsl.g:5327:2: ( ruleResourceType )
            {
            // InternalMyDsl.g:5327:2: ( ruleResourceType )
            // InternalMyDsl.g:5328:3: ruleResourceType
            {
             before(grammarAccess.getHardwareModelAccess().getResourceTypesResourceTypeParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceType();

            state._fsp--;

             after(grammarAccess.getHardwareModelAccess().getResourceTypesResourceTypeParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__HardwareModel__ResourceTypesAssignment_1"


    // $ANTLR start "rule__HardwareModel__ResourcesAssignment_2"
    // InternalMyDsl.g:5337:1: rule__HardwareModel__ResourcesAssignment_2 : ( ruleResource ) ;
    public final void rule__HardwareModel__ResourcesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5341:1: ( ( ruleResource ) )
            // InternalMyDsl.g:5342:2: ( ruleResource )
            {
            // InternalMyDsl.g:5342:2: ( ruleResource )
            // InternalMyDsl.g:5343:3: ruleResource
            {
             before(grammarAccess.getHardwareModelAccess().getResourcesResourceParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getHardwareModelAccess().getResourcesResourceParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__HardwareModel__ResourcesAssignment_2"


    // $ANTLR start "rule__ResourceType__ContainerAssignment_0"
    // InternalMyDsl.g:5352:1: rule__ResourceType__ContainerAssignment_0 : ( ( 'container' ) ) ;
    public final void rule__ResourceType__ContainerAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5356:1: ( ( ( 'container' ) ) )
            // InternalMyDsl.g:5357:2: ( ( 'container' ) )
            {
            // InternalMyDsl.g:5357:2: ( ( 'container' ) )
            // InternalMyDsl.g:5358:3: ( 'container' )
            {
             before(grammarAccess.getResourceTypeAccess().getContainerContainerKeyword_0_0()); 
            // InternalMyDsl.g:5359:3: ( 'container' )
            // InternalMyDsl.g:5360:4: 'container'
            {
             before(grammarAccess.getResourceTypeAccess().getContainerContainerKeyword_0_0()); 
            match(input,50,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getContainerContainerKeyword_0_0()); 

            }

             after(grammarAccess.getResourceTypeAccess().getContainerContainerKeyword_0_0()); 

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
    // $ANTLR end "rule__ResourceType__ContainerAssignment_0"


    // $ANTLR start "rule__ResourceType__NameAssignment_3"
    // InternalMyDsl.g:5371:1: rule__ResourceType__NameAssignment_3 : ( RULE_ID ) ;
    public final void rule__ResourceType__NameAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5375:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5376:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5376:2: ( RULE_ID )
            // InternalMyDsl.g:5377:3: RULE_ID
            {
             before(grammarAccess.getResourceTypeAccess().getNameIDTerminalRuleCall_3_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getNameIDTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__ResourceType__NameAssignment_3"


    // $ANTLR start "rule__ResourceType__ResourceTypesAssignment_5"
    // InternalMyDsl.g:5386:1: rule__ResourceType__ResourceTypesAssignment_5 : ( ruleResourceType ) ;
    public final void rule__ResourceType__ResourceTypesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5390:1: ( ( ruleResourceType ) )
            // InternalMyDsl.g:5391:2: ( ruleResourceType )
            {
            // InternalMyDsl.g:5391:2: ( ruleResourceType )
            // InternalMyDsl.g:5392:3: ruleResourceType
            {
             before(grammarAccess.getResourceTypeAccess().getResourceTypesResourceTypeParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceType();

            state._fsp--;

             after(grammarAccess.getResourceTypeAccess().getResourceTypesResourceTypeParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__ResourceType__ResourceTypesAssignment_5"


    // $ANTLR start "rule__ResourceType__PropertiesAssignment_6"
    // InternalMyDsl.g:5401:1: rule__ResourceType__PropertiesAssignment_6 : ( ruleProperty ) ;
    public final void rule__ResourceType__PropertiesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5405:1: ( ( ruleProperty ) )
            // InternalMyDsl.g:5406:2: ( ruleProperty )
            {
            // InternalMyDsl.g:5406:2: ( ruleProperty )
            // InternalMyDsl.g:5407:3: ruleProperty
            {
             before(grammarAccess.getResourceTypeAccess().getPropertiesPropertyParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getResourceTypeAccess().getPropertiesPropertyParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__ResourceType__PropertiesAssignment_6"


    // $ANTLR start "rule__ResourceType__PropertyReferencesAssignment_7_2"
    // InternalMyDsl.g:5416:1: rule__ResourceType__PropertyReferencesAssignment_7_2 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceType__PropertyReferencesAssignment_7_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5420:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5421:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5421:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5422:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceTypeAccess().getPropertyReferencesPropertyCrossReference_7_2_0()); 
            // InternalMyDsl.g:5423:3: ( RULE_ID )
            // InternalMyDsl.g:5424:4: RULE_ID
            {
             before(grammarAccess.getResourceTypeAccess().getPropertyReferencesPropertyIDTerminalRuleCall_7_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceTypeAccess().getPropertyReferencesPropertyIDTerminalRuleCall_7_2_0_1()); 

            }

             after(grammarAccess.getResourceTypeAccess().getPropertyReferencesPropertyCrossReference_7_2_0()); 

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
    // $ANTLR end "rule__ResourceType__PropertyReferencesAssignment_7_2"


    // $ANTLR start "rule__Resource__NameAssignment_1"
    // InternalMyDsl.g:5435:1: rule__Resource__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Resource__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5439:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5440:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5440:2: ( RULE_ID )
            // InternalMyDsl.g:5441:3: RULE_ID
            {
             before(grammarAccess.getResourceAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Resource__NameAssignment_1"


    // $ANTLR start "rule__Resource__TypeAssignment_3"
    // InternalMyDsl.g:5450:1: rule__Resource__TypeAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Resource__TypeAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5454:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5455:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5455:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5456:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceAccess().getTypeResourceTypeCrossReference_3_0()); 
            // InternalMyDsl.g:5457:3: ( RULE_ID )
            // InternalMyDsl.g:5458:4: RULE_ID
            {
             before(grammarAccess.getResourceAccess().getTypeResourceTypeIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceAccess().getTypeResourceTypeIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getResourceAccess().getTypeResourceTypeCrossReference_3_0()); 

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
    // $ANTLR end "rule__Resource__TypeAssignment_3"


    // $ANTLR start "rule__Resource__ResourcesAssignment_5"
    // InternalMyDsl.g:5469:1: rule__Resource__ResourcesAssignment_5 : ( ruleResource ) ;
    public final void rule__Resource__ResourcesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5473:1: ( ( ruleResource ) )
            // InternalMyDsl.g:5474:2: ( ruleResource )
            {
            // InternalMyDsl.g:5474:2: ( ruleResource )
            // InternalMyDsl.g:5475:3: ruleResource
            {
             before(grammarAccess.getResourceAccess().getResourcesResourceParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleResource();

            state._fsp--;

             after(grammarAccess.getResourceAccess().getResourcesResourceParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__Resource__ResourcesAssignment_5"


    // $ANTLR start "rule__Resource__ValuesAssignment_6"
    // InternalMyDsl.g:5484:1: rule__Resource__ValuesAssignment_6 : ( ruleCurrentResourceValue ) ;
    public final void rule__Resource__ValuesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5488:1: ( ( ruleCurrentResourceValue ) )
            // InternalMyDsl.g:5489:2: ( ruleCurrentResourceValue )
            {
            // InternalMyDsl.g:5489:2: ( ruleCurrentResourceValue )
            // InternalMyDsl.g:5490:3: ruleCurrentResourceValue
            {
             before(grammarAccess.getResourceAccess().getValuesCurrentResourceValueParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleCurrentResourceValue();

            state._fsp--;

             after(grammarAccess.getResourceAccess().getValuesCurrentResourceValueParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__Resource__ValuesAssignment_6"


    // $ANTLR start "rule__CurrentResourceValue__PropertyAssignment_0"
    // InternalMyDsl.g:5499:1: rule__CurrentResourceValue__PropertyAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__CurrentResourceValue__PropertyAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5503:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5504:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5504:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5505:3: ( RULE_ID )
            {
             before(grammarAccess.getCurrentResourceValueAccess().getPropertyPropertyCrossReference_0_0()); 
            // InternalMyDsl.g:5506:3: ( RULE_ID )
            // InternalMyDsl.g:5507:4: RULE_ID
            {
             before(grammarAccess.getCurrentResourceValueAccess().getPropertyPropertyIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCurrentResourceValueAccess().getPropertyPropertyIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getCurrentResourceValueAccess().getPropertyPropertyCrossReference_0_0()); 

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
    // $ANTLR end "rule__CurrentResourceValue__PropertyAssignment_0"


    // $ANTLR start "rule__CurrentResourceValue__ValueAssignment_2"
    // InternalMyDsl.g:5518:1: rule__CurrentResourceValue__ValueAssignment_2 : ( ruleLiteralExpression ) ;
    public final void rule__CurrentResourceValue__ValueAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5522:1: ( ( ruleLiteralExpression ) )
            // InternalMyDsl.g:5523:2: ( ruleLiteralExpression )
            {
            // InternalMyDsl.g:5523:2: ( ruleLiteralExpression )
            // InternalMyDsl.g:5524:3: ruleLiteralExpression
            {
             before(grammarAccess.getCurrentResourceValueAccess().getValueLiteralExpressionParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleLiteralExpression();

            state._fsp--;

             after(grammarAccess.getCurrentResourceValueAccess().getValueLiteralExpressionParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__CurrentResourceValue__ValueAssignment_2"


    // $ANTLR start "rule__SoftwareModel__PropertiesAssignment_0_0"
    // InternalMyDsl.g:5533:1: rule__SoftwareModel__PropertiesAssignment_0_0 : ( ruleProperty ) ;
    public final void rule__SoftwareModel__PropertiesAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5537:1: ( ( ruleProperty ) )
            // InternalMyDsl.g:5538:2: ( ruleProperty )
            {
            // InternalMyDsl.g:5538:2: ( ruleProperty )
            // InternalMyDsl.g:5539:3: ruleProperty
            {
             before(grammarAccess.getSoftwareModelAccess().getPropertiesPropertyParserRuleCall_0_0_0()); 
            pushFollow(FOLLOW_2);
            ruleProperty();

            state._fsp--;

             after(grammarAccess.getSoftwareModelAccess().getPropertiesPropertyParserRuleCall_0_0_0()); 

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
    // $ANTLR end "rule__SoftwareModel__PropertiesAssignment_0_0"


    // $ANTLR start "rule__SoftwareModel__ParametersAssignment_0_1"
    // InternalMyDsl.g:5548:1: rule__SoftwareModel__ParametersAssignment_0_1 : ( ruleMetaParameter ) ;
    public final void rule__SoftwareModel__ParametersAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5552:1: ( ( ruleMetaParameter ) )
            // InternalMyDsl.g:5553:2: ( ruleMetaParameter )
            {
            // InternalMyDsl.g:5553:2: ( ruleMetaParameter )
            // InternalMyDsl.g:5554:3: ruleMetaParameter
            {
             before(grammarAccess.getSoftwareModelAccess().getParametersMetaParameterParserRuleCall_0_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMetaParameter();

            state._fsp--;

             after(grammarAccess.getSoftwareModelAccess().getParametersMetaParameterParserRuleCall_0_1_0()); 

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
    // $ANTLR end "rule__SoftwareModel__ParametersAssignment_0_1"


    // $ANTLR start "rule__SoftwareModel__ComponentsAssignment_1"
    // InternalMyDsl.g:5563:1: rule__SoftwareModel__ComponentsAssignment_1 : ( ruleComponent ) ;
    public final void rule__SoftwareModel__ComponentsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5567:1: ( ( ruleComponent ) )
            // InternalMyDsl.g:5568:2: ( ruleComponent )
            {
            // InternalMyDsl.g:5568:2: ( ruleComponent )
            // InternalMyDsl.g:5569:3: ruleComponent
            {
             before(grammarAccess.getSoftwareModelAccess().getComponentsComponentParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getSoftwareModelAccess().getComponentsComponentParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__SoftwareModel__ComponentsAssignment_1"


    // $ANTLR start "rule__MetaParameter__NameAssignment_1"
    // InternalMyDsl.g:5578:1: rule__MetaParameter__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__MetaParameter__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5582:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5583:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5583:2: ( RULE_ID )
            // InternalMyDsl.g:5584:3: RULE_ID
            {
             before(grammarAccess.getMetaParameterAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMetaParameterAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__MetaParameter__NameAssignment_1"


    // $ANTLR start "rule__Property__NameAssignment_1"
    // InternalMyDsl.g:5593:1: rule__Property__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Property__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5597:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5598:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5598:2: ( RULE_ID )
            // InternalMyDsl.g:5599:3: RULE_ID
            {
             before(grammarAccess.getPropertyAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Property__NameAssignment_1"


    // $ANTLR start "rule__Property__UnitAssignment_2"
    // InternalMyDsl.g:5608:1: rule__Property__UnitAssignment_2 : ( RULE_UNIT ) ;
    public final void rule__Property__UnitAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5612:1: ( ( RULE_UNIT ) )
            // InternalMyDsl.g:5613:2: ( RULE_UNIT )
            {
            // InternalMyDsl.g:5613:2: ( RULE_UNIT )
            // InternalMyDsl.g:5614:3: RULE_UNIT
            {
             before(grammarAccess.getPropertyAccess().getUnitUNITTerminalRuleCall_2_0()); 
            match(input,RULE_UNIT,FOLLOW_2); 
             after(grammarAccess.getPropertyAccess().getUnitUNITTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__Property__UnitAssignment_2"


    // $ANTLR start "rule__Component__NameAssignment_1"
    // InternalMyDsl.g:5623:1: rule__Component__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Component__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5627:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5628:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5628:2: ( RULE_ID )
            // InternalMyDsl.g:5629:3: RULE_ID
            {
             before(grammarAccess.getComponentAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Component__NameAssignment_1"


    // $ANTLR start "rule__Component__UsedPropertiesAssignment_3_2"
    // InternalMyDsl.g:5638:1: rule__Component__UsedPropertiesAssignment_3_2 : ( ( RULE_ID ) ) ;
    public final void rule__Component__UsedPropertiesAssignment_3_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5642:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5643:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5643:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5644:3: ( RULE_ID )
            {
             before(grammarAccess.getComponentAccess().getUsedPropertiesPropertyCrossReference_3_2_0()); 
            // InternalMyDsl.g:5645:3: ( RULE_ID )
            // InternalMyDsl.g:5646:4: RULE_ID
            {
             before(grammarAccess.getComponentAccess().getUsedPropertiesPropertyIDTerminalRuleCall_3_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getUsedPropertiesPropertyIDTerminalRuleCall_3_2_0_1()); 

            }

             after(grammarAccess.getComponentAccess().getUsedPropertiesPropertyCrossReference_3_2_0()); 

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
    // $ANTLR end "rule__Component__UsedPropertiesAssignment_3_2"


    // $ANTLR start "rule__Component__ImplementationsAssignment_4"
    // InternalMyDsl.g:5657:1: rule__Component__ImplementationsAssignment_4 : ( ruleImplementation ) ;
    public final void rule__Component__ImplementationsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5661:1: ( ( ruleImplementation ) )
            // InternalMyDsl.g:5662:2: ( ruleImplementation )
            {
            // InternalMyDsl.g:5662:2: ( ruleImplementation )
            // InternalMyDsl.g:5663:3: ruleImplementation
            {
             before(grammarAccess.getComponentAccess().getImplementationsImplementationParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleImplementation();

            state._fsp--;

             after(grammarAccess.getComponentAccess().getImplementationsImplementationParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Component__ImplementationsAssignment_4"


    // $ANTLR start "rule__Component__UsedPropertiesAssignment_5_2"
    // InternalMyDsl.g:5672:1: rule__Component__UsedPropertiesAssignment_5_2 : ( ( RULE_ID ) ) ;
    public final void rule__Component__UsedPropertiesAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5676:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5677:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5677:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5678:3: ( RULE_ID )
            {
             before(grammarAccess.getComponentAccess().getUsedPropertiesPropertyCrossReference_5_2_0()); 
            // InternalMyDsl.g:5679:3: ( RULE_ID )
            // InternalMyDsl.g:5680:4: RULE_ID
            {
             before(grammarAccess.getComponentAccess().getUsedPropertiesPropertyIDTerminalRuleCall_5_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getComponentAccess().getUsedPropertiesPropertyIDTerminalRuleCall_5_2_0_1()); 

            }

             after(grammarAccess.getComponentAccess().getUsedPropertiesPropertyCrossReference_5_2_0()); 

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
    // $ANTLR end "rule__Component__UsedPropertiesAssignment_5_2"


    // $ANTLR start "rule__Implementation__NameAssignment_1"
    // InternalMyDsl.g:5691:1: rule__Implementation__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Implementation__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5695:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5696:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5696:2: ( RULE_ID )
            // InternalMyDsl.g:5697:3: RULE_ID
            {
             before(grammarAccess.getImplementationAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getImplementationAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Implementation__NameAssignment_1"


    // $ANTLR start "rule__Implementation__ComponentRequirementsAssignment_3"
    // InternalMyDsl.g:5706:1: rule__Implementation__ComponentRequirementsAssignment_3 : ( ruleComponentRequirement ) ;
    public final void rule__Implementation__ComponentRequirementsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5710:1: ( ( ruleComponentRequirement ) )
            // InternalMyDsl.g:5711:2: ( ruleComponentRequirement )
            {
            // InternalMyDsl.g:5711:2: ( ruleComponentRequirement )
            // InternalMyDsl.g:5712:3: ruleComponentRequirement
            {
             before(grammarAccess.getImplementationAccess().getComponentRequirementsComponentRequirementParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleComponentRequirement();

            state._fsp--;

             after(grammarAccess.getImplementationAccess().getComponentRequirementsComponentRequirementParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Implementation__ComponentRequirementsAssignment_3"


    // $ANTLR start "rule__Implementation__ResourceRequirementsAssignment_4"
    // InternalMyDsl.g:5721:1: rule__Implementation__ResourceRequirementsAssignment_4 : ( ruleResourceRequirement ) ;
    public final void rule__Implementation__ResourceRequirementsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5725:1: ( ( ruleResourceRequirement ) )
            // InternalMyDsl.g:5726:2: ( ruleResourceRequirement )
            {
            // InternalMyDsl.g:5726:2: ( ruleResourceRequirement )
            // InternalMyDsl.g:5727:3: ruleResourceRequirement
            {
             before(grammarAccess.getImplementationAccess().getResourceRequirementsResourceRequirementParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceRequirement();

            state._fsp--;

             after(grammarAccess.getImplementationAccess().getResourceRequirementsResourceRequirementParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Implementation__ResourceRequirementsAssignment_4"


    // $ANTLR start "rule__Implementation__ClausesAssignment_5"
    // InternalMyDsl.g:5736:1: rule__Implementation__ClausesAssignment_5 : ( ruleClause ) ;
    public final void rule__Implementation__ClausesAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5740:1: ( ( ruleClause ) )
            // InternalMyDsl.g:5741:2: ( ruleClause )
            {
            // InternalMyDsl.g:5741:2: ( ruleClause )
            // InternalMyDsl.g:5742:3: ruleClause
            {
             before(grammarAccess.getImplementationAccess().getClausesClauseParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleClause();

            state._fsp--;

             after(grammarAccess.getImplementationAccess().getClausesClauseParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__Implementation__ClausesAssignment_5"


    // $ANTLR start "rule__Instance__NameAssignment"
    // InternalMyDsl.g:5751:1: rule__Instance__NameAssignment : ( RULE_ID ) ;
    public final void rule__Instance__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5755:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5756:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5756:2: ( RULE_ID )
            // InternalMyDsl.g:5757:3: RULE_ID
            {
             before(grammarAccess.getInstanceAccess().getNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getInstanceAccess().getNameIDTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__Instance__NameAssignment"


    // $ANTLR start "rule__ComponentRequirement__InstanceAssignment_1"
    // InternalMyDsl.g:5766:1: rule__ComponentRequirement__InstanceAssignment_1 : ( ruleInstance ) ;
    public final void rule__ComponentRequirement__InstanceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5770:1: ( ( ruleInstance ) )
            // InternalMyDsl.g:5771:2: ( ruleInstance )
            {
            // InternalMyDsl.g:5771:2: ( ruleInstance )
            // InternalMyDsl.g:5772:3: ruleInstance
            {
             before(grammarAccess.getComponentRequirementAccess().getInstanceInstanceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleInstance();

            state._fsp--;

             after(grammarAccess.getComponentRequirementAccess().getInstanceInstanceParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ComponentRequirement__InstanceAssignment_1"


    // $ANTLR start "rule__ComponentRequirement__TypeAssignment_4"
    // InternalMyDsl.g:5781:1: rule__ComponentRequirement__TypeAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__ComponentRequirement__TypeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5785:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5786:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5786:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5787:3: ( RULE_ID )
            {
             before(grammarAccess.getComponentRequirementAccess().getTypeComponentCrossReference_4_0()); 
            // InternalMyDsl.g:5788:3: ( RULE_ID )
            // InternalMyDsl.g:5789:4: RULE_ID
            {
             before(grammarAccess.getComponentRequirementAccess().getTypeComponentIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getComponentRequirementAccess().getTypeComponentIDTerminalRuleCall_4_0_1()); 

            }

             after(grammarAccess.getComponentRequirementAccess().getTypeComponentCrossReference_4_0()); 

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
    // $ANTLR end "rule__ComponentRequirement__TypeAssignment_4"


    // $ANTLR start "rule__ResourceRequirement__InstanceAssignment_1"
    // InternalMyDsl.g:5800:1: rule__ResourceRequirement__InstanceAssignment_1 : ( ruleInstance ) ;
    public final void rule__ResourceRequirement__InstanceAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5804:1: ( ( ruleInstance ) )
            // InternalMyDsl.g:5805:2: ( ruleInstance )
            {
            // InternalMyDsl.g:5805:2: ( ruleInstance )
            // InternalMyDsl.g:5806:3: ruleInstance
            {
             before(grammarAccess.getResourceRequirementAccess().getInstanceInstanceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleInstance();

            state._fsp--;

             after(grammarAccess.getResourceRequirementAccess().getInstanceInstanceParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ResourceRequirement__InstanceAssignment_1"


    // $ANTLR start "rule__ResourceRequirement__TypeAssignment_4"
    // InternalMyDsl.g:5815:1: rule__ResourceRequirement__TypeAssignment_4 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceRequirement__TypeAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5819:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5820:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5820:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5821:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceRequirementAccess().getTypeResourceTypeCrossReference_4_0()); 
            // InternalMyDsl.g:5822:3: ( RULE_ID )
            // InternalMyDsl.g:5823:4: RULE_ID
            {
             before(grammarAccess.getResourceRequirementAccess().getTypeResourceTypeIDTerminalRuleCall_4_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceRequirementAccess().getTypeResourceTypeIDTerminalRuleCall_4_0_1()); 

            }

             after(grammarAccess.getResourceRequirementAccess().getTypeResourceTypeCrossReference_4_0()); 

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
    // $ANTLR end "rule__ResourceRequirement__TypeAssignment_4"


    // $ANTLR start "rule__ResourceRequirement__ResourceRequirementsAssignment_5_2"
    // InternalMyDsl.g:5834:1: rule__ResourceRequirement__ResourceRequirementsAssignment_5_2 : ( ruleResourceRequirement ) ;
    public final void rule__ResourceRequirement__ResourceRequirementsAssignment_5_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5838:1: ( ( ruleResourceRequirement ) )
            // InternalMyDsl.g:5839:2: ( ruleResourceRequirement )
            {
            // InternalMyDsl.g:5839:2: ( ruleResourceRequirement )
            // InternalMyDsl.g:5840:3: ruleResourceRequirement
            {
             before(grammarAccess.getResourceRequirementAccess().getResourceRequirementsResourceRequirementParserRuleCall_5_2_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceRequirement();

            state._fsp--;

             after(grammarAccess.getResourceRequirementAccess().getResourceRequirementsResourceRequirementParserRuleCall_5_2_0()); 

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
    // $ANTLR end "rule__ResourceRequirement__ResourceRequirementsAssignment_5_2"


    // $ANTLR start "rule__Clause__TypeAssignment_0"
    // InternalMyDsl.g:5849:1: rule__Clause__TypeAssignment_0 : ( ruleClauseType ) ;
    public final void rule__Clause__TypeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5853:1: ( ( ruleClauseType ) )
            // InternalMyDsl.g:5854:2: ( ruleClauseType )
            {
            // InternalMyDsl.g:5854:2: ( ruleClauseType )
            // InternalMyDsl.g:5855:3: ruleClauseType
            {
             before(grammarAccess.getClauseAccess().getTypeClauseTypeEnumRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleClauseType();

            state._fsp--;

             after(grammarAccess.getClauseAccess().getTypeClauseTypeEnumRuleCall_0_0()); 

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
    // $ANTLR end "rule__Clause__TypeAssignment_0"


    // $ANTLR start "rule__Clause__LHSAssignment_1"
    // InternalMyDsl.g:5864:1: rule__Clause__LHSAssignment_1 : ( rulePropertyDesignatorWithOptionalInstance ) ;
    public final void rule__Clause__LHSAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5868:1: ( ( rulePropertyDesignatorWithOptionalInstance ) )
            // InternalMyDsl.g:5869:2: ( rulePropertyDesignatorWithOptionalInstance )
            {
            // InternalMyDsl.g:5869:2: ( rulePropertyDesignatorWithOptionalInstance )
            // InternalMyDsl.g:5870:3: rulePropertyDesignatorWithOptionalInstance
            {
             before(grammarAccess.getClauseAccess().getLHSPropertyDesignatorWithOptionalInstanceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            rulePropertyDesignatorWithOptionalInstance();

            state._fsp--;

             after(grammarAccess.getClauseAccess().getLHSPropertyDesignatorWithOptionalInstanceParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__Clause__LHSAssignment_1"


    // $ANTLR start "rule__Clause__CompAssignment_2"
    // InternalMyDsl.g:5879:1: rule__Clause__CompAssignment_2 : ( ruleClauseComparator ) ;
    public final void rule__Clause__CompAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5883:1: ( ( ruleClauseComparator ) )
            // InternalMyDsl.g:5884:2: ( ruleClauseComparator )
            {
            // InternalMyDsl.g:5884:2: ( ruleClauseComparator )
            // InternalMyDsl.g:5885:3: ruleClauseComparator
            {
             before(grammarAccess.getClauseAccess().getCompClauseComparatorEnumRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleClauseComparator();

            state._fsp--;

             after(grammarAccess.getClauseAccess().getCompClauseComparatorEnumRuleCall_2_0()); 

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
    // $ANTLR end "rule__Clause__CompAssignment_2"


    // $ANTLR start "rule__Clause__RHSAssignment_3"
    // InternalMyDsl.g:5894:1: rule__Clause__RHSAssignment_3 : ( ruleExpression ) ;
    public final void rule__Clause__RHSAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5898:1: ( ( ruleExpression ) )
            // InternalMyDsl.g:5899:2: ( ruleExpression )
            {
            // InternalMyDsl.g:5899:2: ( ruleExpression )
            // InternalMyDsl.g:5900:3: ruleExpression
            {
             before(grammarAccess.getClauseAccess().getRHSExpressionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleExpression();

            state._fsp--;

             after(grammarAccess.getClauseAccess().getRHSExpressionParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Clause__RHSAssignment_3"


    // $ANTLR start "rule__Request__NameAssignment_1"
    // InternalMyDsl.g:5909:1: rule__Request__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__Request__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5913:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:5914:2: ( RULE_ID )
            {
            // InternalMyDsl.g:5914:2: ( RULE_ID )
            // InternalMyDsl.g:5915:3: RULE_ID
            {
             before(grammarAccess.getRequestAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRequestAccess().getNameIDTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Request__NameAssignment_1"


    // $ANTLR start "rule__Request__ComponentAssignment_3"
    // InternalMyDsl.g:5924:1: rule__Request__ComponentAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Request__ComponentAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5928:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5929:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5929:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5930:3: ( RULE_ID )
            {
             before(grammarAccess.getRequestAccess().getComponentComponentCrossReference_3_0()); 
            // InternalMyDsl.g:5931:3: ( RULE_ID )
            // InternalMyDsl.g:5932:4: RULE_ID
            {
             before(grammarAccess.getRequestAccess().getComponentComponentIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRequestAccess().getComponentComponentIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getRequestAccess().getComponentComponentCrossReference_3_0()); 

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
    // $ANTLR end "rule__Request__ComponentAssignment_3"


    // $ANTLR start "rule__Request__ParameterAssignmentsAssignment_5"
    // InternalMyDsl.g:5943:1: rule__Request__ParameterAssignmentsAssignment_5 : ( ruleMetaParameterAssignment ) ;
    public final void rule__Request__ParameterAssignmentsAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5947:1: ( ( ruleMetaParameterAssignment ) )
            // InternalMyDsl.g:5948:2: ( ruleMetaParameterAssignment )
            {
            // InternalMyDsl.g:5948:2: ( ruleMetaParameterAssignment )
            // InternalMyDsl.g:5949:3: ruleMetaParameterAssignment
            {
             before(grammarAccess.getRequestAccess().getParameterAssignmentsMetaParameterAssignmentParserRuleCall_5_0()); 
            pushFollow(FOLLOW_2);
            ruleMetaParameterAssignment();

            state._fsp--;

             after(grammarAccess.getRequestAccess().getParameterAssignmentsMetaParameterAssignmentParserRuleCall_5_0()); 

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
    // $ANTLR end "rule__Request__ParameterAssignmentsAssignment_5"


    // $ANTLR start "rule__Request__RequirementsAssignment_6"
    // InternalMyDsl.g:5958:1: rule__Request__RequirementsAssignment_6 : ( ruleClause ) ;
    public final void rule__Request__RequirementsAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5962:1: ( ( ruleClause ) )
            // InternalMyDsl.g:5963:2: ( ruleClause )
            {
            // InternalMyDsl.g:5963:2: ( ruleClause )
            // InternalMyDsl.g:5964:3: ruleClause
            {
             before(grammarAccess.getRequestAccess().getRequirementsClauseParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleClause();

            state._fsp--;

             after(grammarAccess.getRequestAccess().getRequirementsClauseParserRuleCall_6_0()); 

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
    // $ANTLR end "rule__Request__RequirementsAssignment_6"


    // $ANTLR start "rule__Objective__AggAssignment_1"
    // InternalMyDsl.g:5973:1: rule__Objective__AggAssignment_1 : ( rulePropertyAggregation ) ;
    public final void rule__Objective__AggAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5977:1: ( ( rulePropertyAggregation ) )
            // InternalMyDsl.g:5978:2: ( rulePropertyAggregation )
            {
            // InternalMyDsl.g:5978:2: ( rulePropertyAggregation )
            // InternalMyDsl.g:5979:3: rulePropertyAggregation
            {
             before(grammarAccess.getObjectiveAccess().getAggPropertyAggregationEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            rulePropertyAggregation();

            state._fsp--;

             after(grammarAccess.getObjectiveAccess().getAggPropertyAggregationEnumRuleCall_1_0()); 

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
    // $ANTLR end "rule__Objective__AggAssignment_1"


    // $ANTLR start "rule__Objective__PropertyAssignment_3"
    // InternalMyDsl.g:5988:1: rule__Objective__PropertyAssignment_3 : ( ( RULE_ID ) ) ;
    public final void rule__Objective__PropertyAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:5992:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:5993:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:5993:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:5994:3: ( RULE_ID )
            {
             before(grammarAccess.getObjectiveAccess().getPropertyPropertyCrossReference_3_0()); 
            // InternalMyDsl.g:5995:3: ( RULE_ID )
            // InternalMyDsl.g:5996:4: RULE_ID
            {
             before(grammarAccess.getObjectiveAccess().getPropertyPropertyIDTerminalRuleCall_3_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getObjectiveAccess().getPropertyPropertyIDTerminalRuleCall_3_0_1()); 

            }

             after(grammarAccess.getObjectiveAccess().getPropertyPropertyCrossReference_3_0()); 

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
    // $ANTLR end "rule__Objective__PropertyAssignment_3"


    // $ANTLR start "rule__Addition__RightAssignment_1_1"
    // InternalMyDsl.g:6007:1: rule__Addition__RightAssignment_1_1 : ( ruleAddition ) ;
    public final void rule__Addition__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6011:1: ( ( ruleAddition ) )
            // InternalMyDsl.g:6012:2: ( ruleAddition )
            {
            // InternalMyDsl.g:6012:2: ( ruleAddition )
            // InternalMyDsl.g:6013:3: ruleAddition
            {
             before(grammarAccess.getAdditionAccess().getRightAdditionParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleAddition();

            state._fsp--;

             after(grammarAccess.getAdditionAccess().getRightAdditionParserRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__Addition__RightAssignment_1_1"


    // $ANTLR start "rule__Multiplication__RightAssignment_1_1"
    // InternalMyDsl.g:6022:1: rule__Multiplication__RightAssignment_1_1 : ( ruleMultiplication ) ;
    public final void rule__Multiplication__RightAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6026:1: ( ( ruleMultiplication ) )
            // InternalMyDsl.g:6027:2: ( ruleMultiplication )
            {
            // InternalMyDsl.g:6027:2: ( ruleMultiplication )
            // InternalMyDsl.g:6028:3: ruleMultiplication
            {
             before(grammarAccess.getMultiplicationAccess().getRightMultiplicationParserRuleCall_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleMultiplication();

            state._fsp--;

             after(grammarAccess.getMultiplicationAccess().getRightMultiplicationParserRuleCall_1_1_0()); 

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
    // $ANTLR end "rule__Multiplication__RightAssignment_1_1"


    // $ANTLR start "rule__Power__RightAssignment_1_2"
    // InternalMyDsl.g:6037:1: rule__Power__RightAssignment_1_2 : ( rulePrimaryExpression ) ;
    public final void rule__Power__RightAssignment_1_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6041:1: ( ( rulePrimaryExpression ) )
            // InternalMyDsl.g:6042:2: ( rulePrimaryExpression )
            {
            // InternalMyDsl.g:6042:2: ( rulePrimaryExpression )
            // InternalMyDsl.g:6043:3: rulePrimaryExpression
            {
             before(grammarAccess.getPowerAccess().getRightPrimaryExpressionParserRuleCall_1_2_0()); 
            pushFollow(FOLLOW_2);
            rulePrimaryExpression();

            state._fsp--;

             after(grammarAccess.getPowerAccess().getRightPrimaryExpressionParserRuleCall_1_2_0()); 

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
    // $ANTLR end "rule__Power__RightAssignment_1_2"


    // $ANTLR start "rule__PropertyDesignator__InstanceAssignment_0"
    // InternalMyDsl.g:6052:1: rule__PropertyDesignator__InstanceAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__PropertyDesignator__InstanceAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6056:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6057:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6057:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6058:3: ( RULE_ID )
            {
             before(grammarAccess.getPropertyDesignatorAccess().getInstanceInstanceCrossReference_0_0()); 
            // InternalMyDsl.g:6059:3: ( RULE_ID )
            // InternalMyDsl.g:6060:4: RULE_ID
            {
             before(grammarAccess.getPropertyDesignatorAccess().getInstanceInstanceIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyDesignatorAccess().getInstanceInstanceIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getPropertyDesignatorAccess().getInstanceInstanceCrossReference_0_0()); 

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
    // $ANTLR end "rule__PropertyDesignator__InstanceAssignment_0"


    // $ANTLR start "rule__PropertyDesignator__PropertyAssignment_2"
    // InternalMyDsl.g:6071:1: rule__PropertyDesignator__PropertyAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__PropertyDesignator__PropertyAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6075:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6076:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6076:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6077:3: ( RULE_ID )
            {
             before(grammarAccess.getPropertyDesignatorAccess().getPropertyPropertyCrossReference_2_0()); 
            // InternalMyDsl.g:6078:3: ( RULE_ID )
            // InternalMyDsl.g:6079:4: RULE_ID
            {
             before(grammarAccess.getPropertyDesignatorAccess().getPropertyPropertyIDTerminalRuleCall_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyDesignatorAccess().getPropertyPropertyIDTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getPropertyDesignatorAccess().getPropertyPropertyCrossReference_2_0()); 

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
    // $ANTLR end "rule__PropertyDesignator__PropertyAssignment_2"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0"
    // InternalMyDsl.g:6090:1: rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6094:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6095:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6095:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6096:3: ( RULE_ID )
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceInstanceCrossReference_0_0_0()); 
            // InternalMyDsl.g:6097:3: ( RULE_ID )
            // InternalMyDsl.g:6098:4: RULE_ID
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceInstanceIDTerminalRuleCall_0_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceInstanceIDTerminalRuleCall_0_0_0_1()); 

            }

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getInstanceInstanceCrossReference_0_0_0()); 

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__InstanceAssignment_0_0"


    // $ANTLR start "rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1"
    // InternalMyDsl.g:6109:1: rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6113:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6114:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6114:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6115:3: ( RULE_ID )
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyPropertyCrossReference_1_0()); 
            // InternalMyDsl.g:6116:3: ( RULE_ID )
            // InternalMyDsl.g:6117:4: RULE_ID
            {
             before(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyPropertyIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyPropertyIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getPropertyDesignatorWithOptionalInstanceAccess().getPropertyPropertyCrossReference_1_0()); 

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
    // $ANTLR end "rule__PropertyDesignatorWithOptionalInstance__PropertyAssignment_1"


    // $ANTLR start "rule__MetaParameterDesignator__MetaParameterAssignment"
    // InternalMyDsl.g:6128:1: rule__MetaParameterDesignator__MetaParameterAssignment : ( ( RULE_ID ) ) ;
    public final void rule__MetaParameterDesignator__MetaParameterAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6132:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6133:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6133:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6134:3: ( RULE_ID )
            {
             before(grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterMetaParameterCrossReference_0()); 
            // InternalMyDsl.g:6135:3: ( RULE_ID )
            // InternalMyDsl.g:6136:4: RULE_ID
            {
             before(grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterMetaParameterIDTerminalRuleCall_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterMetaParameterIDTerminalRuleCall_0_1()); 

            }

             after(grammarAccess.getMetaParameterDesignatorAccess().getMetaParameterMetaParameterCrossReference_0()); 

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
    // $ANTLR end "rule__MetaParameterDesignator__MetaParameterAssignment"


    // $ANTLR start "rule__LiteralExpression__ValueAssignment"
    // InternalMyDsl.g:6147:1: rule__LiteralExpression__ValueAssignment : ( ruleDouble ) ;
    public final void rule__LiteralExpression__ValueAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6151:1: ( ( ruleDouble ) )
            // InternalMyDsl.g:6152:2: ( ruleDouble )
            {
            // InternalMyDsl.g:6152:2: ( ruleDouble )
            // InternalMyDsl.g:6153:3: ruleDouble
            {
             before(grammarAccess.getLiteralExpressionAccess().getValueDoubleParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleDouble();

            state._fsp--;

             after(grammarAccess.getLiteralExpressionAccess().getValueDoubleParserRuleCall_0()); 

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
    // $ANTLR end "rule__LiteralExpression__ValueAssignment"


    // $ANTLR start "rule__MetaParameterAssignment__ParameterAssignment_1"
    // InternalMyDsl.g:6162:1: rule__MetaParameterAssignment__ParameterAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__MetaParameterAssignment__ParameterAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6166:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6167:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6167:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6168:3: ( RULE_ID )
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getParameterMetaParameterCrossReference_1_0()); 
            // InternalMyDsl.g:6169:3: ( RULE_ID )
            // InternalMyDsl.g:6170:4: RULE_ID
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getParameterMetaParameterIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getMetaParameterAssignmentAccess().getParameterMetaParameterIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getMetaParameterAssignmentAccess().getParameterMetaParameterCrossReference_1_0()); 

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
    // $ANTLR end "rule__MetaParameterAssignment__ParameterAssignment_1"


    // $ANTLR start "rule__MetaParameterAssignment__ValueAssignment_3"
    // InternalMyDsl.g:6181:1: rule__MetaParameterAssignment__ValueAssignment_3 : ( ruleLiteralExpression ) ;
    public final void rule__MetaParameterAssignment__ValueAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6185:1: ( ( ruleLiteralExpression ) )
            // InternalMyDsl.g:6186:2: ( ruleLiteralExpression )
            {
            // InternalMyDsl.g:6186:2: ( ruleLiteralExpression )
            // InternalMyDsl.g:6187:3: ruleLiteralExpression
            {
             before(grammarAccess.getMetaParameterAssignmentAccess().getValueLiteralExpressionParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleLiteralExpression();

            state._fsp--;

             after(grammarAccess.getMetaParameterAssignmentAccess().getValueLiteralExpressionParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__MetaParameterAssignment__ValueAssignment_3"


    // $ANTLR start "rule__Solution__AssignmentsAssignment_2"
    // InternalMyDsl.g:6196:1: rule__Solution__AssignmentsAssignment_2 : ( ruleAssignment ) ;
    public final void rule__Solution__AssignmentsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6200:1: ( ( ruleAssignment ) )
            // InternalMyDsl.g:6201:2: ( ruleAssignment )
            {
            // InternalMyDsl.g:6201:2: ( ruleAssignment )
            // InternalMyDsl.g:6202:3: ruleAssignment
            {
             before(grammarAccess.getSolutionAccess().getAssignmentsAssignmentParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getSolutionAccess().getAssignmentsAssignmentParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Solution__AssignmentsAssignment_2"


    // $ANTLR start "rule__Assignment__RequestAssignment_0_0"
    // InternalMyDsl.g:6211:1: rule__Assignment__RequestAssignment_0_0 : ( ( RULE_ID ) ) ;
    public final void rule__Assignment__RequestAssignment_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6215:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6216:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6216:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6217:3: ( RULE_ID )
            {
             before(grammarAccess.getAssignmentAccess().getRequestRequestCrossReference_0_0_0()); 
            // InternalMyDsl.g:6218:3: ( RULE_ID )
            // InternalMyDsl.g:6219:4: RULE_ID
            {
             before(grammarAccess.getAssignmentAccess().getRequestRequestIDTerminalRuleCall_0_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getRequestRequestIDTerminalRuleCall_0_0_0_1()); 

            }

             after(grammarAccess.getAssignmentAccess().getRequestRequestCrossReference_0_0_0()); 

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
    // $ANTLR end "rule__Assignment__RequestAssignment_0_0"


    // $ANTLR start "rule__Assignment__ImplementationAssignment_1"
    // InternalMyDsl.g:6230:1: rule__Assignment__ImplementationAssignment_1 : ( ( RULE_ID ) ) ;
    public final void rule__Assignment__ImplementationAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6234:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6235:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6235:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6236:3: ( RULE_ID )
            {
             before(grammarAccess.getAssignmentAccess().getImplementationImplementationCrossReference_1_0()); 
            // InternalMyDsl.g:6237:3: ( RULE_ID )
            // InternalMyDsl.g:6238:4: RULE_ID
            {
             before(grammarAccess.getAssignmentAccess().getImplementationImplementationIDTerminalRuleCall_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getImplementationImplementationIDTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getAssignmentAccess().getImplementationImplementationCrossReference_1_0()); 

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
    // $ANTLR end "rule__Assignment__ImplementationAssignment_1"


    // $ANTLR start "rule__Assignment__ResourceMappingsAssignment_3"
    // InternalMyDsl.g:6249:1: rule__Assignment__ResourceMappingsAssignment_3 : ( ruleResourceMapping ) ;
    public final void rule__Assignment__ResourceMappingsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6253:1: ( ( ruleResourceMapping ) )
            // InternalMyDsl.g:6254:2: ( ruleResourceMapping )
            {
            // InternalMyDsl.g:6254:2: ( ruleResourceMapping )
            // InternalMyDsl.g:6255:3: ruleResourceMapping
            {
             before(grammarAccess.getAssignmentAccess().getResourceMappingsResourceMappingParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceMapping();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getResourceMappingsResourceMappingParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__Assignment__ResourceMappingsAssignment_3"


    // $ANTLR start "rule__Assignment__ComponentMappingsAssignment_4"
    // InternalMyDsl.g:6264:1: rule__Assignment__ComponentMappingsAssignment_4 : ( ruleComponentMapping ) ;
    public final void rule__Assignment__ComponentMappingsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6268:1: ( ( ruleComponentMapping ) )
            // InternalMyDsl.g:6269:2: ( ruleComponentMapping )
            {
            // InternalMyDsl.g:6269:2: ( ruleComponentMapping )
            // InternalMyDsl.g:6270:3: ruleComponentMapping
            {
             before(grammarAccess.getAssignmentAccess().getComponentMappingsComponentMappingParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            ruleComponentMapping();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getComponentMappingsComponentMappingParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Assignment__ComponentMappingsAssignment_4"


    // $ANTLR start "rule__ResourceMapping__RequiredResourceAssignment_0"
    // InternalMyDsl.g:6279:1: rule__ResourceMapping__RequiredResourceAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceMapping__RequiredResourceAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6283:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6284:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6284:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6285:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceMappingAccess().getRequiredResourceInstanceCrossReference_0_0()); 
            // InternalMyDsl.g:6286:3: ( RULE_ID )
            // InternalMyDsl.g:6287:4: RULE_ID
            {
             before(grammarAccess.getResourceMappingAccess().getRequiredResourceInstanceIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceMappingAccess().getRequiredResourceInstanceIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getResourceMappingAccess().getRequiredResourceInstanceCrossReference_0_0()); 

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
    // $ANTLR end "rule__ResourceMapping__RequiredResourceAssignment_0"


    // $ANTLR start "rule__ResourceMapping__UsedResourceAssignment_2"
    // InternalMyDsl.g:6298:1: rule__ResourceMapping__UsedResourceAssignment_2 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceMapping__UsedResourceAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6302:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6303:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6303:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6304:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceMappingAccess().getUsedResourceResourceCrossReference_2_0()); 
            // InternalMyDsl.g:6305:3: ( RULE_ID )
            // InternalMyDsl.g:6306:4: RULE_ID
            {
             before(grammarAccess.getResourceMappingAccess().getUsedResourceResourceIDTerminalRuleCall_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceMappingAccess().getUsedResourceResourceIDTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getResourceMappingAccess().getUsedResourceResourceCrossReference_2_0()); 

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
    // $ANTLR end "rule__ResourceMapping__UsedResourceAssignment_2"


    // $ANTLR start "rule__ResourceMapping__SubMappingsAssignment_3_1"
    // InternalMyDsl.g:6317:1: rule__ResourceMapping__SubMappingsAssignment_3_1 : ( ruleResourceMapping ) ;
    public final void rule__ResourceMapping__SubMappingsAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6321:1: ( ( ruleResourceMapping ) )
            // InternalMyDsl.g:6322:2: ( ruleResourceMapping )
            {
            // InternalMyDsl.g:6322:2: ( ruleResourceMapping )
            // InternalMyDsl.g:6323:3: ruleResourceMapping
            {
             before(grammarAccess.getResourceMappingAccess().getSubMappingsResourceMappingParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceMapping();

            state._fsp--;

             after(grammarAccess.getResourceMappingAccess().getSubMappingsResourceMappingParserRuleCall_3_1_0()); 

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
    // $ANTLR end "rule__ResourceMapping__SubMappingsAssignment_3_1"


    // $ANTLR start "rule__ComponentMapping__ComponentAssignment_0"
    // InternalMyDsl.g:6332:1: rule__ComponentMapping__ComponentAssignment_0 : ( ( RULE_ID ) ) ;
    public final void rule__ComponentMapping__ComponentAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6336:1: ( ( ( RULE_ID ) ) )
            // InternalMyDsl.g:6337:2: ( ( RULE_ID ) )
            {
            // InternalMyDsl.g:6337:2: ( ( RULE_ID ) )
            // InternalMyDsl.g:6338:3: ( RULE_ID )
            {
             before(grammarAccess.getComponentMappingAccess().getComponentInstanceCrossReference_0_0()); 
            // InternalMyDsl.g:6339:3: ( RULE_ID )
            // InternalMyDsl.g:6340:4: RULE_ID
            {
             before(grammarAccess.getComponentMappingAccess().getComponentInstanceIDTerminalRuleCall_0_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getComponentMappingAccess().getComponentInstanceIDTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getComponentMappingAccess().getComponentInstanceCrossReference_0_0()); 

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
    // $ANTLR end "rule__ComponentMapping__ComponentAssignment_0"


    // $ANTLR start "rule__ComponentMapping__AssignmentAssignment_2"
    // InternalMyDsl.g:6351:1: rule__ComponentMapping__AssignmentAssignment_2 : ( ruleAssignment ) ;
    public final void rule__ComponentMapping__AssignmentAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:6355:1: ( ( ruleAssignment ) )
            // InternalMyDsl.g:6356:2: ( ruleAssignment )
            {
            // InternalMyDsl.g:6356:2: ( ruleAssignment )
            // InternalMyDsl.g:6357:3: ruleAssignment
            {
             before(grammarAccess.getComponentMappingAccess().getAssignmentAssignmentParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getComponentMappingAccess().getAssignmentAssignmentParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__ComponentMapping__AssignmentAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000068000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000001000000002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0004000000400000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0004000000400002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x000400000E400000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000002400020L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000000028000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000000068000002L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_27 = new BitSet(new long[]{0x0000000084000000L});
    public static final BitSet FOLLOW_28 = new BitSet(new long[]{0x0000000006000000L});
    public static final BitSet FOLLOW_29 = new BitSet(new long[]{0x0000000084000002L});
    public static final BitSet FOLLOW_30 = new BitSet(new long[]{0x0000000500000020L});
    public static final BitSet FOLLOW_31 = new BitSet(new long[]{0x0000000100000002L});
    public static final BitSet FOLLOW_32 = new BitSet(new long[]{0x0000000000003000L});
    public static final BitSet FOLLOW_33 = new BitSet(new long[]{0x0000000500000022L});
    public static final BitSet FOLLOW_34 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_35 = new BitSet(new long[]{0x0000000000003002L});
    public static final BitSet FOLLOW_36 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_37 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_38 = new BitSet(new long[]{0x00000000000FC000L});
    public static final BitSet FOLLOW_39 = new BitSet(new long[]{0x0000008000000030L});
    public static final BitSet FOLLOW_40 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_41 = new BitSet(new long[]{0x0000000020003000L});
    public static final BitSet FOLLOW_42 = new BitSet(new long[]{0x0000000020000002L});
    public static final BitSet FOLLOW_43 = new BitSet(new long[]{0x0000000020003002L});
    public static final BitSet FOLLOW_44 = new BitSet(new long[]{0x0000000000300000L});
    public static final BitSet FOLLOW_45 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_46 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_47 = new BitSet(new long[]{0x0000060000000000L});
    public static final BitSet FOLLOW_48 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_49 = new BitSet(new long[]{0x0000180000000000L});
    public static final BitSet FOLLOW_50 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_51 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_52 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_53 = new BitSet(new long[]{0x0000000002000020L});
    public static final BitSet FOLLOW_54 = new BitSet(new long[]{0x0001000000000000L});
    public static final BitSet FOLLOW_55 = new BitSet(new long[]{0x0002000000000000L});

}