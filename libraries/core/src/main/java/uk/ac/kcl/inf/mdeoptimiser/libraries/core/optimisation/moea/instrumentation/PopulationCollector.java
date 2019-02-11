package uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.instrumentation;

import java.util.ArrayList;

import org.moeaframework.analysis.collector.Accumulator;
import org.moeaframework.analysis.collector.AttachPoint;
import org.moeaframework.analysis.collector.Collector;
import org.moeaframework.core.EvolutionaryAlgorithm;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;

import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.moea.problem.MoeaOptimisationSolution;


/**
 * Collects the population from an {@link EvolutionaryAlgorithm}.
 */
public class PopulationCollector implements Collector {

	/**
	 * The algorithm instance used by this collector; or {@code null} if this 
	 * collector has not yet been attached.
	 */
	private final EvolutionaryAlgorithm algorithm;

	/**
	 * Constructs an unattached collector for recording the population used by
	 * an {@code EvolutionaryAlgorithm}.
	 */
	public PopulationCollector() {
		this(null);
	}

	/**
	 * Constructs a collector for recording the population used by the specified
	 * {@code EvolutionaryAlgorithm}.
	 * 
	 * @param algorithm the algorithm this collector records data from
	 */
	public PopulationCollector(EvolutionaryAlgorithm algorithm) {
		super();
		this.algorithm = algorithm;
	}

	@Override
	public AttachPoint getAttachPoint() {
		return AttachPoint.isSubclass(EvolutionaryAlgorithm.class).and(
				AttachPoint.not(AttachPoint.isNestedIn(
						EvolutionaryAlgorithm.class)));
	}

	@Override
	public Collector attach(Object object) {
		return new PopulationCollector(
				(EvolutionaryAlgorithm)object);
	}

	@Override
	public void collect(Accumulator accumulator) {
		ArrayList<Solution> list = new ArrayList<Solution>();
		Population population = algorithm.getPopulation();

		for (Solution solution : population) {
			
			Solution lightCopy = new MoeaOptimisationSolution(solution.getNumberOfObjectives(), solution.getNumberOfConstraints());
			lightCopy.setConstraints(solution.getConstraints());
			lightCopy.setObjectives(solution.getObjectives());
			
			list.add(lightCopy);
		}

		accumulator.add("Population", list);
	}

}