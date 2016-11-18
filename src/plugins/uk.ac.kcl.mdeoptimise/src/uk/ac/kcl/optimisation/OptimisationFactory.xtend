package uk.ac.kcl.optimisation

import uk.ac.kcl.mdeoptimise.OptimisationSpec
import uk.ac.kcl.interpreter.IOptimisation
import java.io.InvalidObjectException
import uk.ac.kcl.optimisation.moea.MoeaOptimisation

class OptimisationFactory implements IOptimisation {
	
	override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {
		
		initializeOptimisation(optimisationSpec.algorithmFactory).execute(optimisationSpec, solutionGenerator);

	}
	
	def IOptimisation initializeOptimisation(String optimisationFactory) {
		
		switch optimisationFactory {
			case "moea":
				return new MoeaOptimisation()
				
			default:
			
				//TODO perhaps this is not the best exception to use here. Implement our own?
				throw new InvalidObjectException("Invalid algorithm factory specified: " + optimisationFactory)
		}
		
	}
	
}
