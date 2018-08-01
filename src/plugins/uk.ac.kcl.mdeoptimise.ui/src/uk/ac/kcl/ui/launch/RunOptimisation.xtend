package uk.ac.kcl.ui.launch

import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.Provider
import java.io.File
import java.util.Date
import org.apache.commons.cli.BasicParser
import org.apache.commons.cli.CommandLine
import org.apache.commons.cli.HelpFormatter
import org.apache.commons.cli.Option
import org.apache.commons.cli.Options
import org.apache.commons.cli.ParseException
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
	
	private Options commandLineOptions;
	
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
		
		
		var parser = new BasicParser();
		var CommandLine command = null
		
		try {
						
			command = parser.parse(app.getCommandLineOptions, args);
			
			if(command.hasOption("classic-matching")) {
				println("You have chosen to use classic matching instead of Henshin nondeterministic matching for model evolution.")
				app.enableManualRandomMatching = true;
			}
			
			var String projectPath = command.getOptionValue("projectPath")
			var String moptPath = command.getOptionValue("moptPath")
			
			if(command.hasOption("batch")){
				var int batchId = Integer.parseInt(command.getOptionValue("batch"))
				
				app.run(projectPath, moptPath, batchId)
				
			} else {
				app.run(projectPath, moptPath)
			}
			
		} catch (ParseException e) {
			app.help();
			System.exit(0);
		}

	}
	
	def void help(){
		var formatter = new HelpFormatter();
		
		var header = "Run MOPT specs from the command line.\n\n";
 		var footer = "\nPlease report issues at https://github.com/mde-optimiser/mde_optimiser/issues";
		
		formatter.printHelp("mdeo", header, this.getCommandLineOptions, footer, true)
		
	}
	
	def Options getCommandLineOptions(){
		
		if(this.commandLineOptions === null) {
			
			this.commandLineOptions = new Options()
			
			var projectPath = new Option("p", "projectPath", true, "use given path as the root of the tool" );
 			projectPath.setRequired(true);
 			projectPath.setArgs(1)
			
			var moptPath = new Option("m", "moptPath", true, "use given MOPT configuration file");
 			moptPath.setRequired(true);
			moptPath.setArgs(1)
			
			commandLineOptions.addOption(projectPath)
			commandLineOptions.addOption(moptPath)
			commandLineOptions.addOption("b", "batch", true, "run a single batch with this numeric ID")
			commandLineOptions.addOption("s", "classic-matching", false, "use classic matching strategy in Henshin. Default is Henshin matching.")
		}
		
		return this.commandLineOptions	
	}
	
	def void run(String moptProjectPath, String configuredMoptFilePath){
		this.run(moptProjectPath, configuredMoptFilePath, null)
	}
	
	def void run(String moptProjectPath, String configuredMoptFilePath, Integer batch){
		
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
					if(batch === null) {
						
						//Run all the batches from the MOPT file
						
						var experimentId = 0;
		            	do {	      				
		            		//TODO Output generated mutation operators for each experiment? or each batch?
		            		mdeoResultsOutput.logBatch(runBatch(moptProjectPath, optimisationModel, experimentId, false))
		            		experimentId++
						
						} while(experimentId < optimisationModel.optimisation.algorithmBatches);
					} else {
						
						//Run a specific batch only
		            	mdeoResultsOutput.logBatch(runBatch(moptProjectPath, optimisationModel, batch, true))
					}

	            	mdeoResultsOutput.saveOutcome(batch);
	            }
			
			} else {
				println("Could not find selected mopt file: " + configuredMoptFilePath)
			}
		}
	}
	
	def MDEOBatch runBatch(String moptProjectPath, Optimisation optimisationModel, Integer batch, boolean singleBatch){
			
			var optimisationInterpreter = new OptimisationInterpreter(moptProjectPath, optimisationModel);
	            		
    		val startTime = System.nanoTime;
    		if(this.enableManualRandomMatching){
    			optimisationInterpreter.enableManualRandomMatching = enableManualRandomMatching;
    		}
    		
    		val optimisationOutcome = optimisationInterpreter.start();
    		
    		val endTime = System.nanoTime;
    		
    		val experimentDuration = (endTime - startTime) / 1000000
			
			var generatedRules = optimisationInterpreter.rulegenOperators;
			
			return new MDEOBatch(batch, experimentDuration, optimisationOutcome, generatedRules, singleBatch)
	}
	
}