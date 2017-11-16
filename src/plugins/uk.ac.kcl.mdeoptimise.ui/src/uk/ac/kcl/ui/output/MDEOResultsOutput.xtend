package uk.ac.kcl.ui.output

import java.util.Date
import java.util.List
import java.util.LinkedList
import org.eclipse.emf.ecore.resource.ResourceSet
import uk.ac.kcl.mdeoptimise.Optimisation
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.EObject
import java.util.Collections
import java.text.SimpleDateFormat
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import java.io.PrintWriter
import java.io.File
import org.eclipse.emf.common.util.URI
import org.eclipse.core.runtime.Path
import org.eclipse.core.runtime.IPath
import java.util.TimeZone
import java.util.HashMap

class MDEOResultsOutput {
	
	private Date experimentStartTime;
	private List<MDEOBatch> batches;
	private ResourceSet resourceSet;
	private String projectRootPath;
	private Optimisation moptConfiguration;
	
	new(Date startTime, String projectRootPath, Optimisation moptConfiguration){
		experimentStartTime = startTime
		//Store output of a batch experiment id, solutions set
		batches = new LinkedList<MDEOBatch>();
		this.resourceSet = new ResourceSetImpl();
		this.projectRootPath = projectRootPath;
		this.moptConfiguration = moptConfiguration;
	}
	
	def void logBatch(MDEOBatch batch){
		batches.add(batch);
	}
	
	
	def void outputBatchSummary(MDEOBatch batch, IPath outcomePath){
		var batchOutputPath = outcomePath.append(String.format("batch-%s/", batch.id))
		var batchInfoPath = batchOutputPath.append("outcome.txt")
		
		new File(batchOutputPath.toPortableString).mkdirs
		val batchWriter = new PrintWriter(new File(batchInfoPath.toPortableString))
		
		var batchDuration = new Date(batch.duration.longValue);
		
		var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		batchWriter.println("Batch duration: " + formatter.format(batchDuration.getTime()))
		batchWriter.println()
		batchWriter.println("============================================")
		for(var i = 0; i < batch.solutions.length; i++){
			
			val solution = batch.solutions.get(i);
			val modelPath = batchOutputPath + String.format("%08X", solution.model.hashCode) + ".xmi"
			
			solution.model.writeModel(modelPath)
			storeSolutionData(batchWriter, modelPath, solution)
		}
		
		batchWriter.close
	}
	
	def void outputExperimentSummary(List<MDEOBatch> batches, IPath outcomePath){
		
		
		//val averageTimeMiliseconds = lResults.fold(0.0, [acc, r|acc + r.timeTaken]) / lResults.size
		
		val averageTime = batches.fold(0.0, [acc, batch | acc + batch.duration])/batches.length
		val averageObjectiveValues = new HashMap<String, Double>();
		var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		for(var i = 0; i < batches.size; i++){

			val batch = batches.get(i);			
			val averageBatchObjectives = new HashMap<String, Double>();
			
			batch.solutions.forEach[solution | 
				solution.formattedObjectives.forEach[p1, p2| 
					if(averageBatchObjectives.containsKey(p1)) {
						averageBatchObjectives.put(p1, averageBatchObjectives.get(p1) + p2)
					} else {
						averageBatchObjectives.put(p1, p2)	
					}
				]
			]
			
			averageBatchObjectives.forEach[p1, p2 |
				if(averageObjectiveValues.containsKey(p1)){
					averageObjectiveValues.put(p1, averageObjectiveValues.get(p1) + p2)
				} else {
					averageObjectiveValues.put(p1, p2);	
				}
			]
		}
		
		val infoWriter = new PrintWriter(new File(outcomePath + "overall-results.txt"))
		
		infoWriter.println(String.format("Average experiment time: %s", formatter.format(averageTime)))
		infoWriter.println()
		averageObjectiveValues.forEach[p1, p2|
			infoWriter.println(String.format("Average value for %s objective: %s", p1, averageObjectiveValues.get(p1)/batches.size))
		]
		
		infoWriter.close
	}
	
	def void saveOutcome(){
		
		val experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(experimentStartTime);
		val outcomePath = new Path(projectRootPath).append(String.format("mdeo-results/experiment-%s/", experimentDate));
			
		batches.forEach[ batch | outputBatchSummary(batch, outcomePath)]
		outputExperimentSummary(batches, outcomePath)
	}
	
	def writeModel(EObject model, String path) {
		val resource = resourceSet.createResource(URI.createFileURI(path))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}

	private def storeSolutionData(PrintWriter infoWriter, String modelPath, MoeaOptimisationSolution solution){
		
		//val infoWriter = new PrintWriter(new File(infoPath + ".txt"))
		
		infoWriter.println("Evaluation data for solution: " + modelPath)
		infoWriter.println()
		infoWriter.println("Objective values:")
		
		//Pretty print the objectives
		var objectives = solution.formattedObjectives
		objectives.forEach[key, value | 
			infoWriter.println(String.format("%s: %s", key, value))
		]
		infoWriter.println("")

				//Pretty print the constraints
		var constraints = solution.formattedConstraints
		if(constraints.size > 0) {
			
			infoWriter.println("Constraint values:")
			constraints.forEach[key, value | 
				infoWriter.println(String.format("%s: %s", key, value))
			]
		}
		infoWriter.println()
		infoWriter.println("============================================")
		infoWriter.println()
	}
}