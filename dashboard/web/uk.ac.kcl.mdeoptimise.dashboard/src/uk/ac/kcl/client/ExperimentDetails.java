package uk.ac.kcl.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import uk.ac.kcl.client.constants.PageConstants;
public class ExperimentDetails extends Content {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network connection and try again.<br><br>";
	
	private Label label = new Label();
	
	/**
	 * TODO (tamara): Add documentation once there is an appropriate widget creation.
	 * 
	 * @param experiment
	 */
	public ExperimentDetails(Experiment experiment) {
		VerticalPanel panel = new VerticalPanel();
		panel.add(label);
		panel.add(new ExperimentInfoWidget(experiment));

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("experimentDetails");
	}
	
	private void getFinalSolutions(String experimentId) {
		greetingService.getSolutions(experimentId, new AsyncCallback<List<Solution>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(SERVER_ERROR + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Solution> result) {
				// TODO (tamara): Uncomment this and create a table for solutions once you have the solutions in the database.
				//ContentContainer.getInstance().setContent(new SolutionsTableWidget(result));
			}
		});
	}
	
	@Override
	public String getToken() {
		return PageConstants.EXPERIMENT_TOKEN;
	}

	@Override
	public String getWindowTitle() {
		return PageConstants.EXPERIMENT_TITLE;
	}
}