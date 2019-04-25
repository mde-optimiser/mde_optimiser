package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import com.google.common.io.Files
import java.io.File
import java.util.Collections
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors.ResultsDescriptor
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import java.nio.file.Path
import java.nio.file.Paths

class GeneratedMutationOperatorsDescriptor implements ResultsDescriptor {
	
	HenshinResourceSet resourceSet;
		
	override generateDescription(Path batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
	
		//Do nothing if there are no generated mutation operators
		if(batch.generatedRules.empty){
			return
		}
	
		var mutationGenerationpath = Paths.get(batchOutputPath.toAbsolutePath.toString, "mutation-operators/");	
		this.resourceSet = new HenshinResourceSet(mutationGenerationpath.toAbsolutePath.toString);
		
		var outputFile = new File(mutationGenerationpath.toAbsolutePath.toString)
		Files.createParentDirs(outputFile)
		
		var metamodel = batch.generatedRules.keySet.head
		var rules = batch.generatedRules.get(metamodel)
		
		for(var i = 0; i < rules.length; i++){
			
			val rule = rules.get(i);
			val modelPath = rule.name + ".henshin"
	
			//This may require a henshin resource set and a metamodel copied in the directory.		
			//Serialize the henshin file
			rule.writeModel(modelPath)
		}
		
		metamodel.writeModel(metamodel.name + ".ecore");
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
