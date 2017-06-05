package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import java.util.Collections
import org.eclipse.emf.common.util.URI
import java.nio.file.Paths

class UserModelProvider implements IModelProvider {
	
	private String modelPath
	
	new (String basepath, String userModelPath){
		this.modelPath = Paths.get(basepath, userModelPath).toString
	}
	
	val ResourceSet resourceSet = new ResourceSetImpl

	def loadModel(String path) {
		val resource = resourceSet.createResource(URI.createURI(path))
		resource.load(Collections.EMPTY_MAP)
		resource.allContents.head
	}

	override initialModels(EPackage metamodel) {
		resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)

		#[loadModel(modelPath)].iterator
	}
}