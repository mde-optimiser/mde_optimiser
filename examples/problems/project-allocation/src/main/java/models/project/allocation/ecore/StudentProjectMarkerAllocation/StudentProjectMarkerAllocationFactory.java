/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

import org.eclipse.emf.ecore.EFactory;

/**
 *
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model. It provides a create method for each non-abstract class of the
 * model.
 * <!-- end-user-doc -->
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage
 * @generated
 */
public interface StudentProjectMarkerAllocationFactory extends EFactory {
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  StudentProjectMarkerAllocationFactory eINSTANCE =
      models.project.allocation.ecore.StudentProjectMarkerAllocation.impl
          .StudentProjectMarkerAllocationFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Academic</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return a new object of class '<em>Academic</em>'.
   * @generated
   */
  Academic createAcademic();

  /**
   * Returns a new object of class '<em>Project</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return a new object of class '<em>Project</em>'.
   * @generated
   */
  Project createProject();

  /**
   * Returns a new object of class '<em>Student</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return a new object of class '<em>Student</em>'.
   * @generated
   */
  Student createStudent();

  /**
   * Returns a new object of class '<em>Marking Team</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return a new object of class '<em>Marking Team</em>'.
   * @generated
   */
  MarkingTeam createMarkingTeam();

  /**
   * Returns a new object of class '<em>Marker Allocation Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return a new object of class '<em>Marker Allocation Model</em>'.
   * @generated
   */
  MarkerAllocationModel createMarkerAllocationModel();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the package supported by this factory.
   * @generated
   */
  StudentProjectMarkerAllocationPackage getStudentProjectMarkerAllocationPackage();
} // StudentProjectMarkerAllocationFactory
