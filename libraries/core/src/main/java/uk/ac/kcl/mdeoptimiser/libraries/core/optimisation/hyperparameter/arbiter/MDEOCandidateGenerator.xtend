package uk.ac.kcl.mdeoptimiser.libraries.core.optimisation.hyperparameter.arbiter

import org.deeplearning4j.arbiter.optimize.api.CandidateGenerator
import org.deeplearning4j.arbiter.optimize.api.OptimizationResult

/**
 * Generates more candidates using the implemented strategy
 */
class MDEOCandidateGenerator<MoeaFrameworkAlgorithmConfiguration> implements CandidateGenerator {
	
	override getCandidate() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getCandidateType() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override getParameterSpace() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override hasMoreCandidates() {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override reportResults(OptimizationResult arg0) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
	
	override setRngSeed(long arg0) {
		throw new UnsupportedOperationException("TODO: auto-generated method stub")
	}
}