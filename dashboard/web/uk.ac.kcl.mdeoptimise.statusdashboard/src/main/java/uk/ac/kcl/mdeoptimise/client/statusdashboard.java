package uk.ac.kcl.mdeoptimise.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import uk.ac.kcl.mdeoptimise.client.constants.PageConstants;
import uk.ac.kcl.mdeoptimise.client.data.Experiment;
import uk.ac.kcl.mdeoptimise.client.data.Worker;
import uk.ac.kcl.mdeoptimise.client.pages.WorkersPage;
import uk.ac.kcl.mdeoptimise.client.services.MDEOService;
import uk.ac.kcl.mdeoptimise.client.services.MDEOServiceAsync;
import uk.ac.kcl.mdeoptimise.client.widgets.NavBar;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class statusdashboard implements EntryPoint  {
	
	private final MDEOServiceAsync service = GWT.create(MDEOService.class);

	private List<Worker> workers = new ArrayList<Worker>();
	private DialogBox dialogBox;
	private Button closeButton;
	private HTML serverResponseLabel;
	private int totalNumOfWorkers;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		getWorkerIdList();
		
		RootPanel contentRoot = RootPanel.get("header");
        contentRoot.clear();
        contentRoot.add(new NavBar());

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
				serverResponseLabel.setHTML(PageConstants.SERVER_ERROR + caught.getMessage());
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<String> result) {
				totalNumOfWorkers = result.size();
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
				serverResponseLabel.setHTML(PageConstants.SERVER_ERROR + caught.getMessage());
				dialogBox.center();
				closeButton.setFocus(true);
			}

			@Override
			public void onSuccess(List<Experiment> result) {
				Worker worker = new Worker(workerId);
				worker.setExperiments(result);
				workers.add(worker);
				if (workers.size() == totalNumOfWorkers)
					ContentContainer.getInstance().setContent(new WorkersPage(workers));
			}
		});
	}
}
