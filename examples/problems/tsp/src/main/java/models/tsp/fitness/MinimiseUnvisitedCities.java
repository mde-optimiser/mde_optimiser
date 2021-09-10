package models.tsp.fitness;

import java.util.ArrayList;
import models.tsp.fitness.TSP.City;
import models.tsp.fitness.TSP.Region;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseUnvisitedCities implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (Region) solution.getModel();
    var totalCities = model.getCities().size();
    //    var visitedCities = model.getCities().stream().filter(c -> c.getIsVisited() !=
    // null).count();
    //    var processed = new ArrayList<Stop>();
    //    var stop = model.getStops().get(0);
    //
    //    while(stop.getNext() != null && !processed.contains(stop.getNext())) {
    //      processed.add(stop);
    //
    //      stop = stop.getNext();
    //
    //    }

    var startCity = model.getCities().stream().filter(City::isStart).findFirst();
    var city = startCity.get();
    var visited = new ArrayList<City>();

    while (city.getNext() != null && !visited.contains(city.getNext())) {
      if (!visited.contains(city)) {
        visited.add(city);
      }
      city = city.getNext();
    }

    if (!visited.contains(city)) {
      visited.add(city);
    }

    return visited.size() - totalCities;
  }

  @Override
  public String getName() {
    return "Minimise Unvisited Cities";
  }
}
