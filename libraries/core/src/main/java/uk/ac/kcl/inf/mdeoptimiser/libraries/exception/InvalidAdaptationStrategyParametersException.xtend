package uk.ac.kcl.inf.mdeoptimiser.libraries.exception

class InvalidAdaptationStrategyParametersException extends Exception {

	new (String strategyParameters) {
		super(String.format("Invalid adaptation strategy parameters. Parameters given: %s", strategyParameters))
	}
	
}
