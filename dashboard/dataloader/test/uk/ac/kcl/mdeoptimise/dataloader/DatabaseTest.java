package uk.ac.kcl.mdeoptimise.dataloader;

import org.junit.Test;

/**
 * Tests {@link Database} functionality without having to run an actual MDEO experiment, by mimicking its behaviour.
 * Note: requires a connection to a database server.
 * <p>
 * TODO (tamara): Fake a database server.
 * @author Tamara 
 *
 */
public class DatabaseTest {
	
	private static final String WORKER_REGISTER_MESSAGE = "{\"WORKER_REGISTER\":{\"metamodel\":\"architectureCRA.ecore\","
			+ "\"worker_name\":\"tamara\",\"worker_id\":\"12345\",\"model\":\"TTC_InputRDG_C.xmi\","
			+ "\"objectives\":[{\"name\":\"MinimiseCoupling\",\"type\":\"java\"}],\"evolutions\":2,"
			+ "\"constraints\":[{\"name\":\"MinimiseClasslessFeatures\",\"type\":\"java\"}],\"population\":30,"
			+ "\"experiment_id\":\"src\\/models\\/cra\\/\"}}";

	private static final String FINAL_SOLUTION_MESSAGE = "{\"FINAL_SOLUTION\":{\"experiment_id\":\"src\\/models\\/cra\\/\","
			+ "\"run_id\":1,\"solutions\":[{\"objectives\":[{\"name\":\"MinimiseCoupling\",\"value\":-1.0}],"
			+ "\"constraints\":[{\"name\":\"MinimiseClasslessFeatures\",\"value\":32.0}]}],\"time_taken\":1000,"
			+ "\"worker_id\":\"12345\"}}";

	@Test
	public void testInsertWorkerRegisterIntoDatabase() {
		try {
			Database.insertIntoDatabase(WORKER_REGISTER_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsertFinalSolutionIntoDatabase() {
		try {
			Database.insertIntoDatabase(FINAL_SOLUTION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
