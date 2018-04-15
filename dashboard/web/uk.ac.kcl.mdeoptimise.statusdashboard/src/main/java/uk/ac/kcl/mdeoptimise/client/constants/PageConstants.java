package uk.ac.kcl.mdeoptimise.client.constants;

public class PageConstants {

	public static enum SolutionType {FINAL, INTERMEDIATE, ALL}
	
	/* The message displayed to the user when the server cannot be reached or returns an error. */
	public static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network connection and try again.<br><br>";

	public static final String DATABSE_URL = "jdbc:h2:tcp://localhost/~/test";
	public static final String DATABSE_USERNAME = "mdeo";
	public static final String DATABSE_PASSWORD = "mdeo";
    
    public static final String EXPERIMENT_TOKEN = "experiment-details";
    public static final String EXPERIMENT_TITLE = "MDEO SD | Experiment Details";
    
    public static final String WORKER_TOKEN = "workers-and-experiments";
    public static final String WORKER_TITLE = "MDEO SD | Workers & Experiments";
    
    public static final String HOME_TOKEN = "home";
    public static final String HOME_TITLE = "MDEO SD";

    public static final String HELP_TOKEN = "help";
    public static final String HELP_TITLE = "MDEO SD | Help";
}
