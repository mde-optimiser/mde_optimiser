package uk.ac.kcl.client.data;

import java.util.List;

/**
 * A worker that contains currently running, as well as past experiments.
 * @author Tamara
 *
 */
public class Worker {
	private String macAddress; // the worker ID
	private List<Experiment> experiments; // experiments running on this worker

	public Worker(String macAddress) {
		this.macAddress = macAddress;
		// TODO Auto-generated constructor stub
	}

	public Worker(String macAddress, List<Experiment> experiments) {
		super();
		this.macAddress = macAddress;
		this.experiments = experiments;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public List<Experiment> getExperiments() {
		return experiments;
	}

	public void setExperiments(List<Experiment> experiments) {
		this.experiments = experiments;
	}

	public void addExperiment(Experiment experiment) {
		this.experiments.add(experiment);
	}
}
