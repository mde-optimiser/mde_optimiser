package uk.ac.kcl.ui.output

import java.util.List
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import java.util.Iterator
import org.eclipse.emf.henshin.model.Module
import org.eclipse.emf.ecore.EPackage
import java.util.Map

class MDEOBatch {
	
	public Integer id;
	public long duration;
	public List<MoeaOptimisationSolution> solutions;
	public Map<EPackage, List<Module>> generatedRules;
	
	new(Integer id, long duration, Iterator<MoeaOptimisationSolution> solutions, Map<EPackage, List<Module>> generatedRules){
		this.id = id;
		this.duration = duration;
		this.solutions = solutions.toList;
		this.generatedRules = generatedRules;
	}
}