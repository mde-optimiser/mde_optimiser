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
		setStyleName("container");
	}
	
	private void getFinalSolutions(String experimentId) {
		// TODO (tamara): use constant/enum instead of a string
		service.getSolutions(experimentId, "ALL", new AsyncCallback<List<Solution>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(PageConstants.SERVER_ERROR + caught.getMessage());
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