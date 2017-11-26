package uk.ac.kcl.ui.output

import java.util.List
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution
import java.util.Iterator
import uk.ac.kcl.interpreter.OptimisationInterpreter
import org.eclipse.emf.ecore.EObject

class MDEOBatch {
	
	public Integer id;
	public long duration;
	public List<MoeaOptimisationSolution> solutions;
	public EObject initialModel;
	
	new(Integer id, long duration, Iterator<MoeaOptimisationSolution> solutions, OptimisationInterpreter optimisationInterpreter){
		this.id = id;
		this.duration = duration;
		this.solutions = solutions.toList;
		this.initialModel = optimisationInterpreter.modelProvider.initialModel
	}
}