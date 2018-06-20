package uk.ac.kcl.mdeoptimise.rulegen.tests.utils

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
import org.eclipse.emf.ecore.xmi.XMIResource
import java.util.HashMap
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.henshin.model.Module

final class ExpectedRulesHelper {

	private static HenshinResourceSet resourceSet = new HenshinResourceSet("src/resources/expected/");

	def static EObject loadRule(String moduleName){
	
		val eObject = resourceSet.getEObject(String.format("%s.henshin", moduleName))
		return eObject;
	}
	
	def static Module loadAddRule(String moduleName){
		return loadModule("add", moduleName);
				
	}
	
	def static Module loadRemoveRule(String moduleName){
		return loadModule("remove", moduleName);
	}
	
	def static Module loadCreateRule(String moduleName){
		return loadModule("create", moduleName)
	}
	
	def static Module loadModule(String prefix, String moduleName) {
		val eObject = resourceSet.getEObject(String.format("%s/%s.henshin", prefix, moduleName))
		return eObject as Module
	}
	
	def static writeModel(EObject model, String modelPathPrefix, String modelFilename) {
    	
    	var reg = Resource.Factory.Registry.INSTANCE;
		var m = reg.getExtensionToFactoryMap();
		m.put("henshin", new HenshinResourceFactory());
    	
		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
		
		var resourceSet = new HenshinResourceSet(modelPathPrefix);
		
		
		val resource = resourceSet.createResource(URI.createFileURI(modelFilename))
		
		if (resource.loaded) {
			resource.contents.clear
		}
		
		resource.contents.add(model)
		resource.save(options)
	}
}