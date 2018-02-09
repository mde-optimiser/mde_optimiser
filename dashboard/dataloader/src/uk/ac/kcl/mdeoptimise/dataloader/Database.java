package uk.ac.kcl.mdeoptimise.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Database functionalities, such as establishing a connection and inserting data.
 * <p>
 * TODO(tamara): This class is quite long. Move some functionality to a helper class (e.g. JsonDecoder).
 * 
 * @author Tamara
 * 
 */
class Database {
	private enum MessageType {WORKER_REGISTER, FINAL_SOLUTION, INTERMEDIATE_SOLUTION}
	private enum CriteriaType {OBJECTIVE, CONSTRAINT}
	
	private static final String URL = "jdbc:h2:tcp://database/test";
	private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
	/*private static final String URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String USERNAME = "mdeo";
    private static final String PASSWORD = "mdeo";*/
    
    // SQL statements for creating new tables.
    public static final String CREATE_EXPERIMENT = 
    		"CREATE TABLE IF NOT EXISTS experiment (worker_id INT NOT NULL, worker_name VARCHAR(255), "
    		+ "experiment_id VARCHAR(255) NOT NULL, metamodel VARCHAR(255), model VARCHAR(255))";
    public static final String CREATE_OBJECTIVE = 
    		"CREATE TABLE IF NOT EXISTS objective (worker_id INT NOT NULL, experiment_id VARCHAR(255) NOT NULL, "
    		+ "objective_name VARCHAR(255) NOT NULL, objective_type VARCHAR(255))";
    public static final String CREATE_CONSTRAINT = 
    		"CREATE TABLE IF NOT EXISTS constraint (worker_id INT NOT NULL, experiment_id VARCHAR(255) NOT NULL, "
    		+ "constraint_name VARCHAR(255) NOT NULL, constraint_type VARCHAR(255))";
    public static final String CREATE_SOLUTION = 
    		"CREATE TABLE IF NOT EXISTS solution (worker_id INT NOT NULL, experiment_id VARCHAR(255) NOT NULL, "
    		+ "run_id INT, time_taken INT, criteria_type VARCHAR(255), criteria_name VARCHAR(255), "
    		+ "criteria_value FLOAT(8))";
	
	static Connection conn = null;

	/**
	 * Insert data into the database.
	 * 
	 * @param message contains data to be inserted
	 * @throws Exception 
	 */
	static void insertIntoDatabase(String message) throws Exception {
		if (conn == null) { connectToDatabase(); }
		
		JSONObject messageJSON = new JSONObject(message);

		if (messageJSON.has(MessageType.WORKER_REGISTER.toString())) {
			System.out.println("[DataLoader] WORKER_REGISTER message received: " + message);
			insertWorkerRegister((JSONObject) messageJSON.get(MessageType.WORKER_REGISTER.toString()));
		} 
		else if (messageJSON.has(MessageType.FINAL_SOLUTION.toString())) {
			System.out.println("[DataLoader] FINAL_SOLUTION message received: " + message);
			insertFinalSolution((JSONObject) messageJSON.get(MessageType.FINAL_SOLUTION.toString()));
		} 
		else if (messageJSON.has(MessageType.INTERMEDIATE_SOLUTION.toString())) {
			System.out.println("[DataLoader] INTERMEDIATE_SOLUTION message received: " + message);
			insertIntermediateSolution((JSONObject) messageJSON.get(MessageType.INTERMEDIATE_SOLUTION.toString()));
		}
		else {
			throw new Exception("Invalid message type given: " + message);
		}
	}

	private static void connectToDatabase() throws ClassNotFoundException, SQLException  {
		System.out.println("[DataLoader] Connecting to database...");
		Class.forName("org.h2.Driver"); 
		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("[DataLoader] Database connected");
		initialiseSchema();
	}

	/**
	 * Initialise database schema, by creating all required tables.
	 * 
	 * @throws SQLException
	 */
	private static void initialiseSchema() throws SQLException {
		System.out.println("[DataLoader] Creating new database schema.");
		PreparedStatement statement = conn.prepareStatement(
				CREATE_EXPERIMENT +"; "+ CREATE_OBJECTIVE +"; "+ CREATE_CONSTRAINT +"; "+ CREATE_SOLUTION +"; ");
		statement.executeUpdate();
		conn.commit();
	}

