package uk.ac.kcl.mdeoptimise.client.pages;

import java.util.List;

import com.github.gwtbootstrap.client.ui.Heading;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import uk.ac.kcl.mdeoptimise.client.Content;
import uk.ac.kcl.mdeoptimise.client.constants.PageConstants;
import uk.ac.kcl.mdeoptimise.client.data.Worker;
import uk.ac.kcl.mdeoptimise.client.widgets.ExperimentsTable;

public class WorkersPage extends Content {

	private Heading heading = new Heading(3, "Workers and Experiments");

	/**
	 * Creates a widget containing a label with the worker id and a
	 * table containing all experiments associated to this worker.
	 * 
	 * @param worker the worker containing a list of experiments to be added to the table
	 */
	public WorkersPage(List<Worker> workers) {
		VerticalPanel panel = new VerticalPanel();
		panel.add(heading);

		for (Worker worker : workers) {
			Label label = new Label();
			label.setText("Worker: " + worker.getMacAddress());
			panel.add(label);
			panel.add(new ExperimentsTable(worker.getExperiments()));
		}

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("container");
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