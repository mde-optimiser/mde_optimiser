package uk.ac.kcl.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMDEOptimiseLexer extends Lexer {
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

    public InternalMDEOptimiseLexer() {;} 
    public InternalMDEOptimiseLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalMDEOptimiseLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalMDEOptimise.g"; }

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:11:7: ( 'mutate' )
            // InternalMDEOptimise.g:11:9: 'mutate'
            {
            match("mutate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:12:7: ( 'breed' )
            // InternalMDEOptimise.g:12:9: 'breed'
            {
            match("breed"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:13:7: ( 'basepath' )
            // InternalMDEOptimise.g:13:9: 'basepath'
            {
            match("basepath"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:14:7: ( 'metamodel' )
            // InternalMDEOptimise.g:14:9: 'metamodel'
            {
            match("metamodel"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:15:7: ( 'model' )
            // InternalMDEOptimise.g:15:9: 'model'
            {
            match("model"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:16:7: ( 'objective' )
            // InternalMDEOptimise.g:16:9: 'objective'
            {
            match("objective"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:17:7: ( '{' )
            // InternalMDEOptimise.g:17:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:18:7: ( '}' )
            // InternalMDEOptimise.g:18:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:19:7: ( 'constraint' )
            // InternalMDEOptimise.g:19:9: 'constraint'
            {
            match("constraint"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:20:7: ( 'using' )
            // InternalMDEOptimise.g:20:9: 'using'
            {
            match("using"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:21:7: ( 'unit' )
            // InternalMDEOptimise.g:21:9: 'unit'
            {
            match("unit"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:22:7: ( 'optimisation' )
            // InternalMDEOptimise.g:22:9: 'optimisation'
            {
            match("optimisation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:23:7: ( 'provider' )
            // InternalMDEOptimise.g:23:9: 'provider'
            {
            match("provider"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:24:7: ( 'algorithm' )
            // InternalMDEOptimise.g:24:9: 'algorithm'
            {
            match("algorithm"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:25:7: ( 'variation' )
            // InternalMDEOptimise.g:25:9: 'variation'
            {
            match("variation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:26:7: ( 'evolutions' )
            // InternalMDEOptimise.g:26:9: 'evolutions'
            {
            match("evolutions"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:27:7: ( 'population' )
            // InternalMDEOptimise.g:27:9: 'population'
            {
            match("population"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "RULE_ALGORITHM_VARIATION"
    public final void mRULE_ALGORITHM_VARIATION() throws RecognitionException {
        try {
            int _type = RULE_ALGORITHM_VARIATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1812:26: ( ( 'genetic' | 'crossover' | 'mutation' ) )
            // InternalMDEOptimise.g:1812:28: ( 'genetic' | 'crossover' | 'mutation' )
            {
            // InternalMDEOptimise.g:1812:28: ( 'genetic' | 'crossover' | 'mutation' )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 'g':
                {
                alt1=1;
                }
                break;
            case 'c':
                {
                alt1=2;
                }
                break;
            case 'm':
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // InternalMDEOptimise.g:1812:29: 'genetic'
                    {
                    match("genetic"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1812:39: 'crossover'
                    {
                    match("crossover"); 


                    }
                    break;
                case 3 :
                    // InternalMDEOptimise.g:1812:51: 'mutation'
                    {
                    match("mutation"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ALGORITHM_VARIATION"

    // $ANTLR start "RULE_ALGORITHM_FACTORY"
    public final void mRULE_ALGORITHM_FACTORY() throws RecognitionException {
        try {
            int _type = RULE_ALGORITHM_FACTORY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1814:24: ( ( 'moea' | 'ecj' ) )
            // InternalMDEOptimise.g:1814:26: ( 'moea' | 'ecj' )
            {
            // InternalMDEOptimise.g:1814:26: ( 'moea' | 'ecj' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='m') ) {
                alt2=1;
            }
            else if ( (LA2_0=='e') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalMDEOptimise.g:1814:27: 'moea'
                    {
                    match("moea"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1814:34: 'ecj'
                    {
                    match("ecj"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ALGORITHM_FACTORY"

    // $ANTLR start "RULE_ALGORITHM_NAME"
    public final void mRULE_ALGORITHM_NAME() throws RecognitionException {
        try {
            int _type = RULE_ALGORITHM_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1816:21: ( ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' ) )
            // InternalMDEOptimise.g:1816:23: ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' )
            {
            // InternalMDEOptimise.g:1816:23: ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' )
            int alt3=5;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // InternalMDEOptimise.g:1816:24: 'eMOEA'
                    {
                    match("eMOEA"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1816:32: 'eNSGAII'
                    {
                    match("eNSGAII"); 


                    }
                    break;
                case 3 :
                    // InternalMDEOptimise.g:1816:42: 'NSGAII'
                    {
                    match("NSGAII"); 


                    }
                    break;
                case 4 :
                    // InternalMDEOptimise.g:1816:51: 'NSGAIII'
                    {
                    match("NSGAIII"); 


                    }
                    break;
                case 5 :
                    // InternalMDEOptimise.g:1816:61: 'SPEA2'
                    {
                    match("SPEA2"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ALGORITHM_NAME"

    // $ANTLR start "RULE_URL"
    public final void mRULE_URL() throws RecognitionException {
        try {
            int _type = RULE_URL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1818:10: ( '<' ( options {greedy=false; } : . )* '>' )
            // InternalMDEOptimise.g:1818:12: '<' ( options {greedy=false; } : . )* '>'
            {
            match('<'); 
            // InternalMDEOptimise.g:1818:16: ( options {greedy=false; } : . )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='>') ) {
                    alt4=2;
                }
                else if ( ((LA4_0>='\u0000' && LA4_0<='=')||(LA4_0>='?' && LA4_0<='\uFFFF')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalMDEOptimise.g:1818:44: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_URL"

    // $ANTLR start "RULE_OBJECTIVE_TENDENCY"
    public final void mRULE_OBJECTIVE_TENDENCY() throws RecognitionException {
        try {
            int _type = RULE_OBJECTIVE_TENDENCY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1820:25: ( ( 'minimise' | 'maximise' ) )
            // InternalMDEOptimise.g:1820:27: ( 'minimise' | 'maximise' )
            {
            // InternalMDEOptimise.g:1820:27: ( 'minimise' | 'maximise' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='m') ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1=='i') ) {
                    alt5=1;
                }
                else if ( (LA5_1=='a') ) {
                    alt5=2;
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
                    // InternalMDEOptimise.g:1820:28: 'minimise'
                    {
                    match("minimise"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1820:39: 'maximise'
                    {
                    match("maximise"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OBJECTIVE_TENDENCY"

    // $ANTLR start "RULE_ENTITY_TYPE"
    public final void mRULE_ENTITY_TYPE() throws RecognitionException {
        try {
            int _type = RULE_ENTITY_TYPE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1822:18: ( ( 'java' | 'ocl' ) )
            // InternalMDEOptimise.g:1822:20: ( 'java' | 'ocl' )
            {
            // InternalMDEOptimise.g:1822:20: ( 'java' | 'ocl' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='j') ) {
                alt6=1;
            }
            else if ( (LA6_0=='o') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalMDEOptimise.g:1822:21: 'java'
                    {
                    match("java"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1822:28: 'ocl'
                    {
                    match("ocl"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ENTITY_TYPE"

    // $ANTLR start "RULE_ENTITY_NAME"
    public final void mRULE_ENTITY_NAME() throws RecognitionException {
        try {
            int _type = RULE_ENTITY_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1824:18: ( ( 'a' .. 'z' | 'A' .. 'Z' )+ )
            // InternalMDEOptimise.g:1824:20: ( 'a' .. 'z' | 'A' .. 'Z' )+
            {
            // InternalMDEOptimise.g:1824:20: ( 'a' .. 'z' | 'A' .. 'Z' )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='A' && LA7_0<='Z')||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalMDEOptimise.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ENTITY_NAME"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1826:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalMDEOptimise.g:1826:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalMDEOptimise.g:1826:11: ( '^' )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='^') ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalMDEOptimise.g:1826:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalMDEOptimise.g:1826:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='0' && LA9_0<='9')||(LA9_0>='A' && LA9_0<='Z')||LA9_0=='_'||(LA9_0>='a' && LA9_0<='z')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalMDEOptimise.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1828:10: ( ( '0' .. '9' )+ )
            // InternalMDEOptimise.g:1828:12: ( '0' .. '9' )+
            {
            // InternalMDEOptimise.g:1828:12: ( '0' .. '9' )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalMDEOptimise.g:1828:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1830:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalMDEOptimise.g:1830:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalMDEOptimise.g:1830:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='\"') ) {
                alt13=1;
            }
            else if ( (LA13_0=='\'') ) {
                alt13=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }
            switch (alt13) {
                case 1 :
                    // InternalMDEOptimise.g:1830:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalMDEOptimise.g:1830:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop11:
                    do {
                        int alt11=3;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0=='\\') ) {
                            alt11=1;
                        }
                        else if ( ((LA11_0>='\u0000' && LA11_0<='!')||(LA11_0>='#' && LA11_0<='[')||(LA11_0>=']' && LA11_0<='\uFFFF')) ) {
                            alt11=2;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // InternalMDEOptimise.g:1830:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalMDEOptimise.g:1830:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop11;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1830:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalMDEOptimise.g:1830:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop12:
                    do {
                        int alt12=3;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0=='\\') ) {
                            alt12=1;
                        }
                        else if ( ((LA12_0>='\u0000' && LA12_0<='&')||(LA12_0>='(' && LA12_0<='[')||(LA12_0>=']' && LA12_0<='\uFFFF')) ) {
                            alt12=2;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // InternalMDEOptimise.g:1830:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalMDEOptimise.g:1830:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1832:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalMDEOptimise.g:1832:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalMDEOptimise.g:1832:24: ( options {greedy=false; } : . )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0=='*') ) {
                    int LA14_1 = input.LA(2);

                    if ( (LA14_1=='/') ) {
                        alt14=2;
                    }
                    else if ( ((LA14_1>='\u0000' && LA14_1<='.')||(LA14_1>='0' && LA14_1<='\uFFFF')) ) {
                        alt14=1;
                    }


                }
                else if ( ((LA14_0>='\u0000' && LA14_0<=')')||(LA14_0>='+' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalMDEOptimise.g:1832:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1834:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalMDEOptimise.g:1834:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalMDEOptimise.g:1834:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalMDEOptimise.g:1834:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // InternalMDEOptimise.g:1834:40: ( ( '\\r' )? '\\n' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0=='\n'||LA17_0=='\r') ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalMDEOptimise.g:1834:41: ( '\\r' )? '\\n'
                    {
                    // InternalMDEOptimise.g:1834:41: ( '\\r' )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( (LA16_0=='\r') ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // InternalMDEOptimise.g:1834:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1836:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalMDEOptimise.g:1836:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalMDEOptimise.g:1836:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>='\t' && LA18_0<='\n')||LA18_0=='\r'||LA18_0==' ') ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalMDEOptimise.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1838:16: ( . )
            // InternalMDEOptimise.g:1838:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalMDEOptimise.g:1:8: ( T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | RULE_ALGORITHM_VARIATION | RULE_ALGORITHM_FACTORY | RULE_ALGORITHM_NAME | RULE_URL | RULE_OBJECTIVE_TENDENCY | RULE_ENTITY_TYPE | RULE_ENTITY_NAME | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt19=31;
        alt19 = dfa19.predict(input);
        switch (alt19) {
            case 1 :
                // InternalMDEOptimise.g:1:10: T__18
                {
                mT__18(); 

                }
                break;
            case 2 :
                // InternalMDEOptimise.g:1:16: T__19
                {
                mT__19(); 

                }
                break;
            case 3 :
                // InternalMDEOptimise.g:1:22: T__20
                {
                mT__20(); 

                }
                break;
            case 4 :
                // InternalMDEOptimise.g:1:28: T__21
                {
                mT__21(); 

                }
                break;
            case 5 :
                // InternalMDEOptimise.g:1:34: T__22
                {
                mT__22(); 

                }
                break;
            case 6 :
                // InternalMDEOptimise.g:1:40: T__23
                {
                mT__23(); 

                }
                break;
            case 7 :
                // InternalMDEOptimise.g:1:46: T__24
                {
                mT__24(); 

                }
                break;
            case 8 :
                // InternalMDEOptimise.g:1:52: T__25
                {
                mT__25(); 

                }
                break;
            case 9 :
                // InternalMDEOptimise.g:1:58: T__26
                {
                mT__26(); 

                }
                break;
            case 10 :
                // InternalMDEOptimise.g:1:64: T__27
                {
                mT__27(); 

                }
                break;
            case 11 :
                // InternalMDEOptimise.g:1:70: T__28
                {
                mT__28(); 

                }
                break;
            case 12 :
                // InternalMDEOptimise.g:1:76: T__29
                {
                mT__29(); 

                }
                break;
            case 13 :
                // InternalMDEOptimise.g:1:82: T__30
                {
                mT__30(); 

                }
                break;
            case 14 :
                // InternalMDEOptimise.g:1:88: T__31
                {
                mT__31(); 

                }
                break;
            case 15 :
                // InternalMDEOptimise.g:1:94: T__32
                {
                mT__32(); 

                }
                break;
            case 16 :
                // InternalMDEOptimise.g:1:100: T__33
                {
                mT__33(); 

                }
                break;
            case 17 :
                // InternalMDEOptimise.g:1:106: T__34
                {
                mT__34(); 

                }
                break;
            case 18 :
                // InternalMDEOptimise.g:1:112: RULE_ALGORITHM_VARIATION
                {
                mRULE_ALGORITHM_VARIATION(); 

                }
                break;
            case 19 :
                // InternalMDEOptimise.g:1:137: RULE_ALGORITHM_FACTORY
                {
                mRULE_ALGORITHM_FACTORY(); 

                }
                break;
            case 20 :
                // InternalMDEOptimise.g:1:160: RULE_ALGORITHM_NAME
                {
                mRULE_ALGORITHM_NAME(); 

                }
                break;
            case 21 :
                // InternalMDEOptimise.g:1:180: RULE_URL
                {
                mRULE_URL(); 

                }
                break;
            case 22 :
                // InternalMDEOptimise.g:1:189: RULE_OBJECTIVE_TENDENCY
                {
                mRULE_OBJECTIVE_TENDENCY(); 

                }
                break;
            case 23 :
                // InternalMDEOptimise.g:1:213: RULE_ENTITY_TYPE
                {
                mRULE_ENTITY_TYPE(); 

                }
                break;
            case 24 :
                // InternalMDEOptimise.g:1:230: RULE_ENTITY_NAME
                {
                mRULE_ENTITY_NAME(); 

                }
                break;
            case 25 :
                // InternalMDEOptimise.g:1:247: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 26 :
                // InternalMDEOptimise.g:1:255: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 27 :
                // InternalMDEOptimise.g:1:264: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 28 :
                // InternalMDEOptimise.g:1:276: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 29 :
                // InternalMDEOptimise.g:1:292: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 30 :
                // InternalMDEOptimise.g:1:308: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 31 :
                // InternalMDEOptimise.g:1:316: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA19 dfa19 = new DFA19(this);
    static final String DFA3_eotS =
        "\12\uffff\1\14\2\uffff";
    static final String DFA3_eofS =
        "\15\uffff";
    static final String DFA3_minS =
        "\1\116\1\115\1\123\3\uffff\1\107\1\101\3\111\2\uffff";
    static final String DFA3_maxS =
        "\1\145\1\116\1\123\3\uffff\1\107\1\101\3\111\2\uffff";
    static final String DFA3_acceptS =
        "\3\uffff\1\5\1\1\1\2\5\uffff\1\4\1\3";
    static final String DFA3_specialS =
        "\15\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\4\uffff\1\3\21\uffff\1\1",
            "\1\4\1\5",
            "\1\6",
            "",
            "",
            "",
            "\1\7",
            "\1\10",
            "\1\11",
            "\1\12",
            "\1\13",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "1816:23: ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' )";
        }
    }
    static final String DFA19_eotS =
        "\1\uffff\3\37\2\uffff\11\37\1\31\2\37\1\31\2\uffff\3\31\2\uffff\5\37\1\uffff\1\37\1\uffff\5\37\2\uffff\17\37\1\uffff\1\37\5\uffff\12\37\1\144\11\37\1\156\11\37\1\156\6\37\1\uffff\3\37\1\u0081\5\37\1\uffff\5\37\1\144\2\37\1\u008f\2\37\1\u0092\5\37\1\u0098\1\uffff\5\37\1\u009e\3\37\1\u009e\1\u00a2\2\37\1\uffff\2\37\1\uffff\5\37\1\uffff\5\37\1\uffff\2\37\1\u009e\1\uffff\16\37\1\u009e\1\u00c2\1\u009e\1\u00c2\1\37\2\u00c4\1\u00c5\4\37\1\u00ca\4\37\1\uffff\1\u00cf\2\uffff\1\u00d0\2\37\1\u00c2\1\uffff\1\37\1\u00d4\1\u00d5\1\37\2\uffff\1\37\1\u00d8\1\u00d9\2\uffff\1\u00da\1\37\3\uffff\1\u00dc\1\uffff";
    static final String DFA19_eofS =
        "\u00dd\uffff";
    static final String DFA19_minS =
        "\1\0\3\60\2\uffff\11\60\1\0\2\60\1\101\2\uffff\2\0\1\52\2\uffff\5\60\1\uffff\1\60\1\uffff\5\60\2\uffff\17\60\1\uffff\1\60\5\uffff\45\60\1\uffff\11\60\1\uffff\22\60\1\uffff\15\60\1\uffff\2\60\1\uffff\5\60\1\uffff\5\60\1\uffff\3\60\1\uffff\37\60\1\uffff\1\60\2\uffff\4\60\1\uffff\4\60\2\uffff\3\60\2\uffff\2\60\3\uffff\1\60\1\uffff";
    static final String DFA19_maxS =
        "\1\uffff\3\172\2\uffff\11\172\1\uffff\3\172\2\uffff\2\uffff\1\57\2\uffff\5\172\1\uffff\1\172\1\uffff\5\172\2\uffff\17\172\1\uffff\1\172\5\uffff\45\172\1\uffff\11\172\1\uffff\22\172\1\uffff\15\172\1\uffff\2\172\1\uffff\5\172\1\uffff\5\172\1\uffff\3\172\1\uffff\37\172\1\uffff\1\172\2\uffff\4\172\1\uffff\4\172\2\uffff\3\172\2\uffff\2\172\3\uffff\1\172\1\uffff";
    static final String DFA19_acceptS =
        "\4\uffff\1\7\1\10\15\uffff\1\31\1\32\3\uffff\1\36\1\37\5\uffff\1\30\1\uffff\1\31\5\uffff\1\7\1\10\17\uffff\1\25\1\uffff\1\32\1\33\1\34\1\35\1\36\45\uffff\1\27\11\uffff\1\23\22\uffff\1\13\15\uffff\1\5\2\uffff\1\2\5\uffff\1\12\5\uffff\1\24\3\uffff\1\1\37\uffff\1\22\1\uffff\1\26\1\3\4\uffff\1\15\4\uffff\1\4\1\6\3\uffff\1\16\1\17\2\uffff\1\11\1\21\1\20\1\uffff\1\14";
    static final String DFA19_specialS =
        "\1\1\16\uffff\1\3\5\uffff\1\0\1\2\u00c6\uffff}>";
    static final String[] DFA19_transitionS = {
            "\11\31\2\30\2\31\1\30\22\31\1\30\1\31\1\25\4\31\1\26\7\31\1\27\12\24\2\31\1\17\4\31\15\21\1\15\4\21\1\16\7\21\3\31\1\22\1\23\1\31\1\11\1\2\1\6\1\21\1\13\1\21\1\14\2\21\1\20\2\21\1\1\1\21\1\3\1\10\4\21\1\7\1\12\4\21\1\4\1\31\1\5\uff82\31",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\36\3\40\1\33\3\40\1\35\5\40\1\34\5\40\1\32\5\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\43\20\40\1\42\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\40\1\44\1\46\14\40\1\45\12\40",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\51\2\40\1\52\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\54\4\40\1\53\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\56\2\40\1\55\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\13\40\1\57\16\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\60\31\40",
            "\12\41\7\uffff\14\40\1\63\1\64\14\40\4\uffff\1\41\1\uffff\2\40\1\62\22\40\1\61\4\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\65\25\40",
            "\12\41\7\uffff\22\40\1\66\7\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\17\40\1\67\12\40\4\uffff\1\41\1\uffff\32\40",
            "\0\70",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\71\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\32\41\4\uffff\1\41\1\uffff\32\41",
            "",
            "",
            "\0\73",
            "\0\73",
            "\1\74\4\uffff\1\75",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\77\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\100\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\3\40\1\101\1\102\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\103\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\27\40\1\104\2\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\105\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\106\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\11\40\1\107\20\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\110\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\13\40\1\111\16\40",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\112\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\113\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\114\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\115\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\116\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\17\40\1\117\12\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\6\40\1\120\23\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\21\40\1\121\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\122\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\11\40\1\123\20\40",
            "\12\41\7\uffff\16\40\1\124\13\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\22\40\1\125\7\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\126\14\40",
            "\12\41\7\uffff\6\40\1\127\23\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\4\40\1\130\25\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\25\40\1\131\4\40",
            "",
            "",
            "",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\132\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\133\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\134\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\135\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\136\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\137\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\140\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\141\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\142\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\143\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\145\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\146\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\147\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\150\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\25\40\1\151\4\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\24\40\1\152\5\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\153\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\154\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\13\40\1\155\16\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\4\40\1\157\25\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\6\40\1\160\23\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\161\25\40",
            "\12\41\7\uffff\1\162\31\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\1\163\31\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\164\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\165\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\14\40\1\166\15\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\13\40\1\167\16\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\14\40\1\170\15\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\14\40\1\171\15\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\3\40\1\172\26\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\17\40\1\173\12\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\2\40\1\174\27\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\14\40\1\175\15\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\176\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\177\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\6\40\1\u0080\23\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u0082\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\13\40\1\u0083\16\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\21\40\1\u0084\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\u0085\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\24\40\1\u0086\5\40",
            "",
            "\12\41\7\uffff\1\u0087\31\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\1\u0088\31\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u0089\6\40",
            "\12\41\7\uffff\10\40\1\u008a\21\40\4\uffff\1\41\1\uffff\32\40",
            "\2\41\1\u008b\7\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u008c\3\40\1\u008d\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u008e\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u0090\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u0091\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\u0093\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u0094\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u0095\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\21\40\1\u0096\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u0097\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\3\40\1\u0099\26\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\u009a\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u009b\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u009c\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u009d\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\10\40\1\u009f\21\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00a0\21\40",
            "\12\41\7\uffff\10\40\1\u00a1\21\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\41\4\uffff\1\41\1\uffff\32\41",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u00a3\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\3\40\1\u00a4\26\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\u00a5\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\u00a6\7\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u00a7\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00a8\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\u00a9\7\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\u00aa\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\25\40\1\u00ab\4\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u00ac\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u00ad\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u00ae\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00af\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00b0\21\40",
            "",
            "\12\41\7\uffff\10\40\1\u00b1\21\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\2\40\1\u00b2\27\40",
            "\12\41\7\uffff\10\40\1\u00b3\21\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\u00b4\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u00b5\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u00b6\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u00b7\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\7\40\1\u00b8\22\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\25\40\1\u00b9\4\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\1\u00ba\31\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00bb\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u00bc\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\21\40\1\u00bd\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00be\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\7\40\1\u00bf\22\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u00c0\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u00c1\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\13\40\1\u00c3\16\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\4\40\1\u00c6\25\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u00c7\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\u00c8\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\21\40\1\u00c9\10\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u00cb\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\14\40\1\u00cc\15\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\u00cd\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\u00ce\14\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\10\40\1\u00d1\21\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\23\40\1\u00d2\6\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\u00d3\14\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\22\40\1\u00d6\7\40",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\16\40\1\u00d7\13\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\15\40\1\u00db\14\40",
            "",
            "",
            "",
            "\12\41\7\uffff\32\40\4\uffff\1\41\1\uffff\32\40",
            ""
    };

    static final short[] DFA19_eot = DFA.unpackEncodedString(DFA19_eotS);
    static final short[] DFA19_eof = DFA.unpackEncodedString(DFA19_eofS);
    static final char[] DFA19_min = DFA.unpackEncodedStringToUnsignedChars(DFA19_minS);
    static final char[] DFA19_max = DFA.unpackEncodedStringToUnsignedChars(DFA19_maxS);
    static final short[] DFA19_accept = DFA.unpackEncodedString(DFA19_acceptS);
    static final short[] DFA19_special = DFA.unpackEncodedString(DFA19_specialS);
    static final short[][] DFA19_transition;

    static {
        int numStates = DFA19_transitionS.length;
        DFA19_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA19_transition[i] = DFA.unpackEncodedString(DFA19_transitionS[i]);
        }
    }

    class DFA19 extends DFA {

        public DFA19(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 19;
            this.eot = DFA19_eot;
            this.eof = DFA19_eof;
            this.min = DFA19_min;
            this.max = DFA19_max;
            this.accept = DFA19_accept;
            this.special = DFA19_special;
            this.transition = DFA19_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | RULE_ALGORITHM_VARIATION | RULE_ALGORITHM_FACTORY | RULE_ALGORITHM_NAME | RULE_URL | RULE_OBJECTIVE_TENDENCY | RULE_ENTITY_TYPE | RULE_ENTITY_NAME | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA19_21 = input.LA(1);

                        s = -1;
                        if ( ((LA19_21>='\u0000' && LA19_21<='\uFFFF')) ) {s = 59;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA19_0 = input.LA(1);

                        s = -1;
                        if ( (LA19_0=='m') ) {s = 1;}

                        else if ( (LA19_0=='b') ) {s = 2;}

                        else if ( (LA19_0=='o') ) {s = 3;}

                        else if ( (LA19_0=='{') ) {s = 4;}

                        else if ( (LA19_0=='}') ) {s = 5;}

                        else if ( (LA19_0=='c') ) {s = 6;}

                        else if ( (LA19_0=='u') ) {s = 7;}

                        else if ( (LA19_0=='p') ) {s = 8;}

                        else if ( (LA19_0=='a') ) {s = 9;}

                        else if ( (LA19_0=='v') ) {s = 10;}

                        else if ( (LA19_0=='e') ) {s = 11;}

                        else if ( (LA19_0=='g') ) {s = 12;}

                        else if ( (LA19_0=='N') ) {s = 13;}

                        else if ( (LA19_0=='S') ) {s = 14;}

                        else if ( (LA19_0=='<') ) {s = 15;}

                        else if ( (LA19_0=='j') ) {s = 16;}

                        else if ( ((LA19_0>='A' && LA19_0<='M')||(LA19_0>='O' && LA19_0<='R')||(LA19_0>='T' && LA19_0<='Z')||LA19_0=='d'||LA19_0=='f'||(LA19_0>='h' && LA19_0<='i')||(LA19_0>='k' && LA19_0<='l')||LA19_0=='n'||(LA19_0>='q' && LA19_0<='t')||(LA19_0>='w' && LA19_0<='z')) ) {s = 17;}

                        else if ( (LA19_0=='^') ) {s = 18;}

                        else if ( (LA19_0=='_') ) {s = 19;}

                        else if ( ((LA19_0>='0' && LA19_0<='9')) ) {s = 20;}

                        else if ( (LA19_0=='\"') ) {s = 21;}

                        else if ( (LA19_0=='\'') ) {s = 22;}

                        else if ( (LA19_0=='/') ) {s = 23;}

                        else if ( ((LA19_0>='\t' && LA19_0<='\n')||LA19_0=='\r'||LA19_0==' ') ) {s = 24;}

                        else if ( ((LA19_0>='\u0000' && LA19_0<='\b')||(LA19_0>='\u000B' && LA19_0<='\f')||(LA19_0>='\u000E' && LA19_0<='\u001F')||LA19_0=='!'||(LA19_0>='#' && LA19_0<='&')||(LA19_0>='(' && LA19_0<='.')||(LA19_0>=':' && LA19_0<=';')||(LA19_0>='=' && LA19_0<='@')||(LA19_0>='[' && LA19_0<=']')||LA19_0=='`'||LA19_0=='|'||(LA19_0>='~' && LA19_0<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA19_22 = input.LA(1);

                        s = -1;
                        if ( ((LA19_22>='\u0000' && LA19_22<='\uFFFF')) ) {s = 59;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA19_15 = input.LA(1);

                        s = -1;
                        if ( ((LA19_15>='\u0000' && LA19_15<='\uFFFF')) ) {s = 56;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 19, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}