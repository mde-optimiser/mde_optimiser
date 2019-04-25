package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.termination.conditions

import org.moeaframework.core.TerminationCondition
import org.moeaframework.core.Algorithm
import org.moeaframework.core.Population
import org.apache.commons.math3.ml.distance.EuclideanDistance
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Parameter

class DeltaTerminationCondition implements TerminationCondition {
	
	double delta = 0d
	double deltaSteps = 0d;
	Algorithm algorithm;
	
	Population lastPopulation;
	double lastDistance;
	int unchangedDeltaSteps;
	
	new(Parameter deltaParameter, Parameter deltaStepsParameter){
		this.delta = Double.parseDouble(deltaParameter.value.numeric)
		setDeltaSteps(deltaStepsParameter)
	}
	
	def void setDeltaSteps(Parameter iterationsParameter) {
		
		if(iterationsParameter === null) {
			//Default to three iterations
			this.deltaSteps = 3
		} else {
			this.deltaSteps = Double.parseDouble(iterationsParameter.value.numeric)
		}
	}
	
	override initialize(Algorithm algorithm) {
		this.algorithm = algorithm;
	}
	
	override shouldTerminate(Algorithm algorithm) {
		if (lastPopulation === null) {
			
			lastPopulation = algorithm.getResult
			unchangedDeltaSteps = 0
			
			return false
			
		} else {
			
			if(isDeltaThresholdChange(algorithm.result, lastPopulation)) {
				this.unchangedDeltaSteps++	
				return this.unchangedDeltaSteps > deltaSteps
				
			} else {
				lastPopulation = algorithm.result
				this.unchangedDeltaSteps = 0
				return false
			}
			
		}
	}
	
	
	private def boolean isDeltaThresholdChange(Population currentPopulation, Population lastPopulation) {
		
		val distanceMeasure = new EuclideanDistance();
		
		if(currentPopulation.isEmpty() || lastPopulation.isEmpty()) {
			return false
		} 
		
		val totalDistance = currentPopulation.fold(0d)[totalDistance, currentSolution |
			
			totalDistance + lastPopulation.fold(0d)[distance, lastSolution |
				
				var currentDistance = 0d;
				
				if(this.algorithm.problem.numberOfConstraints > 0) {
					currentDistance += distanceMeasure.compute(lastSolution.getConstraints(), currentSolution.getConstraints());
				}
				
				currentDistance += distanceMeasure.compute(lastSolution.getObjectives(), currentSolution.getObjectives());
				
				return distance + currentDistance
			]
		]
		
		if(totalDistance === 0d){
			return true;
		}
	
		var delta = (this.lastDistance - totalDistance) /  totalDistance * 100
	
		this.lastDistance = totalDistance
		
		if(Math.abs(delta) < this.delta) {
			return true;
		}
	
		return false;	
	}
	
	
}
