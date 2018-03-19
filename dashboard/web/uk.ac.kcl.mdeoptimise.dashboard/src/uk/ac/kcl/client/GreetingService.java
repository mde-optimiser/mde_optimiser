package uk.ac.kcl.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String greetServer(String name) throws IllegalArgumentException;
	List<String> getWorkerIds() throws Exception;
	List<String> getExperimentIds() throws Exception;
	List<Experiment> getExperimentData(String workerId) throws Exception;
	String getSolutions(int worker_id, String experiment_id) throws Exception;
}
