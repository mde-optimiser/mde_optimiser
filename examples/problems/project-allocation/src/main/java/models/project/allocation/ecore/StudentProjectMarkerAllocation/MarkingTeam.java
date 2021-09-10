/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

import java.math.BigInteger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marking Team</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getNumber
 *       <em>Number</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getAcademics
 *       <em>Academics</em>}
 * </ul>
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkingTeam()
 * @model
 * @generated
 */
public interface MarkingTeam extends EObject {
  /**
   * Returns the value of the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Number</em>' attribute.
   * @see #setNumber(BigInteger)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkingTeam_Number()
   * @model dataType="org.eclipse.emf.ecore.xml.type.Integer"
   * @generated
   */
  BigInteger getNumber();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam#getNumber
   * <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Number</em>' attribute.
   * @see #getNumber()
   * @generated
   */
  void setNumber(BigInteger value);

  /**
   * Returns the value of the '<em><b>Academics</b></em>' reference list. The list contents are of
   * type {@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic}. It is
   * bidirectional and its opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getMarkingTeam
   * <em>Marking Team</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Academics</em>' reference list.
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getMarkingTeam_Academics()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getMarkingTeam
   * @model opposite="markingTeam"
   * @generated
   */
  EList<Academic> getAcademics();
} // MarkingTeam
