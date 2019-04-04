package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import org.deeplearning4j.arbiter.optimize.api.ParameterSpace
import java.util.Collections

class MoeaParameterSpace<T> implements ParameterSpace<T> {
	
	override collectLeaves() {
		return Collections.EMPTY_LIST;
	}
	
	override getNestedSpaces() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getValue(double[] arg0) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override isLeaf() {
		return true;
	}
	
	override numParameters() {
		return 2;
	}
	
	override setIndices(int... arg0) {
				
	}
	
}