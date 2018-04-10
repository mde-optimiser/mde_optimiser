package uk.ac.kcl.optimisation.moea;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.kcl.json.JsonEncoder;
import uk.ac.kcl.mdeoptimise.Optimisation;
import uk.ac.kcl.mdeoptimise.dashboard.api.sender.Sender;

/**
 * Collects intermediate solutions per evolution number and sends them to the queue in batches (for each evolution).
 * 
 * @author Tamara
 *
 */
public class IntermediateSolutionsCollector {
	
	private Map<Integer, List<MoeaOptimisationSolution>> evolutionNumToSolutions = new HashMap<Integer, List<MoeaOptimisationSolution>>();
	private List<Thread> threads = new ArrayList<Thread>(); // collection of Threads that are sending messages to queue
	private Sender sender = new Sender();
	private Optimisation optimisationModel;
	private String experimentId;
	private int numOfSolutions = 0; // number of solutions for the latest evolution
	private int totalEvolutions;
	private int populationSize;
	private int evolutionCounter = 1;

	public IntermediateSolutionsCollector(Optimisation optimisationModel, String experimentId) {
		this.totalEvolutions = optimisationModel.getOptimisation().getAlgorithmEvolutions();
		this.populationSize = optimisationModel.getOptimisation().getAlgorithmPopulation();
		this.optimisationModel = optimisationModel;
		this.experimentId = experimentId;
	}

	/**
	 * Associates the given solution to the current evolution run. Each time an evolution run is complete, 
	 * a collection of intermediate solutions associated with the evolution are going to be sent to the queue.
	 * 
	 * @param solution intermediate solution to be sent to the queue
	 */
	void addIntermediateSolution(MoeaOptimisationSolution solution) {
		numOfSolutions++;
		
		// Initialise the ArrayList for current evolution number.
		if (!evolutionNumToSolutions.containsKey(evolutionCounter))
			evolutionNumToSolutions.put(evolutionCounter, new ArrayList<>());
		
		// Add the given solution to the ArrayList for current evolution number.
		evolutionNumToSolutions.get(evolutionCounter).add(solution);
		System.out.println("Run number: " + evolutionCounter + ". Solutions found: " + evolutionNumToSolutions.get(evolutionCounter).size());
		
		checkEvolutionComplete();
		checkExperimentComplete();
	}

	/**
	 * If the evolution is finished, then start a new <code>Thread</code> that will send a message to the queue  
	 * containing intermediate solutions for the latest evolution.
	 * <p>
	 * Note: do not send a message if this is the last evolution (those solutions are going to be sent as part of 
	 * a final solution message).
	 */
	private void checkEvolutionComplete() {
		if(numOfSolutions == populationSize && evolutionCounter != totalEvolutions) {
			// Start a new Thread for the evolution that just finished.
			Thread evolutionThread = new EvolutionThread(evolutionCounter);
			evolutionThread.start();
			threads.add(evolutionThread);
			
			// Start a new evolution and reset number of solutions counter.
			System.out.println("[MDEO] Finished run number " + evolutionCounter);
			evolutionCounter++;
			numOfSolutions = 0;
		}
	}

	/**
	 * If the experiment execution is finished, then wait for all threads that are sending messages to the queue to 
	 * terminate. Or do nothing otherwise.
	 */
	private void checkExperimentComplete() {
		if (evolutionCounter == totalEvolutions) {
			threads.stream().forEach(thread -> {
				try {
					thread.join();
				} catch (InterruptedException e) {
					System.out.println("[MDEO] A thread sending a message to the queue for evolution number " + 
							evolutionCounter + " has failed.");
				}
			});
		}
	}

	/**
	 * Thread that sends a message to the queue containing intermediate solutions for a given evolution.
	 */
	private class EvolutionThread extends Thread {

		int evolutionNumber;

		public EvolutionThread(int evolutionNumber) {
			this.evolutionNumber = evolutionNumber;
		}

		@Override
		public void run() {
			String  message = "";
			try {
				message = JsonEncoder.generateIntermediateSolutionText(
						optimisationModel, 
						evolutionNumToSolutions.get(evolutionNumber).stream().iterator(), 
						experimentId, 
						evolutionNumber);
			} catch (IOException e) {
				e.printStackTrace();
			}
			sender.sendMessage(message);
			System.out.println("[MDEO] intermediate solution message sent: " + message);
		}
	}
}
