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

class MDEOResultsOutput {
	
	private Date experimentStartTime;
	private List<MDEOBatch> batches;
	private ResourceSet resourceSet;
	private String projectRootPath;
	
	new(Date startTime, String projectRootPath, Optimisation moptConfiguration){
		experimentStartTime = startTime
		//Store output of a batch experiment id, solutions set
		batches = new LinkedList<MDEOBatch>();
		this.resourceSet = new ResourceSetImpl();
		this.projectRootPath = projectRootPath;
	}
	
	def void logBatch(MDEOBatch batch){
		batches.add(batch);
	}
	
	
	def void outputBatchSummary(MDEOBatch batch, IPath outcomePath){
		var batchOutputPath = outcomePath.append(String.format("batch-%s/", batch.id))
		
		new File(batchOutputPath.toPortableString).mkdirs
		
		for(var i = 0; i < batch.solutions.length; i++){
			
			val solution = batch.solutions.get(i);
			val modelPath = batchOutputPath + String.format("%08X", solution.model.hashCode) + ".xmi"
			
			solution.model.writeModel(modelPath)
			storeSolutionData(modelPath, solution)
		}
	}
	
	def void outputExperimentSummary(List<MDEOBatch> batches){
		
	}
	
	def void saveOutcome(){
		
		val experimentDate = new SimpleDateFormat("yyMMdd-HHmmss").format(experimentStartTime);
		val outcomePath = new Path(projectRootPath).append(String.format("mdeo-results/experiment-%s/", experimentDate));
		
		batches.forEach[ batch | outputBatchSummary(batch, outcomePath)]
		outputExperimentSummary(batches)
	}
	
	def writeModel(EObject model, String path) {
		val resource = resourceSet.createResource(URI.createFileURI(path))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}

	private def storeSolutionData(String infoPath, MoeaOptimisationSolution solution){
		
		val infoWriter = new PrintWriter(new File(infoPath + ".txt"))
		
		infoWriter.println("Evaluation data for solution: " + infoPath)
		infoWriter.println()
		infoWriter.println("Objective values:")
		
		//Pretty print the objectives
		var objectives = solution.formattedObjectives
		objectives.forEach[key, value | 
			infoWriter.println(String.format("%s: %s", key, value))
		]
		infoWriter.println("")
		infoWriter.println("Constraint values:")
		
		//Pretty print the constraints
		var constraints = solution.formattedConstraints
		constraints.forEach[key, value | 
			infoWriter.println(String.format("%s: %s", key, value))
		]
		
		infoWriter.close
	}
}