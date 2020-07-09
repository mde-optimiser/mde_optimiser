package models.project.allocation.fitness;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.IGuidanceFunction;
import uk.ac.kcl.inf.mdeoptimiser.libraries.core.optimisation.interpreter.guidance.Solution;

public class MinimiseOverbookedMarkers implements IGuidanceFunction {

  @Override
  public double computeFitness(Solution solution) {

    var model = (MarkerAllocationModel) solution.getModel();

    var overbookedAcademics =
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

                  // println("Marking load: " + markingLoad)
                  // println("Supervisory load: " + supervisoryLoad)

                  if (markingLoad > supervisoryLoad) {
                    // println("Overbooked academic: %s supervised %s
                    // marking".format(supervisoryLoad.toString, markingLoad.toString))
                    return true;
                  }
                  return false;
                })
            .count();

    System.out.println("Overbooked academics: " + overbookedAcademics);

    return overbookedAcademics;
  }

  @Override
  public String getName() {
    return "Minimise overbooked markers";
  }
}
