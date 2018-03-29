package uk.ac.kcl.client;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Encloses experiment data, such as start/end time, status etc. 
 * @author Tamara
 *
 */
public class Solution implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String experimentId;
	private Map<String, Double> objectives = new LinkedHashMap<String, Double>();
	private Map<String, Double> constraints = new LinkedHashMap<String, Double>();
	
	public Solution() {}

	public Solution(String experimentId, Map<String, Double> objectives, Map<String,Double> constraints) {
		super();
		setExperimentId(experimentId);
		setObjectives(objectives);
		setConstraints(constraints);
	}
	
	private Map<String, Double> getConstraints() {
		return constraints;
	}

	private void setConstraints(Map<String, Double> constraints) {
		this.constraints = constraints;
	}

	private Map<String, Double> getObjectives() {
		return objectives;
	}

	private void setObjectives(Map<String, Double> objectives) {
		this.objectives = objectives;
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}
}
