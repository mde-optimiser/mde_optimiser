package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import java.util.Collections
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.interpreter.IModelInitialiser

class UserModelProvider implements IModelProvider {
	
	private String modelPath
	private HenshinResourceSet resourceSet;
	private IModelInitialiser modelInitialiser;
	
	new (HenshinResourceSet resourceSet, String userModelPath){
		this.modelPath = userModelPath;
		this.resourceSet = resourceSet;
	}
	
	new (IModelInitialiser modelInitialiser, HenshinResourceSet resourceSet, String userModelPath) {
			this(resourceSet, userModelPath)
			this.modelInitialiser = modelInitialiser
	}

	def loadModel(String path) {
		val resource = resourceSet.createResource(path)
		resource.load(Collections.EMPTY_MAP)
		resource.allContents.head
		
		if(this.modelInitialiser != null){
			return modelInitialiser.initialise(resource.allContents.head)
		}
		
		return resource.allContents.head
	}

	override initialModels(EPackage metamodel) {
		resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)
		
		if(this.modelInitialiser != null){
			return #[loadModel(modelPath)].iterator;
		}

		#[loadModel(modelPath)].iterator
	}
}