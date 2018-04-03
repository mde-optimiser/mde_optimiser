package uk.ac.kcl.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import uk.ac.kcl.client.Experiment;
import uk.ac.kcl.client.GreetingService;
import uk.ac.kcl.client.Solution;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String s) throws IllegalArgumentException {
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		userAgent = escapeHtml(userAgent);
		String text = "";
		try {
			text = getExampleValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*return "Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>"
				+ userAgent;*/
		if (text.isEmpty()) {
			return "Connecting to the database was unsuccessful.";
		}
		else {
			return "Connecting to the database was successful.";
		}
	}
	
	public List<String> getWorkerIds() throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager
				.getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo"); // add application code here
		System.out.println(conn.getCatalog());
		ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT mac_address FROM worker;");
		List<String> list = new LinkedList<>();
		
		while(resultSet1.next()){
			list.add(resultSet1.getString(1));
		}
		conn.close();

		return list;
	}
	
	public List<String> getExperimentIds() throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager
				.getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo"); // add application code here
		System.out.println(conn.getCatalog());
		ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT experiment_id FROM experiment;");
		List<String> list = new LinkedList<>();
		
		while(resultSet1.next()){
			list.add(resultSet1.getString(1));
		}
		conn.close();

		return list;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	private String getExampleValue() throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager
				.getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo"); // add application code here
		System.out.println(conn.getCatalog());
		ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT * FROM experiment;");
		// TODO (tamara): find an alternative for checking if the database is available.
		return resultSet1.toString();
	}

	@Override
	public List<Solution> getSolutions(String experimentId) throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager
				.getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo"); // add application code here
		System.out.println(conn.getCatalog());
		ResultSet solutionIds = conn.createStatement().executeQuery("SELECT solution_id FROM solution "
				+ "WHERE experiment_id='" + experimentId + "';");
		
		List<Solution> list = new LinkedList<>();
		// For each solution
		while(solutionIds.next()) {
			// Add solution to the list
			list.add(new Solution(
					experimentId, 
					getObjectives(conn, solutionIds.getString(1)), 
					getConstraints(conn, solutionIds.getString(1))));
		}
		conn.close();
		return list;
	}

	private Map<String, Double> getConstraints(Connection conn, String solutionId) throws SQLException {
		ResultSet result = conn.createStatement().executeQuery("SELECT constraint_name, constraint_value FROM solution_constraint "
				+ "WHERE solution_id='" + solutionId + "';");
		
		Map<String, Double> constraints = new LinkedHashMap<String, Double>();
		while(result.next()) {
			constraints.put(result.getString(1), result.getDouble(2));
		}
		return constraints;
	}

	private Map<String, Double> getObjectives(Connection conn, String solutionId) throws SQLException {
		ResultSet result = conn.createStatement().executeQuery("SELECT objective_name, objective_value FROM solution_objective "
				+ "WHERE solution_id='" + solutionId + "';");
		
		Map<String, Double> objectives = new LinkedHashMap<String, Double>();
		while(result.next()) {
			objectives.put(result.getString(1), result.getDouble(2));
		}
		return objectives;
	}

	@Override
	public List<Experiment> getExperimentData(String workerId) throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager
				.getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo"); // add application code here
		System.out.println(conn.getCatalog());
		ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT * FROM experiment "
				+ "WHERE worker_id='" + workerId + "';");
		List<Experiment> list = new LinkedList<>();
		//(String experimentId, String workerId, String moptId, Timestamp startTime, Timestamp endTime)
		while(resultSet1.next()){
			list.add(new Experiment(resultSet1.getString(1), resultSet1.getString(3), resultSet1.getString(4), 
					resultSet1.getTimestamp(5), resultSet1.getTimestamp(6)));
		}
		conn.close();
		return list;
	} 
}
