package uk.ac.kcl.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.CellPreviewEvent;

import uk.ac.kcl.client.constants.PageConstants;
import uk.ac.kcl.client.data.Experiment;
import uk.ac.kcl.client.data.Worker;

public class WorkersPage extends Content {

	private ActionCellTable<Experiment> table = new ActionCellTable<Experiment>();
	private Label label = new Label();
	List<Experiment> experiments;

	public class ActionCellTable<T> extends CellTable<T> {

		Integer previousSelectedRow = null;

		// TODO (tamara): Remove this functionality if row expansion is no longer used.
		public void displayRowDetail(int selectedRow, Element e){
			// if there was an expanded row, increase the selected row id by one.
			if (previousSelectedRow != null && previousSelectedRow < selectedRow) 
				selectedRow++;

		    // Get the tbody of the Cell Table
		    Element tbody = this.getElement().getElementsByTagName("tbody").getItem(0);
		    // Get all the trs in the body
		    NodeList<Element> trs = tbody.getElementsByTagName("tr");

		    // Remove previously selected view, if there was one
		    if(previousSelectedRow!=null) {
		        trs.getItem(previousSelectedRow+1).removeFromParent();
		        //If the current is further down the list then the current your index will be one off.
		        if(selectedRow>previousSelectedRow)selectedRow--;
		    }
		    
		    if(previousSelectedRow==null || selectedRow != previousSelectedRow){
		        Element td = Document.get().createTDElement();
		        td.setAttribute("colspan", Integer.toString(trs.getItem(selectedRow).getChildNodes().getLength()));
		        td.appendChild(e);

		        Element tr = Document.get().createTRElement();
		        tr.appendChild(td);
		        tbody.insertAfter(tr, trs.getItem(selectedRow));
		        previousSelectedRow=selectedRow;
		    } else {
		        previousSelectedRow=null;
		    }
		}
	}
	
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
					// TODO (tamara): Remove the following lines if row expansion is no longer used.
					/*Widget w = new ExperimentInfoWidget(event.getValue());
					table.displayRowDetail(table.getKeyboardSelectedRow(), w.getElement());*/
					ContentContainer.getInstance().setContent(new ExperimentPage(event.getValue()));
				} 
			} 
		});

		initializeColumns();

		// All composites must call initWidget() in their constructors.
		initWidget(panel);

		// Give the overall composite a style name.
		setStyleName("workerTable");
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
				return object.getExperimentId().toUpperCase();
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

	@Override
	public String getToken() {
		return PageConstants.WORKER_TOKEN;
	}

	@Override
	public String getWindowTitle() {
		return PageConstants.WORKER_TITLE;
	}
}