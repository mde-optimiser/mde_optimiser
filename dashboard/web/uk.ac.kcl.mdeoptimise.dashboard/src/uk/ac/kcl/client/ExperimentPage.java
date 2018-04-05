package uk.ac.kcl.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.VerticalPanel;

import uk.ac.kcl.client.constants.PageConstants;
import uk.ac.kcl.client.data.Experiment;
import uk.ac.kcl.client.data.Solution;
import uk.ac.kcl.client.services.MDEOService;
import uk.ac.kcl.client.services.MDEOServiceAsync;
public class ExperimentPage extends Content {

	private final MDEOServiceAsync service = GWT.create(MDEOService.class);
	
	/* The message displayed to the user when the server cannot be reached or returns an error. */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network connection and try again.<br><br>";
	
	private ExperimentInfoWidget experimentWidget;
	VerticalPanel panel;
	
	/**
	 * Creates an experiment details page containing experiment info, such as start time, end time etc.
	 * Additionally, it fetches experiment's solutions and shows them in a table.
	 * 
	 * @param experiment data to be shown
	 */
	public ExperimentPage(Experiment experiment) {
		experimentWidget = new ExperimentInfoWidget(experiment);
		
		panel = new VerticalPanel();
		panel.add(experimentWidget);
		initWidget(panel);
		
		getFinalSolutions(experiment.getExperimentId());
		// All composites must call initWidget() in their constructors.

		// Give the overall composite a style name.
		setStyleName("experimentDetails");
	}
	
	private void getFinalSolutions(String experimentId) {
		service.getSolutions(experimentId, new AsyncCallback<List<Solution>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(SERVER_ERROR + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Solution> result) {
				experimentWidget.setSolutionsTable(new SolutionsTable(result));
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