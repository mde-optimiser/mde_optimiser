package uk.ac.kcl.mdeoptimise.client.services;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import uk.ac.kcl.mdeoptimise.client.constants.PageConstants.SolutionType;
import uk.ac.kcl.mdeoptimise.client.data.Experiment;
import uk.ac.kcl.mdeoptimise.client.data.Solution;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("mdeo")
public interface MDEOService extends RemoteService {
	List<String> getWorkerIds() throws Exception;
	List<String> getExperimentIds() throws Exception;
	List<Experiment> getExperimentData(String workerId) throws Exception;
	List<Solution> getSolutions(String experiment_id, SolutionType solution_type) throws Exception;
}
