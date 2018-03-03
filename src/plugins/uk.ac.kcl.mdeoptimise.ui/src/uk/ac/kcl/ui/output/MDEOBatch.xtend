package uk.ac.kcl.ui.output

import java.util.List
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import java.util.Iterator
import org.eclipse.emf.henshin.model.Module

class MDEOBatch {
	
	public Integer id;
	public long duration;
	public List<MoeaOptimisationSolution> solutions;
	public List<Module> generatedRules;
	
	new(Integer id, long duration, Iterator<MoeaOptimisationSolution> solutions, List<Module> generatedRules){
		this.id = id;
		this.duration = duration;
		this.solutions = solutions.toList;
		this.generatedRules = generatedRules;
	}
}