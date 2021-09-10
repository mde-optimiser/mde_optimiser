package models.project.allocation.fitness;

import java.util.Comparator;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;

public class AcademicComparator implements Comparator<Academic> {

  @Override
  public int compare(Academic o1, Academic o2) {

    int retVal = o1.getEmail().compareTo(o2.getEmail());
    if (retVal != 0) {
      return retVal;
    }
    return o2.getEmail().compareTo(o1.getEmail());
  }
}
