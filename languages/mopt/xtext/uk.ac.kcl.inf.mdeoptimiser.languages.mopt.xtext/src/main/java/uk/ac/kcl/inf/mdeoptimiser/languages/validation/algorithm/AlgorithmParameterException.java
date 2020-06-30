package uk.ac.kcl.inf.mdeoptimiser.languages.validation.algorithm;

public class AlgorithmParameterException extends RuntimeException {
	
	public AlgorithmParameterException(String parameterName) {
		super(String.format("Algorithm parameter exception for parameter: %s", parameterName));
	}
}
