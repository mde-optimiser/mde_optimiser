package uk.ac.kcl.mdeoptimise.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import uk.ac.kcl.mdeoptimise.client.constants.PageConstants.SolutionType;
import uk.ac.kcl.mdeoptimise.client.data.Experiment;
import uk.ac.kcl.mdeoptimise.client.data.Solution;

/**
 * The async counterpart of <code>MDEOService</code>.
 */
public interface MDEOServiceAsync {
	void getWorkerIds(AsyncCallback<List<String>> callback);
	void getExperimentIds(AsyncCallback<List<String>> callback);
	void getExperimentData(String workerId, AsyncCallback<List<Experiment>> callback);
	void getSolutions(String experiment_id, SolutionType solution_type, AsyncCallback<List<Solution>> asyncCallback);
}
