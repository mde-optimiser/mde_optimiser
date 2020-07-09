/** */
package models.project.allocation.ecore.StudentProjectMarkerAllocation.impl;

import java.util.Collection;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Academic;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkerAllocationModel;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.MarkingTeam;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Project;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.Student;
import models.project.allocation.ecore.StudentProjectMarkerAllocation.StudentProjectMarkerAllocationPackage;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 *
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Marker Allocation Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>The following features are implemented:
 *
 * <ul>
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl#getStudents
 *       <em>Students</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl#getProjects
 *       <em>Projects</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl#getAcademics
 *       <em>Academics</em>}
 *   <li>{@link
 *       models.project.allocation.ecore.StudentProjectMarkerAllocation.impl.MarkerAllocationModelImpl#getMarkingTeams
 *       <em>Marking Teams</em>}
 * </ul>
 *
 * @generated
 */
public class MarkerAllocationModelImpl extends MinimalEObjectImpl.Container
    implements MarkerAllocationModel {
  /**
   * The cached value of the '{@link #getStudents() <em>Students</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getStudents()
   * @generated
   * @ordered
   */
  protected EList<Student> students;

  /**
   * The cached value of the '{@link #getProjects() <em>Projects</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getProjects()
   * @generated
   * @ordered
   */
  protected EList<Project> projects;

  /**
   * The cached value of the '{@link #getAcademics() <em>Academics</em>}' containment reference
   * list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getAcademics()
   * @generated
   * @ordered
   */
  protected EList<Academic> academics;

  /**
   * The cached value of the '{@link #getMarkingTeams() <em>Marking Teams</em>}' containment
   * reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @see #getMarkingTeams()
   * @generated
   * @ordered
   */
  protected EList<MarkingTeam> markingTeams;

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  protected MarkerAllocationModelImpl() {
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
  protected EClass eStaticClass() {
    return StudentProjectMarkerAllocationPackage.Literals.MARKER_ALLOCATION_MODEL;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Student> getStudents() {
    if (students == null) {
      students =
          new EObjectContainmentEList<Student>(
              Student.class,
              this,
              StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__STUDENTS);
    }
    return students;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Project> getProjects() {
    if (projects == null) {
      projects =
          new EObjectContainmentEList<Project>(
              Project.class,
              this,
              StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__PROJECTS);
    }
    return projects;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<Academic> getAcademics() {
    if (academics == null) {
      academics =
          new EObjectContainmentEList<Academic>(
              Academic.class,
              this,
              StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__ACADEMICS);
    }
    return academics;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public EList<MarkingTeam> getMarkingTeams() {
    if (markingTeams == null) {
      markingTeams =
          new EObjectContainmentEList<MarkingTeam>(
              MarkingTeam.class,
              this,
              StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__MARKING_TEAMS);
    }
    return markingTeams;
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(
      InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__STUDENTS:
        return ((InternalEList<?>) getStudents()).basicRemove(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__PROJECTS:
        return ((InternalEList<?>) getProjects()).basicRemove(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__ACADEMICS:
        return ((InternalEList<?>) getAcademics()).basicRemove(otherEnd, msgs);
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__MARKING_TEAMS:
        return ((InternalEList<?>) getMarkingTeams()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__STUDENTS:
        return getStudents();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__PROJECTS:
        return getProjects();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__ACADEMICS:
        return getAcademics();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__MARKING_TEAMS:
        return getMarkingTeams();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__STUDENTS:
        getStudents().clear();
        getStudents().addAll((Collection<? extends Student>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__PROJECTS:
        getProjects().clear();
        getProjects().addAll((Collection<? extends Project>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__ACADEMICS:
        getAcademics().clear();
        getAcademics().addAll((Collection<? extends Academic>) newValue);
        return;
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__MARKING_TEAMS:
        getMarkingTeams().clear();
        getMarkingTeams().addAll((Collection<? extends MarkingTeam>) newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__STUDENTS:
        getStudents().clear();
        return;
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__PROJECTS:
        getProjects().clear();
        return;
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__ACADEMICS:
        getAcademics().clear();
        return;
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__MARKING_TEAMS:
        getMarkingTeams().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   *
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   *
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__STUDENTS:
        return students != null && !students.isEmpty();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__PROJECTS:
        return projects != null && !projects.isEmpty();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__ACADEMICS:
        return academics != null && !academics.isEmpty();
      case StudentProjectMarkerAllocationPackage.MARKER_ALLOCATION_MODEL__MARKING_TEAMS:
        return markingTeams != null && !markingTeams.isEmpty();
    }
    return super.eIsSet(featureID);
  }
} // MarkerAllocationModelImpl
