package uk.ac.kcl.tests

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EPackage
import uk.ac.kcl.mdeoptimise.Optimisation

class TestModelLoader {
	
	private HenshinResourceSet henshinResourceSet

    private EPackage theMetamodel
	
	private Optimisation model
	
	new(Optimisation model){
		this.model = model;
	}
	
	def getResourceSet() {
        if (henshinResourceSet == null) {
            henshinResourceSet = new HenshinResourceSet(model.basepath.location)
        }

        henshinResourceSet
    }

    def getMetamodel() {
        if (theMetamodel == null) {
            theMetamodel = getResourceSet.registerDynamicEPackages(model.metamodel.location).head
        }

        theMetamodel
    }
	
	new(String model){
		
	}
	
}