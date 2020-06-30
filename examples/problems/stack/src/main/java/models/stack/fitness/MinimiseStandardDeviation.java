package models.stack.fitness;

import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import stack.Stack;
import stack.StackModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseStandardDeviation  implements IGuidanceFunction {

  @Override
	public double computeFitness(Solution solution) {
		
		var fitness = ((StackModel) solution.getModel())
            .getStacks()
            .stream().map(Stack::getLoad).mapToDouble(Integer::doubleValue);

		return new StandardDeviation().evaluate(fitness.toArray());
	}
	
	@Override
  public String getName() {
		return "Minimise Standard Deviation";
	}
}