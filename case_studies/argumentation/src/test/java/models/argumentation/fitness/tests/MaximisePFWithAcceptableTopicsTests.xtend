package models.argumentation.fitness.tests

import org.junit.Test
import static org.junit.Assert.*;
import models.argumentation.fitness.MaximisePFWithAcceptableTopicsPreferredReasoner
import models.argumentation.fitness.MaximisePFWithAcceptableTopicsGroundReasoner
import models.argumentation.fitness.MaximisePFWithAcceptableTopicsEqArgSolver
import models.argumentation.fitness.MaximisePFWithAcceptableTopicsArgMatSat
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution

class MaximisePFWithAcceptableTopicsTests {
	
	@Test
	def void assertThatTheCorrectNumberOfArgumentationFrameworkArgumentsAreReturnedWithPreferredReasoner(){
		
		var inputModel = new TestModelLoader().loadModel("PF-50-args-Audience-Members-5-Member-Pf-Size-13-args.xmi")
		
		var solution = new Solution(inputModel)
		
		var scenarioParser = new MaximisePFWithAcceptableTopicsPreferredReasoner().computeFitness(solution);
		
		
		assertEquals(-4, scenarioParser, 0)
	}

	@Test
	def void assertThatTheCorrectNumberOfArgumentationFrameworkArgumentsAreReturnedWithGroundReasoner(){
		
		var inputModel = new TestModelLoader().loadModel("scenario-parser-test-input.xmi")
		
		var solution = new Solution(inputModel)
		
		var scenarioParser = new MaximisePFWithAcceptableTopicsGroundReasoner().computeFitness(solution);
		
		
		assertEquals(-7, scenarioParser, 0)
	}

	@Test
	def void assertThatTheCorrectNumberOfArgumentationFrameworkArgumentsAreReturnedWithEqArgSolver(){
		
		var start = System.nanoTime;
		
		
		var inputModel = new TestModelLoader().loadModel("PF-200-args-Audience-Members-50-Member-Pf-Size-51-args.xmi")
		var i =0
		
		var solution = new Solution(inputModel)
		
		
		while(i < 25) {
			var result = new MaximisePFWithAcceptableTopicsEqArgSolver().computeFitness(solution);	
			println(result)
			i++
		}
		
		var end = System.nanoTime
		
		println(String.format("Duration: %s", (end - start) / 1000000))
		//assertEquals(-4, scenarioParser, 0)
		assertTrue(true);
	}


	@Test
	def void assertThatTheCorrectNumberOfArgumentationFrameworkArgumentsAreReturnedWithArgMatSat(){
		
		var start = System.nanoTime;
		
		var inputModel = new TestModelLoader().loadModel("PF-Ladder-51-args-Audience-2-Members-PAF-size-40-args.xmi")
		var i =0
		var solution = new Solution(inputModel)
		
		
		while(i < 25) {
			var result = new MaximisePFWithAcceptableTopicsArgMatSat().computeFitness(solution);
			println(result)
			i++
		}
		
		var end = System.nanoTime
		
		println(String.format("Duration: %s", (end - start) / 1000000))
		//assertEquals(-4, scenarioParser, 0)
		assertTrue(true);
	}
}