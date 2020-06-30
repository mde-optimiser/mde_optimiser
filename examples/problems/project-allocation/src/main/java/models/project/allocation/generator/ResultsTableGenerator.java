package models.project.allocation.generator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.ArrayList;
import java.util.TreeSet;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Project;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage;
import models.project.allocation.fitness.AcademicComparator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ResultsTableGenerator {

  ResourceSet resourceSet;
  static Injector injector = Guice.createInjector();

  public static void main(String args[]) {
    var app = injector.getInstance(ResultsTableGenerator.class);

    try {
      app.run();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void run() throws Exception {

    this.resourceSet = new ResourceSetImpl();

    resourceSet
        .getResourceFactoryRegistry()
        .getExtensionToFactoryMap()
        .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

    resourceSet
        .getPackageRegistry()
        .put(
            StudentProjectMarkerAllocationPackage.eNS_URI,
            StudentProjectMarkerAllocationPackage.eINSTANCE);

    var resource =
        resourceSet.getResource(
            URI.createURI("./src/main/resources/results/batch-03A83D382.xmi"), true);

    var model = (MarkerAllocationModel) resource.getContents().get(0);

    serialiseResults(model);
  }

  public int countTeams(Academic academic, ArrayList<TreeSet<Academic>> teams) {

    var markingTeams = 0;

    for (var team : teams) {
      if (team.contains(academic)) {
        markingTeams += 1;
      }
    }

    return markingTeams;
  }

  public void serialiseResults(MarkerAllocationModel model) {

    model
        .getAcademics()
        .forEach(
            a -> {
              for (Project project : a.getSecondMarker()) {
                System.out.println(
                    String.format(
                        "%s is 2nd marker on project %s for student %s",
                        a.getName(), project.getTitle(), project.getStudent().getName()));
              }
              System.out.println("");
              System.out.println("");
              System.out.println("");
            });

    var teams = this.getTeams(model);

    model
        .getAcademics()
        .forEach(
            a -> {
              System.out.println(
                  String.format(
                      "%s has %s projects appears in %s teams and is second supervisor on %s projects",
                      a.getName(),
                      a.getProjects().size(),
                      this.countTeams(a, teams),
                      a.getSecondMarker().size()));
            });

    var counter = 1;

    for (TreeSet<Academic> team : teams) {
      System.out.println(String.format("Team %s", counter++));
      for (var academic : team) {
        System.out.println(String.format("%s", academic.getName()));
      }

      System.out.println("");
      System.out.println("");
      System.out.println("");
    }
  }

  public ArrayList<TreeSet<Academic>> getTeams(MarkerAllocationModel model) {

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
