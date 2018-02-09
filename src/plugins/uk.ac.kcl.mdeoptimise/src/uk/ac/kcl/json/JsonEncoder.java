package uk.ac.kcl.json;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.json.JSONArray;
import org.json.JSONObject;

import uk.ac.kcl.mdeoptimise.ConstraintInterpreterSpec;
import uk.ac.kcl.mdeoptimise.ObjectiveInterpreterSpec;
import uk.ac.kcl.mdeoptimise.Optimisation;
import uk.ac.kcl.optimisation.moea.MoeaOptimisationSolution;

/**
 * Helper class containing methods to generate JSON text.
 *
 * @author Tamara
 *
 */
public class JsonEncoder {
	
	private enum MessageType {WORKER_REGISTER, FINAL_SOLUTION, INTERMEDIATE_SOLUTION}

	/**
	 * Generate JSON text used to send a worker registration message.
	 *
	 * The message looks like this:
	 * <pre>
	 * {"WORKER_REGISTER":
	 * 		{"worker_id":"12345",
	 * 		"worker_name":"tamara",
	 * 		"experiment_id":"src\/models\/cra\/",
	 * 		"metamodel":"architectureCRA.ecore",
	 * 		"model":"TTC_InputRDG_C.xmi",
	 * 		"objectives":[
	 * 			{"name":"MinimiseCoupling","type":"java"}
	 * 		],
	 * 		"constraints":[
	 * 			{"name":"MinimiseClasslessFeatures","type":"java"}
	 * 		],
	 * 		"evolutions":2,
	 * 		"population":30}
	 * }
	 * </pre>
	 * @param optimisationModel used to extract relevant data, such as model, metamodel etc.
	 * @return String representation of a JSONObject.
	 * @throws IOException
	 */
	public static String generateWorkerRegistrationText(Optimisation optimisationModel) throws IOException {
		// TODO(tamara): what are the worker name and ID?
		JSONObject workerJSON = new JSONObject();
		workerJSON.put("worker_name", "tamara");
		workerJSON.put("worker_id", "12345");
		workerJSON.put("experiment_id", optimisationModel.getBasepath().getLocation());	// experiment ID is the base path?
		workerJSON.put("evolutions", new Integer(optimisationModel.getOptimisation().getAlgorithmEvolutions()));
		workerJSON.put("population", new Integer(optimisationModel.getOptimisation().getAlgorithmPopulation()));
		workerJSON.put("model", optimisationModel.getModel().getLocation());
		workerJSON.put("metamodel", optimisationModel.getMetamodel().getLocation());

		// insert an array of objectives
		workerJSON.put("objectives", generateObjectivesJsonArray(optimisationModel.getObjectives()));
		// insert an array of constraints
		workerJSON.put("constraints", generateConstraintsJsonArray(optimisationModel.getConstraints()));
		
		// construct a final message with the message type
		JSONObject messageJSON = new JSONObject();
		messageJSON.put(MessageType.WORKER_REGISTER.toString(), workerJSON);

		return messageJSON.toString();
	}

	/**
	 * Generate JSON text used to send a final solution message.
	 *
	 * The message looks like this:
	 * <pre>
	 * {"FINAL_SOLUTION":
	 * 		{"worker_id":"12345",
	 * 		"experiment_id":"src\/models\/cra\/",
	 * 		"run_id":"1",
	 * 		"time_taken":1000,
	 * 		"solutions":[
	 * 			{"objectives":[
	 * 				{"name":"MinimiseCoupling","value":-1.0}
	 * 			],
	 * 			"constraints":[
	 * 				{"name":"MinimiseClasslessFeatures","value":32.0}
	 * 			]},
	 * ]}
	 * }
	 * </pre>
	 * @param optimisationModel used to extract relevant data, such as base path location.
	 * @param solutions to be represented as a JSON message.
	 * @return String representation of a JSONObject.
	 * @throws IOException
	 */
	public static String generateFinalSolutionText(Optimisation optimisationModel,
			Iterator<MoeaOptimisationSolution> solutions) throws IOException {
		JSONObject solutionJSON = new JSONObject();
		solutionJSON.put("worker_id", "12345");
		solutionJSON.put("experiment_id", optimisationModel.getBasepath().getLocation());
		solutionJSON.put("run_id", 1); // TODO(tamara): What is the run ID?
		solutionJSON.put("time_taken", 1000); // TODO(tamara): Calculate time taken.

		// insert an array of solutions
		solutionJSON.put("solutions", generateSolutionsJsonArray(solutions));

		// construct a final message with the message type
		JSONObject messageJSON = new JSONObject();
		messageJSON.put(MessageType.FINAL_SOLUTION.toString(), solutionJSON);

		return messageJSON.toString();
	}

	private static JSONArray generateConstraintsJsonArray(EList<ConstraintInterpreterSpec> constraints) {
		JSONArray constraintsArray = new JSONArray();
		for (ConstraintInterpreterSpec constraint : constraints) {
			JSONObject constraintJSON = new JSONObject();
			constraintJSON.put("name", constraint.getConstraintName());
			constraintJSON.put("type", constraint.getConstraintType());
			constraintsArray.put(constraintJSON);
		}
		return constraintsArray;
	}

	private static JSONArray generateObjectivesJsonArray(EList<ObjectiveInterpreterSpec> objectives) {
		JSONArray objectivesArray = new JSONArray();
		for (ObjectiveInterpreterSpec objective : objectives) {
			JSONObject objectiveJSON = new JSONObject();
			objectiveJSON.put("name", objective.getObjectiveName());
			objectiveJSON.put("type", objective.getObjectiveType());
			objectivesArray.put(objectiveJSON);
		}
		return objectivesArray;
	}

	private static JSONArray generateFitnessValuesJsonArray(LinkedHashMap<String, Double> nameValueMap) {
		JSONArray fitnessValuesArray = new JSONArray();
		for (Map.Entry<String, Double> nameValueEntry : nameValueMap.entrySet()) {
			JSONObject fitnessValueJSON = new JSONObject();
			fitnessValueJSON.put("name", nameValueEntry.getKey());
			fitnessValueJSON.put("value", nameValueEntry.getValue());
			fitnessValuesArray.put(fitnessValueJSON);
		}
		return fitnessValuesArray;
	}

	private static JSONArray generateSolutionsJsonArray(Iterator<MoeaOptimisationSolution> solutions) {
		JSONArray solutionsArray = new JSONArray();
		while (solutions.hasNext()) {
			MoeaOptimisationSolution solution = solutions.next();
			JSONObject solutionJSON = new JSONObject();
			solutionJSON.put("objectives", generateFitnessValuesJsonArray(solution.getFormattedObjectives()));
			solutionJSON.put("constraints", generateFitnessValuesJsonArray(solution.getFormattedConstraints()));
			solutionsArray.put(solutionJSON);
		}
		return solutionsArray;
	}
}
