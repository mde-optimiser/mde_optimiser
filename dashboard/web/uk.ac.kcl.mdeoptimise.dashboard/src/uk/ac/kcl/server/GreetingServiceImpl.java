package uk.ac.kcl.server;

import uk.ac.kcl.client.GreetingService;
import uk.ac.kcl.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.*;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException("Name must be at least 4 characters long");
		}

		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
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
			return "Hello, " + input + "!<br><br>Connecting to the database was unsuccessful.";
		}
		else {
			return "Hello, " + input + "!<br><br>Connecting to the database was successful" + "<br><br>The TESTTEXT from MDEO table is: "
					+ text;
		}
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
		Connection conn = DriverManager.
				getConnection("jdbc:h2:tcp://localhost/~/test", "mdeo", "mdeo"); // add application code here
		System.out.println(conn.getCatalog());
		Statement statement = conn.createStatement(); 
		ResultSet resultSet1 = conn.createStatement().executeQuery("SELECT * FROM MDEO");
		String text = "";
		while(resultSet1.next()){
			text += resultSet1.getString("TESTTEXT") + "; ";
			System.out.println("TEST STRING:" + text);
		}
		conn.close();

		return text;
	} 
}
