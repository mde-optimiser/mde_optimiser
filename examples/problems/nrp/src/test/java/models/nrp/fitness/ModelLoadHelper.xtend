package models.nrp.fitness

import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import com.google.common.io.Resources

class ModelLoadHelper {
	
	def static EObject loadModel(String filename) {
		val HenshinResourceSet resourceSet = new HenshinResourceSet("src/test/resources");
		
		val metamodelPath = Resources.getResource("models/nrp/nextReleaseProblem.ecore")

		resourceSet.registerDynamicEPackages(metamodelPath.path).get(0);
		resourceSet.getResource(filename).getContents().get(0);
	}
}