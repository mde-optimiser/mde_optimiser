package uk.ac.kcl.mdeoptimise.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.ac.kcl.mdeoptimise.dataloader.Consts.CriteriaType;
import uk.ac.kcl.mdeoptimise.dataloader.Consts.MessageType;
import uk.ac.kcl.mdeoptimise.dataloader.Consts.SolutionType;

/**
 * Database functionalities, such as establishing a connection and inserting data.
 * <p>
 * TODO (tamara): This class is quite long. Move some functionality to a helper class (e.g. JsonDecoder).
 * 
 * @author Tamara
 * 
 */
class Database {

	static Connection conn = null;

	/**
	 * Insert data into the database.
	 * 
	 * @param message contains data to be inserted
	 * @throws Exception 
	 */
	static void insertIntoDatabase(String message) throws Exception {
		if (conn == null) { connectToDatabase(); }
		initialiseSchema();

		JSONObject messageJSON = new JSONObject(message);

		if (messageJSON.has(MessageType.WORKER_REGISTER.toString())) {
			System.out.println("[DataLoader] WORKER_REGISTER message received: " + message);
			insertWorkerRegister((JSONObject) messageJSON.get(MessageType.WORKER_REGISTER.toString()));
		} 
		else if (messageJSON.has(MessageType.FINAL_SOLUTION.toString())) {
			System.out.println("[DataLoader] FINAL_SOLUTION message received: " + message);
			JSONObject solutionJSON = (JSONObject) messageJSON.get(MessageType.FINAL_SOLUTION.toString());
			insertExperimentEndTime(solutionJSON);
			insertSolution(SolutionType.FINAL, solutionJSON);
		} 
		else if (messageJSON.has(MessageType.INTERMEDIATE_SOLUTION.toString())) {
			System.out.println("[DataLoader] INTERMEDIATE_SOLUTION message received: " + message);
			insertSolution(SolutionType.INTERMEDIATE, (JSONObject) messageJSON.get(MessageType.INTERMEDIATE_SOLUTION.toString()));
		}
		else {
			throw new Exception("Invalid message type given: " + message);
		}
	}

	private static void connectToDatabase() throws ClassNotFoundException, SQLException  {
		System.out.println("[DataLoader] Connecting to database...");
		Class.forName("org.h2.Driver"); 
		conn = DriverManager.getConnection(Consts.DATABASE_CONNECTION_URL, Consts.DATABASE_USERNAME, Consts.DATABASE_PASSWORD);
		System.out.println("[DataLoader] Database connected");
	}

	/**
	 * Initialise database schema, by creating all required tables.
	 * 
	 * @throws SQLException
	 */
	private static void initialiseSchema() throws SQLException {
		PreparedStatement statement = conn.prepareStatement(Consts.CREATE_TABLES);
		statement.executeUpdate();
		conn.commit();
	}

	private static void insertWorkerRegister(JSONObject workerJSON) throws SQLException, JSONException, ParseException {
		String workerId = workerJSON.getString("worker_id");
		ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM worker WHERE mac_address='" + workerId + "';");
		if(!resultSet.next()){
			PreparedStatement workerStatement = conn.prepareStatement("INSERT INTO worker VALUES('" + workerId + "');");
			workerStatement.execute();
			System.out.println("[DataLoader] WORKER table updated with mac_address: " + workerId);
		}
		else
			System.out.println("[DataLoader] WORKER table already contains mac_address: " + workerId);

		String moptId = workerJSON.getString("mopt_id");
		ResultSet moptResultSet = conn.createStatement().executeQuery("SELECT * FROM mopt_specs WHERE mopt_id='" + moptId + "';");
		if(!moptResultSet.next()){
			PreparedStatement moptStatement = conn.prepareStatement("INSERT INTO mopt_specs VALUES(?, ?, ?);");
			System.out.println("[DataLoader] MOPT_SPECS table updated: " + workerJSON);
			moptStatement.setString(1, moptId);
			moptStatement.setString(2, workerJSON.getString("model"));
			moptStatement.setString(3, workerJSON.getString("metamodel"));
			moptStatement.execute();
		}
		else
			System.out.println("[DataLoader] MOPT_SPECS table already contains mopt_id: " + moptId);

		PreparedStatement statement = conn.prepareStatement("INSERT INTO experiment(experiment_id, worker_id, mopt_id, start_time) VALUES(?, ?, ?, ?);");
		statement.setString(1, workerJSON.getString("experiment_id"));
		statement.setString(2, workerJSON.getString("worker_id"));
		statement.setString(3, workerJSON.getString("mopt_id"));
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date parsedDate = dateFormat.parse(workerJSON.getString("start_time"));
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		statement.setTimestamp(4, timestamp);
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
				objectiveStatement.setString(1, workerJSON.getString("worker_id"));
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

	private static void insertSolution(SolutionType solutionType, JSONObject solutionJSON) throws SQLException {
		JSONArray solutionsArray = solutionJSON.getJSONArray("solutions");
		IntStream.range(0, solutionsArray.length()).mapToObj(index -> (JSONObject) solutionsArray.get(index))
		.forEach(solution -> {
			try {
				// insert solution
				PreparedStatement insertSolutionStatement = conn.prepareStatement("INSERT INTO solution VALUES(?, ?, ?, ?);");
				insertSolutionStatement.setString(1, solution.getString("solution_id"));
				insertSolutionStatement.setString(2, solutionJSON.getString("experiment_id"));
				insertSolutionStatement.setString(3, solutionType.toString());
				insertSolutionStatement.setDouble(4, solutionJSON.getInt("run_id"));
				insertSolutionStatement.execute();
				conn.commit();
				System.out.println("[DataLoader] SOLUTION table updated for solution: " + solution.getString("solution_id"));
				// insert its objectives
				insertFitnessValue(CriteriaType.OBJECTIVE, solution.getJSONArray("objectives"), solution.getString("solution_id"));
				// insert its constraints
				insertFitnessValue(CriteriaType.CONSTRAINT, solution.getJSONArray("constraints"), solution.getString("solution_id"));
			} catch (JSONException | SQLException e) {
				e.printStackTrace();
			}
		});
		conn.commit();
	}

	private static void insertExperimentEndTime(JSONObject solutionJSON) throws JSONException, SQLException, ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date parsedDate = dateFormat.parse(solutionJSON.getString("end_time"));
		Timestamp endTime = new java.sql.Timestamp(parsedDate.getTime());
		PreparedStatement statement = conn.prepareStatement("UPDATE experiment SET end_time='" + endTime +
				"' WHERE experiment_id='" + solutionJSON.getString("experiment_id") + "';");
		statement.execute();
		System.out.println("[DataLoader] EXPERIMENT table updated (end_time)");
		conn.commit();
	}

	private static void insertFitnessValue(CriteriaType criteriaType, JSONArray criteriaArray, String solutionId) throws SQLException {
		IntStream.range(0, criteriaArray.length()).mapToObj(index -> (JSONObject) criteriaArray.get(index))
		.forEach(criterionJSON -> {
			try {
				PreparedStatement objectiveStatement = conn
						.prepareStatement("INSERT INTO solution_" + criteriaType + " VALUES(?, ?, ?);");
				objectiveStatement.setString(1, solutionId);
				objectiveStatement.setString(2, criterionJSON.getString("name"));
				objectiveStatement.setDouble(3, criterionJSON.getDouble("value"));
				objectiveStatement.execute();
			} catch (JSONException | SQLException e) {
				e.printStackTrace();
			}
		});
		conn.commit();
		System.out.println("[DataLoader] SOLUTION_" + criteriaType + " table updated for solution: " + solutionId);
	}
}
