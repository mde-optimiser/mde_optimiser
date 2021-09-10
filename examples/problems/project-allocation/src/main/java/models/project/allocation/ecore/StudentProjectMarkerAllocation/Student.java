/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getProject
 *       <em>Project</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getEmail
 *       <em>Email</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getName
 *       <em>Name</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getNumber
 *       <em>Number</em>}
 * </ul>
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getStudent()
 * @model
 * @generated
 */
public interface Student extends EObject {
  /**
   * Returns the value of the '<em><b>Project</b></em>' reference. It is bidirectional and its
   * opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStudent
   * <em>Student</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Project</em>' reference.
   * @see #setProject(Project)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getStudent_Project()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStudent
   * @model opposite="student" required="true"
   * @generated
   */
  Project getProject();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getProject
   * <em>Project</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Project</em>' reference.
   * @see #getProject()
   * @generated
   */
  void setProject(Project value);

  /**
   * Returns the value of the '<em><b>Email</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Email</em>' attribute.
   * @see #setEmail(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getStudent_Email()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getEmail();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getEmail
   * <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Email</em>' attribute.
   * @see #getEmail()
   * @generated
   */
  void setEmail(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getStudent_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getName <em>Name</em>}'
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
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getStudent_Number()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getNumber();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getNumber
   * <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Number</em>' attribute.
   * @see #getNumber()
   * @generated
   */
  void setNumber(String value);
} // Student
