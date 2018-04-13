package uk.ac.kcl.client;

import java.util.Date;
import java.util.List;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;

import uk.ac.kcl.client.constants.PageConstants;
import uk.ac.kcl.client.data.Experiment;
import uk.ac.kcl.client.data.Worker;

public class WorkersPage extends Content {

	private CellTable<Experiment> table = new CellTable<Experiment>();
	private Label label = new Label();
	List<Experiment> experiments;

	/**
	 * Creates a widget containing a label with the worker id and a
	 * table containing all experiments associated to this worker.
	 * 
	 * @param worker the worker containing a list of experiments to be added to the table
	 */
	public WorkersPage(Worker worker) {
		VerticalPanel panel = new VerticalPanel();
		panel.add(label);
		panel.add(table);

		label.setText("Worker: " + worker.getMacAddress());

		// Push the data into the widget.
		table.setRowCount(worker.getExperiments().size(), true);
		table.setRowData(0, worker.getExperiments());
		
		experiments = worker.getExperiments();
		
		table.addCellPreviewHandler(new CellPreviewEvent.Handler<Experiment>() { 
			@Override public void onCellPreview(CellPreviewEvent<Experiment> event) { 
				if("click".equals(event.getNativeEvent().getType())) {
					ContentContainer.getInstance().setContent(new ExperimentPage(event.getValue()));
				} 
			} 
		});

		initializeColumns();

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("container");
	}
	
	private void initializeColumns() {
		TextColumn<Experiment> numColumn = new TextColumn<Experiment>() {

		    @Override
		    public String getValue(Experiment object) {
		        return Integer.toString(experiments.indexOf(object) + 1);
		    }
		};
		table.addColumn(numColumn, "");
		
		TextColumn<Experiment> experimentId = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				String s = object.getExperimentId().toUpperCase();
				return s.substring(0, Math.min(s.length(), 10)) +"...";
			}
		};
		table.addColumn(experimentId, "Experiment ID");

		TextColumn<Experiment> status = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				if (object.getEndTime() != null)
					return "Finished";
				else
					return "Running";
			}
		};
		table.addColumn(status, "Status");

		TextColumn<Experiment> date = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return DateTimeFormat.getFormat("dd/MM/yyyy").format(object.getStartTime());
			}
		};
		table.addColumn(date, "Date");

		TextColumn<Experiment> startTime = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return DateTimeFormat.getFormat("hh:mm aaa").format(object.getStartTime());
			}
		};
		table.addColumn(startTime, "Start Time");

		TextColumn<Experiment> endTime = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				if (object.getEndTime() != null)
					return DateTimeFormat.getFormat("hh:mm aaa").format(object.getEndTime());
				else
					return "-";
			}
		};
		table.addColumn(endTime, "End Time");

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
		table.addColumn(timeTaken, "Time Elapsed");
		
		TextColumn<Experiment> etr = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return "-";
			}
		};
		table.addColumn(etr, "ETR");

		table.setVisibleRange(0, experiments.size());
	}

	@Override
	public String getToken() {
		return PageConstants.WORKER_TOKEN;
	}

	@Override
	public String getWindowTitle() {
		return PageConstants.WORKER_TITLE;
	}
}