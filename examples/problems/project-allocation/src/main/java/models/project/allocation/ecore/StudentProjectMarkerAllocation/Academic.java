/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Academic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getProjects
 *       <em>Projects</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getSecondMarker
 *       <em>Second Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getThirdMarker
 *       <em>Third Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getMarkingTeam
 *       <em>Marking Team</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getName
 *       <em>Name</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getNumber
 *       <em>Number</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getEmail
 *       <em>Email</em>}
 * </ul>
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic()
 * @model
 * @generated
 */
public interface Academic extends EObject {
  /**
   * Returns the value of the '<em><b>Projects</b></em>' reference list. The list contents are of
   * type {@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project}. It is
   * bidirectional and its opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSupervisor
   * <em>Supervisor</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Projects</em>' reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_Projects()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSupervisor
   * @model opposite="supervisor"
   * @generated
   */
  EList<Project> getProjects();

  /**
   * Returns the value of the '<em><b>Second Marker</b></em>' reference list. The list contents are
   * of type {@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project}. It is
   * bidirectional and its opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSecondMarker
   * <em>Second Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Second Marker</em>' reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_SecondMarker()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSecondMarker
   * @model opposite="secondMarker" required="true"
   * @generated
   */
  EList<Project> getSecondMarker();

  /**
   * Returns the value of the '<em><b>Third Marker</b></em>' reference list. The list contents are
   * of type {@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project}. It is
   * bidirectional and its opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getThirdMarker <em>Third
   * Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Third Marker</em>' reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_ThirdMarker()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getThirdMarker
   * @model opposite="thirdMarker" required="true"
   * @generated
   */
  EList<Project> getThirdMarker();

  /**
   * Returns the value of the '<em><b>Marking Team</b></em>' reference list. The list contents are
   * of type {@link models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam}. It
   * is bidirectional and its opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getAcademics
   * <em>Academics</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Marking Team</em>' reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_MarkingTeam()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getAcademics
   * @model opposite="academics"
   * @generated
   */
  EList<MarkingTeam> getMarkingTeam();

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getName <em>Name</em>}'
   * attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Number</em>' attribute.
   * @see #setNumber(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_Number()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getNumber();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getNumber
   * <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Number</em>' attribute.
   * @see #getNumber()
   * @generated
   */
  void setNumber(String value);

  /**
   * Returns the value of the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Email</em>' attribute.
   * @see #setEmail(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getAcademic_Email()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getEmail();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getEmail
   * <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Email</em>' attribute.
   * @see #getEmail()
   * @generated
   */
  void setEmail(String value);
} // Academic
