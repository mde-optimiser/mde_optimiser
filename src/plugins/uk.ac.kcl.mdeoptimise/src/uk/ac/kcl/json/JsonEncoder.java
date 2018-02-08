package uk.ac.kcl.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

	/**
	 * Generate JSON text used to send a worker registration message.
	 *
	 * The message looks like this:
	 * {"workerId":"12345",
	 * "workerName":"tamara",
	 * "experimentId":"src\/models\/cra\/",
	 * "metamodel":"architectureCRA.ecore",
	 * "model":"TTC_InputRDG_C.xmi",
	 * "objectives":[
	 * 		{"name":"MinimiseCoupling","type":"java"}
	 * ],
	 * "constraints":[
	 * 		{"name":"MinimiseClasslessFeatures","type":"java"}
	 * ],
	 * "evolutions":2,
	 * "population":30}
	 *
	 * @param optimisationModel used to extract relevant data, such as model, metamodel etc.
	 * @return String representation of a JSONObject.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static String generateWorkerRegistrationText(Optimisation optimisationModel) throws IOException {
		// TODO(tamara): what are the worker name and ID?
		JSONObject obj = new JSONObject();
		obj.put("workerName", "tamara");
		obj.put("workerId", "12345");
		obj.put("experimentId", optimisationModel.getBasepath().getLocation());	// experiment ID is the base path?
		obj.put("evolutions", new Integer(optimisationModel.getOptimisation().getAlgorithmEvolutions()));
		obj.put("population", new Integer(optimisationModel.getOptimisation().getAlgorithmPopulation()));
		obj.put("model", optimisationModel.getModel().getLocation());
		obj.put("metamodel", optimisationModel.getMetamodel().getLocation());

		// insert an array of objectives
		obj.put("objectives", generateObjectivesJsonArray(optimisationModel.getObjectives()));
		// insert an array of constraints
		obj.put("constraints", generateConstraintsJsonArray(optimisationModel.getConstraints()));

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);

		return out.toString();
	}

	/**
	 * Generate JSON text used to send a final solution message.
	 *
	 * The message looks like this:
	 * {"worker-id":"12345",
	 * "experimentId":"src\/models\/cra\/",
	 * "run-id":"1",
	 * "time-taken":1000,
	 * "solutions":[
	 * 		{"objectives":[
	 * 				{"name":"MinimiseCoupling","value":-1.0}
	 * 		],
	 * 		"constraints":[
	 * 				{"name":"MinimiseClasslessFeatures","value":32.0}
	 * 		]},
	 * ]}
	 *
	 * @param optimisationModel used to extract relevant data, such as basepath location.
	 * @param solutions to be represented as a JSON message.
	 * @return String representation of a JSONObject.
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static String generateFinalSolutionText(Optimisation optimisationModel, Iterator<MoeaOptimisationSolution> solutions) throws IOException {
		JSONObject obj = new JSONObject();
		obj.put("worker-id", "12345");
		obj.put("experiment-id", optimisationModel.getBasepath().getLocation());	// experiment ID is the base path?
		obj.put("run-id", 1);	// TODO(tamara): What is the run ID?
		obj.put("time-taken", 1000);	// TODO(tamara): Calculate time taken.

		// insert an array of solutions
		obj.put("solutions", generateSolutionsJsonArray(solutions));

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);

		return out.toString();
	}

	@SuppressWarnings("unchecked")
	private static JSONArray generateConstraintsJsonArray(EList<ConstraintInterpreterSpec> constraints) {
		JSONArray constraintsArray = new JSONArray();
		for(ConstraintInterpreterSpec constraint : constraints) {
			JSONObject constraintJSON = new JSONObject();
			constraintJSON.put("name", constraint.getConstraintName());
			constraintJSON.put("type", constraint.getConstraintType());
			constraintsArray.add(constraintJSON);
		}
		return constraintsArray;
	}

	@SuppressWarnings("unchecked")
	private static JSONArray generateObjectivesJsonArray(EList<ObjectiveInterpreterSpec> objectives) {
		JSONArray objectivesArray = new JSONArray();
		for(ObjectiveInterpreterSpec objective : objectives) {
			JSONObject objectiveJSON = new JSONObject();
			objectiveJSON.put("name", objective.getObjectiveName());
			objectiveJSON.put("type", objective.getObjectiveType());
			objectivesArray.add(objectiveJSON);
		}
		return objectivesArray;
	}

	@SuppressWarnings("unchecked")
	private static JSONArray generateFitnessValuesJsonArray(LinkedHashMap<String, Double> nameValueMap) {
		JSONArray fitnessValuesArray = new JSONArray();
		for(Map.Entry<String, Double> nameValueEntry : nameValueMap.entrySet()) {
			JSONObject fitnessValueJSON = new JSONObject();
			fitnessValueJSON.put("name", nameValueEntry.getKey());
			fitnessValueJSON.put("value", nameValueEntry.getValue());
			fitnessValuesArray.add(fitnessValueJSON);
		}
		return fitnessValuesArray;
	}

	@SuppressWarnings("unchecked")
	private static JSONArray generateSolutionsJsonArray(Iterator<MoeaOptimisationSolution> solutions) {
		JSONArray solutionsArray = new JSONArray();
		while(solutions.hasNext()) {
			MoeaOptimisationSolution solution = solutions.next();
			JSONObject solutionJSON = new JSONObject();
			solutionJSON.put("objectives", generateFitnessValuesJsonArray(solution.getFormattedObjectives()));
			solutionJSON.put("constraints", generateFitnessValuesJsonArray(solution.getFormattedConstraints()));
			solutionsArray.add(solutionJSON);
		}
		return solutionsArray;
	}
}
