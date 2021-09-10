package argumentation.scenario.generator;

import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

public class ModelLoader {
	
	public HenshinResourceSet getResourceSet(String resourceSetPath) {

		return new HenshinResourceSet(resourceSetPath);
	}
	
	public EObject loadModel(String resourceSetPath, String modelFilePath) {
		
		Resource resource = getResourceSet(resourceSetPath).createResource(URI.createURI(modelFilePath));
		
		try {
			
			resource.load(Collections.EMPTY_MAP);
			return resource.getAllContents().next();
		
		}catch (Exception e) {
			
		}
	
		return null;
	}
}