/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

import models.project.allocation.ecore.StudentProjectMarkerAllocation.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class StudentProjectMarkerAllocationFactoryImpl extends EFactoryImpl
    implements StudentProjectMarkerAllocationFactory {
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public static StudentProjectMarkerAllocationFactory init() {
    try {
      StudentProjectMarkerAllocationFactory theStudentProjectMarkerAllocationFactory =
          (StudentProjectMarkerAllocationFactory)
              EPackage.Registry.INSTANCE.getEFactory(StudentProjectMarkerAllocationPackage.eNS_URI);
      if (theStudentProjectMarkerAllocationFactory != null) {
        return theStudentProjectMarkerAllocationFactory;
      }
    } catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new StudentProjectMarkerAllocationFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  public StudentProjectMarkerAllocationFactoryImpl() {
    super();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case StudentProjectMarkerAllocationPackage.ACADEMIC:
        return createAcademic();
      case StudentProjectMarkerAllocationPackage.PROJECT:
        return createProject();
      case StudentProjectMarkerAllocationPackage.STUDENT:
        return createStudent();
      case StudentProjectMarkerAllocationPackage.MARKING_TEAM:
        return createMarkingTeam();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL:
        return createMarkerAllocationModel();
      default:
        throw new IllegalArgumentException(
            "The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Academic createAcademic() {
    AcademicImpl academic = new AcademicImpl();
    return academic;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Project createProject() {
    ProjectImpl project = new ProjectImpl();
    return project;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Student createStudent() {
    StudentImpl student = new StudentImpl();
    return student;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public MarkingTeam createMarkingTeam() {
    MarkingTeamImpl markingTeam = new MarkingTeamImpl();
    return markingTeam;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public MarkerAllocationModel createMarkerAllocationModel() {
    MarkerAllocationModelImpl markerAllocationModel = new MarkerAllocationModelImpl();
    return markerAllocationModel;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public StudentProjectMarkerAllocationPackage getStudentProjectMarkerAllocationPackage() {
    return (StudentProjectMarkerAllocationPackage) getEPackage();
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @deprecated
   * @generated
   */
  @Deprecated
  public static StudentProjectMarkerAllocationPackage getPackage() {
    return StudentProjectMarkerAllocationPackage.eINSTANCE;
  }
} // StudentProjectMarkerAllocationFactoryImpl
