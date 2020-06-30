/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marker Allocation Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getStudents
 *       <em>Students</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getProjects
 *       <em>Projects</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getAcademics
 *       <em>Academics</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel#getMarkingTeams
 *       <em>Marking Teams</em>}
 * </ul>
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkerAllocationModel()
 * @model
 * @generated
 */
public interface MarkerAllocationModel extends EObject {
  /**
   * Returns the value of the '<em><b>Students</b></em>' containment reference list. The list
   * contents are of type {@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Students</em>' containment reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkerAllocationModel_Students()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Student> getStudents();

  /**
   * Returns the value of the '<em><b>Projects</b></em>' containment reference list. The list
   * contents are of type {@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Projects</em>' containment reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkerAllocationModel_Projects()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Project> getProjects();

  /**
   * Returns the value of the '<em><b>Academics</b></em>' containment reference list. The list
   * contents are of type {@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Academics</em>' containment reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkerAllocationModel_Academics()
   * @model containment="true" required="true"
   * @generated
   */
  EList<Academic> getAcademics();

  /**
   * Returns the value of the '<em><b>Marking Teams</b></em>' containment reference list. The list
   * contents are of type {@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Marking Teams</em>' containment reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkerAllocationModel_MarkingTeams()
   * @model containment="true"
   * @generated
   */
  EList<MarkingTeam> getMarkingTeams();
} // MarkerAllocationModel
