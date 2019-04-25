package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators

import org.eclipse.emf.henshin.model.Unit
import java.util.List

interface IOperatorsProvider {
	
	def List<Unit> getMutationOperators();
	def List<Unit> getCrossoverOperators();
}