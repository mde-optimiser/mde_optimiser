package models.tsp.fitness;

import java.util.ArrayList;
import java.util.List;
import models.tsp.fitness.TSP.City;
import models.tsp.fitness.TSP.Distance;
import models.tsp.fitness.TSP.Region;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseDistanceTravelled implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (Region) solution.getModel();

    var distance = 0d;

    var startCity = model.getCities().stream().filter(City::isStart).findFirst();
    var city = startCity.get();

    var visited = new ArrayList<City>();

    while (city.getNext() != null && !visited.contains(city.getNext())) {
      var from = city;
      var to = city.getNext();

      if (!visited.contains(city)) {
        visited.add(city);
      }

      distance += this.getDistance(model.getDistances(), from, to);
      city = city.getNext();
    }

    return distance;
  }

  public double getDistance(List<Distance> distances, City from, City to) {

    var distance =
        distances.stream()
            .filter(d -> d.getFrom().equals(from) && d.getTo().equals(to))
            .findFirst();

    if (distance.isPresent()) {
      return distance.get().getDistance();
    }

    return 9999999999d;
  }

  @Override
  public String getName() {
    return "Minimise Distance Travelled";
  }
}
