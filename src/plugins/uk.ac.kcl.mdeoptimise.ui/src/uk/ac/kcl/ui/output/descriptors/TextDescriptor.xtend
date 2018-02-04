package uk.ac.kcl.ui.output.descriptors

import uk.ac.kcl.ui.output.MDEOBatch
import org.eclipse.core.runtime.IPath
import java.util.Date
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.TimeZone
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import com.google.common.io.Files
import java.io.File
import org.eclipse.emf.ecore.EObject
import java.util.Collections
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.common.util.URI
import java.nio.charset.Charset

class TextDescriptor implements ResultsDescriptor {
	
	private ResourceSet resourceSet;
	
	new(){
		this.resourceSet = new ResourceSetImpl();	
	}
	
	override generateDescription(IPath batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		var batchInfoPath = batchOutputPath.append(String.format("batch-%s-outcome.txt", batch.id))
		
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
			
			solution.model.writeModel(modelPath)
			storeSolutionData(batchWriter, modelPath, solution)
		}
		
		batchWriter.println()
		batchWriter.println("============================================")
		batchWriter.println()
		batchWriter.close
		
		batchOverallOutput.append(Files.toString(outputFile, Charset.defaultCharset()))
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
	
	def writeModel(EObject model, String path) {
		val resource = resourceSet.createResource(URI.createFileURI(path))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}
	
}