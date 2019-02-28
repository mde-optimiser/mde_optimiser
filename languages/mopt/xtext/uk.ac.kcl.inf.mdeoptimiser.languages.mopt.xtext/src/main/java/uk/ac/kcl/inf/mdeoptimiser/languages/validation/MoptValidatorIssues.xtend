package uk.ac.kcl.inf.mdeoptimiser.languages.validation

class MoptValidatorIssues {
	protected static final String ISSUE_CODE_PREFIX = "uk.ac.kcl.mdeoptimise.IssueCodes."; 
	public static final String MDEO_LIB_NOT_ON_CLASSPATH = "mdeo_lib_missing"; 
	
	public static final String DUPLICATE_PARAMETER_ENCOUNTERED = ISSUE_CODE_PREFIX + "duplicate_parameter_encountered"
	
	public static final String REQUIRED_PARAMETER_NOT_FOUND = ISSUE_CODE_PREFIX + "required_parameter_not_found"
	
	public static final String UNEXPECTED_PARAMETER_FOUND = ISSUE_CODE_PREFIX + "unexpected_parameter_found"
	
}
