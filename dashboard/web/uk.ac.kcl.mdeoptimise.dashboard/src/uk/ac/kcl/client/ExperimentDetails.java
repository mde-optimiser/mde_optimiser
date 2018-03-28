package uk.ac.kcl.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import uk.ac.kcl.client.constants.PageConstants;
public class ExperimentDetails extends Content {

	private Label label = new Label();
	
	/**
	 * TODO (tamara): Add documentation once there is an appropriate widget creation.
	 * 
	 * @param experiment
	 */
	public ExperimentDetails(Experiment experiment) {
		label.setText("Experiment: " + experiment.getExperimentId());
		
		VerticalPanel panel = new VerticalPanel();
		panel.add(label);

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("experimentDetails");
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