	private static void insertWorkerRegister(JSONObject workerJSON) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO experiment VALUES(?, ?, ?, ?, ?);");
		statement.setInt(1, workerJSON.getInt("worker_id"));
		statement.setString(2, workerJSON.getString("worker_name"));
		statement.setString(3, workerJSON.getString("experiment_id"));
		statement.setString(4, workerJSON.getString("metamodel"));
		statement.setString(5, workerJSON.getString("model"));
		statement.execute();

		System.out.println("[DataLoader] EXPERIMENT table updated: " + workerJSON);

		// Iterate through all objectives/constraints and insert them into the database.
		insertCriteria(CriteriaType.OBJECTIVE, workerJSON.getJSONArray("objectives"), workerJSON);
		insertCriteria(CriteriaType.CONSTRAINT, workerJSON.getJSONArray("constraints"), workerJSON);
		conn.commit();
	}
	
	/**
	 * Inserts multiple objectives/constraints into the database. 
	 * Note: Criteria stands for both objectives and constraints.
	 * 
	 * @param criteriaType can be either 'objective' or 'constraint' depending on the contents of the criteriaArray
	 * @param criteriaArray collection of criteria to be inserted into the database
	 * @param workerJSON full worker register message (contains worker_id, experiment_id etc.)
	 */
	private static void insertCriteria(CriteriaType criteriaType, JSONArray criteriaArray, JSONObject workerJSON) {
		IntStream.range(0, criteriaArray.length()).mapToObj(index -> (JSONObject) criteriaArray.get(index))
				.forEach(criterionJSON -> {
					try {
						PreparedStatement objectiveStatement = conn
								.prepareStatement("INSERT INTO " + criteriaType + " VALUES(?, ?, ?, ?);");
						objectiveStatement.setInt(1, workerJSON.getInt("worker_id"));
						objectiveStatement.setString(2, workerJSON.getString("experiment_id"));
						objectiveStatement.setString(3, criterionJSON.getString("name"));
						objectiveStatement.setString(4, criterionJSON.getString("type"));
						objectiveStatement.execute();
						System.out.println("[DataLoader] " + criteriaType + " table updated: " + criterionJSON);
					} catch (JSONException | SQLException e) {
						e.printStackTrace();
					}
				});
	}

	private static void insertFinalSolution(JSONObject solutionJSON) throws SQLException {
		JSONArray solutionsArray = solutionJSON.getJSONArray("solutions");
		IntStream.range(0, solutionsArray.length()).mapToObj(index -> (JSONObject) solutionsArray.get(index))
				.forEach(solution -> {
					try {
						insertFitnessValue(CriteriaType.OBJECTIVE, solution.getJSONArray("objectives"), solutionJSON);
						insertFitnessValue(CriteriaType.CONSTRAINT, solution.getJSONArray("constraints"), solutionJSON);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				});
		conn.commit();
	}
	
	private static void insertFitnessValue(CriteriaType criteriaType, JSONArray criteriaArray,
			JSONObject solutionJSON) {
		IntStream.range(0, criteriaArray.length()).mapToObj(index -> (JSONObject) criteriaArray.get(index))
				.forEach(criterionJSON -> {
					try {
						PreparedStatement objectiveStatement = conn
								.prepareStatement("INSERT INTO solution VALUES(?, ?, ?, ?, ?, ?, ?);");
						objectiveStatement.setInt(1, solutionJSON.getInt("worker_id"));
						objectiveStatement.setString(2, solutionJSON.getString("experiment_id"));
						objectiveStatement.setInt(3, solutionJSON.getInt("run_id"));
						objectiveStatement.setInt(4, solutionJSON.getInt("time_taken"));
						objectiveStatement.setString(5, criteriaType.toString().toLowerCase());
						objectiveStatement.setString(6, criterionJSON.getString("name"));
						objectiveStatement.setDouble(7, criterionJSON.getDouble("value"));
						objectiveStatement.execute();
						System.out.println("[DataLoader] solution table updated: "
								+ criteriaType.toString().toLowerCase() + ":" + criterionJSON);
					} catch (JSONException | SQLException e) {
						e.printStackTrace();
					}
				});
	}

	private static void insertIntermediateSolution(Object object) throws SQLException {
		// TODO(tamara): Send fitness values to database for intermediate solutions.
		conn.commit();
	}
}
