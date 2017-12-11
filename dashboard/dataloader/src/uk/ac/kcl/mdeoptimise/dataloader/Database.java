package uk.ac.kcl.mdeoptimise.dataloader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 
 * 
 * @author Tamara
 *
 */
class Database {
	static Connection conn = null;
	
	private static void connectToDatabase() throws Exception {
		Class.forName("org.h2.Driver"); 
		conn = DriverManager
				.getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo");
	}
	
	static void insertIntoDatabase(String value) throws Exception{
		if (conn == null) { connectToDatabase(); }
		PreparedStatement statement = conn.prepareStatement("INSERT INTO MDEO(TESTTEXT) VALUES(?)");
		statement.setString(1, value);
		statement.execute();
		System.out.println("DATABASE UPADTE " + value + " inserted into the database.");
	}
}
