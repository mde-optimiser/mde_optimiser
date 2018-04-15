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
	private Timestamp startTime;
	private Timestamp endTime;
	
	public Experiment() {}

	public Experiment(String experimentId, String workerId, String moptId, Timestamp startTime, Timestamp endTime) {
		super();
		setExperimentId(experimentId);
		setWorkerId(workerId);
		setMoptId(moptId);
		setStartTime(startTime);
		setEndTime(endTime);
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

	/** Returns elapsed time for active experiments and total time taken for finished experiments. */
	/*public long getTotalTimeTaken() {
		if (getEndTime() != null)
			return getEndTime().getTime() - getStartTime().getTime();
		else
			return new Date().getTime() - getStartTime().getTime();
	}

	public void setTotalTimeTaken(long totalTimeTaken) {
		this.totalTimeTaken = 0;
	}*/
}
