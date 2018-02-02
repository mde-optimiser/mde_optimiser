package uk.ac.kcl.mdeoptimise.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Database functionalities, such as establishing a connection and inserting values.
 * 
 * @author Tamara
 *
 */
class Database {
	private static final String URL = "jdbc:h2:tcp://database/test";
	private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
	/*private static final String URL = "jdbc:h2:tcp://localhost/~/test";
	private static final String USERNAME = "mdeo";
    private static final String PASSWORD = "mdeo";*/
    
    // Sorted list of table names.
    private static final List<String> REQUIRED_TABLES = Arrays.asList("MDEO");
    // SQL statement for creating new table.
    public static final String CREATE_MDEO = "create table MDEO (ID INT NOT NULL AUTO_INCREMENT, TESTTEXT VARCHAR(255), PRIMARY KEY (ID))";
	
	static Connection conn = null;
	
	private static void connectToDatabase() throws ClassNotFoundException, SQLException  {
		System.out.println("[Database] Connecting...");
		Class.forName("org.h2.Driver"); 
		conn = DriverManager
				.getConnection(URL, USERNAME, PASSWORD);
		System.out.println("[Database] Connected");
	}
	
	/**
	 * Insert a given values to the database.
	 * 
	 * @param value the value to be inserted
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static void insertIntoDatabase(String value) throws ClassNotFoundException, SQLException {
		if (conn == null) { connectToDatabase(); }
		initialiseSchema();
		System.out.println("[Database] Inserting into database...");
		PreparedStatement statement = conn.prepareStatement("INSERT INTO MDEO(TESTTEXT) VALUES(?)");
		statement.setString(1, value);
		statement.execute();
		System.out.println("[Database] DATABASE UPDATE " + value + " inserted into the database.");
	}

	/**
	 * Check if all required tables exist in the database schema.
	 * 
	 * @return true if all tables exist, false otherwise.
	 * @throws SQLException
	 */
	private static boolean schemaExists() throws SQLException {
		List<String> tableNames = new ArrayList<String>();
		PreparedStatement statement = conn.prepareStatement("show tables");
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			tableNames.add(result.getString("TABLE_NAME"));
		}
		result.close();
		statement.close();

		Collections.sort(tableNames);
		return tableNames.equals(REQUIRED_TABLES);
	}

	/**
	 * Initialise database schema, by creating all required tables.
	 * 
	 * @throws SQLException
	 */
	private static void initialiseSchema() throws SQLException {
		if (schemaExists()) {
		      return;
		    }
		System.out.println("[Database] Creating new database schema.");
		PreparedStatement statement = conn.prepareStatement(CREATE_MDEO);
		statement.executeUpdate();
		conn.commit();
	}
}
