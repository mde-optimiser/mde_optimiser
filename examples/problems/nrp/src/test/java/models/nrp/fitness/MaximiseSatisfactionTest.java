package models.nrp.fitness;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import models.nrp.fitness.MaximiseSatisfaction;

public class MaximiseSatisfactionTest {

	final private static double DELTA = 1e-3;
	private EObject model;
	private MaximiseSatisfaction objective = new MaximiseSatisfaction();
	
//	@Rule
//	public TestWatcher watcher = new TestWatcher() {
//	   protected void starting(Description description) {
//	      System.out.println("Starting test: " + description.getMethodName());
//	   }
//	   
//	   protected void finished(Description description) {
//		      System.out.println();
//		   }
//	};
//	
//	@Test
//	@DisplayName("Get all valuations of the dependencies of a requirement. ")
//	public void testCollectingValuationsOfHierarchicalRequirement() {
//		model = ModelLoadHelper.loadModel("simple-dependencies.xmi");
//		EObject customer = objective.getReferenceFeature(model, "customers").iterator().next();
//		EObject requirement = objective.getReferenceFeature(model, "requirements").iterator().next();
//		List<Double> expected = Arrays.asList(4.0, 1.0);
//		
//		List<Double> actual = new ArrayList<Double>();
//		for (EObject obj : objective.getDependencyValuations(requirement, customer)) {
//			actual.add((Double)objective.getFeature(obj, "value"));
//		}
//		assertIterableEquals(expected, actual);
//		
//	}
//	
//	@Test
//	@DisplayName("Fitness: Direct requirement has multiple valuations of the same customer. Only the highest should be considered")
//	public void testFitnessOfMultiValuationsWithoutDependencies() {
//		model = ModelLoadHelper.loadModel("direct-requirement-with-multiple-valuations-for-same-customer.xmi");
//		assertEquals(-0.9d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Requirement has a dependency with multiple valuations of the same customer. Only the highest should be considered.")
//	public void testFitnessOfMultiValuationsWithDependencies() {
//		model = ModelLoadHelper.loadModel("dependencies-with-multiple-valuations-for-same-customer.xmi");
//		assertEquals(-0.9d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Direct requirement has multiple partial realisations. Only the highest should be considered.")
//	public void testMultipleRealisations() {
//		model = ModelLoadHelper.loadModel("multiple-realisations.xmi");
//		assertEquals(-0.8d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Requirement has multiple dependencies with different valuations.")
//	public void testSimpleDependencies() {
//		model = ModelLoadHelper.loadModel("simple-dependencies.xmi");
//		assertEquals(-0.9d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: No artifact is selected for the solution.")
//	public void testEmptySolution() {
//		model = ModelLoadHelper.loadModel("empty-solution.xmi");
//		assertEquals(0.0d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Artifact of single artifact realisation is not selected.")
//	public void testSingleArtifactRealisationMissingArtifact() {
//		model = ModelLoadHelper.loadModel("single-artifact-realisation-missing-artifact.xmi");
//		assertEquals(-0.4d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Artifact of multiple artifact realisation is not selected.")
//	public void testMultipleArtifactRealisationMissingArtifact() {
//		model = ModelLoadHelper.loadModel("multiple-artifact-realisation-missing-artifact.xmi");
//		assertEquals(-0.5d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Artifact in artifact hierarchy of realisation is not selected.")
//	public void testRealisationMissingArtifactInArtifactHierarchy() {
//		model = ModelLoadHelper.loadModel("realisation-missing-artifact-in-hierarchy.xmi");
//		assertEquals(0.0d, objective.computeFitness(model), DELTA);
//	}
//	
//	@Test
//	@DisplayName("Fitness: Multiple customers have different importance.")
//	public void testMultipleCustomersWithDifferentImportance() {
//		model = ModelLoadHelper.loadModel("multiple-customers-with-different-importance.xmi");
//		assertEquals(-5.0d, objective.computeFitness(model), DELTA);
//	}

}
