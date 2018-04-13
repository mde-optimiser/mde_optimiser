package uk.ac.kcl.client.data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Encloses solution data, such as its objectives and constraints. 
 * @author Tamara
 *
 */
public class Solution implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String experimentId;
	private String solutionId;
	private int evolutionNumber;
	private Map<String, Double> objectives = new LinkedHashMap<String, Double>();
	private Map<String, Double> constraints = new LinkedHashMap<String, Double>();
	
	public Solution() {}

	public Solution(String experimentId, String solutionId, int evolutionNumber, Map<String, Double> objectives, Map<String,Double> constraints) {
		super();
		setSolutionId(solutionId);
		setExperimentId(experimentId);
		setEvolutionNumber(evolutionNumber);
		setObjectives(objectives);
		setConstraints(constraints);
	}
	
	public Map<String, Double> getConstraints() {
		return constraints;
	}

	public void setConstraints(Map<String, Double> constraints) {
		this.constraints = constraints;
	}

	public Map<String, Double> getObjectives() {
		return objectives;
	}

	public void setObjectives(Map<String, Double> objectives) {
		this.objectives = objectives;
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}

	public int getEvolutionNumber() {
		return evolutionNumber;
	}

	public void setEvolutionNumber(int evolutionNumber) {
		this.evolutionNumber = evolutionNumber;
	}
	
	public String getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(String solutionId) {
		this.solutionId = solutionId;
	}
}
