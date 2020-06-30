package argumentation.scenario.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.argumentation.fitness.EcoreScenarioParser;
import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.parser.FileFormat;
import net.sf.tweety.arg.dung.prover.ProboSolver;
import net.sf.tweety.arg.dung.semantics.Problem;
import net.sf.tweety.arg.dung.syntax.Argument;
import net.sf.tweety.arg.dung.syntax.Attack;
import net.sf.tweety.arg.dung.writer.DungWriter;
import net.sf.tweety.commons.util.Shell;


public class BruteForceSearcher {
	
	DungTheory persuaderAF;
	List<DungTheory> audienceAFs;
	Argument topic;
	
	public BruteForceSearcher(Scenario start){
		persuaderAF = start.getPersuadersFramework();
		audienceAFs = start.getPersuadeeArgumentationFrameworks();
		topic = start.getTopic();
		
	}
	
	//Patch the outcome to also return time taken to find solutions.
	public Map<Integer, List<Argument>> findBestArgumentsToAssert(long startTime) throws IOException{
		
		ArrayList<Argument> args = new ArrayList<Argument>(persuaderAF.getNodes());
		
		if (args.size() > 30) {
			throw new InvalidObjectException("Invalid number of arguments given. The power set cannot be generated");
		}
		
		ArrayList<ArrayList<Argument>> allPossibleAssertions = getSubsets(args);
		
		ArrayList<Argument> best = null;
		Map<Integer, List<Argument>> outcome = new HashMap<Integer, List<Argument>>();
		int mostConvinced = 0;
		
		outerloop:
		for (ArrayList<Argument> possibleAssertion : allPossibleAssertions){
			
			//Decided to leave this in here and explain in the paper
			//if this returns true, that in some cases BF can be lucky
			//and BC of the overhead of MDEO, it may be slower in those instances
			//but it (hopefully) finds the same solutions as the premature BF alg.
			if (mostConvinced == audienceAFs.size()){
				break;
			}

			int convinced = 0;
			for (DungTheory persuadee : audienceAFs){
				if (topicAcceptableWithAssertions(persuadee, possibleAssertion)){
					convinced++;
				}
				
				//If the search ran out of time, then break after 5 minutes
				if(exhaustedRunTime(startTime)) {
					break outerloop;
				}
			}
			if (convinced > mostConvinced){
				mostConvinced = convinced;
				best = possibleAssertion;
				
				outcome.put(convinced, best);
			}
		}
		
		return outcome;
	}
	
	public boolean topicAcceptableWithAssertions(DungTheory persuadee, ArrayList<Argument> possibleAssertion) throws IOException {
		DungTheory dt = new DungTheory(persuadee);
		dt.addAll(possibleAssertion);
		
		for (Attack att : persuaderAF.getAttacks()){
			if (dt.contains(att.getAttacked()) && dt.contains(att.getAttacker())){
				dt.add(att);
			}
		}
		
		ProboSolver nativeSolver = new ProboSolver("argmatsat", Shell.getNativeShell());
		return justify(nativeSolver, Problem.DC_PR, dt, FileFormat.TGF, topic);
	
	}

	
	public boolean justify(ProboSolver solver, Problem problem, DungTheory aaf, FileFormat format, Argument arg) throws IOException {
		
		if (!problem.isJustificationProblem())
			throw new RuntimeException("Fail: "+problem+"is not a justification problem.");
		
		File temp = File.createTempFile("aaf-", "."+format.extension());

		DungWriter writer = DungWriter.getWriter(format);
		writer.write(aaf, temp);
		
		String str = solver.solve(problem, temp, format, " -a "+arg);
		
		//Delete the temporary file now to avoid temp disk filling
		temp.delete();
		
		if (Pattern.matches("\\s*YES\\s*",str)) {
			return true;
		} else if (Pattern.matches("\\s*NO\\s*",str)) {
			return false;
		} else {
			throw new RuntimeException("Calling executable did not return useful output");
		}
	}
	
