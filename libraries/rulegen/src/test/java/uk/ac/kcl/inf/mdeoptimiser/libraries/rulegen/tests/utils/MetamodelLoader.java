package uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.tests.utils;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.BasePathSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.MetaModelSpec;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.Optimisation;
import uk.ac.kcl.inf.mdeoptimiser.languages.mopt.ProblemSpec;
import uk.ac.kcl.inf.mdeoptimiser.libraries.rulegen.metamodel.Multiplicity;

public class MetamodelLoader {

  HenshinResourceSet henshinResourceSet;
  EPackage theMetamodel;
  Optimisation model;

  public HenshinResourceSet getResourceSet() {
    if (henshinResourceSet == null) {
      henshinResourceSet = new HenshinResourceSet(model.getProblem().getBasepath().getLocation());
    }

    return henshinResourceSet;
  }

  public EPackage getMetamodel() {
    if (theMetamodel == null) {
      theMetamodel =
          this.getResourceSet()
              .registerDynamicEPackages(model.getProblem().getMetamodel().getLocation())
              .get(0);
    }

    return theMetamodel;
  }

  public EPackage fakeOptimisationModel(String metamodelName, List<Multiplicity> multiplicities) {
    this.model = mock(Optimisation.class);
    var basepath = mock(BasePathSpec.class);
    var metamodel = mock(MetaModelSpec.class);
    var problem = mock(ProblemSpec.class);

    when(model.getProblem()).thenReturn(problem);
    when(basepath.getLocation()).thenReturn("src/test/resources/");
    when(metamodel.getLocation()).thenReturn(metamodelName);

    when(model.getProblem().getBasepath()).thenReturn(basepath);
    when(model.getProblem().getMetamodel()).thenReturn(metamodel);

    return new MetamodelGenerator(getMetamodel()).generate(multiplicities);
  }

  public EPackage fakeCraMetamodel() {

    var multiplicities = new LinkedList<Multiplicity>();

    return this.fakeOptimisationModel("architectureCRA.ecore", multiplicities);
  }

  public EPackage fakeSprintPlaningMetamodel() {

    var multiplicities = new LinkedList<Multiplicity>();

    return this.fakeOptimisationModel("SprintPlanning.ecore", multiplicities);
  }

  public EPackage fakeOrchestrationMetamodel() {

    var multiplicities = new LinkedList<Multiplicity>();

    return this.fakeOptimisationModel("OrchestrationMM.ecore", multiplicities);
  }

  public EPackage fakeNRPMetamodel() {

    var multiplicities = new LinkedList<Multiplicity>();

    return this.fakeOptimisationModel("nextReleaseProblem.ecore", multiplicities);
  }

  public EPackage fakeGenericMetamodel() {

    var multiplicities = new LinkedList<Multiplicity>();

    return this.fakeOptimisationModel("rulegen.ecore", multiplicities);
  }

  public EPackage fakeGenericIterativeMetamodel() {

    var multiplicities = new LinkedList<Multiplicity>();

    return this.fakeOptimisationModel("rulegen-iterative.ecore", multiplicities);
  }
}
