package uk.ac.kcl.mdeoptimise.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Database functionalities, such as establishing a connection and inserting values.
 * 
 * @author Tamara
 *
 */
class Database {
	private static final String URL = "jdbc:h2:tcp://localhost/test";
	private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
	//private static final String USERNAME = "mdeo";
    //private static final String PASSWORD = "mdeo";
	
	static Connection conn = null;
	
	private static void connectToDatabase() throws ClassNotFoundException, SQLException  {
		Class.forName("org.h2.Driver"); 
		conn = DriverManager
				.getConnection(URL, USERNAME, PASSWORD);
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
		PreparedStatement statement = conn.prepareStatement("INSERT INTO MDEO(TESTTEXT) VALUES(?)");
		statement.setString(1, value);
		statement.execute();
		System.out.println("DATABASE UPDATE " + value + " inserted into the database.");
	}
}
