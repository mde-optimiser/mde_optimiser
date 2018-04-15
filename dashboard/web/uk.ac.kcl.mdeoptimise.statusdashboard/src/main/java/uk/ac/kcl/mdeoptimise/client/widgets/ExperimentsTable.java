package uk.ac.kcl.mdeoptimise.client.widgets;

import java.util.Date;
import java.util.List;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.CellPreviewEvent;

import uk.ac.kcl.mdeoptimise.client.ContentContainer;
import uk.ac.kcl.mdeoptimise.client.data.Experiment;
import uk.ac.kcl.mdeoptimise.client.pages.ExperimentsPage;

public class ExperimentsTable extends CellTable<Experiment> implements CellPreviewEvent.Handler<Experiment> {

	private List<Experiment> experiments;
	
	public ExperimentsTable(List<Experiment> experiments) {
		this.experiments = experiments;
		initializeColumns();

		addCellPreviewHandler(this);
		
		// Push the data into the widget.
		setVisibleRange(0, experiments.size());
		setRowCount(experiments.size(), true);
		setRowData(0, experiments);
	}

	private void initializeColumns() {
		TextColumn<Experiment> numColumn = new TextColumn<Experiment>() {

		    @Override
		    public String getValue(Experiment object) {
		        return Integer.toString(experiments.indexOf(object) + 1);
		    }
		};
		addColumn(numColumn, "");
		
		TextColumn<Experiment> experimentId = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				String s = object.getExperimentId().toUpperCase();
				return s.substring(0, Math.min(s.length(), 10)) +"...";
			}
		};
		addColumn(experimentId, "Experiment ID");

		TextColumn<Experiment> status = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				if (object.getEndTime() != null)
					return "Finished";
				else
					return "Running";
			}
		};
		addColumn(status, "Status");

		TextColumn<Experiment> date = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return DateTimeFormat.getFormat("dd/MM/yyyy").format(object.getStartTime());
			}
		};
		addColumn(date, "Date");

		TextColumn<Experiment> startTime = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return DateTimeFormat.getFormat("hh:mm aaa").format(object.getStartTime());
			}
		};
		addColumn(startTime, "Start Time");

		TextColumn<Experiment> endTime = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				if (object.getEndTime() != null)
					return DateTimeFormat.getFormat("hh:mm aaa").format(object.getEndTime());
				else
					return "-";
			}
		};
		addColumn(endTime, "End Time");

		TextColumn<Experiment> timeTaken = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				long total;
				if (object.getEndTime() != null)
					total = object.getEndTime().getTime() - object.getStartTime().getTime();
				else
					total = new Date().getTime() - object.getStartTime().getTime();
				return DateTimeFormat.getFormat("HH:mm:ss").format(new Date(total));
			}
		};
		addColumn(timeTaken, "Time Elapsed");
		
		TextColumn<Experiment> etr = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return "-";
			}
		};
		addColumn(etr, "ETR");
	}

	@Override 
	public void onCellPreview(CellPreviewEvent<Experiment> event) { 
		if("click".equals(event.getNativeEvent().getType())) {
			ContentContainer.getInstance().setContent(new ExperimentsPage(event.getValue()));
		} 
	} 
}
