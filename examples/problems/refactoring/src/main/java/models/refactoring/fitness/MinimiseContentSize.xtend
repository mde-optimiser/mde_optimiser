package models.refactoring.fitness


import uk.ac.kcl.interpreter.guidance.Solution
import uk.ac.kcl.interpreter.IGuidanceFunction
import at.ac.tuwien.big.momot.examples.refactoring.refactoring.RefactoringModel

class MinimiseContentSize implements IGuidanceFunction {

	override double computeFitness(Solution solution) {

		var refactoring = solution.model as RefactoringModel		
		val fitness = refactoring.properties.length + refactoring.entities.size() * 1.1
		
		println("Fitness: " + fitness)
		
		return fitness
	}
	
	override getName() {
		return "Minimise Content Size"
	}	
}