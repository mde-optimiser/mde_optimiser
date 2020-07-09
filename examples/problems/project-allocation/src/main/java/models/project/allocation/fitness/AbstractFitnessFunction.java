package models.project.allocation.fitness;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;

public class AbstractFitnessFunction {

  private static final LinkedHashMap<MarkerAllocationModel, ArrayList<TreeSet<Academic>>> teams =
      new LinkedHashMap<>();

  public ArrayList<TreeSet<Academic>> getTeams(MarkerAllocationModel model) {

    // Is it cached?
    if (teams.containsKey(model)) {
      var value = teams.get(model);

      if (teams.size() > 500) {
        teams.clear();
      }

      return value;
    }

    var modelTeams = new ArrayList<TreeSet<Academic>>();

    model
        .getAcademics()
        .forEach(
            a -> {
              var team = new TreeSet<Academic>(new AcademicComparator());

              team.add(a);

              a.getProjects()
                  .forEach(
                      p -> {
                        if (p.getSecondMarker() != null) {
                          team.add(p.getSecondMarker());
                        }
                      });

              if (team.size() > 1 && !teamAlreadyExists(modelTeams, team)) {
                modelTeams.add(team);
              }
            });

    // Cache the calculated model teams
    this.teams.put(model, modelTeams);

    return modelTeams;
  }

  private boolean teamAlreadyExists(ArrayList<TreeSet<Academic>> teams, TreeSet<Academic> team) {

    for (var existingTeam : teams) {
      if (existingTeam.equals(team)) {
        return true;
      }
    }

    return false;
  }
}
