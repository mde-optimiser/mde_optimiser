package uk.ac.kcl.ui.output

import java.util.List
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import java.util.Iterator

class MDEOBatch {
	
	public Integer id;
	public long duration;
	public List<MoeaOptimisationSolution> solutions;
	
	new(Integer id, long duration, Iterator<MoeaOptimisationSolution> solutions){
		this.id = id;
		this.duration = duration;
		this.solutions = solutions.toList;
	}
}