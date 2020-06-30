package argumentation.scenario.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.argumentation.fitness.EcoreScenarioParser;
import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.PreferredReasoner;
import net.sf.tweety.arg.dung.parser.FileFormat;
import net.sf.tweety.arg.dung.prover.ProboSolver;
import net.sf.tweety.arg.dung.semantics.Problem;
import net.sf.tweety.arg.dung.syntax.Argument;
import net.sf.tweety.arg.dung.syntax.Attack;
import net.sf.tweety.arg.dung.writer.DungWriter;
import net.sf.tweety.commons.util.Shell;
import net.sf.tweety.logics.pl.sat.Sat4jSolver;
import net.sf.tweety.logics.pl.sat.SatSolver;


public class RandomAsserter {
	
	DungTheory persuaderAF;
	List<DungTheory> audienceAFs;
	Argument topic;
	
	public RandomAsserter(Scenario start){
		SatSolver.setDefaultSolver(new Sat4jSolver());
		
		persuaderAF = start.getPersuadersFramework();
		audienceAFs = start.getPersuadeeArgumentationFrameworks();
		topic = start.getTopic();
	}

	public Pair<Integer, List<Argument>> randomlyAssert(int argLimit) throws IOException{
		ArrayList<Argument> allArgs = new ArrayList<Argument>(persuaderAF.getNodes());
		
		Collections.shuffle(allArgs);
		ArrayList<Argument> toAssert = new ArrayList<Argument>( allArgs.subList(0, argLimit) );
		
		int convinced = 0;
		for (DungTheory persuadee : audienceAFs){
			if (topicAcceptableWithAssertions(persuadee, toAssert)){
				convinced++;
			}
		}
		
		return  Pair.of(convinced, toAssert);
	}
	
	private boolean topicAcceptableWithAssertions(DungTheory persuadee, ArrayList<Argument> possibleAssertion) throws IOException {
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

	
	private boolean justify(ProboSolver solver, Problem problem, DungTheory aaf, FileFormat format, Argument arg) throws IOException {
		
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
	
	public static Scenario loadScenario(File inputModelFile) {
		
		ModelLoader modelLoader = new ModelLoader();
		
		EObject model = modelLoader.loadModel(inputModelFile.getParent(), inputModelFile.getName());
		
		return new EcoreScenarioParser(model).getScenario();
	}
	
	public static String serializeArguments(List<Argument> arguments) {
		
		List<String> serializedArguments = new ArrayList<String>();
		
		for(Argument argument : arguments) {
			serializedArguments.add(argument.toString());
		}
	
		Gson gson = new GsonBuilder().create();
		return gson.toJson(serializedArguments);
	}
	
	public static void saveExperimentOutput(Scenario scenario, long executionTime, String path, 
			ArrayList<LinkedHashMap<String, String>> rows, Pair<Integer, List<Argument>> bestRun) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyMMdd-HHmmss").format(new java.util.Date());
		
		File file = new File(String.format("mdeo-results/ra-%s-%s/%s-%s-output.txt", path, timeStamp, path, timeStamp));
		
		file.getParentFile().mkdirs();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(String.format("Running experiment with input model: %s", path));
		int persuaded = 0;
		
		if(bestRun != null) {
		
			persuaded = bestRun.getLeft();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String result = gson.toJson(serializeArguments(bestRun.getRight()));
			if(bestRun.getLeft() > 0) {
					pw.println(String.format("Persuaded audience members: %s", bestRun.getLeft()));
					pw.println(String.format("Winning arguments: %s", result));
			} 
		} else {
			pw.println("No winning arguments found in any of the runs.");
		}	

		Date date = new Date((executionTime) / 1000000); // if you really have long
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		String runtime = formatter.format(date.getTime()) ;
		
		saveCsv(path, timeStamp, runtime, persuaded, rows);
		
		pw.println(String.format("Experiment duration: %s", runtime));
		pw.close();
		fw.close();
	}
	
	public static void saveCsv(String path, String timeStamp, String runtime, 
				int persuaded, ArrayList<LinkedHashMap<String, String>> rows) throws IOException {
		
		File csvFile = new File(String.format("mdeo-results/ra-%s-%s/%s-%s-output.csv", path, timeStamp, path, timeStamp));
		
		csvFile.getParentFile().mkdirs();
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		FileWriter fw = new FileWriter(csvFile);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("run,runtime,persuaded,assertedArgumentsCount,assertedArguments,inputModel");
		
		for(LinkedHashMap<String, String> row : rows) {
			
			long runtimeMilis = Long.parseLong(row.get("runtime"));
			
			Date date = new Date((runtimeMilis) / 1000000); // if you really have long

			row.put("runtime", formatter.format(date.getTime()));
			pw.println(Joiner.on(",").join(row.values().iterator()));
		}
		
		pw.close();
		fw.close();
	}
	
	public static void main(String[] args) throws IOException{
		String modelPath = "src/main/resources/models/argumentation/PF-Cycle-11-args-Audience-2-Members-PAF-size-4-args.xmi";
		
		//If we passed another parameter, then use that path instead.
		if(args.length == 1) {
			modelPath = args[0];
		}
	
		File inputModelFile = new File(modelPath);
		
		Scenario scenario = loadScenario(inputModelFile);
		
		ArrayList<LinkedHashMap<String, String>> rows = new ArrayList<LinkedHashMap<String, String>>();
		
		Random random = new Random();
		
		long totalExecutionTime = 0;
		
		Pair<Integer, List<Argument>> bestRun = null;
		
		for(int i = 0; i < 100; i++) {
			
			LinkedHashMap<String, String> runOutcome = new LinkedHashMap<String, String>();
			
			long startTime = System.nanoTime();
			
			RandomAsserter r = new RandomAsserter(scenario);
			
			int assertedAlgorithms = random.nextInt(scenario.persuadersFramework.size());
			
			Pair<Integer, List<Argument>> outcome = r.randomlyAssert(assertedAlgorithms);
			
			long stopTime = System.nanoTime();
			long executionTime = stopTime - startTime;
			
			totalExecutionTime += executionTime;
			
			runOutcome.put("run", String.valueOf(i));
			runOutcome.put("runtime", String.valueOf(executionTime));
			runOutcome.put("persuaded", String.valueOf(outcome.getKey()));
			runOutcome.put("assertedArgumentsCount", String.valueOf(outcome.getValue().size()));
			runOutcome.put("assertedArguments", serializeArguments(outcome.getValue()).replace(",", "|"));
			runOutcome.put("inputModel", inputModelFile.getName());
			
			rows.add(runOutcome);
			
			if(bestRun != null) {
				if(bestRun.getLeft() < outcome.getLeft()) {
					bestRun = outcome;
				}
			} else {
				bestRun = outcome;
			}
		}
		
		saveExperimentOutput(scenario, totalExecutionTime, inputModelFile.getName(), rows, bestRun);
		
	}
}
