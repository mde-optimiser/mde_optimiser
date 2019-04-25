package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.henshin

import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl
import org.eclipse.emf.henshin.interpreter.Engine
import org.eclipse.emf.henshin.interpreter.ApplicationMonitor
import org.eclipse.emf.henshin.model.Rule
import org.eclipse.emf.henshin.interpreter.impl.MatchImpl

class MdeoRuleApplicationImpl extends RuleApplicationImpl {
	
	new(Engine engine) {
		super(engine)
	}
	
	
	override boolean execute(ApplicationMonitor monitor) {
		
		if (unit === null) {
			throw new NullPointerException("No transformation unit set");
		}
		
		// Already executed?
		if (isExecuted) {
			if (isCompleteMatchDerived) {
				completeMatch = null;  // reset the complete match if it was derived
				isCompleteMatchDerived = false;				
			}
			isExecuted = false;
			isUndone = false;
			change = null;
			resultMatch = null;
		}
		
		// Do we need to derive a complete match?
		if (completeMatch === null) {
			completeMatch = engine.findMatches(unit as Rule, graph, partialMatch).iterator().next();
			isCompleteMatchDerived = true;
		}
		
		if (completeMatch === null) {
			if (monitor !== null) {
				monitor.notifyExecute(this, false);
			}
			return false;
		}
		
		resultMatch = new MatchImpl(unit as Rule, true);
		change = engine.createChange(unit as Rule, graph, completeMatch, resultMatch);

		if (change === null) {
			if (monitor !== null) {
				monitor.notifyExecute(this, false);
			}
			return false;
		}
		
		change.applyAndReverse();
		isExecuted = true;
		
		if (monitor !== null) {
			monitor.notifyExecute(this, true);
		}
		
		return true;
	}
}
