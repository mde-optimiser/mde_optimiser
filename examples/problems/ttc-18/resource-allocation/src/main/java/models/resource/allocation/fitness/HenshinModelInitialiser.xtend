package models.resource.allocation.fitness

import org.eclipse.emf.henshin.model.resource.HenshinResourceSet
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl
import org.eclipse.emf.henshin.interpreter.Engine
import org.eclipse.emf.henshin.interpreter.impl.ChangeImpl
import uk.ac.kcl.interpreter.IModelInitialiser
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.henshin.model.Module

class HenshinModelInitialiser implements IModelInitialiser {
	
	
	private UnitApplicationImpl unitRunner;
	private Module module;
	
	new(){
		// Create an engine and a rule application:
		var engine = new EngineImpl();
		engine.getOptions().put(Engine.OPTION_DETERMINISTIC, false);
		this.unitRunner = new UnitApplicationImpl(engine);
		
	}
	
	override initialise(EObject model) {
		
		val resourceSet = model.eResource.resourceSet as HenshinResourceSet
		
		var module = resourceSet.getModule("iterative-resource-allocation.henshin", false);
		
		var graph = new EGraphImpl(model);
	
		unitRunner.setEGraph(graph);
		
				// Transferring some money:
		unitRunner.setUnit(module.getUnit("modelInitialisation"));
		
		//ChangeImpl.PRINT_WARNINGS = true;
			
		if (!unitRunner.execute(null)) { 
			throw new RuntimeException("Error running transformation to initialise model");
		}
		
		return graph.roots.head
	}
	
}