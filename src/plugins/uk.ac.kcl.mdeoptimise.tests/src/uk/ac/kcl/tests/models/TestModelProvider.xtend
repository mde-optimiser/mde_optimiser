package uk.ac.kcl.tests.models

import java.util.Collection
import java.util.Collections
import java.util.List
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import uk.ac.kcl.interpreter.IModelProvider

abstract class TestModelProvider implements IModelProvider {

	val ResourceSet resourceSet = new ResourceSetImpl

	override initialModels(EPackage metamodel) {
		resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)

		modelPaths.map [ p |
			loadModel(p)
		].iterator
	}

	def loadModel(String path) {
		val resource = resourceSet.createResource(URI.createURI(path))
		resource.load(Collections.EMPTY_MAP)
		resource.allContents.head
	}

	def writeModel(EObject model, String path) {
		val resource = resourceSet.createResource(URI.createURI(path))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}

	def storeModel(EObject model, String pathPrefix) {
		model.writeModel(pathPrefix + "/" + String.format("%08X", model.hashCode) + ".xmi"
		)
	}

	def storeModels(Collection<EObject> models, String pathPrefix) {
		models.forEach[m|m.storeModel(pathPrefix)]
	}

	abstract def List<String> getModelPaths()
}
