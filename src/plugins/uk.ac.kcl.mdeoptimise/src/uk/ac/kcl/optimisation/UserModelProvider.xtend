package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import java.util.Collections
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet

class UserModelProvider implements IModelProvider {
	
	private String modelPath
	private HenshinResourceSet resourceSet;
	
	new (HenshinResourceSet resourceSet, String userModelPath){
		this.modelPath = userModelPath;
		this.resourceSet = resourceSet;
	}
	
	def loadModel(String path) {
		val resource = resourceSet.createResource(path)
		resource.load(Collections.EMPTY_MAP)
		resource.allContents.head
	}

	override initialModels(EPackage metamodel) {
		resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)

		#[loadModel(modelPath)].iterator
	}
}