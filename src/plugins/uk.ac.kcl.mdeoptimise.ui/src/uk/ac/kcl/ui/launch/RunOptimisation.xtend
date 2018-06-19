package uk.ac.kcl.ui.launch

import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.Provider
import java.io.File
import java.util.Date
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import uk.ac.kcl.MDEOptimiseStandaloneSetup
import uk.ac.kcl.interpreter.OptimisationInterpreter
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.ui.output.MDEOBatch
import uk.ac.kcl.ui.output.MDEOResultsOutput

class RunOptimisation {
	
	static val Injector injector = new MDEOptimiseStandaloneSetup().createInjectorAndDoEMFRegistration()
	
	@Inject
	private Provider<ResourceSet> resourceSetProvider
	
	/*
	 * Flag to enable choice between Henshin nondeterministic matching in SolutionGenerator
	 * and RandomMutationSelection for model evolution.
	 */
	private boolean enableManualRandomMatching = false;
	
	/**
	 * Static method invoked by the MDEOptimiser launch configuration
	 * @param the configured mopt file path to run the optimisation from
	 */
	def public static void main(String[] args){
		val app = injector.getInstance(RunOptimisation)
		
		if(args.length === 2) {
			app.run(args.get(0), args.get(1))
		} else {
			println("Invalid number of arguments. Cannot launch optimisation.")
			println("Expecting a valid project path and a valid mopt file path.")
		}
		
		if(args.length === 3 && Boolean.parseBoolean(args.get(2)) === true) {
			println("You have chosen to use random matching instead of Henshin nondeterministic matching for model evolution.")
			app.enableManualRandomMatching = true;
			app.run(args.get(0), args.get(1))
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
				
				val mdeoResultsOutput = new MDEOResultsOutput(new Date(), new Path(moptProjectPath), 
					new Path(configuredMoptFilePath), optimisationModel, this.enableManualRandomMatching
				);	
				
				if(optimisationModel !== null){
					
					var experimentId = 0;
	            	do {
	            		val startTime = System.nanoTime;
	            		var optimisationInterpreter = new OptimisationInterpreter(moptProjectPath, optimisationModel);
	            		
	            		if(this.enableManualRandomMatching){
	            			optimisationInterpreter.enableManualRandomMatching = enableManualRandomMatching;
	            		}
	            		
	            		val optimisationOutcome = optimisationInterpreter.start();
	            		
	            		val endTime = System.nanoTime;
	            		
	            		val experimentDuration = (endTime - startTime) / 1000000
	      				
	      				var generatedRules = optimisationInterpreter.rulegenOperators;
	      				
	            		//TODO Output generated mutation operators for each experiment? or each batch?
	            		mdeoResultsOutput.logBatch(new MDEOBatch(experimentId, experimentDuration, optimisationOutcome, generatedRules))		
	            		
	            		experimentId++
					
					} while(experimentId < optimisationModel.optimisation.algorithmBatches);

	            	
	            	mdeoResultsOutput.saveOutcome();
	            }
			
			} else {
				println("Could not find selected mopt file: " + configuredMoptFilePath)
			}
		}
	}
}