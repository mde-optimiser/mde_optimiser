package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils;

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.henshin.model.resource.HenshinResourceFactory;
import org.eclipse.emf.ecore.xmi.XMIResource;

import java.io.IOException;
import java.util.HashMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.henshin.model.Module;
import org.sidiff.common.henshin.HenshinModuleAnalysis;
import org.sidiff.serge.util.RuleSemanticsChecker;

public final class ExpectedRulesHelper {
	
	public static Module loadModule(MetamodelLoader metamodelLoader, String moduleName) {
		var eObject = metamodelLoader.getResourceSet().getModule(String.format("expected/%s.henshin", moduleName));
		return eObject;
	}
	
	public static boolean isEqual(Module left, Module right) {
		var leftRule = HenshinModuleAnalysis.getAllRules(left).get(0);
		var rightRule = HenshinModuleAnalysis.getAllRules(right).get(0);
	
		return new RuleSemanticsChecker(leftRule, rightRule).isEqual();
	}

  public static void writeModel(EObject model, String modelPathPrefix, String modelFilename) {
    	
    	var resourceSet = new HenshinResourceSet(modelPathPrefix);
    	
    	var reg = Resource.Factory.Registry.INSTANCE;
		var m = reg.getExtensionToFactoryMap();
		m.put("henshin", new HenshinResourceFactory());
    	
		var options = new HashMap<String, Boolean>();
		options.put(XMIResource.OPTION_SCHEMA_LOCATION, true);
	
		var resource = resourceSet.createResource(URI.createFileURI(modelFilename));
		
		if (resource.isLoaded()) {
			resource.getContents().clear();
		}
		
		resource.getContents().add(model);
    try {
      resource.save(options);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
