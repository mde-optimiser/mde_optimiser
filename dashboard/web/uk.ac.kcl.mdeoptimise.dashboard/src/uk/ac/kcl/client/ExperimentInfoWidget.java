package uk.ac.kcl.client;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ButtonGroup;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import uk.ac.kcl.client.data.Experiment;

public class ExperimentInfoWidget extends Composite {

	private static ExperimentInfoWidgetUiBinder uiBinder = GWT.create(ExperimentInfoWidgetUiBinder.class);

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

	public ExperimentInfoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ExperimentInfoWidget(Experiment experiment) {
		initWidget(uiBinder.createAndBindUi(this));
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
		finalSolutions.addClickHandler(new MyRadioButtonHandler());
		intermediateSolutions.addClickHandler(new MyRadioButtonHandler());
		allSolutions.addClickHandler(new MyRadioButtonHandler());
	}

	private static class MyRadioButtonHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			Window.alert(((Button) event.getSource()).getName() + " SOLUTIONS CHOSEN");
		}
	}
}
