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
import java.nio.file.Paths

class OptimisationInterpreter {
	
	private Optimisation model
	
	private HenshinResourceSet henshinResourceSet
	
	private EPackage theMetamodel
	
	private List<Unit> henshinEvolvers = null

	private String basePath;

	new (String basePath, Optimisation model){
		this.model = model;
		this.basePath = basePath;
		start();
	}
	
	def void start(){
		val userModelProvider = new UserModelProvider(URI.createURI(basePath), model.model.location)
			
		var solutionGenerator = new SolutionGenerator(
											model, 
											henshinEvolvers, 
											henshinResourceSet, 
											userModelProvider, 
											getMetamodel);

		var optimisation = new MoeaOptimisation()
									.execute(model.optimisation, solutionGenerator)		
		
		optimisation
				.forEach[result | userModelProvider.storeModel(result, model.basepath.location + "/final")]	
	}

	def getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(this.basePath);
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.metamodel.location).head
        }

        theMetamodel
    }
    
    def getHenshinEvolvers() {
    	if (henshinEvolvers == null) {
            val hrs = resourceSet
            henshinEvolvers = model.evolvers.map [ e |
                hrs.getModule(URI.createURI(e.rule_location), false).getUnit(e.unit)
            ]
        }
    }
	
	
	
}