package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm

class AlgorithmParameterException extends Exception {
	
	new(String parameterName) {
		super(String.format("Algorithm parameter exception for parameter: %s", parameterName))
	}
}
