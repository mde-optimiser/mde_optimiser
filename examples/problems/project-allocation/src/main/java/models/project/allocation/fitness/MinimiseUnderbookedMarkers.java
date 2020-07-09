package models.project.allocation.fitness;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseUnderbookedMarkers implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var underbookedAcademics =
        model.getAcademics().stream()
            .filter(
                academic -> {
                  var firstMarker =
                      model.getProjects().stream()
                          .filter(
                              p -> p.getSupervisor() != null && p.getSupervisor().equals(academic))
                          .count();

                  var secondMarker =
                      model.getProjects().stream()
                          .filter(
                              p ->
                                  p.getSecondMarker() != null
                                      && p.getSecondMarker().equals(academic))
                          .count();
                  var thirdMarker =
                      model.getProjects().stream()
                          .filter(
                              p ->
                                  p.getThirdMarker() != null && p.getThirdMarker().equals(academic))
                          .count();

                  var supervisoryLoad =
                      academic.getProjects().stream().filter(p -> p.getStudent() != null).count();

                  var markingLoad = firstMarker + secondMarker + thirdMarker;

                  if (markingLoad < supervisoryLoad) {
                    return true;
                  }
                  return false;
                })
            .count();

    System.out.println("Underbooked academics: " + underbookedAcademics);

    return underbookedAcademics;
  }

  @Override
  public String getName() {
    return "Minimise underbooked markers";
  }

  // should calculate how many projects we have
}
