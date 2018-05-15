package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import java.util.Collections
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import java.util.List
import org.eclipse.emf.ecore.EObject
import com.google.common.collect.Iterables
import java.util.stream.Stream
import java.util.Iterator

class UserModelProvider implements IModelProvider {
	
	private Iterator<String> modelPaths
	private HenshinResourceSet resourceSet
	private Iterator<EObject> initialModels
	
	new (HenshinResourceSet resourceSet, List<String> userModelPaths){
		this.modelPaths = Iterables.cycle(userModelPaths).iterator;
		this.resourceSet = resourceSet;
	}
	
	def loadModel(String path) {
		val resource = resourceSet.createResource(path)
		resource.load(Collections.EMPTY_MAP)
		resource.allContents.head
	}

	override initialModels(EPackage metamodel) {
		if (initialModels === null) {
			resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)
			initialModels = Stream.generate([ | loadModel(modelPaths.next())]).iterator
		}		
		return initialModels
	}
}