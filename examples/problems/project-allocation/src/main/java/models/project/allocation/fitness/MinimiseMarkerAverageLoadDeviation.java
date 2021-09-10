package models.project.allocation.fitness;

import java.util.ArrayList;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseMarkerAverageLoadDeviation extends AbstractFitnessFunction
    implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var sd = new StandardDeviation();

    var markerLoad = new ArrayList<Double>();

    for (var academic : model.getAcademics()) {

      if (academic.getSecondMarker() != null) {
        markerLoad.add((double) academic.getSecondMarker().size());
      } else {
        markerLoad.add(0d);
      }
    }

    var standardDeviation = sd.evaluate(ArrayUtils.toPrimitive(markerLoad.toArray(new Double[0])));

    System.out.println("Marker load sd: " + standardDeviation);

    return standardDeviation;
  }

  @Override
  public String getName() {
    return "Minimise Number Of Marking Teams";
  }
}
