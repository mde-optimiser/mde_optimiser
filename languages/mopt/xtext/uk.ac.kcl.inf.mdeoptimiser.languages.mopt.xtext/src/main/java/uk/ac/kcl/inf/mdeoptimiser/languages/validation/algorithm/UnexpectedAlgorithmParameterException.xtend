package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm

class UnexpectedAlgorithmParameterException extends Exception {
	
	new(String parameterName) {
		super(String.format("Unexpected algorithm parameter: %s", parameterName))
	}
}
