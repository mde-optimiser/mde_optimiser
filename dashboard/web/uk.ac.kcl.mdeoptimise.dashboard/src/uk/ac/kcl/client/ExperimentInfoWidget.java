package uk.ac.kcl.client;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import uk.ac.kcl.client.constants.PageConstants;
import uk.ac.kcl.client.data.Experiment;
import uk.ac.kcl.client.data.Solution;
import uk.ac.kcl.client.services.MDEOService;
import uk.ac.kcl.client.services.MDEOServiceAsync;

public class ExperimentInfoWidget extends Composite {

	private static ExperimentInfoWidgetUiBinder uiBinder = GWT.create(ExperimentInfoWidgetUiBinder.class);
	private final MDEOServiceAsync service = GWT.create(MDEOService.class);

	interface ExperimentInfoWidgetUiBinder extends UiBinder<Widget, ExperimentInfoWidget> {
	}

	@UiField
	Label experimentId;
	@UiField
	Label workerId;
	@UiField
	Label startTime;
	@UiField
	Label endTime;
	@UiField
	Label totalTime;
	@UiField
	Label moptId;
	@UiField
	SimplePanel solutionsTable;
	@UiField
	Button finalSolutions;
	@UiField
	Button intermediateSolutions;
	@UiField
	Button allSolutions;
	
	private Experiment experiment;

	public ExperimentInfoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ExperimentInfoWidget(Experiment experiment) {
		initWidget(uiBinder.createAndBindUi(this));
		this.experiment = experiment;
		setExperimentId(experiment.getExperimentId());
		setWorkerId(experiment.getWorkerId());
		setStartTime(experiment.getStartTime());
		if (experiment.getEndTime() != null) {
			setEndTime(experiment.getEndTime());
			setTotalTime(experiment.getEndTime().getTime() - experiment.getStartTime().getTime());
		}
		else {
			setEndTime("N/A");
			setTotalTime(new Date().getTime() - experiment.getStartTime().getTime());
		}
		setMoptId(experiment.getMoptId());
		setButtonListeners();
	}

	private void setTotalTime(long timeElapsed) {
		totalTime.setText(totalTime.getText() + " " + DateTimeFormat.getFormat("HH:mm:ss").format(new Date(timeElapsed)));
	}

	private void setWorkerId(String id) {
		workerId.setText(workerId.getText() + " " + id.toUpperCase());
	}

	public void setExperimentId(String id){
		experimentId.setText(experimentId.getText() + " " + id.toUpperCase());	
	}
	
	public void setStartTime(Timestamp time){
		startTime.setText(startTime.getText() + " " + DateTimeFormat.getFormat("dd/MM/yyyy 'at' hh:mm aaa").format(time));
	}
	
	public void setEndTime(Timestamp time){
		endTime.setText(endTime.getText() + " " + DateTimeFormat.getFormat("dd/MM/yyyy 'at' hh:mm aaa").format(time));
	}

	public void setEndTime(String text){
		endTime.setText(endTime.getText() + " " + text);
	}

	public void setMoptId(String id){
		moptId.setText(moptId.getText() + " " + id.toUpperCase());	
	}
	
	public void setSolutionsTable(SolutionsTable table){
		solutionsTable.setWidget(table);
	}

	private void setButtonListeners() {
		finalSolutions.addClickHandler(new MyRadioButtonHandler(experiment));
		intermediateSolutions.addClickHandler(new MyRadioButtonHandler(experiment));
		allSolutions.addClickHandler(new MyRadioButtonHandler(experiment));
	}

	private void getSolutions(String experimentId, String solutionType) {
		service.getSolutions(experimentId, solutionType, new AsyncCallback<List<Solution>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(PageConstants.SERVER_ERROR + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Solution> result) {
				if (result.size() > 0)
					setSolutionsTable(new SolutionsTable(result));
				else {
					// Show an error message if there are no solutions of the selected solution type
					Window.alert("ERROR: solutions of type '" + solutionType + "' are not available.");
					intermediateSolutions.setActive(true);
					finalSolutions.setActive(false);
					allSolutions.setActive(false);
				}
			}
		});
	}

	private class MyRadioButtonHandler implements ClickHandler {

		private Experiment experiment;
		
		public MyRadioButtonHandler(Experiment experiment) {
			this.experiment = experiment;
		}

		@Override
		public void onClick(ClickEvent event) {
			getSolutions(
					experiment.getExperimentId() /* experiment ID */,
					((Button) event.getSource()).getName() /* selected solution type */ );
		}
	}
}
