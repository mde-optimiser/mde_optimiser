package uk.ac.kcl.optimisation

import uk.ac.kcl.interpreter.IModelProvider
import org.eclipse.emf.ecore.EPackage
import java.util.Collections
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import org.eclipse.emf.henshin.interpreter.UnitApplication
import org.eclipse.emf.henshin.interpreter.Engine

class UserModelProvider implements IModelProvider {
	
	private String modelPath
	private HenshinResourceSet resourceSet;
	public EObject initialModel;
			
	public Engine engine
	public UnitApplication unitRunner
	
	new (HenshinResourceSet resourceSet, String userModelPath){
		this.modelPath = userModelPath;
		this.resourceSet = resourceSet;
		this.engine = new EngineImpl
		this.unitRunner = new UnitApplicationImpl(engine)
	}
	
	def loadModel(String path) {
		val resource = resourceSet.createResource(path)
		resource.load(Collections.EMPTY_MAP)
		val model = resource.allContents.head
		// Run the initialization henshin rule
		this.initialModel = initializeModel(model)
		
		return this.initialModel
	}

	override initialModels(EPackage metamodel) {
		resourceSet.packageRegistry.put(metamodel.nsURI, metamodel)

		#[loadModel(modelPath)].iterator
	}
	
	/**
     * Produce a new solution from the given one using one of the evolvers defined in the optimisation model.
     * This will try evolvers until one of them can be applied or all evolvers have been tried. If no evolver was applicable, returns <code>null</code>,
     * otherwise returns the result of the first randomly picked evolver that was applicable.
     */
    def EObject initializeModel(EObject object) {
    			
		val candidateSolution = EcoreUtil.copy(object)

		// Get all matches
		val graph = new EGraphImpl(candidateSolution)

		val initializationUnit = resourceSet.getModule(URI.createURI("initialization.henshin"), false).getUnit("distributeFeatures")
		
		unitRunner.EGraph = graph
		unitRunner.unit = initializationUnit
		if(unitRunner.execute(null)){
			return graph.head	
		} else {
			throw new InstantiationError("Could not initialize model")
		}
    }
    
}