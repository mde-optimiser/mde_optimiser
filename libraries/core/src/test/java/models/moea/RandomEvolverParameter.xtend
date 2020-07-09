//package models.moea
//
//import java.util.List
//import org.eclipse.emf.ecore.EObject
//import org.eclipse.emf.common.util.EList
//import java.util.Random
//import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.evolvers.parameters.IEvolverParametersFunction
//import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution
//
//class RandomEvolverParameter implements IEvolverParametersFunction {
//	
//	def Object getFeature (EObject o, String feature) {
//		o.eGet (o.eClass.getEStructuralFeature(feature))
//	}
//	
//	override sample(List<Solution> model) {
//		
//		//Count the number of features in the model or one of the parents and use as range for random int generator
//		var features = (model.head.model.getFeature("features") as EList<EObject>).size;
//		
//		var random = new Random().nextInt(features/2);
//		
//		println("Generating random: " + random)
//		
//		return random
//	}
//	
//
//}