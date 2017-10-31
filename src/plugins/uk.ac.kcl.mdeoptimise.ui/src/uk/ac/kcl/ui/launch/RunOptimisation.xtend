package uk.ac.kcl.ui.launch 

import com.google.inject.Inject
import com.google.inject.Injector
import uk.ac.kcl.MDEOptimiseStandaloneSetup
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.interpreter.OptimisationInterpreter
import com.google.inject.Provider
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.common.util.URI
import java.io.File

class RunOptimisation {
	
	static val Injector injector = new MDEOptimiseStandaloneSetup().createInjectorAndDoEMFRegistration()
	
	@Inject
	private Provider<ResourceSet> resourceSetProvider
	
	/**
	 * Static method invoked by the MDEOptimiser launch configuration
	 * @param the configured mopt file path to run the optimisation from
	 */
	def public static void main(String[] args){
		val app = injector.getInstance(RunOptimisation)
		
		if(args.length == 2) {
			app.run(args.get(0), args.get(1))
		} else {
			println("Invalid number of arguments. Cannot launch optimisation.")
			println("Expecting a valid mopt file path. Received " + args)
		}
	}
	
	def void run(String moptProjectPath, String configuredMoptFilePath){
		
		if (configuredMoptFilePath === null || configuredMoptFilePath.empty) {
			println("Received a null or empty mopt file path.")
			
			return

		} else {

			if(resourceSetProvider === null){
				println("Empty ResourceSetProvider")
			}
			
			val moptFile = new File(configuredMoptFilePath);
			
			if(moptFile.exists) {
				
				val resource = resourceSetProvider.get().getResource(URI.createFileURI(moptFile.getAbsolutePath()), true)
				val optimisationModel = resource.contents.head as Optimisation
				
				if(optimisationModel !== null){
	            	
					val optimisation = new OptimisationInterpreter(moptProjectPath, optimisationModel)
	            	optimisation.start();
	            }	
			
			} else {
				
				println("Could not find selected mopt file: " + configuredMoptFilePath)
			}
		}
	}
}