package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm

class UnexpectedAlgorithmException extends Exception {
	
	new(String algorithmName) {
		super(String.format("Unexpected algorithm name: %s", algorithmName))
	}
}
