package uk.ac.kcl.ui.output

import com.google.common.io.Files
import java.io.File
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import java.util.TimeZone
import org.eclipse.core.runtime.IPath
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.ui.output.descriptors.ResultsDescriptor
import uk.ac.kcl.ui.output.descriptors.TextDescriptor
import uk.ac.kcl.ui.output.descriptors.MOEAObjectivesOutputDescriptor
import uk.ac.kcl.ui.output.descriptors.ParetoChartOutputDescriptor
import uk.ac.kcl.ui.output.descriptors.BatchReportsDescriptor
import uk.ac.kcl.ui.output.descriptors.GeneratedMutationOperatorsDescriptor
import uk.ac.kcl.ui.output.descriptors.ExperimentCSVSerializer
import uk.ac.kcl.ui.output.descriptors.AccumulatorSerialiser

class MDEOResultsOutput {
	
	Date experimentStartTime;
	List<MDEOBatch> batches;
	IPath projectRoot;
	IPath moptFile;
	Optimisation moptConfiguration;
	List<ResultsDescriptor> resultsDescriptors;
	boolean classicRuleMatchingEnabled;
	
	new(Date startTime, IPath projectRoot, IPath moptFile, Optimisation moptConfiguration){
		this(startTime, projectRoot, moptFile, moptConfiguration, false)
	}
	
	new(Date startTime, IPath projectRoot, IPath moptFile, Optimisation moptConfiguration, boolean classicRuleMatchingEnabled){
		experimentStartTime = startTime
		//Store output of a batch experiment id, solutions set
		batches = new LinkedList<MDEOBatch>();
		this.projectRoot = projectRoot;
		this.moptConfiguration = moptConfiguration;
		this.moptFile = moptFile;
		this.resultsDescriptors = loadDescriptors();
		this.classicRuleMatchingEnabled = classicRuleMatchingEnabled;
	}
	
	def void logBatch(MDEOBatch batch){
		batches.add(batch);
	}

	def void saveOutcome(){
		this.saveOutcome(null)
	}

	def void saveOutcome(Integer customBatch){
		
		val experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(experimentStartTime);
		val outcomePath = projectRoot.append(String.format("mdeo-results/experiment-%s-%s-matching-%s/", 
			moptFile.lastSegment, experimentDate, this.matchingType
		));
		
		//Used to generate the experiments summary	
		val batchesOutput = new StringBuilder();
		
		batches.forEach[ batch | 
		
			batchesOutput.append("============================================")
			batchesOutput.append(System.getProperty("line.separator"));
			
			val batchOutputPath = outcomePath.append(String.format("batch-%s/", batch.id))
			
			this.resultsDescriptors.forEach[ descriptor |
				descriptor.generateDescription(batchOutputPath, batch, batchesOutput);
			]
		
			batchesOutput.append("============================================")
			batchesOutput.append(System.getProperty("line.separator"));
		]
	
		if(customBatch === null) {
			outputExperimentSummary(batches, outcomePath, moptFile, batchesOutput)
		}	
	}
	
	def void outputExperimentSummary(List<MDEOBatch> batches, IPath outcomePath, IPath moptFile, StringBuilder batchesOutput){
		
		val averageTime = batches.fold(0.0, [acc, batch | acc + batch.duration])/batches.length
		val averageObjectiveValues = new HashMap<String, Double>();
		var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		for(var i = 0; i < batches.size; i++){

			val batch = batches.get(i);			
			val sumBatchObjectives = new HashMap<String, Double>();
			
			batch.solutions.forEach[solution | 
				solution.formattedObjectives.forEach[p1, p2| 
					if(sumBatchObjectives.containsKey(p1)) {
						sumBatchObjectives.put(p1, sumBatchObjectives.get(p1) + p2)
					} else {
						sumBatchObjectives.put(p1, p2)	
					}
				]
			]
			
			sumBatchObjectives.forEach[p1, p2 |
				if(averageObjectiveValues.containsKey(p1)){
					averageObjectiveValues.put(p1, averageObjectiveValues.get(p1) + p2 / batch.solutions.size)
				} else {
					averageObjectiveValues.put(p1, p2);	
				}
			]
		}
				
		val infoWriter = new PrintWriter(new File(outcomePath + "overall-results.txt"))
		
		
		infoWriter.println(String.format("Average experiment time: %s", formatter.format(averageTime)))
		infoWriter.println()
		
		//If configuration has one objective only then show an average
		//if(batches.head.solutions.head.objectives.length == 1){
			averageObjectiveValues.forEach[p1, p2|
				infoWriter.println(String.format("Average value for %s objective: %s", p1, averageObjectiveValues.get(p1)/batches.size))
			]
		//}
		
		infoWriter.println(batchesOutput.toString)
		
		infoWriter.close
		
		if(!moptFile.empty){
			Files.copy(new File(moptFile.toPortableString), 
				new File(outcomePath.append(moptFile.lastSegment).toPortableString))
		}
	}
	
	def String getMatchingType(){
		if(this.classicRuleMatchingEnabled){
			return "classic"
		}
		return "henshin"
	}
	
	/**
	 * Load a set of descriptors that we would like to run on the produced experiments data.
	 * They perform tasks such as generating parseable results, serializing models and generated
	 * mutation operators.
	 */
	private def List<ResultsDescriptor> loadDescriptors(){
		
		var descriptors = new ArrayList<ResultsDescriptor>();
		
		descriptors.add(new TextDescriptor());
		descriptors.add(new MOEAObjectivesOutputDescriptor())
		descriptors.add(new ParetoChartOutputDescriptor())
		descriptors.add(new BatchReportsDescriptor(this.moptConfiguration))
		descriptors.add(new GeneratedMutationOperatorsDescriptor())
		//descriptors.add(new HypervolumeDescriptor())
		descriptors.add(new ExperimentCSVSerializer())
		descriptors.add(new AccumulatorSerialiser())
		
		return descriptors;
		
	}
}