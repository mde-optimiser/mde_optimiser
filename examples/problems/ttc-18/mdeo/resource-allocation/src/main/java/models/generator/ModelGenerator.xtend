package models.generator

import com.google.common.io.Resources
import com.google.inject.Inject
import com.google.inject.Injector
import java.io.File
import java.io.FileReader
import java.io.IOException
import java.io.InvalidObjectException
import java.io.Reader
import java.util.Collections
import java.util.HashMap
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.xtext.parser.IParser
import org.xtext.example.mydsl.MyDslStandaloneSetup
import com.google.common.io.Files
import org.eclipse.xtext.resource.XtextResourceSet
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.XtextPackage
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.EcoreFactory
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.emf.ecore.resource.Resource
import org.xtext.example.mydsl.myDsl.Model

class ModelGenerator {

	static val Injector injector = new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();
	 
    @Inject
    private IParser parser;
    
 	val ResourceSet resourceSet = new ResourceSetImpl
 	
 	static def void main(String[] args) {
		
		val originalModelPath = "models/resource/original/models/"
		val serialisedModelPathPrefix = "src/main/resources/models/resource/allocation/"

		val app = injector.getInstance(ModelGenerator)

		
		var originalModelFiles = app.loadJastAddModels(originalModelPath)


		originalModelFiles.fold(new HashMap<File, EObject>, [map, file | map.put(file, app.parse(new FileReader(file))) return map]).forEach[
			file, model | app.writeModel(model, String.format("%s/%s.xmi", serialisedModelPathPrefix, Files.getNameWithoutExtension(file.name)))
		]
	}
 	
    def EObject parse(Reader reader) throws IOException {
        var result = parser.parse(reader)
		
        if(result.hasSyntaxErrors()) {
            throw new InvalidObjectException("The original model has syntax errors.");
        }


        return result.getRootASTElement();
    }
	
	def writeModel(EObject model, String path) {
		
		
		EcoreUtil2.resolveAll(model);
		
		val resource = resourceSet.createResource(URI.createURI(path))
		if (resource.loaded) {
			resource.contents.clear
		}
		resource.contents.add(model)
		resource.save(Collections.EMPTY_MAP)
	}
	
	def File[] loadJastAddModels(String rootDirectory) {
		var files = Resources.getResource(rootDirectory)
		
		return new File(files.getPath).listFiles
	}
	

	def static XMItoXtext(EObject model){
        
        var xrs = new XtextResourceSet();
		xrs.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		
		XtextPackage.eINSTANCE.eClass();
		EcorePackage.eINSTANCE.eClass();

		xrs.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

		// load the ecore resource
		var ecoreResource = xrs.getResource(URI.createURI(EcoreFactory.eINSTANCE.getEPackage().getNsURI()), true);
		
		ecoreResource.load(null);
		
		EcoreUtil2.resolveAll(ecoreResource);

		// load the xtext resource
		var xtextResource = xrs.getResource(URI.createURI(EcoreFactory.eINSTANCE.getEPackage().getNsURI()), true);
		xtextResource.load(null);
		EcoreUtil2.resolveAll(xtextResource);

		// add the model to the xtext resource
		xtextResource.getContents().add(model);

		return xtextResource.contents.head as Model
	}
	
}