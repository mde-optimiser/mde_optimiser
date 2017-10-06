package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import java.util.Collections
import org.eclipse.emf.common.util.URI
import java.nio.file.Paths
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import java.text.SimpleDateFormat
import java.util.Date

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
	
	def writeModel(EObject model, String path) {
		val resource = resourceSet.createResource(URI.createURI(path))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}
	
	/**
	 * Store model in the base path of the resource set.
	 * @param model which will be serialized
	 */
	def storeModel(EObject model, String projectPath) {
		val batchStartTime = new SimpleDateFormat("yyMMdd-HHmmss").format(new Date())
		val modelPath = projectPath + "/mdeo-results/experiment-" + batchStartTime + "/" + String.format("%08X", model.hashCode) + ".xmi"
		println("Saving results to: " + modelPath)
		model.writeModel(modelPath)
	}
	
}