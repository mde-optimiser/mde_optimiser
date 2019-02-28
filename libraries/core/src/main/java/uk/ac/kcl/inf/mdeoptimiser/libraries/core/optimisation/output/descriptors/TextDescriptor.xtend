package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import com.google.common.io.Files
import java.io.PrintWriter
import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths
import java.text.SimpleDateFormat
import java.util.Collections
import java.util.Date
import java.util.TimeZone
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch

class TextDescriptor implements ResultsDescriptor {
	
	ResourceSet resourceSet;
	
	new(){
		this.resourceSet = new ResourceSetImpl();	
	}
	
	override generateDescription(Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
		
		var batchInfoPath = Paths.get(batchOutputPath.toAbsolutePath.toString, String.format("batch-%s-outcome.txt", batch.id))
		
		var outputFile = batchInfoPath.toAbsolutePath.toFile
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
			
			solution.model.model.writeModel(modelPath)
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
		
		infoWriter.println("")
		
		var transformations = solution.model.transformationsChain
		if(transformations.length > 0) {
			infoWriter.println("Transformations chain:")
			infoWriter.println(String.format("Length: %s", transformations.length))
			infoWriter.println(String.format("Sequence: %s", transformations.join(" -> ")))
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
