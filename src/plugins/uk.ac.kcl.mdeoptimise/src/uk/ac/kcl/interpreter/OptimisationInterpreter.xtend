package uk.ac.kcl.interpreter

import uk.ac.kcl.mdeoptimise.Optimisation
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import java.util.List
import org.eclipse.emf.henshin.model.Unit
import org.eclipse.emf.common.util.URI
import uk.ac.kcl.optimisation.moea.MoeaOptimisation
import uk.ac.kcl.optimisation.SolutionGenerator
import uk.ac.kcl.optimisation.UserModelProvider
import java.util.LinkedList
import org.eclipse.core.runtime.IPath
import org.eclipse.core.runtime.Path
import java.util.Iterator
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution

class OptimisationInterpreter {
	
	private Optimisation model
	
	private HenshinResourceSet henshinResourceSet
	private EPackage theMetamodel
	
	private List<Unit> breedingOperators
	private List<Unit> mutationOperators

	private IPath projectRootPath;
	public UserModelProvider modelProvider;
	
	new (String projectPath, Optimisation model){
		this.model = model;
		this.projectRootPath = new Path(projectPath);
	}
	
	def Iterator<MoeaOptimisationSolution> start() {
		
		//This model provider loads the model given by the user in the DSL
		this.modelProvider = new UserModelProvider(getResourceSet(projectRootPath.append(model.basepath.location).toPortableString), model.model.location)
		var solutionGenerator = new SolutionGenerator(model, 
											getBreedingOperators, 
											getMutationOperators, 
											this.modelProvider, 
											getMetamodel);

		return new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)
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