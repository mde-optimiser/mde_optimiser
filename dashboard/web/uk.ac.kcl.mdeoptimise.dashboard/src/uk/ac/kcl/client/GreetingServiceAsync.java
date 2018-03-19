package uk.ac.kcl.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void getWorkerIds(AsyncCallback<List<String>> callback);
	void getExperimentIds(AsyncCallback<List<String>> callback);
	void getExperimentData(String workerId, AsyncCallback<List<Experiment>> callback);
	void getSolutions(int worker_id, String experiment_id, AsyncCallback<String> asyncCallback);
}
