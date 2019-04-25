package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.operators.mutation.selection

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin.HenshinExecutor
import java.util.List
import org.eclipse.emf.henshin.model.Unit
import java.util.ArrayList
import java.util.Random
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.AlgorithmSpec
import org.moeaframework.Instrumenter

class RandomOperatorSelector implements OperatorSelectionStrategy {
	
	HenshinExecutor henshinExecutor;
	List<Unit> triedOperators;
	
	AlgorithmSpec algorithmSpec
	Instrumenter algorithmStepInstrumenter
		
	new(HenshinExecutor henshinExecutor) {
		this.henshinExecutor = henshinExecutor
		triedOperators = new ArrayList<Unit>(); 
	}
	
	/**
	 * Invoked when the search starts.
	 * 
	 * @param algorithmSpec configured algorithm spec
	 * @param algorithmStepInstrumenter algorithm step size instrumenter
	 */
	override initialize(AlgorithmSpec algorithmSpec, Instrumenter algorithmStepInstrumenter) {
		this.algorithmSpec = algorithmSpec;
		this.algorithmStepInstrumenter = algorithmStepInstrumenter;
	}
	
	/**
	 * Register a tried operator in the current step.
	 */
	override void addTriedOperator(Unit operator) {
		triedOperators.add(operator)
	}
	
	/**
	 * Check if there are any operators that have not yet been tried in this step.
	 * 
	 * @return boolean
	 */
	override boolean hasUntriedOperators(){
		return triedOperators.size < this.henshinExecutor.mutationOperators.size 
	}
	
	/**
	 * Flush the list of tried operators to start a new step.
	 */
	override void flushTriedOperators() {
		triedOperators.clear()
	}
	
	/**
	 * Try to select from the available operators only once and if none can be applied, then return
	 * the candidate solution without any operators applied.
	 * 
	 * @return next untried operator to be applied
	 */
	override Unit getNextOperator(){
		
		if (this.hasUntriedOperators()) {
			val remainingOperators = henshinExecutor.mutationOperators.filter[o|!this.triedOperators.contains(o)].toList
			val operator = remainingOperators.remove(new Random().nextInt(remainingOperators.size))
			
			this.addTriedOperator(operator)
			
			return operator;
		}

		// Used all the available operators. Time to stop the step size loop
		return null
	}
}