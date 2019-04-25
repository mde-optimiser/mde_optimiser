package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.descriptors

import java.nio.file.Path
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.output.MDEOBatch
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.common.util.URI
import java.util.Collections
import java.nio.file.Paths

class MoptSpecSerialiser implements ResultsDescriptor {
	
	ResourceSet resourceSet;
	Path moptFile
	
	new(Path moptFile){
		this.resourceSet = new ResourceSetImpl();	
		this.moptFile = moptFile;
	}
	
	/**
	 * Serialise the MOPT specs to each of the experiment batch output folder.
	 */
	override generateDescription(Path batchPath, MDEOBatch batch, StringBuilder batchOverallOutput) {

		var moptSpec = batch.searchResult.optimisationSpec;
		
		var moptSpecOutputPath = Paths.get(batchPath.toAbsolutePath.toString, String.format("%s", getMoptFileName))
		
		writeModel(moptSpec, moptSpecOutputPath.toAbsolutePath.toString);
	}
	
	/**
	 * Get the file name to save serialise the mopt spec to.
	 * 
	 * @return string
	 */
	def getMoptFileName(){
		
		var String moptSpecFileName = "";
		
		if(this.moptFile !== null) {
			moptSpecFileName = this.moptFile.fileName.toString
			
			//Not sure if we can have this in production
			if(!moptSpecFileName.endsWith(".mopt")) {
				moptSpecFileName = moptSpecFileName + ".mopt";
			}
			
		} else {
			moptSpecFileName = "optimisation.mopt"
		}
		
		return moptSpecFileName;
	}
	
	//TODO This is used in several places. Make an utility class for model manipulation.
	def writeModel(EObject model, String path) {
		val resource = resourceSet.createResource(URI.createFileURI(path))
		
		if (resource.loaded) {
			resource.contents.clear
		}
		
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}
}