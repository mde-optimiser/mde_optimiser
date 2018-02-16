package uk.ac.kcl.tests

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.henshin.model.HenshinPackage
import uk.ac.kcl.mdeoptimise.MdeoptimisePackage

class FullTestInjector extends MDEOptimiseInjectorProvider {
	override void setupRegistry() {
		super.setupRegistry()
		// Need to manually restore the Henshin metamodel registration: Global state storing doesn't capture this because it lives in a delegate Registry
		EPackage.Registry.INSTANCE.put(HenshinPackage.eNS_URI, HenshinPackage.eINSTANCE)
		EPackage.Registry.INSTANCE.put(MdeoptimisePackage.eNS_URI, MdeoptimisePackage.eINSTANCE)
		
	}
}