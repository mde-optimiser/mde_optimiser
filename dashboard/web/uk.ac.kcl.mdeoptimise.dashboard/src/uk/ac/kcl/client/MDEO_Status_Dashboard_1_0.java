package uk.ac.kcl.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MDEO_Status_Dashboard_1_0 implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private ListBox workers;
	private ListBox experiments;
	private DialogBox dialogBox;
	private Button closeButton;
	private HTML serverResponseLabel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Test database connection");
		final TextBox nameField = new TextBox();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		sendButton.setVisible(false);

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("sendButtonContainer").add(sendButton);

		// Make a new list box, adding a few items to it.
		workers = new ListBox();
		getWorkerIdList();

		// Make a new list box, adding a few items to it.
		//experiments = new ListBox();
		//getExperimentIdList();
		
		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		/*
		 * dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		 * dialogVPanel.add(textToServerLabel); dialogVPanel.add(new
		 * HTML("<br><b>Server replies:</b>"));
		 */
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});
		
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				serverResponseLabel.setText("");
				greetingService.greetServer("text", new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}
		
		class ListBoxChangeHandler implements ChangeHandler {

			@Override
			public void onChange(ChangeEvent event) {
				updateTable();
			}

			private void updateTable() {
				System.out.println("update table just called");
				// load data from the database for selected experiment and worker IDs
				greetingService.getSolutions(
						Integer.parseInt(workers.getSelectedValue()), 
						experiments.getSelectedValue(), 
						new AsyncCallback<String>() {

					@Override
					public void onFailure(Throwable caught) {
						System.out.println("on failure");
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR + caught.getMessage());
						dialogBox.center();
						closeButton.setFocus(true);
					}

					/*@Override
					public void onSuccess(List<Solution> result) {
						System.out.println("on success");
						// Set the total row count. This isn't strictly necessary, but it affects
					    // paging calculations, so its good habit to keep the row count up to date.
					    table.setRowCount(result.size(), true);

					    // Push the data into the widget.
					    table.setRowData(0, result);

					    // Add it to the root panel.
					    RootPanel.get().add(table);
					}*/
					
					@Override
					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
					
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		//ListBoxChangeHandler listBoxChangeHandler = new ListBoxChangeHandler();
		workers.addChangeHandler(new ListBoxChangeHandler());
		experiments.addChangeHandler(new ListBoxChangeHandler());
	}

	private void getWorkerIdList() {
		greetingService.getWorkerIds(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR + caught.getMessage());
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<String> result) {
				result.stream().forEach(worker -> workers.addItem(worker.toString()));
				// Add it to the root panel.
				workers.setItemSelected(0, true);
				//RootPanel.get("workersMenu").add(workers);
				
				for (String workerId : result) {
					getExperiments(workerId);
				}
			}

		});
	}

	private void getExperiments(String workerId) {
		greetingService.getExperimentData(workerId, new AsyncCallback<List<Experiment>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR + caught.getMessage());
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Experiment> result) {
				Worker worker = new Worker(workerId);
				worker.setExperiments(result);
				WorkerTableWidget tableWidget = new WorkerTableWidget(worker);
				RootPanel.get().add(tableWidget);
			}

		});
	}

	private void getExperimentIdList() {
		greetingService.getExperimentIds(new AsyncCallback<List<String>>() {

			@Override
			public void onFailure(Throwable caught) {
				// Show the RPC error message to the user
				dialogBox.setText("Remote Procedure Call - Failure");
				serverResponseLabel.addStyleName("serverResponseLabelError");
				serverResponseLabel.setHTML(SERVER_ERROR + caught.getMessage());
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<String> result) {
				result.stream().forEach(experiment -> experiments.addItem(experiment.toString()));
				experiments.setItemSelected(0, true);
				// Add it to the root panel.
				RootPanel.get("experimentsMenu").add(experiments);
			}

		});
	}
}
