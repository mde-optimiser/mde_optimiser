package uk.ac.kcl.interpreter

import java.util.LinkedList
import java.util.List
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.Path
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import uk.ac.kcl.mdeoptimise.Optimisation
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.UserModelProvider
import uk.ac.kcl.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.mdeoptimise.dashboard.api.macaddress.MacAddressRetriever
import uk.ac.kcl.mdeoptimise.dashboard.api.hashing.Hashing
import java.text.SimpleDateFormat
import java.util.Date

class OptimisationInterpreter {
	
	private Optimisation model
	
	private HenshinResourceSet henshinResourceSet
	private EPackage theMetamodel
	
	private List<Unit> breedingOperators
	private List<Unit> mutationOperators

	private IPath projectRootPath;

	new (String projectPath, Optimisation model){
		this.model = model;
		this.projectRootPath = new Path(projectPath);
	}
	
	def void start() {
		
		// This model provider loads the model given by the user in the DSL
		val userModelProvider = new UserModelProvider(getResourceSet(projectRootPath.append(model.basepath.location).toPortableString), model.model.location)
		
		var MacAddressRetriever macAddressRetriever = new MacAddressRetriever();
		var macAddress = macAddressRetriever.getMacAddress();
		var moptId = Hashing.generateMoptId(
			model.getModel().getLocation(), 
			model.getMetamodel().getLocation());
		var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		var date = new Date();
		var startTime = dateFormat.format(date);
		var experimentId = Hashing.generateExperimentId(macAddress, moptId, startTime);
		
		var solutionGenerator = new SolutionGenerator(model, 
											getBreedingOperators, 
											getMutationOperators, 
											userModelProvider, 
											getMetamodel,
											experimentId);

		var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
		
		optimisation
				.forEach[result | userModelProvider.storeModelAndInfo(result, projectRootPath.toPortableString, model)]	
	}

	def getResourceSet(String basePath) {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(basePath);
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = getResourceSet(this.model.basepath.location).registerDynamicEPackages(model.metamodel.location).head
        }

        theMetamodel
    }
    
    def getBreedingOperators() {
    	if(breedingOperators == null){
			
			breedingOperators = new LinkedList
			
			breedingOperators.addAll(model.evolvers.filter[ operator | operator.evolverType.getName.equals("BREED")]
				.map[ operator | getResourceSet(model.basepath.location).getModule(URI.createURI(operator.rule_location), false).getUnit(operator.unit)]
			)
    		
    	}
    	
    	breedingOperators
    }
    
    def getMutationOperators() {
    	if(mutationOperators == null){
			
			mutationOperators = new LinkedList
			
			mutationOperators.addAll(model.evolvers.filter[ operator | operator.evolverType.getName.equals("MUTATE")]
				.map[ operator | getResourceSet(model.basepath.location).getModule(URI.createURI(operator.rule_location), false).getUnit(operator.unit)]
			)
    		
    	}
    	
    	mutationOperators
    }
}