package argumentation.scenario.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.tweety.arg.dung.DungTheory;
import net.sf.tweety.arg.dung.syntax.Argument;


public class BruteForceSearcherUncappedExecution extends BruteForceSearcher {
	
	public BruteForceSearcherUncappedExecution(Scenario start){
		super(start);
	}
	
	//Patch the outcome to also return time taken to find solutions.
	public LinkedHashMap<Map.Entry<Long, List<Argument>>, Integer> findBestArgumentsToAssertUncapped(long startTime) throws IOException{
		
		ArrayList<Argument> args = new ArrayList<Argument>(persuaderAF.getNodes());
		
		if (args.size() > 30) {
			throw new InvalidObjectException("Invalid number of arguments given. The power set cannot be generated");
		}
		
		ArrayList<ArrayList<Argument>> allPossibleAssertions = getSubsets(args);
		
		ArrayList<Argument> best = null;
		LinkedHashMap<Map.Entry<Long, List<Argument>>, Integer> outcome = new LinkedHashMap<Map.Entry<Long, List<Argument>>, Integer>();
		int mostConvinced = 0;

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
			}
			if (convinced > mostConvinced){
				mostConvinced = convinced;
				best = possibleAssertion;
				
				System.out.println("Convinced: " + convinced);
				
				Map.Entry<Long, List<Argument>> bestWithTime = 
						new AbstractMap.SimpleEntry<Long, List<Argument>>(System.nanoTime() - startTime, best);
				
				outcome.put(bestWithTime, convinced);
			}
		}
		
		return outcome;
	}
	
	public List<String> serialiseArgumentsUncapped(List<Argument> arguments){
		List<String> serializedWinningArguments = new ArrayList<String>();
			
		for(Argument argument : arguments) {
			serializedWinningArguments.add(argument.toString());
		}
	
		return serializedWinningArguments;
	}
	
	public void saveExperimentOutputUncapped(Scenario scenario, long executionTime, 
			String path, LinkedHashMap<Map.Entry<Long, List<Argument>>, Integer> solutionsList) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyMMdd-HHmmss").format(new java.util.Date());
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Gson gson = new GsonBuilder().create();

		//Prepare output
		Date date = new Date((executionTime) / 1000000); // if you really have long
		String totalRuntime = formatter.format(date.getTime()) ;
		
		List<LinkedHashMap<String, String>> dataRows = new ArrayList<LinkedHashMap<String, String>>();
		
		
		for(Map.Entry<Long, List<Argument>> rowId : solutionsList.keySet()) {
			LinkedHashMap<String, String> row = new LinkedHashMap<String, String>();
			
			Date intermediateRuntime = new Date((rowId.getKey()) / 1000000); // if you really have long
			
			row.put("run", "0");
			row.put("runtime", formatter.format(intermediateRuntime.getTime()));
			row.put("persuaded", solutionsList.get(rowId).toString());
			row.put("assertedArguments", gson.toJson(serialiseArgumentsUncapped(rowId.getValue())));
			
			dataRows.add(row);
		}
		
		//save pretty text
		saveTxtUncapped(path, timeStamp, totalRuntime, dataRows);
		
		//save csv
		saveCsvUncapped(path, timeStamp, totalRuntime, dataRows);
		
	}
	
	public void saveTxtUncapped(String path, String timestamp, String runtime, List<LinkedHashMap<String, String>> csvRows) throws IOException {
		
		File file = new File(String.format("mdeo-results/bf-%s-%s/%s-%s-output.txt", path, timestamp, path, timestamp));
		
		file.getParentFile().mkdirs();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		pw.println(String.format("Running experiment with input model: %s", path));

		if(csvRows.size() > 0) {
			for(LinkedHashMap<String, String> key : csvRows) {
				pw.println(String.format("Intermediate runtime: %s", key.get("runtime")));
				pw.println(String.format("Persuaded audience members: %s", key.get("persuaded")));
				pw.println(String.format("Winning arguments: %s", key.get("assertedArguments")));
			}
		} else {
			pw.println("No winning arguments found for this problem.");
		}
			
		pw.println(String.format("Total experiment duration: %s", runtime));
		pw.close();
		fw.close();

	}
	
	public void saveCsvUncapped(String path, String timeStamp, String runtime, List<LinkedHashMap<String, String>> csvRows) throws IOException {
		
		File csvFile = new File(String.format("mdeo-results/bf-%s-%s/%s-%s-output.csv", path, timeStamp, path, timeStamp));
		csvFile.getParentFile().mkdirs();
		
		csvFile.getParentFile().mkdirs();
		
		FileWriter fw = new FileWriter(csvFile);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("run,runtime,persuaded,assertedArguments");
		
		for(LinkedHashMap<String, String> row : csvRows) {
			pw.println(Joiner.on(",").join(row.values().iterator()));
		}
		
		pw.close();
		fw.close();
	}
	
	public static List<File> loadInputModels(String path) {
		
		File bruteForceUncappedInputModels = new File(path);
		
		File[] inputModelsArray = bruteForceUncappedInputModels.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.endsWith(".xmi");
			}
			
		});
		
		List<File> inputModelsList = Arrays.asList(inputModelsArray);
		Collections.sort(inputModelsList);
		
		return inputModelsList;
	}
	
	public static void main(String[] args) throws IOException{
		
		String path = "src/main/resources/models/argumentation/brute-force-uncapped/";
		
		List<File> inputModelFiles = loadInputModels(path);
		
		int fileIndex = 1;
		for(File inputModelFile : inputModelFiles) {
			
			System.out.println(String.format("Processing %s - Model %s out of %s.", 
					inputModelFile.getName(),fileIndex++, inputModelFiles.size()));
			
			Scenario scenario = loadScenario(inputModelFile);
			
			long startTime = System.nanoTime();
			BruteForceSearcherUncappedExecution bruteForceRunner = new BruteForceSearcherUncappedExecution(scenario);
			LinkedHashMap<Map.Entry<Long, List<Argument>>, Integer> winningArguments = bruteForceRunner.findBestArgumentsToAssertUncapped(startTime);
			long stopTime = System.nanoTime();
			long executionTime = stopTime - startTime;
			
			bruteForceRunner.saveExperimentOutputUncapped(scenario, executionTime, inputModelFile.getName() , winningArguments);
			
		}
	}
}