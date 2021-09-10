package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils;

import java.util.List;
import java.util.stream.Collectors;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.exceptions.MultiplicityException;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;

public class MetamodelGenerator {

  EPackage metamodel;

  public MetamodelGenerator(EPackage metamodel) {
    this.metamodel = metamodel;
  }

  public EPackage generate(List<Multiplicity> multiplicities) {

    multiplicities.forEach(
        multiplicity -> {
          updateEdgeLowerBound(multiplicity);
          updateEdgeUpperBound(multiplicity);
        });

    return this.metamodel;
  }

  private void updateEdgeLowerBound(Multiplicity multiplicity) {
    var edge = getEdge(multiplicity);

    edge.setLowerBound(multiplicity.lower);

    if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
      throw new MultiplicityException(multiplicity);
    }
  }

  private void updateEdgeUpperBound(Multiplicity multiplicity) {
    var edge = getEdge(multiplicity);

    edge.setUpperBound(multiplicity.upper);

    if (!Multiplicity.checkMultiplicityRangeValidity(edge)) {
      throw new MultiplicityException(multiplicity);
    }
  }

  public EReference getEdge(Multiplicity multiplicity) {
    return getEdge(multiplicity.source, multiplicity.edge);
  }

  public EReference getEdge(String edgeSource, String edgeName) {
    var container =
        (EClass)
            metamodel.getEClassifiers().stream()
                .filter(classifier -> classifier.getName().equals(edgeSource))
                .findFirst()
                .get();

    var references =
        container.getEAllStructuralFeatures().stream()
            .filter(reference -> reference.getName().equals(edgeName))
            .collect(Collectors.toList());

    return (EReference) references.get(0);
  }
}
