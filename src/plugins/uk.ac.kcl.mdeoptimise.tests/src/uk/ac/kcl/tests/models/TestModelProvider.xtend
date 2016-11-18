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
import java.io.BufferedReader
import java.util.stream.Collectors
import java.io.InputStreamReader
import java.io.File
import java.io.PrintWriter

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
		model.writeModel(pathPrefix + "/" + String.format("%08X", model.hashCode) + ".xmi")
	}

	def storeModelAndInfo(EObject model, String pathPrefix, String initialModel) {
		storeModel(model, pathPrefix)
		val info = runEvaluationJarAgainstBestModel(pathPrefix + "/" + String.format("%08X", model.hashCode) + ".xmi")
		
		var File f = new File(pathPrefix + "/" + String.format("%08X", model.hashCode) + ".txt")
	
		val PrintWriter pw = new PrintWriter(f)
		pw.println("Initial model: " + initialModel)
		pw.println("Evaluation output: ")
		pw.println(info)
		pw.close
		System.out.println(info)
		
	}
	
	
	def String runEvaluationJarAgainstBestModel(String modelPath) {
		
		var evaluatorJar = Runtime.getRuntime().exec("java -jar gen/CRAIndexCalculator.jar " + modelPath)
		
		var output = new BufferedReader(new InputStreamReader(evaluatorJar.getInputStream())).lines().parallel().collect(Collectors.joining("\n"))
		
		if(output.length == 0){
			output += new BufferedReader(new InputStreamReader(evaluatorJar.getErrorStream())).lines().parallel().collect(Collectors.joining("\n"))
		}
		
		output
	}

	def storeModels(Collection<EObject> models, String pathPrefix) {
		models.forEach[m|m.storeModel(pathPrefix)]
	}

	abstract def List<String> getModelPaths()
}
