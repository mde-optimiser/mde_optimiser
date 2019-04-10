package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions

import org.moeaframework.core.TerminationCondition
import java.security.InvalidParameterException
import java.util.ArrayList
import org.moeaframework.core.termination.CompoundTerminationCondition
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.SolverSpec
import uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter.MDEOHyperparametersConfiguration

class TerminationConditionAdapter {

	TerminationCondition terminationCondition;
	SolverSpec solverSpec

	MDEOHyperparametersConfiguration hyperparameterConfiguration

	new(SolverSpec solverSpec) {
		this.solverSpec = solverSpec
	}

	new(MDEOHyperparametersConfiguration hyperparameterConfiguration) {
		this.hyperparameterConfiguration = hyperparameterConfiguration;
	}

	def TerminationCondition getCondition() {

		if (this.terminationCondition === null) {

			val terminationConditions = new ArrayList<TerminationCondition>();

			if (this.hyperparameterConfiguration === null) {

				if (solverSpec.terminationCondition.parameters.empty) {
					throw new InvalidParameterException("No termination condition parameters found.")
				}

				this.solverSpec.terminationCondition.parameters.forEach [ parameter |

					val terminationCondition = TerminationConditionFactory.
						getTerminationCondition(parameter, solverSpec)

					if (terminationCondition !== null)
						terminationConditions.add(terminationCondition)
				]

				if (terminationConditions.empty) {
					throw new InvalidParameterException("No known termination condition parameters found.")
				}
			} else {
				
				//Return the termination condition found by Arbiter
				terminationConditions.add(hyperparameterConfiguration.evolutionsTerminationCondition);
			}
		
			this.terminationCondition = new CompoundTerminationCondition(terminationConditions)

		}

		return this.terminationCondition
	}
}
