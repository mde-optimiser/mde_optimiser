package uk.ac.kcl.client;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
		if (experiment.getEndTime() != null)
			setEndTime(experiment.getEndTime());
		setTotalTime(experiment.getStartTime(), Optional.of(experiment.getEndTime()));
		setMoptId(experiment.getMoptId());
		setButtonListeners();
	}

	private void setTotalTime(Timestamp startTime, Optional<Timestamp> endTime) {
		long total;
		if (endTime.isPresent())
			total = endTime.get().getTime() - startTime.getTime();
		else
			total = new Date().getTime() - startTime.getTime();
		totalTime.setText(totalTime.getText() + " " + DateTimeFormat.getFormat("HH:mm:ss").format(new Date(total)));
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
		// TODO (tamara): use the solution type to pull different solutions from the database
		service.getSolutions(experimentId, new AsyncCallback<List<Solution>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				Window.alert(PageConstants.SERVER_ERROR + caught.getMessage());
			}

			@Override
			public void onSuccess(List<Solution> result) {
				setSolutionsTable(new SolutionsTable(result));
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
			String solutionType = ((Button) event.getSource()).getName();
			Window.alert(solutionType + " SOLUTIONS CHOSEN");
			getSolutions(experiment.getExperimentId(), solutionType);
		}
	}
}
