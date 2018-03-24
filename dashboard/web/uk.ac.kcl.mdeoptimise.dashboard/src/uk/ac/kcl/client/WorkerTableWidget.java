package uk.ac.kcl.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.CellPreviewEvent;
public class WorkerTableWidget extends Composite {

	private ActionCellTable<Experiment> table = new ActionCellTable<Experiment>();
	private Label label = new Label();
	List<Experiment> listData;

	public class ActionCellTable<T> extends CellTable<T> {

		Integer previousSelectedRow = null;

		public void displayRowDetail(int selectedRow, Element e){
			/*if (previousSelectedRow != null && previousSelectedRow + 1 == selectedRow) 
				return;*/
			
			// if there was an expanded row, increase the selected row id by one.
			if (previousSelectedRow != null && previousSelectedRow < selectedRow) 
				selectedRow++;
			
			
		    //Get the tbody of the Cell Table
		    //Assumption that we want the first (only?) tbody.
		    Element tbody = this.getElement().getElementsByTagName("tbody").getItem(0);
		    //Get all the trs in the body
		    NodeList<Element> trs = tbody.getElementsByTagName("tr");

		    //remove previously selected view, if there was one
		    if(previousSelectedRow!=null){
		        trs.getItem(previousSelectedRow+1).removeFromParent();
		        //If the current is further down the list then the current your index will be one off.
		        if(selectedRow>previousSelectedRow)selectedRow--;
		    }
		    if(previousSelectedRow==null || selectedRow != previousSelectedRow){// if the are equal we don't want to do anything else
		        Element td = Document.get().createTDElement();
		        td.setAttribute("colspan", Integer.toString(trs.getItem(selectedRow).getChildNodes().getLength()));
		        td.appendChild(e);

		        Element tr = Document.get().createTRElement();
		        tr.appendChild(td);
		        //int insertIndex = (previousSelectedRow != null && previousSelectedRow > selectedRow) ? selectedRow : selectedRow;
		        tbody.insertAfter(tr, trs.getItem(selectedRow));
		        previousSelectedRow=selectedRow;
		    } else {
		        previousSelectedRow=null;
		    }
		}
	}
	
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
		listData = worker.getExperiments();
		//label.setChecked(true);
		//label.addClickHandler(this);
		
		table.addCellPreviewHandler(new CellPreviewEvent.Handler<Experiment>() { 
			@Override public void onCellPreview(CellPreviewEvent<Experiment> event) { 
				if("click".equals(event.getNativeEvent().getType())) {
					Element element = DOM.createLabel();
					element.setInnerText("Selected roww!");
					Widget w = new ExperimentInfoWidget(event.getValue());
					table.displayRowDetail(table.getKeyboardSelectedRow(), w.getElement());
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
		        return Integer.toString(listData.indexOf(object) + 1);
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

		/*TextColumn<Experiment> startTime = new TextColumn<Experiment>() {
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
		table.addColumn(endTime, "End Time");*/

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