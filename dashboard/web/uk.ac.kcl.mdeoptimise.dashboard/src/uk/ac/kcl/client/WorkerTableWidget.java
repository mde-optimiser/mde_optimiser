package uk.ac.kcl.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkerTableWidget extends Composite {

	private CellTable<Experiment> table = new CellTable<Experiment>();
	private Label label = new Label();

	/**
	 * Constructs an OptionalTextBox with the given caption on the check.
	 * 
	 * @param caption the caption to be displayed with the check box
	 */
	public WorkerTableWidget(Worker worker) {
		// Place the check above the text box using a vertical panel.
		VerticalPanel panel = new VerticalPanel();
		panel.add(label);
		panel.add(table);

		// Set the check box's caption, and check it by default.
		label.setText("Worker: " + worker.getMacAddress());
		// Set the total row count. This isn't strictly necessary, but it affects
		// paging calculations, so its good habit to keep the row count up to date.
		table.setRowCount(worker.getExperiments().size(), true);

		// Push the data into the widget.
		table.setRowData(0, worker.getExperiments());
		//label.setChecked(true);
		//label.addClickHandler(this);

		initializeColumns();

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("workerTable");
	}

	/*public void onClick(ClickEvent event) {
		Object sender = event.getSource();
		if (sender == label) {
			// When the check box is clicked, update the text box's enabled state.
			textBox.setEnabled(label.isChecked());
		}
	}*/

	private void initializeColumns() {
		TextColumn<Experiment> experimentId = new TextColumn<Experiment>() {
			@Override
			public String getValue(Experiment object) {
				return object.getExperimentId();
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
	}

	/**
	 * Sets the caption associated with the check box.
	 * 
	 * @param caption the check box's caption
	 */
	public void setCaption(String caption) {
		// Note how we use the use composition of the contained widgets to provide
		// only the methods that we want to.
		label.setText(caption);
	}

	/**
	 * Gets the caption associated with the check box.
	 * 
	 * @return the check box's caption
	 */
	public String getCaption() {
		return label.getText();
	}
}