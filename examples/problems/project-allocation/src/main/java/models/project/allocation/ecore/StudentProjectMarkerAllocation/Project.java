/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation;

import org.eclipse.emf.ecore.EObject;

/**
 *
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Project</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are supported:
 *
 * <ul>
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSupervisor
 *       <em>Supervisor</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStudent
 *       <em>Student</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSecondMarker
 *       <em>Second Marker</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getThirdMarker
 *       <em>Third Marker</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getTitle
 *       <em>Title</em>}
 *   <li>{@link models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStage
 *       <em>Stage</em>}
 * </ul>
 *
 * @see
 *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject()
 * @model
 * @generated
 */
public interface Project extends EObject {
  /**
   * Returns the value of the '<em><b>Supervisor</b></em>' reference. It is bidirectional and its
   * opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getProjects
   * <em>Projects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Supervisor</em>' reference.
   * @see #setSupervisor(Academic)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject_Supervisor()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getProjects
   * @model opposite="projects" required="true"
   * @generated
   */
  Academic getSupervisor();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSupervisor
   * <em>Supervisor</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Supervisor</em>' reference.
   * @see #getSupervisor()
   * @generated
   */
  void setSupervisor(Academic value);

  /**
   * Returns the value of the '<em><b>Student</b></em>' reference. It is bidirectional and its
   * opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getProject
   * <em>Project</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Student</em>' reference.
   * @see #setStudent(Student)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject_Student()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Student#getProject
   * @model opposite="project"
   * @generated
   */
  Student getStudent();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStudent
   * <em>Student</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Student</em>' reference.
   * @see #getStudent()
   * @generated
   */
  void setStudent(Student value);

  /**
   * Returns the value of the '<em><b>Second Marker</b></em>' reference. It is bidirectional and its
   * opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getSecondMarker
   * <em>Second Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Second Marker</em>' reference.
   * @see #setSecondMarker(Academic)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject_SecondMarker()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getSecondMarker
   * @model opposite="secondMarker" required="true"
   * @generated
   */
  Academic getSecondMarker();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getSecondMarker
   * <em>Second Marker</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Second Marker</em>' reference.
   * @see #getSecondMarker()
   * @generated
   */
  void setSecondMarker(Academic value);

  /**
   * Returns the value of the '<em><b>Third Marker</b></em>' reference. It is bidirectional and its
   * opposite is '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getThirdMarker
   * <em>Third Marker</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Third Marker</em>' reference.
   * @see #setThirdMarker(Academic)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject_ThirdMarker()
   * @see models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic#getThirdMarker
   * @model opposite="thirdMarker" required="true"
   * @generated
   */
  Academic getThirdMarker();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getThirdMarker <em>Third
   * Marker</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Third Marker</em>' reference.
   * @see #getThirdMarker()
   * @generated
   */
  void setThirdMarker(Academic value);

  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject_Title()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getTitle
   * <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Stage</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @return the value of the '<em>Stage</em>' attribute.
   * @see #setStage(String)
   * @see
   *     models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage#getProject_Stage()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   * @generated
   */
  String getStage();

  /**
   * Sets the value of the '{@link
   * models.project.allocation.ecore.StudentProjectMarkerAllocation.Project#getStage
   * <em>Stage</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @param value the new value of the '<em>Stage</em>' attribute.
   * @see #getStage()
   * @generated
   */
  void setStage(String value);
} // Project
