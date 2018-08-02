package uk.ac.kcl.ui.output.descriptors

import com.google.common.io.Files
import java.io.File
import java.util.Collections
import org.eclipse.core.runtime.IPath
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.ui.output.MDEOBatch

class GeneratedMutationOperatorsDescriptor implements ResultsDescriptor {
	
	private HenshinResourceSet resourceSet;
		
	override generateDescription(IPath batchOutputPath, MDEOBatch batch, StringBuilder batchOverallOutput) {
	
		//Do nothing if there are no generated mutation operators
		if(batch.generatedRules.empty){
			return
		}
	
		var mutationGenerationpath = batchOutputPath.append("mutation-operators/");	
		this.resourceSet = new HenshinResourceSet(mutationGenerationpath.toPortableString);
		
		var outputFile = new File(mutationGenerationpath.toPortableString)
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