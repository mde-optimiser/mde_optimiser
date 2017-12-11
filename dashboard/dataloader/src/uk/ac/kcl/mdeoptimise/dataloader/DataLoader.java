/**
 * 
 */
package uk.ac.kcl.mdeoptimise.dataloader;

/**
 * Listens for new data from the queue and updated the database.
 * 
 * @author Tamara
 *
 */
public class DataLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Database.insertIntoDatabase("fifth");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
