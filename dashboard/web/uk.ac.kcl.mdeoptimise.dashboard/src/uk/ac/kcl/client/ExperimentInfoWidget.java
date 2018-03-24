package uk.ac.kcl.client;

import java.sql.Timestamp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class ExperimentInfoWidget extends Composite {

	private static ExperimentInfoWidgetUiBinder uiBinder = GWT.create(ExperimentInfoWidgetUiBinder.class);

	interface ExperimentInfoWidgetUiBinder extends UiBinder<Widget, ExperimentInfoWidget> {
	}

	public ExperimentInfoWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Label experimentId;
	@UiField
	Label startTime;
	@UiField
	Label endTime;
	@UiField
	Label moptId;

	public ExperimentInfoWidget(Experiment experiment) {
		initWidget(uiBinder.createAndBindUi(this));
		setExperimentId(experiment.getExperimentId());
		setStartTime(experiment.getStartTime());
		if (experiment.getEndTime() != null) 
			setEndTime(experiment.getEndTime());
		setMoptId(experiment.getMoptId());
		//endTime.setText(endTime.getText() + " " + DateTimeFormat.getFormat("dd/MM/yyyy 'at' HH:mm:ss").format(experiment.getEndTime()));
	}

	public void setExperimentId(String id){
		experimentId.setText(experimentId.getText() + " " + id.toUpperCase());	
	}
	
	public void setStartTime(Timestamp time){
		startTime.setText(startTime.getText() + " " + DateTimeFormat.getFormat("dd/MM/yyyy 'at' HH:mm:ss").format(time));	
	}
	
	public void setEndTime(Timestamp time){
		endTime.setText(endTime.getText() + " " + DateTimeFormat.getFormat("dd/MM/yyyy 'at' HH:mm:ss").format(time));	
	}
	
	public void setMoptId(String id){
		moptId.setText(moptId.getText() + " " + id.toUpperCase());	
	}
}
