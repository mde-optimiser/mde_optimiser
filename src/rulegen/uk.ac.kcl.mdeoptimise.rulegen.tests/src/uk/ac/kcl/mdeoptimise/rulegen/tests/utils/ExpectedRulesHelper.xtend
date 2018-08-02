package uk.ac.kcl.mdeoptimise.rulegen.tests.utils

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory
import org.eclipse.emf.ecore.xmi.XMIResource
import java.util.HashMap
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.henshin.model.Module
import org.sidiff.common.henshin.HenshinModuleAnalysis
import org.sidiff.serge.util.RuleSemanticsChecker

final class ExpectedRulesHelper {
	
	def static dispatch Module loadModule(MetamodelLoader metamodelLoader, String moduleName) {
		val eObject = metamodelLoader.getResourceSet.getModule(String.format("expected/%s.henshin", moduleName))
		return eObject
	}
	
	def static boolean isEqual(Module left, Module right) {
		var leftRule = HenshinModuleAnalysis.getAllRules(left).head
		var rightRule = HenshinModuleAnalysis.getAllRules(right).head
	
		return new RuleSemanticsChecker(leftRule, rightRule).isEqual()
	}
	
	def static writeModel(EObject model, String modelPathPrefix, String modelFilename) {
    	
    	var resourceSet = new HenshinResourceSet(modelPathPrefix);
    	
    	var reg = Resource.Factory.Registry.INSTANCE;
		var m = reg.getExtensionToFactoryMap();
		m.put("henshin", new HenshinResourceFactory());
    	
		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
	
		val resource = resourceSet.createResource(URI.createFileURI(modelFilename))
		
		if (resource.loaded) {
			resource.contents.clear
		}
		
		resource.contents.add(model)
		resource.save(options)
	}
}