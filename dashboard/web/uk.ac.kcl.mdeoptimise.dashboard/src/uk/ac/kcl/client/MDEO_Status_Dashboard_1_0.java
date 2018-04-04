package uk.ac.kcl.client;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import uk.ac.kcl.client.data.Experiment;
import uk.ac.kcl.client.data.Worker;
import uk.ac.kcl.client.services.MDEOService;
import uk.ac.kcl.client.services.MDEOServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MDEO_Status_Dashboard_1_0 implements EntryPoint  {
	
	/* The message displayed to the user when the server cannot be reached or returns an error. */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network connection and try again.<br><br>";

	private final MDEOServiceAsync service = GWT.create(MDEOService.class);

	private DialogBox dialogBox;
	private Button closeButton;
	private HTML serverResponseLabel;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		getWorkerIdList();

		// Create the popup dialog box
		dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		
		// Create the close button and a placeholder for the server response message
		closeButton = new Button("Close");
		closeButton.getElement().setId("closeButton");
		serverResponseLabel = new HTML();
		
		// Create a panel to hold everything together and add it to the dialog box
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
	}

	private void getWorkerIdList() {
		service.getWorkerIds(new AsyncCallback<List<String>>() {

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
				for (String workerId : result) {
					getExperiments(workerId);
				}
			}
		});
	}

	private void getExperiments(String workerId) {
		service.getExperimentData(workerId, new AsyncCallback<List<Experiment>>() {

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
				ContentContainer.getInstance().setContent(new WorkersPage(worker));
			}
		});
	}
}