	public static ArrayList<ArrayList<Argument>> getSubsets(ArrayList<Argument> set) {

        ArrayList<ArrayList<Argument>> allsubsets = new ArrayList<ArrayList<Argument>>();
        int max = 1 << set.size();             //there are 2 power n different subsets

        for (int i = 0; i < max; i++) {
            ArrayList<Argument> subset = new ArrayList<Argument>();
            for (int j = 0; j < set.size(); j++) {
                if (((i >> j) & 1) == 1) {
                    subset.add(set.get(j));
                }
            }
            allsubsets.add(subset);
        }
        return allsubsets;
    }
	
	public static String formatElapsedTime(long milliseconds) {

	    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

	    return sdf.format(milliseconds);
	}
	
	public static List<String> serialiseArguments(Map<Integer, List<Argument>> arguments){
		List<String> serializedWinningArguments = new ArrayList<String>();
		
		for(Integer persuaded : arguments.keySet()) {
			for(Argument argument : arguments.get(persuaded)) {
				serializedWinningArguments.add(argument.toString());
			}
		}
		
		return serializedWinningArguments;
	}
	
	public static Scenario loadScenario(File inputModelFile) {
		
		ModelLoader modelLoader = new ModelLoader();
		
		EObject model = modelLoader.loadModel(inputModelFile.getParent(), inputModelFile.getName());
		
		return new EcoreScenarioParser(model).getScenario();
	}
	
	public static void saveExperimentOutput(Scenario scenario, long executionTime, String path, Map<Integer, List<Argument>> winningArguments) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyMMdd-HHmmss").format(new java.util.Date());
		
		File file = new File(String.format("mdeo-results/bf-%s-%s/%s-%s-output.txt", path, timeStamp, path, timeStamp));
		
		file.getParentFile().mkdirs();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(String.format("Running experiment with input model: %s", path));
		
		Gson gson = new GsonBuilder().create();
		String result = gson.toJson(serialiseArguments(winningArguments));
		int persuaded = 0;
		
		if(winningArguments.size() > 0) {
			for(Integer key : winningArguments.keySet()) {
				persuaded = key;
				pw.println(String.format("Persuaded audience members: %s", key));
				pw.println(String.format("Winning arguments: %s", result));
			}
		} else {
			pw.println("No winning arguments found in the given time.");
		}
			

		Date date = new Date((executionTime) / 1000000); // if you really have long
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String runtime = formatter.format(date.getTime()) ;
		
		saveCsv(path, timeStamp, runtime, persuaded, result);
		
		pw.println(String.format("Experiment duration: %s", runtime));
		pw.close();
		fw.close();
	}
	
	public static void saveCsv(String path, String timeStamp, String runtime, int persuaded, String winningArguments) throws IOException {
		File csvFile = new File(String.format("mdeo-results/bf-%s-%s/%s-%s-output.csv", path, timeStamp, path, timeStamp));
		
		csvFile.getParentFile().mkdirs();
		
		FileWriter fw = new FileWriter(csvFile);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("run,runtime,persuaded,assertedArguments");
		pw.println(String.format("0,%s,%s,%s",runtime, persuaded, winningArguments));
		
		pw.close();
		fw.close();
	}
	
	public static boolean exhaustedRunTime(long startTime) {
		
		long currentTime = System.nanoTime();		
		long elapsedTime = (currentTime - startTime)/1000000000;

		return elapsedTime > 300;
	}
	
	public static void main(String[] args) throws IOException{
		
		String modelPath = "src/main/resources/models/argumentation/PF-Cycle-101-args-Audience-10-Members-PAF-size-27-args.xmi";
		
		//If we passed another parameter, then use that path instead.
		if(args.length == 1) {
			modelPath = args[0];
		}
	
		File inputModelFile = new File(modelPath);
		
		Scenario scenario = loadScenario(inputModelFile);
		
		long startTime = System.nanoTime();
		BruteForceSearcher b = new BruteForceSearcher(scenario);
		Map<Integer, List<Argument>> winningArguments = b.findBestArgumentsToAssert(startTime);
		long stopTime = System.nanoTime();
		long executionTime = stopTime - startTime;
		
		saveExperimentOutput(scenario, executionTime, inputModelFile.getName() , winningArguments);

	}
}