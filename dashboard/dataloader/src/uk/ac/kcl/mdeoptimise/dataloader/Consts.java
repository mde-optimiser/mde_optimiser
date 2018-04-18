package uk.ac.kcl.mdeoptimise.dataloader;

/**
 * Constants of general utility.
 *
 * @author Tamara
 *
 */
public final class Consts {

	static final String INITIAL_CONTEXT_FACTORY = "org.apache.qpid.jms.jndi.JmsInitialContextFactory";
	static final String CONNECTION_JNDI_NAME = "myFactoryLookup";
	static final String QUEUE_JNDI_NAME = "myQueueLookup";
	static final String QUEUE_NAME = "queue";

	/** Use the following setting when running via Docker. */
	/*static final String QUEUE_CONNECTION_NAME = "amqp://queue:5672";
    static final String DATABASE_CONNECTION_URL = "jdbc:h2:./test";
	static final String DATABASE_USERNAME = "sa";
    static final String DATABASE_PASSWORD = "";*/

	/** Use the following setting when running locally. */
	static final String QUEUE_CONNECTION_NAME = "amqp://localhost:5672";
	static final String DATABASE_CONNECTION_URL = "jdbc:h2:tcp://localhost/~/test";
	static final String DATABASE_USERNAME = "mdeo";
	static final String DATABASE_PASSWORD = "mdeo";

	static enum MessageType {WORKER_REGISTER, FINAL_SOLUTION, INTERMEDIATE_SOLUTION}
	static enum CriteriaType {OBJECTIVE, CONSTRAINT}
	static enum SolutionType {FINAL, INTERMEDIATE}

	// SQL statements for creating new tables.
	static final String CREATE_WORKER =
			"CREATE TABLE IF NOT EXISTS worker (mac_address VARCHAR(255) NOT NULL PRIMARY KEY)";
	static final String CREATE_MOPT_SPECS =
			"CREATE TABLE IF NOT EXISTS mopt_specs (mopt_id VARCHAR(255) NOT NULL PRIMARY KEY, "
					+ "model VARCHAR(255) NOT NULL, metamodel VARCHAR(255) NOT NULL)";
	static final String CREATE_EXPERIMENT =
			"CREATE TABLE IF NOT EXISTS experiment (experiment_id VARCHAR(255) NOT NULL, "
					+ "run_id int NOT NULL IDENTITY(1,1) PRIMARY KEY, worker_id VARCHAR(255) NOT NULL, "
					+ "FOREIGN KEY (worker_id) REFERENCES worker(mac_address), mopt_id VARCHAR(255) NOT NULL, "
					+ "FOREIGN KEY (mopt_id) REFERENCES mopt_specs(mopt_id), start_time TIMESTAMP NOT NULL, "
					+ "end_time TIMESTAMP);";
	static final String CREATE_OBJECTIVE =
			"CREATE TABLE IF NOT EXISTS objective (worker_id VARCHAR(255) NOT NULL, experiment_id VARCHAR(255) NOT NULL, "
					+ "objective_name VARCHAR(255) NOT NULL, objective_type VARCHAR(255))";
	static final String CREATE_CONSTRAINT =
			"CREATE TABLE IF NOT EXISTS constraint (worker_id VARCHAR(255) NOT NULL, experiment_id VARCHAR(255) NOT NULL, "
					+ "constraint_name VARCHAR(255) NOT NULL, constraint_type VARCHAR(255))";
	static final String CREATE_SOLUTION =
			"CREATE TABLE IF NOT EXISTS solution (solution_id VARCHAR(255) NOT NULL PRIMARY KEY, experiment_id VARCHAR(255) NOT NULL, "
					+ "solution_type VARCHAR(255) NOT NULL, run_id int NOT NULL, "
					+ "FOREIGN KEY (experiment_id) REFERENCES experiment(experiment_id))";
	static final String CREATE_SOLUTION_OBJECTIVE =
			"CREATE TABLE IF NOT EXISTS solution_objective (solution_id VARCHAR(255) NOT NULL, "
					+ "objective_name VARCHAR(255) NOT NULL, objective_value FLOAT(8) NOT NULL, "
					+ "FOREIGN KEY (solution_id) REFERENCES solution(solution_id), "
					+ "PRIMARY KEY(solution_id, objective_name))";
	static final String CREATE_SOLUTION_CONSTRAINT =
			"CREATE TABLE IF NOT EXISTS solution_constraint (solution_id VARCHAR(255) NOT NULL, "
					+ "constraint_name VARCHAR(255) NOT NULL, constraint_value FLOAT(8) NOT NULL, "
					+ "FOREIGN KEY (solution_id) REFERENCES solution(solution_id), "
					+ "PRIMARY KEY(solution_id, constraint_name))";
	static final String CREATE_TABLES =
			CREATE_WORKER +"; "
					+ CREATE_MOPT_SPECS +"; "
					+ CREATE_EXPERIMENT +"; "
					+ CREATE_OBJECTIVE +"; "
					+ CREATE_CONSTRAINT +"; "
					+ CREATE_SOLUTION +"; "
					+ CREATE_SOLUTION_OBJECTIVE +"; "
					+ CREATE_SOLUTION_CONSTRAINT +"; ";
}
