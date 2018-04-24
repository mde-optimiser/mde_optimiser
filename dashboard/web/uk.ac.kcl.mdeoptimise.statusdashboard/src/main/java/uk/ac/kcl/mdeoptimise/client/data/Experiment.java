package uk.ac.kcl.mdeoptimise.client.data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Encloses experiment data, such as start/end time, status etc. 
 * @author Tamara
 *
 */
public class Experiment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String experimentId;
	private String workerId;
	private String moptId;
	private int lastEvolutionNumber;
	private int totalEvaluations;
	private int totalPopulation;
	private Timestamp startTime;
	private Timestamp endTime;
	
	public Experiment() {}

	public Experiment(String experimentId, String workerId, int totalEvolutions, int totalPopulation, String moptId, Timestamp startTime, int lastEvolutionNumber, Timestamp endTime) {
		super();
		setExperimentId(experimentId);
		setWorkerId(workerId);
		setMoptId(moptId);
		setStartTime(startTime);
		setEndTime(endTime);
		setLastEvaluationNumber(lastEvolutionNumber);
		setTotalEvolutions(totalEvolutions);
		setTotalPopulation(totalPopulation);
	}

	public String getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(String experimentId) {
		this.experimentId = experimentId;
	}

	public String getWorkerId() {
		return workerId;
	}

	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}

	public String getMoptId() {
		return moptId;
	}

	public void setMoptId(String moptId) {
		this.moptId = moptId;
	}

	public int getTotalEvolutions() {
		return totalEvaluations;
	}

	public void setTotalEvolutions(int totalEvaluations) {
		this.totalEvaluations = totalEvaluations;
	}

	public int getTotalPopulation() {
		return totalPopulation;
	}

	public void setTotalPopulation(int totalPopulation) {
		this.totalPopulation = totalPopulation;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getLastEvolutionNumber() {
		return lastEvolutionNumber;
	}

	public void setLastEvaluationNumber(int lastEvolutionNumber) {
		this.lastEvolutionNumber = lastEvolutionNumber;
	}
}
