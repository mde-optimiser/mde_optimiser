package uk.ac.kcl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMDEOptimiseLexer extends Lexer {
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

    public InternalMDEOptimiseLexer() {;} 
    public InternalMDEOptimiseLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalMDEOptimiseLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalMDEOptimise.g"; }

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:11:7: ( 'basepath' )
            // InternalMDEOptimise.g:11:9: 'basepath'
            {
            match("basepath"); 


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
            // InternalMDEOptimise.g:12:7: ( 'metamodel' )
            // InternalMDEOptimise.g:12:9: 'metamodel'
            {
            match("metamodel"); 


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
            // InternalMDEOptimise.g:13:7: ( 'model' )
            // InternalMDEOptimise.g:13:9: 'model'
            {
            match("model"); 


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
            // InternalMDEOptimise.g:14:7: ( 'objective' )
            // InternalMDEOptimise.g:14:9: 'objective'
            {
            match("objective"); 


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
            // InternalMDEOptimise.g:15:7: ( '{' )
            // InternalMDEOptimise.g:15:9: '{'
            {
            match('{'); 

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
            // InternalMDEOptimise.g:16:7: ( '}' )
            // InternalMDEOptimise.g:16:9: '}'
            {
            match('}'); 

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
            // InternalMDEOptimise.g:17:7: ( 'constraint' )
            // InternalMDEOptimise.g:17:9: 'constraint'
            {
            match("constraint"); 


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
            // InternalMDEOptimise.g:18:7: ( 'using' )
            // InternalMDEOptimise.g:18:9: 'using'
            {
            match("using"); 


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
            // InternalMDEOptimise.g:19:7: ( 'unit' )
            // InternalMDEOptimise.g:19:9: 'unit'
            {
            match("unit"); 


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
            // InternalMDEOptimise.g:20:7: ( 'parameters' )
            // InternalMDEOptimise.g:20:9: 'parameters'
            {
            match("parameters"); 


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
            // InternalMDEOptimise.g:21:7: ( ',' )
            // InternalMDEOptimise.g:21:9: ','
            {
            match(','); 

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
            // InternalMDEOptimise.g:22:7: ( '=>' )
            // InternalMDEOptimise.g:22:9: '=>'
            {
            match("=>"); 


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
            // InternalMDEOptimise.g:23:7: ( '(' )
            // InternalMDEOptimise.g:23:9: '('
            {
            match('('); 

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
            // InternalMDEOptimise.g:24:7: ( ')' )
            // InternalMDEOptimise.g:24:9: ')'
            {
            match(')'); 

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
            // InternalMDEOptimise.g:25:7: ( 'optimisation' )
            // InternalMDEOptimise.g:25:9: 'optimisation'
            {
            match("optimisation"); 


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
            // InternalMDEOptimise.g:26:7: ( 'provider' )
            // InternalMDEOptimise.g:26:9: 'provider'
            {
            match("provider"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "T__35"
    public final void mT__35() throws RecognitionException {
        try {
            int _type = T__35;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:27:7: ( 'algorithm' )
            // InternalMDEOptimise.g:27:9: 'algorithm'
            {
            match("algorithm"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__35"

    // $ANTLR start "T__36"
    public final void mT__36() throws RecognitionException {
        try {
            int _type = T__36;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:28:7: ( 'variation' )
            // InternalMDEOptimise.g:28:9: 'variation'
            {
            match("variation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__36"

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:29:7: ( 'evolutions' )
            // InternalMDEOptimise.g:29:9: 'evolutions'
            {
            match("evolutions"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:30:7: ( 'population' )
            // InternalMDEOptimise.g:30:9: 'population'
            {
            match("population"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:31:7: ( 'experiments' )
            // InternalMDEOptimise.g:31:9: 'experiments'
            {
            match("experiments"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:32:7: ( 'mutate' )
            // InternalMDEOptimise.g:32:9: 'mutate'
            {
            match("mutate"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:33:7: ( 'breed' )
            // InternalMDEOptimise.g:33:9: 'breed'
            {
            match("breed"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "RULE_GENETIC_VARIATION"
    public final void mRULE_GENETIC_VARIATION() throws RecognitionException {
        try {
            int _type = RULE_GENETIC_VARIATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1161:24: ( 'genetic' )
            // InternalMDEOptimise.g:1161:26: 'genetic'
            {
            match("genetic"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_GENETIC_VARIATION"

    // $ANTLR start "RULE_CROSSOVER_VARIATION"
    public final void mRULE_CROSSOVER_VARIATION() throws RecognitionException {
        try {
            int _type = RULE_CROSSOVER_VARIATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1163:26: ( 'crossover' )
            // InternalMDEOptimise.g:1163:28: 'crossover'
            {
            match("crossover"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_CROSSOVER_VARIATION"

    // $ANTLR start "RULE_MUTATION_VARIATION"
    public final void mRULE_MUTATION_VARIATION() throws RecognitionException {
        try {
            int _type = RULE_MUTATION_VARIATION;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1165:25: ( 'mutation' )
            // InternalMDEOptimise.g:1165:27: 'mutation'
            {
            match("mutation"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_MUTATION_VARIATION"

    // $ANTLR start "RULE_ALGORITHM_FACTORY"
    public final void mRULE_ALGORITHM_FACTORY() throws RecognitionException {
        try {
            int _type = RULE_ALGORITHM_FACTORY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1167:24: ( ( 'moea' | 'ecj' ) )
            // InternalMDEOptimise.g:1167:26: ( 'moea' | 'ecj' )
            {
            // InternalMDEOptimise.g:1167:26: ( 'moea' | 'ecj' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='m') ) {
                alt1=1;
            }
            else if ( (LA1_0=='e') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalMDEOptimise.g:1167:27: 'moea'
                    {
                    match("moea"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1167:34: 'ecj'
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
            // InternalMDEOptimise.g:1169:21: ( ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' ) )
            // InternalMDEOptimise.g:1169:23: ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' )
            {
            // InternalMDEOptimise.g:1169:23: ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' )
            int alt2=5;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // InternalMDEOptimise.g:1169:24: 'eMOEA'
                    {
                    match("eMOEA"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1169:32: 'eNSGAII'
                    {
                    match("eNSGAII"); 


                    }
                    break;
                case 3 :
                    // InternalMDEOptimise.g:1169:42: 'NSGAII'
                    {
                    match("NSGAII"); 


                    }
                    break;
                case 4 :
                    // InternalMDEOptimise.g:1169:51: 'NSGAIII'
                    {
                    match("NSGAIII"); 


                    }
                    break;
                case 5 :
                    // InternalMDEOptimise.g:1169:61: 'SPEA2'
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
            // InternalMDEOptimise.g:1171:10: ( '<' ( options {greedy=false; } : . )* '>' )
            // InternalMDEOptimise.g:1171:12: '<' ( options {greedy=false; } : . )* '>'
            {
            match('<'); 
            // InternalMDEOptimise.g:1171:16: ( options {greedy=false; } : . )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='>') ) {
                    alt3=2;
                }
                else if ( ((LA3_0>='\u0000' && LA3_0<='=')||(LA3_0>='?' && LA3_0<='\uFFFF')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMDEOptimise.g:1171:44: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop3;
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
            // InternalMDEOptimise.g:1173:25: ( ( 'minimise' | 'maximise' ) )
            // InternalMDEOptimise.g:1173:27: ( 'minimise' | 'maximise' )
            {
            // InternalMDEOptimise.g:1173:27: ( 'minimise' | 'maximise' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='m') ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1=='i') ) {
                    alt4=1;
                }
                else if ( (LA4_1=='a') ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalMDEOptimise.g:1173:28: 'minimise'
                    {
                    match("minimise"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1173:39: 'maximise'
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
            // InternalMDEOptimise.g:1175:18: ( ( 'java' | 'ocl' ) )
            // InternalMDEOptimise.g:1175:20: ( 'java' | 'ocl' )
            {
            // InternalMDEOptimise.g:1175:20: ( 'java' | 'ocl' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='j') ) {
                alt5=1;
            }
            else if ( (LA5_0=='o') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // InternalMDEOptimise.g:1175:21: 'java'
                    {
                    match("java"); 


                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1175:28: 'ocl'
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

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalMDEOptimise.g:1177:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalMDEOptimise.g:1177:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalMDEOptimise.g:1177:11: ( '^' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='^') ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalMDEOptimise.g:1177:11: '^'
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

            // InternalMDEOptimise.g:1177:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='0' && LA7_0<='9')||(LA7_0>='A' && LA7_0<='Z')||LA7_0=='_'||(LA7_0>='a' && LA7_0<='z')) ) {
                    alt7=1;
                }


                switch (alt7) {
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
            	    break loop7;
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
            // InternalMDEOptimise.g:1179:10: ( ( '0' .. '9' )+ )
            // InternalMDEOptimise.g:1179:12: ( '0' .. '9' )+
            {
            // InternalMDEOptimise.g:1179:12: ( '0' .. '9' )+
            int cnt8=0;
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalMDEOptimise.g:1179:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt8 >= 1 ) break loop8;
                        EarlyExitException eee =
                            new EarlyExitException(8, input);
                        throw eee;
                }
                cnt8++;
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
            // InternalMDEOptimise.g:1181:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalMDEOptimise.g:1181:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalMDEOptimise.g:1181:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\"') ) {
                alt11=1;
            }
            else if ( (LA11_0=='\'') ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalMDEOptimise.g:1181:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalMDEOptimise.g:1181:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop9:
                    do {
                        int alt9=3;
                        int LA9_0 = input.LA(1);

                        if ( (LA9_0=='\\') ) {
                            alt9=1;
                        }
                        else if ( ((LA9_0>='\u0000' && LA9_0<='!')||(LA9_0>='#' && LA9_0<='[')||(LA9_0>=']' && LA9_0<='\uFFFF')) ) {
                            alt9=2;
                        }


                        switch (alt9) {
                    	case 1 :
                    	    // InternalMDEOptimise.g:1181:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalMDEOptimise.g:1181:28: ~ ( ( '\\\\' | '\"' ) )
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
                    	    break loop9;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalMDEOptimise.g:1181:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalMDEOptimise.g:1181:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop10:
                    do {
                        int alt10=3;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0=='\\') ) {
                            alt10=1;
                        }
                        else if ( ((LA10_0>='\u0000' && LA10_0<='&')||(LA10_0>='(' && LA10_0<='[')||(LA10_0>=']' && LA10_0<='\uFFFF')) ) {
                            alt10=2;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // InternalMDEOptimise.g:1181:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalMDEOptimise.g:1181:61: ~ ( ( '\\\\' | '\\'' ) )
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
                    	    break loop10;
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
            // InternalMDEOptimise.g:1183:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalMDEOptimise.g:1183:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalMDEOptimise.g:1183:24: ( options {greedy=false; } : . )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0=='*') ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1=='/') ) {
                        alt12=2;
                    }
                    else if ( ((LA12_1>='\u0000' && LA12_1<='.')||(LA12_1>='0' && LA12_1<='\uFFFF')) ) {
                        alt12=1;
                    }


                }
                else if ( ((LA12_0>='\u0000' && LA12_0<=')')||(LA12_0>='+' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMDEOptimise.g:1183:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop12;
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
            // InternalMDEOptimise.g:1185:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalMDEOptimise.g:1185:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalMDEOptimise.g:1185:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalMDEOptimise.g:1185:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop13;
                }
            } while (true);

            // InternalMDEOptimise.g:1185:40: ( ( '\\r' )? '\\n' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0=='\n'||LA15_0=='\r') ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMDEOptimise.g:1185:41: ( '\\r' )? '\\n'
                    {
                    // InternalMDEOptimise.g:1185:41: ( '\\r' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0=='\r') ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalMDEOptimise.g:1185:41: '\\r'
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
            // InternalMDEOptimise.g:1187:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalMDEOptimise.g:1187:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalMDEOptimise.g:1187:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='\t' && LA16_0<='\n')||LA16_0=='\r'||LA16_0==' ') ) {
                    alt16=1;
                }


                switch (alt16) {
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
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
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
            // InternalMDEOptimise.g:1189:16: ( . )
            // InternalMDEOptimise.g:1189:18: .
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
        // InternalMDEOptimise.g:1:8: ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | RULE_GENETIC_VARIATION | RULE_CROSSOVER_VARIATION | RULE_MUTATION_VARIATION | RULE_ALGORITHM_FACTORY | RULE_ALGORITHM_NAME | RULE_URL | RULE_OBJECTIVE_TENDENCY | RULE_ENTITY_TYPE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt17=38;
        alt17 = dfa17.predict(input);
        switch (alt17) {
            case 1 :
                // InternalMDEOptimise.g:1:10: T__19
                {
                mT__19(); 

                }
                break;
            case 2 :
                // InternalMDEOptimise.g:1:16: T__20
                {
                mT__20(); 

                }
                break;
            case 3 :
                // InternalMDEOptimise.g:1:22: T__21
                {
                mT__21(); 

                }
                break;
            case 4 :
                // InternalMDEOptimise.g:1:28: T__22
                {
                mT__22(); 

                }
                break;
            case 5 :
                // InternalMDEOptimise.g:1:34: T__23
                {
                mT__23(); 

                }
                break;
            case 6 :
                // InternalMDEOptimise.g:1:40: T__24
                {
                mT__24(); 

                }
                break;
            case 7 :
                // InternalMDEOptimise.g:1:46: T__25
                {
                mT__25(); 

                }
                break;
            case 8 :
                // InternalMDEOptimise.g:1:52: T__26
                {
                mT__26(); 

                }
                break;
            case 9 :
                // InternalMDEOptimise.g:1:58: T__27
                {
                mT__27(); 

                }
                break;
            case 10 :
                // InternalMDEOptimise.g:1:64: T__28
                {
                mT__28(); 

                }
                break;
            case 11 :
                // InternalMDEOptimise.g:1:70: T__29
                {
                mT__29(); 

                }
                break;
            case 12 :
                // InternalMDEOptimise.g:1:76: T__30
                {
                mT__30(); 

                }
                break;
            case 13 :
                // InternalMDEOptimise.g:1:82: T__31
                {
                mT__31(); 

                }
                break;
            case 14 :
                // InternalMDEOptimise.g:1:88: T__32
                {
                mT__32(); 

                }
                break;
            case 15 :
                // InternalMDEOptimise.g:1:94: T__33
                {
                mT__33(); 

                }
                break;
            case 16 :
                // InternalMDEOptimise.g:1:100: T__34
                {
                mT__34(); 

                }
                break;
            case 17 :
                // InternalMDEOptimise.g:1:106: T__35
                {
                mT__35(); 

                }
                break;
            case 18 :
                // InternalMDEOptimise.g:1:112: T__36
                {
                mT__36(); 

                }
                break;
            case 19 :
                // InternalMDEOptimise.g:1:118: T__37
                {
                mT__37(); 

                }
                break;
            case 20 :
                // InternalMDEOptimise.g:1:124: T__38
                {
                mT__38(); 

                }
                break;
            case 21 :
                // InternalMDEOptimise.g:1:130: T__39
                {
                mT__39(); 

                }
                break;
            case 22 :
                // InternalMDEOptimise.g:1:136: T__40
                {
                mT__40(); 

                }
                break;
            case 23 :
                // InternalMDEOptimise.g:1:142: T__41
                {
                mT__41(); 

                }
                break;
            case 24 :
                // InternalMDEOptimise.g:1:148: RULE_GENETIC_VARIATION
                {
                mRULE_GENETIC_VARIATION(); 

                }
                break;
            case 25 :
                // InternalMDEOptimise.g:1:171: RULE_CROSSOVER_VARIATION
                {
                mRULE_CROSSOVER_VARIATION(); 

                }
                break;
            case 26 :
                // InternalMDEOptimise.g:1:196: RULE_MUTATION_VARIATION
                {
                mRULE_MUTATION_VARIATION(); 

                }
                break;
            case 27 :
                // InternalMDEOptimise.g:1:220: RULE_ALGORITHM_FACTORY
                {
                mRULE_ALGORITHM_FACTORY(); 

                }
                break;
            case 28 :
                // InternalMDEOptimise.g:1:243: RULE_ALGORITHM_NAME
                {
                mRULE_ALGORITHM_NAME(); 

                }
                break;
            case 29 :
                // InternalMDEOptimise.g:1:263: RULE_URL
                {
                mRULE_URL(); 

                }
                break;
            case 30 :
                // InternalMDEOptimise.g:1:272: RULE_OBJECTIVE_TENDENCY
                {
                mRULE_OBJECTIVE_TENDENCY(); 

                }
                break;
            case 31 :
                // InternalMDEOptimise.g:1:296: RULE_ENTITY_TYPE
                {
                mRULE_ENTITY_TYPE(); 

                }
                break;
            case 32 :
                // InternalMDEOptimise.g:1:313: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 33 :
                // InternalMDEOptimise.g:1:321: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 34 :
                // InternalMDEOptimise.g:1:330: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 35 :
                // InternalMDEOptimise.g:1:342: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 36 :
                // InternalMDEOptimise.g:1:358: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 37 :
                // InternalMDEOptimise.g:1:374: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 38 :
                // InternalMDEOptimise.g:1:382: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA2_eotS =
        "\12\uffff\1\14\2\uffff";
    static final String DFA2_eofS =
        "\15\uffff";
    static final String DFA2_minS =
        "\1\116\1\115\1\123\3\uffff\1\107\1\101\3\111\2\uffff";
    static final String DFA2_maxS =
        "\1\145\1\116\1\123\3\uffff\1\107\1\101\3\111\2\uffff";
    static final String DFA2_acceptS =
        "\3\uffff\1\5\1\1\1\2\5\uffff\1\4\1\3";
    static final String DFA2_specialS =
        "\15\uffff}>";
    static final String[] DFA2_transitionS = {
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

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "1169:23: ( 'eMOEA' | 'eNSGAII' | 'NSGAII' | 'NSGAIII' | 'SPEA2' )";
        }
    }
    static final String DFA17_eotS =
        "\1\uffff\3\37\2\uffff\3\37\1\uffff\1\34\2\uffff\6\37\1\34\1\37\1\34\2\uffff\3\34\2\uffff\2\37\1\uffff\10\37\2\uffff\7\37\4\uffff\12\37\1\uffff\1\37\5\uffff\12\37\1\155\13\37\1\171\12\37\1\171\5\37\1\uffff\3\37\1\u008c\7\37\1\uffff\5\37\1\155\1\37\1\u009a\1\37\1\u009c\7\37\1\u00a5\1\uffff\7\37\1\u00ad\3\37\1\u00ad\1\37\1\uffff\1\37\1\uffff\1\u00b3\7\37\1\uffff\7\37\1\uffff\2\37\1\u00ad\2\37\1\uffff\16\37\1\u00ad\1\u00d5\1\u00ad\1\u00d6\1\37\1\u00d8\2\u00d9\5\37\1\u00df\5\37\2\uffff\1\u00e5\2\uffff\1\u00e6\2\37\1\u00e9\1\37\1\uffff\1\37\1\u00ec\1\u00ed\2\37\2\uffff\1\37\1\u00f1\1\uffff\1\u00f2\1\u00f3\2\uffff\1\u00f4\2\37\4\uffff\1\u00f7\1\u00f8\2\uffff";
    static final String DFA17_eofS =
        "\u00f9\uffff";
    static final String DFA17_minS =
        "\1\0\2\141\1\142\2\uffff\1\157\1\156\1\141\1\uffff\1\76\2\uffff\1\154\1\141\1\115\1\145\1\123\1\120\1\0\1\141\1\101\2\uffff\2\0\1\52\2\uffff\1\163\1\145\1\uffff\1\164\1\144\1\164\1\156\1\170\1\152\1\164\1\154\2\uffff\1\156\1\157\2\151\1\162\1\157\1\160\4\uffff\1\147\1\162\1\157\1\160\1\152\1\117\1\123\1\156\1\107\1\105\1\uffff\1\166\5\uffff\2\145\1\141\1\145\2\141\2\151\1\145\1\151\1\60\2\163\1\156\1\164\1\141\1\166\1\165\1\157\1\151\1\154\1\145\1\60\1\105\1\107\1\145\2\101\1\141\1\160\1\144\1\155\1\154\1\60\1\164\2\155\1\143\1\155\1\uffff\1\164\1\163\1\147\1\60\1\155\1\151\1\154\1\162\1\141\1\165\1\162\1\uffff\2\101\1\164\1\111\1\62\1\60\1\141\1\60\1\157\1\60\1\145\2\151\1\164\1\151\1\162\1\157\1\60\1\uffff\1\145\1\144\1\141\1\151\2\164\1\151\1\60\1\111\1\151\1\111\1\60\1\164\1\uffff\1\144\1\uffff\1\60\1\157\2\163\1\151\1\163\1\141\1\166\1\uffff\1\164\1\145\2\164\2\151\1\155\1\uffff\1\111\1\143\1\60\1\150\1\145\1\uffff\1\156\2\145\1\166\1\141\1\151\2\145\1\162\1\151\1\150\2\157\1\145\4\60\1\154\3\60\1\145\1\164\1\156\2\162\1\60\1\157\1\155\3\156\2\uffff\1\60\2\uffff\1\60\1\151\1\164\1\60\1\163\1\uffff\1\156\2\60\1\163\1\164\2\uffff\1\157\1\60\1\uffff\2\60\2\uffff\1\60\1\163\1\156\4\uffff\2\60\2\uffff";
    static final String DFA17_maxS =
        "\1\uffff\1\162\1\165\1\160\2\uffff\1\162\1\163\1\162\1\uffff\1\76\2\uffff\1\154\1\141\1\170\1\145\1\123\1\120\1\uffff\1\141\1\172\2\uffff\2\uffff\1\57\2\uffff\1\163\1\145\1\uffff\1\164\1\145\1\164\1\156\1\170\1\152\1\164\1\154\2\uffff\1\156\1\157\2\151\1\162\1\157\1\160\4\uffff\1\147\1\162\1\157\1\160\1\152\1\117\1\123\1\156\1\107\1\105\1\uffff\1\166\5\uffff\2\145\1\141\1\145\2\141\2\151\1\145\1\151\1\172\2\163\1\156\1\164\1\141\1\166\1\165\1\157\1\151\1\154\1\145\1\172\1\105\1\107\1\145\2\101\1\141\1\160\1\144\1\155\1\154\1\172\1\164\2\155\1\143\1\155\1\uffff\1\164\1\163\1\147\1\172\1\155\1\151\1\154\1\162\1\141\1\165\1\162\1\uffff\2\101\1\164\1\111\1\62\1\172\1\141\1\172\1\157\1\172\3\151\1\164\1\151\1\162\1\157\1\172\1\uffff\1\145\1\144\1\141\1\151\2\164\1\151\1\172\1\111\1\151\1\111\1\172\1\164\1\uffff\1\144\1\uffff\1\172\1\157\2\163\1\151\1\163\1\141\1\166\1\uffff\1\164\1\145\2\164\2\151\1\155\1\uffff\1\111\1\143\1\172\1\150\1\145\1\uffff\1\156\2\145\1\166\1\141\1\151\2\145\1\162\1\151\1\150\2\157\1\145\4\172\1\154\3\172\1\145\1\164\1\156\2\162\1\172\1\157\1\155\3\156\2\uffff\1\172\2\uffff\1\172\1\151\1\164\1\172\1\163\1\uffff\1\156\2\172\1\163\1\164\2\uffff\1\157\1\172\1\uffff\2\172\2\uffff\1\172\1\163\1\156\4\uffff\2\172\2\uffff";
    static final String DFA17_acceptS =
        "\4\uffff\1\5\1\6\3\uffff\1\13\1\uffff\1\15\1\16\11\uffff\1\40\1\41\3\uffff\1\45\1\46\2\uffff\1\40\10\uffff\1\5\1\6\7\uffff\1\13\1\14\1\15\1\16\12\uffff\1\35\1\uffff\1\41\1\42\1\43\1\44\1\45\47\uffff\1\37\13\uffff\1\33\22\uffff\1\11\15\uffff\1\27\1\uffff\1\3\10\uffff\1\10\7\uffff\1\34\5\uffff\1\26\41\uffff\1\30\1\1\1\uffff\1\32\1\36\5\uffff\1\20\5\uffff\1\2\1\4\2\uffff\1\31\2\uffff\1\21\1\22\3\uffff\1\7\1\12\1\24\1\23\2\uffff\1\25\1\17";
    static final String DFA17_specialS =
        "\1\0\22\uffff\1\2\4\uffff\1\1\1\3\u00df\uffff}>";
    static final String[] DFA17_transitionS = {
            "\11\34\2\33\2\34\1\33\22\34\1\33\1\34\1\30\4\34\1\31\1\13\1\14\2\34\1\11\2\34\1\32\12\27\2\34\1\23\1\12\3\34\15\26\1\21\4\26\1\22\7\26\3\34\1\25\1\26\1\34\1\15\1\1\1\6\1\26\1\17\1\26\1\20\2\26\1\24\2\26\1\2\1\26\1\3\1\10\4\26\1\7\1\16\4\26\1\4\1\34\1\5\uff82\34",
            "\1\35\20\uffff\1\36",
            "\1\44\3\uffff\1\40\3\uffff\1\43\5\uffff\1\41\5\uffff\1\42",
            "\1\45\1\47\14\uffff\1\46",
            "",
            "",
            "\1\52\2\uffff\1\53",
            "\1\55\4\uffff\1\54",
            "\1\56\15\uffff\1\60\2\uffff\1\57",
            "",
            "\1\62",
            "",
            "",
            "\1\65",
            "\1\66",
            "\1\72\1\73\24\uffff\1\71\22\uffff\1\67\1\uffff\1\70",
            "\1\74",
            "\1\75",
            "\1\76",
            "\0\77",
            "\1\100",
            "\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "\0\102",
            "\0\102",
            "\1\103\4\uffff\1\104",
            "",
            "",
            "\1\106",
            "\1\107",
            "",
            "\1\110",
            "\1\111\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "",
            "",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
            "",
            "",
            "",
            "",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "",
            "\1\142",
            "",
            "",
            "",
            "",
            "",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\161",
            "\1\162",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u008d",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "",
            "\1\u0094",
            "\1\u0095",
            "\1\u0096",
            "\1\u0097",
            "\1\u0098",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u0099",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u009b",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u009d\3\uffff\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\1\u00a2",
            "\1\u00a3",
            "\1\u00a4",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\1\u00a6",
            "\1\u00a7",
            "\1\u00a8",
            "\1\u00a9",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00ae",
            "\1\u00af",
            "\1\u00b0",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00b1",
            "",
            "\1\u00b2",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00b4",
            "\1\u00b5",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00b8",
            "\1\u00b9",
            "\1\u00ba",
            "",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\1\u00bf",
            "\1\u00c0",
            "\1\u00c1",
            "",
            "\1\u00c2",
            "\1\u00c3",
            "\12\37\7\uffff\10\37\1\u00c4\21\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00c5",
            "\1\u00c6",
            "",
            "\1\u00c7",
            "\1\u00c8",
            "\1\u00c9",
            "\1\u00ca",
            "\1\u00cb",
            "\1\u00cc",
            "\1\u00cd",
            "\1\u00ce",
            "\1\u00cf",
            "\1\u00d0",
            "\1\u00d1",
            "\1\u00d2",
            "\1\u00d3",
            "\1\u00d4",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00d7",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00da",
            "\1\u00db",
            "\1\u00dc",
            "\1\u00dd",
            "\1\u00de",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00e0",
            "\1\u00e1",
            "\1\u00e2",
            "\1\u00e3",
            "\1\u00e4",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00e7",
            "\1\u00e8",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00ea",
            "",
            "\1\u00eb",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00ee",
            "\1\u00ef",
            "",
            "",
            "\1\u00f0",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u00f5",
            "\1\u00f6",
            "",
            "",
            "",
            "",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "",
            ""
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | RULE_GENETIC_VARIATION | RULE_CROSSOVER_VARIATION | RULE_MUTATION_VARIATION | RULE_ALGORITHM_FACTORY | RULE_ALGORITHM_NAME | RULE_URL | RULE_OBJECTIVE_TENDENCY | RULE_ENTITY_TYPE | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA17_0 = input.LA(1);

                        s = -1;
                        if ( (LA17_0=='b') ) {s = 1;}

                        else if ( (LA17_0=='m') ) {s = 2;}

                        else if ( (LA17_0=='o') ) {s = 3;}

                        else if ( (LA17_0=='{') ) {s = 4;}

                        else if ( (LA17_0=='}') ) {s = 5;}

                        else if ( (LA17_0=='c') ) {s = 6;}

                        else if ( (LA17_0=='u') ) {s = 7;}

                        else if ( (LA17_0=='p') ) {s = 8;}

                        else if ( (LA17_0==',') ) {s = 9;}

                        else if ( (LA17_0=='=') ) {s = 10;}

                        else if ( (LA17_0=='(') ) {s = 11;}

                        else if ( (LA17_0==')') ) {s = 12;}

                        else if ( (LA17_0=='a') ) {s = 13;}

                        else if ( (LA17_0=='v') ) {s = 14;}

                        else if ( (LA17_0=='e') ) {s = 15;}

                        else if ( (LA17_0=='g') ) {s = 16;}

                        else if ( (LA17_0=='N') ) {s = 17;}

                        else if ( (LA17_0=='S') ) {s = 18;}

                        else if ( (LA17_0=='<') ) {s = 19;}

                        else if ( (LA17_0=='j') ) {s = 20;}

                        else if ( (LA17_0=='^') ) {s = 21;}

                        else if ( ((LA17_0>='A' && LA17_0<='M')||(LA17_0>='O' && LA17_0<='R')||(LA17_0>='T' && LA17_0<='Z')||LA17_0=='_'||LA17_0=='d'||LA17_0=='f'||(LA17_0>='h' && LA17_0<='i')||(LA17_0>='k' && LA17_0<='l')||LA17_0=='n'||(LA17_0>='q' && LA17_0<='t')||(LA17_0>='w' && LA17_0<='z')) ) {s = 22;}

                        else if ( ((LA17_0>='0' && LA17_0<='9')) ) {s = 23;}

                        else if ( (LA17_0=='\"') ) {s = 24;}

                        else if ( (LA17_0=='\'') ) {s = 25;}

                        else if ( (LA17_0=='/') ) {s = 26;}

                        else if ( ((LA17_0>='\t' && LA17_0<='\n')||LA17_0=='\r'||LA17_0==' ') ) {s = 27;}

                        else if ( ((LA17_0>='\u0000' && LA17_0<='\b')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='\u001F')||LA17_0=='!'||(LA17_0>='#' && LA17_0<='&')||(LA17_0>='*' && LA17_0<='+')||(LA17_0>='-' && LA17_0<='.')||(LA17_0>=':' && LA17_0<=';')||(LA17_0>='>' && LA17_0<='@')||(LA17_0>='[' && LA17_0<=']')||LA17_0=='`'||LA17_0=='|'||(LA17_0>='~' && LA17_0<='\uFFFF')) ) {s = 28;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA17_24 = input.LA(1);

                        s = -1;
                        if ( ((LA17_24>='\u0000' && LA17_24<='\uFFFF')) ) {s = 66;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA17_19 = input.LA(1);

                        s = -1;
                        if ( ((LA17_19>='\u0000' && LA17_19<='\uFFFF')) ) {s = 63;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA17_25 = input.LA(1);

                        s = -1;
                        if ( ((LA17_25>='\u0000' && LA17_25<='\uFFFF')) ) {s = 66;}

                        else s = 28;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 17, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}