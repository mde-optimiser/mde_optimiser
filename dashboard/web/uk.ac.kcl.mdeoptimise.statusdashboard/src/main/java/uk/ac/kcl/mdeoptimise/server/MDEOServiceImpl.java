package uk.ac.kcl.mdeoptimise.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import uk.ac.kcl.mdeoptimise.client.constants.PageConstants;
import uk.ac.kcl.mdeoptimise.client.constants.PageConstants.SolutionType;
import uk.ac.kcl.mdeoptimise.client.data.Experiment;
import uk.ac.kcl.mdeoptimise.client.data.Solution;
import uk.ac.kcl.mdeoptimise.client.services.MDEOService;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class MDEOServiceImpl extends RemoteServiceServlet implements MDEOService {

	public List<String> getWorkerIds() throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager.getConnection(
				PageConstants.DATABSE_URL, 
				PageConstants.DATABSE_USERNAME, 
				PageConstants.DATABSE_PASSWORD);
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
		Connection conn = DriverManager.getConnection(
				PageConstants.DATABSE_URL, 
				PageConstants.DATABSE_USERNAME, 
				PageConstants.DATABSE_PASSWORD);
		System.out.println(conn.getCatalog());
		ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT experiment_id FROM experiment;");
		List<String> list = new LinkedList<>();
		
		while(resultSet1.next()){
			list.add(resultSet1.getString(1));
		}
		conn.close();

		return list;
	}

	@Override
	public List<Solution> getSolutions(String experimentId, SolutionType solution_type) throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager.getConnection(
				PageConstants.DATABSE_URL, 
				PageConstants.DATABSE_USERNAME, 
				PageConstants.DATABSE_PASSWORD);
		System.out.println(conn.getCatalog());

		ResultSet solutionIds;
		if (solution_type == PageConstants.SolutionType.FINAL ||
				solution_type == PageConstants.SolutionType.INTERMEDIATE)
			solutionIds = conn.createStatement().executeQuery("SELECT solution_id, run_id FROM solution "
				+ "WHERE experiment_id= '" + experimentId.toLowerCase() + "' "
						+ "AND solution_type= '" + solution_type.toString() + "' ORDER BY run_id;");
		else
			solutionIds = conn.createStatement().executeQuery("SELECT solution_id, run_id FROM solution "
					+ "WHERE experiment_id= '" + experimentId.toLowerCase() + "' ORDER BY run_id;");
		
		List<Solution> list = new LinkedList<>();
		// For each solution
		while(solutionIds.next()) {
			// Add solution to the list
			String solutionId = solutionIds.getString(1);
			int runId = solutionIds.getInt(2);
			Map<String, Double> objectives = getObjectives(conn, solutionId);
			Map<String, Double> constraints = getConstraints(conn, solutionId);
			list.add(new Solution(
					experimentId, 
					solutionId,
					runId,
					objectives, 
					constraints));
		}
		conn.close();
		return list;
	}

	private Map<String, Double> getConstraints(Connection conn, String solutionId) throws SQLException {
		ResultSet result = conn.createStatement().executeQuery("SELECT constraint_name, constraint_value FROM solution_constraint "
				+ "WHERE solution_id= '" + solutionId + "';");
		
		Map<String, Double> constraints = new LinkedHashMap<String, Double>();
		while(result.next()) {
			constraints.put(result.getString(1), result.getDouble(2));
		}
		return constraints;
	}

	private Map<String, Double> getObjectives(Connection conn, String solutionId) throws SQLException {
		ResultSet result = conn.createStatement().executeQuery("SELECT objective_name, objective_value FROM solution_objective "
				+ "WHERE solution_id= '" + solutionId + "';");
		
		Map<String, Double> objectives = new LinkedHashMap<String, Double>();
		while(result.next()) {
			objectives.put(result.getString(1), result.getDouble(2));
		}
		return objectives;
	}

	@Override
	public List<Experiment> getExperimentData(String workerId) throws Exception {
		Class.forName("org.h2.Driver"); 
		Connection conn = DriverManager.getConnection(
				PageConstants.DATABSE_URL, 
				PageConstants.DATABSE_USERNAME, 
				PageConstants.DATABSE_PASSWORD);
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
