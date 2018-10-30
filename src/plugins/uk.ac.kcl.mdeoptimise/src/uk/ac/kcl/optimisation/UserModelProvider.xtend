package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import java.util.Collections
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.interpreter.IModelInitialiser
import uk.ac.kcl.interpreter.guidance.Solution
import java.util.LinkedList

class UserModelProvider implements IModelProvider {
	
	String modelPath
	HenshinResourceSet resourceSet;
	IModelInitialiser modelInitialiser;
	
	new (HenshinResourceSet resourceSet, String userModelPath){
		this.modelPath = userModelPath;
		this.resourceSet = resourceSet;
	}
	
	new (IModelInitialiser modelInitialiser, HenshinResourceSet resourceSet, String userModelPath) {
			this(resourceSet, userModelPath)
			this.modelInitialiser = modelInitialiser
	}

	def Solution loadModel(String path) {
		val resource = resourceSet.createResource(path)
		resource.load(Collections.EMPTY_MAP)
		resource.allContents.head
		
		if(this.modelInitialiser !== null){
			return modelInitialiser.initialise(resource.allContents.head)
		}
		
		return new Solution(resource.allContents.head, new LinkedList<String>)
	}

	override initialModels(EPackage metamodel) {
		resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)
		
		if(this.modelInitialiser !== null){
			
			return #[loadModel(modelPath)].iterator;
		}

		#[loadModel(modelPath)].iterator
	}
}