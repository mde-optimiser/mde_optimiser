package uk.ac.kcl.ui.output

import com.google.common.io.Files
import java.io.File
import java.io.PrintWriter
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.Date
import java.util.HashMap
import java.util.LinkedList
import java.util.List
import java.util.Map
import java.util.TimeZone
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.XMIResource
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution

class MDEOResultsOutput {
	
	private Date experimentStartTime;
	private List<MDEOBatch> batches;
	private ResourceSet resourceSet;
	private IPath projectRoot;
	private IPath moptFile;
	private Optimisation moptConfiguration;
	
	new(Date startTime, IPath projectRoot, IPath moptFile, Optimisation moptConfiguration){
		experimentStartTime = startTime
		//Store output of a batch experiment id, solutions set
		batches = new LinkedList<MDEOBatch>();
		this.resourceSet = new ResourceSetImpl();
		this.projectRoot = projectRoot;
		this.moptConfiguration = moptConfiguration;
		this.moptFile = moptFile;
	}
	
	def void logBatch(MDEOBatch batch){
		batches.add(batch);
	}
	
	def String outputBatchSummary(MDEOBatch batch, IPath outcomePath, IPath metaModelOutputPath) {
		var batchOutputPath = outcomePath.append(String.format("batch-%s/", batch.id))
		var batchInfoPath = batchOutputPath.append("outcome.txt")
		
		var outputFile = new File(batchInfoPath.toPortableString)
		Files.createParentDirs(outputFile)
		
		val batchWriter = new PrintWriter(outputFile)
		
		var batchDuration = new Date(batch.duration.longValue);
		
		var formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		batchWriter.println()
		batchWriter.println("============================================")
		batchWriter.println()
		batchWriter.println(String.format("Batch %s duration: %s", batch.id, formatter.format(batchDuration.getTime())))
		batchWriter.println()

		batchWriter.println("--------------------------------------------")
		
		for(var i = 0; i < batch.solutions.length; i++){
			
			val solution = batch.solutions.get(i);
			val modelPath = batchOutputPath + String.format("%08X", solution.model.hashCode) + ".xmi"
			
			solution.model.writeModel(modelPath, metaModelOutputPath.toPortableString)
			storeSolutionData(batchWriter, modelPath, solution)
		}
		
		batchWriter.println()
		batchWriter.println("============================================")
		batchWriter.println()
		batchWriter.close
		
		return Files.toString(outputFile, Charset.defaultCharset())
	}
	
	def void outputExperimentSummary(List<MDEOBatch> batches, IPath outcomePath, IPath moptFile, StringBuilder batchesOutput){
		
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
			infoWriter.println(String.format("Average value for %s objective: %s", p1, -1 * averageObjectiveValues.get(p1)/batches.size))
		]
		
		infoWriter.println(batchesOutput.toString)
		
		infoWriter.close
		
		if(!moptFile.empty){
			Files.copy(new File(moptFile.toPortableString), 
				new File(outcomePath.append(moptFile.lastSegment).toPortableString))
		}
	}
	
	def IPath copyMetaModel(IPath outcomePath){
		val metaModelInputPath = projectRoot.append(moptConfiguration.basepath.location).append(moptConfiguration.metamodel.location)
		val metaModelOutputPath = outcomePath.append(metaModelInputPath.lastSegment)
		
		if (!metaModelInputPath.empty) {
			val outputFile = metaModelOutputPath.toFile
			Files.createParentDirs(outputFile)
			Files.copy(metaModelInputPath.toFile, outputFile)
		}
		return metaModelOutputPath
	}
	
	def void saveOutcome(){
		
		val experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(experimentStartTime);
		val outcomePath = projectRoot.append(String.format("mdeo-results/experiment-%s/", experimentDate));
				
		val metaModelOutputPath = copyMetaModel(outcomePath)
		
		val batchesOutput = new StringBuilder();
		
		batches.forEach[ batch | batchesOutput.append(outputBatchSummary(batch, outcomePath, metaModelOutputPath))]
		
		outputExperimentSummary(batches, outcomePath, moptFile, batchesOutput)
	}
	
	def writeModel(EObject model, String path, String metaModelPath) {
		val resource = resourceSet.createResource(URI.createFileURI(new File(path).absolutePath))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		
		// Change URI of model package to reference the meta model copied to output location
		model.eClass.EPackage.eResource.URI = URI.createFileURI(metaModelPath)
				
		// Keep schema location in output models
		val Map<Object,Object> options = new HashMap<Object,Object>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
		
		resource.save(options)
	}

	private def storeSolutionData(PrintWriter infoWriter, String modelPath, MoeaOptimisationSolution solution){
		
		infoWriter.println("Evaluation data for solution: " + modelPath)
		infoWriter.println()
		infoWriter.println("Objective values:")
		
		//Pretty print the objectives
		var objectives = solution.formattedObjectives
		objectives.forEach[key, value | 
			infoWriter.println(String.format("%s: %s", key, -1 * value))
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
	}
}