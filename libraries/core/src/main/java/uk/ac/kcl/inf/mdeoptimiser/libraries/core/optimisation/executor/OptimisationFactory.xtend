package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.executor

import java.io.InvalidObjectException
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IOptimisationExecutor
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.OptimisationSpec
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.MoeaOptimisation

class OptimisationFactory implements IOptimisationExecutor {
	
	override execute(OptimisationSpec optimisationSpec, SolutionGenerator solutionGenerator) {
		initializeOptimisation(optimisationSpec.algorithmFactory).execute(optimisationSpec, solutionGenerator);
	}
	
	def IOptimisationExecutor initializeOptimisation(String optimisationFactory) {
		
		switch optimisationFactory {
			case "moea":
				return new MoeaOptimisation()
			default:
			
				//TODO perhaps this is not the best exception to use here. Implement our own?
				throw new InvalidObjectException("Invalid algorithm factory specified: " + optimisationFactory)
		}
		
	}
	
}
