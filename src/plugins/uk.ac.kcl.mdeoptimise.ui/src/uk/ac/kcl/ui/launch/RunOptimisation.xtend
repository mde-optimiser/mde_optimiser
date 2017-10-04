package uk.ac.kcl.ui.launch 

import com.google.inject.Inject
import com.google.inject.Injector
import uk.ac.kcl.MDEOptimiseStandaloneSetup
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.interpreter.OptimisationInterpreter
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.common.util.URI
import org.eclipse.core.resources.IFile

class RunOptimisation {
	
	
	static val Injector injector = new MDEOptimiseStandaloneSetup().createInjectorAndDoEMFRegistration();
	
	def public static void main(String[] args){
		val app = new RunOptimisation()
		
		app.run(null)
	}
	
	def void run(IFile file){
		
		if (file == null ) {
			println("Received a null file")
			return
		} 
		
		println("Configuration mopt file: " + file.fullPath.toString)
	
		
	
	}
}
			